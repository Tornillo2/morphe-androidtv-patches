package com.google.common.collect;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.Immutable;
import j$.util.Objects;
import java.lang.reflect.Array;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable(containerOf = {"R", "C", ExifInterface.GPS_MEASUREMENT_INTERRUPTED})
@GwtCompatible
public final class DenseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    public final int[] cellColumnIndices;
    public final int[] cellRowIndices;
    public final int[] columnCounts;
    public final ImmutableMap<C, Integer> columnKeyToIndex;
    public final ImmutableMap<C, ImmutableMap<R, V>> columnMap;
    public final int[] rowCounts;
    public final ImmutableMap<R, Integer> rowKeyToIndex;
    public final ImmutableMap<R, ImmutableMap<C, V>> rowMap;
    public final V[][] values;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class Column extends ImmutableArrayMap<R, V> {
        public final int columnIndex;

        public Column(int columnIndex) {
            super(DenseImmutableTable.this.columnCounts[columnIndex]);
            this.columnIndex = columnIndex;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public V getValue(int keyIndex) {
            return DenseImmutableTable.this.values[keyIndex][this.columnIndex];
        }

        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap, com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return new ImmutableMap.SerializedForm(this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ColumnMap extends ImmutableArrayMap<C, ImmutableMap<R, V>> {
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return false;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap, com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return new ImmutableMap.SerializedForm(this);
        }

        public ColumnMap() {
            super(DenseImmutableTable.this.columnCounts.length);
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<R, V> getValue(int keyIndex) {
            return new Column(keyIndex);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class ImmutableArrayMap<K, V> extends ImmutableMap.IteratorBasedImmutableMap<K, V> {
        public final int size;

        public ImmutableArrayMap(int size) {
            this.size = size;
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return isFull() ? keyToIndex().keySet() : new ImmutableMapKeySet(this);
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap
        public UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
            return new AbstractIterator<Map.Entry<K, V>>() { // from class: com.google.common.collect.DenseImmutableTable.ImmutableArrayMap.1
                public int index = -1;
                public final int maxIndex;

                {
                    this.maxIndex = ImmutableArrayMap.this.keyToIndex().size();
                }

                @Override // com.google.common.collect.AbstractIterator
                public Map.Entry<K, V> computeNext() {
                    int i = this.index;
                    while (true) {
                        this.index = i + 1;
                        int i2 = this.index;
                        if (i2 >= this.maxIndex) {
                            endOfData();
                            return null;
                        }
                        Object value = ImmutableArrayMap.this.getValue(i2);
                        if (value != null) {
                            return new ImmutableEntry(ImmutableArrayMap.this.getKey(this.index), value);
                        }
                        i = this.index;
                    }
                }
            };
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public V get(Object key) {
            Integer num = keyToIndex().get(key);
            if (num == null) {
                return null;
            }
            return getValue(num.intValue());
        }

        public K getKey(int index) {
            return keyToIndex().keySet().asList().get(index);
        }

        public abstract V getValue(int keyIndex);

        public final boolean isFull() {
            return this.size == keyToIndex().size();
        }

        public abstract ImmutableMap<K, Integer> keyToIndex();

        @Override // java.util.Map
        public int size() {
            return this.size;
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return new ImmutableMap.SerializedForm(this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class Row extends ImmutableArrayMap<C, V> {
        public final int rowIndex;

        public Row(int rowIndex) {
            super(DenseImmutableTable.this.rowCounts[rowIndex]);
            this.rowIndex = rowIndex;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public V getValue(int keyIndex) {
            return DenseImmutableTable.this.values[this.rowIndex][keyIndex];
        }

        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap, com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return new ImmutableMap.SerializedForm(this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class RowMap extends ImmutableArrayMap<R, ImmutableMap<C, V>> {
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return false;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap, com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        @J2ktIncompatible
        @GwtIncompatible
        public Object writeReplace() {
            return new ImmutableMap.SerializedForm(this);
        }

        public RowMap() {
            super(DenseImmutableTable.this.rowCounts.length);
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<C, V> getValue(int keyIndex) {
            return new Row(keyIndex);
        }
    }

    public DenseImmutableTable(ImmutableList<Table.Cell<R, C, V>> immutableList, ImmutableSet<R> immutableSet, ImmutableSet<C> immutableSet2) {
        this.values = (V[][]) ((Object[][]) Array.newInstance((Class<?>) Object.class, immutableSet.size(), immutableSet2.size()));
        ImmutableMap<R, Integer> immutableMapIndexMap = Maps.indexMap(immutableSet);
        this.rowKeyToIndex = immutableMapIndexMap;
        ImmutableMap<C, Integer> immutableMapIndexMap2 = Maps.indexMap(immutableSet2);
        this.columnKeyToIndex = immutableMapIndexMap2;
        this.rowCounts = new int[immutableMapIndexMap.size()];
        this.columnCounts = new int[immutableMapIndexMap2.size()];
        int[] iArr = new int[immutableList.size()];
        int[] iArr2 = new int[immutableList.size()];
        for (int i = 0; i < immutableList.size(); i++) {
            Table.Cell<R, C, V> cell = immutableList.get(i);
            R rowKey = cell.getRowKey();
            C columnKey = cell.getColumnKey();
            Integer num = this.rowKeyToIndex.get(rowKey);
            Objects.requireNonNull(num);
            int iIntValue = num.intValue();
            Integer num2 = this.columnKeyToIndex.get(columnKey);
            Objects.requireNonNull(num2);
            int iIntValue2 = num2.intValue();
            checkNoDuplicate(rowKey, columnKey, this.values[iIntValue][iIntValue2], cell.getValue());
            this.values[iIntValue][iIntValue2] = cell.getValue();
            int[] iArr3 = this.rowCounts;
            iArr3[iIntValue] = iArr3[iIntValue] + 1;
            int[] iArr4 = this.columnCounts;
            iArr4[iIntValue2] = iArr4[iIntValue2] + 1;
            iArr[i] = iIntValue;
            iArr2[i] = iIntValue2;
        }
        this.cellRowIndices = iArr;
        this.cellColumnIndices = iArr2;
        this.rowMap = new RowMap();
        this.columnMap = new ColumnMap();
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.copyOf((Map) this.columnMap);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public V get(Object rowKey, Object columnKey) {
        Integer num = this.rowKeyToIndex.get(rowKey);
        Integer num2 = this.columnKeyToIndex.get(columnKey);
        if (num == null || num2 == null) {
            return null;
        }
        return this.values[num.intValue()][num2.intValue()];
    }

    @Override // com.google.common.collect.RegularImmutableTable
    public Table.Cell<R, C, V> getCell(int index) {
        int i = this.cellRowIndices[index];
        int i2 = this.cellColumnIndices[index];
        R r = rowKeySet().asList().get(i);
        C c = columnKeySet().asList().get(i2);
        V v = this.values[i][i2];
        Objects.requireNonNull(v);
        return ImmutableTable.cellOf(r, c, v);
    }

    @Override // com.google.common.collect.RegularImmutableTable
    public V getValue(int index) {
        V v = this.values[this.cellRowIndices[index]][this.cellColumnIndices[index]];
        Objects.requireNonNull(v);
        return v;
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.copyOf((Map) this.rowMap);
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.cellRowIndices.length;
    }

    @Override // com.google.common.collect.RegularImmutableTable, com.google.common.collect.ImmutableTable
    @J2ktIncompatible
    @GwtIncompatible
    public Object writeReplace() {
        return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, this.cellColumnIndices);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public Map columnMap() {
        return ImmutableMap.copyOf((Map) this.columnMap);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public Map rowMap() {
        return ImmutableMap.copyOf((Map) this.rowMap);
    }
}
