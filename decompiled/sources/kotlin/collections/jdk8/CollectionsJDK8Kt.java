package kotlin.collections.jdk8;

import j$.util.Map;
import java.util.Map;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@JvmName(name = "CollectionsJDK8Kt")
public final class CollectionsJDK8Kt {
    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static final <K, V> V getOrDefault(Map<? extends K, ? extends V> map, K k, V v) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return (V) Map.EL.getOrDefault(map, k, v);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static final <K, V> boolean remove(java.util.Map<? extends K, ? extends V> map, K k, V v) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return Map.EL.remove(TypeIntrinsics.asMutableMap(map), k, v);
    }
}
