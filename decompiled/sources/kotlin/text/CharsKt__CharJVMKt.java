package kotlin.text;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class CharsKt__CharJVMKt {
    @PublishedApi
    public static int checkRadix(int i) {
        if (2 <= i && i < 37) {
            return i;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("radix ", i, " was not in valid range ");
        sbM.append(new IntRange(2, 36, 1));
        throw new IllegalArgumentException(sbM.toString());
    }

    public static final int digitOf(char c, int i) {
        return Character.digit((int) c, i);
    }

    @NotNull
    public static final CharCategory getCategory(char c) {
        return CharCategory.Companion.valueOf(Character.getType(c));
    }

    @NotNull
    public static final CharDirectionality getDirectionality(char c) {
        return CharDirectionality.Companion.valueOf(Character.getDirectionality(c));
    }

    @InlineOnly
    public static final boolean isDefined(char c) {
        return Character.isDefined(c);
    }

    @InlineOnly
    public static final boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    @InlineOnly
    public static final boolean isHighSurrogate(char c) {
        return Character.isHighSurrogate(c);
    }

    @InlineOnly
    public static final boolean isISOControl(char c) {
        return Character.isISOControl(c);
    }

    @InlineOnly
    public static final boolean isIdentifierIgnorable(char c) {
        return Character.isIdentifierIgnorable(c);
    }

    @InlineOnly
    public static final boolean isJavaIdentifierPart(char c) {
        return Character.isJavaIdentifierPart(c);
    }

    @InlineOnly
    public static final boolean isJavaIdentifierStart(char c) {
        return Character.isJavaIdentifierStart(c);
    }

    @InlineOnly
    public static final boolean isLetter(char c) {
        return Character.isLetter(c);
    }

    @InlineOnly
    public static final boolean isLetterOrDigit(char c) {
        return Character.isLetterOrDigit(c);
    }

    @InlineOnly
    public static final boolean isLowSurrogate(char c) {
        return Character.isLowSurrogate(c);
    }

    @InlineOnly
    public static final boolean isLowerCase(char c) {
        return Character.isLowerCase(c);
    }

    @InlineOnly
    public static final boolean isTitleCase(char c) {
        return Character.isTitleCase(c);
    }

    @InlineOnly
    public static final boolean isUpperCase(char c) {
        return Character.isUpperCase(c);
    }

    public static final boolean isWhitespace(char c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static final String lowercase(char c) {
        String strValueOf = String.valueOf(c);
        Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
        String lowerCase = strValueOf.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static final char lowercaseChar(char c) {
        return Character.toLowerCase(c);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final String titlecase(char c, @NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        String strUppercase = uppercase(c, locale);
        if (strUppercase.length() <= 1) {
            String strValueOf = String.valueOf(c);
            Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
            String upperCase = strValueOf.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            if (strUppercase.equals(upperCase)) {
                return String.valueOf(Character.toTitleCase(c));
            }
        } else if (c != 329) {
            char cCharAt = strUppercase.charAt(0);
            String strSubstring = strUppercase.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            String lowerCase = strSubstring.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return cCharAt + lowerCase;
        }
        return strUppercase;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static final char titlecaseChar(char c) {
        return Character.toTitleCase(c);
    }

    @Deprecated(message = "Use lowercaseChar() instead.", replaceWith = @ReplaceWith(expression = "lowercaseChar()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.5")
    @InlineOnly
    public static final char toLowerCase(char c) {
        return Character.toLowerCase(c);
    }

    @Deprecated(message = "Use titlecaseChar() instead.", replaceWith = @ReplaceWith(expression = "titlecaseChar()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.5")
    @InlineOnly
    public static final char toTitleCase(char c) {
        return Character.toTitleCase(c);
    }

    @Deprecated(message = "Use uppercaseChar() instead.", replaceWith = @ReplaceWith(expression = "uppercaseChar()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.5")
    @InlineOnly
    public static final char toUpperCase(char c) {
        return Character.toUpperCase(c);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static final String uppercase(char c) {
        String strValueOf = String.valueOf(c);
        Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
        String upperCase = strValueOf.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static final char uppercaseChar(char c) {
        return Character.toUpperCase(c);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final String lowercase(char c, @NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        String strValueOf = String.valueOf(c);
        Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
        String lowerCase = strValueOf.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final String uppercase(char c, @NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        String strValueOf = String.valueOf(c);
        Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
        String upperCase = strValueOf.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }
}
