package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class t2 extends w2 implements z1 {
    @Override // j$.util.stream.d2
    public final /* synthetic */ void forEach(Consumer consumer) {
        w3.Q(this, consumer);
    }

    @Override // j$.util.stream.w2, j$.util.stream.d2
    public final /* synthetic */ d2 i(long j, long j2, IntFunction intFunction) {
        return w3.T(this, j, j2);
    }

    @Override // j$.util.stream.w2, j$.util.stream.d2
    public final /* bridge */ /* synthetic */ d2 a(int i) {
        a(i);
        throw null;
    }

    @Override // j$.util.stream.w2, j$.util.stream.d2
    public final c2 a(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ void k(Object[] objArr, int i) {
        w3.N(this, (Integer[]) objArr, i);
    }

    @Override // j$.util.stream.c2
    public final /* bridge */ /* synthetic */ Object b() {
        return w3.e;
    }

    @Override // j$.util.stream.d2
    public final /* bridge */ /* synthetic */ Spliterator.OfPrimitive spliterator() {
        return Spliterators.b;
    }

    @Override // j$.util.stream.d2
    public final /* bridge */ /* synthetic */ Spliterator spliterator() {
        return Spliterators.b;
    }
}
