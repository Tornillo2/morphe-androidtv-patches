package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.InlineMe;
import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class ComparisonChain {
    public static final ComparisonChain ACTIVE = new AnonymousClass1();
    public static final ComparisonChain LESS = new InactiveComparisonChain(-1);
    public static final ComparisonChain GREATER = new InactiveComparisonChain(1);

    /* JADX INFO: renamed from: com.google.common.collect.ComparisonChain$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends ComparisonChain {
        public ComparisonChain classify(int result) {
            return result < 0 ? ComparisonChain.LESS : result > 0 ? ComparisonChain.GREATER : ComparisonChain.ACTIVE;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(Comparable<?> left, Comparable<?> right) {
            return classify(left.compareTo(right));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareFalseFirst(boolean left, boolean right) {
            return classify(Boolean.compare(left, right));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareTrueFirst(boolean left, boolean right) {
            return classify(Boolean.compare(right, left));
        }

        @Override // com.google.common.collect.ComparisonChain
        public int result() {
            return 0;
        }

        @Override // com.google.common.collect.ComparisonChain
        public <T> ComparisonChain compare(@ParametricNullness T left, @ParametricNullness T right, Comparator<T> comparator) {
            return classify(comparator.compare(left, right));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(int left, int right) {
            return classify(Integer.compare(left, right));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(long left, long right) {
            return classify(Long.compare(left, right));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(float left, float right) {
            return classify(Float.compare(left, right));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(double left, double right) {
            return classify(Double.compare(left, right));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InactiveComparisonChain extends ComparisonChain {
        public final int result;

        public InactiveComparisonChain(int result) {
            this.result = result;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(double left, double right) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public int result() {
            return this.result;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(float left, float right) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(int left, int right) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(long left, long right) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(Comparable<?> left, Comparable<?> right) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public <T> ComparisonChain compare(@ParametricNullness T left, @ParametricNullness T right, Comparator<T> comparator) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareFalseFirst(boolean left, boolean right) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareTrueFirst(boolean left, boolean right) {
            return this;
        }
    }

    public ComparisonChain() {
    }

    public static ComparisonChain start() {
        return ACTIVE;
    }

    public abstract ComparisonChain compare(double left, double right);

    public abstract ComparisonChain compare(float left, float right);

    public abstract ComparisonChain compare(int left, int right);

    public abstract ComparisonChain compare(long left, long right);

    @InlineMe(replacement = "this.compareFalseFirst(left, right)")
    @Deprecated
    public final ComparisonChain compare(Boolean left, Boolean right) {
        return compareFalseFirst(left.booleanValue(), right.booleanValue());
    }

    public abstract ComparisonChain compare(Comparable<?> left, Comparable<?> right);

    public abstract <T> ComparisonChain compare(@ParametricNullness T left, @ParametricNullness T right, Comparator<T> comparator);

    public abstract ComparisonChain compareFalseFirst(boolean left, boolean right);

    public abstract ComparisonChain compareTrueFirst(boolean left, boolean right);

    public abstract int result();

    public ComparisonChain(AnonymousClass1 anonymousClass1) {
    }
}
