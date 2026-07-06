package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.LinearTransformation;
import com.google.common.primitives.Doubles;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class PairedStatsAccumulator {
    public final StatsAccumulator xStats = new StatsAccumulator();
    public final StatsAccumulator yStats = new StatsAccumulator();
    public double sumOfProductsOfDeltas = 0.0d;

    public static double ensureInUnitRange(double value) {
        return Doubles.constrainToRange(value, -1.0d, 1.0d);
    }

    public void add(double x, double y) {
        this.xStats.add(x);
        if (Doubles.isFinite(x) && Doubles.isFinite(y)) {
            StatsAccumulator statsAccumulator = this.xStats;
            if (statsAccumulator.count > 1) {
                this.sumOfProductsOfDeltas = ((y - this.yStats.mean()) * (x - statsAccumulator.mean())) + this.sumOfProductsOfDeltas;
            }
        } else {
            this.sumOfProductsOfDeltas = Double.NaN;
        }
        this.yStats.add(y);
    }

    public void addAll(PairedStats values) {
        Stats stats = values.xStats;
        if (stats.count == 0) {
            return;
        }
        this.xStats.addAll(stats);
        if (this.yStats.count == 0) {
            this.sumOfProductsOfDeltas = values.sumOfProductsOfDeltas;
        } else {
            this.sumOfProductsOfDeltas = ((values.yStats.mean() - this.yStats.mean()) * (values.xStats.mean() - this.xStats.mean()) * values.xStats.count) + values.sumOfProductsOfDeltas + this.sumOfProductsOfDeltas;
        }
        this.yStats.addAll(values.yStats);
    }

    public long count() {
        return this.xStats.count;
    }

    public final double ensurePositive(double value) {
        if (value > 0.0d) {
            return value;
        }
        return Double.MIN_VALUE;
    }

    public final LinearTransformation leastSquaresFit() {
        Preconditions.checkState(this.xStats.count > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return LinearTransformation.NaNLinearTransformation.INSTANCE;
        }
        StatsAccumulator statsAccumulator = this.xStats;
        double d = statsAccumulator.sumOfSquaresOfDeltas;
        if (d > 0.0d) {
            StatsAccumulator statsAccumulator2 = this.yStats;
            return statsAccumulator2.sumOfSquaresOfDeltas > 0.0d ? LinearTransformation.mapping(statsAccumulator.mean(), this.yStats.mean()).withSlope(this.sumOfProductsOfDeltas / d) : LinearTransformation.horizontal(statsAccumulator2.mean());
        }
        Preconditions.checkState(this.yStats.sumOfSquaresOfDeltas > 0.0d);
        return LinearTransformation.vertical(this.xStats.mean());
    }

    public final double pearsonsCorrelationCoefficient() {
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

    public final double sampleCovariance() {
        Preconditions.checkState(this.xStats.count > 1);
        return this.sumOfProductsOfDeltas / (this.xStats.count - 1);
    }

    public PairedStats snapshot() {
        return new PairedStats(this.xStats.snapshot(), this.yStats.snapshot(), this.sumOfProductsOfDeltas);
    }

    public Stats xStats() {
        return this.xStats.snapshot();
    }

    public Stats yStats() {
        return this.yStats.snapshot();
    }
}
