package j$.util.stream;

import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public final class k extends k5 {
    public final /* synthetic */ int b = 0;
    public boolean c;
    public Object d;

    public /* synthetic */ k(o5 o5Var) {
        super(o5Var);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(w8 w8Var, o5 o5Var) {
        super(o5Var);
        this.d = w8Var;
        this.c = true;
    }

    @Override // j$.util.stream.k5, j$.util.stream.o5
    public final void c(long j) {
        switch (this.b) {
            case 0:
                this.c = false;
                this.d = null;
                this.a.c(-1L);
                break;
            case 1:
                this.a.c(-1L);
                break;
            default:
                this.a.c(-1L);
                break;
        }
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        switch (this.b) {
            case 0:
                o5 o5Var = this.a;
                if (obj == null) {
                    if (this.c) {
                        return;
                    }
                    this.c = true;
                    this.d = null;
                    o5Var.n((Object) null);
                    return;
                }
                Object obj2 = this.d;
                if (obj2 == null || !obj.equals(obj2)) {
                    this.d = obj;
                    o5Var.n(obj);
                    return;
                }
                return;
            case 1:
                Stream stream = (Stream) ((j$.util.q) ((q) this.d).u).apply(obj);
                if (stream != null) {
                    try {
                        boolean z = this.c;
                        o5 o5Var2 = this.a;
                        if (!z) {
                            ((Stream) stream.sequential()).forEach(o5Var2);
                        } else {
                            Spliterator<T> spliterator = ((Stream) stream.sequential()).spliterator();
                            while (!o5Var2.e() && spliterator.tryAdvance(o5Var2)) {
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            stream.close();
                            break;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                    break;
                }
                if (stream != null) {
                    stream.close();
                    return;
                }
                return;
            default:
                if (this.c) {
                    boolean zTest = ((w8) this.d).u.test(obj);
                    this.c = zTest;
                    if (zTest) {
                        this.a.n(obj);
                        return;
                    }
                    return;
                }
                return;
        }
    }

    @Override // j$.util.stream.k5, j$.util.stream.o5
    public boolean e() {
        switch (this.b) {
            case 1:
                this.c = true;
                return this.a.e();
            case 2:
                return !this.c || this.a.e();
            default:
                return super.e();
        }
    }

    @Override // j$.util.stream.k5, j$.util.stream.o5
    public void end() {
        switch (this.b) {
            case 0:
                this.c = false;
                this.d = null;
                this.a.end();
                break;
            default:
                super.end();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(q qVar, o5 o5Var) {
        super(o5Var);
        this.d = qVar;
    }
}
