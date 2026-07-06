package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class LittleEndianDataOutputStream extends FilterOutputStream implements DataOutput {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LittleEndianDataOutputStream(OutputStream out) {
        super(new DataOutputStream(out));
        out.getClass();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ((FilterOutputStream) this).out.close();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.DataOutput
    public void write(byte[] b, int off, int len) throws IOException {
        ((FilterOutputStream) this).out.write(b, off, len);
    }

    @Override // java.io.DataOutput
    public void writeBoolean(boolean v) throws IOException {
        ((DataOutputStream) ((FilterOutputStream) this).out).writeBoolean(v);
    }

    @Override // java.io.DataOutput
    public void writeByte(int v) throws IOException {
        ((DataOutputStream) ((FilterOutputStream) this).out).writeByte(v);
    }

    @Override // java.io.DataOutput
    @Deprecated
    public void writeBytes(String s) throws IOException {
        ((DataOutputStream) ((FilterOutputStream) this).out).writeBytes(s);
    }

    @Override // java.io.DataOutput
    public void writeChar(int v) throws IOException {
        writeShort(v);
    }

    @Override // java.io.DataOutput
    public void writeChars(String s) throws IOException {
        for (int i = 0; i < s.length(); i++) {
            writeShort(s.charAt(i));
        }
    }

    @Override // java.io.DataOutput
    public void writeDouble(double v) throws IOException {
        writeLong(Double.doubleToLongBits(v));
    }

    @Override // java.io.DataOutput
    public void writeFloat(float v) throws IOException {
        writeInt(Float.floatToIntBits(v));
    }

    @Override // java.io.DataOutput
    public void writeInt(int v) throws IOException {
        ((FilterOutputStream) this).out.write(v & 255);
        ((FilterOutputStream) this).out.write((v >> 8) & 255);
        ((FilterOutputStream) this).out.write((v >> 16) & 255);
        ((FilterOutputStream) this).out.write((v >> 24) & 255);
    }

    @Override // java.io.DataOutput
    public void writeLong(long v) throws IOException {
        ((DataOutputStream) ((FilterOutputStream) this).out).writeLong(Long.reverseBytes(v));
    }

    @Override // java.io.DataOutput
    public void writeShort(int v) throws IOException {
        ((FilterOutputStream) this).out.write(v & 255);
        ((FilterOutputStream) this).out.write((v >> 8) & 255);
    }

    @Override // java.io.DataOutput
    public void writeUTF(String str) throws IOException {
        ((DataOutputStream) ((FilterOutputStream) this).out).writeUTF(str);
    }
}
