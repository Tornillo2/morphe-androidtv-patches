package androidx.core.util;

import android.util.SparseIntArray;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSparseIntArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseIntArray.kt\nandroidx/core/util/SparseIntArrayKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,100:1\n76#1,4:102\n1#2:101\n*S KotlinDebug\n*F\n+ 1 SparseIntArray.kt\nandroidx/core/util/SparseIntArrayKt\n*L\n72#1:102,4\n*E\n"})
public final class SparseIntArrayKt {
    public static final boolean contains(@NotNull SparseIntArray sparseIntArray, int i) {
        return sparseIntArray.indexOfKey(i) >= 0;
    }

    public static final boolean containsKey(@NotNull SparseIntArray sparseIntArray, int i) {
        return sparseIntArray.indexOfKey(i) >= 0;
    }

    public static final boolean containsValue(@NotNull SparseIntArray sparseIntArray, int i) {
        return sparseIntArray.indexOfValue(i) >= 0;
    }

    public static final void forEach(@NotNull SparseIntArray sparseIntArray, @NotNull Function2<? super Integer, ? super Integer, Unit> function2) {
        int size = sparseIntArray.size();
        for (int i = 0; i < size; i++) {
            function2.invoke(Integer.valueOf(sparseIntArray.keyAt(i)), Integer.valueOf(sparseIntArray.valueAt(i)));
        }
    }

    public static final int getOrDefault(@NotNull SparseIntArray sparseIntArray, int i, int i2) {
        return sparseIntArray.get(i, i2);
    }

    public static final int getOrElse(@NotNull SparseIntArray sparseIntArray, int i, @NotNull Function0<Integer> function0) {
        int iIndexOfKey = sparseIntArray.indexOfKey(i);
        return iIndexOfKey >= 0 ? sparseIntArray.valueAt(iIndexOfKey) : function0.invoke().intValue();
    }

    public static final int getSize(@NotNull SparseIntArray sparseIntArray) {
        return sparseIntArray.size();
    }

    public static final boolean isEmpty(@NotNull SparseIntArray sparseIntArray) {
        return sparseIntArray.size() == 0;
    }

    public static final boolean isNotEmpty(@NotNull SparseIntArray sparseIntArray) {
        return sparseIntArray.size() != 0;
    }

    @NotNull
    public static final IntIterator keyIterator(@NotNull final SparseIntArray sparseIntArray) {
        return new IntIterator() { // from class: androidx.core.util.SparseIntArrayKt.keyIterator.1
            public int index;

            public final int getIndex() {
                return this.index;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < sparseIntArray.size();
            }

            @Override // kotlin.collections.IntIterator
            public int nextInt() {
                SparseIntArray sparseIntArray2 = sparseIntArray;
                int i = this.index;
                this.index = i + 1;
                return sparseIntArray2.keyAt(i);
            }

            public final void setIndex(int i) {
                this.index = i;
            }
        };
    }

    @NotNull
    public static final SparseIntArray plus(@NotNull SparseIntArray sparseIntArray, @NotNull SparseIntArray sparseIntArray2) {
        SparseIntArray sparseIntArray3 = new SparseIntArray(sparseIntArray2.size() + sparseIntArray.size());
        putAll(sparseIntArray3, sparseIntArray);
        putAll(sparseIntArray3, sparseIntArray2);
        return sparseIntArray3;
    }

    public static final void putAll(@NotNull SparseIntArray sparseIntArray, @NotNull SparseIntArray sparseIntArray2) {
        int size = sparseIntArray2.size();
        for (int i = 0; i < size; i++) {
            sparseIntArray.put(sparseIntArray2.keyAt(i), sparseIntArray2.valueAt(i));
        }
    }

    public static final boolean remove(@NotNull SparseIntArray sparseIntArray, int i, int i2) {
        int iIndexOfKey = sparseIntArray.indexOfKey(i);
        if (iIndexOfKey < 0 || i2 != sparseIntArray.valueAt(iIndexOfKey)) {
            return false;
        }
        sparseIntArray.removeAt(iIndexOfKey);
        return true;
    }

    public static final void set(@NotNull SparseIntArray sparseIntArray, int i, int i2) {
        sparseIntArray.put(i, i2);
    }

    @NotNull
    public static final IntIterator valueIterator(@NotNull final SparseIntArray sparseIntArray) {
        return new IntIterator() { // from class: androidx.core.util.SparseIntArrayKt.valueIterator.1
            public int index;

            public final int getIndex() {
                return this.index;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < sparseIntArray.size();
            }

            @Override // kotlin.collections.IntIterator
            public int nextInt() {
                SparseIntArray sparseIntArray2 = sparseIntArray;
                int i = this.index;
                this.index = i + 1;
                return sparseIntArray2.valueAt(i);
            }

            public final void setIndex(int i) {
                this.index = i;
            }
        };
    }
}
