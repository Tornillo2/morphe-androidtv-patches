package com.google.common.collect;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use ImmutableMultimap, HashMultimap, or another implementation")
@GwtCompatible
public interface Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    void clear();

    boolean containsEntry(@CompatibleWith("K") Object key, @CompatibleWith(ExifInterface.GPS_MEASUREMENT_INTERRUPTED) Object value);

    boolean containsKey(@CompatibleWith("K") Object key);

    boolean containsValue(@CompatibleWith(ExifInterface.GPS_MEASUREMENT_INTERRUPTED) Object value);

    Collection<Map.Entry<K, V>> entries();

    boolean equals(Object obj);

    Collection<V> get(@ParametricNullness K key);

    int hashCode();

    boolean isEmpty();

    Set<K> keySet();

    Multiset<K> keys();

    @CanIgnoreReturnValue
    boolean put(@ParametricNullness K key, @ParametricNullness V value);

    @CanIgnoreReturnValue
    boolean putAll(Multimap<? extends K, ? extends V> multimap);

    @CanIgnoreReturnValue
    boolean putAll(@ParametricNullness K key, Iterable<? extends V> values);

    @CanIgnoreReturnValue
    boolean remove(@CompatibleWith("K") Object key, @CompatibleWith(ExifInterface.GPS_MEASUREMENT_INTERRUPTED) Object value);

    @CanIgnoreReturnValue
    Collection<V> removeAll(@CompatibleWith("K") Object key);

    @CanIgnoreReturnValue
    Collection<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values);

    int size();

    Collection<V> values();
}
