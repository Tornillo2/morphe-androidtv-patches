package com.amazon.minerva.client.thirdparty.kpi;

import j$.util.DesugarCollections;
import j$.util.Objects;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractKPIReporter {
    public static Set<String> mKPIKeys;
    public HashMap<String, HashMap<String, Long>> mKPIMap = new HashMap<>();

    static {
        HashSet hashSet = new HashSet();
        for (KPIMetric kPIMetric : KPIMetric.values()) {
            hashSet.add(kPIMetric.metricName);
        }
        mKPIKeys = DesugarCollections.unmodifiableSet(hashSet);
    }

    public synchronized void clear() {
        this.mKPIMap.clear();
    }

    public synchronized Map<String, HashMap<String, Long>> getAndClear() {
        HashMap<String, HashMap<String, Long>> map;
        map = this.mKPIMap;
        this.mKPIMap = new HashMap<>();
        return map;
    }

    public synchronized void report(String str, String str2, long j) {
        try {
            Objects.requireNonNull(str, "kpiKey cannot be null in report method.");
            Objects.requireNonNull(str2, "MetricGroupId cannot be null in report method.");
            if (!mKPIKeys.contains(str) || str2.isEmpty() || j < 0) {
                throw new IllegalArgumentException("Key, MetricGroupID, or Value is invalid in report method.");
            }
            if (j == 0) {
                return;
            }
            HashMap<String, Long> map = this.mKPIMap.get(str2);
            if (map == null) {
                map = new HashMap<>();
                this.mKPIMap.put(str2, map);
            }
            if (map.containsKey(str)) {
                j += map.get(str).longValue();
            }
            map.put(str, Long.valueOf(j));
        } catch (Throwable th) {
            throw th;
        }
    }

    public abstract void shutdown();

    public synchronized int size() {
        return this.mKPIMap.size();
    }
}
