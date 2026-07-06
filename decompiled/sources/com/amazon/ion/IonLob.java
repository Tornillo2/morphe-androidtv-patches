package com.amazon.ion;

import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonLob extends IonValue {
    int byteSize();

    @Override // com.amazon.ion.IonValue
    IonLob clone() throws UnknownSymbolException;

    byte[] getBytes();

    InputStream newInputStream();

    void setBytes(byte[] bArr);

    void setBytes(byte[] bArr, int i, int i2);
}
