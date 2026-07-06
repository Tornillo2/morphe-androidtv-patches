package j$.util.stream;

import j$.util.Objects;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class s8 extends n8 implements Consumer {
    public Object b;
    public z6 c;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.a;
        if (i == 0) {
            this.b = obj;
            this.a = i + 1;
        } else {
            if (i > 0) {
                if (this.c == null) {
                    z6 z6Var = new z6();
                    this.c = z6Var;
                    z6Var.accept(this.b);
                    this.a++;
                }
                this.c.accept(obj);
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        Objects.requireNonNull(consumer);
        if (this.a != -2) {
            return false;
        }
        consumer.accept(this.b);
        this.a = -1;
        return true;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        Objects.requireNonNull(consumer);
        if (this.a == -2) {
            consumer.accept(this.b);
            this.a = -1;
        }
    }
}
