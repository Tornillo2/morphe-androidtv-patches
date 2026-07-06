package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ForwardingTable<R, C, V> extends ForwardingObject implements Table<R, C, V> {
    @Override // com.google.common.collect.Table
    public Set<Table.Cell<R, C, V>> cellSet() {
        return delegate().cellSet();
    }

    @Override // com.google.common.collect.Table
    public void clear() {
        delegate().clear();
    }

    @Override // com.google.common.collect.Table
    public Map<R, V> column(@ParametricNullness C columnKey) {
        return delegate().column(columnKey);
    }

    @Override // com.google.common.collect.Table
    public Set<C> columnKeySet() {
        return delegate().columnKeySet();
    }

    @Override // com.google.common.collect.Table
    public Map<C, Map<R, V>> columnMap() {
        return delegate().columnMap();
    }

    @Override // com.google.common.collect.Table
    public boolean contains(Object rowKey, Object columnKey) {
        return delegate().contains(rowKey, columnKey);
    }

    @Override // com.google.common.collect.Table
    public boolean containsColumn(Object columnKey) {
        return delegate().containsColumn(columnKey);
    }

    @Override // com.google.common.collect.Table
    public boolean containsRow(Object rowKey) {
        return delegate().containsRow(rowKey);
    }

    @Override // com.google.common.collect.Table
    public boolean containsValue(Object value) {
        return delegate().containsValue(value);
    }

    @Override // com.google.common.collect.ForwardingObject
    public abstract Table<R, C, V> delegate();

    @Override // com.google.common.collect.Table
    public boolean equals(Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // com.google.common.collect.Table
    public V get(Object rowKey, Object columnKey) {
        return delegate().get(rowKey, columnKey);
    }

    @Override // com.google.common.collect.Table
    public int hashCode() {
        return delegate().hashCode();
    }

    @Override // com.google.common.collect.Table
    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override // com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V put(@ParametricNullness R rowKey, @ParametricNullness C columnKey, @ParametricNullness V value) {
        return delegate().put(rowKey, columnKey, value);
    }

    @Override // com.google.common.collect.Table
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        delegate().putAll(table);
    }

    @Override // com.google.common.collect.Table
    @CanIgnoreReturnValue
    public V remove(Object rowKey, Object columnKey) {
        return delegate().remove(rowKey, columnKey);
    }

    @Override // com.google.common.collect.Table
    public Map<C, V> row(@ParametricNullness R rowKey) {
        return delegate().row(rowKey);
    }

    @Override // com.google.common.collect.Table
    public Set<R> rowKeySet() {
        return delegate().rowKeySet();
    }

    @Override // com.google.common.collect.Table
    public Map<R, Map<C, V>> rowMap() {
        return delegate().rowMap();
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return delegate().size();
    }

    @Override // com.google.common.collect.Table
    public Collection<V> values() {
        return delegate().values();
    }
}
