package com.google.android.exoplayer2.mediacodec;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class IntArrayQueue {
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public int wrapAroundMask;
    public int headIndex = 0;
    public int tailIndex = -1;
    public int size = 0;
    public int[] data = new int[16];

    public IntArrayQueue() {
        this.wrapAroundMask = r0.length - 1;
    }

    public void add(int i) {
        if (this.size == this.data.length) {
            doubleArraySize();
        }
        int i2 = (this.tailIndex + 1) & this.wrapAroundMask;
        this.tailIndex = i2;
        this.data[i2] = i;
        this.size++;
    }

    public int capacity() {
        return this.data.length;
    }

    public void clear() {
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
    }

    public final void doubleArraySize() {
        int[] iArr = this.data;
        int length = iArr.length << 1;
        if (length < 0) {
            throw new IllegalStateException();
        }
        int[] iArr2 = new int[length];
        int length2 = iArr.length;
        int i = this.headIndex;
        int i2 = length2 - i;
        System.arraycopy(iArr, i, iArr2, 0, i2);
        System.arraycopy(this.data, 0, iArr2, i2, i);
        this.headIndex = 0;
        this.tailIndex = this.size - 1;
        this.data = iArr2;
        this.wrapAroundMask = iArr2.length - 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int remove() {
        int i = this.size;
        if (i == 0) {
            throw new NoSuchElementException();
        }
        int[] iArr = this.data;
        int i2 = this.headIndex;
        int i3 = iArr[i2];
        this.headIndex = (i2 + 1) & this.wrapAroundMask;
        this.size = i - 1;
        return i3;
    }

    public int size() {
        return this.size;
    }
}
