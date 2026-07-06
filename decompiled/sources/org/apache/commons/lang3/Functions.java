package org.apache.commons.lang3;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.UndeclaredThrowableException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class Functions {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @FunctionalInterface
    public interface FailableBiConsumer<O1, O2, T extends Throwable> {
        void accept(O1 o1, O2 o2) throws Throwable;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @FunctionalInterface
    public interface FailableBiFunction<I1, I2, O, T extends Throwable> {
        O apply(I1 i1, I2 i2) throws Throwable;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @FunctionalInterface
    public interface FailableBiPredicate<O1, O2, T extends Throwable> {
        boolean test(O1 o1, O2 o2) throws Throwable;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @FunctionalInterface
    public interface FailableCallable<O, T extends Throwable> {
        O call() throws Throwable;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @FunctionalInterface
    public interface FailableConsumer<O, T extends Throwable> {
        void accept(O o) throws Throwable;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @FunctionalInterface
    public interface FailableFunction<I, O, T extends Throwable> {
        O apply(I i) throws Throwable;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @FunctionalInterface
    public interface FailablePredicate<O, T extends Throwable> {
        boolean test(O o) throws Throwable;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @FunctionalInterface
    public interface FailableRunnable<T extends Throwable> {
        void run() throws Throwable;
    }

    public static /* synthetic */ void $r8$lambda$LyxSlFFCKMusjwBLBptnWUtqAuo(Throwable th) {
        rethrow(th);
        throw null;
    }

    public static <O, T extends Throwable> void accept(FailableConsumer<O, T> failableConsumer, O o) {
        try {
            failableConsumer.accept(o);
            throw null;
        } catch (Throwable th) {
            rethrow(th);
            throw null;
        }
    }

    public static <I, O, T extends Throwable> O apply(FailableFunction<I, O, T> failableFunction, I i) {
        try {
            return failableFunction.apply(i);
        } catch (Throwable th) {
            rethrow(th);
            throw null;
        }
    }

    public static <O, T extends Throwable> O call(FailableCallable<O, T> failableCallable) {
        try {
            return failableCallable.call();
        } catch (Throwable th) {
            rethrow(th);
            throw null;
        }
    }

    public static RuntimeException rethrow(Throwable th) {
        if (th == null) {
            throw new NullPointerException("The Throwable must not be null.");
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        if (th instanceof IOException) {
            throw new UncheckedIOException((IOException) th);
        }
        throw new UndeclaredThrowableException(th);
    }

    public static <T extends Throwable> void run(FailableRunnable<T> failableRunnable) {
        try {
            failableRunnable.run();
        } catch (Throwable th) {
            rethrow(th);
            throw null;
        }
    }

    public static <O, T extends Throwable> boolean test(FailablePredicate<O, T> failablePredicate, O o) {
        try {
            return failablePredicate.test(o);
        } catch (Throwable th) {
            rethrow(th);
            throw null;
        }
    }

    @SafeVarargs
    public static void tryWithResources(FailableRunnable<? extends Throwable> failableRunnable, FailableConsumer<Throwable, ? extends Throwable> failableConsumer, FailableRunnable<? extends Throwable>... failableRunnableArr) {
        if (failableConsumer == null) {
            failableConsumer = new Functions$$ExternalSyntheticLambda0();
        }
        if (failableRunnableArr != null) {
            for (FailableRunnable<? extends Throwable> failableRunnable2 : failableRunnableArr) {
                if (failableRunnable2 == null) {
                    throw new NullPointerException("A resource action must not be null.");
                }
            }
        }
        try {
            failableRunnable.run();
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        if (failableRunnableArr != null) {
            for (FailableRunnable<? extends Throwable> failableRunnable3 : failableRunnableArr) {
                try {
                    failableRunnable3.run();
                } catch (Throwable th2) {
                    if (th == null) {
                        th = th2;
                    }
                }
            }
        }
        if (th == null) {
            return;
        }
        try {
            failableConsumer.accept(th);
            throw null;
        } catch (Throwable th3) {
            rethrow(th3);
            throw null;
        }
    }

    public static <O1, O2, T extends Throwable> void accept(FailableBiConsumer<O1, O2, T> failableBiConsumer, O1 o1, O2 o2) {
        try {
            failableBiConsumer.accept(o1, o2);
        } catch (Throwable th) {
            rethrow(th);
            throw null;
        }
    }

    public static <I1, I2, O, T extends Throwable> O apply(FailableBiFunction<I1, I2, O, T> failableBiFunction, I1 i1, I2 i2) {
        try {
            return failableBiFunction.apply(i1, i2);
        } catch (Throwable th) {
            rethrow(th);
            throw null;
        }
    }

    public static <O1, O2, T extends Throwable> boolean test(FailableBiPredicate<O1, O2, T> failableBiPredicate, O1 o1, O2 o2) {
        try {
            return failableBiPredicate.test(o1, o2);
        } catch (Throwable th) {
            rethrow(th);
            throw null;
        }
    }

    @SafeVarargs
    public static void tryWithResources(FailableRunnable<? extends Throwable> failableRunnable, FailableRunnable<? extends Throwable>... failableRunnableArr) {
        tryWithResources(failableRunnable, null, failableRunnableArr);
    }
}
