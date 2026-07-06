package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl._Private_ScalarConversions;
import com.android.billingclient.api.ProxyBillingActivity;
import com.google.android.gms.location.LocationRequest;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonTokenConstsX {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CLOB_CHARACTER_LIMIT = 255;
    public static final int ESCAPE_BIG_U = -15;
    public static final int ESCAPE_BIG_U_MINIMUM = 65536;
    public static final int ESCAPE_HEX = -16;
    public static final int ESCAPE_LITTLE_U = -14;
    public static final int ESCAPE_LITTLE_U_MINIMUM = 256;
    public static final int ESCAPE_NOT_DEFINED = -11;
    public static final int ESCAPE_REMOVES_NEWLINE = -12;
    public static final int ESCAPE_REMOVES_NEWLINE2 = -13;
    public static final int KEYWORD_BLOB = 11;
    public static final int KEYWORD_BOOL = 4;
    public static final int KEYWORD_CLOB = 12;
    public static final int KEYWORD_DECIMAL = 7;
    public static final int KEYWORD_FALSE = 2;
    public static final int KEYWORD_FLOAT = 6;
    public static final int KEYWORD_INT = 5;
    public static final int KEYWORD_LIST = 13;
    public static final int KEYWORD_NAN = 16;
    public static final int KEYWORD_NULL = 3;
    public static final int KEYWORD_SEXP = 14;
    public static final int KEYWORD_STRING = 10;
    public static final int KEYWORD_STRUCT = 15;
    public static final int KEYWORD_SYMBOL = 9;
    public static final int KEYWORD_TIMESTAMP = 8;
    public static final int KEYWORD_TRUE = 1;
    public static final int KEYWORD_none = 0;
    public static final int KEYWORD_sid = 17;
    public static final int KEYWORD_unrecognized = -1;
    public static final int KW_ALL_BITS = 8191;
    public static final int KW_BIT_BLOB = 1;
    public static final int KW_BIT_BOOL = 2;
    public static final int KW_BIT_CLOB = 4;
    public static final int KW_BIT_DECIMAL = 8;
    public static final int KW_BIT_FLOAT = 16;
    public static final int KW_BIT_INT = 32;
    public static final int KW_BIT_LIST = 48;
    public static final int KW_BIT_NULL = 128;
    public static final int KW_BIT_SEXP = 256;
    public static final int KW_BIT_STRING = 512;
    public static final int KW_BIT_STRUCT = 1024;
    public static final int KW_BIT_SYMBOL = 2048;
    public static final int KW_BIT_TIMESTAMP = 4096;
    public static final int TN_LETTER_MAX_IDX = 19;
    public static final int TN_MAX_NAME_LENGTH;
    public static final int TOKEN_BINARY = 26;
    public static final int TOKEN_CLOSE_BRACE = 21;
    public static final int TOKEN_CLOSE_DOUBLE_BRACE = 25;
    public static final int TOKEN_CLOSE_PAREN = 19;
    public static final int TOKEN_CLOSE_SQUARE = 23;
    public static final int TOKEN_COLON = 16;
    public static final int TOKEN_COMMA = 15;
    public static final int TOKEN_DECIMAL = 4;
    public static final int TOKEN_DOT = 14;
    public static final int TOKEN_DOUBLE_COLON = 17;
    public static final int TOKEN_EOF = 0;
    public static final int TOKEN_ERROR = -1;
    public static final int TOKEN_FLOAT = 5;
    public static final int TOKEN_FLOAT_INF = 6;
    public static final int TOKEN_FLOAT_MINUS_INF = 7;
    public static final int TOKEN_HEX = 3;
    public static final int TOKEN_INT = 2;
    public static final int TOKEN_MAX = 26;
    public static final int TOKEN_OPEN_BRACE = 20;
    public static final int TOKEN_OPEN_DOUBLE_BRACE = 24;
    public static final int TOKEN_OPEN_PAREN = 18;
    public static final int TOKEN_OPEN_SQUARE = 22;
    public static final int TOKEN_STRING_DOUBLE_QUOTE = 12;
    public static final int TOKEN_STRING_TRIPLE_QUOTE = 13;
    public static final int TOKEN_SYMBOL_IDENTIFIER = 9;
    public static final int TOKEN_SYMBOL_OPERATOR = 11;
    public static final int TOKEN_SYMBOL_QUOTED = 10;
    public static final int TOKEN_TIMESTAMP = 8;
    public static final int TOKEN_UNKNOWN_NUMERIC = 1;
    public static final int TOKEN_count = 27;
    public static final int[] TypeNameBitIndex;
    public static final int base64FillerCharacter = 61;
    public static final String[] escapeCharacterImage;
    public static final int[] escapeCharactersValues;
    public static final int[] hexValue;
    public static final boolean[] invalidTerminatingCharsForInf;
    public static final boolean[] isHexDigit;
    public static final boolean[] isValidExtendedSymbolCharacter;
    public static final boolean[] isValidStartSymbolCharacter;
    public static final boolean[] isValidSymbolCharacter;
    public static final int[] typeNameBits;
    public static final int[] typeNameKeyWordIds;
    public static final String[] typeNameNames;
    public static final char[] BLOB_TERMINATOR = {'}', '}'};
    public static final char[] CLOB_DOUBLE_QUOTED_TERMINATOR = {'\'', '\'', '\''};
    public static final char[] CLOB_TRIPLE_QUOTED_TERMINATOR = {'\"'};
    public static final boolean[] isBase64Character = makeBase64Array();

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonTokenConstsX$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType;

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
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr2 = new int[EscapeType.values().length];
            $SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType = iArr2;
            try {
                iArr2[EscapeType.ESCAPE_DESTINATION_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType[EscapeType.ESCAPE_DESTINATION_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType[EscapeType.ESCAPE_DESTINATION_SYMBOL.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType[EscapeType.ESCAPE_DESTINATION_CLOB.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CharacterSequence {
        public static final int CHAR_SEQ_EOF = -1;
        public static final int CHAR_SEQ_ESCAPED_NEWLINE_SEQUENCE_1 = -7;
        public static final int CHAR_SEQ_ESCAPED_NEWLINE_SEQUENCE_2 = -8;
        public static final int CHAR_SEQ_ESCAPED_NEWLINE_SEQUENCE_3 = -9;
        public static final int CHAR_SEQ_NEWLINE_SEQUENCE_1 = -4;
        public static final int CHAR_SEQ_NEWLINE_SEQUENCE_2 = -5;
        public static final int CHAR_SEQ_NEWLINE_SEQUENCE_3 = -6;
        public static final int CHAR_SEQ_STRING_NON_TERMINATOR = -3;
        public static final int CHAR_SEQ_STRING_TERMINATOR = -2;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum EscapeType {
        ESCAPE_DESTINATION_NONE,
        ESCAPE_DESTINATION_STRING,
        ESCAPE_DESTINATION_SYMBOL,
        ESCAPE_DESTINATION_CLOB
    }

    static {
        int[] iArrMakeHexValueArray = makeHexValueArray();
        hexValue = iArrMakeHexValueArray;
        isHexDigit = makeHexDigitTestArray(iArrMakeHexValueArray);
        escapeCharactersValues = makeEscapeCharacterValuesArray();
        escapeCharacterImage = makeEscapeCharacterImageArray();
        invalidTerminatingCharsForInf = makeInvalidTerminatingCharsForInfArray();
        isValidExtendedSymbolCharacter = makeIsValidExtendedSymbolCharacterArray();
        isValidSymbolCharacter = makeIsValidSymbolCharacterArray();
        isValidStartSymbolCharacter = makeIsValidStartSymbolCharacterArray();
        TN_MAX_NAME_LENGTH = 10;
        typeNameBits = new int[]{1, 2, 4, 8, 16, 32, 48, 128, 256, 512, 1024, 2048, 4096};
        typeNameNames = new String[]{"blob", "bool", "clob", "decimal", "float", "int", "list", AbstractJsonLexerKt.NULL, "sexp", "string", "struct", "symbol", "timestamp"};
        typeNameKeyWordIds = new int[]{11, 4, 12, 7, 6, 5, 13, 3, 14, 10, 15, 9, 8};
        TypeNameBitIndex = makeTypeNameBitIndex();
    }

    public static final int decimalDigitValue(int i) {
        if (isDigit(i)) {
            return i - 48;
        }
        throw new IllegalArgumentException("character '" + ((char) i) + "' is not a hex digit");
    }

    public static int decodeSid(CharSequence charSequence) {
        String string = charSequence.subSequence(1, charSequence.length()).toString();
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            throw new IonException(String.format("Unable to parse SID %s", string), e);
        }
    }

    public static final String describeToken(int i) {
        switch (i) {
            case 18:
                return "(";
            case 19:
                return ")";
            case 20:
                return "{";
            case 21:
                return "}";
            case 22:
                return "[";
            case 23:
                return "]";
            case 24:
                return "{{";
            case 25:
                return "}}";
            default:
                return getTokenName(i);
        }
    }

    public static final int escapeReplacementCharacter(int i) {
        if (isValidEscapeStart(i)) {
            return escapeCharactersValues[i];
        }
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("not a valid escape sequence character: ", i));
    }

    public static String escapeSequence(int i) {
        if (i >= 0 || i <= 1114111) {
            if (i < 128) {
                return escapeCharacterImage[i];
            }
            if (i < 65535) {
                String hexString = Integer.toHexString(i);
                int length = hexString.length();
                if (length < 4) {
                    hexString = "0000".substring(length);
                }
                return RemoteInput$$ExternalSyntheticOutline0.m(_Private_IonTextAppender.HEX_4_PREFIX, hexString);
            }
            if (i < 65535) {
                String hexString2 = Integer.toHexString(i);
                int length2 = hexString2.length();
                if (length2 < 4) {
                    hexString2 = "00000000".substring(length2);
                }
                return RemoteInput$$ExternalSyntheticOutline0.m(_Private_IonTextAppender.HEX_8_PREFIX, hexString2);
            }
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("the value ", i, " isn't a valid character"));
    }

    public static final String getEscapeCharacterImage(int i) {
        if (i < 0 || i > 255) {
            throw new IllegalArgumentException("character is outside escapable range (0-255 inclusive)");
        }
        return escapeCharacterImage[i];
    }

    public static final String getNullImage(IonType ionType) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) {
            case 1:
                return AbstractJsonLexerKt.NULL;
            case 2:
                return "null.bool";
            case 3:
                return "null.int";
            case 4:
                return "null.float";
            case 5:
                return "null.decimal";
            case 6:
                return Timestamp.NULL_TIMESTAMP_IMAGE;
            case 7:
                return "null.symbol";
            case 8:
                return "null.string";
            case 9:
                return "null.blob";
            case 10:
                return "null.clob";
            case 11:
                return "null.sexp";
            case 12:
                return "null.list";
            case 13:
                return "null.struct";
            default:
                throw new IllegalStateException("unexpected type " + ionType);
        }
    }

    public static final IonType getNullType(CharSequence charSequence) {
        IonType ionType;
        boolean z = false;
        boolean z2 = false;
        int i = 0;
        while (!z2 && i < charSequence.length()) {
            int i2 = i + 1;
            char cCharAt = charSequence.charAt(i);
            if (cCharAt == '\t' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ') {
                i = i2;
            } else {
                if (cCharAt != 'n') {
                    invalid_null_image(charSequence);
                    throw null;
                }
                i = i2;
                z2 = true;
            }
        }
        if (i < charSequence.length()) {
            int i3 = i + 1;
            if (charSequence.charAt(i) == 'u') {
                if (i3 < charSequence.length()) {
                    int i4 = i + 2;
                    if (charSequence.charAt(i3) == 'l') {
                        if (i4 < charSequence.length()) {
                            int i5 = i + 3;
                            if (charSequence.charAt(i4) == 'l') {
                                while (!z && i5 < charSequence.length()) {
                                    int i6 = i5 + 1;
                                    char cCharAt2 = charSequence.charAt(i5);
                                    if (cCharAt2 == '\t' || cCharAt2 == '\n' || cCharAt2 == '\r' || cCharAt2 == ' ') {
                                        i5 = i6;
                                    } else {
                                        if (cCharAt2 != '.') {
                                            invalid_null_image(charSequence);
                                            throw null;
                                        }
                                        i5 = i6;
                                        z = true;
                                    }
                                }
                                if (z) {
                                    switch (keyword(charSequence, i5, charSequence.length())) {
                                        case 3:
                                            ionType = IonType.NULL;
                                            break;
                                        case 4:
                                            ionType = IonType.BOOL;
                                            break;
                                        case 5:
                                            ionType = IonType.INT;
                                            break;
                                        case 6:
                                            ionType = IonType.FLOAT;
                                            break;
                                        case 7:
                                            ionType = IonType.DECIMAL;
                                            break;
                                        case 8:
                                            ionType = IonType.TIMESTAMP;
                                            break;
                                        case 9:
                                            ionType = IonType.SYMBOL;
                                            break;
                                        case 10:
                                            ionType = IonType.STRING;
                                            break;
                                        case 11:
                                            ionType = IonType.BLOB;
                                            break;
                                        case 12:
                                            ionType = IonType.CLOB;
                                            break;
                                        case 13:
                                            ionType = IonType.LIST;
                                            break;
                                        case 14:
                                            ionType = IonType.SEXP;
                                            break;
                                        case 15:
                                            ionType = IonType.STRUCT;
                                            break;
                                        default:
                                            invalid_null_image(charSequence);
                                            throw null;
                                    }
                                } else {
                                    ionType = null;
                                }
                                while (i5 < charSequence.length()) {
                                    int i7 = i5 + 1;
                                    char cCharAt3 = charSequence.charAt(i5);
                                    if (cCharAt3 != '\t' && cCharAt3 != '\n' && cCharAt3 != '\r' && cCharAt3 != ' ') {
                                        invalid_null_image(charSequence);
                                        throw null;
                                    }
                                    i5 = i7;
                                }
                                return ionType;
                            }
                        }
                        invalid_null_image(charSequence);
                        throw null;
                    }
                }
                invalid_null_image(charSequence);
                throw null;
            }
        }
        invalid_null_image(charSequence);
        throw null;
    }

    public static final String getTokenName(int i) {
        switch (i) {
            case -1:
                return "TOKEN_ERROR";
            case 0:
                return "TOKEN_EOF";
            case 1:
                return "TOKEN_UNKNOWN_NUMERIC";
            case 2:
                return "TOKEN_INT";
            case 3:
                return "TOKEN_HEX";
            case 4:
                return "TOKEN_DECIMAL";
            case 5:
                return "TOKEN_FLOAT";
            case 6:
                return "TOKEN_FLOAT_INF";
            case 7:
                return "TOKEN_FLOAT_MINUS_INF";
            case 8:
                return "TOKEN_TIMESTAMP";
            case 9:
                return "TOKEN_SYMBOL_IDENTIFIER";
            case 10:
                return "TOKEN_SYMBOL_QUOTED";
            case 11:
                return "TOKEN_SYMBOL_OPERATOR";
            case 12:
                return "TOKEN_STRING_DOUBLE_QUOTE";
            case 13:
                return "TOKEN_STRING_TRIPLE_QUOTE";
            case 14:
                return "TOKEN_DOT";
            case 15:
                return "TOKEN_COMMA";
            case 16:
                return "TOKEN_COLON";
            case 17:
                return "TOKEN_DOUBLE_COLON";
            case 18:
                return "TOKEN_OPEN_PAREN";
            case 19:
                return "TOKEN_CLOSE_PAREN";
            case 20:
                return "TOKEN_OPEN_BRACE";
            case 21:
                return "TOKEN_CLOSE_BRACE";
            case 22:
                return "TOKEN_OPEN_SQUARE";
            case 23:
                return "TOKEN_CLOSE_SQUARE";
            case 24:
                return "TOKEN_OPEN_DOUBLE_BRACE";
            case 25:
                return "TOKEN_CLOSE_DOUBLE_BRACE";
            default:
                return ObjectListKt$$ExternalSyntheticOutline1.m("<invalid token ", i, ">");
        }
    }

    public static final int hexDigitValue(int i) {
        if (isHexDigit(i)) {
            return hexValue[i];
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("character '" + ((char) i) + "' is not a hex digit");
        throw new IonException(illegalArgumentException.getMessage(), illegalArgumentException);
    }

    public static void invalid_null_image(CharSequence charSequence) {
        throw new _Private_ScalarConversions.CantConvertException("invalid image " + charSequence.toString());
    }

    public static final IonType ion_type_of_scalar(int i) {
        if (i == 2) {
            return IonType.INT;
        }
        if (i == 3) {
            return IonType.INT;
        }
        if (i == 4) {
            return IonType.DECIMAL;
        }
        if (i == 5) {
            return IonType.FLOAT;
        }
        if (i == 26) {
            return IonType.INT;
        }
        switch (i) {
        }
        return IonType.STRING;
    }

    public static final boolean is7bitValue(int i) {
        return (i & (-128)) == 0;
    }

    public static final boolean is8bitValue(int i) {
        return (i & (-256)) == 0;
    }

    public static final boolean isBinaryDigit(int i) {
        return i == 48 || i == 49;
    }

    public static final boolean isDigit(int i) {
        return i >= 48 && i <= 57;
    }

    public static final boolean isHexDigit(int i) {
        return isHexDigit[i & 255] && is8bitValue(i);
    }

    public static final boolean isValidEscapeStart(int i) {
        return escapeCharactersValues[i & 255] != -11 && is8bitValue(i);
    }

    public static final boolean isValidExtendedSymbolCharacter(int i) {
        return isValidExtendedSymbolCharacter[i & 255] && is8bitValue(i);
    }

    public static final boolean isValidStartSymbolCharacter(int i) {
        return isValidStartSymbolCharacter[i & 255] && is8bitValue(i);
    }

    public static final boolean isValidSymbolCharacter(int i) {
        return isValidSymbolCharacter[i & 255] && is8bitValue(i);
    }

    public static final boolean isValidTerminatingCharForInf(int i) {
        return (is8bitValue(i) && invalidTerminatingCharsForInf[i & 255]) ? false : true;
    }

    public static final boolean isWhitespace(int i) {
        return i == 32 || i == 9 || i == 10 || i == 13;
    }

    public static int keyword(CharSequence charSequence, int i, int i2) {
        char cCharAt = charSequence.charAt(i);
        int i3 = i2 - i;
        if (cCharAt == '$') {
            if (i3 <= 1) {
                return -1;
            }
            for (int i4 = i + 1; i4 < i2; i4++) {
                if (!isDigit(charSequence.charAt(i4))) {
                    return -1;
                }
            }
            return 17;
        }
        if (cCharAt == 'f') {
            if (i3 == 5) {
                int i5 = i + 1;
                if (charSequence.charAt(i5) == 'a' && charSequence.charAt(i + 2) == 'l' && charSequence.charAt(i + 3) == 's' && charSequence.charAt(i + 4) == 'e') {
                    return 2;
                }
                if (charSequence.charAt(i5) == 'l' && charSequence.charAt(i + 2) == 'o' && charSequence.charAt(i + 3) == 'a' && charSequence.charAt(i + 4) == 't') {
                    return 6;
                }
            }
            return -1;
        }
        if (cCharAt == 'i') {
            return (i3 == 3 && charSequence.charAt(i + 1) == 'n' && charSequence.charAt(i + 2) == 't') ? 5 : -1;
        }
        if (cCharAt == 'l') {
            return (i3 == 4 && charSequence.charAt(i + 1) == 'i' && charSequence.charAt(i + 2) == 's' && charSequence.charAt(i + 3) == 't') ? 13 : -1;
        }
        if (cCharAt == 'n') {
            if (i3 == 4) {
                if (charSequence.charAt(i + 1) == 'u' && charSequence.charAt(i + 2) == 'l' && charSequence.charAt(i + 3) == 'l') {
                    return 3;
                }
            } else if (i3 == 3 && charSequence.charAt(i + 1) == 'a' && charSequence.charAt(i + 2) == 'n') {
                return 16;
            }
            return -1;
        }
        if (cCharAt == 's') {
            if (i3 == 4) {
                if (charSequence.charAt(i + 1) == 'e' && charSequence.charAt(i + 2) == 'x' && charSequence.charAt(i + 3) == 'p') {
                    return 14;
                }
            } else if (i3 == 6) {
                int i6 = i + 1;
                if (charSequence.charAt(i6) == 't' && charSequence.charAt(i + 2) == 'r') {
                    int i7 = i + 3;
                    if (charSequence.charAt(i7) == 'i' && charSequence.charAt(i + 4) == 'n' && charSequence.charAt(i + 5) == 'g') {
                        return 10;
                    }
                    return (charSequence.charAt(i7) == 'u' && charSequence.charAt(i + 4) == 'c' && charSequence.charAt(i + 5) == 't') ? 15 : -1;
                }
                if (charSequence.charAt(i6) == 'y' && charSequence.charAt(i + 2) == 'm' && charSequence.charAt(i + 3) == 'b' && charSequence.charAt(i + 4) == 'o' && charSequence.charAt(i + 5) == 'l') {
                    return 9;
                }
            }
            return -1;
        }
        if (cCharAt != 't') {
            switch (cCharAt) {
                case 'b':
                    if (i3 == 4) {
                        int i8 = i + 1;
                        if (charSequence.charAt(i8) == 'o' && charSequence.charAt(i + 2) == 'o' && charSequence.charAt(i + 3) == 'l') {
                            return 4;
                        }
                        if (charSequence.charAt(i8) == 'l' && charSequence.charAt(i + 2) == 'o' && charSequence.charAt(i + 3) == 'b') {
                            return 11;
                        }
                    }
                    return -1;
                case 'c':
                    return (i3 == 4 && charSequence.charAt(i + 1) == 'l' && charSequence.charAt(i + 2) == 'o' && charSequence.charAt(i + 3) == 'b') ? 12 : -1;
                case 'd':
                    return (i3 == 7 && charSequence.charAt(i + 1) == 'e' && charSequence.charAt(i + 2) == 'c' && charSequence.charAt(i + 3) == 'i' && charSequence.charAt(i + 4) == 'm' && charSequence.charAt(i + 5) == 'a' && charSequence.charAt(i + 6) == 'l') ? 7 : -1;
                default:
                    return -1;
            }
        }
        if (i3 == 4) {
            if (charSequence.charAt(i + 1) == 'r' && charSequence.charAt(i + 2) == 'u' && charSequence.charAt(i + 3) == 'e') {
                return 1;
            }
        } else if (i3 == 9 && charSequence.charAt(i + 1) == 'i' && charSequence.charAt(i + 2) == 'm' && charSequence.charAt(i + 3) == 'e' && charSequence.charAt(i + 4) == 's' && charSequence.charAt(i + 5) == 't' && charSequence.charAt(i + 6) == 'a' && charSequence.charAt(i + 7) == 'm' && charSequence.charAt(i + 8) == 'p') {
            return 8;
        }
        return -1;
    }

    public static boolean[] makeBase64Array() {
        boolean[] zArr = new boolean[256];
        for (int i = 48; i <= 57; i++) {
            zArr[i] = true;
        }
        for (int i2 = 97; i2 <= 122; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            zArr[i3] = true;
        }
        zArr[43] = true;
        zArr[47] = true;
        return zArr;
    }

    public static final String[] makeEscapeCharacterImageArray() {
        String[] strArr = new String[256];
        for (int i = 0; i < 256; i++) {
            strArr[i] = null;
        }
        strArr[48] = "\\0";
        strArr[97] = "\\a";
        strArr[98] = "\\b";
        strArr[116] = "\\t";
        strArr[110] = "\\n";
        strArr[102] = "\\f";
        strArr[114] = "\\r";
        strArr[118] = "\\v";
        strArr[34] = "\\\"";
        strArr[39] = "\\'";
        strArr[63] = "\\?";
        strArr[92] = "\\\\";
        strArr[47] = "\\/";
        return strArr;
    }

    public static final int[] makeEscapeCharacterValuesArray() {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            iArr[i] = -11;
        }
        iArr[48] = 0;
        iArr[97] = 7;
        iArr[98] = 8;
        iArr[116] = 9;
        iArr[110] = 10;
        iArr[102] = 12;
        iArr[114] = 13;
        iArr[118] = 11;
        iArr[34] = 34;
        iArr[39] = 39;
        iArr[63] = 63;
        iArr[92] = 92;
        iArr[47] = 47;
        iArr[10] = -12;
        iArr[13] = -13;
        iArr[120] = -16;
        iArr[117] = -14;
        iArr[85] = -15;
        return iArr;
    }

    public static final boolean[] makeHexDigitTestArray(int[] iArr) {
        boolean[] zArr = new boolean[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            zArr[i] = iArr[i] >= 0;
        }
        return zArr;
    }

    public static final int[] makeHexValueArray() {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            iArr[i] = -1;
        }
        for (int i2 = 48; i2 <= 57; i2++) {
            iArr[i2] = i2 - 48;
        }
        for (int i3 = 97; i3 <= 102; i3++) {
            iArr[i3] = i3 - 87;
        }
        for (int i4 = 65; i4 <= 70; i4++) {
            iArr[i4] = i4 - 55;
        }
        return iArr;
    }

    public static final boolean[] makeInvalidTerminatingCharsForInfArray() {
        boolean[] zArr = new boolean[256];
        for (int i = 97; i <= 122; i++) {
            zArr[i] = true;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            zArr[i3] = true;
        }
        zArr[36] = true;
        zArr[95] = true;
        return zArr;
    }

    public static final boolean[] makeIsValidExtendedSymbolCharacterArray() {
        boolean[] zArr = new boolean[256];
        zArr[33] = true;
        zArr[35] = true;
        zArr[37] = true;
        zArr[38] = true;
        zArr[42] = true;
        zArr[43] = true;
        zArr[45] = true;
        zArr[46] = true;
        zArr[47] = true;
        zArr[59] = true;
        zArr[60] = true;
        zArr[61] = true;
        zArr[62] = true;
        zArr[63] = true;
        zArr[64] = true;
        zArr[94] = true;
        zArr[96] = true;
        zArr[124] = true;
        zArr[126] = true;
        return zArr;
    }

    public static final boolean[] makeIsValidStartSymbolCharacterArray() {
        boolean[] zArr = new boolean[256];
        for (int i = 97; i <= 122; i++) {
            zArr[i] = true;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            zArr[i2] = true;
        }
        zArr[36] = true;
        zArr[95] = true;
        return zArr;
    }

    public static final boolean[] makeIsValidSymbolCharacterArray() {
        boolean[] zArr = new boolean[256];
        for (int i = 97; i <= 122; i++) {
            zArr[i] = true;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            zArr[i3] = true;
        }
        zArr[36] = true;
        zArr[95] = true;
        return zArr;
    }

    public static final int[] makeTypeNameBitIndex() {
        int[] iArr = new int[TN_MAX_NAME_LENGTH * 19];
        int i = 0;
        while (true) {
            String[] strArr = typeNameNames;
            if (i >= strArr.length) {
                return iArr;
            }
            String str = strArr[i];
            int i2 = typeNameBits[i];
            for (int i3 = 0; i3 < str.length(); i3++) {
                typename_set_bit(iArr, i3, typeNameLetterIdx(str.charAt(i3)), i2);
            }
            i++;
        }
    }

    public static final boolean needsClobEscape(int i) {
        return i < 32 || i == 34 || i == 92 || i > 127;
    }

    public static final boolean needsIonEscape(EscapeType escapeType, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$ion$impl$IonTokenConstsX$EscapeType[escapeType.ordinal()];
        if (i2 == 1) {
            return false;
        }
        if (i2 == 2) {
            return needsStringEscape(i);
        }
        if (i2 == 3) {
            return needsSymbolEscape(i);
        }
        if (i2 == 4) {
            return needsClobEscape(i);
        }
        throw new IllegalArgumentException("escapeType " + escapeType + " is unrecognized");
    }

    public static final boolean needsStringEscape(int i) {
        return i < 32 || i == 34 || i == 92;
    }

    public static final boolean needsSymbolEscape(int i) {
        return i < 32 || i == 39 || i == 92;
    }

    public static final int typeNameKeyWordFromMask(int i, int i2) {
        if (i == 8191) {
            return -1;
        }
        int i3 = 0;
        while (true) {
            int[] iArr = typeNameBits;
            if (i3 >= iArr.length) {
                return -1;
            }
            if (iArr[i3] == i) {
                if (typeNameNames[i3].length() == i2) {
                    return typeNameKeyWordIds[i3];
                }
                return -1;
            }
            i3++;
        }
    }

    public static final int typeNameLetterIdx(int i) {
        switch (i) {
            case 97:
                return 1;
            case 98:
                return 2;
            case 99:
                return 3;
            case 100:
                return 4;
            case ProxyBillingActivity.REQUEST_CODE_IN_APP_MESSAGE_FLOW /* 101 */:
                return 5;
            case 102:
                return 6;
            case 103:
                return 7;
            case 104:
            case 106:
            case 107:
            case 113:
            case 118:
            case 119:
            default:
                return -1;
            case LocationRequest.PRIORITY_NO_POWER /* 105 */:
                return 8;
            case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR /* 108 */:
                return 9;
            case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY /* 109 */:
                return 10;
            case ProxyBillingActivity.REQUEST_CODE_FIRST_PARTY_PURCHASE_FLOW /* 110 */:
                return 11;
            case 111:
                return 12;
            case 112:
                return 13;
            case 114:
                return 14;
            case 115:
                return 15;
            case 116:
                return 16;
            case AbstractJsonLexerKt.ESC2C_MAX /* 117 */:
                return 17;
            case 120:
                return 18;
            case 121:
                return 19;
        }
    }

    public static final int typeNamePossibilityMask(int i, int i2) {
        return TypeNameBitIndex[((i * 19) + i2) - 1];
    }

    public static final void typename_set_bit(int[] iArr, int i, int i2, int i3) {
        int i4 = ((i * 19) + i2) - 1;
        iArr[i4] = iArr[i4] | i3;
    }
}
