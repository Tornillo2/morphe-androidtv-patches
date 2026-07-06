package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class a4 implements r4, l5 {
    public double a;
    public final /* synthetic */ double b;
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

    public a4(double d, DoubleBinaryOperator doubleBinaryOperator) {
        this.b = d;
        this.c = doubleBinaryOperator;
    }

    @Override // j$.util.stream.r4
    public final void j(r4 r4Var) {
        accept(((a4) r4Var).a);
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        this.a = this.b;
    }

    @Override // j$.util.stream.o5
    public final void accept(double d) {
        this.a = this.c.applyAsDouble(this.a, d);
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return Double.valueOf(this.a);
    }
}
