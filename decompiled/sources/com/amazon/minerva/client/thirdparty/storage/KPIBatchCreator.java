package com.amazon.minerva.client.thirdparty.storage;

import android.content.Context;
import android.util.Log;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.ion.IonException;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonValue;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.minerva.client.thirdparty.api.Predefined;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.kpi.KPIConstant;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class KPIBatchCreator extends AbstractBatchCreatorScheduler {
    public static final IonSystem ION_SYSTEM;
    public static final String KPI_DIRECTORY = "KPI";
    public static final String KPI_FILENAME = "KPIBATCH_{region}_0001";
    public static final String TAG;
    public static final ValueFactory valueFactory;
    public final Context mContext;
    public final CustomDeviceUtil mCustomDeviceUtil;
    public final File mDirOfKPI;
    public FlushKPIs mFlushKpi;
    public Map<String, HashMap<String, Long>> mKpiMap;
    public final MetricBatchSerializer mMetricBatchSerializer;
    public final MetricsConfigurationHelper mMetricsConfigurationHelper;
    public final ServiceKPIReporter mServiceKPIReporter;
    public Object uploadDoneSignal;
    public volatile boolean uploading;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class FlushKPIs implements Callable<Void> {
        public FlushKPIs() {
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Void call() throws Exception {
            call2();
            return null;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: call, reason: avoid collision after fix types in other method */
        public Void call2() throws Exception {
            KPIBatchCreator.this.enqueueBatchForTransmission();
            return null;
        }
    }

    static {
        IonSystemBuilder ionSystemBuilder = IonSystemBuilder.STANDARD;
        ION_SYSTEM = ionSystemBuilder.build();
        valueFactory = ionSystemBuilder.build();
        TAG = "KPIBatchCreator";
    }

    public KPIBatchCreator(Context context, ServiceKPIReporter serviceKPIReporter, MetricsConfigurationHelper metricsConfigurationHelper, File file, CustomDeviceUtil customDeviceUtil) {
        super(metricsConfigurationHelper, KPI_DIRECTORY);
        this.mKpiMap = new ConcurrentHashMap();
        this.uploading = false;
        this.uploadDoneSignal = new Object();
        Objects.requireNonNull(serviceKPIReporter, "serviceKPIReporter cannot be null.");
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("dirOfMinerva must be a valid directory.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(file.getAbsolutePath());
        File file2 = new File(ActivityCompat$$ExternalSyntheticOutline0.m(sb, File.separator, KPI_DIRECTORY));
        this.mDirOfKPI = file2;
        if (!file2.isDirectory()) {
            file2.mkdir();
        }
        this.mContext = context;
        this.mCustomDeviceUtil = customDeviceUtil;
        this.mServiceKPIReporter = serviceKPIReporter;
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mFlushKpi = new FlushKPIs();
        this.mMetricBatchSerializer = metricsConfigurationHelper.getUploadConfiguration().createMetricBatchSerializer();
        scheduleBatchOpenTimeWatcher();
    }

    public static Map<String, HashMap<String, Long>> convertIonMetricEventsToMapMetricGroupIdToKpiValues(List<IonMetricEvent> list) {
        HashMap map = new HashMap();
        Iterator<IonMetricEvent> it = list.iterator();
        while (it.hasNext()) {
            IonStruct keyValuePairs = it.next().getKeyValuePairs();
            if (keyValuePairs != null) {
                String strStringValue = ((IonSymbol) keyValuePairs.get(KPIConstant.CUSTOMER_METRIC_GROUP_ID)).stringValue();
                HashMap map2 = (HashMap) map.get(strStringValue);
                if (map2 == null) {
                    map2 = new HashMap();
                    map.put(strStringValue, map2);
                }
                fillKpiValueMapWithKeyValuePair(keyValuePairs, map2);
            }
        }
        return map;
    }

    public static List<IonMetricEvent> convertKpiMapToIonMetricEvents(Map<String, HashMap<String, Long>> map, String str) {
        ArrayList arrayList = new ArrayList();
        long j = Timestamp.now().epochMillis;
        int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(r1.getTimeZone().getOffset(j));
        for (Map.Entry<String, HashMap<String, Long>> entry : map.entrySet()) {
            IonSystem ionSystem = ION_SYSTEM;
            IonSymbol ionSymbolNewSymbol = ionSystem.newSymbol(KPIConstant.KPI_METRIC_GROUP_ID);
            IonSymbol ionSymbolNewSymbol2 = ionSystem.newSymbol(KPIConstant.KPI_METRIC_SCHEMA_ID);
            com.amazon.ion.Timestamp timestampForMillis = com.amazon.ion.Timestamp.forMillis(j, Integer.valueOf(minutes));
            String key = entry.getKey();
            HashMap<String, Long> value = entry.getValue();
            IonStruct ionStructNewEmptyStruct = ionSystem.newEmptyStruct();
            ValueFactory valueFactory2 = valueFactory;
            ionStructNewEmptyStruct.add(KPIConstant.CUSTOMER_METRIC_GROUP_ID, valueFactory2.newSymbol(key));
            ionStructNewEmptyStruct.add(Predefined.DEVICE_TYPE.key, valueFactory2.newSymbol(str));
            for (Map.Entry<String, Long> entry2 : value.entrySet()) {
                ionStructNewEmptyStruct.add(entry2.getKey(), valueFactory.newInt(entry2.getValue()));
            }
            IonSystem ionSystem2 = ION_SYSTEM;
            arrayList.add(new IonMetricEvent(ionSymbolNewSymbol, ionSymbolNewSymbol2, ionSystem2.newTimestamp(timestampForMillis), ionSystem2.newString(UUID.randomUUID().toString()), ionStructNewEmptyStruct));
        }
        return arrayList;
    }

    public static void fillKpiValueMapWithKeyValuePair(IonStruct ionStruct, HashMap<String, Long> map) {
        for (IonValue ionValue : ionStruct) {
            if (ionValue instanceof IonInt) {
                String fieldName = ionValue.getFieldName();
                long jLongValue = ((IonInt) ionValue).longValue();
                if (map.containsKey(fieldName)) {
                    jLongValue += map.get(fieldName).longValue();
                }
                map.put(fieldName, Long.valueOf(jLongValue));
            }
        }
    }

    public static void mergeMapForAllMetricGroups(Map<String, HashMap<String, Long>> map, Map<String, HashMap<String, Long>> map2) {
        for (Map.Entry<String, HashMap<String, Long>> entry : map.entrySet()) {
            String key = entry.getKey();
            HashMap<String, Long> value = entry.getValue();
            HashMap<String, Long> map3 = map2.get(key);
            if (map3 == null) {
                map3 = new HashMap<>();
                map2.put(key, map3);
            }
            mergeMapForSingleMetricGroup(value, map3);
        }
    }

    public static void mergeMapForSingleMetricGroup(HashMap<String, Long> map, HashMap<String, Long> map2) {
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            String key = entry.getKey();
            long jLongValue = entry.getValue().longValue();
            if (map2.containsKey(key)) {
                jLongValue += map2.get(key).longValue();
            }
            map2.put(key, Long.valueOf(jLongValue));
        }
    }

    @Override // com.amazon.minerva.client.thirdparty.storage.AbstractBatchCreatorScheduler
    public synchronized void checkBatchOpenTimeAndEnqueueIfReady() {
        try {
            if (maxBatchOpenTimeReached() && (this.mKpiMap.size() > 0 || this.mServiceKPIReporter.size() > 0)) {
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
                enqueueBatchForTransmission();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void enqueueBatchForTransmission() {
        byte[] bArrSerialize;
        try {
            pullServiceKPIMap();
            Map<String, HashMap<String, Long>> kPIBatchFile = readKPIBatchFile();
            if (kPIBatchFile != null) {
                mergeMapForAllMetricGroups(kPIBatchFile, this.mKpiMap);
            }
            try {
                bArrSerialize = this.mMetricBatchSerializer.serialize(convertKpiMapToIonMetricEvents(this.mKpiMap, this.mCustomDeviceUtil.getDeviceType() == null ? "UNKNOWN" : this.mCustomDeviceUtil.getDeviceType()));
            } catch (IonException e) {
                e = e;
                Log.e(TAG, "An error occurs when converting KPI metric events to Ion Binary.", e);
                bArrSerialize = null;
            } catch (IOException e2) {
                e = e2;
                Log.e(TAG, "An error occurs when converting KPI metric events to Ion Binary.", e);
                bArrSerialize = null;
            } catch (Exception e3) {
                Log.e(TAG, "Unexpected error when converting KPI metric events to Ion Binary.", e3);
                bArrSerialize = null;
            }
            if (bArrSerialize == null) {
                return;
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(getKpiFilePath(), false);
                try {
                    fileOutputStream.write(bArrSerialize);
                    fileOutputStream.close();
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e4) {
                Log.e(TAG, "An error occurs when writing KPI metrics to disk.", e4);
            }
            this.mKpiMap.clear();
            this.mTimeSinceLastPublish.set(System.currentTimeMillis());
        } catch (Throwable th3) {
            throw th3;
        }
    }

    public void finishUpload() {
        this.uploading = false;
        sendUploadDoneSignal();
    }

    public void flush() {
        this.threadPool.submit(this.mFlushKpi);
    }

    public String getKpiFileName() {
        return KPI_FILENAME.replace("{region}", this.mMetricsConfigurationHelper.getUploadConfiguration().getKPIRegion());
    }

    public final String getKpiFilePath() {
        return this.mDirOfKPI.getAbsolutePath() + File.separator + getKpiFileName();
    }

    public boolean isUploading() {
        return this.uploading;
    }

    public synchronized void pullServiceKPIMap() {
        mergeMapForAllMetricGroups(this.mServiceKPIReporter.getAndClear(), this.mKpiMap);
    }

    public Map<String, HashMap<String, Long>> readKPIBatchFile() {
        File file = new File(getKpiFilePath());
        if (file.exists() && file.length() > 0) {
            byte[] bArr = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    fileInputStream.read(bArr);
                    Map<String, HashMap<String, Long>> mapConvertIonMetricEventsToMapMetricGroupIdToKpiValues = convertIonMetricEventsToMapMetricGroupIdToKpiValues(this.mMetricBatchSerializer.deserialize(bArr));
                    fileInputStream.close();
                    return mapConvertIonMetricEventsToMapMetricGroupIdToKpiValues;
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IonException e) {
                e = e;
                Log.e(TAG, "An error occurs when reading KPI file.", e);
                return null;
            } catch (IOException e2) {
                e = e2;
                Log.e(TAG, "An error occurs when reading KPI file.", e);
                return null;
            } catch (Exception e3) {
                Log.e(TAG, "Unexpected error when reading KPI file.", e3);
            }
        }
        return null;
    }

    public synchronized void recordClientKPIMetricEvent(IonMetricEvent ionMetricEvent) {
        try {
            IonStruct keyValuePairs = ionMetricEvent.getKeyValuePairs();
            String strStringValue = ((IonSymbol) keyValuePairs.get(KPIConstant.CUSTOMER_METRIC_GROUP_ID)).stringValue();
            HashMap<String, Long> map = this.mKpiMap.get(strStringValue);
            if (map == null) {
                map = new HashMap<>();
                this.mKpiMap.put(strStringValue, map);
            }
            fillKpiValueMapWithKeyValuePair(keyValuePairs, map);
        } catch (Throwable th) {
            throw th;
        }
    }

    public void sendUploadDoneSignal() {
        synchronized (this.uploadDoneSignal) {
            this.uploadDoneSignal.notifyAll();
        }
    }

    @Override // com.amazon.minerva.client.thirdparty.storage.AbstractBatchCreatorScheduler
    public synchronized void shutdown() {
        enqueueBatchForTransmission();
        super.shutdown();
    }

    public void startUpload() {
        this.uploading = true;
    }
}
