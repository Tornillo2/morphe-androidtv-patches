package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class q8 extends n8 implements LongConsumer, Spliterator.OfLong {
    public long b;
    public w6 c;

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.g(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.u(this, consumer);
    }

    @Override // j$.util.stream.n8, j$.util.Spliterator
    public final /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
        return null;
    }

    @Override // j$.util.stream.n8, j$.util.Spliterator
    public final /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
        return null;
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j) {
        int i = this.a;
        if (i == 0) {
            this.b = j;
            this.a = i + 1;
        } else {
            if (i > 0) {
                if (this.c == null) {
                    w6 w6Var = new w6();
                    this.c = w6Var;
                    w6Var.accept(this.b);
                    this.a++;
                }
                this.c.accept(j);
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        if (this.a != -2) {
            return false;
        }
        longConsumer.accept(this.b);
        this.a = -1;
        return true;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        if (this.a == -2) {
            longConsumer.accept(this.b);
            this.a = -1;
        }
    }
}
