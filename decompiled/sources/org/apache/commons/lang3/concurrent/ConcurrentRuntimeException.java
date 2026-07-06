package org.apache.commons.lang3.concurrent;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ConcurrentRuntimeException extends RuntimeException {
    public static final long serialVersionUID = -6582182735562919670L;

    public ConcurrentRuntimeException() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcurrentRuntimeException(Throwable th) {
        super(th);
        ConcurrentUtils.checkedException(th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcurrentRuntimeException(String str, Throwable th) {
        super(str, th);
        ConcurrentUtils.checkedException(th);
    }
}
