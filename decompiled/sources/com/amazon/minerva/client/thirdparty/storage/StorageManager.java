package com.amazon.minerva.client.thirdparty.storage;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.kpi.KPIConstant;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazon.minerva.client.thirdparty.transport.BoundedBatchFileQueue;
import com.amazon.minerva.client.thirdparty.utils.BackgroundThreadFactory;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class StorageManager {
    public static int MAX_STORAGE_PRIORITY = 15;
    public static final String TAG = "StorageManager";
    public static final String THREAD_NAME_BATCH_QUEUES_TTL_PURGER = "MnvBchQsTTLPgr";
    public static final int THREAD_POOL_TERMINATION_WAIT_MS = 5000;
    public final MetricsConfigurationHelper mMetricsConfigurationHelper;
    public ScheduledThreadPoolExecutor mQueueTTLPurgerExecutor;
    public final ServiceKPIReporter mServiceKPIReporter;
    public File rootDir;
    public volatile boolean uploading = false;
    public Object uploadDoneSignal = new Object();

    @SuppressLint({"UseSparseArrays"})
    public Map<Integer, BoundedBatchFileQueue> mapStoragePriorityToBatchQueue = new HashMap();
    public long totalSizeInByte = 0;
    public long totalBatchFileCount = 0;
    public final QueuesTTLPurger mQueuesTTLPurger = new QueuesTTLPurger();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class QueuesTTLPurger implements Runnable {
        public final AtomicBoolean mIsActive = new AtomicBoolean(true);

        public QueuesTTLPurger() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mIsActive.get()) {
                Log.i(StorageManager.TAG, "QueuePurger.run. Shutdown invoked.");
            } else {
                Log.i(StorageManager.TAG, "QueuePurger.run. Purging expired batches.");
                StorageManager.this.purgeExpiredBatches();
            }
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }
    }

    public StorageManager(MetricsConfigurationHelper metricsConfigurationHelper, ServiceKPIReporter serviceKPIReporter, File file) {
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mServiceKPIReporter = serviceKPIReporter;
        this.rootDir = file;
        reloadBatchFiles();
        scheduleQueueTTLPeriodicPurger();
    }

    @TargetApi(9)
    public synchronized long addBatch(byte[] bArr, String str, int i) {
        BoundedBatchFileQueue boundedBatchFileQueue;
        try {
            String str2 = TAG;
            Log.i(str2, "addBatch to BoundedBatchQueue, then write to disk file...");
            if (bArr == null || bArr.length == 0) {
                throw new IllegalArgumentException("Parameter batchContent can not be null or empty");
            }
            if (str == null || str.isEmpty()) {
                throw new IllegalArgumentException("Parameter region can not be null or empty");
            }
            if (i < 0 || i > MAX_STORAGE_PRIORITY) {
                throw new IllegalArgumentException(String.format("Parameter storagePriority should be with range of [0..%d]", Integer.valueOf(MAX_STORAGE_PRIORITY)));
            }
            BoundedBatchFileQueue boundedBatchFileQueue2 = this.mapStoragePriorityToBatchQueue.get(Integer.valueOf(i));
            if (boundedBatchFileQueue2 == null) {
                File file = new File(this.rootDir.getAbsolutePath() + File.separator + i);
                if (!file.isDirectory()) {
                    file.mkdir();
                }
                BoundedBatchFileQueue boundedBatchFileQueue3 = new BoundedBatchFileQueue(this.mMetricsConfigurationHelper, file, this.mServiceKPIReporter);
                this.mapStoragePriorityToBatchQueue.put(Integer.valueOf(i), boundedBatchFileQueue3);
                boundedBatchFileQueue = boundedBatchFileQueue3;
            } else {
                boundedBatchFileQueue = boundedBatchFileQueue2;
            }
            long maxStorageSpaceBytes = this.mMetricsConfigurationHelper.getStorageConfiguration().getMaxStorageSpaceBytes();
            long maxNumberOfBatchFiles = this.mMetricsConfigurationHelper.getStorageConfiguration().getMaxNumberOfBatchFiles();
            if (!canFreeEnoughSpace(maxStorageSpaceBytes, i, bArr.length)) {
                this.mServiceKPIReporter.report(KPIMetric.DISK_EXCEEDED_MAX, bArr);
                this.mServiceKPIReporter.report(KPIMetric.BATCH_DISK_EXCEEDED_MAX.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                Log.e(str2, String.format("Can not free enough space to save batchContent for this storage Priority. The batch is dropped and KPI is reported. (storagePriority: %d, batchContent.length: %d)", Integer.valueOf(i), Integer.valueOf(bArr.length)));
                return 0L;
            }
            if (!canFreeOneCount(maxNumberOfBatchFiles, i)) {
                this.mServiceKPIReporter.report(KPIMetric.NUMBER_OF_FILES_EXCEEDED_MAX, bArr);
                this.mServiceKPIReporter.report(KPIMetric.BATCH_NUMBER_OF_FILES_EXCEEDED_MAX.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                Log.e(str2, String.format("Can not free 1 batch file to save batchContent for this storage Priority. The batch is dropped and KPI is reported. (storagePriority: %d, batchContent.length: %d)", Integer.valueOf(i), Integer.valueOf(bArr.length)));
                return 0L;
            }
            long jPurgeAtLeast = this.totalSizeInByte + ((long) bArr.length) > maxStorageSpaceBytes ? purgeAtLeast(i, bArr.length) : this.totalBatchFileCount >= maxNumberOfBatchFiles ? purgeOneFile(i) : 0L;
            long jAddBatch = boundedBatchFileQueue.addBatch(str, bArr);
            this.totalSizeInByte += jAddBatch;
            this.totalBatchFileCount++;
            return jAddBatch - jPurgeAtLeast;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final boolean canFreeEnoughSpace(long j, int i, long j2) {
        long totalSizeInByte = 0;
        for (int i2 = i + 1; i2 <= MAX_STORAGE_PRIORITY; i2++) {
            BoundedBatchFileQueue boundedBatchFileQueue = this.mapStoragePriorityToBatchQueue.get(Integer.valueOf(i2));
            if (boundedBatchFileQueue != null) {
                totalSizeInByte = boundedBatchFileQueue.getTotalSizeInByte() + totalSizeInByte;
            }
        }
        return j - totalSizeInByte >= j2;
    }

    public final boolean canFreeOneCount(long j, int i) {
        long size = 0;
        for (int i2 = i + 1; i2 <= MAX_STORAGE_PRIORITY; i2++) {
            BoundedBatchFileQueue boundedBatchFileQueue = this.mapStoragePriorityToBatchQueue.get(Integer.valueOf(i2));
            if (boundedBatchFileQueue != null) {
                size += (long) boundedBatchFileQueue.size();
            }
        }
        return j - size > 0;
    }

    public void finishUpload() {
        this.uploading = false;
        sendUploadDoneSignal();
    }

    public long getTotalBatchFileCount() {
        return this.totalBatchFileCount;
    }

    public long getTotalSizeInByte() {
        return this.totalSizeInByte;
    }

    public boolean isUploading() {
        return this.uploading;
    }

    public final long purgeAtLeast(int i, long j) {
        long j2 = 0;
        long j3 = j;
        for (int i2 = 0; i2 <= i; i2++) {
            BoundedBatchFileQueue boundedBatchFileQueue = this.mapStoragePriorityToBatchQueue.get(Integer.valueOf(i2));
            if (boundedBatchFileQueue != null) {
                long jPurgeAtLeast = boundedBatchFileQueue.purgeAtLeast(j3);
                j3 -= jPurgeAtLeast;
                j2 += jPurgeAtLeast;
                if (j2 >= j) {
                    break;
                }
            }
        }
        recalculateTotalSizeAndCount();
        return j2;
    }

    public final void purgeExpiredBatches() {
        synchronized (this.uploadDoneSignal) {
            while (isUploading()) {
                try {
                    this.uploadDoneSignal.wait();
                } catch (InterruptedException e) {
                    Log.e(TAG, "Exception in purgeExpiredBatches.", e);
                    return;
                }
            }
        }
        Iterator<Map.Entry<Integer, BoundedBatchFileQueue>> it = this.mapStoragePriorityToBatchQueue.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().purgeExpiredBatches();
        }
        reloadBatchFiles();
    }

    public final long purgeOneFile(int i) {
        long j;
        int i2 = 0;
        while (true) {
            j = 0;
            if (i2 > i) {
                break;
            }
            BoundedBatchFileQueue boundedBatchFileQueue = this.mapStoragePriorityToBatchQueue.get(Integer.valueOf(i2));
            if (boundedBatchFileQueue != null) {
                long jPurgeOneFile = boundedBatchFileQueue.purgeOneFile();
                if (jPurgeOneFile > 0) {
                    j = jPurgeOneFile;
                    break;
                }
            }
            i2++;
        }
        recalculateTotalSizeAndCount();
        return j;
    }

    public final synchronized void recalculateTotalSizeAndCount() {
        this.totalSizeInByte = 0L;
        this.totalBatchFileCount = 0L;
        for (Map.Entry<Integer, BoundedBatchFileQueue> entry : this.mapStoragePriorityToBatchQueue.entrySet()) {
            this.totalSizeInByte += entry.getValue().getTotalSizeInByte();
            this.totalBatchFileCount += (long) entry.getValue().size();
        }
    }

    public synchronized void reloadBatchFiles() {
        this.mapStoragePriorityToBatchQueue.clear();
        this.totalSizeInByte = 0L;
        this.totalBatchFileCount = 0L;
        for (File file : this.rootDir.listFiles(new FileFilter() { // from class: com.amazon.minerva.client.thirdparty.storage.StorageManager.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isDirectory() && !file2.getName().equals(KPIBatchCreator.KPI_DIRECTORY);
            }
        })) {
            int i = Integer.parseInt(file.getName());
            BoundedBatchFileQueue boundedBatchFileQueue = new BoundedBatchFileQueue(this.mMetricsConfigurationHelper, file, this.mServiceKPIReporter);
            this.totalSizeInByte += boundedBatchFileQueue.mTotalSizeInByte;
            this.totalBatchFileCount += (long) boundedBatchFileQueue.size();
            this.mapStoragePriorityToBatchQueue.put(Integer.valueOf(i), boundedBatchFileQueue);
        }
    }

    public final void scheduleQueueTTLPeriodicPurger() {
        this.mQueueTTLPurgerExecutor = new ScheduledThreadPoolExecutor(1, new BackgroundThreadFactory(THREAD_NAME_BATCH_QUEUES_TTL_PURGER));
        long purgePeriodMillis = this.mMetricsConfigurationHelper.getStorageConfiguration().getPurgePeriodMillis();
        this.mQueueTTLPurgerExecutor.scheduleWithFixedDelay(this.mQueuesTTLPurger, purgePeriodMillis, purgePeriodMillis, TimeUnit.MILLISECONDS);
        this.mQueueTTLPurgerExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
    }

    public void sendUploadDoneSignal() {
        synchronized (this.uploadDoneSignal) {
            this.uploadDoneSignal.notifyAll();
        }
    }

    public void shutdown() {
        this.mQueuesTTLPurger.shutdown();
        List<Runnable> listShutdownNow = this.mQueueTTLPurgerExecutor.shutdownNow();
        if (listShutdownNow == null || listShutdownNow.size() <= 0) {
            return;
        }
        Log.i(TAG, String.format("shutdown called with %d pending tasks", Integer.valueOf(listShutdownNow.size())));
    }

    public void startUpload() {
        this.uploading = true;
    }

    public String toString() {
        return "StorageManager{mapStoragePriorityToBatchQueue=" + this.mapStoragePriorityToBatchQueue + ", totalSizeInByte=" + this.totalSizeInByte + ", totalBatchFileCount=" + this.totalBatchFileCount + '}';
    }
}
