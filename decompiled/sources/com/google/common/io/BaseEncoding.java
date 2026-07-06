package com.google.common.io;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.Objects;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public abstract class BaseEncoding {
    public static final BaseEncoding BASE64 = new Base64Encoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    public static final BaseEncoding BASE64_URL = new Base64Encoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
    public static final BaseEncoding BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    public static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    public static final BaseEncoding BASE16 = new Base16Encoding("base16()", "0123456789ABCDEF");

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Alphabet {
        public final int bitsPerChar;
        public final int bytesPerChunk;
        public final char[] chars;
        public final int charsPerChunk;
        public final byte[] decodabet;
        public final boolean ignoreCase;
        public final int mask;
        public final String name;
        public final boolean[] validPadding;

        public Alphabet(String name, char[] chars) {
            this(name, chars, decodabetFor(chars), false);
        }

        public static byte[] decodabetFor(char[] chars) {
            byte[] bArr = new byte[128];
            Arrays.fill(bArr, (byte) -1);
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                boolean z = true;
                Preconditions.checkArgument(c < 128, "Non-ASCII character: %s", c);
                if (bArr[c] != -1) {
                    z = false;
                }
                Preconditions.checkArgument(z, "Duplicate character: %s", c);
                bArr[c] = (byte) i;
            }
            return bArr;
        }

        public boolean canDecode(char ch) {
            return ch <= 127 && this.decodabet[ch] != -1;
        }

        public int decode(char ch) throws DecodingException {
            if (ch > 127) {
                throw new DecodingException("Unrecognized character: 0x" + Integer.toHexString(ch));
            }
            byte b = this.decodabet[ch];
            if (b != -1) {
                return b;
            }
            if (ch <= ' ' || ch == 127) {
                throw new DecodingException("Unrecognized character: 0x" + Integer.toHexString(ch));
            }
            throw new DecodingException("Unrecognized character: " + ch);
        }

        public char encode(int bits) {
            return this.chars[bits];
        }

        public boolean equals(Object other) {
            if (other instanceof Alphabet) {
                Alphabet alphabet = (Alphabet) other;
                if (this.ignoreCase == alphabet.ignoreCase && Arrays.equals(this.chars, alphabet.chars)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean hasLowerCase() {
            for (char c : this.chars) {
                if (Ascii.isLowerCase(c)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean hasUpperCase() {
            for (char c : this.chars) {
                if (Ascii.isUpperCase(c)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.chars) + (this.ignoreCase ? 1231 : 1237);
        }

        public Alphabet ignoreCase() {
            if (this.ignoreCase) {
                return this;
            }
            byte[] bArr = this.decodabet;
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            int i = 65;
            while (true) {
                if (i > 90) {
                    return new Alphabet(ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder(), this.name, ".ignoreCase()"), this.chars, bArrCopyOf, true);
                }
                int i2 = i | 32;
                byte[] bArr2 = this.decodabet;
                byte b = bArr2[i];
                byte b2 = bArr2[i2];
                if (b == -1) {
                    bArrCopyOf[i] = b2;
                } else {
                    Preconditions.checkState(b2 == -1, "Can't ignoreCase() since '%s' and '%s' encode different values", (char) i, (char) i2);
                    bArrCopyOf[i2] = b;
                }
                i++;
            }
        }

        public boolean isValidPaddingStartPosition(int index) {
            return this.validPadding[index % this.charsPerChunk];
        }

        public Alphabet lowerCase() {
            if (!hasUpperCase()) {
                return this;
            }
            Preconditions.checkState(!hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int i = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i >= cArr2.length) {
                    break;
                }
                cArr[i] = Ascii.toLowerCase(cArr2[i]);
                i++;
            }
            Alphabet alphabet = new Alphabet(ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder(), this.name, ".lowerCase()"), cArr);
            return this.ignoreCase ? alphabet.ignoreCase() : alphabet;
        }

        public boolean matches(char c) {
            byte[] bArr = this.decodabet;
            return c < bArr.length && bArr[c] != -1;
        }

        public String toString() {
            return this.name;
        }

        public Alphabet upperCase() {
            if (!hasLowerCase()) {
                return this;
            }
            Preconditions.checkState(!hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int i = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i >= cArr2.length) {
                    break;
                }
                cArr[i] = Ascii.toUpperCase(cArr2[i]);
                i++;
            }
            Alphabet alphabet = new Alphabet(ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder(), this.name, ".upperCase()"), cArr);
            return this.ignoreCase ? alphabet.ignoreCase() : alphabet;
        }

        public Alphabet(String name, char[] chars, byte[] decodabet, boolean ignoreCase) {
            name.getClass();
            this.name = name;
            chars.getClass();
            this.chars = chars;
            try {
                int iLog2 = IntMath.log2(chars.length, RoundingMode.UNNECESSARY);
                this.bitsPerChar = iLog2;
                int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(iLog2);
                int i = 1 << (3 - iNumberOfTrailingZeros);
                this.charsPerChunk = i;
                this.bytesPerChunk = iLog2 >> iNumberOfTrailingZeros;
                this.mask = chars.length - 1;
                this.decodabet = decodabet;
                boolean[] zArr = new boolean[i];
                for (int i2 = 0; i2 < this.bytesPerChunk; i2++) {
                    zArr[IntMath.divide(i2 * 8, this.bitsPerChar, RoundingMode.CEILING)] = true;
                }
                this.validPadding = zArr;
                this.ignoreCase = ignoreCase;
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException("Illegal alphabet length " + chars.length, e);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Base16Encoding extends StandardBaseEncoding {
        public final char[] encoding;

        public Base16Encoding(String name, String alphabetChars) {
            this(new Alphabet(name, alphabetChars.toCharArray()));
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        public int decodeTo(byte[] target, CharSequence chars) throws DecodingException {
            target.getClass();
            if (chars.length() % 2 == 1) {
                throw new DecodingException("Invalid input length " + chars.length());
            }
            int i = 0;
            int i2 = 0;
            while (i < chars.length()) {
                target[i2] = (byte) ((this.alphabet.decode(chars.charAt(i)) << 4) | this.alphabet.decode(chars.charAt(i + 1)));
                i += 2;
                i2++;
            }
            return i2;
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        public void encodeTo(Appendable target, byte[] bytes, int off, int len) throws IOException {
            target.getClass();
            Preconditions.checkPositionIndexes(off, off + len, bytes.length);
            for (int i = 0; i < len; i++) {
                int i2 = bytes[off + i] & 255;
                target.append(this.encoding[i2]);
                target.append(this.encoding[i2 | 256]);
            }
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding
        public BaseEncoding newInstance(Alphabet alphabet, Character paddingChar) {
            return new Base16Encoding(alphabet);
        }

        public Base16Encoding(Alphabet alphabet) {
            super(alphabet, null);
            this.encoding = new char[512];
            Preconditions.checkArgument(alphabet.chars.length == 16);
            for (int i = 0; i < 256; i++) {
                char[] cArr = this.encoding;
                char[] cArr2 = alphabet.chars;
                cArr[i] = cArr2[i >>> 4];
                cArr[i | 256] = cArr2[i & 15];
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Base64Encoding extends StandardBaseEncoding {
        public Base64Encoding(String name, String alphabetChars, Character paddingChar) {
            this(new Alphabet(name, alphabetChars.toCharArray()), paddingChar);
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        public int decodeTo(byte[] target, CharSequence chars) throws DecodingException {
            target.getClass();
            CharSequence charSequenceTrimTrailingPadding = trimTrailingPadding(chars);
            if (!this.alphabet.isValidPaddingStartPosition(charSequenceTrimTrailingPadding.length())) {
                throw new DecodingException("Invalid input length " + charSequenceTrimTrailingPadding.length());
            }
            int i = 0;
            int i2 = 0;
            while (i < charSequenceTrimTrailingPadding.length()) {
                int i3 = i + 2;
                int iDecode = (this.alphabet.decode(charSequenceTrimTrailingPadding.charAt(i)) << 18) | (this.alphabet.decode(charSequenceTrimTrailingPadding.charAt(i + 1)) << 12);
                int i4 = i2 + 1;
                target[i2] = (byte) (iDecode >>> 16);
                if (i3 < charSequenceTrimTrailingPadding.length()) {
                    int i5 = i + 3;
                    int iDecode2 = iDecode | (this.alphabet.decode(charSequenceTrimTrailingPadding.charAt(i3)) << 6);
                    int i6 = i2 + 2;
                    target[i4] = (byte) ((iDecode2 >>> 8) & 255);
                    if (i5 < charSequenceTrimTrailingPadding.length()) {
                        i += 4;
                        i2 += 3;
                        target[i6] = (byte) ((iDecode2 | this.alphabet.decode(charSequenceTrimTrailingPadding.charAt(i5))) & 255);
                    } else {
                        i2 = i6;
                        i = i5;
                    }
                } else {
                    i2 = i4;
                    i = i3;
                }
            }
            return i2;
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        public void encodeTo(Appendable target, byte[] bytes, int off, int len) throws IOException {
            target.getClass();
            int i = off + len;
            Preconditions.checkPositionIndexes(off, i, bytes.length);
            while (len >= 3) {
                int i2 = off + 2;
                int i3 = ((bytes[off + 1] & 255) << 8) | ((bytes[off] & 255) << 16);
                off += 3;
                int i4 = i3 | (bytes[i2] & 255);
                target.append(this.alphabet.chars[i4 >>> 18]);
                target.append(this.alphabet.chars[(i4 >>> 12) & 63]);
                target.append(this.alphabet.chars[(i4 >>> 6) & 63]);
                target.append(this.alphabet.chars[i4 & 63]);
                len -= 3;
            }
            if (off < i) {
                encodeChunkTo(target, bytes, off, i - off);
            }
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding
        public BaseEncoding newInstance(Alphabet alphabet, Character paddingChar) {
            return new Base64Encoding(alphabet, paddingChar);
        }

        public Base64Encoding(Alphabet alphabet, Character paddingChar) {
            super(alphabet, paddingChar);
            Preconditions.checkArgument(alphabet.chars.length == 64);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DecodingException extends IOException {
        public DecodingException(String message) {
            super(message);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SeparatedBaseEncoding extends BaseEncoding {
        public final int afterEveryChars;
        public final BaseEncoding delegate;
        public final String separator;

        public SeparatedBaseEncoding(BaseEncoding delegate, String separator, int afterEveryChars) {
            delegate.getClass();
            this.delegate = delegate;
            separator.getClass();
            this.separator = separator;
            this.afterEveryChars = afterEveryChars;
            Preconditions.checkArgument(afterEveryChars > 0, "Cannot add a separator after every %s chars", afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public boolean canDecode(CharSequence chars) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length(); i++) {
                char cCharAt = chars.charAt(i);
                if (this.separator.indexOf(cCharAt) < 0) {
                    sb.append(cCharAt);
                }
            }
            return this.delegate.canDecode(sb);
        }

        @Override // com.google.common.io.BaseEncoding
        public int decodeTo(byte[] target, CharSequence chars) throws DecodingException {
            StringBuilder sb = new StringBuilder(chars.length());
            for (int i = 0; i < chars.length(); i++) {
                char cCharAt = chars.charAt(i);
                if (this.separator.indexOf(cCharAt) < 0) {
                    sb.append(cCharAt);
                }
            }
            return this.delegate.decodeTo(target, sb);
        }

        @Override // com.google.common.io.BaseEncoding
        @J2ktIncompatible
        @GwtIncompatible
        public InputStream decodingStream(Reader reader) {
            return this.delegate.decodingStream(BaseEncoding.ignoringReader(reader, this.separator));
        }

        @Override // com.google.common.io.BaseEncoding
        public void encodeTo(Appendable target, byte[] bytes, int off, int len) throws IOException {
            this.delegate.encodeTo(BaseEncoding.separatingAppendable(target, this.separator, this.afterEveryChars), bytes, off, len);
        }

        @Override // com.google.common.io.BaseEncoding
        @J2ktIncompatible
        @GwtIncompatible
        public OutputStream encodingStream(Writer output) {
            return this.delegate.encodingStream(BaseEncoding.separatingWriter(output, this.separator, this.afterEveryChars));
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding ignoreCase() {
            return this.delegate.ignoreCase().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding lowerCase() {
            return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public int maxDecodedSize(int chars) {
            return this.delegate.maxDecodedSize(chars);
        }

        @Override // com.google.common.io.BaseEncoding
        public int maxEncodedSize(int bytes) {
            int iMaxEncodedSize = this.delegate.maxEncodedSize(bytes);
            return (IntMath.divide(Math.max(0, iMaxEncodedSize - 1), this.afterEveryChars, RoundingMode.FLOOR) * this.separator.length()) + iMaxEncodedSize;
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.delegate);
            sb.append(".withSeparator(\"");
            sb.append(this.separator);
            sb.append("\", ");
            return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, this.afterEveryChars, ")");
        }

        @Override // com.google.common.io.BaseEncoding
        public CharSequence trimTrailingPadding(CharSequence chars) {
            return this.delegate.trimTrailingPadding(chars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding upperCase() {
            return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withPadChar(char padChar) {
            return this.delegate.withPadChar(padChar).withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withSeparator(String separator, int afterEveryChars) {
            throw new UnsupportedOperationException("Already have a separator");
        }
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    public static byte[] extract(byte[] result, int length) {
        if (length == result.length) {
            return result;
        }
        byte[] bArr = new byte[length];
        System.arraycopy(result, 0, bArr, 0, length);
        return bArr;
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static Reader ignoringReader(final Reader delegate, final String toIgnore) {
        delegate.getClass();
        toIgnore.getClass();
        return new Reader() { // from class: com.google.common.io.BaseEncoding.3
            @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                delegate.close();
            }

            @Override // java.io.Reader
            public int read() throws IOException {
                int i;
                do {
                    i = delegate.read();
                    if (i == -1) {
                        break;
                    }
                } while (toIgnore.indexOf((char) i) >= 0);
                return i;
            }

            @Override // java.io.Reader
            public int read(char[] cbuf, int off, int len) throws IOException {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static Appendable separatingAppendable(Appendable delegate, String separator, int afterEveryChars) {
        delegate.getClass();
        separator.getClass();
        Preconditions.checkArgument(afterEveryChars > 0);
        return new Appendable(afterEveryChars, delegate, separator) { // from class: com.google.common.io.BaseEncoding.4
            public int charsUntilSeparator;
            public final /* synthetic */ int val$afterEveryChars;
            public final /* synthetic */ Appendable val$delegate;
            public final /* synthetic */ String val$separator;

            {
                this.val$afterEveryChars = afterEveryChars;
                this.val$delegate = delegate;
                this.val$separator = separator;
                this.charsUntilSeparator = afterEveryChars;
            }

            @Override // java.lang.Appendable
            public Appendable append(char c) throws IOException {
                if (this.charsUntilSeparator == 0) {
                    this.val$delegate.append(this.val$separator);
                    this.charsUntilSeparator = this.val$afterEveryChars;
                }
                this.val$delegate.append(c);
                this.charsUntilSeparator--;
                return this;
            }

            @Override // java.lang.Appendable
            public Appendable append(CharSequence chars, int off, int len) {
                throw new UnsupportedOperationException();
            }

            @Override // java.lang.Appendable
            public Appendable append(CharSequence chars) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static Writer separatingWriter(final Writer delegate, String separator, int afterEveryChars) {
        final Appendable appendableSeparatingAppendable = separatingAppendable(delegate, separator, afterEveryChars);
        return new Writer() { // from class: com.google.common.io.BaseEncoding.5
            @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                delegate.close();
            }

            @Override // java.io.Writer, java.io.Flushable
            public void flush() throws IOException {
                delegate.flush();
            }

            @Override // java.io.Writer
            public void write(int c) throws IOException {
                appendableSeparatingAppendable.append((char) c);
            }

            @Override // java.io.Writer
            public void write(char[] chars, int off, int len) throws IOException {
                throw new UnsupportedOperationException();
            }
        };
    }

    public abstract boolean canDecode(CharSequence chars);

    public final byte[] decode(CharSequence chars) {
        try {
            return decodeChecked(chars);
        } catch (DecodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final byte[] decodeChecked(CharSequence chars) throws DecodingException {
        CharSequence charSequenceTrimTrailingPadding = trimTrailingPadding(chars);
        byte[] bArr = new byte[maxDecodedSize(charSequenceTrimTrailingPadding.length())];
        return extract(bArr, decodeTo(bArr, charSequenceTrimTrailingPadding));
    }

    public abstract int decodeTo(byte[] target, CharSequence chars) throws DecodingException;

    @J2ktIncompatible
    @GwtIncompatible
    public final ByteSource decodingSource(final CharSource encodedSource) {
        encodedSource.getClass();
        return new ByteSource(this) { // from class: com.google.common.io.BaseEncoding.2
            public final /* synthetic */ BaseEncoding this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.io.ByteSource
            public InputStream openStream() throws IOException {
                return this.this$0.decodingStream(encodedSource.openStream());
            }
        };
    }

    @J2ktIncompatible
    @GwtIncompatible
    public abstract InputStream decodingStream(Reader reader);

    public String encode(byte[] bytes) {
        return encode(bytes, 0, bytes.length);
    }

    public abstract void encodeTo(Appendable target, byte[] bytes, int off, int len) throws IOException;

    @J2ktIncompatible
    @GwtIncompatible
    public final ByteSink encodingSink(final CharSink encodedSink) {
        encodedSink.getClass();
        return new ByteSink(this) { // from class: com.google.common.io.BaseEncoding.1
            public final /* synthetic */ BaseEncoding this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.io.ByteSink
            public OutputStream openStream() throws IOException {
                return this.this$0.encodingStream(encodedSink.openStream());
            }
        };
    }

    @J2ktIncompatible
    @GwtIncompatible
    public abstract OutputStream encodingStream(Writer writer);

    public abstract BaseEncoding ignoreCase();

    public abstract BaseEncoding lowerCase();

    public abstract int maxDecodedSize(int chars);

    public abstract int maxEncodedSize(int bytes);

    public abstract BaseEncoding omitPadding();

    public CharSequence trimTrailingPadding(CharSequence chars) {
        chars.getClass();
        return chars;
    }

    public abstract BaseEncoding upperCase();

    public abstract BaseEncoding withPadChar(char padChar);

    public abstract BaseEncoding withSeparator(String separator, int n);

    public final String encode(byte[] bytes, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, bytes.length);
        StringBuilder sb = new StringBuilder(maxEncodedSize(len));
        try {
            encodeTo(sb, bytes, off, len);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class StandardBaseEncoding extends BaseEncoding {
        public final Alphabet alphabet;

        @LazyInit
        public volatile BaseEncoding ignoreCase;

        @LazyInit
        public volatile BaseEncoding lowerCase;
        public final Character paddingChar;

        @LazyInit
        public volatile BaseEncoding upperCase;

        public StandardBaseEncoding(Alphabet alphabet, Character paddingChar) {
            alphabet.getClass();
            this.alphabet = alphabet;
            Preconditions.checkArgument(paddingChar == null || !alphabet.matches(paddingChar.charValue()), "Padding character %s was already in alphabet", paddingChar);
            this.paddingChar = paddingChar;
        }

        @Override // com.google.common.io.BaseEncoding
        public boolean canDecode(CharSequence chars) {
            chars.getClass();
            CharSequence charSequenceTrimTrailingPadding = trimTrailingPadding(chars);
            if (this.alphabet.isValidPaddingStartPosition(charSequenceTrimTrailingPadding.length())) {
                for (int i = 0; i < charSequenceTrimTrailingPadding.length(); i++) {
                    if (this.alphabet.canDecode(charSequenceTrimTrailingPadding.charAt(i))) {
                    }
                }
                return true;
            }
            return false;
        }

        @Override // com.google.common.io.BaseEncoding
        public int decodeTo(byte[] target, CharSequence chars) throws DecodingException {
            Alphabet alphabet;
            target.getClass();
            CharSequence charSequenceTrimTrailingPadding = trimTrailingPadding(chars);
            if (!this.alphabet.isValidPaddingStartPosition(charSequenceTrimTrailingPadding.length())) {
                throw new DecodingException("Invalid input length " + charSequenceTrimTrailingPadding.length());
            }
            int i = 0;
            int i2 = 0;
            while (i < charSequenceTrimTrailingPadding.length()) {
                long jDecode = 0;
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    alphabet = this.alphabet;
                    if (i3 >= alphabet.charsPerChunk) {
                        break;
                    }
                    jDecode <<= alphabet.bitsPerChar;
                    if (i + i3 < charSequenceTrimTrailingPadding.length()) {
                        jDecode |= (long) this.alphabet.decode(charSequenceTrimTrailingPadding.charAt(i4 + i));
                        i4++;
                    }
                    i3++;
                }
                int i5 = alphabet.bytesPerChunk;
                int i6 = (i5 * 8) - (i4 * alphabet.bitsPerChar);
                int i7 = (i5 - 1) * 8;
                while (i7 >= i6) {
                    target[i2] = (byte) ((jDecode >>> i7) & 255);
                    i7 -= 8;
                    i2++;
                }
                i += this.alphabet.charsPerChunk;
            }
            return i2;
        }

        @Override // com.google.common.io.BaseEncoding
        @J2ktIncompatible
        @GwtIncompatible
        public InputStream decodingStream(final Reader reader) {
            reader.getClass();
            return new InputStream(this) { // from class: com.google.common.io.BaseEncoding.StandardBaseEncoding.2
                public final /* synthetic */ StandardBaseEncoding this$0;
                public int bitBuffer = 0;
                public int bitBufferLength = 0;
                public int readChars = 0;
                public boolean hitPadding = false;

                {
                    this.this$0 = this;
                }

                @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    reader.close();
                }

                /* JADX WARN: Code restructure failed: missing block: B:24:0x006e, code lost:
                
                    throw new com.google.common.io.BaseEncoding.DecodingException("Padding cannot start at index " + r4.readChars);
                 */
                @Override // java.io.InputStream
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public int read() throws java.io.IOException {
                    /*
                        r4 = this;
                    L0:
                        java.io.Reader r0 = r2
                        int r0 = r0.read()
                        r1 = -1
                        if (r0 != r1) goto L31
                        boolean r0 = r4.hitPadding
                        if (r0 != 0) goto L30
                        com.google.common.io.BaseEncoding$StandardBaseEncoding r0 = r4.this$0
                        com.google.common.io.BaseEncoding$Alphabet r0 = r0.alphabet
                        int r2 = r4.readChars
                        boolean r0 = r0.isValidPaddingStartPosition(r2)
                        if (r0 == 0) goto L1a
                        goto L30
                    L1a:
                        com.google.common.io.BaseEncoding$DecodingException r0 = new com.google.common.io.BaseEncoding$DecodingException
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                        java.lang.String r2 = "Invalid input length "
                        r1.<init>(r2)
                        int r2 = r4.readChars
                        r1.append(r2)
                        java.lang.String r1 = r1.toString()
                        r0.<init>(r1)
                        throw r0
                    L30:
                        return r1
                    L31:
                        int r1 = r4.readChars
                        r2 = 1
                        int r1 = r1 + r2
                        r4.readChars = r1
                        char r0 = (char) r0
                        com.google.common.io.BaseEncoding$StandardBaseEncoding r1 = r4.this$0
                        java.lang.Character r1 = r1.paddingChar
                        if (r1 == 0) goto L72
                        char r1 = r1.charValue()
                        if (r1 != r0) goto L72
                        boolean r0 = r4.hitPadding
                        if (r0 != 0) goto L6f
                        int r0 = r4.readChars
                        if (r0 == r2) goto L59
                        com.google.common.io.BaseEncoding$StandardBaseEncoding r1 = r4.this$0
                        com.google.common.io.BaseEncoding$Alphabet r1 = r1.alphabet
                        int r0 = r0 + (-1)
                        boolean r0 = r1.isValidPaddingStartPosition(r0)
                        if (r0 == 0) goto L59
                        goto L6f
                    L59:
                        com.google.common.io.BaseEncoding$DecodingException r0 = new com.google.common.io.BaseEncoding$DecodingException
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                        java.lang.String r2 = "Padding cannot start at index "
                        r1.<init>(r2)
                        int r2 = r4.readChars
                        r1.append(r2)
                        java.lang.String r1 = r1.toString()
                        r0.<init>(r1)
                        throw r0
                    L6f:
                        r4.hitPadding = r2
                        goto L0
                    L72:
                        boolean r1 = r4.hitPadding
                        if (r1 != 0) goto L9e
                        int r1 = r4.bitBuffer
                        com.google.common.io.BaseEncoding$StandardBaseEncoding r2 = r4.this$0
                        com.google.common.io.BaseEncoding$Alphabet r2 = r2.alphabet
                        int r3 = r2.bitsPerChar
                        int r1 = r1 << r3
                        r4.bitBuffer = r1
                        int r0 = r2.decode(r0)
                        r0 = r0 | r1
                        r4.bitBuffer = r0
                        int r1 = r4.bitBufferLength
                        com.google.common.io.BaseEncoding$StandardBaseEncoding r2 = r4.this$0
                        com.google.common.io.BaseEncoding$Alphabet r2 = r2.alphabet
                        int r2 = r2.bitsPerChar
                        int r1 = r1 + r2
                        r4.bitBufferLength = r1
                        r2 = 8
                        if (r1 < r2) goto L0
                        int r1 = r1 - r2
                        r4.bitBufferLength = r1
                        int r0 = r0 >> r1
                        r0 = r0 & 255(0xff, float:3.57E-43)
                        return r0
                    L9e:
                        com.google.common.io.BaseEncoding$DecodingException r1 = new com.google.common.io.BaseEncoding$DecodingException
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder
                        java.lang.String r3 = "Expected padding character but found '"
                        r2.<init>(r3)
                        r2.append(r0)
                        java.lang.String r0 = "' at index "
                        r2.append(r0)
                        int r0 = r4.readChars
                        r2.append(r0)
                        java.lang.String r0 = r2.toString()
                        r1.<init>(r0)
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.BaseEncoding.StandardBaseEncoding.AnonymousClass2.read():int");
                }

                @Override // java.io.InputStream
                public int read(byte[] buf, int off, int len) throws IOException {
                    int i = len + off;
                    Preconditions.checkPositionIndexes(off, i, buf.length);
                    int i2 = off;
                    while (i2 < i) {
                        int i3 = read();
                        if (i3 == -1) {
                            int i4 = i2 - off;
                            if (i4 == 0) {
                                return -1;
                            }
                            return i4;
                        }
                        buf[i2] = (byte) i3;
                        i2++;
                    }
                    return i2 - off;
                }
            };
        }

        public void encodeChunkTo(Appendable target, byte[] bytes, int off, int len) throws IOException {
            target.getClass();
            Preconditions.checkPositionIndexes(off, off + len, bytes.length);
            int i = 0;
            Preconditions.checkArgument(len <= this.alphabet.bytesPerChunk);
            long j = 0;
            for (int i2 = 0; i2 < len; i2++) {
                j = (j | ((long) (bytes[off + i2] & 255))) << 8;
            }
            int i3 = ((len + 1) * 8) - this.alphabet.bitsPerChar;
            while (i < len * 8) {
                Alphabet alphabet = this.alphabet;
                target.append(alphabet.chars[((int) (j >>> (i3 - i))) & alphabet.mask]);
                i += this.alphabet.bitsPerChar;
            }
            if (this.paddingChar != null) {
                while (i < this.alphabet.bytesPerChunk * 8) {
                    target.append(this.paddingChar.charValue());
                    i += this.alphabet.bitsPerChar;
                }
            }
        }

        @Override // com.google.common.io.BaseEncoding
        public void encodeTo(Appendable target, byte[] bytes, int off, int len) throws IOException {
            target.getClass();
            Preconditions.checkPositionIndexes(off, off + len, bytes.length);
            int i = 0;
            while (i < len) {
                encodeChunkTo(target, bytes, off + i, Math.min(this.alphabet.bytesPerChunk, len - i));
                i += this.alphabet.bytesPerChunk;
            }
        }

        @Override // com.google.common.io.BaseEncoding
        @J2ktIncompatible
        @GwtIncompatible
        public OutputStream encodingStream(final Writer out) {
            out.getClass();
            return new OutputStream(this) { // from class: com.google.common.io.BaseEncoding.StandardBaseEncoding.1
                public final /* synthetic */ StandardBaseEncoding this$0;
                public int bitBuffer = 0;
                public int bitBufferLength = 0;
                public int writtenChars = 0;

                {
                    this.this$0 = this;
                }

                @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    int i = this.bitBufferLength;
                    if (i > 0) {
                        int i2 = this.bitBuffer;
                        Alphabet alphabet = this.this$0.alphabet;
                        out.write(alphabet.chars[(i2 << (alphabet.bitsPerChar - i)) & alphabet.mask]);
                        this.writtenChars++;
                        if (this.this$0.paddingChar != null) {
                            while (true) {
                                int i3 = this.writtenChars;
                                StandardBaseEncoding standardBaseEncoding = this.this$0;
                                if (i3 % standardBaseEncoding.alphabet.charsPerChunk == 0) {
                                    break;
                                }
                                out.write(standardBaseEncoding.paddingChar.charValue());
                                this.writtenChars++;
                            }
                        }
                    }
                    out.close();
                }

                @Override // java.io.OutputStream, java.io.Flushable
                public void flush() throws IOException {
                    out.flush();
                }

                @Override // java.io.OutputStream
                public void write(int b) throws IOException {
                    this.bitBuffer = (b & 255) | (this.bitBuffer << 8);
                    this.bitBufferLength += 8;
                    while (true) {
                        int i = this.bitBufferLength;
                        Alphabet alphabet = this.this$0.alphabet;
                        int i2 = alphabet.bitsPerChar;
                        if (i < i2) {
                            return;
                        }
                        out.write(alphabet.chars[(this.bitBuffer >> (i - i2)) & alphabet.mask]);
                        this.writtenChars++;
                        this.bitBufferLength -= this.this$0.alphabet.bitsPerChar;
                    }
                }
            };
        }

        public boolean equals(Object other) {
            if (other instanceof StandardBaseEncoding) {
                StandardBaseEncoding standardBaseEncoding = (StandardBaseEncoding) other;
                if (this.alphabet.equals(standardBaseEncoding.alphabet) && Objects.equals(this.paddingChar, standardBaseEncoding.paddingChar)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.alphabet.hashCode() ^ Objects.hashCode(this.paddingChar);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding ignoreCase() {
            BaseEncoding baseEncodingNewInstance = this.ignoreCase;
            if (baseEncodingNewInstance == null) {
                Alphabet alphabetIgnoreCase = this.alphabet.ignoreCase();
                baseEncodingNewInstance = alphabetIgnoreCase == this.alphabet ? this : newInstance(alphabetIgnoreCase, this.paddingChar);
                this.ignoreCase = baseEncodingNewInstance;
            }
            return baseEncodingNewInstance;
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding lowerCase() {
            BaseEncoding baseEncodingNewInstance = this.lowerCase;
            if (baseEncodingNewInstance == null) {
                Alphabet alphabetLowerCase = this.alphabet.lowerCase();
                baseEncodingNewInstance = alphabetLowerCase == this.alphabet ? this : newInstance(alphabetLowerCase, this.paddingChar);
                this.lowerCase = baseEncodingNewInstance;
            }
            return baseEncodingNewInstance;
        }

        @Override // com.google.common.io.BaseEncoding
        public int maxDecodedSize(int chars) {
            return (int) (((((long) this.alphabet.bitsPerChar) * ((long) chars)) + 7) / 8);
        }

        @Override // com.google.common.io.BaseEncoding
        public int maxEncodedSize(int bytes) {
            Alphabet alphabet = this.alphabet;
            return IntMath.divide(bytes, alphabet.bytesPerChunk, RoundingMode.CEILING) * alphabet.charsPerChunk;
        }

        public BaseEncoding newInstance(Alphabet alphabet, Character paddingChar) {
            return new StandardBaseEncoding(alphabet, paddingChar);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.paddingChar == null ? this : newInstance(this.alphabet, null);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("BaseEncoding.");
            sb.append(this.alphabet);
            if (8 % this.alphabet.bitsPerChar != 0) {
                if (this.paddingChar == null) {
                    sb.append(".omitPadding()");
                } else {
                    sb.append(".withPadChar('");
                    sb.append(this.paddingChar);
                    sb.append("')");
                }
            }
            return sb.toString();
        }

        @Override // com.google.common.io.BaseEncoding
        public CharSequence trimTrailingPadding(CharSequence chars) {
            chars.getClass();
            Character ch = this.paddingChar;
            if (ch == null) {
                return chars;
            }
            char cCharValue = ch.charValue();
            int length = chars.length() - 1;
            while (length >= 0 && chars.charAt(length) == cCharValue) {
                length--;
            }
            return chars.subSequence(0, length + 1);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding upperCase() {
            BaseEncoding baseEncodingNewInstance = this.upperCase;
            if (baseEncodingNewInstance == null) {
                Alphabet alphabetUpperCase = this.alphabet.upperCase();
                baseEncodingNewInstance = alphabetUpperCase == this.alphabet ? this : newInstance(alphabetUpperCase, this.paddingChar);
                this.upperCase = baseEncodingNewInstance;
            }
            return baseEncodingNewInstance;
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withPadChar(char padChar) {
            Character ch;
            return (8 % this.alphabet.bitsPerChar == 0 || ((ch = this.paddingChar) != null && ch.charValue() == padChar)) ? this : newInstance(this.alphabet, Character.valueOf(padChar));
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withSeparator(String separator, int afterEveryChars) {
            for (int i = 0; i < separator.length(); i++) {
                Preconditions.checkArgument(!this.alphabet.matches(separator.charAt(i)), "Separator (%s) cannot contain alphabet characters", separator);
            }
            Character ch = this.paddingChar;
            if (ch != null) {
                Preconditions.checkArgument(separator.indexOf(ch.charValue()) < 0, "Separator (%s) cannot contain padding character", separator);
            }
            return new SeparatedBaseEncoding(this, separator, afterEveryChars);
        }

        public StandardBaseEncoding(String name, String alphabetChars, Character paddingChar) {
            this(new Alphabet(name, alphabetChars.toCharArray()), paddingChar);
        }
    }
}
