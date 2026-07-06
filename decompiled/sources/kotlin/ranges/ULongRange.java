package kotlin.ranges;

import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.5")
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
public final class ULongRange extends ULongProgression implements ClosedRange<ULong>, OpenEndRange<ULong> {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final ULongRange EMPTY = new ULongRange(-1, 0);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final ULongRange getEMPTY() {
            return ULongRange.EMPTY;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public ULongRange(long j, long j2) {
        super(j, j2, 1L);
    }

    @Override // kotlin.ranges.ClosedRange
    public /* synthetic */ boolean contains(Comparable comparable) {
        return m1825containsVKZWuLQ(((ULong) comparable).data);
    }

    /* JADX INFO: renamed from: contains-VKZWuLQ, reason: not valid java name */
    public boolean m1825containsVKZWuLQ(long j) {
        return Long.compare(this.first ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE) <= 0 && Long.compare(j ^ Long.MIN_VALUE, this.last ^ Long.MIN_VALUE) <= 0;
    }

    @Override // kotlin.ranges.ULongProgression
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ULongRange)) {
            return false;
        }
        if (isEmpty() && ((ULongRange) obj).isEmpty()) {
            return true;
        }
        ULongRange uLongRange = (ULongRange) obj;
        return this.first == uLongRange.first && this.last == uLongRange.last;
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* synthetic */ Comparable getEndExclusive() {
        return new ULong(m1826getEndExclusivesVKNKU());
    }

    /* JADX INFO: renamed from: getEndExclusive-s-VKNKU, reason: not valid java name */
    public long m1826getEndExclusivesVKNKU() {
        long j = this.last;
        if (j != -1) {
            return j + (((long) 1) & 4294967295L);
        }
        throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.");
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getEndInclusive() {
        return new ULong(this.last);
    }

    /* JADX INFO: renamed from: getEndInclusive-s-VKNKU, reason: not valid java name */
    public long m1827getEndInclusivesVKNKU() {
        return this.last;
    }

    @Override // kotlin.ranges.ClosedRange
    public Comparable getStart() {
        return new ULong(this.first);
    }

    /* JADX INFO: renamed from: getStart-s-VKNKU, reason: not valid java name */
    public long m1828getStartsVKNKU() {
        return this.first;
    }

    @Override // kotlin.ranges.ULongProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j = this.first;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        long j2 = this.last;
        return i + ((int) (j2 ^ (j2 >>> 32)));
    }

    @Override // kotlin.ranges.ULongProgression, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return Long.compare(this.first ^ Long.MIN_VALUE, this.last ^ Long.MIN_VALUE) > 0;
    }

    @Override // kotlin.ranges.ULongProgression
    @NotNull
    public String toString() {
        return ((Object) UnsignedKt.ulongToString(this.first, 10)) + ".." + ((Object) UnsignedKt.ulongToString(this.last, 10));
    }

    public /* synthetic */ ULongRange(long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2);
    }

    @Deprecated(message = "Can throw an exception when it's impossible to represent the value with ULong type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")
    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* JADX INFO: renamed from: getEndExclusive-s-VKNKU$annotations, reason: not valid java name */
    public static /* synthetic */ void m1824getEndExclusivesVKNKU$annotations() {
    }
}
