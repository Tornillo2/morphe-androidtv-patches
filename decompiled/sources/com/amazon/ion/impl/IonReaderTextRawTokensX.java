package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.UnexpectedEofException;
import com.amazon.ion.impl.IonTokenConstsX;
import com.amazon.ion.impl.UnifiedSavePointManagerX;
import com.amazon.ion.util.IonTextUtils;
import com.android.billingclient.api.ProxyBillingActivity;
import com.google.android.gms.location.LocationRequest;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonReaderTextRawTokensX {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BASE64_EOF = 128;
    public static final boolean _debug = false;
    public int _base64_prefetch_count;
    public int _base64_prefetch_stack;
    public long _line_count;
    public long _line_count_cached;
    public boolean _line_count_has_cached;
    public long _line_offset_cached;
    public long _line_starting_position;
    public UnifiedInputStreamX _stream;
    public int _token;
    public boolean _unfinished_token;
    public static final Appendable NULL_APPENDABLE = new AnonymousClass1();
    public static final int[] BASE64_CHAR_TO_BIN = Base64Encoder.Base64EncodingCharToInt;
    public static final int BASE64_TERMINATOR_CHAR = Base64Encoder.Base64EncodingTerminator;

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderTextRawTokensX$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass1 implements Appendable {
        @Override // java.lang.Appendable
        public Appendable append(char c) throws IOException {
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence charSequence) throws IOException {
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
            return this;
        }
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderTextRawTokensX$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$impl$IonReaderTextRawTokensX$NumericState;

        static {
            int[] iArr = new int[NumericState.values().length];
            $SwitchMap$com$amazon$ion$impl$IonReaderTextRawTokensX$NumericState = iArr;
            try {
                iArr[NumericState.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderTextRawTokensX$NumericState[NumericState.DIGIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$impl$IonReaderTextRawTokensX$NumericState[NumericState.UNDERSCORE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum CommentStrategy {
        IGNORE { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy.1
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy
            public boolean onComment(IonReaderTextRawTokensX ionReaderTextRawTokensX) throws IOException {
                int i = ionReaderTextRawTokensX.read_char();
                if (i == 42) {
                    ionReaderTextRawTokensX.skip_block_comment();
                    return true;
                }
                if (i != 47) {
                    ionReaderTextRawTokensX.unread_char(i);
                    return false;
                }
                ionReaderTextRawTokensX.skip_single_line_comment();
                return true;
            }
        },
        ERROR { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy.2
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy
            public boolean onComment(IonReaderTextRawTokensX ionReaderTextRawTokensX) throws IOException {
                int i = ionReaderTextRawTokensX.read_char();
                if (i == 47 || i == 42) {
                    ionReaderTextRawTokensX.error("Illegal comment");
                    throw null;
                }
                ionReaderTextRawTokensX.unread_char(i);
                return false;
            }
        },
        BREAK { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy.3
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy
            public boolean onComment(IonReaderTextRawTokensX ionReaderTextRawTokensX) throws IOException {
                return false;
            }
        };

        public abstract boolean onComment(IonReaderTextRawTokensX ionReaderTextRawTokensX) throws IOException;

        CommentStrategy(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class IonReaderTextTokenException extends IonException {
        public static final long serialVersionUID = 1;

        public IonReaderTextTokenException(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum NumericState {
        START,
        UNDERSCORE,
        DIGIT
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ProhibitedCharacters {
        SHORT_CHAR { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.1
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters
            public boolean includes(int i) {
                return ProhibitedCharacters.isControlCharacter(i) && !ProhibitedCharacters.isWhitespace(i);
            }
        },
        LONG_CHAR { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.2
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters
            public boolean includes(int i) {
                return (!ProhibitedCharacters.isControlCharacter(i) || ProhibitedCharacters.isWhitespace(i) || ProhibitedCharacters.isNewline(i)) ? false : true;
            }
        },
        NONE { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.3
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters
            public boolean includes(int i) {
                return false;
            }
        };

        public static boolean isControlCharacter(int i) {
            return i <= 31 && i >= 0;
        }

        public static boolean isNewline(int i) {
            return i == 10 || i == 13;
        }

        public static boolean isWhitespace(int i) {
            return i == 9 || i == 11 || i == 12 || i == 32;
        }

        public abstract boolean includes(int i);

        ProhibitedCharacters(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Radix {
        BINARY { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.Radix.1
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            public boolean isPrefix(int i) {
                return i == 98 || i == 66;
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            public boolean isValidDigit(int i) {
                return IonTokenConstsX.isBinaryDigit(i);
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            public char normalizeDigit(char c) {
                return c;
            }
        },
        DECIMAL { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.Radix.2
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            public boolean isPrefix(int i) {
                return false;
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            public boolean isValidDigit(int i) {
                return IonTokenConstsX.isDigit(i);
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            public char normalizeDigit(char c) {
                return c;
            }
        },
        HEX { // from class: com.amazon.ion.impl.IonReaderTextRawTokensX.Radix.3
            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            public boolean isPrefix(int i) {
                return i == 120 || i == 88;
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            public boolean isValidDigit(int i) {
                return IonTokenConstsX.isHexDigit(i);
            }

            @Override // com.amazon.ion.impl.IonReaderTextRawTokensX.Radix
            public char normalizeDigit(char c) {
                return Character.toLowerCase(c);
            }
        };

        public static final /* synthetic */ boolean $assertionsDisabled = false;

        public abstract boolean isPrefix(int i);

        public abstract boolean isValidDigit(int i);

        public abstract char normalizeDigit(char c);

        Radix(AnonymousClass1 anonymousClass1) {
        }

        public void assertPrefix(int i) {
        }
    }

    public IonReaderTextRawTokensX(UnifiedInputStreamX unifiedInputStreamX) {
        this(unifiedInputStreamX, 1L, 1L);
    }

    public static final int decode_base64_byte1(int i, int i2, int i3, int i4) {
        return ((i << 2) & 252) | ((i2 >> 4) & 3);
    }

    public static final int decode_base64_byte2(int i, int i2, int i3, int i4) {
        return (((i2 << 4) & 240) | ((i3 >> 2) & 15)) & 255;
    }

    public static final int decode_base64_byte3(int i, int i2, int i3, int i4) {
        return (((i3 & 3) << 6) | (i4 & 63)) & 255;
    }

    public static final int decode_base64_length(int i, int i2, int i3, int i4) {
        if (i4 != 128) {
            return 3;
        }
        return i3 != 128 ? 2 : 1;
    }

    public final void bad_escape_sequence() {
        throw new IonReaderTextTokenException("bad escape character encountered " + input_position());
    }

    public final void bad_token(int i) {
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("a bad character ", IonTextUtils.printCodePointAsString(i), " was encountered ");
        sbM.append(input_position());
        throw new IonReaderTextTokenException(sbM.toString());
    }

    public final void bad_token_start(int i) {
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("bad character [", i, ", ");
        sbM.append(IonTextUtils.printCodePointAsString(i));
        sbM.append("] encountered where a token was supposed to start ");
        sbM.append(input_position());
        throw new IonReaderTextTokenException(sbM.toString());
    }

    public final boolean check_for_low_surrogate(int i, boolean z) throws IonException {
        if (!IonUTF8.isLowSurrogate(i)) {
            if (!z) {
                return false;
            }
            expected_but_found("a low surrogate", i);
            throw null;
        }
        if (z) {
            return false;
        }
        error("unexpected low surrogate " + IonTextUtils.printCodePointAsString(i));
        throw null;
    }

    public void close() throws IOException {
        this._stream.close();
    }

    public final void error(String str) {
        StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m(str);
        sbM.append(input_position());
        throw new IonReaderTextTokenException(sbM.toString());
    }

    public final void expected_but_found(String str, int i) {
        throw new IonReaderTextTokenException("Expected " + str + " but found " + IonTextUtils.printCodePointAsString(i) + input_position());
    }

    public final void finish_token(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        if (this._unfinished_token) {
            unread_char(skip_to_end(savePoint));
            this._unfinished_token = false;
        }
    }

    public long getLineNumber() {
        return this._line_count;
    }

    public long getLineOffset() {
        return this._stream.getPosition() - this._line_starting_position;
    }

    public UnifiedInputStreamX getSourceStream() {
        return this._stream;
    }

    public final long getStartingOffset() throws IOException {
        unread_char(this._unfinished_token ? skip_to_end(null) : skip_over_whitespace(CommentStrategy.IGNORE));
        return this._stream.getPosition();
    }

    public int getToken() {
        return this._token;
    }

    public String input_position() {
        return " at line " + this._line_count + " offset " + getLineOffset();
    }

    public final boolean isBufferedInput() {
        return !this._stream._is_stream;
    }

    public final boolean isUnfinishedToken() {
        return this._unfinished_token;
    }

    public final boolean is_2_single_quotes_helper() throws IOException {
        int i = read_char();
        if (i != 39) {
            unread_char(i);
            return false;
        }
        int i2 = read_char();
        if (i2 == 39) {
            return true;
        }
        unread_char(i2);
        unread_char(39);
        return false;
    }

    public final boolean is_value_terminating_character(int i) throws IOException {
        if (i != 47) {
            switch (i) {
                case -9:
                case IonTokenConstsX.CharacterSequence.CHAR_SEQ_ESCAPED_NEWLINE_SEQUENCE_2 /* -8 */:
                case -7:
                case -6:
                case -5:
                case -4:
                    return true;
                default:
                    return IonTextUtils.isNumericStop(i);
            }
        }
        int i2 = read_char();
        unread_char(i2);
        return i2 == 47 || i2 == 42;
    }

    public final int line_count(int i) throws IOException {
        int i2;
        if (i == 10) {
            i2 = -4;
        } else if (i == 13) {
            int i3 = this._stream.read();
            if (i3 == 10) {
                i2 = -6;
            } else {
                unread_char(i3);
                i2 = -5;
            }
        } else {
            if (i != 92) {
                throw new IllegalStateException();
            }
            int i4 = this._stream.read();
            if (i4 == 10) {
                i2 = -7;
            } else {
                if (i4 != 13) {
                    unread_char(i4);
                    return i;
                }
                int i5 = this._stream.read();
                if (i5 != 10) {
                    unread_char(i5);
                    i2 = -8;
                } else {
                    i2 = -9;
                }
            }
        }
        long j = this._line_count;
        this._line_count_cached = j;
        this._line_offset_cached = this._line_starting_position;
        this._line_count_has_cached = true;
        this._line_count = j + 1;
        this._line_starting_position = this._stream.getPosition() - 1;
        return i2;
    }

    public final int line_count_unread(int i) {
        if (this._line_count_has_cached) {
            this._line_count = this._line_count_cached;
            this._line_starting_position = this._line_offset_cached;
            this._line_count_has_cached = false;
        }
        return i;
    }

    public final int loadRadixValue(StringBuilder sb, boolean z, int i, Radix radix) throws IOException {
        radix.getClass();
        sb.append((char) i);
        return readNumeric(sb, radix, NumericState.START);
    }

    public void load_blob(StringBuilder sb) throws IOException {
        while (true) {
            int i = read_base64_byte();
            if (i == -1) {
                break;
            } else {
                sb.append(i);
            }
        }
        if (this._stream._eof) {
            unexpected_eof();
            throw null;
        }
        int i2 = read_char();
        if (i2 < 0) {
            unexpected_eof();
            throw null;
        }
        if (i2 == 125) {
            return;
        }
        error("improperly closed BLOB, " + IonTextUtils.printCodePointAsString(i2) + " encountered when '}' was expected");
        throw null;
    }

    public void load_clob(int i, StringBuilder sb) throws IOException {
        if (i == 12) {
            load_double_quoted_string(sb, true);
            return;
        }
        if (i == 13) {
            load_triple_quoted_string(sb, true);
            return;
        }
        if (i == 24) {
            load_blob(sb);
            return;
        }
        error("unexpected token " + IonTokenConstsX.getTokenName(i) + " encountered for lob content");
        throw null;
    }

    public final int load_digits(StringBuilder sb, int i) throws IOException {
        if (!IonTokenConstsX.isDigit(i)) {
            return i;
        }
        sb.append((char) i);
        return readNumeric(sb, Radix.DECIMAL, NumericState.DIGIT);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004d, code lost:
    
        if (r6 != false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004f, code lost:
    
        check_for_low_surrogate(r2, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0052, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int load_double_quoted_string(java.lang.StringBuilder r5, boolean r6) throws java.io.IOException {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
        L2:
            com.amazon.ion.impl.IonReaderTextRawTokensX$ProhibitedCharacters r2 = com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.SHORT_CHAR
            int r2 = r4.read_string_char(r2)
            r3 = -1
            if (r2 == r3) goto L4d
            r3 = 34
            if (r2 == r3) goto L4d
            r3 = 92
            if (r2 == r3) goto L28
            switch(r2) {
                case -9: goto L2;
                case -8: goto L2;
                case -7: goto L2;
                case -6: goto L23;
                case -5: goto L23;
                case -4: goto L23;
                default: goto L16;
            }
        L16:
            if (r6 != 0) goto L2c
            boolean r3 = com.amazon.ion.impl.IonTokenConstsX.is7bitValue(r2)
            if (r3 != 0) goto L2c
            int r2 = r4.read_large_char_sequence(r2)
            goto L2c
        L23:
            r4.bad_token(r2)
            r5 = 0
            throw r5
        L28:
            int r2 = r4.read_char_escaped(r2, r6)
        L2c:
            if (r6 != 0) goto L48
            r4.check_for_low_surrogate(r2, r1)
            boolean r1 = com.amazon.ion.impl.IonUTF8.needsSurrogateEncoding(r2)
            if (r1 == 0) goto L44
            char r1 = com.amazon.ion.impl.IonUTF8.highSurrogate(r2)
            r5.append(r1)
            char r2 = com.amazon.ion.impl.IonUTF8.lowSurrogate(r2)
            r1 = 0
            goto L48
        L44:
            boolean r1 = com.amazon.ion.impl.IonUTF8.isHighSurrogate(r2)
        L48:
            char r2 = (char) r2
            r5.append(r2)
            goto L2
        L4d:
            if (r6 != 0) goto L52
            r4.check_for_low_surrogate(r2, r1)
        L52:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.load_double_quoted_string(java.lang.StringBuilder, boolean):int");
    }

    public final int load_exponent(StringBuilder sb) throws IOException {
        int i = read_char();
        if (i == 45 || i == 43) {
            sb.append((char) i);
            i = read_char();
        }
        int iLoad_digits = load_digits(sb, i);
        if (iLoad_digits != 46) {
            return iLoad_digits;
        }
        sb.append((char) iLoad_digits);
        return load_digits(sb, read_char());
    }

    public final IonType load_finish_number(CharSequence charSequence, int i, int i2) throws IOException {
        if (is_value_terminating_character(i)) {
            unread_char(i);
            return IonTokenConstsX.ion_type_of_scalar(i2);
        }
        error("Numeric value followed by invalid character: " + ((Object) charSequence) + ((char) i));
        throw null;
    }

    public final void load_fixed_digits(StringBuilder sb, int i) throws IOException {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        while (i > 4) {
                            int i2 = read_char();
                            if (!IonTokenConstsX.isDigit(i2)) {
                                bad_token(i2);
                                throw null;
                            }
                            sb.append((char) i2);
                            i--;
                        }
                    }
                    int i3 = read_char();
                    if (!IonTokenConstsX.isDigit(i3)) {
                        bad_token(i3);
                        throw null;
                    }
                    sb.append((char) i3);
                }
                int i4 = read_char();
                if (!IonTokenConstsX.isDigit(i4)) {
                    bad_token(i4);
                    throw null;
                }
                sb.append((char) i4);
            }
            int i5 = read_char();
            if (!IonTokenConstsX.isDigit(i5)) {
                bad_token(i5);
                throw null;
            }
            sb.append((char) i5);
        }
        int i6 = read_char();
        if (IonTokenConstsX.isDigit(i6)) {
            sb.append((char) i6);
        } else {
            bad_token(i6);
            throw null;
        }
    }

    public IonType load_number(StringBuilder sb) throws IOException {
        int i;
        int i2 = read_char();
        boolean z = i2 == 45 || i2 == 43;
        if (z) {
            sb.append((char) i2);
            i2 = read_char();
        }
        if (!IonTokenConstsX.isDigit(i2)) {
            bad_token(i2);
            throw null;
        }
        boolean z2 = i2 == 48;
        if (z2) {
            int i3 = read_char();
            Radix radix = Radix.HEX;
            if (radix.isPrefix(i3)) {
                sb.append((char) i2);
                return load_finish_number(sb, loadRadixValue(sb, z, i3, radix), 3);
            }
            Radix radix2 = Radix.BINARY;
            if (radix2.isPrefix(i3)) {
                sb.append((char) i2);
                return load_finish_number(sb, loadRadixValue(sb, z, i3, radix2), 26);
            }
            unread_char(i3);
        }
        int iLoad_digits = load_digits(sb, i2);
        int i4 = 4;
        if (iLoad_digits == 45 || iLoad_digits == 84) {
            if (z) {
                error("Numeric value followed by invalid character: " + ((Object) sb) + ((char) iLoad_digits));
                throw null;
            }
            if (sb.length() == 4) {
                return load_timestamp(sb, iLoad_digits);
            }
            error("Numeric value followed by invalid character: " + ((Object) sb) + ((char) iLoad_digits));
            throw null;
        }
        if (z2) {
            int length = sb.length();
            if (z) {
                length--;
            }
            if (length != 1) {
                error("Invalid leading zero in number: " + ((Object) sb));
                throw null;
            }
        }
        if (iLoad_digits == 46) {
            sb.append((char) iLoad_digits);
            iLoad_digits = load_digits(sb, read_char());
            i = 4;
        } else {
            i = 2;
        }
        if (iLoad_digits == 101 || iLoad_digits == 69) {
            sb.append((char) iLoad_digits);
            iLoad_digits = load_exponent(sb);
            i4 = 5;
        } else if (iLoad_digits == 100 || iLoad_digits == 68) {
            sb.append((char) iLoad_digits);
            iLoad_digits = load_exponent(sb);
        } else {
            i4 = i;
        }
        return load_finish_number(sb, iLoad_digits, i4);
    }

    public void load_raw_characters(StringBuilder sb) throws IOException {
        read_char();
        while (true) {
            int iLowSurrogate = read_char();
            if (iLowSurrogate != -9 && iLowSurrogate != -8 && iLowSurrogate != -7) {
                if (iLowSurrogate == -1) {
                    return;
                }
                if (!IonTokenConstsX.is7bitValue(iLowSurrogate)) {
                    iLowSurrogate = read_large_char_sequence(iLowSurrogate);
                }
                if (IonUTF8.needsSurrogateEncoding(iLowSurrogate)) {
                    sb.append(IonUTF8.highSurrogate(iLowSurrogate));
                    iLowSurrogate = IonUTF8.lowSurrogate(iLowSurrogate);
                }
                sb.append((char) iLowSurrogate);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005c, code lost:
    
        if (r7 != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
    
        check_for_low_surrogate(r2, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0061, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int load_single_quoted_string(java.lang.StringBuilder r6, boolean r7) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
        L2:
            com.amazon.ion.impl.IonReaderTextRawTokensX$ProhibitedCharacters r2 = com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.NONE
            int r2 = r5.read_string_char(r2)
            r3 = -1
            if (r2 == r3) goto L5c
            r3 = 39
            if (r2 == r3) goto L5c
            r3 = 92
            r4 = 0
            if (r2 == r3) goto L28
            switch(r2) {
                case -9: goto L2;
                case -8: goto L2;
                case -7: goto L2;
                case -6: goto L24;
                case -5: goto L24;
                case -4: goto L24;
                default: goto L17;
            }
        L17:
            if (r7 != 0) goto L30
            boolean r3 = com.amazon.ion.impl.IonTokenConstsX.is7bitValue(r2)
            if (r3 != 0) goto L30
            int r2 = r5.read_large_char_sequence(r2)
            goto L30
        L24:
            r5.bad_token(r2)
            throw r4
        L28:
            int r2 = r5.read_char()
            int r2 = r5.read_escaped_char_content_helper(r2, r7)
        L30:
            if (r7 != 0) goto L4d
            r5.check_for_low_surrogate(r2, r1)
            boolean r1 = com.amazon.ion.impl.IonUTF8.needsSurrogateEncoding(r2)
            if (r1 == 0) goto L48
            char r1 = com.amazon.ion.impl.IonUTF8.highSurrogate(r2)
            r6.append(r1)
            char r2 = com.amazon.ion.impl.IonUTF8.lowSurrogate(r2)
            r1 = 0
            goto L53
        L48:
            boolean r1 = com.amazon.ion.impl.IonUTF8.isHighSurrogate(r2)
            goto L53
        L4d:
            boolean r3 = com.amazon.ion.impl.IonTokenConstsX.is8bitValue(r2)
            if (r3 != 0) goto L58
        L53:
            char r2 = (char) r2
            r6.append(r2)
            goto L2
        L58:
            r5.bad_token(r2)
            throw r4
        L5c:
            if (r7 != 0) goto L61
            r5.check_for_low_surrogate(r2, r1)
        L61:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.load_single_quoted_string(java.lang.StringBuilder, boolean):int");
    }

    public void load_symbol_identifier(StringBuilder sb) throws IOException {
        int i = read_char();
        while (IonTokenConstsX.isValidSymbolCharacter(i)) {
            sb.append((char) i);
            i = read_char();
        }
        unread_char(i);
    }

    public void load_symbol_operator(StringBuilder sb) throws IOException {
        int i = read_char();
        if ((i == 43 || i == 45) && peek_inf_helper(i)) {
            sb.append((char) i);
            sb.append("inf");
        } else {
            while (IonTokenConstsX.isValidExtendedSymbolCharacter(i)) {
                sb.append((char) i);
                i = read_char();
            }
            unread_char(i);
        }
    }

    public final IonType load_timestamp(StringBuilder sb, int i) throws IOException {
        int i2;
        sb.append((char) i);
        if (i == 84) {
            return load_finish_number(sb, read_char(), 8);
        }
        load_fixed_digits(sb, 2);
        int i3 = read_char();
        if (i3 == 84) {
            sb.append((char) i3);
            return load_finish_number(sb, read_char(), 8);
        }
        if (i3 != 45) {
            bad_token(i3);
            throw null;
        }
        sb.append((char) i3);
        load_fixed_digits(sb, 2);
        int i4 = read_char();
        if (i4 != 84) {
            return load_finish_number(sb, i4, 8);
        }
        sb.append((char) i4);
        int i5 = read_char();
        if (!IonTokenConstsX.isDigit(i5)) {
            return load_finish_number(sb, i5, 8);
        }
        sb.append((char) i5);
        load_fixed_digits(sb, 1);
        int i6 = read_char();
        if (i6 != 58) {
            bad_token(i6);
            throw null;
        }
        sb.append((char) i6);
        load_fixed_digits(sb, 2);
        int iLoad_digits = read_char();
        if (iLoad_digits == 58) {
            sb.append((char) iLoad_digits);
            load_fixed_digits(sb, 2);
            iLoad_digits = read_char();
            if (iLoad_digits == 46) {
                sb.append((char) iLoad_digits);
                int i7 = read_char();
                if (!IonTokenConstsX.isDigit(i7)) {
                    expected_but_found("at least one digit after timestamp's decimal point", i7);
                    throw null;
                }
                iLoad_digits = load_digits(sb, i7);
            }
        }
        if (iLoad_digits == 122 || iLoad_digits == 90) {
            sb.append((char) iLoad_digits);
            i2 = read_char();
        } else {
            if (iLoad_digits != 43 && iLoad_digits != 45) {
                bad_token(iLoad_digits);
                throw null;
            }
            sb.append((char) iLoad_digits);
            load_fixed_digits(sb, 2);
            int i8 = read_char();
            if (i8 != 58) {
                bad_token(i8);
                throw null;
            }
            sb.append((char) i8);
            load_fixed_digits(sb, 2);
            i2 = read_char();
        }
        return load_finish_number(sb, i2, 8);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0008. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int load_triple_quoted_string(java.lang.StringBuilder r5, boolean r6) throws java.io.IOException {
        /*
            r4 = this;
            r0 = 0
        L1:
            r1 = 0
        L2:
            int r2 = r4.read_triple_quoted_char(r6)
            r3 = 10
            switch(r2) {
                case -9: goto L2;
                case -8: goto L2;
                case -7: goto L2;
                case -6: goto L18;
                case -5: goto L18;
                case -4: goto L18;
                case -3: goto L12;
                case -2: goto Lc;
                case -1: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L1a
        Lc:
            if (r6 != 0) goto L11
            r4.check_for_low_surrogate(r2, r1)
        L11:
            return r2
        L12:
            if (r6 != 0) goto L2
            r4.check_for_low_surrogate(r2, r1)
            goto L1
        L18:
            r2 = 10
        L1a:
            if (r6 != 0) goto L36
            r4.check_for_low_surrogate(r2, r1)
            boolean r1 = com.amazon.ion.impl.IonUTF8.needsSurrogateEncoding(r2)
            if (r1 == 0) goto L32
            char r1 = com.amazon.ion.impl.IonUTF8.highSurrogate(r2)
            r5.append(r1)
            char r2 = com.amazon.ion.impl.IonUTF8.lowSurrogate(r2)
            r1 = 0
            goto L36
        L32:
            boolean r1 = com.amazon.ion.impl.IonUTF8.isHighSurrogate(r2)
        L36:
            char r2 = (char) r2
            r5.append(r2)
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.load_triple_quoted_string(java.lang.StringBuilder, boolean):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int nextToken() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.nextToken():int");
    }

    public final int next_token_finish(int i, boolean z) {
        this._token = i;
        this._unfinished_token = z;
        return i;
    }

    public final int peekLobStartPunctuation() throws IOException {
        int iSkip_over_whitespace = skip_over_whitespace(CommentStrategy.BREAK);
        if (iSkip_over_whitespace == 34) {
            return 12;
        }
        if (iSkip_over_whitespace != 39) {
            unread_char(iSkip_over_whitespace);
            return -1;
        }
        int i = read_char();
        if (i != 39) {
            unread_char(i);
            unread_char(39);
            return -1;
        }
        int i2 = read_char();
        if (i2 == 39) {
            return 13;
        }
        unread_char(i2);
        unread_char(39);
        unread_char(39);
        return -1;
    }

    public final int peekNullTypeSymbol() throws IOException {
        int i = read_char();
        int i2 = 0;
        if (i != 46) {
            unread_char(i);
            return 0;
        }
        int[] iArr = new int[IonTokenConstsX.TN_MAX_NAME_LENGTH + 1];
        int iTypeNamePossibilityMask = IonTokenConstsX.KW_ALL_BITS;
        while (true) {
            if (i2 >= IonTokenConstsX.TN_MAX_NAME_LENGTH + 1) {
                break;
            }
            i = read_char();
            int i3 = i2 + 1;
            iArr[i2] = i;
            int iTypeNameLetterIdx = IonTokenConstsX.typeNameLetterIdx(i);
            if (iTypeNameLetterIdx >= 1) {
                iTypeNamePossibilityMask &= IonTokenConstsX.typeNamePossibilityMask(i2, iTypeNameLetterIdx);
                if (iTypeNamePossibilityMask == 0) {
                    peekNullTypeSymbolUndo(iArr, i3);
                    throw null;
                }
                i2 = i3;
            } else {
                if (!IonTokenConstsX.isValidTerminatingCharForInf(i)) {
                    peekNullTypeSymbolUndo(iArr, i3);
                    throw null;
                }
                i2 = i3;
            }
        }
        int iTypeNameKeyWordFromMask = IonTokenConstsX.typeNameKeyWordFromMask(iTypeNamePossibilityMask, i2 - 1);
        if (iTypeNameKeyWordFromMask != -1) {
            unread_char(i);
            return iTypeNameKeyWordFromMask;
        }
        peekNullTypeSymbolUndo(iArr, i2);
        throw null;
    }

    public final int peekNullTypeSymbolUndo(int[] iArr, int i) {
        String string = "";
        for (int i2 = 0; i2 < i; i2++) {
            StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m(string);
            sbM.append((char) iArr[i2]);
            string = sbM.toString();
        }
        error("invalid type name on a typed null value");
        throw null;
    }

    public final boolean peek_inf_helper(int i) throws IOException {
        if (i != 43 && i != 45) {
            return false;
        }
        int i2 = read_char();
        if (i2 == 105) {
            int i3 = read_char();
            if (i3 == 110) {
                int i4 = read_char();
                if (i4 == 102) {
                    int i5 = read_char();
                    if (is_value_terminating_character(i5)) {
                        unread_char(i5);
                        return true;
                    }
                    unread_char(i5);
                    i4 = 102;
                }
                unread_char(i4);
                i3 = ProxyBillingActivity.REQUEST_CODE_FIRST_PARTY_PURCHASE_FLOW;
            }
            unread_char(i3);
            i2 = LocationRequest.PRIORITY_NO_POWER;
        }
        unread_char(i2);
        return false;
    }

    public final int readNumeric(Appendable appendable, Radix radix) throws IOException {
        return readNumeric(appendable, radix, NumericState.START);
    }

    public final int read_base64_byte() throws IOException {
        int i = this._base64_prefetch_count;
        if (i < 1) {
            return read_base64_byte_helper();
        }
        int i2 = this._base64_prefetch_stack;
        int i3 = i2 & 255;
        this._base64_prefetch_stack = i2 >> 8;
        this._base64_prefetch_count = i - 1;
        return i3;
    }

    public final int read_base64_byte_helper() throws IOException {
        int iSkip_over_whitespace = skip_over_whitespace(CommentStrategy.BREAK);
        if (iSkip_over_whitespace == -1 || iSkip_over_whitespace == 125) {
            return -1;
        }
        int i = read_base64_getchar_helper(iSkip_over_whitespace);
        int i2 = read_base64_getchar_helper();
        int i3 = read_base64_getchar_helper();
        int i4 = read_base64_getchar_helper();
        int iDecode_base64_length = decode_base64_length(i, i2, i3, i4);
        this._base64_prefetch_stack = 0;
        this._base64_prefetch_count = iDecode_base64_length - 1;
        if (iDecode_base64_length != 1) {
            if (iDecode_base64_length != 2) {
                if (iDecode_base64_length != 3) {
                    throw new IonReaderTextTokenException("invalid binhex sequence encountered at offset" + input_position());
                }
                this._base64_prefetch_stack = (decode_base64_byte3(i, i2, i3, i4) << 8) & 65280;
            }
            this._base64_prefetch_stack = (decode_base64_byte2(i, i2, i3, i4) & 255) | this._base64_prefetch_stack;
        }
        return decode_base64_byte1(i, i2, i3, i4);
    }

    public final int read_base64_getchar_helper() throws IOException {
        int iSkip_over_whitespace = skip_over_whitespace(CommentStrategy.BREAK);
        if (iSkip_over_whitespace != -1 && iSkip_over_whitespace != 125) {
            return read_base64_getchar_helper2(iSkip_over_whitespace);
        }
        error("invalid base64 image - too short");
        throw null;
    }

    public final int read_base64_getchar_helper2(int i) throws IOException {
        if (i == BASE64_TERMINATOR_CHAR) {
            return 128;
        }
        int i2 = BASE64_CHAR_TO_BIN[i & 255];
        if (i2 != -1 && IonTokenConstsX.is8bitValue(i)) {
            return i2;
        }
        throw new IonReaderTextTokenException("invalid character " + Character.toString((char) i) + " encountered in base64 value at " + input_position());
    }

    public final int read_char() throws IOException {
        int i = this._stream.read();
        return (i == 13 || i == 10) ? line_count(i) : i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0032, code lost:
    
        if (r6 != (-1)) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0035, code lost:
    
        if (r7 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003b, code lost:
    
        if (com.amazon.ion.impl.IonTokenConstsX.is8bitValue(r6) == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x003e, code lost:
    
        error("invalid character [" + com.amazon.ion.util.IonTextUtils.printCodePointAsString(r6) + "] in CLOB");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0058, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
    
        return r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int read_char_escaped(int r6, boolean r7) throws java.io.IOException {
        /*
            r5 = this;
        L0:
            r0 = -9
            if (r6 == r0) goto L69
            r1 = -8
            if (r6 == r1) goto L69
            r2 = -7
            if (r6 == r2) goto L69
            r3 = 92
            r4 = 0
            if (r6 == r3) goto L1c
            if (r7 != 0) goto L31
            boolean r0 = com.amazon.ion.impl.IonTokenConstsX.is7bitValue(r6)
            if (r0 != 0) goto L31
            int r6 = r5.read_large_char_sequence(r6)
            goto L31
        L1c:
            int r6 = r5.read_char()
            if (r6 < 0) goto L65
            int r6 = r5.read_escaped_char_content_helper(r6, r7)
            if (r6 == r2) goto L5e
            if (r6 == r1) goto L5e
            if (r6 != r0) goto L2d
            goto L5e
        L2d:
            r0 = -11
            if (r6 == r0) goto L5a
        L31:
            r0 = -1
            if (r6 != r0) goto L35
            goto L59
        L35:
            if (r7 == 0) goto L59
            boolean r7 = com.amazon.ion.impl.IonTokenConstsX.is8bitValue(r6)
            if (r7 == 0) goto L3e
            goto L59
        L3e:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "invalid character ["
            r7.<init>(r0)
            java.lang.String r6 = com.amazon.ion.util.IonTextUtils.printCodePointAsString(r6)
            r7.append(r6)
            java.lang.String r6 = "] in CLOB"
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            r5.error(r6)
            throw r4
        L59:
            return r6
        L5a:
            r5.bad_escape_sequence()
            throw r4
        L5e:
            com.amazon.ion.impl.IonReaderTextRawTokensX$ProhibitedCharacters r6 = com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.NONE
            int r6 = r5.read_string_char(r6)
            goto L0
        L65:
            r5.unexpected_eof()
            throw r4
        L69:
            com.amazon.ion.impl.IonReaderTextRawTokensX$ProhibitedCharacters r6 = com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.NONE
            int r6 = r5.read_string_char(r6)
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.read_char_escaped(int, boolean):int");
    }

    public int read_double_quoted_char(boolean z) throws IOException {
        int i = read_char();
        if (z && i > 127) {
            throw new IonReaderTextTokenException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("non ASCII character in clob: ", i));
        }
        if (i != -1) {
            if (i == 34) {
                unread_char(i);
                return -2;
            }
            if (i == 92) {
                return read_char_escaped(i, z);
            }
            if (!z && !IonTokenConstsX.is7bitValue(i)) {
                return read_large_char_sequence(i);
            }
        }
        return i;
    }

    public final int read_escaped_char_content_helper(int i, boolean z) throws IOException {
        if (i < 0) {
            if (i == -6) {
                return -9;
            }
            if (i == -5) {
                return -8;
            }
            if (i == -4) {
                return -7;
            }
            bad_escape_sequence(i);
            throw null;
        }
        if (!IonTokenConstsX.isValidEscapeStart(i)) {
            bad_escape_sequence(i);
            throw null;
        }
        int iEscapeReplacementCharacter = IonTokenConstsX.escapeReplacementCharacter(i);
        if (iEscapeReplacementCharacter == -11) {
            return iEscapeReplacementCharacter;
        }
        switch (iEscapeReplacementCharacter) {
            case IonTokenConstsX.ESCAPE_HEX /* -16 */:
                return read_hex_escape_sequence_value(2);
            case IonTokenConstsX.ESCAPE_BIG_U /* -15 */:
                if (!z) {
                    return read_hex_escape_sequence_value(8);
                }
                bad_escape_sequence(iEscapeReplacementCharacter);
                throw null;
            case IonTokenConstsX.ESCAPE_LITTLE_U /* -14 */:
                if (!z) {
                    return read_hex_escape_sequence_value(4);
                }
                bad_escape_sequence(iEscapeReplacementCharacter);
                throw null;
            default:
                return iEscapeReplacementCharacter;
        }
    }

    public final int read_hex_escape_sequence_value(int i) throws IOException {
        int i2 = 0;
        while (i > 0) {
            i--;
            int i3 = read_char();
            if (i3 < 0) {
                unexpected_eof();
                throw null;
            }
            int iHexDigitValue = IonTokenConstsX.hexDigitValue(i3);
            if (iHexDigitValue < 0) {
                return -1;
            }
            i2 = (i2 << 4) + iHexDigitValue;
        }
        if (i <= 0) {
            return i2;
        }
        error("invalid hex digit [" + IonTextUtils.printCodePointAsString(i2) + "] in escape sequence");
        throw null;
    }

    public final int read_large_char_sequence(int i) throws IOException {
        if (this._stream._is_byte_data) {
            return read_ut8_sequence(i);
        }
        if (_Private_IonConstants.isHighSurrogate(i)) {
            int i2 = read_char();
            if (_Private_IonConstants.isLowSurrogate(i2)) {
                return _Private_IonConstants.makeUnicodeScalar(i, i2);
            }
            unread_char(i2);
        }
        return i;
    }

    public final int read_string_char(ProhibitedCharacters prohibitedCharacters) throws IOException {
        int i = this._stream.read();
        if (!prohibitedCharacters.includes(i)) {
            return (i == 13 || i == 10 || i == 92) ? line_count(i) : i;
        }
        error("invalid character [" + IonTextUtils.printCodePointAsString(i) + "]");
        throw null;
    }

    public int read_triple_quoted_char(boolean z) throws IOException {
        int i = read_string_char(ProhibitedCharacters.LONG_CHAR);
        if (z && i > 127) {
            throw new IonReaderTextTokenException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("non ASCII character in clob: ", i));
        }
        if (i != -1) {
            if (i != 39) {
                if (i == 92) {
                    return read_char_escaped(i, z);
                }
                switch (i) {
                    default:
                        if (!z && !IonTokenConstsX.is7bitValue(i)) {
                            return read_large_char_sequence(i);
                        }
                    case -9:
                    case IonTokenConstsX.CharacterSequence.CHAR_SEQ_ESCAPED_NEWLINE_SEQUENCE_2 /* -8 */:
                    case -7:
                    case -6:
                    case -5:
                    case -4:
                        return i;
                }
            } else if (is_2_single_quotes_helper()) {
                int iSkip_over_whitespace = skip_over_whitespace(CommentStrategy.IGNORE);
                if (iSkip_over_whitespace == 39 && is_2_single_quotes_helper()) {
                    return -3;
                }
                unread_char(iSkip_over_whitespace);
                return -2;
            }
        }
        return i;
    }

    public final int read_ut8_sequence(int i) throws IOException {
        int uTF8LengthFromFirstByte = IonUTF8.getUTF8LengthFromFirstByte(i);
        if (uTF8LengthFromFirstByte == 1) {
            return i;
        }
        if (uTF8LengthFromFirstByte == 2) {
            return IonUTF8.twoByteScalar(i, read_char());
        }
        if (uTF8LengthFromFirstByte == 3) {
            return IonUTF8.threeByteScalar(i, read_char(), read_char());
        }
        if (uTF8LengthFromFirstByte == 4) {
            return IonUTF8.fourByteScalar(i, read_char(), read_char(), read_char());
        }
        error("invalid UTF8 starting byte");
        throw null;
    }

    public void save_point_activate(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        this._stream._save_points.savePointPushActive(savePoint, this._line_count, this._line_starting_position);
        this._line_count = savePoint._start_line_count;
        this._line_starting_position = savePoint._start_line_start;
    }

    public void save_point_deactivate(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        this._stream._save_points.savePointPopActive(savePoint);
        this._line_count = savePoint._prev_line_count;
        this._line_starting_position = savePoint._prev_line_start;
    }

    public void save_point_start(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        savePoint.start(this._line_count, this._line_starting_position);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int scan_for_numeric_type(int r8) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 6
            int[] r0 = new int[r0]
            boolean r1 = com.amazon.ion.impl.IonTokenConstsX.isDigit(r8)
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L8f
            int r1 = r7.read_char()
            r0[r3] = r1
            r3 = 48
            r4 = 4
            r5 = 3
            r6 = 2
            if (r8 != r3) goto L4d
            r8 = 46
            if (r1 == r8) goto L4d
            r8 = 66
            if (r1 == r8) goto L4a
            r8 = 88
            if (r1 == r8) goto L48
            r8 = 98
            if (r1 == r8) goto L4a
            r8 = 120(0x78, float:1.68E-43)
            if (r1 == r8) goto L48
            r8 = 68
            if (r1 == r8) goto L46
            r8 = 69
            if (r1 == r8) goto L44
            r8 = 100
            if (r1 == r8) goto L46
            r8 = 101(0x65, float:1.42E-43)
            if (r1 == r8) goto L44
            boolean r8 = r7.is_value_terminating_character(r1)
            if (r8 == 0) goto L4d
            r8 = 2
            goto L4e
        L44:
            r8 = 5
            goto L4e
        L46:
            r8 = 4
            goto L4e
        L48:
            r8 = 3
            goto L4e
        L4a:
            r8 = 26
            goto L4e
        L4d:
            r8 = 1
        L4e:
            if (r8 != r2) goto L85
            boolean r1 = com.amazon.ion.impl.IonTokenConstsX.isDigit(r1)
            if (r1 == 0) goto L85
            int r1 = r7.read_char()
            r0[r2] = r1
            boolean r1 = com.amazon.ion.impl.IonTokenConstsX.isDigit(r1)
            if (r1 == 0) goto L84
            int r1 = r7.read_char()
            r0[r6] = r1
            boolean r1 = com.amazon.ion.impl.IonTokenConstsX.isDigit(r1)
            if (r1 == 0) goto L82
            int r1 = r7.read_char()
            r0[r5] = r1
            r2 = 45
            if (r1 == r2) goto L7f
            r2 = 84
            if (r1 != r2) goto L7d
            goto L7f
        L7d:
            r2 = 4
            goto L85
        L7f:
            r8 = 8
            goto L7d
        L82:
            r2 = 3
            goto L85
        L84:
            r2 = 2
        L85:
            int r2 = r2 + (-1)
            r1 = r0[r2]
            r7.unread_char(r1)
            if (r2 > 0) goto L85
            return r8
        L8f:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.Object[] r0 = new java.lang.Object[r2]
            r0[r3] = r8
            java.lang.String r8 = "Expected digit, got U+%04X"
            java.lang.String r8 = java.lang.String.format(r8, r0)
            r7.error(r8)
            r8 = 0
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.scan_for_numeric_type(int):int");
    }

    public final int scan_negative_for_numeric_type(int i) throws IOException {
        int i2 = read_char();
        int iScan_for_numeric_type = scan_for_numeric_type(i2);
        if (iScan_for_numeric_type != 8) {
            unread_char(i2);
            return iScan_for_numeric_type;
        }
        bad_token(i2);
        throw null;
    }

    public final boolean skipDoubleColon() throws IOException {
        int iSkip_over_whitespace = skip_over_whitespace(CommentStrategy.IGNORE);
        if (iSkip_over_whitespace != 58) {
            unread_char(iSkip_over_whitespace);
            return false;
        }
        int i = read_char();
        if (i == 58) {
            return true;
        }
        unread_char(i);
        unread_char(58);
        return false;
    }

    public final int skipOverRadix(UnifiedSavePointManagerX.SavePoint savePoint, Radix radix) throws IOException {
        if (read_char() == 45) {
            read_char();
        }
        read_char();
        radix.getClass();
        int numeric = readNumeric(NULL_APPENDABLE, radix, NumericState.START);
        if (!is_value_terminating_character(numeric)) {
            bad_token(numeric);
            throw null;
        }
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
        return numeric;
    }

    public final void skip_block_comment() throws IOException {
        int i;
        while (true) {
            int i2 = read_char();
            if (i2 == -1) {
                bad_token_start(i2);
                throw null;
            }
            if (i2 == 42) {
                do {
                    i = read_char();
                    if (i == 47) {
                        return;
                    }
                } while (i == 42);
            }
        }
    }

    public final void skip_clob_close_punctuation() throws IOException {
        int iSkip_over_whitespace = skip_over_whitespace(CommentStrategy.ERROR);
        if (iSkip_over_whitespace == 125) {
            int i = read_char();
            if (i == 125) {
                return;
            }
            unread_char(i);
            iSkip_over_whitespace = 125;
        }
        unread_char(iSkip_over_whitespace);
        error("invalid closing puctuation for CLOB");
        throw null;
    }

    public final void skip_double_quoted_string(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        skip_double_quoted_string_helper();
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0025, code lost:
    
        bad_token(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0028, code lost:
    
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void skip_double_quoted_string_helper() throws java.io.IOException {
        /*
            r3 = this;
        L0:
            com.amazon.ion.impl.IonReaderTextRawTokensX$ProhibitedCharacters r0 = com.amazon.ion.impl.IonReaderTextRawTokensX.ProhibitedCharacters.NONE
            int r0 = r3.read_string_char(r0)
            r1 = -6
            r2 = 0
            if (r0 == r1) goto L25
            r1 = -5
            if (r0 == r1) goto L25
            r1 = -4
            if (r0 == r1) goto L25
            r1 = -1
            if (r0 == r1) goto L21
            r1 = 34
            if (r0 == r1) goto L20
            r1 = 92
            if (r0 == r1) goto L1c
            goto L0
        L1c:
            r3.read_char()
            goto L0
        L20:
            return
        L21:
            r3.unexpected_eof()
            throw r2
        L25:
            r3.bad_token(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.skip_double_quoted_string_helper():void");
    }

    public final int skip_optional_timestamp_offset(int i) throws IOException {
        if (i != 45 && i != 43) {
            return i;
        }
        int iSkip_timestamp_past_digits = skip_timestamp_past_digits(2, 2);
        if (iSkip_timestamp_past_digits == 58) {
            return skip_timestamp_past_digits(2, 2);
        }
        bad_token(iSkip_timestamp_past_digits);
        throw null;
    }

    public final void skip_over_blob(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int iSkip_over_whitespace = skip_over_whitespace(CommentStrategy.BREAK);
        while (true) {
            if (iSkip_over_whitespace == -1 || iSkip_over_whitespace == 125) {
                break;
            } else {
                iSkip_over_whitespace = skip_over_whitespace(CommentStrategy.BREAK);
            }
        }
        if (savePoint != null) {
            savePoint.markEnd(iSkip_over_whitespace != 125 ? 0 : -1);
        }
        if (iSkip_over_whitespace != 125) {
            unexpected_eof();
            throw null;
        }
        int i = read_char();
        if (i < 0) {
            unexpected_eof();
            throw null;
        }
        if (i == 125) {
            if (savePoint != null) {
                savePoint.markEnd();
            }
        } else {
            error("improperly closed BLOB, " + IonTextUtils.printCodePointAsString(i) + " encountered when '}' was expected");
            throw null;
        }
    }

    public final int skip_over_blob_whitespace() throws IOException {
        return skip_over_whitespace(CommentStrategy.BREAK);
    }

    public final int skip_over_clob_whitespace() throws IOException {
        return skip_over_whitespace(CommentStrategy.ERROR);
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x0000, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void skip_over_container(int r6) throws java.io.IOException {
        /*
            r5 = this;
        L0:
            com.amazon.ion.impl.IonReaderTextRawTokensX$CommentStrategy r0 = com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy.IGNORE
            int r0 = r5.skip_over_whitespace(r0)
            r1 = -1
            r2 = 0
            if (r0 == r1) goto L7c
            r1 = 34
            if (r0 == r1) goto L78
            r3 = 91
            r4 = 93
            if (r0 == r3) goto L74
            if (r0 == r4) goto L71
            r3 = 123(0x7b, float:1.72E-43)
            r4 = 125(0x7d, float:1.75E-43)
            if (r0 == r3) goto L3a
            if (r0 == r4) goto L71
            switch(r0) {
                case 39: goto L28;
                case 40: goto L22;
                case 41: goto L71;
                default: goto L21;
            }
        L21:
            goto L0
        L22:
            r0 = 41
            r5.skip_over_container(r0)
            goto L0
        L28:
            boolean r0 = r5.is_2_single_quotes_helper()
            if (r0 == 0) goto L32
            r5.skip_triple_quoted_string(r2)
            goto L0
        L32:
            int r0 = r5.skip_single_quoted_string(r2)
            r5.unread_char(r0)
            goto L0
        L3a:
            int r0 = r5.read_char()
            if (r0 != r3) goto L67
            com.amazon.ion.impl.IonReaderTextRawTokensX$CommentStrategy r0 = com.amazon.ion.impl.IonReaderTextRawTokensX.CommentStrategy.BREAK
            int r0 = r5.skip_over_whitespace(r0)
            if (r0 != r1) goto L4b
            r0 = 12
            goto L63
        L4b:
            r1 = 39
            if (r0 != r1) goto L5e
            boolean r0 = r5.is_2_single_quotes_helper()
            if (r0 == 0) goto L58
            r0 = 13
            goto L63
        L58:
            java.lang.String r6 = "invalid single quote in lob content"
            r5.error(r6)
            throw r2
        L5e:
            r5.unread_char(r0)
            r0 = 24
        L63:
            r5.skip_over_lob(r0, r2)
            goto L0
        L67:
            if (r0 != r4) goto L6a
            goto L0
        L6a:
            r5.unread_char(r0)
            r5.skip_over_container(r4)
            goto L0
        L71:
            if (r0 != r6) goto L0
            return
        L74:
            r5.skip_over_container(r4)
            goto L0
        L78:
            r5.skip_double_quoted_string_helper()
            goto L0
        L7c:
            r5.unexpected_eof()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawTokensX.skip_over_container(int):void");
    }

    public final int skip_over_decimal(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        return skip_over_number(savePoint);
    }

    public final int skip_over_digits(int i) throws IOException {
        while (IonTokenConstsX.isDigit(i)) {
            i = read_char();
        }
        return i;
    }

    public final int skip_over_float(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        return skip_over_number(savePoint);
    }

    public final int skip_over_int(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int i = read_char();
        if (i == 45) {
            i = read_char();
        }
        int iSkip_over_digits = skip_over_digits(i);
        if (!is_value_terminating_character(iSkip_over_digits)) {
            bad_token(iSkip_over_digits);
            throw null;
        }
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
        return iSkip_over_digits;
    }

    public void skip_over_list() throws IOException {
        skip_over_container(93);
    }

    public void skip_over_lob(int i, UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        if (i == 12) {
            skip_double_quoted_string(savePoint);
            skip_clob_close_punctuation();
            return;
        }
        if (i == 13) {
            skip_triple_quoted_clob_string(savePoint);
            skip_clob_close_punctuation();
        } else {
            if (i == 24) {
                skip_over_blob(savePoint);
                return;
            }
            error("unexpected token " + IonTokenConstsX.getTokenName(i) + " encountered for lob content");
            throw null;
        }
    }

    public final int skip_over_lob_whitespace() throws IOException {
        return skip_over_whitespace(CommentStrategy.BREAK);
    }

    public final int skip_over_number(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int i = read_char();
        if (i == 45) {
            i = read_char();
        }
        int iSkip_over_digits = skip_over_digits(i);
        if (iSkip_over_digits == 46) {
            iSkip_over_digits = skip_over_digits(read_char());
        }
        if (iSkip_over_digits == 100 || iSkip_over_digits == 68 || iSkip_over_digits == 101 || iSkip_over_digits == 69) {
            int i2 = read_char();
            if (i2 == 45 || i2 == 43) {
                i2 = read_char();
            }
            iSkip_over_digits = skip_over_digits(i2);
        }
        if (!is_value_terminating_character(iSkip_over_digits)) {
            bad_token(iSkip_over_digits);
            throw null;
        }
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
        return iSkip_over_digits;
    }

    public void skip_over_sexp() throws IOException {
        skip_over_container(41);
    }

    public void skip_over_struct() throws IOException {
        skip_over_container(125);
    }

    public final int skip_over_symbol_identifier(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int i = read_char();
        while (IonTokenConstsX.isValidSymbolCharacter(i)) {
            i = read_char();
        }
        if (savePoint != null) {
            savePoint.markEnd(0);
        }
        return i;
    }

    public final int skip_over_symbol_operator(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int i = read_char();
        if (peek_inf_helper(i)) {
            i = read_char();
        } else {
            while (IonTokenConstsX.isValidExtendedSymbolCharacter(i)) {
                i = read_char();
            }
        }
        if (savePoint != null) {
            savePoint.markEnd(0);
        }
        return i;
    }

    public final int skip_over_timestamp(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int iSkip_timestamp_past_digits = skip_timestamp_past_digits(4, 4);
        if (iSkip_timestamp_past_digits == 84) {
            if (savePoint != null) {
                savePoint.markEnd(0);
            }
            return skip_over_whitespace(CommentStrategy.IGNORE);
        }
        if (iSkip_timestamp_past_digits != 45) {
            error("invalid timestamp encountered");
            throw null;
        }
        int iSkip_timestamp_past_digits2 = skip_timestamp_past_digits(2, 2);
        if (iSkip_timestamp_past_digits2 == 84) {
            if (savePoint != null) {
                savePoint.markEnd(0);
            }
            return skip_over_whitespace(CommentStrategy.IGNORE);
        }
        skip_timestamp_validate(iSkip_timestamp_past_digits2, 45);
        int iSkip_timestamp_past_digits3 = skip_timestamp_past_digits(2, 2);
        if (iSkip_timestamp_past_digits3 != 84) {
            skip_timestamp_finish(iSkip_timestamp_past_digits3, savePoint);
            return iSkip_timestamp_past_digits3;
        }
        int i = read_char();
        if (!IonTokenConstsX.isDigit(i)) {
            int iSkip_optional_timestamp_offset = skip_optional_timestamp_offset(i);
            skip_timestamp_finish(iSkip_optional_timestamp_offset, savePoint);
            return iSkip_optional_timestamp_offset;
        }
        int iSkip_timestamp_past_digits4 = skip_timestamp_past_digits(1, 1);
        if (iSkip_timestamp_past_digits4 != 58) {
            bad_token(iSkip_timestamp_past_digits4);
            throw null;
        }
        int iSkip_timestamp_past_digits5 = skip_timestamp_past_digits(2, 2);
        if (iSkip_timestamp_past_digits5 != 58) {
            return skip_timestamp_offset_or_z(iSkip_timestamp_past_digits5, savePoint);
        }
        int iSkip_timestamp_past_digits6 = skip_timestamp_past_digits(2, 2);
        if (iSkip_timestamp_past_digits6 != 46) {
            return skip_timestamp_offset_or_z(iSkip_timestamp_past_digits6, savePoint);
        }
        int iSkip_over_digits = read_char();
        if (IonTokenConstsX.isDigit(iSkip_over_digits)) {
            iSkip_over_digits = skip_over_digits(iSkip_over_digits);
        }
        return skip_timestamp_offset_or_z(iSkip_over_digits, savePoint);
    }

    public final int skip_over_whitespace() throws IOException {
        return skip_over_whitespace(CommentStrategy.IGNORE);
    }

    public final void skip_single_line_comment() throws IOException {
        while (true) {
            int i = read_char();
            if (i != -1) {
                switch (i) {
                    case -9:
                    case IonTokenConstsX.CharacterSequence.CHAR_SEQ_ESCAPED_NEWLINE_SEQUENCE_2 /* -8 */:
                    case -7:
                    case -6:
                    case -5:
                    case -4:
                        return;
                }
            }
            return;
        }
    }

    public final int skip_single_quoted_string(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        while (true) {
            int i = read_string_char(ProhibitedCharacters.NONE);
            if (i == -1) {
                unexpected_eof();
                throw null;
            }
            if (i == 39) {
                if (savePoint != null) {
                    savePoint.markEnd(-1);
                }
                return read_char();
            }
            if (i == 92) {
                read_char();
            }
        }
    }

    public final int skip_timestamp_finish(int i, UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        if (!is_value_terminating_character(i)) {
            bad_token(i);
            throw null;
        }
        if (savePoint != null) {
            savePoint.markEnd(-1);
        }
        return i;
    }

    public final int skip_timestamp_offset_or_z(int i, UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int iSkip_timestamp_past_digits;
        if (i == 45 || i == 43) {
            int iSkip_timestamp_past_digits2 = skip_timestamp_past_digits(2, 2);
            if (iSkip_timestamp_past_digits2 != 58) {
                bad_token(iSkip_timestamp_past_digits2);
                throw null;
            }
            iSkip_timestamp_past_digits = skip_timestamp_past_digits(2, 2);
        } else {
            if (i != 90 && i != 122) {
                bad_token(i);
                throw null;
            }
            iSkip_timestamp_past_digits = read_char();
        }
        skip_timestamp_finish(iSkip_timestamp_past_digits, savePoint);
        return iSkip_timestamp_past_digits;
    }

    public final int skip_timestamp_past_digits(int i) throws IOException {
        return skip_timestamp_past_digits(i, i);
    }

    public final void skip_timestamp_validate(int i, int i2) {
        if (i == i2) {
            return;
        }
        error("invalid character '" + ((char) i) + "' encountered in timestamp (when '" + ((char) i2) + "' was expected");
        throw null;
    }

    public final int skip_to_end(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        int iSkip_over_number;
        int i = this._token;
        if (i == 1) {
            iSkip_over_number = skip_over_number(savePoint);
        } else if (i == 2) {
            iSkip_over_number = skip_over_int(savePoint);
        } else if (i == 3) {
            iSkip_over_number = skipOverRadix(savePoint, Radix.HEX);
        } else if (i == 4 || i == 5) {
            iSkip_over_number = skip_over_number(savePoint);
        } else if (i == 18) {
            skip_over_sexp();
            iSkip_over_number = read_char();
        } else if (i == 20) {
            skip_over_struct();
            iSkip_over_number = read_char();
        } else if (i == 22) {
            skip_over_list();
            iSkip_over_number = read_char();
        } else if (i == 24) {
            skip_over_blob(savePoint);
            iSkip_over_number = read_char();
        } else if (i != 26) {
            switch (i) {
                case 8:
                    iSkip_over_number = skip_over_timestamp(savePoint);
                    break;
                case 9:
                    iSkip_over_number = skip_over_symbol_identifier(savePoint);
                    break;
                case 10:
                    iSkip_over_number = skip_single_quoted_string(savePoint);
                    break;
                case 11:
                    iSkip_over_number = skip_over_symbol_operator(savePoint);
                    break;
                case 12:
                    skip_double_quoted_string_helper();
                    iSkip_over_number = skip_over_whitespace(CommentStrategy.IGNORE);
                    break;
                case 13:
                    skip_triple_quoted_string(savePoint);
                    iSkip_over_number = skip_over_whitespace(CommentStrategy.IGNORE);
                    break;
                default:
                    error("token " + IonTokenConstsX.getTokenName(this._token) + " unexpectedly encounterd as \"unfinished\"");
                    throw null;
            }
        } else {
            iSkip_over_number = skipOverRadix(savePoint, Radix.BINARY);
        }
        if (IonTokenConstsX.isWhitespace(iSkip_over_number)) {
            iSkip_over_number = skip_over_whitespace(CommentStrategy.IGNORE);
        }
        this._unfinished_token = false;
        return iSkip_over_number;
    }

    public final void skip_triple_quoted_clob_string(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        skip_triple_quoted_string(savePoint, CommentStrategy.ERROR);
    }

    public final void skip_triple_quoted_string(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        skip_triple_quoted_string(savePoint, CommentStrategy.IGNORE);
    }

    public final boolean skip_whitespace() throws IOException {
        return skip_whitespace(CommentStrategy.IGNORE);
    }

    public final void tokenIsFinished() {
        this._unfinished_token = false;
        this._base64_prefetch_count = 0;
    }

    public final void unexpected_eof() {
        throw new UnexpectedEofException("unexpected EOF encountered " + input_position());
    }

    public final void unread_char(int i) {
        if (i >= 0) {
            this._stream.unread(i);
            return;
        }
        if (i == -1) {
            this._stream.getClass();
            return;
        }
        switch (i) {
            case -9:
                this._stream.unread(10);
                this._stream.unread(13);
                this._stream.unread(92);
                break;
            case IonTokenConstsX.CharacterSequence.CHAR_SEQ_ESCAPED_NEWLINE_SEQUENCE_2 /* -8 */:
                this._stream.unread(13);
                this._stream.unread(92);
                break;
            case -7:
                this._stream.unread(10);
                this._stream.unread(92);
                break;
            case -6:
                line_count_unread(i);
                this._stream.unread(10);
                this._stream.unread(13);
                break;
            case -5:
                line_count_unread(i);
                this._stream.unread(13);
                break;
            case -4:
                line_count_unread(i);
                this._stream.unread(10);
                break;
        }
    }

    public IonReaderTextRawTokensX(UnifiedInputStreamX unifiedInputStreamX, long j, long j2) {
        this._token = -1;
        this._line_count_has_cached = false;
        this._stream = unifiedInputStreamX;
        this._line_count = j;
        this._line_starting_position = unifiedInputStreamX.getPosition() - j2;
    }

    public final int readNumeric(Appendable appendable, Radix radix, NumericState numericState) throws IOException {
        int i;
        while (true) {
            i = read_char();
            int i2 = AnonymousClass2.$SwitchMap$com$amazon$ion$impl$IonReaderTextRawTokensX$NumericState[numericState.ordinal()];
            if (i2 == 1) {
                if (!radix.isValidDigit(i)) {
                    break;
                }
                appendable.append(radix.normalizeDigit((char) i));
                numericState = NumericState.DIGIT;
            } else if (i2 == 2) {
                if (!radix.isValidDigit(i)) {
                    if (i != 95) {
                        break;
                    }
                    numericState = NumericState.UNDERSCORE;
                } else {
                    appendable.append(radix.normalizeDigit((char) i));
                    numericState = NumericState.DIGIT;
                }
            } else if (i2 != 3) {
                continue;
            } else {
                if (!radix.isValidDigit(i)) {
                    unread_char(i);
                    return 95;
                }
                appendable.append(radix.normalizeDigit((char) i));
                numericState = NumericState.DIGIT;
            }
        }
        return i;
    }

    public final int skip_over_whitespace(CommentStrategy commentStrategy) throws IOException {
        skip_whitespace(commentStrategy);
        return read_char();
    }

    public final int skip_timestamp_past_digits(int i, int i2) throws IOException {
        while (i > 0) {
            int i3 = read_char();
            if (!IonTokenConstsX.isDigit(i3)) {
                error("invalid character '" + ((char) i3) + "' encountered in timestamp");
                throw null;
            }
            i--;
            i2--;
        }
        while (i2 > 0) {
            int i4 = read_char();
            if (!IonTokenConstsX.isDigit(i4)) {
                return i4;
            }
            i2--;
        }
        return read_char();
    }

    public final void skip_triple_quoted_string(UnifiedSavePointManagerX.SavePoint savePoint, CommentStrategy commentStrategy) throws IOException {
        int iSkip_over_whitespace;
        while (true) {
            int i = read_char();
            if (i == -1) {
                unexpected_eof();
                throw null;
            }
            if (i != 39) {
                if (i == 92) {
                    read_char();
                }
            } else if (read_char() == 39) {
                int i2 = read_char();
                if (savePoint != null) {
                    savePoint.markEnd(-3);
                }
                if (i2 == 39 && ((iSkip_over_whitespace = skip_over_whitespace(commentStrategy)) != 39 || !is_2_single_quotes_helper())) {
                    break;
                }
            } else {
                continue;
            }
        }
        unread_char(iSkip_over_whitespace);
    }

    public final boolean skip_whitespace(CommentStrategy commentStrategy) throws IOException {
        int i;
        boolean z = false;
        while (true) {
            i = read_char();
            if (i != 9 && i != 32) {
                if (i != 47) {
                    switch (i) {
                    }
                } else if (!commentStrategy.onComment(this)) {
                }
            }
            z = true;
        }
        unread_char(i);
        return z;
    }

    public final int read_base64_getchar_helper(int i) throws IOException {
        if (i == -1 || i == 125) {
            return -1;
        }
        if (i != BASE64_TERMINATOR_CHAR) {
            return read_base64_getchar_helper2(i);
        }
        error("invalid base64 image - excess terminator characters ['=']");
        throw null;
    }

    public final void bad_escape_sequence(int i) {
        throw new IonReaderTextTokenException("bad escape character '" + IonTextUtils.printCodePointAsString(i) + "' encountered " + input_position());
    }
}
