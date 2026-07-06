package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/SequencesKt__SequencesKt$Sequence$1\n+ 2 ViewGroup.kt\nandroidx/core/view/ViewGroupKt\n*L\n1#1,680:1\n134#2:681\n*E\n"})
public final class ViewGroupKt$special$$inlined$Sequence$1 implements Sequence<View> {
    public final /* synthetic */ ViewGroup $this_descendants$inlined;

    public ViewGroupKt$special$$inlined$Sequence$1(ViewGroup viewGroup) {
        this.$this_descendants$inlined = viewGroup;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<View> iterator() {
        return new TreeIterator(new ViewGroupKt$children$1(this.$this_descendants$inlined).iterator(), ViewGroupKt$descendants$1$1.INSTANCE);
    }
}
