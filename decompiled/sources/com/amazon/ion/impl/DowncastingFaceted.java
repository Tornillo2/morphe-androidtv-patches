package com.amazon.ion.impl;

import com.amazon.ion.facet.Faceted;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class DowncastingFaceted implements Faceted {
    @Override // com.amazon.ion.facet.Faceted
    public final <T> T asFacet(Class<T> cls) {
        if (cls.isInstance(this)) {
            return cls.cast(this);
        }
        return null;
    }
}
