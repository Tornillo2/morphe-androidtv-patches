package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.Timestamp;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface ByteReader {
    public static final int EOF = -1;

    int position();

    void position(int i);

    int read() throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    Decimal readDecimal(int i) throws IOException;

    double readFloat(int i) throws IOException;

    String readString(int i) throws IOException;

    Timestamp readTimestamp(int i) throws IOException;

    int readTypeDesc() throws IOException;

    long readULong(int i) throws IOException;

    int readVarInt() throws IOException;

    long readVarLong() throws IOException;

    int readVarUInt() throws IOException;

    long readVarULong() throws IOException;

    void skip(int i);
}
