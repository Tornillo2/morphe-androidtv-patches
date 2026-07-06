package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class g4 extends s4 implements r4 {
    public final /* synthetic */ Object b;
    public final /* synthetic */ BiFunction c;
    public final /* synthetic */ BinaryOperator d;

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

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ boolean e() {
        return false;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.r4
    public final void j(r4 r4Var) {
        this.a = this.d.apply(this.a, ((g4) r4Var).a);
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        this.a = this.b;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        this.a = this.c.apply(this.a, obj);
    }

    public g4(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        this.b = obj;
        this.c = biFunction;
        this.d = binaryOperator;
    }
}
