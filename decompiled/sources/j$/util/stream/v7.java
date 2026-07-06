package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class v7 implements n5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ LongConsumer b;

    public /* synthetic */ v7(LongConsumer longConsumer, int i) {
        this.a = i;
        this.b = longConsumer;
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

    @Override // j$.util.stream.n5, j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        switch (this.a) {
            case 0:
                this.b.accept(j);
                break;
            default:
                ((w6) this.b).accept(j);
                break;
        }
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        switch (this.a) {
            case 0:
                l((Long) obj);
                break;
            default:
                l((Long) obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        switch (this.a) {
        }
        return LongConsumer$CC.$default$andThen(this, longConsumer);
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

    @Override // j$.util.stream.n5
    public final /* synthetic */ void l(Long l) {
        switch (this.a) {
            case 0:
                w3.H(this, l);
                break;
            default:
                w3.H(this, l);
                break;
        }
    }
}
