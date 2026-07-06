package androidx.media3.common;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline0;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class PriorityTaskManager {
    public final Object lock = new Object();
    public final PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder());
    public int highestPriority = Integer.MIN_VALUE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class PriorityTooLowException extends IOException {
        public PriorityTooLowException(int i, int i2) {
            super(ObjectListKt$$ExternalSyntheticOutline0.m("Priority too low [priority=", i, ", highest=", i2, "]"));
        }
    }

    public void add(int i) {
        synchronized (this.lock) {
            this.queue.add(Integer.valueOf(i));
            this.highestPriority = Math.max(this.highestPriority, i);
        }
    }

    public void proceed(int i) throws InterruptedException {
        synchronized (this.lock) {
            while (this.highestPriority != i) {
                try {
                    this.lock.wait();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public boolean proceedNonBlocking(int i) {
        boolean z;
        synchronized (this.lock) {
            z = this.highestPriority == i;
        }
        return z;
    }

    public void proceedOrThrow(int i) throws PriorityTooLowException {
        synchronized (this.lock) {
            try {
                if (this.highestPriority != i) {
                    throw new PriorityTooLowException(i, this.highestPriority);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void remove(int i) {
        int iIntValue;
        synchronized (this.lock) {
            this.queue.remove(Integer.valueOf(i));
            if (this.queue.isEmpty()) {
                iIntValue = Integer.MIN_VALUE;
            } else {
                Integer numPeek = this.queue.peek();
                Util.castNonNull(numPeek);
                iIntValue = numPeek.intValue();
            }
            this.highestPriority = iIntValue;
            this.lock.notifyAll();
        }
    }
}
