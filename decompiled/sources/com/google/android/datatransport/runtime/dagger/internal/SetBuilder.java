package com.google.android.datatransport.runtime.dagger.internal;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SetBuilder<T> {
    public static final String SET_CONTRIBUTIONS_CANNOT_BE_NULL = "Set contributions cannot be null";
    public final List<T> contributions;

    public SetBuilder(int i) {
        this.contributions = new ArrayList(i);
    }

    public static <T> SetBuilder<T> newSetBuilder(int i) {
        return new SetBuilder<>(i);
    }

    public SetBuilder<T> add(T t) {
        List<T> list = this.contributions;
        Preconditions.checkNotNull(t, "Set contributions cannot be null");
        list.add(t);
        return this;
    }

    public SetBuilder<T> addAll(Collection<? extends T> collection) {
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "Set contributions cannot be null");
        }
        this.contributions.addAll(collection);
        return this;
    }

    public Set<T> build() {
        int size = this.contributions.size();
        return size != 0 ? size != 1 ? DesugarCollections.unmodifiableSet(new HashSet(this.contributions)) : Collections.singleton(this.contributions.get(0)) : Collections.EMPTY_SET;
    }
}
