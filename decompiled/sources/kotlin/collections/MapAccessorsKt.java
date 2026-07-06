package kotlin.collections;

import java.util.Map;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@JvmName(name = "MapAccessorsKt")
public final class MapAccessorsKt {
    @InlineOnly
    public static final <V, V1 extends V> V1 getValue(Map<? super String, ? extends V> map, Object obj, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(property, "property");
        return (V1) MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(map, property.getName());
    }

    @InlineOnly
    @JvmName(name = "getVar")
    public static final <V, V1 extends V> V1 getVar(Map<? super String, ? extends V> map, Object obj, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(property, "property");
        return (V1) MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(map, property.getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    public static final <V> void setValue(Map<? super String, ? super V> map, Object obj, KProperty<?> property, V v) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(property, "property");
        map.put(property.getName(), v);
    }
}
