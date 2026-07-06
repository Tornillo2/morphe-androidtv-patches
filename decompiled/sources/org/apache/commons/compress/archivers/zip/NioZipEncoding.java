package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class NioZipEncoding implements ZipEncoding {
    public final Charset charset;

    public NioZipEncoding(Charset charset) {
        this.charset = charset;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public boolean canEncode(String str) {
        CharsetEncoder charsetEncoderNewEncoder = this.charset.newEncoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPORT;
        charsetEncoderNewEncoder.onMalformedInput(codingErrorAction);
        charsetEncoderNewEncoder.onUnmappableCharacter(codingErrorAction);
        return charsetEncoderNewEncoder.canEncode(str);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public String decode(byte[] bArr) throws IOException {
        CharsetDecoder charsetDecoderNewDecoder = this.charset.newDecoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPORT;
        return charsetDecoderNewDecoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction).decode(ByteBuffer.wrap(bArr)).toString();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public ByteBuffer encode(String str) {
        CharsetEncoder charsetEncoderNewEncoder = this.charset.newEncoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPORT;
        charsetEncoderNewEncoder.onMalformedInput(codingErrorAction);
        charsetEncoderNewEncoder.onUnmappableCharacter(codingErrorAction);
        CharBuffer charBufferWrap = CharBuffer.wrap(str);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(((str.length() + 1) / 2) + str.length());
        while (true) {
            if (charBufferWrap.remaining() <= 0) {
                break;
            }
            CoderResult coderResultEncode = charsetEncoderNewEncoder.encode(charBufferWrap, byteBufferAllocate, true);
            if (!coderResultEncode.isUnmappable() && !coderResultEncode.isMalformed()) {
                if (!coderResultEncode.isOverflow()) {
                    if (coderResultEncode.isUnderflow()) {
                        charsetEncoderNewEncoder.flush(byteBufferAllocate);
                        break;
                    }
                } else {
                    byteBufferAllocate = ZipEncodingHelper.growBuffer(byteBufferAllocate, 0);
                }
            } else {
                if (coderResultEncode.length() * 6 > byteBufferAllocate.remaining()) {
                    byteBufferAllocate = ZipEncodingHelper.growBuffer(byteBufferAllocate, (coderResultEncode.length() * 6) + byteBufferAllocate.position());
                }
                for (int i = 0; i < coderResultEncode.length(); i++) {
                    ZipEncodingHelper.appendSurrogate(byteBufferAllocate, charBufferWrap.get());
                }
            }
        }
        byteBufferAllocate.limit(byteBufferAllocate.position());
        byteBufferAllocate.rewind();
        return byteBufferAllocate;
    }
}
