package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ArraySetKt {
    public static final int ARRAY_SET_BASE_SIZE = 4;

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> void addAllInternal(@NotNull ArraySet<E> arraySet, @NotNull ArraySet<? extends E> array) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        Intrinsics.checkNotNullParameter(array, "array");
        int i = array._size;
        arraySet.ensureCapacity(arraySet._size + i);
        if (arraySet._size != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                arraySet.add(array.array[i2]);
            }
            return;
        }
        if (i > 0) {
            ArraysKt___ArraysJvmKt.copyInto$default(array.hashes, arraySet.hashes, 0, 0, i, 6, (Object) null);
            ArraysKt___ArraysJvmKt.copyInto$default(array.array, arraySet.array, 0, 0, i, 6, (Object) null);
            if (arraySet._size != 0) {
                throw new ConcurrentModificationException();
            }
            arraySet._size = i;
        }
    }

    public static final <E> boolean addInternal(@NotNull ArraySet<E> arraySet, E e) {
        int i;
        int iIndexOf;
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        int i2 = arraySet._size;
        if (e == null) {
            iIndexOf = indexOfNull(arraySet);
            i = 0;
        } else {
            int iHashCode = e.hashCode();
            i = iHashCode;
            iIndexOf = indexOf(arraySet, e, iHashCode);
        }
        if (iIndexOf >= 0) {
            return false;
        }
        int i3 = ~iIndexOf;
        int[] iArr = arraySet.hashes;
        if (i2 >= iArr.length) {
            int i4 = 8;
            if (i2 >= 8) {
                i4 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i4 = 4;
            }
            Object[] objArr = arraySet.array;
            allocArrays(arraySet, i4);
            if (i2 != arraySet._size) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = arraySet.hashes;
            if (!(iArr2.length == 0)) {
                ArraysKt___ArraysJvmKt.copyInto$default(iArr, iArr2, 0, 0, iArr.length, 6, (Object) null);
                ArraysKt___ArraysJvmKt.copyInto$default(objArr, arraySet.array, 0, 0, objArr.length, 6, (Object) null);
            }
        }
        if (i3 < i2) {
            int[] iArr3 = arraySet.hashes;
            int i5 = i3 + 1;
            ArraysKt___ArraysJvmKt.copyInto(iArr3, iArr3, i5, i3, i2);
            Object[] objArr2 = arraySet.array;
            ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i5, i3, i2);
        }
        int i6 = arraySet._size;
        if (i2 == i6) {
            int[] iArr4 = arraySet.hashes;
            if (i3 < iArr4.length) {
                iArr4[i3] = i;
                arraySet.array[i3] = e;
                arraySet._size = i6 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    public static final <E> void allocArrays(@NotNull ArraySet<E> arraySet, int i) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        arraySet.hashes = new int[i];
        arraySet.array = new Object[i];
    }

    @NotNull
    public static final <T> ArraySet<T> arraySetOf() {
        return new ArraySet<>(0, 1, null);
    }

    public static final <E> int binarySearchInternal(@NotNull ArraySet<E> arraySet, int i) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        try {
            return ContainerHelpersKt.binarySearch(arraySet.hashes, arraySet._size, i);
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static final <E> void clearInternal(@NotNull ArraySet<E> arraySet) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        if (arraySet._size != 0) {
            arraySet.setHashes$collection(ContainerHelpersKt.EMPTY_INTS);
            arraySet.setArray$collection(ContainerHelpersKt.EMPTY_OBJECTS);
            arraySet._size = 0;
        }
        if (arraySet._size != 0) {
            throw new ConcurrentModificationException();
        }
    }

    public static final <E> boolean containsAllInternal(@NotNull ArraySet<E> arraySet, @NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        while (it.hasNext()) {
            if (!arraySet.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static final <E> boolean containsInternal(@NotNull ArraySet<E> arraySet, E e) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        return arraySet.indexOf(e) >= 0;
    }

    public static final <E> void ensureCapacityInternal(@NotNull ArraySet<E> arraySet, int i) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        int i2 = arraySet._size;
        int[] iArr = arraySet.hashes;
        if (iArr.length < i) {
            Object[] objArr = arraySet.array;
            allocArrays(arraySet, i);
            int i3 = arraySet._size;
            if (i3 > 0) {
                ArraysKt___ArraysJvmKt.copyInto$default(iArr, arraySet.hashes, 0, 0, i3, 6, (Object) null);
                ArraysKt___ArraysJvmKt.copyInto$default(objArr, arraySet.array, 0, 0, arraySet._size, 6, (Object) null);
            }
        }
        if (arraySet._size != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public static final <E> boolean equalsInternal(@NotNull ArraySet<E> arraySet, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        if (arraySet == obj) {
            return true;
        }
        if (!(obj instanceof Set) || arraySet._size != ((Set) obj).size()) {
            return false;
        }
        try {
            int i = arraySet._size;
            for (int i2 = 0; i2 < i; i2++) {
                if (!((Set) obj).contains(arraySet.array[i2])) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static final <E> int hashCodeInternal(@NotNull ArraySet<E> arraySet) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        int[] iArr = arraySet.hashes;
        int i = arraySet._size;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public static final <E> int indexOf(@NotNull ArraySet<E> arraySet, @Nullable Object obj, int i) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        int i2 = arraySet._size;
        if (i2 == 0) {
            return -1;
        }
        int iBinarySearchInternal = binarySearchInternal(arraySet, i);
        if (iBinarySearchInternal < 0 || Intrinsics.areEqual(obj, arraySet.array[iBinarySearchInternal])) {
            return iBinarySearchInternal;
        }
        int i3 = iBinarySearchInternal + 1;
        while (i3 < i2 && arraySet.hashes[i3] == i) {
            if (Intrinsics.areEqual(obj, arraySet.array[i3])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iBinarySearchInternal - 1; i4 >= 0 && arraySet.hashes[i4] == i; i4--) {
            if (Intrinsics.areEqual(obj, arraySet.array[i4])) {
                return i4;
            }
        }
        return ~i3;
    }

    public static final <E> int indexOfInternal(@NotNull ArraySet<E> arraySet, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        return obj == null ? indexOfNull(arraySet) : indexOf(arraySet, obj, obj.hashCode());
    }

    public static final <E> int indexOfNull(@NotNull ArraySet<E> arraySet) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        return indexOf(arraySet, null, 0);
    }

    public static final <E> boolean isEmptyInternal(@NotNull ArraySet<E> arraySet) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        return arraySet._size <= 0;
    }

    public static final <E> boolean removeAllInternal(@NotNull ArraySet<E> arraySet, @NotNull ArraySet<? extends E> array) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        Intrinsics.checkNotNullParameter(array, "array");
        int i = array._size;
        int i2 = arraySet._size;
        for (int i3 = 0; i3 < i; i3++) {
            arraySet.remove(array.array[i3]);
        }
        return i2 != arraySet._size;
    }

    public static final <E> E removeAtInternal(@NotNull ArraySet<E> arraySet, int i) {
        int[] iArr;
        Object[] objArr;
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        int i2 = arraySet._size;
        Object[] objArr2 = arraySet.array;
        E e = (E) objArr2[i];
        if (i2 <= 1) {
            arraySet.clear();
            return e;
        }
        int i3 = i2 - 1;
        int[] iArr2 = arraySet.hashes;
        if (iArr2.length <= 8 || i2 >= iArr2.length / 3) {
            if (i < i3) {
                int i4 = i + 1;
                ArraysKt___ArraysJvmKt.copyInto(iArr2, iArr2, i, i4, i2);
                Object[] objArr3 = arraySet.array;
                ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, i, i4, i2);
            }
            arraySet.array[i3] = null;
        } else {
            allocArrays(arraySet, i2 > 8 ? i2 + (i2 >> 1) : 8);
            if (i > 0) {
                ArraysKt___ArraysJvmKt.copyInto$default(iArr2, arraySet.hashes, 0, 0, i, 6, (Object) null);
                iArr = iArr2;
                objArr = objArr2;
                ArraysKt___ArraysJvmKt.copyInto$default(objArr, arraySet.array, 0, 0, i, 6, (Object) null);
            } else {
                iArr = iArr2;
                objArr = objArr2;
            }
            if (i < i3) {
                int i5 = i + 1;
                ArraysKt___ArraysJvmKt.copyInto(iArr, arraySet.hashes, i, i5, i2);
                ArraysKt___ArraysJvmKt.copyInto(objArr, arraySet.array, i, i5, i2);
            }
        }
        if (i2 != arraySet._size) {
            throw new ConcurrentModificationException();
        }
        arraySet._size = i3;
        return e;
    }

    public static final <E> boolean removeInternal(@NotNull ArraySet<E> arraySet, E e) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        int iIndexOf = arraySet.indexOf(e);
        if (iIndexOf < 0) {
            return false;
        }
        arraySet.removeAt(iIndexOf);
        return true;
    }

    public static final <E> boolean retainAllInternal(@NotNull ArraySet<E> arraySet, @NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean z = false;
        for (int i = arraySet._size - 1; -1 < i; i--) {
            if (!CollectionsKt___CollectionsKt.contains(elements, arraySet.array[i])) {
                arraySet.removeAt(i);
                z = true;
            }
        }
        return z;
    }

    @NotNull
    public static final <E> String toStringInternal(@NotNull ArraySet<E> arraySet) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        if (arraySet.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(arraySet._size * 14);
        sb.append('{');
        int i = arraySet._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object obj = arraySet.array[i2];
            if (obj != arraySet) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        return ArraySet$$ExternalSyntheticOutline0.m(sb, '}', "StringBuilder(capacity).…builderAction).toString()");
    }

    public static final <E> E valueAtInternal(@NotNull ArraySet<E> arraySet, int i) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        return (E) arraySet.array[i];
    }

    @NotNull
    public static final <T> ArraySet<T> arraySetOf(@NotNull T... values) {
        Intrinsics.checkNotNullParameter(values, "values");
        ArraySet<T> arraySet = new ArraySet<>(values.length);
        for (T t : values) {
            arraySet.add(t);
        }
        return arraySet;
    }

    public static final <E> boolean removeAllInternal(@NotNull ArraySet<E> arraySet, @NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<? extends E> it = elements.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= arraySet.remove(it.next());
        }
        return zRemove;
    }

    public static final <E> boolean addAllInternal(@NotNull ArraySet<E> arraySet, @NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(arraySet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        arraySet.ensureCapacity(elements.size() + arraySet._size);
        Iterator<? extends E> it = elements.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= arraySet.add(it.next());
        }
        return zAdd;
    }
}
