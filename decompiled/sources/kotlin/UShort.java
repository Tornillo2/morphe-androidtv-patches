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
public final class UShort implements Comparable<UShort> {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final short MAX_VALUE = -1;
    public static final short MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    public final short data;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    public /* synthetic */ UShort(short s) {
        this.data = s;
    }

    @InlineOnly
    /* JADX INFO: renamed from: and-xj2QHRw, reason: not valid java name */
    public static final short m854andxj2QHRw(short s, short s2) {
        return (short) (s & s2);
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UShort m855boximpl(short s) {
        return new UShort(s);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-7apg3OU, reason: not valid java name */
    public static final int m856compareTo7apg3OU(short s, byte b) {
        return Intrinsics.compare(s & MAX_VALUE, b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    public static final int m857compareToVKZWuLQ(short s, long j) {
        return Long.compare((((long) s) & 65535) ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    public static final int m858compareToWZ4Q5Ns(short s, int i) {
        return Integer.compare((s & MAX_VALUE) ^ Integer.MIN_VALUE, i ^ Integer.MIN_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: dec-Mh2AYeg, reason: not valid java name */
    public static final short m861decMh2AYeg(short s) {
        return (short) (s - 1);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-7apg3OU, reason: not valid java name */
    public static final int m862div7apg3OU(short s, byte b) {
        return UByte$$ExternalSyntheticBackport0.m(s & MAX_VALUE, b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-VKZWuLQ, reason: not valid java name */
    public static final long m863divVKZWuLQ(short s, long j) {
        return UByte$$ExternalSyntheticBackport3.m(((long) s) & 65535, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-WZ4Q5Ns, reason: not valid java name */
    public static final int m864divWZ4Q5Ns(short s, int i) {
        return UByte$$ExternalSyntheticBackport0.m(s & MAX_VALUE, i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-xj2QHRw, reason: not valid java name */
    public static final int m865divxj2QHRw(short s, short s2) {
        return UByte$$ExternalSyntheticBackport0.m(s & MAX_VALUE, s2 & MAX_VALUE);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m866equalsimpl(short s, Object obj) {
        return (obj instanceof UShort) && s == ((UShort) obj).data;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m867equalsimpl0(short s, short s2) {
        return s == s2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-7apg3OU, reason: not valid java name */
    public static final int m868floorDiv7apg3OU(short s, byte b) {
        return UByte$$ExternalSyntheticBackport0.m(s & MAX_VALUE, b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    public static final long m869floorDivVKZWuLQ(short s, long j) {
        return UByte$$ExternalSyntheticBackport3.m(((long) s) & 65535, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    public static final int m870floorDivWZ4Q5Ns(short s, int i) {
        return UByte$$ExternalSyntheticBackport0.m(s & MAX_VALUE, i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    public static final int m871floorDivxj2QHRw(short s, short s2) {
        return UByte$$ExternalSyntheticBackport0.m(s & MAX_VALUE, s2 & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: inc-Mh2AYeg, reason: not valid java name */
    public static final short m873incMh2AYeg(short s) {
        return (short) (s + 1);
    }

    @InlineOnly
    /* JADX INFO: renamed from: inv-Mh2AYeg, reason: not valid java name */
    public static final short m874invMh2AYeg(short s) {
        return (short) (~s);
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-7apg3OU, reason: not valid java name */
    public static final int m875minus7apg3OU(short s, byte b) {
        return (s & MAX_VALUE) - (b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-VKZWuLQ, reason: not valid java name */
    public static final long m876minusVKZWuLQ(short s, long j) {
        return (((long) s) & 65535) - j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    public static final int m877minusWZ4Q5Ns(short s, int i) {
        return (s & 65535) - i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-xj2QHRw, reason: not valid java name */
    public static final int m878minusxj2QHRw(short s, short s2) {
        return (s & MAX_VALUE) - (s2 & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-7apg3OU, reason: not valid java name */
    public static final byte m879mod7apg3OU(short s, byte b) {
        return (byte) UByte$$ExternalSyntheticBackport1.m(s & MAX_VALUE, b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-VKZWuLQ, reason: not valid java name */
    public static final long m880modVKZWuLQ(short s, long j) {
        return UByte$$ExternalSyntheticBackport2.m(((long) s) & 65535, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    public static final int m881modWZ4Q5Ns(short s, int i) {
        return UByte$$ExternalSyntheticBackport1.m(s & MAX_VALUE, i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-xj2QHRw, reason: not valid java name */
    public static final short m882modxj2QHRw(short s, short s2) {
        return (short) UByte$$ExternalSyntheticBackport1.m(s & MAX_VALUE, s2 & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: or-xj2QHRw, reason: not valid java name */
    public static final short m883orxj2QHRw(short s, short s2) {
        return (short) (s | s2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-7apg3OU, reason: not valid java name */
    public static final int m884plus7apg3OU(short s, byte b) {
        return (s & MAX_VALUE) + (b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-VKZWuLQ, reason: not valid java name */
    public static final long m885plusVKZWuLQ(short s, long j) {
        return (((long) s) & 65535) + j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    public static final int m886plusWZ4Q5Ns(short s, int i) {
        return (s & 65535) + i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-xj2QHRw, reason: not valid java name */
    public static final int m887plusxj2QHRw(short s, short s2) {
        return (s & MAX_VALUE) + (s2 & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rangeTo-xj2QHRw, reason: not valid java name */
    public static final UIntRange m888rangeToxj2QHRw(short s, short s2) {
        return new UIntRange(s & MAX_VALUE, s2 & MAX_VALUE, 1);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: rangeUntil-xj2QHRw, reason: not valid java name */
    public static final UIntRange m889rangeUntilxj2QHRw(short s, short s2) {
        return URangesKt___URangesKt.m1856untilJ1ME1BU(s & MAX_VALUE, s2 & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-7apg3OU, reason: not valid java name */
    public static final int m890rem7apg3OU(short s, byte b) {
        return UByte$$ExternalSyntheticBackport1.m(s & MAX_VALUE, b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-VKZWuLQ, reason: not valid java name */
    public static final long m891remVKZWuLQ(short s, long j) {
        return UByte$$ExternalSyntheticBackport2.m(((long) s) & 65535, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    public static final int m892remWZ4Q5Ns(short s, int i) {
        return UByte$$ExternalSyntheticBackport1.m(s & MAX_VALUE, i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-xj2QHRw, reason: not valid java name */
    public static final int m893remxj2QHRw(short s, short s2) {
        return UByte$$ExternalSyntheticBackport1.m(s & MAX_VALUE, s2 & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-7apg3OU, reason: not valid java name */
    public static final int m894times7apg3OU(short s, byte b) {
        return (s & MAX_VALUE) * (b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-VKZWuLQ, reason: not valid java name */
    public static final long m895timesVKZWuLQ(short s, long j) {
        return (((long) s) & 65535) * j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-WZ4Q5Ns, reason: not valid java name */
    public static final int m896timesWZ4Q5Ns(short s, int i) {
        return (s & 65535) * i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-xj2QHRw, reason: not valid java name */
    public static final int m897timesxj2QHRw(short s, short s2) {
        return (s & MAX_VALUE) * (s2 & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toByte-impl, reason: not valid java name */
    public static final byte m898toByteimpl(short s) {
        return (byte) s;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    public static final double m899toDoubleimpl(short s) {
        return UnsignedKt.uintToDouble(s & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toFloat-impl, reason: not valid java name */
    public static final float m900toFloatimpl(short s) {
        return (float) UnsignedKt.uintToDouble(s & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    public static final int m901toIntimpl(short s) {
        return s & MAX_VALUE;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    public static final long m902toLongimpl(short s) {
        return ((long) s) & 65535;
    }

    @NotNull
    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m904toStringimpl(short s) {
        return String.valueOf(s & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUByte-w2LRezQ, reason: not valid java name */
    public static final byte m905toUBytew2LRezQ(short s) {
        return (byte) s;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUInt-pVg5ArA, reason: not valid java name */
    public static final int m906toUIntpVg5ArA(short s) {
        return s & MAX_VALUE;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toULong-s-VKNKU, reason: not valid java name */
    public static final long m907toULongsVKNKU(short s) {
        return ((long) s) & 65535;
    }

    @InlineOnly
    /* JADX INFO: renamed from: xor-xj2QHRw, reason: not valid java name */
    public static final short m909xorxj2QHRw(short s, short s2) {
        return (short) (s ^ s2);
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(UShort uShort) {
        return Intrinsics.compare(this.data & MAX_VALUE, uShort.data & MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-xj2QHRw, reason: not valid java name */
    public final int m910compareToxj2QHRw(short s) {
        return Intrinsics.compare(this.data & MAX_VALUE, s & MAX_VALUE);
    }

    public boolean equals(Object obj) {
        return m866equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return this.data;
    }

    @NotNull
    public String toString() {
        return m904toStringimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ short m911unboximpl() {
        return this.data;
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-xj2QHRw, reason: not valid java name */
    public static int m859compareToxj2QHRw(short s, short s2) {
        return Intrinsics.compare(s & MAX_VALUE, s2 & MAX_VALUE);
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static short m860constructorimpl(short s) {
        return s;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m872hashCodeimpl(short s) {
        return s;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toShort-impl, reason: not valid java name */
    public static final short m903toShortimpl(short s) {
        return s;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    public static final short m908toUShortMh2AYeg(short s) {
        return s;
    }
}
