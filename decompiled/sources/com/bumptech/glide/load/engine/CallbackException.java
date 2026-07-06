package com.bumptech.glide.load.engine;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CallbackException extends RuntimeException {
    public static final long serialVersionUID = -7530898992688511851L;

    public CallbackException(Throwable th) {
        super("Unexpected exception thrown by non-Glide code", th);
    }
}
