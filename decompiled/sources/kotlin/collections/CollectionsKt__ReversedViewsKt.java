package kotlin.collections;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import java.util.List;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsKt {
    public static final int access$reverseIteratorIndex(List list, int i) {
        return CollectionsKt__CollectionsKt.getLastIndex(list) - i;
    }

    @NotNull
    public static final <T> List<T> asReversed(@NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return new ReversedListReadOnly(list);
    }

    @JvmName(name = "asReversedMutable")
    @NotNull
    public static final <T> List<T> asReversedMutable(@NotNull List<T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return new ReversedList(list);
    }

    public static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(List<?> list, int i) {
        if (i >= 0 && i <= CollectionsKt__CollectionsKt.getLastIndex(list)) {
            return CollectionsKt__CollectionsKt.getLastIndex(list) - i;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Element index ", i, " must be in range [");
        sbM.append(new IntRange(0, CollectionsKt__CollectionsKt.getLastIndex(list), 1));
        sbM.append("].");
        throw new IndexOutOfBoundsException(sbM.toString());
    }

    public static final int reverseIteratorIndex$CollectionsKt__ReversedViewsKt(List<?> list, int i) {
        return CollectionsKt__CollectionsKt.getLastIndex(list) - i;
    }

    public static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(List<?> list, int i) {
        if (i >= 0 && i <= list.size()) {
            return list.size() - i;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Position index ", i, " must be in range [");
        sbM.append(new IntRange(0, list.size(), 1));
        sbM.append("].");
        throw new IndexOutOfBoundsException(sbM.toString());
    }
}
