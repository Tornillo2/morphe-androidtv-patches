package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true, serializable = true)
public final class HashMultiset<E> extends AbstractMapBasedMultiset<E> {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    public HashMultiset(int distinctElements) {
        super(distinctElements);
    }

    public static <E> HashMultiset<E> create() {
        return new HashMultiset<>(3);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean contains(Object element) {
        return super.contains(element);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.AbstractMapBasedMultiset
    public ObjectCountHashMap<E> newBackingMap(int distinctElements) {
        return new ObjectCountHashMap<>(distinctElements);
    }

    public static <E> HashMultiset<E> create(int distinctElements) {
        return new HashMultiset<>(distinctElements);
    }

    public static <E> HashMultiset<E> create(Iterable<? extends E> elements) {
        HashMultiset<E> hashMultiset = new HashMultiset<>(Multisets.inferDistinctElements(elements));
        Iterables.addAll(hashMultiset, elements);
        return hashMultiset;
    }
}
