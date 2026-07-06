package com.amazon.minerva.client.thirdparty.storage;

import android.annotation.TargetApi;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.utils.MinervaLogger;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.amazon.minerva.identifiers.schemaid.attribute.attributes.AttributeEnumV2;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import j$.util.Objects;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class BatchCreatorManager {
    public static final MinervaLogger log = new MinervaLogger("BatchCreatorManager");
    public final MetricsConfigurationHelper mMetricsConfigurationHelper;
    public final StorageManager mStorageManager;
    public Map<String, BatchCreator> mapStoragePriorityToBatchCreator = new HashMap();

    public BatchCreatorManager(MetricsConfigurationHelper metricsConfigurationHelper, StorageManager storageManager) {
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mStorageManager = storageManager;
    }

    @TargetApi(24)
    public synchronized void addMetricEvent(IonMetricEvent ionMetricEvent) {
        try {
            Objects.requireNonNull(ionMetricEvent, "Parameter metricEvent can not be null");
            int storagePriority = getStoragePriority(ionMetricEvent);
            String region = ionMetricEvent.getRegion();
            String str = storagePriority + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + region;
            BatchCreator batchCreator = this.mapStoragePriorityToBatchCreator.get(str);
            MinervaLogger minervaLogger = log;
            minervaLogger.debug("batchCreatorKey: " + str);
            minervaLogger.debug("mapStoragePriorityToBatchCreator size: " + this.mapStoragePriorityToBatchCreator.size());
            if (batchCreator == null) {
                batchCreator = new BatchCreator(this.mMetricsConfigurationHelper, this.mStorageManager, region, storagePriority);
                this.mapStoragePriorityToBatchCreator.put(str, batchCreator);
            }
            batchCreator.addMetricEvent(ionMetricEvent);
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void flush() {
        Iterator<Map.Entry<String, BatchCreator>> it = this.mapStoragePriorityToBatchCreator.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().enqueueBatchForTransmission();
        }
    }

    public final int getStoragePriority(IonMetricEvent ionMetricEvent) {
        return new SchemaId(ionMetricEvent.getMetricSchemaId().stringValue()).versionedAttributes.getIntegerValue(AttributeEnumV2.STORAGE_PRIORITY).intValue();
    }

    public synchronized void shutdown() {
        Iterator<Map.Entry<String, BatchCreator>> it = this.mapStoragePriorityToBatchCreator.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().shutdown();
        }
    }
}
