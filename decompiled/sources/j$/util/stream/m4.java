package j$.util.stream;

import java.util.function.IntBinaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final class m4 extends w3 {
    public final /* synthetic */ IntBinaryOperator h;
    public final /* synthetic */ int i;

    @Override // j$.util.stream.w3
    public final r4 u0() {
        return new l4(this.i, this.h);
    }

    public m4(d7 d7Var, IntBinaryOperator intBinaryOperator, int i) {
        this.h = intBinaryOperator;
        this.i = i;
    }
}
