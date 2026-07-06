package kotlin.experimental;

import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class BitwiseOperationsKt {
    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final byte and(byte b, byte b2) {
        return (byte) (b & b2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final byte inv(byte b) {
        return (byte) (~b);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final byte or(byte b, byte b2) {
        return (byte) (b | b2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final byte xor(byte b, byte b2) {
        return (byte) (b ^ b2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final short and(short s, short s2) {
        return (short) (s & s2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final short inv(short s) {
        return (short) (~s);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final short or(short s, short s2) {
        return (short) (s | s2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    public static final short xor(short s, short s2) {
        return (short) (s ^ s2);
    }
}
