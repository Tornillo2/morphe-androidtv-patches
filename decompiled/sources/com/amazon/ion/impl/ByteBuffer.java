package com.amazon.ion.impl;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface ByteBuffer {
    int getBytes(byte[] bArr, int i, int i2);

    byte[] getBytes();

    ByteReader getReader();

    ByteWriter getWriter();

    void writeBytes(OutputStream outputStream) throws IOException;
}
