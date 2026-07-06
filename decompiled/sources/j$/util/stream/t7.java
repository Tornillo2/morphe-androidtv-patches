package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class t7 implements m5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ IntConsumer b;

    public /* synthetic */ t7(IntConsumer intConsumer, int i) {
        this.a = i;
        this.b = intConsumer;
    }

    private final /* synthetic */ void a(long j) {
    }

    private final /* synthetic */ void b(long j) {
    }

    private final /* synthetic */ void f() {
    }

    private final /* synthetic */ void g() {
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void accept(double d) {
        switch (this.a) {
            case 0:
                w3.C();
                throw null;
            default:
                w3.C();
                throw null;
        }
    }

    @Override // j$.util.stream.m5, j$.util.stream.o5
    public final void accept(int i) {
        switch (this.a) {
            case 0:
                this.b.accept(i);
                break;
            default:
                ((u6) this.b).accept(i);
                break;
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
                d((Integer) obj);
                break;
            default:
                d((Integer) obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        switch (this.a) {
        }
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void c(long j) {
        int i = this.a;
    }

    @Override // j$.util.stream.m5
    public final /* synthetic */ void d(Integer num) {
        switch (this.a) {
            case 0:
                w3.F(this, num);
                break;
            default:
                w3.F(this, num);
                break;
        }
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
}
