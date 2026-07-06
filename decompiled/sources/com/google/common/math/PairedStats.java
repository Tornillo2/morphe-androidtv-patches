package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.math.LinearTransformation;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class PairedStats implements Serializable {
    public static final int BYTES = 88;
    public static final long serialVersionUID = 0;
    public final double sumOfProductsOfDeltas;
    public final Stats xStats;
    public final Stats yStats;

    public PairedStats(Stats xStats, Stats yStats, double sumOfProductsOfDeltas) {
        this.xStats = xStats;
        this.yStats = yStats;
        this.sumOfProductsOfDeltas = sumOfProductsOfDeltas;
    }

    public static double ensureInUnitRange(double value) {
        if (value >= 1.0d) {
            return 1.0d;
        }
        if (value <= -1.0d) {
            return -1.0d;
        }
        return value;
    }

    public static double ensurePositive(double value) {
        if (value > 0.0d) {
            return value;
        }
        return Double.MIN_VALUE;
    }

    public static PairedStats fromByteArray(byte[] byteArray) {
        byteArray.getClass();
        Preconditions.checkArgument(byteArray.length == 88, "Expected PairedStats.BYTES = %s, got %s", 88, byteArray.length);
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN);
        return new PairedStats(Stats.readFrom(byteBufferOrder), Stats.readFrom(byteBufferOrder), byteBufferOrder.getDouble());
    }

    public long count() {
        return this.xStats.count;
    }

    public boolean equals(Object obj) {
        if (obj == null || PairedStats.class != obj.getClass()) {
            return false;
        }
        PairedStats pairedStats = (PairedStats) obj;
        return this.xStats.equals(pairedStats.xStats) && this.yStats.equals(pairedStats.yStats) && Double.doubleToLongBits(this.sumOfProductsOfDeltas) == Double.doubleToLongBits(pairedStats.sumOfProductsOfDeltas);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.xStats, this.yStats, Double.valueOf(this.sumOfProductsOfDeltas)});
    }

    public LinearTransformation leastSquaresFit() {
        Preconditions.checkState(this.xStats.count > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return LinearTransformation.NaNLinearTransformation.INSTANCE;
        }
        Stats stats = this.xStats;
        double d = stats.sumOfSquaresOfDeltas;
        if (d > 0.0d) {
            Stats stats2 = this.yStats;
            return stats2.sumOfSquaresOfDeltas > 0.0d ? LinearTransformation.mapping(stats.mean(), this.yStats.mean()).withSlope(this.sumOfProductsOfDeltas / d) : LinearTransformation.horizontal(stats2.mean());
        }
        Preconditions.checkState(this.yStats.sumOfSquaresOfDeltas > 0.0d);
        return LinearTransformation.vertical(this.xStats.mean());
    }

    public double pearsonsCorrelationCoefficient() {
        Preconditions.checkState(this.xStats.count > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return Double.NaN;
        }
        double d = this.xStats.sumOfSquaresOfDeltas;
        double d2 = this.yStats.sumOfSquaresOfDeltas;
        Preconditions.checkState(d > 0.0d);
        Preconditions.checkState(d2 > 0.0d);
        return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(ensurePositive(d * d2)));
    }

    public double populationCovariance() {
        Preconditions.checkState(this.xStats.count != 0);
        return this.sumOfProductsOfDeltas / this.xStats.count;
    }

    public double sampleCovariance() {
        Preconditions.checkState(this.xStats.count > 1);
        return this.sumOfProductsOfDeltas / (this.xStats.count - 1);
    }

    public double sumOfProductsOfDeltas() {
        return this.sumOfProductsOfDeltas;
    }

    public byte[] toByteArray() {
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(88).order(ByteOrder.LITTLE_ENDIAN);
        this.xStats.writeTo(byteBufferOrder);
        this.yStats.writeTo(byteBufferOrder);
        byteBufferOrder.putDouble(this.sumOfProductsOfDeltas);
        return byteBufferOrder.array();
    }

    public String toString() {
        if (this.xStats.count <= 0) {
            MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
            stringHelper.addHolder("xStats", this.xStats);
            stringHelper.addHolder("yStats", this.yStats);
            return stringHelper.toString();
        }
        MoreObjects.ToStringHelper stringHelper2 = MoreObjects.toStringHelper(this);
        stringHelper2.addHolder("xStats", this.xStats);
        stringHelper2.addHolder("yStats", this.yStats);
        stringHelper2.add("populationCovariance", populationCovariance());
        return stringHelper2.toString();
    }

    public Stats xStats() {
        return this.xStats;
    }

    public Stats yStats() {
        return this.yStats;
    }
}
