package com.amazon.ion;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonBlob extends IonLob {
    @Override // com.amazon.ion.IonLob, com.amazon.ion.IonValue
    IonBlob clone() throws UnknownSymbolException;

    void printBase64(Appendable appendable) throws NullValueException, IOException;
}
