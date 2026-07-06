package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class CallableBackgroundInitializer<T> extends BackgroundInitializer<T> {
    public final Callable<T> callable;

    public CallableBackgroundInitializer(Callable<T> callable, ExecutorService executorService) {
        super(executorService);
        checkCallable(callable);
        this.callable = callable;
    }

    public final void checkCallable(Callable<T> callable) {
        Validate.isTrue(callable != null, "Callable must not be null!", new Object[0]);
    }

    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer
    public T initialize() throws Exception {
        return this.callable.call();
    }

    public CallableBackgroundInitializer(Callable<T> callable) {
        super(null);
        checkCallable(callable);
        this.callable = callable;
    }
}
