package j$.util.stream;

import j$.util.Objects;
import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class i5 implements m5 {
    public final o5 a;

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
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
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

    public i5(o5 o5Var) {
        this.a = (o5) Objects.requireNonNull(o5Var);
    }

    @Override // j$.util.stream.o5
    public void c(long j) {
        this.a.c(j);
    }

    @Override // j$.util.stream.o5
    public void end() {
        this.a.end();
    }

    @Override // j$.util.stream.o5
    public boolean e() {
        return this.a.e();
    }
}
