package kotlin.io.encoding;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import java.io.IOException;
import kotlin.SinceKotlin;
import kotlin.collections.AbstractList;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ExperimentalEncodingApi
@SinceKotlin(version = "1.8")
public class Base64 {

    @NotNull
    public static final Base64 Mime;

    @NotNull
    public static final Base64 UrlSafe;
    public static final int bitsPerByte = 8;
    public static final int bitsPerSymbol = 6;
    public static final int bytesPerGroup = 3;
    public static final int mimeGroupsPerLine = 19;
    public static final int mimeLineLength = 76;
    public static final byte padSymbol = 61;
    public static final int symbolsPerGroup = 4;
    public final boolean isMimeScheme;
    public final boolean isUrlSafe;

    @NotNull
    public final PaddingOption paddingOption;

    @NotNull
    public static final Default Default = new Default();

    @NotNull
    public static final byte[] mimeLineSeparatorSymbols = {13, 10};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Default extends Base64 {
        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Base64 getMime() {
            return Base64.Mime;
        }

        @NotNull
        public final byte[] getMimeLineSeparatorSymbols$kotlin_stdlib() {
            return Base64.mimeLineSeparatorSymbols;
        }

        @NotNull
        public final Base64 getUrlSafe() {
            return Base64.UrlSafe;
        }

        public Default() {
            super(false, false, PaddingOption.PRESENT);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SinceKotlin(version = "2.0")
    public static final class PaddingOption {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ PaddingOption[] $VALUES;
        public static final PaddingOption PRESENT = new PaddingOption("PRESENT", 0);
        public static final PaddingOption ABSENT = new PaddingOption("ABSENT", 1);
        public static final PaddingOption PRESENT_OPTIONAL = new PaddingOption("PRESENT_OPTIONAL", 2);
        public static final PaddingOption ABSENT_OPTIONAL = new PaddingOption("ABSENT_OPTIONAL", 3);

        public static final /* synthetic */ PaddingOption[] $values() {
            return new PaddingOption[]{PRESENT, ABSENT, PRESENT_OPTIONAL, ABSENT_OPTIONAL};
        }

        static {
            PaddingOption[] paddingOptionArr$values = $values();
            $VALUES = paddingOptionArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(paddingOptionArr$values);
        }

        public PaddingOption(String str, int i) {
        }

        @NotNull
        public static EnumEntries<PaddingOption> getEntries() {
            return $ENTRIES;
        }

        public static PaddingOption valueOf(String str) {
            return (PaddingOption) Enum.valueOf(PaddingOption.class, str);
        }

        public static PaddingOption[] values() {
            return (PaddingOption[]) $VALUES.clone();
        }
    }

    static {
        PaddingOption paddingOption = PaddingOption.PRESENT;
        UrlSafe = new Base64(true, false, paddingOption);
        Mime = new Base64(false, true, paddingOption);
    }

    public /* synthetic */ Base64(boolean z, boolean z2, PaddingOption paddingOption, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2, paddingOption);
    }

    public static /* synthetic */ byte[] decode$default(Base64 base64, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decode");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return base64.decode(bArr, i, i2);
    }

    public static /* synthetic */ int decodeIntoByteArray$default(Base64 base64, byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = bArr.length;
        }
        return base64.decodeIntoByteArray(bArr, bArr2, i, i2, i3);
    }

    public static /* synthetic */ String encode$default(Base64 base64, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encode");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return base64.encode(bArr, i, i2);
    }

