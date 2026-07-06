package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public class UncheckedTimeoutException extends RuntimeException {
    public static final long serialVersionUID = 0;

    public UncheckedTimeoutException() {
    }

    public UncheckedTimeoutException(String message) {
        super(message);
    }

    public UncheckedTimeoutException(Throwable cause) {
        super(cause);
    }

    public UncheckedTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
