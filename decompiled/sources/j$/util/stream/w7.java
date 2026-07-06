package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class w7 extends e7 implements Spliterator.OfLong {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.g(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.u(this, consumer);
    }

    @Override // j$.util.stream.e7
    public final e7 e(Spliterator spliterator) {
        return new w7(this.b, spliterator, this.a);
    }

    @Override // j$.util.stream.e7
    public final void d() {
        w6 w6Var = new w6();
        this.h = w6Var;
        Objects.requireNonNull(w6Var);
        this.e = this.b.w0(new v7(w6Var, 1));
        this.f = new j$.util.q(13, this);
    }

    @Override // j$.util.stream.e7, j$.util.Spliterator
    public final Spliterator.OfLong trySplit() {
        return (Spliterator.OfLong) super.trySplit();
    }

    @Override // j$.util.stream.e7, j$.util.Spliterator
    public final Spliterator.OfPrimitive trySplit() {
        return (Spliterator.OfLong) super.trySplit();
    }

    @Override // j$.util.stream.e7, j$.util.Spliterator
    public final Spliterator trySplit() {
        return (Spliterator.OfLong) super.trySplit();
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(LongConsumer longConsumer) {
        long j;
        Objects.requireNonNull(longConsumer);
        boolean zA = a();
        if (zA) {
            w6 w6Var = (w6) this.h;
            long j2 = this.g;
            int iR = w6Var.r(j2);
            if (w6Var.c == 0 && iR == 0) {
                j = ((long[]) w6Var.e)[(int) j2];
            } else {
                j = ((long[][]) w6Var.f)[iR][(int) (j2 - w6Var.d[iR])];
            }
            longConsumer.accept(j);
        }
        return zA;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(LongConsumer longConsumer) {
        if (this.h == null && !this.i) {
            Objects.requireNonNull(longConsumer);
            c();
            Objects.requireNonNull(longConsumer);
            v7 v7Var = new v7(longConsumer, 0);
            this.b.v0(this.d, v7Var);
            this.i = true;
            return;
        }
        while (tryAdvance(longConsumer)) {
        }
    }
}
