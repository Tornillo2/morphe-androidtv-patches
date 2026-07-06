package kotlin;

import kotlin.internal.InlineOnly;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class NumbersKt__NumbersKt extends NumbersKt__NumbersJVMKt {
    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int countLeadingZeroBits(byte b) {
        return Integer.numberOfLeadingZeros(b & 255) - 24;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int countOneBits(byte b) {
        return Integer.bitCount(b & 255);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int countTrailingZeroBits(byte b) {
        return Integer.numberOfTrailingZeros(b | 256);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final byte rotateLeft(byte b, int i) {
        int i2 = i & 7;
        return (byte) (((b & 255) >>> (8 - i2)) | (b << i2));
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final byte rotateRight(byte b, int i) {
        int i2 = i & 7;
        return (byte) (((b & 255) >>> i2) | (b << (8 - i2)));
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final byte takeHighestOneBit(byte b) {
        return (byte) Integer.highestOneBit(b & 255);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final byte takeLowestOneBit(byte b) {
        return (byte) Integer.lowestOneBit(b);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int countLeadingZeroBits(short s) {
        return Integer.numberOfLeadingZeros(s & UShort.MAX_VALUE) - 16;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int countOneBits(short s) {
        return Integer.bitCount(s & UShort.MAX_VALUE);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int countTrailingZeroBits(short s) {
        return Integer.numberOfTrailingZeros(s | 65536);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final short rotateLeft(short s, int i) {
        int i2 = i & 15;
        return (short) (((s & 65535) >>> (16 - i2)) | (s << i2));
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final short rotateRight(short s, int i) {
        int i2 = i & 15;
        return (short) (((s & 65535) >>> i2) | (s << (16 - i2)));
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final short takeHighestOneBit(short s) {
        return (short) Integer.highestOneBit(s & UShort.MAX_VALUE);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final short takeLowestOneBit(short s) {
        return (short) Integer.lowestOneBit(s);
    }
}
