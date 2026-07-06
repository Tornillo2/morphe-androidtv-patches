package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AggregateFuture;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public abstract class CollectionFuture<V, C> extends AggregateFuture<V, C> {

    @LazyInit
    public List<Present<V>> values;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ListFuture<V> extends CollectionFuture<V, List<V>> {
        public ListFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
            super(futures, allMustSucceed);
            init();
        }

        @Override // com.google.common.util.concurrent.CollectionFuture
        public List<V> combine(List<Present<V>> values) {
            ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(values.size());
            Iterator<Present<V>> it = values.iterator();
            while (it.hasNext()) {
                Present<V> next = it.next();
                arrayListNewArrayListWithCapacity.add(next != null ? next.value : null);
            }
            return DesugarCollections.unmodifiableList(arrayListNewArrayListWithCapacity);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Present<V> {

        @ParametricNullness
        public final V value;

        public Present(@ParametricNullness V value) {
            this.value = value;
        }
    }

    public CollectionFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
        super(futures, allMustSucceed, true);
        List<Present<V>> listNewArrayListWithCapacity = futures.isEmpty() ? Collections.EMPTY_LIST : Lists.newArrayListWithCapacity(futures.size());
        for (int i = 0; i < futures.size(); i++) {
            listNewArrayListWithCapacity.add(null);
        }
        this.values = listNewArrayListWithCapacity;
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public final void collectOneValue(int index, @ParametricNullness V returnValue) {
        List<Present<V>> list = this.values;
        if (list != null) {
            list.set(index, new Present<>(returnValue));
        }
    }

    public abstract C combine(List<Present<V>> values);

    @Override // com.google.common.util.concurrent.AggregateFuture
    public final void handleAllCompleted() {
        List<Present<V>> list = this.values;
        if (list != null) {
            set(combine(list));
        }
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public void releaseResources(AggregateFuture.ReleaseResourcesReason reason) {
        super.releaseResources(reason);
        this.values = null;
    }
}
