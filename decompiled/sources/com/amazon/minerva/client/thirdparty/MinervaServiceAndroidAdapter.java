package com.amazon.minerva.client.thirdparty;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.amazon.minerva.client.thirdparty.configuration.DenyListConfiguration;
import com.amazon.minerva.client.thirdparty.kpi.PeriodicKPIReporter;
import com.amazon.minerva.client.thirdparty.metric.DataPoint;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEventBuilder;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import com.amazon.minerva.client.thirdparty.utils.MinervaLogger;
import com.amazon.minerva.client.thirdparty.utils.PredefinedKeyUtil;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MinervaServiceAndroidAdapter {
    public static final long SHUTDOWN_TIMEOUT_MS = 120000;
    public static final String THREAD_NAME = "MetricsServiceAndroid3rdParty";
    public static final MinervaLogger log = new MinervaLogger("MinervaServiceAndroidAdapter");
    public static final MinervaServiceAndroidAdapter sMinervaServiceAndroidAdapter = new MinervaServiceAndroidAdapter();
    public CustomDeviceUtil mCustomDeviceUtil;
    public DenyListConfiguration mDenyListConfiguration;
    public Handler mHandler;
    public final AtomicBoolean mIsInitialized = new AtomicBoolean(false);
    public HandlerThread mMainThread;
    public MinervaServiceManager mMinervaServiceManager;
    public PeriodicKPIReporter mPeriodicKPIReporter;

    public static MinervaServiceAndroidAdapter getInstance() {
        return sMinervaServiceAndroidAdapter;
    }

    public void flush() {
        PeriodicKPIReporter periodicKPIReporter = this.mPeriodicKPIReporter;
        if (periodicKPIReporter != null) {
            periodicKPIReporter.flush();
        }
        MinervaServiceManager minervaServiceManager = this.mMinervaServiceManager;
        if (minervaServiceManager != null) {
            minervaServiceManager.flush();
        }
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public MinervaServiceManager getMinervaServiceManager() {
        return this.mMinervaServiceManager;
    }

    public PeriodicKPIReporter getPeriodicKPIReporter() {
        return this.mPeriodicKPIReporter;
    }

    public boolean handleMessageForService(Message message) {
        MinervaServiceManager minervaServiceManager;
        Object obj = message.obj;
        if (obj != null && (obj instanceof IonMetricEvent) && (minervaServiceManager = this.mMinervaServiceManager) != null) {
            minervaServiceManager.record((IonMetricEvent) obj);
            return true;
        }
        log.error("handleMessageForService received unknown android.os.Message " + message);
        return false;
    }

    public synchronized void initialize(Context context, boolean z) {
        try {
            if (!this.mIsInitialized.get()) {
                log.info("Initialize(context) -- MinervaServiceAndroidAdapter");
                HandlerThread handlerThread = new HandlerThread(THREAD_NAME, 0);
                this.mMainThread = handlerThread;
                handlerThread.start();
                this.mHandler = new Handler(this.mMainThread.getLooper(), new Handler.Callback() { // from class: com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter.1
                    @Override // android.os.Handler.Callback
                    public boolean handleMessage(Message message) {
                        return MinervaServiceAndroidAdapter.this.handleMessageForService(message);
                    }
                });
                CustomDeviceUtil customDeviceUtil = CustomDeviceUtil.getInstance();
                this.mCustomDeviceUtil = customDeviceUtil;
                if (!customDeviceUtil.isInitialized()) {
                    this.mCustomDeviceUtil.initialize();
                }
                MinervaServiceManager minervaServiceManager = MinervaServiceManager.getInstance();
                this.mMinervaServiceManager = minervaServiceManager;
                if (!minervaServiceManager.isInitialized()) {
                    this.mMinervaServiceManager.initialize(context, new PredefinedKeyUtil(context, this.mCustomDeviceUtil), z);
                }
                PeriodicKPIReporter periodicKPIReporter = new PeriodicKPIReporter();
                this.mPeriodicKPIReporter = periodicKPIReporter;
                periodicKPIReporter.mMinervaServiceAndroidAdapter = sMinervaServiceAndroidAdapter;
                this.mIsInitialized.set(true);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public boolean isInitialized() {
        return this.mIsInitialized.get();
    }

    public void record(String str, String str2, String str3, String str4, long j, int i, List<DataPoint> list) {
        IonMetricEvent ionMetricEventBuild;
        if (!this.mIsInitialized.get()) {
            log.error("Minerva Client not properly initialized! Dropping metric event");
            return;
        }
        DenyListConfiguration denyListConfiguration = this.mMinervaServiceManager.getConfigurationManager().getMetricsConfigurationHelper().getDenyListConfiguration();
        this.mDenyListConfiguration = denyListConfiguration;
        if (denyListConfiguration.isDenylisted(str2, str3)) {
            log.debug(String.format(Locale.US, "The GroupID#SchemaID has been Denylisted. Metrics data will be dropped, GroupID: %s; SchemaID: %s", str2, str3));
            return;
        }
        MinervaLogger minervaLogger = log;
        if (minervaLogger.isDebugEnabled()) {
            StringBuffer stringBuffer = new StringBuffer("*****record, region:");
            stringBuffer.append(str);
            stringBuffer.append(", metricGroupId:");
            stringBuffer.append(str2);
            stringBuffer.append(", metricSchemaId:");
            stringBuffer.append(str3);
            stringBuffer.append(", metricEventId:");
            stringBuffer.append(str4);
            stringBuffer.append(", utcTimestamp:");
            stringBuffer.append(j);
            stringBuffer.append(", localOffsetMinutes:");
            stringBuffer.append(i);
            stringBuffer.append(", dataPoints size:");
            stringBuffer.append(list.size());
            minervaLogger.debug(stringBuffer.toString());
        }
        try {
            IonMetricEventBuilder ionMetricEventBuilder = new IonMetricEventBuilder();
            ionMetricEventBuilder.withRegion(str);
            ionMetricEventBuilder.withMetricGroupId(str2);
            ionMetricEventBuilder.withSchemaId(str3);
            ionMetricEventBuilder.withMetricEventId(str4);
            ionMetricEventBuilder.withUtcTimestamp(j);
            ionMetricEventBuilder.withLocalOffsetMinutes(i);
            ionMetricEventBuilder.withDataPoints(list);
            ionMetricEventBuild = ionMetricEventBuilder.build();
        } catch (Exception e) {
            log.error(String.format("record: failed to create IonMetricEvent from IPC parameters. %s", e.getMessage()));
            ionMetricEventBuild = null;
        }
        if (ionMetricEventBuild == null) {
            return;
        }
        log.debug("metricEvent:" + ionMetricEventBuild.toString());
        Handler handler = getHandler();
        Message messageObtainMessage = handler.obtainMessage();
        messageObtainMessage.obj = ionMetricEventBuild;
        handler.sendMessage(messageObtainMessage);
    }

    public synchronized void resetForTesting() {
        try {
            if (this.mIsInitialized.get()) {
                this.mPeriodicKPIReporter.shutdown();
                MinervaServiceManager minervaServiceManager = this.mMinervaServiceManager;
                if (minervaServiceManager != null) {
                    minervaServiceManager.shutdown();
                }
                HandlerThread handlerThread = this.mMainThread;
                if (handlerThread == null) {
                    log.warn("Shutdown: HandlerThread is null - nothing to do in shutdown.");
                } else {
                    handlerThread.quitSafely();
                    try {
                        this.mMainThread.join(SHUTDOWN_TIMEOUT_MS);
                        log.info("Shutdown: (super) Shutting down...");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        log.error("System service shutdown failed" + e.getMessage());
                    }
                }
                this.mIsInitialized.set(false);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Deprecated
    public synchronized void shutdown() {
        flush();
    }

    @Deprecated
    public void shutdownWithUpload() {
        flush();
    }

    public Future<Void> upload() {
        return this.mMinervaServiceManager.upload();
    }
}
