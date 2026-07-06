package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public class VerifyException extends RuntimeException {
    public VerifyException() {
    }

    public VerifyException(String message) {
        super(message);
    }

    public VerifyException(Throwable cause) {
        super(cause);
    }

    public VerifyException(String message, Throwable cause) {
        super(message, cause);
    }
}
