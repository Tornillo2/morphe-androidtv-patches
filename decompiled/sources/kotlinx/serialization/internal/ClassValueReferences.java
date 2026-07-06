package kotlinx.serialization.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueReferences\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,219:1\n1#2:220\n*E\n"})
@SuppressAnimalSniffer
public final class ClassValueReferences<T> extends ClassValue<MutableSoftReference<T>> {
    @Override // java.lang.ClassValue
    public /* bridge */ /* synthetic */ Object computeValue(Class cls) {
        return computeValue((Class<?>) cls);
    }

    public final T getOrSet(@NotNull Class<?> key, @NotNull final Function0<? extends T> factory) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(factory, "factory");
        T t = get(key);
        Intrinsics.checkNotNullExpressionValue(t, "get(...)");
        MutableSoftReference mutableSoftReference = (MutableSoftReference) t;
        T t2 = mutableSoftReference.reference.get();
        return t2 != null ? t2 : (T) mutableSoftReference.getOrSetWithLock(new Function0<T>() { // from class: kotlinx.serialization.internal.ClassValueReferences.getOrSet.2
            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                return factory.invoke();
            }
        });
    }

    public final boolean isStored(@NotNull Class<?> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return ((MutableSoftReference) get(key)).reference.get() != null;
    }

    @Override // java.lang.ClassValue
    @NotNull
    public MutableSoftReference<T> computeValue(@NotNull Class<?> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new MutableSoftReference<>();
    }
}
