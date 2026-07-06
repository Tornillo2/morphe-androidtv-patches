package j$.util.stream;

import j$.util.OptionalDouble;
import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class c4 implements r4, l5 {
    public boolean a;
    public double b;
    public final /* synthetic */ DoubleBinaryOperator c;

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

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        n((Double) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ boolean e() {
        return false;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void n(Double d) {
        w3.D(this, d);
    }

    public c4(DoubleBinaryOperator doubleBinaryOperator) {
        this.c = doubleBinaryOperator;
    }

    @Override // j$.util.stream.r4
    public final void j(r4 r4Var) {
        c4 c4Var = (c4) r4Var;
        if (c4Var.a) {
            return;
        }
        accept(c4Var.b);
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        this.a = true;
        this.b = 0.0d;
    }

    @Override // j$.util.stream.o5
    public final void accept(double d) {
        if (this.a) {
            this.a = false;
            this.b = d;
        } else {
            this.b = this.c.applyAsDouble(this.b, d);
        }
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.a ? OptionalDouble.empty() : OptionalDouble.of(this.b);
    }
}
