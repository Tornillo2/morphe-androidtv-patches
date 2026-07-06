package androidx.collection;

import com.bumptech.glide.util.CachedHashCodeArrayMap;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ArrayMapKt {
    @NotNull
    public static final <K, V> ArrayMap<K, V> arrayMapOf() {
        return new ArrayMap<>();
    }

    @NotNull
    public static final <K, V> ArrayMap<K, V> arrayMapOf(@NotNull Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        CachedHashCodeArrayMap cachedHashCodeArrayMap = (ArrayMap<K, V>) new ArrayMap(pairs.length);
        for (Pair<? extends K, ? extends V> pair : pairs) {
            cachedHashCodeArrayMap.put(pair.first, pair.second);
        }
        return cachedHashCodeArrayMap;
    }
}