    public static /* synthetic */ int encodeIntoByteArray$default(Base64 base64, byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeIntoByteArray");
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = bArr.length;
        }
        return base64.encodeIntoByteArray(bArr, bArr2, i, i2, i3);
    }

    public static /* synthetic */ Appendable encodeToAppendable$default(Base64 base64, byte[] bArr, Appendable appendable, int i, int i2, int i3, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeToAppendable");
        }
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = bArr.length;
        }
        base64.encodeToAppendable(bArr, appendable, i, i2);
        return appendable;
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(Base64 base64, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeToByteArray");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return base64.encodeToByteArray(bArr, i, i2);
    }

    @NotNull
    public final String bytesToStringImpl$kotlin_stdlib(@NotNull byte[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        StringBuilder sb = new StringBuilder(source.length);
        for (byte b : source) {
            sb.append((char) b);
        }
        return sb.toString();
    }

    @NotNull
    public final byte[] charsToBytesImpl$kotlin_stdlib(@NotNull CharSequence source, int i, int i2) {
        Intrinsics.checkNotNullParameter(source, "source");
        checkSourceBounds$kotlin_stdlib(source.length(), i, i2);
        byte[] bArr = new byte[i2 - i];
        int i3 = 0;
        while (i < i2) {
            char cCharAt = source.charAt(i);
            if (cCharAt <= 255) {
                bArr[i3] = (byte) cCharAt;
                i3++;
            } else {
                bArr[i3] = 63;
                i3++;
            }
            i++;
        }
        return bArr;
    }

    public final void checkDestinationBounds(int i, int i2, int i3) {
        if (i2 < 0 || i2 > i) {
            throw new IndexOutOfBoundsException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("destination offset: ", i2, ", destination size: ", i));
        }
        int i4 = i2 + i3;
        if (i4 < 0 || i4 > i) {
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("The destination array does not have enough capacity, destination offset: ", i2, ", destination size: ", i, ", capacity needed: ");
            sbM.append(i3);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
    }

    public final void checkPaddingIsAllowed(int i) {
        if (this.paddingOption == PaddingOption.ABSENT) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("The padding option is set to ABSENT, but the input has a pad character at index ", i));
        }
    }

    public final void checkSourceBounds$kotlin_stdlib(int i, int i2, int i3) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i2, i3, i);
    }

    @NotNull
    public final byte[] decode(@NotNull byte[] source, int i, int i2) {
        Intrinsics.checkNotNullParameter(source, "source");
        checkSourceBounds$kotlin_stdlib(source.length, i, i2);
        int iDecodeSize$kotlin_stdlib = decodeSize$kotlin_stdlib(source, i, i2);
        byte[] bArr = new byte[iDecodeSize$kotlin_stdlib];
        if (decodeImpl(source, bArr, 0, i, i2) == iDecodeSize$kotlin_stdlib) {
            return bArr;
        }
        throw new IllegalStateException("Check failed.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d6, code lost:
    
        if (r7 == (-2)) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d9, code lost:
    
        if (r7 == (-8)) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00db, code lost:
    
        if (r4 != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00e1, code lost:
    
        if (r19.paddingOption == kotlin.io.encoding.Base64.PaddingOption.PRESENT) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00eb, code lost:
    
        throw new java.lang.IllegalArgumentException("The padding option is set to PRESENT, but the input is not properly padded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ec, code lost:
    
        if (r8 != 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ee, code lost:
    
        r3 = skipIllegalSymbolsIfMime(r20, r6, r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f2, code lost:
    
        if (r3 < r24) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f6, code lost:
    
        return r9 - r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00f7, code lost:
    
        r1 = r20[r3] & 255;
        r4 = new java.lang.StringBuilder("Symbol '");
        r4.append((char) r1);
        r4.append("'(");
        kotlin.text.CharsKt__CharJVMKt.checkRadix(8);
        r1 = java.lang.Integer.toString(r1, 8);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, "toString(...)");
        r4.append(r1);
        r4.append(") at index ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0128, code lost:
    
        throw new java.lang.IllegalArgumentException(android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(r4, r3 - 1, " is prohibited after the pad character"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0130, code lost:
    
        throw new java.lang.IllegalArgumentException("The pad bits must be zeros");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0138, code lost:
    
        throw new java.lang.IllegalArgumentException("The last unit of input does not have enough bits");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int decodeImpl(byte[] r20, byte[] r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.encoding.Base64.decodeImpl(byte[], byte[], int, int, int):int");
    }

    public final int decodeIntoByteArray(@NotNull byte[] source, @NotNull byte[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        checkSourceBounds$kotlin_stdlib(source.length, i2, i3);
        checkDestinationBounds(destination.length, i, decodeSize$kotlin_stdlib(source, i2, i3));
        return decodeImpl(source, destination, i, i2, i3);
    }

    public final int decodeSize$kotlin_stdlib(@NotNull byte[] source, int i, int i2) {
        Intrinsics.checkNotNullParameter(source, "source");
        int i3 = i2 - i;
        if (i3 == 0) {
            return 0;
        }
        if (i3 == 1) {
            throw new IllegalArgumentException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Input should have at least 2 symbols for Base64 decoding, startIndex: ", i, ", endIndex: ", i2));
        }
        if (this.isMimeScheme) {
            while (true) {
                if (i >= i2) {
                    break;
                }
                int i4 = Base64Kt.base64DecodeMap[source[i] & 255];
                if (i4 < 0) {
                    if (i4 == -2) {
                        i3 -= i2 - i;
                        break;
                    }
                    i3--;
                }
                i++;
            }
        } else if (source[i2 - 1] == 61) {
            i3 = source[i2 + (-2)] == 61 ? i3 - 2 : i3 - 1;
        }
        return (int) ((((long) i3) * ((long) 6)) / ((long) 8));
    }

    @NotNull
    public final String encode(@NotNull byte[] source, int i, int i2) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new String(encodeToByteArrayImpl$kotlin_stdlib(source, i, i2), Charsets.ISO_8859_1);
    }

    public final int encodeIntoByteArray(@NotNull byte[] source, @NotNull byte[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return encodeIntoByteArrayImpl$kotlin_stdlib(source, destination, i, i2, i3);
    }

    public final int encodeIntoByteArrayImpl$kotlin_stdlib(@NotNull byte[] source, @NotNull byte[] destination, int i, int i2, int i3) {
        int i4 = i2;
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        checkSourceBounds$kotlin_stdlib(source.length, i4, i3);
        checkDestinationBounds(destination.length, i, encodeSize$kotlin_stdlib(i3 - i4));
        byte[] bArr = this.isUrlSafe ? Base64Kt.base64UrlEncodeMap : Base64Kt.base64EncodeMap;
        int i5 = this.isMimeScheme ? 19 : Integer.MAX_VALUE;
        int i6 = i;
        while (i4 + 2 < i3) {
            int iMin = Math.min((i3 - i4) / 3, i5);
            for (int i7 = 0; i7 < iMin; i7++) {
                int i8 = source[i4] & 255;
                int i9 = i4 + 2;
                int i10 = source[i4 + 1] & 255;
                i4 += 3;
                int i11 = (i10 << 8) | (i8 << 16) | (source[i9] & 255);
                destination[i6] = bArr[i11 >>> 18];
                destination[i6 + 1] = bArr[(i11 >>> 12) & 63];
                int i12 = i6 + 3;
                destination[i6 + 2] = bArr[(i11 >>> 6) & 63];
                i6 += 4;
                destination[i12] = bArr[i11 & 63];
            }
            if (iMin == i5 && i4 != i3) {
                int i13 = i6 + 1;
                byte[] bArr2 = mimeLineSeparatorSymbols;
                destination[i6] = bArr2[0];
                i6 += 2;
                destination[i13] = bArr2[1];
            }
        }
        int i14 = i3 - i4;
        if (i14 == 1) {
            int i15 = i4 + 1;
            int i16 = (source[i4] & 255) << 4;
            destination[i6] = bArr[i16 >>> 6];
            int i17 = i6 + 2;
            destination[i6 + 1] = bArr[i16 & 63];
            if (shouldPadOnEncode()) {
                int i18 = i6 + 3;
                destination[i17] = padSymbol;
                i6 += 4;
                destination[i18] = padSymbol;
                i4 = i15;
            } else {
                i4 = i15;
                i6 = i17;
            }
        } else if (i14 == 2) {
            int i19 = i4 + 1;
            int i20 = source[i4] & 255;
            i4 += 2;
            int i21 = ((source[i19] & 255) << 2) | (i20 << 10);
            destination[i6] = bArr[i21 >>> 12];
            destination[i6 + 1] = bArr[(i21 >>> 6) & 63];
            int i22 = i6 + 3;
            destination[i6 + 2] = bArr[i21 & 63];
            if (shouldPadOnEncode()) {
                i6 += 4;
                destination[i22] = padSymbol;
            } else {
                i6 = i22;
            }
        }
        if (i4 == i3) {
            return i6 - i;
        }
        throw new IllegalStateException("Check failed.");
    }

    public final int encodeSize$kotlin_stdlib(int i) {
        int i2 = i / 3;
        int i3 = i % 3;
        int i4 = i2 * 4;
        if (i3 != 0) {
            i4 += shouldPadOnEncode() ? 4 : i3 + 1;
        }
        if (this.isMimeScheme) {
            i4 += ((i4 - 1) / 76) * 2;
        }
        if (i4 >= 0) {
            return i4;
        }
        throw new IllegalArgumentException("Input is too big");
    }

    @NotNull
    public final <A extends Appendable> A encodeToAppendable(@NotNull byte[] source, @NotNull A destination, int i, int i2) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        destination.append(new String(encodeToByteArrayImpl$kotlin_stdlib(source, i, i2), Charsets.ISO_8859_1));
        return destination;
    }

    @NotNull
    public final byte[] encodeToByteArray(@NotNull byte[] source, int i, int i2) {
        Intrinsics.checkNotNullParameter(source, "source");
        return encodeToByteArrayImpl$kotlin_stdlib(source, i, i2);
    }

    @NotNull
    public final byte[] encodeToByteArrayImpl$kotlin_stdlib(@NotNull byte[] source, int i, int i2) {
        Intrinsics.checkNotNullParameter(source, "source");
        checkSourceBounds$kotlin_stdlib(source.length, i, i2);
        byte[] bArr = new byte[encodeSize$kotlin_stdlib(i2 - i)];
        encodeIntoByteArrayImpl$kotlin_stdlib(source, bArr, 0, i, i2);
        return bArr;
    }

    @NotNull
    public final PaddingOption getPaddingOption$kotlin_stdlib() {
        return this.paddingOption;
    }

    public final int handlePaddingSymbol(byte[] bArr, int i, int i2, int i3) {
        if (i3 == -8) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Redundant pad character at index ", i));
        }
        if (i3 == -6) {
            checkPaddingIsAllowed(i);
            return i + 1;
        }
        if (i3 != -4) {
            if (i3 == -2) {
                return i + 1;
            }
            throw new IllegalStateException("Unreachable");
        }
        checkPaddingIsAllowed(i);
        int iSkipIllegalSymbolsIfMime = skipIllegalSymbolsIfMime(bArr, i + 1, i2);
        if (iSkipIllegalSymbolsIfMime == i2 || bArr[iSkipIllegalSymbolsIfMime] != 61) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Missing one pad character at index ", iSkipIllegalSymbolsIfMime));
        }
        return iSkipIllegalSymbolsIfMime + 1;
    }

    public final boolean isMimeScheme$kotlin_stdlib() {
        return this.isMimeScheme;
    }

    public final boolean isUrlSafe$kotlin_stdlib() {
        return this.isUrlSafe;
    }

    public final boolean shouldPadOnEncode() {
        PaddingOption paddingOption = this.paddingOption;
        return paddingOption == PaddingOption.PRESENT || paddingOption == PaddingOption.PRESENT_OPTIONAL;
    }

    public final int skipIllegalSymbolsIfMime(byte[] bArr, int i, int i2) {
        if (!this.isMimeScheme) {
            return i;
        }
        while (i < i2) {
            if (Base64Kt.base64DecodeMap[bArr[i] & 255] != -1) {
                break;
            }
            i++;
        }
        return i;
    }

    @SinceKotlin(version = "2.0")
    @NotNull
    public final Base64 withPadding(@NotNull PaddingOption option) {
        Intrinsics.checkNotNullParameter(option, "option");
        return this.paddingOption == option ? this : new Base64(this.isUrlSafe, this.isMimeScheme, option);
    }

    public Base64(boolean z, boolean z2, PaddingOption paddingOption) {
        this.isUrlSafe = z;
        this.isMimeScheme = z2;
        this.paddingOption = paddingOption;
        if (z && z2) {
            throw new IllegalArgumentException("Failed requirement.");
        }
    }

    public static /* synthetic */ byte[] decode$default(Base64 base64, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decode");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return base64.decode(charSequence, i, i2);
    }

    public static /* synthetic */ int decodeIntoByteArray$default(Base64 base64, CharSequence charSequence, byte[] bArr, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = charSequence.length();
        }
        return base64.decodeIntoByteArray(charSequence, bArr, i, i2, i3);
    }

    public final int decodeIntoByteArray(@NotNull CharSequence source, @NotNull byte[] destination, int i, int i2, int i3) {
        byte[] bArrCharsToBytesImpl$kotlin_stdlib;
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (source instanceof String) {
            String str = (String) source;
            checkSourceBounds$kotlin_stdlib(str.length(), i2, i3);
            String strSubstring = str.substring(i2, i3);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            bArrCharsToBytesImpl$kotlin_stdlib = strSubstring.getBytes(Charsets.ISO_8859_1);
            Intrinsics.checkNotNullExpressionValue(bArrCharsToBytesImpl$kotlin_stdlib, "getBytes(...)");
        } else {
            bArrCharsToBytesImpl$kotlin_stdlib = charsToBytesImpl$kotlin_stdlib(source, i2, i3);
        }
        return decodeIntoByteArray$default(this, bArrCharsToBytesImpl$kotlin_stdlib, destination, i, 0, 0, 24, (Object) null);
    }

    @NotNull
    public final byte[] decode(@NotNull CharSequence source, int i, int i2) {
        byte[] bArrCharsToBytesImpl$kotlin_stdlib;
        Intrinsics.checkNotNullParameter(source, "source");
        if (source instanceof String) {
            String str = (String) source;
            checkSourceBounds$kotlin_stdlib(str.length(), i, i2);
            String strSubstring = str.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            bArrCharsToBytesImpl$kotlin_stdlib = strSubstring.getBytes(Charsets.ISO_8859_1);
            Intrinsics.checkNotNullExpressionValue(bArrCharsToBytesImpl$kotlin_stdlib, "getBytes(...)");
        } else {
            bArrCharsToBytesImpl$kotlin_stdlib = charsToBytesImpl$kotlin_stdlib(source, i, i2);
        }
        return decode$default(this, bArrCharsToBytesImpl$kotlin_stdlib, 0, 0, 6, (Object) null);
    }
}
