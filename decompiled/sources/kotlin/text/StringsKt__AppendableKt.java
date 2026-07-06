package kotlin.text;

import java.io.IOException;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class StringsKt__AppendableKt {
    @NotNull
    public static final <T extends Appendable> T append(@NotNull T t, @NotNull CharSequence... value) throws IOException {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        for (CharSequence charSequence : value) {
            t.append(charSequence);
        }
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void appendElement(@NotNull Appendable appendable, T t, @Nullable Function1<? super T, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(appendable, "<this>");
        if (function1 != null) {
            appendable.append(function1.invoke(t));
            return;
        }
        if (t == 0 ? true : t instanceof CharSequence) {
            appendable.append((CharSequence) t);
        } else if (t instanceof Character) {
            appendable.append(((Character) t).charValue());
        } else {
            appendable.append(t.toString());
        }
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Appendable appendLine(Appendable appendable) {
        Intrinsics.checkNotNullParameter(appendable, "<this>");
        return appendable.append('\n');
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T extends Appendable> T appendRange(@NotNull T t, @NotNull CharSequence value, int i, int i2) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        T t2 = (T) t.append(value, i, i2);
        Intrinsics.checkNotNull(t2, "null cannot be cast to non-null type T of kotlin.text.StringsKt__AppendableKt.appendRange");
        return t2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Appendable appendLine(Appendable appendable, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(appendable, "<this>");
        return appendable.append(charSequence).append('\n');
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Appendable appendLine(Appendable appendable, char c) {
        Intrinsics.checkNotNullParameter(appendable, "<this>");
        return appendable.append(c).append('\n');
    }
}
