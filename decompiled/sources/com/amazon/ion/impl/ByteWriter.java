package com.amazon.ion.impl;

import java.io.IOException;
import java.math.BigDecimal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface ByteWriter {
    void insert(int i);

    int position();

    void position(int i);

    void remove(int i);

    void write(byte b) throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;

    int writeDecimal(BigDecimal bigDecimal) throws IOException;

    int writeFloat(double d) throws IOException;

    int writeIonInt(int i, int i2) throws IOException;

    int writeIonInt(long j, int i) throws IOException;

    int writeString(String str) throws IOException;

    void writeTypeDesc(int i) throws IOException;

    int writeTypeDescWithLength(int i, int i2) throws IOException;

    int writeTypeDescWithLength(int i, int i2, int i3) throws IOException;

    int writeVarInt(int i, int i2, boolean z) throws IOException;

    int writeVarInt(long j, int i, boolean z) throws IOException;

    int writeVarUInt(int i, int i2, boolean z) throws IOException;

    int writeVarUInt(long j, int i, boolean z) throws IOException;
}
