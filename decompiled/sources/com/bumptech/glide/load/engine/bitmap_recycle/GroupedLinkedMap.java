package com.bumptech.glide.load.engine.bitmap_recycle;

import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class GroupedLinkedMap<K extends Poolable, V> {
    public final LinkedEntry<K, V> head = new LinkedEntry<>(null);
    public final Map<K, LinkedEntry<K, V>> keyToEntry = new HashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LinkedEntry<K, V> {
        public final K key;
        public LinkedEntry<K, V> next;
        public LinkedEntry<K, V> prev;
        public List<V> values;

        public LinkedEntry() {
            this(null);
        }

        public void add(V v) {
            if (this.values == null) {
                this.values = new ArrayList();
            }
            this.values.add(v);
        }

        @Nullable
        public V removeLast() {
            int size = size();
            if (size > 0) {
                return this.values.remove(size - 1);
            }
            return null;
        }

        public int size() {
            List<V> list = this.values;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public LinkedEntry(K k) {
            this.prev = this;
            this.next = this;
            this.key = k;
        }
    }

    public static <K, V> void removeEntry(LinkedEntry<K, V> linkedEntry) {
        LinkedEntry<K, V> linkedEntry2 = linkedEntry.prev;
        linkedEntry2.next = linkedEntry.next;
        linkedEntry.next.prev = linkedEntry2;
    }

    public static <K, V> void updateEntry(LinkedEntry<K, V> linkedEntry) {
        linkedEntry.next.prev = linkedEntry;
        linkedEntry.prev.next = linkedEntry;
    }

    @Nullable
    public V get(K k) {
        LinkedEntry<K, V> linkedEntry = this.keyToEntry.get(k);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry<>(k);
            this.keyToEntry.put(k, linkedEntry);
        } else {
            k.offer();
        }
        makeHead(linkedEntry);
        return linkedEntry.removeLast();
    }

    public final void makeHead(LinkedEntry<K, V> linkedEntry) {
        removeEntry(linkedEntry);
        LinkedEntry<K, V> linkedEntry2 = this.head;
        linkedEntry.prev = linkedEntry2;
        LinkedEntry<K, V> linkedEntry3 = linkedEntry2.next;
        linkedEntry.next = linkedEntry3;
        linkedEntry3.prev = linkedEntry;
        linkedEntry.prev.next = linkedEntry;
    }

    public final void makeTail(LinkedEntry<K, V> linkedEntry) {
        removeEntry(linkedEntry);
        LinkedEntry<K, V> linkedEntry2 = this.head;
        linkedEntry.prev = linkedEntry2.prev;
        linkedEntry.next = linkedEntry2;
        linkedEntry2.prev = linkedEntry;
        linkedEntry.prev.next = linkedEntry;
    }

    public void put(K k, V v) {
        LinkedEntry<K, V> linkedEntry = this.keyToEntry.get(k);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry<>(k);
            makeTail(linkedEntry);
            this.keyToEntry.put(k, linkedEntry);
        } else {
            k.offer();
        }
        linkedEntry.add(v);
    }

    @Nullable
    public V removeLast() {
        for (LinkedEntry linkedEntry = this.head.prev; !linkedEntry.equals(this.head); linkedEntry = linkedEntry.prev) {
            V v = (V) linkedEntry.removeLast();
            if (v != null) {
                return v;
            }
            removeEntry(linkedEntry);
            this.keyToEntry.remove(linkedEntry.key);
            ((Poolable) linkedEntry.key).offer();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        LinkedEntry linkedEntry = this.head.next;
        boolean z = false;
        while (!linkedEntry.equals(this.head)) {
            sb.append('{');
            sb.append(linkedEntry.key);
            sb.append(':');
            sb.append(linkedEntry.size());
            sb.append("}, ");
            linkedEntry = linkedEntry.next;
            z = true;
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }
}
