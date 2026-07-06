package com.amazon.ion;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class InvalidSystemSymbolException extends IonException {
    public static final long serialVersionUID = 2206499395645594047L;
    public String myBadSymbol;

    public InvalidSystemSymbolException(String str) {
        super(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Invalid system symbol '", str, "'"));
        this.myBadSymbol = str;
    }

    public String getBadSymbol() {
        return this.myBadSymbol;
    }
}
