package kotlinx.serialization.internal;

import com.google.common.base.Ascii;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nPlatform.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Platform.common.kt\nkotlinx/serialization/internal/InternalHexConverter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,190:1\n1#2:191\n*E\n"})
public final class InternalHexConverter {

    @NotNull
    public static final InternalHexConverter INSTANCE = new InternalHexConverter();

    @NotNull
    public static final String hexCode = "0123456789ABCDEF";

    public static /* synthetic */ String printHexBinary$default(InternalHexConverter internalHexConverter, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return internalHexConverter.printHexBinary(bArr, z);
    }

    public final int hexToInt(char c) {
        if ('0' <= c && c < ':') {
            return c - '0';
        }
        if ('A' <= c && c < 'G') {
            return c - '7';
        }
        if ('a' > c || c >= 'g') {
            return -1;
        }
        return c - 'W';
    }

    @NotNull
    public final byte[] parseHexBinary(@NotNull String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        int length = s.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("HexBinary string must be even length");
        }
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            int iHexToInt = hexToInt(s.charAt(i));
            int i2 = i + 1;
            int iHexToInt2 = hexToInt(s.charAt(i2));
            if (iHexToInt == -1 || iHexToInt2 == -1) {
                throw new IllegalArgumentException(("Invalid hex chars: " + s.charAt(i) + s.charAt(i2)).toString());
            }
            bArr[i / 2] = (byte) ((iHexToInt << 4) + iHexToInt2);
        }
        return bArr;
    }

    @NotNull
    public final String printHexBinary(@NotNull byte[] data, boolean z) {
        Intrinsics.checkNotNullParameter(data, "data");
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            sb.append("0123456789ABCDEF".charAt((b >> 4) & 15));
            sb.append("0123456789ABCDEF".charAt(b & Ascii.SI));
        }
        if (!z) {
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
        String string2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        String lowerCase = string2.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    @NotNull
    public final String toHexString(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i >> (24 - (i2 * 8)));
        }
        String strTrimStart = StringsKt__StringsKt.trimStart(printHexBinary(bArr, true), '0');
        if (strTrimStart.length() <= 0) {
            strTrimStart = null;
        }
        return strTrimStart == null ? "0" : strTrimStart;
    }
}
