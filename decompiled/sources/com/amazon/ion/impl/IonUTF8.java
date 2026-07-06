package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.ion.IonException;
import com.google.common.base.Ascii;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonUTF8 {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int HIGH_SURROGATE = 55296;
    public static final int LOW_SURROGATE = 56320;
    public static final int MAXIMUM_UTF16_1_CHAR_CODE_POINT = 65535;
    public static final int SURROGATE_MASK = -1024;
    public static final int SURROGATE_OFFSET = 65536;
    public static final int UNICODE_CONTINUATION_BYTE_HEADER = 128;
    public static final int UNICODE_CONTINUATION_BYTE_MASK = 63;
    public static final int UNICODE_FOUR_BYTE_HEADER = 240;
    public static final int UNICODE_FOUR_BYTE_MASK = 7;
    public static final int UNICODE_MAX_FOUR_BYTE_SCALAR = 1114111;
    public static final int UNICODE_MAX_ONE_BYTE_SCALAR = 127;
    public static final int UNICODE_MAX_THREE_BYTE_SCALAR = 65535;
    public static final int UNICODE_MAX_TWO_BYTE_SCALAR = 2047;
    public static final int UNICODE_THREE_BYTES_OR_FEWER_MASK = -65536;
    public static final int UNICODE_THREE_BYTE_HEADER = 224;
    public static final int UNICODE_THREE_BYTE_MASK = 15;
    public static final int UNICODE_TWO_BYTE_HEADER = 192;
    public static final int UNICODE_TWO_BYTE_MASK = 31;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class InvalidUnicodeCodePoint extends IonException {
        public static final long serialVersionUID = -3200811216940328945L;

        public InvalidUnicodeCodePoint(String str) {
            super(str);
        }

        public InvalidUnicodeCodePoint(String str, Exception exc) {
            super(str, exc);
        }

        public InvalidUnicodeCodePoint() {
            super("invalid UTF8");
        }

        public InvalidUnicodeCodePoint(Exception exc) {
            super(exc);
        }
    }

    public static final int convertToUTF8Bytes(int i, byte[] bArr, int i2, int i3) {
        int i4;
        int i5;
        int i6 = i3 + i2;
        int uTF8ByteCount = getUTF8ByteCount(i);
        if (uTF8ByteCount != 1) {
            if (uTF8ByteCount == 2) {
                int i7 = i2 + 1;
                if (i7 >= i6) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                bArr[i2] = getByte1Of2(i);
                i5 = i2 + 2;
                bArr[i7] = getByte2Of2(i);
            } else if (uTF8ByteCount != 3) {
                if (uTF8ByteCount != 4) {
                    i4 = i2;
                } else {
                    if (i2 + 3 >= i6) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    bArr[i2] = getByte1Of4(i);
                    bArr[i2 + 1] = getByte2Of4(i);
                    bArr[i2 + 2] = getByte3Of4(i);
                    i4 = i2 + 4;
                    bArr[i2 + 3] = getByte4Of4(i);
                }
            } else {
                if (i2 + 2 >= i6) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                bArr[i2] = getByte1Of3(i);
                bArr[i2 + 1] = getByte2Of3(i);
                i5 = i2 + 3;
                bArr[i2 + 2] = getByte3Of3(i);
            }
            i4 = i5;
        } else {
            if (i2 >= i6) {
                throw new ArrayIndexOutOfBoundsException();
            }
            i4 = i2 + 1;
            bArr[i2] = (byte) (i & 255);
        }
        return i4 - i2;
    }

    public static final int fourByteScalar(int i, int i2, int i3, int i4) {
        return ((i & 7) << 18) | ((i2 & 63) << 12) | ((i3 & 63) << 6) | (i4 & 63);
    }

    public static final int getAs4BytesReversed(int i) {
        int byte1Of2;
        int byte2Of2;
        int uTF8ByteCount = getUTF8ByteCount(i);
        if (uTF8ByteCount == 1) {
            return i;
        }
        if (uTF8ByteCount == 2) {
            byte1Of2 = getByte1Of2(i);
            byte2Of2 = getByte2Of2(i) << 8;
        } else if (uTF8ByteCount == 3) {
            byte1Of2 = getByte1Of3(i) | (getByte2Of3(i) << 8);
            byte2Of2 = getByte3Of3(i) << 16;
        } else {
            if (uTF8ByteCount != 4) {
                throw new InvalidUnicodeCodePoint();
            }
            byte1Of2 = getByte1Of4(i) | (getByte2Of4(i) << 8) | (getByte3Of4(i) << 16);
            byte2Of2 = getByte4Of4(i) << Ascii.CAN;
        }
        return byte2Of2 | byte1Of2;
    }

    public static final byte getByte1Of2(int i) {
        return (byte) (((i >> 6) & 31) | 192);
    }

    public static final byte getByte1Of3(int i) {
        return (byte) (((i >> 12) & 15) | 224);
    }

    public static final byte getByte1Of4(int i) {
        return (byte) (((i >> 18) & 7) | 240);
    }

    public static final byte getByte2Of2(int i) {
        return (byte) ((i & 63) | 128);
    }

    public static final byte getByte2Of3(int i) {
        return (byte) (((i >> 6) & 63) | 128);
    }

    public static final byte getByte2Of4(int i) {
        return (byte) (((i >> 12) & 63) | 128);
    }

    public static final byte getByte3Of3(int i) {
        return (byte) ((i & 63) | 128);
    }

    public static final byte getByte3Of4(int i) {
        return (byte) (((i >> 6) & 63) | 128);
    }

    public static final byte getByte4Of4(int i) {
        return (byte) ((i & 63) | 128);
    }

    public static final int getScalarFrom4BytesReversed(int i) {
        int i2;
        int i3;
        int i4 = i & 255;
        int uTF8LengthFromFirstByte = getUTF8LengthFromFirstByte(i4);
        if (uTF8LengthFromFirstByte == 1) {
            return i4;
        }
        if (uTF8LengthFromFirstByte == 2) {
            i2 = (i & 31) << 6;
            i3 = i >> 8;
        } else if (uTF8LengthFromFirstByte == 3) {
            i2 = ((i & 15) << 12) | ((i >> 2) & 4032);
            i3 = i >> 16;
        } else {
            if (uTF8LengthFromFirstByte != 4) {
                throw new InvalidUnicodeCodePoint();
            }
            i2 = ((i & 7) << 18) | ((i << 4) & 258048) | ((i >> 2) & 4032);
            i3 = i >> 24;
        }
        return (i3 & 63) | i2;
    }

    public static final int getScalarFromBytes(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        if (i >= i3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i4 = i + 1;
        byte b = bArr[i];
        int i5 = b & 255;
        int uTF8LengthFromFirstByte = getUTF8LengthFromFirstByte(i5);
        if (i4 + uTF8LengthFromFirstByte > i3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (uTF8LengthFromFirstByte == 1) {
            return i5;
        }
        if (uTF8LengthFromFirstByte == 2) {
            return (bArr[i4] & 63) | (b & 31);
        }
        if (uTF8LengthFromFirstByte == 3) {
            return (bArr[i + 2] & 63) | (bArr[i4] & 63) | (b & Ascii.SI);
        }
        if (uTF8LengthFromFirstByte != 4) {
            throw new InvalidUnicodeCodePoint(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("code point is invalid: ", uTF8LengthFromFirstByte));
        }
        return (bArr[i + 3] & 63) | (b & 7) | (bArr[i4] & 63) | (bArr[i + 2] & 63);
    }

    public static final int getScalarReadLengthFromBytes(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        if (i >= i3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int uTF8LengthFromFirstByte = getUTF8LengthFromFirstByte(bArr[i] & 255);
        if (i + 1 + uTF8LengthFromFirstByte <= i3) {
            return uTF8LengthFromFirstByte;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public static final int getUTF8ByteCount(int i) {
        if (((-65536) & i) == 0) {
            if (i <= 127) {
                return 1;
            }
            return i <= 2047 ? 2 : 3;
        }
        if (i < 0 || i > 1114111) {
            throw new InvalidUnicodeCodePoint();
        }
        return 4;
    }

    public static final int getUTF8LengthFromFirstByte(int i) {
        int i2 = i & 255;
        if (isOneByteUTF8(i2)) {
            return 1;
        }
        if (isTwoByteUTF8(i2)) {
            return 2;
        }
        if (isThreeByteUTF8(i2)) {
            return 3;
        }
        return isFourByteUTF8(i2) ? 4 : -1;
    }

    public static final int getUnicodeScalarFromSurrogates(int i, int i2) {
        return (i2 & 1023) + ((i & 1023) << 10) + 65536;
    }

    public static final char highSurrogate(int i) {
        return (char) ((((i - 65536) >> 10) | 55296) & 65535);
    }

    public static final boolean isContinueByteUTF8(int i) {
        return (i & (-64)) == 128;
    }

    public static final boolean isFourByteScalar(int i) {
        return i <= 1114111;
    }

    public static final boolean isFourByteUTF8(int i) {
        return (i & (-8)) == 240;
    }

    public static final boolean isHighSurrogate(int i) {
        return (i & (-1024)) == 55296;
    }

    public static final boolean isLowSurrogate(int i) {
        return (i & (-1024)) == 56320;
    }

    public static final boolean isOneByteScalar(int i) {
        return i <= 127;
    }

    public static final boolean isOneByteUTF8(int i) {
        return (i & 128) == 0;
    }

    public static final boolean isStartByte(int i) {
        return isOneByteUTF8(i) || !isContinueByteUTF8(i);
    }

    public static final boolean isSurrogate(int i) {
        return i >= 55296 && i <= 57343;
    }

    public static final boolean isThreeByteScalar(int i) {
        return i <= 65535;
    }

    public static final boolean isThreeByteUTF8(int i) {
        return (i & (-16)) == 224;
    }

    public static final boolean isTwoByteScalar(int i) {
        return i <= 2047;
    }

    public static final boolean isTwoByteUTF8(int i) {
        return (i & (-32)) == 192;
    }

    public static final char lowSurrogate(int i) {
        return (char) ((((i - 65536) & 1023) | 56320) & 65535);
    }

    public static final boolean needsSurrogateEncoding(int i) {
        if (i <= 1114111) {
            return i > 65535;
        }
        throw new IonException("Invalid encoding: encountered non-Unicode character.");
    }

    public static final int packBytesAfter1(int i, int i2) {
        int byte2Of3;
        int byte3Of3;
        if (i2 == 2) {
            return getByte2Of2(i);
        }
        if (i2 == 3) {
            byte2Of3 = getByte2Of3(i);
            byte3Of3 = getByte3Of3(i) << 8;
        } else {
            if (i2 != 4) {
                throw new IllegalArgumentException("pack requires len > 1");
            }
            byte2Of3 = getByte2Of4(i) | (getByte3Of4(i) << 8);
            byte3Of3 = getByte4Of4(i) << 16;
        }
        return byte3Of3 | byte2Of3;
    }

    public static final int threeByteScalar(int i, int i2, int i3) {
        return ((i & 15) << 12) | ((i2 & 63) << 6) | (i3 & 63);
    }

    public static final char twoByteScalar(int i, int i2) {
        return (char) (((i & 31) << 6) | (i2 & 63));
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UTF8ToChar extends OutputStream implements Closeable {
        public final Appendable _char_stream;
        public int _pending_c1;
        public int _pending_c2;
        public int _pending_c3;
        public int _expected_count = 0;
        public int _pending_count = 0;

        public UTF8ToChar(Appendable appendable) {
            this._char_stream = appendable;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            if (this._pending_count > 0) {
                throw new IOException("unfinished utf8 sequence still open");
            }
        }

        public final Appendable getAppendable() {
            return this._char_stream;
        }

        public final void throwBadContinuationByte() throws IOException {
            throw new IOException("continuation byte expected");
        }

        @Override // java.io.OutputStream
        public final void write(int i) throws IOException {
            int i2 = i & 255;
            if (this._expected_count > 0) {
                write_helper_continue(i2);
            } else if (i2 <= 127) {
                this._char_stream.append((char) i2);
            } else {
                if (!IonUTF8.isStartByte(i2)) {
                    throw new IOException("invalid UTF8 sequence: byte > 127 is not a UTF8 leading byte");
                }
                write_helper_start_sequence(i2);
            }
        }

        public final void write_helper_continue(int i) throws IOException {
            int i2 = this._pending_count + 1;
            this._pending_count = i2;
            if (this._expected_count == i2) {
                write_helper_write_char(i);
            } else if (i2 == 2) {
                this._pending_c2 = i;
            } else {
                if (i2 != 3) {
                    throw new IOException("invalid state for pending vs expected UTF8 bytes");
                }
                this._pending_c3 = i;
            }
        }

        public final void write_helper_start_sequence(int i) throws IOException {
            this._expected_count = IonUTF8.getUTF8LengthFromFirstByte(i);
            this._pending_count = 1;
            this._pending_c1 = i;
        }

        public final void write_helper_write_char(int i) throws IOException {
            int i2 = this._pending_count;
            if (i2 == 2) {
                if (!IonUTF8.isContinueByteUTF8(i)) {
                    throwBadContinuationByte();
                    throw null;
                }
                this._char_stream.append(IonUTF8.twoByteScalar(this._pending_c1, i));
                return;
            }
            if (i2 == 3) {
                if (!IonUTF8.isContinueByteUTF8(i)) {
                    throwBadContinuationByte();
                    throw null;
                }
                if (!IonUTF8.isContinueByteUTF8(this._pending_c2)) {
                    throwBadContinuationByte();
                    throw null;
                }
                int iThreeByteScalar = IonUTF8.threeByteScalar(this._pending_c1, this._pending_c2, i);
                if (!IonUTF8.needsSurrogateEncoding(iThreeByteScalar)) {
                    this._char_stream.append((char) iThreeByteScalar);
                    return;
                } else {
                    this._char_stream.append(IonUTF8.lowSurrogate(iThreeByteScalar));
                    this._char_stream.append(IonUTF8.highSurrogate(iThreeByteScalar));
                    return;
                }
            }
            if (i2 != 4) {
                throw new IOException("invalid state for UTF8 sequence length " + this._pending_count);
            }
            if (!IonUTF8.isContinueByteUTF8(i)) {
                throwBadContinuationByte();
                throw null;
            }
            if (!IonUTF8.isContinueByteUTF8(this._pending_c2)) {
                throwBadContinuationByte();
                throw null;
            }
            if (!IonUTF8.isContinueByteUTF8(this._pending_c3)) {
                throwBadContinuationByte();
                throw null;
            }
            int iFourByteScalar = IonUTF8.fourByteScalar(this._pending_c1, this._pending_c2, this._pending_c3, i);
            if (!IonUTF8.needsSurrogateEncoding(iFourByteScalar)) {
                this._char_stream.append((char) iFourByteScalar);
            } else {
                this._char_stream.append(IonUTF8.lowSurrogate(iFourByteScalar));
                this._char_stream.append(IonUTF8.highSurrogate(iFourByteScalar));
            }
        }

        @Override // java.io.OutputStream
        public final void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public final void write(byte[] bArr, int i, int i2) throws IOException {
            for (int i3 = i; i3 < i + i2; i3++) {
                int i4 = bArr[i3] & 255;
                if (this._pending_count == 0 && i4 < 128) {
                    this._char_stream.append((char) i4);
                } else {
                    write(i4);
                }
            }
        }
    }
}
