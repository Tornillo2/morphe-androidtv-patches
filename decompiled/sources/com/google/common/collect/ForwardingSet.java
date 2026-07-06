package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ForwardingSet<E> extends ForwardingCollection<E> implements Set<E> {
    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract Set<E> delegate();

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object object) {
        return object == this || delegate().equals(object);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return delegate().hashCode();
    }

    public boolean standardEquals(Object object) {
        return Sets.equalsImpl(this, object);
    }

    public int standardHashCode() {
        return Sets.hashCodeImpl(this);
    }

    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardRemoveAll(Collection<?> collection) {
        collection.getClass();
        return Sets.removeAllImpl(this, collection);
    }
}
