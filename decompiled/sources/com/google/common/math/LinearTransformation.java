package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class LinearTransformation {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LinearTransformationBuilder {
        public final double x1;
        public final double y1;

        public LinearTransformation and(double x2, double y2) {
            Preconditions.checkArgument(DoubleUtils.isFinite(x2) && DoubleUtils.isFinite(y2));
            double d = this.x1;
            if (x2 != d) {
                return withSlope((y2 - this.y1) / (x2 - d));
            }
            Preconditions.checkArgument(y2 != this.y1);
            return new VerticalLinearTransformation(this.x1);
        }

        public LinearTransformation withSlope(double slope) {
            Preconditions.checkArgument(!Double.isNaN(slope));
            return DoubleUtils.isFinite(slope) ? new RegularLinearTransformation(slope, this.y1 - (this.x1 * slope)) : new VerticalLinearTransformation(this.x1);
        }

        public LinearTransformationBuilder(double x1, double y1) {
            this.x1 = x1;
            this.y1 = y1;
        }
    }

    @Deprecated
    public LinearTransformation() {
    }

    public static LinearTransformation forNaN() {
        return NaNLinearTransformation.INSTANCE;
    }

    public static LinearTransformation horizontal(double y) {
        Preconditions.checkArgument(DoubleUtils.isFinite(y));
        return new RegularLinearTransformation(0.0d, y);
    }

    public static LinearTransformationBuilder mapping(double x1, double y1) {
        Preconditions.checkArgument(DoubleUtils.isFinite(x1) && DoubleUtils.isFinite(y1));
        return new LinearTransformationBuilder(x1, y1);
    }

    public static LinearTransformation vertical(double x) {
        Preconditions.checkArgument(DoubleUtils.isFinite(x));
        return new VerticalLinearTransformation(x);
    }

    public abstract LinearTransformation inverse();

    public abstract boolean isHorizontal();

    public abstract boolean isVertical();

    public abstract double slope();

    public abstract double transform(double x);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class VerticalLinearTransformation extends LinearTransformation {

        @LazyInit
        public LinearTransformation inverse;
        public final double x;

        public VerticalLinearTransformation(double x) {
            this.x = x;
            this.inverse = null;
        }

        private LinearTransformation createInverse() {
            return new RegularLinearTransformation(0.0d, this.x, this);
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.inverse;
            if (linearTransformation != null) {
                return linearTransformation;
            }
            LinearTransformation linearTransformationCreateInverse = createInverse();
            this.inverse = linearTransformationCreateInverse;
            return linearTransformationCreateInverse;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return true;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            throw new IllegalStateException();
        }

        public String toString() {
            return String.format("x = %g", Double.valueOf(this.x));
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double x) {
            throw new IllegalStateException();
        }

        public VerticalLinearTransformation(double x, LinearTransformation inverse) {
            this.x = x;
            this.inverse = inverse;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class RegularLinearTransformation extends LinearTransformation {

        @LazyInit
        public LinearTransformation inverse;
        public final double slope;
        public final double yIntercept;

        public RegularLinearTransformation(double slope, double yIntercept) {
            this.slope = slope;
            this.yIntercept = yIntercept;
            this.inverse = null;
        }

        public final LinearTransformation createInverse() {
            double d = this.slope;
            return d != 0.0d ? new RegularLinearTransformation(1.0d / d, (this.yIntercept * (-1.0d)) / d, this) : new VerticalLinearTransformation(this.yIntercept, this);
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.inverse;
            if (linearTransformation != null) {
                return linearTransformation;
            }
            LinearTransformation linearTransformationCreateInverse = createInverse();
            this.inverse = linearTransformationCreateInverse;
            return linearTransformationCreateInverse;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return this.slope == 0.0d;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return this.slope;
        }

        public String toString() {
            return String.format("y = %g * x + %g", Double.valueOf(this.slope), Double.valueOf(this.yIntercept));
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double x) {
            return (x * this.slope) + this.yIntercept;
        }

        public RegularLinearTransformation(double slope, double yIntercept, LinearTransformation inverse) {
            this.slope = slope;
            this.yIntercept = yIntercept;
            this.inverse = inverse;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NaNLinearTransformation extends LinearTransformation {
        public static final NaNLinearTransformation INSTANCE = new NaNLinearTransformation();

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return Double.NaN;
        }

        public String toString() {
            return "NaN";
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double x) {
            return Double.NaN;
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            return this;
        }
    }
}
