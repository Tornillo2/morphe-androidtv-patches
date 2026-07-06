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
@SourceDebugExtension({"SMAP\nIntList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntList.kt\nandroidx/collection/MutableIntList\n+ 2 IntList.kt\nandroidx/collection/IntList\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,969:1\n549#1:970\n70#2:971\n253#2,6:974\n70#2:980\n70#2:981\n70#2:982\n70#2:989\n70#2:990\n13600#3,2:972\n1663#3,6:983\n*S KotlinDebug\n*F\n+ 1 IntList.kt\nandroidx/collection/MutableIntList\n*L\n692#1:970\n753#1:971\n772#1:974,6\n783#1:980\n787#1:981\n834#1:982\n850#1:989\n869#1:990\n763#1:972,2\n836#1:983,6\n*E\n"})
public final class MutableIntList extends IntList {
    public MutableIntList() {
        this(0, 1, null);
    }

    public static /* synthetic */ void trim$default(MutableIntList mutableIntList, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = mutableIntList._size;
        }
        mutableIntList.trim(i);
    }

    public final boolean add(int i) {
        ensureCapacity(this._size + 1);
        int[] iArr = this.content;
        int i2 = this._size;
        iArr[i2] = i;
        this._size = i2 + 1;
        return true;
    }

    public final boolean addAll(@IntRange(from = 0) int i, @NotNull int[] elements) {
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
        int[] iArr = this.content;
        int i3 = this._size;
        if (i != i3) {
            ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, elements.length + i, i, i3);
        }
        ArraysKt___ArraysJvmKt.copyInto$default(elements, iArr, i, 0, 0, 12, (Object) null);
        this._size += elements.length;
        return true;
    }

    public final void clear() {
        this._size = 0;
    }

    public final void ensureCapacity(int i) {
        int[] iArr = this.content;
        if (iArr.length < i) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, Math.max(i, (iArr.length * 3) / 2));
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            this.content = iArrCopyOf;
        }
    }

    public final int getCapacity() {
        return this.content.length;
    }

    public final void minusAssign(int i) {
        remove(i);
    }

    public final void plusAssign(@NotNull IntList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        addAll(this._size, elements);
    }

    public final boolean remove(int i) {
        int iIndexOf = indexOf(i);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public final boolean removeAll(@NotNull int[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        for (int i2 : elements) {
            remove(i2);
        }
        return i != this._size;
    }

    public final int removeAt(@IntRange(from = 0) int i) {
        int i2;
        if (i < 0 || i >= (i2 = this._size)) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Index ", i, " must be in 0..");
            sbM.append(this._size - 1);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        int[] iArr = this.content;
        int i3 = iArr[i];
        if (i != i2 - 1) {
            ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, i, i + 1, i2);
        }
        this._size--;
        return i3;
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
                int[] iArr = this.content;
                ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, i, i2, i3);
            }
            this._size -= i2 - i;
        }
    }

    public final boolean retainAll(@NotNull int[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        int[] iArr = this.content;
        int i2 = i - 1;
        while (true) {
            int i3 = 0;
            int i4 = -1;
            if (-1 >= i2) {
                break;
            }
            int i5 = iArr[i2];
            int length = elements.length;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (elements[i3] == i5) {
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

    public final int set(@IntRange(from = 0) int i, int i2) {
        if (i < 0 || i >= this._size) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("set index ", i, " must be between 0 .. ");
            sbM.append(this._size - 1);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        int[] iArr = this.content;
        int i3 = iArr[i];
        iArr[i] = i2;
        return i3;
    }

    public final void sort() {
        ArraysKt___ArraysJvmKt.sort(this.content, 0, this._size);
    }

    public final void sortDescending() {
        ArraysKt___ArraysKt.sortDescending(this.content, 0, this._size);
    }

    public final void trim(int i) {
        int iMax = Math.max(i, this._size);
        int[] iArr = this.content;
        if (iArr.length > iMax) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iMax);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            this.content = iArrCopyOf;
        }
    }

    public MutableIntList(int i) {
        super(i);
    }

    public final void minusAssign(@NotNull int[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        for (int i : elements) {
            remove(i);
        }
    }

    public final void plusAssign(@NotNull int[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        addAll(this._size, elements);
    }

    public MutableIntList(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        super((i2 & 1) != 0 ? 16 : i);
    }

    public final void plusAssign(int i) {
        add(i);
    }

    public final void add(@IntRange(from = 0) int i, int i2) {
        int i3;
        if (i >= 0 && i <= (i3 = this._size)) {
            ensureCapacity(i3 + 1);
            int[] iArr = this.content;
            int i4 = this._size;
            if (i != i4) {
                ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, i + 1, i, i4);
            }
            iArr[i] = i2;
            this._size++;
            return;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Index ", i, " must be in 0..");
        sbM.append(this._size);
        throw new IndexOutOfBoundsException(sbM.toString());
    }

    public final void minusAssign(@NotNull IntList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int[] iArr = elements.content;
        int i = elements._size;
        for (int i2 = 0; i2 < i; i2++) {
            remove(iArr[i2]);
        }
    }

    public final boolean removeAll(@NotNull IntList elements) {
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

    public final boolean retainAll(@NotNull IntList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        int i = this._size;
        int[] iArr = this.content;
        for (int i2 = i - 1; -1 < i2; i2--) {
            if (!elements.contains(iArr[i2])) {
                removeAt(i2);
            }
        }
        return i != this._size;
    }

    public final boolean addAll(@IntRange(from = 0) int i, @NotNull IntList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (i >= 0 && i <= this._size) {
            if (elements.isEmpty()) {
                return false;
            }
            ensureCapacity(this._size + elements._size);
            int[] iArr = this.content;
            int i2 = this._size;
            if (i != i2) {
                ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, elements._size + i, i, i2);
            }
            ArraysKt___ArraysJvmKt.copyInto(elements.content, iArr, i, 0, elements._size);
            this._size += elements._size;
            return true;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Index ", i, " must be in 0..");
        sbM.append(this._size);
        throw new IndexOutOfBoundsException(sbM.toString());
    }

    public final boolean addAll(@NotNull IntList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return addAll(this._size, elements);
    }

    public final boolean addAll(@NotNull int[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return addAll(this._size, elements);
    }
}
