package kotlin.coroutines.jvm.internal;

import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@JvmName(name = "Boxing")
public final class Boxing {
    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Boolean boxBoolean(boolean z) {
        return Boolean.valueOf(z);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Byte boxByte(byte b) {
        return Byte.valueOf(b);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Character boxChar(char c) {
        return new Character(c);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Double boxDouble(double d) {
        return new Double(d);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Float boxFloat(float f) {
        return new Float(f);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Integer boxInt(int i) {
        return new Integer(i);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Long boxLong(long j) {
        return new Long(j);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Short boxShort(short s) {
        return new Short(s);
    }
}
