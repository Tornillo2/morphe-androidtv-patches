package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.common.collect.Synchronized;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import j$.util.DesugarCollections;
import j$.util.stream.Collector;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.function.BinaryOperator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Tables {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class AbstractCell<R, C, V> implements Table.Cell<R, C, V> {
        @Override // com.google.common.collect.Table.Cell
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Table.Cell) {
                Table.Cell cell = (Table.Cell) obj;
                if (Objects.equal(getRowKey(), cell.getRowKey()) && Objects.equal(getColumnKey(), cell.getColumnKey()) && Objects.equal(getValue(), cell.getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.Table.Cell
        public int hashCode() {
            return Arrays.hashCode(new Object[]{getRowKey(), getColumnKey(), getValue()});
        }

        public String toString() {
            return "(" + getRowKey() + "," + getColumnKey() + ")=" + getValue();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ImmutableCell<R, C, V> extends AbstractCell<R, C, V> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        @ParametricNullness
        public final C columnKey;

        @ParametricNullness
        public final R rowKey;

        @ParametricNullness
        public final V value;

        public ImmutableCell(@ParametricNullness R rowKey, @ParametricNullness C columnKey, @ParametricNullness V value) {
            this.rowKey = rowKey;
            this.columnKey = columnKey;
            this.value = value;
        }

        @Override // com.google.common.collect.Table.Cell
        @ParametricNullness
        public C getColumnKey() {
            return this.columnKey;
        }

        @Override // com.google.common.collect.Table.Cell
        @ParametricNullness
        public R getRowKey() {
            return this.rowKey;
        }

        @Override // com.google.common.collect.Table.Cell
        @ParametricNullness
        public V getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransformedTable<R, C, V1, V2> extends AbstractTable<R, C, V2> {
        public final Table<R, C, V1> fromTable;
        public final Function<? super V1, V2> function;

        public static Table.Cell $r8$lambda$cxB1qRYqsQA8uw5DZ0P1cZSGz8I(TransformedTable transformedTable, Table.Cell cell) {
            transformedTable.getClass();
            return new ImmutableCell(cell.getRowKey(), cell.getColumnKey(), transformedTable.function.apply((Object) cell.getValue()));
        }

        public TransformedTable(Table<R, C, V1> fromTable, Function<? super V1, V2> function) {
            fromTable.getClass();
            this.fromTable = fromTable;
            function.getClass();
            this.function = function;
        }

        public Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>> cellFunction() {
            return new Function() { // from class: com.google.common.collect.Tables$TransformedTable$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return Tables.TransformedTable.$r8$lambda$cxB1qRYqsQA8uw5DZ0P1cZSGz8I(this.f$0, (Table.Cell) obj);
                }
            };
        }

        @Override // com.google.common.collect.AbstractTable
        public Iterator<Table.Cell<R, C, V2>> cellIterator() {
            return new Iterators.AnonymousClass6(this.fromTable.cellSet().iterator(), cellFunction());
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void clear() {
            this.fromTable.clear();
        }

        @Override // com.google.common.collect.Table
        public Map<R, V2> column(@ParametricNullness C columnKey) {
            return Maps.transformValues(this.fromTable.column(columnKey), this.function);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public Set<C> columnKeySet() {
            return this.fromTable.columnKeySet();
        }

        @Override // com.google.common.collect.Table
        public Map<C, Map<R, V2>> columnMap() {
            return Maps.transformValues(this.fromTable.columnMap(), new Function() { // from class: com.google.common.collect.Tables$TransformedTable$$ExternalSyntheticLambda2
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return Maps.transformValues((Map) obj, this.f$0.function);
                }
            });
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean contains(Object rowKey, Object columnKey) {
            return this.fromTable.contains(rowKey, columnKey);
        }

        @Override // com.google.common.collect.AbstractTable
        public Collection<V2> createValues() {
            return new Collections2.TransformedCollection(this.fromTable.values(), this.function);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V2 get(Object obj, Object obj2) {
            if (contains(obj, obj2)) {
                return this.function.apply(this.fromTable.get(obj, obj2));
            }
            return null;
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V2 put(@ParametricNullness R rowKey, @ParametricNullness C columnKey, @ParametricNullness V2 value) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V2> table) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V2 remove(Object obj, Object obj2) {
            if (contains(obj, obj2)) {
                return this.function.apply(this.fromTable.remove(obj, obj2));
            }
            return null;
        }

        @Override // com.google.common.collect.Table
        public Map<C, V2> row(@ParametricNullness R rowKey) {
            return Maps.transformValues(this.fromTable.row(rowKey), this.function);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public Set<R> rowKeySet() {
            return this.fromTable.rowKeySet();
        }

        @Override // com.google.common.collect.Table
        public Map<R, Map<C, V2>> rowMap() {
            return Maps.transformValues(this.fromTable.rowMap(), new Function() { // from class: com.google.common.collect.Tables$TransformedTable$$ExternalSyntheticLambda1
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return Maps.transformValues((Map) obj, this.f$0.function);
                }
            });
        }

        @Override // com.google.common.collect.Table
        public int size() {
            return this.fromTable.size();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransposeTable<C, R, V> extends AbstractTable<C, R, V> {
        public final Table<R, C, V> original;

        public TransposeTable(Table<R, C, V> original) {
            original.getClass();
            this.original = original;
        }

        @Override // com.google.common.collect.AbstractTable
        public Iterator<Table.Cell<C, R, V>> cellIterator() {
            return new Iterators.AnonymousClass6(this.original.cellSet().iterator(), new Tables$TransposeTable$$ExternalSyntheticLambda0());
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void clear() {
            this.original.clear();
        }

        @Override // com.google.common.collect.Table
        public Map<C, V> column(@ParametricNullness R columnKey) {
            return this.original.row(columnKey);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public Set<R> columnKeySet() {
            return this.original.rowKeySet();
        }

        @Override // com.google.common.collect.Table
        public Map<R, Map<C, V>> columnMap() {
            return this.original.rowMap();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean contains(Object rowKey, Object columnKey) {
            return this.original.contains(columnKey, rowKey);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean containsColumn(Object columnKey) {
            return this.original.containsRow(columnKey);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean containsRow(Object rowKey) {
            return this.original.containsColumn(rowKey);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public boolean containsValue(Object value) {
            return this.original.containsValue(value);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V get(Object rowKey, Object columnKey) {
            return this.original.get(columnKey, rowKey);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V put(@ParametricNullness C rowKey, @ParametricNullness R columnKey, @ParametricNullness V value) {
            return this.original.put(columnKey, rowKey, value);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public void putAll(Table<? extends C, ? extends R, ? extends V> table) {
            this.original.putAll(Tables.transpose(table));
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public V remove(Object rowKey, Object columnKey) {
            return this.original.remove(columnKey, rowKey);
        }

        @Override // com.google.common.collect.Table
        public Map<R, V> row(@ParametricNullness C rowKey) {
            return this.original.column(rowKey);
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public Set<C> rowKeySet() {
            return this.original.columnKeySet();
        }

        @Override // com.google.common.collect.Table
        public Map<C, Map<R, V>> rowMap() {
            return this.original.columnMap();
        }

        @Override // com.google.common.collect.Table
        public int size() {
            return this.original.size();
        }

        @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
        public Collection<V> values() {
            return this.original.values();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnmodifiableTable<R, C, V> extends ForwardingTable<R, C, V> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Table<? extends R, ? extends C, ? extends V> delegate;

        public UnmodifiableTable(Table<? extends R, ? extends C, ? extends V> delegate) {
            delegate.getClass();
            this.delegate = delegate;
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Set<Table.Cell<R, C, V>> cellSet() {
            return DesugarCollections.unmodifiableSet(super.cellSet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<R, V> column(@ParametricNullness C columnKey) {
            return DesugarCollections.unmodifiableMap(super.column(columnKey));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Set<C> columnKeySet() {
            return DesugarCollections.unmodifiableSet(super.columnKeySet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<C, Map<R, V>> columnMap() {
            return DesugarCollections.unmodifiableMap(Maps.transformValues(super.columnMap(), new Tables$UnmodifiableRowSortedMap$$ExternalSyntheticLambda0()));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public V put(@ParametricNullness R rowKey, @ParametricNullness C columnKey, @ParametricNullness V value) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public V remove(Object rowKey, Object columnKey) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<C, V> row(@ParametricNullness R rowKey) {
            return DesugarCollections.unmodifiableMap(super.row(rowKey));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Set<R> rowKeySet() {
            return DesugarCollections.unmodifiableSet(super.rowKeySet());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Map<R, Map<C, V>> rowMap() {
            return DesugarCollections.unmodifiableMap(Maps.transformValues(super.rowMap(), new Tables$UnmodifiableRowSortedMap$$ExternalSyntheticLambda0()));
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public Collection<V> values() {
            return DesugarCollections.unmodifiableCollection(super.values());
        }

        @Override // com.google.common.collect.ForwardingTable, com.google.common.collect.ForwardingObject
        public Table<R, C, V> delegate() {
            return this.delegate;
        }
    }

    public static boolean equalsImpl(Table<?, ?, ?> table, Object obj) {
        if (obj == table) {
            return true;
        }
        if (obj instanceof Table) {
            return table.cellSet().equals(((Table) obj).cellSet());
        }
        return false;
    }

    public static <R, C, V> Table.Cell<R, C, V> immutableCell(@ParametricNullness R rowKey, @ParametricNullness C columnKey, @ParametricNullness V value) {
        return new ImmutableCell(rowKey, columnKey, value);
    }

    public static <R, C, V> Table<R, C, V> newCustomTable(Map<R, Map<C, V>> backingMap, Supplier<? extends Map<C, V>> factory) {
        Preconditions.checkArgument(backingMap.isEmpty());
        factory.getClass();
        return new StandardTable(backingMap, factory);
    }

    @J2ktIncompatible
    public static <R, C, V> Table<R, C, V> synchronizedTable(Table<R, C, V> table) {
        return new Synchronized.SynchronizedTable((Object) table, (Object) null);
    }

    @IgnoreJRERequirement
    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(java.util.function.Function<? super T, ? extends R> rowFunction, java.util.function.Function<? super T, ? extends C> columnFunction, java.util.function.Function<? super T, ? extends V> valueFunction, java.util.function.Supplier<I> tableSupplier) {
        return TableCollectors.toTable(rowFunction, columnFunction, valueFunction, tableSupplier);
    }

    public static <R, C, V1, V2> Table<R, C, V2> transformValues(Table<R, C, V1> fromTable, Function<? super V1, V2> function) {
        return new TransformedTable(fromTable, function);
    }

    public static <R, C, V> Table<C, R, V> transpose(Table<R, C, V> table) {
        return table instanceof TransposeTable ? ((TransposeTable) table).original : new TransposeTable(table);
    }

    public static <R, C, V> Table.Cell<C, R, V> transposeCell(Table.Cell<R, C, V> cell) {
        return new ImmutableCell(cell.getColumnKey(), cell.getRowKey(), cell.getValue());
    }

    public static <R, C, V> RowSortedTable<R, C, V> unmodifiableRowSortedTable(RowSortedTable<R, ? extends C, ? extends V> table) {
        return new UnmodifiableRowSortedMap((Table) table);
    }

    public static <R, C, V> Table<R, C, V> unmodifiableTable(Table<? extends R, ? extends C, ? extends V> table) {
        return new UnmodifiableTable(table);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnmodifiableRowSortedMap<R, C, V> extends UnmodifiableTable<R, C, V> implements RowSortedTable<R, C, V> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public UnmodifiableRowSortedMap(RowSortedTable<R, ? extends C, ? extends V> delegate) {
            super(delegate);
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.ForwardingObject
        public RowSortedTable<R, C, V> delegate() {
            return (RowSortedTable) this.delegate;
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public SortedSet<R> rowKeySet() {
            return DesugarCollections.unmodifiableSortedSet(((RowSortedTable) this.delegate).rowKeySet());
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.Table
        public SortedMap<R, Map<C, V>> rowMap() {
            return DesugarCollections.unmodifiableSortedMap(Maps.transformValues((SortedMap) ((RowSortedTable) this.delegate).rowMap(), (Function) new Tables$UnmodifiableRowSortedMap$$ExternalSyntheticLambda0()));
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.ForwardingObject
        public Table delegate() {
            return (RowSortedTable) this.delegate;
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ForwardingTable, com.google.common.collect.ForwardingObject
        public Object delegate() {
            return (RowSortedTable) this.delegate;
        }
    }

    @IgnoreJRERequirement
    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(java.util.function.Function<? super T, ? extends R> rowFunction, java.util.function.Function<? super T, ? extends C> columnFunction, java.util.function.Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction, java.util.function.Supplier<I> tableSupplier) {
        return TableCollectors.toTable(rowFunction, columnFunction, valueFunction, mergeFunction, tableSupplier);
    }
}
