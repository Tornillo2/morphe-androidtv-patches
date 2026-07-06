package androidx.collection;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import androidx.annotation.IntRange;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nLongList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LongList.kt\nandroidx/collection/MutableLongList\n+ 2 LongList.kt\nandroidx/collection/LongList\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,969:1\n549#1:970\n70#2:971\n253#2,6:974\n70#2:980\n70#2:981\n70#2:982\n70#2:989\n70#2:990\n13607#3,2:972\n1675#3,6:983\n*S KotlinDebug\n*F\n+ 1 LongList.kt\nandroidx/collection/MutableLongList\n*L\n692#1:970\n753#1:971\n772#1:974,6\n783#1:980\n787#1:981\n834#1:982\n850#1:989\n869#1:990\n763#1:972,2\n836#1:983,6\n*E\n"})
public final class MutableLongList extends LongList {
    public MutableLongList() {
        this(0, 1, null);
    }

    public static /* synthetic */ void trim$default(MutableLongList mutableLongList, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = mutableLongList._size;
        }
        mutableLongList.trim(i);
    }

    public final boolean add(long j) {
        ensureCapacity(this._size + 1);
        long[] jArr = this.content;
        int i = this._size;
        jArr[i] = j;
        this._size = i + 1;
        return true;
    }

    public final boolean addAll(@IntRange(from = 0) int i, @NotNull long[] elements) {
        int i2;
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (i < 0 || i > (i2 = this._size)) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Index ", i, " must be in 0..");
            sbM.append(this._size);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        if (elements.length == 0) {
            return false;
        }
        ensureCapacity(i2 + elements.length);
        long[] jArr = this.content;
        int i3 = this._size;
        if (i != i3) {
            ArraysKt___ArraysJvmKt.copyInto(jArr, jArr, elements.length + i, i, i3);
        }
        ArraysKt___ArraysJvmKt.copyInto$default(elements, jArr, i, 0, 0, 12, (Object) null);
        this._size += elements.length;
        return true;
    }

    public final void clear() {
        this._size = 0;
    }

    public final void ensureCapacity(int i) {
        long[] jArr = this.content;
        if (jArr.length < i) {
            long[] jArrCopyOf = Arrays.copyOf(jArr, Math.max(i, (jArr.length * 3) / 2));
            Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(this, newSize)");
            this.content = jArrCopyOf;
        }
    }

    public final int getCapacity() {
        return this.content.length;
    }

    public final void minusAssign(long j) {
        remove(j);
    }

    public final void plusAssign(@NotNull LongList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        addAll(this._size, elements);
    }

    public final boolean remove(long j) {
        int iIndexOf = indexOf(j);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public final boolean removeAll(@NotNull long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        for (long j : elements) {
            remove(j);
        }
        return i != this._size;
    }

    public final long removeAt(@IntRange(from = 0) int i) {
        int i2;
        if (i < 0 || i >= (i2 = this._size)) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Index ", i, " must be in 0..");
            sbM.append(this._size - 1);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        long[] jArr = this.content;
        long j = jArr[i];
        if (i != i2 - 1) {
            ArraysKt___ArraysJvmKt.copyInto(jArr, jArr, i, i + 1, i2);
        }
        this._size--;
        return j;
    }

    public final void removeRange(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        int i3;
        if (i < 0 || i > (i3 = this._size) || i2 < 0 || i2 > i3) {
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("Start (", i, ") and end (", i2, ") must be in 0..");
            sbM.append(this._size);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException("Start (" + i + ") is more than end (" + i2 + ')');
        }
        if (i2 != i) {
            if (i2 < i3) {
                long[] jArr = this.content;
                ArraysKt___ArraysJvmKt.copyInto(jArr, jArr, i, i2, i3);
            }
            this._size -= i2 - i;
        }
    }

    public final boolean retainAll(@NotNull long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        long[] jArr = this.content;
        int i2 = i - 1;
        while (true) {
            int i3 = 0;
            int i4 = -1;
            if (-1 >= i2) {
                break;
            }
            long j = jArr[i2];
            int length = elements.length;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (elements[i3] == j) {
                    i4 = i3;
                    break;
                }
                i3++;
            }
            if (i4 < 0) {
                removeAt(i2);
            }
            i2--;
        }
        return i != this._size;
    }

    public final long set(@IntRange(from = 0) int i, long j) {
        if (i < 0 || i >= this._size) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("set index ", i, " must be between 0 .. ");
            sbM.append(this._size - 1);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        long[] jArr = this.content;
        long j2 = jArr[i];
        jArr[i] = j;
        return j2;
    }

    public final void sort() {
        ArraysKt___ArraysJvmKt.sort(this.content, 0, this._size);
    }

    public final void sortDescending() {
        ArraysKt___ArraysKt.sortDescending(this.content, 0, this._size);
    }

    public final void trim(int i) {
        int iMax = Math.max(i, this._size);
        long[] jArr = this.content;
        if (jArr.length > iMax) {
            long[] jArrCopyOf = Arrays.copyOf(jArr, iMax);
            Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(this, newSize)");
            this.content = jArrCopyOf;
        }
    }

    public MutableLongList(int i) {
        super(i);
    }

    public final void minusAssign(@NotNull long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (long j : elements) {
            remove(j);
        }
    }

    public final void plusAssign(@NotNull long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        addAll(this._size, elements);
    }

    public MutableLongList(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        super((i2 & 1) != 0 ? 16 : i);
    }

    public final void plusAssign(long j) {
        add(j);
    }

    public final void add(@IntRange(from = 0) int i, long j) {
        int i2;
        if (i >= 0 && i <= (i2 = this._size)) {
            ensureCapacity(i2 + 1);
            long[] jArr = this.content;
            int i3 = this._size;
            if (i != i3) {
                ArraysKt___ArraysJvmKt.copyInto(jArr, jArr, i + 1, i, i3);
            }
            jArr[i] = j;
            this._size++;
            return;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Index ", i, " must be in 0..");
        sbM.append(this._size);
        throw new IndexOutOfBoundsException(sbM.toString());
    }

    public final void minusAssign(@NotNull LongList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        long[] jArr = elements.content;
        int i = elements._size;
        for (int i2 = 0; i2 < i; i2++) {
            remove(jArr[i2]);
        }
    }

    public final boolean removeAll(@NotNull LongList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        int i2 = elements._size - 1;
        if (i2 >= 0) {
            int i3 = 0;
            while (true) {
                remove(elements.get(i3));
                if (i3 == i2) {
                    break;
                }
                i3++;
            }
        }
        return i != this._size;
    }

    public final boolean retainAll(@NotNull LongList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        long[] jArr = this.content;
        for (int i2 = i - 1; -1 < i2; i2--) {
            if (!elements.contains(jArr[i2])) {
                removeAt(i2);
            }
        }
        return i != this._size;
    }

    public final boolean addAll(@IntRange(from = 0) int i, @NotNull LongList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (i >= 0 && i <= this._size) {
            if (elements.isEmpty()) {
                return false;
            }
            ensureCapacity(this._size + elements._size);
            long[] jArr = this.content;
            int i2 = this._size;
            if (i != i2) {
                ArraysKt___ArraysJvmKt.copyInto(jArr, jArr, elements._size + i, i, i2);
            }
            ArraysKt___ArraysJvmKt.copyInto(elements.content, jArr, i, 0, elements._size);
            this._size += elements._size;
            return true;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Index ", i, " must be in 0..");
        sbM.append(this._size);
        throw new IndexOutOfBoundsException(sbM.toString());
    }

    public final boolean addAll(@NotNull LongList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return addAll(this._size, elements);
    }

    public final boolean addAll(@NotNull long[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return addAll(this._size, elements);
    }
}
