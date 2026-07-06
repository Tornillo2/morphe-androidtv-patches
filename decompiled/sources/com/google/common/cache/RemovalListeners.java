package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public final class RemovalListeners {
    public static <K, V> RemovalListener<K, V> asynchronous(final RemovalListener<K, V> listener, final Executor executor) {
        listener.getClass();
        executor.getClass();
        return new RemovalListener() { // from class: com.google.common.cache.RemovalListeners$$ExternalSyntheticLambda1
            @Override // com.google.common.cache.RemovalListener
            public final void onRemoval(RemovalNotification removalNotification) {
                executor.execute(new Runnable() { // from class: com.google.common.cache.RemovalListeners$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        removalListener.onRemoval(removalNotification);
                    }
                });
            }
        };
    }
}
