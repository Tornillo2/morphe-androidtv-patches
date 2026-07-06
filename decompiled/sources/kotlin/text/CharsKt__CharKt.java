package kotlin.text;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nChar.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Char.kt\nkotlin/text/CharsKt__CharKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,349:1\n1#2:350\n*E\n"})
public class CharsKt__CharKt extends CharsKt__CharJVMKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final char digitToChar(int i) {
        if (i < 0 || i >= 10) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Int ", i, " is not a decimal digit"));
        }
        return (char) (i + 48);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final int digitToInt(char c, int i) {
        Integer numDigitToIntOrNull = digitToIntOrNull(c, i);
        if (numDigitToIntOrNull != null) {
            return numDigitToIntOrNull.intValue();
        }
        throw new IllegalArgumentException("Char " + c + " is not a digit in the given radix=" + i);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Integer digitToIntOrNull(char c, int i) {
        CharsKt__CharJVMKt.checkRadix(i);
        Integer numValueOf = Integer.valueOf(Character.digit((int) c, i));
        if (numValueOf.intValue() >= 0) {
            return numValueOf;
        }
        return null;
    }

    public static final boolean equals(char c, char c2, boolean z) {
        if (c == c2) {
            return true;
        }
        if (!z) {
            return false;
        }
        char upperCase = Character.toUpperCase(c);
        char upperCase2 = Character.toUpperCase(c2);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static /* synthetic */ boolean equals$default(char c, char c2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return equals(c, c2, z);
    }

    public static final boolean isSurrogate(char c) {
        return 55296 <= c && c < 57344;
    }

    @InlineOnly
    public static final String plus(char c, String other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return c + other;
    }

    @SinceKotlin(version = "1.5")
    @NotNull
    public static String titlecase(char c) {
        return _OneToManyTitlecaseMappingsKt.titlecaseImpl(c);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final int digitToInt(char c) {
        int iDigit = Character.digit((int) c, 10);
        if (iDigit >= 0) {
            return iDigit;
        }
        throw new IllegalArgumentException("Char " + c + " is not a decimal digit");
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Integer digitToIntOrNull(char c) {
        Integer numValueOf = Integer.valueOf(Character.digit((int) c, 10));
        if (numValueOf.intValue() >= 0) {
            return numValueOf;
        }
        return null;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final char digitToChar(int i, int i2) {
        if (2 > i2 || i2 >= 37) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Invalid radix: ", i2, ". Valid radix values are in range 2..36"));
        }
        if (i < 0 || i >= i2) {
            throw new IllegalArgumentException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Digit ", i, " does not represent a valid digit in radix ", i2));
        }
        return (char) (i < 10 ? i + 48 : ((char) (i + 65)) - '\n');
    }
}
