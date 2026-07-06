package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class TreeMultiset<E> extends AbstractSortedMultiset<E> implements Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 1;
    public final transient AvlNode<E> header;
    public final transient GeneralRange<E> range;
    public final transient Reference<AvlNode<E>> rootReference;

    /* JADX INFO: renamed from: com.google.common.collect.TreeMultiset$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends Multisets.AbstractEntry<E> {
        public final /* synthetic */ TreeMultiset this$0;
        public final /* synthetic */ AvlNode val$baseEntry;

        public AnonymousClass1(final TreeMultiset this$0, final AvlNode val$baseEntry) {
            this.val$baseEntry = val$baseEntry;
            this.this$0 = this$0;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            int i = this.val$baseEntry.elemCount;
            return i == 0 ? this.this$0.count(getElement()) : i;
        }

        @Override // com.google.common.collect.Multiset.Entry
        @ParametricNullness
        public E getElement() {
            return this.val$baseEntry.elem;
        }
    }

    /* JADX INFO: renamed from: com.google.common.collect.TreeMultiset$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 implements Iterator<Multiset.Entry<E>> {
        public AvlNode<E> current;
        public Multiset.Entry<E> prevEntry;

        public AnonymousClass2() {
            this.current = TreeMultiset.this.firstNode();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.current == null) {
                return false;
            }
            if (!TreeMultiset.this.range.tooHigh(this.current.elem)) {
                return true;
            }
            this.current = null;
            return false;
        }

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState(this.prevEntry != null, "no calls to next() since the last call to remove()");
            TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
            this.prevEntry = null;
        }

        @Override // java.util.Iterator
        public Multiset.Entry<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            TreeMultiset treeMultiset = TreeMultiset.this;
            AvlNode<E> avlNode = this.current;
            Objects.requireNonNull(avlNode);
            Multiset.Entry<E> entryAccess$1500 = TreeMultiset.access$1500(treeMultiset, avlNode);
            this.prevEntry = entryAccess$1500;
            AvlNode<E> avlNode2 = this.current.succ;
            Objects.requireNonNull(avlNode2);
            if (avlNode2 == TreeMultiset.this.header) {
                this.current = null;
                return entryAccess$1500;
            }
            AvlNode<E> avlNode3 = this.current.succ;
            Objects.requireNonNull(avlNode3);
            this.current = avlNode3;
            return entryAccess$1500;
        }
    }

    /* JADX INFO: renamed from: com.google.common.collect.TreeMultiset$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3 implements Iterator<Multiset.Entry<E>> {
        public AvlNode<E> current;
        public Multiset.Entry<E> prevEntry = null;

        public AnonymousClass3() {
            this.current = TreeMultiset.this.lastNode();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.current == null) {
                return false;
            }
            if (!TreeMultiset.this.range.tooLow(this.current.elem)) {
                return true;
            }
            this.current = null;
            return false;
        }

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState(this.prevEntry != null, "no calls to next() since the last call to remove()");
            TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
            this.prevEntry = null;
        }

        @Override // java.util.Iterator
        public Multiset.Entry<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Objects.requireNonNull(this.current);
            Multiset.Entry<E> entryAccess$1500 = TreeMultiset.access$1500(TreeMultiset.this, this.current);
            this.prevEntry = entryAccess$1500;
            AvlNode<E> avlNode = this.current.pred;
            Objects.requireNonNull(avlNode);
            if (avlNode == TreeMultiset.this.header) {
                this.current = null;
                return entryAccess$1500;
            }
            AvlNode<E> avlNode2 = this.current.pred;
            Objects.requireNonNull(avlNode2);
            this.current = avlNode2;
            return entryAccess$1500;
        }
    }

    /* JADX INFO: renamed from: com.google.common.collect.TreeMultiset$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass4 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$BoundType;

        static {
            int[] iArr = new int[BoundType.values().length];
            $SwitchMap$com$google$common$collect$BoundType = iArr;
            try {
                iArr[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Aggregate {
        SIZE { // from class: com.google.common.collect.TreeMultiset.Aggregate.1
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(AvlNode<?> node) {
                return node.elemCount;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(AvlNode<?> root) {
                if (root == null) {
                    return 0L;
                }
                return root.totalCount;
            }
        },
        DISTINCT { // from class: com.google.common.collect.TreeMultiset.Aggregate.2
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(AvlNode<?> node) {
                return 1;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(AvlNode<?> root) {
                if (root == null) {
                    return 0L;
                }
                return root.distinctElements;
            }
        };

        public abstract int nodeAggregate(AvlNode<?> node);

        public abstract long treeAggregate(AvlNode<?> root);

        Aggregate(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Reference<T> {
        public T value;

        public Reference() {
        }

        public void checkAndSet(T expected, T newValue) {
            if (this.value != expected) {
                throw new ConcurrentModificationException();
            }
            this.value = newValue;
        }

        public void clear() {
            this.value = null;
        }

        public T get() {
            return this.value;
        }

        public Reference(AnonymousClass1 anonymousClass1) {
        }
    }

    public TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        this.range = GeneralRange.all(comparator);
        AvlNode<E> avlNode = new AvlNode<>();
        this.header = avlNode;
        avlNode.succ = avlNode;
        avlNode.pred = avlNode;
        this.rootReference = new Reference<>();
    }

    public static Multiset.Entry access$1500(TreeMultiset treeMultiset, AvlNode avlNode) {
        treeMultiset.getClass();
        return new AnonymousClass1(treeMultiset, avlNode);
    }

    public static void access$1900(AvlNode avlNode, AvlNode avlNode2) {
        avlNode.succ = avlNode2;
        avlNode2.pred = avlNode;
    }

    public static <E extends Comparable> TreeMultiset<E> create() {
        return new TreeMultiset<>(NaturalOrdering.INSTANCE);
    }

    @J2ktIncompatible
    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        Object object = stream.readObject();
        Objects.requireNonNull(object);
        Comparator comparator = (Comparator) object;
        Serialization.getFieldSetter(AbstractSortedMultiset.class, "comparator").set(this, comparator);
        Serialization.getFieldSetter(TreeMultiset.class, "range").set(this, GeneralRange.all(comparator));
        Serialization.getFieldSetter(TreeMultiset.class, "rootReference").set(this, new Reference());
        AvlNode<E> avlNode = new AvlNode<>();
        Serialization.getFieldSetter(TreeMultiset.class, "header").set(this, avlNode);
        avlNode.succ = avlNode;
        avlNode.pred = avlNode;
        Serialization.populateMultiset(this, stream, stream.readInt());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void successor(AvlNode<T> a, AvlNode<T> b) {
        a.succ = b;
        b.pred = a;
    }

    @J2ktIncompatible
    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(super.elementSet().comparator());
        Serialization.writeMultiset(this, stream);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(@ParametricNullness E element, int occurrences) {
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return count(element);
        }
        Preconditions.checkArgument(this.range.contains(element));
        AvlNode<E> avlNode = this.rootReference.value;
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.add(this.comparator, element, occurrences, iArr));
            return iArr[0];
        }
        this.comparator.compare(element, element);
        AvlNode<E> avlNode2 = new AvlNode<>(element, occurrences);
        AvlNode<E> avlNode3 = this.header;
        successor(avlNode3, avlNode2, avlNode3);
        this.rootReference.checkAndSet(avlNode, avlNode2);
        return 0;
    }

    public final long aggregateAboveRange(Aggregate aggregate, AvlNode<E> avlNode) {
        if (avlNode == null) {
            return 0L;
        }
        int iCompare = this.comparator.compare(this.range.upperEndpoint, avlNode.elem);
        if (iCompare > 0) {
            return aggregateAboveRange(aggregate, avlNode.right);
        }
        if (iCompare != 0) {
            return aggregateAboveRange(aggregate, avlNode.left) + aggregate.treeAggregate(avlNode.right) + ((long) aggregate.nodeAggregate(avlNode));
        }
        int i = AnonymousClass4.$SwitchMap$com$google$common$collect$BoundType[this.range.upperBoundType.ordinal()];
        if (i == 1) {
            return aggregate.treeAggregate(avlNode.right) + ((long) aggregate.nodeAggregate(avlNode));
        }
        if (i == 2) {
            return aggregate.treeAggregate(avlNode.right);
        }
        throw new AssertionError();
    }

    public final long aggregateBelowRange(Aggregate aggregate, AvlNode<E> avlNode) {
        if (avlNode == null) {
            return 0L;
        }
        int iCompare = this.comparator.compare(this.range.lowerEndpoint, avlNode.elem);
        if (iCompare < 0) {
            return aggregateBelowRange(aggregate, avlNode.left);
        }
        if (iCompare != 0) {
            return aggregateBelowRange(aggregate, avlNode.right) + aggregate.treeAggregate(avlNode.left) + ((long) aggregate.nodeAggregate(avlNode));
        }
        int i = AnonymousClass4.$SwitchMap$com$google$common$collect$BoundType[this.range.lowerBoundType.ordinal()];
        if (i == 1) {
            return aggregate.treeAggregate(avlNode.left) + ((long) aggregate.nodeAggregate(avlNode));
        }
        if (i == 2) {
            return aggregate.treeAggregate(avlNode.left);
        }
        throw new AssertionError();
    }

    public final long aggregateForEntries(Aggregate aggr) {
        AvlNode<E> avlNode = this.rootReference.value;
        long jTreeAggregate = aggr.treeAggregate(avlNode);
        if (this.range.hasLowerBound) {
            jTreeAggregate -= aggregateBelowRange(aggr, avlNode);
        }
        return this.range.hasUpperBound ? jTreeAggregate - aggregateAboveRange(aggr, avlNode) : jTreeAggregate;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        GeneralRange<E> generalRange = this.range;
        if (generalRange.hasLowerBound || generalRange.hasUpperBound) {
            Iterators.clear(new AnonymousClass2());
            return;
        }
        AvlNode<E> avlNode = this.header.succ;
        Objects.requireNonNull(avlNode);
        while (true) {
            AvlNode<E> avlNode2 = this.header;
            if (avlNode == avlNode2) {
                avlNode2.succ = avlNode2;
                avlNode2.pred = avlNode2;
                this.rootReference.value = null;
                return;
            }
            AvlNode<E> avlNode3 = avlNode.succ;
            Objects.requireNonNull(avlNode3);
            avlNode.elemCount = 0;
            avlNode.left = null;
            avlNode.right = null;
            avlNode.pred = null;
            avlNode.succ = null;
            avlNode = avlNode3;
        }
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public Comparator comparator() {
        return this.comparator;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean contains(Object element) {
        return super.contains(element);
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object element) {
        try {
            AvlNode<E> avlNode = this.rootReference.value;
            if (this.range.contains(element) && avlNode != null) {
                return avlNode.count(this.comparator, element);
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractSortedMultiset
    public Iterator<Multiset.Entry<E>> descendingEntryIterator() {
        return new AnonymousClass3();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset descendingMultiset() {
        return super.descendingMultiset();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public int distinctElements() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.DISTINCT));
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<E> elementIterator() {
        return new Multisets.AnonymousClass5(new AnonymousClass2());
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ NavigableSet elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<Multiset.Entry<E>> entryIterator() {
        return new AnonymousClass2();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry firstEntry() {
        return super.firstEntry();
    }

    public final AvlNode<E> firstNode() {
        AvlNode<E> avlNodeCeiling;
        AvlNode<E> avlNode = this.rootReference.value;
        if (avlNode == null) {
            return null;
        }
        GeneralRange<E> generalRange = this.range;
        if (generalRange.hasLowerBound) {
            E e = generalRange.lowerEndpoint;
            avlNodeCeiling = avlNode.ceiling(this.comparator, e);
            if (avlNodeCeiling == null) {
                return null;
            }
            if (this.range.lowerBoundType == BoundType.OPEN && this.comparator.compare(e, avlNodeCeiling.elem) == 0) {
                avlNodeCeiling = avlNodeCeiling.succ;
                Objects.requireNonNull(avlNodeCeiling);
            }
        } else {
            avlNodeCeiling = this.header.succ;
            Objects.requireNonNull(avlNodeCeiling);
        }
        if (avlNodeCeiling == this.header || !this.range.contains(avlNodeCeiling.elem)) {
            return null;
        }
        return avlNodeCeiling;
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(@ParametricNullness E upperBound, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.upTo(this.comparator, upperBound, boundType)), this.header);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry lastEntry() {
        return super.lastEntry();
    }

    public final AvlNode<E> lastNode() {
        AvlNode<E> avlNodeFloor;
        AvlNode<E> avlNode = this.rootReference.value;
        if (avlNode == null) {
            return null;
        }
        GeneralRange<E> generalRange = this.range;
        if (generalRange.hasUpperBound) {
            E e = generalRange.upperEndpoint;
            avlNodeFloor = avlNode.floor(this.comparator, e);
            if (avlNodeFloor == null) {
                return null;
            }
            if (this.range.upperBoundType == BoundType.OPEN && this.comparator.compare(e, avlNodeFloor.elem) == 0) {
                avlNodeFloor = avlNodeFloor.pred;
                Objects.requireNonNull(avlNodeFloor);
            }
        } else {
            avlNodeFloor = this.header.pred;
            Objects.requireNonNull(avlNodeFloor);
        }
        if (avlNodeFloor == this.header || !this.range.contains(avlNodeFloor.elem)) {
            return null;
        }
        return avlNodeFloor;
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollFirstEntry() {
        return super.pollFirstEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollLastEntry() {
        return super.pollLastEntry();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(Object element, int occurrences) {
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return count(element);
        }
        AvlNode<E> avlNode = this.rootReference.value;
        int[] iArr = new int[1];
        try {
            if (this.range.contains(element) && avlNode != null) {
                this.rootReference.checkAndSet(avlNode, avlNode.remove(this.comparator, element, occurrences, iArr));
                return iArr[0];
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(@ParametricNullness E element, int count) {
        CollectPreconditions.checkNonnegative(count, "count");
        if (!this.range.contains(element)) {
            Preconditions.checkArgument(count == 0);
            return 0;
        }
        AvlNode<E> avlNode = this.rootReference.value;
        if (avlNode == null) {
            if (count > 0) {
                add(element, count);
            }
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.checkAndSet(avlNode, avlNode.setCount(this.comparator, element, count, iArr));
        return iArr[0];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.SIZE));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset subMultiset(@ParametricNullness Object fromElement, BoundType fromBoundType, @ParametricNullness Object toElement, BoundType toBoundType) {
        return super.subMultiset(fromElement, fromBoundType, toElement, toBoundType);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(@ParametricNullness E lowerBound, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.downTo(this.comparator, lowerBound, boundType)), this.header);
    }

    public final Multiset.Entry<E> wrapEntry(AvlNode<E> baseEntry) {
        return new AnonymousClass1(this, baseEntry);
    }

    public static int distinctElements(AvlNode<?> node) {
        if (node == null) {
            return 0;
        }
        return node.distinctElements;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void successor(AvlNode<T> a, AvlNode<T> b, AvlNode<T> c) {
        a.succ = b;
        b.pred = a;
        b.succ = c;
        c.pred = b;
    }

    public static <E> TreeMultiset<E> create(Comparator<? super E> comparator) {
        if (comparator == null) {
            return new TreeMultiset<>(NaturalOrdering.INSTANCE);
        }
        return new TreeMultiset<>(comparator);
    }

    public static <E extends Comparable> TreeMultiset<E> create(Iterable<? extends E> elements) {
        TreeMultiset<E> treeMultisetCreate = create();
        Iterables.addAll(treeMultisetCreate, elements);
        return treeMultisetCreate;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AvlNode<E> {
        public int distinctElements;
        public final E elem;
        public int elemCount;
        public int height;
        public AvlNode<E> left;
        public AvlNode<E> pred;
        public AvlNode<E> right;
        public AvlNode<E> succ;
        public long totalCount;

        public AvlNode(@ParametricNullness E elem, int elemCount) {
            Preconditions.checkArgument(elemCount > 0);
            this.elem = elem;
            this.elemCount = elemCount;
            this.totalCount = elemCount;
            this.distinctElements = 1;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

        public static AvlNode access$1200(AvlNode avlNode) {
            AvlNode<E> avlNode2 = avlNode.pred;
            Objects.requireNonNull(avlNode2);
            return avlNode2;
        }

        public static AvlNode access$700(AvlNode avlNode) {
            AvlNode<E> avlNode2 = avlNode.succ;
            Objects.requireNonNull(avlNode2);
            return avlNode2;
        }

        public static int height(AvlNode<?> node) {
            if (node == null) {
                return 0;
            }
            return node.height;
        }

        public static long totalCount(AvlNode<?> node) {
            if (node == null) {
                return 0L;
            }
            return node.totalCount;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> add(Comparator<? super E> comparator, @ParametricNullness E e, int i, int[] iArr) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    addLeftChild(e, i);
                    return this;
                }
                int i2 = avlNode.height;
                AvlNode<E> avlNodeAdd = avlNode.add(comparator, e, i, iArr);
                this.left = avlNodeAdd;
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += (long) i;
                if (avlNodeAdd.height != i2) {
                    return rebalance();
                }
            } else {
                if (iCompare <= 0) {
                    int i3 = this.elemCount;
                    iArr[0] = i3;
                    long j = i;
                    Preconditions.checkArgument(((long) i3) + j <= 2147483647L);
                    this.elemCount += i;
                    this.totalCount += j;
                    return this;
                }
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    addRightChild(e, i);
                    return this;
                }
                int i4 = avlNode2.height;
                AvlNode<E> avlNodeAdd2 = avlNode2.add(comparator, e, i, iArr);
                this.right = avlNodeAdd2;
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += (long) i;
                if (avlNodeAdd2.height != i4) {
                    return rebalance();
                }
            }
            return this;
        }

        @CanIgnoreReturnValue
        public final AvlNode<E> addLeftChild(@ParametricNullness E e, int count) {
            this.left = new AvlNode<>(e, count);
            AvlNode<E> avlNode = this.pred;
            Objects.requireNonNull(avlNode);
            TreeMultiset.successor(avlNode, this.left, this);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += (long) count;
            return this;
        }

        @CanIgnoreReturnValue
        public final AvlNode<E> addRightChild(@ParametricNullness E e, int count) {
            AvlNode<E> avlNode = new AvlNode<>(e, count);
            this.right = avlNode;
            AvlNode<E> avlNode2 = this.succ;
            Objects.requireNonNull(avlNode2);
            TreeMultiset.successor(this, avlNode, avlNode2);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += (long) count;
            return this;
        }

        public final int balanceFactor() {
            AvlNode<E> avlNode = this.left;
            int i = avlNode == null ? 0 : avlNode.height;
            AvlNode<E> avlNode2 = this.right;
            return i - (avlNode2 != null ? avlNode2.height : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final AvlNode<E> ceiling(Comparator<? super E> comparator, @ParametricNullness E e) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode != null) {
                    return (AvlNode) MoreObjects.firstNonNull(avlNode.ceiling(comparator, e), this);
                }
            } else if (iCompare != 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    return null;
                }
                return avlNode2.ceiling(comparator, e);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int count(Comparator<? super E> comparator, @ParametricNullness E e) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    return 0;
                }
                return avlNode.count(comparator, e);
            }
            if (iCompare <= 0) {
                return this.elemCount;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return 0;
            }
            return avlNode2.count(comparator, e);
        }

        public final AvlNode<E> deleteMe() {
            int i = this.elemCount;
            this.elemCount = 0;
            AvlNode<E> avlNode = this.pred;
            Objects.requireNonNull(avlNode);
            AvlNode<E> avlNode2 = this.succ;
            Objects.requireNonNull(avlNode2);
            TreeMultiset.access$1900(avlNode, avlNode2);
            AvlNode<E> avlNode3 = this.left;
            if (avlNode3 == null) {
                return this.right;
            }
            AvlNode<E> avlNode4 = this.right;
            if (avlNode4 == null) {
                return avlNode3;
            }
            if (avlNode3.height >= avlNode4.height) {
                AvlNode<E> avlNode5 = this.pred;
                Objects.requireNonNull(avlNode5);
                avlNode5.left = this.left.removeMax(avlNode5);
                avlNode5.right = this.right;
                avlNode5.distinctElements = this.distinctElements - 1;
                avlNode5.totalCount = this.totalCount - ((long) i);
                return avlNode5.rebalance();
            }
            AvlNode<E> avlNode6 = this.succ;
            Objects.requireNonNull(avlNode6);
            avlNode6.right = this.right.removeMin(avlNode6);
            avlNode6.left = this.left;
            avlNode6.distinctElements = this.distinctElements - 1;
            avlNode6.totalCount = this.totalCount - ((long) i);
            return avlNode6.rebalance();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final AvlNode<E> floor(Comparator<? super E> comparator, @ParametricNullness E e) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare > 0) {
                AvlNode<E> avlNode = this.right;
                if (avlNode != null) {
                    return (AvlNode) MoreObjects.firstNonNull(avlNode.floor(comparator, e), this);
                }
            } else if (iCompare != 0) {
                AvlNode<E> avlNode2 = this.left;
                if (avlNode2 == null) {
                    return null;
                }
                return avlNode2.floor(comparator, e);
            }
            return this;
        }

        public int getCount() {
            return this.elemCount;
        }

        @ParametricNullness
        public E getElement() {
            return this.elem;
        }

        public final AvlNode<E> pred() {
            AvlNode<E> avlNode = this.pred;
            Objects.requireNonNull(avlNode);
            return avlNode;
        }

        public final AvlNode<E> rebalance() {
            int iBalanceFactor = balanceFactor();
            if (iBalanceFactor == -2) {
                Objects.requireNonNull(this.right);
                if (this.right.balanceFactor() > 0) {
                    this.right = this.right.rotateRight();
                }
                return rotateLeft();
            }
            if (iBalanceFactor != 2) {
                recomputeHeight();
                return this;
            }
            Objects.requireNonNull(this.left);
            if (this.left.balanceFactor() < 0) {
                this.left = this.left.rotateLeft();
            }
            return rotateRight();
        }

        public final void recompute() {
            recomputeMultiset();
            recomputeHeight();
        }

        public final void recomputeHeight() {
            AvlNode<E> avlNode = this.left;
            int i = avlNode == null ? 0 : avlNode.height;
            AvlNode<E> avlNode2 = this.right;
            this.height = Math.max(i, avlNode2 != null ? avlNode2.height : 0) + 1;
        }

        public final void recomputeMultiset() {
            int iDistinctElements = TreeMultiset.distinctElements(this.left) + 1;
            AvlNode<E> avlNode = this.right;
            this.distinctElements = iDistinctElements + (avlNode == null ? 0 : avlNode.distinctElements);
            this.totalCount = totalCount(this.right) + totalCount(this.left) + ((long) this.elemCount);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> remove(Comparator<? super E> comparator, @ParametricNullness E e, int i, int[] iArr) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.left = avlNode.remove(comparator, e, i, iArr);
                int i2 = iArr[0];
                if (i2 > 0) {
                    if (i >= i2) {
                        this.distinctElements--;
                        this.totalCount -= (long) i2;
                    } else {
                        this.totalCount -= (long) i;
                    }
                }
                return i2 == 0 ? this : rebalance();
            }
            if (iCompare <= 0) {
                int i3 = this.elemCount;
                iArr[0] = i3;
                if (i >= i3) {
                    return deleteMe();
                }
                this.elemCount = i3 - i;
                this.totalCount -= (long) i;
                return this;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                iArr[0] = 0;
                return this;
            }
            this.right = avlNode2.remove(comparator, e, i, iArr);
            int i4 = iArr[0];
            if (i4 > 0) {
                if (i >= i4) {
                    this.distinctElements--;
                    this.totalCount -= (long) i4;
                } else {
                    this.totalCount -= (long) i;
                }
            }
            return rebalance();
        }

        public final AvlNode<E> removeMax(AvlNode<E> node) {
            AvlNode<E> avlNode = this.right;
            if (avlNode == null) {
                return this.left;
            }
            this.right = avlNode.removeMax(node);
            this.distinctElements--;
            this.totalCount -= (long) node.elemCount;
            return rebalance();
        }

        public final AvlNode<E> removeMin(AvlNode<E> node) {
            AvlNode<E> avlNode = this.left;
            if (avlNode == null) {
                return this.right;
            }
            this.left = avlNode.removeMin(node);
            this.distinctElements--;
            this.totalCount -= (long) node.elemCount;
            return rebalance();
        }

        public final AvlNode<E> rotateLeft() {
            Preconditions.checkState(this.right != null);
            AvlNode<E> avlNode = this.right;
            this.right = avlNode.left;
            avlNode.left = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        public final AvlNode<E> rotateRight() {
            Preconditions.checkState(this.left != null);
            AvlNode<E> avlNode = this.left;
            this.left = avlNode.right;
            avlNode.right = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> setCount(Comparator<? super E> comparator, @ParametricNullness E e, int i, int i2, int[] iArr) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode != null) {
                    this.left = avlNode.setCount(comparator, e, i, i2, iArr);
                    int i3 = iArr[0];
                    if (i3 == i) {
                        if (i2 == 0 && i3 != 0) {
                            this.distinctElements--;
                        } else if (i2 > 0 && i3 == 0) {
                            this.distinctElements++;
                        }
                        this.totalCount += (long) (i2 - i3);
                    }
                    return rebalance();
                }
                iArr[0] = 0;
                if (i == 0 && i2 > 0) {
                    addLeftChild(e, i2);
                    return this;
                }
            } else if (iCompare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 != null) {
                    this.right = avlNode2.setCount(comparator, e, i, i2, iArr);
                    int i4 = iArr[0];
                    if (i4 == i) {
                        if (i2 == 0 && i4 != 0) {
                            this.distinctElements--;
                        } else if (i2 > 0 && i4 == 0) {
                            this.distinctElements++;
                        }
                        this.totalCount += (long) (i2 - i4);
                    }
                    return rebalance();
                }
                iArr[0] = 0;
                if (i == 0 && i2 > 0) {
                    addRightChild(e, i2);
                    return this;
                }
            } else {
                int i5 = this.elemCount;
                iArr[0] = i5;
                if (i == i5) {
                    if (i2 == 0) {
                        return deleteMe();
                    }
                    this.totalCount += (long) (i2 - i5);
                    this.elemCount = i2;
                }
            }
            return this;
        }

        public final AvlNode<E> succ() {
            AvlNode<E> avlNode = this.succ;
            Objects.requireNonNull(avlNode);
            return avlNode;
        }

        public String toString() {
            return new Multisets.ImmutableEntry(this.elem, this.elemCount).toString();
        }

        public AvlNode() {
            this.elem = null;
            this.elemCount = 1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AvlNode<E> setCount(Comparator<? super E> comparator, @ParametricNullness E e, int i, int[] iArr) {
            int iCompare = comparator.compare(e, this.elem);
            if (iCompare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    if (i > 0) {
                        addLeftChild(e, i);
                        return this;
                    }
                } else {
                    this.left = avlNode.setCount(comparator, e, i, iArr);
                    if (i == 0 && iArr[0] != 0) {
                        this.distinctElements--;
                    } else if (i > 0 && iArr[0] == 0) {
                        this.distinctElements++;
                    }
                    this.totalCount += (long) (i - iArr[0]);
                    return rebalance();
                }
            } else if (iCompare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    if (i > 0) {
                        addRightChild(e, i);
                    }
                } else {
                    this.right = avlNode2.setCount(comparator, e, i, iArr);
                    if (i == 0 && iArr[0] != 0) {
                        this.distinctElements--;
                    } else if (i > 0 && iArr[0] == 0) {
                        this.distinctElements++;
                    }
                    this.totalCount += (long) (i - iArr[0]);
                    return rebalance();
                }
            } else {
                int i2 = this.elemCount;
                iArr[0] = i2;
                if (i == 0) {
                    return deleteMe();
                }
                this.totalCount += (long) (i - i2);
                this.elemCount = i;
                return this;
            }
            return this;
        }
    }

    public TreeMultiset(Reference<AvlNode<E>> rootReference, GeneralRange<E> range, AvlNode<E> endLink) {
        super(range.comparator);
        this.rootReference = rootReference;
        this.range = range;
        this.header = endLink;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public boolean setCount(@ParametricNullness E element, int oldCount, int newCount) {
        CollectPreconditions.checkNonnegative(newCount, "newCount");
        CollectPreconditions.checkNonnegative(oldCount, "oldCount");
        Preconditions.checkArgument(this.range.contains(element));
        AvlNode<E> avlNode = this.rootReference.value;
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.setCount(this.comparator, element, oldCount, newCount, iArr));
            return iArr[0] == oldCount;
        }
        if (oldCount != 0) {
            return false;
        }
        if (newCount > 0) {
            add(element, newCount);
        }
        return true;
    }
}
