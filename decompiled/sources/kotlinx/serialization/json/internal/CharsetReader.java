package kotlinx.serialization.json.internal;

import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class CharsetReader {

    @NotNull
    public final ByteBuffer byteBuffer;

    @NotNull
    public final Charset charset;

    @NotNull
    public final CharsetDecoder decoder;
    public boolean hasLeftoverPotentiallySurrogateChar;

    @NotNull
    public final InputStream inputStream;
    public char leftoverChar;

    public CharsetReader(@NotNull InputStream inputStream, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(charset, "charset");
        this.inputStream = inputStream;
        this.charset = charset;
        CharsetDecoder charsetDecoderNewDecoder = charset.newDecoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPLACE;
        this.decoder = charsetDecoderNewDecoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(ByteArrayPool8k.INSTANCE.take(8196));
        this.byteBuffer = byteBufferWrap;
        byteBufferWrap.flip();
    }

    public final int doRead(char[] cArr, int i, int i2) throws CharacterCodingException {
        CharBuffer charBufferWrap = CharBuffer.wrap(cArr, i, i2);
        if (charBufferWrap.position() != 0) {
            charBufferWrap = charBufferWrap.slice();
        }
        boolean z = false;
        while (true) {
            CoderResult coderResultDecode = this.decoder.decode(this.byteBuffer, charBufferWrap, z);
            if (coderResultDecode.isUnderflow()) {
                if (!z && charBufferWrap.hasRemaining()) {
                    if (fillByteBuffer() < 0) {
                        if (charBufferWrap.position() == 0 && !this.byteBuffer.hasRemaining()) {
                            z = true;
                            break;
                        }
                        this.decoder.reset();
                        z = true;
                    } else {
                        continue;
                    }
                } else {
                    break;
                }
            } else {
                if (coderResultDecode.isOverflow()) {
                    charBufferWrap.position();
                    break;
                }
                coderResultDecode.throwException();
            }
        }
        if (z) {
            this.decoder.reset();
        }
        if (charBufferWrap.position() == 0) {
            return -1;
        }
        return charBufferWrap.position();
    }

    public final int fillByteBuffer() {
        this.byteBuffer.compact();
        try {
            int iLimit = this.byteBuffer.limit();
            int iPosition = this.byteBuffer.position();
            int i = this.inputStream.read(this.byteBuffer.array(), this.byteBuffer.arrayOffset() + iPosition, iPosition <= iLimit ? iLimit - iPosition : 0);
            if (i < 0) {
                return i;
            }
            ByteBuffer byteBuffer = this.byteBuffer;
            Intrinsics.checkNotNull(byteBuffer, "null cannot be cast to non-null type java.nio.Buffer");
            byteBuffer.position(iPosition + i);
            this.byteBuffer.flip();
            return this.byteBuffer.remaining();
        } finally {
            this.byteBuffer.flip();
        }
    }

    public final int oneShotReadSlowPath() {
        if (this.hasLeftoverPotentiallySurrogateChar) {
            this.hasLeftoverPotentiallySurrogateChar = false;
            return this.leftoverChar;
        }
        char[] cArr = new char[2];
        int i = read(cArr, 0, 2);
        if (i == -1) {
            return -1;
        }
        if (i == 1) {
            return cArr[0];
        }
        if (i == 2) {
            this.leftoverChar = cArr[1];
            this.hasLeftoverPotentiallySurrogateChar = true;
            return cArr[0];
        }
        throw new IllegalStateException(("Unreachable state: " + i).toString());
    }

    public final int read(@NotNull char[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        if (i < 0 || i >= array.length || i2 < 0 || i + i2 > array.length) {
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("Unexpected arguments: ", i, ", ", i2, ", ");
            sbM.append(array.length);
            throw new IllegalArgumentException(sbM.toString().toString());
        }
        if (this.hasLeftoverPotentiallySurrogateChar) {
            array[i] = this.leftoverChar;
            i++;
            i2--;
            this.hasLeftoverPotentiallySurrogateChar = false;
            if (i2 == 0) {
                return 1;
            }
            i3 = 1;
        }
        if (i2 != 1) {
            return doRead(array, i, i2) + i3;
        }
        int iOneShotReadSlowPath = oneShotReadSlowPath();
        if (iOneShotReadSlowPath != -1) {
            array[i] = (char) iOneShotReadSlowPath;
            return i3 + 1;
        }
        if (i3 == 0) {
            return -1;
        }
        return i3;
    }

    public final void release() {
        ByteArrayPool8k byteArrayPool8k = ByteArrayPool8k.INSTANCE;
        byte[] bArrArray = this.byteBuffer.array();
        Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
        byteArrayPool8k.getClass();
        byteArrayPool8k.releaseImpl(bArrArray);
    }
}
