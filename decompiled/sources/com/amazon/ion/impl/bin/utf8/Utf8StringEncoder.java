package com.amazon.ion.impl.bin.utf8;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Utf8StringEncoder extends Poolable<Utf8StringEncoder> {
    public static final int SMALL_STRING_SIZE = 4096;
    public final char[] charArray;
    public final CharBuffer charBuffer;
    public final CharsetEncoder utf8Encoder;
    public final ByteBuffer utf8EncodingBuffer;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Result {
        public final byte[] buffer;
        public final int encodedLength;

        public Result(int i, byte[] bArr) {
            this.encodedLength = i;
            this.buffer = bArr;
        }

        public byte[] getBuffer() {
            return this.buffer;
        }

        public int getEncodedLength() {
            return this.encodedLength;
        }
    }

    public Utf8StringEncoder(Pool<Utf8StringEncoder> pool) {
        super(pool);
        CharsetEncoder charsetEncoderNewEncoder = Charset.forName("UTF-8").newEncoder();
        this.utf8Encoder = charsetEncoderNewEncoder;
        this.utf8EncodingBuffer = ByteBuffer.allocate((int) (charsetEncoderNewEncoder.maxBytesPerChar() * 4096.0f));
        char[] cArr = new char[4096];
        this.charArray = cArr;
        this.charBuffer = CharBuffer.wrap(cArr);
    }

    @Override // com.amazon.ion.impl.bin.utf8.Poolable, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public Result encode(String str) {
        ByteBuffer byteBufferAllocate;
        CharBuffer charBufferWrap;
        if (str.length() > 4096) {
            byteBufferAllocate = ByteBuffer.allocate((int) (this.utf8Encoder.maxBytesPerChar() * str.length()));
            char[] cArr = new char[str.length()];
            str.getChars(0, str.length(), cArr, 0);
            charBufferWrap = CharBuffer.wrap(cArr);
        } else {
            byteBufferAllocate = this.utf8EncodingBuffer;
            byteBufferAllocate.clear();
            charBufferWrap = this.charBuffer;
            str.getChars(0, str.length(), this.charArray, 0);
            this.charBuffer.rewind();
            this.charBuffer.limit(str.length());
        }
        if (!this.utf8Encoder.encode(charBufferWrap, byteBufferAllocate, true).isUnderflow()) {
            throw new IllegalArgumentException("Could not encode string as UTF8 bytes: ".concat(str));
        }
        byteBufferAllocate.flip();
        return new Result(byteBufferAllocate.remaining(), byteBufferAllocate.array());
    }
}
