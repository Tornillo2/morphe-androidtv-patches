package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.ColorSpace;
import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ColorKt {
    public static final int component1(@ColorInt int i) {
        return (i >> 24) & 255;
    }

    public static final int component2(@ColorInt int i) {
        return (i >> 16) & 255;
    }

    public static final int component3(@ColorInt int i) {
        return (i >> 8) & 255;
    }

    public static final int component4(@ColorInt int i) {
        return i & 255;
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final long convertTo(@ColorInt int i, @NotNull ColorSpace.Named named) {
        return Color.convert(i, ColorSpace.get(named));
    }

    public static final int getAlpha(@ColorInt int i) {
        return (i >> 24) & 255;
    }

    public static final int getBlue(@ColorInt int i) {
        return i & 255;
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    @NotNull
    public static final ColorSpace getColorSpace(long j) {
        return Color.colorSpace(j);
    }

    public static final int getGreen(@ColorInt int i) {
        return (i >> 8) & 255;
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float getLuminance(@ColorInt int i) {
        return Color.luminance(i);
    }

    public static final int getRed(@ColorInt int i) {
        return (i >> 16) & 255;
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final boolean isSrgb(long j) {
        return Color.isSrgb(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final boolean isWideGamut(long j) {
        return Color.isWideGamut(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    @NotNull
    public static final Color plus(@NotNull Color color, @NotNull Color color2) {
        return ColorUtils.compositeColors(color2, color);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    @NotNull
    public static final Color toColor(@ColorInt int i) {
        return Color.valueOf(i);
    }

    @ColorInt
    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final int toColorInt(long j) {
        return Color.toArgb(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final long toColorLong(@ColorInt int i) {
        return Color.pack(i);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float component1(@NotNull Color color) {
        return color.getComponent(0);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float component2(@NotNull Color color) {
        return color.getComponent(1);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float component3(@NotNull Color color) {
        return color.getComponent(2);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float component4(@NotNull Color color) {
        return color.getComponent(3);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final long convertTo(@ColorInt int i, @NotNull ColorSpace colorSpace) {
        return Color.convert(i, colorSpace);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float getAlpha(long j) {
        return Color.alpha(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float getBlue(long j) {
        return Color.blue(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float getGreen(long j) {
        return Color.green(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float getLuminance(long j) {
        return Color.luminance(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float getRed(long j) {
        return Color.red(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    @NotNull
    public static final Color toColor(long j) {
        return Color.valueOf(j);
    }

    @ColorInt
    public static final int toColorInt(@NotNull String str) {
        return Color.parseColor(str);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float component1(long j) {
        return Color.red(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float component2(long j) {
        return Color.green(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float component3(long j) {
        return Color.blue(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final float component4(long j) {
        return Color.alpha(j);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final long convertTo(long j, @NotNull ColorSpace.Named named) {
        return Color.convert(j, ColorSpace.get(named));
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    public static final long convertTo(long j, @NotNull ColorSpace colorSpace) {
        return Color.convert(j, colorSpace);
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    @NotNull
    public static final Color convertTo(@NotNull Color color, @NotNull ColorSpace.Named named) {
        return color.convert(ColorSpace.get(named));
    }

    @RequiresApi(26)
    @SuppressLint({"ClassVerificationFailure"})
    @NotNull
    public static final Color convertTo(@NotNull Color color, @NotNull ColorSpace colorSpace) {
        return color.convert(colorSpace);
    }
}
