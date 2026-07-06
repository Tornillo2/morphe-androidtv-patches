package j$.util.stream;

import j$.util.OptionalInt;
import j$.util.function.IntConsumer$CC;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class e0 extends h0 implements m5 {
    public static final c0 c;
    public static final c0 d;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.h0, j$.util.stream.o5
    public final void accept(int i) {
        n(Integer.valueOf(i));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return OptionalInt.of(((Integer) this.b).intValue());
        }
        return null;
    }

    static {
        d7 d7Var = d7.INT_VALUE;
        c = new c0(true, d7Var, OptionalInt.empty(), new n(7), new n(8));
        d = new c0(false, d7Var, OptionalInt.empty(), new n(7), new n(8));
    }
}
