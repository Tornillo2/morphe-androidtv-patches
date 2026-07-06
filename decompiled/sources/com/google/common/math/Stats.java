package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import j$.util.stream.Collector;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class Stats implements Serializable {
    public static final int BYTES = 40;
    public static final long serialVersionUID = 0;
    public final long count;
    public final double max;
    public final double mean;
    public final double min;
    public final double sumOfSquaresOfDeltas;

    public static /* synthetic */ StatsAccumulator $r8$lambda$LVqnUhmAR0322qlhfu4tTg0VMfI(StatsAccumulator statsAccumulator, StatsAccumulator statsAccumulator2) {
        statsAccumulator.addAll(statsAccumulator2);
        return statsAccumulator;
    }

    public Stats(long count, double mean, double sumOfSquaresOfDeltas, double min, double max) {
        this.count = count;
        this.mean = mean;
        this.sumOfSquaresOfDeltas = sumOfSquaresOfDeltas;
        this.min = min;
        this.max = max;
    }

    public static Stats fromByteArray(byte[] byteArray) {
        byteArray.getClass();
        Preconditions.checkArgument(byteArray.length == 40, "Expected Stats.BYTES = %s remaining , got %s", 40, byteArray.length);
        return readFrom(ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN));
    }

    public static double meanOf(Iterable<? extends Number> values) {
        return meanOf(values.iterator());
    }

    public static Stats of(Iterable<? extends Number> values) {
        StatsAccumulator statsAccumulator = new StatsAccumulator();
        statsAccumulator.addAll(values);
        return statsAccumulator.snapshot();
    }

    public static Stats readFrom(ByteBuffer buffer) {
        buffer.getClass();
        Preconditions.checkArgument(buffer.remaining() >= 40, "Expected at least Stats.BYTES = %s remaining , got %s", 40, buffer.remaining());
        return new Stats(buffer.getLong(), buffer.getDouble(), buffer.getDouble(), buffer.getDouble(), buffer.getDouble());
    }

    @IgnoreJRERequirement
    public static Collector<Number, StatsAccumulator, Stats> toStats() {
        return Collector.CC.of(new Stats$$ExternalSyntheticLambda0(), new Stats$$ExternalSyntheticLambda5(), new Stats$$ExternalSyntheticLambda6(), new Stats$$ExternalSyntheticLambda7(), Collector.Characteristics.UNORDERED);
    }

    public long count() {
        return this.count;
    }

    public boolean equals(Object obj) {
        if (obj == null || Stats.class != obj.getClass()) {
            return false;
        }
        Stats stats = (Stats) obj;
        return this.count == stats.count && Double.doubleToLongBits(this.mean) == Double.doubleToLongBits(stats.mean) && Double.doubleToLongBits(this.sumOfSquaresOfDeltas) == Double.doubleToLongBits(stats.sumOfSquaresOfDeltas) && Double.doubleToLongBits(this.min) == Double.doubleToLongBits(stats.min) && Double.doubleToLongBits(this.max) == Double.doubleToLongBits(stats.max);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.count), Double.valueOf(this.mean), Double.valueOf(this.sumOfSquaresOfDeltas), Double.valueOf(this.min), Double.valueOf(this.max)});
    }

    public double max() {
        Preconditions.checkState(this.count != 0);
        return this.max;
    }

    public double mean() {
        Preconditions.checkState(this.count != 0);
        return this.mean;
    }

    public double min() {
        Preconditions.checkState(this.count != 0);
        return this.min;
    }

    public double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    public double populationVariance() {
        Preconditions.checkState(this.count > 0);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        if (this.count == 1) {
            return 0.0d;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / this.count;
    }

    public double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    public double sampleVariance() {
        Preconditions.checkState(this.count > 1);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / (this.count - 1);
    }

    public double sum() {
        return this.mean * this.count;
    }

    public double sumOfSquaresOfDeltas() {
        return this.sumOfSquaresOfDeltas;
    }

    public byte[] toByteArray() {
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN);
        writeTo(byteBufferOrder);
        return byteBufferOrder.array();
    }

    public String toString() {
        if (this.count <= 0) {
            MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
            stringHelper.add("count", this.count);
            return stringHelper.toString();
        }
        MoreObjects.ToStringHelper stringHelper2 = MoreObjects.toStringHelper(this);
        stringHelper2.add("count", this.count);
        stringHelper2.add("mean", this.mean);
        stringHelper2.add("populationStandardDeviation", populationStandardDeviation());
        stringHelper2.add("min", this.min);
        stringHelper2.add("max", this.max);
        return stringHelper2.toString();
    }

    public void writeTo(ByteBuffer buffer) {
        buffer.getClass();
        Preconditions.checkArgument(buffer.remaining() >= 40, "Expected at least Stats.BYTES = %s remaining , got %s", 40, buffer.remaining());
        buffer.putLong(this.count).putDouble(this.mean).putDouble(this.sumOfSquaresOfDeltas).putDouble(this.min).putDouble(this.max);
    }

    public static double meanOf(Iterator<? extends Number> values) {
        Preconditions.checkArgument(values.hasNext());
        double dDoubleValue = values.next().doubleValue();
        long j = 1;
        while (values.hasNext()) {
            double dDoubleValue2 = values.next().doubleValue();
            j++;
            dDoubleValue = (Doubles.isFinite(dDoubleValue2) && Doubles.isFinite(dDoubleValue)) ? ((dDoubleValue2 - dDoubleValue) / j) + dDoubleValue : StatsAccumulator.calculateNewMeanNonFinite(dDoubleValue, dDoubleValue2);
        }
        return dDoubleValue;
    }

    public static Stats of(Iterator<? extends Number> values) {
        StatsAccumulator statsAccumulator = new StatsAccumulator();
        statsAccumulator.addAll(values);
        return statsAccumulator.snapshot();
    }

    public static Stats of(double... values) {
        StatsAccumulator statsAccumulator = new StatsAccumulator();
        statsAccumulator.addAll(values);
        return statsAccumulator.snapshot();
    }

    public static double meanOf(double... values) {
        Preconditions.checkArgument(values.length > 0);
        double dCalculateNewMeanNonFinite = values[0];
        for (int i = 1; i < values.length; i++) {
            double d = values[i];
            dCalculateNewMeanNonFinite = (Doubles.isFinite(d) && Doubles.isFinite(dCalculateNewMeanNonFinite)) ? ((d - dCalculateNewMeanNonFinite) / ((double) (i + 1))) + dCalculateNewMeanNonFinite : StatsAccumulator.calculateNewMeanNonFinite(dCalculateNewMeanNonFinite, d);
        }
        return dCalculateNewMeanNonFinite;
    }

    public static Stats of(int... values) {
        StatsAccumulator statsAccumulator = new StatsAccumulator();
        statsAccumulator.addAll(values);
        return statsAccumulator.snapshot();
    }

    public static Stats of(long... values) {
        StatsAccumulator statsAccumulator = new StatsAccumulator();
        statsAccumulator.addAll(values);
        return statsAccumulator.snapshot();
    }

    public static double meanOf(int... values) {
        Preconditions.checkArgument(values.length > 0);
        double dCalculateNewMeanNonFinite = values[0];
        for (int i = 1; i < values.length; i++) {
            double d = values[i];
            dCalculateNewMeanNonFinite = (Doubles.isFinite(d) && Doubles.isFinite(dCalculateNewMeanNonFinite)) ? ((d - dCalculateNewMeanNonFinite) / ((double) (i + 1))) + dCalculateNewMeanNonFinite : StatsAccumulator.calculateNewMeanNonFinite(dCalculateNewMeanNonFinite, d);
        }
        return dCalculateNewMeanNonFinite;
    }

    @IgnoreJRERequirement
    public static Stats of(DoubleStream values) {
        return ((StatsAccumulator) values.collect(new Stats$$ExternalSyntheticLambda0(), new Stats$$ExternalSyntheticLambda4(), new Stats$$ExternalSyntheticLambda2())).snapshot();
    }

    @IgnoreJRERequirement
    public static Stats of(IntStream values) {
        return ((StatsAccumulator) values.collect(new Stats$$ExternalSyntheticLambda0(), new Stats$$ExternalSyntheticLambda3(), new Stats$$ExternalSyntheticLambda2())).snapshot();
    }

    public static double meanOf(long... values) {
        Preconditions.checkArgument(values.length > 0);
        double dCalculateNewMeanNonFinite = values[0];
        for (int i = 1; i < values.length; i++) {
            double d = values[i];
            dCalculateNewMeanNonFinite = (Doubles.isFinite(d) && Doubles.isFinite(dCalculateNewMeanNonFinite)) ? ((d - dCalculateNewMeanNonFinite) / ((double) (i + 1))) + dCalculateNewMeanNonFinite : StatsAccumulator.calculateNewMeanNonFinite(dCalculateNewMeanNonFinite, d);
        }
        return dCalculateNewMeanNonFinite;
    }

    @IgnoreJRERequirement
    public static Stats of(LongStream values) {
        return ((StatsAccumulator) values.collect(new Stats$$ExternalSyntheticLambda0(), new Stats$$ExternalSyntheticLambda1(), new Stats$$ExternalSyntheticLambda2())).snapshot();
    }
}
