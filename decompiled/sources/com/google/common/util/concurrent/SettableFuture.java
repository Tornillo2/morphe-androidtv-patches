package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class SettableFuture<V> extends AbstractFuture.TrustedFuture<V> {
    public static <V> SettableFuture<V> create() {
        return new SettableFuture<>();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    @CanIgnoreReturnValue
    public boolean set(@ParametricNullness V value) {
        return super.set(value);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    @CanIgnoreReturnValue
    public boolean setException(Throwable throwable) {
        return super.setException(throwable);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    @CanIgnoreReturnValue
    public boolean setFuture(ListenableFuture<? extends V> future) {
        return super.setFuture(future);
    }
}
