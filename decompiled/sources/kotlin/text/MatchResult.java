package kotlin.text;

import java.util.List;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface MatchResult {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @NotNull
        public static Destructured getDestructured(@NotNull MatchResult matchResult) {
            return new Destructured(matchResult);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Destructured {

        @NotNull
        public final MatchResult match;

        public Destructured(@NotNull MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            this.match = match;
        }

        @InlineOnly
        public final String component1() {
            return this.match.getGroupValues().get(1);
        }

        @InlineOnly
        public final String component10() {
            return this.match.getGroupValues().get(10);
        }

        @InlineOnly
        public final String component2() {
            return this.match.getGroupValues().get(2);
        }

        @InlineOnly
        public final String component3() {
            return this.match.getGroupValues().get(3);
        }

        @InlineOnly
        public final String component4() {
            return this.match.getGroupValues().get(4);
        }

        @InlineOnly
        public final String component5() {
            return this.match.getGroupValues().get(5);
        }

        @InlineOnly
        public final String component6() {
            return this.match.getGroupValues().get(6);
        }

        @InlineOnly
        public final String component7() {
            return this.match.getGroupValues().get(7);
        }

        @InlineOnly
        public final String component8() {
            return this.match.getGroupValues().get(8);
        }

        @InlineOnly
        public final String component9() {
            return this.match.getGroupValues().get(9);
        }

        @NotNull
        public final MatchResult getMatch() {
            return this.match;
        }

        @NotNull
        public final List<String> toList() {
            return this.match.getGroupValues().subList(1, this.match.getGroupValues().size());
        }
    }

    @NotNull
    Destructured getDestructured();

    @NotNull
    List<String> getGroupValues();

    @NotNull
    MatchGroupCollection getGroups();

    @NotNull
    IntRange getRange();

    @NotNull
    String getValue();

    @Nullable
    MatchResult next();
}
