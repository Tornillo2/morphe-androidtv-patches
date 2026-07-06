package j$.util.stream;

import j$.util.Spliterator;
import java.util.Deque;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class f3 extends g3 {
    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        d2 d2VarA;
        if (!c()) {
            return false;
        }
        boolean zTryAdvance = this.d.tryAdvance(consumer);
        if (!zTryAdvance) {
            if (this.c == null && (d2VarA = g3.a(this.e)) != null) {
                Spliterator spliterator = d2VarA.spliterator();
                this.d = spliterator;
                return spliterator.tryAdvance(consumer);
            }
            this.a = null;
        }
        return zTryAdvance;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        if (this.a == null) {
            return;
        }
        if (this.d == null) {
            Spliterator spliterator = this.c;
            if (spliterator == null) {
                Deque dequeB = b();
                while (true) {
                    d2 d2VarA = g3.a(dequeB);
                    if (d2VarA != null) {
                        d2VarA.forEach(consumer);
                    } else {
                        this.a = null;
                        return;
                    }
                }
            } else {
                spliterator.forEachRemaining(consumer);
            }
        } else {
            while (tryAdvance(consumer)) {
            }
        }
    }
}
