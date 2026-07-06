package com.amazon.livingroom.mediapipelinebackend;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class PtsQueue {
    public long[] queue;
    public int size;
    public int startingIndex = 0;

    public PtsQueue(int i) {
        this.queue = new long[i];
        clear();
    }

    public void add(long j) {
        if (this.size == this.queue.length) {
            resize();
        }
        int i = this.size;
        this.queue[getIndexIntoQueue(i)] = j;
        while (i > 0) {
            int i2 = i - 1;
            if (j >= this.queue[getIndexIntoQueue(i2)]) {
                break;
            }
            swap(getIndexIntoQueue(i2), getIndexIntoQueue(i));
            i--;
        }
        this.size++;
    }

    public void clear() {
        Arrays.fill(this.queue, Long.MIN_VALUE);
        this.size = 0;
        this.startingIndex = 0;
    }

    public final int getIndexIntoQueue(int i) {
        return (this.startingIndex + i) % this.queue.length;
    }

    public long peek() {
        return this.queue[this.startingIndex];
    }

    public void pop() {
        int i = this.size;
        if (i > 0) {
            long[] jArr = this.queue;
            int i2 = this.startingIndex;
            jArr[i2] = Long.MIN_VALUE;
            this.size = i - 1;
            this.startingIndex = (i2 + 1) % jArr.length;
        }
    }

    public final void resize() {
        long[] jArr = new long[this.queue.length * 2];
        Arrays.fill(jArr, Long.MIN_VALUE);
        for (int i = 0; i < this.size; i++) {
            jArr[i] = this.queue[getIndexIntoQueue(i)];
        }
        this.queue = jArr;
        this.startingIndex = 0;
    }

    public int size() {
        return this.size;
    }

    public final void swap(int i, int i2) {
        long[] jArr = this.queue;
        long j = jArr[i];
        jArr[i] = jArr[i2];
        jArr[i2] = j;
    }
}
