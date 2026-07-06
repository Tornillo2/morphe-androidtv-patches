package j$.util.stream;

import j$.util.OptionalLong;
import j$.util.function.Consumer$CC;
import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class q4 implements r4, n5 {
    public boolean a;
    public long b;
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

    public q4(LongBinaryOperator longBinaryOperator) {
        this.c = longBinaryOperator;
    }

    @Override // j$.util.stream.r4
    public final void j(r4 r4Var) {
        q4 q4Var = (q4) r4Var;
        if (q4Var.a) {
            return;
        }
        accept(q4Var.b);
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        this.a = true;
        this.b = 0L;
    }

    @Override // j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        if (this.a) {
            this.a = false;
            this.b = j;
        } else {
            this.b = this.c.applyAsLong(this.b, j);
        }
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.a ? OptionalLong.empty() : OptionalLong.of(this.b);
    }
}
