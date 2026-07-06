package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j8 {
    public final Spliterator a;
    public final boolean b;
    public final int c;
    public final long d;
    public final AtomicLong e;

    public abstract Spliterator b(Spliterator spliterator);

    public j8(Spliterator spliterator, long j, long j2) {
        this.a = spliterator;
        this.b = j2 < 0;
        this.d = j2 >= 0 ? j2 : 0L;
        this.c = 128;
        this.e = new AtomicLong(j2 >= 0 ? j + j2 : j);
    }

    public j8(Spliterator spliterator, j8 j8Var) {
        this.a = spliterator;
        this.b = j8Var.b;
        this.e = j8Var.e;
        this.d = j8Var.d;
        this.c = j8Var.c;
    }

    public final long a(long j) {
        long j2;
        boolean z;
        long jMin;
        do {
            j2 = this.e.get();
            z = this.b;
            if (j2 != 0) {
                jMin = Math.min(j2, j);
                if (jMin <= 0) {
                    break;
                }
            } else {
                if (z) {
                    return j;
                }
                return 0L;
            }
        } while (!this.e.compareAndSet(j2, j2 - jMin));
        if (z) {
            return Math.max(j - jMin, 0L);
        }
        long j3 = this.d;
        return j2 > j3 ? Math.max(jMin - (j2 - j3), 0L) : jMin;
    }

    public final i8 f() {
        if (this.e.get() > 0) {
            return i8.MAYBE_MORE;
        }
        return this.b ? i8.UNLIMITED : i8.NO_MORE;
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public final Spliterator m575trySplit() {
        Spliterator spliteratorTrySplit;
        if (this.e.get() == 0 || (spliteratorTrySplit = this.a.trySplit()) == null) {
            return null;
        }
        return b(spliteratorTrySplit);
    }

    public final long estimateSize() {
        return this.a.estimateSize();
    }

    public final int characteristics() {
        return this.a.characteristics() & (-16465);
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Spliterator.OfPrimitive m574trySplit() {
        return (Spliterator.OfPrimitive) m575trySplit();
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Spliterator.OfInt m572trySplit() {
        return (Spliterator.OfInt) m575trySplit();
    }

    /* JADX INFO: renamed from: trySplit, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Spliterator.OfLong m573trySplit() {
        return (Spliterator.OfLong) m575trySplit();
    }

    public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) m575trySplit();
    }
}
