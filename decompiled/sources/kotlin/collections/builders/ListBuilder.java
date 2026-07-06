package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,718:1\n1#2:719\n*E\n"})
public final class ListBuilder<E> extends AbstractMutableList<E> implements List<E>, RandomAccess, Serializable, KMutableList {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final ListBuilder Empty;

    @NotNull
    public E[] backing;
    public boolean isReadOnly;
    public int length;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BuilderSubList<E> extends AbstractMutableList<E> implements List<E>, RandomAccess, Serializable, KMutableList {

        @NotNull
        public E[] backing;
        public int length;
        public final int offset;

        @Nullable
        public final BuilderSubList<E> parent;

        @NotNull
        public final ListBuilder<E> root;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder$BuilderSubList$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,718:1\n1#2:719\n*E\n"})
        public static final class Itr<E> implements ListIterator<E>, KMutableListIterator {
            public int expectedModCount;
            public int index;
            public int lastIndex;

            @NotNull
            public final BuilderSubList<E> list;

            public Itr(@NotNull BuilderSubList<E> list, int i) {
                Intrinsics.checkNotNullParameter(list, "list");
                this.list = list;
                this.index = i;
                this.lastIndex = -1;
                this.expectedModCount = ((AbstractList) list).modCount;
            }

            private final void checkForComodification() {
                if (((AbstractList) this.list.root).modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override // java.util.ListIterator
            public void add(E e) {
                checkForComodification();
                BuilderSubList<E> builderSubList = this.list;
                int i = this.index;
                this.index = i + 1;
                builderSubList.add(i, e);
                this.lastIndex = -1;
                this.expectedModCount = ((AbstractList) this.list).modCount;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public boolean hasNext() {
                return this.index < this.list.length;
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return this.index > 0;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public E next() {
                checkForComodification();
                int i = this.index;
                BuilderSubList<E> builderSubList = this.list;
                if (i >= builderSubList.length) {
                    throw new NoSuchElementException();
                }
                this.index = i + 1;
                this.lastIndex = i;
                return builderSubList.backing[builderSubList.offset + i];
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return this.index;
            }

            @Override // java.util.ListIterator
            public E previous() {
                checkForComodification();
                int i = this.index;
                if (i <= 0) {
                    throw new NoSuchElementException();
                }
                int i2 = i - 1;
                this.index = i2;
                this.lastIndex = i2;
                BuilderSubList<E> builderSubList = this.list;
                return builderSubList.backing[builderSubList.offset + i2];
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return this.index - 1;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public void remove() {
                checkForComodification();
                int i = this.lastIndex;
                if (i == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                this.list.removeAt(i);
                this.index = this.lastIndex;
                this.lastIndex = -1;
                this.expectedModCount = ((AbstractList) this.list).modCount;
            }

            @Override // java.util.ListIterator
            public void set(E e) {
                checkForComodification();
                int i = this.lastIndex;
                if (i == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                this.list.set(i, e);
            }
        }

        public BuilderSubList(@NotNull E[] backing, int i, int i2, @Nullable BuilderSubList<E> builderSubList, @NotNull ListBuilder<E> root) {
            Intrinsics.checkNotNullParameter(backing, "backing");
            Intrinsics.checkNotNullParameter(root, "root");
            this.backing = backing;
            this.offset = i;
            this.length = i2;
            this.parent = builderSubList;
            this.root = root;
            ((AbstractList) this).modCount = ((AbstractList) root).modCount;
        }

        private final void registerModification() {
            ((AbstractList) this).modCount++;
        }

        private final Object writeReplace() throws NotSerializableException {
            if (this.root.isReadOnly) {
                return new SerializedCollection(this, 0);
            }
            throw new NotSerializableException("The list cannot be serialized while it is being built.");
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(E e) {
            checkIsMutable();
            checkForComodification();
            addAtInternal(this.offset + this.length, e);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(@NotNull Collection<? extends E> elements) {
            Intrinsics.checkNotNullParameter(elements, "elements");
            checkIsMutable();
            checkForComodification();
            int size = elements.size();
            addAllInternal(this.offset + this.length, elements, size);
            return size > 0;
        }

        public final void addAllInternal(int i, Collection<? extends E> collection, int i2) {
            registerModification();
            BuilderSubList<E> builderSubList = this.parent;
            if (builderSubList != null) {
                builderSubList.addAllInternal(i, collection, i2);
            } else {
                this.root.addAllInternal(i, collection, i2);
            }
            this.backing = (E[]) this.root.backing;
            this.length += i2;
        }

        public final void addAtInternal(int i, E e) {
            registerModification();
            BuilderSubList<E> builderSubList = this.parent;
            if (builderSubList != null) {
                builderSubList.addAtInternal(i, e);
            } else {
                this.root.addAtInternal(i, e);
            }
            this.backing = (E[]) this.root.backing;
            this.length++;
        }

        public final void checkForComodification() {
            if (((AbstractList) this.root).modCount != ((AbstractList) this).modCount) {
                throw new ConcurrentModificationException();
            }
        }

        public final void checkIsMutable() {
            if (this.root.isReadOnly) {
                throw new UnsupportedOperationException();
            }
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            checkIsMutable();
            checkForComodification();
            removeRangeInternal(this.offset, this.length);
        }

        public final boolean contentEquals(List<?> list) {
            return ListBuilderKt.subarrayContentEquals(this.backing, this.offset, this.length, list);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@Nullable Object obj) {
            checkForComodification();
            if (obj == this) {
                return true;
            }
            if (obj instanceof List) {
                return ListBuilderKt.subarrayContentEquals(this.backing, this.offset, this.length, (List) obj);
            }
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i) {
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
            return this.backing[this.offset + i];
        }

        @Override // kotlin.collections.AbstractMutableList
        public int getSize() {
            checkForComodification();
            return this.length;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            checkForComodification();
            return ListBuilderKt.subarrayContentHashCode(this.backing, this.offset, this.length);
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            checkForComodification();
            for (int i = 0; i < this.length; i++) {
                if (Intrinsics.areEqual(this.backing[this.offset + i], obj)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            checkForComodification();
            return this.length == 0;
        }

        public final boolean isReadOnly() {
            return this.root.isReadOnly;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        @NotNull
        public Iterator<E> iterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            checkForComodification();
            for (int i = this.length - 1; i >= 0; i--) {
                if (Intrinsics.areEqual(this.backing[this.offset + i], obj)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        @NotNull
        public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean remove(Object obj) {
            checkIsMutable();
            checkForComodification();
            int iIndexOf = indexOf(obj);
            if (iIndexOf >= 0) {
                removeAt(iIndexOf);
            }
            return iIndexOf >= 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean removeAll(@NotNull Collection<?> elements) {
            Intrinsics.checkNotNullParameter(elements, "elements");
            checkIsMutable();
            checkForComodification();
            return retainOrRemoveAllInternal(this.offset, this.length, elements, false) > 0;
        }

        @Override // kotlin.collections.AbstractMutableList
        public E removeAt(int i) {
            checkIsMutable();
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
            return removeAtInternal(this.offset + i);
        }

        public final E removeAtInternal(int i) {
            registerModification();
            BuilderSubList<E> builderSubList = this.parent;
            this.length--;
            return builderSubList != null ? builderSubList.removeAtInternal(i) : (E) this.root.removeAtInternal(i);
        }

        public final void removeRangeInternal(int i, int i2) {
            if (i2 > 0) {
                registerModification();
            }
            BuilderSubList<E> builderSubList = this.parent;
            if (builderSubList != null) {
                builderSubList.removeRangeInternal(i, i2);
            } else {
                this.root.removeRangeInternal(i, i2);
            }
            this.length -= i2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean retainAll(@NotNull Collection<?> elements) {
            Intrinsics.checkNotNullParameter(elements, "elements");
            checkIsMutable();
            checkForComodification();
            return retainOrRemoveAllInternal(this.offset, this.length, elements, true) > 0;
        }

        public final int retainOrRemoveAllInternal(int i, int i2, Collection<? extends E> collection, boolean z) {
            BuilderSubList<E> builderSubList = this.parent;
            int iRetainOrRemoveAllInternal = builderSubList != null ? builderSubList.retainOrRemoveAllInternal(i, i2, collection, z) : this.root.retainOrRemoveAllInternal(i, i2, collection, z);
            if (iRetainOrRemoveAllInternal > 0) {
                registerModification();
            }
            this.length -= iRetainOrRemoveAllInternal;
            return iRetainOrRemoveAllInternal;
        }

        @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
        public E set(int i, E e) {
            checkIsMutable();
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
            E[] eArr = this.backing;
            int i2 = this.offset;
            E e2 = eArr[i2 + i];
            eArr[i2 + i] = e;
            return e2;
        }

        @Override // java.util.AbstractList, java.util.List
        @NotNull
        public List<E> subList(int i, int i2) {
            kotlin.collections.AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, this.length);
            return new BuilderSubList(this.backing, this.offset + i, i2 - i, this, this.root);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        @NotNull
        public <T> T[] toArray(@NotNull T[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            checkForComodification();
            int length = array.length;
            int i = this.length;
            if (length < i) {
                E[] eArr = this.backing;
                int i2 = this.offset;
                T[] tArr = (T[]) Arrays.copyOfRange(eArr, i2, i + i2, array.getClass());
                Intrinsics.checkNotNullExpressionValue(tArr, "copyOfRange(...)");
                return tArr;
            }
            E[] eArr2 = this.backing;
            int i3 = this.offset;
            ArraysKt___ArraysJvmKt.copyInto(eArr2, array, 0, i3, i + i3);
            CollectionsKt__CollectionsJVMKt.terminateCollectionToArray(this.length, array);
            return array;
        }

        @Override // java.util.AbstractCollection
        @NotNull
        public String toString() {
            checkForComodification();
            return ListBuilderKt.subarrayContentToString(this.backing, this.offset, this.length, this);
        }

        @Override // java.util.AbstractList, java.util.List
        @NotNull
        public ListIterator<E> listIterator(int i) {
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
            return new Itr(this, i);
        }

        @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
        public void add(int i, E e) {
            checkIsMutable();
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
            addAtInternal(this.offset + i, e);
        }

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int i, @NotNull Collection<? extends E> elements) {
            Intrinsics.checkNotNullParameter(elements, "elements");
            checkIsMutable();
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
            int size = elements.size();
            addAllInternal(this.offset + i, elements, size);
            return size > 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        @NotNull
        public Object[] toArray() {
            checkForComodification();
            E[] eArr = this.backing;
            int i = this.offset;
            return ArraysKt___ArraysJvmKt.copyOfRange(eArr, i, this.length + i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,718:1\n1#2:719\n*E\n"})
    public static final class Itr<E> implements ListIterator<E>, KMutableListIterator {
        public int expectedModCount;
        public int index;
        public int lastIndex;

        @NotNull
        public final ListBuilder<E> list;

        public Itr(@NotNull ListBuilder<E> list, int i) {
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
            this.index = i;
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) list).modCount;
        }

        private final void checkForComodification() {
            if (((AbstractList) this.list).modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            checkForComodification();
            ListBuilder<E> listBuilder = this.list;
            int i = this.index;
            this.index = i + 1;
            listBuilder.add(i, e);
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) this.list).modCount;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.index < this.list.length;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            checkForComodification();
            int i = this.index;
            ListBuilder<E> listBuilder = this.list;
            if (i >= listBuilder.length) {
                throw new NoSuchElementException();
            }
            this.index = i + 1;
            this.lastIndex = i;
            return listBuilder.backing[i];
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public E previous() {
            checkForComodification();
            int i = this.index;
            if (i <= 0) {
                throw new NoSuchElementException();
            }
            int i2 = i - 1;
            this.index = i2;
            this.lastIndex = i2;
            return this.list.backing[i2];
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            checkForComodification();
            int i = this.lastIndex;
            if (i == -1) {
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
            }
            this.list.removeAt(i);
            this.index = this.lastIndex;
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) this.list).modCount;
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            checkForComodification();
            int i = this.lastIndex;
            if (i == -1) {
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
            }
            this.list.set(i, e);
        }
    }

    static {
        ListBuilder listBuilder = new ListBuilder(0);
        listBuilder.isReadOnly = true;
        Empty = listBuilder;
    }

    public ListBuilder() {
        this(0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addAllInternal(int i, Collection<? extends E> collection, int i2) {
        registerModification();
        insertAtInternal(i, i2);
        Iterator<? extends E> it = collection.iterator();
        for (int i3 = 0; i3 < i2; i3++) {
            this.backing[i + i3] = it.next();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addAtInternal(int i, E e) {
        registerModification();
        insertAtInternal(i, 1);
        this.backing[i] = e;
    }

    private final void checkIsMutable() {
        if (this.isReadOnly) {
            throw new UnsupportedOperationException();
        }
    }

    private final boolean contentEquals(List<?> list) {
        return ListBuilderKt.subarrayContentEquals(this.backing, 0, this.length, list);
    }

    private final void registerModification() {
        ((AbstractList) this).modCount++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final E removeAtInternal(int i) {
        registerModification();
        E[] eArr = this.backing;
        E e = eArr[i];
        ArraysKt___ArraysJvmKt.copyInto(eArr, eArr, i, i + 1, this.length);
        ListBuilderKt.resetAt(this.backing, this.length - 1);
        this.length--;
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeRangeInternal(int i, int i2) {
        if (i2 > 0) {
            registerModification();
        }
        E[] eArr = this.backing;
        ArraysKt___ArraysJvmKt.copyInto(eArr, eArr, i, i + i2, this.length);
        E[] eArr2 = this.backing;
        int i3 = this.length;
        ListBuilderKt.resetRange(eArr2, i3 - i2, i3);
        this.length -= i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int retainOrRemoveAllInternal(int i, int i2, Collection<? extends E> collection, boolean z) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i + i3;
            if (collection.contains(this.backing[i5]) == z) {
                E[] eArr = this.backing;
                i3++;
                eArr[i4 + i] = eArr[i5];
                i4++;
            } else {
                i3++;
            }
        }
        int i6 = i2 - i4;
        E[] eArr2 = this.backing;
        ArraysKt___ArraysJvmKt.copyInto(eArr2, eArr2, i + i4, i2 + i, this.length);
        E[] eArr3 = this.backing;
        int i7 = this.length;
        ListBuilderKt.resetRange(eArr3, i7 - i6, i7);
        if (i6 > 0) {
            registerModification();
        }
        this.length -= i6;
        return i6;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.isReadOnly) {
            return new SerializedCollection(this, 0);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        checkIsMutable();
        addAtInternal(this.length, e);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        checkIsMutable();
        int size = elements.size();
        addAllInternal(this.length, elements, size);
        return size > 0;
    }

    @NotNull
    public final List<E> build() {
        checkIsMutable();
        this.isReadOnly = true;
        return this.length > 0 ? this : Empty;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        checkIsMutable();
        removeRangeInternal(0, this.length);
    }

    public final void ensureCapacityInternal(int i) {
        if (i < 0) {
            throw new OutOfMemoryError();
        }
        E[] eArr = this.backing;
        if (i > eArr.length) {
            this.backing = (E[]) ListBuilderKt.copyOfUninitializedElements(this.backing, kotlin.collections.AbstractList.Companion.newCapacity$kotlin_stdlib(eArr.length, i));
        }
    }

    public final void ensureExtraCapacity(int i) {
        ensureCapacityInternal(this.length + i);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            if (ListBuilderKt.subarrayContentEquals(this.backing, 0, this.length, (List) obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        return this.backing[i];
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.length;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return ListBuilderKt.subarrayContentHashCode(this.backing, 0, this.length);
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        for (int i = 0; i < this.length; i++) {
            if (Intrinsics.areEqual(this.backing[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    public final void insertAtInternal(int i, int i2) {
        ensureExtraCapacity(i2);
        E[] eArr = this.backing;
        ArraysKt___ArraysJvmKt.copyInto(eArr, eArr, i + i2, i, this.length);
        this.length += i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    @NotNull
    public Iterator<E> iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        for (int i = this.length - 1; i >= 0; i--) {
            if (Intrinsics.areEqual(this.backing[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    @NotNull
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        checkIsMutable();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            removeAt(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(@NotNull Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        checkIsMutable();
        return retainOrRemoveAllInternal(0, this.length, elements, false) > 0;
    }

    @Override // kotlin.collections.AbstractMutableList
    public E removeAt(int i) {
        checkIsMutable();
        kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        return removeAtInternal(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(@NotNull Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        checkIsMutable();
        return retainOrRemoveAllInternal(0, this.length, elements, true) > 0;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        checkIsMutable();
        kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        E[] eArr = this.backing;
        E e2 = eArr[i];
        eArr[i] = e;
        return e2;
    }

    @Override // java.util.AbstractList, java.util.List
    @NotNull
    public List<E> subList(int i, int i2) {
        kotlin.collections.AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, this.length);
        return new BuilderSubList(this.backing, i, i2 - i, null, this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public <T> T[] toArray(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        int length = array.length;
        int i = this.length;
        if (length < i) {
            T[] tArr = (T[]) Arrays.copyOfRange(this.backing, 0, i, array.getClass());
            Intrinsics.checkNotNullExpressionValue(tArr, "copyOfRange(...)");
            return tArr;
        }
        ArraysKt___ArraysJvmKt.copyInto(this.backing, array, 0, 0, i);
        CollectionsKt__CollectionsJVMKt.terminateCollectionToArray(this.length, array);
        return array;
    }

    @Override // java.util.AbstractCollection
    @NotNull
    public String toString() {
        return ListBuilderKt.subarrayContentToString(this.backing, 0, this.length, this);
    }

    public /* synthetic */ ListBuilder(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }

    @Override // java.util.AbstractList, java.util.List
    @NotNull
    public ListIterator<E> listIterator(int i) {
        kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        return new Itr(this, i);
    }

    public ListBuilder(int i) {
        this.backing = (E[]) ListBuilderKt.arrayOfUninitializedElements(i);
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        checkIsMutable();
        kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        addAtInternal(i, e);
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i, @NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        checkIsMutable();
        kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        int size = elements.size();
        addAllInternal(i, elements, size);
        return size > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public Object[] toArray() {
        return ArraysKt___ArraysJvmKt.copyOfRange(this.backing, 0, this.length);
    }
}
