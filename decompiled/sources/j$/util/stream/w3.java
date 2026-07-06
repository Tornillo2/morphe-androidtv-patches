package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public abstract class w3 implements t8 {
    public static final v2 a = new v2();
    public static final t2 b = new t2();
    public static final u2 c = new u2();
    public static final s2 d = new s2();
    public static final int[] e = new int[0];
    public static final long[] f = new long[0];
    public static final double[] g = new double[0];

    public abstract void c0(Spliterator spliterator, o5 o5Var);

    public abstract boolean d0(Spliterator spliterator, o5 o5Var);

    public abstract d2 g0(Spliterator spliterator, boolean z, IntFunction intFunction);

    public abstract long h0(Spliterator spliterator);

    public abstract v1 s0(long j, IntFunction intFunction);

    public abstract r4 u0();

    @Override // j$.util.stream.t8
    public /* synthetic */ int v() {
        return 0;
    }

    public abstract o5 v0(Spliterator spliterator, o5 o5Var);

    public abstract o5 w0(o5 o5Var);

    public abstract Spliterator x0(Spliterator spliterator);

    public static j$.util.q m0(Function function) {
        j$.util.q qVar = new j$.util.q(6);
        qVar.b = function;
        return qVar;
    }

    public static j$.time.format.v t0(q1 q1Var, Predicate predicate) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(q1Var);
        return new j$.time.format.v(d7.REFERENCE, q1Var, new j$.time.format.v(6, q1Var, predicate));
    }

    public static w2 f0(d7 d7Var) {
        int i = e2.a[d7Var.ordinal()];
        if (i == 1) {
            return a;
        }
        if (i == 2) {
            return b;
        }
        if (i == 3) {
            return c;
        }
        if (i == 4) {
            return d;
        }
        throw new IllegalStateException("Unknown shape " + d7Var);
    }

    public static j$.time.format.v q0(q1 q1Var) {
        Objects.requireNonNull(null);
        Objects.requireNonNull(q1Var);
        return new j$.time.format.v(d7.INT_VALUE, q1Var, new k1(q1Var, 1));
    }

    public static d2 V(d2 d2Var, long j, long j2, IntFunction intFunction) {
        if (j == 0 && j2 == d2Var.count()) {
            return d2Var;
        }
        Spliterator spliterator = d2Var.spliterator();
        long j3 = j2 - j;
        v1 v1VarW = W(j3, intFunction);
        v1VarW.c(j3);
        for (int i = 0; i < j && spliterator.tryAdvance(new z0(6)); i++) {
        }
        if (j2 == d2Var.count()) {
            spliterator.forEachRemaining(v1VarW);
        } else {
            for (int i2 = 0; i2 < j3 && spliterator.tryAdvance(v1VarW); i2++) {
            }
        }
        v1VarW.end();
        return v1VarW.build();
    }

    public static f2 b0(d7 d7Var, d2 d2Var, d2 d2Var2) {
        int i = e2.a[d7Var.ordinal()];
        if (i == 1) {
            return new o2(d2Var, d2Var2);
        }
        if (i == 2) {
            return new l2((z1) d2Var, (z1) d2Var2);
        }
        if (i == 3) {
            return new m2((b2) d2Var, (b2) d2Var2);
        }
        if (i != 4) {
            throw new IllegalStateException("Unknown shape " + d7Var);
        }
        return new k2((x1) d2Var, (x1) d2Var2);
    }

    public static j$.time.format.v r0(q1 q1Var) {
        Objects.requireNonNull(null);
        Objects.requireNonNull(q1Var);
        return new j$.time.format.v(d7.LONG_VALUE, q1Var, new k1(q1Var, 0));
    }

    public static void J() {
        throw new IllegalStateException("called wrong accept method");
    }

    public static j$.time.format.v p0(q1 q1Var) {
        Objects.requireNonNull(null);
        Objects.requireNonNull(q1Var);
        return new j$.time.format.v(d7.DOUBLE_VALUE, q1Var, new k1(q1Var, 2));
    }

    public static void K() {
        throw new IllegalStateException("called wrong accept method");
    }

    public static v1 W(long j, IntFunction intFunction) {
        if (j >= 0 && j < 2147483639) {
            return new x2(j, intFunction);
        }
        return new p3();
    }

    public static void C() {
        throw new IllegalStateException("called wrong accept method");
    }

    public static void F(m5 m5Var, Integer num) {
        if (v8.a) {
            v8.a(m5Var.getClass(), "{0} calling Sink.OfInt.accept(Integer)");
            throw null;
        }
        m5Var.accept(num.intValue());
    }

    public static void H(n5 n5Var, Long l) {
        if (v8.a) {
            v8.a(n5Var.getClass(), "{0} calling Sink.OfLong.accept(Long)");
            throw null;
        }
        n5Var.accept(l.longValue());
    }

    public static t1 n0(long j) {
        if (j < 0 || j >= 2147483639) {
            return new a3();
        }
        return new z2(j);
    }

    public static void D(l5 l5Var, Double d2) {
        if (v8.a) {
            v8.a(l5Var.getClass(), "{0} calling Sink.OfDouble.accept(Double)");
            throw null;
        }
        l5Var.accept(d2.doubleValue());
    }

    public static u1 o0(long j) {
        if (j < 0 || j >= 2147483639) {
            return new j3();
        }
        return new i3(j);
    }

    public static Object[] L(c2 c2Var, IntFunction intFunction) {
        if (v8.a) {
            v8.a(c2Var.getClass(), "{0} calling Node.OfPrimitive.asArray");
            throw null;
        }
        if (c2Var.count() >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) c2Var.count());
        c2Var.k(objArr, 0);
        return objArr;
    }

    public static s1 e0(long j) {
        if (j < 0 || j >= 2147483639) {
            return new r2();
        }
        return new q2(j);
    }

    public static d2 X(w3 w3Var, Spliterator spliterator, boolean z, IntFunction intFunction) {
        long jH0 = w3Var.h0(spliterator);
        if (jH0 < 0 || !spliterator.hasCharacteristics(16384)) {
            j0 j0Var = new j0();
            j0Var.a = intFunction;
            d2 d2Var = (d2) new i2(w3Var, spliterator, j0Var, new z0(14), 3).invoke();
            return z ? i0(d2Var, intFunction) : d2Var;
        }
        if (jH0 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) jH0);
        new n3(spliterator, w3Var, objArr).invoke();
        return new g2(objArr);
    }

    public static void Q(z1 z1Var, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            z1Var.g((IntConsumer) consumer);
        } else {
            if (v8.a) {
                v8.a(z1Var.getClass(), "{0} calling Node.OfInt.forEachRemaining(Consumer)");
                throw null;
            }
            ((Spliterator.OfInt) z1Var.spliterator()).forEachRemaining(consumer);
        }
    }

    public static void N(z1 z1Var, Integer[] numArr, int i) {
        if (v8.a) {
            v8.a(z1Var.getClass(), "{0} calling Node.OfInt.copyInto(Integer[], int)");
            throw null;
        }
        int[] iArr = (int[]) z1Var.b();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            numArr[i + i2] = Integer.valueOf(iArr[i2]);
        }
    }

    public static z1 T(z1 z1Var, long j, long j2) {
        if (j == 0 && j2 == z1Var.count()) {
            return z1Var;
        }
        long j3 = j2 - j;
        Spliterator.OfInt ofInt = (Spliterator.OfInt) z1Var.spliterator();
        t1 t1VarN0 = n0(j3);
        t1VarN0.c(j3);
        for (int i = 0; i < j && ofInt.tryAdvance((IntConsumer) new y1(0)); i++) {
        }
        if (j2 == z1Var.count()) {
            ofInt.forEachRemaining((IntConsumer) t1VarN0);
        } else {
            for (int i2 = 0; i2 < j3 && ofInt.tryAdvance((IntConsumer) t1VarN0); i2++) {
            }
        }
        t1VarN0.end();
        return t1VarN0.build();
    }

    public static z1 Z(w3 w3Var, Spliterator spliterator, boolean z) {
        long jH0 = w3Var.h0(spliterator);
        if (jH0 < 0 || !spliterator.hasCharacteristics(16384)) {
            z1 z1Var = (z1) new i2(w3Var, spliterator, new z0(10), new z0(11), 1).invoke();
            return z ? k0(z1Var) : z1Var;
        }
        if (jH0 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        int[] iArr = new int[(int) jH0];
        new l3(spliterator, w3Var, iArr).invoke();
        return new y2(iArr);
    }

    public static b2 a0(w3 w3Var, Spliterator spliterator, boolean z) {
        long jH0 = w3Var.h0(spliterator);
        if (jH0 < 0 || !spliterator.hasCharacteristics(16384)) {
            b2 b2Var = (b2) new i2(w3Var, spliterator, new z0(12), new z0(13), 2).invoke();
            return z ? l0(b2Var) : b2Var;
        }
        if (jH0 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        long[] jArr = new long[(int) jH0];
        new m3(spliterator, w3Var, jArr).invoke();
        return new h3(jArr);
    }

    public static void R(b2 b2Var, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            b2Var.g((LongConsumer) consumer);
        } else {
            if (v8.a) {
                v8.a(b2Var.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                throw null;
            }
            ((Spliterator.OfLong) b2Var.spliterator()).forEachRemaining(consumer);
        }
    }

    public static void O(b2 b2Var, Long[] lArr, int i) {
        if (v8.a) {
            v8.a(b2Var.getClass(), "{0} calling Node.OfInt.copyInto(Long[], int)");
            throw null;
        }
        long[] jArr = (long[]) b2Var.b();
        for (int i2 = 0; i2 < jArr.length; i2++) {
            lArr[i + i2] = Long.valueOf(jArr[i2]);
        }
    }

    public static b2 U(b2 b2Var, long j, long j2) {
        if (j == 0 && j2 == b2Var.count()) {
            return b2Var;
        }
        long j3 = j2 - j;
        Spliterator.OfLong ofLong = (Spliterator.OfLong) b2Var.spliterator();
        u1 u1VarO0 = o0(j3);
        u1VarO0.c(j3);
        for (int i = 0; i < j && ofLong.tryAdvance((LongConsumer) new a2(0)); i++) {
        }
        if (j2 == b2Var.count()) {
            ofLong.forEachRemaining((LongConsumer) u1VarO0);
        } else {
            for (int i2 = 0; i2 < j3 && ofLong.tryAdvance((LongConsumer) u1VarO0); i2++) {
            }
        }
        u1VarO0.end();
        return u1VarO0.build();
    }

    public static x1 Y(w3 w3Var, Spliterator spliterator, boolean z) {
        long jH0 = w3Var.h0(spliterator);
        if (jH0 < 0 || !spliterator.hasCharacteristics(16384)) {
            x1 x1Var = (x1) new i2(w3Var, spliterator, new z0(8), new z0(9), 0).invoke();
            return z ? j0(x1Var) : x1Var;
        }
        if (jH0 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        double[] dArr = new double[(int) jH0];
        new k3(spliterator, w3Var, dArr).invoke();
        return new p2(dArr);
    }

    public static d2 i0(d2 d2Var, IntFunction intFunction) {
        if (d2Var.o() <= 0) {
            return d2Var;
        }
        long jCount = d2Var.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) jCount);
        new u3(d2Var, objArr).invoke();
        return new g2(objArr);
    }

    public static void P(x1 x1Var, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            x1Var.g((DoubleConsumer) consumer);
        } else {
            if (v8.a) {
                v8.a(x1Var.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                throw null;
            }
            ((Spliterator.OfDouble) x1Var.spliterator()).forEachRemaining(consumer);
        }
    }

    public static z1 k0(z1 z1Var) {
        if (z1Var.o() <= 0) {
            return z1Var;
        }
        long jCount = z1Var.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        int[] iArr = new int[(int) jCount];
        new r3(z1Var, iArr).invoke();
        return new y2(iArr);
    }

    public static void M(x1 x1Var, Double[] dArr, int i) {
        if (v8.a) {
            v8.a(x1Var.getClass(), "{0} calling Node.OfDouble.copyInto(Double[], int)");
            throw null;
        }
        double[] dArr2 = (double[]) x1Var.b();
        for (int i2 = 0; i2 < dArr2.length; i2++) {
            dArr[i + i2] = Double.valueOf(dArr2[i2]);
        }
    }

    public static x1 S(x1 x1Var, long j, long j2) {
        if (j == 0 && j2 == x1Var.count()) {
            return x1Var;
        }
        long j3 = j2 - j;
        Spliterator.OfDouble ofDouble = (Spliterator.OfDouble) x1Var.spliterator();
        s1 s1VarE0 = e0(j3);
        s1VarE0.c(j3);
        for (int i = 0; i < j && ofDouble.tryAdvance((DoubleConsumer) new w1(0)); i++) {
        }
        if (j2 == x1Var.count()) {
            ofDouble.forEachRemaining((DoubleConsumer) s1VarE0);
        } else {
            for (int i2 = 0; i2 < j3 && ofDouble.tryAdvance((DoubleConsumer) s1VarE0); i2++) {
            }
        }
        s1VarE0.end();
        return s1VarE0.build();
    }

    public static b2 l0(b2 b2Var) {
        if (b2Var.o() <= 0) {
            return b2Var;
        }
        long jCount = b2Var.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        long[] jArr = new long[(int) jCount];
        new s3(b2Var, jArr).invoke();
        return new h3(jArr);
    }

    public static x1 j0(x1 x1Var) {
        if (x1Var.o() <= 0) {
            return x1Var;
        }
        long jCount = x1Var.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        double[] dArr = new double[(int) jCount];
        new q3(x1Var, dArr).invoke();
        return new p2(dArr);
    }

    @Override // j$.util.stream.t8
    public Object f(a aVar, Spliterator spliterator) {
        r4 r4VarU0 = u0();
        aVar.v0(spliterator, r4VarU0);
        return r4VarU0.get();
    }

    @Override // j$.util.stream.t8
    public Object i(w3 w3Var, Spliterator spliterator) {
        return ((r4) new y4(this, w3Var, spliterator).invoke()).get();
    }
}
