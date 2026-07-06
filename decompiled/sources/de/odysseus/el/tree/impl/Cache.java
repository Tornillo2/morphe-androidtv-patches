package de.odysseus.el.tree.impl;

import de.odysseus.el.tree.Tree;
import de.odysseus.el.tree.TreeCache;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Cache implements TreeCache {
    public final int capacity;
    public final ConcurrentMap<String, Tree> map;
    public final ConcurrentLinkedQueue<String> queue;
    public final AtomicInteger size;

    public Cache(int i) {
        this(i, 16);
    }

    @Override // de.odysseus.el.tree.TreeCache
    public Tree get(String str) {
        return this.map.get(str);
    }

    @Override // de.odysseus.el.tree.TreeCache
    public void put(String str, Tree tree) {
        if (this.map.putIfAbsent(str, tree) == null) {
            this.queue.offer(str);
            if (this.size.incrementAndGet() > this.capacity) {
                this.size.decrementAndGet();
                this.map.remove(this.queue.poll());
            }
        }
    }

    public int size() {
        return this.size.get();
    }

    public Cache(int i, int i2) {
        this.map = new ConcurrentHashMap(16, 0.75f, i2);
        this.queue = new ConcurrentLinkedQueue<>();
        this.size = new AtomicInteger();
        this.capacity = i;
    }
}
