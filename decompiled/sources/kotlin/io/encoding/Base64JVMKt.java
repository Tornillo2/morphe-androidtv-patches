package kotlin.io.encoding;

import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Base64JVMKt {
    @ExperimentalEncodingApi
    @SinceKotlin(version = "1.8")
    @InlineOnly
    public static final byte[] platformCharsToBytes(Base64 base64, CharSequence source, int i, int i2) {
        Intrinsics.checkNotNullParameter(base64, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(source instanceof String)) {
            return base64.charsToBytesImpl$kotlin_stdlib(source, i, i2);
        }
        String str = (String) source;
        base64.checkSourceBounds$kotlin_stdlib(str.length(), i, i2);
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        byte[] bytes = strSubstring.getBytes(Charsets.ISO_8859_1);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return bytes;
    }

    @ExperimentalEncodingApi
    @SinceKotlin(version = "1.8")
    @InlineOnly
    public static final int platformEncodeIntoByteArray(Base64 base64, byte[] source, byte[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(base64, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return base64.encodeIntoByteArrayImpl$kotlin_stdlib(source, destination, i, i2, i3);
    }

    @ExperimentalEncodingApi
    @SinceKotlin(version = "1.8")
    @InlineOnly
    public static final byte[] platformEncodeToByteArray(Base64 base64, byte[] source, int i, int i2) {
        Intrinsics.checkNotNullParameter(base64, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        return base64.encodeToByteArrayImpl$kotlin_stdlib(source, i, i2);
    }

    @ExperimentalEncodingApi
    @SinceKotlin(version = "1.8")
    @InlineOnly
    public static final String platformEncodeToString(Base64 base64, byte[] source, int i, int i2) {
        Intrinsics.checkNotNullParameter(base64, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        return new String(base64.encodeToByteArrayImpl$kotlin_stdlib(source, i, i2), Charsets.ISO_8859_1);
    }
}
