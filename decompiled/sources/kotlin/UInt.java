package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt___URangesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.5")
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
public final class UInt implements Comparable<UInt> {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    public final int data;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    public /* synthetic */ UInt(int i) {
        this.data = i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: and-WZ4Q5Ns, reason: not valid java name */
    public static final int m668andWZ4Q5Ns(int i, int i2) {
        return i & i2;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UInt m669boximpl(int i) {
        return new UInt(i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-7apg3OU, reason: not valid java name */
    public static final int m670compareTo7apg3OU(int i, byte b) {
        return Integer.compare(i ^ Integer.MIN_VALUE, (b & 255) ^ Integer.MIN_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    public static final int m671compareToVKZWuLQ(int i, long j) {
        return Long.compare((((long) i) & 4294967295L) ^ Long.MIN_VALUE, j ^ Long.MIN_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-xj2QHRw, reason: not valid java name */
    public static final int m673compareToxj2QHRw(int i, short s) {
        return Integer.compare(i ^ Integer.MIN_VALUE, (s & UShort.MAX_VALUE) ^ Integer.MIN_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: dec-pVg5ArA, reason: not valid java name */
    public static final int m675decpVg5ArA(int i) {
        return i - 1;
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-7apg3OU, reason: not valid java name */
    public static final int m676div7apg3OU(int i, byte b) {
        return UByte$$ExternalSyntheticBackport0.m(i, b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-VKZWuLQ, reason: not valid java name */
    public static final long m677divVKZWuLQ(int i, long j) {
        return UByte$$ExternalSyntheticBackport3.m(((long) i) & 4294967295L, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-WZ4Q5Ns, reason: not valid java name */
    public static final int m678divWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m931uintDivideJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-xj2QHRw, reason: not valid java name */
    public static final int m679divxj2QHRw(int i, short s) {
        return UByte$$ExternalSyntheticBackport0.m(i, s & UShort.MAX_VALUE);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m680equalsimpl(int i, Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).data;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m681equalsimpl0(int i, int i2) {
        return i == i2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-7apg3OU, reason: not valid java name */
    public static final int m682floorDiv7apg3OU(int i, byte b) {
        return UByte$$ExternalSyntheticBackport0.m(i, b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    public static final long m683floorDivVKZWuLQ(int i, long j) {
        return UByte$$ExternalSyntheticBackport3.m(((long) i) & 4294967295L, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    public static final int m684floorDivWZ4Q5Ns(int i, int i2) {
        return UByte$$ExternalSyntheticBackport0.m(i, i2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    public static final int m685floorDivxj2QHRw(int i, short s) {
        return UByte$$ExternalSyntheticBackport0.m(i, s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: inc-pVg5ArA, reason: not valid java name */
    public static final int m687incpVg5ArA(int i) {
        return i + 1;
    }

    @InlineOnly
    /* JADX INFO: renamed from: inv-pVg5ArA, reason: not valid java name */
    public static final int m688invpVg5ArA(int i) {
        return ~i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-7apg3OU, reason: not valid java name */
    public static final int m689minus7apg3OU(int i, byte b) {
        return i - (b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-VKZWuLQ, reason: not valid java name */
    public static final long m690minusVKZWuLQ(int i, long j) {
        return (((long) i) & 4294967295L) - j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    public static final int m691minusWZ4Q5Ns(int i, int i2) {
        return i - i2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-xj2QHRw, reason: not valid java name */
    public static final int m692minusxj2QHRw(int i, short s) {
        return i - (s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-7apg3OU, reason: not valid java name */
    public static final byte m693mod7apg3OU(int i, byte b) {
        return (byte) UByte$$ExternalSyntheticBackport1.m(i, b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-VKZWuLQ, reason: not valid java name */
    public static final long m694modVKZWuLQ(int i, long j) {
        return UByte$$ExternalSyntheticBackport2.m(((long) i) & 4294967295L, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    public static final int m695modWZ4Q5Ns(int i, int i2) {
        return UByte$$ExternalSyntheticBackport1.m(i, i2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-xj2QHRw, reason: not valid java name */
    public static final short m696modxj2QHRw(int i, short s) {
        return (short) UByte$$ExternalSyntheticBackport1.m(i, s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: or-WZ4Q5Ns, reason: not valid java name */
    public static final int m697orWZ4Q5Ns(int i, int i2) {
        return i | i2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-7apg3OU, reason: not valid java name */
    public static final int m698plus7apg3OU(int i, byte b) {
        return i + (b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-VKZWuLQ, reason: not valid java name */
    public static final long m699plusVKZWuLQ(int i, long j) {
        return (((long) i) & 4294967295L) + j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    public static final int m700plusWZ4Q5Ns(int i, int i2) {
        return i + i2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-xj2QHRw, reason: not valid java name */
    public static final int m701plusxj2QHRw(int i, short s) {
        return i + (s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rangeTo-WZ4Q5Ns, reason: not valid java name */
    public static final UIntRange m702rangeToWZ4Q5Ns(int i, int i2) {
        return new UIntRange(i, i2, 1);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: rangeUntil-WZ4Q5Ns, reason: not valid java name */
    public static final UIntRange m703rangeUntilWZ4Q5Ns(int i, int i2) {
        return URangesKt___URangesKt.m1856untilJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-7apg3OU, reason: not valid java name */
    public static final int m704rem7apg3OU(int i, byte b) {
        return UByte$$ExternalSyntheticBackport1.m(i, b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-VKZWuLQ, reason: not valid java name */
    public static final long m705remVKZWuLQ(int i, long j) {
        return UByte$$ExternalSyntheticBackport2.m(((long) i) & 4294967295L, j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    public static final int m706remWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m932uintRemainderJ1ME1BU(i, i2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-xj2QHRw, reason: not valid java name */
    public static final int m707remxj2QHRw(int i, short s) {
        return UByte$$ExternalSyntheticBackport1.m(i, s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: shl-pVg5ArA, reason: not valid java name */
    public static final int m708shlpVg5ArA(int i, int i2) {
        return i << i2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: shr-pVg5ArA, reason: not valid java name */
    public static final int m709shrpVg5ArA(int i, int i2) {
        return i >>> i2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-7apg3OU, reason: not valid java name */
    public static final int m710times7apg3OU(int i, byte b) {
        return i * (b & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-VKZWuLQ, reason: not valid java name */
    public static final long m711timesVKZWuLQ(int i, long j) {
        return (((long) i) & 4294967295L) * j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-WZ4Q5Ns, reason: not valid java name */
    public static final int m712timesWZ4Q5Ns(int i, int i2) {
        return i * i2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-xj2QHRw, reason: not valid java name */
    public static final int m713timesxj2QHRw(int i, short s) {
        return i * (s & UShort.MAX_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toByte-impl, reason: not valid java name */
    public static final byte m714toByteimpl(int i) {
        return (byte) i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    public static final double m715toDoubleimpl(int i) {
        return UnsignedKt.uintToDouble(i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toFloat-impl, reason: not valid java name */
    public static final float m716toFloatimpl(int i) {
        return (float) UnsignedKt.uintToDouble(i);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    public static final long m718toLongimpl(int i) {
        return ((long) i) & 4294967295L;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toShort-impl, reason: not valid java name */
    public static final short m719toShortimpl(int i) {
        return (short) i;
    }

    @NotNull
    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m720toStringimpl(int i) {
        return String.valueOf(((long) i) & 4294967295L);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUByte-w2LRezQ, reason: not valid java name */
    public static final byte m721toUBytew2LRezQ(int i) {
        return (byte) i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toULong-s-VKNKU, reason: not valid java name */
    public static final long m723toULongsVKNKU(int i) {
        return ((long) i) & 4294967295L;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    public static final short m724toUShortMh2AYeg(int i) {
        return (short) i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: xor-WZ4Q5Ns, reason: not valid java name */
    public static final int m725xorWZ4Q5Ns(int i, int i2) {
        return i ^ i2;
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(UInt uInt) {
        return UnsignedKt.uintCompare(this.data, uInt.data);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    public final int m726compareToWZ4Q5Ns(int i) {
        return UnsignedKt.uintCompare(this.data, i);
    }

    public boolean equals(Object obj) {
        return m680equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return this.data;
    }

    @NotNull
    public String toString() {
        return m720toStringimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m727unboximpl() {
        return this.data;
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    public static int m672compareToWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2);
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m674constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m686hashCodeimpl(int i) {
        return i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    public static final int m717toIntimpl(int i) {
        return i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUInt-pVg5ArA, reason: not valid java name */
    public static final int m722toUIntpVg5ArA(int i) {
        return i;
    }
}
