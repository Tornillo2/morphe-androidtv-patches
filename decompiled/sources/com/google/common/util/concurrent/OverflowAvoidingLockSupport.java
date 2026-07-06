package com.google.common.util.concurrent;

import com.google.common.annotations.J2ktIncompatible;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
public final class OverflowAvoidingLockSupport {
    public static final long MAX_NANOSECONDS_THRESHOLD = 2147483647999999999L;

    public static void parkNanos(Object blocker, long nanos) {
        LockSupport.parkNanos(blocker, Math.min(nanos, MAX_NANOSECONDS_THRESHOLD));
    }
}
