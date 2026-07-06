package com.amazon.minerva.client.thirdparty.transport;

import android.util.Log;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.kpi.KPIConstant;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class BoundedBatchFileQueue {
    public static final String TAG = "BoundedBatchFileQueue";
    public final File mDirOfBatchFiles;
    public final MetricsConfigurationHelper mMetricsConfigurationHelper;
    public final ServiceKPIReporter mServiceKPIReporter;
    public final Map<String, Long> mapFileNameToSizeInByte = new HashMap();
    public long mTotalSizeInByte = 0;
    public final Deque<String> mFileNameQueue = new LinkedList();

    public BoundedBatchFileQueue(MetricsConfigurationHelper metricsConfigurationHelper, File file, ServiceKPIReporter serviceKPIReporter) {
        if (metricsConfigurationHelper == null) {
            throw new IllegalArgumentException("metricsConfigurationHelper cannot be null.");
        }
        if (file == null || !file.isDirectory()) {
            throw new IllegalArgumentException("Directory of batch files cannot be null or invalid.");
        }
        if (serviceKPIReporter == null) {
            throw new IllegalArgumentException("serviceKPIReporter cannot be null.");
        }
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mDirOfBatchFiles = file;
        this.mServiceKPIReporter = serviceKPIReporter;
        readFileNamesToQueue();
    }

    public synchronized long addBatch(String str, byte[] bArr) {
        String strWriteBatchToFile;
        if (!isValidBatchContent(bArr) || (strWriteBatchToFile = writeBatchToFile(new SerializedBatch(bArr, getNewFileName(str)))) == null) {
            return 0L;
        }
        this.mFileNameQueue.add(strWriteBatchToFile);
        long length = bArr.length;
        this.mapFileNameToSizeInByte.put(strWriteBatchToFile, Long.valueOf(length));
        this.mTotalSizeInByte += length;
        this.mServiceKPIReporter.report(KPIMetric.BATCH_CREATED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
        return bArr.length;
    }

    public synchronized void addFirst(SerializedBatch serializedBatch) {
        if (serializedBatch == null) {
            return;
        }
        byte[] batchContent = serializedBatch.getBatchContent();
        if (isValidBatchContent(batchContent)) {
            String strWriteBatchToFile = writeBatchToFile(serializedBatch);
            if (strWriteBatchToFile != null) {
                this.mFileNameQueue.addFirst(strWriteBatchToFile);
                long length = batchContent.length;
                this.mapFileNameToSizeInByte.put(strWriteBatchToFile, Long.valueOf(length));
                this.mTotalSizeInByte += length;
                this.mServiceKPIReporter.report(KPIMetric.BATCH_CREATED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
            }
        }
    }

    public final String getAbsolutePath(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDirOfBatchFiles.getAbsolutePath());
        return ActivityCompat$$ExternalSyntheticOutline0.m(sb, File.separator, str);
    }

    public final long getFileTimestamp(String str) {
        String[] strArrSplit = str.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 3);
        if (strArrSplit.length == 3) {
            return Long.parseLong(strArrSplit[0]);
        }
        return -1L;
    }

    public final String getNewFileName(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i = 1;
        while (true) {
            int i2 = i + 1;
            String str2 = String.format(Locale.US, "%d_%s_%04d", Long.valueOf(jCurrentTimeMillis), str, Integer.valueOf(i));
            if (!this.mapFileNameToSizeInByte.containsKey(str2)) {
                return str2;
            }
            i = i2;
        }
    }

    public long getTotalSizeInByte() {
        return this.mTotalSizeInByte;
    }

    public final boolean isValidBatchContent(byte[] bArr) {
        return bArr != null && bArr.length > 0;
    }

    public synchronized long purgeAtLeast(long j) {
        long j2;
        j2 = 0;
        while (true) {
            try {
                long j3 = this.mTotalSizeInByte;
                if (j3 <= 0 || j <= 0) {
                    break;
                }
                SerializedBatch serializedBatchRemove = remove();
                if (serializedBatchRemove != null) {
                    this.mServiceKPIReporter.report(KPIMetric.BATCH_DISK_EXCEEDED_MAX.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                    this.mServiceKPIReporter.report(KPIMetric.DISK_EXCEEDED_MAX, serializedBatchRemove.batchContent);
                }
                long j4 = j3 - this.mTotalSizeInByte;
                j2 += j4;
                j -= j4;
            } catch (Throwable th) {
                throw th;
            }
        }
        return j2;
    }

    public synchronized void purgeExpiredBatches() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mMetricsConfigurationHelper.getStorageConfiguration().getExpiryTimeMillis();
            int size = this.mFileNameQueue.size();
            while (this.mFileNameQueue.peek() != null) {
                long fileTimestamp = getFileTimestamp(this.mFileNameQueue.peek());
                if (fileTimestamp != -1 && fileTimestamp >= jCurrentTimeMillis) {
                    break;
                }
                SerializedBatch serializedBatchRemove = remove();
                if (serializedBatchRemove != null) {
                    this.mServiceKPIReporter.report(KPIMetric.BATCH_TTL_DROPPED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                    this.mServiceKPIReporter.report(KPIMetric.TTL_EXCEEDED_MAX, serializedBatchRemove.batchContent);
                }
            }
            Log.i(TAG, String.format("Number of batches purged: %d", Integer.valueOf(size - this.mFileNameQueue.size())));
        } catch (Throwable th) {
            throw th;
        }
    }

    public long purgeOneFile() {
        long j = this.mTotalSizeInByte;
        SerializedBatch serializedBatchRemove = remove();
        if (serializedBatchRemove != null) {
            this.mServiceKPIReporter.report(KPIMetric.BATCH_NUMBER_OF_FILES_EXCEEDED_MAX.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
            this.mServiceKPIReporter.report(KPIMetric.NUMBER_OF_FILES_EXCEEDED_MAX, serializedBatchRemove.batchContent);
        }
        return j - this.mTotalSizeInByte;
    }

    public final void readFileNamesToQueue() {
        File[] fileArrListFiles = this.mDirOfBatchFiles.listFiles();
        Arrays.sort(fileArrListFiles);
        for (File file : fileArrListFiles) {
            if (file.exists() && file.isFile()) {
                String name = file.getName();
                long length = file.length();
                this.mFileNameQueue.add(name);
                this.mapFileNameToSizeInByte.put(name, Long.valueOf(length));
                this.mTotalSizeInByte += length;
            }
        }
    }

    public synchronized SerializedBatch remove() {
        String strPoll = this.mFileNameQueue.poll();
        if (strPoll == null) {
            return null;
        }
        File file = new File(getAbsolutePath(strPoll));
        if (!file.exists()) {
            Long lRemove = this.mapFileNameToSizeInByte.remove(strPoll);
            if (lRemove != null) {
                this.mTotalSizeInByte -= lRemove.longValue();
            }
            return null;
        }
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                int i = fileInputStream.read(bArr);
                if (i != length) {
                    Log.e(TAG, "Partial read encountered: Expected " + length + " bytes but got " + i + " bytes.");
                    this.mFileNameQueue.add(strPoll);
                    fileInputStream.close();
                    return null;
                }
                fileInputStream.close();
                file.delete();
                if (file.exists()) {
                    Log.e(TAG, "Failed to delete batch file: ".concat(strPoll));
                    this.mFileNameQueue.add(strPoll);
                    return null;
                }
                Long lRemove2 = this.mapFileNameToSizeInByte.remove(strPoll);
                if (lRemove2 != null) {
                    this.mTotalSizeInByte -= lRemove2.longValue();
                }
                return new SerializedBatch(bArr, strPoll);
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            Log.e(TAG, "An error occurs when reading bytes from the input stream.", e);
            this.mServiceKPIReporter.report(KPIMetric.BATCH_IOEXCEPTION_DROPPED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
            return null;
        }
    }

    public synchronized int size() {
        return this.mFileNameQueue.size();
    }

    public String toString() {
        return "BoundedBatchFileQueue{mapFileNameToSizeInByte=" + this.mapFileNameToSizeInByte + ", totalSizeInByte=" + this.mTotalSizeInByte + '}';
    }

    public final String writeBatchToFile(SerializedBatch serializedBatch) {
        String fileName;
        if (serializedBatch == null || (fileName = serializedBatch.getFileName()) == null || !isValidBatchContent(serializedBatch.getBatchContent())) {
            return null;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getAbsolutePath(fileName));
            try {
                fileOutputStream.write(serializedBatch.getBatchContent());
                fileOutputStream.close();
                return fileName;
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "An error occurs when writing bytes to the file.", e);
            return null;
        } catch (IOException e2) {
            Log.e(TAG, "An error occurs when writing bytes to the file.", e2);
            this.mServiceKPIReporter.report(KPIMetric.BATCH_IOEXCEPTION_DROPPED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
            return null;
        }
    }
}
