package j$.time.temporal;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class p implements o {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;

    public /* synthetic */ p(int i, int i2) {
        this.a = i2;
        this.b = i;
    }

    @Override // j$.time.temporal.o
    public final m o(m mVar) {
        switch (this.a) {
            case 0:
                int iJ = mVar.j(a.DAY_OF_WEEK);
                int i = this.b;
                if (iJ == i) {
                    return mVar;
                }
                return mVar.d(iJ - i >= 0 ? 7 - r0 : -r0, b.DAYS);
            default:
                int iJ2 = mVar.j(a.DAY_OF_WEEK);
                int i2 = this.b;
                if (iJ2 == i2) {
                    return mVar;
                }
                return mVar.w(i2 - iJ2 >= 0 ? 7 - r1 : -r1, b.DAYS);
        }
    }
}
