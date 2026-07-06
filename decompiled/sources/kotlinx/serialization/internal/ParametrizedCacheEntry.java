package kotlinx.serialization.internal;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ParametrizedCacheEntry\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,219:1\n1557#2:220\n1628#2,3:221\n72#3,2:224\n1#4:226\n1#4:227\n*S KotlinDebug\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ParametrizedCacheEntry\n*L\n212#1:220\n212#1:221,3\n213#1:224,2\n213#1:227\n*E\n"})
public final class ParametrizedCacheEntry<T> {

    @NotNull
    public final ConcurrentHashMap<List<KTypeWrapper>, Result<KSerializer<T>>> serializers = new ConcurrentHashMap<>();

    @NotNull
    /* JADX INFO: renamed from: computeIfAbsent-gIAlu-s, reason: not valid java name */
    public final Object m2153computeIfAbsentgIAlus(@NotNull List<? extends KType> types, @NotNull Function0<? extends KSerializer<T>> producer) {
        Object objCreateFailure;
        Intrinsics.checkNotNullParameter(types, "types");
        Intrinsics.checkNotNullParameter(producer, "producer");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(types, 10));
        Iterator<T> it = types.iterator();
        while (it.hasNext()) {
            arrayList.add(new KTypeWrapper((KType) it.next()));
        }
        ConcurrentHashMap<List<KTypeWrapper>, Result<KSerializer<T>>> concurrentHashMap = this.serializers;
        Result<KSerializer<T>> result = concurrentHashMap.get(arrayList);
        if (result == null) {
            try {
                objCreateFailure = (KSerializer) producer.invoke();
            } catch (Throwable th) {
                objCreateFailure = ResultKt.createFailure(th);
            }
            result = new Result<>(objCreateFailure);
            Result<KSerializer<T>> resultPutIfAbsent = concurrentHashMap.putIfAbsent(arrayList, result);
            if (resultPutIfAbsent != null) {
                result = resultPutIfAbsent;
            }
        }
        return result.value;
    }
}
