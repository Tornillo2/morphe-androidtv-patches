package org.apache.commons.lang3.concurrent;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class CircuitBreakingException extends RuntimeException {
    public static final long serialVersionUID = 1408176654686913340L;

    public CircuitBreakingException() {
    }

    public CircuitBreakingException(String str, Throwable th) {
        super(str, th);
    }

    public CircuitBreakingException(String str) {
        super(str);
    }

    public CircuitBreakingException(Throwable th) {
        super(th);
    }
}
