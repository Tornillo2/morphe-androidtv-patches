package j$.util.stream;

import j$.util.OptionalLong;
import j$.util.function.LongConsumer$CC;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class f0 extends h0 implements n5 {
    public static final c0 c;
    public static final c0 d;

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.h0, j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        n(Long.valueOf(j));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return OptionalLong.of(((Long) this.b).longValue());
        }
        return null;
    }

    static {
        d7 d7Var = d7.LONG_VALUE;
        c = new c0(true, d7Var, OptionalLong.empty(), new n(9), new n(10));
        d = new c0(false, d7Var, OptionalLong.empty(), new n(9), new n(10));
    }
}
