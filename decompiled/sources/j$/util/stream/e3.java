package j$.util.stream;

import j$.util.Spliterator;
import java.util.Deque;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e3 extends g3 implements Spliterator.OfPrimitive {
    @Override // j$.util.Spliterator.OfPrimitive
    public final boolean tryAdvance(Object obj) {
        c2 c2Var;
        if (!c()) {
            return false;
        }
        boolean zTryAdvance = ((Spliterator.OfPrimitive) this.d).tryAdvance(obj);
        if (!zTryAdvance) {
            if (this.c == null && (c2Var = (c2) g3.a(this.e)) != null) {
                Spliterator.OfPrimitive ofPrimitiveSpliterator = c2Var.spliterator();
                this.d = ofPrimitiveSpliterator;
                return ofPrimitiveSpliterator.tryAdvance(obj);
            }
            this.a = null;
        }
        return zTryAdvance;
    }

    @Override // j$.util.Spliterator.OfPrimitive
    public final void forEachRemaining(Object obj) {
        if (this.a == null) {
            return;
        }
        if (this.d == null) {
            Spliterator spliterator = this.c;
            if (spliterator == null) {
                Deque dequeB = b();
                while (true) {
                    c2 c2Var = (c2) g3.a(dequeB);
                    if (c2Var != null) {
                        c2Var.g(obj);
                    } else {
                        this.a = null;
                        return;
                    }
                }
            } else {
                ((Spliterator.OfPrimitive) spliterator).forEachRemaining(obj);
            }
        } else {
            while (tryAdvance(obj)) {
            }
        }
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
        forEachRemaining((Object) intConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
        return tryAdvance((Object) intConsumer);
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
        forEachRemaining((Object) longConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
        return tryAdvance((Object) longConsumer);
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
        forEachRemaining((Object) doubleConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
        return tryAdvance((Object) doubleConsumer);
    }
}
