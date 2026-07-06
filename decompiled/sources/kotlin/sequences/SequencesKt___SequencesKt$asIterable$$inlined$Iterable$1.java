package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nIterables.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Iterables.kt\nkotlin/collections/CollectionsKt__IterablesKt$Iterable$1\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,17:1\n3033#2:18\n*E\n"})
public final class SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1<T> implements Iterable<T>, KMappedMarker {
    public final /* synthetic */ Sequence $this_asIterable$inlined;

    public SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(Sequence sequence) {
        this.$this_asIterable$inlined = sequence;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.$this_asIterable$inlined.iterator();
    }
}
