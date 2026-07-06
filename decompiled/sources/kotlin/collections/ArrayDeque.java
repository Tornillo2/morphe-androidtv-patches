package kotlin.collections;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.4")
@SourceDebugExtension({"SMAP\nArrayDeque.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayDeque.kt\nkotlin/collections/ArrayDeque\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,660:1\n476#1,53:665\n476#1,53:718\n37#2:661\n36#2,3:662\n*S KotlinDebug\n*F\n+ 1 ArrayDeque.kt\nkotlin/collections/ArrayDeque\n*L\n471#1:665,53\n473#1:718,53\n46#1:661\n46#1:662,3\n*E\n"})
public final class ArrayDeque<E> extends AbstractMutableList<E> {
    public static final int defaultMinCapacity = 10;

    @NotNull
    public Object[] elementData;
    public int head;
    public int size;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Object[] emptyElementData = new Object[0];

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public ArrayDeque() {
        this.elementData = emptyElementData;
    }

    private final void ensureCapacity(int i) {
        if (i < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.elementData;
        if (i <= objArr.length) {
            return;
        }
        if (objArr != emptyElementData) {
            copyElements(AbstractList.Companion.newCapacity$kotlin_stdlib(objArr.length, i));
            return;
        }
        if (i < 10) {
            i = 10;
        }
        this.elementData = new Object[i];
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        registerModification();
        ensureCapacity(elements.size() + getSize());
        copyCollectionElements(positiveMod(getSize() + this.head), elements);
        return true;
    }

    public final void addFirst(E e) {
        registerModification();
        ensureCapacity(getSize() + 1);
        int iDecremented = decremented(this.head);
        this.head = iDecremented;
        this.elementData[iDecremented] = e;
        this.size = getSize() + 1;
    }

    public final void addLast(E e) {
        registerModification();
        ensureCapacity(getSize() + 1);
        this.elementData[positiveMod(getSize() + this.head)] = e;
        this.size = getSize() + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        if (!isEmpty()) {
            registerModification();
            nullifyNonEmpty(this.head, positiveMod(getSize() + this.head));
        }
        this.head = 0;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void copyCollectionElements(int i, Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        int length = this.elementData.length;
        while (i < length && it.hasNext()) {
            this.elementData[i] = it.next();
            i++;
        }
        int i2 = this.head;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.elementData[i3] = it.next();
        }
        this.size = collection.size() + getSize();
    }

    public final void copyElements(int i) {
        Object[] objArr = new Object[i];
        Object[] objArr2 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr, 0, this.head, objArr2.length);
        Object[] objArr3 = this.elementData;
        int length = objArr3.length;
        int i2 = this.head;
        ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr, length - i2, 0, i2);
        this.head = 0;
        this.elementData = objArr;
    }

    public final int decremented(int i) {
        return i == 0 ? ArraysKt___ArraysKt.getLastIndex(this.elementData) : i - 1;
    }

    public final boolean filterInPlace(Function1<? super E, Boolean> function1) {
        int iPositiveMod;
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int iPositiveMod2 = positiveMod(getSize() + this.head);
            int i = this.head;
            if (i < iPositiveMod2) {
                iPositiveMod = i;
                while (i < iPositiveMod2) {
                    Object obj = this.elementData[i];
                    if (function1.invoke(obj).booleanValue()) {
                        this.elementData[iPositiveMod] = obj;
                        iPositiveMod++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, iPositiveMod, iPositiveMod2);
            } else {
                int length = this.elementData.length;
                int i2 = i;
                boolean z2 = false;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (function1.invoke(obj2).booleanValue()) {
                        this.elementData[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                iPositiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < iPositiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (function1.invoke(obj3).booleanValue()) {
                        this.elementData[iPositiveMod] = obj3;
                        iPositiveMod = incremented(iPositiveMod);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                registerModification();
                this.size = negativeMod(iPositiveMod - this.head);
            }
        }
        return z;
    }

    public final E first() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return (E) this.elementData[this.head];
    }

    @Nullable
    public final E firstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.elementData[this.head];
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, getSize());
        return (E) this.elementData[positiveMod(this.head + i)];
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.size;
    }

    public final int incremented(int i) {
        if (i == ArraysKt___ArraysKt.getLastIndex(this.elementData)) {
            return 0;
        }
        return i + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        int i;
        int iPositiveMod = positiveMod(getSize() + this.head);
        int length = this.head;
        if (length < iPositiveMod) {
            while (length < iPositiveMod) {
                if (Intrinsics.areEqual(obj, this.elementData[length])) {
                    i = this.head;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iPositiveMod) {
            return -1;
        }
        int length2 = this.elementData.length;
        while (true) {
            if (length >= length2) {
                for (int i2 = 0; i2 < iPositiveMod; i2++) {
                    if (Intrinsics.areEqual(obj, this.elementData[i2])) {
                        length = i2 + this.elementData.length;
                        i = this.head;
                    }
                }
                return -1;
            }
            if (Intrinsics.areEqual(obj, this.elementData[length])) {
                i = this.head;
                break;
            }
            length++;
        }
        return length - i;
    }

    @InlineOnly
    public final E internalGet(int i) {
        return (E) this.elementData[i];
    }

    @InlineOnly
    public final int internalIndex(int i) {
        return positiveMod(this.head + i);
    }

    public final void internalStructure$kotlin_stdlib(@NotNull Function2<? super Integer, ? super Object[], Unit> structure) {
        int i;
        Intrinsics.checkNotNullParameter(structure, "structure");
        structure.invoke(Integer.valueOf((isEmpty() || (i = this.head) < positiveMod(getSize() + this.head)) ? this.head : i - this.elementData.length), toArray());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return getSize() == 0;
    }

    public final E last() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return (E) this.elementData[positiveMod(CollectionsKt__CollectionsKt.getLastIndex(this) + this.head)];
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        int lastIndex;
        int i;
        int iPositiveMod = positiveMod(getSize() + this.head);
        int i2 = this.head;
        if (i2 < iPositiveMod) {
            lastIndex = iPositiveMod - 1;
            if (i2 <= lastIndex) {
                while (!Intrinsics.areEqual(obj, this.elementData[lastIndex])) {
                    if (lastIndex != i2) {
                        lastIndex--;
                    }
                }
                i = this.head;
                return lastIndex - i;
            }
            return -1;
        }
        if (i2 > iPositiveMod) {
            int i3 = iPositiveMod - 1;
            while (true) {
                if (-1 >= i3) {
                    lastIndex = ArraysKt___ArraysKt.getLastIndex(this.elementData);
                    int i4 = this.head;
                    if (i4 <= lastIndex) {
                        while (!Intrinsics.areEqual(obj, this.elementData[lastIndex])) {
                            if (lastIndex != i4) {
                                lastIndex--;
                            }
                        }
                        i = this.head;
                    }
                } else {
                    if (Intrinsics.areEqual(obj, this.elementData[i3])) {
                        lastIndex = i3 + this.elementData.length;
                        i = this.head;
                        break;
                    }
                    i3--;
                }
            }
        }
        return -1;
    }

    @Nullable
    public final E lastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.elementData[positiveMod(CollectionsKt__CollectionsKt.getLastIndex(this) + this.head)];
    }

    public final int negativeMod(int i) {
        return i < 0 ? i + this.elementData.length : i;
    }

    public final void nullifyNonEmpty(int i, int i2) {
        if (i < i2) {
            ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, i, i2);
            return;
        }
        Object[] objArr = this.elementData;
        ArraysKt___ArraysJvmKt.fill(objArr, (Object) null, i, objArr.length);
        ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, 0, i2);
    }

    public final int positiveMod(int i) {
        Object[] objArr = this.elementData;
        return i >= objArr.length ? i - objArr.length : i;
    }

    public final void registerModification() {
        ((java.util.AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(@NotNull Collection<?> elements) {
        int iPositiveMod;
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int iPositiveMod2 = positiveMod(getSize() + this.head);
            int i = this.head;
            if (i < iPositiveMod2) {
                iPositiveMod = i;
                while (i < iPositiveMod2) {
                    Object obj = this.elementData[i];
                    if (elements.contains(obj)) {
                        z = true;
                    } else {
                        this.elementData[iPositiveMod] = obj;
                        iPositiveMod++;
                    }
                    i++;
                }
                ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, iPositiveMod, iPositiveMod2);
            } else {
                int length = this.elementData.length;
                int i2 = i;
                boolean z2 = false;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (elements.contains(obj2)) {
                        z2 = true;
                    } else {
                        this.elementData[i2] = obj2;
                        i2++;
                    }
                    i++;
                }
                iPositiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < iPositiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (elements.contains(obj3)) {
                        z2 = true;
                    } else {
                        this.elementData[iPositiveMod] = obj3;
                        iPositiveMod = incremented(iPositiveMod);
                    }
                }
                z = z2;
            }
            if (z) {
                registerModification();
                this.size = negativeMod(iPositiveMod - this.head);
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList
    public E removeAt(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, getSize());
        if (i == CollectionsKt__CollectionsKt.getLastIndex(this)) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        registerModification();
        int iPositiveMod = positiveMod(this.head + i);
        E e = (E) this.elementData[iPositiveMod];
        if (i < (getSize() >> 1)) {
            int i2 = this.head;
            if (iPositiveMod >= i2) {
                Object[] objArr = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i2 + 1, i2, iPositiveMod);
            } else {
                Object[] objArr2 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, 1, 0, iPositiveMod);
                Object[] objArr3 = this.elementData;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i3 = this.head;
                ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, i3 + 1, i3, objArr3.length - 1);
            }
            Object[] objArr4 = this.elementData;
            int i4 = this.head;
            objArr4[i4] = null;
            this.head = incremented(i4);
        } else {
            int iPositiveMod2 = positiveMod(CollectionsKt__CollectionsKt.getLastIndex(this) + this.head);
            if (iPositiveMod <= iPositiveMod2) {
                Object[] objArr5 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, iPositiveMod, iPositiveMod + 1, iPositiveMod2 + 1);
            } else {
                Object[] objArr6 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, iPositiveMod, iPositiveMod + 1, objArr6.length);
                Object[] objArr7 = this.elementData;
                objArr7[objArr7.length - 1] = objArr7[0];
                ArraysKt___ArraysJvmKt.copyInto(objArr7, objArr7, 0, 1, iPositiveMod2 + 1);
            }
            this.elementData[iPositiveMod2] = null;
        }
        this.size = getSize() - 1;
        return e;
    }

    public final E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        registerModification();
        Object[] objArr = this.elementData;
        int i = this.head;
        E e = (E) objArr[i];
        objArr[i] = null;
        this.head = incremented(i);
        this.size = getSize() - 1;
        return e;
    }

    @Nullable
    public final E removeFirstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }

    public final E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        registerModification();
        int iPositiveMod = positiveMod(CollectionsKt__CollectionsKt.getLastIndex(this) + this.head);
        Object[] objArr = this.elementData;
        E e = (E) objArr[iPositiveMod];
        objArr[iPositiveMod] = null;
        this.size = getSize() - 1;
        return e;
    }

    @Nullable
    public final E removeLastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeLast();
    }

    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, getSize());
        int i3 = i2 - i;
        if (i3 == 0) {
            return;
        }
        if (i3 == getSize()) {
            clear();
            return;
        }
        if (i3 == 1) {
            removeAt(i);
            return;
        }
        registerModification();
        if (i < getSize() - i2) {
            removeRangeShiftPreceding(i, i2);
            int iPositiveMod = positiveMod(this.head + i3);
            nullifyNonEmpty(this.head, iPositiveMod);
            this.head = iPositiveMod;
        } else {
            removeRangeShiftSucceeding(i, i2);
            int iPositiveMod2 = positiveMod(getSize() + this.head);
            nullifyNonEmpty(negativeMod(iPositiveMod2 - i3), iPositiveMod2);
        }
        this.size = getSize() - i3;
    }

    public final void removeRangeShiftPreceding(int i, int i2) {
        int iPositiveMod = positiveMod((i - 1) + this.head);
        int iPositiveMod2 = positiveMod((i2 - 1) + this.head);
        while (i > 0) {
            int i3 = iPositiveMod + 1;
            int iMin = Math.min(i, Math.min(i3, iPositiveMod2 + 1));
            Object[] objArr = this.elementData;
            int i4 = iPositiveMod2 - iMin;
            int i5 = iPositiveMod - iMin;
            ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i4 + 1, i5 + 1, i3);
            iPositiveMod = negativeMod(i5);
            iPositiveMod2 = negativeMod(i4);
            i -= iMin;
        }
    }

    public final void removeRangeShiftSucceeding(int i, int i2) {
        int iPositiveMod = positiveMod(this.head + i2);
        int iPositiveMod2 = positiveMod(this.head + i);
        int size = getSize();
        while (true) {
            size -= i2;
            if (size <= 0) {
                return;
            }
            Object[] objArr = this.elementData;
            i2 = Math.min(size, Math.min(objArr.length - iPositiveMod, objArr.length - iPositiveMod2));
            Object[] objArr2 = this.elementData;
            int i3 = iPositiveMod + i2;
            ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, iPositiveMod2, iPositiveMod, i3);
            iPositiveMod = positiveMod(i3);
            iPositiveMod2 = positiveMod(iPositiveMod2 + i2);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(@NotNull Collection<?> elements) {
        int iPositiveMod;
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int iPositiveMod2 = positiveMod(getSize() + this.head);
            int i = this.head;
            if (i < iPositiveMod2) {
                iPositiveMod = i;
                while (i < iPositiveMod2) {
                    Object obj = this.elementData[i];
                    if (elements.contains(obj)) {
                        this.elementData[iPositiveMod] = obj;
                        iPositiveMod++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, iPositiveMod, iPositiveMod2);
            } else {
                int length = this.elementData.length;
                int i2 = i;
                boolean z2 = false;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (elements.contains(obj2)) {
                        this.elementData[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                iPositiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < iPositiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (elements.contains(obj3)) {
                        this.elementData[iPositiveMod] = obj3;
                        iPositiveMod = incremented(iPositiveMod);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                registerModification();
                this.size = negativeMod(iPositiveMod - this.head);
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, getSize());
        int iPositiveMod = positiveMod(this.head + i);
        Object[] objArr = this.elementData;
        E e2 = (E) objArr[iPositiveMod];
        objArr[iPositiveMod] = e;
        return e2;
    }

    public final void testRemoveRange$kotlin_stdlib(int i, int i2) {
        removeRange(i, i2);
    }

    @NotNull
    public final <T> T[] testToArray$kotlin_stdlib(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) toArray(array);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public Object[] toArray() {
        return toArray(new Object[getSize()]);
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, getSize());
        if (i == getSize()) {
            addLast(e);
            return;
        }
        if (i == 0) {
            addFirst(e);
            return;
        }
        registerModification();
        ensureCapacity(getSize() + 1);
        int iPositiveMod = positiveMod(this.head + i);
        if (i < ((getSize() + 1) >> 1)) {
            int iDecremented = decremented(iPositiveMod);
            int iDecremented2 = decremented(this.head);
            int i2 = this.head;
            if (iDecremented >= i2) {
                Object[] objArr = this.elementData;
                objArr[iDecremented2] = objArr[i2];
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i2, i2 + 1, iDecremented + 1);
            } else {
                Object[] objArr2 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i2 - 1, i2, objArr2.length);
                Object[] objArr3 = this.elementData;
                objArr3[objArr3.length - 1] = objArr3[0];
                ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, 0, 1, iDecremented + 1);
            }
            this.elementData[iDecremented] = e;
            this.head = iDecremented2;
        } else {
            int iPositiveMod2 = positiveMod(getSize() + this.head);
            if (iPositiveMod < iPositiveMod2) {
                Object[] objArr4 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr4, objArr4, iPositiveMod + 1, iPositiveMod, iPositiveMod2);
            } else {
                Object[] objArr5 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, 1, 0, iPositiveMod2);
                Object[] objArr6 = this.elementData;
                objArr6[0] = objArr6[objArr6.length - 1];
                ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, iPositiveMod + 1, iPositiveMod, objArr6.length - 1);
            }
            this.elementData[iPositiveMod] = e;
        }
        this.size = getSize() + 1;
    }

    @NotNull
    public final Object[] testToArray$kotlin_stdlib() {
        return toArray();
    }

    public ArrayDeque(int i) {
        Object[] objArr;
        if (i == 0) {
            objArr = emptyElementData;
        } else if (i > 0) {
            objArr = new Object[i];
        } else {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Illegal Capacity: ", i));
        }
        this.elementData = objArr;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public <T> T[] toArray(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (array.length < getSize()) {
            array = (T[]) ArraysKt__ArraysJVMKt.arrayOfNulls(array, getSize());
        }
        T[] tArr = array;
        int iPositiveMod = positiveMod(getSize() + this.head);
        int i = this.head;
        if (i < iPositiveMod) {
            ArraysKt___ArraysJvmKt.copyInto$default(this.elementData, tArr, 0, i, iPositiveMod, 2, (Object) null);
        } else if (!isEmpty()) {
            Object[] objArr = this.elementData;
            ArraysKt___ArraysJvmKt.copyInto(objArr, tArr, 0, this.head, objArr.length);
            Object[] objArr2 = this.elementData;
            ArraysKt___ArraysJvmKt.copyInto(objArr2, tArr, objArr2.length - this.head, 0, iPositiveMod);
        }
        CollectionsKt__CollectionsJVMKt.terminateCollectionToArray(getSize(), tArr);
        return tArr;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i, @NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, getSize());
        if (elements.isEmpty()) {
            return false;
        }
        if (i == getSize()) {
            return addAll(elements);
        }
        registerModification();
        ensureCapacity(elements.size() + getSize());
        int iPositiveMod = positiveMod(getSize() + this.head);
        int iPositiveMod2 = positiveMod(this.head + i);
        int size = elements.size();
        if (i < ((getSize() + 1) >> 1)) {
            int i2 = this.head;
            int length = i2 - size;
            if (iPositiveMod2 < i2) {
                Object[] objArr = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, length, i2, objArr.length);
                if (size >= iPositiveMod2) {
                    Object[] objArr2 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, objArr2.length - size, 0, iPositiveMod2);
                } else {
                    Object[] objArr3 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, objArr3.length - size, 0, size);
                    Object[] objArr4 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr4, objArr4, 0, size, iPositiveMod2);
                }
            } else if (length >= 0) {
                Object[] objArr5 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, length, i2, iPositiveMod2);
            } else {
                Object[] objArr6 = this.elementData;
                length += objArr6.length;
                int i3 = iPositiveMod2 - i2;
                int length2 = objArr6.length - length;
                if (length2 >= i3) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, length, i2, iPositiveMod2);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, length, i2, i2 + length2);
                    Object[] objArr7 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr7, objArr7, 0, this.head + length2, iPositiveMod2);
                }
            }
            this.head = length;
            copyCollectionElements(negativeMod(iPositiveMod2 - size), elements);
        } else {
            int i4 = iPositiveMod2 + size;
            if (iPositiveMod2 < iPositiveMod) {
                int i5 = size + iPositiveMod;
                Object[] objArr8 = this.elementData;
                if (i5 <= objArr8.length) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr8, objArr8, i4, iPositiveMod2, iPositiveMod);
                } else if (i4 >= objArr8.length) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr8, objArr8, i4 - objArr8.length, iPositiveMod2, iPositiveMod);
                } else {
                    int length3 = iPositiveMod - (i5 - objArr8.length);
                    ArraysKt___ArraysJvmKt.copyInto(objArr8, objArr8, 0, length3, iPositiveMod);
                    Object[] objArr9 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr9, objArr9, i4, iPositiveMod2, length3);
                }
            } else {
                Object[] objArr10 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr10, objArr10, size, 0, iPositiveMod);
                Object[] objArr11 = this.elementData;
                if (i4 >= objArr11.length) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr11, objArr11, i4 - objArr11.length, iPositiveMod2, objArr11.length);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                    Object[] objArr12 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr12, objArr12, i4, iPositiveMod2, objArr12.length - size);
                }
            }
            copyCollectionElements(iPositiveMod2, elements);
        }
        return true;
    }

    public ArrayDeque(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Object[] array = elements.toArray(new Object[0]);
        this.elementData = array;
        this.size = array.length;
        if (array.length == 0) {
            this.elementData = emptyElementData;
        }
    }
}
