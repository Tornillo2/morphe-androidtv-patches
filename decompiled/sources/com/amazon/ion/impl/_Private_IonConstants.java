package com.amazon.ion.impl;

import com.amazon.ion.IonException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class _Private_IonConstants {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BB_INT64_LEN_MAX = 8;
    public static final int BB_MAX_7BIT_INT = 127;
    public static final int BB_TOKEN_LEN = 1;
    public static final int BB_VAR_INT32_LEN_MAX = 5;
    public static final int BB_VAR_INT64_LEN_MAX = 10;
    public static final int BB_VAR_LEN_MIN = 1;
    public static final byte[] BINARY_VERSION_MARKER_1_0;
    public static final int BINARY_VERSION_MARKER_SIZE;
    public static final int INT32_SIZE = 4;
    public static final String UNKNOWN_SYMBOL_TEXT_PREFIX = " -- UNKNOWN SYMBOL TEXT -- $";
    public static final int high_surrogate_value = 55296;
    public static final int lnBooleanFalse = 0;
    public static final int lnBooleanTrue = 1;
    public static final int lnIsEmptyContainer = 0;
    public static final int lnIsNull = 15;
    public static final int lnIsNullAtom = 15;
    public static final int lnIsNullSequence = 15;
    public static final int lnIsNullStruct = 15;
    public static final int lnIsOrderedStruct = 1;
    public static final int lnIsVarLen = 14;
    public static final int lnNumericZero = 0;
    public static final int low_surrogate_value = 56320;
    public static final int surrogate_mask = -1024;
    public static final int surrogate_utf32_offset = 65536;
    public static final int surrogate_utf32_shift = 10;
    public static final int surrogate_value_mask = 1023;
    public static final int tidBlob = 10;
    public static final int tidBoolean = 1;
    public static final int tidClob = 9;
    public static final int tidDATAGRAM = 16;
    public static final int tidDecimal = 5;
    public static final int tidFloat = 4;
    public static final int tidList = 11;
    public static final int tidNegInt = 3;
    public static final int tidNopPad = 99;
    public static final int tidNull = 0;
    public static final int tidPosInt = 2;
    public static final int tidSexp = 12;
    public static final int tidString = 8;
    public static final int tidStruct = 13;
    public static final int tidSymbol = 7;
    public static final int tidTimestamp = 6;
    public static final int tidTypedecl = 14;
    public static final int tidUnused = 15;
    public static final int MAX_LONG_TEXT_SIZE = Math.max(Long.toString(Long.MAX_VALUE).length(), Long.toString(Long.MIN_VALUE).length());
    public static final int True = 17;
    public static final int False = 16;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum HighNibble {
        hnNull(0, false, false),
        hnBoolean(1, false, false),
        hnPosInt(2, false, false),
        hnNegInt(3, false, false),
        hnFloat(4, false, false),
        hnDecimal(5, false, false),
        hnTimestamp(6, false, false),
        hnSymbol(7, false, false),
        hnString(8, false, false),
        hnClob(9, false, false),
        hnBlob(10, false, false),
        hnList(11, true, true),
        hnSexp(12, true, true),
        hnStruct(13, true, true),
        hnTypedecl(14, false, false),
        hnUnused(15, false, false);

        public boolean _isContainer;
        public boolean _lengthFollows;
        public int _value;

        HighNibble(int i, boolean z, boolean z2) {
            if ((i & (-16)) != 0) {
                throw new IonException("illegal high nibble initialization");
            }
            this._value = i;
            this._lengthFollows = z;
            this._isContainer = z2;
        }

        public static HighNibble getHighNibble(int i) {
            switch (i) {
                case 0:
                    return hnNull;
                case 1:
                    return hnBoolean;
                case 2:
                    return hnPosInt;
                case 3:
                    return hnNegInt;
                case 4:
                    return hnFloat;
                case 5:
                    return hnDecimal;
                case 6:
                    return hnTimestamp;
                case 7:
                    return hnSymbol;
                case 8:
                    return hnString;
                case 9:
                    return hnClob;
                case 10:
                    return hnBlob;
                case 11:
                    return hnList;
                case 12:
                    return hnSexp;
                case 13:
                    return hnStruct;
                case 14:
                    return hnTypedecl;
                case 15:
                    return hnUnused;
                default:
                    return null;
            }
        }

        public boolean isContainer() {
            return this._isContainer;
        }

        public boolean lengthAlwaysFollows() {
            return this._lengthFollows;
        }

        public int value() {
            return this._value;
        }
    }

    static {
        byte[] bArr = {-32, 1, 0, -22};
        BINARY_VERSION_MARKER_1_0 = bArr;
        BINARY_VERSION_MARKER_SIZE = bArr.length;
    }

    public static final int getLowNibble(int i) {
        return i & 15;
    }

    public static final int getTypeCode(int i) {
        return i >> 4;
    }

    public static final boolean isHighSurrogate(int i) {
        return (i & (-1024)) == 55296;
    }

    public static final boolean isLowSurrogate(int i) {
        return (i & (-1024)) == 56320;
    }

    public static final boolean isSurrogate(int i) {
        return (i & (-1024)) == 55296;
    }

    public static final int makeHighSurrogate(int i) {
        return ((i - 65536) >>> 10) | 55296;
    }

    public static final int makeLowSurrogate(int i) {
        return ((i - 65536) & 1023) | 56320;
    }

    public static final int makeTypeDescriptor(int i, int i2) {
        return (i << 4) | i2;
    }

    public static final int makeUnicodeScalar(int i, int i2) {
        return (((i & 1023) << 10) | (i2 & 1023)) + 65536;
    }
}
