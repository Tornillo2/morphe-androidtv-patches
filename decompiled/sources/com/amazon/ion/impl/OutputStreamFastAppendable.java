package com.amazon.ion.impl;

import com.amazon.ion.util._Private_FastAppendable;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class OutputStreamFastAppendable implements _Private_FastAppendable, Closeable, Flushable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MAX_BYTES_LEN = 4096;
    public final byte[] _byteBuffer;
    public final OutputStream _out;
    public int _pos;

    public OutputStreamFastAppendable(OutputStream outputStream) {
        outputStream.getClass();
        this._out = outputStream;
        this._pos = 0;
        this._byteBuffer = new byte[4096];
    }

    @Override // java.lang.Appendable
    public Appendable append(char c) throws IOException {
        if (c < 128) {
            appendAscii(c);
            return this;
        }
        appendUtf16(c);
        return this;
    }

    @Override // com.amazon.ion.util._Private_FastAppendable
    public final void appendAscii(char c) throws IOException {
        int i = this._pos;
        byte[] bArr = this._byteBuffer;
        if (i == bArr.length) {
            this._out.write(bArr, 0, i);
            this._pos = 0;
        }
        byte[] bArr2 = this._byteBuffer;
        int i2 = this._pos;
        this._pos = i2 + 1;
        bArr2[i2] = (byte) c;
    }

    @Override // com.amazon.ion.util._Private_FastAppendable
    public final void appendUtf16(char c) throws IOException {
        int i = this._pos;
        byte[] bArr = this._byteBuffer;
        if (i > bArr.length - 3) {
            this._out.write(bArr, 0, i);
            this._pos = 0;
        }
        if (c < 2048) {
            byte[] bArr2 = this._byteBuffer;
            int i2 = this._pos;
            int i3 = i2 + 1;
            this._pos = i3;
            bArr2[i2] = (byte) (((c >> 6) | 192) & 255);
            this._pos = i2 + 2;
            bArr2[i3] = (byte) (((c & '?') | 128) & 255);
            return;
        }
        if (c < 0) {
            byte[] bArr3 = this._byteBuffer;
            int i4 = this._pos;
            int i5 = i4 + 1;
            this._pos = i5;
            bArr3[i4] = (byte) (((c >> '\f') | 224) & 255);
            int i6 = i4 + 2;
            this._pos = i6;
            bArr3[i5] = (byte) ((((c >> 6) & 63) | 128) & 255);
            this._pos = i4 + 3;
            bArr3[i6] = (byte) (((c & '?') | 128) & 255);
        }
    }

    @Override // com.amazon.ion.util._Private_FastAppendable
    public final void appendUtf16Surrogate(char c, char c2) throws IOException {
        int iMakeUnicodeScalar = _Private_IonConstants.makeUnicodeScalar(c, c2);
        int i = this._pos;
        byte[] bArr = this._byteBuffer;
        if (i > bArr.length - 4) {
            this._out.write(bArr, 0, i);
            this._pos = 0;
        }
        byte[] bArr2 = this._byteBuffer;
        int i2 = this._pos;
        int i3 = i2 + 1;
        this._pos = i3;
        bArr2[i2] = (byte) (((iMakeUnicodeScalar >> 18) | 240) & 255);
        int i4 = i2 + 2;
        this._pos = i4;
        bArr2[i3] = (byte) ((((iMakeUnicodeScalar >> 12) & 63) | 128) & 255);
        int i5 = i2 + 3;
        this._pos = i5;
        bArr2[i4] = (byte) ((((iMakeUnicodeScalar >> 6) & 63) | 128) & 255);
        this._pos = i2 + 4;
        bArr2[i5] = (byte) (((iMakeUnicodeScalar & 63) | 128) & 255);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        try {
            flush();
        } finally {
            this._out.close();
        }
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
        int i = this._pos;
        if (i > 0) {
            this._out.write(this._byteBuffer, 0, i);
            this._pos = 0;
        }
        this._out.flush();
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence charSequence) throws IOException {
        append(charSequence, 0, charSequence.length());
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
        while (i < i2) {
            append(charSequence.charAt(i));
            i++;
        }
        return this;
    }

    @Override // com.amazon.ion.util._Private_FastAppendable
    public final void appendAscii(CharSequence charSequence) throws IOException {
        appendAscii(charSequence, 0, charSequence.length());
    }

    @Override // com.amazon.ion.util._Private_FastAppendable
    public final void appendAscii(CharSequence charSequence, int i, int i2) throws IOException {
        if (!(charSequence instanceof String)) {
            while (i < i2) {
                int i3 = this._pos;
                byte[] bArr = this._byteBuffer;
                if (i3 == bArr.length) {
                    this._out.write(bArr, 0, i3);
                    this._pos = 0;
                }
                char cCharAt = charSequence.charAt(i);
                byte[] bArr2 = this._byteBuffer;
                int i4 = this._pos;
                this._pos = i4 + 1;
                bArr2[i4] = (byte) cCharAt;
                i++;
            }
            return;
        }
        String str = (String) charSequence;
        int i5 = i2 - i;
        int i6 = this._pos;
        int i7 = i6 + i5;
        byte[] bArr3 = this._byteBuffer;
        if (i7 < bArr3.length) {
            str.getBytes(i, i2, bArr3, i6);
            this._pos += i5;
            return;
        }
        do {
            this._out.write(this._byteBuffer, 0, this._pos);
            int length = i2 - i;
            byte[] bArr4 = this._byteBuffer;
            if (length > bArr4.length) {
                length = bArr4.length;
            }
            this._pos = length;
            str.getBytes(i, length + i, bArr4, 0);
            i += this._pos;
        } while (i < i2);
    }
}
