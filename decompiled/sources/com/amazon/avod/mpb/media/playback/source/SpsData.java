package com.amazon.avod.mpb.media.playback.source;

import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SpsData {
    public final int height;
    public final float pixelWidthHeightRatio;
    public final int width;

    public SpsData(int i, int i2, float f) {
        this.width = i;
        this.height = i2;
        this.pixelWidthHeightRatio = f;
    }

    public static SpsData copy$default(SpsData spsData, int i, int i2, float f, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = spsData.width;
        }
        if ((i3 & 2) != 0) {
            i2 = spsData.height;
        }
        if ((i3 & 4) != 0) {
            f = spsData.pixelWidthHeightRatio;
        }
        spsData.getClass();
        return new SpsData(i, i2, f);
    }

    public final int component1() {
        return this.width;
    }

    public final int component2() {
        return this.height;
    }

    public final float component3() {
        return this.pixelWidthHeightRatio;
    }

    @NotNull
    public final SpsData copy(int i, int i2, float f) {
        return new SpsData(i, i2, f);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SpsData)) {
            return false;
        }
        SpsData spsData = (SpsData) obj;
        return this.width == spsData.width && this.height == spsData.height && Float.compare(this.pixelWidthHeightRatio, spsData.pixelWidthHeightRatio) == 0;
    }

    public final int getHeight() {
        return this.height;
    }

    public final float getPixelWidthHeightRatio() {
        return this.pixelWidthHeightRatio;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.pixelWidthHeightRatio) + (((this.width * 31) + this.height) * 31);
    }

    @NotNull
    public String toString() {
        int i = this.width;
        int i2 = this.height;
        float f = this.pixelWidthHeightRatio;
        StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("SpsData(width=", i, ", height=", i2, ", pixelWidthHeightRatio=");
        sbM.append(f);
        sbM.append(")");
        return sbM.toString();
    }
}
