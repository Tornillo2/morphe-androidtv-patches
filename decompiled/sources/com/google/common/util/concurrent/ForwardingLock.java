package com.google.common.util.concurrent;

import com.google.common.annotations.J2ktIncompatible;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
public abstract class ForwardingLock implements Lock {
    public abstract Lock delegate();

    @Override // java.util.concurrent.locks.Lock
    public void lock() {
        delegate().lock();
    }

    @Override // java.util.concurrent.locks.Lock
    public void lockInterruptibly() throws InterruptedException {
        delegate().lockInterruptibly();
    }

    @Override // java.util.concurrent.locks.Lock
    public Condition newCondition() {
        return delegate().newCondition();
    }

    @Override // java.util.concurrent.locks.Lock
    public boolean tryLock() {
        return delegate().tryLock();
    }

    @Override // java.util.concurrent.locks.Lock
    public void unlock() {
        delegate().unlock();
    }

    @Override // java.util.concurrent.locks.Lock
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return delegate().tryLock(time, unit);
    }
}
