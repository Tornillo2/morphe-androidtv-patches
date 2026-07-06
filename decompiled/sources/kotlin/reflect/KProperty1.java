package kotlin.reflect;

import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface KProperty1<T, V> extends KProperty<V>, Function1<T, V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Getter<T, V> extends KProperty.Getter<V>, Function1<T, V> {
    }

    V get(T t);

    @SinceKotlin(version = "1.1")
    @Nullable
    Object getDelegate(T t);

    @Override // kotlin.reflect.KProperty
    @NotNull
    Getter<T, V> getGetter();
}
