package kotlin.text;

import java.util.List;
import java.util.regex.Matcher;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.MatchResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MatcherMatchResult implements MatchResult {

    @Nullable
    public List<String> groupValues_;

    @NotNull
    public final MatchGroupCollection groups;

    @NotNull
    public final CharSequence input;

    @NotNull
    public final Matcher matcher;

    public MatcherMatchResult(@NotNull Matcher matcher, @NotNull CharSequence input) {
        Intrinsics.checkNotNullParameter(matcher, "matcher");
        Intrinsics.checkNotNullParameter(input, "input");
        this.matcher = matcher;
        this.input = input;
        this.groups = new MatcherMatchResult$groups$1(this);
    }

    @Override // kotlin.text.MatchResult
    @NotNull
    public MatchResult.Destructured getDestructured() {
        return new MatchResult.Destructured(this);
    }

    @Override // kotlin.text.MatchResult
    @NotNull
    public List<String> getGroupValues() {
        if (this.groupValues_ == null) {
            this.groupValues_ = new AbstractList<String>() { // from class: kotlin.text.MatcherMatchResult$groupValues$1
                @Override // kotlin.collections.AbstractCollection, java.util.Collection
                public final /* bridge */ boolean contains(Object obj) {
                    if (obj instanceof String) {
                        return super.contains(obj);
                    }
                    return false;
                }

                @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
                public int getSize() {
                    return this.this$0.matcher.groupCount() + 1;
                }

                @Override // kotlin.collections.AbstractList, java.util.List
                public final /* bridge */ int indexOf(Object obj) {
                    if (obj instanceof String) {
                        return super.indexOf(obj);
                    }
                    return -1;
                }

                @Override // kotlin.collections.AbstractList, java.util.List
                public final /* bridge */ int lastIndexOf(Object obj) {
                    if (obj instanceof String) {
                        return super.lastIndexOf(obj);
                    }
                    return -1;
                }

                @Override // kotlin.collections.AbstractList, java.util.List
                public String get(int i) {
                    String strGroup = this.this$0.matcher.group(i);
                    return strGroup == null ? "" : strGroup;
                }

                public /* bridge */ boolean contains(String str) {
                    return super.contains((Object) str);
                }

                public /* bridge */ int indexOf(String str) {
                    return super.indexOf((Object) str);
                }

                public /* bridge */ int lastIndexOf(String str) {
                    return super.lastIndexOf((Object) str);
                }
            };
        }
        List<String> list = this.groupValues_;
        Intrinsics.checkNotNull(list);
        return list;
    }

    @Override // kotlin.text.MatchResult
    @NotNull
    public MatchGroupCollection getGroups() {
        return this.groups;
    }

    public final java.util.regex.MatchResult getMatchResult() {
        return this.matcher;
    }

    @Override // kotlin.text.MatchResult
    @NotNull
    public IntRange getRange() {
        return RegexKt.range(this.matcher);
    }

    @Override // kotlin.text.MatchResult
    @NotNull
    public String getValue() {
        String strGroup = this.matcher.group();
        Intrinsics.checkNotNullExpressionValue(strGroup, "group(...)");
        return strGroup;
    }

    @Override // kotlin.text.MatchResult
    @Nullable
    public MatchResult next() {
        int iEnd = this.matcher.end() + (this.matcher.end() == this.matcher.start() ? 1 : 0);
        if (iEnd > this.input.length()) {
            return null;
        }
        Matcher matcher = this.matcher.pattern().matcher(this.input);
        Intrinsics.checkNotNullExpressionValue(matcher, "matcher(...)");
        return RegexKt.findNext(matcher, iEnd, this.input);
    }
}
