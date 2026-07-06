package kotlinx.serialization.internal;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueParametrizedCache\n+ 2 Caching.kt\nkotlinx/serialization/internal/ClassValueReferences\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Caching.kt\nkotlinx/serialization/internal/ParametrizedCacheEntry\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n*L\n1#1,219:1\n84#2,3:220\n89#2:224\n1#3:223\n1#3:234\n212#4:225\n213#4:230\n214#4:233\n1557#5:226\n1628#5,3:227\n72#6,2:231\n*S KotlinDebug\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueParametrizedCache\n*L\n128#1:220,3\n128#1:224\n128#1:223\n129#1:234\n129#1:225\n129#1:230\n129#1:233\n129#1:226\n129#1:227,3\n129#1:231,2\n*E\n"})
public final class ClassValueParametrizedCache<T> implements ParametrizedSerializerCache<T> {

    @NotNull
    public final ClassValueReferences<ParametrizedCacheEntry<T>> classValue;

    @NotNull
    public final Function2<KClass<Object>, List<? extends KType>, KSerializer<T>> compute;

    /* JADX WARN: Multi-variable type inference failed */
    public ClassValueParametrizedCache(@NotNull Function2<? super KClass<Object>, ? super List<? extends KType>, ? extends KSerializer<T>> compute) {
        Intrinsics.checkNotNullParameter(compute, "compute");
        this.compute = compute;
        this.classValue = new ClassValueReferences<>();
    }

    @Override // kotlinx.serialization.internal.ParametrizedSerializerCache
    @NotNull
    /* JADX INFO: renamed from: get-gIAlu-s, reason: not valid java name */
    public Object mo2148getgIAlus(@NotNull KClass<Object> key, @NotNull List<? extends KType> types) {
        Object objCreateFailure;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(types, "types");
        ParametrizedCacheEntry<T> parametrizedCacheEntry = this.classValue.get(JvmClassMappingKt.getJavaClass((KClass) key));
        Intrinsics.checkNotNullExpressionValue(parametrizedCacheEntry, "get(...)");
        MutableSoftReference mutableSoftReference = (MutableSoftReference) parametrizedCacheEntry;
        T t = mutableSoftReference.reference.get();
        if (t == null) {
            t = (T) mutableSoftReference.getOrSetWithLock(new ClassValueParametrizedCache$getgIAlus$$inlined$getOrSet$1());
        }
        ParametrizedCacheEntry parametrizedCacheEntry2 = t;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(types, 10));
        Iterator<T> it = types.iterator();
        while (it.hasNext()) {
            arrayList.add(new KTypeWrapper((KType) it.next()));
        }
        ConcurrentHashMap<List<KTypeWrapper>, Result<KSerializer<T>>> concurrentHashMap = parametrizedCacheEntry2.serializers;
        Result<KSerializer<T>> result = concurrentHashMap.get(arrayList);
        if (result == null) {
            try {
                objCreateFailure = (KSerializer) this.compute.invoke(key, types);
            } catch (Throwable th) {
                objCreateFailure = ResultKt.createFailure(th);
            }
            Result<KSerializer<T>> result2 = new Result<>(objCreateFailure);
            Result<KSerializer<T>> resultPutIfAbsent = concurrentHashMap.putIfAbsent(arrayList, result2);
            result = resultPutIfAbsent == null ? result2 : resultPutIfAbsent;
        }
        return result.value;
    }
}
