package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class z7 extends a8 implements Spliterator.OfLong {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.g(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.u(this, consumer);
    }

    @Override // j$.util.stream.c8
    public final Spliterator a(Spliterator spliterator, long j, long j2, long j3, long j4) {
        return new z7((Spliterator.OfLong) spliterator, j, j2, j3, j4);
    }

    @Override // j$.util.stream.a8
    public final Object b() {
        return new a2(1);
    }
}
