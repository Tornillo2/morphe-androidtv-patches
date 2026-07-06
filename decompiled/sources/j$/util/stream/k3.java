package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.DoubleConsumer$CC;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class k3 extends o3 implements l5 {
    public final double[] h;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        n((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return DoubleConsumer$CC.$default$andThen(this, doubleConsumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void n(Double d) {
        w3.D(this, d);
    }

    public k3(Spliterator spliterator, w3 w3Var, double[] dArr) {
        super(spliterator, w3Var, dArr.length);
        this.h = dArr;
    }

    public k3(k3 k3Var, Spliterator spliterator, long j, long j2) {
        super(k3Var, spliterator, j, j2, k3Var.h.length);
        this.h = k3Var.h;
    }

    @Override // j$.util.stream.o3
    public final o3 a(Spliterator spliterator, long j, long j2) {
        return new k3(this, spliterator, j, j2);
    }

    @Override // j$.util.stream.o3, j$.util.stream.o5
    public final void accept(double d) {
        int i = this.f;
        if (i >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        double[] dArr = this.h;
        this.f = i + 1;
        dArr[i] = d;
    }
}
