package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Supplier;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Callables {
    /* JADX INFO: renamed from: $r8$lambda$0JLCWtFc-CX88ML71l-ZR2fwd2o, reason: not valid java name */
    public static /* synthetic */ void m559$r8$lambda$0JLCWtFcCX88ML71lZR2fwd2o(Supplier supplier, Runnable runnable) {
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        boolean zTrySetName = trySetName((String) supplier.get(), threadCurrentThread);
        try {
            runnable.run();
        } finally {
            if (zTrySetName) {
                trySetName(name, threadCurrentThread);
            }
        }
    }

    public static /* synthetic */ Object $r8$lambda$RqoBQCbP49wv7k2RdfUNimRqb4s(Supplier supplier, Callable callable) {
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        boolean zTrySetName = trySetName((String) supplier.get(), threadCurrentThread);
        try {
            return callable.call();
        } finally {
            if (zTrySetName) {
                trySetName(name, threadCurrentThread);
            }
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <T> AsyncCallable<T> asAsyncCallable(final Callable<T> callable, final ListeningExecutorService listeningExecutorService) {
        callable.getClass();
        listeningExecutorService.getClass();
        return new AsyncCallable() { // from class: com.google.common.util.concurrent.Callables$$ExternalSyntheticLambda1
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return listeningExecutorService.submit(callable);
            }
        };
    }

    public static <T> Callable<T> returning(@ParametricNullness final T value) {
        return new Callable() { // from class: com.google.common.util.concurrent.Callables$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return value;
            }
        };
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static Runnable threadRenaming(final Runnable task, final Supplier<String> nameSupplier) {
        nameSupplier.getClass();
        task.getClass();
        return new Runnable() { // from class: com.google.common.util.concurrent.Callables$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                Callables.m559$r8$lambda$0JLCWtFcCX88ML71lZR2fwd2o(nameSupplier, task);
            }
        };
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static boolean trySetName(String threadName, Thread currentThread) {
        try {
            currentThread.setName(threadName);
            return true;
        } catch (SecurityException unused) {
            return false;
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static <T> Callable<T> threadRenaming(final Callable<T> callable, final Supplier<String> nameSupplier) {
        nameSupplier.getClass();
        callable.getClass();
        return new Callable() { // from class: com.google.common.util.concurrent.Callables$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Callables.$r8$lambda$RqoBQCbP49wv7k2RdfUNimRqb4s(nameSupplier, callable);
            }
        };
    }

    public static /* synthetic */ Object $r8$lambda$igoGSsmydPxoPHWhodWU_i0bGEk(Object obj) {
        return obj;
    }
}
