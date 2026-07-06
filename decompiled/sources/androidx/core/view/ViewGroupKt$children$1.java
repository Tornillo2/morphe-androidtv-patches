package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt;
import java.util.Iterator;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ViewGroupKt$children$1 implements Sequence<View> {
    public final /* synthetic */ ViewGroup $this_children;

    public ViewGroupKt$children$1(ViewGroup viewGroup) {
        this.$this_children = viewGroup;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<View> iterator() {
        return new ViewGroupKt.AnonymousClass1(this.$this_children);
    }
}
