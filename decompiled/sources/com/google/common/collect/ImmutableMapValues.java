package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class ImmutableMapValues<K, V> extends ImmutableCollection<V> {
    public final ImmutableMap<K, V> map;

    /* JADX INFO: renamed from: com.google.common.collect.ImmutableMapValues$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends UnmodifiableIterator<V> {
        public final UnmodifiableIterator<Map.Entry<K, V>> entryItr;

        public AnonymousClass1() {
            this.entryItr = ImmutableMapValues.this.map.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.entryItr.hasNext();
        }

        @Override // java.util.Iterator
        public V next() {
            return this.entryItr.next().getValue();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    @J2ktIncompatible
    public static class SerializedForm<V> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final ImmutableMap<?, V> map;

        public SerializedForm(ImmutableMap<?, V> map) {
            this.map = map;
        }

        public Object readResolve() {
            return this.map.values();
        }
    }

    public ImmutableMapValues(ImmutableMap<K, V> map) {
        this.map = map;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<V> asList() {
        final ImmutableList<Map.Entry<K, V>> immutableListAsList = this.map.entrySet().asList();
        return new ImmutableList<V>(this) { // from class: com.google.common.collect.ImmutableMapValues.2
            public final /* synthetic */ ImmutableMapValues this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.List
            public V get(int i) {
                return (V) ((Map.Entry) immutableListAsList.get(i)).getValue();
            }

            @Override // com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return true;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return immutableListAsList.size();
            }

            @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
            @J2ktIncompatible
            @GwtIncompatible
            public Object writeReplace() {
                return super.writeReplace();
            }
        };
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object object) {
        return object != null && Iterators.contains(new AnonymousClass1(), object);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return true;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<V> iterator() {
        return new AnonymousClass1();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.map.size();
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.map);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public Iterator iterator() {
        return new AnonymousClass1();
    }
}
