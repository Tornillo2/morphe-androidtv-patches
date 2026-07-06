package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class p8 extends n8 implements IntConsumer, Spliterator.OfInt {
    public int b;
    public u6 c;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.f(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.t(this, consumer);
    }

    @Override // j$.util.stream.n8, j$.util.Spliterator
    public final /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
        return null;
    }

    @Override // j$.util.stream.n8, j$.util.Spliterator
    public final /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
        return null;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        int i2 = this.a;
        if (i2 == 0) {
            this.b = i;
            this.a = i2 + 1;
        } else {
            if (i2 > 0) {
                if (this.c == null) {
                    u6 u6Var = new u6();
                    this.c = u6Var;
                    u6Var.accept(this.b);
                    this.a++;
                }
                this.c.accept(i);
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        if (this.a != -2) {
            return false;
        }
        intConsumer.accept(this.b);
        this.a = -1;
        return true;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        if (this.a == -2) {
            intConsumer.accept(this.b);
            this.a = -1;
        }
    }
}
