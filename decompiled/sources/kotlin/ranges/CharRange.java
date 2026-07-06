package kotlin.ranges;

import com.google.common.xml.XmlEscapers;
import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CharRange extends CharProgression implements ClosedRange<Character>, OpenEndRange<Character> {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final CharRange EMPTY = new CharRange(1, 0, 1);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final CharRange getEMPTY() {
            return CharRange.EMPTY;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public CharRange(char c, char c2) {
        super(c, c2, 1);
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return contains(((Character) comparable).charValue());
    }

    @Override // kotlin.ranges.CharProgression
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof CharRange)) {
            return false;
        }
        if (isEmpty() && ((CharRange) obj).isEmpty()) {
            return true;
        }
        CharRange charRange = (CharRange) obj;
        return this.first == charRange.first && this.last == charRange.last;
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public Character getEndInclusive() {
        return Character.valueOf(this.last);
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public Character getStart() {
        return Character.valueOf(this.first);
    }

    @Override // kotlin.ranges.CharProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.first * XmlEscapers.MAX_ASCII_CONTROL_CHAR) + this.last;
    }

    @Override // kotlin.ranges.CharProgression, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return Intrinsics.compare((int) this.first, (int) this.last) > 0;
    }

    @Override // kotlin.ranges.CharProgression
    @NotNull
    public String toString() {
        return this.first + ".." + this.last;
    }

    public boolean contains(char c) {
        return Intrinsics.compare((int) this.first, (int) c) <= 0 && Intrinsics.compare((int) c, (int) this.last) <= 0;
    }

    @Override // kotlin.ranges.OpenEndRange
    @NotNull
    public Character getEndExclusive() {
        char c = this.last;
        if (c != 65535) {
            return Character.valueOf((char) (c + 1));
        }
        throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.");
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getEndInclusive() {
        return Character.valueOf(this.last);
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getStart() {
        return Character.valueOf(this.first);
    }

    @Deprecated(message = "Can throw an exception when it's impossible to represent the value with Char type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")
    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static /* synthetic */ void getEndExclusive$annotations() {
    }
}
