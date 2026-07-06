package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.support.v4.media.session.PlaybackStateCompat;
import com.amazon.ion.Decimal;
import com.amazon.ion.IonException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.UnexpectedEofException;
import com.amazon.ion.impl.BlockedBuffer;
import com.amazon.ion.impl._Private_IonConstants;
import com.amazon.ion.impl.bin.WriteBuffer;
import com.amazon.ion.util.IonStreamUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Stack;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonBinary {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int SIZE_OF_LONG = 8;
    public static final int _ib_FLOAT64_LEN = 8;
    public static final int _ib_INT64_LEN_MAX = 8;
    public static final int _ib_TOKEN_LEN = 1;
    public static final int _ib_VAR_INT32_LEN_MAX = 5;
    public static final int _ib_VAR_INT64_LEN_MAX = 10;
    public static boolean debugValidation = false;
    public static final BigInteger MAX_LONG_VALUE = new BigInteger(Long.toString(Long.MAX_VALUE));
    public static final Double DOUBLE_POS_ZERO = Double.valueOf(0.0d);
    public static final int ZERO_DECIMAL_TYPEDESC = _Private_IonConstants.makeTypeDescriptor(5, 0);
    public static final int NULL_DECIMAL_TYPEDESC = 95;

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonBinary$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$Timestamp$Precision;

        static {
            int[] iArr = new int[Timestamp.Precision.values().length];
            $SwitchMap$com$amazon$ion$Timestamp$Precision = iArr;
            try {
                iArr[Timestamp.Precision.FRACTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Timestamp.Precision.SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Timestamp.Precision.MINUTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Timestamp.Precision.DAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Timestamp.Precision.MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Timestamp.Precision.YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class BufferManager {
        public BlockedBuffer _buf;
        public Reader _reader;
        public Writer _writer;

        public BufferManager() {
            this._buf = new BlockedBuffer();
            openReader();
            openWriter();
        }

        public static BufferManager makeReadManager(BlockedBuffer blockedBuffer) {
            BufferManager bufferManager = new BufferManager(blockedBuffer);
            bufferManager.openReader();
            return bufferManager;
        }

        public static BufferManager makeReadWriteManager(BlockedBuffer blockedBuffer) {
            BufferManager bufferManager = new BufferManager(blockedBuffer);
            bufferManager.openReader();
            bufferManager.openWriter();
            return bufferManager;
        }

        public BlockedBuffer buffer() {
            return this._buf;
        }

        public Reader openReader() {
            if (this._reader == null) {
                this._reader = new Reader(0, this._buf);
            }
            return this._reader;
        }

        public Writer openWriter() {
            if (this._writer == null) {
                this._writer = new Writer(this._buf);
            }
            return this._writer;
        }

        public Reader reader() {
            return this._reader;
        }

        public Writer writer() {
            return this._writer;
        }

        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public BufferManager m244clone() throws CloneNotSupportedException {
            return new BufferManager(this._buf.m243clone());
        }

        public Reader reader(int i) throws IOException {
            this._reader.setPosition(i);
            return this._reader;
        }

        public Writer writer(int i) throws IOException {
            this._writer.setPosition(i);
            return this._writer;
        }

        public BufferManager(BlockedBuffer blockedBuffer) {
            this._buf = blockedBuffer;
            openReader();
            openWriter();
        }

        public BufferManager(InputStream inputStream) {
            this();
            try {
                this._writer.write(inputStream);
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        }

        public BufferManager(InputStream inputStream, int i) {
            this();
            try {
                this._writer.write(inputStream, i);
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class PositionMarker {
        public int _pos;
        public Object _userData;

        public PositionMarker() {
        }

        public int getPosition() {
            return this._pos;
        }

        public Object getUserData() {
            return this._userData;
        }

        public void setPosition(int i) {
            this._pos = i;
        }

        public void setUserData(Object obj) {
            this._userData = obj;
        }

        public PositionMarker(int i, Object obj) {
            this._pos = i;
            this._userData = obj;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Reader extends BlockedBuffer.BlockedByteInputStream {
        public static final /* synthetic */ boolean $assertionsDisabled = false;

        public Reader(BlockedBuffer blockedBuffer) {
            super(0, blockedBuffer);
        }

        public byte[] getBytes() throws IOException {
            if (this._buf == null) {
                return null;
            }
            sync();
            setPosition(0);
            int i = this._buf._buf_limit;
            byte[] bArr = new byte[i];
            if (_Private_Utils.readFully(this, bArr) == i) {
                return bArr;
            }
            throw new UnexpectedEofException();
        }

        public int readActualTypeDesc() throws IOException {
            int i;
            int i2 = read();
            if (i2 < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            int typeCode = _Private_IonConstants.getTypeCode(i2);
            if (typeCode != 14 || (i = i2 & 15) == 0) {
                return i2;
            }
            readLength(typeCode, i);
            for (int varIntAsInt = readVarIntAsInt(); varIntAsInt > 0; varIntAsInt--) {
                if (read() < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
            }
            int i3 = read();
            if (i3 >= 0) {
                return i3;
            }
            throwUnexpectedEOFException();
            throw null;
        }

        public int[] readAnnotations() throws IOException {
            int varUIntAsInt = readVarUIntAsInt();
            int i = this._pos;
            int i2 = varUIntAsInt + i;
            int i3 = 0;
            int i4 = 0;
            while (this._pos < i2) {
                readVarUIntAsInt();
                i4++;
            }
            if (i4 <= 0) {
                return null;
            }
            int[] iArr = new int[i4];
            setPosition(i);
            while (this._pos < i2) {
                iArr[i3] = readVarUIntAsInt();
                i3++;
            }
            return iArr;
        }

        public Decimal readDecimalValue(int i) throws IOException {
            BigInteger bigInteger;
            MathContext mathContext = MathContext.UNLIMITED;
            int i2 = 0;
            if (i == 0) {
                return Decimal.valueOf(0, mathContext);
            }
            int i3 = this._pos;
            int varIntAsInt = readVarIntAsInt();
            int i4 = i - (this._pos - i3);
            if (i4 > 0) {
                byte[] bArr = new byte[i4];
                IonBinary.readAll(this, bArr, 0, i4);
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
            int i5 = -varIntAsInt;
            return (bigInteger.signum() == 0 && i2 == -1) ? Decimal.negativeZero(i5, mathContext) : Decimal.valueOf(bigInteger, i5, mathContext);
        }

        public double readFloatValue(int i) throws IOException {
            if (i == 0) {
                return 0.0d;
            }
            if (i == 8) {
                return Double.longBitsToDouble(readUIntAsLong(i));
            }
            throw new IonException("Length of float read must be 0 or 8");
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0043  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0045 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x004a  */
        @java.lang.Deprecated
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int readIntAsInt(int r6) throws java.io.IOException {
            /*
                r5 = this;
                r0 = 0
                if (r6 <= 0) goto L52
                int r1 = r5.read()
                r2 = 0
                if (r1 < 0) goto L4e
                r3 = r1 & 127(0x7f, float:1.78E-43)
                r1 = r1 & 128(0x80, float:1.8E-43)
                r4 = 1
                if (r1 == 0) goto L12
                r0 = 1
            L12:
                int r6 = r6 - r4
                switch(r6) {
                    case 2: goto L2e;
                    case 3: goto L1f;
                    case 4: goto L17;
                    case 5: goto L17;
                    case 6: goto L17;
                    case 7: goto L17;
                    default: goto L16;
                }
            L16:
                goto L41
            L17:
                com.amazon.ion.IonException r6 = new com.amazon.ion.IonException
                java.lang.String r0 = "overflow attempt to read long value into an int"
                r6.<init>(r0)
                throw r6
            L1f:
                int r6 = r5.read()
                if (r6 < 0) goto L2a
                int r1 = r3 << 8
                r3 = r1 | r6
                goto L2e
            L2a:
                r5.throwUnexpectedEOFException()
                throw r2
            L2e:
                int r6 = r5.read()
                if (r6 < 0) goto L4a
                int r1 = r3 << 8
                r6 = r6 | r1
                int r1 = r5.read()
                if (r1 < 0) goto L46
                int r6 = r6 << 8
                r3 = r6 | r1
            L41:
                if (r0 == 0) goto L45
                int r6 = -r3
                return r6
            L45:
                return r3
            L46:
                r5.throwUnexpectedEOFException()
                throw r2
            L4a:
                r5.throwUnexpectedEOFException()
                throw r2
            L4e:
                r5.throwUnexpectedEOFException()
                throw r2
            L52:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonBinary.Reader.readIntAsInt(int):int");
        }

        public long readIntAsLong(int i) throws IOException {
            if (i <= 0) {
                return 0L;
            }
            int i2 = read();
            if (i2 < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            long j = i2 & 127;
            boolean z = (i2 & 128) != 0;
            switch (i - 1) {
                case 8:
                    int i3 = read();
                    if (i3 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    j = (j << 8) | ((long) i3);
                    break;
                case 7:
                    int i4 = read();
                    if (i4 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    j = (j << 8) | ((long) i4);
                    break;
                case 6:
                    int i5 = read();
                    if (i5 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    j = (j << 8) | ((long) i5);
                    break;
                case 5:
                    int i6 = read();
                    if (i6 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    j = (j << 8) | ((long) i6);
                    break;
                case 4:
                    int i7 = read();
                    if (i7 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    j = (j << 8) | ((long) i7);
                    break;
                case 3:
                    int i8 = read();
                    if (i8 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    j = (j << 8) | ((long) i8);
                    break;
                case 2:
                    int i9 = read();
                    if (i9 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    j = (j << 8) | ((long) i9);
                    break;
                case 1:
                    int i10 = read();
                    if (i10 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    j = (j << 8) | ((long) i10);
                    break;
                default:
                    return z ? -j : j;
            }
        }

        public int readLength(int i, int i2) throws IOException {
            if (i != 99) {
                switch (i) {
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 14:
                        if (i2 != 0) {
                            if (i2 == 14) {
                                return readVarUIntAsInt();
                            }
                            if (i2 != 15) {
                            }
                        }
                    case 0:
                    case 1:
                        return 0;
                    case 13:
                        if (i2 != 0) {
                            if (i2 == 1 || i2 == 14) {
                                return readVarUIntAsInt();
                            }
                            if (i2 != 15) {
                            }
                        }
                        return 0;
                    default:
                        throw new BlockedBuffer.BlockedBufferException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("invalid type id encountered: ", i));
                }
            } else if (i2 == 14) {
                return readVarUIntAsInt();
            }
            return i2;
        }

        public String readString(int i) throws IOException {
            char[] cArr = new char[i];
            int i2 = this._pos + i;
            int i3 = 0;
            while (true) {
                int i4 = this._pos;
                if (i4 >= i2) {
                    if (i4 >= i2) {
                        return new String(cArr, 0, i3);
                    }
                    throwUnexpectedEOFException();
                    throw null;
                }
                int unicodeScalar = readUnicodeScalar();
                if (unicodeScalar < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                if (unicodeScalar < 65536) {
                    cArr[i3] = (char) unicodeScalar;
                    i3++;
                } else {
                    int i5 = i3 + 1;
                    cArr[i3] = (char) _Private_IonConstants.makeHighSurrogate(unicodeScalar);
                    i3 += 2;
                    cArr[i5] = (char) _Private_IonConstants.makeLowSurrogate(unicodeScalar);
                }
            }
        }

        public Timestamp readTimestampValue(int i) throws IOException {
            Timestamp.Precision precision;
            Decimal decimalValue;
            int i2;
            int i3;
            int i4;
            int i5;
            if (i < 1) {
                return null;
            }
            int i6 = this._pos + i;
            Integer varIntWithNegativeZero = readVarIntWithNegativeZero();
            int varUIntAsInt = readVarUIntAsInt();
            Timestamp.Precision precision2 = Timestamp.Precision.YEAR;
            int i7 = 0;
            try {
                if (this._pos < i6) {
                    int varUIntAsInt2 = readVarUIntAsInt();
                    precision = Timestamp.Precision.MONTH;
                    if (this._pos < i6) {
                        int varUIntAsInt3 = readVarUIntAsInt();
                        Timestamp.Precision precision3 = Timestamp.Precision.DAY;
                        if (this._pos >= i6) {
                            i2 = varUIntAsInt3;
                            precision = precision3;
                            i7 = varUIntAsInt2;
                            decimalValue = null;
                            i3 = 0;
                            i4 = 0;
                            i5 = 0;
                            return Timestamp.createFromUtcFields(precision, varUIntAsInt, i7, i2, i3, i4, i5, decimalValue, varIntWithNegativeZero);
                        }
                        int varUIntAsInt4 = readVarUIntAsInt();
                        int varUIntAsInt5 = readVarUIntAsInt();
                        Timestamp.Precision precision4 = Timestamp.Precision.MINUTE;
                        if (this._pos >= i6) {
                            i2 = varUIntAsInt3;
                            precision = precision4;
                            i4 = varUIntAsInt5;
                            i3 = varUIntAsInt4;
                            i7 = varUIntAsInt2;
                            decimalValue = null;
                            i5 = 0;
                            return Timestamp.createFromUtcFields(precision, varUIntAsInt, i7, i2, i3, i4, i5, decimalValue, varIntWithNegativeZero);
                        }
                        int varUIntAsInt6 = readVarUIntAsInt();
                        Timestamp.Precision precision5 = Timestamp.Precision.SECOND;
                        int i8 = i6 - this._pos;
                        i2 = varUIntAsInt3;
                        precision = precision5;
                        i4 = varUIntAsInt5;
                        i3 = varUIntAsInt4;
                        decimalValue = i8 > 0 ? readDecimalValue(i8) : null;
                        i5 = varUIntAsInt6;
                        i7 = varUIntAsInt2;
                        return Timestamp.createFromUtcFields(precision, varUIntAsInt, i7, i2, i3, i4, i5, decimalValue, varIntWithNegativeZero);
                    }
                    i7 = varUIntAsInt2;
                } else {
                    precision = precision2;
                }
                return Timestamp.createFromUtcFields(precision, varUIntAsInt, i7, i2, i3, i4, i5, decimalValue, varIntWithNegativeZero);
            } catch (IllegalArgumentException e) {
                throw new IonException(e.getMessage() + " at: " + this._pos);
            }
            decimalValue = null;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }

        public int readToken() throws IOException, UnexpectedEofException {
            int i = read();
            if (i >= 0) {
                return i;
            }
            throwUnexpectedEOFException();
            throw null;
        }

        public BigInteger readUIntAsBigInteger(int i, int i2) throws IOException {
            byte[] bArr = new byte[i];
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = read();
                if (i4 < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                bArr[i3] = (byte) i4;
            }
            return new BigInteger(i2, bArr);
        }

        public int readUIntAsInt(int i) throws IOException {
            int i2 = 0;
            if (i == 0) {
                return 0;
            }
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            throw new IonException("overflow attempt to read long value into an int");
                        }
                        i2 = read();
                        if (i2 < 0) {
                            throwUnexpectedEOFException();
                            throw null;
                        }
                    }
                    int i3 = read();
                    if (i3 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    i2 = (i2 << 8) | i3;
                }
                int i4 = read();
                if (i4 < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                i2 = (i2 << 8) | i4;
            }
            int i5 = read();
            if (i5 >= 0) {
                return i5 | (i2 << 8);
            }
            throwUnexpectedEOFException();
            throw null;
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
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public long readUIntAsLong(int r7) throws java.io.IOException {
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
                java.lang.String r0 = "overflow attempt to read long value into an int"
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonBinary.Reader.readUIntAsLong(int):long");
        }

        public int readUnicodeScalar() throws IOException {
            int i = read();
            if (i < 0) {
                return -1;
            }
            if ((i & 128) == 0) {
                return i;
            }
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
                throw new IonException("illegal surrgate value encountered in input utf-8 stream");
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
            throw new IonException("illegal surrgate value encountered in input utf-8 stream");
        }

        public int readVarIntAsInt() throws IOException {
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
                            i2 = (i6 & 127) | (i2 << 7);
                            if ((i6 & 128) == 0) {
                                throw new IonException("var int overflow at: " + this._pos);
                            }
                        }
                    }
                }
            }
            return z ? -i2 : i2;
        }

        public long readVarIntAsLong() throws IOException {
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
                            throw new IonException("overflow attempt to read long value into a long");
                        }
                        j = (j << 7) | ((long) (i & 127));
                    } while ((i & 128) == 0);
                }
            }
            return z ? -j : j;
        }

        public Integer readVarIntWithNegativeZero() throws IOException {
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
                                throw new IonException("var int overflow at: " + this._pos);
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

        public int readVarUIntAsInt() throws IOException {
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
            throw new IonException("var int overflow at: " + this._pos);
        }

        public long readVarUIntAsLong() throws IOException {
            int i;
            long j = 0;
            do {
                i = read();
                if (i < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                if (((-144115188075855872L) & j) != 0) {
                    throw new IonException("overflow attempt to read long value into a long");
                }
                j = (j << 7) | ((long) (i & 127));
            } while ((i & 128) == 0);
            return j;
        }

        public boolean skipThroughNopPad() throws IOException {
            int i = this._pos;
            boolean z = _Private_IonConstants.getTypeCode(read()) == 14;
            setPosition(i);
            int actualTypeDesc = readActualTypeDesc();
            int i2 = actualTypeDesc >> 4;
            int i3 = actualTypeDesc & 15;
            if (i2 != 0 || i3 == 15) {
                setPosition(i);
                return false;
            }
            if (z) {
                throw new IonException("NOP padding is not allowed within annotation wrappers.");
            }
            int length = readLength(99, i3);
            long j = length;
            long jSkip = skip(j);
            if (length <= 0 || j == jSkip) {
                return true;
            }
            throw new IonException("Nop pad too short declared length: " + length + " pad actual size: " + jSkip);
        }

        public void throwUTF8Exception() {
            throw new IonException("Invalid UTF-8 character encounter in a string at pos " + this._pos);
        }

        public void throwUnexpectedEOFException() {
            throw new BlockedBuffer.BlockedBufferException("unexpected EOF in value at offset " + this._pos);
        }

        public Reader(BlockedBuffer blockedBuffer, int i) {
            super(i, blockedBuffer);
        }

        public String readString() throws IOException {
            int i = read();
            if (_Private_IonConstants.getTypeCode(i) != 8) {
                throw new IonException("readString helper only works for string(7) not " + ((i >> 4) & 15));
            }
            int varUIntAsInt = i & 15;
            if (varUIntAsInt == 15) {
                return null;
            }
            if (varUIntAsInt == 14) {
                varUIntAsInt = readVarUIntAsInt();
            }
            return readString(varUIntAsInt);
        }
    }

    public static boolean isNibbleZero(BigDecimal bigDecimal) {
        return !Decimal.isNegativeZero(bigDecimal) && bigDecimal.signum() == 0 && bigDecimal.scale() == 0;
    }

    public static int lenAnnotationListWithLen(String[] strArr, SymbolTable symbolTable) {
        if (strArr == null) {
            return 0;
        }
        int iLenVarUInt = 0;
        for (String str : strArr) {
            iLenVarUInt += lenVarUInt(symbolTable.findSymbol(str));
        }
        return lenVarUInt(iLenVarUInt) + iLenVarUInt;
    }

    public static int lenInt(long j) {
        if (j != 0) {
            return 0;
        }
        if (j < 0) {
            j = -j;
        }
        if (j < 128) {
            return 1;
        }
        if (j < 32768) {
            return 2;
        }
        if (j < WriteBuffer.INT24_SIGN_MASK) {
            return 3;
        }
        if (j < WriteBuffer.INT32_SIGN_MASK) {
            return 4;
        }
        if (j < WriteBuffer.INT40_SIGN_MASK) {
            return 5;
        }
        if (j < WriteBuffer.INT48_SIGN_MASK) {
            return 6;
        }
        if (j < 36028797018963968L) {
            return 7;
        }
        return j == Long.MIN_VALUE ? 9 : 8;
    }

    public static int lenIonBlobWithTypeDesc(byte[] bArr) {
        int iLenLenFieldWithOptionalNibble;
        if (bArr != null) {
            int length = bArr.length;
            iLenLenFieldWithOptionalNibble = lenLenFieldWithOptionalNibble(length) + length;
        } else {
            iLenLenFieldWithOptionalNibble = 0;
        }
        return iLenLenFieldWithOptionalNibble + 1;
    }

    public static int lenIonBooleanWithTypeDesc(Boolean bool) {
        return 1;
    }

    public static int lenIonClobWithTypeDesc(String str) {
        return lenIonStringWithTypeDesc(str);
    }

    public static int lenIonDecimal(BigDecimal bigDecimal) {
        if (bigDecimal == null || isNibbleZero(bigDecimal)) {
            return 0;
        }
        int iLenInt = lenInt(bigDecimal.unscaledValue(), Decimal.isNegativeZero(bigDecimal));
        int iLenVarInt = lenVarInt(bigDecimal.scale());
        if (iLenVarInt == 0) {
            iLenVarInt = 1;
        }
        return iLenVarInt + iLenInt;
    }

    public static int lenIonDecimalWithTypeDesc(BigDecimal bigDecimal) {
        int iLenLenFieldWithOptionalNibble;
        if (bigDecimal != null) {
            int iLenIonDecimal = lenIonDecimal(bigDecimal);
            iLenLenFieldWithOptionalNibble = lenLenFieldWithOptionalNibble(iLenIonDecimal) + iLenIonDecimal;
        } else {
            iLenLenFieldWithOptionalNibble = 0;
        }
        return iLenLenFieldWithOptionalNibble + 1;
    }

    public static int lenIonFloat(double d) {
        return Double.valueOf(d).equals(DOUBLE_POS_ZERO) ? 0 : 8;
    }

    public static int lenIonFloatWithTypeDesc(Double d) {
        int iLenLenFieldWithOptionalNibble;
        if (d != null) {
            int iLenIonFloat = lenIonFloat(d.doubleValue());
            iLenLenFieldWithOptionalNibble = lenLenFieldWithOptionalNibble(iLenIonFloat) + iLenIonFloat;
        } else {
            iLenLenFieldWithOptionalNibble = 0;
        }
        return iLenLenFieldWithOptionalNibble + 1;
    }

    public static int lenIonInt(long j) {
        if (j < 0) {
            if (j == Long.MIN_VALUE) {
                return 8;
            }
            return lenUInt(-j);
        }
        if (j > 0) {
            return lenUInt(j);
        }
        return 0;
    }

    public static int lenIonIntWithTypeDesc(Long l) {
        int iLenLenFieldWithOptionalNibble;
        if (l != null) {
            int iLenIonInt = lenIonInt(l.longValue());
            iLenLenFieldWithOptionalNibble = lenLenFieldWithOptionalNibble(iLenIonInt) + iLenIonInt;
        } else {
            iLenLenFieldWithOptionalNibble = 0;
        }
        return iLenLenFieldWithOptionalNibble + 1;
    }

    public static int lenIonNullWithTypeDesc() {
        return 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0013 A[PHI: r0
      0x0013: PHI (r0v12 int) = (r0v1 int), (r0v2 int) binds: [B:9:0x0011, B:31:0x009c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int lenIonString(java.lang.String r8) {
        /*
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            r1 = 0
        L5:
            int r2 = r8.length()
            if (r0 >= r2) goto Lc1
            char r2 = r8.charAt(r0)
            r3 = 128(0x80, float:1.8E-43)
            if (r2 >= r3) goto L17
        L13:
            int r1 = r1 + 1
            goto Lb5
        L17:
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r4) goto L9c
            r4 = 57343(0xdfff, float:8.0355E-41)
            if (r2 > r4) goto L9c
            boolean r4 = com.amazon.ion.impl._Private_IonConstants.isHighSurrogate(r2)
            java.lang.String r5 = " at index "
            if (r4 == 0) goto L77
            int r4 = r0 + 1
            int r6 = r8.length()
            if (r4 >= r6) goto L5f
            char r6 = r8.charAt(r4)
            boolean r7 = com.amazon.ion.impl._Private_IonConstants.isLowSurrogate(r6)
            if (r7 == 0) goto L41
            int r2 = com.amazon.ion.impl._Private_IonConstants.makeUnicodeScalar(r2, r6)
            r0 = r4
            goto L9c
        L41:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r1 = "Text contains unmatched UTF-16 high surrogate "
            r8.<init>(r1)
            java.lang.String r1 = com.amazon.ion.util.IonTextUtils.printCodePointAsString(r2)
            r8.append(r1)
            r8.append(r5)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r8)
            throw r0
        L5f:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Text ends with unmatched UTF-16 surrogate "
            r8.<init>(r0)
            java.lang.String r0 = com.amazon.ion.util.IonTextUtils.printCodePointAsString(r2)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r8)
            throw r0
        L77:
            boolean r4 = com.amazon.ion.impl._Private_IonConstants.isLowSurrogate(r2)
            if (r4 != 0) goto L7e
            goto L9c
        L7e:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r1 = "Text contains unmatched UTF-16 low surrogate "
            r8.<init>(r1)
            java.lang.String r1 = com.amazon.ion.util.IonTextUtils.printCodePointAsString(r2)
            r8.append(r1)
            r8.append(r5)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r8)
            throw r0
        L9c:
            if (r2 >= r3) goto La0
            goto L13
        La0:
            r3 = 2048(0x800, float:2.87E-42)
            if (r2 >= r3) goto La7
            int r1 = r1 + 2
            goto Lb5
        La7:
            r3 = 65536(0x10000, float:9.1835E-41)
            if (r2 >= r3) goto Lae
            int r1 = r1 + 3
            goto Lb5
        Lae:
            r3 = 1114111(0x10ffff, float:1.561202E-39)
            if (r2 > r3) goto Lb9
            int r1 = r1 + 4
        Lb5:
            int r0 = r0 + 1
            goto L5
        Lb9:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "invalid string, illegal Unicode scalar (character) encountered"
            r8.<init>(r0)
            throw r8
        Lc1:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonBinary.lenIonString(java.lang.String):int");
    }

    public static int lenIonStringWithTypeDesc(String str) {
        int iLenLenFieldWithOptionalNibble;
        if (str != null) {
            int iLenIonString = lenIonString(str);
            if (iLenIonString < 0) {
                return -1;
            }
            iLenLenFieldWithOptionalNibble = lenLenFieldWithOptionalNibble(iLenIonString) + iLenIonString;
        } else {
            iLenLenFieldWithOptionalNibble = 0;
        }
        return iLenLenFieldWithOptionalNibble + 1;
    }

    public static int lenIonTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return 0;
        }
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[timestamp._precision.ordinal()]) {
            case 1:
            case 2:
                BigDecimal bigDecimal = timestamp._fraction;
                iLenVarUInt = (bigDecimal != null ? lenIonDecimal(bigDecimal) : 0) + 1;
            case 3:
                iLenVarUInt += 2;
            case 4:
                iLenVarUInt++;
            case 5:
                iLenVarUInt++;
            case 6:
                iLenVarUInt += lenVarUInt(timestamp._year);
                break;
        }
        Integer num = timestamp._offset;
        return num == null ? iLenVarUInt + 1 : num.intValue() == 0 ? iLenVarUInt + 1 : lenVarInt(num.longValue()) + iLenVarUInt;
    }

    public static int lenIonTimestampWithTypeDesc(Timestamp timestamp) {
        int iLenLenFieldWithOptionalNibble;
        if (timestamp != null) {
            int iLenIonTimestamp = lenIonTimestamp(timestamp);
            iLenLenFieldWithOptionalNibble = lenLenFieldWithOptionalNibble(iLenIonTimestamp) + iLenIonTimestamp;
        } else {
            iLenLenFieldWithOptionalNibble = 0;
        }
        return iLenLenFieldWithOptionalNibble + 1;
    }

    public static int lenLenFieldWithOptionalNibble(int i) {
        if (i < 14) {
            return 0;
        }
        return lenVarUInt(i);
    }

    public static int lenTypeDescWithAppropriateLenField(int i, int i2) {
        switch (i) {
            case 0:
            case 1:
                return 1;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                if (i2 < 14) {
                    return 1;
                }
                return lenVarUInt(i2) + 1;
            default:
                throw new IonException("invalid type");
        }
    }

    public static int lenUInt(long j) {
        if (j == 0) {
            return 0;
        }
        if (j < 0) {
            throw new BlockedBuffer.BlockedBufferException("fatal signed long where unsigned was promised");
        }
        if (j < 256) {
            return 1;
        }
        if (j < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            return 2;
        }
        if (j < 16777216) {
            return 3;
        }
        if (j < 4294967296L) {
            return 4;
        }
        if (j < 1099511627776L) {
            return 5;
        }
        if (j < WriteBuffer.VAR_INT_8_OCTET_MIN_VALUE) {
            return 6;
        }
        return j < WriteBuffer.VAR_UINT_9_OCTET_MIN_VALUE ? 7 : 8;
    }

    public static int lenVarInt(long j) {
        if (j == 0) {
            return 0;
        }
        if (j < 0) {
            j = -j;
        }
        if (j < 64) {
            return 1;
        }
        if (j < 8192) {
            return 2;
        }
        if (j < 1048576) {
            return 3;
        }
        if (j < WriteBuffer.VAR_INT_5_OCTET_MIN_VALUE) {
            return 4;
        }
        if (j < 17179869184L) {
            return 5;
        }
        if (j < WriteBuffer.VAR_INT_7_OCTET_MIN_VALUE) {
            return 6;
        }
        if (j < WriteBuffer.VAR_INT_8_OCTET_MIN_VALUE) {
            return 7;
        }
        if (j < 36028797018963968L) {
            return 8;
        }
        return j < 4611686018427387904L ? 9 : 10;
    }

    public static int lenVarUInt(long j) {
        if (j < 128) {
            return 1;
        }
        if (j < 16384) {
            return 2;
        }
        if (j < 2097152) {
            return 3;
        }
        if (j < WriteBuffer.VAR_UINT_5_OCTET_MIN_VALUE) {
            return 4;
        }
        if (j < WriteBuffer.VAR_UINT_6_OCTET_MIN_VALUE) {
            return 5;
        }
        if (j < WriteBuffer.VAR_UINT_7_OCTET_MIN_VALUE) {
            return 6;
        }
        if (j < WriteBuffer.VAR_UINT_8_OCTET_MIN_VALUE) {
            return 7;
        }
        if (j < WriteBuffer.VAR_UINT_9_OCTET_MIN_VALUE) {
            return 8;
        }
        return j < Long.MIN_VALUE ? 9 : 10;
    }

    public static final int makeUTF8IntFromScalar(int i) throws IOException {
        int i2;
        int i3;
        if (i < 128) {
            return i & 255;
        }
        if (i < 2048) {
            i2 = ((i >> 6) | 192) & 255;
            i3 = (((i & 63) | 128) & 255) << 8;
        } else if (i < 65536) {
            if (i > 55295 && i < 57344) {
                throwUTF8Exception();
                throw null;
            }
            i2 = (((i >> 12) | 224) & 255) | (((((i >> 6) & 63) | 128) & 255) << 8);
            i3 = (((i & 63) | 128) & 255) << 16;
        } else {
            if (i > 1114111) {
                throwUTF8Exception();
                throw null;
            }
            i2 = (((i >> 18) | 240) & 255) | (((((i >> 12) & 63) | 128) & 255) << 8) | (((((i >> 6) & 63) | 128) & 255) << 16);
            i3 = (((i & 63) | 128) & 255) << 24;
        }
        return i3 | i2;
    }

    public static void readAll(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int i3 = inputStream.read(bArr, i, i2);
            if (i3 <= 0) {
                if (!(inputStream instanceof Reader)) {
                    throw new IonException("Unexpected EOF");
                }
                ((Reader) inputStream).throwUnexpectedEOFException();
                throw null;
            }
            i2 -= i3;
            i += i3;
        }
    }

    public static void throwUTF8Exception() throws IOException {
        throw new IOException("Invalid UTF-8 character encountered");
    }

    public static BigInteger unsignedLongToBigInteger(int i, long j) {
        return new BigInteger(i, new byte[]{(byte) ((j >> 56) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) (j & 255)});
    }

    public static void verifyBinaryVersionMarker(Reader reader) throws IonException {
        try {
            int i = reader._pos;
            int i2 = _Private_IonConstants.BINARY_VERSION_MARKER_SIZE;
            byte[] bArr = new byte[i2];
            int fully = _Private_Utils.readFully(reader, bArr);
            if (fully < i2) {
                throw new IonException("Binary data is too short: at least " + i2 + " bytes are required, but only " + fully + " were found.");
            }
            if (IonStreamUtils.isIonBinary(bArr)) {
                reader.setPosition(i);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Binary data has unrecognized header");
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = bArr[i3] & 255;
                sb.append(" 0x");
                sb.append(Integer.toHexString(i4).toUpperCase());
            }
            throw new IonException(sb.toString());
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public static int writeIonInt(OutputStream outputStream, long j, int i) throws IOException {
        if (j == 0) {
            return i;
        }
        if (j < 0) {
            j = -j;
        }
        switch (i) {
            case 8:
                outputStream.write((byte) ((j >> 56) & 255));
            case 7:
                outputStream.write((byte) ((j >> 48) & 255));
            case 6:
                outputStream.write((byte) ((j >> 40) & 255));
            case 5:
                outputStream.write((byte) ((j >> 32) & 255));
            case 4:
                outputStream.write((byte) ((j >> 24) & 255));
            case 3:
                outputStream.write((byte) ((j >> 16) & 255));
            case 2:
                outputStream.write((byte) ((j >> 8) & 255));
            case 1:
                outputStream.write((byte) (j & 255));
                break;
        }
        return i;
    }

    public static int writeString(OutputStream outputStream, String str) throws IOException {
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int iCharAt = str.charAt(i);
            if (iCharAt < 128) {
                outputStream.write((byte) iCharAt);
                i2++;
            } else {
                if (iCharAt >= 55296 && iCharAt <= 57343) {
                    if (_Private_IonConstants.isHighSurrogate(iCharAt)) {
                        i++;
                        if (i >= str.length()) {
                            throw new IllegalArgumentException("invalid string, unpaired high surrogate character");
                        }
                        char cCharAt = str.charAt(i);
                        if (!_Private_IonConstants.isLowSurrogate(cCharAt)) {
                            throw new IllegalArgumentException("invalid string, unpaired high surrogate character");
                        }
                        iCharAt = _Private_IonConstants.makeUnicodeScalar(iCharAt, cCharAt);
                    } else if (_Private_IonConstants.isLowSurrogate(iCharAt)) {
                        throw new IllegalArgumentException("invalid string, unpaired low surrogate character");
                    }
                }
                int iMakeUTF8IntFromScalar = makeUTF8IntFromScalar(iCharAt);
                while ((iMakeUTF8IntFromScalar & (-256)) != 0) {
                    outputStream.write((byte) (iMakeUTF8IntFromScalar & 255));
                    iMakeUTF8IntFromScalar >>>= 8;
                    i2++;
                }
            }
            i++;
        }
        return i2;
    }

    public static int writeTypeDescWithLength(OutputStream outputStream, int i, int i2, int i3) throws IOException {
        int i4 = (i & 15) << 4;
        if (i3 < 14) {
            outputStream.write((byte) ((i4 | (i3 & 15)) & 255));
            return 1;
        }
        outputStream.write((byte) ((i4 | 14) & 255));
        writeVarUInt(outputStream, i3, i2, true);
        return i2 + 1;
    }

    public static int writeVarUInt(OutputStream outputStream, long j) throws IOException {
        int iLenVarUInt = lenVarUInt(j);
        writeVarUInt(outputStream, j, iLenVarUInt, false);
        return iLenVarUInt;
    }

    public static int lenInt(BigInteger bigInteger, boolean z) {
        int iBitLength = bigInteger.abs().bitLength();
        int iSignum = bigInteger.signum();
        if (iSignum != -1) {
            if (iSignum == 0) {
                return z ? 1 : 0;
            }
            if (iSignum != 1) {
                return 0;
            }
        }
        return (iBitLength / 8) + 1;
    }

    public static int lenIonInt(BigInteger bigInteger) {
        if (bigInteger.signum() < 0) {
            bigInteger = bigInteger.negate();
        }
        return lenUInt(bigInteger);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int writeVarUInt(OutputStream outputStream, long j, int i, boolean z) throws IOException {
        switch (i - 1) {
            case -1:
                if (z) {
                    outputStream.write(-128);
                }
                break;
            case 0:
                outputStream.write((byte) ((j & ((long) 127)) | 128));
                break;
            case 1:
                outputStream.write((byte) ((j >> 7) & ((long) 127)));
                outputStream.write((byte) ((j & ((long) 127)) | 128));
                break;
            case 2:
                outputStream.write((byte) ((j >> 14) & ((long) 127)));
                outputStream.write((byte) ((j >> 7) & ((long) 127)));
                outputStream.write((byte) ((j & ((long) 127)) | 128));
                break;
            case 3:
                outputStream.write((byte) ((j >> 21) & ((long) 127)));
                outputStream.write((byte) ((j >> 14) & ((long) 127)));
                outputStream.write((byte) ((j >> 7) & ((long) 127)));
                outputStream.write((byte) ((j & ((long) 127)) | 128));
                break;
            case 4:
                outputStream.write((byte) ((j >> 28) & ((long) 127)));
                outputStream.write((byte) ((j >> 21) & ((long) 127)));
                outputStream.write((byte) ((j >> 14) & ((long) 127)));
                outputStream.write((byte) ((j >> 7) & ((long) 127)));
                outputStream.write((byte) ((j & ((long) 127)) | 128));
                break;
            case 5:
                outputStream.write((byte) ((j >> 35) & ((long) 127)));
                outputStream.write((byte) ((j >> 28) & ((long) 127)));
                outputStream.write((byte) ((j >> 21) & ((long) 127)));
                outputStream.write((byte) ((j >> 14) & ((long) 127)));
                outputStream.write((byte) ((j >> 7) & ((long) 127)));
                outputStream.write((byte) ((j & ((long) 127)) | 128));
                break;
            case 6:
                outputStream.write((byte) ((j >> 42) & ((long) 127)));
                outputStream.write((byte) ((j >> 35) & ((long) 127)));
                outputStream.write((byte) ((j >> 28) & ((long) 127)));
                outputStream.write((byte) ((j >> 21) & ((long) 127)));
                outputStream.write((byte) ((j >> 14) & ((long) 127)));
                outputStream.write((byte) ((j >> 7) & ((long) 127)));
                outputStream.write((byte) ((j & ((long) 127)) | 128));
                break;
            case 7:
                outputStream.write((byte) ((j >> 49) & ((long) 127)));
                outputStream.write((byte) ((j >> 42) & ((long) 127)));
                outputStream.write((byte) ((j >> 35) & ((long) 127)));
                outputStream.write((byte) ((j >> 28) & ((long) 127)));
                outputStream.write((byte) ((j >> 21) & ((long) 127)));
                outputStream.write((byte) ((j >> 14) & ((long) 127)));
                outputStream.write((byte) ((j >> 7) & ((long) 127)));
                outputStream.write((byte) ((j & ((long) 127)) | 128));
                break;
            case 8:
                outputStream.write((byte) ((j >> 56) & ((long) 127)));
                outputStream.write((byte) ((j >> 49) & ((long) 127)));
                outputStream.write((byte) ((j >> 42) & ((long) 127)));
                outputStream.write((byte) ((j >> 35) & ((long) 127)));
                outputStream.write((byte) ((j >> 28) & ((long) 127)));
                outputStream.write((byte) ((j >> 21) & ((long) 127)));
                outputStream.write((byte) ((j >> 14) & ((long) 127)));
                outputStream.write((byte) ((j >> 7) & ((long) 127)));
                outputStream.write((byte) ((j & ((long) 127)) | 128));
                break;
            case 9:
                outputStream.write((byte) ((j >> 63) & ((long) 127)));
                outputStream.write((byte) ((j >> 56) & ((long) 127)));
                outputStream.write((byte) ((j >> 49) & ((long) 127)));
                outputStream.write((byte) ((j >> 42) & ((long) 127)));
                outputStream.write((byte) ((j >> 35) & ((long) 127)));
                outputStream.write((byte) ((j >> 28) & ((long) 127)));
                outputStream.write((byte) ((j >> 21) & ((long) 127)));
                outputStream.write((byte) ((j >> 14) & ((long) 127)));
                outputStream.write((byte) ((j >> 7) & ((long) 127)));
                outputStream.write((byte) ((j & ((long) 127)) | 128));
                break;
        }
        return i;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Writer extends BlockedBuffer.BlockedByteOutputStream {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final byte[] negativeZeroBitArray = {-128};
        public static final byte[] positiveZeroBitArray = _Private_Utils.EMPTY_BYTE_ARRAY;
        public static final int stringBufferLen = 128;
        public int _pending_high_surrogate;
        public Stack<Integer> _pending_high_surrogate_stack;
        public Stack<PositionMarker> _pos_stack;
        public byte[] numberBuffer;
        public byte[] singleCodepointUtf8Buffer;
        public byte[] stringBuffer;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class lhNode {
            public int _hn;
            public boolean _length_follows;
            public int _lownibble;

            public lhNode(int i, int i2, boolean z) {
                this._hn = i;
                this._lownibble = i2;
                this._length_follows = z;
            }
        }

        public Writer() {
            this.numberBuffer = new byte[10];
            this.singleCodepointUtf8Buffer = new byte[4];
            this.stringBuffer = new byte[128];
        }

        public final int _writeUnicodeScalarToByteBuffer(int i, byte[] bArr, int i2) throws IOException {
            if (i < 2048) {
                bArr[i2] = (byte) (((i >> 6) | 192) & 255);
                bArr[i2 + 1] = (byte) (((i & 63) | 128) & 255);
                return 2;
            }
            if (i < 65536) {
                if (i > 55295 && i < 57344) {
                    throwUTF8Exception();
                    throw null;
                }
                bArr[i2] = (byte) (((i >> 12) | 224) & 255);
                bArr[i2 + 1] = (byte) ((((i >> 6) & 63) | 128) & 255);
                bArr[i2 + 2] = (byte) (((i & 63) | 128) & 255);
                return 3;
            }
            if (i > 1114111) {
                throwUTF8Exception();
                throw null;
            }
            bArr[i2] = (byte) (((i >> 18) | 240) & 255);
            bArr[i2 + 1] = (byte) ((((i >> 12) & 63) | 128) & 255);
            bArr[i2 + 2] = (byte) ((((i >> 6) & 63) | 128) & 255);
            bArr[i2 + 3] = (byte) (((i & 63) | 128) & 255);
            return 4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:37:0x0075, code lost:
        
            if (com.amazon.ion.impl.IonBinary.debugValidation == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0077, code lost:
        
            _validate();
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x007a, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:?, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void appendToLongValue(java.lang.CharSequence r9, boolean r10) throws java.io.IOException {
            /*
                r8 = this;
                boolean r0 = com.amazon.ion.impl.IonBinary.debugValidation
                if (r0 == 0) goto L7
                r8._validate()
            L7:
                int r0 = r9.length()
                r1 = 0
                r2 = 0
            Ld:
                if (r2 >= r0) goto L73
                char r3 = r9.charAt(r2)
                if (r10 == 0) goto L28
                r4 = 255(0xff, float:3.57E-43)
                if (r3 > r4) goto L20
                r3 = r3 & 255(0xff, float:3.57E-43)
                byte r3 = (byte) r3
                r8.write(r3)
                goto L68
            L20:
                com.amazon.ion.IonException r9 = new com.amazon.ion.IonException
                java.lang.String r10 = "escaped character value too large in clob (0 to 255 only)"
                r9.<init>(r10)
                throw r9
            L28:
                int r4 = r8._pending_high_surrogate
                java.lang.String r5 = "unmatched high surrogate character encountered, invalid utf-16"
                r6 = 56320(0xdc00, float:7.8921E-41)
                if (r4 == 0) goto L42
                r7 = r3 & (-1024(0xfffffffffffffc00, float:NaN))
                if (r7 != r6) goto L3c
                int r3 = com.amazon.ion.impl._Private_IonConstants.makeUnicodeScalar(r4, r3)
                r8._pending_high_surrogate = r1
                goto L65
            L3c:
                com.amazon.ion.IonException r9 = new com.amazon.ion.IonException
                r9.<init>(r5)
                throw r9
            L42:
                r4 = r3 & (-1024(0xfffffffffffffc00, float:NaN))
                r7 = 55296(0xd800, float:7.7486E-41)
                if (r4 != r7) goto L63
                int r2 = r2 + 1
                if (r2 < r0) goto L50
                r8._pending_high_surrogate = r3
                goto L73
            L50:
                char r4 = r9.charAt(r2)
                r7 = r4 & (-1024(0xfffffffffffffc00, float:NaN))
                if (r7 != r6) goto L5d
                int r3 = com.amazon.ion.impl._Private_IonConstants.makeUnicodeScalar(r3, r4)
                goto L65
            L5d:
                com.amazon.ion.IonException r9 = new com.amazon.ion.IonException
                r9.<init>(r5)
                throw r9
            L63:
                if (r4 == r6) goto L6b
            L65:
                r8.writeUnicodeScalarAsUTF8(r3)
            L68:
                int r2 = r2 + 1
                goto Ld
            L6b:
                com.amazon.ion.IonException r9 = new com.amazon.ion.IonException
                java.lang.String r10 = "unmatched low surrogate character encountered, invalid utf-16"
                r9.<init>(r10)
                throw r9
            L73:
                boolean r9 = com.amazon.ion.impl.IonBinary.debugValidation
                if (r9 == 0) goto L7a
                r8._validate()
            L7a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonBinary.Writer.appendToLongValue(java.lang.CharSequence, boolean):void");
        }

        public final boolean isLongTerminator(int i, PushbackReader pushbackReader) throws IOException {
            int i2 = pushbackReader.read();
            if (i2 != i) {
                pushbackReader.unread(i2);
                return false;
            }
            int i3 = pushbackReader.read();
            if (i3 == i) {
                return true;
            }
            pushbackReader.unread(i3);
            pushbackReader.unread(i);
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void patchLongHeader(int r11, int r12) throws java.io.IOException {
            /*
                r10 = this;
                int r0 = r10._pos
                boolean r1 = com.amazon.ion.impl.IonBinary.debugValidation
                if (r1 == 0) goto L9
                r10._validate()
            L9:
                com.amazon.ion.impl.IonBinary$PositionMarker r1 = r10.popPosition()
                java.lang.Object r2 = r1.getUserData()
                com.amazon.ion.impl.IonBinary$Writer$lhNode r2 = (com.amazon.ion.impl.IonBinary.Writer.lhNode) r2
                int r3 = r1.getPosition()
                int r3 = r0 - r3
                r4 = -1
                if (r12 != r4) goto L1e
                int r12 = r2._lownibble
            L1e:
                r4 = 1
                int r3 = r3 - r4
                long r5 = (long) r3
                int r7 = com.amazon.ion.impl.IonBinary.lenVarUInt(r5)
                boolean r2 = r2._length_follows
                r8 = 0
                r9 = 14
                if (r2 == 0) goto L32
                if (r12 != r4) goto L2f
                goto L39
            L2f:
                if (r3 >= r9) goto L37
                goto L34
            L32:
                if (r3 >= r9) goto L37
            L34:
                r12 = r3
                r7 = 0
                goto L39
            L37:
                r12 = 14
            L39:
                int r1 = r1.getPosition()
                r10.setPosition(r1)
                if (r7 <= 0) goto L45
                r10.insert(r7)
            L45:
                int r11 = r11 << 4
                r11 = r11 | r12
                r10.write(r11)
                if (r7 <= 0) goto L50
                r10.writeVarUIntValue(r5, r4)
            L50:
                if (r7 >= 0) goto L56
                int r11 = -r7
                r10.remove(r11)
            L56:
                int r0 = r0 + r7
                r10.setPosition(r0)
                boolean r11 = com.amazon.ion.impl.IonBinary.debugValidation
                if (r11 == 0) goto L61
                r10._validate()
            L61:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonBinary.Writer.patchLongHeader(int, int):void");
        }

        public PositionMarker popPosition() {
            if (this._pending_high_surrogate != 0) {
                throw new IonException("unmatched high surrogate encountered in input, illegal utf-16 character sequence");
            }
            PositionMarker positionMarkerPop = this._pos_stack.pop();
            this._pending_high_surrogate = this._pending_high_surrogate_stack.pop().intValue();
            return positionMarkerPop;
        }

        public void pushLongHeader(int i, int i2, boolean z) {
            pushPosition(new lhNode(i, i2, z));
        }

        public void pushPosition(Object obj) {
            PositionMarker positionMarker = new PositionMarker(this._pos, obj);
            if (this._pos_stack == null) {
                this._pos_stack = new Stack<>();
                this._pending_high_surrogate_stack = new Stack<>();
            }
            this._pos_stack.push(positionMarker);
            this._pending_high_surrogate_stack.push(Integer.valueOf(this._pending_high_surrogate));
            this._pending_high_surrogate = 0;
        }

        public void startLongWrite(int i) throws IOException {
            if (IonBinary.debugValidation) {
                _validate();
            }
            pushLongHeader(i, 0, false);
            writeCommonHeader(i, 0);
            if (IonBinary.debugValidation) {
                _validate();
            }
        }

        public void throwException(String str) {
            throw new BlockedBuffer.BlockedBufferException(str);
        }

        public void throwUTF8Exception() {
            throwException("Invalid UTF-8 character encounter in a string at pos " + this._pos);
            throw null;
        }

        public int writeAnnotations(ArrayList<Integer> arrayList) throws IOException {
            int i = this._pos;
            int size = arrayList.size();
            int i2 = 0;
            int iLenVarUInt = 0;
            int i3 = 0;
            while (i3 < size) {
                Integer num = arrayList.get(i3);
                i3++;
                iLenVarUInt += IonBinary.lenVarUInt(num.intValue());
            }
            writeVarUIntValue(iLenVarUInt, true);
            int size2 = arrayList.size();
            while (i2 < size2) {
                Integer num2 = arrayList.get(i2);
                i2++;
                writeVarUIntValue(num2.intValue(), true);
            }
            return this._pos - i;
        }

        public int writeByte(_Private_IonConstants.HighNibble highNibble, int i) throws IOException {
            if (i < 0) {
                throw new IonException("negative token length encountered");
            }
            if (i > 13) {
                i = 14;
            }
            write(_Private_IonConstants.makeTypeDescriptor(highNibble._value, i));
            return 1;
        }

        public int writeCommonHeader(int i, int i2) throws IOException {
            if (i2 < 14) {
                write(_Private_IonConstants.makeTypeDescriptor(i, i2));
                return 1;
            }
            write(_Private_IonConstants.makeTypeDescriptor(i, 14));
            return writeVarUIntValue(i2, false) + 1;
        }

        public int writeDecimalContent(BigDecimal bigDecimal) throws IOException {
            byte[] byteArray;
            if (bigDecimal == null || IonBinary.isNibbleZero(bigDecimal)) {
                return 0;
            }
            int iWriteVarIntValue = writeVarIntValue(-bigDecimal.scale(), true);
            BigInteger bigIntegerUnscaledValue = bigDecimal.unscaledValue();
            int iSignum = bigIntegerUnscaledValue.signum();
            if (iSignum == -1) {
                byteArray = bigIntegerUnscaledValue.negate().toByteArray();
                byteArray[0] = (byte) (byteArray[0] | 128);
            } else if (iSignum == 0) {
                byteArray = Decimal.isNegativeZero(bigDecimal) ? negativeZeroBitArray : positiveZeroBitArray;
            } else {
                if (iSignum != 1) {
                    throw new IllegalStateException("mantissa signum out of range");
                }
                byteArray = bigIntegerUnscaledValue.toByteArray();
            }
            write(byteArray, 0, byteArray.length);
            return iWriteVarIntValue + byteArray.length;
        }

        public int writeDecimalWithTD(BigDecimal bigDecimal) throws IOException {
            if (bigDecimal == null) {
                write(IonBinary.NULL_DECIMAL_TYPEDESC);
                return 1;
            }
            if (IonBinary.isNibbleZero(bigDecimal)) {
                write(IonBinary.ZERO_DECIMAL_TYPEDESC);
                return 1;
            }
            int iLenIonDecimal = IonBinary.lenIonDecimal(bigDecimal);
            if (iLenIonDecimal < 14) {
                write(iLenIonDecimal | 80);
            } else {
                write(94);
                writeVarIntValue(iLenIonDecimal, false);
            }
            return writeDecimalContent(bigDecimal) + 1;
        }

        public int writeFloatValue(double d) throws IOException {
            if (Double.valueOf(d).equals(IonBinary.DOUBLE_POS_ZERO)) {
                return 0;
            }
            writeUIntValue(Double.doubleToRawLongBits(d), 8);
            return 8;
        }

        public int writeIntValue(long j) throws IOException {
            int length = this.numberBuffer.length;
            boolean z = j < 0;
            if (z) {
                j = -j;
            }
            while (j > 0) {
                length--;
                this.numberBuffer[length] = (byte) (255 & j);
                j >>>= 8;
            }
            byte[] bArr = this.numberBuffer;
            if ((bArr[length] & 128) == 64) {
                length--;
                bArr[length] = 0;
            }
            if (z) {
                bArr[length] = (byte) (bArr[length] | 128);
            }
            int length2 = bArr.length - length;
            write(bArr, length, length2);
            return length2;
        }

        public int writeNullWithTD(_Private_IonConstants.HighNibble highNibble) throws IOException {
            writeByte(highNibble, 15);
            return 1;
        }

        public int writeStringData(String str) throws IOException {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < str.length()) {
                int iCharAt = str.charAt(i);
                if (i2 > 124) {
                    write(this.stringBuffer, 0, i2);
                    i2 = 0;
                }
                if (iCharAt < 128) {
                    this.stringBuffer[i2] = (byte) iCharAt;
                    i3++;
                    i2++;
                } else {
                    if (iCharAt >= 55296 && iCharAt <= 57343) {
                        if (_Private_IonConstants.isHighSurrogate(iCharAt)) {
                            i++;
                            if (i >= str.length()) {
                                throw new IllegalArgumentException("invalid string, unpaired high surrogate character");
                            }
                            char cCharAt = str.charAt(i);
                            if (!_Private_IonConstants.isLowSurrogate(cCharAt)) {
                                throw new IllegalArgumentException("invalid string, unpaired high surrogate character");
                            }
                            iCharAt = _Private_IonConstants.makeUnicodeScalar(iCharAt, cCharAt);
                        } else if (_Private_IonConstants.isLowSurrogate(iCharAt)) {
                            throw new IllegalArgumentException("invalid string, unpaired low surrogate character");
                        }
                    }
                    int i_writeUnicodeScalarToByteBuffer = _writeUnicodeScalarToByteBuffer(iCharAt, this.stringBuffer, i2);
                    i2 += i_writeUnicodeScalarToByteBuffer;
                    i3 += i_writeUnicodeScalarToByteBuffer;
                }
                i++;
            }
            if (i2 > 0) {
                write(this.stringBuffer, 0, i2);
            }
            return i3;
        }

        public int writeStringWithTD(String str) throws IOException {
            int iLenIonString = IonBinary.lenIonString(str);
            if (iLenIonString < 0) {
                throwUTF8Exception();
                throw null;
            }
            int iWriteCommonHeader = writeCommonHeader(8, iLenIonString) + iLenIonString;
            writeStringData(str);
            return iWriteCommonHeader;
        }

        public void writeStubStructHeader(int i, int i2) throws IOException {
            write(_Private_IonConstants.makeTypeDescriptor(i, i2));
        }

        public int writeSymbolWithTD(int i) throws IOException {
            long j = i;
            int iLenUInt = IonBinary.lenUInt(j);
            int iWriteCommonHeader = writeCommonHeader(7, iLenUInt);
            writeUIntValue(j, iLenUInt);
            return iWriteCommonHeader + iLenUInt;
        }

        public int writeTimestamp(Timestamp timestamp) throws IOException {
            int iWriteVarIntValue;
            if (timestamp == null) {
                return 0;
            }
            Timestamp.Precision precision = timestamp._precision;
            if (timestamp._offset == null) {
                write(-64);
                iWriteVarIntValue = 1;
            } else {
                iWriteVarIntValue = writeVarIntValue(r1.intValue(), true);
            }
            if (precision.includes(Timestamp.Precision.YEAR)) {
                iWriteVarIntValue += writeVarUIntValue(timestamp._year, true);
            }
            if (precision.includes(Timestamp.Precision.MONTH)) {
                iWriteVarIntValue += writeVarUIntValue(timestamp._month, true);
            }
            if (precision.includes(Timestamp.Precision.DAY)) {
                iWriteVarIntValue += writeVarUIntValue(timestamp._day, true);
            }
            if (precision.includes(Timestamp.Precision.MINUTE)) {
                iWriteVarIntValue = writeVarUIntValue(timestamp._minute, true) + writeVarUIntValue(timestamp._hour, true) + iWriteVarIntValue;
            }
            if (!precision.includes(Timestamp.Precision.SECOND)) {
                return iWriteVarIntValue;
            }
            int iWriteVarUIntValue = writeVarUIntValue(timestamp._second, true) + iWriteVarIntValue;
            BigDecimal bigDecimal = timestamp._fraction;
            return bigDecimal != null ? writeDecimalContent(bigDecimal) + iWriteVarUIntValue : iWriteVarUIntValue;
        }

        public int writeTimestampWithTD(Timestamp timestamp) throws IOException {
            if (timestamp == null) {
                return writeCommonHeader(6, 15);
            }
            return writeTimestamp(timestamp) + writeCommonHeader(6, IonBinary.lenIonTimestamp(timestamp));
        }

        public int writeUIntValue(long j) throws IOException {
            int length = this.numberBuffer.length;
            while (j != 0) {
                length--;
                this.numberBuffer[length] = (byte) (255 & j);
                j >>>= 8;
            }
            byte[] bArr = this.numberBuffer;
            int length2 = bArr.length - length;
            write(bArr, length, length2);
            return length2;
        }

        public int writeUnicodeScalarAsUTF8(int i) throws IOException {
            if (i >= 128) {
                int i_writeUnicodeScalarToByteBuffer = _writeUnicodeScalarToByteBuffer(i, this.singleCodepointUtf8Buffer, 0);
                write(this.singleCodepointUtf8Buffer, 0, i_writeUnicodeScalarToByteBuffer);
                return i_writeUnicodeScalarToByteBuffer;
            }
            start_write();
            _write((byte) (i & 255));
            end_write();
            return 1;
        }

        public int writeVarIntValue(long j, boolean z) throws IOException {
            if (j == 0) {
                if (!z) {
                    return 0;
                }
                write(-128);
                return 1;
            }
            int length = this.numberBuffer.length;
            boolean z2 = j < 0;
            if (z2) {
                j = -j;
            }
            while (j > 0) {
                length--;
                this.numberBuffer[length] = (byte) (127 & j);
                j >>>= 7;
            }
            byte[] bArr = this.numberBuffer;
            int length2 = bArr.length - 1;
            bArr[length2] = (byte) (bArr[length2] | 128);
            if ((bArr[length] & 64) == 64) {
                length--;
                bArr[length] = 0;
            }
            if (z2) {
                bArr[length] = (byte) (bArr[length] | 64);
            }
            int length3 = bArr.length - length;
            write(bArr, length, length3);
            return length3;
        }

        public int writeVarUIntValue(long j, boolean z) throws IOException {
            if (j == 0) {
                if (!z) {
                    return 0;
                }
                write(-128);
                return 1;
            }
            int length = this.numberBuffer.length;
            while (j > 0) {
                length--;
                this.numberBuffer[length] = (byte) (127 & j);
                j >>>= 7;
            }
            byte[] bArr = this.numberBuffer;
            int length2 = bArr.length - 1;
            bArr[length2] = (byte) (bArr[length2] | 128);
            int length3 = bArr.length - length;
            write(bArr, length, length3);
            return length3;
        }

        public Writer(BlockedBuffer blockedBuffer) {
            super(blockedBuffer);
            this.numberBuffer = new byte[10];
            this.singleCodepointUtf8Buffer = new byte[4];
            this.stringBuffer = new byte[128];
        }

        public int writeUIntValue(long j, int i) throws IOException {
            int length = this.numberBuffer.length;
            for (int i2 = 0; i2 < i; i2++) {
                length--;
                this.numberBuffer[length] = (byte) (255 & j);
                j >>>= 8;
            }
            write(this.numberBuffer, length, i);
            return i;
        }

        public int writeByte(byte b) throws IOException {
            write(b);
            return 1;
        }

        public int writeAnnotations(SymbolToken[] symbolTokenArr, SymbolTable symbolTable) throws IOException {
            int i = this._pos;
            int iLenVarUInt = 0;
            for (SymbolToken symbolToken : symbolTokenArr) {
                iLenVarUInt += IonBinary.lenVarUInt(symbolToken.getSid());
            }
            writeVarUIntValue(iLenVarUInt, true);
            for (SymbolToken symbolToken2 : symbolTokenArr) {
                writeVarUIntValue(symbolToken2.getSid(), true);
            }
            return this._pos - i;
        }

        public int writeByte(int i) throws IOException {
            write(i);
            return 1;
        }

        public int writeUIntValue(BigInteger bigInteger, int i) throws IOException {
            int iSignum = bigInteger.signum();
            if (iSignum != 0) {
                if (iSignum >= 0) {
                    if (bigInteger.compareTo(IonBinary.MAX_LONG_VALUE) == -1) {
                        writeUIntValue(bigInteger.longValue(), i);
                    } else {
                        byte[] byteArray = bigInteger.toByteArray();
                        int i2 = 0;
                        while (i2 < byteArray.length && byteArray[i2] == 0) {
                            i2++;
                        }
                        write(byteArray, i2, byteArray.length - i2);
                    }
                } else {
                    throw new IllegalArgumentException("value must be greater than or equal to 0");
                }
            }
            return i;
        }

        public Writer(BlockedBuffer blockedBuffer, int i) {
            super(blockedBuffer, i);
            this.numberBuffer = new byte[10];
            this.singleCodepointUtf8Buffer = new byte[4];
            this.stringBuffer = new byte[128];
        }

        /* JADX WARN: Code restructure failed: missing block: B:127:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00f1, code lost:
        
            throw new com.amazon.ion.IonException("Text contains unmatched UTF-16 high surrogate " + com.amazon.ion.util.IonTextUtils.printCodePointAsString(r0));
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x00ff, code lost:
        
            if (r0 != r9) goto L80;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0103, code lost:
        
            if (com.amazon.ion.impl.IonBinary.debugValidation == false) goto L127;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0105, code lost:
        
            _validate();
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x0108, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x010e, code lost:
        
            throw new com.amazon.ion.UnexpectedEofException();
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void appendToLongValue(int r9, boolean r10, boolean r11, boolean r12, java.io.PushbackReader r13) throws java.io.IOException, com.amazon.ion.UnexpectedEofException {
            /*
                Method dump skipped, instruction units count: 345
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonBinary.Writer.appendToLongValue(int, boolean, boolean, boolean, java.io.PushbackReader):void");
        }
    }

    public static int lenUInt(BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            int iBitLength = bigInteger.bitLength();
            int i = iBitLength >> 3;
            return (iBitLength & 7) != 0 ? i + 1 : i;
        }
        throw new IllegalArgumentException("lenUInt expects a non-negative a value");
    }

    public static int writeTypeDescWithLength(OutputStream outputStream, int i, int i2) throws IOException {
        int i3 = (i & 15) << 4;
        if (i2 >= 14) {
            outputStream.write((byte) ((i3 | 14) & 255));
            long j = i2;
            int iLenVarUInt = lenVarUInt(j);
            writeVarUInt(outputStream, j, iLenVarUInt, true);
            return iLenVarUInt + 1;
        }
        outputStream.write((byte) ((i3 | (i2 & 15)) & 255));
        return 1;
    }

    public static int lenAnnotationListWithLen(ArrayList<Integer> arrayList) {
        int size = arrayList.size();
        int iLenVarUInt = 0;
        int i = 0;
        while (i < size) {
            Integer num = arrayList.get(i);
            i++;
            iLenVarUInt += lenVarUInt(num.intValue());
        }
        return lenVarUInt(iLenVarUInt) + iLenVarUInt;
    }
}
