package j$.util.stream;

import j$.util.Optional;
import j$.util.function.Consumer$CC;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class h4 implements r4 {
    public boolean a;
    public Object b;
    public final /* synthetic */ BinaryOperator c;

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

    @Override // j$.util.stream.o5, java.util.function.LongConsumer
    public final /* synthetic */ void accept(long j) {
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

    public h4(BinaryOperator binaryOperator) {
        this.c = binaryOperator;
    }

    @Override // j$.util.stream.r4
    public final void j(r4 r4Var) {
        h4 h4Var = (h4) r4Var;
        if (h4Var.a) {
            return;
        }
        n(h4Var.b);
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        this.a = true;
        this.b = null;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        if (this.a) {
            this.a = false;
            this.b = obj;
        } else {
            this.b = this.c.apply(this.b, obj);
        }
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.a ? Optional.empty() : Optional.of(this.b);
    }
}
