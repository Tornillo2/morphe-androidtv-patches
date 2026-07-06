package com.amazon.ignitionshared;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ThreadUtils {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Condition {
        boolean evaluate();
    }

    public ThreadUtils() {
        throw new UnsupportedOperationException("Abstract utility class; do not instantiate");
    }

    public static Object createMutex() {
        return new Object();
    }

    public static boolean wait(Object obj, Condition condition, long j) {
        if (condition.evaluate()) {
            return true;
        }
        long jNanoTime = System.nanoTime();
        long jNanoTime2 = j;
        while (jNanoTime2 > 0) {
            try {
                obj.wait(jNanoTime2);
            } catch (InterruptedException unused) {
            }
            if (condition.evaluate()) {
                return true;
            }
            jNanoTime2 = j - ((System.nanoTime() - jNanoTime) / 1000000);
        }
        return false;
    }
}
