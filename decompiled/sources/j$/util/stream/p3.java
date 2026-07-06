package j$.util.stream;

import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class p3 extends z6 implements d2, v1 {
    @Override // j$.util.stream.o5
    public final /* synthetic */ void accept(double d) {
        w3.C();
        throw null;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void accept(int i) {
        w3.J();
        throw null;
    }

    @Override // j$.util.stream.o5, java.util.function.LongConsumer
    public final /* synthetic */ void accept(long j) {
        w3.K();
        throw null;
    }

    @Override // j$.util.stream.v1
    public final d2 build() {
        return this;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ boolean e() {
        return false;
    }

    @Override // j$.util.stream.o5
    public final void end() {
    }

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

    @Override // j$.util.stream.d2
    public final void k(Object[] objArr, int i) {
        long j = i;
        long jCount = count() + j;
        if (jCount > objArr.length || jCount < j) {
            throw new IndexOutOfBoundsException("does not fit");
        }
        if (this.c == 0) {
            System.arraycopy(this.e, 0, objArr, i, this.b);
            return;
        }
        for (int i2 = 0; i2 < this.c; i2++) {
            Object[] objArr2 = this.f[i2];
            System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
            i += this.f[i2].length;
        }
        int i3 = this.b;
        if (i3 > 0) {
            System.arraycopy(this.e, 0, objArr, i, i3);
        }
    }

    @Override // j$.util.stream.d2
    public final Object[] m(IntFunction intFunction) {
        long jCount = count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) jCount);
        k(objArr, 0);
        return objArr;
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        clear();
        p(j);
    }
}
