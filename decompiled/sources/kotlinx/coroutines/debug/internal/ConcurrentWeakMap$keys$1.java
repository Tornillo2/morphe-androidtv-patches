package kotlinx.coroutines.debug.internal;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ConcurrentWeakMap$keys$1<K, V> extends Lambda implements Function2<K, V, K> {
    public static final ConcurrentWeakMap$keys$1 INSTANCE = new ConcurrentWeakMap$keys$1(2);

    public ConcurrentWeakMap$keys$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    @NotNull
    public final K invoke(@NotNull K k, @NotNull V v) {
        return k;
    }
}
