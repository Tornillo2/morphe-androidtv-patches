package j$.util.stream;

import java.util.concurrent.CountedCompleter;

/* JADX INFO: loaded from: classes2.dex */
public abstract class v3 extends CountedCompleter {
    public final d2 a;
    public final int b;

    public abstract void a();

    public abstract v3 b(int i, int i2);

    public v3(d2 d2Var) {
        this.a = d2Var;
        this.b = 0;
    }

    public v3(v3 v3Var, d2 d2Var, int i) {
        super(v3Var);
        this.a = d2Var;
        this.b = i;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        v3 v3VarB = this;
        while (v3VarB.a.o() != 0) {
            v3VarB.setPendingCount(v3VarB.a.o() - 1);
            int i = 0;
            int iCount = 0;
            while (i < v3VarB.a.o() - 1) {
                v3 v3VarB2 = v3VarB.b(i, v3VarB.b + iCount);
                iCount = (int) (v3VarB2.a.count() + ((long) iCount));
                v3VarB2.fork();
                i++;
            }
            v3VarB = v3VarB.b(i, v3VarB.b + iCount);
        }
        v3VarB.a();
        v3VarB.propagateCompletion();
    }
}
