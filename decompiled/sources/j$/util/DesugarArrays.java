package j$.util;

import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.StreamSupport;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class DesugarArrays {
    public static IntStream stream(int[] iArr, int i, int i2) {
        return StreamSupport.intStream(Spliterators.spliterator(iArr, i, i2, 1040), false);
    }

    public static LongStream stream(long[] jArr, int i, int i2) {
        return StreamSupport.longStream(Spliterators.spliterator(jArr, i, i2, 1040), false);
    }

    public static DoubleStream stream(double[] dArr, int i, int i2) {
        return StreamSupport.doubleStream(Spliterators.spliterator(dArr, i, i2, 1040), false);
    }
}
