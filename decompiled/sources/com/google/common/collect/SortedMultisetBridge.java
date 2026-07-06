package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.Set;
import java.util.SortedSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public interface SortedMultisetBridge<E> extends Multiset<E> {

    /* JADX INFO: renamed from: com.google.common.collect.SortedMultisetBridge$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    @Override // com.google.common.collect.Multiset
    /* bridge */ /* synthetic */ Set elementSet();

    @Override // com.google.common.collect.Multiset
    SortedSet<E> elementSet();
}
