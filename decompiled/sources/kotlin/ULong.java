package kotlin;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ULongRange;
import kotlin.ranges.URangesKt___URangesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.5")
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
public final class ULong implements Comparable<ULong> {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    public final long data;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    public /* synthetic */ ULong(long j) {
        this.data = j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: and-VKZWuLQ, reason: not valid java name */
    public static final long m747andVKZWuLQ(long j, long j2) {
        return j & j2;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ULong m748boximpl(long j) {
        return new ULong(j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-7apg3OU, reason: not valid java name */
    public static final int m749compareTo7apg3OU(long j, byte b) {
        return Long.compare(j ^ Long.MIN_VALUE, (((long) b) & 255) ^ Long.MIN_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    public static final int m751compareToWZ4Q5Ns(long j, int i) {
        return Long.compare(j ^ Long.MIN_VALUE, (((long) i) & 4294967295L) ^ Long.MIN_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-xj2QHRw, reason: not valid java name */
    public static final int m752compareToxj2QHRw(long j, short s) {
        return Long.compare(j ^ Long.MIN_VALUE, (((long) s) & 65535) ^ Long.MIN_VALUE);
    }

    @InlineOnly
    /* JADX INFO: renamed from: dec-s-VKNKU, reason: not valid java name */
    public static final long m754decsVKNKU(long j) {
        return j - 1;
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-7apg3OU, reason: not valid java name */
    public static final long m755div7apg3OU(long j, byte b) {
        return UByte$$ExternalSyntheticBackport3.m(j, ((long) b) & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-VKZWuLQ, reason: not valid java name */
    public static final long m756divVKZWuLQ(long j, long j2) {
        return UnsignedKt.m933ulongDivideeb3DHEI(j, j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-WZ4Q5Ns, reason: not valid java name */
    public static final long m757divWZ4Q5Ns(long j, int i) {
        return UByte$$ExternalSyntheticBackport3.m(j, ((long) i) & 4294967295L);
    }

    @InlineOnly
    /* JADX INFO: renamed from: div-xj2QHRw, reason: not valid java name */
    public static final long m758divxj2QHRw(long j, short s) {
        return UByte$$ExternalSyntheticBackport3.m(j, ((long) s) & 65535);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m759equalsimpl(long j, Object obj) {
        return (obj instanceof ULong) && j == ((ULong) obj).data;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m760equalsimpl0(long j, long j2) {
        return j == j2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-7apg3OU, reason: not valid java name */
    public static final long m761floorDiv7apg3OU(long j, byte b) {
        return UByte$$ExternalSyntheticBackport3.m(j, ((long) b) & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    public static final long m762floorDivVKZWuLQ(long j, long j2) {
        return UByte$$ExternalSyntheticBackport3.m(j, j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    public static final long m763floorDivWZ4Q5Ns(long j, int i) {
        return UByte$$ExternalSyntheticBackport3.m(j, ((long) i) & 4294967295L);
    }

    @InlineOnly
    /* JADX INFO: renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    public static final long m764floorDivxj2QHRw(long j, short s) {
        return UByte$$ExternalSyntheticBackport3.m(j, ((long) s) & 65535);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m765hashCodeimpl(long j) {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: inc-s-VKNKU, reason: not valid java name */
    public static final long m766incsVKNKU(long j) {
        return j + 1;
    }

    @InlineOnly
    /* JADX INFO: renamed from: inv-s-VKNKU, reason: not valid java name */
    public static final long m767invsVKNKU(long j) {
        return ~j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-7apg3OU, reason: not valid java name */
    public static final long m768minus7apg3OU(long j, byte b) {
        return j - (((long) b) & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-VKZWuLQ, reason: not valid java name */
    public static final long m769minusVKZWuLQ(long j, long j2) {
        return j - j2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    public static final long m770minusWZ4Q5Ns(long j, int i) {
        return j - (((long) i) & 4294967295L);
    }

    @InlineOnly
    /* JADX INFO: renamed from: minus-xj2QHRw, reason: not valid java name */
    public static final long m771minusxj2QHRw(long j, short s) {
        return j - (((long) s) & 65535);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-7apg3OU, reason: not valid java name */
    public static final byte m772mod7apg3OU(long j, byte b) {
        return (byte) UByte$$ExternalSyntheticBackport2.m(j, ((long) b) & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-VKZWuLQ, reason: not valid java name */
    public static final long m773modVKZWuLQ(long j, long j2) {
        return UByte$$ExternalSyntheticBackport2.m(j, j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    public static final int m774modWZ4Q5Ns(long j, int i) {
        return (int) UByte$$ExternalSyntheticBackport2.m(j, ((long) i) & 4294967295L);
    }

    @InlineOnly
    /* JADX INFO: renamed from: mod-xj2QHRw, reason: not valid java name */
    public static final short m775modxj2QHRw(long j, short s) {
        return (short) UByte$$ExternalSyntheticBackport2.m(j, ((long) s) & 65535);
    }

    @InlineOnly
    /* JADX INFO: renamed from: or-VKZWuLQ, reason: not valid java name */
    public static final long m776orVKZWuLQ(long j, long j2) {
        return j | j2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-7apg3OU, reason: not valid java name */
    public static final long m777plus7apg3OU(long j, byte b) {
        return j + (((long) b) & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-VKZWuLQ, reason: not valid java name */
    public static final long m778plusVKZWuLQ(long j, long j2) {
        return j + j2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    public static final long m779plusWZ4Q5Ns(long j, int i) {
        return j + (((long) i) & 4294967295L);
    }

    @InlineOnly
    /* JADX INFO: renamed from: plus-xj2QHRw, reason: not valid java name */
    public static final long m780plusxj2QHRw(long j, short s) {
        return j + (((long) s) & 65535);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rangeTo-VKZWuLQ, reason: not valid java name */
    public static final ULongRange m781rangeToVKZWuLQ(long j, long j2) {
        return new ULongRange(j, j2);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: rangeUntil-VKZWuLQ, reason: not valid java name */
    public static final ULongRange m782rangeUntilVKZWuLQ(long j, long j2) {
        return URangesKt___URangesKt.m1858untileb3DHEI(j, j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-7apg3OU, reason: not valid java name */
    public static final long m783rem7apg3OU(long j, byte b) {
        return UByte$$ExternalSyntheticBackport2.m(j, ((long) b) & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-VKZWuLQ, reason: not valid java name */
    public static final long m784remVKZWuLQ(long j, long j2) {
        return UnsignedKt.m934ulongRemaindereb3DHEI(j, j2);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    public static final long m785remWZ4Q5Ns(long j, int i) {
        return UByte$$ExternalSyntheticBackport2.m(j, ((long) i) & 4294967295L);
    }

    @InlineOnly
    /* JADX INFO: renamed from: rem-xj2QHRw, reason: not valid java name */
    public static final long m786remxj2QHRw(long j, short s) {
        return UByte$$ExternalSyntheticBackport2.m(j, ((long) s) & 65535);
    }

    @InlineOnly
    /* JADX INFO: renamed from: shl-s-VKNKU, reason: not valid java name */
    public static final long m787shlsVKNKU(long j, int i) {
        return j << i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: shr-s-VKNKU, reason: not valid java name */
    public static final long m788shrsVKNKU(long j, int i) {
        return j >>> i;
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-7apg3OU, reason: not valid java name */
    public static final long m789times7apg3OU(long j, byte b) {
        return j * (((long) b) & 255);
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-VKZWuLQ, reason: not valid java name */
    public static final long m790timesVKZWuLQ(long j, long j2) {
        return j * j2;
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-WZ4Q5Ns, reason: not valid java name */
    public static final long m791timesWZ4Q5Ns(long j, int i) {
        return j * (((long) i) & 4294967295L);
    }

    @InlineOnly
    /* JADX INFO: renamed from: times-xj2QHRw, reason: not valid java name */
    public static final long m792timesxj2QHRw(long j, short s) {
        return j * (((long) s) & 65535);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toByte-impl, reason: not valid java name */
    public static final byte m793toByteimpl(long j) {
        return (byte) j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    public static final double m794toDoubleimpl(long j) {
        return UnsignedKt.ulongToDouble(j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toFloat-impl, reason: not valid java name */
    public static final float m795toFloatimpl(long j) {
        return (float) UnsignedKt.ulongToDouble(j);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    public static final int m796toIntimpl(long j) {
        return (int) j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toShort-impl, reason: not valid java name */
    public static final short m798toShortimpl(long j) {
        return (short) j;
    }

    @NotNull
    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m799toStringimpl(long j) {
        return UnsignedKt.ulongToString(j, 10);
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUByte-w2LRezQ, reason: not valid java name */
    public static final byte m800toUBytew2LRezQ(long j) {
        return (byte) j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUInt-pVg5ArA, reason: not valid java name */
    public static final int m801toUIntpVg5ArA(long j) {
        return (int) j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    public static final short m803toUShortMh2AYeg(long j) {
        return (short) j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: xor-VKZWuLQ, reason: not valid java name */
    public static final long m804xorVKZWuLQ(long j, long j2) {
        return j ^ j2;
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(ULong uLong) {
        return UnsignedKt.ulongCompare(this.data, uLong.data);
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    public final int m805compareToVKZWuLQ(long j) {
        return UnsignedKt.ulongCompare(this.data, j);
    }

    public boolean equals(Object obj) {
        return m759equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(this.data);
    }

    @NotNull
    public String toString() {
        return UnsignedKt.ulongToString(this.data, 10);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m806unboximpl() {
        return this.data;
    }

    @InlineOnly
    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    public static int m750compareToVKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2);
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m753constructorimpl(long j) {
        return j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    public static final long m797toLongimpl(long j) {
        return j;
    }

    @InlineOnly
    /* JADX INFO: renamed from: toULong-s-VKNKU, reason: not valid java name */
    public static final long m802toULongsVKNKU(long j) {
        return j;
    }
}
