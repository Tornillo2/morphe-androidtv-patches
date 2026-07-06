package androidx.core.view;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Px;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ViewGroupKt {

    /* JADX INFO: renamed from: androidx.core.view.ViewGroupKt$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<View>, KMutableIterator {
        public final /* synthetic */ ViewGroup $this_iterator;
        public int index;

        public AnonymousClass1(ViewGroup viewGroup) {
            this.$this_iterator = viewGroup;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.$this_iterator.getChildCount();
        }

        @Override // java.util.Iterator
        public void remove() {
            ViewGroup viewGroup = this.$this_iterator;
            int i = this.index - 1;
            this.index = i;
            viewGroup.removeViewAt(i);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public View next() {
            ViewGroup viewGroup = this.$this_iterator;
            int i = this.index;
            this.index = i + 1;
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null) {
                return childAt;
            }
            throw new IndexOutOfBoundsException();
        }
    }

    public static final boolean contains(@NotNull ViewGroup viewGroup, @NotNull View view) {
        return viewGroup.indexOfChild(view) != -1;
    }

    public static final void forEach(@NotNull ViewGroup viewGroup, @NotNull Function1<? super View, Unit> function1) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            function1.invoke(viewGroup.getChildAt(i));
        }
    }

    public static final void forEachIndexed(@NotNull ViewGroup viewGroup, @NotNull Function2<? super Integer, ? super View, Unit> function2) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            function2.invoke(Integer.valueOf(i), viewGroup.getChildAt(i));
        }
    }

    @NotNull
    public static final View get(@NotNull ViewGroup viewGroup, int i) {
        View childAt = viewGroup.getChildAt(i);
        if (childAt != null) {
            return childAt;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Index: ", i, ", Size: ");
        sbM.append(viewGroup.getChildCount());
        throw new IndexOutOfBoundsException(sbM.toString());
    }

    @NotNull
    public static final Sequence<View> getChildren(@NotNull ViewGroup viewGroup) {
        return new ViewGroupKt$children$1(viewGroup);
    }

    @NotNull
    public static final Sequence<View> getDescendants(@NotNull ViewGroup viewGroup) {
        return new ViewGroupKt$special$$inlined$Sequence$1(viewGroup);
    }

    @NotNull
    public static final IntRange getIndices(@NotNull ViewGroup viewGroup) {
        return RangesKt___RangesKt.until(0, viewGroup.getChildCount());
    }

    public static final int getSize(@NotNull ViewGroup viewGroup) {
        return viewGroup.getChildCount();
    }

    public static final boolean isEmpty(@NotNull ViewGroup viewGroup) {
        return viewGroup.getChildCount() == 0;
    }

    public static final boolean isNotEmpty(@NotNull ViewGroup viewGroup) {
        return viewGroup.getChildCount() != 0;
    }

    @NotNull
    public static final Iterator<View> iterator(@NotNull ViewGroup viewGroup) {
        return new AnonymousClass1(viewGroup);
    }

    public static final void minusAssign(@NotNull ViewGroup viewGroup, @NotNull View view) {
        viewGroup.removeView(view);
    }

    public static final void plusAssign(@NotNull ViewGroup viewGroup, @NotNull View view) {
        viewGroup.addView(view);
    }

    public static final void setMargins(@NotNull ViewGroup.MarginLayoutParams marginLayoutParams, @Px int i) {
        marginLayoutParams.setMargins(i, i, i, i);
    }

    public static final void updateMargins(@NotNull ViewGroup.MarginLayoutParams marginLayoutParams, @Px int i, @Px int i2, @Px int i3, @Px int i4) {
        marginLayoutParams.setMargins(i, i2, i3, i4);
    }

    public static /* synthetic */ void updateMargins$default(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = marginLayoutParams.leftMargin;
        }
        if ((i5 & 2) != 0) {
            i2 = marginLayoutParams.topMargin;
        }
        if ((i5 & 4) != 0) {
            i3 = marginLayoutParams.rightMargin;
        }
        if ((i5 & 8) != 0) {
            i4 = marginLayoutParams.bottomMargin;
        }
        marginLayoutParams.setMargins(i, i2, i3, i4);
    }

    public static final void updateMarginsRelative(@NotNull ViewGroup.MarginLayoutParams marginLayoutParams, @Px int i, @Px int i2, @Px int i3, @Px int i4) {
        marginLayoutParams.setMarginStart(i);
        marginLayoutParams.topMargin = i2;
        marginLayoutParams.setMarginEnd(i3);
        marginLayoutParams.bottomMargin = i4;
    }

    public static /* synthetic */ void updateMarginsRelative$default(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = marginLayoutParams.getMarginStart();
        }
        if ((i5 & 2) != 0) {
            i2 = marginLayoutParams.topMargin;
        }
        if ((i5 & 4) != 0) {
            i3 = marginLayoutParams.getMarginEnd();
        }
        if ((i5 & 8) != 0) {
            i4 = marginLayoutParams.bottomMargin;
        }
        marginLayoutParams.setMarginStart(i);
        marginLayoutParams.topMargin = i2;
        marginLayoutParams.setMarginEnd(i3);
        marginLayoutParams.bottomMargin = i4;
    }
}
