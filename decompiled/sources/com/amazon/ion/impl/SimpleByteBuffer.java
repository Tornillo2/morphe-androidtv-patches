package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.IonException;
import com.amazon.ion.Timestamp;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SimpleByteBuffer implements ByteBuffer {
    public byte[] _bytes;
    public int _eob;
    public boolean _is_read_only;
    public int _start;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SimpleByteReader implements ByteReader {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public SimpleByteBuffer _buffer;
        public int _position;

        public SimpleByteReader(SimpleByteBuffer simpleByteBuffer) {
            this._buffer = simpleByteBuffer;
            this._position = simpleByteBuffer._start;
        }

        @Override // com.amazon.ion.impl.ByteReader
        public int position() {
            return this._position - this._buffer._start;
        }

        @Override // com.amazon.ion.impl.ByteReader
        public int read() {
            int i = this._position;
            SimpleByteBuffer simpleByteBuffer = this._buffer;
            if (i >= simpleByteBuffer._eob) {
                return -1;
            }
            byte[] bArr = simpleByteBuffer._bytes;
            this._position = i + 1;
            return bArr[i] & 255;
        }

        @Override // com.amazon.ion.impl.ByteReader
        public Decimal readDecimal(int i) throws IOException {
            BigInteger bigInteger;
            MathContext mathContext = MathContext.UNLIMITED;
            int i2 = 0;
            if (i == 0) {
                return Decimal.valueOf(0, mathContext);
            }
            int iPosition = position();
            int varInt = readVarInt();
            int iPosition2 = i - (position() - iPosition);
            if (iPosition2 > 0) {
                byte[] bArr = new byte[iPosition2];
                read(bArr, 0, iPosition2);
                byte b = bArr[0];
                if (b < 0) {
                    bArr[0] = (byte) (b & 127);
                    i2 = -1;
                } else {
                    i2 = 1;
                }
                bigInteger = new BigInteger(i2, bArr);
            } else {
                bigInteger = BigInteger.ZERO;
            }
            int i3 = -varInt;
            return (bigInteger.signum() == 0 && i2 == -1) ? Decimal.negativeZero(i3, mathContext) : Decimal.valueOf(bigInteger, i3, mathContext);
        }

        @Override // com.amazon.ion.impl.ByteReader
        public double readFloat(int i) throws IOException {
            if (i == 0) {
                return 0.0d;
            }
            if (i == 8) {
                return Double.longBitsToDouble(readULong(i));
            }
            throw new IOException("Length of float read must be 0 or 8");
        }

        @Override // com.amazon.ion.impl.ByteReader
        public String readString(int i) throws IOException {
            char[] cArr = new char[i];
            int iPosition = position() + i;
            int i2 = 0;
            while (position() < iPosition) {
                int unicodeScalar = readUnicodeScalar();
                if (unicodeScalar < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                if (unicodeScalar < 65536) {
                    cArr[i2] = (char) unicodeScalar;
                    i2++;
                } else {
                    int i3 = i2 + 1;
                    cArr[i2] = (char) _Private_IonConstants.makeHighSurrogate(unicodeScalar);
                    i2 += 2;
                    cArr[i3] = (char) _Private_IonConstants.makeLowSurrogate(unicodeScalar);
                }
            }
            if (position() >= iPosition) {
                return new String(cArr, 0, i2);
            }
            throwUnexpectedEOFException();
            throw null;
        }

        @Override // com.amazon.ion.impl.ByteReader
        public Timestamp readTimestamp(int i) throws IOException {
            Timestamp.Precision precision;
            Decimal decimal;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            if (i < 1) {
                return null;
            }
            int iPosition = position() + i;
            Integer varInteger = readVarInteger();
            if (position() < iPosition) {
                int varUInt = readVarUInt();
                Timestamp.Precision precision2 = Timestamp.Precision.YEAR;
                if (position() < iPosition) {
                    int varUInt2 = readVarUInt();
                    Timestamp.Precision precision3 = Timestamp.Precision.MONTH;
                    if (position() < iPosition) {
                        int varUInt3 = readVarUInt();
                        Timestamp.Precision precision4 = Timestamp.Precision.DAY;
                        if (position() < iPosition) {
                            int varUInt4 = readVarUInt();
                            int varUInt5 = readVarUInt();
                            Timestamp.Precision precision5 = Timestamp.Precision.MINUTE;
                            if (position() < iPosition) {
                                int varUInt6 = readVarUInt();
                                Timestamp.Precision precision6 = Timestamp.Precision.SECOND;
                                int iPosition2 = iPosition - position();
                                decimal = iPosition2 > 0 ? readDecimal(iPosition2) : null;
                                i7 = varUInt6;
                                precision = precision6;
                            } else {
                                decimal = null;
                                precision = precision5;
                                i7 = 0;
                            }
                            i6 = varUInt5;
                            i5 = varUInt4;
                        } else {
                            decimal = null;
                            precision = precision4;
                            i5 = 0;
                            i6 = 0;
                            i7 = 0;
                        }
                        i4 = varUInt3;
                    } else {
                        decimal = null;
                        precision = precision3;
                        i4 = 0;
                        i5 = 0;
                        i6 = 0;
                        i7 = 0;
                    }
                    i3 = varUInt2;
                } else {
                    decimal = null;
                    precision = precision2;
                    i3 = 0;
                    i4 = 0;
                    i5 = 0;
                    i6 = 0;
                    i7 = 0;
                }
                i2 = varUInt;
            } else {
                precision = null;
                decimal = null;
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
            }
            return Timestamp.createFromUtcFields(precision, i2, i3, i4, i5, i6, i7, decimal, varInteger);
        }

        @Override // com.amazon.ion.impl.ByteReader
        public int readTypeDesc() {
            return read();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:13:0x0022  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0026  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0030  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x006c  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0076  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x007b  */
        @Override // com.amazon.ion.impl.ByteReader
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public long readULong(int r7) throws java.io.IOException {
            /*
                r6 = this;
                r0 = 8
                r1 = 0
                r2 = 0
                switch(r7) {
                    case 0: goto L7f;
                    case 1: goto L70;
                    case 2: goto L62;
                    case 3: goto L54;
                    case 4: goto L46;
                    case 5: goto L38;
                    case 6: goto L2a;
                    case 7: goto L1c;
                    case 8: goto L10;
                    default: goto L8;
                }
            L8:
                com.amazon.ion.IonException r7 = new com.amazon.ion.IonException
                java.lang.String r0 = "value too large for Java long"
                r7.<init>(r0)
                throw r7
            L10:
                int r7 = r6.read()
                if (r7 < 0) goto L18
                long r2 = (long) r7
                goto L1c
            L18:
                r6.throwUnexpectedEOFException()
                throw r1
            L1c:
                int r7 = r6.read()
                if (r7 < 0) goto L26
                long r2 = r2 << r0
                long r4 = (long) r7
                long r2 = r2 | r4
                goto L2a
            L26:
                r6.throwUnexpectedEOFException()
                throw r1
            L2a:
                int r7 = r6.read()
                if (r7 < 0) goto L34
                long r2 = r2 << r0
                long r4 = (long) r7
                long r2 = r2 | r4
                goto L38
            L34:
                r6.throwUnexpectedEOFException()
                throw r1
            L38:
                int r7 = r6.read()
                if (r7 < 0) goto L42
                long r2 = r2 << r0
                long r4 = (long) r7
                long r2 = r2 | r4
                goto L46
            L42:
                r6.throwUnexpectedEOFException()
                throw r1
            L46:
                int r7 = r6.read()
                if (r7 < 0) goto L50
                long r2 = r2 << r0
                long r4 = (long) r7
                long r2 = r2 | r4
                goto L54
            L50:
                r6.throwUnexpectedEOFException()
                throw r1
            L54:
                int r7 = r6.read()
                if (r7 < 0) goto L5e
                long r2 = r2 << r0
                long r4 = (long) r7
                long r2 = r2 | r4
                goto L62
            L5e:
                r6.throwUnexpectedEOFException()
                throw r1
            L62:
                int r7 = r6.read()
                if (r7 < 0) goto L6c
                long r2 = r2 << r0
                long r4 = (long) r7
                long r2 = r2 | r4
                goto L70
            L6c:
                r6.throwUnexpectedEOFException()
                throw r1
            L70:
                int r7 = r6.read()
                if (r7 < 0) goto L7b
                long r0 = r2 << r0
                long r2 = (long) r7
                long r0 = r0 | r2
                return r0
            L7b:
                r6.throwUnexpectedEOFException()
                throw r1
            L7f:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.SimpleByteBuffer.SimpleByteReader.readULong(int):long");
        }

        public final int readUnicodeScalar() throws IOException {
            int i = read();
            return i >= 128 ? readUnicodeScalar_helper(i) : i;
        }

        public final int readUnicodeScalar_helper(int i) throws IOException {
            if ((i & 224) == 192) {
                int i2 = i & (-225);
                int i3 = read();
                if ((i3 & 192) == 128) {
                    return (i2 << 6) | (i3 & (-129));
                }
                throwUTF8Exception();
                throw null;
            }
            if ((i & 240) == 224) {
                int i4 = i & (-241);
                int i5 = read();
                if ((i5 & 192) != 128) {
                    throwUTF8Exception();
                    throw null;
                }
                int i6 = (i4 << 6) | (i5 & (-129));
                int i7 = read();
                if ((i7 & 192) != 128) {
                    throwUTF8Exception();
                    throw null;
                }
                int i8 = (i6 << 6) | (i7 & (-129));
                if (i8 <= 55295 || i8 >= 57344) {
                    return i8;
                }
                throw new IonException("illegal surrogate value encountered in input utf-8 stream");
            }
            if ((i & 248) != 240) {
                throwUTF8Exception();
                throw null;
            }
            int i9 = i & (-249);
            int i10 = read();
            if ((i10 & 192) != 128) {
                throwUTF8Exception();
                throw null;
            }
            int i11 = (i9 << 6) | (i10 & (-129));
            int i12 = read();
            if ((i12 & 192) != 128) {
                throwUTF8Exception();
                throw null;
            }
            int i13 = (i11 << 6) | (i12 & (-129));
            int i14 = read();
            if ((i14 & 192) != 128) {
                throwUTF8Exception();
                throw null;
            }
            int i15 = (i13 << 6) | (i14 & (-129));
            if (i15 <= 1114111) {
                return i15;
            }
            throw new IonException("illegal utf value encountered in input utf-8 stream");
        }

        @Override // com.amazon.ion.impl.ByteReader
        public int readVarInt() throws IOException {
            int i = read();
            if (i < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            boolean z = (i & 64) != 0;
            int i2 = i & 63;
            if ((i & 128) == 0) {
                int i3 = read();
                if (i3 < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                i2 = (i2 << 7) | (i3 & 127);
                if ((i3 & 128) == 0) {
                    int i4 = read();
                    if (i4 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    i2 = (i2 << 7) | (i4 & 127);
                    if ((i4 & 128) == 0) {
                        int i5 = read();
                        if (i5 < 0) {
                            throwUnexpectedEOFException();
                            throw null;
                        }
                        i2 = (i2 << 7) | (i5 & 127);
                        if ((i5 & 128) == 0) {
                            int i6 = read();
                            if (i6 < 0) {
                                throwUnexpectedEOFException();
                                throw null;
                            }
                            i2 = (i2 << 7) | (i6 & 127);
                            if ((i6 & 128) == 0) {
                                throwIntOverflowExeption();
                                throw null;
                            }
                        }
                    }
                }
            }
            return z ? -i2 : i2;
        }

        public Integer readVarInteger() throws IOException {
            int i = read();
            if (i < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            boolean z = (i & 64) != 0;
            int i2 = i & 63;
            if ((i & 128) == 0) {
                int i3 = read();
                if (i3 < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                i2 = (i2 << 7) | (i3 & 127);
                if ((i3 & 128) == 0) {
                    int i4 = read();
                    if (i4 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    i2 = (i2 << 7) | (i4 & 127);
                    if ((i4 & 128) == 0) {
                        int i5 = read();
                        if (i5 < 0) {
                            throwUnexpectedEOFException();
                            throw null;
                        }
                        i2 = (i2 << 7) | (i5 & 127);
                        if ((i5 & 128) == 0) {
                            int i6 = read();
                            if (i6 < 0) {
                                throwUnexpectedEOFException();
                                throw null;
                            }
                            i2 = (i2 << 7) | (i6 & 127);
                            if ((i6 & 128) == 0) {
                                throwIntOverflowExeption();
                                throw null;
                            }
                        }
                    }
                }
            }
            if (!z) {
                return new Integer(i2);
            }
            if (i2 != 0) {
                return new Integer(-i2);
            }
            return null;
        }

        @Override // com.amazon.ion.impl.ByteReader
        public long readVarLong() throws IOException {
            int i;
            int i2 = read();
            if (i2 < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            boolean z = (i2 & 64) != 0;
            long j = i2 & 63;
            if ((i2 & 128) == 0) {
                int i3 = read();
                if (i3 < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                j = (j << 7) | ((long) (i3 & 127));
                if ((i3 & 128) == 0) {
                    do {
                        i = read();
                        if (i < 0) {
                            throwUnexpectedEOFException();
                            throw null;
                        }
                        if (((-144115188075855872L) & j) != 0) {
                            throwIntOverflowExeption();
                            throw null;
                        }
                        j = (j << 7) | ((long) (i & 127));
                    } while ((i & 128) == 0);
                }
            }
            return z ? -j : j;
        }

        @Override // com.amazon.ion.impl.ByteReader
        public int readVarUInt() throws IOException {
            int i = read();
            if (i < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            int i2 = i & 127;
            if ((i & 128) != 0) {
                return i2;
            }
            int i3 = read();
            if (i3 < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            int i4 = (i2 << 7) | (i3 & 127);
            if ((i3 & 128) != 0) {
                return i4;
            }
            int i5 = read();
            if (i5 < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            int i6 = (i4 << 7) | (i5 & 127);
            if ((i5 & 128) != 0) {
                return i6;
            }
            int i7 = read();
            if (i7 < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            int i8 = (i6 << 7) | (i7 & 127);
            if ((i7 & 128) != 0) {
                return i8;
            }
            int i9 = read();
            if (i9 < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            int i10 = (i8 << 7) | (i9 & 127);
            if ((i9 & 128) != 0) {
                return i10;
            }
            throwIntOverflowExeption();
            throw null;
        }

        @Override // com.amazon.ion.impl.ByteReader
        public long readVarULong() throws IOException {
            int i;
            long j = 0;
            do {
                i = read();
                if (i < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                if (((-144115188075855872L) & j) != 0) {
                    throwIntOverflowExeption();
                    throw null;
                }
                j = (j << 7) | ((long) (i & 127));
            } while ((i & 128) == 0);
            return j;
        }

        @Override // com.amazon.ion.impl.ByteReader
        public void skip(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("length to skip must be non-negative");
            }
            int i2 = this._position + i;
            if (i2 > this._buffer._eob) {
                throw new IllegalArgumentException("skip would skip past end of buffer");
            }
            this._position = i2;
        }

        public void throwIntOverflowExeption() throws IOException {
            throw new IOException("int in stream is too long for a Java int 32 use readLong()");
        }

        public void throwUTF8Exception() throws IOException {
            throw new IOException("Invalid UTF-8 character encounter in a string at pos " + position());
        }

        public void throwUnexpectedEOFException() throws IOException {
            throw new IOException("unexpected EOF in value at offset " + position());
        }

        @Override // com.amazon.ion.impl.ByteReader
        public void position(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("position must be non-negative");
            }
            SimpleByteBuffer simpleByteBuffer = this._buffer;
            int i2 = i + simpleByteBuffer._start;
            if (i2 > simpleByteBuffer._eob) {
                throw new IllegalArgumentException("position is past end of buffer");
            }
            this._position = i2;
        }

        @Override // com.amazon.ion.impl.ByteReader
        public int read(byte[] bArr, int i, int i2) {
            if (bArr != null && i >= 0 && i2 >= 0 && i + i2 <= bArr.length) {
                int i3 = this._position;
                SimpleByteBuffer simpleByteBuffer = this._buffer;
                int i4 = simpleByteBuffer._eob;
                if (i3 >= i4) {
                    return 0;
                }
                if (i2 + i3 > i4) {
                    i2 = i4 - i3;
                }
                System.arraycopy(simpleByteBuffer._bytes, i3, bArr, i, i2);
                this._position += i2;
                return i2;
            }
            throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SimpleByteWriter extends OutputStream implements ByteWriter {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final Double DOUBLE_POS_ZERO = Double.valueOf(0.0d);
        public static final int _ib_FLOAT64_LEN = 8;
        public SimpleByteBuffer _buffer;
        public int _position;

        public SimpleByteWriter(SimpleByteBuffer simpleByteBuffer) {
            this._buffer = simpleByteBuffer;
            this._position = simpleByteBuffer._start;
        }

        public void flushTo(OutputStream outputStream) throws IOException {
            this._buffer.writeBytes(outputStream);
            this._position = 0;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public void insert(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("insert length must be non negative");
            }
            SimpleByteBuffer simpleByteBuffer = this._buffer;
            int i2 = simpleByteBuffer._eob;
            int i3 = this._position;
            byte[] bArr = simpleByteBuffer._bytes;
            System.arraycopy(bArr, i3, bArr, i3 + i, i2 - i3);
            this._buffer._eob += i;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int position() {
            return this._position - this._buffer._start;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public void remove(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("remove length must be non negative");
            }
            SimpleByteBuffer simpleByteBuffer = this._buffer;
            int i2 = simpleByteBuffer._eob;
            int i3 = this._position;
            byte[] bArr = simpleByteBuffer._bytes;
            System.arraycopy(bArr, i3 + i, bArr, i3, i2 - i3);
            this._buffer._eob -= i;
        }

        public void throwException(String str) throws IOException {
            throw new IOException(str);
        }

        public void throwUTF8Exception() throws IOException {
            throwException("Invalid UTF-8 character encounter in a string at pos " + position());
            throw null;
        }

        @Override // java.io.OutputStream
        public final void write(int i) throws IOException {
            write((byte) i);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeDecimal(BigDecimal bigDecimal) throws IOException {
            return writeDecimal(bigDecimal, null);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeFloat(double d) throws IOException {
            if (Double.valueOf(d).equals(DOUBLE_POS_ZERO)) {
                return 0;
            }
            writeULong(Double.doubleToRawLongBits(d), 8);
            return 8;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeIonInt(int i, int i2) {
            writeIonInt(i, i2);
            return i2;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public final int writeString(String str) throws IOException {
            return writeString(str, null);
        }

        public int writeTimestamp(Timestamp timestamp) throws IOException {
            int iWriteVarInt;
            if (timestamp == null) {
                return 0;
            }
            Timestamp.Precision precision = timestamp._precision;
            Integer num = timestamp._offset;
            if (num == null) {
                write((byte) -64);
                iWriteVarInt = 1;
            } else {
                iWriteVarInt = writeVarInt(num.intValue(), true);
            }
            if (precision.includes(Timestamp.Precision.YEAR)) {
                iWriteVarInt += writeVarUInt(timestamp._year, true);
            }
            if (precision.includes(Timestamp.Precision.MONTH)) {
                iWriteVarInt += writeVarUInt(timestamp._month, true);
            }
            if (precision.includes(Timestamp.Precision.DAY)) {
                iWriteVarInt += writeVarUInt(timestamp._day, true);
            }
            if (precision.includes(Timestamp.Precision.MINUTE)) {
                iWriteVarInt = writeVarUInt(timestamp._minute, true) + writeVarUInt(timestamp._hour, true) + iWriteVarInt;
            }
            if (!precision.includes(Timestamp.Precision.SECOND)) {
                return iWriteVarInt;
            }
            int iWriteVarUInt = writeVarUInt(timestamp._second, true) + iWriteVarInt;
            BigDecimal bigDecimal = timestamp._fraction;
            return bigDecimal != null ? writeDecimal(bigDecimal, null) + iWriteVarUInt : iWriteVarUInt;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public void writeTypeDesc(int i) {
            write((byte) (i & 255));
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeTypeDescWithLength(int i, int i2, int i3) {
            int i4 = (i & 15) << 4;
            if (i3 >= 14) {
                writeTypeDesc(i4 | 14);
                return writeVarUInt(i3, i2, true) + 1;
            }
            writeTypeDesc(i4 | (i3 & 15));
            return 1;
        }

        public int writeULong(long j, int i) throws IOException {
            switch (i) {
                case 8:
                    write((byte) ((j >> 56) & 255));
                case 7:
                    write((byte) ((j >> 48) & 255));
                case 6:
                    write((byte) ((j >> 40) & 255));
                case 5:
                    write((byte) ((j >> 32) & 255));
                case 4:
                    write((byte) ((j >> 24) & 255));
                case 3:
                    write((byte) ((j >> 16) & 255));
                case 2:
                    write((byte) ((j >> 8) & 255));
                case 1:
                    write((byte) (j & 255));
                    break;
            }
            return i;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeVarInt(int i, int i2, boolean z) {
            if (i == 0) {
                if (z) {
                    write((byte) -128);
                }
                return i2;
            }
            boolean z2 = i < 0;
            if (z2) {
                i = -i;
            }
            int i3 = (byte) ((i >>> ((i2 - 1) * 7)) & 127);
            if (z2) {
                i3 |= 64;
            }
            if (i2 == 1) {
                i3 |= 128;
            }
            write((byte) i3);
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return i2;
                        }
                        write((byte) ((i >> 21) & 127));
                    }
                    write((byte) ((i >> 14) & 127));
                }
                write((byte) ((i >> 7) & 127));
            }
            write((byte) ((i & 127) | 128));
            return i2;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeVarUInt(int i, int i2, boolean z) {
            if (i < 0) {
                throw new IllegalArgumentException("signed int where unsigned (>= 0) was expected");
            }
            int i3 = i2 - 1;
            if (i3 != -1) {
                if (i3 != 0) {
                    if (i3 != 1) {
                        if (i3 != 2) {
                            if (i3 != 3) {
                                if (i3 == 4) {
                                    write((byte) ((i >> 28) & 127));
                                }
                            }
                            write((byte) ((i >> 21) & 127));
                        }
                        write((byte) ((i >> 14) & 127));
                    }
                    write((byte) ((i >> 7) & 127));
                }
                write((byte) (((long) (i & 127)) | 128));
                return i2;
            }
            if (z) {
                write((byte) -128);
                return 1;
            }
            return i2;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public void position(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("position must be non-negative");
            }
            SimpleByteBuffer simpleByteBuffer = this._buffer;
            int i2 = i + simpleByteBuffer._start;
            if (i2 > simpleByteBuffer._eob) {
                throw new IllegalArgumentException("position is past end of buffer");
            }
            this._position = i2;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public final void write(byte b) {
            SimpleByteBuffer simpleByteBuffer = this._buffer;
            byte[] bArr = simpleByteBuffer._bytes;
            int i = this._position;
            int i2 = i + 1;
            this._position = i2;
            bArr[i] = b;
            if (i2 > simpleByteBuffer._eob) {
                simpleByteBuffer._eob = i2;
            }
        }

        public final int writeDecimal(BigDecimal bigDecimal, UserByteWriter userByteWriter) throws IOException {
            int iLenVarUInt;
            if (bigDecimal == null || BigDecimal.ZERO.equals(bigDecimal)) {
                return 0;
            }
            BigInteger bigIntegerUnscaledValue = bigDecimal.unscaledValue();
            boolean z = bigIntegerUnscaledValue.compareTo(BigInteger.ZERO) < 0;
            if (z) {
                bigIntegerUnscaledValue = bigIntegerUnscaledValue.negate();
            }
            byte[] byteArray = bigIntegerUnscaledValue.toByteArray();
            int i = -bigDecimal.scale();
            if (userByteWriter != null) {
                iLenVarUInt = IonBinary.lenVarUInt(i);
                userByteWriter.writeIonInt(i, iLenVarUInt);
            } else {
                long j = i;
                int iLenVarUInt2 = IonBinary.lenVarUInt(j);
                writeIonInt(j, iLenVarUInt2);
                iLenVarUInt = iLenVarUInt2;
            }
            byte b = byteArray[0];
            if ((b & 128) != 0) {
                if (userByteWriter != null) {
                    userByteWriter.write((byte) (z ? 128 : 0));
                } else {
                    write((byte) (z ? 128 : 0));
                }
                iLenVarUInt++;
            } else if (z) {
                byteArray[0] = (byte) (b | 128);
            }
            if (userByteWriter != null) {
                userByteWriter.write(byteArray, 0, byteArray.length);
            } else {
                write(byteArray, 0, byteArray.length);
            }
            return iLenVarUInt + byteArray.length;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeIonInt(long j, int i) {
            if (j == 0) {
                return i;
            }
            if (j < 0) {
                j = -j;
            }
            switch (i) {
                case 8:
                    write((byte) ((j >> 56) & 255));
                case 7:
                    write((byte) ((j >> 48) & 255));
                case 6:
                    write((byte) ((j >> 40) & 255));
                case 5:
                    write((byte) ((j >> 32) & 255));
                case 4:
                    write((byte) ((j >> 24) & 255));
                case 3:
                    write((byte) ((j >> 16) & 255));
                case 2:
                    write((byte) ((j >> 8) & 255));
                case 1:
                    write((byte) (j & 255));
                    break;
            }
            return i;
        }

        public final int writeString(String str, UserByteWriter userByteWriter) throws IOException {
            int i = 0;
            int i2 = 0;
            while (i < str.length()) {
                int iCharAt = str.charAt(i);
                if (iCharAt > 127) {
                    if (iCharAt >= 55296 && iCharAt <= 57343) {
                        if (_Private_IonConstants.isHighSurrogate(iCharAt)) {
                            i++;
                            if (i >= str.length()) {
                                throw new IonException("invalid string, unpaired high surrogate character");
                            }
                            char cCharAt = str.charAt(i);
                            if (!_Private_IonConstants.isLowSurrogate(cCharAt)) {
                                throw new IonException("invalid string, unpaired high surrogate character");
                            }
                            iCharAt = _Private_IonConstants.makeUnicodeScalar(iCharAt, cCharAt);
                        } else if (_Private_IonConstants.isLowSurrogate(iCharAt)) {
                            throw new IonException("invalid string, unpaired low surrogate character");
                        }
                    }
                    iCharAt = IonBinary.makeUTF8IntFromScalar(iCharAt);
                }
                if (userByteWriter == null) {
                    while (true) {
                        write((byte) (iCharAt & 255));
                        i2++;
                        if ((iCharAt & (-256)) == 0) {
                            break;
                        }
                        iCharAt >>>= 8;
                    }
                } else {
                    while (true) {
                        userByteWriter.write((byte) (iCharAt & 255));
                        i2++;
                        if ((iCharAt & (-256)) == 0) {
                            break;
                        }
                        iCharAt >>>= 8;
                    }
                }
                i++;
            }
            return i2;
        }

        @Override // java.io.OutputStream, com.amazon.ion.impl.ByteWriter
        public void write(byte[] bArr, int i, int i2) {
            if (bArr != null && i >= 0 && i < bArr.length && i2 >= 0 && i + i2 <= bArr.length) {
                System.arraycopy(bArr, i, this._buffer._bytes, this._position, i2);
                int i3 = this._position + i2;
                this._position = i3;
                SimpleByteBuffer simpleByteBuffer = this._buffer;
                if (i3 > simpleByteBuffer._eob) {
                    simpleByteBuffer._eob = i3;
                    return;
                }
                return;
            }
            throw new IllegalArgumentException();
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeTypeDescWithLength(int i, int i2) {
            int i3 = (i & 15) << 4;
            if (i2 >= 14) {
                writeTypeDesc(i3 | 14);
                return writeVarUInt(i2, IonBinary.lenVarUInt(i2), true) + 1;
            }
            writeTypeDesc(i3 | (i2 & 15));
            return 1;
        }

        public int writeVarInt(int i, boolean z) {
            int iLenVarInt = IonBinary.lenVarInt(i);
            writeVarInt(i, iLenVarInt, z);
            return iLenVarInt;
        }

        public int writeVarUInt(int i, boolean z) {
            return writeVarUInt(i, IonBinary.lenVarUInt(i), z);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeVarInt(long j, int i, boolean z) {
            int i2;
            if (j == 0) {
                if (z) {
                    write((byte) -128);
                }
                return i;
            }
            if (j < 0) {
                j = -j;
                if (j == Long.MIN_VALUE) {
                    i2 = (byte) ((j >>> (i * 7)) & 127);
                } else {
                    byte b = (byte) ((j >>> (i * 7)) & 127);
                    i2 = i == 1 ? b | 128 : b;
                }
                write((byte) (i2 | 64));
            } else {
                int i3 = (byte) ((j >>> (i * 7)) & 127);
                if (i == 1) {
                    i3 |= 128;
                }
                write((byte) i3);
            }
            switch (i - 1) {
                case 9:
                    write((byte) ((j >>> 63) & 127));
                case 8:
                    write((byte) ((j >> 56) & 127));
                case 7:
                    write((byte) ((j >> 49) & 127));
                case 6:
                    write((byte) ((j >> 42) & 127));
                case 5:
                    write((byte) ((j >> 35) & 127));
                case 4:
                    write((byte) ((j >> 28) & 127));
                case 3:
                    write((byte) ((j >> 21) & 127));
                case 2:
                    write((byte) ((j >> 14) & 127));
                case 1:
                    write((byte) ((j >> 7) & 127));
                case 0:
                    write((byte) ((j & 127) | 128));
                    break;
            }
            return i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.amazon.ion.impl.ByteWriter
        public int writeVarUInt(long j, int i, boolean z) {
            switch (i - 1) {
                case -1:
                    if (z) {
                        write((byte) -128);
                    }
                    break;
                case 0:
                    write((byte) ((j & ((long) 127)) | 128));
                    break;
                case 1:
                    write((byte) ((j >> 7) & ((long) 127)));
                    write((byte) ((j & ((long) 127)) | 128));
                    break;
                case 2:
                    write((byte) ((j >> 14) & ((long) 127)));
                    write((byte) ((j >> 7) & ((long) 127)));
                    write((byte) ((j & ((long) 127)) | 128));
                    break;
                case 3:
                    write((byte) ((j >> 21) & ((long) 127)));
                    write((byte) ((j >> 14) & ((long) 127)));
                    write((byte) ((j >> 7) & ((long) 127)));
                    write((byte) ((j & ((long) 127)) | 128));
                    break;
                case 4:
                    write((byte) ((j >> 28) & ((long) 127)));
                    write((byte) ((j >> 21) & ((long) 127)));
                    write((byte) ((j >> 14) & ((long) 127)));
                    write((byte) ((j >> 7) & ((long) 127)));
                    write((byte) ((j & ((long) 127)) | 128));
                    break;
                case 5:
                    write((byte) ((j >> 35) & ((long) 127)));
                    write((byte) ((j >> 28) & ((long) 127)));
                    write((byte) ((j >> 21) & ((long) 127)));
                    write((byte) ((j >> 14) & ((long) 127)));
                    write((byte) ((j >> 7) & ((long) 127)));
                    write((byte) ((j & ((long) 127)) | 128));
                    break;
                case 6:
                    write((byte) ((j >> 42) & ((long) 127)));
                    write((byte) ((j >> 35) & ((long) 127)));
                    write((byte) ((j >> 28) & ((long) 127)));
                    write((byte) ((j >> 21) & ((long) 127)));
                    write((byte) ((j >> 14) & ((long) 127)));
                    write((byte) ((j >> 7) & ((long) 127)));
                    write((byte) ((j & ((long) 127)) | 128));
                    break;
                case 7:
                    write((byte) ((j >> 49) & ((long) 127)));
                    write((byte) ((j >> 42) & ((long) 127)));
                    write((byte) ((j >> 35) & ((long) 127)));
                    write((byte) ((j >> 28) & ((long) 127)));
                    write((byte) ((j >> 21) & ((long) 127)));
                    write((byte) ((j >> 14) & ((long) 127)));
                    write((byte) ((j >> 7) & ((long) 127)));
                    write((byte) ((j & ((long) 127)) | 128));
                    break;
                case 8:
                    write((byte) ((j >> 56) & ((long) 127)));
                    write((byte) ((j >> 49) & ((long) 127)));
                    write((byte) ((j >> 42) & ((long) 127)));
                    write((byte) ((j >> 35) & ((long) 127)));
                    write((byte) ((j >> 28) & ((long) 127)));
                    write((byte) ((j >> 21) & ((long) 127)));
                    write((byte) ((j >> 14) & ((long) 127)));
                    write((byte) ((j >> 7) & ((long) 127)));
                    write((byte) ((j & ((long) 127)) | 128));
                    break;
                case 9:
                    write((byte) ((j >> 63) & ((long) 127)));
                    write((byte) ((j >> 56) & ((long) 127)));
                    write((byte) ((j >> 49) & ((long) 127)));
                    write((byte) ((j >> 42) & ((long) 127)));
                    write((byte) ((j >> 35) & ((long) 127)));
                    write((byte) ((j >> 28) & ((long) 127)));
                    write((byte) ((j >> 21) & ((long) 127)));
                    write((byte) ((j >> 14) & ((long) 127)));
                    write((byte) ((j >> 7) & ((long) 127)));
                    write((byte) ((j & ((long) 127)) | 128));
                    break;
            }
            return i;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UserByteWriter extends OutputStream implements ByteWriter {
        public static final int MAX_FLOAT_BINARY_LENGTH = 8;
        public static final int MAX_UINT7_BINARY_LENGTH = 5;
        public static final int REQUIRED_BUFFER_SPACE = 8;
        public int _buffer_size;
        public int _limit;
        public int _position;
        public SimpleByteWriter _simple_writer;
        public OutputStream _user_stream;

        public UserByteWriter(OutputStream outputStream, byte[] bArr) {
            if (bArr == null || bArr.length < 8) {
                throw new IllegalArgumentException("requires a buffer at least 8 bytes long");
            }
            this._simple_writer = new SimpleByteWriter(new SimpleByteBuffer(bArr, 0, bArr.length, false));
            this._user_stream = outputStream;
            int length = bArr.length;
            this._buffer_size = length;
            this._limit = length;
        }

        public final void checkForSpace(int i) {
            if (this._position + i > this._limit) {
                flush();
            }
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
            if (this._position + this._buffer_size > this._limit) {
                try {
                    this._simple_writer.flushTo(this._user_stream);
                    this._limit = this._position + this._buffer_size;
                } catch (IOException e) {
                    throw new IonException(e.getMessage(), e);
                }
            }
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public void insert(int i) {
            throw new UnsupportedOperationException("use a SimpleByteWriter if you need to insert");
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int position() {
            return this._position;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public void remove(int i) {
            throw new UnsupportedOperationException("use a SimpleByteWriter if you need to remove bytes");
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            write((byte) i);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeDecimal(BigDecimal bigDecimal) throws IOException {
            return this._simple_writer.writeDecimal(bigDecimal, this);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeFloat(double d) throws IOException {
            checkForSpace(8);
            return this._simple_writer.writeFloat(d);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeIonInt(long j, int i) throws IOException {
            checkForSpace(i);
            this._simple_writer.writeIonInt(j, i);
            return i;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeString(String str) throws IOException {
            return this._simple_writer.writeString(str, this);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public void writeTypeDesc(int i) throws IOException {
            checkForSpace(1);
            this._simple_writer.writeTypeDesc(i);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeTypeDescWithLength(int i, int i2, int i3) throws IOException {
            checkForSpace(6);
            return this._simple_writer.writeTypeDescWithLength(i, i2, i3);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeVarInt(long j, int i, boolean z) throws IOException {
            checkForSpace(i);
            this._simple_writer.writeVarInt(j, i, z);
            return i;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeVarUInt(int i, int i2, boolean z) throws IOException {
            checkForSpace(i2);
            return this._simple_writer.writeVarUInt(i, i2, z);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public void position(int i) {
            throw new UnsupportedOperationException("use a SimpleByteWriter if you need to set your position");
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public void write(byte b) throws IOException {
            checkForSpace(1);
            this._simple_writer.write(b);
            this._position++;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeIonInt(int i, int i2) throws IOException {
            checkForSpace(i2);
            this._simple_writer.writeIonInt(i, i2);
            return i2;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeTypeDescWithLength(int i, int i2) throws IOException {
            checkForSpace(6);
            return this._simple_writer.writeTypeDescWithLength(i, i2);
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeVarInt(int i, int i2, boolean z) throws IOException {
            checkForSpace(i2);
            this._simple_writer.writeVarInt(i, i2, z);
            return i2;
        }

        @Override // com.amazon.ion.impl.ByteWriter
        public int writeVarUInt(long j, int i, boolean z) throws IOException {
            checkForSpace(i);
            this._simple_writer.writeVarUInt(j, i, z);
            return i;
        }
    }

    public SimpleByteBuffer(byte[] bArr) {
        this(bArr, 0, bArr.length, false);
    }

    @Override // com.amazon.ion.impl.ByteBuffer
    public byte[] getBytes() {
        int i = this._eob;
        int i2 = this._start;
        int i3 = i - i2;
        byte[] bArr = new byte[i3];
        System.arraycopy(this._bytes, i2, bArr, 0, i3);
        return bArr;
    }

    public int getLength() {
        return this._eob - this._start;
    }

    @Override // com.amazon.ion.impl.ByteBuffer
    public ByteReader getReader() {
        return new SimpleByteReader(this);
    }

    @Override // com.amazon.ion.impl.ByteBuffer
    public ByteWriter getWriter() {
        if (this._is_read_only) {
            throw new IllegalStateException("this buffer is read only");
        }
        return new SimpleByteWriter(this);
    }

    @Override // com.amazon.ion.impl.ByteBuffer
    public void writeBytes(OutputStream outputStream) throws IOException {
        int i = this._eob;
        int i2 = this._start;
        outputStream.write(this._bytes, i2, i - i2);
    }

    public SimpleByteBuffer(byte[] bArr, boolean z) {
        this(bArr, 0, bArr.length, z);
    }

    public SimpleByteBuffer(byte[] bArr, int i, int i2) {
        this(bArr, i, i2, false);
    }

    public SimpleByteBuffer(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        if (bArr != null && i >= 0 && i <= bArr.length && i2 >= 0 && (i3 = i2 + i) <= bArr.length) {
            this._bytes = bArr;
            this._start = i;
            this._eob = i3;
            this._is_read_only = z;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // com.amazon.ion.impl.ByteBuffer
    public int getBytes(byte[] bArr, int i, int i2) {
        if (bArr != null && i >= 0 && i <= bArr.length && i2 >= 0 && i + i2 <= bArr.length) {
            int i3 = this._eob;
            int i4 = this._start;
            int i5 = i3 - i4;
            if (i5 <= i2) {
                System.arraycopy(this._bytes, i4, bArr, i, i5);
                return i5;
            }
            throw new IllegalArgumentException("insufficient space in destination buffer");
        }
        throw new IllegalArgumentException();
    }
}
