package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class x4 extends s4 implements r4 {
    public long b;

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
    public final /* synthetic */ boolean e() {
        return false;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        this.b = 0L;
    }
}
