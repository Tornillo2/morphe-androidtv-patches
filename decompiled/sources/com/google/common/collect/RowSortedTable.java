package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public interface RowSortedTable<R, C, V> extends Table<R, C, V> {

    /* JADX INFO: renamed from: com.google.common.collect.RowSortedTable$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    @Override // com.google.common.collect.Table
    /* bridge */ /* synthetic */ Set rowKeySet();

    @Override // com.google.common.collect.Table
    SortedSet<R> rowKeySet();

    @Override // com.google.common.collect.Table
    /* bridge */ /* synthetic */ Map rowMap();

    @Override // com.google.common.collect.Table
    SortedMap<R, Map<C, V>> rowMap();
}
