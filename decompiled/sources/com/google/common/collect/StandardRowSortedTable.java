package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public class StandardRowSortedTable<R, C, V> extends StandardTable<R, C, V> implements RowSortedTable<R, C, V> {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class RowSortedMap extends StandardTable<R, C, V>.RowMap implements SortedMap<R, Map<C, V>> {
        public RowSortedMap() {
            super();
        }

        @Override // java.util.SortedMap
        public Comparator<? super R> comparator() {
            return ((SortedMap) StandardRowSortedTable.this.backingMap).comparator();
        }

        @Override // java.util.SortedMap
        public R firstKey() {
            return (R) ((SortedMap) StandardRowSortedTable.this.backingMap).firstKey();
        }

        @Override // java.util.SortedMap
        public SortedMap<R, Map<C, V>> headMap(R toKey) {
            toKey.getClass();
            return new StandardRowSortedTable((Map) ((SortedMap) StandardRowSortedTable.this.backingMap).headMap(toKey), (Supplier) StandardRowSortedTable.this.factory).rowMap();
        }

        @Override // java.util.SortedMap
        public R lastKey() {
            return (R) ((SortedMap) StandardRowSortedTable.this.backingMap).lastKey();
        }

        @Override // java.util.SortedMap
        public SortedMap<R, Map<C, V>> subMap(R fromKey, R toKey) {
            fromKey.getClass();
            toKey.getClass();
            return new StandardRowSortedTable((Map) ((SortedMap) StandardRowSortedTable.this.backingMap).subMap(fromKey, toKey), (Supplier) StandardRowSortedTable.this.factory).rowMap();
        }

        @Override // java.util.SortedMap
        public SortedMap<R, Map<C, V>> tailMap(R fromKey) {
            fromKey.getClass();
            return new StandardRowSortedTable((Map) ((SortedMap) StandardRowSortedTable.this.backingMap).tailMap(fromKey), (Supplier) StandardRowSortedTable.this.factory).rowMap();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public SortedSet<R> createKeySet() {
            return new Maps.SortedKeySet((Map) this);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public SortedSet<R> keySet() {
            return (SortedSet) super.keySet();
        }
    }

    public StandardRowSortedTable(SortedMap<R, Map<C, V>> backingMap, Supplier<? extends Map<C, V>> factory) {
        super(backingMap, factory);
    }

    public static SortedMap access$100(StandardRowSortedTable standardRowSortedTable) {
        return (SortedMap) standardRowSortedTable.backingMap;
    }

    public final SortedMap<R, Map<C, V>> sortedBackingMap() {
        return (SortedMap) this.backingMap;
    }

    @Override // com.google.common.collect.StandardTable
    public SortedMap<R, Map<C, V>> createRowMap() {
        return new RowSortedMap();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public SortedSet<R> rowKeySet() {
        return (SortedSet) rowMap().keySet();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.Table
    public SortedMap<R, Map<C, V>> rowMap() {
        return (SortedMap) super.rowMap();
    }
}
