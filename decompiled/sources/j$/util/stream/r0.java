package j$.util.stream;

import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final class r0 extends i5 {
    public final /* synthetic */ int b;
    public final /* synthetic */ a c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r0(a aVar, o5 o5Var, int i) {
        super(o5Var);
        this.b = i;
        this.c = aVar;
    }

    @Override // j$.util.stream.i5, j$.util.stream.o5
    public void c(long j) {
        switch (this.b) {
            case 5:
                this.a.c(-1L);
                break;
            default:
                super.c(j);
                break;
        }
    }

    @Override // j$.util.stream.m5, j$.util.stream.o5
    public final void accept(int i) {
        switch (this.b) {
            case 0:
                this.a.accept(((IntFunction) ((q) this.c).u).apply(i));
                return;
            case 1:
                ((IntConsumer) ((s0) this.c).u).accept(i);
                this.a.accept(i);
                return;
            case 2:
                ((s) this.c).getClass();
                IntUnaryOperator intUnaryOperator = null;
                intUnaryOperator.applyAsInt(i);
                throw null;
            case 3:
                ((t) this.c).getClass();
                IntToLongFunction intToLongFunction = null;
                intToLongFunction.applyAsLong(i);
                throw null;
            case 4:
                ((r) this.c).getClass();
                IntToDoubleFunction intToDoubleFunction = null;
                intToDoubleFunction.applyAsDouble(i);
                throw null;
            default:
                ((s) this.c).getClass();
                IntPredicate intPredicate = null;
                intPredicate.test(i);
                throw null;
        }
    }
}
