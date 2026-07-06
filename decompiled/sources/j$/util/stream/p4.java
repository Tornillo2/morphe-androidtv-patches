package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class p4 implements r4, n5 {
    public long a;
    public final /* synthetic */ long b;
    public final /* synthetic */ LongBinaryOperator c;

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

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        l((Long) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ boolean e() {
        return false;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.n5
    public final /* synthetic */ void l(Long l) {
        w3.H(this, l);
    }

    public p4(long j, LongBinaryOperator longBinaryOperator) {
        this.b = j;
        this.c = longBinaryOperator;
    }

    @Override // j$.util.stream.r4
    public final void j(r4 r4Var) {
        accept(((p4) r4Var).a);
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        this.a = this.b;
    }

    @Override // j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        this.a = this.c.applyAsLong(this.a, j);
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return Long.valueOf(this.a);
    }
}
