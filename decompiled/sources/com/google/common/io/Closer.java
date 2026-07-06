package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Throwables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class Closer implements Closeable {
    public static final Suppressor SUPPRESSING_SUPPRESSOR = new Closer$$ExternalSyntheticLambda0();
    public final Deque<Closeable> stack = new ArrayDeque(4);

    @VisibleForTesting
    public final Suppressor suppressor;
    public Throwable thrown;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public interface Suppressor {
        void suppress(Closeable closeable, Throwable thrown, Throwable suppressed);
    }

    /* JADX INFO: renamed from: $r8$lambda$MTDIWeSId9CNMGBPrEaFO8aaB-o, reason: not valid java name */
    public static /* synthetic */ void m550$r8$lambda$MTDIWeSId9CNMGBPrEaFO8aaBo(Closeable closeable, Throwable th, Throwable th2) {
        if (th == th2) {
            return;
        }
        try {
            th.addSuppressed(th2);
        } catch (Throwable unused) {
            Closeables.logger.log(Level.WARNING, "Suppressing exception thrown when closing " + closeable, th2);
        }
    }

    @VisibleForTesting
    public Closer(Suppressor suppressor) {
        suppressor.getClass();
        this.suppressor = suppressor;
    }

    public static Closer create() {
        return new Closer(SUPPRESSING_SUPPRESSOR);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        Throwable th = this.thrown;
        while (!this.stack.isEmpty()) {
            Closeable closeableRemoveFirst = this.stack.removeFirst();
            try {
                closeableRemoveFirst.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    this.suppressor.suppress(closeableRemoveFirst, th, th2);
                }
            }
        }
        if (this.thrown != null || th == null) {
            return;
        }
        Throwables.throwIfInstanceOf(th, IOException.class);
        Throwables.throwIfUnchecked(th);
        throw new AssertionError(th);
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public <C extends Closeable> C register(@ParametricNullness C closeable) {
        if (closeable != null) {
            this.stack.addFirst(closeable);
        }
        return closeable;
    }

    public RuntimeException rethrow(Throwable e) throws Throwable {
        e.getClass();
        this.thrown = e;
        Throwables.throwIfInstanceOf(e, IOException.class);
        Throwables.throwIfUnchecked(e);
        throw new RuntimeException(e);
    }

    public <X extends Exception> RuntimeException rethrow(Throwable e, Class<X> declaredType) throws Exception {
        e.getClass();
        this.thrown = e;
        Throwables.throwIfInstanceOf(e, IOException.class);
        Throwables.throwIfInstanceOf(e, declaredType);
        Throwables.throwIfUnchecked(e);
        throw new RuntimeException(e);
    }

    public <X1 extends Exception, X2 extends Exception> RuntimeException rethrow(Throwable e, Class<X1> declaredType1, Class<X2> declaredType2) throws Exception {
        e.getClass();
        this.thrown = e;
        Throwables.throwIfInstanceOf(e, IOException.class);
        Throwables.throwIfInstanceOf(e, declaredType1);
        Throwables.throwIfInstanceOf(e, declaredType2);
        Throwables.throwIfUnchecked(e);
        throw new RuntimeException(e);
    }
}
