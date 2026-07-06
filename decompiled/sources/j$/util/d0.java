package j$.util;

import j$.util.function.DoubleConsumer$CC;
import j$.util.stream.o5;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class d0 implements DoubleConsumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Consumer b;

    public /* synthetic */ d0(Consumer consumer, int i) {
        this.a = i;
        this.b = consumer;
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        switch (this.a) {
            case 0:
                this.b.accept(Double.valueOf(d));
                break;
            default:
                ((o5) this.b).accept(d);
                break;
        }
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        switch (this.a) {
        }
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }
}
