package j$.util.stream;

import j$.util.Collection;
import j$.util.Spliterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class h2 implements d2 {
    public final Collection a;

    @Override // j$.util.stream.d2
    public final /* synthetic */ d2 i(long j, long j2, IntFunction intFunction) {
        return w3.V(this, j, j2, intFunction);
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ int o() {
        return 0;
    }

    @Override // j$.util.stream.d2
    public final d2 a(int i) {
        throw new IndexOutOfBoundsException();
    }

    public h2(Collection collection) {
        this.a = collection;
    }

    @Override // j$.util.stream.d2
    public final Spliterator spliterator() {
        return Collection.EL.stream(this.a).spliterator();
    }

    @Override // j$.util.stream.d2
    public final void k(Object[] objArr, int i) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
    }

    @Override // j$.util.stream.d2
    public final Object[] m(IntFunction intFunction) {
        java.util.Collection collection = this.a;
        return collection.toArray((Object[]) intFunction.apply(collection.size()));
    }

    @Override // j$.util.stream.d2
    public final long count() {
        return this.a.size();
    }

    @Override // j$.util.stream.d2
    public final void forEach(Consumer consumer) {
        Collection.EL.a(this.a, consumer);
    }

    public final String toString() {
        return String.format("CollectionNode[%d][%s]", Integer.valueOf(this.a.size()), this.a);
    }
}
