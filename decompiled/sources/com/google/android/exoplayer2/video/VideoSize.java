package com.google.android.exoplayer2.video;

import android.os.Bundle;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class VideoSize implements Bundleable {
    public static final int DEFAULT_HEIGHT = 0;
    public static final float DEFAULT_PIXEL_WIDTH_HEIGHT_RATIO = 1.0f;
    public static final int DEFAULT_UNAPPLIED_ROTATION_DEGREES = 0;
    public static final int DEFAULT_WIDTH = 0;

    @IntRange(from = 0)
    public final int height;

    @FloatRange(from = 0.0d, fromInclusive = false)
    public final float pixelWidthHeightRatio;

    @IntRange(from = 0, to = 359)
    public final int unappliedRotationDegrees;

    @IntRange(from = 0)
    public final int width;
    public static final VideoSize UNKNOWN = new VideoSize(0, 0);
    public static final String FIELD_WIDTH = Util.intToStringMaxRadix(0);
    public static final String FIELD_HEIGHT = Integer.toString(1, 36);
    public static final String FIELD_UNAPPLIED_ROTATION_DEGREES = Integer.toString(2, 36);
    public static final String FIELD_PIXEL_WIDTH_HEIGHT_RATIO = Integer.toString(3, 36);
    public static final Bundleable.Creator<VideoSize> CREATOR = new VideoSize$$ExternalSyntheticLambda0();

    public static /* synthetic */ VideoSize $r8$lambda$Vcy9luxdITX3Asjgr_UYxhsBWBE(Bundle bundle) {
        return new VideoSize(bundle.getInt(FIELD_WIDTH, 0), bundle.getInt(FIELD_HEIGHT, 0), bundle.getInt(FIELD_UNAPPLIED_ROTATION_DEGREES, 0), bundle.getFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, 1.0f));
    }

    public VideoSize(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        this(i, i2, 0, 1.0f);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VideoSize) {
            VideoSize videoSize = (VideoSize) obj;
            if (this.width == videoSize.width && this.height == videoSize.height && this.unappliedRotationDegrees == videoSize.unappliedRotationDegrees && this.pixelWidthHeightRatio == videoSize.pixelWidthHeightRatio) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToRawIntBits(this.pixelWidthHeightRatio) + ((((((DefaultImageHeaderParser.MARKER_EOI + this.width) * 31) + this.height) * 31) + this.unappliedRotationDegrees) * 31);
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_WIDTH, this.width);
        bundle.putInt(FIELD_HEIGHT, this.height);
        bundle.putInt(FIELD_UNAPPLIED_ROTATION_DEGREES, this.unappliedRotationDegrees);
        bundle.putFloat(FIELD_PIXEL_WIDTH_HEIGHT_RATIO, this.pixelWidthHeightRatio);
        return bundle;
    }

    public VideoSize(@IntRange(from = 0) int i, @IntRange(from = 0) int i2, @IntRange(from = 0, to = 359) int i3, @FloatRange(from = 0.0d, fromInclusive = false) float f) {
        this.width = i;
        this.height = i2;
        this.unappliedRotationDegrees = i3;
        this.pixelWidthHeightRatio = f;
    }
}
