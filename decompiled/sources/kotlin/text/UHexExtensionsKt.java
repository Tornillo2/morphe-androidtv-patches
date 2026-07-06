package kotlin.text;

import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UHexExtensionsKt {
    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @InlineOnly
    public static final byte hexToUByte(String str, HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.hexToByte(str, format);
    }

    public static byte hexToUByte$default(String str, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.hexToByte(str, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    public static final byte[] hexToUByteArray(String str, HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.hexToByteArray(str, format);
    }

    public static byte[] hexToUByteArray$default(String str, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.hexToByteArray(str, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @InlineOnly
    public static final int hexToUInt(String str, HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.hexToInt(str, format);
    }

    public static int hexToUInt$default(String str, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.hexToInt(str, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @InlineOnly
    public static final long hexToULong(String str, HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.hexToLong(str, format);
    }

    public static long hexToULong$default(String str, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.hexToLong(str, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @InlineOnly
    public static final short hexToUShort(String str, HexFormat format) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.hexToShort(str, format);
    }

    public static short hexToUShort$default(String str, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.hexToShort(str, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @InlineOnly
    /* JADX INFO: renamed from: toHexString-8M7LxHw, reason: not valid java name */
    public static final String m1899toHexString8M7LxHw(int i, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(i, format);
    }

    /* JADX INFO: renamed from: toHexString-8M7LxHw$default, reason: not valid java name */
    public static String m1900toHexString8M7LxHw$default(int i, HexFormat format, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(i, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @InlineOnly
    /* JADX INFO: renamed from: toHexString-8UJCm-I, reason: not valid java name */
    public static final String m1901toHexString8UJCmI(long j, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(j, format);
    }

    /* JADX INFO: renamed from: toHexString-8UJCm-I$default, reason: not valid java name */
    public static String m1902toHexString8UJCmI$default(long j, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(j, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @InlineOnly
    /* JADX INFO: renamed from: toHexString-ZQbaR00, reason: not valid java name */
    public static final String m1903toHexStringZQbaR00(byte b, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(b, format);
    }

    /* JADX INFO: renamed from: toHexString-ZQbaR00$default, reason: not valid java name */
    public static String m1904toHexStringZQbaR00$default(byte b, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(b, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: toHexString-lZCiFrA, reason: not valid java name */
    public static final String m1905toHexStringlZCiFrA(byte[] toHexString, int i, int i2, HexFormat format) {
        Intrinsics.checkNotNullParameter(toHexString, "$this$toHexString");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(toHexString, i, i2, format);
    }

    /* JADX INFO: renamed from: toHexString-lZCiFrA$default, reason: not valid java name */
    public static String m1906toHexStringlZCiFrA$default(byte[] toHexString, int i, int i2, HexFormat format, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = toHexString.length;
        }
        if ((i3 & 4) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(toHexString, "$this$toHexString");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(toHexString, i, i2, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @InlineOnly
    /* JADX INFO: renamed from: toHexString-r3ox_E0, reason: not valid java name */
    public static final String m1907toHexStringr3ox_E0(short s, HexFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(s, format);
    }

    /* JADX INFO: renamed from: toHexString-r3ox_E0$default, reason: not valid java name */
    public static String m1908toHexStringr3ox_E0$default(short s, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(s, format);
    }

    @SinceKotlin(version = "1.9")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: toHexString-zHuV2wU, reason: not valid java name */
    public static final String m1909toHexStringzHuV2wU(byte[] toHexString, HexFormat format) {
        Intrinsics.checkNotNullParameter(toHexString, "$this$toHexString");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(toHexString, format);
    }

    /* JADX INFO: renamed from: toHexString-zHuV2wU$default, reason: not valid java name */
    public static String m1910toHexStringzHuV2wU$default(byte[] toHexString, HexFormat format, int i, Object obj) {
        if ((i & 1) != 0) {
            HexFormat.Companion.getClass();
            format = HexFormat.Default;
        }
        Intrinsics.checkNotNullParameter(toHexString, "$this$toHexString");
        Intrinsics.checkNotNullParameter(format, "format");
        return HexExtensionsKt.toHexString(toHexString, format);
    }
}
