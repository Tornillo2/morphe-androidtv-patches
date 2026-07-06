package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class ArrayTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final V[][] array;
    public final ImmutableMap<C, Integer> columnKeyToIndex;
    public final ImmutableList<C> columnList;

    @LazyInit
    public transient ArrayTable<R, C, V>.ColumnMap columnMap;
    public final ImmutableMap<R, Integer> rowKeyToIndex;
    public final ImmutableList<R> rowList;

    @LazyInit
    public transient ArrayTable<R, C, V>.RowMap rowMap;

    /* JADX INFO: renamed from: com.google.common.collect.ArrayTable$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 extends Tables.AbstractCell<R, C, V> {
        public final int columnIndex;
        public final int rowIndex;
        public final /* synthetic */ ArrayTable this$0;
        public final /* synthetic */ int val$index;

        public AnonymousClass2(final ArrayTable this$0, final int val$index) {
            this.val$index = val$index;
            this.this$0 = this$0;
            this.rowIndex = val$index / this$0.columnList.size();
            this.columnIndex = val$index % this$0.columnList.size();
        }

        @Override // com.google.common.collect.Table.Cell
        public C getColumnKey() {
            return this.this$0.columnList.get(this.columnIndex);
        }

        @Override // com.google.common.collect.Table.Cell
        public R getRowKey() {
            return this.this$0.rowList.get(this.rowIndex);
        }

        @Override // com.google.common.collect.Table.Cell
        public V getValue() {
            return (V) this.this$0.at(this.rowIndex, this.columnIndex);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class ArrayMap<K, V> extends Maps.IteratorBasedAbstractMap<K, V> {
        public final ImmutableMap<K, Integer> keyIndex;

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            return this.keyIndex.containsKey(key);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return new AbstractIndexedListIterator<Map.Entry<K, V>>(size()) { // from class: com.google.common.collect.ArrayTable.ArrayMap.2
                @Override // com.google.common.collect.AbstractIndexedListIterator
                public Map.Entry<K, V> get(int index) {
                    return ArrayMap.this.getEntry(index);
                }
            };
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            Integer num = this.keyIndex.get(key);
            if (num == null) {
                return null;
            }
            return getValue(num.intValue());
        }

        public Map.Entry<K, V> getEntry(final int index) {
            Preconditions.checkElementIndex(index, size());
            return new AbstractMapEntry<K, V>(this) { // from class: com.google.common.collect.ArrayTable.ArrayMap.1
                public final /* synthetic */ ArrayMap this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public K getKey() {
                    return (K) this.this$0.getKey(index);
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                @ParametricNullness
                public V getValue() {
                    return (V) this.this$0.getValue(index);
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                @ParametricNullness
                public V setValue(@ParametricNullness V v) {
                    return (V) this.this$0.setValue(index, v);
                }
            };
        }

        public K getKey(int index) {
            return this.keyIndex.keySet().asList().get(index);
        }

        public abstract String getKeyRole();

        @ParametricNullness
        public abstract V getValue(int index);

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.keyIndex.isEmpty();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.keyIndex.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K key, @ParametricNullness V value) {
            Integer num = this.keyIndex.get(key);
            if (num != null) {
                return setValue(num.intValue(), value);
            }
            throw new IllegalArgumentException(getKeyRole() + StringUtils.SPACE + key + " not in " + this.keyIndex.keySet());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object key) {
            throw new UnsupportedOperationException();
        }

        @ParametricNullness
        public abstract V setValue(int index, @ParametricNullness V newValue);

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return this.keyIndex.size();
        }

        public ArrayMap(ImmutableMap<K, Integer> keyIndex) {
            this.keyIndex = keyIndex;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class Column extends ArrayMap<R, V> {
        public final int columnIndex;

        public Column(int columnIndex) {
            super(ArrayTable.this.rowKeyToIndex);
            this.columnIndex = columnIndex;
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public String getKeyRole() {
            return "Row";
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public V getValue(int i) {
            return (V) ArrayTable.this.at(i, this.columnIndex);
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public V setValue(int i, V v) {
            return (V) ArrayTable.this.set(i, this.columnIndex, v);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class ColumnMap extends ArrayMap<C, Map<R, V>> {
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public String getKeyRole() {
            return "Column";
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap, java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ Object put(Object key, Object value) {
            put(key, (Map) value);
            throw null;
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public /* bridge */ /* synthetic */ Object setValue(int index, Object newValue) {
            setValue(index, (Map) newValue);
            throw null;
        }

        public ColumnMap() {
            super(ArrayTable.this.columnKeyToIndex);
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public Map<R, V> getValue(int index) {
            return new Column(index);
        }

        public Map<R, V> put(C key, Map<R, V> value) {
            throw new UnsupportedOperationException();
        }

        public Map<R, V> setValue(int index, Map<R, V> newValue) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class Row extends ArrayMap<C, V> {
        public final int rowIndex;

        public Row(int rowIndex) {
            super(ArrayTable.this.columnKeyToIndex);
            this.rowIndex = rowIndex;
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public String getKeyRole() {
            return "Column";
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public V getValue(int i) {
            return (V) ArrayTable.this.at(this.rowIndex, i);
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public V setValue(int i, V v) {
            return (V) ArrayTable.this.set(this.rowIndex, i, v);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class RowMap extends ArrayMap<R, Map<C, V>> {
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public String getKeyRole() {
            return "Row";
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap, java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ Object put(Object key, Object value) {
            put(key, (Map) value);
            throw null;
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public /* bridge */ /* synthetic */ Object setValue(int index, Object newValue) {
            setValue(index, (Map) newValue);
            throw null;
        }

        public RowMap() {
            super(ArrayTable.this.rowKeyToIndex);
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public Map<C, V> getValue(int index) {
            return new Row(index);
        }

        public Map<C, V> put(R key, Map<C, V> value) {
            throw new UnsupportedOperationException();
        }

        public Map<C, V> setValue(int index, Map<C, V> newValue) {
            throw new UnsupportedOperationException();
        }
    }

    public ArrayTable(ArrayTable<R, C, V> arrayTable) {
        ImmutableList<R> immutableList = arrayTable.rowList;
        this.rowList = immutableList;
        ImmutableList<C> immutableList2 = arrayTable.columnList;
        this.columnList = immutableList2;
        this.rowKeyToIndex = arrayTable.rowKeyToIndex;
        this.columnKeyToIndex = arrayTable.columnKeyToIndex;
        V[][] vArr = (V[][]) ((Object[][]) Array.newInstance((Class<?>) Object.class, immutableList.size(), immutableList2.size()));
        this.array = vArr;
        for (int i = 0; i < this.rowList.size(); i++) {
            V[] vArr2 = arrayTable.array[i];
            System.arraycopy(vArr2, 0, vArr[i], 0, vArr2.length);
        }
    }

    public static Table.Cell access$000(ArrayTable arrayTable, int i) {
        arrayTable.getClass();
        return new AnonymousClass2(arrayTable, i);
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Iterable<? extends R> rowKeys, Iterable<? extends C> columnKeys) {
        return new ArrayTable<>(rowKeys, columnKeys);
    }

    private Table.Cell<R, C, V> getCell(int index) {
        return new AnonymousClass2(this, index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public V getValue(int index) {
        return at(index / this.columnList.size(), index % this.columnList.size());
    }

    public V at(int rowIndex, int columnIndex) {
        Preconditions.checkElementIndex(rowIndex, this.rowList.size());
        Preconditions.checkElementIndex(columnIndex, this.columnList.size());
        return this.array[rowIndex][columnIndex];
    }

    @Override // com.google.common.collect.AbstractTable
    public Iterator<Table.Cell<R, C, V>> cellIterator() {
        return new AbstractIndexedListIterator<Table.Cell<R, C, V>>(size()) { // from class: com.google.common.collect.ArrayTable.1
            @Override // com.google.common.collect.AbstractIndexedListIterator
            public Table.Cell<R, C, V> get(int index) {
                return ArrayTable.access$000(ArrayTable.this, index);
            }
        };
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Table
    public Map<R, V> column(C columnKey) {
        columnKey.getClass();
        Integer num = this.columnKeyToIndex.get(columnKey);
        return num == null ? Collections.EMPTY_MAP : new Column(num.intValue());
    }

    public ImmutableList<C> columnKeyList() {
        return this.columnList;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public ImmutableSet<C> columnKeySet() {
        return this.columnKeyToIndex.keySet();
    }

    @Override // com.google.common.collect.Table
    public Map<C, Map<R, V>> columnMap() {
        ArrayTable<R, C, V>.ColumnMap columnMap = this.columnMap;
        if (columnMap != null) {
            return columnMap;
        }
        ArrayTable<R, C, V>.ColumnMap columnMap2 = new ColumnMap();
        this.columnMap = columnMap2;
        return columnMap2;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean contains(Object rowKey, Object columnKey) {
        return this.rowKeyToIndex.containsKey(rowKey) && this.columnKeyToIndex.containsKey(columnKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsColumn(Object columnKey) {
        return this.columnKeyToIndex.containsKey(columnKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsRow(Object rowKey) {
        return this.rowKeyToIndex.containsKey(rowKey);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean containsValue(Object value) {
        for (V[] vArr : this.array) {
            for (V v : vArr) {
                if (Objects.equal(value, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean equals(Object obj) {
        return Tables.equalsImpl(this, obj);
    }

    @CanIgnoreReturnValue
    public V erase(Object rowKey, Object columnKey) {
        Integer num = this.rowKeyToIndex.get(rowKey);
        Integer num2 = this.columnKeyToIndex.get(columnKey);
        if (num == null || num2 == null) {
            return null;
        }
        return set(num.intValue(), num2.intValue(), null);
    }

    public void eraseAll() {
        for (V[] vArr : this.array) {
            Arrays.fill(vArr, (Object) null);
        }
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public V get(Object rowKey, Object columnKey) {
        Integer num = this.rowKeyToIndex.get(rowKey);
        Integer num2 = this.columnKeyToIndex.get(columnKey);
        if (num == null || num2 == null) {
            return null;
        }
        return at(num.intValue(), num2.intValue());
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean isEmpty() {
        return this.rowList.isEmpty() || this.columnList.isEmpty();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V put(R rowKey, C columnKey, V value) {
        rowKey.getClass();
        columnKey.getClass();
        Integer num = this.rowKeyToIndex.get(rowKey);
        Preconditions.checkArgument(num != null, "Row %s not in %s", rowKey, this.rowList);
        Integer num2 = this.columnKeyToIndex.get(columnKey);
        Preconditions.checkArgument(num2 != null, "Column %s not in %s", columnKey, this.columnList);
        return set(num.intValue(), num2.intValue(), value);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        super.putAll(table);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public V remove(Object rowKey, Object columnKey) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Table
    public Map<C, V> row(R rowKey) {
        rowKey.getClass();
        Integer num = this.rowKeyToIndex.get(rowKey);
        return num == null ? Collections.EMPTY_MAP : new Row(num.intValue());
    }

    public ImmutableList<R> rowKeyList() {
        return this.rowList;
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public ImmutableSet<R> rowKeySet() {
        return this.rowKeyToIndex.keySet();
    }

    @Override // com.google.common.collect.Table
    public Map<R, Map<C, V>> rowMap() {
        ArrayTable<R, C, V>.RowMap rowMap = this.rowMap;
        if (rowMap != null) {
            return rowMap;
        }
        ArrayTable<R, C, V>.RowMap rowMap2 = new RowMap();
        this.rowMap = rowMap2;
        return rowMap2;
    }

    @CanIgnoreReturnValue
    public V set(int rowIndex, int columnIndex, V value) {
        Preconditions.checkElementIndex(rowIndex, this.rowList.size());
        Preconditions.checkElementIndex(columnIndex, this.columnList.size());
        V[] vArr = this.array[rowIndex];
        V v = vArr[columnIndex];
        vArr[columnIndex] = value;
        return v;
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.columnList.size() * this.rowList.size();
    }

    @GwtIncompatible
    public V[][] toArray(Class<V> cls) {
        V[][] vArr = (V[][]) ((Object[][]) Array.newInstance((Class<?>) cls, this.rowList.size(), this.columnList.size()));
        for (int i = 0; i < this.rowList.size(); i++) {
            V[] vArr2 = this.array[i];
            System.arraycopy(vArr2, 0, vArr[i], 0, vArr2.length);
        }
        return vArr;
    }

    @Override // com.google.common.collect.AbstractTable
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Collection<V> values() {
        return super.values();
    }

    @Override // com.google.common.collect.AbstractTable
    public Iterator<V> valuesIterator() {
        return new AbstractIndexedListIterator<V>(size()) { // from class: com.google.common.collect.ArrayTable.3
            @Override // com.google.common.collect.AbstractIndexedListIterator
            public V get(int i) {
                return (V) ArrayTable.this.getValue(i);
            }
        };
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Table<R, C, ? extends V> table) {
        return table instanceof ArrayTable ? new ArrayTable<>((ArrayTable) table) : new ArrayTable<>(table);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Set columnKeySet() {
        return this.columnKeyToIndex.keySet();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Set rowKeySet() {
        return this.rowKeyToIndex.keySet();
    }

    public ArrayTable(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        ImmutableList<R> immutableListCopyOf = ImmutableList.copyOf(iterable);
        this.rowList = immutableListCopyOf;
        ImmutableList<C> immutableListCopyOf2 = ImmutableList.copyOf(iterable2);
        this.columnList = immutableListCopyOf2;
        Preconditions.checkArgument(immutableListCopyOf.isEmpty() == immutableListCopyOf2.isEmpty());
        this.rowKeyToIndex = Maps.indexMap(immutableListCopyOf);
        this.columnKeyToIndex = Maps.indexMap(immutableListCopyOf2);
        this.array = (V[][]) ((Object[][]) Array.newInstance((Class<?>) Object.class, immutableListCopyOf.size(), immutableListCopyOf2.size()));
        eraseAll();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArrayTable(Table<R, C, ? extends V> table) {
        this(table.rowKeySet(), table.columnKeySet());
        super.putAll(table);
    }
}
