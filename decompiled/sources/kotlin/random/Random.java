package kotlin.random;

import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline0;
import java.io.Serializable;
import kotlin.SinceKotlin;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@SourceDebugExtension({"SMAP\nRandom.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Random.kt\nkotlin/random/Random\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,383:1\n1#2:384\n*E\n"})
public abstract class Random {

    @NotNull
    public static final Default Default = new Default();

    @NotNull
    public static final Random defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Default extends Random implements Serializable {

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Serialized implements Serializable {

            @NotNull
            public static final Serialized INSTANCE = new Serialized();
            public static final long serialVersionUID = 0;

            public final Object readResolve() {
                return Random.Default;
            }
        }

        public Default() {
        }

        @Override // kotlin.random.Random
        public int nextBits(int i) {
            return Random.defaultRandom.nextBits(i);
        }

        @Override // kotlin.random.Random
        public boolean nextBoolean() {
            return Random.defaultRandom.nextBoolean();
        }

        @Override // kotlin.random.Random
        @NotNull
        public byte[] nextBytes(int i) {
            return Random.defaultRandom.nextBytes(i);
        }

        @Override // kotlin.random.Random
        public double nextDouble() {
            return Random.defaultRandom.nextDouble();
        }

        @Override // kotlin.random.Random
        public float nextFloat() {
            return Random.defaultRandom.nextFloat();
        }

        @Override // kotlin.random.Random
        public int nextInt() {
            return Random.defaultRandom.nextInt();
        }

        @Override // kotlin.random.Random
        public long nextLong() {
            return Random.defaultRandom.nextLong();
        }

        public final Object writeReplace() {
            return Serialized.INSTANCE;
        }

        public Default(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Override // kotlin.random.Random
        @NotNull
        public byte[] nextBytes(@NotNull byte[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            return Random.defaultRandom.nextBytes(array);
        }

        @Override // kotlin.random.Random
        public double nextDouble(double d) {
            return Random.defaultRandom.nextDouble(d);
        }

        @Override // kotlin.random.Random
        public int nextInt(int i) {
            return Random.defaultRandom.nextInt(i);
        }

        @Override // kotlin.random.Random
        public long nextLong(long j) {
            return Random.defaultRandom.nextLong(j);
        }

        @Override // kotlin.random.Random
        @NotNull
        public byte[] nextBytes(@NotNull byte[] array, int i, int i2) {
            Intrinsics.checkNotNullParameter(array, "array");
            return Random.defaultRandom.nextBytes(array, i, i2);
        }

        @Override // kotlin.random.Random
        public double nextDouble(double d, double d2) {
            return Random.defaultRandom.nextDouble(d, d2);
        }

        @Override // kotlin.random.Random
        public int nextInt(int i, int i2) {
            return Random.defaultRandom.nextInt(i, i2);
        }

        @Override // kotlin.random.Random
        public long nextLong(long j, long j2) {
            return Random.defaultRandom.nextLong(j, j2);
        }
    }

    public static /* synthetic */ byte[] nextBytes$default(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return random.nextBytes(bArr, i, i2);
    }

    public abstract int nextBits(int i);

    public boolean nextBoolean() {
        return nextBits(1) != 0;
    }

    @NotNull
    public byte[] nextBytes(@NotNull byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (i < 0 || i > array.length || i2 < 0 || i2 > array.length) {
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("fromIndex (", i, ") or toIndex (", i2, ") are out of range: 0..");
            sbM.append(array.length);
            sbM.append('.');
            throw new IllegalArgumentException(sbM.toString().toString());
        }
        if (i > i2) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline0.m("fromIndex (", i, ") must be not greater than toIndex (", i2, ").").toString());
        }
        int i3 = (i2 - i) / 4;
        for (int i4 = 0; i4 < i3; i4++) {
            int iNextInt = nextInt();
            array[i] = (byte) iNextInt;
            array[i + 1] = (byte) (iNextInt >>> 8);
            array[i + 2] = (byte) (iNextInt >>> 16);
            array[i + 3] = (byte) (iNextInt >>> 24);
            i += 4;
        }
        int i5 = i2 - i;
        int iNextBits = nextBits(i5 * 8);
        for (int i6 = 0; i6 < i5; i6++) {
            array[i + i6] = (byte) (iNextBits >>> (i6 * 8));
        }
        return array;
    }

