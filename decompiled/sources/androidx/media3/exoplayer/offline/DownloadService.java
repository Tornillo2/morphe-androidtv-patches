package androidx.media3.exoplayer.offline;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.NotificationUtil;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.offline.DownloadManager;
import androidx.media3.exoplayer.scheduler.Requirements;
import androidx.media3.exoplayer.scheduler.Scheduler;
import java.util.HashMap;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class DownloadService extends Service {
    public static final String ACTION_ADD_DOWNLOAD = "androidx.media3.exoplayer.downloadService.action.ADD_DOWNLOAD";
    public static final String ACTION_INIT = "androidx.media3.exoplayer.downloadService.action.INIT";
    public static final String ACTION_PAUSE_DOWNLOADS = "androidx.media3.exoplayer.downloadService.action.PAUSE_DOWNLOADS";
    public static final String ACTION_REMOVE_ALL_DOWNLOADS = "androidx.media3.exoplayer.downloadService.action.REMOVE_ALL_DOWNLOADS";
    public static final String ACTION_REMOVE_DOWNLOAD = "androidx.media3.exoplayer.downloadService.action.REMOVE_DOWNLOAD";
    public static final String ACTION_RESTART = "androidx.media3.exoplayer.downloadService.action.RESTART";
    public static final String ACTION_RESUME_DOWNLOADS = "androidx.media3.exoplayer.downloadService.action.RESUME_DOWNLOADS";
    public static final String ACTION_SET_REQUIREMENTS = "androidx.media3.exoplayer.downloadService.action.SET_REQUIREMENTS";
    public static final String ACTION_SET_STOP_REASON = "androidx.media3.exoplayer.downloadService.action.SET_STOP_REASON";
    public static final long DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL = 1000;
    public static final int FOREGROUND_NOTIFICATION_ID_NONE = 0;
    public static final String KEY_CONTENT_ID = "content_id";
    public static final String KEY_DOWNLOAD_REQUEST = "download_request";
    public static final String KEY_FOREGROUND = "foreground";
    public static final String KEY_REQUIREMENTS = "requirements";
    public static final String KEY_STOP_REASON = "stop_reason";
    public static final String TAG = "DownloadService";
    public static final HashMap<Class<? extends DownloadService>, DownloadManagerHelper> downloadManagerHelpers = new HashMap<>();

    @StringRes
    public final int channelDescriptionResourceId;

    @Nullable
    public final String channelId;

    @StringRes
    public final int channelNameResourceId;
    public DownloadManagerHelper downloadManagerHelper;

    @Nullable
    public final ForegroundNotificationUpdater foregroundNotificationUpdater;
    public boolean isDestroyed;
    public boolean isStopped;
    public int lastStartId;
    public boolean startedInForeground;
    public boolean taskRemoved;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DownloadManagerHelper implements DownloadManager.Listener {
        public final Context context;
        public final DownloadManager downloadManager;

        @Nullable
        public DownloadService downloadService;
        public final boolean foregroundAllowed;
        public Requirements scheduledRequirements;

        @Nullable
        public final Scheduler scheduler;
        public final Class<? extends DownloadService> serviceClass;

        public void attachService(final DownloadService downloadService) {
            Assertions.checkState(this.downloadService == null);
            this.downloadService = downloadService;
            if (this.downloadManager.initialized) {
                Util.createHandlerForCurrentOrMainLooper().postAtFrontOfQueue(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadService$DownloadManagerHelper$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        downloadService.notifyDownloads(this.f$0.downloadManager.downloads);
                    }
                });
            }
        }

        @RequiresNonNull({"scheduler"})
        public final void cancelScheduler() {
            Requirements requirements = new Requirements(0);
            if (schedulerNeedsUpdate(requirements)) {
                this.scheduler.cancel();
                this.scheduledRequirements = requirements;
            }
        }

        public void detachService(DownloadService downloadService) {
            Assertions.checkState(this.downloadService == downloadService);
            this.downloadService = null;
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public void onDownloadChanged(DownloadManager downloadManager, Download download, @Nullable Exception exc) {
            DownloadService downloadService = this.downloadService;
            if (downloadService != null) {
                downloadService.notifyDownloadChanged(download);
            }
            if (serviceMayNeedRestart() && DownloadService.needsStartedService(download.state)) {
                Log.w("DownloadService", "DownloadService wasn't running. Restarting.");
                restartService();
            }
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public void onDownloadRemoved(DownloadManager downloadManager, Download download) {
            DownloadService downloadService = this.downloadService;
            if (downloadService != null) {
                downloadService.notifyDownloadRemoved();
            }
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public final void onIdle(DownloadManager downloadManager) {
            DownloadService downloadService = this.downloadService;
            if (downloadService != null) {
                downloadService.onIdle();
            }
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public void onInitialized(DownloadManager downloadManager) {
            DownloadService downloadService = this.downloadService;
            if (downloadService != null) {
                downloadService.notifyDownloads(downloadManager.downloads);
            }
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public void onRequirementsStateChanged(DownloadManager downloadManager, Requirements requirements, int i) {
            updateScheduler();
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public void onWaitingForRequirementsChanged(DownloadManager downloadManager, boolean z) {
            if (z || downloadManager.downloadsPaused || !serviceMayNeedRestart()) {
                return;
            }
            List<Download> list = downloadManager.downloads;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).state == 0) {
                    restartService();
                    return;
                }
            }
        }

        public final void restartService() {
            if (this.foregroundAllowed) {
                try {
                    Util.startForegroundService(this.context, DownloadService.getIntent(this.context, this.serviceClass, DownloadService.ACTION_RESTART));
                    return;
                } catch (IllegalStateException unused) {
                    Log.w("DownloadService", "Failed to restart (foreground launch restriction)");
                    return;
                }
            }
            try {
                this.context.startService(DownloadService.getIntent(this.context, this.serviceClass, DownloadService.ACTION_INIT));
            } catch (IllegalStateException unused2) {
                Log.w("DownloadService", "Failed to restart (process is idle)");
            }
        }

        public final boolean schedulerNeedsUpdate(Requirements requirements) {
            return !Util.areEqual(this.scheduledRequirements, requirements);
        }

        public final boolean serviceMayNeedRestart() {
            DownloadService downloadService = this.downloadService;
            return downloadService == null || downloadService.isStopped;
        }

        public boolean updateScheduler() {
            DownloadManager downloadManager = this.downloadManager;
            boolean z = downloadManager.waitingForRequirements;
            Scheduler scheduler = this.scheduler;
            if (scheduler == null) {
                return !z;
            }
            if (!z) {
                cancelScheduler();
                return true;
            }
            Requirements requirements = downloadManager.requirementsWatcher.requirements;
            if (!scheduler.getSupportedRequirements(requirements).equals(requirements)) {
                cancelScheduler();
                return false;
            }
            if (!schedulerNeedsUpdate(requirements)) {
                return true;
            }
            if (this.scheduler.schedule(requirements, this.context.getPackageName(), DownloadService.ACTION_RESTART)) {
                this.scheduledRequirements = requirements;
                return true;
            }
            Log.w("DownloadService", "Failed to schedule restart");
            cancelScheduler();
            return false;
        }

        public DownloadManagerHelper(Context context, DownloadManager downloadManager, boolean z, @Nullable Scheduler scheduler, Class<? extends DownloadService> cls) {
            this.context = context;
            this.downloadManager = downloadManager;
            this.foregroundAllowed = z;
            this.scheduler = scheduler;
            this.serviceClass = cls;
            downloadManager.getClass();
            downloadManager.listeners.add(this);
            updateScheduler();
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onDownloadsPausedChanged(DownloadManager downloadManager, boolean z) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ForegroundNotificationUpdater {
        public final Handler handler = new Handler(Looper.getMainLooper());
        public boolean notificationDisplayed;
        public final int notificationId;
        public boolean periodicUpdatesStarted;
        public final long updateInterval;

        public ForegroundNotificationUpdater(int i, long j) {
            this.notificationId = i;
            this.updateInterval = j;
        }

        public void invalidate() {
            if (this.notificationDisplayed) {
                update();
            }
        }

        public void showNotificationIfNotAlready() {
            if (this.notificationDisplayed) {
                return;
            }
            update();
        }

        public void startPeriodicUpdates() {
            this.periodicUpdatesStarted = true;
            update();
        }

        public void stopPeriodicUpdates() {
            this.periodicUpdatesStarted = false;
            this.handler.removeCallbacksAndMessages(null);
        }

        @SuppressLint({"InlinedApi"})
        public final void update() {
            DownloadManagerHelper downloadManagerHelper = DownloadService.this.downloadManagerHelper;
            downloadManagerHelper.getClass();
            DownloadManager downloadManager = downloadManagerHelper.downloadManager;
            Notification foregroundNotification = DownloadService.this.getForegroundNotification(downloadManager.downloads, downloadManager.notMetRequirements);
            if (this.notificationDisplayed) {
                ((NotificationManager) DownloadService.this.getSystemService("notification")).notify(this.notificationId, foregroundNotification);
            } else {
                Util.setForegroundServiceNotification(DownloadService.this, this.notificationId, foregroundNotification, 1, "dataSync");
                this.notificationDisplayed = true;
            }
            if (this.periodicUpdatesStarted) {
                this.handler.removeCallbacksAndMessages(null);
                this.handler.postDelayed(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadService$ForegroundNotificationUpdater$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.update();
                    }
                }, this.updateInterval);
            }
        }
    }

    public DownloadService(int i) {
        this(i, 1000L);
    }

    public static Intent buildAddDownloadIntent(Context context, Class<? extends DownloadService> cls, DownloadRequest downloadRequest, boolean z) {
        return buildAddDownloadIntent(context, cls, downloadRequest, 0, z);
    }

    public static Intent buildPauseDownloadsIntent(Context context, Class<? extends DownloadService> cls, boolean z) {
        return getIntent(context, cls, ACTION_PAUSE_DOWNLOADS, z);
    }

    public static Intent buildRemoveAllDownloadsIntent(Context context, Class<? extends DownloadService> cls, boolean z) {
        return getIntent(context, cls, ACTION_REMOVE_ALL_DOWNLOADS, z);
    }

    public static Intent buildRemoveDownloadIntent(Context context, Class<? extends DownloadService> cls, String str, boolean z) {
        return getIntent(context, cls, ACTION_REMOVE_DOWNLOAD, z).putExtra("content_id", str);
    }

    public static Intent buildResumeDownloadsIntent(Context context, Class<? extends DownloadService> cls, boolean z) {
        return getIntent(context, cls, ACTION_RESUME_DOWNLOADS, z);
    }

    public static Intent buildSetRequirementsIntent(Context context, Class<? extends DownloadService> cls, Requirements requirements, boolean z) {
        return getIntent(context, cls, ACTION_SET_REQUIREMENTS, z).putExtra("requirements", requirements);
    }

    public static Intent buildSetStopReasonIntent(Context context, Class<? extends DownloadService> cls, @Nullable String str, int i, boolean z) {
        return getIntent(context, cls, ACTION_SET_STOP_REASON, z).putExtra("content_id", str).putExtra("stop_reason", i);
    }

    public static void clearDownloadManagerHelpers() {
        downloadManagerHelpers.clear();
    }

    public static Intent getIntent(Context context, Class<? extends DownloadService> cls, String str, boolean z) {
        return getIntent(context, cls, str).putExtra("foreground", z);
    }

    public static boolean needsStartedService(int i) {
        return i == 2 || i == 5 || i == 7;
    }

    public static void sendAddDownload(Context context, Class<? extends DownloadService> cls, DownloadRequest downloadRequest, boolean z) {
        startService(context, buildAddDownloadIntent(context, cls, downloadRequest, 0, z), z);
    }

    public static void sendPauseDownloads(Context context, Class<? extends DownloadService> cls, boolean z) {
        startService(context, getIntent(context, cls, ACTION_PAUSE_DOWNLOADS, z), z);
    }

    public static void sendRemoveAllDownloads(Context context, Class<? extends DownloadService> cls, boolean z) {
        startService(context, getIntent(context, cls, ACTION_REMOVE_ALL_DOWNLOADS, z), z);
    }

    public static void sendRemoveDownload(Context context, Class<? extends DownloadService> cls, String str, boolean z) {
        startService(context, buildRemoveDownloadIntent(context, cls, str, z), z);
    }

    public static void sendResumeDownloads(Context context, Class<? extends DownloadService> cls, boolean z) {
        startService(context, getIntent(context, cls, ACTION_RESUME_DOWNLOADS, z), z);
    }

    public static void sendSetRequirements(Context context, Class<? extends DownloadService> cls, Requirements requirements, boolean z) {
        startService(context, buildSetRequirementsIntent(context, cls, requirements, z), z);
    }

    public static void sendSetStopReason(Context context, Class<? extends DownloadService> cls, @Nullable String str, int i, boolean z) {
        startService(context, buildSetStopReasonIntent(context, cls, str, i, z), z);
    }

    public static void start(Context context, Class<? extends DownloadService> cls) {
        context.startService(getIntent(context, cls, ACTION_INIT));
    }

    public static void startForeground(Context context, Class<? extends DownloadService> cls) {
        Util.startForegroundService(context, getIntent(context, cls, ACTION_INIT, true));
    }

    public static void startService(Context context, Intent intent, boolean z) {
        if (z) {
            Util.startForegroundService(context, intent);
        } else {
            context.startService(intent);
        }
    }

    public abstract DownloadManager getDownloadManager();

    public abstract Notification getForegroundNotification(List<Download> list, int i);

    @Nullable
    public abstract Scheduler getScheduler();

    public final void invalidateForegroundNotification() {
        ForegroundNotificationUpdater foregroundNotificationUpdater = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater == null || this.isDestroyed) {
            return;
        }
        foregroundNotificationUpdater.invalidate();
    }

    public final boolean isStopped() {
        return this.isStopped;
    }

    public final void notifyDownloadChanged(Download download) {
        if (this.foregroundNotificationUpdater != null) {
            if (needsStartedService(download.state)) {
                this.foregroundNotificationUpdater.startPeriodicUpdates();
            } else {
                this.foregroundNotificationUpdater.invalidate();
            }
        }
    }

    public final void notifyDownloadRemoved() {
        ForegroundNotificationUpdater foregroundNotificationUpdater = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater != null) {
            foregroundNotificationUpdater.invalidate();
        }
    }

    public final void notifyDownloads(List<Download> list) {
        if (this.foregroundNotificationUpdater != null) {
            for (int i = 0; i < list.size(); i++) {
                if (needsStartedService(list.get(i).state)) {
                    this.foregroundNotificationUpdater.startPeriodicUpdates();
                    return;
                }
            }
        }
    }

    @Override // android.app.Service
    @Nullable
    public final IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException();
    }

    @Override // android.app.Service
    public void onCreate() {
        String str = this.channelId;
        if (str != null) {
            NotificationUtil.createNotificationChannel(this, str, this.channelNameResourceId, this.channelDescriptionResourceId, 2);
        }
        Class<?> cls = getClass();
        HashMap<Class<? extends DownloadService>, DownloadManagerHelper> map = downloadManagerHelpers;
        DownloadManagerHelper downloadManagerHelper = map.get(cls);
        if (downloadManagerHelper == null) {
            boolean z = this.foregroundNotificationUpdater != null;
            Scheduler scheduler = (z && (Util.SDK_INT < 31)) ? getScheduler() : null;
            DownloadManager downloadManager = getDownloadManager();
            downloadManager.setDownloadsPaused(false);
            DownloadManagerHelper downloadManagerHelper2 = new DownloadManagerHelper(getApplicationContext(), downloadManager, z, scheduler, cls);
            map.put((Class<? extends DownloadService>) cls, downloadManagerHelper2);
            downloadManagerHelper = downloadManagerHelper2;
        }
        this.downloadManagerHelper = downloadManagerHelper;
        downloadManagerHelper.attachService(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.isDestroyed = true;
        DownloadManagerHelper downloadManagerHelper = this.downloadManagerHelper;
        downloadManagerHelper.getClass();
        downloadManagerHelper.detachService(this);
        ForegroundNotificationUpdater foregroundNotificationUpdater = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater != null) {
            foregroundNotificationUpdater.stopPeriodicUpdates();
        }
    }

    public final void onIdle() {
        ForegroundNotificationUpdater foregroundNotificationUpdater = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater != null) {
            foregroundNotificationUpdater.stopPeriodicUpdates();
        }
        DownloadManagerHelper downloadManagerHelper = this.downloadManagerHelper;
        downloadManagerHelper.getClass();
        if (downloadManagerHelper.updateScheduler()) {
            if (Util.SDK_INT >= 28 || !this.taskRemoved) {
                this.isStopped |= stopSelfResult(this.lastStartId);
            } else {
                stopSelf();
                this.isStopped = true;
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        String action;
        String stringExtra;
        DownloadManager downloadManager;
        ForegroundNotificationUpdater foregroundNotificationUpdater;
        this.lastStartId = i2;
        this.taskRemoved = false;
        if (intent != null) {
            action = intent.getAction();
            stringExtra = intent.getStringExtra("content_id");
            this.startedInForeground |= intent.getBooleanExtra("foreground", false) || ACTION_RESTART.equals(action);
        } else {
            action = null;
            stringExtra = null;
        }
        if (action == null) {
            action = ACTION_INIT;
        }
        DownloadManagerHelper downloadManagerHelper = this.downloadManagerHelper;
        downloadManagerHelper.getClass();
        downloadManager = downloadManagerHelper.downloadManager;
        switch (action) {
            case "androidx.media3.exoplayer.downloadService.action.SET_STOP_REASON":
                intent.getClass();
                if (!intent.hasExtra("stop_reason")) {
                    Log.e("DownloadService", "Ignored SET_STOP_REASON: Missing stop_reason extra");
                    break;
                } else {
                    downloadManager.setStopReason(stringExtra, intent.getIntExtra("stop_reason", 0));
                    break;
                }
                break;
            case "androidx.media3.exoplayer.downloadService.action.REMOVE_DOWNLOAD":
                if (stringExtra != null) {
                    downloadManager.removeDownload(stringExtra);
                    break;
                } else {
                    Log.e("DownloadService", "Ignored REMOVE_DOWNLOAD: Missing content_id extra");
                    break;
                }
                break;
            case "androidx.media3.exoplayer.downloadService.action.RESTART":
            case "androidx.media3.exoplayer.downloadService.action.INIT":
                break;
            case "androidx.media3.exoplayer.downloadService.action.RESUME_DOWNLOADS":
                downloadManager.setDownloadsPaused(false);
                break;
            case "androidx.media3.exoplayer.downloadService.action.REMOVE_ALL_DOWNLOADS":
                downloadManager.removeAllDownloads();
                break;
            case "androidx.media3.exoplayer.downloadService.action.ADD_DOWNLOAD":
                intent.getClass();
                DownloadRequest downloadRequest = (DownloadRequest) intent.getParcelableExtra("download_request");
                if (downloadRequest != null) {
                    downloadManager.addDownload(downloadRequest, intent.getIntExtra("stop_reason", 0));
                    break;
                } else {
                    Log.e("DownloadService", "Ignored ADD_DOWNLOAD: Missing download_request extra");
                    break;
                }
                break;
            case "androidx.media3.exoplayer.downloadService.action.SET_REQUIREMENTS":
                intent.getClass();
                Requirements requirements = (Requirements) intent.getParcelableExtra("requirements");
                if (requirements != null) {
                    downloadManager.setRequirements(requirements);
                    break;
                } else {
                    Log.e("DownloadService", "Ignored SET_REQUIREMENTS: Missing requirements extra");
                    break;
                }
                break;
            case "androidx.media3.exoplayer.downloadService.action.PAUSE_DOWNLOADS":
                downloadManager.setDownloadsPaused(true);
                break;
            default:
                Log.e("DownloadService", "Ignored unrecognized action: ".concat(action));
                break;
        }
        if (Util.SDK_INT >= 26 && this.startedInForeground && (foregroundNotificationUpdater = this.foregroundNotificationUpdater) != null) {
            foregroundNotificationUpdater.showNotificationIfNotAlready();
        }
        this.isStopped = false;
        if (downloadManager.isIdle()) {
            onIdle();
        }
        return 1;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        this.taskRemoved = true;
    }

    public DownloadService(int i, long j) {
        this(i, j, null, 0, 0);
    }

    public static Intent buildAddDownloadIntent(Context context, Class<? extends DownloadService> cls, DownloadRequest downloadRequest, int i, boolean z) {
        return getIntent(context, cls, ACTION_ADD_DOWNLOAD, z).putExtra("download_request", downloadRequest).putExtra("stop_reason", i);
    }

    public static Intent getIntent(Context context, Class<? extends DownloadService> cls, String str) {
        return new Intent(context, cls).setAction(str);
    }

    public DownloadService(int i, long j, @Nullable String str, @StringRes int i2, @StringRes int i3) {
        if (i == 0) {
            this.foregroundNotificationUpdater = null;
            this.channelId = null;
            this.channelNameResourceId = 0;
            this.channelDescriptionResourceId = 0;
            return;
        }
        this.foregroundNotificationUpdater = new ForegroundNotificationUpdater(i, j);
        this.channelId = str;
        this.channelNameResourceId = i2;
        this.channelDescriptionResourceId = i3;
    }

    public static void sendAddDownload(Context context, Class<? extends DownloadService> cls, DownloadRequest downloadRequest, int i, boolean z) {
        startService(context, buildAddDownloadIntent(context, cls, downloadRequest, i, z), z);
    }
}
