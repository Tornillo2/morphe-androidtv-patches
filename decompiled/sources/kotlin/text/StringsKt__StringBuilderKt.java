package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class StringsKt__StringBuilderKt extends StringsKt__StringBuilderJVMKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use append(value: Any?) instead", replaceWith = @ReplaceWith(expression = "append(value = obj)", imports = {}))
    @InlineOnly
    public static final StringBuilder append(StringBuilder sb, Object obj) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(obj);
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append('\n');
        return sb;
    }

    @InlineOnly
    public static final String buildString(Function1<? super StringBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        StringBuilder sb = new StringBuilder();
        builderAction.invoke(sb);
        return sb.toString();
    }

    @NotNull
    public static final StringBuilder append(@NotNull StringBuilder sb, @NotNull String... value) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        for (String str : value) {
            sb.append(str);
        }
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(charSequence);
        sb.append('\n');
        return sb;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final String buildString(int i, Function1<? super StringBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        StringBuilder sb = new StringBuilder(i);
        builderAction.invoke(sb);
        return sb.toString();
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, String str) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(str);
        sb.append('\n');
        return sb;
    }

    @NotNull
    public static final StringBuilder append(@NotNull StringBuilder sb, @NotNull Object... value) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        for (Object obj : value) {
            sb.append(obj);
        }
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, Object obj) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(obj);
        sb.append('\n');
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, char[] value) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        sb.append(value);
        sb.append('\n');
        return sb;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use appendRange instead.", replaceWith = @ReplaceWith(expression = "this.appendRange(str, offset, offset + len)", imports = {}))
    @InlineOnly
    public static final StringBuilder append(StringBuilder sb, char[] str, int i, int i2) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(str, "str");
        throw new NotImplementedError(null, 1, null);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, char c) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(c);
        sb.append('\n');
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, boolean z) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(z);
        sb.append('\n');
        return sb;
    }
}
