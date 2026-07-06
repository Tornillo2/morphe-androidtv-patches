package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public class EmptyImmutableSetMultimap extends ImmutableSetMultimap<Object, Object> {
    public static final EmptyImmutableSetMultimap INSTANCE = new EmptyImmutableSetMultimap();

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    public EmptyImmutableSetMultimap() {
        super(RegularImmutableMap.EMPTY, 0, null);
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public ImmutableMap<Object, Collection<Object>> asMap() {
        return this.map;
    }
}
