package kotlin.ranges;

import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.5")
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
public final class UIntRange extends UIntProgression implements ClosedRange<UInt>, OpenEndRange<UInt> {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final UIntRange EMPTY = new UIntRange(-1, 0, 1);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final UIntRange getEMPTY() {
            return UIntRange.EMPTY;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public UIntRange(int i, int i2) {
        super(i, i2, 1);
    }

    @Override // kotlin.ranges.ClosedRange
    public /* synthetic */ boolean contains(Comparable comparable) {
        return m1816containsWZ4Q5Ns(((UInt) comparable).data);
    }

    /* JADX INFO: renamed from: contains-WZ4Q5Ns, reason: not valid java name */
    public boolean m1816containsWZ4Q5Ns(int i) {
        return Integer.compare(this.first ^ Integer.MIN_VALUE, i ^ Integer.MIN_VALUE) <= 0 && Integer.compare(i ^ Integer.MIN_VALUE, this.last ^ Integer.MIN_VALUE) <= 0;
    }

    @Override // kotlin.ranges.UIntProgression
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof UIntRange)) {
            return false;
        }
        if (isEmpty() && ((UIntRange) obj).isEmpty()) {
            return true;
        }
        UIntRange uIntRange = (UIntRange) obj;
        return this.first == uIntRange.first && this.last == uIntRange.last;
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* synthetic */ Comparable getEndExclusive() {
        return new UInt(m1817getEndExclusivepVg5ArA());
    }

    /* JADX INFO: renamed from: getEndExclusive-pVg5ArA, reason: not valid java name */
    public int m1817getEndExclusivepVg5ArA() {
        int i = this.last;
        if (i != -1) {
            return i + 1;
        }
        throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.");
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getEndInclusive() {
        return new UInt(this.last);
    }

    /* JADX INFO: renamed from: getEndInclusive-pVg5ArA, reason: not valid java name */
    public int m1818getEndInclusivepVg5ArA() {
        return this.last;
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getStart() {
        return new UInt(this.first);
    }

    /* JADX INFO: renamed from: getStart-pVg5ArA, reason: not valid java name */
    public int m1819getStartpVg5ArA() {
        return this.first;
    }

    @Override // kotlin.ranges.UIntProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.first * 31) + this.last;
    }

    @Override // kotlin.ranges.UIntProgression, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return Integer.compare(this.first ^ Integer.MIN_VALUE, this.last ^ Integer.MIN_VALUE) > 0;
    }

    @Override // kotlin.ranges.UIntProgression
    @NotNull
    public String toString() {
        return ((Object) UInt.m720toStringimpl(this.first)) + ".." + ((Object) UInt.m720toStringimpl(this.last));
    }

    public UIntRange(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        super(i, i2, 1);
    }

    @Deprecated(message = "Can throw an exception when it's impossible to represent the value with UInt type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")
    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* JADX INFO: renamed from: getEndExclusive-pVg5ArA$annotations, reason: not valid java name */
    public static /* synthetic */ void m1815getEndExclusivepVg5ArA$annotations() {
    }
}