    public double nextDouble() {
        return PlatformRandomKt.doubleFromParts(nextBits(26), nextBits(27));
    }

    public float nextFloat() {
        return nextBits(24) / 1.6777216E7f;
    }

    public int nextInt() {
        return nextBits(32);
    }

    public long nextLong() {
        return (((long) nextInt()) << 32) + ((long) nextInt());
    }

    public double nextDouble(double d) {
        return nextDouble(0.0d, d);
    }

    public int nextInt(int i) {
        return nextInt(0, i);
    }

    public long nextLong(long j) {
        return nextLong(0L, j);
    }

    public double nextDouble(double d, double d2) {
        double dNextDouble;
        RandomKt.checkRangeBounds(d, d2);
        double d3 = d2 - d;
        if (Double.isInfinite(d3) && Math.abs(d) <= Double.MAX_VALUE && Math.abs(d2) <= Double.MAX_VALUE) {
            double d4 = 2;
            double dNextDouble2 = ((d2 / d4) - (d / d4)) * nextDouble();
            dNextDouble = d + dNextDouble2 + dNextDouble2;
        } else {
            dNextDouble = d + (nextDouble() * d3);
        }
        return dNextDouble >= d2 ? Math.nextAfter(d2, Double.NEGATIVE_INFINITY) : dNextDouble;
    }

    public int nextInt(int i, int i2) {
        int iNextInt;
        int i3;
        int iNextBits;
        RandomKt.checkRangeBounds(i, i2);
        int i4 = i2 - i;
        if (i4 > 0 || i4 == Integer.MIN_VALUE) {
            if (((-i4) & i4) == i4) {
                iNextBits = nextBits(RandomKt.fastLog2(i4));
            } else {
                do {
                    iNextInt = nextInt() >>> 1;
                    i3 = iNextInt % i4;
                } while ((i4 - 1) + (iNextInt - i3) < 0);
                iNextBits = i3;
            }
            return i + iNextBits;
        }
        while (true) {
            int iNextInt2 = nextInt();
            if (i <= iNextInt2 && iNextInt2 < i2) {
                return iNextInt2;
            }
        }
    }

    public long nextLong(long j, long j2) {
        long jNextLong;
        long j3;
        long jNextBits;
        int iNextInt;
        RandomKt.checkRangeBounds(j, j2);
        long j4 = j2 - j;
        if (j4 > 0) {
            if (((-j4) & j4) == j4) {
                int i = (int) j4;
                int i2 = (int) (j4 >>> 32);
                if (i != 0) {
                    iNextInt = nextBits(RandomKt.fastLog2(i));
                } else if (i2 == 1) {
                    iNextInt = nextInt();
                } else {
                    jNextBits = (((long) nextBits(RandomKt.fastLog2(i2))) << 32) + (((long) nextInt()) & 4294967295L);
                }
                jNextBits = ((long) iNextInt) & 4294967295L;
            } else {
                do {
                    jNextLong = nextLong() >>> 1;
                    j3 = jNextLong % j4;
                } while ((j4 - 1) + (jNextLong - j3) < 0);
                jNextBits = j3;
            }
            return j + jNextBits;
        }
        while (true) {
            long jNextLong2 = nextLong();
            if (j <= jNextLong2 && jNextLong2 < j2) {
                return jNextLong2;
            }
        }
    }

    @NotNull
    public byte[] nextBytes(@NotNull byte[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return nextBytes(array, 0, array.length);
    }

    @NotNull
    public byte[] nextBytes(int i) {
        return nextBytes(new byte[i]);
    }
}
