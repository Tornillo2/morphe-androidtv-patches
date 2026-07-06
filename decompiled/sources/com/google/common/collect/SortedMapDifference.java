package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.MapDifference;
import java.util.Map;
import java.util.SortedMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public interface SortedMapDifference<K, V> extends MapDifference<K, V> {

    /* JADX INFO: renamed from: com.google.common.collect.SortedMapDifference$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    @Override // com.google.common.collect.MapDifference
    /* bridge */ /* synthetic */ Map entriesDiffering();

    @Override // com.google.common.collect.MapDifference
    SortedMap<K, MapDifference.ValueDifference<V>> entriesDiffering();

    @Override // com.google.common.collect.MapDifference
    /* bridge */ /* synthetic */ Map entriesInCommon();

    @Override // com.google.common.collect.MapDifference
    SortedMap<K, V> entriesInCommon();

    @Override // com.google.common.collect.MapDifference
    /* bridge */ /* synthetic */ Map entriesOnlyOnLeft();

    @Override // com.google.common.collect.MapDifference
    SortedMap<K, V> entriesOnlyOnLeft();

    @Override // com.google.common.collect.MapDifference
    /* bridge */ /* synthetic */ Map entriesOnlyOnRight();

    @Override // com.google.common.collect.MapDifference
    SortedMap<K, V> entriesOnlyOnRight();
}
