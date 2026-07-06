package com.google.common.math;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import j$.util.DesugarCollections;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class Quantiles {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Scale {
        public final int scale;

        public ScaleAndIndex index(int index) {
            return new ScaleAndIndex(this.scale, index);
        }

        public ScaleAndIndexes indexes(int... indexes) {
            return new ScaleAndIndexes(this.scale, (int[]) indexes.clone());
        }

        public Scale(int scale) {
            Preconditions.checkArgument(scale > 0, "Quantile scale must be positive");
            this.scale = scale;
        }

        public ScaleAndIndexes indexes(Collection<Integer> indexes) {
            return new ScaleAndIndexes(this.scale, Ints.toArray(indexes));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ScaleAndIndex {
        public final int index;
        public final int scale;

        public double compute(int... dataset) {
            return computeInPlace(Quantiles.intsToDoubles(dataset));
        }

        public double computeInPlace(double... dataset) {
            Preconditions.checkArgument(dataset.length > 0, "Cannot calculate quantiles of an empty dataset");
            if (Quantiles.containsNaN(dataset)) {
                return Double.NaN;
            }
            long length = ((long) this.index) * ((long) (dataset.length - 1));
            int iDivide = (int) LongMath.divide(length, this.scale, RoundingMode.DOWN);
            int i = (int) (length - (((long) iDivide) * ((long) this.scale)));
            Quantiles.selectInPlace(iDivide, dataset, 0, dataset.length - 1);
            if (i == 0) {
                return dataset[iDivide];
            }
            int i2 = iDivide + 1;
            Quantiles.selectInPlace(i2, dataset, i2, dataset.length - 1);
            return Quantiles.interpolate(dataset[iDivide], dataset[i2], i, this.scale);
        }

        public ScaleAndIndex(int scale, int index) {
            Quantiles.checkIndex(index, scale);
            this.scale = scale;
            this.index = index;
        }

        public double compute(long... dataset) {
            return computeInPlace(Quantiles.longsToDoubles(dataset));
        }

        public double compute(Collection<? extends Number> dataset) {
            return computeInPlace(Doubles.toArray(dataset));
        }

        public double compute(double... dataset) {
            return computeInPlace((double[]) dataset.clone());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ScaleAndIndexes {
        public final int[] indexes;
        public final int scale;

        public Map<Integer, Double> compute(int... dataset) {
            return computeInPlace(Quantiles.intsToDoubles(dataset));
        }

        public Map<Integer, Double> computeInPlace(double... dataset) {
            int i = 0;
            Preconditions.checkArgument(dataset.length > 0, "Cannot calculate quantiles of an empty dataset");
            if (Quantiles.containsNaN(dataset)) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                int[] iArr = this.indexes;
                int length = iArr.length;
                while (i < length) {
                    linkedHashMap.put(Integer.valueOf(iArr[i]), Double.valueOf(Double.NaN));
                    i++;
                }
                return DesugarCollections.unmodifiableMap(linkedHashMap);
            }
            int[] iArr2 = this.indexes;
            int[] iArr3 = new int[iArr2.length];
            int[] iArr4 = new int[iArr2.length];
            int[] iArr5 = new int[iArr2.length * 2];
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int[] iArr6 = this.indexes;
                if (i2 >= iArr6.length) {
                    break;
                }
                long length2 = ((long) iArr6[i2]) * ((long) (dataset.length - 1));
                int iDivide = (int) LongMath.divide(length2, this.scale, RoundingMode.DOWN);
                int i4 = (int) (length2 - (((long) iDivide) * ((long) this.scale)));
                iArr3[i2] = iDivide;
                iArr4[i2] = i4;
                iArr5[i3] = iDivide;
                int i5 = i3 + 1;
                if (i4 != 0) {
                    iArr5[i5] = iDivide + 1;
                    i3 += 2;
                } else {
                    i3 = i5;
                }
                i2++;
            }
            Arrays.sort(iArr5, 0, i3);
            Quantiles.selectAllInPlace(iArr5, 0, i3 - 1, dataset, 0, dataset.length - 1);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            while (true) {
                int[] iArr7 = this.indexes;
                if (i >= iArr7.length) {
                    return DesugarCollections.unmodifiableMap(linkedHashMap2);
                }
                int i6 = iArr3[i];
                int i7 = iArr4[i];
                if (i7 == 0) {
                    linkedHashMap2.put(Integer.valueOf(iArr7[i]), Double.valueOf(dataset[i6]));
                } else {
                    linkedHashMap2.put(Integer.valueOf(iArr7[i]), Double.valueOf(Quantiles.interpolate(dataset[i6], dataset[i6 + 1], i7, this.scale)));
                }
                i++;
            }
        }

        public ScaleAndIndexes(int scale, int[] indexes) {
            for (int i : indexes) {
                Quantiles.checkIndex(i, scale);
            }
            Preconditions.checkArgument(indexes.length > 0, "Indexes must be a non empty array");
            this.scale = scale;
            this.indexes = indexes;
        }

        public Map<Integer, Double> compute(long... dataset) {
            return computeInPlace(Quantiles.longsToDoubles(dataset));
        }

        public Map<Integer, Double> compute(Collection<? extends Number> dataset) {
            return computeInPlace(Doubles.toArray(dataset));
        }

        public Map<Integer, Double> compute(double... dataset) {
            return computeInPlace((double[]) dataset.clone());
        }
    }

    @Deprecated
    public Quantiles() {
    }

    public static void checkIndex(int index, int scale) {
        if (index < 0 || index > scale) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Quantile indexes must be between 0 and the scale, which is ", scale));
        }
    }

    public static int chooseNextSelection(int[] allRequired, int requiredFrom, int requiredTo, int from, int to) {
        if (requiredFrom == requiredTo) {
            return requiredFrom;
        }
        int i = from + to;
        int i2 = i >>> 1;
        while (requiredTo > requiredFrom + 1) {
            int i3 = (requiredFrom + requiredTo) >>> 1;
            int i4 = allRequired[i3];
            if (i4 > i2) {
                requiredTo = i3;
            } else {
                if (i4 >= i2) {
                    return i3;
                }
                requiredFrom = i3;
            }
        }
        return (i - allRequired[requiredFrom]) - allRequired[requiredTo] > 0 ? requiredTo : requiredFrom;
    }

    public static boolean containsNaN(double... dataset) {
        for (double d : dataset) {
            if (Double.isNaN(d)) {
                return true;
            }
        }
        return false;
    }

    public static double interpolate(double lower, double upper, double remainder, double scale) {
        if (lower == Double.NEGATIVE_INFINITY) {
            return upper == Double.POSITIVE_INFINITY ? Double.NaN : Double.NEGATIVE_INFINITY;
        }
        if (upper == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        return (((upper - lower) * remainder) / scale) + lower;
    }

    public static double[] intsToDoubles(int[] ints) {
        int length = ints.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = ints[i];
        }
        return dArr;
    }

    public static double[] longsToDoubles(long[] longs) {
        int length = longs.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = longs[i];
        }
        return dArr;
    }

    public static ScaleAndIndex median() {
        return new Scale(2).index(1);
    }

    public static void movePivotToStartOfSlice(double[] array, int from, int to) {
        int i = (from + to) >>> 1;
        double d = array[to];
        double d2 = array[i];
        boolean z = d < d2;
        double d3 = array[from];
        boolean z2 = d2 < d3;
        boolean z3 = d < d3;
        if (z == z2) {
            array[i] = d3;
            array[from] = d2;
        } else if (z != z3) {
            array[from] = d;
            array[to] = d3;
        }
    }

    public static int partition(double[] array, int from, int to) {
        movePivotToStartOfSlice(array, from, to);
        double d = array[from];
        int i = to;
        while (to > from) {
            double d2 = array[to];
            if (d2 > d) {
                double d3 = array[i];
                array[i] = d2;
                array[to] = d3;
                i--;
            }
            to--;
        }
        double d4 = array[from];
        array[from] = array[i];
        array[i] = d4;
        return i;
    }

    public static Scale percentiles() {
        return new Scale(100);
    }

    public static Scale quartiles() {
        return new Scale(4);
    }

    public static Scale scale(int scale) {
        return new Scale(scale);
    }

    public static void selectAllInPlace(int[] allRequired, int requiredFrom, int requiredTo, double[] array, int from, int to) {
        int iChooseNextSelection = chooseNextSelection(allRequired, requiredFrom, requiredTo, from, to);
        int i = allRequired[iChooseNextSelection];
        selectInPlace(i, array, from, to);
        int i2 = iChooseNextSelection - 1;
        while (i2 >= requiredFrom && allRequired[i2] == i) {
            i2--;
        }
        if (i2 >= requiredFrom) {
            selectAllInPlace(allRequired, requiredFrom, i2, array, from, i - 1);
        }
        int i3 = iChooseNextSelection + 1;
        while (i3 <= requiredTo && allRequired[i3] == i) {
            i3++;
        }
        if (i3 <= requiredTo) {
            selectAllInPlace(allRequired, i3, requiredTo, array, i + 1, to);
        }
    }

    public static void selectInPlace(int required, double[] array, int from, int to) {
        if (required != from) {
            while (to > from) {
                int iPartition = partition(array, from, to);
                if (iPartition >= required) {
                    to = iPartition - 1;
                }
                if (iPartition <= required) {
                    from = iPartition + 1;
                }
            }
            return;
        }
        int i = from;
        for (int i2 = from + 1; i2 <= to; i2++) {
            if (array[i] > array[i2]) {
                i = i2;
            }
        }
        if (i != from) {
            swap(array, i, from);
        }
    }

    public static void swap(double[] array, int i, int j) {
        double d = array[i];
        array[i] = array[j];
        array[j] = d;
    }
}
