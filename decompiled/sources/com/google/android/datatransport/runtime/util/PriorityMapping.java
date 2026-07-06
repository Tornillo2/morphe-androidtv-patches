package com.google.android.datatransport.runtime.util;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.google.android.datatransport.Priority;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PriorityMapping {
    public static HashMap<Priority, Integer> PRIORITY_INT_MAP;
    public static SparseArray<Priority> PRIORITY_MAP = new SparseArray<>();

    static {
        HashMap<Priority, Integer> map = new HashMap<>();
        PRIORITY_INT_MAP = map;
        map.put(Priority.DEFAULT, 0);
        PRIORITY_INT_MAP.put(Priority.VERY_LOW, 1);
        PRIORITY_INT_MAP.put(Priority.HIGHEST, 2);
        for (Priority priority : PRIORITY_INT_MAP.keySet()) {
            PRIORITY_MAP.append(PRIORITY_INT_MAP.get(priority).intValue(), priority);
        }
    }

    public static int toInt(@NonNull Priority priority) {
        Integer num = PRIORITY_INT_MAP.get(priority);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("PriorityMapping is missing known Priority value " + priority);
    }

    @NonNull
    public static Priority valueOf(int i) {
        Priority priority = PRIORITY_MAP.get(i);
        if (priority != null) {
            return priority;
        }
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unknown Priority for value ", i));
    }
}
