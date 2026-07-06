package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class c0 implements t8 {
    public final int a;
    public final Object b;
    public final Predicate c;
    public final Supplier d;

    public c0(boolean z, d7 d7Var, Object obj, Predicate predicate, Supplier supplier) {
        this.a = (z ? 0 : c7.r) | c7.u;
        this.b = obj;
        this.c = predicate;
        this.d = supplier;
    }

    @Override // j$.util.stream.t8
    public final int v() {
        return this.a;
    }

    @Override // j$.util.stream.t8
    public final Object f(a aVar, Spliterator spliterator) {
        u8 u8Var = (u8) this.d.get();
        aVar.v0(spliterator, u8Var);
        Object obj = u8Var.get();
        return obj != null ? obj : this.b;
    }

    @Override // j$.util.stream.t8
    public final Object i(w3 w3Var, Spliterator spliterator) {
        a aVar = (a) w3Var;
        return new i0(this, c7.ORDERED.o(aVar.m), aVar, spliterator).invoke();
    }
}
