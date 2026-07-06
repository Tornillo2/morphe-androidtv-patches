package com.amazon.ion;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class EmptySymbolException extends IonException {
    public static final long serialVersionUID = -7801632953459636349L;

    public EmptySymbolException() {
        super("Symbols must contain at least one character.");
    }
}
