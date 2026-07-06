package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Utf8Old extends Utf8 {
    public static final ThreadLocal<Cache> CACHE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Cache {
        public final CharsetDecoder decoder;
        public final CharsetEncoder encoder;
        public CharSequence lastInput = null;
        public ByteBuffer lastOutput = null;

        public Cache() {
            Charset charset = StandardCharsets.UTF_8;
            this.encoder = charset.newEncoder();
            this.decoder = charset.newDecoder();
        }
    }

    public static /* synthetic */ Cache $r8$lambda$H6DtQOdY71VKmQKkbdDLin5kXeM() {
        return new Cache();
    }

    static {
        final Utf8Old$$ExternalSyntheticLambda0 utf8Old$$ExternalSyntheticLambda0 = new Utf8Old$$ExternalSyntheticLambda0();
        CACHE = new ThreadLocal() { // from class: androidx.emoji2.text.flatbuffer.Utf8Old$$ExternalSyntheticThreadLocal1
            @Override // java.lang.ThreadLocal
            public /* synthetic */ Object initialValue() {
                return utf8Old$$ExternalSyntheticLambda0.get();
            }
        };
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public String decodeUtf8(ByteBuffer byteBuffer, int i, int i2) {
        CharsetDecoder charsetDecoder = CACHE.get().decoder;
        charsetDecoder.reset();
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i);
        byteBufferDuplicate.limit(i + i2);
        try {
            return charsetDecoder.decode(byteBufferDuplicate).toString();
        } catch (CharacterCodingException e) {
            throw new IllegalArgumentException("Bad encoding", e);
        }
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        Cache cache = CACHE.get();
        if (cache.lastInput != charSequence) {
            encodedLength(charSequence);
        }
        byteBuffer.put(cache.lastOutput);
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public int encodedLength(CharSequence charSequence) {
        Cache cache = CACHE.get();
        int iMaxBytesPerChar = (int) (cache.encoder.maxBytesPerChar() * charSequence.length());
        ByteBuffer byteBuffer = cache.lastOutput;
        if (byteBuffer == null || byteBuffer.capacity() < iMaxBytesPerChar) {
            cache.lastOutput = ByteBuffer.allocate(Math.max(128, iMaxBytesPerChar));
        }
        cache.lastOutput.clear();
        cache.lastInput = charSequence;
        CoderResult coderResultEncode = cache.encoder.encode(charSequence instanceof CharBuffer ? (CharBuffer) charSequence : CharBuffer.wrap(charSequence), cache.lastOutput, true);
        if (coderResultEncode.isError()) {
            try {
                coderResultEncode.throwException();
            } catch (CharacterCodingException e) {
                throw new IllegalArgumentException("bad character encoding", e);
            }
        }
        cache.lastOutput.flip();
        return cache.lastOutput.remaining();
    }
}
