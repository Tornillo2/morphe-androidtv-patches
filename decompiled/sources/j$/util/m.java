package j$.util;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class m implements Iterator, b0 {
    public final /* synthetic */ int a = 0;
    public final Iterator b;

    public m(n nVar) {
        this.b = nVar.a.iterator();
    }

    public m(t tVar) {
        this.b = tVar.a.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.a) {
        }
        return this.b.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.a) {
            case 0:
                return this.b.next();
            default:
                return new r((Map.Entry) this.b.next());
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Iterator, j$.util.b0
    public final void forEachRemaining(Consumer consumer) {
        switch (this.a) {
            case 0:
                j$.com.android.tools.r8.a.D(this.b, consumer);
                break;
            default:
                j$.com.android.tools.r8.a.D(this.b, new q(0, consumer));
                break;
        }
    }
}
