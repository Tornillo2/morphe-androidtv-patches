package androidx.core.util;

import android.util.SparseBooleanArray;
import kotlin.Unit;
import kotlin.collections.BooleanIterator;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSparseBooleanArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseBooleanArray.kt\nandroidx/core/util/SparseBooleanArrayKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n78#1,4:104\n1#2:103\n*S KotlinDebug\n*F\n+ 1 SparseBooleanArray.kt\nandroidx/core/util/SparseBooleanArrayKt\n*L\n74#1:104,4\n*E\n"})
public final class SparseBooleanArrayKt {
    public static final boolean contains(@NotNull SparseBooleanArray sparseBooleanArray, int i) {
        return sparseBooleanArray.indexOfKey(i) >= 0;
    }

    public static final boolean containsKey(@NotNull SparseBooleanArray sparseBooleanArray, int i) {
        return sparseBooleanArray.indexOfKey(i) >= 0;
    }

    public static final boolean containsValue(@NotNull SparseBooleanArray sparseBooleanArray, boolean z) {
        return sparseBooleanArray.indexOfValue(z) >= 0;
    }

    public static final void forEach(@NotNull SparseBooleanArray sparseBooleanArray, @NotNull Function2<? super Integer, ? super Boolean, Unit> function2) {
        int size = sparseBooleanArray.size();
        for (int i = 0; i < size; i++) {
            function2.invoke(Integer.valueOf(sparseBooleanArray.keyAt(i)), Boolean.valueOf(sparseBooleanArray.valueAt(i)));
        }
    }

    public static final boolean getOrDefault(@NotNull SparseBooleanArray sparseBooleanArray, int i, boolean z) {
        return sparseBooleanArray.get(i, z);
    }

    public static final boolean getOrElse(@NotNull SparseBooleanArray sparseBooleanArray, int i, @NotNull Function0<Boolean> function0) {
        int iIndexOfKey = sparseBooleanArray.indexOfKey(i);
        return iIndexOfKey >= 0 ? sparseBooleanArray.valueAt(iIndexOfKey) : function0.invoke().booleanValue();
    }

    public static final int getSize(@NotNull SparseBooleanArray sparseBooleanArray) {
        return sparseBooleanArray.size();
    }

    public static final boolean isEmpty(@NotNull SparseBooleanArray sparseBooleanArray) {
        return sparseBooleanArray.size() == 0;
    }

    public static final boolean isNotEmpty(@NotNull SparseBooleanArray sparseBooleanArray) {
        return sparseBooleanArray.size() != 0;
    }

    @NotNull
    public static final IntIterator keyIterator(@NotNull final SparseBooleanArray sparseBooleanArray) {
        return new IntIterator() { // from class: androidx.core.util.SparseBooleanArrayKt.keyIterator.1
            public int index;

            public final int getIndex() {
                return this.index;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < sparseBooleanArray.size();
            }

            @Override // kotlin.collections.IntIterator
            public int nextInt() {
                SparseBooleanArray sparseBooleanArray2 = sparseBooleanArray;
                int i = this.index;
                this.index = i + 1;
                return sparseBooleanArray2.keyAt(i);
            }

            public final void setIndex(int i) {
                this.index = i;
            }
        };
    }

    @NotNull
    public static final SparseBooleanArray plus(@NotNull SparseBooleanArray sparseBooleanArray, @NotNull SparseBooleanArray sparseBooleanArray2) {
        SparseBooleanArray sparseBooleanArray3 = new SparseBooleanArray(sparseBooleanArray2.size() + sparseBooleanArray.size());
        putAll(sparseBooleanArray3, sparseBooleanArray);
        putAll(sparseBooleanArray3, sparseBooleanArray2);
        return sparseBooleanArray3;
    }

    public static final void putAll(@NotNull SparseBooleanArray sparseBooleanArray, @NotNull SparseBooleanArray sparseBooleanArray2) {
        int size = sparseBooleanArray2.size();
        for (int i = 0; i < size; i++) {
            sparseBooleanArray.put(sparseBooleanArray2.keyAt(i), sparseBooleanArray2.valueAt(i));
        }
    }

    public static final boolean remove(@NotNull SparseBooleanArray sparseBooleanArray, int i, boolean z) {
        int iIndexOfKey = sparseBooleanArray.indexOfKey(i);
        if (iIndexOfKey < 0 || z != sparseBooleanArray.valueAt(iIndexOfKey)) {
            return false;
        }
        sparseBooleanArray.delete(i);
        return true;
    }

    public static final void set(@NotNull SparseBooleanArray sparseBooleanArray, int i, boolean z) {
        sparseBooleanArray.put(i, z);
    }

    @NotNull
    public static final BooleanIterator valueIterator(@NotNull final SparseBooleanArray sparseBooleanArray) {
        return new BooleanIterator() { // from class: androidx.core.util.SparseBooleanArrayKt.valueIterator.1
            public int index;

            public final int getIndex() {
                return this.index;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < sparseBooleanArray.size();
            }

            @Override // kotlin.collections.BooleanIterator
            public boolean nextBoolean() {
                SparseBooleanArray sparseBooleanArray2 = sparseBooleanArray;
                int i = this.index;
                this.index = i + 1;
                return sparseBooleanArray2.valueAt(i);
            }

            public final void setIndex(int i) {
                this.index = i;
            }
        };
    }
}
