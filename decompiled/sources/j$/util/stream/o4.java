package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class o4 extends s4 implements r4, m5 {
    public final /* synthetic */ Supplier b;
    public final /* synthetic */ ObjIntConsumer c;
    public final /* synthetic */ o d;

    @Override // j$.util.stream.o5
    public final /* synthetic */ void accept(double d) {
        w3.C();
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
        d((Integer) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.m5
    public final /* synthetic */ void d(Integer num) {
        w3.F(this, num);
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
        this.a = this.d.apply(this.a, ((o4) r4Var).a);
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        this.a = this.b.get();
    }

    @Override // j$.util.stream.o5
    public final void accept(int i) {
        this.c.accept(this.a, i);
    }

    public o4(Supplier supplier, ObjIntConsumer objIntConsumer, o oVar) {
        this.b = supplier;
        this.c = objIntConsumer;
        this.d = oVar;
    }
}
