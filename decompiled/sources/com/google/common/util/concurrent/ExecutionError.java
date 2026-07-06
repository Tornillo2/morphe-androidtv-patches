package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public class ExecutionError extends Error {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    @Deprecated
    public ExecutionError() {
    }

    @Deprecated
    public ExecutionError(String message) {
        super(message);
    }

    public ExecutionError(String message, Error cause) {
        super(message, cause);
    }

    public ExecutionError(Error cause) {
        super(cause);
    }
}
