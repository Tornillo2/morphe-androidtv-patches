package j$.util.stream;

import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public abstract class n2 extends f2 implements c2 {
    @Override // j$.util.stream.d2
    public final /* synthetic */ Object[] m(IntFunction intFunction) {
        return w3.L(this, intFunction);
    }

    @Override // j$.util.stream.c2
    public final void g(Object obj) {
        ((c2) this.a).g(obj);
        ((c2) this.b).g(obj);
    }

    @Override // j$.util.stream.c2
    public final void f(int i, Object obj) {
        d2 d2Var = this.a;
        ((c2) d2Var).f(i, obj);
        ((c2) this.b).f(i + ((int) ((c2) d2Var).count()), obj);
    }

    @Override // j$.util.stream.c2
    public final Object b() {
        long j = this.c;
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object objNewArray = newArray((int) j);
        f(0, objNewArray);
        return objNewArray;
    }

    public final String toString() {
        long j = this.c;
        return j < 32 ? String.format("%s[%s.%s]", getClass().getName(), this.a, this.b) : String.format("%s[size=%d]", getClass().getName(), Long.valueOf(j));
    }
}
