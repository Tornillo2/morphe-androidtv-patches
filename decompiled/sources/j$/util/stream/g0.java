package j$.util.stream;

import j$.util.Optional;

/* JADX INFO: loaded from: classes2.dex */
public final class g0 extends h0 {
    public static final c0 c;
    public static final c0 d;

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return Optional.of(this.b);
        }
        return null;
    }

    static {
        d7 d7Var = d7.REFERENCE;
        c = new c0(true, d7Var, Optional.empty(), new n(11), new n(12));
        d = new c0(false, d7Var, Optional.empty(), new n(11), new n(12));
    }
}
