package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public final class i0 extends b {
    public final c0 j;
    public final boolean k;

    public i0(c0 c0Var, boolean z, a aVar, Spliterator spliterator) {
        super(aVar, spliterator);
        this.k = z;
        this.j = c0Var;
    }

    public i0(i0 i0Var, Spliterator spliterator) {
        super(i0Var, spliterator);
        this.k = i0Var.k;
        this.j = i0Var.j;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new i0(this, spliterator);
    }

    @Override // j$.util.stream.b
    public final Object h() {
        return this.j.b;
    }

    @Override // j$.util.stream.d
    public final Object a() {
        w3 w3Var = this.a;
        u8 u8Var = (u8) this.j.d.get();
        w3Var.v0(this.b, u8Var);
        Object obj = u8Var.get();
        if (this.k) {
            if (obj != null) {
                d dVar = this;
                while (dVar != null) {
                    d dVar2 = (d) dVar.getCompleter();
                    if (dVar2 != null && dVar2.d != dVar) {
                        g();
                        return obj;
                    }
                    dVar = dVar2;
                }
                AtomicReference atomicReference = this.h;
                while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
                }
                return obj;
            }
        } else if (obj != null) {
            AtomicReference atomicReference2 = this.h;
            while (!atomicReference2.compareAndSet(null, obj) && atomicReference2.get() == null) {
            }
        }
        return null;
    }

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        if (this.k) {
            i0 i0Var = (i0) this.d;
            i0 i0Var2 = null;
            while (true) {
                if (i0Var != i0Var2) {
                    Object objI = i0Var.i();
                    if (objI != null && this.j.c.test(objI)) {
                        d(objI);
                        d dVar = this;
                        while (true) {
                            if (dVar != null) {
                                d dVar2 = (d) dVar.getCompleter();
                                if (dVar2 != null && dVar2.d != dVar) {
                                    g();
                                    break;
                                }
                                dVar = dVar2;
                            } else {
                                AtomicReference atomicReference = this.h;
                                while (!atomicReference.compareAndSet(null, objI) && atomicReference.get() == null) {
                                }
                            }
                        }
                    } else {
                        i0Var2 = i0Var;
                        i0Var = (i0) this.e;
                    }
                } else {
                    break;
                }
            }
        }
        super.onCompletion(countedCompleter);
    }
}
