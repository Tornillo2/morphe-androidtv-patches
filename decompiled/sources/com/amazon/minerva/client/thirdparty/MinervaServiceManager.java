package com.amazon.minerva.client.thirdparty;

import android.content.Context;
import android.os.Build;
import com.amazon.ion.IonStruct;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.minerva.client.thirdparty.api.Predefined;
import com.amazon.minerva.client.thirdparty.configuration.ConfigurationManager;
import com.amazon.minerva.client.thirdparty.configuration.MultiProcessConfiguration;
import com.amazon.minerva.client.thirdparty.kpi.KPIConstant;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.storage.BatchCreatorManager;
import com.amazon.minerva.client.thirdparty.storage.KPIBatchCreator;
import com.amazon.minerva.client.thirdparty.storage.StorageManager;
import com.amazon.minerva.client.thirdparty.transport.MetricsTransmissionManager;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import com.amazon.minerva.client.thirdparty.utils.MinervaLogger;
import com.amazon.minerva.client.thirdparty.utils.MultiProcessUtil;
import com.amazon.minerva.client.thirdparty.utils.PredefinedKeyUtil;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.amazon.minerva.identifiers.schemaid.attribute.attributes.AttributeEnumV2;
import com.amazon.minerva.identifiers.schemaid.attribute.attributes.VersionedAttributes;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MinervaServiceManager {
    public AtomicBoolean initialized = new AtomicBoolean(false);
    public AtomicLong lastUploadCall = new AtomicLong(0);
    public BatchCreatorManager mBatchCreatorManager;
    public ConfigurationManager mConfigurationManager;
    public CustomDeviceUtil mCustomDeviceUtil;
    public KPIBatchCreator mKPIBatchCreator;
    public MetricsTransmissionManager mMetricsTransmissionManager;
    public PredefinedKeyUtil mPredefinedKeyUtil;
    public ServiceKPIReporter mServiceKPIReporter;
    public StorageManager mStorageManager;
    public static final MinervaLogger log = new MinervaLogger("MinervaServiceManager");
    public static final MinervaServiceManager INSTANCE = new MinervaServiceManager();
    public static final ValueFactory VALUE_FACTORY = IonSystemBuilder.STANDARD.build();

    public static MinervaServiceManager getInstance() {
        return INSTANCE;
    }

    public final void addPredefinedValues(IonMetricEvent ionMetricEvent) {
        IonStruct keyValuePairs = ionMetricEvent.getKeyValuePairs();
        for (Predefined predefined : Predefined.values()) {
            if (keyValuePairs.containsKey(predefined.key)) {
                keyValuePairs.put(predefined.key, VALUE_FACTORY.newSymbol(this.mPredefinedKeyUtil.getPredefinedValue(predefined, ionMetricEvent.getMetricSchemaId().stringValue())));
            }
        }
    }

    public void flush() {
        if (!this.initialized.get()) {
            log.warn("Warning! Flush called with shut down client!");
        } else {
            this.mBatchCreatorManager.flush();
            this.mKPIBatchCreator.flush();
        }
    }

    public ConfigurationManager getConfigurationManager() {
        return this.mConfigurationManager;
    }

    public synchronized void initialize(Context context, PredefinedKeyUtil predefinedKeyUtil, boolean z) {
        if (this.initialized.get()) {
            log.warn("Tried to initialize MinervaServiceManager after it has been initialized");
        } else {
            MinervaLogger minervaLogger = log;
            minervaLogger.info("Initializing MinervaServiceManager");
            if (context == null) {
                minervaLogger.error("Context cannot be null.");
                return;
            }
            if (predefinedKeyUtil == null) {
                minervaLogger.error("PredefinedKeyUtil cannot be null.");
                return;
            }
            CustomDeviceUtil customDeviceUtil = CustomDeviceUtil.getInstance();
            this.mCustomDeviceUtil = customDeviceUtil;
            if (!customDeviceUtil.isInitialized()) {
                this.mCustomDeviceUtil.initialize();
            }
            this.mPredefinedKeyUtil = predefinedKeyUtil;
            this.mConfigurationManager = new ConfigurationManager(context);
            this.mServiceKPIReporter = new ServiceKPIReporter(this.mConfigurationManager.getMetricsConfigurationHelper());
            MultiProcessConfiguration multiProcessConfiguration = this.mConfigurationManager.getMetricsConfigurationHelper().getMultiProcessConfiguration();
            String processName = MultiProcessUtil.getProcessName();
            if (!processName.isEmpty()) {
                minervaLogger.info("MinervaServiceManager is initialized by process: ".concat(processName));
            }
            String directoryToUse = MultiProcessUtil.getDirectoryToUse(multiProcessConfiguration, context, processName);
            MultiProcessUtil.clearUnusedDirectories(multiProcessConfiguration, context, processName);
            File dir = context.getDir(directoryToUse, 0);
            minervaLogger.info("Batch DIR:" + dir.getAbsolutePath());
            this.mStorageManager = new StorageManager(this.mConfigurationManager.getMetricsConfigurationHelper(), this.mServiceKPIReporter, dir);
            this.mKPIBatchCreator = new KPIBatchCreator(context, this.mServiceKPIReporter, this.mConfigurationManager.getMetricsConfigurationHelper(), dir, this.mCustomDeviceUtil);
            this.mBatchCreatorManager = new BatchCreatorManager(this.mConfigurationManager.getMetricsConfigurationHelper(), this.mStorageManager);
            MetricsTransmissionManager metricsTransmissionManager = new MetricsTransmissionManager(context, this.mConfigurationManager.getMetricsConfigurationHelper(), this.mServiceKPIReporter, this.mStorageManager, dir, this.mKPIBatchCreator);
            this.mMetricsTransmissionManager = metricsTransmissionManager;
            if (z) {
                metricsTransmissionManager.transmitMetricBatches();
            }
            this.initialized.set(true);
            minervaLogger.info("Finished initializing MinervaServiceManager");
        }
    }

    public boolean isInitialized() {
        return this.initialized.get();
    }

    public final boolean isKPIMetric(IonMetricEvent ionMetricEvent) {
        if (!ionMetricEvent.getMetricGroupId().stringValue().equals(KPIConstant.KPI_METRIC_GROUP_ID)) {
            return false;
        }
        return new SchemaId(KPIConstant.KPI_METRIC_SCHEMA_ID).identifier.equals(new SchemaId(ionMetricEvent.getMetricSchemaId().stringValue()).identifier);
    }

    public void record(IonMetricEvent ionMetricEvent) {
        this.mServiceKPIReporter.report(KPIMetric.IPC.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
        try {
            if (isKPIMetric(ionMetricEvent)) {
                this.mKPIBatchCreator.recordClientKPIMetricEvent(ionMetricEvent);
                this.mServiceKPIReporter.report(KPIMetric.RECORD_ON_DISK.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                return;
            }
            VersionedAttributes versionedAttributes = new SchemaId(ionMetricEvent.getMetricSchemaId().stringValue()).versionedAttributes;
            if (!versionedAttributes.getBooleanValue(AttributeEnumV2.ALLOW_CHILD_PROFILE).booleanValue()) {
                CustomDeviceUtil.getInstance().getChildProfileVerifier().getClass();
            }
            if (!versionedAttributes.getBooleanValue(AttributeEnumV2.DEPRECATED_EXEMPT_FROM_3P_OPT_OUT).booleanValue()) {
                CustomDeviceUtil.getInstance().getUserControlVerifier().getClass();
            }
            addPredefinedValues(ionMetricEvent);
            this.mBatchCreatorManager.addMetricEvent(ionMetricEvent);
            this.mServiceKPIReporter.report(KPIMetric.RECORD_ON_DISK.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
        } catch (Exception e) {
            log.error(String.format("Can't record metric event. metricGroupId: %s, schemaId: %s", ionMetricEvent.getMetricGroupId(), ionMetricEvent.getMetricSchemaId()) + " due to Exception: " + e);
        }
    }

    public void resetForTesting() {
        if (isInitialized()) {
            this.mBatchCreatorManager.shutdown();
            this.mMetricsTransmissionManager.shutdown();
            this.mStorageManager.shutdown();
            this.initialized.set(false);
            this.lastUploadCall.set(0L);
        }
    }

    @Deprecated
    public void shutdown() {
        flush();
    }

    @Deprecated
    public void shutdownWithUpload() {
        log.info("Shutdown Upload has been disabled for now due to concerns of data integrity");
        shutdown();
    }

    public synchronized Future<Void> upload() {
        if (this.lastUploadCall.longValue() + 300000 < System.currentTimeMillis() && this.initialized.get()) {
            flush();
            Future<Void> futureTransmitMetricBatches = this.mMetricsTransmissionManager.transmitMetricBatches();
            this.lastUploadCall.set(System.currentTimeMillis());
            return futureTransmitMetricBatches;
        }
        log.info("Upload call was rejected: Called too often.");
        if (Build.VERSION.SDK_INT < 24) {
            FutureTask futureTask = new FutureTask(new Runnable() { // from class: com.amazon.minerva.client.thirdparty.MinervaServiceManager.1
                @Override // java.lang.Runnable
                public void run() {
                }
            }, null);
            futureTask.run();
            return futureTask;
        }
        MinervaServiceManager$$ExternalSyntheticApiModelOutline1.m();
        CompletableFuture completableFutureM = MinervaServiceManager$$ExternalSyntheticApiModelOutline0.m();
        completableFutureM.complete(null);
        return completableFutureM;
    }
}
