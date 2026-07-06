package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.utils.SerialExecutor;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SystemAlarmDispatcher implements ExecutionListener {
    public static final int DEFAULT_START_ID = 0;
    public static final String KEY_START_ID = "KEY_START_ID";
    public static final String PROCESS_COMMAND_TAG = "ProcessCommand";
    public static final String TAG = Logger.tagWithPrefix("SystemAlarmDispatcher");
    public final CommandHandler mCommandHandler;

    @Nullable
    public CommandsCompletedListener mCompletedListener;
    public final Context mContext;
    public Intent mCurrentIntent;
    public final List<Intent> mIntents;
    public final Handler mMainHandler;
    public final Processor mProcessor;
    public final TaskExecutor mTaskExecutor;
    public final WorkManagerImpl mWorkManager;
    public final WorkTimer mWorkTimer;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AddRunnable implements Runnable {
        public final SystemAlarmDispatcher mDispatcher;
        public final Intent mIntent;
        public final int mStartId;

        public AddRunnable(@NonNull SystemAlarmDispatcher dispatcher, @NonNull Intent intent, int startId) {
            this.mDispatcher = dispatcher;
            this.mIntent = intent;
            this.mStartId = startId;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mDispatcher.add(this.mIntent, this.mStartId);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface CommandsCompletedListener {
        void onAllCommandsCompleted();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DequeueAndCheckForCompletion implements Runnable {
        public final SystemAlarmDispatcher mDispatcher;

        public DequeueAndCheckForCompletion(@NonNull SystemAlarmDispatcher dispatcher) {
            this.mDispatcher = dispatcher;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mDispatcher.dequeueAndCheckForCompletion();
        }
    }

    public SystemAlarmDispatcher(@NonNull Context context) {
        this(context, null, null);
    }

    @MainThread
    public boolean add(@NonNull final Intent intent, final int startId) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, String.format("Adding command %s (%s)", intent, Integer.valueOf(startId)), new Throwable[0]);
        assertMainThread();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            Logger.get().warning(str, "Unknown command. Ignoring", new Throwable[0]);
            return false;
        }
        if (CommandHandler.ACTION_CONSTRAINTS_CHANGED.equals(action) && hasIntentWithAction(CommandHandler.ACTION_CONSTRAINTS_CHANGED)) {
            return false;
        }
        intent.putExtra(KEY_START_ID, startId);
        synchronized (this.mIntents) {
            try {
                boolean zIsEmpty = this.mIntents.isEmpty();
                this.mIntents.add(intent);
                if (zIsEmpty) {
                    processCommand();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    public final void assertMainThread() {
        if (this.mMainHandler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }

    @MainThread
    public void dequeueAndCheckForCompletion() {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Checking if commands are complete.", new Throwable[0]);
        assertMainThread();
        synchronized (this.mIntents) {
            try {
                if (this.mCurrentIntent != null) {
                    Logger.get().debug(str, String.format("Removing command %s", this.mCurrentIntent), new Throwable[0]);
                    if (!this.mIntents.remove(0).equals(this.mCurrentIntent)) {
                        throw new IllegalStateException("Dequeue-d command is not the first.");
                    }
                    this.mCurrentIntent = null;
                }
                SerialExecutor backgroundExecutor = this.mTaskExecutor.getBackgroundExecutor();
                if (!this.mCommandHandler.hasPendingCommands() && this.mIntents.isEmpty() && !backgroundExecutor.hasPendingTasks()) {
                    Logger.get().debug(str, "No more commands & intents.", new Throwable[0]);
                    CommandsCompletedListener commandsCompletedListener = this.mCompletedListener;
                    if (commandsCompletedListener != null) {
                        commandsCompletedListener.onAllCommandsCompleted();
                    }
                } else if (!this.mIntents.isEmpty()) {
                    processCommand();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Processor getProcessor() {
        return this.mProcessor;
    }

    public TaskExecutor getTaskExecutor() {
        return this.mTaskExecutor;
    }

    public WorkManagerImpl getWorkManager() {
        return this.mWorkManager;
    }

    public WorkTimer getWorkTimer() {
        return this.mWorkTimer;
    }

    @MainThread
    public final boolean hasIntentWithAction(@NonNull String action) {
        assertMainThread();
        synchronized (this.mIntents) {
            try {
                Iterator<Intent> it = this.mIntents.iterator();
                while (it.hasNext()) {
                    if (action.equals(it.next().getAction())) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void onDestroy() {
        Logger.get().debug(TAG, "Destroying SystemAlarmDispatcher", new Throwable[0]);
        this.mProcessor.removeExecutionListener(this);
        this.mWorkTimer.onDestroy();
        this.mCompletedListener = null;
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(@NonNull String workSpecId, boolean needsReschedule) {
        postOnMainThread(new AddRunnable(this, CommandHandler.createExecutionCompletedIntent(this.mContext, workSpecId, needsReschedule), 0));
    }

    public void postOnMainThread(@NonNull Runnable runnable) {
        this.mMainHandler.post(runnable);
    }

    @MainThread
    public final void processCommand() {
        assertMainThread();
        PowerManager.WakeLock wakeLockNewWakeLock = WakeLocks.newWakeLock(this.mContext, PROCESS_COMMAND_TAG);
        try {
            wakeLockNewWakeLock.acquire();
            this.mWorkManager.getWorkTaskExecutor().executeOnBackgroundThread(new Runnable() { // from class: androidx.work.impl.background.systemalarm.SystemAlarmDispatcher.1
                @Override // java.lang.Runnable
                public void run() {
                    SystemAlarmDispatcher systemAlarmDispatcher;
                    DequeueAndCheckForCompletion dequeueAndCheckForCompletion;
                    synchronized (SystemAlarmDispatcher.this.mIntents) {
                        SystemAlarmDispatcher systemAlarmDispatcher2 = SystemAlarmDispatcher.this;
                        systemAlarmDispatcher2.mCurrentIntent = systemAlarmDispatcher2.mIntents.get(0);
                    }
                    Intent intent = SystemAlarmDispatcher.this.mCurrentIntent;
                    if (intent != null) {
                        String action = intent.getAction();
                        int intExtra = SystemAlarmDispatcher.this.mCurrentIntent.getIntExtra(SystemAlarmDispatcher.KEY_START_ID, 0);
                        Logger logger = Logger.get();
                        String str = SystemAlarmDispatcher.TAG;
                        logger.debug(str, String.format("Processing command %s, %s", SystemAlarmDispatcher.this.mCurrentIntent, Integer.valueOf(intExtra)), new Throwable[0]);
                        PowerManager.WakeLock wakeLockNewWakeLock2 = WakeLocks.newWakeLock(SystemAlarmDispatcher.this.mContext, String.format("%s (%s)", action, Integer.valueOf(intExtra)));
                        try {
                            Logger.get().debug(str, String.format("Acquiring operation wake lock (%s) %s", action, wakeLockNewWakeLock2), new Throwable[0]);
                            wakeLockNewWakeLock2.acquire();
                            SystemAlarmDispatcher systemAlarmDispatcher3 = SystemAlarmDispatcher.this;
                            systemAlarmDispatcher3.mCommandHandler.onHandleIntent(systemAlarmDispatcher3.mCurrentIntent, intExtra, systemAlarmDispatcher3);
                            Logger.get().debug(str, String.format("Releasing operation wake lock (%s) %s", action, wakeLockNewWakeLock2), new Throwable[0]);
                            wakeLockNewWakeLock2.release();
                            systemAlarmDispatcher = SystemAlarmDispatcher.this;
                            dequeueAndCheckForCompletion = new DequeueAndCheckForCompletion(systemAlarmDispatcher);
                        } catch (Throwable th) {
                            try {
                                Logger logger2 = Logger.get();
                                String str2 = SystemAlarmDispatcher.TAG;
                                logger2.error(str2, "Unexpected error in onHandleIntent", th);
                                Logger.get().debug(str2, String.format("Releasing operation wake lock (%s) %s", action, wakeLockNewWakeLock2), new Throwable[0]);
                                wakeLockNewWakeLock2.release();
                                systemAlarmDispatcher = SystemAlarmDispatcher.this;
                                dequeueAndCheckForCompletion = new DequeueAndCheckForCompletion(systemAlarmDispatcher);
                            } catch (Throwable th2) {
                                Logger.get().debug(SystemAlarmDispatcher.TAG, String.format("Releasing operation wake lock (%s) %s", action, wakeLockNewWakeLock2), new Throwable[0]);
                                wakeLockNewWakeLock2.release();
                                SystemAlarmDispatcher systemAlarmDispatcher4 = SystemAlarmDispatcher.this;
                                systemAlarmDispatcher4.postOnMainThread(new DequeueAndCheckForCompletion(systemAlarmDispatcher4));
                                throw th2;
                            }
                        }
                        systemAlarmDispatcher.postOnMainThread(dequeueAndCheckForCompletion);
                    }
                }
            });
        } finally {
            wakeLockNewWakeLock.release();
        }
    }

    public void setCompletedListener(@NonNull CommandsCompletedListener listener) {
        if (this.mCompletedListener != null) {
            Logger.get().error(TAG, "A completion listener for SystemAlarmDispatcher already exists.", new Throwable[0]);
        } else {
            this.mCompletedListener = listener;
        }
    }

    @VisibleForTesting
    public SystemAlarmDispatcher(@NonNull Context context, @Nullable Processor processor, @Nullable WorkManagerImpl workManager) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mCommandHandler = new CommandHandler(applicationContext);
        this.mWorkTimer = new WorkTimer();
        workManager = workManager == null ? WorkManagerImpl.getInstance(context) : workManager;
        this.mWorkManager = workManager;
        processor = processor == null ? workManager.getProcessor() : processor;
        this.mProcessor = processor;
        this.mTaskExecutor = workManager.getWorkTaskExecutor();
        processor.addExecutionListener(this);
        this.mIntents = new ArrayList();
        this.mCurrentIntent = null;
        this.mMainHandler = new Handler(Looper.getMainLooper());
    }
}
