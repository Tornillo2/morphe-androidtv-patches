package com.amazon.ion.impl;

import com.amazon.ion.IonException;
import com.amazon.ion.util.IonTextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Base64Encoder {
    public static final int BUFSIZE = 1024;
    public static final int BUFSIZE_BIN = 384;
    public static final int BUFSIZE_TEXT = 512;
    public static final EL[] Base64Alphabet;
    public static final int[] Base64EncodingCharToInt;
    public static final int[] Base64EncodingIntToChar;
    public static final char Base64EncodingTerminator;
    public static final int[] URLSafe64CharToInt;
    public static final int[] URLSafe64IntToChar;
    public static final char URLSafe64IntToCharTerminator;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class EL {
        public char letter;
        public int value;

        public EL(int i, char c) {
            this.value = i;
            this.letter = c;
        }
    }

    static {
        EL[] elArr = {new EL(-1, '='), new EL(0, 'A'), new EL(17, 'R'), new EL(34, 'i'), new EL(51, 'z'), new EL(1, 'B'), new EL(18, 'S'), new EL(35, 'j'), new EL(52, '0'), new EL(2, 'C'), new EL(19, 'T'), new EL(36, 'k'), new EL(53, '1'), new EL(3, 'D'), new EL(20, 'U'), new EL(37, 'l'), new EL(54, '2'), new EL(4, 'E'), new EL(21, 'V'), new EL(38, 'm'), new EL(55, '3'), new EL(5, 'F'), new EL(22, 'W'), new EL(39, 'n'), new EL(56, '4'), new EL(6, 'G'), new EL(23, 'X'), new EL(40, 'o'), new EL(57, '5'), new EL(7, 'H'), new EL(24, 'Y'), new EL(41, 'p'), new EL(58, '6'), new EL(8, 'I'), new EL(25, 'Z'), new EL(42, 'q'), new EL(59, '7'), new EL(9, 'J'), new EL(26, 'a'), new EL(43, 'r'), new EL(60, '8'), new EL(10, 'K'), new EL(27, 'b'), new EL(44, 's'), new EL(61, '9'), new EL(11, 'L'), new EL(28, 'c'), new EL(45, 't'), new EL(62, '+'), new EL(12, 'M'), new EL(29, 'd'), new EL(46, AbstractJsonLexerKt.UNICODE_ESC), new EL(63, '/'), new EL(13, 'N'), new EL(30, 'e'), new EL(47, 'v'), new EL(14, 'O'), new EL(31, 'f'), new EL(48, 'w'), new EL(15, 'P'), new EL(32, 'g'), new EL(49, 'x'), new EL(16, 'Q'), new EL(33, 'h'), new EL(50, 'y')};
        Base64Alphabet = elArr;
        URLSafe64IntToCharTerminator = init64IntToCharTerminator(elArr);
        URLSafe64IntToChar = init64IntToChar(elArr);
        URLSafe64CharToInt = init64CharToInt(elArr);
        Base64EncodingIntToChar = init64IntToChar(elArr);
        Base64EncodingCharToInt = init64CharToInt(elArr);
        Base64EncodingTerminator = init64IntToCharTerminator(elArr);
    }

    public static int[] init64CharToInt(EL[] elArr) {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            iArr[i] = -1;
        }
        for (EL el : elArr) {
            char c = el.letter;
            if (c > 255) {
                throw new RuntimeException("fatal base 64 encoding static initializer: letter out of bounds");
            }
            int i2 = el.value;
            if (i2 >= 0) {
                iArr[c] = i2;
            }
        }
        return iArr;
    }

    public static int[] init64IntToChar(EL[] elArr) {
        int[] iArr = new int[64];
        for (EL el : elArr) {
            int i = el.value;
            if (i != -1) {
                iArr[i] = el.letter;
            }
        }
        return iArr;
    }

    public static char init64IntToCharTerminator(EL[] elArr) {
        for (EL el : elArr) {
            if (el.value == -1) {
                return el.letter;
            }
        }
        throw new RuntimeException(new IonException("fatal: invalid char map definition - missing terminator"));
    }

    public static final boolean isBase64Character(int i) {
        return i >= 32 && i <= 255 && URLSafe64CharToInt[i] >= 0;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BinaryStream extends Reader {
        public int _bufEnd;
        public int _bufPos;
        public char[] _buffer;
        public int[] _chartobin;
        public int _otherTerminator;
        public boolean _ready;
        public Reader _source;
        public int _state;
        public int _terminatingChar;
        public int _terminator;

        public BinaryStream(Reader reader, int[] iArr, char c, char c2) {
            this._source = reader;
            this._chartobin = iArr;
            this._terminator = c;
            this._otherTerminator = c2;
            this._terminatingChar = -1;
            this._buffer = new char[5];
            this._ready = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:6:0x000a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int characterToBinary(int r4) throws java.io.IOException {
            /*
                r3 = this;
                if (r4 < 0) goto La
                int[] r0 = r3._chartobin
                int r1 = r0.length
                if (r4 >= r1) goto La
                r0 = r0[r4]
                goto Lb
            La:
                r0 = -1
            Lb:
                if (r0 < 0) goto Le
                return r0
            Le:
                com.amazon.ion.IonException r0 = new com.amazon.ion.IonException
                java.lang.String r1 = "invalid base64 character ("
                java.lang.String r2 = ")"
                java.lang.String r4 = androidx.collection.ObjectListKt$$ExternalSyntheticOutline1.m(r1, r4, r2)
                r0.<init>(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.Base64Encoder.BinaryStream.characterToBinary(int):int");
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this._source.close();
        }

        public final void loadNextBuffer() throws IOException {
            this._bufEnd = 0;
            this._bufPos = 0;
            if (this._state == 1) {
                return;
            }
            int i = 0;
            int i2 = -1;
            while (i < 4) {
                i2 = this._source.read();
                if (i2 == -1 || i2 == 65535 || i2 == this._terminator || i2 == this._otherTerminator) {
                    this._terminatingChar = i2;
                    break;
                } else if (!IonTextUtils.isWhitespace(i2)) {
                    this._buffer[i] = (char) characterToBinary(i2);
                    i++;
                }
            }
            if (i != 4) {
                if (i == 0 && i2 != this._terminator) {
                    this._state = 1;
                    return;
                }
                int i3 = i;
                while (i2 == this._terminator) {
                    i3++;
                    i2 = this._source.read();
                }
                if (i3 != 4) {
                    throw new IonException("base64 character count must be divisible by 4, using '=' for padding");
                }
                if (i < 1) {
                    throw new IonException("base64 character count must be divisible by 4, but using no more than 3 '=' chars for padding");
                }
                this._terminatingChar = i2;
            }
            char[] cArr = this._buffer;
            int i4 = (cArr[0] << 18) | (cArr[1] << '\f');
            if (i < 3) {
                int i5 = this._bufEnd;
                this._bufEnd = i5 + 1;
                cArr[i5] = (char) ((i4 & 16711680) >> 16);
                this._state = 1;
                return;
            }
            int i6 = i4 | (cArr[2] << 6);
            if (i < 4) {
                int i7 = this._bufEnd;
                int i8 = i7 + 1;
                this._bufEnd = i8;
                cArr[i7] = (char) ((16711680 & i6) >> 16);
                this._bufEnd = i7 + 2;
                cArr[i8] = (char) ((i6 & 65280) >> 8);
                this._state = 1;
                return;
            }
            int i9 = i6 | cArr[3];
            int i10 = this._bufEnd;
            int i11 = i10 + 1;
            this._bufEnd = i11;
            cArr[i10] = (char) ((16711680 & i9) >> 16);
            int i12 = i10 + 2;
            this._bufEnd = i12;
            cArr[i11] = (char) ((i9 & 65280) >> 8);
            this._bufEnd = i10 + 3;
            cArr[i12] = (char) (i9 & 255);
        }

        @Override // java.io.Reader
        public boolean markSupported() {
            return false;
        }

        @Override // java.io.Reader
        public int read() throws IOException {
            if (!this._ready) {
                throw new IOException(BinaryStream.class.getName().concat(" is not ready"));
            }
            if (this._bufPos >= this._bufEnd) {
                loadNextBuffer();
            }
            int i = this._bufPos;
            if (i >= this._bufEnd) {
                return -1;
            }
            char[] cArr = this._buffer;
            this._bufPos = i + 1;
            return cArr[i];
        }

        public int terminatingChar() {
            return this._terminatingChar;
        }

        @Override // java.io.Reader
        public int read(char[] cArr) throws IOException {
            int i;
            int i2 = 0;
            while (i2 < cArr.length && (i = read()) != -1) {
                cArr[i2] = (char) i;
                i2++;
            }
            return i2;
        }

        public BinaryStream(Reader reader, char c) {
            this(reader, Base64Encoder.URLSafe64CharToInt, Base64Encoder.URLSafe64IntToCharTerminator, c);
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            int i3 = 0;
            while (i3 < i2) {
                int i4 = read();
                if (i4 == -1) {
                    break;
                }
                cArr[i + i3] = (char) i4;
                i3++;
            }
            return i3;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TextStream extends Reader {
        public final int[] _bintochar;
        public byte[] _inbuf;
        public int _outBufEnd;
        public int _outBufPos;
        public char[] _outbuf;
        public final char _padding;
        public boolean _ready;
        public final InputStream _source;
        public int _state;

        public TextStream(InputStream inputStream, int[] iArr, char c) {
            this._inbuf = new byte[385];
            this._outbuf = new char[512];
            this._source = inputStream;
            this._bintochar = iArr;
            this._padding = c;
            this._ready = true;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this._ready) {
                this._ready = false;
                this._source.close();
            }
            this._inbuf = null;
            this._outbuf = null;
        }

        public final void loadNextBuffer() throws IOException {
            int i = 0;
            this._outBufEnd = 0;
            this._outBufPos = 0;
            int i2 = this._source.read(this._inbuf, 0, 384);
            if (i2 < 0) {
                this._state = 1;
                return;
            }
            while (i < i2) {
                byte[] bArr = this._inbuf;
                int i3 = i + 1;
                int i4 = (bArr[i] & 255) << 16;
                if (i3 >= i2) {
                    char[] cArr = this._outbuf;
                    int i5 = this._outBufEnd;
                    int i6 = i5 + 1;
                    this._outBufEnd = i6;
                    int[] iArr = this._bintochar;
                    cArr[i5] = (char) iArr[(16515072 & i4) >> 18];
                    int i7 = i5 + 2;
                    this._outBufEnd = i7;
                    cArr[i6] = (char) iArr[(i4 & 258048) >> 12];
                    int i8 = i5 + 3;
                    this._outBufEnd = i8;
                    char c = this._padding;
                    cArr[i7] = c;
                    this._outBufEnd = i5 + 4;
                    cArr[i8] = c;
                    return;
                }
                int i9 = i + 2;
                int i10 = ((bArr[i3] & 255) << 8) | i4;
                if (i9 >= i2) {
                    char[] cArr2 = this._outbuf;
                    int i11 = this._outBufEnd;
                    int i12 = i11 + 1;
                    this._outBufEnd = i12;
                    int[] iArr2 = this._bintochar;
                    cArr2[i11] = (char) iArr2[(16515072 & i10) >> 18];
                    int i13 = i11 + 2;
                    this._outBufEnd = i13;
                    cArr2[i12] = (char) iArr2[(258048 & i10) >> 12];
                    int i14 = i11 + 3;
                    this._outBufEnd = i14;
                    cArr2[i13] = (char) iArr2[(i10 & 4032) >> 6];
                    this._outBufEnd = i11 + 4;
                    cArr2[i14] = this._padding;
                    return;
                }
                i += 3;
                int i15 = (bArr[i9] & 255) | i10;
                char[] cArr3 = this._outbuf;
                int i16 = this._outBufEnd;
                int i17 = i16 + 1;
                this._outBufEnd = i17;
                int[] iArr3 = this._bintochar;
                cArr3[i16] = (char) iArr3[(16515072 & i15) >> 18];
                int i18 = i16 + 2;
                this._outBufEnd = i18;
                cArr3[i17] = (char) iArr3[(258048 & i15) >> 12];
                int i19 = i16 + 3;
                this._outBufEnd = i19;
                cArr3[i18] = (char) iArr3[(i15 & 4032) >> 6];
                this._outBufEnd = i16 + 4;
                cArr3[i19] = (char) iArr3[i15 & 63];
            }
        }

        @Override // java.io.Reader
        public void mark(int i) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.io.Reader
        public boolean markSupported() {
            return false;
        }

        @Override // java.io.Reader
        public int read() throws IOException {
            if (!this._ready) {
                throw new IOException(TextStream.class.getName().concat(" is closed"));
            }
            if (this._state != 0) {
                return -1;
            }
            if (this._outBufPos >= this._outBufEnd) {
                loadNextBuffer();
            }
            int i = this._outBufPos;
            if (i >= this._outBufEnd) {
                return -1;
            }
            char[] cArr = this._outbuf;
            this._outBufPos = i + 1;
            return cArr[i];
        }

        @Override // java.io.Reader
        public boolean ready() throws IOException {
            return this._ready && this._source.available() > 0;
        }

        @Override // java.io.Reader
        public void reset() throws IOException {
            throw new IOException("reset not supported");
        }

        @Override // java.io.Reader
        public long skip(long j) throws IOException {
            if (!this._ready) {
                throw new IOException(TextStream.class.getName().concat(" is closed"));
            }
            long j2 = 0;
            if (j < 0) {
                throw new IllegalArgumentException("error skip only support non-negative a values for n");
            }
            long j3 = j;
            while (j3 > 0) {
                int i = this._outBufEnd - this._outBufPos;
                if (i < 1) {
                    loadNextBuffer();
                    i = this._outBufEnd - this._outBufPos;
                    if (i < 1) {
                        break;
                    }
                }
                long j4 = i;
                if (j4 > j3) {
                    this._outBufPos += (int) j3;
                    break;
                }
                j3 -= j4;
                this._outBufPos += i;
            }
            j2 = j3;
            return j - j2;
        }

        public TextStream(InputStream inputStream) {
            this(inputStream, Base64Encoder.URLSafe64IntToChar, Base64Encoder.URLSafe64IntToCharTerminator);
        }

        @Override // java.io.Reader
        public int read(char[] cArr) throws IOException {
            if (this._ready) {
                if (this._state != 0) {
                    return -1;
                }
                int length = cArr.length;
                while (length > 0) {
                    if (this._outBufPos >= this._outBufEnd) {
                        loadNextBuffer();
                    }
                    int i = this._outBufPos;
                    int i2 = this._outBufEnd;
                    if (i >= i2) {
                        break;
                    }
                    int i3 = i2 - i;
                    if (i3 > length) {
                        i3 = length;
                    }
                    System.arraycopy(this._outbuf, i, cArr, 0, i3);
                    this._outBufPos += i3;
                    length -= i3;
                }
                return 0;
            }
            throw new IOException(TextStream.class.getName().concat(" is closed"));
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this._ready) {
                if (this._state != 0) {
                    return -1;
                }
                while (i2 > 0) {
                    if (this._outBufPos >= this._outBufEnd) {
                        loadNextBuffer();
                    }
                    int i3 = this._outBufPos;
                    int i4 = this._outBufEnd;
                    if (i3 >= i4) {
                        break;
                    }
                    int i5 = i4 - i3;
                    if (i5 > i2) {
                        i5 = i2;
                    }
                    System.arraycopy(this._outbuf, i3, cArr, i, i5);
                    this._outBufPos += i5;
                    i += i5;
                    i2 -= i5;
                }
                return i;
            }
            throw new IOException(TextStream.class.getName().concat(" is closed"));
        }
    }
}
