package com.amazon.ion;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public interface IonBinaryWriter extends IonWriter {
    @Deprecated
    int byteSize();

    @Deprecated
    int getBytes(byte[] bArr, int i, int i2) throws IOException;

    @Deprecated
    byte[] getBytes() throws IOException;

    @Deprecated
    int writeBytes(OutputStream outputStream) throws IOException;
}
