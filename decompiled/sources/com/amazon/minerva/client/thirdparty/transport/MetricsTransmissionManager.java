package com.amazon.minerva.client.thirdparty.transport;

import android.content.Context;
import android.util.Log;
import com.amazon.ion.IonException;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.kpi.KPIConstant;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer;
import com.amazon.minerva.client.thirdparty.storage.KPIBatchCreator;
import com.amazon.minerva.client.thirdparty.storage.StorageManager;
import com.amazon.minerva.client.thirdparty.utils.BackgroundThreadFactory;
import com.amazon.minerva.client.thirdparty.utils.MetricEventResponseIonConverter;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MetricsTransmissionManager {
    public static final int MIN_TRANSMISSION_PERIOD_MILLIS = 300000;
    public static final String TAG = "MetricsTransmissionManager";
    public static final String THREAD_NAME_BATCH_TRANSMITTER = "BatchTransmitterThreadName";
    public static final int THREAD_POOL_CORE_SIZE = 1;
    public static final int THREAD_POOL_TERMINATION_WAIT_MILLS = 5000;
    public static MetricBatchSerializer sMetricBatchSerializer;
    public KPIBatchCreator mKPIBatchCreator;
    public MetricsConfigurationHelper mMetricsConfigurationHelper;
    public MetricsTransporter mMetricsTransporter;
    public File mRootDir;
    public ScheduledThreadPoolExecutor mScheduledExecutor;
    public ServiceKPIReporter mServiceKPIReporter;
    public StorageManager mStorageManager;
    public long mTransmissionOffsetMillis;
    public Transmitter mTransmitter;
    public long mUploadInterval;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class Transmitter implements Runnable {
        public final AtomicBoolean mIsActive = new AtomicBoolean(true);

        public Transmitter() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mIsActive.get()) {
                Log.i(MetricsTransmissionManager.TAG, "Transmitter.run... Shutdown invoked.");
                return;
            }
            MetricsTransmissionManager.this.transmitMetricBatches();
            Log.i(MetricsTransmissionManager.TAG, "Transmitter.run....Rescheduling next transmission.");
            MetricsTransmissionManager metricsTransmissionManager = MetricsTransmissionManager.this;
            metricsTransmissionManager.mScheduledExecutor.schedule(metricsTransmissionManager.mTransmitter, metricsTransmissionManager.mUploadInterval, TimeUnit.MILLISECONDS);
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }
    }

    public MetricsTransmissionManager(Context context, MetricsConfigurationHelper metricsConfigurationHelper, ServiceKPIReporter serviceKPIReporter, StorageManager storageManager, File file, KPIBatchCreator kPIBatchCreator) {
        this.mMetricsTransporter = new MetricsTransporter(context, metricsConfigurationHelper, serviceKPIReporter);
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mStorageManager = storageManager;
        this.mRootDir = file;
        this.mKPIBatchCreator = kPIBatchCreator;
        this.mServiceKPIReporter = serviceKPIReporter;
        sMetricBatchSerializer = metricsConfigurationHelper.getUploadConfiguration().createMetricBatchSerializer();
        this.mTransmissionOffsetMillis = ((long) ((Math.random() * 9.223372036854776E18d) / 1000.0d)) * 1000;
        if (this.mMetricsConfigurationHelper.getStorageConfiguration().getTransmissionPeriodMillis() < 300000) {
            this.mUploadInterval = 300000L;
            Log.e(TAG, "transmission period is set too short, override to the minimal limitation: 5 mins");
        } else {
            this.mUploadInterval = metricsConfigurationHelper.getStorageConfiguration().getTransmissionPeriodMillis();
            Log.i(TAG, "scheduled transmission interval is set to: " + this.mUploadInterval);
        }
        createDefaultThreadPoolExecutor(this.mTransmissionOffsetMillis);
    }

    public long addOffset(long j, long j2) {
        long j3 = j2 % j;
        return j3 < j / 2 ? j3 + j : j3;
    }

    public final void createDefaultThreadPoolExecutor(long j) {
        long jAddOffset = addOffset(this.mUploadInterval, j);
        Log.i(TAG, "initialTransmissionPeriodMillis: " + jAddOffset);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new BackgroundThreadFactory(THREAD_NAME_BATCH_TRANSMITTER));
        this.mScheduledExecutor = scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        Transmitter transmitter = new Transmitter();
        this.mTransmitter = transmitter;
        this.mScheduledExecutor.schedule(transmitter, jAddOffset, TimeUnit.MILLISECONDS);
    }

    public final byte[] createRetryableBatch(byte[] bArr, byte[] bArr2) {
        byte[] bArr3;
        byte[] bArr4 = null;
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            Log.e(TAG, "Response payload or original batch is invalid.");
            return null;
        }
        if (bArr.length <= 2) {
            Log.i(TAG, "All metric events have been successfully uploaded.");
            return null;
        }
        try {
            List<IonMetricEvent> listDeserialize = sMetricBatchSerializer.deserialize(bArr2);
            Map<String, String> mapConvertIonBinaryToResponseMap = MetricEventResponseIonConverter.convertIonBinaryToResponseMap(bArr);
            ArrayList arrayList = new ArrayList();
            for (IonMetricEvent ionMetricEvent : listDeserialize) {
                HashMap map = (HashMap) mapConvertIonBinaryToResponseMap;
                if (map.containsKey(ionMetricEvent.getMetricEventId().stringValue())) {
                    String str = (String) map.get(ionMetricEvent.getMetricEventId().stringValue());
                    if (str.equals(MetricEventResponseResult.SERVER_ERROR.toString())) {
                        bArr3 = bArr4;
                        Log.w(TAG, String.format("An error occurs with code %s after uploading metric event with schemaID %s", str, ionMetricEvent.getMetricSchemaId()));
                        arrayList.add(ionMetricEvent);
                    } else {
                        bArr3 = bArr4;
                        if (str.equals(MetricEventResponseResult.SCHEMA_NOT_FOUND.toString()) || str.equals(MetricEventResponseResult.METRIC_DENIED.toString()) || str.equals(MetricEventResponseResult.VALIDATION_FAILURE.toString())) {
                            Log.e(TAG, String.format("An error occurs with code %s after uploading metric event with schemaID %s, and discard.", str, ionMetricEvent.getMetricSchemaId()));
                            this.mServiceKPIReporter.report(KPIMetric.UPLOAD_CLIENT_ERROR.metricName, ionMetricEvent.getMetricGroupId().stringValue(), 1L);
                        } else {
                            Log.w(TAG, String.format("An error occurs with code %s after uploading metric event with schemaID %s", str, ionMetricEvent.getMetricSchemaId()));
                            arrayList.add(ionMetricEvent);
                        }
                    }
                } else {
                    bArr3 = bArr4;
                }
                bArr4 = bArr3;
            }
            byte[] bArr5 = bArr4;
            if (arrayList.size() > 0) {
                try {
                    return sMetricBatchSerializer.serialize(arrayList);
                } catch (IonException e) {
                    e = e;
                    Log.e(TAG, "An error occurs when converting updated batch to byte array.", e);
                    this.mServiceKPIReporter.report(KPIMetric.BATCH_IOEXCEPTION_DROPPED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                    return bArr5;
                } catch (IOException e2) {
                    e = e2;
                    Log.e(TAG, "An error occurs when converting updated batch to byte array.", e);
                    this.mServiceKPIReporter.report(KPIMetric.BATCH_IOEXCEPTION_DROPPED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                    return bArr5;
                } catch (Exception e3) {
                    Log.e(TAG, "Unexpected error when converting updated batch to byte array.", e3);
                }
            }
            return bArr5;
        } catch (IonException e4) {
            e = e4;
            Log.e(TAG, "An error occurs when converting from Ion.", e);
            this.mServiceKPIReporter.report(KPIMetric.BATCH_IOEXCEPTION_DROPPED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
            return null;
        } catch (IOException e5) {
            e = e5;
            Log.e(TAG, "An error occurs when converting from Ion.", e);
            this.mServiceKPIReporter.report(KPIMetric.BATCH_IOEXCEPTION_DROPPED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
            return null;
        } catch (Exception e6) {
            Log.e(TAG, "Unexpected error when converting from Ion.", e6);
            return null;
        }
    }

    public int loadMetricBatches(List<BoundedBatchFileQueue> list, final boolean z) {
        int size = 0;
        for (File file : this.mRootDir.listFiles(new FileFilter() { // from class: com.amazon.minerva.client.thirdparty.transport.MetricsTransmissionManager.3
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                if (!file2.isDirectory()) {
                    return false;
                }
                if (z && file2.getName().equals(KPIBatchCreator.KPI_DIRECTORY)) {
                    return true;
                }
                return (z || file2.getName().equals(KPIBatchCreator.KPI_DIRECTORY)) ? false : true;
            }
        })) {
            BoundedBatchFileQueue boundedBatchFileQueue = new BoundedBatchFileQueue(this.mMetricsConfigurationHelper, file, this.mServiceKPIReporter);
            list.add(boundedBatchFileQueue);
            size += boundedBatchFileQueue.size();
        }
        Log.i(TAG, "typeOfFilesLoaded: " + (z ? KPIBatchCreator.KPI_DIRECTORY : "non_KPI") + ", numberOfFilesLoaded: " + size);
        return size;
    }

    public void shutdown() {
        List<Runnable> listShutdownNow = this.mScheduledExecutor.shutdownNow();
        if (listShutdownNow == null || listShutdownNow.size() <= 0) {
            return;
        }
        Log.i(TAG, String.format("shutdown called with %d pending tasks", Integer.valueOf(listShutdownNow.size())));
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0158 A[LOOP:2: B:44:0x0152->B:46:0x0158, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean transmit(java.util.List<com.amazon.minerva.client.thirdparty.transport.BoundedBatchFileQueue> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.minerva.client.thirdparty.transport.MetricsTransmissionManager.transmit(java.util.List):boolean");
    }

    public void transmitKPIMetricBatches() {
        if (!this.mMetricsTransporter.hasNetworkConnectivity()) {
            Log.w(TAG, "No network connectivity available, skipping KPI transmission");
            this.mServiceKPIReporter.report(KPIMetric.UPLOAD_CONNECTION_ERROR.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
        } else {
            final LinkedList linkedList = new LinkedList();
            loadMetricBatches(linkedList, true);
            this.mScheduledExecutor.execute(new Runnable() { // from class: com.amazon.minerva.client.thirdparty.transport.MetricsTransmissionManager.2
                @Override // java.lang.Runnable
                public void run() {
                    MetricsTransmissionManager.this.transmitMetricBatch(linkedList, true);
                }
            });
        }
    }

    public void transmitMetricBatch(List<BoundedBatchFileQueue> list, boolean z) {
        if (z) {
            synchronized (this.mKPIBatchCreator) {
                try {
                    this.mKPIBatchCreator.startUpload();
                    try {
                        String str = TAG;
                        Log.i(str, "transmitKPIMetricBatch started");
                        transmit(list);
                        Log.i(str, "transmitKPIMetricBatch finished");
                    } finally {
                        this.mKPIBatchCreator.finishUpload();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        this.mStorageManager.startUpload();
        try {
            String str2 = TAG;
            Log.i(str2, "transmitMetricBatches started");
            boolean zTransmit = transmit(list);
            Log.i(str2, "transmitMetricBatches finished");
            if (zTransmit) {
                try {
                    this.mStorageManager.reloadBatchFiles();
                    Log.i(str2, "transmitMetricBatches, storageManager(after execute): " + this.mStorageManager.toString());
                } catch (Exception e) {
                    Log.e(TAG, "Error reloading batch files", e);
                }
            }
        } finally {
            this.mStorageManager.finishUpload();
        }
    }

    public Future<Void> transmitMetricBatches() {
        if (!this.mMetricsTransporter.hasNetworkConnectivity()) {
            Log.w(TAG, "No network connectivity available, skipping transmission");
            this.mServiceKPIReporter.report(KPIMetric.UPLOAD_CONNECTION_ERROR.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
            return CompletableFuture.completedFuture(null);
        }
        transmitKPIMetricBatches();
        final LinkedList linkedList = new LinkedList();
        loadMetricBatches(linkedList, false);
        return this.mScheduledExecutor.submit(new Runnable() { // from class: com.amazon.minerva.client.thirdparty.transport.MetricsTransmissionManager.1
            @Override // java.lang.Runnable
            public void run() {
                MetricsTransmissionManager.this.transmitMetricBatch(linkedList, false);
            }
        }, null);
    }
}
