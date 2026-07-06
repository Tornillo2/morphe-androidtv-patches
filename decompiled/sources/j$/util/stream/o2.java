package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class o2 extends f2 {
    @Override // j$.util.stream.d2
    public final d2 i(long j, long j2, IntFunction intFunction) {
        if (j == 0 && j2 == this.c) {
            return this;
        }
        long jCount = this.a.count();
        if (j >= jCount) {
            return this.b.i(j - jCount, j2 - jCount, intFunction);
        }
        if (j2 > jCount) {
            return w3.b0(d7.REFERENCE, this.a.i(j, jCount, intFunction), this.b.i(0L, j2 - jCount, intFunction));
        }
        return this.a.i(j, j2, intFunction);
    }

    @Override // j$.util.stream.d2
    public final Spliterator spliterator() {
        return new f3(this);
    }

    @Override // j$.util.stream.d2
    public final void k(Object[] objArr, int i) {
        Objects.requireNonNull(objArr);
        d2 d2Var = this.a;
        d2Var.k(objArr, i);
        this.b.k(objArr, i + ((int) d2Var.count()));
    }

    @Override // j$.util.stream.d2
    public final Object[] m(IntFunction intFunction) {
        long j = this.c;
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) j);
        k(objArr, 0);
        return objArr;
    }

    @Override // j$.util.stream.d2
    public final void forEach(Consumer consumer) {
        this.a.forEach(consumer);
        this.b.forEach(consumer);
    }

    public final String toString() {
        long j = this.c;
        return j < 32 ? String.format("ConcNode[%s.%s]", this.a, this.b) : String.format("ConcNode[size=%d]", Long.valueOf(j));
    }
}
