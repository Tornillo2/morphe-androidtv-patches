package com.amazon.minerva.client.thirdparty.storage;

import android.annotation.TargetApi;
import com.amazon.ion.IonException;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer;
import com.amazon.minerva.client.thirdparty.utils.MinervaLogger;
import j$.util.Objects;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class BatchCreator extends AbstractBatchCreatorScheduler {
    public static final MinervaLogger log = new MinervaLogger("BatchCreator");
    public static MetricBatchSerializer sMetricBatchSerializer;
    public final MetricsConfigurationHelper mMetricsConfigurationHelper;
    public final String mRegion;
    public List<IonMetricEvent> mRunningBatch;
    public final StorageManager mStorageManager;
    public final int mStoragePriority;
    public long mTotalBatchSize;

    public BatchCreator(MetricsConfigurationHelper metricsConfigurationHelper, StorageManager storageManager, String str, int i) {
        super(metricsConfigurationHelper, Integer.toString(i));
        this.mRunningBatch = new LinkedList();
        this.mTotalBatchSize = 0L;
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mRegion = str;
        this.mStorageManager = storageManager;
        this.mStoragePriority = i;
        sMetricBatchSerializer = metricsConfigurationHelper.getUploadConfiguration().createMetricBatchSerializer();
        scheduleBatchOpenTimeWatcher();
    }

    @TargetApi(19)
    public synchronized void addMetricEvent(IonMetricEvent ionMetricEvent) {
        try {
            Objects.requireNonNull(ionMetricEvent, "Parameter metricEvent can not be null");
            if (willExceedMaxBatchEntries() || willExceedMaxBatchSize(ionMetricEvent.getSizeInByte())) {
                enqueueBatchForTransmission();
            }
            this.mRunningBatch.add(ionMetricEvent);
            this.mTotalBatchSize += ionMetricEvent.getSizeInByte();
            log.debug("RunningBatch size:" + this.mRunningBatch.size());
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.amazon.minerva.client.thirdparty.storage.AbstractBatchCreatorScheduler
    public synchronized void checkBatchOpenTimeAndEnqueueIfReady() {
        if (maxBatchOpenTimeReached()) {
            enqueueBatchForTransmission();
        }
    }

    public synchronized void enqueueBatchForTransmission() {
        if (this.mRunningBatch.size() > 0) {
            log.info("enqueueBatchForTransmission");
            try {
                this.mStorageManager.addBatch(sMetricBatchSerializer.serialize(this.mRunningBatch), this.mRegion, this.mStoragePriority);
            } catch (IonException e) {
                e = e;
                log.error(String.format("Running batch failed to add to disk. %s", e.getMessage()));
            } catch (IOException e2) {
                e = e2;
                log.error(String.format("Running batch failed to add to disk. %s", e.getMessage()));
            } catch (Exception e3) {
                log.error(String.format("Unexpected error while adding batch to disk. %s", e3.getMessage()));
            }
            this.mRunningBatch.clear();
            this.mTotalBatchSize = 0L;
            this.mTimeSinceLastPublish.set(System.currentTimeMillis());
        }
    }

    @Override // com.amazon.minerva.client.thirdparty.storage.AbstractBatchCreatorScheduler
    public synchronized void shutdown() {
        enqueueBatchForTransmission();
        super.shutdown();
    }

    public final boolean willExceedMaxBatchEntries() {
        return ((long) (this.mRunningBatch.size() + 1)) > this.mMetricsConfigurationHelper.getStorageConfiguration().getMaxBatchEntries();
    }

    public final boolean willExceedMaxBatchSize(long j) {
        return this.mTotalBatchSize + j > this.mMetricsConfigurationHelper.getStorageConfiguration().getMaxBatchSizeBytes();
    }
}
