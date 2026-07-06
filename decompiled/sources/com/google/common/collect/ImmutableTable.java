package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.DoNotMock;
import j$.util.stream.Collector;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ImmutableTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 912559;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DoNotMock
    public static final class Builder<R, C, V> {
        public final List<Table.Cell<R, C, V>> cells = new ArrayList();
        public Comparator<? super C> columnComparator;
        public Comparator<? super R> rowComparator;

        public ImmutableTable<R, C, V> build() {
            return buildOrThrow();
        }

        public ImmutableTable<R, C, V> buildOrThrow() {
            int size = this.cells.size();
            return size != 0 ? size != 1 ? RegularImmutableTable.forCells(this.cells, this.rowComparator, this.columnComparator) : new SingletonImmutableTable((Table.Cell) Iterables.getOnlyElement(this.cells)) : (ImmutableTable<R, C, V>) SparseImmutableTable.EMPTY;
        }

        @CanIgnoreReturnValue
        public Builder<R, C, V> combine(Builder<R, C, V> other) {
            this.cells.addAll(other.cells);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<R, C, V> orderColumnsBy(Comparator<? super C> columnComparator) {
            Preconditions.checkNotNull(columnComparator, "columnComparator");
            this.columnComparator = columnComparator;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<R, C, V> orderRowsBy(Comparator<? super R> rowComparator) {
            Preconditions.checkNotNull(rowComparator, "rowComparator");
            this.rowComparator = rowComparator;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<R, C, V> put(R rowKey, C columnKey, V value) {
            this.cells.add(ImmutableTable.cellOf(rowKey, columnKey, value));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<R, C, V> putAll(Table<? extends R, ? extends C, ? extends V> table) {
            Iterator<Table.Cell<? extends R, ? extends C, ? extends V>> it = table.cellSet().iterator();
            while (it.hasNext()) {
                put(it.next());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<R, C, V> put(Table.Cell<? extends R, ? extends C, ? extends V> cell) {
            if (!(cell instanceof Tables.ImmutableCell)) {
                put(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
                return this;
            }
            Tables.ImmutableCell immutableCell = (Tables.ImmutableCell) cell;
            Preconditions.checkNotNull(immutableCell.rowKey, "row");
            Preconditions.checkNotNull(immutableCell.columnKey, "column");
            Preconditions.checkNotNull(immutableCell.value, "value");
            this.cells.add(cell);
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SerializedForm implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final int[] cellColumnIndices;
        public final int[] cellRowIndices;
        public final Object[] cellValues;
        public final Object[] columnKeys;
        public final Object[] rowKeys;

        public SerializedForm(Object[] rowKeys, Object[] columnKeys, Object[] cellValues, int[] cellRowIndices, int[] cellColumnIndices) {
            this.rowKeys = rowKeys;
            this.columnKeys = columnKeys;
            this.cellValues = cellValues;
            this.cellRowIndices = cellRowIndices;
            this.cellColumnIndices = cellColumnIndices;
        }

        public static SerializedForm create(ImmutableTable<?, ?, ?> table, int[] cellRowIndices, int[] cellColumnIndices) {
            ImmutableSet<?> immutableSetRowKeySet = table.rowKeySet();
            Object[] objArr = ImmutableCollection.EMPTY_ARRAY;
            return new SerializedForm(immutableSetRowKeySet.toArray(objArr), table.columnKeySet().toArray(objArr), table.values().toArray(objArr), cellRowIndices, cellColumnIndices);
        }

        public Object readResolve() {
            Object[] objArr = this.cellValues;
            if (objArr.length == 0) {
                return SparseImmutableTable.EMPTY;
            }
            int i = 0;
            if (objArr.length == 1) {
                return new SingletonImmutableTable(this.rowKeys[0], this.columnKeys[0], objArr[0]);
            }
            ImmutableList.Builder builder = new ImmutableList.Builder(objArr.length);
            while (true) {
                Object[] objArr2 = this.cellValues;
                if (i >= objArr2.length) {
                    return RegularImmutableTable.forOrderedComponents(builder.build(), ImmutableSet.copyOf(this.rowKeys), ImmutableSet.copyOf(this.columnKeys));
                }
                builder.add(ImmutableTable.cellOf(this.rowKeys[this.cellRowIndices[i]], this.columnKeys[this.cellColumnIndices[i]], objArr2[i]));
                i++;
            }
        }
    }

    public static <R, C, V> Builder<R, C, V> builder() {
        return new Builder<>();
    }

    public static <R, C, V> Table.Cell<R, C, V> cellOf(R rowKey, C columnKey, V value) {
        Preconditions.checkNotNull(rowKey, "rowKey");
        Preconditions.checkNotNull(columnKey, "columnKey");
        Preconditions.checkNotNull(value, "value");
        return new Tables.ImmutableCell(rowKey, columnKey, value);
    }

    public static <R, C, V> ImmutableTable<R, C, V> copyOf(Table<? extends R, ? extends C, ? extends V> table) {
        return table instanceof ImmutableTable ? (ImmutableTable) table : copyOf(table.cellSet());
    }

    public static <R, C, V> ImmutableTable<R, C, V> of() {
        return (ImmutableTable<R, C, V>) SparseImmutableTable.EMPTY;
    }

    @J2ktIncompatible
    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @IgnoreJRERequirement
    public static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(Function<? super T, ? extends R> rowFunction, Function<? super T, ? extends C> columnFunction, Function<? super T, ? extends V> valueFunction) {
        return TableCollectors.toImmutableTable(rowFunction, columnFunction, valueFunction);
    }

    @Override // com.google.common.collect.AbstractTable
    public /* bridge */ /* synthetic */ Iterator cellIterator() {
        cellIterator();
        throw null;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Table
    public abstract ImmutableMap<C, Map<R, V>> columnMap();

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean contains(Object rowKey, Object columnKey) {
        return get(rowKey, columnKey) != null;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsColumn(Object columnKey) {
        return super.containsColumn(columnKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean containsRow(Object rowKey) {
        return super.containsRow(rowKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override // com.google.common.collect.AbstractTable
    public abstract ImmutableSet<Table.Cell<R, C, V>> createCellSet();

    @Override // com.google.common.collect.AbstractTable
    public abstract ImmutableCollection<V> createValues();

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean equals(Object obj) {
        return Tables.equalsImpl(this, obj);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ Object get(Object rowKey, Object columnKey) {
        return super.get(rowKey, columnKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final V put(R rowKey, C columnKey, V value) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public final V remove(Object rowKey, Object columnKey) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Table
    public abstract ImmutableMap<R, Map<C, V>> rowMap();

    @Override // com.google.common.collect.AbstractTable
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.AbstractTable
    public final Iterator<V> valuesIterator() {
        throw new AssertionError("should never be called");
    }

    @J2ktIncompatible
    @GwtIncompatible
    public abstract Object writeReplace();

    public static <R, C, V> ImmutableTable<R, C, V> of(R rowKey, C columnKey, V value) {
        return new SingletonImmutableTable(rowKey, columnKey, value);
    }

    @IgnoreJRERequirement
    public static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(Function<? super T, ? extends R> rowFunction, Function<? super T, ? extends C> columnFunction, Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
        return TableCollectors.toImmutableTable(rowFunction, columnFunction, valueFunction, mergeFunction);
    }

    @Override // com.google.common.collect.AbstractTable
    public final UnmodifiableIterator<Table.Cell<R, C, V>> cellIterator() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public ImmutableSet<Table.Cell<R, C, V>> cellSet() {
        return (ImmutableSet) super.cellSet();
    }

    @Override // com.google.common.collect.Table
    public ImmutableMap<R, V> column(C columnKey) {
        Preconditions.checkNotNull(columnKey, "columnKey");
        return (ImmutableMap) MoreObjects.firstNonNull((ImmutableMap) columnMap().get(columnKey), RegularImmutableMap.EMPTY);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public ImmutableSet<C> columnKeySet() {
        return columnMap().keySet();
    }

    @Override // com.google.common.collect.Table
    public ImmutableMap<C, V> row(R rowKey) {
        Preconditions.checkNotNull(rowKey, "rowKey");
        return (ImmutableMap) MoreObjects.firstNonNull((ImmutableMap) rowMap().get(rowKey), RegularImmutableMap.EMPTY);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public ImmutableSet<R> rowKeySet() {
        return rowMap().keySet();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public ImmutableCollection<V> values() {
        return (ImmutableCollection) super.values();
    }

    public static <R, C, V> ImmutableTable<R, C, V> copyOf(Iterable<? extends Table.Cell<? extends R, ? extends C, ? extends V>> cells) {
        Builder builder = new Builder();
        Iterator<? extends Table.Cell<? extends R, ? extends C, ? extends V>> it = cells.iterator();
        while (it.hasNext()) {
            builder.put(it.next());
        }
        return builder.buildOrThrow();
    }
}
