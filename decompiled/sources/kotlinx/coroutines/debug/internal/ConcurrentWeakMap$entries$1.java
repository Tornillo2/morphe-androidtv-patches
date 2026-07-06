package kotlinx.coroutines.debug.internal;

import java.util.Map;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.debug.internal.ConcurrentWeakMap;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ConcurrentWeakMap$entries$1<K, V> extends Lambda implements Function2<K, V, Map.Entry<K, V>> {
    public static final ConcurrentWeakMap$entries$1 INSTANCE = new ConcurrentWeakMap$entries$1(2);

    public ConcurrentWeakMap$entries$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Object obj, Object obj2) {
        return new ConcurrentWeakMap.Entry(obj, obj2);
    }

    @Override // kotlin.jvm.functions.Function2
    @NotNull
    public final Map.Entry<K, V> invoke(@NotNull K k, @NotNull V v) {
        return new ConcurrentWeakMap.Entry(k, v);
    }
}
