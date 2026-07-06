package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ExecutionModule_ExecutorFactory implements Factory<Executor> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final ExecutionModule_ExecutorFactory INSTANCE = new ExecutionModule_ExecutorFactory();
    }

    public static ExecutionModule_ExecutorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Executor executor() {
        return ExecutionModule.executor();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return ExecutionModule.executor();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Executor get() {
        return ExecutionModule.executor();
    }
}
