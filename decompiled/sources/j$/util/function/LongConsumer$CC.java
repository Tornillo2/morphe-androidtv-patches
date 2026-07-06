package j$.util.function;

import j$.util.Objects;
import java.util.function.LongConsumer;

/* JADX INFO: renamed from: j$.util.function.LongConsumer$-CC, reason: invalid class name */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class LongConsumer$CC {
    public static LongConsumer $default$andThen(final LongConsumer longConsumer, final LongConsumer longConsumer2) {
        Objects.requireNonNull(longConsumer2);
        return new LongConsumer() { // from class: j$.util.function.e
            public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer3) {
                return LongConsumer$CC.$default$andThen(this, longConsumer3);
            }

            @Override // java.util.function.LongConsumer
            public final void accept(long j) {
                longConsumer.accept(j);
                longConsumer2.accept(j);
            }
        };
    }
}
