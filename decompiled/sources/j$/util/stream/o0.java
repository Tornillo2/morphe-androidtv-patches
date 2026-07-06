package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class o0 implements t8, u8 {
    public final boolean a;

    public /* synthetic */ void accept(double d) {
        w3.C();
        throw null;
    }

    public /* synthetic */ void accept(int i) {
        w3.J();
        throw null;
    }

    public /* synthetic */ void accept(long j) {
        w3.K();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void c(long j) {
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ boolean e() {
        return false;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void end() {
    }

    public o0(boolean z) {
        this.a = z;
    }

    @Override // j$.util.stream.t8
    public final int v() {
        if (this.a) {
            return 0;
        }
        return c7.r;
    }

    public final void a(w3 w3Var, Spliterator spliterator) {
        if (this.a) {
            new p0(w3Var, spliterator, this).invoke();
        } else {
            new q0(w3Var, spliterator, w3Var.w0(this)).invoke();
        }
    }
}
