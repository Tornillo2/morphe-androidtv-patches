package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nStringNumberConversionsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringNumberConversionsJVM.kt\nkotlin/text/StringsKt__StringNumberConversionsJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,512:1\n267#1,7:513\n267#1,7:520\n267#1,7:527\n267#1,7:534\n1#2:541\n*S KotlinDebug\n*F\n+ 1 StringNumberConversionsJVM.kt\nkotlin/text/StringsKt__StringNumberConversionsJVMKt\n*L\n166#1:513,7\n173#1:520,7\n253#1:527,7\n264#1:534,7\n*E\n"})
public class StringsKt__StringNumberConversionsJVMKt extends StringsKt__StringBuilderKt {
    @InlineOnly
    public static final int advanceAndValidateMantissa$StringsKt__StringNumberConversionsJVMKt(String str, int i, int i2, boolean z, Function1<? super Character, Boolean> function1) {
        boolean z2;
        int i3 = i;
        while (i3 <= i2 && function1.invoke(Character.valueOf(str.charAt(i3))).booleanValue()) {
            i3++;
        }
        boolean z3 = i != i3;
        if (i3 > i2) {
            if (z) {
                return -1;
            }
            return i3;
        }
        if (str.charAt(i3) == '.') {
            int i4 = i3 + 1;
            int i5 = i4;
            while (i5 <= i2 && function1.invoke(Character.valueOf(str.charAt(i5))).booleanValue()) {
                i5++;
            }
            z2 = i4 != i5;
            i3 = i5;
        } else {
            z2 = false;
        }
        if (z3 || z2) {
            return i3;
        }
        if (z) {
            return -1;
        }
        String str2 = i2 == i3 + 2 ? "NaN" : i2 == i3 + 7 ? "Infinity" : null;
        if (str2 != null && StringsKt__StringsKt.indexOf((CharSequence) str, str2, i3, false) == i3) {
            return i2 + 1;
        }
        return -1;
    }

    @InlineOnly
    public static final int advanceWhile$StringsKt__StringNumberConversionsJVMKt(String str, int i, int i2, Function1<? super Character, Boolean> function1) {
        while (i <= i2 && function1.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
            i++;
        }
        return i;
    }

    @InlineOnly
    public static final int asciiLetterToLowerCaseCode$StringsKt__StringNumberConversionsJVMKt(char c) {
        return c | ' ';
    }

    @InlineOnly
    public static final int backtrackWhile$StringsKt__StringNumberConversionsJVMKt(String str, int i, int i2, Function1<? super Character, Boolean> function1) {
        while (i2 > i && function1.invoke(Character.valueOf(str.charAt(i2))).booleanValue()) {
            i2--;
        }
        return i2;
    }

    @InlineOnly
    public static final String guessNamedFloatConstant$StringsKt__StringNumberConversionsJVMKt(int i, int i2) {
        if (i2 == i + 2) {
            return "NaN";
        }
        if (i2 == i + 7) {
            return "Infinity";
        }
        return null;
    }

    @InlineOnly
    public static final boolean isAsciiDigit$StringsKt__StringNumberConversionsJVMKt(char c) {
        return ((c + 65488) & 65535) < 10;
    }

    @InlineOnly
    public static final boolean isHexLetter$StringsKt__StringNumberConversionsJVMKt(char c) {
        return (((c | ' ') + (-97)) & 65535) < 6;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean isValidFloat$StringsKt__StringNumberConversionsJVMKt(java.lang.String r19) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringNumberConversionsJVMKt.isValidFloat$StringsKt__StringNumberConversionsJVMKt(java.lang.String):boolean");
    }

    public static final <T> T screenFloatValue$StringsKt__StringNumberConversionsJVMKt(String str, Function1<? super String, ? extends T> function1) {
        try {
            if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(str)) {
                return function1.invoke(str);
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static final BigDecimal toBigDecimal(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new BigDecimal(str);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(str)) {
                return new BigDecimal(str);
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static final BigInteger toBigInteger(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new BigInteger(str);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toBigIntegerOrNull(str, 10);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean toBoolean(String str) {
        return Boolean.parseBoolean(str);
    }

    @InlineOnly
    public static final byte toByte(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Byte.parseByte(str);
    }

    @InlineOnly
    public static final double toDouble(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Double.parseDouble(str);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static Double toDoubleOrNull(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(str)) {
                return Double.valueOf(Double.parseDouble(str));
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }

    @InlineOnly
    public static final float toFloat(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Float.parseFloat(str);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static Float toFloatOrNull(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(str)) {
                return Float.valueOf(Float.parseFloat(str));
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }

    @InlineOnly
    public static final int toInt(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Integer.parseInt(str);
    }

    @InlineOnly
    public static final long toLong(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Long.parseLong(str);
    }

    @InlineOnly
    public static final short toShort(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Short.parseShort(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final String toString(byte b, int i) {
        CharsKt__CharJVMKt.checkRadix(i);
        String string = Integer.toString(b, i);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static final BigDecimal toBigDecimal(String str, MathContext mathContext) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(mathContext, "mathContext");
        return new BigDecimal(str, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static final BigInteger toBigInteger(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharsKt__CharJVMKt.checkRadix(i);
        return new BigInteger(str, i);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharsKt__CharJVMKt.checkRadix(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        if (length != 1) {
            for (int i2 = str.charAt(0) == '-' ? 1 : 0; i2 < length; i2++) {
                if (Character.digit((int) str.charAt(i2), i) < 0) {
                    return null;
                }
            }
        } else if (Character.digit((int) str.charAt(0), i) < 0) {
            return null;
        }
        CharsKt__CharJVMKt.checkRadix(i);
        return new BigInteger(str, i);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final byte toByte(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharsKt__CharJVMKt.checkRadix(i);
        return Byte.parseByte(str, i);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final int toInt(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharsKt__CharJVMKt.checkRadix(i);
        return Integer.parseInt(str, i);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final long toLong(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharsKt__CharJVMKt.checkRadix(i);
        return Long.parseLong(str, i);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final short toShort(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharsKt__CharJVMKt.checkRadix(i);
        return Short.parseShort(str, i);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final String toString(short s, int i) {
        CharsKt__CharJVMKt.checkRadix(i);
        String string = Integer.toString(s, i);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final String toString(int i, int i2) {
        CharsKt__CharJVMKt.checkRadix(i2);
        String string = Integer.toString(i, i2);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String str, @NotNull MathContext mathContext) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(mathContext, "mathContext");
        try {
            if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(str)) {
                return new BigDecimal(str, mathContext);
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final String toString(long j, int i) {
        CharsKt__CharJVMKt.checkRadix(i);
        String string = Long.toString(j, i);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
