package dagger.internal;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SetFactory<T> implements Factory<Set<T>> {
    public static final Factory<Set<Object>> EMPTY_FACTORY = InstanceFactory.create(Collections.EMPTY_SET);
    public final List<Provider<Collection<T>>> collectionProviders;
    public final List<Provider<T>> individualProviders;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder<T> {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public final List<Provider<Collection<T>>> collectionProviders;
        public final List<Provider<T>> individualProviders;

        public Builder<T> addCollectionProvider(Provider<? extends Collection<? extends T>> collectionProvider) {
            this.collectionProviders.add(collectionProvider);
            return this;
        }

        public Builder<T> addProvider(Provider<? extends T> individualProvider) {
            this.individualProviders.add(individualProvider);
            return this;
        }

        public SetFactory<T> build() {
            return new SetFactory<>(this.individualProviders, this.collectionProviders);
        }

        public Builder(int individualProviderSize, int collectionProviderSize) {
            this.individualProviders = DaggerCollections.presizedList(individualProviderSize);
            this.collectionProviders = DaggerCollections.presizedList(collectionProviderSize);
        }

        @Deprecated
        public Builder<T> addCollectionProvider(javax.inject.Provider<? extends Collection<? extends T>> collectionProvider) {
            this.collectionProviders.add(Providers.asDaggerProvider(collectionProvider));
            return this;
        }

        @Deprecated
        public Builder<T> addProvider(javax.inject.Provider<? extends T> individualProvider) {
            this.individualProviders.add(Providers.asDaggerProvider(individualProvider));
            return this;
        }
    }

    public static <T> Builder<T> builder(int individualProviderSize, int collectionProviderSize) {
        return new Builder<>(individualProviderSize, collectionProviderSize);
    }

    public static <T> Factory<Set<T>> empty() {
        return (Factory<Set<T>>) EMPTY_FACTORY;
    }

    public SetFactory(List<Provider<T>> individualProviders, List<Provider<Collection<T>>> collectionProviders) {
        this.individualProviders = individualProviders;
        this.collectionProviders = collectionProviders;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Set<T> get() {
        int size = this.individualProviders.size();
        ArrayList arrayList = new ArrayList(this.collectionProviders.size());
        int size2 = this.collectionProviders.size();
        for (int i = 0; i < size2; i++) {
            Collection<T> collection = this.collectionProviders.get(i).get();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet hashSetNewHashSetWithExpectedSize = DaggerCollections.newHashSetWithExpectedSize(size);
        int size3 = this.individualProviders.size();
        for (int i2 = 0; i2 < size3; i2++) {
            T t = this.individualProviders.get(i2).get();
            t.getClass();
            hashSetNewHashSetWithExpectedSize.add(t);
        }
        int size4 = arrayList.size();
        for (int i3 = 0; i3 < size4; i3++) {
            for (Object obj : (Collection) arrayList.get(i3)) {
                obj.getClass();
                hashSetNewHashSetWithExpectedSize.add(obj);
            }
        }
        return DesugarCollections.unmodifiableSet(hashSetNewHashSetWithExpectedSize);
    }
}
