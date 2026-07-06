package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nArraySet.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArraySet.jvm.kt\nandroidx/collection/ArraySet\n+ 2 ArraySet.kt\nandroidx/collection/ArraySetKt\n*L\n1#1,300:1\n304#2,10:301\n317#2,14:311\n334#2:325\n339#2:326\n345#2:327\n350#2:328\n355#2,61:329\n420#2,17:390\n440#2,6:407\n450#2,60:413\n518#2,9:473\n531#2,22:482\n557#2,7:504\n568#2,19:511\n591#2,6:530\n601#2,6:536\n611#2,5:542\n620#2,8:547\n*S KotlinDebug\n*F\n+ 1 ArraySet.jvm.kt\nandroidx/collection/ArraySet\n*L\n98#1:301,10\n108#1:311,14\n118#1:325\n128#1:326\n138#1:327\n145#1:328\n157#1:329,61\n167#1:390,17\n177#1:407,6\n188#1:413,60\n197#1:473,9\n224#1:482,22\n231#1:504,7\n240#1:511,19\n267#1:530,6\n276#1:536,6\n286#1:542,5\n297#1:547,8\n*E\n"})
public final class ArraySet<E> implements Collection<E>, Set<E>, KMutableCollection, KMutableSet {
    public int _size;

    @NotNull
    public Object[] array;

