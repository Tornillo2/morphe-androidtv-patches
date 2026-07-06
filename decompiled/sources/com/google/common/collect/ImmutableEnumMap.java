package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true, serializable = true)
public final class ImmutableEnumMap<K extends Enum<K>, V> extends ImmutableMap.IteratorBasedImmutableMap<K, V> {
    public final transient EnumMap<K, V> delegate;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    public static class EnumSerializedForm<K extends Enum<K>, V> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final EnumMap<K, V> delegate;

        public EnumSerializedForm(EnumMap<K, V> delegate) {
            this.delegate = delegate;
        }

        public Object readResolve() {
            return new ImmutableEnumMap(this.delegate);
        }
    }

    public static <K extends Enum<K>, V> ImmutableMap<K, V> asImmutable(EnumMap<K, V> enumMap) {
        int size = enumMap.size();
        if (size == 0) {
            return (ImmutableMap<K, V>) RegularImmutableMap.EMPTY;
        }
        if (size != 1) {
            return new ImmutableEnumMap(enumMap);
        }
        Map.Entry entry = (Map.Entry) Iterables.getOnlyElement(enumMap.entrySet());
        return ImmutableMap.of((Enum) entry.getKey(), entry.getValue());
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use EnumSerializedForm");
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public boolean containsKey(Object key) {
        return this.delegate.containsKey(key);
    }

    @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap
    public UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
        return new Maps.AnonymousClass8(this.delegate.entrySet().iterator());
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof ImmutableEnumMap) {
            object = ((ImmutableEnumMap) object).delegate;
        }
        return this.delegate.equals(object);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object key) {
        return this.delegate.get(key);
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableMap
    public UnmodifiableIterator<K> keyIterator() {
        return Iterators.unmodifiableIterator(this.delegate.keySet().iterator());
    }

    @Override // java.util.Map
    public int size() {
        return this.delegate.size();
    }

    @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
    @J2ktIncompatible
    public Object writeReplace() {
        return new EnumSerializedForm(this.delegate);
    }

    public ImmutableEnumMap(EnumMap<K, V> delegate) {
        this.delegate = delegate;
        Preconditions.checkArgument(!delegate.isEmpty());
    }
}
