package org.apache.commons.lang3.concurrent;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class AtomicSafeInitializer<T> implements ConcurrentInitializer<T> {
    public final AtomicReference<AtomicSafeInitializer<T>> factory = new AtomicReference<>();
    public final AtomicReference<T> reference = new AtomicReference<>();

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public final T get() throws ConcurrentException {
        while (true) {
            T t = this.reference.get();
            if (t != null) {
                return t;
            }
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.factory, null, this)) {
                this.reference.set(initialize());
            }
        }
    }

    public abstract T initialize() throws ConcurrentException;
}