    @NotNull
    public int[] hashes;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ElementIterator extends IndexBasedArrayIterator<E> {
        public ElementIterator() {
            super(ArraySet.this._size);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        public E elementAt(int i) {
            return (E) ArraySet.this.array[i];
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        public void removeAt(int i) {
            ArraySet.this.removeAt(i);
        }
    }

    @JvmOverloads
    public ArraySet() {
        this(0, 1, null);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e) {
        int i;
        int iIndexOf;
        int i2 = this._size;
        if (e == null) {
            iIndexOf = ArraySetKt.indexOfNull(this);
            i = 0;
        } else {
            int iHashCode = e.hashCode();
            i = iHashCode;
            iIndexOf = ArraySetKt.indexOf(this, e, iHashCode);
        }
        if (iIndexOf >= 0) {
            return false;
        }
        int i3 = ~iIndexOf;
        int[] iArr = this.hashes;
        if (i2 >= iArr.length) {
            int i4 = 8;
            if (i2 >= 8) {
                i4 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i4 = 4;
            }
            Object[] objArr = this.array;
            ArraySetKt.allocArrays(this, i4);
            if (i2 != this._size) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.hashes;
            if (!(iArr2.length == 0)) {
                ArraysKt___ArraysJvmKt.copyInto$default(iArr, iArr2, 0, 0, iArr.length, 6, (Object) null);
                ArraysKt___ArraysJvmKt.copyInto$default(objArr, this.array, 0, 0, objArr.length, 6, (Object) null);
            }
        }
        if (i3 < i2) {
            int[] iArr3 = this.hashes;
            int i5 = i3 + 1;
            ArraysKt___ArraysJvmKt.copyInto(iArr3, iArr3, i5, i3, i2);
            Object[] objArr2 = this.array;
            ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i5, i3, i2);
        }
        int i6 = this._size;
        if (i2 == i6) {
            int[] iArr4 = this.hashes;
            if (i3 < iArr4.length) {
                iArr4[i3] = i;
                this.array[i3] = e;
                this._size = i6 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addAll(@NotNull ArraySet<? extends E> array) {
        Intrinsics.checkNotNullParameter(array, "array");
        int i = array._size;
        ensureCapacity(this._size + i);
        if (this._size != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                add(array.array[i2]);
            }
            return;
        }
        if (i > 0) {
            ArraysKt___ArraysJvmKt.copyInto$default(array.hashes, this.hashes, 0, 0, i, 6, (Object) null);
            ArraysKt___ArraysJvmKt.copyInto$default(array.array, this.array, 0, 0, i, 6, (Object) null);
            if (this._size != 0) {
                throw new ConcurrentModificationException();
            }
            this._size = i;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        if (this._size != 0) {
            setHashes$collection(ContainerHelpersKt.EMPTY_INTS);
            setArray$collection(ContainerHelpersKt.EMPTY_OBJECTS);
            this._size = 0;
        }
        if (this._size != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<? extends Object> it = elements.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final void ensureCapacity(int i) {
        int i2 = this._size;
        int[] iArr = this.hashes;
        if (iArr.length < i) {
            Object[] objArr = this.array;
            ArraySetKt.allocArrays(this, i);
            int i3 = this._size;
            if (i3 > 0) {
                ArraysKt___ArraysJvmKt.copyInto$default(iArr, this.hashes, 0, 0, i3, 6, (Object) null);
                ArraysKt___ArraysJvmKt.copyInto$default(objArr, this.array, 0, 0, this._size, 6, (Object) null);
            }
        }
        if (this._size != i2) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Set) || this._size != ((Set) obj).size()) {
            return false;
        }
        try {
            int i = this._size;
            for (int i2 = 0; i2 < i; i2++) {
                if (!((Set) obj).contains(this.array[i2])) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @NotNull
    public final Object[] getArray$collection() {
        return this.array;
    }

    @NotNull
    public final int[] getHashes$collection() {
        return this.hashes;
    }

    public int getSize() {
        return this._size;
    }

    public final int get_size$collection() {
        return this._size;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.hashes;
        int i = this._size;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public final int indexOf(@Nullable Object obj) {
        return obj == null ? ArraySetKt.indexOfNull(this) : ArraySetKt.indexOf(this, obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this._size <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    @NotNull
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public final boolean removeAll(@NotNull ArraySet<? extends E> array) {
        Intrinsics.checkNotNullParameter(array, "array");
        int i = array._size;
        int i2 = this._size;
        for (int i3 = 0; i3 < i; i3++) {
            remove(array.array[i3]);
        }
        return i2 != this._size;
    }

    public final E removeAt(int i) {
        int[] iArr;
        Object[] objArr;
        int i2 = this._size;
        Object[] objArr2 = this.array;
        E e = (E) objArr2[i];
        if (i2 <= 1) {
            clear();
            return e;
        }
        int i3 = i2 - 1;
        int[] iArr2 = this.hashes;
        if (iArr2.length <= 8 || i2 >= iArr2.length / 3) {
            if (i < i3) {
                int i4 = i + 1;
                ArraysKt___ArraysJvmKt.copyInto(iArr2, iArr2, i, i4, i2);
                Object[] objArr3 = this.array;
                ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, i, i4, i2);
            }
            this.array[i3] = null;
        } else {
            ArraySetKt.allocArrays(this, i2 > 8 ? i2 + (i2 >> 1) : 8);
            if (i > 0) {
                ArraysKt___ArraysJvmKt.copyInto$default(iArr2, this.hashes, 0, 0, i, 6, (Object) null);
                iArr = iArr2;
                objArr = objArr2;
                ArraysKt___ArraysJvmKt.copyInto$default(objArr, this.array, 0, 0, i, 6, (Object) null);
            } else {
                iArr = iArr2;
                objArr = objArr2;
            }
            if (i < i3) {
                int i5 = i + 1;
                ArraysKt___ArraysJvmKt.copyInto(iArr, this.hashes, i, i5, i2);
                ArraysKt___ArraysJvmKt.copyInto(objArr, this.array, i, i5, i2);
            }
        }
        if (i2 != this._size) {
            throw new ConcurrentModificationException();
        }
        this._size = i3;
        return e;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean z = false;
        for (int i = this._size - 1; -1 < i; i--) {
            if (!CollectionsKt___CollectionsKt.contains(elements, this.array[i])) {
                removeAt(i);
                z = true;
            }
        }
        return z;
    }

    public final void setArray$collection(@NotNull Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<set-?>");
        this.array = objArr;
    }

    public final void setHashes$collection(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.hashes = iArr;
    }

    public final void set_size$collection(int i) {
        this._size = i;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this._size;
    }

    @Override // java.util.Collection, java.util.Set
    @NotNull
    public final Object[] toArray() {
        return ArraysKt___ArraysJvmKt.copyOfRange(this.array, 0, this._size);
    }

    @NotNull
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this._size * 14);
        sb.append('{');
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object obj = this.array[i2];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        return ArraySet$$ExternalSyntheticOutline0.m(sb, '}', "StringBuilder(capacity).…builderAction).toString()");
    }

    public final E valueAt(int i) {
        return (E) this.array[i];
    }

    @JvmOverloads
    public ArraySet(int i) {
        this.hashes = ContainerHelpersKt.EMPTY_INTS;
        this.array = ContainerHelpersKt.EMPTY_OBJECTS;
        if (i > 0) {
            ArraySetKt.allocArrays(this, i);
        }
    }

    @Override // java.util.Collection, java.util.Set
    @NotNull
    public final <T> T[] toArray(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        T[] tArr = (T[]) ArraySetJvmUtil.resizeForToArray(array, this._size);
        ArraysKt___ArraysJvmKt.copyInto(this.array, tArr, 0, 0, this._size);
        return tArr;
    }

    public /* synthetic */ ArraySet(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    public ArraySet(@Nullable ArraySet<? extends E> arraySet) {
        this(0);
        if (arraySet != null) {
            addAll((ArraySet) arraySet);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<? extends Object> it = elements.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    public ArraySet(@Nullable Collection<? extends E> collection) {
        this(0);
        if (collection != null) {
            addAll(collection);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(@Nullable E[] eArr) {
        this(0);
        if (eArr == null) {
            return;
        }
        Iterator it = ArrayIteratorKt.iterator(eArr);
        while (true) {
            ArrayIterator arrayIterator = (ArrayIterator) it;
            if (!arrayIterator.hasNext()) {
                return;
            } else {
                add(arrayIterator.next());
            }
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        ensureCapacity(elements.size() + this._size);
        Iterator<? extends E> it = elements.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }
}
