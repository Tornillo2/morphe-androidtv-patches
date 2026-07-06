package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.LongConsumer$CC;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class m3 extends o3 implements n5 {
    public final long[] h;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        l((Long) obj);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.n5
    public final /* synthetic */ void l(Long l) {
        w3.H(this, l);
    }

    public m3(Spliterator spliterator, w3 w3Var, long[] jArr) {
        super(spliterator, w3Var, jArr.length);
        this.h = jArr;
    }

    public m3(m3 m3Var, Spliterator spliterator, long j, long j2) {
        super(m3Var, spliterator, j, j2, m3Var.h.length);
        this.h = m3Var.h;
    }

    @Override // j$.util.stream.o3
    public final o3 a(Spliterator spliterator, long j, long j2) {
        return new m3(this, spliterator, j, j2);
    }

    @Override // j$.util.stream.o3, j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        int i = this.f;
        if (i >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        long[] jArr = this.h;
        this.f = i + 1;
        jArr[i] = j;
    }
}
