package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class u7 extends e7 implements Spliterator.OfInt {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.f(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.t(this, consumer);
    }

    @Override // j$.util.stream.e7
    public final e7 e(Spliterator spliterator) {
        return new u7(this.b, spliterator, this.a);
    }

    @Override // j$.util.stream.e7
    public final void d() {
        u6 u6Var = new u6();
        this.h = u6Var;
        Objects.requireNonNull(u6Var);
        this.e = this.b.w0(new t7(u6Var, 1));
        this.f = new j$.util.q(12, this);
    }

    @Override // j$.util.stream.e7, j$.util.Spliterator
    public final Spliterator.OfInt trySplit() {
        return (Spliterator.OfInt) super.trySplit();
    }

    @Override // j$.util.stream.e7, j$.util.Spliterator
    public final Spliterator.OfPrimitive trySplit() {
        return (Spliterator.OfInt) super.trySplit();
    }

    @Override // j$.util.stream.e7, j$.util.Spliterator
    public final Spliterator trySplit() {
        return (Spliterator.OfInt) super.trySplit();
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(IntConsumer intConsumer) {
        int i;
        Objects.requireNonNull(intConsumer);
        boolean zA = a();
        if (zA) {
            u6 u6Var = (u6) this.h;
            long j = this.g;
            int iR = u6Var.r(j);
            if (u6Var.c == 0 && iR == 0) {
                i = ((int[]) u6Var.e)[(int) j];
            } else {
                i = ((int[][]) u6Var.f)[iR][(int) (j - u6Var.d[iR])];
            }
            intConsumer.accept(i);
        }
        return zA;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(IntConsumer intConsumer) {
        if (this.h == null && !this.i) {
            Objects.requireNonNull(intConsumer);
            c();
            Objects.requireNonNull(intConsumer);
            t7 t7Var = new t7(intConsumer, 0);
            this.b.v0(this.d, t7Var);
            this.i = true;
            return;
        }
        while (tryAdvance(intConsumer)) {
        }
    }
}
