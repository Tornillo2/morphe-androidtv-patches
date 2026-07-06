package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public abstract class Striped64 extends Number {
    public static final long BASE_OFFSET;
    public static final long BUSY_OFFSET;
    public static final Unsafe UNSAFE;
    public volatile transient long base;
    public volatile transient int busy;
    public volatile transient Cell[] cells;
    public static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    public static final Random rng = new Random();
    public static final int NCPU = Runtime.getRuntime().availableProcessors();

    /* JADX INFO: renamed from: com.google.common.cache.Striped64$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements PrivilegedExceptionAction<Unsafe> {
        @Override // java.security.PrivilegedExceptionAction
        public Unsafe run() throws Exception {
            for (Field field : Unsafe.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            throw new NoSuchFieldError("the Unsafe");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Cell {
        public static final Unsafe UNSAFE;
        public static final long VALUE_OFFSET;
        public volatile long p0;
        public volatile long p1;
        public volatile long p2;
        public volatile long p3;
        public volatile long p4;
        public volatile long p5;
        public volatile long p6;
        public volatile long q0;
        public volatile long q1;
        public volatile long q2;
        public volatile long q3;
        public volatile long q4;
        public volatile long q5;
        public volatile long q6;
        public volatile long value;

        static {
            try {
                Unsafe unsafe = Striped64.getUnsafe();
                UNSAFE = unsafe;
                VALUE_OFFSET = unsafe.objectFieldOffset(Cell.class.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        public Cell(long x) {
            this.value = x;
        }

        public final boolean cas(long cmp, long val) {
            return UNSAFE.compareAndSwapLong(this, VALUE_OFFSET, cmp, val);
        }
    }

    static {
        try {
            Unsafe unsafe = getUnsafe();
            UNSAFE = unsafe;
            BASE_OFFSET = unsafe.objectFieldOffset(Striped64.class.getDeclaredField("base"));
            BUSY_OFFSET = unsafe.objectFieldOffset(Striped64.class.getDeclaredField("busy"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static Unsafe getUnsafe() {
        try {
            try {
                return Unsafe.getUnsafe();
            } catch (PrivilegedActionException e) {
                throw new RuntimeException("Could not initialize intrinsics", e.getCause());
            }
        } catch (SecurityException unused) {
            return (Unsafe) AccessController.doPrivileged(new AnonymousClass1());
        }
    }

    public final boolean casBase(long cmp, long val) {
        return UNSAFE.compareAndSwapLong(this, BASE_OFFSET, cmp, val);
    }

    public final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, BUSY_OFFSET, 0, 1);
    }

    public abstract long fn(long currentValue, long newValue);

    public final void internalReset(long initialValue) {
        Cell[] cellArr = this.cells;
        this.base = initialValue;
        if (cellArr != null) {
            for (Cell cell : cellArr) {
                if (cell != null) {
                    cell.value = initialValue;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x0023 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00f1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void retryUpdate(long r17, int[] r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.Striped64.retryUpdate(long, int[], boolean):void");
    }
}
