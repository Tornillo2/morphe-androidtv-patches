package kotlinx.serialization.internal;

import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueCache\n+ 2 Caching.kt\nkotlinx/serialization/internal/ClassValueReferences\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,219:1\n84#2,3:220\n89#2:224\n1#3:223\n*S KotlinDebug\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueCache\n*L\n52#1:220,3\n52#1:224\n52#1:223\n*E\n"})
public final class ClassValueCache<T> implements SerializerCache<T> {

    @NotNull
    public final ClassValueReferences<CacheEntry<T>> classValue;

    @NotNull
    public final Function1<KClass<?>, KSerializer<T>> compute;

    /* JADX WARN: Multi-variable type inference failed */
    public ClassValueCache(@NotNull Function1<? super KClass<?>, ? extends KSerializer<T>> compute) {
        Intrinsics.checkNotNullParameter(compute, "compute");
        this.compute = compute;
        this.classValue = new ClassValueReferences<>();
    }

    @Override // kotlinx.serialization.internal.SerializerCache
    @Nullable
    public KSerializer<T> get(@NotNull final KClass<Object> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        CacheEntry<T> cacheEntry = this.classValue.get(JvmClassMappingKt.getJavaClass((KClass) key));
        Intrinsics.checkNotNullExpressionValue(cacheEntry, "get(...)");
        MutableSoftReference mutableSoftReference = (MutableSoftReference) cacheEntry;
        T t = mutableSoftReference.reference.get();
        if (t == null) {
            t = (T) mutableSoftReference.getOrSetWithLock(new Function0<T>() { // from class: kotlinx.serialization.internal.ClassValueCache$get$$inlined$getOrSet$1
                @Override // kotlin.jvm.functions.Function0
                public final T invoke() {
                    return (T) new CacheEntry(this.this$0.compute.invoke(key));
                }
            });
        }
        return t.serializer;
    }

    @NotNull
    public final Function1<KClass<?>, KSerializer<T>> getCompute() {
        return this.compute;
    }

    @Override // kotlinx.serialization.internal.SerializerCache
    public boolean isStored(@NotNull KClass<?> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.classValue.isStored(JvmClassMappingKt.getJavaClass((KClass) key));
    }
}
