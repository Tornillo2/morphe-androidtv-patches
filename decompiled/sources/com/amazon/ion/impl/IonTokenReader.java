package com.amazon.ion.impl;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import com.amazon.ion.Decimal;
import com.amazon.ion.IonException;
import com.amazon.ion.Timestamp;
import com.amazon.ion.UnexpectedEofException;
import com.amazon.ion.impl._Private_IonConstants;
import com.amazon.ion.util.IonTextUtils;
import com.android.billingclient.api.ProxyBillingActivity;
import com.google.android.gms.location.LocationRequest;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.builder.ToStringStyle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonTokenReader {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int EMPTY_ESCAPE_SEQUENCE = -2;
    public static int isConstant = 8;
    public static int isDecimal = 128;
    public static int isFloat = 64;
    public static int isKeyword = 2;
    public static int isNegInt = 32;
    public static int isPosInt = 16;
    public static int isPunctuation = 1;
    public static int isTag = 256;
    public static int isTypeName = 4;
    public Timestamp dateValue;
    public BigDecimal decimalValue;
    public Double doubleValue;
    public int embeddedSlash;
    public int endquote;
    public IonCharacterReader in;
    public boolean inQuotedContent;
    public BigInteger intValue;
    public boolean isIncomplete;
    public boolean isLongString;
    public boolean isNegative;
    public Type keyword;
    public LocalReader localReader;
    public NumberType numberType;
    public PushbackReader pushbackReader;
    public boolean quotedIdentifier;
    public String stringValue;
    public Type t;
    public StringBuilder value;
    public Stack<Context> contextStack = new Stack<>();
    public Context context = Context.NONE;

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonTokenReader$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$IonTokenReader$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$com$amazon$ion$impl$IonTokenReader$Type = iArr;
            try {
                iArr[Type.kwNan.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenReader$Type[Type.kwPosInf.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenReader$Type[Type.kwNegInf.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenReader$Type[Type.constNegInt.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenReader$Type[Type.constPosInt.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenReader$Type[Type.constFloat.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenReader$Type[Type.constDecimal.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonTokenReader$Type[Type.constTime.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Context {
        NONE,
        STRING,
        BLOB,
        CLOB,
        EXPRESSION,
        DATALIST,
        STRUCT
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum NumberType {
        NT_POSINT,
        NT_NEGINT,
        NT_HEX,
        NT_FLOAT,
        NT_DECIMAL,
        NT_DECIMAL_NEGATIVE_ZERO
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'eof' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Type {
        public static final /* synthetic */ Type[] $VALUES;
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final Type constDecimal;
        public static final Type constFloat;
        public static final Type constMemberName;
        public static final Type constNegInt;
        public static final Type constPosInt;
        public static final Type constString;
        public static final Type constSymbol;
        public static final Type constTime;
        public static final Type constUserTypeDecl;
        public static final Type eof;
        public static final Type kwFalse;
        public static final Type kwNan;
        public static final Type kwNegInf;
        public static final Type kwNull;
        public static final Type kwNullBlob;
        public static final Type kwNullBoolean;
        public static final Type kwNullClob;
        public static final Type kwNullDecimal;
        public static final Type kwNullFloat;
        public static final Type kwNullInt;
        public static final Type kwNullList;
        public static final Type kwNullNull;
        public static final Type kwNullSexp;
        public static final Type kwNullString;
        public static final Type kwNullStruct;
        public static final Type kwNullSymbol;
        public static final Type kwNullTimestamp;
        public static final Type kwPosInf;
        public static final Type kwTrue;
        public static final Type none;
        public static final Type tCloseCurly;
        public static final Type tCloseParen;
        public static final Type tCloseSquare;
        public static final Type tComma;
        public static final Type tDoubleQuote;
        public static final Type tOpenCurly;
        public static final Type tOpenDoubleCurly;
        public static final Type tOpenParen;
        public static final Type tOpenSquare;
        public static final Type tSingleQuote;
        public int flags;
        public _Private_IonConstants.HighNibble highNibble;
        public String image;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class timeinfo {
            public static Timestamp parse(String str) {
                try {
                    return Timestamp.valueOf(str.trim());
                } catch (IllegalArgumentException e) {
                    throw new IonException(e.getMessage(), e);
                }
            }
        }

        static {
            int i = IonTokenReader.isPunctuation;
            Type type = new Type("eof", 0, i, "<eof>");
            eof = type;
            Type type2 = new Type("tOpenParen", 1, i, "(");
            tOpenParen = type2;
            Type type3 = new Type("tCloseParen", 2, i, ")");
            tCloseParen = type3;
            Type type4 = new Type("tOpenSquare", 3, i, "[");
            tOpenSquare = type4;
            Type type5 = new Type("tCloseSquare", 4, i, "[");
            tCloseSquare = type5;
            Type type6 = new Type("tOpenCurly", 5, i, "{");
            tOpenCurly = type6;
            Type type7 = new Type("tCloseCurly", 6, i, "}");
            tCloseCurly = type7;
            Type type8 = new Type("tOpenDoubleCurly", 7, i, "{{");
            tOpenDoubleCurly = type8;
            Type type9 = new Type("tSingleQuote", 8, i, "'");
            tSingleQuote = type9;
            Type type10 = new Type("tDoubleQuote", 9, i, ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
            tDoubleQuote = type10;
            Type type11 = new Type("tComma", 10, i, ",");
            tComma = type11;
            int i2 = IonTokenReader.isConstant + IonTokenReader.isTag + IonTokenReader.isKeyword;
            _Private_IonConstants.HighNibble highNibble = _Private_IonConstants.HighNibble.hnBoolean;
            Type type12 = new Type("kwTrue", 11, i2, "true", highNibble);
            kwTrue = type12;
            int i3 = IonTokenReader.isConstant;
            int i4 = IonTokenReader.isTag;
            int i5 = IonTokenReader.isKeyword;
            Type type13 = new Type("kwFalse", 12, i3 + i4 + i5, "false", highNibble);
            kwFalse = type13;
            _Private_IonConstants.HighNibble highNibble2 = _Private_IonConstants.HighNibble.hnNull;
            Type type14 = new Type("kwNull", 13, i3 + i4 + i5, AbstractJsonLexerKt.NULL, highNibble2);
            kwNull = type14;
            int i6 = IonTokenReader.isConstant;
            int i7 = IonTokenReader.isTag;
            int i8 = IonTokenReader.isKeyword;
            Type type15 = new Type("kwNullNull", 14, i6 + i7 + i8, "null.null", highNibble2);
            kwNullNull = type15;
            _Private_IonConstants.HighNibble highNibble3 = _Private_IonConstants.HighNibble.hnPosInt;
            Type type16 = new Type("kwNullInt", 15, i6 + i7 + i8, "null.int", highNibble3);
            kwNullInt = type16;
            Type type17 = new Type("kwNullList", 16, IonTokenReader.isConstant + IonTokenReader.isTag + IonTokenReader.isKeyword, "null.list", _Private_IonConstants.HighNibble.hnList);
            kwNullList = type17;
            Type type18 = new Type("kwNullSexp", 17, IonTokenReader.isConstant + IonTokenReader.isTag + IonTokenReader.isKeyword, "null.sexp", _Private_IonConstants.HighNibble.hnSexp);
            kwNullSexp = type18;
            int i9 = IonTokenReader.isConstant + IonTokenReader.isTag + IonTokenReader.isKeyword;
            _Private_IonConstants.HighNibble highNibble4 = _Private_IonConstants.HighNibble.hnFloat;
            Type type19 = new Type("kwNullFloat", 18, i9, "null.float", highNibble4);
            kwNullFloat = type19;
            Type type20 = new Type("kwNullBlob", 19, IonTokenReader.isConstant + IonTokenReader.isTag + IonTokenReader.isKeyword, "null.blob", _Private_IonConstants.HighNibble.hnBlob);
            kwNullBlob = type20;
            Type type21 = new Type("kwNullClob", 20, IonTokenReader.isConstant + IonTokenReader.isTag + IonTokenReader.isKeyword, "null.clob", _Private_IonConstants.HighNibble.hnClob);
            kwNullClob = type21;
            int i10 = IonTokenReader.isConstant + IonTokenReader.isTag + IonTokenReader.isKeyword;
            _Private_IonConstants.HighNibble highNibble5 = _Private_IonConstants.HighNibble.hnString;
            Type type22 = new Type("kwNullString", 21, i10, "null.string", highNibble5);
            kwNullString = type22;
            Type type23 = new Type("kwNullStruct", 22, IonTokenReader.isConstant + IonTokenReader.isTag + IonTokenReader.isKeyword, "null.struct", _Private_IonConstants.HighNibble.hnStruct);
            kwNullStruct = type23;
            int i11 = IonTokenReader.isConstant + IonTokenReader.isTag + IonTokenReader.isKeyword;
            _Private_IonConstants.HighNibble highNibble6 = _Private_IonConstants.HighNibble.hnSymbol;
            Type type24 = new Type("kwNullSymbol", 23, i11, "null.symbol", highNibble6);
            kwNullSymbol = type24;
            int i12 = IonTokenReader.isConstant;
            int i13 = IonTokenReader.isTag;
            int i14 = IonTokenReader.isKeyword;
            Type type25 = new Type("kwNullBoolean", 24, i12 + i13 + i14, "null.bool", highNibble);
            kwNullBoolean = type25;
            _Private_IonConstants.HighNibble highNibble7 = _Private_IonConstants.HighNibble.hnDecimal;
            Type type26 = new Type("kwNullDecimal", 25, i12 + i13 + i14, "null.decimal", highNibble7);
            kwNullDecimal = type26;
            int i15 = IonTokenReader.isConstant + IonTokenReader.isTag + IonTokenReader.isKeyword;
            _Private_IonConstants.HighNibble highNibble8 = _Private_IonConstants.HighNibble.hnTimestamp;
            Type type27 = new Type("kwNullTimestamp", 26, i15, Timestamp.NULL_TIMESTAMP_IMAGE, highNibble8);
            kwNullTimestamp = type27;
            int i16 = IonTokenReader.isConstant;
            int i17 = IonTokenReader.isKeyword;
            Type type28 = new Type("kwNan", 27, i16 + i17, "nan", highNibble4);
            kwNan = type28;
            Type type29 = new Type("kwPosInf", 28, i16 + i17, "+inf", highNibble4);
            kwPosInf = type29;
            Type type30 = new Type("kwNegInf", 29, i16 + i17, "-inf", highNibble4);
            kwNegInf = type30;
            Type type31 = new Type("constNegInt", 30, i16 + IonTokenReader.isNegInt, "cNegInt", _Private_IonConstants.HighNibble.hnNegInt);
            constNegInt = type31;
            int i18 = IonTokenReader.isConstant;
            Type type32 = new Type("constPosInt", 31, i18 + IonTokenReader.isPosInt, "cPosInt", highNibble3);
            constPosInt = type32;
            Type type33 = new Type("constFloat", 32, i18 + IonTokenReader.isFloat, "cFloat", highNibble4);
            constFloat = type33;
            Type type34 = new Type("constDecimal", 33, i18 + IonTokenReader.isDecimal, "cDec", highNibble7);
            constDecimal = type34;
            Type type35 = new Type("constTime", 34, i18, "cTime", highNibble8);
            constTime = type35;
            int i19 = IonTokenReader.isTag;
            Type type36 = new Type("constString", 35, i18 + i19, "cString", highNibble5);
            constString = type36;
            Type type37 = new Type("constSymbol", 36, i18 + i19, "cSymbol", highNibble6);
            constSymbol = type37;
            Type type38 = new Type("constMemberName", 37, i18 + i19, "cMemberName", highNibble6);
            constMemberName = type38;
            Type type39 = new Type("constUserTypeDecl", 38, i18 + i19, "cUserTypeDecl", highNibble6);
            constUserTypeDecl = type39;
            Type type40 = new Type("none", 39, 0);
            none = type40;
            $VALUES = new Type[]{type, type2, type3, type4, type5, type6, type7, type8, type9, type10, type11, type12, type13, type14, type15, type16, type17, type18, type19, type20, type21, type22, type23, type24, type25, type26, type27, type28, type29, type30, type31, type32, type33, type34, type35, type36, type37, type38, type39, type40};
        }

        public Type(String str, int i) {
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }

        public _Private_IonConstants.HighNibble getHighNibble() {
            return this.highNibble;
        }

        public String getImage() {
            String str = this.image;
            return str == null ? name() : str;
        }

        public boolean isConstant() {
            return (this.flags & IonTokenReader.isConstant) != 0;
        }

        public boolean isKeyword() {
            return (this.flags & IonTokenReader.isKeyword) != 0;
        }

        public boolean isNumeric() {
            return (this.flags & (((IonTokenReader.isPosInt + IonTokenReader.isNegInt) + IonTokenReader.isFloat) + IonTokenReader.isDecimal)) != 0;
        }

        public Type setNumericValue(IonTokenReader ionTokenReader, String str) {
            switch (AnonymousClass1.$SwitchMap$com$amazon$ion$impl$IonTokenReader$Type[ordinal()]) {
                case 1:
                    ionTokenReader.doubleValue = Double.valueOf(Double.NaN);
                    return this;
                case 2:
                    ionTokenReader.doubleValue = Double.valueOf(Double.POSITIVE_INFINITY);
                    return this;
                case 3:
                    ionTokenReader.doubleValue = Double.valueOf(Double.NEGATIVE_INFINITY);
                    return this;
                case 4:
                case 5:
                    if (!NumberType.NT_HEX.equals(ionTokenReader.numberType)) {
                        ionTokenReader.intValue = new BigInteger(str, 10);
                        return this;
                    }
                    BigInteger bigInteger = new BigInteger(str, 16);
                    ionTokenReader.intValue = bigInteger;
                    if (this == constNegInt) {
                        ionTokenReader.intValue = bigInteger.negate();
                    }
                    return this;
                case 6:
                    ionTokenReader.doubleValue = Double.valueOf(Double.parseDouble(str));
                    return this;
                case 7:
                    String strReplace = str.replace('d', 'e');
                    if (strReplace == str) {
                        strReplace = str.replace('D', 'e');
                    }
                    ionTokenReader.decimalValue = Decimal.valueOf(strReplace);
                    return this;
                case 8:
                    ionTokenReader.dateValue = timeinfo.parse(str);
                    return this;
                default:
                    throw new AssertionError("Unknown op for numeric case: " + this);
            }
        }

        @Override // java.lang.Enum
        public String toString() {
            return getImage() != null ? getImage() : super.toString();
        }

        public Type(String str, int i, int i2) {
            this.flags = i2;
        }

        public Type(String str, int i, int i2, String str2) {
            this.flags = i2;
            this.image = str2;
        }

        public Type(String str, int i, int i2, String str2, _Private_IonConstants.HighNibble highNibble) {
            this.flags = i2;
            this.image = str2;
            this.highNibble = highNibble;
        }
    }

    public IonTokenReader(Reader reader) {
        Type type = Type.none;
        this.t = type;
        this.keyword = type;
        this.value = new StringBuilder();
        this.in = new IonCharacterReader(reader);
    }

    public static Type matchKeyword(StringBuilder sb, int i, int i2) throws IOException {
        int i3 = i + 1;
        char cCharAt = sb.charAt(i);
        if (cCharAt == 'f') {
            if (i2 != 5) {
                return null;
            }
            int i4 = i + 2;
            if (sb.charAt(i3) != 'a') {
                return null;
            }
            int i5 = i + 3;
            if (sb.charAt(i4) != 'l') {
                return null;
            }
            int i6 = i + 4;
            if (sb.charAt(i5) == 's' && sb.charAt(i6) == 'e') {
                return Type.kwFalse;
            }
            return null;
        }
        if (cCharAt != 'n') {
            if (cCharAt != 't' || i2 != 4) {
                return null;
            }
            int i7 = i + 2;
            if (sb.charAt(i3) != 'r') {
                return null;
            }
            int i8 = i + 3;
            if (sb.charAt(i7) == 'u' && sb.charAt(i8) == 'e') {
                return Type.kwTrue;
            }
            return null;
        }
        if (i2 == 4) {
            int i9 = i + 2;
            if (sb.charAt(i3) == 'u') {
                i3 = i + 3;
                if (sb.charAt(i9) == 'l') {
                    int i10 = i + 4;
                    if (sb.charAt(i3) == 'l') {
                        return Type.kwNull;
                    }
                    i3 = i10;
                }
            } else {
                i3 = i9;
            }
        }
        if (i2 != 3) {
            return null;
        }
        int i11 = i3 + 1;
        if (sb.charAt(i3) == 'a' && sb.charAt(i11) == 'n') {
            return Type.kwNan;
        }
        return null;
    }

    public static int readDigit(PushbackReader pushbackReader, int i, boolean z) throws IOException {
        int i2 = pushbackReader.read();
        if (i2 < 0) {
            pushbackReader.unread(i2);
            return -1;
        }
        int iDigit = Character.digit(i2, i);
        if (iDigit >= 0) {
            return iDigit;
        }
        if (!z) {
            pushbackReader.unread(i2);
            return -1;
        }
        throw new IonException("bad digit in escaped character '" + ((char) i2) + "'");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int readEscapedCharacter(java.io.PushbackReader r4, boolean r5) throws java.io.IOException, com.amazon.ion.UnexpectedEofException {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonTokenReader.readEscapedCharacter(java.io.PushbackReader, boolean):int");
    }

    public final void checkAndUnreadNumericStopper(int i) throws IOException {
        if (i != -1) {
            if (isValueTerminatingCharacter(i)) {
                unread(i);
                return;
            }
            throw new IonException(position() + ": Numeric value followed by invalid character " + IonTextUtils.printCodePointAsString(i));
        }
    }

    public void closeLongString() {
        this.isIncomplete = true;
        this.inQuotedContent = false;
        this.isLongString = true;
    }

    public void closeString() {
        this.isIncomplete = false;
        this.inQuotedContent = false;
        this.isLongString = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:
    
        unread(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void finishScanString(boolean r4) throws java.io.IOException {
        /*
            r3 = this;
        L0:
            com.amazon.ion.impl.IonCharacterReader r4 = r3.in
            int r4 = r4.read()
            r0 = -1
            if (r4 == r0) goto L65
            int r0 = r3.endquote
            if (r4 != r0) goto Le
            return
        Le:
            r0 = 92
            r1 = 0
            if (r4 != r0) goto L22
            com.amazon.ion.impl.IonCharacterReader r4 = r3.in
            int r4 = readEscapedCharacter(r4, r1)
            r0 = -2
            if (r4 == r0) goto L0
            java.lang.StringBuilder r0 = r3.value
            r0.appendCodePoint(r4)
            goto L0
        L22:
            r0 = 39
            if (r4 != r0) goto L4d
            boolean r2 = r3.isLongString
            if (r2 == 0) goto L4d
            boolean r2 = r3.twoMoreSingleQuotes()
            if (r2 == 0) goto L46
            r3.inQuotedContent = r1
            int r4 = r3.readIgnoreWhitespace()
            if (r4 != r0) goto L42
            boolean r0 = r3.twoMoreSingleQuotes()
            if (r0 == 0) goto L42
            r4 = 1
            r3.inQuotedContent = r4
            goto L0
        L42:
            r3.unread(r4)
            return
        L46:
            java.lang.StringBuilder r0 = r3.value
            char r4 = (char) r4
            r0.append(r4)
            goto L0
        L4d:
            boolean r0 = r3.isLongString
            if (r0 != 0) goto L5e
            r0 = 10
            if (r4 == r0) goto L56
            goto L5e
        L56:
            com.amazon.ion.IonException r4 = new com.amazon.ion.IonException
            java.lang.String r0 = "unexpected line terminator encountered in quoted string"
            r4.<init>(r0)
            throw r4
        L5e:
            java.lang.StringBuilder r0 = r3.value
            char r4 = (char) r4
            r0.append(r4)
            goto L0
        L65:
            com.amazon.ion.UnexpectedEofException r4 = new com.amazon.ion.UnexpectedEofException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonTokenReader.finishScanString(boolean):void");
    }

    public int getColumn() {
        return this.in.m_column;
    }

    public long getConsumedAmount() {
        return this.in.m_consumed;
    }

    public int getLineNumber() {
        return this.in.m_line;
    }

    public PushbackReader getPushbackReader() {
        if (this.localReader == null) {
            this.localReader = new LocalReader(this);
            this.pushbackReader = new PushbackReader(this.localReader, 11);
        }
        this.localReader.reset();
        return this.pushbackReader;
    }

    public String getValueString(boolean z) throws IOException {
        if (this.isIncomplete) {
            finishScanString(z);
            this.stringValue = this.value.toString();
            this.inQuotedContent = false;
        } else if (this.stringValue == null) {
            this.stringValue = this.value.toString();
        }
        return this.stringValue;
    }

    public final boolean isValueTerminatingCharacter(int i) throws IOException {
        if (i != 47) {
            return IonTextUtils.isNumericStop(i);
        }
        int i2 = this.in.read();
        unread(i2);
        return i2 == 47 || i2 == 42;
    }

    public void leaveOpenString(int i, boolean z) {
        if (i == -1) {
            throw new UnexpectedEofException();
        }
        this.isIncomplete = true;
        this.inQuotedContent = true;
        this.isLongString = z;
    }

    public boolean makeValidNumeric(Type type) throws IOException {
        String valueString = getValueString(false);
        try {
            type.setNumericValue(this, valueString);
            this.t = type;
            return type.isNumeric();
        } catch (NumberFormatException e) {
            throw new IonException(position() + ": invalid numeric value " + valueString, e);
        }
    }

    public Type next(boolean z) throws IOException {
        this.inQuotedContent = false;
        return next(readIgnoreWhitespace(), z);
    }

    public Context popContext() {
        Context contextPop = this.contextStack.pop();
        this.context = contextPop;
        return contextPop;
    }

    public String position() {
        return "line " + this.in.m_line + " column " + this.in.m_column;
    }

    public void pushContext(Context context) {
        this.contextStack.push(context);
        this.context = context;
    }

    public final int read() throws IOException {
        return this.in.read();
    }

    public int readDigits(int i, String str) throws IOException {
        int i2 = 0;
        while (true) {
            int i3 = this.in.read();
            if (!IonTextUtils.isDigit(i3, 10)) {
                return i3;
            }
            i2++;
            if (i2 > i) {
                StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("invalid format ", str, " too long at ");
                sbM.append(position());
                throw new IonException(sbM.toString());
            }
            this.value.append((char) i3);
        }
    }

    public boolean readIdentifierContents(int i) throws IOException {
        int i2;
        int escapedCharacter;
        boolean z = i == 39 || i == 34;
        this.inQuotedContent = z;
        this.quotedIdentifier = z;
        if (z) {
            while (true) {
                escapedCharacter = this.in.read();
                if (escapedCharacter < 0 || escapedCharacter == i) {
                    break;
                }
                if (escapedCharacter == 92) {
                    escapedCharacter = readEscapedCharacter(this.in, false);
                }
                if (escapedCharacter != -2) {
                    this.value.appendCodePoint(escapedCharacter);
                }
            }
            if (escapedCharacter == -1) {
                throw new IonException("end encountered before closing quote '\\" + ((char) this.endquote) + "'");
            }
            this.inQuotedContent = false;
        } else {
            this.value.append((char) i);
            while (true) {
                i2 = this.in.read();
                if (!_Private_IonTextAppender.isIdentifierPart(i2)) {
                    break;
                }
                this.value.append((char) i2);
            }
            unread(i2);
        }
        return this.quotedIdentifier;
    }

    public int readIgnoreWhitespace() throws IOException {
        int i;
        do {
            i = this.in.read();
            if (i == 47) {
                int i2 = this.in.read();
                if (i2 == 47) {
                    i = i2;
                    while (i != 10 && i != -1) {
                        i = this.in.read();
                    }
                } else if (i2 == 42) {
                    while (i2 != -1) {
                        i2 = this.in.read();
                        if (i2 == 42) {
                            i2 = this.in.read();
                            if (i2 == 47) {
                                break;
                            }
                            unread(i2);
                        }
                    }
                    i = this.in.read();
                } else {
                    unread(i2);
                }
            }
        } while (IonTextUtils.isWhitespace(i));
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0114, code lost:
    
        if (r4 != 101) goto L104;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.ion.impl.IonTokenReader.Type readNumber(int r17) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 598
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonTokenReader.readNumber(int):com.amazon.ion.impl.IonTokenReader$Type");
    }

    public void resetValue() {
        this.isIncomplete = false;
        this.stringValue = null;
        this.doubleValue = null;
        this.intValue = null;
        this.dateValue = null;
        this.decimalValue = null;
        this.isNegative = false;
        this.numberType = null;
        this.t = null;
        this.value.setLength(0);
    }

    public Type scanHexNumber() throws IOException {
        int i;
        this.value.setLength(0);
        boolean z = true;
        boolean z2 = false;
        while (true) {
            i = this.in.read();
            if (!IonTextUtils.isDigit(i, 16)) {
                break;
            }
            z &= i == 48;
            this.value.append((char) i);
            z2 = true;
        }
        if (!z2) {
            throw new IonException("badly formed hexadecimal number encountered at " + position());
        }
        checkAndUnreadNumericStopper(i);
        if (z && this.t == Type.constNegInt) {
            this.t = Type.constPosInt;
        }
        return this.t;
    }

    public Type scanIdentifier(int i) throws IOException {
        resetValue();
        this.t = Type.constSymbol;
        this.keyword = null;
        if (!readIdentifierContents(i)) {
            StringBuilder sb = this.value;
            Type typeMatchKeyword = matchKeyword(sb, 0, sb.length());
            this.keyword = typeMatchKeyword;
            if (typeMatchKeyword != null) {
                if (typeMatchKeyword == Type.kwNull) {
                    int i2 = this.in.read();
                    if (i2 == 46) {
                        int length = this.value.length();
                        this.value.append((char) i2);
                        int i3 = length + 1;
                        switch (IonTokenConstsX.keyword(this.value, i3, readIdentifierContents(this.in.read(), IonTokenConstsX.TN_MAX_NAME_LENGTH + 1) + length + 1)) {
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
                            case 15:
                                this.keyword = setNullType(this.value, i3, (r4.length() - length) - 1);
                                break;
                            default:
                                int length2 = this.value.length();
                                while (length2 > length) {
                                    length2--;
                                    unread(this.value.charAt(length2));
                                }
                                throw new IonException(position() + ": Expected Ion type after 'null.' but found: " + ((Object) this.value));
                        }
                    } else {
                        unread(i2);
                    }
                }
                Type type = this.keyword;
                this.t = type;
                return type;
            }
        }
        int ignoreWhitespace = readIgnoreWhitespace();
        if (ignoreWhitespace != 58) {
            unread(ignoreWhitespace);
        } else {
            int i4 = this.in.read();
            if (i4 != 58) {
                unread(i4);
                this.t = Type.constMemberName;
            } else {
                this.t = Type.constUserTypeDecl;
            }
        }
        return this.t;
    }

    public Type scanLongString() throws IOException {
        resetValue();
        this.endquote = -1;
        leaveOpenString(-2, true);
        return Type.constString;
    }

    public Type scanOperator(int i) throws IOException {
        resetValue();
        this.t = Type.constSymbol;
        this.keyword = null;
        this.value.append((char) i);
        while (true) {
            int i2 = this.in.read();
            if (!_Private_IonTextAppender.isOperatorPart(i2)) {
                unread(i2);
                return this.t;
            }
            this.value.append((char) i2);
        }
    }

    public Type scanString(int i, int i2) throws IOException {
        int i3;
        resetValue();
        if (i != 34) {
            throw new IonException("Programmer error! Only a quote should get you here.");
        }
        this.endquote = 34;
        while (true) {
            i3 = i2 - 1;
            if (i2 <= 0 || (i = this.in.read()) == -1) {
                break;
            }
            if (i == 10) {
                throw new IonException("unexpected line terminator encountered in quoted string");
            }
            if (i == 34) {
                break;
            }
            if (i != 92) {
                this.value.append((char) i);
            } else {
                i = readEscapedCharacter(this.in, false);
                if (i != -2) {
                    this.value.appendCodePoint(i);
                }
            }
            i2 = i3;
        }
        if (i3 == -1 || i != 34) {
            leaveOpenString(i, false);
        } else {
            closeString();
        }
        return Type.constString;
    }

    public Type scanTimestamp(int i) throws IOException {
        int digits;
        if (i == 84) {
            this.value.append((char) i);
            digits = this.in.read();
        } else {
            if (i != 45) {
                throw new IllegalStateException("invalid timestamp, expecting a dash here at " + position());
            }
            this.value.append((char) i);
            int digits2 = readDigits(2, "month");
            if (digits2 == 84) {
                this.value.append((char) digits2);
                digits = this.in.read();
            } else {
                if (digits2 != 45) {
                    throw new IonException("invalid timestamp, expecting month at " + position());
                }
                this.value.append((char) digits2);
                digits = readDigits(2, "day of month");
                if (digits == 84) {
                    this.value.append((char) digits);
                    int length = this.value.length();
                    int digits3 = readDigits(2, "hours");
                    if (length == this.value.length()) {
                        digits = digits3;
                    } else {
                        if (digits3 != 58) {
                            throw new IonException("invalid timestamp, expecting hours at " + position());
                        }
                        this.value.append((char) digits3);
                        digits = readDigits(2, "minutes");
                        if (digits == 58) {
                            this.value.append((char) digits);
                            digits = readDigits(2, "seconds");
                            if (digits == 46) {
                                this.value.append((char) digits);
                                digits = readDigits(32, "fractional seconds");
                            } else if (digits == 45 || digits == 43 || digits == 90) {
                            }
                        } else if (digits == 45 || digits == 43 || digits == 90) {
                        }
                    }
                    if (digits == 45 || digits == 43) {
                        this.value.append((char) digits);
                        int digits4 = readDigits(2, "timezone offset");
                        if (digits4 != 58) {
                            digits = digits4;
                        } else {
                            this.value.append((char) digits4);
                            digits = readDigits(2, "timezone offset");
                        }
                    } else if (digits == 90) {
                        this.value.append((char) digits);
                        digits = this.in.read();
                    }
                }
            }
        }
        checkAndUnreadNumericStopper(digits);
        return Type.constTime;
    }

    public Type setNullType(StringBuilder sb, int i, int i2) {
        if (i2 == 3) {
            int i3 = i + 1;
            if (sb.charAt(i) == 'i') {
                int i4 = i + 2;
                if (sb.charAt(i3) == 'n' && sb.charAt(i4) == 't') {
                    return Type.kwNullInt;
                }
            }
        } else if (i2 == 4) {
            int i5 = i + 1;
            char cCharAt = sb.charAt(i);
            if (cCharAt == 'b') {
                int i6 = i + 2;
                char cCharAt2 = sb.charAt(i5);
                if (cCharAt2 == 'l') {
                    int i7 = i + 3;
                    if (sb.charAt(i6) == 'o' && sb.charAt(i7) == 'b') {
                        return Type.kwNullBlob;
                    }
                } else if (cCharAt2 == 'o') {
                    int i8 = i + 3;
                    if (sb.charAt(i6) == 'o' && sb.charAt(i8) == 'l') {
                        return Type.kwNullBoolean;
                    }
                }
            } else if (cCharAt == 'c') {
                int i9 = i + 2;
                if (sb.charAt(i5) == 'l') {
                    int i10 = i + 3;
                    if (sb.charAt(i9) == 'o' && sb.charAt(i10) == 'b') {
                        return Type.kwNullClob;
                    }
                }
            } else if (cCharAt == 'l') {
                int i11 = i + 2;
                if (sb.charAt(i5) == 'i') {
                    int i12 = i + 3;
                    if (sb.charAt(i11) == 's' && sb.charAt(i12) == 't') {
                        return Type.kwNullList;
                    }
                }
            } else if (cCharAt == 'n') {
                int i13 = i + 2;
                if (sb.charAt(i5) == 'u') {
                    int i14 = i + 3;
                    if (sb.charAt(i13) == 'l' && sb.charAt(i14) == 'l') {
                        return Type.kwNullNull;
                    }
                }
            } else if (cCharAt == 's') {
                int i15 = i + 2;
                if (sb.charAt(i5) == 'e') {
                    int i16 = i + 3;
                    if (sb.charAt(i15) == 'x' && sb.charAt(i16) == 'p') {
                        return Type.kwNullSexp;
                    }
                }
            }
        } else if (i2 == 5) {
            int i17 = i + 1;
            if (sb.charAt(i) == 'f') {
                int i18 = i + 2;
                if (sb.charAt(i17) == 'l') {
                    int i19 = i + 3;
                    if (sb.charAt(i18) == 'o') {
                        int i20 = i + 4;
                        if (sb.charAt(i19) == 'a' && sb.charAt(i20) == 't') {
                            return Type.kwNullFloat;
                        }
                    }
                }
            }
        } else if (i2 == 6) {
            int i21 = i + 1;
            if (sb.charAt(i) == 's') {
                int i22 = i + 2;
                char cCharAt3 = sb.charAt(i21);
                if (cCharAt3 == 't') {
                    int i23 = i + 3;
                    if (sb.charAt(i22) == 'r') {
                        int i24 = i + 4;
                        char cCharAt4 = sb.charAt(i23);
                        if (cCharAt4 == 'i') {
                            int i25 = i + 5;
                            if (sb.charAt(i24) == 'n' && sb.charAt(i25) == 'g') {
                                return Type.kwNullString;
                            }
                        } else if (cCharAt4 == 'u') {
                            int i26 = i + 5;
                            if (sb.charAt(i24) == 'c' && sb.charAt(i26) == 't') {
                                return Type.kwNullStruct;
                            }
                        }
                    }
                } else if (cCharAt3 == 'y') {
                    int i27 = i + 3;
                    if (sb.charAt(i22) == 'm') {
                        int i28 = i + 4;
                        if (sb.charAt(i27) == 'b') {
                            int i29 = i + 5;
                            if (sb.charAt(i28) == 'o' && sb.charAt(i29) == 'l') {
                                return Type.kwNullSymbol;
                            }
                        }
                    }
                }
            }
        } else if (i2 == 7) {
            int i30 = i + 1;
            if (sb.charAt(i) == 'd') {
                int i31 = i + 2;
                if (sb.charAt(i30) == 'e') {
                    int i32 = i + 3;
                    if (sb.charAt(i31) == 'c') {
                        int i33 = i + 4;
                        if (sb.charAt(i32) == 'i') {
                            int i34 = i + 5;
                            if (sb.charAt(i33) == 'm') {
                                int i35 = i + 6;
                                if (sb.charAt(i34) == 'a' && sb.charAt(i35) == 'l') {
                                    return Type.kwNullDecimal;
                                }
                            }
                        }
                    }
                }
            }
        } else if (i2 == 9) {
            int i36 = i + 1;
            if (sb.charAt(i) == 't') {
                int i37 = i + 2;
                if (sb.charAt(i36) == 'i') {
                    int i38 = i + 3;
                    if (sb.charAt(i37) == 'm') {
                        int i39 = i + 4;
                        if (sb.charAt(i38) == 'e') {
                            int i40 = i + 5;
                            if (sb.charAt(i39) == 's') {
                                int i41 = i + 6;
                                if (sb.charAt(i40) == 't') {
                                    int i42 = i + 7;
                                    if (sb.charAt(i41) == 'a') {
                                        int i43 = i + 8;
                                        if (sb.charAt(i42) == 'm' && sb.charAt(i43) == 'p') {
                                            return Type.kwNullTimestamp;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("invalid null value '", sb.toString(), "' at ");
        sbM.append(position());
        throw new IonException(sbM.toString());
    }

    public final boolean twoMoreSingleQuotes() throws IOException {
        int i = this.in.read();
        if (i == 39) {
            int i2 = this.in.read();
            if (i2 == 39) {
                return true;
            }
            unread(i2);
        }
        unread(i);
        return false;
    }

    public void unread(int i) throws IOException {
        this.in.unread(i);
    }

    public final Type next(int i, boolean z) throws IOException {
        Type type = Type.none;
        this.t = type;
        this.isIncomplete = false;
        if (i == -1) {
            Type type2 = Type.eof;
            this.t = type2;
            return type2;
        }
        if (i == 34) {
            this.inQuotedContent = true;
            this.keyword = type;
            return scanString(i, 13);
        }
        if (i == 91) {
            Type type3 = Type.tOpenSquare;
            this.t = type3;
            return type3;
        }
        if (i == 93) {
            Type type4 = Type.tCloseSquare;
            this.t = type4;
            return type4;
        }
        if (i == 123) {
            int i2 = this.in.read();
            if (i2 == 123) {
                Type type5 = Type.tOpenDoubleCurly;
                this.t = type5;
                return type5;
            }
            unread(i2);
            Type type6 = Type.tOpenCurly;
            this.t = type6;
            return type6;
        }
        if (i != 125) {
            switch (i) {
                case 39:
                    int i3 = this.in.read();
                    if (i3 == 39) {
                        int i4 = this.in.read();
                        if (i4 == 39) {
                            return scanLongString();
                        }
                        unread(i4);
                        i3 = 39;
                    }
                    unread(i3);
                    this.inQuotedContent = true;
                    return scanIdentifier(i);
                case 40:
                    Type type7 = Type.tOpenParen;
                    this.t = type7;
                    return type7;
                case 41:
                    Type type8 = Type.tCloseParen;
                    this.t = type8;
                    return type8;
                default:
                    int i5 = ProxyBillingActivity.REQUEST_CODE_FIRST_PARTY_PURCHASE_FLOW;
                    int i6 = LocationRequest.PRIORITY_NO_POWER;
                    switch (i) {
                        case 43:
                            int i7 = this.in.read();
                            if (i7 == 105) {
                                int i8 = this.in.read();
                                if (i8 == 110) {
                                    int i9 = this.in.read();
                                    if (i9 == 102) {
                                        Type type9 = Type.kwPosInf;
                                        this.t = type9;
                                        return type9;
                                    }
                                    unread(i9);
                                } else {
                                    i5 = i8;
                                }
                                unread(i5);
                            } else {
                                i6 = i7;
                            }
                            unread(i6);
                            if (z) {
                                return scanOperator(i);
                            }
                            break;
                        case 44:
                            Type type10 = Type.tComma;
                            this.t = type10;
                            return type10;
                        case 45:
                            int i10 = this.in.read();
                            if (i10 >= 48 && i10 <= 57) {
                                unread(i10);
                                return readNumber(i);
                            }
                            if (i10 == 105) {
                                int i11 = this.in.read();
                                if (i11 == 110) {
                                    int i12 = this.in.read();
                                    if (i12 == 102) {
                                        Type type11 = Type.kwNegInf;
                                        this.t = type11;
                                        return type11;
                                    }
                                    unread(i12);
                                } else {
                                    i5 = i11;
                                }
                                unread(i5);
                            } else {
                                i6 = i10;
                            }
                            unread(i6);
                            if (z) {
                                return scanOperator(i);
                            }
                            break;
                        default:
                            switch (i) {
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                    return readNumber(i);
                                default:
                                    if (_Private_IonTextAppender.isIdentifierStart(i)) {
                                        return scanIdentifier(i);
                                    }
                                    if (z && _Private_IonTextAppender.isOperatorPart(i)) {
                                        return scanOperator(i);
                                    }
                                    break;
                            }
                            break;
                    }
                    throw new IonException("Unexpected character " + IonTextUtils.printCodePointAsString(i) + " encountered at line " + this.in.m_line + " column " + this.in.m_column);
            }
        }
        Type type12 = Type.tCloseCurly;
        this.t = type12;
        return type12;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LocalReader extends Reader {
        public int _sbavailable;
        public int _sboffset;
        public IonTokenReader _tr;

        public LocalReader(IonTokenReader ionTokenReader) {
            this._tr = ionTokenReader;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this._tr = null;
        }

        @Override // java.io.Reader
        public int read() throws IOException {
            if (this._sbavailable <= 0) {
                return this._tr.in.read();
            }
            StringBuilder sb = this._tr.value;
            int i = this._sboffset;
            this._sboffset = i + 1;
            this._sbavailable--;
            return sb.charAt(i);
        }

        @Override // java.io.Reader
        public void reset() {
            this._sboffset = 0;
            this._sbavailable = this._tr.value.length();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            int i3;
            int i4;
            int i5 = i2;
            while (true) {
                i3 = i5 - 1;
                if (i5 <= 0 || (i4 = read()) < 0) {
                    break;
                }
                cArr[i] = (char) i4;
                i5 = i3;
                i++;
            }
            return i2 - i3;
        }
    }

    public int readIdentifierContents(int i, int i2) throws IOException {
        this.value.append((char) i);
        int i3 = 1;
        while (i3 < i2) {
            int i4 = this.in.read();
            if (!_Private_IonTextAppender.isIdentifierPart(i4)) {
                unread(i4);
                return i3;
            }
            this.value.append((char) i4);
            i3++;
        }
        return i3;
    }
}
