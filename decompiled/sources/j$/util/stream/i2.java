package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.BinaryOperator;
import java.util.function.LongFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class i2 extends j2 {
    public final /* synthetic */ int k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i2(w3 w3Var, Spliterator spliterator, LongFunction longFunction, BinaryOperator binaryOperator, int i) {
        super(w3Var, spliterator, longFunction, binaryOperator);
        this.k = i;
    }

    @Override // j$.util.stream.j2, j$.util.stream.d
    public final d c(Spliterator spliterator) {
        switch (this.k) {
        }
        return new j2(this, spliterator);
    }

    @Override // j$.util.stream.j2, j$.util.stream.d
    public final /* bridge */ /* synthetic */ Object a() {
        switch (this.k) {
        }
        return a();
    }
}
