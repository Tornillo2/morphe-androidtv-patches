package kotlin.text;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nRegex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Regex.kt\nkotlin/text/RegexKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,401:1\n1803#2,3:402\n*S KotlinDebug\n*F\n+ 1 Regex.kt\nkotlin/text/RegexKt\n*L\n19#1:402,3\n*E\n"})
public final class RegexKt {
    public static final MatchResult findNext(Matcher matcher, int i, CharSequence charSequence) {
        if (matcher.find(i)) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    public static final <T extends Enum<T> & FlagEnum> Set<T> fromInt(int i) {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final MatchResult matchEntire(Matcher matcher, CharSequence charSequence) {
        if (matcher.matches()) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    public static final IntRange range(java.util.regex.MatchResult matchResult) {
        return RangesKt___RangesKt.until(matchResult.start(), matchResult.end());
    }

    public static final int toInt(Iterable<? extends FlagEnum> iterable) {
        Iterator<? extends FlagEnum> it = iterable.iterator();
        int value = 0;
        while (it.hasNext()) {
            value |= it.next().getValue();
        }
        return value;
    }

    public static final IntRange range(java.util.regex.MatchResult matchResult, int i) {
        return RangesKt___RangesKt.until(matchResult.start(i), matchResult.end(i));
    }
}
