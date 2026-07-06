package j$.time.chrono;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class k0 implements n {
    public static final k0 BE;
    public static final k0 BEFORE_BE;
    public static final /* synthetic */ k0[] a;

    @Override // j$.time.temporal.n
    public final /* synthetic */ Object B(j$.time.e eVar) {
        return j$.com.android.tools.r8.a.p(this, eVar);
    }

    @Override // j$.time.temporal.n
    public final /* synthetic */ long C(j$.time.temporal.r rVar) {
        return j$.com.android.tools.r8.a.j(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final /* synthetic */ boolean e(j$.time.temporal.r rVar) {
        return j$.com.android.tools.r8.a.l(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final /* synthetic */ int j(j$.time.temporal.r rVar) {
        return j$.com.android.tools.r8.a.i(this, rVar);
    }

    public static k0 valueOf(String str) {
        return (k0) Enum.valueOf(k0.class, str);
    }

    public static k0[] values() {
        return (k0[]) a.clone();
    }

    static {
        k0 k0Var = new k0("BEFORE_BE", 0);
        BEFORE_BE = k0Var;
        k0 k0Var2 = new k0("BE", 1);
        BE = k0Var2;
        a = new k0[]{k0Var, k0Var2};
    }

    @Override // j$.time.chrono.n
    public final int getValue() {
        return ordinal();
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        return j$.time.temporal.s.d(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return mVar.c(getValue(), j$.time.temporal.a.ERA);
    }
}
