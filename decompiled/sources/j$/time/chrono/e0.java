package j$.time.chrono;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class e0 implements n {
    public static final e0 BEFORE_ROC;
    public static final e0 ROC;
    public static final /* synthetic */ e0[] a;

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

    public static e0 valueOf(String str) {
        return (e0) Enum.valueOf(e0.class, str);
    }

    public static e0[] values() {
        return (e0[]) a.clone();
    }

    static {
        e0 e0Var = new e0("BEFORE_ROC", 0);
        BEFORE_ROC = e0Var;
        e0 e0Var2 = new e0("ROC", 1);
        ROC = e0Var2;
        a = new e0[]{e0Var, e0Var2};
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
