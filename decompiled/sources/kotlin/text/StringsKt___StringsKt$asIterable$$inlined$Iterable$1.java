package kotlin.text;

import java.util.Iterator;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nIterables.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Iterables.kt\nkotlin/collections/CollectionsKt__IterablesKt$Iterable$1\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,17:1\n2560#2:18\n*E\n"})
public final class StringsKt___StringsKt$asIterable$$inlined$Iterable$1 implements Iterable<Character>, KMappedMarker {
    public final /* synthetic */ CharSequence $this_asIterable$inlined;

    public StringsKt___StringsKt$asIterable$$inlined$Iterable$1(CharSequence charSequence) {
        this.$this_asIterable$inlined = charSequence;
    }

    @Override // java.lang.Iterable
    public Iterator<Character> iterator() {
        return StringsKt__StringsKt.iterator(this.$this_asIterable$inlined);
    }
}
