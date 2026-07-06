package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/SequencesKt__SequencesKt$Sequence$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,22:1\n3641#2:23\n*E\n"})
public final class CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1<T> implements Sequence<T> {
    public final /* synthetic */ Iterable $this_asSequence$inlined;

    public CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(Iterable iterable) {
        this.$this_asSequence$inlined = iterable;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return this.$this_asSequence$inlined.iterator();
    }
}
