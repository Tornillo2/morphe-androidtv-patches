package androidx.media3.common.util;

import androidx.annotation.Nullable;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class TimedValueQueue<V> {
    public static final int INITIAL_BUFFER_SIZE = 10;
    public int first;
    public int size;
    public long[] timestamps;
    public V[] values;

    public TimedValueQueue() {
        this(10);
    }

    public static <V> V[] newArray(int i) {
        return (V[]) new Object[i];
    }

    public synchronized void add(long j, V v) {
        clearBufferOnTimeDiscontinuity(j);
        doubleCapacityIfFull();
        addUnchecked(j, v);
    }

    public final void addUnchecked(long j, V v) {
        int i = this.first;
        int i2 = this.size;
        V[] vArr = this.values;
        int length = (i + i2) % vArr.length;
        this.timestamps[length] = j;
        vArr[length] = v;
        this.size = i2 + 1;
    }

    public synchronized void clear() {
        this.first = 0;
        this.size = 0;
        Arrays.fill(this.values, (Object) null);
    }

    public final void clearBufferOnTimeDiscontinuity(long j) {
        if (this.size > 0) {
            if (j <= this.timestamps[((this.first + r0) - 1) % this.values.length]) {
                clear();
            }
        }
    }

    public final void doubleCapacityIfFull() {
        int length = this.values.length;
        if (this.size < length) {
            return;
        }
        int i = length * 2;
        long[] jArr = new long[i];
        V[] vArr = (V[]) new Object[i];
        int i2 = this.first;
        int i3 = length - i2;
        System.arraycopy(this.timestamps, i2, jArr, 0, i3);
        System.arraycopy(this.values, this.first, vArr, 0, i3);
        int i4 = this.first;
        if (i4 > 0) {
            System.arraycopy(this.timestamps, 0, jArr, i3, i4);
            System.arraycopy(this.values, 0, vArr, i3, this.first);
        }
        this.timestamps = jArr;
        this.values = vArr;
        this.first = 0;
    }

    @Nullable
    public synchronized V poll(long j) {
        return poll(j, false);
    }

    @Nullable
    public synchronized V pollFirst() {
        return this.size == 0 ? null : popFirst();
    }

    @Nullable
    public synchronized V pollFloor(long j) {
        return poll(j, true);
    }

    @Nullable
    public final V popFirst() {
        Assertions.checkState(this.size > 0);
        V[] vArr = this.values;
        int i = this.first;
        V v = vArr[i];
        vArr[i] = null;
        this.first = (i + 1) % vArr.length;
        this.size--;
        return v;
    }

    public synchronized int size() {
        return this.size;
    }

    public TimedValueQueue(int i) {
        this.timestamps = new long[i];
        this.values = (V[]) new Object[i];
    }

    @Nullable
    public final V poll(long j, boolean z) {
        V vPopFirst = null;
        long j2 = Long.MAX_VALUE;
        while (this.size > 0) {
            long j3 = j - this.timestamps[this.first];
            if (j3 < 0 && (z || (-j3) >= j2)) {
                break;
            }
            vPopFirst = popFirst();
            j2 = j3;
        }
        return vPopFirst;
    }
}
