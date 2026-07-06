package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.IntConsumer$CC;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class l3 extends o3 implements m5 {
    public final int[] h;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        d((Integer) obj);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.m5
    public final /* synthetic */ void d(Integer num) {
        w3.F(this, num);
    }

    public l3(Spliterator spliterator, w3 w3Var, int[] iArr) {
        super(spliterator, w3Var, iArr.length);
        this.h = iArr;
    }

    public l3(l3 l3Var, Spliterator spliterator, long j, long j2) {
        super(l3Var, spliterator, j, j2, l3Var.h.length);
        this.h = l3Var.h;
    }

    @Override // j$.util.stream.o3
    public final o3 a(Spliterator spliterator, long j, long j2) {
        return new l3(this, spliterator, j, j2);
    }

    @Override // j$.util.stream.o3, j$.util.stream.o5
    public final void accept(int i) {
        int i2 = this.f;
        if (i2 >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        int[] iArr = this.h;
        this.f = i2 + 1;
        iArr[i2] = i;
    }
}
