package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import j$.util.DesugarCollections;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class TopKSelector<T> {
    public final T[] buffer;
    public int bufferSize;
    public final Comparator<? super T> comparator;
    public final int k;
    public T threshold;

    public TopKSelector(Comparator<? super T> comparator, int i) {
        Preconditions.checkNotNull(comparator, "comparator");
        this.comparator = comparator;
        this.k = i;
        Preconditions.checkArgument(i >= 0, "k (%s) must be >= 0", i);
        Preconditions.checkArgument(i <= 1073741823, "k (%s) must be <= Integer.MAX_VALUE / 2", i);
        this.buffer = (T[]) new Object[IntMath.checkedMultiply(i, 2)];
        this.bufferSize = 0;
        this.threshold = null;
    }

    public static <T> TopKSelector<T> greatest(int k, Comparator<? super T> comparator) {
        return new TopKSelector<>(Ordering.from(comparator).reverse(), k);
    }

    public static <T> TopKSelector<T> least(int k, Comparator<? super T> comparator) {
        return new TopKSelector<>(comparator, k);
    }

    public TopKSelector<T> combine(TopKSelector<T> other) {
        for (int i = 0; i < other.bufferSize; i++) {
            offer(other.buffer[i]);
        }
        return this;
    }

    public void offer(@ParametricNullness T t) {
        int i = this.k;
        if (i == 0) {
            return;
        }
        int i2 = this.bufferSize;
        if (i2 == 0) {
            this.buffer[0] = t;
            this.threshold = t;
            this.bufferSize = 1;
            return;
        }
        if (i2 < i) {
            T[] tArr = this.buffer;
            this.bufferSize = i2 + 1;
            tArr[i2] = t;
            if (this.comparator.compare(t, this.threshold) > 0) {
                this.threshold = t;
                return;
            }
            return;
        }
        if (this.comparator.compare(t, this.threshold) < 0) {
            T[] tArr2 = this.buffer;
            int i3 = this.bufferSize;
            int i4 = i3 + 1;
            this.bufferSize = i4;
            tArr2[i3] = t;
            if (i4 == this.k * 2) {
                trim();
            }
        }
    }

    public void offerAll(Iterable<? extends T> elements) {
        offerAll(elements.iterator());
    }

    public final int partition(int i, int i2, int i3) {
        T[] tArr = this.buffer;
        T t = tArr[i3];
        tArr[i3] = tArr[i2];
        int i4 = i;
        while (i < i2) {
            if (this.comparator.compare(this.buffer[i], t) < 0) {
                swap(i4, i);
                i4++;
            }
            i++;
        }
        T[] tArr2 = this.buffer;
        tArr2[i2] = tArr2[i4];
        tArr2[i4] = t;
        return i4;
    }

    public final void swap(int i, int j) {
        T[] tArr = this.buffer;
        T t = tArr[i];
        tArr[i] = tArr[j];
        tArr[j] = t;
    }

    public List<T> topK() {
        T[] tArr = this.buffer;
        Arrays.sort(tArr, 0, this.bufferSize, this.comparator);
        int i = this.bufferSize;
        int i2 = this.k;
        if (i > i2) {
            T[] tArr2 = this.buffer;
            Arrays.fill(tArr2, i2, tArr2.length, (Object) null);
            int i3 = this.k;
            this.bufferSize = i3;
            this.threshold = this.buffer[i3 - 1];
        }
        return DesugarCollections.unmodifiableList(Arrays.asList(Arrays.copyOf(tArr, this.bufferSize)));
    }

    public final void trim() {
        int i = (this.k * 2) - 1;
        int iLog2 = IntMath.log2(i, RoundingMode.CEILING) * 3;
        int iMax = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (iMax >= i) {
                break;
            }
            int iPartition = partition(iMax, i, ((iMax + i) + 1) >>> 1);
            int i4 = this.k;
            if (iPartition <= i4) {
                if (iPartition >= i4) {
                    break;
                }
                iMax = Math.max(iPartition, iMax + 1);
                i3 = iPartition;
            } else {
                i = iPartition - 1;
            }
            i2++;
            if (i2 >= iLog2) {
                Arrays.sort(this.buffer, iMax, i + 1, this.comparator);
                break;
            }
        }
        this.bufferSize = this.k;
        this.threshold = this.buffer[i3];
        while (true) {
            i3++;
            if (i3 >= this.k) {
                return;
            }
            if (this.comparator.compare(this.buffer[i3], this.threshold) > 0) {
                this.threshold = this.buffer[i3];
            }
        }
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> greatest(int k) {
        return greatest(k, NaturalOrdering.INSTANCE);
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> least(int k) {
        return new TopKSelector<>(NaturalOrdering.INSTANCE, k);
    }

    public void offerAll(Iterator<? extends T> elements) {
        while (elements.hasNext()) {
            offer(elements.next());
        }
    }
}
