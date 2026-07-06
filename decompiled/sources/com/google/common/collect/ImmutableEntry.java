package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(serializable = true)
public class ImmutableEntry<K, V> extends AbstractMapEntry<K, V> implements Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    @ParametricNullness
    public final K key;

    @ParametricNullness
    public final V value;

    public ImmutableEntry(@ParametricNullness K key, @ParametricNullness V value) {
        this.key = key;
        this.value = value;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    @ParametricNullness
    public final K getKey() {
        return this.key;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    @ParametricNullness
    public final V getValue() {
        return this.value;
    }

    @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
    @ParametricNullness
    public final V setValue(@ParametricNullness V value) {
        throw new UnsupportedOperationException();
    }
}
