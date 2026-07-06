package androidx.media3.common.util;

import androidx.annotation.VisibleForTesting;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class LongArrayQueue {
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public long[] data;
    public int headIndex;
    public int size;
    public int tailIndex;
    public int wrapAroundMask;

    public LongArrayQueue() {
        this(16);
    }

    public void add(long j) {
        if (this.size == this.data.length) {
            doubleArraySize();
        }
        int i = (this.tailIndex + 1) & this.wrapAroundMask;
        this.tailIndex = i;
        this.data[i] = j;
        this.size++;
    }

    @VisibleForTesting
    public int capacity() {
        return this.data.length;
    }

    public void clear() {
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
    }

    public final void doubleArraySize() {
        long[] jArr = this.data;
        int length = jArr.length << 1;
        if (length < 0) {
            throw new IllegalStateException();
        }
        long[] jArr2 = new long[length];
        int length2 = jArr.length;
        int i = this.headIndex;
        int i2 = length2 - i;
        System.arraycopy(jArr, i, jArr2, 0, i2);
        System.arraycopy(this.data, 0, jArr2, i2, i);
        this.headIndex = 0;
        this.tailIndex = this.size - 1;
        this.data = jArr2;
        this.wrapAroundMask = jArr2.length - 1;
    }

    public long element() {
        if (this.size != 0) {
            return this.data[this.headIndex];
        }
        throw new NoSuchElementException();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public long remove() {
        int i = this.size;
        if (i == 0) {
            throw new NoSuchElementException();
        }
        long[] jArr = this.data;
        int i2 = this.headIndex;
        long j = jArr[i2];
        this.headIndex = this.wrapAroundMask & (i2 + 1);
        this.size = i - 1;
        return j;
    }

    public int size() {
        return this.size;
    }

    public LongArrayQueue(int i) {
        Assertions.checkArgument(i >= 0 && i <= 1073741824);
        i = i == 0 ? 1 : i;
        i = Integer.bitCount(i) != 1 ? Integer.highestOneBit(i - 1) << 1 : i;
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
        long[] jArr = new long[i];
        this.data = jArr;
        this.wrapAroundMask = jArr.length - 1;
    }
}
