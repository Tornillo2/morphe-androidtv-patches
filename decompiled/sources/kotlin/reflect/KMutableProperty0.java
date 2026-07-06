package kotlin.reflect;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KMutableProperty;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface KMutableProperty0<V> extends KProperty0<V>, KMutableProperty<V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Setter<V> extends KMutableProperty.Setter<V>, Function1<V, Unit> {
    }

    @Override // kotlin.reflect.KMutableProperty
    @NotNull
    Setter<V> getSetter();

    void set(V v);
}
