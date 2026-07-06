package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.DoubleConsumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class r7 implements l5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ DoubleConsumer b;

    public /* synthetic */ r7(DoubleConsumer doubleConsumer, int i) {
        this.a = i;
        this.b = doubleConsumer;
    }

    private final /* synthetic */ void a(long j) {
    }

    private final /* synthetic */ void b(long j) {
    }

    private final /* synthetic */ void f() {
    }

    private final /* synthetic */ void g() {
    }

    @Override // j$.util.stream.l5, j$.util.stream.o5
    public final void accept(double d) {
        switch (this.a) {
            case 0:
                this.b.accept(d);
                break;
            default:
                ((s6) this.b).accept(d);
                break;
        }
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void accept(int i) {
        switch (this.a) {
            case 0:
                w3.J();
                throw null;
            default:
                w3.J();
                throw null;
        }
    }

    @Override // j$.util.stream.o5, java.util.function.LongConsumer
    public final /* synthetic */ void accept(long j) {
        switch (this.a) {
            case 0:
                w3.K();
                throw null;
            default:
                w3.K();
                throw null;
        }
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        switch (this.a) {
            case 0:
                n((Double) obj);
                break;
            default:
                n((Double) obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        switch (this.a) {
        }
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void c(long j) {
        int i = this.a;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ boolean e() {
        switch (this.a) {
        }
        return false;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void end() {
        int i = this.a;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void n(Double d) {
        switch (this.a) {
            case 0:
                w3.D(this, d);
                break;
            default:
                w3.D(this, d);
                break;
        }
    }
}
