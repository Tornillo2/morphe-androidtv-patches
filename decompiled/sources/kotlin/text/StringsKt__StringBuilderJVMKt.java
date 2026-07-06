package kotlin.text;

import java.io.IOException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nStringBuilderJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringBuilderJVM.kt\nkotlin/text/StringsKt__StringBuilderJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,417:1\n1#2:418\n*E\n"})
public class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    @SinceKotlin(version = "1.9")
    @InlineOnly
    public static final StringBuilder append(StringBuilder sb, byte b) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append((int) b);
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, StringBuffer stringBuffer) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(stringBuffer);
        sb.append('\n');
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendRange(StringBuilder sb, char[] value, int i, int i2) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        sb.append(value, i, i2 - i);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @NotNull
    public static final Appendable appendln(@NotNull Appendable appendable) throws IOException {
        Intrinsics.checkNotNullParameter(appendable, "<this>");
        Appendable appendableAppend = appendable.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkNotNullExpressionValue(appendableAppend, "append(...)");
        return appendableAppend;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final StringBuilder clear(@NotNull StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.setLength(0);
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder deleteAt(StringBuilder sb, int i) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder sbDeleteCharAt = sb.deleteCharAt(i);
        Intrinsics.checkNotNullExpressionValue(sbDeleteCharAt, "deleteCharAt(...)");
        return sbDeleteCharAt;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder deleteRange(StringBuilder sb, int i, int i2) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder sbDelete = sb.delete(i, i2);
        Intrinsics.checkNotNullExpressionValue(sbDelete, "delete(...)");
        return sbDelete;
    }

    @SinceKotlin(version = "1.9")
    @InlineOnly
    public static final StringBuilder insert(StringBuilder sb, int i, byte b) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder sbInsert = sb.insert(i, (int) b);
        Intrinsics.checkNotNullExpressionValue(sbInsert, "insert(...)");
        return sbInsert;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder insertRange(StringBuilder sb, int i, char[] value, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        StringBuilder sbInsert = sb.insert(i, value, i2, i3 - i2);
        Intrinsics.checkNotNullExpressionValue(sbInsert, "insert(...)");
        return sbInsert;
    }

    @InlineOnly
    public static final void set(StringBuilder sb, int i, char c) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.setCharAt(i, c);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder setRange(StringBuilder sb, int i, int i2, String value) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        StringBuilder sbReplace = sb.replace(i, i2, value);
        Intrinsics.checkNotNullExpressionValue(sbReplace, "replace(...)");
        return sbReplace;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final void toCharArray(StringBuilder sb, char[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        sb.getChars(i2, i3, destination, i);
    }

    public static /* synthetic */ void toCharArray$default(StringBuilder sb, char[] destination, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sb.length();
        }
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        sb.getChars(i2, i3, destination, i);
    }

    @SinceKotlin(version = "1.9")
    @InlineOnly
    public static final StringBuilder append(StringBuilder sb, short s) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append((int) s);
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, StringBuilder sb2) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append((CharSequence) sb2);
        sb.append('\n');
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendRange(StringBuilder sb, CharSequence value, int i, int i2) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        sb.append(value, i, i2);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final Appendable appendln(Appendable appendable, CharSequence charSequence) throws IOException {
        Intrinsics.checkNotNullParameter(appendable, "<this>");
        Appendable appendableAppend = appendable.append(charSequence);
        Intrinsics.checkNotNullExpressionValue(appendableAppend, "append(...)");
        return appendln(appendableAppend);
    }

    @SinceKotlin(version = "1.9")
    @InlineOnly
    public static final StringBuilder insert(StringBuilder sb, int i, short s) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        StringBuilder sbInsert = sb.insert(i, (int) s);
        Intrinsics.checkNotNullExpressionValue(sbInsert, "insert(...)");
        return sbInsert;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder insertRange(StringBuilder sb, int i, CharSequence value, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        StringBuilder sbInsert = sb.insert(i, value, i2, i3);
        Intrinsics.checkNotNullExpressionValue(sbInsert, "insert(...)");
        return sbInsert;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, int i) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(i);
        sb.append('\n');
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final Appendable appendln(Appendable appendable, char c) throws IOException {
        Intrinsics.checkNotNullParameter(appendable, "<this>");
        Appendable appendableAppend = appendable.append(c);
        Intrinsics.checkNotNullExpressionValue(appendableAppend, "append(...)");
        return appendln(appendableAppend);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, short s) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append((int) s);
        sb.append('\n');
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @NotNull
    public static final StringBuilder appendln(@NotNull StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(SystemProperties.LINE_SEPARATOR);
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, byte b) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append((int) b);
        sb.append('\n');
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, StringBuffer stringBuffer) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(stringBuffer);
        appendln(sb);
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, long j) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(j);
        sb.append('\n');
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(charSequence);
        appendln(sb);
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, float f) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(f);
        sb.append('\n');
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, String str) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(str);
        appendln(sb);
        return sb;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final StringBuilder appendLine(StringBuilder sb, double d) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(d);
        sb.append('\n');
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, Object obj) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(obj);
        appendln(sb);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, StringBuilder sb2) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append((CharSequence) sb2);
        appendln(sb);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, char[] value) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        sb.append(value);
        appendln(sb);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, char c) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(c);
        appendln(sb);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, boolean z) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(z);
        appendln(sb);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, int i) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(i);
        appendln(sb);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, short s) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append((int) s);
        appendln(sb);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, byte b) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append((int) b);
        appendln(sb);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, long j) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(j);
        appendln(sb);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, float f) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(f);
        appendln(sb);
        return sb;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    public static final StringBuilder appendln(StringBuilder sb, double d) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        sb.append(d);
        appendln(sb);
        return sb;
    }
}
