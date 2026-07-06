package com.amazon.ion;

import java.util.IdentityHashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonException extends RuntimeException {
    public static final long serialVersionUID = 5769577011706279252L;

    public IonException() {
    }

    public <T extends Throwable> T causeOfType(Class<T> cls) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        T t = (T) getCause();
        while (t != null && !cls.isInstance(t)) {
            if (identityHashMap.put(t, t) != null) {
                return null;
            }
            t = (T) t.getCause();
        }
        return t;
    }

    public Throwable externalCause() {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        Throwable cause = getCause();
        while (cause instanceof IonException) {
            if (identityHashMap.put(cause, cause) != null) {
                return null;
            }
            cause = cause.getCause();
        }
        return cause;
    }

    public final <T extends Throwable> void rethrowCauseOfType(Class<T> cls) throws Throwable {
        Throwable thCauseOfType = causeOfType(cls);
        if (thCauseOfType != null) {
            throw thCauseOfType;
        }
    }

    public IonException(String str) {
        super(str);
    }

    public IonException(String str, Throwable th) {
        super(str, th);
    }

    public IonException(Throwable th) {
        super(th.getMessage(), th);
    }
}
