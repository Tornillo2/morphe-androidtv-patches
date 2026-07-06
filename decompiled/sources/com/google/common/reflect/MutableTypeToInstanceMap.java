package com.google.common.reflect;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ForwardingMapEntry;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Iterators;
import com.google.common.reflect.TypeToken;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MutableTypeToInstanceMap<B> extends ForwardingMap<TypeToken<? extends B>, B> implements TypeToInstanceMap<B> {
    public final Map<TypeToken<? extends B>, B> backingMap = new HashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnmodifiableEntry<K, V> extends ForwardingMapEntry<K, V> {
        public final Map.Entry<K, V> delegate;

        /* JADX INFO: renamed from: com.google.common.reflect.MutableTypeToInstanceMap$UnmodifiableEntry$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 extends ForwardingSet<Map.Entry<K, V>> {
            public final /* synthetic */ Set val$entries;

            public AnonymousClass1(final Set val$entries) {
                this.val$entries = val$entries;
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, V>> iterator() {
                return UnmodifiableEntry.transformEntries(super.iterator());
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
            public Object[] toArray() {
                return standardToArray();
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] tArr) {
                return (T[]) standardToArray(tArr);
            }

            @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
            public Set<Map.Entry<K, V>> delegate() {
                return this.val$entries;
            }
        }

        /* JADX INFO: renamed from: $r8$lambda$PXRRP_NYxQ7IKAyO13H8YX2p-q0, reason: not valid java name */
        public static /* synthetic */ UnmodifiableEntry m556$r8$lambda$PXRRP_NYxQ7IKAyO13H8YX2pq0(Map.Entry entry) {
            return new UnmodifiableEntry(entry);
        }

        public UnmodifiableEntry(Map.Entry<K, V> delegate) {
            delegate.getClass();
            this.delegate = delegate;
        }

        public static <K, V> Set<Map.Entry<K, V>> transformEntries(Set<Map.Entry<K, V>> entries) {
            return new AnonymousClass1(entries);
        }

        @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
        public Object delegate() {
            return this.delegate;
        }

        @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V setValue(@ParametricNullness V value) {
            throw new UnsupportedOperationException();
        }

        public static <K, V> Iterator<Map.Entry<K, V>> transformEntries(Iterator<Map.Entry<K, V>> entries) {
            return new Iterators.AnonymousClass6(entries, new MutableTypeToInstanceMap$UnmodifiableEntry$$ExternalSyntheticLambda0());
        }

        @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
        public Map.Entry<K, V> delegate() {
            return this.delegate;
        }
    }

    private <T extends B> T trustedGet(TypeToken<T> typeToken) {
        return this.backingMap.get(typeToken);
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Object delegate() {
        return this.backingMap;
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<Map.Entry<TypeToken<? extends B>, B>> entrySet() {
        return new UnmodifiableEntry.AnonymousClass1(super.entrySet());
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    public <T extends B> T getInstance(TypeToken<T> typeToken) {
        typeToken.rejectTypeVariables();
        return this.backingMap.get(typeToken);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public /* bridge */ /* synthetic */ Object put(Object key, @ParametricNullness Object value) {
        put((TypeToken<? extends Object>) key, value);
        throw null;
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void putAll(Map<? extends TypeToken<? extends B>, ? extends B> map) {
        throw new UnsupportedOperationException("Please use putInstance() instead.");
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(TypeToken<T> typeToken, @ParametricNullness T t) {
        typeToken.rejectTypeVariables();
        return this.backingMap.put(typeToken, t);
    }

    public final <T extends B> T trustedPut(TypeToken<T> typeToken, @ParametricNullness T t) {
        return this.backingMap.put(typeToken, t);
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Map<TypeToken<? extends B>, B> delegate() {
        return this.backingMap;
    }

    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    @CanIgnoreReturnValue
    public B put(TypeToken<? extends B> key, @ParametricNullness B value) {
        throw new UnsupportedOperationException("Please use putInstance() instead.");
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    public <T extends B> T getInstance(Class<T> cls) {
        return this.backingMap.get(new TypeToken.SimpleTypeToken(cls));
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(Class<T> cls, @ParametricNullness T t) {
        return this.backingMap.put(new TypeToken.SimpleTypeToken(cls), t);
    }
}
