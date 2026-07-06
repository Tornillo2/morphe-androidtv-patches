package j$.util.stream;

import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public abstract class w2 implements d2 {
    @Override // j$.util.stream.d2
    public final long count() {
        return 0L;
    }

    public final void g(Object obj) {
    }

    @Override // j$.util.stream.d2
    public /* synthetic */ d2 i(long j, long j2, IntFunction intFunction) {
        return w3.V(this, j, j2, intFunction);
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ int o() {
        return 0;
    }

    @Override // j$.util.stream.d2
    public d2 a(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.d2
    public final Object[] m(IntFunction intFunction) {
        return (Object[]) intFunction.apply(0);
    }

    public final void f(int i, Object obj) {
    }
}
