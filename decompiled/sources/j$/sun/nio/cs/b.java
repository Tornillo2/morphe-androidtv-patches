package j$.sun.nio.cs;

import j$.util.Objects;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends CharsetEncoder {
    public final e a;

    @Override // java.nio.charset.CharsetEncoder
    public final boolean canEncode(char c) {
        return c <= 255;
    }

    @Override // java.nio.charset.CharsetEncoder
    public final boolean isLegalReplacement(byte[] bArr) {
        return true;
    }

    public b(c cVar) {
        super(cVar, 1.0f, 1.0f);
        e eVar = new e();
        eVar.a = CoderResult.UNDERFLOW;
        this.a = eVar;
    }

    public static int a(char[] cArr, int i, byte[] bArr, int i2, int i3) {
        int i4 = 0;
        if (i3 <= 0) {
            return 0;
        }
        Objects.requireNonNull(cArr);
        Objects.requireNonNull(bArr);
        if (i < 0 || i >= cArr.length) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        if (i2 < 0 || i2 >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException(i2);
        }
        int i5 = (i + i3) - 1;
        if (i5 < 0 || i5 >= cArr.length) {
            throw new ArrayIndexOutOfBoundsException(i5);
        }
        int i6 = (i2 + i3) - 1;
        if (i6 < 0 || i6 >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException(i6);
        }
        while (i4 < i3) {
            int i7 = i + 1;
            char c = cArr[i];
            if (c > 255) {
                break;
            }
            bArr[i2] = (byte) c;
            i4++;
            i = i7;
            i2++;
        }
        return i4;
    }

    @Override // java.nio.charset.CharsetEncoder
    public final CoderResult encodeLoop(CharBuffer charBuffer, ByteBuffer byteBuffer) {
        CoderResult coderResultUnmappableForLength;
        CoderResult coderResultUnmappableForLength2;
        int i = 2;
        if (!charBuffer.hasArray() || !byteBuffer.hasArray()) {
            int iPosition = charBuffer.position();
            while (true) {
                try {
                    if (!charBuffer.hasRemaining()) {
                        coderResultUnmappableForLength = CoderResult.UNDERFLOW;
                        break;
                    }
                    char c = charBuffer.get();
                    if (c <= 255) {
                        if (!byteBuffer.hasRemaining()) {
                            coderResultUnmappableForLength = CoderResult.OVERFLOW;
                            break;
                        }
                        byteBuffer.put((byte) c);
                        iPosition++;
                    } else if (this.a.a(c, charBuffer) < 0) {
                        coderResultUnmappableForLength = this.a.a;
                    } else {
                        if (!this.a.b) {
                            i = 1;
                        }
                        coderResultUnmappableForLength = CoderResult.unmappableForLength(i);
                    }
                } finally {
                }
            }
            return coderResultUnmappableForLength;
        }
        char[] cArrArray = charBuffer.array();
        int iArrayOffset = charBuffer.arrayOffset();
        int iPosition2 = charBuffer.position() + iArrayOffset;
        int iLimit = charBuffer.limit() + iArrayOffset;
        if (iPosition2 > iLimit) {
            iPosition2 = iLimit;
        }
        byte[] bArrArray = byteBuffer.array();
        int iArrayOffset2 = byteBuffer.arrayOffset();
        int iPosition3 = byteBuffer.position() + iArrayOffset2;
        int iLimit2 = byteBuffer.limit() + iArrayOffset2;
        if (iPosition3 > iLimit2) {
            iPosition3 = iLimit2;
        }
        int i2 = iLimit2 - iPosition3;
        int i3 = iLimit - iPosition2;
        if (i2 >= i3) {
            i2 = i3;
        }
        try {
            int iA = a(cArrArray, iPosition2, bArrArray, iPosition3, i2);
            int i4 = iPosition2 + iA;
            int i5 = iPosition3 + iA;
            if (iA == i2) {
                coderResultUnmappableForLength2 = i2 < i3 ? CoderResult.OVERFLOW : CoderResult.UNDERFLOW;
            } else if (this.a.b(cArrArray[i4], cArrArray, i4, iLimit) < 0) {
                coderResultUnmappableForLength2 = this.a.a;
            } else {
                if (!this.a.b) {
                    i = 1;
                }
                coderResultUnmappableForLength2 = CoderResult.unmappableForLength(i);
            }
            return coderResultUnmappableForLength2;
        } catch (Throwable th) {
            throw th;
        }
    }
}
