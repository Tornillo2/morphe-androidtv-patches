package kotlinx.serialization.internal;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class CachingKt {
    public static final boolean useClassValue;

    static {
        boolean z;
        try {
            Class.forName("java.lang.ClassValue");
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        useClassValue = z;
    }

    @NotNull
    public static final <T> SerializerCache<T> createCache(@NotNull Function1<? super KClass<?>, ? extends KSerializer<T>> factory) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        return useClassValue ? new ClassValueCache(factory) : new ConcurrentHashMapCache(factory);
    }

    @NotNull
    public static final <T> ParametrizedSerializerCache<T> createParametrizedCache(@NotNull Function2<? super KClass<Object>, ? super List<? extends KType>, ? extends KSerializer<T>> factory) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        return useClassValue ? new ClassValueParametrizedCache(factory) : new ConcurrentHashMapParametrizedCache(factory);
    }
}
