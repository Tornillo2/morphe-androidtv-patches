package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt___URangesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.5")
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
public final class UByte implements Comparable<UByte> {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    public final byte data;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    public /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    @InlineOnly
    /* JADX INFO: renamed from: and-7apg3OU, reason: not valid java name */
    public static final byte m591and7apg3OU(byte b, byte b2) {
        return (byte) (b & b2);
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UByte m592boximpl(byte b) {
        return new UByte(b);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    public static final int m594compareToVKZWuLQ(byte b, long j) {
        return Long.compare((((long) b) & 255) ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    public static final int m595compareToWZ4Q5Ns(byte b, int i) {
        return Integer.compare((b & 255) ^ Integer.MIN_VALUE, i ^ Integer.MIN_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-xj2QHRw, reason: not valid java name */
    public static final int m596compareToxj2QHRw(byte b, short s) {
        return Intrinsics.compare(b & 255, s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: dec-w2LRezQ, reason: not valid java name */
    public static final byte m598decw2LRezQ(byte b) {
        return (byte) (b - 1);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-7apg3OU, reason: not valid java name */
    public static final int m599div7apg3OU(byte b, byte b2) {
        return UByte$$ExternalSyntheticBackport0.m(b & 255, b2 & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-VKZWuLQ, reason: not valid java name */
    public static final long m600divVKZWuLQ(byte b, long j) {
        return UByte$$ExternalSyntheticBackport3.m(((long) b) & 255, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-WZ4Q5Ns, reason: not valid java name */
    public static final int m601divWZ4Q5Ns(byte b, int i) {
        return UByte$$ExternalSyntheticBackport0.m(b & 255, i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-xj2QHRw, reason: not valid java name */
    public static final int m602divxj2QHRw(byte b, short s) {
        return UByte$$ExternalSyntheticBackport0.m(b & 255, s & UShort.MAX_VALUE);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m603equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).data;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m604equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-7apg3OU, reason: not valid java name */
    public static final int m605floorDiv7apg3OU(byte b, byte b2) {
        return UByte$$ExternalSyntheticBackport0.m(b & 255, b2 & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    public static final long m606floorDivVKZWuLQ(byte b, long j) {
        return UByte$$ExternalSyntheticBackport3.m(((long) b) & 255, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    public static final int m607floorDivWZ4Q5Ns(byte b, int i) {
        return UByte$$ExternalSyntheticBackport0.m(b & 255, i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    public static final int m608floorDivxj2QHRw(byte b, short s) {
        return UByte$$ExternalSyntheticBackport0.m(b & 255, s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: inc-w2LRezQ, reason: not valid java name */
    public static final byte m610incw2LRezQ(byte b) {
        return (byte) (b + 1);
    }

    @InlineOnly
    /* JADX INFO: renamed from: inv-w2LRezQ, reason: not valid java name */
    public static final byte m611invw2LRezQ(byte b) {
        return (byte) (~b);
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-7apg3OU, reason: not valid java name */
    public static final int m612minus7apg3OU(byte b, byte b2) {
        return (b & 255) - (b2 & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-VKZWuLQ, reason: not valid java name */
    public static final long m613minusVKZWuLQ(byte b, long j) {
        return (((long) b) & 255) - j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    public static final int m614minusWZ4Q5Ns(byte b, int i) {
        return (b & 255) - i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-xj2QHRw, reason: not valid java name */
    public static final int m615minusxj2QHRw(byte b, short s) {
        return (b & 255) - (s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-7apg3OU, reason: not valid java name */
    public static final byte m616mod7apg3OU(byte b, byte b2) {
        return (byte) UByte$$ExternalSyntheticBackport1.m(b & 255, b2 & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-VKZWuLQ, reason: not valid java name */
    public static final long m617modVKZWuLQ(byte b, long j) {
        return UByte$$ExternalSyntheticBackport2.m(((long) b) & 255, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    public static final int m618modWZ4Q5Ns(byte b, int i) {
        return UByte$$ExternalSyntheticBackport1.m(b & 255, i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-xj2QHRw, reason: not valid java name */
    public static final short m619modxj2QHRw(byte b, short s) {
        return (short) UByte$$ExternalSyntheticBackport1.m(b & 255, s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: or-7apg3OU, reason: not valid java name */
    public static final byte m620or7apg3OU(byte b, byte b2) {
        return (byte) (b | b2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-7apg3OU, reason: not valid java name */
    public static final int m621plus7apg3OU(byte b, byte b2) {
        return (b & 255) + (b2 & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-VKZWuLQ, reason: not valid java name */
    public static final long m622plusVKZWuLQ(byte b, long j) {
        return (((long) b) & 255) + j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    public static final int m623plusWZ4Q5Ns(byte b, int i) {
        return (b & 255) + i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-xj2QHRw, reason: not valid java name */
    public static final int m624plusxj2QHRw(byte b, short s) {
        return (b & 255) + (s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rangeTo-7apg3OU, reason: not valid java name */
    public static final UIntRange m625rangeTo7apg3OU(byte b, byte b2) {
        return new UIntRange(b & 255, b2 & 255, 1);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: rangeUntil-7apg3OU, reason: not valid java name */
    public static final UIntRange m626rangeUntil7apg3OU(byte b, byte b2) {
        return URangesKt___URangesKt.m1856untilJ1ME1BU(b & 255, b2 & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-7apg3OU, reason: not valid java name */
    public static final int m627rem7apg3OU(byte b, byte b2) {
        return UByte$$ExternalSyntheticBackport1.m(b & 255, b2 & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-VKZWuLQ, reason: not valid java name */
    public static final long m628remVKZWuLQ(byte b, long j) {
        return UByte$$ExternalSyntheticBackport2.m(((long) b) & 255, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    public static final int m629remWZ4Q5Ns(byte b, int i) {
        return UByte$$ExternalSyntheticBackport1.m(b & 255, i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-xj2QHRw, reason: not valid java name */
    public static final int m630remxj2QHRw(byte b, short s) {
        return UByte$$ExternalSyntheticBackport1.m(b & 255, s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-7apg3OU, reason: not valid java name */
    public static final int m631times7apg3OU(byte b, byte b2) {
        return (b & 255) * (b2 & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-VKZWuLQ, reason: not valid java name */
    public static final long m632timesVKZWuLQ(byte b, long j) {
        return (((long) b) & 255) * j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-WZ4Q5Ns, reason: not valid java name */
    public static final int m633timesWZ4Q5Ns(byte b, int i) {
        return (b & 255) * i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-xj2QHRw, reason: not valid java name */
    public static final int m634timesxj2QHRw(byte b, short s) {
        return (b & 255) * (s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    public static final double m636toDoubleimpl(byte b) {
        return UnsignedKt.uintToDouble(b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toFloat-impl, reason: not valid java name */
    public static final float m637toFloatimpl(byte b) {
        return (float) UnsignedKt.uintToDouble(b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    public static final int m638toIntimpl(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    public static final long m639toLongimpl(byte b) {
        return ((long) b) & 255;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toShort-impl, reason: not valid java name */
    public static final short m640toShortimpl(byte b) {
        return (short) (b & 255);
    }

    @NotNull
    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m641toStringimpl(byte b) {
        return String.valueOf(b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUInt-pVg5ArA, reason: not valid java name */
    public static final int m643toUIntpVg5ArA(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toULong-s-VKNKU, reason: not valid java name */
    public static final long m644toULongsVKNKU(byte b) {
        return ((long) b) & 255;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    public static final short m645toUShortMh2AYeg(byte b) {
        return (short) (b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: xor-7apg3OU, reason: not valid java name */
    public static final byte m646xor7apg3OU(byte b, byte b2) {
        return (byte) (b ^ b2);
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(UByte uByte) {
        return Intrinsics.compare(this.data & 255, uByte.data & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-7apg3OU, reason: not valid java name */
    public final int m647compareTo7apg3OU(byte b) {
        return Intrinsics.compare(this.data & 255, b & 255);
    }

    public boolean equals(Object obj) {
        return m603equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return this.data;
    }

    @NotNull
    public String toString() {
        return m641toStringimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ byte m648unboximpl() {
        return this.data;
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-7apg3OU, reason: not valid java name */
    public static int m593compareTo7apg3OU(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255);
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static byte m597constructorimpl(byte b) {
        return b;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m609hashCodeimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toByte-impl, reason: not valid java name */
    public static final byte m635toByteimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUByte-w2LRezQ, reason: not valid java name */
    public static final byte m642toUBytew2LRezQ(byte b) {
        return b;
    }
}
