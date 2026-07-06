package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.concurrent.CountedCompleter;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class o3 extends CountedCompleter implements o5 {
    public final Spliterator a;
    public final w3 b;
    public final long c;
    public final long d;
    public final long e;
    public int f;
    public int g;

    public abstract o3 a(Spliterator spliterator, long j, long j2);

    public /* synthetic */ void accept(double d) {
        w3.C();
        throw null;
    }

    public /* synthetic */ void accept(int i) {
        w3.J();
        throw null;
    }

    public /* synthetic */ void accept(long j) {
        w3.K();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ boolean e() {
        return false;
    }

    @Override // j$.util.stream.o5
    public final /* synthetic */ void end() {
    }

    public o3(Spliterator spliterator, w3 w3Var, int i) {
        this.a = spliterator;
        this.b = w3Var;
        this.c = d.e(spliterator.estimateSize());
        this.d = 0L;
        this.e = i;
    }

    public o3(o3 o3Var, Spliterator spliterator, long j, long j2, int i) {
        super(o3Var);
        this.a = spliterator;
        this.b = o3Var.b;
        this.c = o3Var.c;
        this.d = j;
        this.e = j2;
        if (j < 0 || j2 < 0 || (j + j2) - 1 >= i) {
            throw new IllegalArgumentException(String.format("offset and length interval [%d, %d + %d) is not within array size interval [0, %d)", Long.valueOf(j), Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i)));
        }
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.a;
        o3 o3VarA = this;
        while (spliterator.estimateSize() > o3VarA.c && (spliteratorTrySplit = spliterator.trySplit()) != null) {
            o3VarA.setPendingCount(1);
            long jEstimateSize = spliteratorTrySplit.estimateSize();
            o3 o3Var = o3VarA;
            o3Var.a(spliteratorTrySplit, o3VarA.d, jEstimateSize).fork();
            o3VarA = o3Var.a(spliterator, o3Var.d + jEstimateSize, o3Var.e - jEstimateSize);
        }
        o3 o3Var2 = o3VarA;
        o3Var2.b.v0(spliterator, o3Var2);
        o3Var2.propagateCompletion();
    }

    @Override // j$.util.stream.o5
    public final void c(long j) {
        long j2 = this.e;
        if (j > j2) {
            throw new IllegalStateException("size passed to Sink.begin exceeds array length");
        }
        int i = (int) this.d;
        this.f = i;
        this.g = i + ((int) j2);
    }
}
