package androidx.media3.common.util;

import androidx.annotation.GuardedBy;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class CopyOnWriteMultiset<E> implements Iterable<E> {
    public final Object lock = new Object();

    @GuardedBy("lock")
    public final Map<E, Integer> elementCounts = new HashMap();

    @GuardedBy("lock")
    public Set<E> elementSet = Collections.EMPTY_SET;

    @GuardedBy("lock")
    public List<E> elements = Collections.EMPTY_LIST;

    public void add(E e) {
        synchronized (this.lock) {
            try {
                ArrayList arrayList = new ArrayList(this.elements);
                arrayList.add(e);
                this.elements = DesugarCollections.unmodifiableList(arrayList);
                Integer num = this.elementCounts.get(e);
                if (num == null) {
                    HashSet hashSet = new HashSet(this.elementSet);
                    hashSet.add(e);
                    this.elementSet = DesugarCollections.unmodifiableSet(hashSet);
                }
                this.elementCounts.put(e, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int count(E e) {
        int iIntValue;
        synchronized (this.lock) {
            try {
                iIntValue = this.elementCounts.containsKey(e) ? this.elementCounts.get(e).intValue() : 0;
            } catch (Throwable th) {
                throw th;
            }
        }
        return iIntValue;
    }

    public Set<E> elementSet() {
        Set<E> set;
        synchronized (this.lock) {
            set = this.elementSet;
        }
        return set;
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        Iterator<E> it;
        synchronized (this.lock) {
            it = this.elements.iterator();
        }
        return it;
    }

    public void remove(E e) {
        synchronized (this.lock) {
            try {
                Integer num = this.elementCounts.get(e);
                if (num == null) {
                    return;
                }
                ArrayList arrayList = new ArrayList(this.elements);
                arrayList.remove(e);
                this.elements = DesugarCollections.unmodifiableList(arrayList);
                if (num.intValue() == 1) {
                    this.elementCounts.remove(e);
                    HashSet hashSet = new HashSet(this.elementSet);
                    hashSet.remove(e);
                    this.elementSet = DesugarCollections.unmodifiableSet(hashSet);
                } else {
                    this.elementCounts.put(e, Integer.valueOf(num.intValue() - 1));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
