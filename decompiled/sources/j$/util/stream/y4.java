package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;

/* JADX INFO: loaded from: classes2.dex */
public final class y4 extends d {
    public final w3 h;

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        d dVar = this.d;
        if (dVar != null) {
            r4 r4Var = (r4) ((y4) dVar).f;
            r4Var.j((r4) ((y4) this.e).f);
            this.f = r4Var;
        }
        super.onCompletion(countedCompleter);
    }

    public y4(w3 w3Var, w3 w3Var2, Spliterator spliterator) {
        super(w3Var2, spliterator);
        this.h = w3Var;
    }

    public y4(y4 y4Var, Spliterator spliterator) {
        super(y4Var, spliterator);
        this.h = y4Var.h;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new y4(this, spliterator);
    }

    @Override // j$.util.stream.d
    public final Object a() {
        w3 w3Var = this.a;
        r4 r4VarU0 = this.h.u0();
        w3Var.v0(this.b, r4VarU0);
        return r4VarU0;
    }
}
