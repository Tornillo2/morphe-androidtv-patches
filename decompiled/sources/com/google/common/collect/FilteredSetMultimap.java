package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public interface FilteredSetMultimap<K, V> extends FilteredMultimap<K, V>, SetMultimap<K, V> {

    /* JADX INFO: renamed from: com.google.common.collect.FilteredSetMultimap$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    @Override // com.google.common.collect.FilteredMultimap
    /* bridge */ /* synthetic */ Multimap unfiltered();

    @Override // com.google.common.collect.FilteredMultimap
    SetMultimap<K, V> unfiltered();
}
