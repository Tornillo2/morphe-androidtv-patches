package com.amazon.avod.mpb.api.sample;

import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class VideoMetadata {

    @NotNull
    public final VideoCodecType codecType;
    public final double frameRate;
    public final int pixelHeight;
    public final int pixelWidth;

    public VideoMetadata(int i, int i2, @NotNull VideoCodecType codecType, double d) {
        Intrinsics.checkNotNullParameter(codecType, "codecType");
        this.pixelWidth = i;
        this.pixelHeight = i2;
        this.codecType = codecType;
        this.frameRate = d;
    }

    public static /* synthetic */ VideoMetadata copy$default(VideoMetadata videoMetadata, int i, int i2, VideoCodecType videoCodecType, double d, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = videoMetadata.pixelWidth;
        }
        if ((i3 & 2) != 0) {
            i2 = videoMetadata.pixelHeight;
        }
        if ((i3 & 4) != 0) {
            videoCodecType = videoMetadata.codecType;
        }
        if ((i3 & 8) != 0) {
            d = videoMetadata.frameRate;
        }
        VideoCodecType videoCodecType2 = videoCodecType;
        return videoMetadata.copy(i, i2, videoCodecType2, d);
    }

    public final int component1() {
        return this.pixelWidth;
    }

    public final int component2() {
        return this.pixelHeight;
    }

    @NotNull
    public final VideoCodecType component3() {
        return this.codecType;
    }

    public final double component4() {
        return this.frameRate;
    }

    @NotNull
    public final VideoMetadata copy(int i, int i2, @NotNull VideoCodecType codecType, double d) {
        Intrinsics.checkNotNullParameter(codecType, "codecType");
        return new VideoMetadata(i, i2, codecType, d);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoMetadata)) {
            return false;
        }
        VideoMetadata videoMetadata = (VideoMetadata) obj;
        return this.pixelWidth == videoMetadata.pixelWidth && this.pixelHeight == videoMetadata.pixelHeight && this.codecType == videoMetadata.codecType && Double.compare(this.frameRate, videoMetadata.frameRate) == 0;
    }

    @NotNull
    public final VideoCodecType getCodecType() {
        return this.codecType;
    }

    public final double getFrameRate() {
        return this.frameRate;
    }

    public final int getPixelHeight() {
        return this.pixelHeight;
    }

    public final int getPixelWidth() {
        return this.pixelWidth;
    }

    public int hashCode() {
        return VideoMetadata$$ExternalSyntheticBackport0.m(this.frameRate) + ((this.codecType.hashCode() + (((this.pixelWidth * 31) + this.pixelHeight) * 31)) * 31);
    }

    @NotNull
    public String toString() {
        int i = this.pixelWidth;
        int i2 = this.pixelHeight;
        VideoCodecType videoCodecType = this.codecType;
        double d = this.frameRate;
        StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("VideoMetadata(pixelWidth=", i, ", pixelHeight=", i2, ", codecType=");
        sbM.append(videoCodecType);
        sbM.append(", frameRate=");
        sbM.append(d);
        sbM.append(")");
        return sbM.toString();
    }
}
