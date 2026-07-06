package com.amazon.avod.mpb.media.playback.zoom;

import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class VideoRegion {
    public final int fromLeft;
    public final int fromTop;
    public final int height;
    public final int width;

    public VideoRegion(int i, int i2, int i3, int i4) {
        this.fromTop = i;
        this.fromLeft = i2;
        this.width = i3;
        this.height = i4;
    }

    public static VideoRegion copy$default(VideoRegion videoRegion, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = videoRegion.fromTop;
        }
        if ((i5 & 2) != 0) {
            i2 = videoRegion.fromLeft;
        }
        if ((i5 & 4) != 0) {
            i3 = videoRegion.width;
        }
        if ((i5 & 8) != 0) {
            i4 = videoRegion.height;
        }
        videoRegion.getClass();
        return new VideoRegion(i, i2, i3, i4);
    }

    public final int component1() {
        return this.fromTop;
    }

    public final int component2() {
        return this.fromLeft;
    }

    public final int component3() {
        return this.width;
    }

    public final int component4() {
        return this.height;
    }

    @NotNull
    public final VideoRegion copy(int i, int i2, int i3, int i4) {
        return new VideoRegion(i, i2, i3, i4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoRegion)) {
            return false;
        }
        VideoRegion videoRegion = (VideoRegion) obj;
        return this.fromTop == videoRegion.fromTop && this.fromLeft == videoRegion.fromLeft && this.width == videoRegion.width && this.height == videoRegion.height;
    }

    public final int getFromLeft() {
        return this.fromLeft;
    }

    public final int getFromTop() {
        return this.fromTop;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (((((this.fromTop * 31) + this.fromLeft) * 31) + this.width) * 31) + this.height;
    }

    @NotNull
    public String toString() {
        int i = this.fromTop;
        int i2 = this.fromLeft;
        int i3 = this.width;
        int i4 = this.height;
        StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("VideoRegion(fromTop=", i, ", fromLeft=", i2, ", width=");
        sbM.append(i3);
        sbM.append(", height=");
        sbM.append(i4);
        sbM.append(")");
        return sbM.toString();
    }
}
