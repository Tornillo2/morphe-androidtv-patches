package com.amazon.ion;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class NullValueException extends IonException {
    public static final long serialVersionUID = 1;

    public NullValueException() {
    }

    public NullValueException(String str) {
        super(str);
    }

    public NullValueException(String str, Throwable th) {
        super(str, th);
    }

    public NullValueException(Throwable th) {
        super(th);
    }
}
