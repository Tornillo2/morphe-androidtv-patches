package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.function.DoubleConsumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class o8 extends n8 implements DoubleConsumer, Spliterator.OfDouble {
    public double b;
    public s6 c;

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.e(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.s(this, consumer);
    }

    @Override // j$.util.stream.n8, j$.util.Spliterator
    public final /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
        return null;
    }

    @Override // j$.util.stream.n8, j$.util.Spliterator
    public final /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
        return null;
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        int i = this.a;
        if (i == 0) {
            this.b = d;
            this.a = i + 1;
        } else {
            if (i > 0) {
                if (this.c == null) {
                    s6 s6Var = new s6();
                    this.c = s6Var;
                    s6Var.accept(this.b);
                    this.a++;
                }
                this.c.accept(d);
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        if (this.a != -2) {
            return false;
        }
        doubleConsumer.accept(this.b);
        this.a = -1;
        return true;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        if (this.a == -2) {
            doubleConsumer.accept(this.b);
            this.a = -1;
        }
    }
}
