package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class SimpleTimeLimiter implements TimeLimiter {
    public final ExecutorService executor;

    public static /* synthetic */ Object $r8$lambda$O8UtKbQsnNbtQw6Al7O3tFe1PH4(Method method, Object obj, Object[] objArr) throws Exception {
        try {
            return method.invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            throwCause(e, false);
            throw null;
        }
    }

    public static /* synthetic */ Object $r8$lambda$__pOonjahG6xqUkakBXu3MBEpvQ(SimpleTimeLimiter simpleTimeLimiter, final Object obj, long j, TimeUnit timeUnit, Set set, Object obj2, final Method method, final Object[] objArr) {
        simpleTimeLimiter.getClass();
        return simpleTimeLimiter.callWithTimeout(new Callable() { // from class: com.google.common.util.concurrent.SimpleTimeLimiter$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SimpleTimeLimiter.$r8$lambda$O8UtKbQsnNbtQw6Al7O3tFe1PH4(method, obj, objArr);
            }
        }, j, timeUnit, set.contains(method));
    }

    public SimpleTimeLimiter(ExecutorService executor) {
        executor.getClass();
        this.executor = executor;
    }

    public static void checkPositiveTimeout(long timeoutDuration) {
        Preconditions.checkArgument(timeoutDuration > 0, "timeout must be positive: %s", timeoutDuration);
    }

    public static SimpleTimeLimiter create(ExecutorService executor) {
        return new SimpleTimeLimiter(executor);
    }

    public static boolean declaresInterruptedEx(Method method) {
        for (Class<?> cls : method.getExceptionTypes()) {
            if (cls == InterruptedException.class) {
                return true;
            }
        }
        return false;
    }

    public static Set<Method> findInterruptibleMethods(Class<?> interfaceType) {
        HashSet hashSet = new HashSet();
        for (Method method : interfaceType.getMethods()) {
            if (declaresInterruptedEx(method)) {
                hashSet.add(method);
            }
        }
        return hashSet;
    }

    public static <T> T newProxy(Class<T> interfaceType, InvocationHandler handler) {
        return interfaceType.cast(Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, handler));
    }

    public static Exception throwCause(Exception e, boolean combineStackTraces) throws Exception {
        Throwable cause = e.getCause();
        if (cause == null) {
            throw e;
        }
        if (combineStackTraces) {
            cause.setStackTrace((StackTraceElement[]) ObjectArrays.concat(cause.getStackTrace(), e.getStackTrace(), StackTraceElement.class));
        }
        if (cause instanceof Exception) {
            throw ((Exception) cause);
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        throw e;
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    @CanIgnoreReturnValue
    @ParametricNullness
    public <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        callable.getClass();
        timeUnit.getClass();
        checkPositiveTimeout(j);
        Future<T> futureSubmit = this.executor.submit(callable);
        try {
            return (T) Uninterruptibles.getUninterruptibly(futureSubmit, j, timeUnit);
        } catch (ExecutionException e) {
            wrapAndThrowExecutionExceptionOrError(e.getCause());
            throw null;
        } catch (TimeoutException e2) {
            futureSubmit.cancel(true);
            throw e2;
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    @CanIgnoreReturnValue
    @ParametricNullness
    public <T> T callWithTimeout(Callable<T> callable, long timeoutDuration, TimeUnit timeoutUnit) throws Throwable {
        callable.getClass();
        timeoutUnit.getClass();
        checkPositiveTimeout(timeoutDuration);
        Future<T> futureSubmit = this.executor.submit(callable);
        try {
            return futureSubmit.get(timeoutDuration, timeoutUnit);
        } catch (InterruptedException e) {
            e = e;
            futureSubmit.cancel(true);
            throw e;
        } catch (ExecutionException e2) {
            wrapAndThrowExecutionExceptionOrError(e2.getCause());
            throw null;
        } catch (TimeoutException e3) {
            e = e3;
            futureSubmit.cancel(true);
            throw e;
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runUninterruptiblyWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) throws TimeoutException {
        runnable.getClass();
        timeoutUnit.getClass();
        checkPositiveTimeout(timeoutDuration);
        Future<?> futureSubmit = this.executor.submit(runnable);
        try {
            Uninterruptibles.getUninterruptibly(futureSubmit, timeoutDuration, timeoutUnit);
        } catch (ExecutionException e) {
            wrapAndThrowRuntimeExecutionExceptionOrError(e.getCause());
            throw null;
        } catch (TimeoutException e2) {
            futureSubmit.cancel(true);
            throw e2;
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) throws Throwable {
        runnable.getClass();
        timeoutUnit.getClass();
        checkPositiveTimeout(timeoutDuration);
        Future<?> futureSubmit = this.executor.submit(runnable);
        try {
            futureSubmit.get(timeoutDuration, timeoutUnit);
        } catch (InterruptedException e) {
            e = e;
            futureSubmit.cancel(true);
            throw e;
        } catch (ExecutionException e2) {
            wrapAndThrowRuntimeExecutionExceptionOrError(e2.getCause());
            throw null;
        } catch (TimeoutException e3) {
            e = e3;
            futureSubmit.cancel(true);
            throw e;
        }
    }

    public final void wrapAndThrowExecutionExceptionOrError(Throwable cause) throws ExecutionException {
        if (cause instanceof Error) {
            throw new ExecutionError(cause);
        }
        if (!(cause instanceof RuntimeException)) {
            throw new ExecutionException(cause);
        }
        throw new UncheckedExecutionException(cause);
    }

    public final void wrapAndThrowRuntimeExecutionExceptionOrError(Throwable cause) {
        if (!(cause instanceof Error)) {
            throw new UncheckedExecutionException(cause);
        }
        throw new ExecutionError(cause);
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T newProxy(final T t, Class<T> cls, final long j, final TimeUnit timeUnit) {
        t.getClass();
        cls.getClass();
        timeUnit.getClass();
        checkPositiveTimeout(j);
        Preconditions.checkArgument(cls.isInterface(), "interfaceType must be an interface type");
        final Set<Method> setFindInterruptibleMethods = findInterruptibleMethods(cls);
        return (T) newProxy(cls, new InvocationHandler() { // from class: com.google.common.util.concurrent.SimpleTimeLimiter$$ExternalSyntheticLambda1
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                return SimpleTimeLimiter.$r8$lambda$__pOonjahG6xqUkakBXu3MBEpvQ(this.f$0, t, j, timeUnit, setFindInterruptibleMethods, obj, method, objArr);
            }
        });
    }

    @ParametricNullness
    public final <T> T callWithTimeout(Callable<T> callable, long j, TimeUnit timeUnit, boolean z) throws Exception {
        callable.getClass();
        timeUnit.getClass();
        checkPositiveTimeout(j);
        Future<T> futureSubmit = this.executor.submit(callable);
        try {
            if (z) {
                return futureSubmit.get(j, timeUnit);
            }
            return (T) Uninterruptibles.getUninterruptibly(futureSubmit, j, timeUnit);
        } catch (InterruptedException e) {
            futureSubmit.cancel(true);
            throw e;
        } catch (ExecutionException e2) {
            throwCause(e2, true);
            throw null;
        } catch (TimeoutException e3) {
            futureSubmit.cancel(true);
            throw new UncheckedTimeoutException(e3);
        }
    }
}
