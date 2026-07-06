package com.bumptech.glide.load.engine;

import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.Key;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Jobs {
    public final Map<Key, EngineJob<?>> jobs = new HashMap();
    public final Map<Key, EngineJob<?>> onlyCacheJobs = new HashMap();

    public EngineJob<?> get(Key key, boolean z) {
        return getJobMap(z).get(key);
    }

    @VisibleForTesting
    public Map<Key, EngineJob<?>> getAll() {
        return DesugarCollections.unmodifiableMap(this.jobs);
    }

    public final Map<Key, EngineJob<?>> getJobMap(boolean z) {
        return z ? this.onlyCacheJobs : this.jobs;
    }

    public void put(Key key, EngineJob<?> engineJob) {
        getJobMap(engineJob.onlyRetrieveFromCache()).put(key, engineJob);
    }

    public void removeIfCurrent(Key key, EngineJob<?> engineJob) {
        Map<Key, EngineJob<?>> jobMap = getJobMap(engineJob.onlyRetrieveFromCache());
        if (engineJob.equals(jobMap.get(key))) {
            jobMap.remove(key);
        }
    }
}
