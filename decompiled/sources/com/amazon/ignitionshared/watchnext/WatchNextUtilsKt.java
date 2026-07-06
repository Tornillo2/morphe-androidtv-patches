package com.amazon.ignitionshared.watchnext;

import androidx.annotation.RequiresApi;
import androidx.media3.common.util.ColorParser$$ExternalSyntheticOutline0;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WatchNextUtilsKt {
    @RequiresApi(26)
    @NotNull
    public static final Map<String, Integer> generateContentTypeMap() {
        HashMap map = new HashMap();
        ColorParser$$ExternalSyntheticOutline0.m(0, map, "MOVIE", 3, "EPISODE");
        ColorParser$$ExternalSyntheticOutline0.m(2, map, "SEASON", 1, "SERIES");
        map.put("EVENT", 5);
        return map;
    }

    @RequiresApi(26)
    @NotNull
    public static final Map<String, Integer> generateWatchNextProgramTypeMap() {
        HashMap map = new HashMap();
        ColorParser$$ExternalSyntheticOutline0.m(0, map, "CONTINUE", 1, "NEXT");
        ColorParser$$ExternalSyntheticOutline0.m(2, map, "NEW", 3, "WATCHLIST");
        return map;
    }
}
