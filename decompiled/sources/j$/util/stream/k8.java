package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class k8 implements o5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Consumer b;

    public /* synthetic */ k8(Consumer consumer, int i) {
        this.a = i;
        this.b = consumer;
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
    public final void n(Object obj) {
        switch (this.a) {
            case 0:
                ((z6) this.b).n(obj);
                break;
            default:
                this.b.n(obj);
                break;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
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
}
