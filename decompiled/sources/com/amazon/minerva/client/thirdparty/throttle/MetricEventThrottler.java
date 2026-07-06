package com.amazon.minerva.client.thirdparty.throttle;

import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import com.amazon.minerva.client.thirdparty.utils.LRUCache;
import com.amazon.minerva.client.thirdparty.utils.MinervaLogger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MetricEventThrottler {
    public static int sDefaultThrottleCreditPerHour;
    public static int sMaxThrottleCredit;
    public static MetricEventThrottler sMetricEventThrottler;
    public MinervaServiceAndroidAdapter mMinervaServiceAndroidAdapter;
    public LRUCache<String, ThrottleProfile> mThrottleCache = new LRUCache<>(MAX_CACHE_ENTRIES.intValue());
    public static final MinervaLogger log = new MinervaLogger("MetricEventThrottler");
    public static final Integer MAX_CACHE_ENTRIES = 500;

    public MetricEventThrottler(MinervaServiceAndroidAdapter minervaServiceAndroidAdapter) {
        this.mMinervaServiceAndroidAdapter = minervaServiceAndroidAdapter;
    }

    public static MetricEventThrottler getInstance(MinervaServiceAndroidAdapter minervaServiceAndroidAdapter) {
        if (sMetricEventThrottler == null) {
            synchronized (MetricEventThrottler.class) {
                try {
                    if (sMetricEventThrottler == null) {
                        sMetricEventThrottler = new MetricEventThrottler(minervaServiceAndroidAdapter);
                    }
                } finally {
                }
            }
        }
        return sMetricEventThrottler;
    }

    public double calculateCurrentCredit(long j, ThrottleProfile throttleProfile) {
        return Math.min(throttleProfile.getCredit() + ((((((double) getDefaultThrottleCredit()) * (j - throttleProfile.getTimestamp())) / 1000.0d) / 60.0d) / 60.0d), getMaxThrottleCredit());
    }

    public int getDefaultThrottleCredit() {
        int defaultThrottleCreditHour = this.mMinervaServiceAndroidAdapter.getMinervaServiceManager().getConfigurationManager().getMetricsConfigurationHelper().getThrottleConfiguration().getDefaultThrottleCreditHour();
        sDefaultThrottleCreditPerHour = defaultThrottleCreditHour;
        return defaultThrottleCreditHour;
    }

    public int getMaxThrottleCredit() {
        int maxThrottleCredit = this.mMinervaServiceAndroidAdapter.getMinervaServiceManager().getConfigurationManager().getMetricsConfigurationHelper().getThrottleConfiguration().getMaxThrottleCredit();
        sMaxThrottleCredit = maxThrottleCredit;
        return maxThrottleCredit;
    }

    public synchronized boolean shouldThrottleMetricEvent(MetricEvent metricEvent) {
        try {
            String metricGroupId = metricEvent.getMetricGroupId();
            ThrottleProfile throttleProfile = this.mThrottleCache.get(metricGroupId);
            if (throttleProfile == null) {
                this.mThrottleCache.put(metricGroupId, new ThrottleProfile(Timestamp.now().epochMillis, getDefaultThrottleCredit() - 1));
            } else {
                long j = Timestamp.now().epochMillis;
                double dCalculateCurrentCredit = calculateCurrentCredit(j, throttleProfile);
                if (dCalculateCurrentCredit < 1.0d) {
                    log.debug("Drop metric event due to throttle. MetricGroupId=" + metricEvent.getMetricGroupId() + " SchemaId=" + metricEvent.getSchemaId());
                    return true;
                }
                throttleProfile.setCredit(dCalculateCurrentCredit - 1.0d);
                throttleProfile.setTimestamp(j);
                this.mThrottleCache.put(metricGroupId, throttleProfile);
            }
            return false;
        } catch (Throwable th) {
            throw th;
        }
    }
}
