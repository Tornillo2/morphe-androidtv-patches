package j$.util.stream;

import j$.util.Optional;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.OptionalLong;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Predicate$CC;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class n implements ToDoubleFunction, IntFunction, DoubleBinaryOperator, Supplier, ObjDoubleConsumer, Predicate, ToIntFunction, IntBinaryOperator, ObjIntConsumer, BiConsumer, ObjLongConsumer, LongBinaryOperator, ToLongFunction {
    public final /* synthetic */ int a;

    public /* synthetic */ n(int i) {
        this.a = i;
    }

    @Override // java.util.function.ObjLongConsumer
    public void accept(Object obj, long j) {
        ((j$.util.c0) obj).accept(j);
    }

    public /* synthetic */ Predicate and(Predicate predicate) {
        switch (this.a) {
        }
        return Predicate$CC.$default$and(this, predicate);
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.a) {
            case 19:
                break;
            case 24:
                break;
        }
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }

    @Override // java.util.function.DoubleBinaryOperator
    public double applyAsDouble(double d, double d2) {
        return Math.max(d, d2);
    }

    @Override // java.util.function.IntBinaryOperator
    public int applyAsInt(int i, int i2) {
        switch (this.a) {
            case 17:
                return Math.min(i, i2);
            case 20:
                return i + i2;
            default:
                return Math.max(i, i2);
        }
    }

    @Override // java.util.function.LongBinaryOperator
    public long applyAsLong(long j, long j2) {
        return Math.min(j, j2);
    }

    public /* synthetic */ Predicate negate() {
        switch (this.a) {
        }
        return Predicate$CC.$default$negate(this);
    }

    public /* synthetic */ Predicate or(Predicate predicate) {
        switch (this.a) {
        }
        return Predicate$CC.$default$or(this, predicate);
    }

    @Override // java.util.function.Predicate
    public boolean test(Object obj) {
        switch (this.a) {
            case 5:
                return ((OptionalDouble) obj).isPresent();
            case 6:
            case 8:
            default:
                return ((Optional) obj).isPresent();
            case 7:
                return ((OptionalInt) obj).isPresent();
            case 9:
                return ((OptionalLong) obj).isPresent();
        }
    }

    @Override // java.util.function.ToDoubleFunction
    public double applyAsDouble(Object obj) {
        return ((Double) obj).doubleValue();
    }

    @Override // java.util.function.ToLongFunction
    public long applyAsLong(Object obj) {
        return ((Long) obj).longValue();
    }

    @Override // java.util.function.ObjDoubleConsumer
    public void accept(Object obj, double d) {
        double[] dArr = (double[]) obj;
        Collectors.a(dArr, d);
        dArr[2] = dArr[2] + d;
    }

    @Override // java.util.function.ToIntFunction
    public int applyAsInt(Object obj) {
        return ((Integer) obj).intValue();
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.a) {
            case 3:
                return new double[3];
            case 6:
                return new d0();
            case 8:
                return new e0();
            case 10:
                return new f0();
            case 12:
                return new g0();
            default:
                return new long[2];
        }
    }

    @Override // java.util.function.ObjIntConsumer
    public void accept(Object obj, int i) {
        switch (this.a) {
            case 18:
                ((j$.util.a0) obj).accept(i);
                break;
            default:
                long[] jArr = (long[]) obj;
                jArr[0] = jArr[0] + 1;
                jArr[1] = jArr[1] + ((long) i);
                break;
        }
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        switch (this.a) {
            case 19:
                ((j$.util.a0) obj).a((j$.util.a0) obj2);
                break;
            case 24:
                long[] jArr = (long[]) obj;
                long[] jArr2 = (long[]) obj2;
                jArr[0] = jArr[0] + jArr2[0];
                jArr[1] = jArr[1] + jArr2[1];
                break;
            default:
                ((j$.util.c0) obj).a((j$.util.c0) obj2);
                break;
        }
    }

    @Override // java.util.function.IntFunction
    public Object apply(int i) {
        switch (this.a) {
            case 1:
                return new Double[i];
            case 13:
                return new Object[i];
            case 14:
                return new Integer[i];
            case 16:
                return Integer.valueOf(i);
            default:
                return new Long[i];
        }
    }
}
