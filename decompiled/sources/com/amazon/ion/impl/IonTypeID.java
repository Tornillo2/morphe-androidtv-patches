package com.amazon.ion.impl;

import com.amazon.ion.IonType;
import com.google.common.base.Ascii;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonTypeID {
    public static final int ANNOTATION_WRAPPER_MAX_LENGTH = 14;
    public static final int ANNOTATION_WRAPPER_MIN_LENGTH = 3;
    public static final int BITS_PER_NIBBLE = 4;
    public static final IonType[] ION_TYPES;
    public static final IonType ION_TYPE_ANNOTATION_WRAPPER;
    public static final int LOW_NIBBLE_BITMASK = 15;
    public static final int NEGATIVE_INT_TYPE_CODE = 3;
    public static final int NULL_VALUE_NIBBLE = 15;
    public static final int NUMBER_OF_BYTES = 256;
    public static final int ORDERED_STRUCT_NIBBLE = 1;
    public static final int TYPE_CODE_INVALID = 15;
    public static final IonTypeID[] TYPE_IDS;
    public static final int VARIABLE_LENGTH_NIBBLE = 14;
    public final boolean isNegativeInt;
    public final boolean isNopPad;
    public final boolean isNull;
    public final boolean isValid;
    public final byte length;
    public final byte lowerNibble;
    public final IonType type;
    public final boolean variableLength;

    static {
        IonType ionType = IonType.DATAGRAM;
        ION_TYPE_ANNOTATION_WRAPPER = ionType;
        IonType ionType2 = IonType.INT;
        ION_TYPES = new IonType[]{IonType.NULL, IonType.BOOL, ionType2, ionType2, IonType.FLOAT, IonType.DECIMAL, IonType.TIMESTAMP, IonType.SYMBOL, IonType.STRING, IonType.CLOB, IonType.BLOB, IonType.LIST, IonType.SEXP, IonType.STRUCT, ionType, null};
        TYPE_IDS = new IonTypeID[256];
        for (int i = 0; i < 256; i++) {
            TYPE_IDS[i] = new IonTypeID((byte) i);
        }
    }

    public IonTypeID(byte b) {
        byte b2 = (byte) ((b >> 4) & 15);
        byte b3 = (byte) (b & Ascii.SI);
        this.lowerNibble = b3;
        IonType ionType = ION_TYPES[b2];
        this.type = ionType;
        boolean zIsValid = isValid(b2, b3, ionType);
        this.isValid = zIsValid;
        boolean z = b3 == 15;
        this.isNull = z;
        IonType ionType2 = IonType.NULL;
        boolean z2 = ionType == ionType2 && !z;
        this.isNopPad = z2;
        if ((ionType == ionType2 && !z2) || ionType == IonType.BOOL || !zIsValid) {
            this.variableLength = false;
            b3 = 0;
        } else if (ionType == IonType.STRUCT && b3 == 1) {
            this.variableLength = true;
        } else {
            this.variableLength = b3 == 14;
        }
        b3 = z ? (byte) 0 : b3;
        this.isNegativeInt = ionType == IonType.INT && b2 == 3;
        this.length = b3;
    }

    public static boolean isValid(byte b, byte b2, IonType ionType) {
        if (b == 15) {
            return false;
        }
        if (ionType == IonType.BOOL) {
            return b2 <= 1 || b2 == 15;
        }
        if (ionType == IonType.INT && b == 3) {
            return b2 != 0;
        }
        if (ionType == IonType.FLOAT) {
            return b2 == 0 || b2 == 4 || b2 == 8 || b2 == 15;
        }
        if (ionType == IonType.TIMESTAMP) {
            return b2 > 1;
        }
        if (ionType == ION_TYPE_ANNOTATION_WRAPPER) {
            return b2 >= 3 && b2 <= 14;
        }
        return true;
    }
}
