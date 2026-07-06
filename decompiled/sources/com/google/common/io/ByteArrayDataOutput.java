package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.DataOutput;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public interface ByteArrayDataOutput extends DataOutput {
    byte[] toByteArray();

    @Override // java.io.DataOutput
    void write(int b);

    @Override // java.io.DataOutput
    void write(byte[] b);

    @Override // java.io.DataOutput
    void write(byte[] b, int off, int len);

    @Override // java.io.DataOutput
    void writeBoolean(boolean v);

    @Override // java.io.DataOutput
    void writeByte(int v);

    @Override // java.io.DataOutput
    @Deprecated
    void writeBytes(String s);

    @Override // java.io.DataOutput
    void writeChar(int v);

    @Override // java.io.DataOutput
    void writeChars(String s);

    @Override // java.io.DataOutput
    void writeDouble(double v);

    @Override // java.io.DataOutput
    void writeFloat(float v);

    @Override // java.io.DataOutput
    void writeInt(int v);

    @Override // java.io.DataOutput
    void writeLong(long v);

    @Override // java.io.DataOutput
    void writeShort(int v);

    @Override // java.io.DataOutput
    void writeUTF(String s);
}
