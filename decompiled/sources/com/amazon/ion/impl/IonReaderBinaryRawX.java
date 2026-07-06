package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import com.amazon.ion.Decimal;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl.UnifiedSavePointManagerX;
import com.amazon.ion.impl._Private_ScalarConversions;
import com.amazon.ion.impl.bin.utf8.ByteBufferPool;
import com.amazon.ion.impl.bin.utf8.PoolableByteBuffer;
import com.amazon.ion.impl.bin.utf8.Utf8StringDecoder;
import com.amazon.ion.impl.bin.utf8.Utf8StringDecoderPool;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonReaderBinaryRawX implements IonReader {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BINARY_VERSION_MARKER_LEN;
    public static final int BINARY_VERSION_MARKER_TID;
    public static final int DEFAULT_ANNOTATION_SIZE = 10;
    public static final int DEFAULT_CONTAINER_STACK_SIZE = 12;
    public static final int LIMIT_SHIFT = 32;
    public static final int NO_LIMIT = Integer.MIN_VALUE;
    public static final int POS_OFFSET = 0;
    public static final int POS_STACK_STEP = 2;
    public static final int TYPE_LIMIT_OFFSET = 1;
    public static final long TYPE_MASK = -1;
    public int _annotation_count;
    public int[] _annotation_ids;
    public UnifiedSavePointManagerX.SavePoint _annotations;
    public long[] _container_stack;
    public int _container_top;
    public boolean _eof;
    public boolean _has_next_needed;
    public UnifiedInputStreamX _input;
    public boolean _is_in_struct;
    public int _local_remaining;
    public int _parent_tid;
    public long _position_len;
    public long _position_start;
    public State _state;
    public boolean _struct_is_ordered;
    public _Private_ScalarConversions.ValueVariant _v;
    public int _value_field_id;
    public boolean _value_is_null;
    public boolean _value_is_true;
    public int _value_len;
    public boolean _value_lob_is_ready;
    public int _value_lob_remaining;
    public long _value_start;
    public int _value_tid;
    public IonType _value_type;
    public final Utf8StringDecoder utf8Decoder = Utf8StringDecoderPool.getInstance().getOrCreate();
    public final PoolableByteBuffer pooledUtf8InputBuffer = ByteBufferPool.getInstance().getOrCreate();

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderBinaryRawX$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.STRUCT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[State.values().length];
            $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State = iArr2;
            try {
                iArr2[State.S_BEFORE_FIELD.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State[State.S_BEFORE_TID.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State[State.S_BEFORE_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State[State.S_AFTER_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State[State.S_EOF.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum State {
        S_INVALID,
        S_BEFORE_FIELD,
        S_BEFORE_TID,
        S_BEFORE_VALUE,
        S_AFTER_VALUE,
        S_EOF
    }

    static {
        byte b = _Private_IonConstants.BINARY_VERSION_MARKER_1_0[0];
        BINARY_VERSION_MARKER_TID = (b & 255) >> 4;
        BINARY_VERSION_MARKER_LEN = b & Ascii.SI;
    }

    private final void clear_value() {
        this._value_type = null;
        this._value_tid = -1;
        this._value_is_null = false;
        this._value_lob_is_ready = false;
        this._annotations.clear();
        this._v.clear();
        this._annotation_count = 0;
        this._value_field_id = -1;
    }

    private final void pop() {
        this._container_top -= 2;
    }

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    @Override // com.amazon.ion.IonReader
    public int byteSize() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()];
        if (i != 4 && i != 5) {
            throw new IllegalStateException("only valid for LOB values");
        }
        if (!this._value_lob_is_ready) {
            this._value_lob_remaining = this._value_is_null ? 0 : this._value_len;
            this._value_lob_is_ready = true;
        }
        return this._value_lob_remaining;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._input.close();
        this.utf8Decoder.close();
        this.pooledUtf8InputBuffer.close();
    }

    public void error(String str) {
        throw new IonException(str);
    }

    @Override // com.amazon.ion.IonReader
    public int getBytes(byte[] bArr, int i, int i2) {
        int iByteSize = byteSize();
        if (iByteSize <= i2) {
            i2 = iByteSize;
        }
        return readBytes(bArr, i, i2);
    }

    @Override // com.amazon.ion.IonReader
    public int getDepth() {
        return this._container_top / 2;
    }

    public final long getPosition() {
        return this._input.getPosition();
    }

    @Override // com.amazon.ion.IonReader
    public IonType getType() {
        return this._value_type;
    }

    public final IonType get_iontype_from_tid(int i) {
        switch (i) {
            case 0:
                return IonType.NULL;
            case 1:
                return IonType.BOOL;
            case 2:
            case 3:
                return IonType.INT;
            case 4:
                return IonType.FLOAT;
            case 5:
                return IonType.DECIMAL;
            case 6:
                return IonType.TIMESTAMP;
            case 7:
                return IonType.SYMBOL;
            case 8:
                return IonType.STRING;
            case 9:
                return IonType.CLOB;
            case 10:
                return IonType.BLOB;
            case 11:
                return IonType.LIST;
            case 12:
                return IonType.SEXP;
            case 13:
                return IonType.STRUCT;
            case 14:
                return null;
            default:
                throw newErrorAt("unrecognized value type encountered: " + i);
        }
    }

    public final int get_top_local_remaining() {
        return (int) (this._container_stack[this._container_top - 1] >> 32);
    }

    public final long get_top_position() {
        return this._container_stack[this._container_top - 2];
    }

    public final int get_top_type() {
        int i = (int) this._container_stack[this._container_top - 1];
        if (i >= 0 && i <= 16) {
            return i;
        }
        throwErrorAt("invalid type id in parent stack");
        throw null;
    }

    @Override // com.amazon.ion.IonReader
    public boolean hasNext() {
        if (!this._eof && this._has_next_needed) {
            try {
                has_next_helper_raw();
            } catch (IOException e) {
                error(e);
                throw null;
            }
        }
        return !this._eof;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0061 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x005a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void has_next_helper_raw() throws java.io.IOException {
        /*
            r15 = this;
            r15.clear_value()
        L3:
            int r0 = r15._value_tid
            r1 = 0
            r2 = -1
            if (r0 != r2) goto Lbc
            boolean r0 = r15._eof
            if (r0 != 0) goto Lbc
            int[] r0 = com.amazon.ion.impl.IonReaderBinaryRawX.AnonymousClass1.$SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State
            com.amazon.ion.impl.IonReaderBinaryRawX$State r3 = r15._state
            int r3 = r3.ordinal()
            r0 = r0[r3]
            r3 = 2
            r4 = 1
            if (r0 == r4) goto L43
            if (r0 == r3) goto L4e
            r1 = 3
            if (r0 == r1) goto L2e
            r1 = 4
            if (r0 == r1) goto L33
            r1 = 5
            if (r0 != r1) goto L27
            goto L3
        L27:
            java.lang.String r0 = "internal error: raw binary reader in invalid state!"
            r15.error(r0)
            r0 = 0
            throw r0
        L2e:
            int r0 = r15._value_len
            r15.skip(r0)
        L33:
            boolean r0 = r15.isInStruct()
            if (r0 == 0) goto L3e
            com.amazon.ion.impl.IonReaderBinaryRawX$State r0 = com.amazon.ion.impl.IonReaderBinaryRawX.State.S_BEFORE_FIELD
            r15._state = r0
            goto L3
        L3e:
            com.amazon.ion.impl.IonReaderBinaryRawX$State r0 = com.amazon.ion.impl.IonReaderBinaryRawX.State.S_BEFORE_TID
            r15._state = r0
            goto L3
        L43:
            int r0 = r15.readVarUIntOrEOF()
            r15._value_field_id = r0
            if (r0 != r2) goto L4e
            r15._eof = r4
            goto L3
        L4e:
            com.amazon.ion.impl.IonReaderBinaryRawX$State r0 = com.amazon.ion.impl.IonReaderBinaryRawX.State.S_BEFORE_VALUE
            r15._state = r0
            int r0 = r15.read_type_id()
            r15._value_tid = r0
            if (r0 != r2) goto L61
            com.amazon.ion.impl.IonReaderBinaryRawX$State r0 = com.amazon.ion.impl.IonReaderBinaryRawX.State.S_EOF
            r15._state = r0
            r15._eof = r4
            goto L3
        L61:
            r2 = 99
            if (r0 != r2) goto L6e
            int r0 = r15._value_len
            r15.skip(r0)
            r15.clear_value()
            goto L3
        L6e:
            r2 = 14
            if (r0 != r2) goto Lb4
            int r0 = r15._value_len
            int r2 = com.amazon.ion.impl.IonReaderBinaryRawX.BINARY_VERSION_MARKER_LEN
            if (r0 != r2) goto L80
            r15.load_version_marker()
            com.amazon.ion.IonType r0 = com.amazon.ion.IonType.SYMBOL
            r15._value_type = r0
            goto L3
        L80:
            long r5 = r15._position_start
            long r7 = r15._position_len
            com.amazon.ion.IonType r0 = r15.load_annotation_start_with_value_type()
            r15._value_type = r0
            long r9 = r5 + r7
            long r11 = r15._position_start
            long r13 = r15._position_len
            long r11 = r11 + r13
            int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r0 != 0) goto L9b
            r15._position_start = r5
            r15._position_len = r7
            goto L3
        L9b:
            java.lang.Long r0 = java.lang.Long.valueOf(r9)
            java.lang.Long r2 = java.lang.Long.valueOf(r11)
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r1] = r0
            r3[r4] = r2
            java.lang.String r0 = "Wrapper length mismatch: wrapper %s wrapped value %s"
            java.lang.String r0 = java.lang.String.format(r0, r3)
            com.amazon.ion.IonException r0 = r15.newErrorAt(r0)
            throw r0
        Lb4:
            com.amazon.ion.IonType r0 = r15.get_iontype_from_tid(r0)
            r15._value_type = r0
            goto L3
        Lbc:
            r15._has_next_needed = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderBinaryRawX.has_next_helper_raw():void");
    }

    public final void init_raw(UnifiedInputStreamX unifiedInputStreamX) {
        this._input = unifiedInputStreamX;
        this._container_stack = new long[12];
        this._annotations = unifiedInputStreamX._save_points.savePointAllocate();
        this._v = new _Private_ScalarConversions.ValueVariant();
        this._annotation_ids = new int[10];
        re_init_raw();
        this._position_start = -1L;
    }

    public final boolean isEOF() {
        int i = this._local_remaining;
        if (i > 0) {
            return false;
        }
        if (i == Integer.MIN_VALUE) {
            return this._input._eof;
        }
        return true;
    }

    @Override // com.amazon.ion.IonReader
    public boolean isInStruct() {
        return this._is_in_struct;
    }

    @Override // com.amazon.ion.IonReader
    public boolean isNullValue() {
        return this._value_is_null;
    }

    public final void load_annotation_append(int i) {
        int[] iArr = this._annotation_ids;
        int length = iArr.length;
        if (this._annotation_count >= length) {
            int[] iArr2 = new int[length * 2];
            System.arraycopy(iArr, 0, iArr2, 0, length);
            this._annotation_ids = iArr2;
        }
        int[] iArr3 = this._annotation_ids;
        int i2 = this._annotation_count;
        this._annotation_count = i2 + 1;
        iArr3[i2] = i;
    }

    public final IonType load_annotation_start_with_value_type() throws IOException {
        int varUInt = readVarUInt();
        this._annotations.start(this._input.getPosition(), 0L);
        skip(varUInt);
        this._annotations.markEnd();
        int i = read_type_id();
        this._value_tid = i;
        if (i == 99) {
            throwErrorAt("NOP padding is not allowed within annotation wrappers.");
            throw null;
        }
        if (i == -1) {
            throwErrorAt("unexpected EOF encountered where a type descriptor byte was expected");
            throw null;
        }
        if (i != 14) {
            return get_iontype_from_tid(i);
        }
        throwErrorAt("An annotation wrapper may not contain another annotation wrapper.");
        throw null;
    }

    public final int load_annotations() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$impl$IonReaderBinaryRawX$State[this._state.ordinal()];
        if (i != 3 && i != 4) {
            throw new IllegalStateException("annotations require the value to be ready");
        }
        if (this._annotations.isDefined()) {
            int i2 = this._local_remaining;
            UnifiedInputStreamX unifiedInputStreamX = this._input;
            unifiedInputStreamX._save_points.savePointPushActive(this._annotations, unifiedInputStreamX.getPosition(), 0L);
            this._local_remaining = Integer.MIN_VALUE;
            this._annotation_count = 0;
            do {
                try {
                    int varUIntOrEOF = readVarUIntOrEOF();
                    if (varUIntOrEOF == -1) {
                        break;
                    }
                    load_annotation_append(varUIntOrEOF);
                } catch (IOException e) {
                    error(e);
                    throw null;
                }
            } while (!isEOF());
            this._input._save_points.savePointPopActive(this._annotations);
            this._local_remaining = i2;
            this._annotations.clear();
        }
        return this._annotation_count;
    }

    public final void load_version_marker() throws IOException {
        int i = 1;
        while (true) {
            byte[] bArr = _Private_IonConstants.BINARY_VERSION_MARKER_1_0;
            if (i >= bArr.length) {
                this._value_tid = 7;
                this._value_len = 0;
                this._value_start = 0L;
                this._v.setValue(2);
                this._v.setAuthoritativeType(3);
                this._value_is_null = false;
                this._value_lob_is_ready = false;
                this._annotations.clear();
                this._value_field_id = -1;
                this._state = State.S_AFTER_VALUE;
                return;
            }
            if (read() != (bArr[i] & 255)) {
                throwErrorAt("invalid binary image");
                throw null;
            }
            i++;
        }
    }

    @Override // com.amazon.ion.IonReader
    public byte[] newBytes() {
        int iByteSize = byteSize();
        if (this._value_is_null) {
            return null;
        }
        byte[] bArr = new byte[iByteSize];
        getBytes(bArr, 0, iByteSize);
        return bArr;
    }

    public IonException newErrorAt(String str) {
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, " at position ");
        sbM.append(this._input.getPosition());
        return new IonException(sbM.toString());
    }

    @Override // com.amazon.ion.IonReader
    public IonType next() {
        if (this._eof) {
            return null;
        }
        if (this._has_next_needed) {
            try {
                has_next_helper_raw();
            } catch (IOException e) {
                error(e);
                throw null;
            }
        }
        this._has_next_needed = true;
        return this._value_type;
    }

    public final void push(int i, long j, int i2) {
        long[] jArr = this._container_stack;
        int length = jArr.length;
        if (this._container_top + 2 >= length) {
            long[] jArr2 = new long[length * 2];
            System.arraycopy(jArr, 0, jArr2, 0, length);
            this._container_stack = jArr2;
        }
        long[] jArr3 = this._container_stack;
        int i3 = this._container_top;
        jArr3[i3] = j;
        jArr3[i3 + 1] = (((long) i2) << 32) | ((long) i);
        this._container_top = i3 + 2;
    }

    public final void re_init_raw() {
        this._local_remaining = Integer.MIN_VALUE;
        this._parent_tid = 16;
        this._value_field_id = -1;
        this._state = State.S_BEFORE_TID;
        this._has_next_needed = true;
        this._eof = false;
        this._value_type = null;
        this._value_is_null = false;
        this._value_is_true = false;
        this._value_len = 0;
        this._value_start = 0L;
        this._value_lob_remaining = 0;
        this._value_lob_is_ready = false;
        this._annotation_count = 0;
        this._is_in_struct = false;
        this._struct_is_ordered = false;
        this._parent_tid = 0;
        this._container_top = 0;
    }

    public final int read() throws IOException {
        int i = this._local_remaining;
        if (i != Integer.MIN_VALUE) {
            if (i < 1) {
                return -1;
            }
            this._local_remaining = i - 1;
        }
        return this._input.read();
    }

    public void readAll(byte[] bArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int i3 = read(bArr, i, i2);
            if (i3 <= 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            i2 -= i3;
            i += i3;
        }
    }

    public final BigInteger readBigInteger(int i, boolean z) throws IOException {
        if (i <= 0) {
            return BigInteger.ZERO;
        }
        byte[] bArr = new byte[i];
        readAll(bArr, 0, i);
        return new BigInteger(z ? -1 : 1, bArr);
    }

    public int readBytes(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 < 1) {
            return 0;
        }
        try {
            int i3 = read(bArr, i, i2);
            int i4 = this._value_lob_remaining - i3;
            this._value_lob_remaining = i4;
            if (i4 == 0) {
                this._state = State.S_AFTER_VALUE;
                return i3;
            }
            this._value_len = i4;
            return i3;
        } catch (IOException e) {
            error(e);
            throw null;
        }
    }

    public final Decimal readDecimal(int i) throws IOException {
        BigInteger bigInteger;
        MathContext mathContext = MathContext.UNLIMITED;
        int i2 = 0;
        if (i == 0) {
            return Decimal.valueOf(0, mathContext);
        }
        int i3 = this._local_remaining;
        int i4 = i3 != Integer.MIN_VALUE ? i3 - i : Integer.MIN_VALUE;
        this._local_remaining = i;
        int varInt = readVarInt();
        int i5 = this._local_remaining;
        if (i5 > 0) {
            byte[] bArr = new byte[i5];
            readAll(bArr, 0, i5);
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
        int i6 = -varInt;
        Decimal decimalNegativeZero = (bigInteger.signum() == 0 && i2 == -1) ? Decimal.negativeZero(i6, mathContext) : Decimal.valueOf(bigInteger, i6, mathContext);
        this._local_remaining = i4;
        return decimalNegativeZero;
    }

    public final double readFloat(int i) throws IOException {
        if (i == 0) {
            return 0.0d;
        }
        if (i != 4 && i != 8) {
            throw new IOException("Length of float read must be 0, 4, or 8");
        }
        return i == 4 ? Float.intBitsToFloat((int) (r1 & 4294967295L)) : Double.longBitsToDouble(readULong(i));
    }

    public final String readString(int i) throws IOException {
        java.nio.ByteBuffer buffer = this.pooledUtf8InputBuffer.getBuffer();
        if (i <= buffer.capacity()) {
            return readStringWithReusableBuffer(i, buffer);
        }
        this.utf8Decoder.prepareDecode(i);
        int i2 = this._local_remaining;
        int i3 = i2 != Integer.MIN_VALUE ? i2 - i : Integer.MIN_VALUE;
        this._local_remaining = i;
        int i4 = 0;
        int iRemaining = 0;
        while (i4 < i) {
            int i5 = read(buffer.array(), iRemaining, Math.min(i - i4, buffer.array().length - iRemaining));
            if (i5 <= 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            i4 += i5;
            buffer.position(0);
            buffer.limit(iRemaining + i5);
            this.utf8Decoder.partialDecode(buffer, i4 >= i);
            iRemaining = buffer.remaining();
            if (iRemaining > 0) {
                System.arraycopy(buffer.array(), buffer.position(), buffer.array(), 0, iRemaining);
            }
        }
        this._local_remaining = i3;
        return this.utf8Decoder.finishDecode();
    }

    public final String readStringWithReusableBuffer(int i, java.nio.ByteBuffer byteBuffer) throws IOException {
        int i2 = this._local_remaining;
        int i3 = i2 != Integer.MIN_VALUE ? i2 - i : Integer.MIN_VALUE;
        this._local_remaining = i;
        readAll(byteBuffer.array(), 0, i);
        this._local_remaining = i3;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        return this.utf8Decoder.decode(byteBuffer, i);
    }

    public final Timestamp readTimestamp(int i) throws IOException {
        Timestamp.Precision precision;
        Decimal decimal;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (i < 1) {
            return null;
        }
        int i7 = this._local_remaining;
        int i8 = i7 != Integer.MIN_VALUE ? i7 - i : Integer.MIN_VALUE;
        this._local_remaining = i;
        Integer varInteger = readVarInteger();
        int varUInt = readVarUInt();
        Timestamp.Precision precision2 = Timestamp.Precision.YEAR;
        try {
            if (this._local_remaining > 0) {
                int varUInt2 = readVarUInt();
                Timestamp.Precision precision3 = Timestamp.Precision.MONTH;
                if (this._local_remaining > 0) {
                    int varUInt3 = readVarUInt();
                    Timestamp.Precision precision4 = Timestamp.Precision.DAY;
                    if (this._local_remaining > 0) {
                        int varUInt4 = readVarUInt();
                        int varUInt5 = readVarUInt();
                        Timestamp.Precision precision5 = Timestamp.Precision.MINUTE;
                        if (this._local_remaining > 0) {
                            int varUInt6 = readVarUInt();
                            Timestamp.Precision precision6 = Timestamp.Precision.SECOND;
                            int i9 = this._local_remaining;
                            if (i9 > 0) {
                                Decimal decimal2 = readDecimal(i9);
                                if (decimal2.compareTo(BigDecimal.ZERO) < 0 || decimal2.compareTo(BigDecimal.ONE) >= 0) {
                                    throwErrorAt("The fractional seconds value in a timestamp must be greater than or equal to zero and less than one.");
                                    throw null;
                                }
                                i6 = varUInt6;
                                precision = precision6;
                                decimal = decimal2;
                                i4 = varUInt4;
                                i5 = varUInt5;
                                i2 = varUInt2;
                                i3 = varUInt3;
                                this._local_remaining = i8;
                                return Timestamp.createFromUtcFields(precision, varUInt, i2, i3, i4, i5, i6, decimal, varInteger);
                            }
                            decimal = null;
                            i6 = varUInt6;
                            i5 = varUInt5;
                            precision = precision6;
                        } else {
                            decimal = null;
                            i5 = varUInt5;
                            precision = precision5;
                            i6 = 0;
                        }
                        i3 = varUInt3;
                        i4 = varUInt4;
                    } else {
                        i3 = varUInt3;
                        decimal = null;
                        precision = precision4;
                        i4 = 0;
                        i5 = 0;
                        i6 = 0;
                    }
                    i2 = varUInt2;
                    this._local_remaining = i8;
                    return Timestamp.createFromUtcFields(precision, varUInt, i2, i3, i4, i5, i6, decimal, varInteger);
                }
                i2 = varUInt2;
                precision = precision3;
                decimal = null;
            } else {
                precision = precision2;
                decimal = null;
                i2 = 0;
            }
            return Timestamp.createFromUtcFields(precision, varUInt, i2, i3, i4, i5, i6, decimal, varInteger);
        } catch (IllegalArgumentException e) {
            throw newErrorAt("Invalid timestamp encoding: " + e.getMessage());
        }
        i3 = 0;
        i4 = 0;
        i5 = 0;
        i6 = 0;
        this._local_remaining = i8;
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
    public final long readULong(int r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 8
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
            throw r0
        L1c:
            int r7 = r6.read()
            if (r7 < 0) goto L26
            long r2 = r2 << r1
            long r4 = (long) r7
            long r2 = r2 | r4
            goto L2a
        L26:
            r6.throwUnexpectedEOFException()
            throw r0
        L2a:
            int r7 = r6.read()
            if (r7 < 0) goto L34
            long r2 = r2 << r1
            long r4 = (long) r7
            long r2 = r2 | r4
            goto L38
        L34:
            r6.throwUnexpectedEOFException()
            throw r0
        L38:
            int r7 = r6.read()
            if (r7 < 0) goto L42
            long r2 = r2 << r1
            long r4 = (long) r7
            long r2 = r2 | r4
            goto L46
        L42:
            r6.throwUnexpectedEOFException()
            throw r0
        L46:
            int r7 = r6.read()
            if (r7 < 0) goto L50
            long r2 = r2 << r1
            long r4 = (long) r7
            long r2 = r2 | r4
            goto L54
        L50:
            r6.throwUnexpectedEOFException()
            throw r0
        L54:
            int r7 = r6.read()
            if (r7 < 0) goto L5e
            long r2 = r2 << r1
            long r4 = (long) r7
            long r2 = r2 | r4
            goto L62
        L5e:
            r6.throwUnexpectedEOFException()
            throw r0
        L62:
            int r7 = r6.read()
            if (r7 < 0) goto L6c
            long r2 = r2 << r1
            long r4 = (long) r7
            long r2 = r2 | r4
            goto L70
        L6c:
            r6.throwUnexpectedEOFException()
            throw r0
        L70:
            int r7 = r6.read()
            if (r7 < 0) goto L7b
            long r0 = r2 << r1
            long r2 = (long) r7
            long r0 = r0 | r2
            return r0
        L7b:
            r6.throwUnexpectedEOFException()
            throw r0
        L7f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderBinaryRawX.readULong(int):long");
    }

    public final int readVarInt() throws IOException {
        return readVarInt(read());
    }

    public final Integer readVarInteger() throws IOException {
        int i = read();
        if (i == 192) {
            return null;
        }
        return Integer.valueOf(readVarInt(i));
    }

    public final int readVarUInt() throws IOException {
        int varUIntOrEOF = readVarUIntOrEOF();
        if (varUIntOrEOF != -1) {
            return varUIntOrEOF;
        }
        throwUnexpectedEOFException();
        throw null;
    }

    public final int readVarUIntOrEOF() throws IOException {
        int i = read();
        if (i < 0) {
            return -1;
        }
        long j = i & 127;
        if ((i & 128) == 0) {
            int i2 = read();
            if (i2 < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            j = (j << 7) | ((long) (i2 & 127));
            if ((i2 & 128) == 0) {
                int i3 = read();
                if (i3 < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                j = (j << 7) | ((long) (i3 & 127));
                if ((i3 & 128) == 0) {
                    int i4 = read();
                    if (i4 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    j = (j << 7) | ((long) (i4 & 127));
                    if ((i4 & 128) == 0) {
                        int i5 = read();
                        if (i5 < 0) {
                            throwUnexpectedEOFException();
                            throw null;
                        }
                        j = (j << 7) | ((long) (i5 & 127));
                        if ((i5 & 128) == 0) {
                            throwVarIntOverflowException();
                            throw null;
                        }
                    }
                }
            }
        }
        int i6 = (int) j;
        if (j == i6) {
            return i6;
        }
        throwVarIntOverflowException();
        throw null;
    }

    public final int read_field_id() throws IOException {
        return readVarUIntOrEOF();
    }

    public final int read_type_id() throws IOException {
        long position = this._input.getPosition();
        long position2 = 1 + position;
        int i = read();
        if (i < 0) {
            return -1;
        }
        int i2 = i >> 4;
        int varUInt = i & 15;
        if (i2 == 0 && varUInt != 15) {
            if (varUInt == 14) {
                varUInt = readVarUInt();
            }
            this._state = this._is_in_struct ? State.S_BEFORE_FIELD : State.S_BEFORE_TID;
            i2 = 99;
        } else if (varUInt == 14) {
            varUInt = readVarUInt();
            position2 = this._input.getPosition();
        } else {
            if (i2 == 0 || varUInt == 15) {
                this._value_is_null = true;
                this._state = State.S_AFTER_VALUE;
            } else if (i2 == 1) {
                if (varUInt == 0) {
                    this._value_is_true = false;
                } else {
                    if (varUInt != 1) {
                        throwErrorAt("invalid length nibble in boolean value: " + varUInt);
                        throw null;
                    }
                    this._value_is_true = true;
                }
                this._state = State.S_AFTER_VALUE;
            } else if (i2 == 13) {
                boolean z = varUInt == 1;
                this._struct_is_ordered = z;
                if (z) {
                    varUInt = readVarUInt();
                    if (varUInt == 0) {
                        throwErrorAt("Structs flagged as having ordered keys must contain at least one key/value pair.");
                        throw null;
                    }
                    position2 = this._input.getPosition();
                }
            }
            varUInt = 0;
        }
        this._value_tid = i2;
        this._value_len = varUInt;
        this._value_start = position2;
        this._position_len = (position2 - position) + ((long) varUInt);
        this._position_start = position;
        return i2;
    }

    public final void skip(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        int i2 = this._local_remaining;
        if (i2 == Integer.MIN_VALUE) {
            this._input.skip(i);
            return;
        }
        if (i > i2) {
            if (i2 < 1) {
                throwUnexpectedEOFException();
                throw null;
            }
            i = i2;
        }
        this._input.skip(i);
        this._local_remaining -= i;
    }

    @Override // com.amazon.ion.IonReader
    public void stepIn() {
        IonType ionType = this._value_type;
        if (ionType == null || this._eof) {
            throw new IllegalStateException();
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            throw new IllegalStateException();
        }
        long position = this._input.getPosition();
        int i2 = this._value_len;
        long j = position + ((long) i2);
        int i3 = this._local_remaining;
        if (i3 != Integer.MIN_VALUE && (i3 = i3 - i2) < 0) {
            i3 = 0;
        }
        push(this._parent_tid, j, i3);
        int i4 = this._value_tid;
        boolean z = i4 == 13;
        this._is_in_struct = z;
        this._local_remaining = this._value_len;
        this._state = z ? State.S_BEFORE_FIELD : State.S_BEFORE_TID;
        this._parent_tid = i4;
        clear_value();
        this._has_next_needed = true;
    }

    @Override // com.amazon.ion.IonReader
    public void stepOut() {
        if (getDepth() < 1) {
            throw new IllegalStateException(IonMessages.CANNOT_STEP_OUT);
        }
        long j = get_top_position();
        int i = get_top_local_remaining();
        int i2 = get_top_type();
        pop();
        this._eof = false;
        this._parent_tid = i2;
        if (i2 == 13) {
            this._is_in_struct = true;
            this._state = State.S_BEFORE_FIELD;
        } else {
            this._is_in_struct = false;
            this._state = State.S_BEFORE_TID;
        }
        this._has_next_needed = true;
        clear_value();
        long position = this._input.getPosition();
        if (j > position) {
            long j2 = j - position;
            while (true) {
                long j3 = 2147483646;
                if (j2 <= j3) {
                    break;
                }
                try {
                    skip(2147483646);
                    j2 -= j3;
                } catch (IOException e) {
                    error(e);
                    throw null;
                }
                error(e);
                throw null;
            }
            if (j2 > 0) {
                skip((int) j2);
            }
        } else if (j < position) {
            StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("invalid position during stepOut, current position ", position, " next value at ");
            sbM.append(j);
            error(sbM.toString());
            throw null;
        }
        this._local_remaining = i;
    }

    public void throwErrorAt(String str) {
        throw newErrorAt(str);
    }

    public final void throwUnexpectedEOFException() throws IOException {
        throwErrorAt("unexpected EOF in value");
        throw null;
    }

    public final void throwVarIntOverflowException() throws IOException {
        throwErrorAt("int in stream is too long for a Java int 32");
        throw null;
    }

    private int readVarInt(int i) throws IOException {
        if (i < 0) {
            throwUnexpectedEOFException();
            throw null;
        }
        boolean z = (i & 64) != 0;
        long j = i & 63;
        if ((i & 128) == 0) {
            int i2 = read();
            if (i2 < 0) {
                throwUnexpectedEOFException();
                throw null;
            }
            j = (j << 7) | ((long) (i2 & 127));
            if ((i2 & 128) == 0) {
                int i3 = read();
                if (i3 < 0) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                j = (j << 7) | ((long) (i3 & 127));
                if ((i3 & 128) == 0) {
                    int i4 = read();
                    if (i4 < 0) {
                        throwUnexpectedEOFException();
                        throw null;
                    }
                    j = (j << 7) | ((long) (i4 & 127));
                    if ((i4 & 128) == 0) {
                        int i5 = read();
                        if (i5 < 0) {
                            throwUnexpectedEOFException();
                            throw null;
                        }
                        j = (j << 7) | ((long) (i5 & 127));
                        if ((i5 & 128) == 0) {
                            throwVarIntOverflowException();
                            throw null;
                        }
                    }
                }
            }
        }
        if (z) {
            j = -j;
        }
        int i6 = (int) j;
        if (j == i6) {
            return i6;
        }
        throwVarIntOverflowException();
        throw null;
    }

    public void error(Exception exc) {
        throw new IonException(exc);
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null && i >= 0 && i2 >= 0 && i + i2 <= bArr.length) {
            int i3 = this._local_remaining;
            if (i3 == Integer.MIN_VALUE) {
                return this._input.read(bArr, i, i2);
            }
            if (i2 > i3) {
                if (i3 < 1) {
                    throwUnexpectedEOFException();
                    throw null;
                }
                i2 = i3;
            }
            int i4 = this._input.read(bArr, i, i2);
            this._local_remaining -= i4;
            return i4;
        }
        throw new IllegalArgumentException();
    }
}
