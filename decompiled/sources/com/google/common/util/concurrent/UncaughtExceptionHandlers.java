package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import j$.util.Objects;
import java.lang.Thread;
import java.util.Locale;
import java.util.logging.Level;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class UncaughtExceptionHandlers {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class Exiter implements Thread.UncaughtExceptionHandler {
        public static final LazyLogger logger = new LazyLogger(Exiter.class);
        public final RuntimeWrapper runtime;

        public Exiter(RuntimeWrapper runtime) {
            this.runtime = runtime;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread t, Throwable e) {
            try {
                logger.get().log(Level.SEVERE, String.format(Locale.ROOT, "Caught an exception in %s.  Shutting down.", t), e);
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public interface RuntimeWrapper {
        void exit(int status);
    }

    public static Thread.UncaughtExceptionHandler systemExit() {
        final Runtime runtime = Runtime.getRuntime();
        Objects.requireNonNull(runtime);
        return new Exiter(new RuntimeWrapper() { // from class: com.google.common.util.concurrent.UncaughtExceptionHandlers$$ExternalSyntheticLambda0
            @Override // com.google.common.util.concurrent.UncaughtExceptionHandlers.RuntimeWrapper
            public final void exit(int i) {
                runtime.exit(i);
            }
        });
    }
}
