package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class LazyLogger {
    public final Object lock = new Object();
    public volatile Logger logger;
    public final String loggerName;

    public LazyLogger(Class<?> ownerOfLogger) {
        this.loggerName = ownerOfLogger.getName();
    }

    public Logger get() {
        Logger logger = this.logger;
        if (logger != null) {
            return logger;
        }
        synchronized (this.lock) {
            try {
                Logger logger2 = this.logger;
                if (logger2 != null) {
                    return logger2;
                }
                Logger logger3 = Logger.getLogger(this.loggerName);
                this.logger = logger3;
                return logger3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
