package org.apache.commons.lang3.concurrent;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ConcurrentException extends Exception {
    public static final long serialVersionUID = 6622707671812226130L;

    public ConcurrentException() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcurrentException(Throwable th) {
        super(th);
        ConcurrentUtils.checkedException(th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcurrentException(String str, Throwable th) {
        super(str, th);
        ConcurrentUtils.checkedException(th);
    }
}
