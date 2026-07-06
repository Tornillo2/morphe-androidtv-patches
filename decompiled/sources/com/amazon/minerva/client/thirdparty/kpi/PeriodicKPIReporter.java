package com.amazon.minerva.client.thirdparty.kpi;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class PeriodicKPIReporter extends AbstractKPIReporter {
    public static final int DEFAULT_REPORT_TIME_IN_SECONDS = 1;
    public static final String TAG = "PeriodicKPIReporter";
    public MinervaServiceAndroidAdapter mMinervaServiceAndroidAdapter;
    public ScheduledExecutorService mScheduler;

    public PeriodicKPIReporter() {
        schedulePeriodicReport();
    }

    public void flush() {
        recordKPIMetricEvents();
    }

    public final void recordKPIMetricEvents() {
        Iterator it = ((HashMap) transferToMetricEvent()).entrySet().iterator();
        while (it.hasNext()) {
            MetricEvent metricEvent = (MetricEvent) ((Map.Entry) it.next()).getValue();
            metricEvent.setClientTimestamp(Timestamp.now());
            this.mMinervaServiceAndroidAdapter.record("", metricEvent.getMetricGroupId(), metricEvent.getSchemaId(), metricEvent.getMetricEventId().toString(), metricEvent.getClientTimestamp().epochMillis, (int) TimeUnit.MILLISECONDS.toMinutes(metricEvent.getClientTimestamp().getTimeZoneOffset()), metricEvent.getKeyValuePairsAsDataPoints());
            metricEvent.clear();
        }
    }

    public final void schedulePeriodicReport() {
        Runnable runnable = new Runnable() { // from class: com.amazon.minerva.client.thirdparty.kpi.PeriodicKPIReporter.1
            @Override // java.lang.Runnable
            public void run() {
                PeriodicKPIReporter.this.recordKPIMetricEvents();
            }
        };
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mScheduler = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
        scheduledExecutorServiceNewSingleThreadScheduledExecutor.scheduleWithFixedDelay(runnable, 1L, 1L, TimeUnit.SECONDS);
    }

    public void setMinervaServiceAndroidAdapter(MinervaServiceAndroidAdapter minervaServiceAndroidAdapter) {
        this.mMinervaServiceAndroidAdapter = minervaServiceAndroidAdapter;
    }

    @Override // com.amazon.minerva.client.thirdparty.kpi.AbstractKPIReporter
    public void shutdown() {
        Log.i(TAG, "Shutdown was invoked.");
        recordKPIMetricEvents();
        this.mScheduler.shutdownNow();
    }

    public final synchronized Map<String, MetricEvent> transferToMetricEvent() {
        HashMap map;
        try {
            map = new HashMap();
            for (Map.Entry<String, HashMap<String, Long>> entry : this.mKPIMap.entrySet()) {
                String key = entry.getKey();
                HashMap<String, Long> value = entry.getValue();
                MetricEvent metricEvent = new MetricEvent(KPIConstant.KPI_METRIC_GROUP_ID, KPIConstant.KPI_METRIC_SCHEMA_ID);
                metricEvent.addString(KPIConstant.CUSTOMER_METRIC_GROUP_ID, key);
                for (Map.Entry<String, Long> entry2 : value.entrySet()) {
                    metricEvent.addLong(entry2.getKey(), entry2.getValue().longValue());
                }
                map.put(key, metricEvent);
            }
            this.mKPIMap.clear();
        } catch (Throwable th) {
            throw th;
        }
        return map;
    }
}
