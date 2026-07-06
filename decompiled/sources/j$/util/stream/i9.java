package j$.util.stream;

import java.util.function.DoublePredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class i9 extends h5 implements l9 {
    @Override // j$.util.stream.l9
    public final long h() {
        return 0L;
    }

    public i9(j9 j9Var, o5 o5Var, boolean z) {
        super(o5Var);
    }

    @Override // j$.util.stream.l5, j$.util.stream.o5
    public final void accept(double d) {
        DoublePredicate doublePredicate = null;
        doublePredicate.test(d);
        throw null;
    }
}
