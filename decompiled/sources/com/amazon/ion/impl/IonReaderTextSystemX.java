package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonBlob;
import com.amazon.ion.IonClob;
import com.amazon.ion.IonException;
import com.amazon.ion.IonList;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.impl.IonReaderTextRawTokensX;
import com.amazon.ion.impl.IonReaderTextRawX;
import com.amazon.ion.impl._Private_ScalarConversions;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonReaderTextSystemX extends IonReaderTextRawX implements _Private_ReaderWriter {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static int UNSIGNED_BYTE_MAX_VALUE = 255;
    public SymbolTable _system_symtab = _Private_Utils.systemSymtab(1);

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderTextSystemX$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BOOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DECIMAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.TIMESTAMP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Radix {
        DECIMAL { // from class: com.amazon.ion.impl.IonReaderTextSystemX.Radix.1
            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            public boolean isInt(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_INT_IMAGE, Radix.MAX_INT_IMAGE);
            }

            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            public boolean isLong(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_LONG_IMAGE, Radix.MAX_LONG_IMAGE);
            }
        },
        HEX { // from class: com.amazon.ion.impl.IonReaderTextSystemX.Radix.2
            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            public boolean isInt(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_HEX_INT_IMAGE, Radix.MAX_HEX_INT_IMAGE);
            }

            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            public boolean isLong(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_HEX_LONG_IMAGE, Radix.MAX_HEX_LONG_IMAGE);
            }
        },
        BINARY { // from class: com.amazon.ion.impl.IonReaderTextSystemX.Radix.3
            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            public boolean isInt(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_BINARY_INT_IMAGE, Radix.MAX_BINARY_INT_IMAGE);
            }

            @Override // com.amazon.ion.impl.IonReaderTextSystemX.Radix
            public boolean isLong(String str, int i) {
                return Radix.valueWithinBounds(str, i, Radix.MIN_BINARY_LONG_IMAGE, Radix.MAX_BINARY_LONG_IMAGE);
            }
        };

        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final char[] MAX_INT_IMAGE = Integer.toString(Integer.MAX_VALUE).toCharArray();
        public static final char[] MIN_INT_IMAGE = Integer.toString(Integer.MIN_VALUE).toCharArray();
        public static final char[] MAX_LONG_IMAGE = Long.toString(Long.MAX_VALUE).toCharArray();
        public static final char[] MIN_LONG_IMAGE = Long.toString(Long.MIN_VALUE).toCharArray();
        public static final char[] MAX_BINARY_INT_IMAGE = Integer.toBinaryString(Integer.MAX_VALUE).toCharArray();
        public static final char[] MIN_BINARY_INT_IMAGE = ("-" + Integer.toBinaryString(Integer.MIN_VALUE)).toCharArray();
        public static final char[] MAX_BINARY_LONG_IMAGE = Long.toBinaryString(Long.MAX_VALUE).toCharArray();
        public static final char[] MIN_BINARY_LONG_IMAGE = ("-" + Long.toBinaryString(Long.MIN_VALUE)).toCharArray();
        public static final char[] MAX_HEX_INT_IMAGE = Integer.toHexString(Integer.MAX_VALUE).toCharArray();
        public static final char[] MIN_HEX_INT_IMAGE = ("-" + Integer.toHexString(Integer.MIN_VALUE)).toCharArray();
        public static final char[] MAX_HEX_LONG_IMAGE = Long.toHexString(Long.MAX_VALUE).toCharArray();
        public static final char[] MIN_HEX_LONG_IMAGE = ("-" + Long.toHexString(Long.MIN_VALUE)).toCharArray();

        public static boolean magnitudeLessThanOrEqualTo(String str, int i, char[] cArr) {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (str.charAt(i2) > cArr[i2]) {
                    return false;
                }
            }
            return true;
        }

        public static boolean valueWithinBounds(String str, int i, char[] cArr, char[] cArr2) {
            if (str.charAt(0) != '-') {
                cArr = cArr2;
            }
            int length = cArr.length;
            if (i >= length) {
                return i == length && magnitudeLessThanOrEqualTo(str, i, cArr);
            }
            return true;
        }

        public abstract boolean isInt(String str, int i);

        public abstract boolean isLong(String str, int i);

        Radix(AnonymousClass1 anonymousClass1) {
        }
    }

    public IonReaderTextSystemX(UnifiedInputStreamX unifiedInputStreamX) {
        init_once();
        init(unifiedInputStreamX, IonType.DATAGRAM);
    }

    private void checkIsIntApplicableType() {
        IonType ionType = this._value_type;
        if (ionType == IonType.INT || ionType == IonType.DECIMAL || ionType == IonType.FLOAT) {
            return;
        }
        throw new IllegalStateException("Unexpected value type: " + this._value_type);
    }

    private void load_once() {
        if (this._v.isEmpty()) {
            try {
                load_scalar_value();
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        }
    }

    private final void load_scalar_value() throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()]) {
            case 1:
                this._v.setValueToNull(this._null_type);
                this._v.setAuthoritativeType(1);
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                StringBuilder sb = token_contents_load(this._scanner._token);
                int i = this._scanner._token;
                if (this._value_type == IonType.DECIMAL) {
                    for (int i2 = 0; i2 < sb.length(); i2++) {
                        char cCharAt = sb.charAt(i2);
                        if (cCharAt == 'd' || cCharAt == 'D') {
                            sb.setCharAt(i2, 'e');
                        }
                    }
                } else if (i == 3 || i == 26) {
                    int i3 = sb.charAt(0) == '-' ? 1 : 0;
                    char c = i == 3 ? 'x' : 'b';
                    if (sb.length() <= (i3 != 0 ? 3 : 2) || Character.toLowerCase(sb.charAt(i3 + 1)) != c) {
                        StringBuilder sb2 = new StringBuilder("Invalid ");
                        sb2.append(c == 'x' ? "hexadecimal" : "binary");
                        sb2.append(" int value.");
                        parse_error(sb2.toString());
                        throw null;
                    }
                    sb.deleteCharAt(i3);
                    sb.deleteCharAt(i3);
                }
                int length = sb.length();
                String string = sb.toString();
                clear_current_value_buffer();
                if (i == 1) {
                    int i4 = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()];
                    if (i4 == 3) {
                        Radix radix = Radix.DECIMAL;
                        if (radix.isInt(string, length)) {
                            this._v.setValue(Integer.parseInt(string));
                            return;
                        } else if (radix.isLong(string, length)) {
                            this._v.setValue(Long.parseLong(string));
                            return;
                        } else {
                            this._v.setValue(new BigInteger(string));
                            return;
                        }
                    }
                    if (i4 == 4) {
                        try {
                            this._v.setValue(Double.parseDouble(string));
                            return;
                        } catch (NumberFormatException e) {
                            parse_error(e);
                            throw null;
                        }
                    }
                    if (i4 == 5) {
                        try {
                            this._v.setValue(Decimal.valueOf(string));
                            return;
                        } catch (NumberFormatException e2) {
                            parse_error(e2);
                            throw null;
                        }
                    }
                    if (i4 == 6) {
                        this._v.setValue(Timestamp.valueOf(string));
                        return;
                    }
                    parse_error("unexpected prefectched value type " + getType().toString() + " encountered handling an unquoted symbol");
                    throw null;
                }
                if (i == 2) {
                    Radix radix2 = Radix.DECIMAL;
                    if (radix2.isInt(string, length)) {
                        this._v.setValue(Integer.parseInt(string));
                        return;
                    } else if (radix2.isLong(string, length)) {
                        this._v.setValue(Long.parseLong(string));
                        return;
                    } else {
                        this._v.setValue(new BigInteger(string));
                        return;
                    }
                }
                if (i == 3) {
                    Radix radix3 = Radix.HEX;
                    if (radix3.isInt(string, length)) {
                        this._v.setValue(Integer.parseInt(string, 16));
                        return;
                    } else if (radix3.isLong(string, length)) {
                        this._v.setValue(Long.parseLong(string, 16));
                        return;
                    } else {
                        this._v.setValue(new BigInteger(string, 16));
                        return;
                    }
                }
                if (i == 4) {
                    try {
                        this._v.setValue(Decimal.valueOf(string));
                        return;
                    } catch (NumberFormatException e3) {
                        parse_error(e3);
                        throw null;
                    }
                }
                if (i == 5) {
                    try {
                        this._v.setValue(Double.parseDouble(string));
                        return;
                    } catch (NumberFormatException e4) {
                        parse_error(e4);
                        throw null;
                    }
                }
                if (i == 26) {
                    Radix radix4 = Radix.BINARY;
                    if (radix4.isInt(string, length)) {
                        this._v.setValue(Integer.parseInt(string, 2));
                        return;
                    } else if (radix4.isLong(string, length)) {
                        this._v.setValue(Long.parseLong(string, 2));
                        return;
                    } else {
                        this._v.setValue(new BigInteger(string, 2));
                        return;
                    }
                }
                switch (i) {
                    case 8:
                        try {
                            this._v.setValue(Timestamp.valueOf(string));
                            return;
                        } catch (IllegalArgumentException e5) {
                            parse_error(e5);
                            throw null;
                        }
                    case 9:
                        if (isNullValue()) {
                            this._v.setValueToNull(this._null_type);
                            return;
                        }
                        int i5 = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[getType().ordinal()];
                        if (i5 == 2) {
                            int i6 = this._value_keyword;
                            if (i6 == 1) {
                                this._v.setValue(true);
                                return;
                            }
                            if (i6 == 2) {
                                this._v.setValue(false);
                                return;
                            }
                            parse_error("unexpected keyword " + string + " identified as a BOOL");
                            throw null;
                        }
                        if (i5 != 4) {
                            if (i5 == 7) {
                                this._v.setValue(string);
                                return;
                            }
                            parse_error("unexpected prefectched value type " + getType().toString() + " encountered handling an unquoted symbol");
                            throw null;
                        }
                        if (this._value_keyword == 16) {
                            this._v.setValue(Double.NaN);
                            return;
                        }
                        parse_error("unexpected keyword " + string + " identified as a FLOAT");
                        throw null;
                    case 10:
                    case 11:
                    case 12:
                        this._v.setValue(string);
                        return;
                    case 13:
                        this._v.setValue(string);
                        return;
                    default:
                        parse_error("scalar token " + IonTokenConstsX.getTokenName(this._scanner._token) + "isn't a recognized type");
                        throw null;
                }
            default:
                return;
        }
    }

    private int readBytes(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6 = this._lob_token;
        if (i6 == 12) {
            i3 = i;
            i4 = -1;
            while (true) {
                int i7 = i2 - 1;
                if (i2 <= 0) {
                    break;
                }
                i4 = this._scanner.read_double_quoted_char(true);
                if (i4 >= 0) {
                    bArr[i3] = (byte) i4;
                    i3++;
                } else if (i4 != -7 && i4 != -8 && i4 != -9) {
                    break;
                }
                i2 = i7;
            }
        } else if (i6 == 13) {
            i3 = i;
            i4 = -1;
            while (true) {
                int i8 = i2 - 1;
                if (i2 <= 0) {
                    break;
                }
                i4 = this._scanner.read_triple_quoted_char(true);
                if (i4 >= 0) {
                    i5 = i3 + 1;
                    bArr[i3] = (byte) i4;
                } else if (i4 != -7 && i4 != -8 && i4 != -9 && i4 != -3) {
                    if (i4 != -4 && i4 != -5 && i4 != -6) {
                        break;
                    }
                    i5 = i3 + 1;
                    bArr[i3] = 10;
                } else {
                    i2 = i8;
                }
                i3 = i5;
                i2 = i8;
            }
        } else {
            if (i6 != 24) {
                throw new IonReaderTextRawTokensX.IonReaderTextTokenException("invalid type [" + this._value_type.toString() + "] for lob handling");
            }
            i3 = i;
            i4 = -1;
            while (true) {
                int i9 = i2 - 1;
                if (i2 <= 0 || (i4 = this._scanner.read_base64_byte()) < 0) {
                    break;
                }
                bArr[i3] = (byte) i4;
                i3++;
                i2 = i9;
            }
        }
        if (i4 == -1) {
            this._scanner.tokenIsFinished();
        }
        int i10 = i3 - i;
        this._lob_value_position += (long) i10;
        return i10;
    }

    @Override // com.amazon.ion.IonReader
    public BigDecimal bigDecimalValue() {
        load_or_cast_cached_value(6);
        if (this._v.hasValueOfType(1)) {
            return null;
        }
        return this._v.getBigDecimal();
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public BigInteger bigIntegerValue() {
        checkIsIntApplicableType();
        load_or_cast_cached_value(5);
        if (this._v.hasValueOfType(1)) {
            return null;
        }
        return this._v.getBigInteger();
    }

    @Override // com.amazon.ion.IonReader
    public boolean booleanValue() {
        load_or_cast_cached_value(2);
        return this._v.getBoolean();
    }

    @Override // com.amazon.ion.IonReader
    public int byteSize() {
        ensureLob("byteSize");
        try {
            long jLoad_lob_contents = load_lob_contents();
            if (jLoad_lob_contents >= 0 && jLoad_lob_contents <= 2147483647L) {
                return (int) jLoad_lob_contents;
            }
            load_lob_length_overflow_error(jLoad_lob_contents);
            throw null;
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    public final void cast_cached_value(int i) {
        if (this._v.hasValueOfType(1)) {
            return;
        }
        if (!IonType.SYMBOL.equals(this._value_type)) {
            if (this._v.can_convert(i)) {
                this._v.cast(_Private_ScalarConversions.getConversionFnid(this._v._authoritative_type_idx, i));
                return;
            } else {
                throw new _Private_ScalarConversions.CantConvertException("can't cast from " + _Private_ScalarConversions.getValueTypeName(this._v._authoritative_type_idx) + " to " + _Private_ScalarConversions.getValueTypeName(i));
            }
        }
        if (i == 3) {
            this._v.addValue(getSymbolTable().findSymbol(this._v.getString()));
        } else if (i == 8) {
            this._v.addValue(getSymbolTable().findKnownSymbol(this._v.getInt()));
        } else {
            throw new _Private_ScalarConversions.CantConvertException("can't cast symbol from " + _Private_ScalarConversions.getValueTypeName(this._v._authoritative_type_idx) + " to " + _Private_ScalarConversions.getValueTypeName(i));
        }
    }

    @Override // com.amazon.ion.IonReader
    public Date dateValue() {
        load_or_cast_cached_value(9);
        if (this._v.hasValueOfType(1)) {
            return null;
        }
        return this._v.getDate();
    }

    @Override // com.amazon.ion.IonReader
    public Decimal decimalValue() {
        load_or_cast_cached_value(6);
        if (this._v.hasValueOfType(1)) {
            return null;
        }
        return this._v.getDecimal();
    }

    @Override // com.amazon.ion.IonReader
    public double doubleValue() {
        load_or_cast_cached_value(7);
        return this._v.getDouble();
    }

    public final void ensureLob(String str) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()];
        if (i == 9 || i == 10) {
            return;
        }
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, " is only valid if the reader is on a lob value, not a ");
        sbM.append(this._value_type);
        sbM.append(" value");
        throw new IllegalStateException(sbM.toString());
    }

    public final void fillContainerList(IonSystem ionSystem, IonSequence ionSequence) {
        stepIn();
        while (next() != null) {
            ionSequence.add(getIonValue(ionSystem));
        }
        stepOut();
    }

    public final void fillContainerStruct(IonSystem ionSystem, IonStruct ionStruct) {
        stepIn();
        while (next() != null) {
            ionStruct.add(getFieldName(), getIonValue(ionSystem));
        }
        stepOut();
    }

    @Override // com.amazon.ion.IonReader
    public int getBytes(byte[] bArr, int i, int i2) {
        ensureLob("getBytes");
        if (this._lob_loaded == IonReaderTextRawX.LOB_STATE.READ) {
            try {
                load_lob_contents();
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        }
        if (this._lob_loaded == IonReaderTextRawX.LOB_STATE.FINISHED) {
            int i3 = this._lob_actual_len;
            if (i2 > i3) {
                i2 = i3;
            }
            System.arraycopy(this._lob_bytes, 0, bArr, i, i2);
            return i2;
        }
        try {
            if (this._current_value_save_point_loaded && this._lob_value_position > 0) {
                if (this._current_value_save_point.isActive()) {
                    this._scanner.save_point_deactivate(this._current_value_save_point);
                }
                this._scanner.save_point_activate(this._current_value_save_point);
                this._lob_value_position = 0L;
            }
            this._scanner.save_point_activate(this._current_value_save_point);
            int bytes = readBytes(bArr, i, i2);
            this._scanner.save_point_deactivate(this._current_value_save_point);
            return bytes;
        } catch (IOException e2) {
            throw new IonException(e2.getMessage(), e2);
        }
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public final int getFieldId() {
        String rawFieldName;
        int fieldId = super.getFieldId();
        return (fieldId != -1 || (rawFieldName = getRawFieldName()) == null) ? fieldId : getSymbolTable().findSymbol(rawFieldName);
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public final String getFieldName() {
        int fieldId;
        String rawFieldName = getRawFieldName();
        if (rawFieldName != null || (fieldId = getFieldId()) == -1) {
            return rawFieldName;
        }
        String strFindKnownSymbol = getSymbolTable().findKnownSymbol(fieldId);
        if (strFindKnownSymbol != null) {
            return strFindKnownSymbol;
        }
        throw new UnknownSymbolException(fieldId);
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public SymbolToken getFieldNameSymbol() {
        SymbolToken fieldNameSymbol = super.getFieldNameSymbol();
        return fieldNameSymbol != null ? _Private_Utils.localize(getSymbolTable(), fieldNameSymbol) : fieldNameSymbol;
    }

    @Override // com.amazon.ion.IonReader
    public IntegerSize getIntegerSize() {
        load_once();
        if (this._value_type != IonType.INT || this._v.hasValueOfType(1)) {
            return null;
        }
        return _Private_ScalarConversions.getIntegerSize(this._v._authoritative_type_idx);
    }

    public IonValue getIonValue(IonSystem ionSystem) {
        if (isNullValue()) {
            switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()]) {
                case 1:
                    return ionSystem.newNull();
                case 2:
                    return ionSystem.newNullBool();
                case 3:
                    return ionSystem.newNullInt();
                case 4:
                    return ionSystem.newNullFloat();
                case 5:
                    return ionSystem.newNullDecimal();
                case 6:
                    return ionSystem.newNullTimestamp();
                case 7:
                    return ionSystem.newNullSymbol();
                case 8:
                    return ionSystem.newNullString();
                case 9:
                    return ionSystem.newNullClob();
                case 10:
                    return ionSystem.newNullBlob();
                case 11:
                    return ionSystem.newNullList();
                case 12:
                    return ionSystem.newNullSexp();
                case 13:
                    return ionSystem.newNullString();
                default:
                    throw new IonException("unrecognized type encountered");
            }
        }
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[this._value_type.ordinal()]) {
            case 1:
                return ionSystem.newNull();
            case 2:
                return ionSystem.newBool(booleanValue());
            case 3:
                return ionSystem.newInt(longValue());
            case 4:
                return ionSystem.newFloat(doubleValue());
            case 5:
                return ionSystem.newDecimal(decimalValue());
            case 6:
                IonTimestamp ionTimestampNewNullTimestamp = ionSystem.newNullTimestamp();
                ionTimestampNewNullTimestamp.setValue(timestampValue());
                return ionTimestampNewNullTimestamp;
            case 7:
                return ionSystem.newSymbol(stringValue());
            case 8:
                return ionSystem.newString(stringValue());
            case 9:
                IonClob ionClobNewNullClob = ionSystem.newNullClob();
                ionClobNewNullClob.setBytes(newBytes());
                return ionClobNewNullClob;
            case 10:
                IonBlob ionBlobNewNullBlob = ionSystem.newNullBlob();
                ionBlobNewNullBlob.setBytes(newBytes());
                return ionBlobNewNullBlob;
            case 11:
                IonList ionListNewNullList = ionSystem.newNullList();
                fillContainerList(ionSystem, ionListNewNullList);
                return ionListNewNullList;
            case 12:
                IonSexp ionSexpNewNullSexp = ionSystem.newNullSexp();
                fillContainerList(ionSystem, ionSexpNewNullSexp);
                return ionSexpNewNullSexp;
            case 13:
                IonStruct ionStructNewNullStruct = ionSystem.newNullStruct();
                fillContainerStruct(ionSystem, ionStructNewNullStruct);
                return ionStructNewNullStruct;
            default:
                throw new IonException("unrecognized type encountered");
        }
    }

    @Override // com.amazon.ion.impl.IonReaderTextRawX, com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return this._system_symtab;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        int i = this._annotation_count;
        if (i == 0) {
            return SymbolToken.EMPTY_ARRAY;
        }
        resolveAnnotationSymbols(i);
        SymbolToken[] symbolTokenArr = new SymbolToken[i];
        System.arraycopy(this._annotations, 0, symbolTokenArr, 0, i);
        return symbolTokenArr;
    }

    @Override // com.amazon.ion.IonReader
    public String[] getTypeAnnotations() {
        resolveAnnotationSymbols(this._annotation_count);
        return _Private_Utils.toStrings(this._annotations, this._annotation_count);
    }

    @Override // com.amazon.ion.IonReader
    public int intValue() {
        checkIsIntApplicableType();
        load_or_cast_cached_value(3);
        return this._v.getInt();
    }

    @Override // com.amazon.ion.IonReader
    public boolean isNullValue() {
        return this._v.hasValueOfType(1);
    }

    public final int load_lob_contents() throws IOException {
        if (this._lob_loaded == IonReaderTextRawX.LOB_STATE.EMPTY) {
            load_lob_save_point();
        }
        if (this._lob_loaded == IonReaderTextRawX.LOB_STATE.READ) {
            long length = this._current_value_save_point.length();
            if (length < 0 || length > 2147483647L) {
                load_lob_length_overflow_error(length);
                throw null;
            }
            int i = (int) length;
            this._lob_bytes = new byte[i];
            try {
                this._scanner.save_point_activate(this._current_value_save_point);
                this._lob_actual_len = readBytes(this._lob_bytes, 0, i);
                this._scanner.save_point_deactivate(this._current_value_save_point);
                this._lob_loaded = IonReaderTextRawX.LOB_STATE.FINISHED;
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        }
        return this._lob_actual_len;
    }

    public final void load_lob_length_overflow_error(long j) {
        throw new IonException("Size overflow: " + this._value_type.toString() + " size (" + Long.toString(j) + ") exceeds int ");
    }

    public final long load_lob_save_point() throws IOException {
        if (this._lob_loaded == IonReaderTextRawX.LOB_STATE.EMPTY) {
            this._scanner.save_point_start(this._current_value_save_point);
            this._scanner.skip_over_lob(this._lob_token, this._current_value_save_point);
            this._current_value_save_point_loaded = true;
            tokenValueIsFinished();
            this._lob_loaded = IonReaderTextRawX.LOB_STATE.READ;
        }
        return this._current_value_save_point.length();
    }

    public final void load_or_cast_cached_value(int i) {
        load_once();
        if (i == 0 || this._v.hasValueOfType(i)) {
            return;
        }
        cast_cached_value(i);
    }

    @Override // com.amazon.ion.IonReader
    public long longValue() {
        checkIsIntApplicableType();
        load_or_cast_cached_value(4);
        return this._v.getLong();
    }

    @Override // com.amazon.ion.IonReader
    public byte[] newBytes() {
        ensureLob("newBytes");
        try {
            int iLoad_lob_contents = load_lob_contents();
            byte[] bArr = new byte[iLoad_lob_contents];
            System.arraycopy(this._lob_bytes, 0, bArr, 0, iLoad_lob_contents);
            return bArr;
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.ion.impl._Private_ReaderWriter
    public SymbolTable pop_passed_symbol_table() {
        return null;
    }

    public final void resolveAnnotationSymbols(int i) {
        SymbolTable symbolTable = getSymbolTable();
        for (int i2 = 0; i2 < i; i2++) {
            SymbolToken symbolToken = this._annotations[i2];
            SymbolToken symbolTokenLocalize = _Private_Utils.localize(symbolTable, symbolToken);
            if (symbolTokenLocalize != symbolToken) {
                this._annotations[i2] = symbolTokenLocalize;
            }
        }
    }

    @Override // com.amazon.ion.IonReader
    public final String stringValue() {
        if (!IonType.isText(this._value_type)) {
            throw new IllegalStateException("Unexpected value type: " + this._value_type);
        }
        if (this._v.hasValueOfType(1)) {
            return null;
        }
        load_or_cast_cached_value(8);
        String string = this._v.getString();
        if (string != null) {
            return string;
        }
        throw new UnknownSymbolException(this._v.getInt());
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken symbolValue() {
        if (this._value_type != IonType.SYMBOL) {
            throw new IllegalStateException("Unexpected value type: " + this._value_type);
        }
        if (this._v.hasValueOfType(1)) {
            return null;
        }
        load_or_cast_cached_value(8);
        if (!this._v.hasValueOfType(3)) {
            cast_cached_value(3);
        }
        return new SymbolTokenImpl(this._v.getString(), this._v.getInt());
    }

    @Override // com.amazon.ion.IonReader
    public Timestamp timestampValue() {
        load_or_cast_cached_value(10);
        if (this._v.hasValueOfType(1)) {
            return null;
        }
        return this._v.getTimestamp();
    }
}
