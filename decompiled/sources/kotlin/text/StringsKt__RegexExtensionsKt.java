package kotlin.text;

import java.util.Set;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class StringsKt__RegexExtensionsKt extends StringsKt__RegexExtensionsJVMKt {
    @InlineOnly
    public static final Regex toRegex(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new Regex(str);
    }

    @InlineOnly
    public static final Regex toRegex(String str, RegexOption option) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(option, "option");
        return new Regex(str, option);
    }

    @InlineOnly
    public static final Regex toRegex(String str, Set<? extends RegexOption> options) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return new Regex(str, options);
    }
}
