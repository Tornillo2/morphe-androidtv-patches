package j$.util.stream;

import j$.util.function.BiConsumer$CC;
import j$.util.function.BiFunction$CC;
import j$.util.function.Consumer$CC;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class z0 implements LongFunction, Supplier, ObjLongConsumer, BiConsumer, LongBinaryOperator, Consumer, IntFunction, BinaryOperator {
    public final /* synthetic */ int a;

    public /* synthetic */ z0(int i) {
        this.a = i;
    }

    private final void accept$j$$util$stream$Node$$ExternalSyntheticLambda0(Object obj) {
    }

    private final void accept$j$$util$stream$StreamSpliterators$SliceSpliterator$OfRef$$ExternalSyntheticLambda0(Object obj) {
    }

    private final void accept$j$$util$stream$StreamSpliterators$SliceSpliterator$OfRef$$ExternalSyntheticLambda1(Object obj) {
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        int i = this.a;
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        switch (this.a) {
        }
        return BiFunction$CC.$default$andThen(this, function);
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
            case 6:
                break;
            case 19:
                break;
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j) {
        switch (this.a) {
            case 0:
                return Long.valueOf(j);
            case 8:
                return w3.e0(j);
            case 10:
                return w3.n0(j);
            default:
                return w3.o0(j);
        }
    }

    @Override // java.util.function.LongBinaryOperator
    public long applyAsLong(long j, long j2) {
        switch (this.a) {
            case 4:
                return Math.max(j, j2);
            default:
                return j + j2;
        }
    }

    @Override // java.util.function.Supplier
    public Object get() {
        return new long[2];
    }

    @Override // java.util.function.ObjLongConsumer
    public void accept(Object obj, long j) {
        long[] jArr = (long[]) obj;
        jArr[0] = jArr[0] + 1;
        jArr[1] = jArr[1] + j;
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        long[] jArr = (long[]) obj;
        long[] jArr2 = (long[]) obj2;
        jArr[0] = jArr[0] + jArr2[0];
        jArr[1] = jArr[1] + jArr2[1];
    }

    @Override // java.util.function.IntFunction
    public Object apply(int i) {
        switch (this.a) {
            case 7:
                return new Object[i];
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 19:
            case 20:
            default:
                return new Double[i];
            case 15:
                return new Object[i];
            case 16:
                return new Integer[i];
            case 17:
                return new Long[i];
            case 18:
                return new Double[i];
            case 21:
                return new Integer[i];
            case 22:
                return new Integer[i];
            case 23:
                return new Long[i];
            case 24:
                return new Long[i];
            case 25:
                return new Double[i];
        }
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        switch (this.a) {
            case 9:
                return new k2((x1) obj, (x1) obj2);
            case 10:
            case 12:
            default:
                return new o2((d2) obj, (d2) obj2);
            case 11:
                return new l2((z1) obj, (z1) obj2);
            case 13:
                return new m2((b2) obj, (b2) obj2);
        }
    }
}
