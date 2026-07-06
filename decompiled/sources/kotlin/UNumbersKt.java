package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@JvmName(name = "UNumbersKt")
public final class UNumbersKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countLeadingZeroBits-7apg3OU, reason: not valid java name */
    public static final int m826countLeadingZeroBits7apg3OU(byte b) {
        return Integer.numberOfLeadingZeros(b & 255) - 24;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countLeadingZeroBits-VKZWuLQ, reason: not valid java name */
    public static final int m827countLeadingZeroBitsVKZWuLQ(long j) {
        return Long.numberOfLeadingZeros(j);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countLeadingZeroBits-WZ4Q5Ns, reason: not valid java name */
    public static final int m828countLeadingZeroBitsWZ4Q5Ns(int i) {
        return Integer.numberOfLeadingZeros(i);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countLeadingZeroBits-xj2QHRw, reason: not valid java name */
    public static final int m829countLeadingZeroBitsxj2QHRw(short s) {
        return Integer.numberOfLeadingZeros(s & UShort.MAX_VALUE) - 16;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countOneBits-7apg3OU, reason: not valid java name */
    public static final int m830countOneBits7apg3OU(byte b) {
        return Integer.bitCount(b & 255);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countOneBits-VKZWuLQ, reason: not valid java name */
    public static final int m831countOneBitsVKZWuLQ(long j) {
        return Long.bitCount(j);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countOneBits-WZ4Q5Ns, reason: not valid java name */
    public static final int m832countOneBitsWZ4Q5Ns(int i) {
        return Integer.bitCount(i);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countOneBits-xj2QHRw, reason: not valid java name */
    public static final int m833countOneBitsxj2QHRw(short s) {
        return Integer.bitCount(s & UShort.MAX_VALUE);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countTrailingZeroBits-7apg3OU, reason: not valid java name */
    public static final int m834countTrailingZeroBits7apg3OU(byte b) {
        return Integer.numberOfTrailingZeros(b | 256);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countTrailingZeroBits-VKZWuLQ, reason: not valid java name */
    public static final int m835countTrailingZeroBitsVKZWuLQ(long j) {
        return Long.numberOfTrailingZeros(j);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countTrailingZeroBits-WZ4Q5Ns, reason: not valid java name */
    public static final int m836countTrailingZeroBitsWZ4Q5Ns(int i) {
        return Integer.numberOfTrailingZeros(i);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: countTrailingZeroBits-xj2QHRw, reason: not valid java name */
    public static final int m837countTrailingZeroBitsxj2QHRw(short s) {
        return Integer.numberOfTrailingZeros(s | 65536);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: rotateLeft-JSWoG40, reason: not valid java name */
    public static final long m838rotateLeftJSWoG40(long j, int i) {
        return Long.rotateLeft(j, i);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: rotateLeft-LxnNnR4, reason: not valid java name */
    public static final byte m839rotateLeftLxnNnR4(byte b, int i) {
        return NumbersKt__NumbersKt.rotateLeft(b, i);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: rotateLeft-V7xB4Y4, reason: not valid java name */
    public static final int m840rotateLeftV7xB4Y4(int i, int i2) {
        return Integer.rotateLeft(i, i2);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: rotateLeft-olVBNx4, reason: not valid java name */
    public static final short m841rotateLeftolVBNx4(short s, int i) {
        return NumbersKt__NumbersKt.rotateLeft(s, i);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: rotateRight-JSWoG40, reason: not valid java name */
    public static final long m842rotateRightJSWoG40(long j, int i) {
        return Long.rotateRight(j, i);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: rotateRight-LxnNnR4, reason: not valid java name */
    public static final byte m843rotateRightLxnNnR4(byte b, int i) {
        return NumbersKt__NumbersKt.rotateRight(b, i);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: rotateRight-V7xB4Y4, reason: not valid java name */
    public static final int m844rotateRightV7xB4Y4(int i, int i2) {
        return Integer.rotateRight(i, i2);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class, ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* JADX INFO: renamed from: rotateRight-olVBNx4, reason: not valid java name */
    public static final short m845rotateRightolVBNx4(short s, int i) {
        return NumbersKt__NumbersKt.rotateRight(s, i);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: takeHighestOneBit-7apg3OU, reason: not valid java name */
    public static final byte m846takeHighestOneBit7apg3OU(byte b) {
        return (byte) Integer.highestOneBit(b & 255);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: takeHighestOneBit-VKZWuLQ, reason: not valid java name */
    public static final long m847takeHighestOneBitVKZWuLQ(long j) {
        return Long.highestOneBit(j);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: takeHighestOneBit-WZ4Q5Ns, reason: not valid java name */
    public static final int m848takeHighestOneBitWZ4Q5Ns(int i) {
        return Integer.highestOneBit(i);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: takeHighestOneBit-xj2QHRw, reason: not valid java name */
    public static final short m849takeHighestOneBitxj2QHRw(short s) {
        return (short) Integer.highestOneBit(s & UShort.MAX_VALUE);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: takeLowestOneBit-7apg3OU, reason: not valid java name */
    public static final byte m850takeLowestOneBit7apg3OU(byte b) {
        return (byte) Integer.lowestOneBit(b & 255);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: takeLowestOneBit-VKZWuLQ, reason: not valid java name */
    public static final long m851takeLowestOneBitVKZWuLQ(long j) {
        return Long.lowestOneBit(j);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: takeLowestOneBit-WZ4Q5Ns, reason: not valid java name */
    public static final int m852takeLowestOneBitWZ4Q5Ns(int i) {
        return Integer.lowestOneBit(i);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* JADX INFO: renamed from: takeLowestOneBit-xj2QHRw, reason: not valid java name */
    public static final short m853takeLowestOneBitxj2QHRw(short s) {
        return (short) Integer.lowestOneBit(s & UShort.MAX_VALUE);
    }
}
