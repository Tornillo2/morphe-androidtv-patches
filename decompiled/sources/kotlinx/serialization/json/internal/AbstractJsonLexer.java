package kotlinx.serialization.json.internal;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nAbstractJsonLexer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer$fail$1\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,763:1\n226#1,10:764\n755#1,5:775\n226#1,10:780\n226#1,10:792\n229#2:774\n229#2:790\n1#3:791\n*S KotlinDebug\n*F\n+ 1 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n*L\n206#1:764,10\n216#1:775,5\n223#1:780,10\n685#1:792,10\n206#1:774\n223#1:790\n*E\n"})
public abstract class AbstractJsonLexer {

    @JvmField
    public int currentPosition;

    @Nullable
    public String peekedString;

    @JvmField
    @NotNull
    public final JsonPath path = new JsonPath();

    @NotNull
    public StringBuilder escapedString = new StringBuilder();

    /* JADX INFO: renamed from: kotlinx.serialization.json.internal.AbstractJsonLexer$fail$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Function2<String, String, String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String expected, String source) {
            Intrinsics.checkNotNullParameter(expected, "expected");
            Intrinsics.checkNotNullParameter(source, "source");
            return "Expected " + expected + ", but had '" + source + "' instead";
        }
    }

    public static final double consumeNumericLiteral$calculateExponent(long j, boolean z) {
        if (!z) {
            return Math.pow(10.0d, -j);
        }
        if (z) {
            return Math.pow(10.0d, j);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* synthetic */ Void fail$default(AbstractJsonLexer abstractJsonLexer, String str, int i, String str2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
        }
        if ((i2 & 2) != 0) {
            i = abstractJsonLexer.currentPosition;
        }
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        abstractJsonLexer.fail(str, i, str2);
        throw null;
    }

    public static Void fail$kotlinx_serialization_json$default(AbstractJsonLexer abstractJsonLexer, byte b, boolean z, Function2 message, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            message = AnonymousClass1.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(message, "message");
        String str = AbstractJsonLexerKt.tokenDescription(b);
        int i2 = z ? abstractJsonLexer.currentPosition - 1 : abstractJsonLexer.currentPosition;
        fail$default(abstractJsonLexer, (String) message.invoke(str, (abstractJsonLexer.currentPosition == abstractJsonLexer.getSource().length() || i2 < 0) ? "EOF" : String.valueOf(abstractJsonLexer.getSource().charAt(i2))), i2, null, 4, null);
        throw null;
    }

    public static void require$kotlinx_serialization_json$default(AbstractJsonLexer abstractJsonLexer, boolean z, int i, Function0 message, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: require");
        }
        if ((i2 & 2) != 0) {
            i = abstractJsonLexer.currentPosition;
        }
        int i3 = i;
        Intrinsics.checkNotNullParameter(message, "message");
        if (z) {
            return;
        }
        fail$default(abstractJsonLexer, (String) message.invoke(), i3, null, 4, null);
        throw null;
    }

    public static /* synthetic */ boolean tryConsumeNull$default(AbstractJsonLexer abstractJsonLexer, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryConsumeNull");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        return abstractJsonLexer.tryConsumeNull(z);
    }

    public final int appendEsc(int i) {
        int iPrefetchOrEof = prefetchOrEof(i);
        if (iPrefetchOrEof == -1) {
            fail$default(this, "Expected escape sequence to continue, got EOF", 0, null, 6, null);
            throw null;
        }
        int i2 = iPrefetchOrEof + 1;
        char cCharAt = getSource().charAt(iPrefetchOrEof);
        if (cCharAt == 'u') {
            return appendHex(getSource(), i2);
        }
        char cEscapeToChar = AbstractJsonLexerKt.escapeToChar(cCharAt);
        if (cEscapeToChar != 0) {
            this.escapedString.append(cEscapeToChar);
            return i2;
        }
        fail$default(this, "Invalid escaped char '" + cCharAt + '\'', 0, null, 6, null);
        throw null;
    }

    public final int appendEscape(int i, int i2) {
        appendRange(i, i2);
        return appendEsc(i2 + 1);
    }

    public final int appendHex(CharSequence charSequence, int i) {
        int i2 = i + 4;
        if (i2 < charSequence.length()) {
            this.escapedString.append((char) (fromHexChar(charSequence, i + 3) + (fromHexChar(charSequence, i) << 12) + (fromHexChar(charSequence, i + 1) << 8) + (fromHexChar(charSequence, i + 2) << 4)));
            return i2;
        }
        this.currentPosition = i;
        ensureHaveChars();
        if (this.currentPosition + 4 < charSequence.length()) {
            return appendHex(charSequence, this.currentPosition);
        }
        fail$default(this, "Unexpected EOF during unicode escape", 0, null, 6, null);
        throw null;
    }

    public void appendRange(int i, int i2) {
        this.escapedString.append(getSource(), i, i2);
    }

    public abstract boolean canConsumeValue();

    public final boolean consumeBoolean() {
        return consumeBoolean(skipWhitespaces());
    }

    public final boolean consumeBooleanLenient() {
        boolean z;
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces == getSource().length()) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw null;
        }
        if (getSource().charAt(iSkipWhitespaces) == '\"') {
            iSkipWhitespaces++;
            z = true;
        } else {
            z = false;
        }
        boolean zConsumeBoolean = consumeBoolean(iSkipWhitespaces);
        if (!z) {
            return zConsumeBoolean;
        }
        if (this.currentPosition == getSource().length()) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw null;
        }
        if (getSource().charAt(this.currentPosition) == '\"') {
            this.currentPosition++;
            return zConsumeBoolean;
        }
        fail$default(this, "Expected closing quotation mark", 0, null, 6, null);
        throw null;
    }

    public final void consumeBooleanLiteral(String str, int i) {
        if (getSource().length() - i < str.length()) {
            fail$default(this, "Unexpected end of boolean literal", 0, null, 6, null);
            throw null;
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (str.charAt(i2) != (getSource().charAt(i + i2) | ' ')) {
                fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, null, 6, null);
                throw null;
            }
        }
        this.currentPosition = str.length() + i;
    }

    @NotNull
    public abstract String consumeKeyString();

    public abstract byte consumeNextToken();

    public final byte consumeNextToken(byte b) {
        byte bConsumeNextToken = consumeNextToken();
        if (bConsumeNextToken == b) {
            return bConsumeNextToken;
        }
        String str = AbstractJsonLexerKt.tokenDescription(b);
        int i = this.currentPosition;
        int i2 = i - 1;
        fail$default(this, "Expected " + str + ", but had '" + ((i == getSource().length() || i2 < 0) ? "EOF" : String.valueOf(getSource().charAt(i2))) + "' instead", i2, null, 4, null);
        throw null;
    }

    public abstract void consumeNextToken(char c);

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01ac, code lost:
    
        fail$default(r20, "Numeric value overflow", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01b7, code lost:
    
        throw r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01b8, code lost:
    
        fail$default(r20, "Expected numeric literal", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01c3, code lost:
    
        throw r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008b, code lost:
    
        if (r2 == r1) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0090, code lost:
    
        fail$default(r20, "Unexpected symbol '-' in numeric literal", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009b, code lost:
    
        throw r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0102, code lost:
    
        fail$default(r20, "Unexpected symbol '" + r4 + "' in numeric literal", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x011e, code lost:
    
        throw r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0121, code lost:
    
        if (r2 == r1) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0123, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0125, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0126, code lost:
    
        if (r1 == r2) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0128, code lost:
    
        if (r10 == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x012c, code lost:
    
        if (r1 == (r2 - 1)) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x012e, code lost:
    
        if (r0 == false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0130, code lost:
    
        if (r4 == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x013a, code lost:
    
        if (getSource().charAt(r2) != '\"') goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x013c, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0141, code lost:
    
        fail$default(r20, "Expected closing quotation mark", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x014c, code lost:
    
        throw r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x014d, code lost:
    
        fail$default(r20, "EOF", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0158, code lost:
    
        throw r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0159, code lost:
    
        r20.currentPosition = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x015b, code lost:
    
        if (r9 == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x015d, code lost:
    
        r3 = consumeNumericLiteral$calculateExponent(r13, r15) * r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0168, code lost:
    
        if (r3 > 9.223372036854776E18d) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x016e, code lost:
    
        if (r3 < (-9.223372036854776E18d)) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0176, code lost:
    
        if (java.lang.Math.floor(r3) != r3) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0178, code lost:
    
        r11 = (long) r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x017a, code lost:
    
        fail$default(r20, "Can't convert " + r3 + " to Long", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0194, code lost:
    
        throw r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0195, code lost:
    
        fail$default(r20, "Numeric value overflow", 0, null, 6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01a0, code lost:
    
        throw r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01a1, code lost:
    
        if (r10 == false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01a3, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01a8, code lost:
    
        if (r11 == Long.MIN_VALUE) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01ab, code lost:
    
        return -r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long consumeNumericLiteral() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 466
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.AbstractJsonLexer.consumeNumericLiteral():long");
    }

    public final long consumeNumericLiteralFully() throws Throwable {
        long jConsumeNumericLiteral = consumeNumericLiteral();
        if (consumeNextToken() == 10) {
            return jConsumeNumericLiteral;
        }
        int i = this.currentPosition;
        int i2 = i - 1;
        fail$default(this, MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Expected input to contain a single valid number, but got '", (i == getSource().length() || i2 < 0) ? "EOF" : String.valueOf(getSource().charAt(i2)), "' after it"), i2, null, 4, null);
        throw null;
    }

    @NotNull
    public final String consumeString() {
        return this.peekedString != null ? takePeeked() : consumeKeyString();
    }

    public void consumeStringChunked(boolean z, @NotNull Function1<? super String, Unit> consumeChunk) {
        int i;
        int iPrefetchOrEof;
        Intrinsics.checkNotNullParameter(consumeChunk, "consumeChunk");
        byte bPeekNextToken = peekNextToken();
        if (!z || bPeekNextToken == 0) {
            if (!z) {
                consumeNextToken('\"');
            }
            int i2 = this.currentPosition;
            char cCharAt = getSource().charAt(i2);
            boolean z2 = false;
            int i3 = i2;
            while (insideString(z, cCharAt)) {
                if (z || cCharAt != '\\') {
                    int i4 = i3 + 1;
                    i = i2;
                    iPrefetchOrEof = i4;
                } else {
                    iPrefetchOrEof = prefetchOrEof(appendEscape(i2, i3));
                    z2 = true;
                    i = iPrefetchOrEof;
                }
                if (iPrefetchOrEof >= getSource().length()) {
                    writeRange(i, iPrefetchOrEof, z2, consumeChunk);
                    int iPrefetchOrEof2 = prefetchOrEof(iPrefetchOrEof);
                    if (iPrefetchOrEof2 == -1) {
                        fail$default(this, "EOF", iPrefetchOrEof2, null, 4, null);
                        throw null;
                    }
                    i2 = iPrefetchOrEof2;
                    i3 = i2;
                    z2 = false;
                } else {
                    int i5 = i;
                    i3 = iPrefetchOrEof;
                    i2 = i5;
                }
                cCharAt = getSource().charAt(i3);
            }
            writeRange(i2, i3, z2, consumeChunk);
            this.currentPosition = i3;
            if (z) {
                return;
            }
            consumeNextToken('\"');
        }
    }

    @NotNull
    public final String consumeStringLenient() {
        if (this.peekedString != null) {
            return takePeeked();
        }
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= getSource().length() || iSkipWhitespaces == -1) {
            fail$default(this, "EOF", iSkipWhitespaces, null, 4, null);
            throw null;
        }
        byte bCharToTokenClass = AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iSkipWhitespaces));
        if (bCharToTokenClass == 1) {
            return consumeString();
        }
        if (bCharToTokenClass != 0) {
            fail$default(this, "Expected beginning of the string, but got " + getSource().charAt(iSkipWhitespaces), 0, null, 6, null);
            throw null;
        }
        boolean z = false;
        while (AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iSkipWhitespaces)) == 0) {
            iSkipWhitespaces++;
            if (iSkipWhitespaces >= getSource().length()) {
                appendRange(this.currentPosition, iSkipWhitespaces);
                int iPrefetchOrEof = prefetchOrEof(iSkipWhitespaces);
                if (iPrefetchOrEof == -1) {
                    this.currentPosition = iSkipWhitespaces;
                    return decodedString(0, 0);
                }
                iSkipWhitespaces = iPrefetchOrEof;
                z = true;
            }
        }
        String strSubstring = !z ? substring(this.currentPosition, iSkipWhitespaces) : decodedString(this.currentPosition, iSkipWhitespaces);
        this.currentPosition = iSkipWhitespaces;
        return strSubstring;
    }

    @NotNull
    public final String consumeStringLenientNotNull() {
        String strConsumeStringLenient = consumeStringLenient();
        if (!Intrinsics.areEqual(strConsumeStringLenient, AbstractJsonLexerKt.NULL) || !wasUnquotedString()) {
            return strConsumeStringLenient;
        }
        fail$default(this, "Unexpected 'null' value instead of string literal", 0, null, 6, null);
        throw null;
    }

    public final String decodedString(int i, int i2) {
        appendRange(i, i2);
        String string = this.escapedString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        this.escapedString.setLength(0);
        return string;
    }

    public final void discardPeeked() {
        this.peekedString = null;
    }

    public final void expectEof() {
        if (consumeNextToken() == 10) {
            return;
        }
        fail$default(this, "Expected EOF after parsing, but had " + getSource().charAt(this.currentPosition - 1) + " instead", 0, null, 6, null);
        throw null;
    }

    @NotNull
    public final Void fail(@NotNull String message, int i, @NotNull String hint) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(hint, "hint");
        String strConcat = hint.length() == 0 ? "" : StringUtils.LF.concat(hint);
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(message, " at path: ");
        sbM.append(this.path.getPath());
        sbM.append(strConcat);
        throw JsonExceptionsKt.JsonDecodingException(i, sbM.toString(), getSource());
    }

    @NotNull
    public final Void fail$kotlinx_serialization_json(byte b, boolean z, @NotNull Function2<? super String, ? super String, String> message) {
        Intrinsics.checkNotNullParameter(message, "message");
        String str = AbstractJsonLexerKt.tokenDescription(b);
        int i = z ? this.currentPosition - 1 : this.currentPosition;
        fail$default(this, message.invoke(str, (this.currentPosition == getSource().length() || i < 0) ? "EOF" : String.valueOf(getSource().charAt(i))), i, null, 4, null);
        throw null;
    }

    public final void failOnUnknownKey(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) substring(0, this.currentPosition), key, 0, false, 6, (Object) null);
        throw new JsonDecodingException("Encountered an unknown key '" + key + "' at offset " + iLastIndexOf$default + " at path: " + this.path.getPath() + "\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder or '@JsonIgnoreUnknownKeys' annotation to ignore unknown keys.\nJSON input: " + ((Object) JsonExceptionsKt.minify(getSource(), iLastIndexOf$default)));
    }

    public final int fromHexChar(CharSequence charSequence, int i) {
        char cCharAt = charSequence.charAt(i);
        if ('0' <= cCharAt && cCharAt < ':') {
            return cCharAt - '0';
        }
        if ('a' <= cCharAt && cCharAt < 'g') {
            return cCharAt - 'W';
        }
        if ('A' <= cCharAt && cCharAt < 'G') {
            return cCharAt - '7';
        }
        fail$default(this, "Invalid toHexChar char '" + cCharAt + "' in unicode escape", 0, null, 6, null);
        throw null;
    }

    @NotNull
    public final StringBuilder getEscapedString() {
        return this.escapedString;
    }

    @NotNull
    public abstract CharSequence getSource();

    public int indexOf(char c, int i) {
        return StringsKt__StringsKt.indexOf$default(getSource(), c, i, false, 4, (Object) null);
    }

    public final boolean insideString(boolean z, char c) {
        return z ? AbstractJsonLexerKt.charToTokenClass(c) == 0 : c != '\"';
    }

    public final boolean isNotEof() {
        return peekNextToken() != 10;
    }

    public final boolean isValidValueStart(char c) {
        return (c == ',' || c == ':' || c == ']' || c == '}') ? false : true;
    }

    public final boolean isWs(char c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t';
    }

    @Nullable
    public abstract String peekLeadingMatchingValue(@NotNull String str, boolean z);

    public byte peekNextToken() {
        CharSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof == -1) {
                this.currentPosition = iPrefetchOrEof;
                return (byte) 10;
            }
            char cCharAt = source.charAt(iPrefetchOrEof);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != ' ') {
                this.currentPosition = iPrefetchOrEof;
                return AbstractJsonLexerKt.charToTokenClass(cCharAt);
            }
            i = iPrefetchOrEof + 1;
        }
    }

    @Nullable
    public final String peekString(boolean z) {
        String strConsumeString;
        byte bPeekNextToken = peekNextToken();
        if (z) {
            if (bPeekNextToken != 1 && bPeekNextToken != 0) {
                return null;
            }
            strConsumeString = consumeStringLenient();
        } else {
            if (bPeekNextToken != 1) {
                return null;
            }
            strConsumeString = consumeString();
        }
        this.peekedString = strConsumeString;
        return strConsumeString;
    }

    public abstract int prefetchOrEof(int i);

    public final void require$kotlinx_serialization_json(boolean z, int i, @NotNull Function0<String> message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (z) {
            return;
        }
        fail$default(this, message.invoke(), i, null, 4, null);
        throw null;
    }

    public final void setEscapedString(@NotNull StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<set-?>");
        this.escapedString = sb;
    }

    public final void skipElement(boolean z) {
        ArrayList arrayList = new ArrayList();
        byte bPeekNextToken = peekNextToken();
        if (bPeekNextToken != 8 && bPeekNextToken != 6) {
            consumeStringLenient();
            return;
        }
        while (true) {
            byte bPeekNextToken2 = peekNextToken();
            if (bPeekNextToken2 != 1) {
                if (bPeekNextToken2 == 8 || bPeekNextToken2 == 6) {
                    arrayList.add(Byte.valueOf(bPeekNextToken2));
                } else if (bPeekNextToken2 == 9) {
                    if (((Number) CollectionsKt___CollectionsKt.last((List) arrayList)).byteValue() != 8) {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found ] instead of } at path: " + this.path, getSource());
                    }
                    CollectionsKt__MutableCollectionsKt.removeLast(arrayList);
                } else if (bPeekNextToken2 == 7) {
                    if (((Number) CollectionsKt___CollectionsKt.last((List) arrayList)).byteValue() != 6) {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found } instead of ] at path: " + this.path, getSource());
                    }
                    CollectionsKt__MutableCollectionsKt.removeLast(arrayList);
                } else if (bPeekNextToken2 == 10) {
                    fail$default(this, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, null, 6, null);
                    throw null;
                }
                consumeNextToken();
                if (arrayList.size() == 0) {
                    return;
                }
            } else if (z) {
                consumeStringLenient();
            } else {
                consumeKeyString();
            }
        }
    }

    public abstract int skipWhitespaces();

    @NotNull
    public String substring(int i, int i2) {
        return getSource().subSequence(i, i2).toString();
    }

    public final String takePeeked() {
        String str = this.peekedString;
        Intrinsics.checkNotNull(str);
        this.peekedString = null;
        return str;
    }

    @NotNull
    public String toString() {
        return "JsonReader(source='" + ((Object) getSource()) + "', currentPosition=" + this.currentPosition + ')';
    }

    public final boolean tryConsumeComma() {
        int iSkipWhitespaces = skipWhitespaces();
        CharSequence source = getSource();
        if (iSkipWhitespaces >= source.length() || iSkipWhitespaces == -1 || source.charAt(iSkipWhitespaces) != ',') {
            return false;
        }
        this.currentPosition++;
        return true;
    }

    public final boolean tryConsumeNull(boolean z) {
        int iPrefetchOrEof = prefetchOrEof(skipWhitespaces());
        int length = getSource().length() - iPrefetchOrEof;
        if (length >= 4 && iPrefetchOrEof != -1) {
            int i = 0;
            while (true) {
                if (i < 4) {
                    if (AbstractJsonLexerKt.NULL.charAt(i) != getSource().charAt(iPrefetchOrEof + i)) {
                        break;
                    }
                    i++;
                } else if (length <= 4 || AbstractJsonLexerKt.charToTokenClass(getSource().charAt(iPrefetchOrEof + 4)) != 0) {
                    if (z) {
                        this.currentPosition = iPrefetchOrEof + 4;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public final void unexpectedToken(char c) {
        int i = this.currentPosition;
        if (i > 0 && c == '\"') {
            try {
                this.currentPosition = i - 1;
                String strConsumeStringLenient = consumeStringLenient();
                this.currentPosition = i;
                if (Intrinsics.areEqual(strConsumeStringLenient, AbstractJsonLexerKt.NULL)) {
                    fail("Expected string literal but 'null' literal was found", this.currentPosition - 1, AbstractJsonLexerKt.coerceInputValuesHint);
                    throw null;
                }
            } catch (Throwable th) {
                this.currentPosition = i;
                throw th;
            }
        }
        String str = AbstractJsonLexerKt.tokenDescription(AbstractJsonLexerKt.charToTokenClass(c));
        int i2 = this.currentPosition;
        int i3 = i2 - 1;
        fail$default(this, "Expected " + str + ", but had '" + ((i2 == getSource().length() || i3 < 0) ? "EOF" : String.valueOf(getSource().charAt(i3))) + "' instead", i3, null, 4, null);
        throw null;
    }

    public final boolean wasUnquotedString() {
        return getSource().charAt(this.currentPosition - 1) != '\"';
    }

    public final <T> T withPositionRollback(Function0<? extends T> function0) {
        int i = this.currentPosition;
        try {
            return function0.invoke();
        } finally {
            this.currentPosition = i;
        }
    }

    public final void writeRange(int i, int i2, boolean z, Function1<? super String, Unit> function1) {
        if (z) {
            function1.invoke(decodedString(i, i2));
        } else {
            function1.invoke(substring(i, i2));
        }
    }

    public final boolean consumeBoolean(int i) {
        int iPrefetchOrEof = prefetchOrEof(i);
        if (iPrefetchOrEof >= getSource().length() || iPrefetchOrEof == -1) {
            fail$default(this, "EOF", 0, null, 6, null);
            throw null;
        }
        int i2 = iPrefetchOrEof + 1;
        int iCharAt = getSource().charAt(iPrefetchOrEof) | ' ';
        if (iCharAt == 102) {
            consumeBooleanLiteral("alse", i2);
            return false;
        }
        if (iCharAt == 116) {
            consumeBooleanLiteral("rue", i2);
            return true;
        }
        fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, null, 6, null);
        throw null;
    }

    @NotNull
    public final String consumeString(@NotNull CharSequence source, int i, int i2) {
        String strDecodedString;
        Intrinsics.checkNotNullParameter(source, "source");
        int iPrefetchOrEof = i;
        char cCharAt = source.charAt(i2);
        boolean z = false;
        int i3 = i2;
        while (cCharAt != '\"') {
            if (cCharAt == '\\') {
                iPrefetchOrEof = prefetchOrEof(appendEscape(iPrefetchOrEof, i3));
                if (iPrefetchOrEof == -1) {
                    fail$default(this, "Unexpected EOF", iPrefetchOrEof, null, 4, null);
                    throw null;
                }
            } else {
                i3++;
                if (i3 >= source.length()) {
                    appendRange(iPrefetchOrEof, i3);
                    iPrefetchOrEof = prefetchOrEof(i3);
                    if (iPrefetchOrEof == -1) {
                        fail$default(this, "Unexpected EOF", iPrefetchOrEof, null, 4, null);
                        throw null;
                    }
                } else {
                    continue;
                    cCharAt = source.charAt(i3);
                }
            }
            i3 = iPrefetchOrEof;
            z = true;
            cCharAt = source.charAt(i3);
        }
        if (!z) {
            strDecodedString = substring(iPrefetchOrEof, i3);
        } else {
            strDecodedString = decodedString(iPrefetchOrEof, i3);
        }
        this.currentPosition = i3 + 1;
        return strDecodedString;
    }

    public void ensureHaveChars() {
    }
}
