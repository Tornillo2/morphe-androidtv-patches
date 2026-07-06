package com.google.common.hash;

import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.hash.BloomFilterStrategies;
import com.google.common.math.DoubleMath;
import com.google.common.math.LongMath;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import j$.util.stream.Collector;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.function.Supplier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    public static final double LOG_TWO;
    public static final double SQUARED_LOG_TWO;
    public static final long serialVersionUID = 912559;
    public final BloomFilterStrategies.LockFreeBitArray bits;
    public final Funnel<? super T> funnel;
    public final int numHashFunctions;
    public final Strategy strategy;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SerialForm<T> implements Serializable {
        public static final long serialVersionUID = 1;
        public final long[] data;
        public final Funnel<? super T> funnel;
        public final int numHashFunctions;
        public final Strategy strategy;

        public SerialForm(BloomFilter<T> bf) {
            this.data = BloomFilterStrategies.LockFreeBitArray.toPlainArray(bf.bits.data);
            this.numHashFunctions = bf.numHashFunctions;
            this.funnel = bf.funnel;
            this.strategy = bf.strategy;
        }

        public Object readResolve() {
            return new BloomFilter(new BloomFilterStrategies.LockFreeBitArray(this.data), this.numHashFunctions, this.funnel, this.strategy);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Strategy extends Serializable {
        <T> boolean mightContain(@ParametricNullness T object, Funnel<? super T> funnel, int numHashFunctions, BloomFilterStrategies.LockFreeBitArray bits);

        int ordinal();

        <T> boolean put(@ParametricNullness T object, Funnel<? super T> funnel, int numHashFunctions, BloomFilterStrategies.LockFreeBitArray bits);
    }

    /* JADX INFO: renamed from: $r8$lambda$5l-xRjFaIfkWS1H2YPGwqF_qtWA, reason: not valid java name */
    public static /* synthetic */ BloomFilter m549$r8$lambda$5lxRjFaIfkWS1H2YPGwqF_qtWA(BloomFilter bloomFilter, BloomFilter bloomFilter2) {
        bloomFilter.putAll(bloomFilter2);
        return bloomFilter;
    }

    static {
        double dLog = Math.log(2.0d);
        LOG_TWO = dLog;
        SQUARED_LOG_TWO = dLog * dLog;
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions, double fpp) {
        return create(funnel, expectedInsertions, fpp, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    @VisibleForTesting
    public static long optimalNumOfBits(long n, double p) {
        if (p == 0.0d) {
            p = Double.MIN_VALUE;
        }
        return (long) ((Math.log(p) * (-n)) / SQUARED_LOG_TWO);
    }

    @VisibleForTesting
    public static int optimalNumOfHashFunctions(double p) {
        return Math.max(1, (int) Math.round((-Math.log(p)) / LOG_TWO));
    }

    public static <T> BloomFilter<T> readFrom(InputStream in, Funnel<? super T> funnel) throws IOException {
        int i;
        int i2;
        DataInputStream dataInputStream;
        byte b;
        Preconditions.checkNotNull(in, "InputStream");
        Preconditions.checkNotNull(funnel, "Funnel");
        byte b2 = -1;
        try {
            try {
                dataInputStream = new DataInputStream(in);
                b = dataInputStream.readByte();
                try {
                    i2 = dataInputStream.readByte() & 255;
                } catch (Exception e) {
                    e = e;
                    i2 = -1;
                }
            } catch (IOException e2) {
                throw e2;
            }
        } catch (Exception e3) {
            e = e3;
            i = -1;
            i2 = -1;
        }
        try {
            int i3 = dataInputStream.readInt();
            try {
                BloomFilterStrategies bloomFilterStrategies = BloomFilterStrategies.values()[b];
                BloomFilterStrategies.LockFreeBitArray lockFreeBitArray = new BloomFilterStrategies.LockFreeBitArray(LongMath.checkedMultiply(i3, 64L));
                for (int i4 = 0; i4 < i3; i4++) {
                    lockFreeBitArray.putData(i4, dataInputStream.readLong());
                }
                return new BloomFilter<>(lockFreeBitArray, i2, funnel, bloomFilterStrategies);
            } catch (Exception e4) {
                e = e4;
                b2 = b;
                i = i3;
                StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ", b2, " numHashFunctions: ", i2, " dataLength: ");
                sbM.append(i);
                throw new IOException(sbM.toString(), e);
            }
        } catch (Exception e5) {
            e = e5;
            b2 = b;
            i = -1;
            StringBuilder sbM2 = MutableFloatList$$ExternalSyntheticOutline0.m("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ", b2, " numHashFunctions: ", i2, " dataLength: ");
            sbM2.append(i);
            throw new IOException(sbM2.toString(), e);
        }
    }

    @IgnoreJRERequirement
    public static <T> Collector<T, ?, BloomFilter<T>> toBloomFilter(Funnel<? super T> funnel, long expectedInsertions) {
        return toBloomFilter(funnel, expectedInsertions, 0.03d);
    }

    @Override // com.google.common.base.Predicate
    @InlineMe(replacement = "this.mightContain(input)")
    @Deprecated
    public boolean apply(@ParametricNullness T input) {
        return mightContain(input);
    }

    public long approximateElementCount() {
        double dBitSize = this.bits.bitSize();
        return DoubleMath.roundToLong(((-Math.log1p(-(this.bits.bitCount.sum() / dBitSize))) * dBitSize) / ((double) this.numHashFunctions), RoundingMode.HALF_UP);
    }

    @VisibleForTesting
    public long bitSize() {
        return this.bits.bitSize();
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<>(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
    }

    @Override // com.google.common.base.Predicate
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof BloomFilter) {
            BloomFilter bloomFilter = (BloomFilter) object;
            if (this.numHashFunctions == bloomFilter.numHashFunctions && this.funnel.equals(bloomFilter.funnel) && this.bits.equals(bloomFilter.bits) && this.strategy.equals(bloomFilter.strategy)) {
                return true;
            }
        }
        return false;
    }

    public double expectedFpp() {
        return Math.pow(this.bits.bitCount.sum() / this.bits.bitSize(), this.numHashFunctions);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits});
    }

    public boolean isCompatible(BloomFilter<T> that) {
        that.getClass();
        return this != that && this.numHashFunctions == that.numHashFunctions && this.bits.bitSize() == that.bits.bitSize() && this.strategy.equals(that.strategy) && this.funnel.equals(that.funnel);
    }

    public boolean mightContain(@ParametricNullness T object) {
        return this.strategy.mightContain(object, this.funnel, this.numHashFunctions, this.bits);
    }

    @CanIgnoreReturnValue
    public boolean put(@ParametricNullness T object) {
        return this.strategy.put(object, this.funnel, this.numHashFunctions, this.bits);
    }

    public void putAll(BloomFilter<T> that) {
        that.getClass();
        Preconditions.checkArgument(this != that, "Cannot combine a BloomFilter with itself.");
        int i = this.numHashFunctions;
        int i2 = that.numHashFunctions;
        Preconditions.checkArgument(i == i2, "BloomFilters must have the same number of hash functions (%s != %s)", i, i2);
        Preconditions.checkArgument(this.bits.bitSize() == that.bits.bitSize(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", this.bits.bitSize(), that.bits.bitSize());
        Preconditions.checkArgument(this.strategy.equals(that.strategy), "BloomFilters must have equal strategies (%s != %s)", this.strategy, that.strategy);
        Preconditions.checkArgument(this.funnel.equals(that.funnel), "BloomFilters must have equal funnels (%s != %s)", this.funnel, that.funnel);
        this.bits.putAll(that.bits);
    }

    public final void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public final Object writeReplace() {
        return new SerialForm(this);
    }

    public void writeTo(OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        dataOutputStream.writeByte(SignedBytes.checkedCast(this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.checkedCast(this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.data.length());
        for (int i = 0; i < this.bits.data.length(); i++) {
            dataOutputStream.writeLong(this.bits.data.get(i));
        }
    }

    public BloomFilter(BloomFilterStrategies.LockFreeBitArray bits, int numHashFunctions, Funnel<? super T> funnel, Strategy strategy) {
        Preconditions.checkArgument(numHashFunctions > 0, "numHashFunctions (%s) must be > 0", numHashFunctions);
        Preconditions.checkArgument(numHashFunctions <= 255, "numHashFunctions (%s) must be <= 255", numHashFunctions);
        bits.getClass();
        this.bits = bits;
        this.numHashFunctions = numHashFunctions;
        funnel.getClass();
        this.funnel = funnel;
        strategy.getClass();
        this.strategy = strategy;
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long expectedInsertions) {
        return create(funnel, expectedInsertions, 0.03d, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    @IgnoreJRERequirement
    public static <T> Collector<T, ?, BloomFilter<T>> toBloomFilter(final Funnel<? super T> funnel, final long expectedInsertions, final double fpp) {
        funnel.getClass();
        Preconditions.checkArgument(expectedInsertions >= 0, "Expected insertions (%s) must be >= 0", expectedInsertions);
        Preconditions.checkArgument(fpp > 0.0d, "False positive probability (%s) must be > 0.0", Double.valueOf(fpp));
        Preconditions.checkArgument(fpp < 1.0d, "False positive probability (%s) must be < 1.0", Double.valueOf(fpp));
        return Collector.CC.of(new Supplier() { // from class: com.google.common.hash.BloomFilter$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return BloomFilter.create(funnel, expectedInsertions, fpp, BloomFilterStrategies.MURMUR128_MITZ_64);
            }
        }, new BloomFilter$$ExternalSyntheticLambda1(), new BloomFilter$$ExternalSyntheticLambda2(), Collector.Characteristics.UNORDERED, Collector.Characteristics.CONCURRENT);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long expectedInsertions, double fpp) {
        return create(funnel, expectedInsertions, fpp, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions) {
        return create(funnel, expectedInsertions);
    }

    @VisibleForTesting
    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long expectedInsertions, double fpp, Strategy strategy) {
        funnel.getClass();
        Preconditions.checkArgument(expectedInsertions >= 0, "Expected insertions (%s) must be >= 0", expectedInsertions);
        Preconditions.checkArgument(fpp > 0.0d, "False positive probability (%s) must be > 0.0", Double.valueOf(fpp));
        Preconditions.checkArgument(fpp < 1.0d, "False positive probability (%s) must be < 1.0", Double.valueOf(fpp));
        strategy.getClass();
        if (expectedInsertions == 0) {
            expectedInsertions = 1;
        }
        long jOptimalNumOfBits = optimalNumOfBits(expectedInsertions, fpp);
        try {
            return new BloomFilter<>(new BloomFilterStrategies.LockFreeBitArray(jOptimalNumOfBits), optimalNumOfHashFunctions(fpp), funnel, strategy);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("Could not create BloomFilter of ", jOptimalNumOfBits, " bits"), e);
        }
    }
}
