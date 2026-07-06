package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class StatsAccumulator {
    public long count = 0;
    public double mean = 0.0d;
    public double sumOfSquaresOfDeltas = 0.0d;
    public double min = Double.NaN;
    public double max = Double.NaN;

    public static double calculateNewMeanNonFinite(double previousMean, double value) {
        if (Doubles.isFinite(previousMean)) {
            return value;
        }
        if (Doubles.isFinite(value) || previousMean == value) {
            return previousMean;
        }
        return Double.NaN;
    }

    public void add(double value) {
        long j = this.count;
        if (j == 0) {
            this.count = 1L;
            this.mean = value;
            this.min = value;
            this.max = value;
            if (Doubles.isFinite(value)) {
                return;
            }
            this.sumOfSquaresOfDeltas = Double.NaN;
            return;
        }
        this.count = j + 1;
        if (Doubles.isFinite(value) && Doubles.isFinite(this.mean)) {
            double d = this.mean;
            double d2 = value - d;
            double d3 = (d2 / this.count) + d;
            this.mean = d3;
            this.sumOfSquaresOfDeltas = ((value - d3) * d2) + this.sumOfSquaresOfDeltas;
        } else {
            this.mean = calculateNewMeanNonFinite(this.mean, value);
            this.sumOfSquaresOfDeltas = Double.NaN;
        }
        this.min = Math.min(this.min, value);
        this.max = Math.max(this.max, value);
    }

    public void addAll(Iterable<? extends Number> values) {
        Iterator<? extends Number> it = values.iterator();
        while (it.hasNext()) {
            add(it.next().doubleValue());
        }
    }

    public long count() {
        return this.count;
    }

    public double max() {
        Preconditions.checkState(this.count != 0);
        return this.max;
    }

    public double mean() {
        Preconditions.checkState(this.count != 0);
        return this.mean;
    }

    public final void merge(long otherCount, double otherMean, double otherSumOfSquaresOfDeltas, double otherMin, double otherMax) {
        long j = this.count;
        if (j == 0) {
            this.count = otherCount;
            this.mean = otherMean;
            this.sumOfSquaresOfDeltas = otherSumOfSquaresOfDeltas;
            this.min = otherMin;
            this.max = otherMax;
            return;
        }
        this.count = j + otherCount;
        if (Doubles.isFinite(this.mean) && Doubles.isFinite(otherMean)) {
            double d = this.mean;
            double d2 = otherMean - d;
            double d3 = otherCount;
            double d4 = ((d2 * d3) / this.count) + d;
            this.mean = d4;
            this.sumOfSquaresOfDeltas = ((otherMean - d4) * d2 * d3) + otherSumOfSquaresOfDeltas + this.sumOfSquaresOfDeltas;
        } else {
            this.mean = calculateNewMeanNonFinite(this.mean, otherMean);
            this.sumOfSquaresOfDeltas = Double.NaN;
        }
        this.min = Math.min(this.min, otherMin);
        this.max = Math.max(this.max, otherMax);
    }

    public double min() {
        Preconditions.checkState(this.count != 0);
        return this.min;
    }

    public final double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    public final double populationVariance() {
        Preconditions.checkState(this.count != 0);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        if (this.count == 1) {
            return 0.0d;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / this.count;
    }

    public final double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    public final double sampleVariance() {
        Preconditions.checkState(this.count > 1);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / (this.count - 1);
    }

    public Stats snapshot() {
        return new Stats(this.count, this.mean, this.sumOfSquaresOfDeltas, this.min, this.max);
    }

    public final double sum() {
        return this.mean * this.count;
    }

    public double sumOfSquaresOfDeltas() {
        return this.sumOfSquaresOfDeltas;
    }

    public void addAll(Iterator<? extends Number> values) {
        while (values.hasNext()) {
            add(values.next().doubleValue());
        }
    }

    public void addAll(double... values) {
        for (double d : values) {
            add(d);
        }
    }

    public void addAll(int... values) {
        for (int i : values) {
            add(i);
        }
    }

    public void addAll(long... values) {
        for (long j : values) {
            add(j);
        }
    }

    @IgnoreJRERequirement
    public void addAll(DoubleStream values) {
        addAll((StatsAccumulator) values.collect(new Stats$$ExternalSyntheticLambda0(), new Stats$$ExternalSyntheticLambda4(), new Stats$$ExternalSyntheticLambda2()));
    }

    @IgnoreJRERequirement
    public void addAll(IntStream values) {
        addAll((StatsAccumulator) values.collect(new Stats$$ExternalSyntheticLambda0(), new Stats$$ExternalSyntheticLambda3(), new Stats$$ExternalSyntheticLambda2()));
    }

    @IgnoreJRERequirement
    public void addAll(LongStream values) {
        addAll((StatsAccumulator) values.collect(new Stats$$ExternalSyntheticLambda0(), new Stats$$ExternalSyntheticLambda1(), new Stats$$ExternalSyntheticLambda2()));
    }

    public void addAll(StatsAccumulator values) {
        long j = values.count;
        if (j == 0) {
            return;
        }
        merge(j, values.mean(), values.sumOfSquaresOfDeltas, values.min(), values.max());
    }

    public void addAll(Stats values) {
        long j = values.count;
        if (j == 0) {
            return;
        }
        merge(j, values.mean(), values.sumOfSquaresOfDeltas, values.min(), values.max());
    }
}
