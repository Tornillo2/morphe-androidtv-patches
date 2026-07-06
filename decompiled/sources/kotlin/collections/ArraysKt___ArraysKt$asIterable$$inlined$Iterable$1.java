package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nIterables.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Iterables.kt\nkotlin/collections/CollectionsKt__IterablesKt$Iterable$1\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,17:1\n24864#2:18\n*E\n"})
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$1<T> implements Iterable<T>, KMappedMarker {
    public final /* synthetic */ Object[] $this_asIterable$inlined;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$1(Object[] objArr) {
        this.$this_asIterable$inlined = objArr;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return ArrayIteratorKt.iterator(this.$this_asIterable$inlined);
    }
}
