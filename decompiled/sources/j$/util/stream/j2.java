package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.BinaryOperator;
import java.util.function.LongFunction;

/* JADX INFO: loaded from: classes2.dex */
public class j2 extends d {
    public final w3 h;
    public final LongFunction i;
    public final BinaryOperator j;

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        d dVar = this.d;
        if (dVar != null) {
            this.f = (d2) this.j.apply((d2) ((j2) dVar).f, (d2) ((j2) this.e).f);
        }
        super.onCompletion(countedCompleter);
    }

    public j2(w3 w3Var, Spliterator spliterator, LongFunction longFunction, BinaryOperator binaryOperator) {
        super(w3Var, spliterator);
        this.h = w3Var;
        this.i = longFunction;
        this.j = binaryOperator;
    }

    public j2(j2 j2Var, Spliterator spliterator) {
        super(j2Var, spliterator);
        this.h = j2Var.h;
        this.i = j2Var.i;
        this.j = j2Var.j;
    }

    @Override // j$.util.stream.d
    public d c(Spliterator spliterator) {
        return new j2(this, spliterator);
    }

    @Override // j$.util.stream.d
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public final d2 a() {
        v1 v1Var = (v1) this.i.apply(this.h.h0(this.b));
        this.h.v0(this.b, v1Var);
        return v1Var.build();
    }
}
