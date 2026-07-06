package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class s7 extends e7 implements Spliterator.OfDouble {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.e(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.s(this, consumer);
    }

    @Override // j$.util.stream.e7
    public final e7 e(Spliterator spliterator) {
        return new s7(this.b, spliterator, this.a);
    }

    @Override // j$.util.stream.e7
    public final void d() {
        s6 s6Var = new s6();
        this.h = s6Var;
        Objects.requireNonNull(s6Var);
        this.e = this.b.w0(new r7(s6Var, 1));
        this.f = new j$.util.q(11, this);
    }

    @Override // j$.util.stream.e7, j$.util.Spliterator
    public final Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) super.trySplit();
    }

    @Override // j$.util.stream.e7, j$.util.Spliterator
    public final Spliterator.OfPrimitive trySplit() {
        return (Spliterator.OfDouble) super.trySplit();
    }

    @Override // j$.util.stream.e7, j$.util.Spliterator
    public final Spliterator trySplit() {
        return (Spliterator.OfDouble) super.trySplit();
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        double d;
        Objects.requireNonNull(doubleConsumer);
        boolean zA = a();
        if (zA) {
            s6 s6Var = (s6) this.h;
            long j = this.g;
            int iR = s6Var.r(j);
            if (s6Var.c == 0 && iR == 0) {
                d = ((double[]) s6Var.e)[(int) j];
            } else {
                d = ((double[][]) s6Var.f)[iR][(int) (j - s6Var.d[iR])];
            }
            doubleConsumer.accept(d);
        }
        return zA;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        if (this.h == null && !this.i) {
            Objects.requireNonNull(doubleConsumer);
            c();
            Objects.requireNonNull(doubleConsumer);
            r7 r7Var = new r7(doubleConsumer, 0);
            this.b.v0(this.d, r7Var);
            this.i = true;
            return;
        }
        while (tryAdvance(doubleConsumer)) {
        }
    }
}
