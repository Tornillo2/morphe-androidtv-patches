package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public class SingletonImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {
    public final C singleColumnKey;
    public final R singleRowKey;
    public final V singleValue;

    public SingletonImmutableTable(R rowKey, C columnKey, V value) {
        rowKey.getClass();
        this.singleRowKey = rowKey;
        columnKey.getClass();
        this.singleColumnKey = columnKey;
        value.getClass();
        this.singleValue = value;
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return 1;
    }

    @Override // com.google.common.collect.ImmutableTable
    @J2ktIncompatible
    @GwtIncompatible
    public Object writeReplace() {
        return ImmutableTable.SerializedForm.create(this, new int[]{0}, new int[]{0});
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, V> column(C c) {
        c.getClass();
        return containsColumn(c) ? ImmutableMap.of(this.singleRowKey, (Object) this.singleValue) : (ImmutableMap<R, V>) RegularImmutableMap.EMPTY;
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.of(this.singleColumnKey, ImmutableMap.of(this.singleRowKey, (Object) this.singleValue));
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.AbstractTable
    public ImmutableSet<Table.Cell<R, C, V>> createCellSet() {
        return ImmutableSet.of(ImmutableTable.cellOf(this.singleRowKey, this.singleColumnKey, this.singleValue));
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.AbstractTable
    public ImmutableCollection<V> createValues() {
        return ImmutableSet.of(this.singleValue);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.of(this.singleRowKey, ImmutableMap.of(this.singleColumnKey, (Object) this.singleValue));
    }

    public SingletonImmutableTable(Table.Cell<R, C, V> cell) {
        this(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
    }
}
