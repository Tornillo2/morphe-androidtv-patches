package com.amazon.ion;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class UnsupportedIonVersionException extends IonException {
    public static final long serialVersionUID = -1166749371823975664L;
    public final String _unsupportedIonVersionId;

    public UnsupportedIonVersionException(String str) {
        this._unsupportedIonVersionId = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Unsupported Ion version " + this._unsupportedIonVersionId;
    }

    public String getUnsuportedIonVersionId() {
        return this._unsupportedIonVersionId;
    }
}
