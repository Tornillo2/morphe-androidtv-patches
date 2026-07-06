package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a extends w3 implements BaseStream {
    public final a h;
    public final a i;
    public final int j;
    public final a k;
    public int l;
    public int m;
    public Spliterator n;
    public Supplier o;
    public boolean p;
    public final boolean q;
    public Runnable r;
    public boolean s;

    public abstract d2 A0(a aVar, Spliterator spliterator, boolean z, IntFunction intFunction);

    public abstract boolean B0(Spliterator spliterator, o5 o5Var);

    public abstract d7 C0();

    public abstract Spliterator D0(Supplier supplier);

    public abstract boolean G0();

    public abstract o5 H0(int i, o5 o5Var);

    public abstract Spliterator K0(a aVar, Supplier supplier, boolean z);

    public a(Spliterator spliterator, int i, boolean z) {
        this.i = null;
        this.n = spliterator;
        this.h = this;
        int i2 = c7.g & i;
        this.j = i2;
        this.m = (~(i2 << 1)) & c7.l;
        this.l = 0;
        this.s = z;
    }

    public a(a aVar, int i) {
        if (aVar.p) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        aVar.p = true;
        aVar.k = this;
        this.i = aVar;
        this.j = c7.h & i;
        this.m = c7.j(i, aVar.m);
        a aVar2 = aVar.h;
        this.h = aVar2;
        if (G0()) {
            aVar2.q = true;
        }
        this.l = aVar.l + 1;
    }

    public a(Supplier supplier, int i, boolean z) {
        this.i = null;
        this.o = supplier;
        this.h = this;
        int i2 = c7.g & i;
        this.j = i2;
        this.m = (~(i2 << 1)) & c7.l;
        this.l = 0;
        this.s = z;
    }

    public final Object y0(t8 t8Var) {
        if (this.p) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.p = true;
        if (this.h.s) {
            return t8Var.i(this, I0(t8Var.v()));
        }
        return t8Var.f(this, I0(t8Var.v()));
    }

    public final d2 z0(IntFunction intFunction) {
        if (this.p) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.p = true;
        if (this.h.s && this.i != null && G0()) {
            this.l = 0;
            a aVar = this.i;
            return E0(aVar, aVar.I0(0), intFunction);
        }
        return g0(I0(0), true, intFunction);
    }

    public final Spliterator J0() {
        a aVar = this.h;
        if (this != aVar) {
            throw new IllegalStateException();
        }
        if (this.p) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.p = true;
        Spliterator spliterator = aVar.n;
        if (spliterator != null) {
            aVar.n = null;
            return spliterator;
        }
        Supplier supplier = aVar.o;
        if (supplier != null) {
            Spliterator spliterator2 = (Spliterator) supplier.get();
            this.h.o = null;
            return spliterator2;
        }
        throw new IllegalStateException("source already consumed or closed");
    }

    @Override // j$.util.stream.BaseStream
    public final BaseStream sequential() {
        this.h.s = false;
        return this;
    }

    @Override // j$.util.stream.BaseStream
    public final BaseStream parallel() {
        this.h.s = true;
        return this;
    }

    @Override // j$.util.stream.BaseStream, java.lang.AutoCloseable
    public final void close() {
        this.p = true;
        this.o = null;
        this.n = null;
        a aVar = this.h;
        Runnable runnable = aVar.r;
        if (runnable != null) {
            aVar.r = null;
            runnable.run();
        }
    }

    @Override // j$.util.stream.BaseStream
    public final BaseStream onClose(Runnable runnable) {
        if (this.p) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        Objects.requireNonNull(runnable);
        a aVar = this.h;
        Runnable runnable2 = aVar.r;
        if (runnable2 != null) {
            runnable = new m8(runnable2, runnable);
        }
        aVar.r = runnable;
        return this;
    }

    @Override // j$.util.stream.BaseStream
    public Spliterator spliterator() {
        if (this.p) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.p = true;
        a aVar = this.h;
        if (this == aVar) {
            Spliterator spliterator = aVar.n;
            if (spliterator != null) {
                aVar.n = null;
                return spliterator;
            }
            Supplier supplier = aVar.o;
            if (supplier != null) {
                aVar.o = null;
                return D0(supplier);
            }
            throw new IllegalStateException("source already consumed or closed");
        }
        return K0(this, new j$.util.q(2, this), aVar.s);
    }

    @Override // j$.util.stream.w3
    public final d2 g0(Spliterator spliterator, boolean z, IntFunction intFunction) {
        if (this.h.s) {
            return A0(this, spliterator, z, intFunction);
        }
        v1 v1VarS0 = s0(h0(spliterator), intFunction);
        v0(spliterator, v1VarS0);
        return v1VarS0.build();
    }

    @Override // j$.util.stream.BaseStream
    public final boolean isParallel() {
        return this.h.s;
    }

    public final Spliterator I0(int i) {
        int i2;
        int i3;
        a aVar = this.h;
        Spliterator spliteratorF0 = aVar.n;
        if (spliteratorF0 != null) {
            aVar.n = null;
        } else {
            Supplier supplier = aVar.o;
            if (supplier != null) {
                spliteratorF0 = (Spliterator) supplier.get();
                this.h.o = null;
            } else {
                throw new IllegalStateException("source already consumed or closed");
            }
        }
        a aVar2 = this.h;
        if (aVar2.s && aVar2.q) {
            a aVar3 = aVar2.k;
            int i4 = 1;
            while (aVar2 != this) {
                int i5 = aVar3.j;
                if (aVar3.G0()) {
                    if (c7.SHORT_CIRCUIT.o(i5)) {
                        i5 &= ~c7.u;
                    }
                    spliteratorF0 = aVar3.F0(aVar2, spliteratorF0);
                    if (spliteratorF0.hasCharacteristics(64)) {
                        i2 = (~c7.t) & i5;
                        i3 = c7.s;
                    } else {
                        i2 = (~c7.s) & i5;
                        i3 = c7.t;
                    }
                    i5 = i2 | i3;
                    i4 = 0;
                }
                int i6 = i4 + 1;
                aVar3.l = i4;
                aVar3.m = c7.j(i5, aVar2.m);
                a aVar4 = aVar3;
                aVar3 = aVar3.k;
                aVar2 = aVar4;
                i4 = i6;
            }
        }
        if (i != 0) {
            this.m = c7.j(i, this.m);
        }
        return spliteratorF0;
    }

    @Override // j$.util.stream.w3
    public final long h0(Spliterator spliterator) {
        if (c7.SIZED.o(this.m)) {
            return spliterator.getExactSizeIfKnown();
        }
        return -1L;
    }

    @Override // j$.util.stream.w3
    public final o5 v0(Spliterator spliterator, o5 o5Var) {
        c0(spliterator, w0((o5) Objects.requireNonNull(o5Var)));
        return o5Var;
    }

    @Override // j$.util.stream.w3
    public final void c0(Spliterator spliterator, o5 o5Var) {
        Objects.requireNonNull(o5Var);
        if (!c7.SHORT_CIRCUIT.o(this.m)) {
            o5Var.c(spliterator.getExactSizeIfKnown());
            spliterator.forEachRemaining(o5Var);
            o5Var.end();
            return;
        }
        d0(spliterator, o5Var);
    }

    @Override // j$.util.stream.w3
    public final boolean d0(Spliterator spliterator, o5 o5Var) {
        a aVar = this;
        while (aVar.l > 0) {
            aVar = aVar.i;
        }
        o5Var.c(spliterator.getExactSizeIfKnown());
        boolean zB0 = aVar.B0(spliterator, o5Var);
        o5Var.end();
        return zB0;
    }

    @Override // j$.util.stream.w3
    public final o5 w0(o5 o5Var) {
        Objects.requireNonNull(o5Var);
        for (a aVar = this; aVar.l > 0; aVar = aVar.i) {
            o5Var = aVar.H0(aVar.i.m, o5Var);
        }
        return o5Var;
    }

    @Override // j$.util.stream.w3
    public final Spliterator x0(Spliterator spliterator) {
        return this.l == 0 ? spliterator : K0(this, new j$.util.q(3, spliterator), this.h.s);
    }

    public d2 E0(w3 w3Var, Spliterator spliterator, IntFunction intFunction) {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    public Spliterator F0(a aVar, Spliterator spliterator) {
        return E0(aVar, spliterator, new j$.time.e(11)).spliterator();
    }
}
