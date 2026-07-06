package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.cache.LocalCache;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public interface ReferenceEntry<K, V> {
    long getAccessTime();

    int getHash();

    K getKey();

    ReferenceEntry<K, V> getNext();

    ReferenceEntry<K, V> getNextInAccessQueue();

    ReferenceEntry<K, V> getNextInWriteQueue();

    ReferenceEntry<K, V> getPreviousInAccessQueue();

    ReferenceEntry<K, V> getPreviousInWriteQueue();

    LocalCache.ValueReference<K, V> getValueReference();

    long getWriteTime();

    void setAccessTime(long time);

    void setNextInAccessQueue(ReferenceEntry<K, V> next);

    void setNextInWriteQueue(ReferenceEntry<K, V> next);

    void setPreviousInAccessQueue(ReferenceEntry<K, V> previous);

    void setPreviousInWriteQueue(ReferenceEntry<K, V> previous);

    void setValueReference(LocalCache.ValueReference<K, V> valueReference);

    void setWriteTime(long time);
}
