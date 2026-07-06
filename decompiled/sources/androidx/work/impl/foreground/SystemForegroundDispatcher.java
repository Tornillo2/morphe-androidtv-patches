package androidx.work.impl.foreground;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SystemForegroundDispatcher implements WorkConstraintsCallback, ExecutionListener {
    public static final String ACTION_CANCEL_WORK = "ACTION_CANCEL_WORK";
    public static final String ACTION_NOTIFY = "ACTION_NOTIFY";
    public static final String ACTION_START_FOREGROUND = "ACTION_START_FOREGROUND";
    public static final String ACTION_STOP_FOREGROUND = "ACTION_STOP_FOREGROUND";
    public static final String KEY_FOREGROUND_SERVICE_TYPE = "KEY_FOREGROUND_SERVICE_TYPE";
    public static final String KEY_NOTIFICATION = "KEY_NOTIFICATION";
    public static final String KEY_NOTIFICATION_ID = "KEY_NOTIFICATION_ID";
    public static final String KEY_WORKSPEC_ID = "KEY_WORKSPEC_ID";
    public static final String TAG = Logger.tagWithPrefix("SystemFgDispatcher");

    @Nullable
    public Callback mCallback;
    public final WorkConstraintsTracker mConstraintsTracker;
    public Context mContext;
    public String mCurrentForegroundWorkSpecId;
    public final Map<String, ForegroundInfo> mForegroundInfoById;
    public final Object mLock;
    public final TaskExecutor mTaskExecutor;
    public final Set<WorkSpec> mTrackedWorkSpecs;
    public WorkManagerImpl mWorkManagerImpl;
    public final Map<String, WorkSpec> mWorkSpecById;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Callback {
        void cancelNotification(int notificationId);

        void notify(int notificationId, @NonNull Notification notification);

        void startForeground(int notificationId, int notificationType, @NonNull Notification notification);

        void stop();
    }

    public SystemForegroundDispatcher(@NonNull Context context) {
        this.mContext = context;
        this.mLock = new Object();
        WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(context);
        this.mWorkManagerImpl = workManagerImpl;
        TaskExecutor workTaskExecutor = workManagerImpl.getWorkTaskExecutor();
        this.mTaskExecutor = workTaskExecutor;
        this.mCurrentForegroundWorkSpecId = null;
        this.mForegroundInfoById = new LinkedHashMap();
        this.mTrackedWorkSpecs = new HashSet();
        this.mWorkSpecById = new HashMap();
        this.mConstraintsTracker = new WorkConstraintsTracker(this.mContext, workTaskExecutor, this);
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
    }

    @NonNull
    public static Intent createCancelWorkIntent(@NonNull Context context, @NonNull String workSpecId) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction(ACTION_CANCEL_WORK);
        intent.setData(Uri.parse(String.format("workspec://%s", workSpecId)));
        intent.putExtra("KEY_WORKSPEC_ID", workSpecId);
        return intent;
    }

    @NonNull
    public static Intent createNotifyIntent(@NonNull Context context, @NonNull String workSpecId, @NonNull ForegroundInfo info) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction(ACTION_NOTIFY);
        intent.putExtra(KEY_NOTIFICATION_ID, info.mNotificationId);
        intent.putExtra(KEY_FOREGROUND_SERVICE_TYPE, info.mForegroundServiceType);
        intent.putExtra(KEY_NOTIFICATION, info.mNotification);
        intent.putExtra("KEY_WORKSPEC_ID", workSpecId);
        return intent;
    }

    @NonNull
    public static Intent createStartForegroundIntent(@NonNull Context context, @NonNull String workSpecId, @NonNull ForegroundInfo info) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction(ACTION_START_FOREGROUND);
        intent.putExtra("KEY_WORKSPEC_ID", workSpecId);
        intent.putExtra(KEY_NOTIFICATION_ID, info.mNotificationId);
        intent.putExtra(KEY_FOREGROUND_SERVICE_TYPE, info.mForegroundServiceType);
        intent.putExtra(KEY_NOTIFICATION, info.mNotification);
        intent.putExtra("KEY_WORKSPEC_ID", workSpecId);
        return intent;
    }

    @NonNull
    public static Intent createStopForegroundIntent(@NonNull Context context) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction(ACTION_STOP_FOREGROUND);
        return intent;
    }

    public WorkManagerImpl getWorkManager() {
        return this.mWorkManagerImpl;
    }

    @MainThread
    public final void handleCancelWork(@NonNull Intent intent) {
        Logger.get().info(TAG, String.format("Stopping foreground work for %s", intent), new Throwable[0]);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        if (stringExtra == null || TextUtils.isEmpty(stringExtra)) {
            return;
        }
        this.mWorkManagerImpl.cancelWorkById(UUID.fromString(stringExtra));
    }

    @MainThread
    public final void handleNotify(@NonNull Intent intent) {
        int i = 0;
        int intExtra = intent.getIntExtra(KEY_NOTIFICATION_ID, 0);
        int intExtra2 = intent.getIntExtra(KEY_FOREGROUND_SERVICE_TYPE, 0);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        Notification notification = (Notification) intent.getParcelableExtra(KEY_NOTIFICATION);
        Logger.get().debug(TAG, String.format("Notifying with (id: %s, workSpecId: %s, notificationType: %s)", Integer.valueOf(intExtra), stringExtra, Integer.valueOf(intExtra2)), new Throwable[0]);
        if (notification == null || this.mCallback == null) {
            return;
        }
        this.mForegroundInfoById.put(stringExtra, new ForegroundInfo(intExtra, notification, intExtra2));
        if (TextUtils.isEmpty(this.mCurrentForegroundWorkSpecId)) {
            this.mCurrentForegroundWorkSpecId = stringExtra;
            this.mCallback.startForeground(intExtra, intExtra2, notification);
            return;
        }
        this.mCallback.notify(intExtra, notification);
        if (intExtra2 == 0 || Build.VERSION.SDK_INT < 29) {
            return;
        }
        Iterator<Map.Entry<String, ForegroundInfo>> it = this.mForegroundInfoById.entrySet().iterator();
        while (it.hasNext()) {
            i |= it.next().getValue().mForegroundServiceType;
        }
        ForegroundInfo foregroundInfo = this.mForegroundInfoById.get(this.mCurrentForegroundWorkSpecId);
        if (foregroundInfo != null) {
            this.mCallback.startForeground(foregroundInfo.mNotificationId, i, foregroundInfo.mNotification);
        }
    }

    @MainThread
    public final void handleStartForeground(@NonNull Intent intent) {
        Logger.get().info(TAG, String.format("Started foreground service %s", intent), new Throwable[0]);
        final String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        final WorkDatabase workDatabase = this.mWorkManagerImpl.getWorkDatabase();
        this.mTaskExecutor.executeOnBackgroundThread(new Runnable() { // from class: androidx.work.impl.foreground.SystemForegroundDispatcher.1
            @Override // java.lang.Runnable
            public void run() {
                WorkSpec workSpec = workDatabase.workSpecDao().getWorkSpec(stringExtra);
                if (workSpec == null || !workSpec.hasConstraints()) {
                    return;
                }
                synchronized (SystemForegroundDispatcher.this.mLock) {
                    SystemForegroundDispatcher.this.mWorkSpecById.put(stringExtra, workSpec);
                    SystemForegroundDispatcher.this.mTrackedWorkSpecs.add(workSpec);
                    SystemForegroundDispatcher systemForegroundDispatcher = SystemForegroundDispatcher.this;
                    systemForegroundDispatcher.mConstraintsTracker.replace(systemForegroundDispatcher.mTrackedWorkSpecs);
                }
            }
        });
    }

    @MainThread
    public void handleStop(@NonNull Intent intent) {
        Logger.get().info(TAG, "Stopping foreground service", new Throwable[0]);
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.stop();
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(@NonNull List<String> workSpecIds) {
        if (workSpecIds.isEmpty()) {
            return;
        }
        for (String str : workSpecIds) {
            Logger.get().debug(TAG, String.format("Constraints unmet for WorkSpec %s", str), new Throwable[0]);
            this.mWorkManagerImpl.stopForegroundWork(str);
        }
    }

    @MainThread
    public void onDestroy() {
        this.mCallback = null;
        synchronized (this.mLock) {
            this.mConstraintsTracker.reset();
        }
        this.mWorkManagerImpl.getProcessor().removeExecutionListener(this);
    }

    @Override // androidx.work.impl.ExecutionListener
    @MainThread
    public void onExecuted(@NonNull String workSpecId, boolean needsReschedule) {
        Map.Entry<String, ForegroundInfo> entry;
        synchronized (this.mLock) {
            try {
                WorkSpec workSpecRemove = this.mWorkSpecById.remove(workSpecId);
                if (workSpecRemove != null ? this.mTrackedWorkSpecs.remove(workSpecRemove) : false) {
                    this.mConstraintsTracker.replace(this.mTrackedWorkSpecs);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        ForegroundInfo foregroundInfoRemove = this.mForegroundInfoById.remove(workSpecId);
        if (workSpecId.equals(this.mCurrentForegroundWorkSpecId) && this.mForegroundInfoById.size() > 0) {
            Iterator<Map.Entry<String, ForegroundInfo>> it = this.mForegroundInfoById.entrySet().iterator();
            Map.Entry<String, ForegroundInfo> next = it.next();
            while (true) {
                entry = next;
                if (!it.hasNext()) {
                    break;
                } else {
                    next = it.next();
                }
            }
            this.mCurrentForegroundWorkSpecId = entry.getKey();
            if (this.mCallback != null) {
                ForegroundInfo value = entry.getValue();
                this.mCallback.startForeground(value.mNotificationId, value.mForegroundServiceType, value.mNotification);
                this.mCallback.cancelNotification(value.mNotificationId);
            }
        }
        Callback callback = this.mCallback;
        if (foregroundInfoRemove == null || callback == null) {
            return;
        }
        Logger.get().debug(TAG, String.format("Removing Notification (id: %s, workSpecId: %s ,notificationType: %s)", Integer.valueOf(foregroundInfoRemove.mNotificationId), workSpecId, Integer.valueOf(foregroundInfoRemove.mForegroundServiceType)), new Throwable[0]);
        callback.cancelNotification(foregroundInfoRemove.mNotificationId);
    }

    public void onStartCommand(@NonNull Intent intent) {
        String action = intent.getAction();
        if (ACTION_START_FOREGROUND.equals(action)) {
            handleStartForeground(intent);
            handleNotify(intent);
        } else if (ACTION_NOTIFY.equals(action)) {
            handleNotify(intent);
        } else if (ACTION_CANCEL_WORK.equals(action)) {
            handleCancelWork(intent);
        } else if (ACTION_STOP_FOREGROUND.equals(action)) {
            handleStop(intent);
        }
    }

    @MainThread
    public void setCallback(@NonNull Callback callback) {
        if (this.mCallback != null) {
            Logger.get().error(TAG, "A callback already exists.", new Throwable[0]);
        } else {
            this.mCallback = callback;
        }
    }

    @VisibleForTesting
    public SystemForegroundDispatcher(@NonNull Context context, @NonNull WorkManagerImpl workManagerImpl, @NonNull WorkConstraintsTracker tracker) {
        this.mContext = context;
        this.mLock = new Object();
        this.mWorkManagerImpl = workManagerImpl;
        this.mTaskExecutor = workManagerImpl.getWorkTaskExecutor();
        this.mCurrentForegroundWorkSpecId = null;
        this.mForegroundInfoById = new LinkedHashMap();
        this.mTrackedWorkSpecs = new HashSet();
        this.mWorkSpecById = new HashMap();
        this.mConstraintsTracker = tracker;
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(@NonNull List<String> workSpecIds) {
    }
}
