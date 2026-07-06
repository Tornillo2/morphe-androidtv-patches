package j$.time.chrono;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class s implements n {
    public static final s AH;
    public static final /* synthetic */ s[] a;

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

    @Override // j$.time.chrono.n
    public final int getValue() {
        return 1;
    }

    @Override // j$.time.temporal.n
    public final /* synthetic */ int j(j$.time.temporal.r rVar) {
        return j$.com.android.tools.r8.a.i(this, rVar);
    }

    public static s valueOf(String str) {
        return (s) Enum.valueOf(s.class, str);
    }

    public static s[] values() {
        return (s[]) a.clone();
    }

    static {
        s sVar = new s("AH", 0);
        AH = sVar;
        a = new s[]{sVar};
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.ERA) {
            return j$.time.temporal.v.f(1L, 1L);
        }
        return j$.time.temporal.s.d(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return mVar.c(1, j$.time.temporal.a.ERA);
    }
}
