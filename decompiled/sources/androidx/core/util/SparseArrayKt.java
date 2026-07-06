package androidx.core.util;

import android.util.SparseArray;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSparseArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseArray.kt\nandroidx/core/util/SparseArrayKt\n*L\n1#1,101:1\n77#1,4:102\n*S KotlinDebug\n*F\n+ 1 SparseArray.kt\nandroidx/core/util/SparseArrayKt\n*L\n73#1:102,4\n*E\n"})
public final class SparseArrayKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.core.util.SparseArrayKt$valueIterator$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C00381<T> implements Iterator<T>, KMappedMarker {
        public final /* synthetic */ SparseArray<T> $this_valueIterator;
        public int index;

        public C00381(SparseArray<T> sparseArray) {
            this.$this_valueIterator = sparseArray;
        }

        public final int getIndex() {
            return this.index;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.$this_valueIterator.size();
        }

        @Override // java.util.Iterator
        public T next() {
            SparseArray<T> sparseArray = this.$this_valueIterator;
            int i = this.index;
            this.index = i + 1;
            return sparseArray.valueAt(i);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setIndex(int i) {
            this.index = i;
        }
    }

    public static final <T> boolean contains(@NotNull SparseArray<T> sparseArray, int i) {
        return sparseArray.indexOfKey(i) >= 0;
    }

    public static final <T> boolean containsKey(@NotNull SparseArray<T> sparseArray, int i) {
        return sparseArray.indexOfKey(i) >= 0;
    }

    public static final <T> boolean containsValue(@NotNull SparseArray<T> sparseArray, T t) {
        return sparseArray.indexOfValue(t) >= 0;
    }

    public static final <T> void forEach(@NotNull SparseArray<T> sparseArray, @NotNull Function2<? super Integer, ? super T, Unit> function2) {
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            function2.invoke(Integer.valueOf(sparseArray.keyAt(i)), sparseArray.valueAt(i));
        }
    }

    public static final <T> T getOrDefault(@NotNull SparseArray<T> sparseArray, int i, T t) {
        T t2 = sparseArray.get(i);
        return t2 == null ? t : t2;
    }

    public static final <T> T getOrElse(@NotNull SparseArray<T> sparseArray, int i, @NotNull Function0<? extends T> function0) {
        T t = sparseArray.get(i);
        return t == null ? function0.invoke() : t;
    }

    public static final <T> int getSize(@NotNull SparseArray<T> sparseArray) {
        return sparseArray.size();
    }

    public static final <T> boolean isEmpty(@NotNull SparseArray<T> sparseArray) {
        return sparseArray.size() == 0;
    }

    public static final <T> boolean isNotEmpty(@NotNull SparseArray<T> sparseArray) {
        return sparseArray.size() != 0;
    }

    @NotNull
    public static final <T> IntIterator keyIterator(@NotNull final SparseArray<T> sparseArray) {
        return new IntIterator() { // from class: androidx.core.util.SparseArrayKt.keyIterator.1
            public int index;

            public final int getIndex() {
                return this.index;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < sparseArray.size();
            }

            @Override // kotlin.collections.IntIterator
            public int nextInt() {
                SparseArray<T> sparseArray2 = sparseArray;
                int i = this.index;
                this.index = i + 1;
                return sparseArray2.keyAt(i);
            }

            public final void setIndex(int i) {
                this.index = i;
            }
        };
    }

    @NotNull
    public static final <T> SparseArray<T> plus(@NotNull SparseArray<T> sparseArray, @NotNull SparseArray<T> sparseArray2) {
        SparseArray<T> sparseArray3 = new SparseArray<>(sparseArray2.size() + sparseArray.size());
        putAll(sparseArray3, sparseArray);
        putAll(sparseArray3, sparseArray2);
        return sparseArray3;
    }

    public static final <T> void putAll(@NotNull SparseArray<T> sparseArray, @NotNull SparseArray<T> sparseArray2) {
        int size = sparseArray2.size();
        for (int i = 0; i < size; i++) {
            sparseArray.put(sparseArray2.keyAt(i), sparseArray2.valueAt(i));
        }
    }

    public static final <T> boolean remove(@NotNull SparseArray<T> sparseArray, int i, T t) {
        int iIndexOfKey = sparseArray.indexOfKey(i);
        if (iIndexOfKey < 0 || !Intrinsics.areEqual(t, sparseArray.valueAt(iIndexOfKey))) {
            return false;
        }
        sparseArray.removeAt(iIndexOfKey);
        return true;
    }

    public static final <T> void set(@NotNull SparseArray<T> sparseArray, int i, T t) {
        sparseArray.put(i, t);
    }

    @NotNull
    public static final <T> Iterator<T> valueIterator(@NotNull SparseArray<T> sparseArray) {
        return new C00381(sparseArray);
    }
}
