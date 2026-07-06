package com.amazon.ion.util;

import androidx.media3.container.MdtaMetadataEntry;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.impl._Private_IonConstants;
import com.amazon.ion.impl._Private_IonTextAppender;
import com.amazon.ion.impl._Private_IonTextWriterBuilder;
import com.android.billingclient.api.ProxyBillingActivity;
import java.io.IOException;
import java.math.BigDecimal;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonTextUtils {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum EscapeMode {
        JSON,
        ION_SYMBOL,
        ION_STRING,
        ION_LONG_STRING
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum SymbolVariant {
        IDENTIFIER,
        OPERATOR,
        QUOTED
    }

    public static boolean isAllWhitespace(CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (!isWhitespace(Character.codePointAt(charSequence, i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDigit(int i, int i2) {
        switch (i) {
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                return i2 == 8 || i2 == 10 || i2 == 16;
            case 56:
            case 57:
                return i2 == 10 || i2 == 16;
            default:
                switch (i) {
                    case 65:
                    case 66:
                    case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    case 68:
                    case 69:
                    case 70:
                        break;
                    default:
                        switch (i) {
                            case 97:
                            case 98:
                            case 99:
                            case 100:
                            case ProxyBillingActivity.REQUEST_CODE_IN_APP_MESSAGE_FLOW /* 101 */:
                            case 102:
                                break;
                            default:
                                return false;
                        }
                        break;
                }
                return i2 == 16;
        }
    }

    public static boolean isIdentifierPart(int i) {
        return _Private_IonTextAppender.isIdentifierPart(i);
    }

    public static boolean isIdentifierStart(int i) {
        return _Private_IonTextAppender.isIdentifierStart(i);
    }

    public static boolean isNumericStop(int i) {
        if (i == -1 || i == 13 || i == 32 || i == 34 || i == 44 || i == 91 || i == 93 || i == 123 || i == 125 || i == 9 || i == 10) {
            return true;
        }
        switch (i) {
            case 39:
            case 40:
            case 41:
                return true;
            default:
                return false;
        }
    }

    public static boolean isOperatorPart(int i) {
        return _Private_IonTextAppender.isOperatorPart(i);
    }

    public static boolean isWhitespace(int i) {
        return i == 9 || i == 10 || i == 13 || i == 32;
    }

    public static void printBlob(Appendable appendable, byte[] bArr) throws IOException {
        if (bArr == null) {
            appendable.append("null.blob");
        } else {
            _Private_IonTextAppender.forAppendable(appendable).printBlob(_Private_IonTextWriterBuilder.STANDARD, bArr, 0, bArr.length);
        }
    }

    public static void printClob(Appendable appendable, byte[] bArr) throws IOException {
        if (bArr == null) {
            appendable.append("null.clob");
        } else {
            _Private_IonTextAppender.forAppendable(appendable).printClob(_Private_IonTextWriterBuilder.STANDARD, bArr, 0, bArr.length);
        }
    }

    public static void printCodePoint(Appendable appendable, int i, EscapeMode escapeMode) throws IOException {
        if (i == 0) {
            appendable.append(escapeMode == EscapeMode.JSON ? "\\u0000" : "\\0");
            return;
        }
        if (i != 34) {
            if (i != 39) {
                if (i == 92) {
                    appendable.append("\\\\");
                    return;
                }
                switch (i) {
                    case 7:
                        appendable.append(escapeMode == EscapeMode.JSON ? "\\u0007" : "\\a");
                        break;
                    case 8:
                        appendable.append("\\b");
                        break;
                    case 9:
                        appendable.append("\\t");
                        break;
                    case 10:
                        if (escapeMode != EscapeMode.ION_LONG_STRING) {
                            appendable.append("\\n");
                        } else {
                            appendable.append('\n');
                        }
                        break;
                    case 11:
                        appendable.append(escapeMode == EscapeMode.JSON ? "\\u000b" : "\\v");
                        break;
                    case 12:
                        appendable.append("\\f");
                        break;
                    case 13:
                        appendable.append("\\r");
                        break;
                }
                return;
            }
            if (escapeMode == EscapeMode.ION_SYMBOL || escapeMode == EscapeMode.ION_LONG_STRING) {
                appendable.append("\\'");
                return;
            }
        } else if (escapeMode == EscapeMode.JSON || escapeMode == EscapeMode.ION_STRING) {
            appendable.append("\\\"");
            return;
        }
        if (i < 32) {
            if (escapeMode == EscapeMode.JSON) {
                printCodePointAsFourHexDigits(appendable, i);
                return;
            } else {
                printCodePointAsTwoHexDigits(appendable, i);
                return;
            }
        }
        if (i < 127) {
            appendable.append((char) i);
            return;
        }
        if (i <= 255) {
            if (escapeMode == EscapeMode.JSON) {
                printCodePointAsFourHexDigits(appendable, i);
                return;
            } else {
                printCodePointAsTwoHexDigits(appendable, i);
                return;
            }
        }
        if (i <= 65535) {
            printCodePointAsFourHexDigits(appendable, i);
        } else if (escapeMode == EscapeMode.JSON) {
            printCodePointAsSurrogatePairHexDigits(appendable, i);
        } else {
            printCodePointAsEightHexDigits(appendable, i);
        }
    }

    public static void printCodePointAsEightHexDigits(Appendable appendable, int i) throws IOException {
        String hexString = Integer.toHexString(i);
        appendable.append(_Private_IonTextAppender.HEX_8_PREFIX);
        appendable.append(_Private_IonTextAppender.ZERO_PADDING[8 - hexString.length()]);
        appendable.append(hexString);
    }

    public static void printCodePointAsFourHexDigits(Appendable appendable, int i) throws IOException {
        String hexString = Integer.toHexString(i);
        appendable.append(_Private_IonTextAppender.HEX_4_PREFIX);
        appendable.append(_Private_IonTextAppender.ZERO_PADDING[4 - hexString.length()]);
        appendable.append(hexString);
    }

    public static String printCodePointAsString(int i) {
        StringBuilder sb = new StringBuilder(12);
        sb.append('\"');
        try {
            printStringCodePoint(sb, i);
            sb.append('\"');
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static void printCodePointAsSurrogatePairHexDigits(Appendable appendable, int i) throws IOException {
        for (char c : Character.toChars(i)) {
            printCodePointAsFourHexDigits(appendable, c);
        }
    }

    public static void printCodePointAsTwoHexDigits(Appendable appendable, int i) throws IOException {
        String hexString = Integer.toHexString(i);
        appendable.append("\\x");
        if (hexString.length() < 2) {
            appendable.append(_Private_IonTextAppender.ZERO_PADDING[2 - hexString.length()]);
        }
        appendable.append(hexString);
    }

    public static void printCodePoints(Appendable appendable, CharSequence charSequence, EscapeMode escapeMode) throws IOException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int iCharAt = charSequence.charAt(i);
            if (_Private_IonConstants.isHighSurrogate(iCharAt)) {
                i++;
                if (i < length) {
                    char cCharAt = charSequence.charAt(i);
                    if (_Private_IonConstants.isLowSurrogate(cCharAt)) {
                        iCharAt = _Private_IonConstants.makeUnicodeScalar(iCharAt, cCharAt);
                    }
                }
                throw new IllegalArgumentException("text is invalid UTF-16. It contains an unmatched high surrogate 0x" + Integer.toHexString(iCharAt) + " at index " + i);
            }
            if (_Private_IonConstants.isLowSurrogate(iCharAt)) {
                throw new IllegalArgumentException("text is invalid UTF-16. It contains an unmatched low surrogate 0x" + Integer.toHexString(iCharAt) + " at index " + i);
            }
            printCodePoint(appendable, iCharAt, escapeMode);
            i++;
        }
    }

    public static void printDecimal(Appendable appendable, BigDecimal bigDecimal) throws IOException {
        _Private_IonTextAppender.forAppendable(appendable).printDecimal(_Private_IonTextWriterBuilder.STANDARD, bigDecimal);
    }

    public static void printFloat(Appendable appendable, double d) throws IOException {
        _Private_IonTextAppender.forAppendable(appendable).printFloat(_Private_IonTextWriterBuilder.STANDARD, d);
    }

    public static void printJsonCodePoint(Appendable appendable, int i) throws IOException {
        printCodePoint(appendable, i, EscapeMode.JSON);
    }

    public static void printJsonString(Appendable appendable, CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendable.append(AbstractJsonLexerKt.NULL);
            return;
        }
        appendable.append('\"');
        printCodePoints(appendable, charSequence, EscapeMode.JSON);
        appendable.append('\"');
    }

    public static String printLongString(CharSequence charSequence) {
        if (charSequence == null) {
            return "null.string";
        }
        if (charSequence.length() == 0) {
            return "''''''";
        }
        StringBuilder sb = new StringBuilder(charSequence.length() + 6);
        try {
            printLongString(sb, charSequence);
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static void printQuotedSymbol(Appendable appendable, CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendable.append("null.symbol");
            return;
        }
        appendable.append('\'');
        printCodePoints(appendable, charSequence, EscapeMode.ION_SYMBOL);
        appendable.append('\'');
    }

    public static void printString(Appendable appendable, CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendable.append("null.string");
            return;
        }
        appendable.append('\"');
        printCodePoints(appendable, charSequence, EscapeMode.ION_STRING);
        appendable.append('\"');
    }

    public static void printStringCodePoint(Appendable appendable, int i) throws IOException {
        printCodePoint(appendable, i, EscapeMode.ION_STRING);
    }

    public static void printSymbol(Appendable appendable, CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendable.append("null.symbol");
        } else if (_Private_IonTextAppender.symbolNeedsQuoting(charSequence, true)) {
            printQuotedSymbol(appendable, charSequence);
        } else {
            appendable.append(charSequence);
        }
    }

    public static void printSymbolCodePoint(Appendable appendable, int i) throws IOException {
        printCodePoint(appendable, i, EscapeMode.ION_SYMBOL);
    }

    public static SymbolVariant symbolVariant(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0 || _Private_IonTextAppender.isIdentifierKeyword(charSequence)) {
            return SymbolVariant.QUOTED;
        }
        int i = 0;
        char cCharAt = charSequence.charAt(0);
        if (!_Private_IonTextAppender.isIdentifierStart(cCharAt)) {
            if (!_Private_IonTextAppender.isOperatorPart(cCharAt)) {
                return SymbolVariant.QUOTED;
            }
            while (i < length) {
                if (!_Private_IonTextAppender.isOperatorPart(charSequence.charAt(i))) {
                    return SymbolVariant.QUOTED;
                }
                i++;
            }
            return SymbolVariant.OPERATOR;
        }
        while (i < length) {
            char cCharAt2 = charSequence.charAt(i);
            if (cCharAt2 == '\'' || cCharAt2 < ' ' || cCharAt2 > '~' || !_Private_IonTextAppender.isIdentifierPart(cCharAt2)) {
                return SymbolVariant.QUOTED;
            }
            i++;
        }
        return SymbolVariant.IDENTIFIER;
    }

    public static String printDecimal(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "null.decimal";
        }
        StringBuilder sb = new StringBuilder(64);
        try {
            _Private_IonTextAppender.forAppendable(sb).printDecimal(_Private_IonTextWriterBuilder.STANDARD, bigDecimal);
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static String printFloat(double d) {
        StringBuilder sb = new StringBuilder(64);
        try {
            _Private_IonTextAppender.forAppendable(sb).printFloat(_Private_IonTextWriterBuilder.STANDARD, d);
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static String printBlob(byte[] bArr) {
        if (bArr == null) {
            return "null.blob";
        }
        StringBuilder sb = new StringBuilder(64);
        try {
            printBlob(sb, bArr);
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static String printClob(byte[] bArr) {
        if (bArr == null) {
            return "null.clob";
        }
        StringBuilder sb = new StringBuilder(64);
        try {
            printClob(sb, bArr);
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static String printQuotedSymbol(CharSequence charSequence) {
        if (charSequence == null) {
            return "null.symbol";
        }
        StringBuilder sb = new StringBuilder(charSequence.length() + 2);
        try {
            printQuotedSymbol(sb, charSequence);
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static String printString(CharSequence charSequence) {
        if (charSequence == null) {
            return "null.string";
        }
        if (charSequence.length() == 0) {
            return "\"\"";
        }
        StringBuilder sb = new StringBuilder(charSequence.length() + 2);
        try {
            printString(sb, charSequence);
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static String printSymbol(CharSequence charSequence) {
        if (charSequence == null) {
            return "null.symbol";
        }
        StringBuilder sb = new StringBuilder(charSequence.length() + 2);
        try {
            printSymbol(sb, charSequence);
            return sb.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static void printLongString(Appendable appendable, CharSequence charSequence) throws IOException {
        if (charSequence == null) {
            appendable.append("null.string");
            return;
        }
        appendable.append(_Private_IonTextAppender.TRIPLE_QUOTES);
        printCodePoints(appendable, charSequence, EscapeMode.ION_LONG_STRING);
        appendable.append(_Private_IonTextAppender.TRIPLE_QUOTES);
    }

    public static void printFloat(Appendable appendable, Double d) throws IOException {
        _Private_IonTextAppender.forAppendable(appendable).printFloat(_Private_IonTextWriterBuilder.STANDARD, d);
    }

    public static String printFloat(Double d) {
        if (d == null) {
            return "null.float";
        }
        return printFloat(d.doubleValue());
    }

    public static String printSymbol(SymbolToken symbolToken) {
        return String.format("{$%s:%s}", symbolToken.getText(), Integer.valueOf(symbolToken.getSid()));
    }
}
