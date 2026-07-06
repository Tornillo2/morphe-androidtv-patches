package com.amazon.livingroom.mediapipelinebackend;

import j$.util.DesugarCollections;
import j$.util.Objects;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class MediaPipelineBackendEngineManager$$ExternalSyntheticBackport1 {
    public static /* synthetic */ Map m(Map.Entry[] entryArr) {
        HashMap map = new HashMap(entryArr.length);
        for (Map.Entry entry : entryArr) {
            Object key = entry.getKey();
            Objects.requireNonNull(key);
            Object value = entry.getValue();
            Objects.requireNonNull(value);
            if (map.put(key, value) != null) {
                throw new IllegalArgumentException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("duplicate key: ", key));
            }
        }
        return DesugarCollections.unmodifiableMap(map);
    }
}
