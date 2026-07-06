package com.amazon.ion;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class UnknownSymbolException extends IonException {
    public static final long serialVersionUID = 1;
    public final int mySid;
    public final String myText;

    public UnknownSymbolException(int i) {
        this.mySid = i;
        this.myText = null;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String str = this.myText;
        if (str != null) {
            return str;
        }
        return "Unknown symbol text for $" + this.mySid;
    }

    public int getSid() {
        return this.mySid;
    }

    public UnknownSymbolException(String str) {
        this.myText = str;
        this.mySid = 0;
    }
}
