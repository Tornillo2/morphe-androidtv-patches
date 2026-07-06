package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class m2 extends n2 implements b2 {
    @Override // j$.util.stream.d2
    public final /* synthetic */ void forEach(Consumer consumer) {
        w3.R(this, consumer);
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ d2 i(long j, long j2, IntFunction intFunction) {
        return w3.U(this, j, j2);
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ void k(Object[] objArr, int i) {
        w3.O(this, (Long[]) objArr, i);
    }

    @Override // j$.util.stream.c2
    public final Object newArray(int i) {
        return new long[i];
    }

    @Override // j$.util.stream.d2
    public final Spliterator.OfPrimitive spliterator() {
        return new d3(this);
    }

    @Override // j$.util.stream.d2
    public final Spliterator spliterator() {
        return new d3(this);
    }
}
