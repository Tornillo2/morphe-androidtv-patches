package androidx.media3.common;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class FrameInfo {
    public final ColorInfo colorInfo;
    public final int height;
    public final long offsetToAddUs;
    public final float pixelWidthHeightRatio;
    public final int width;

    public FrameInfo(ColorInfo colorInfo, int i, int i2, float f, long j) {
        Assertions.checkArgument(i > 0, "width must be positive, but is: " + i);
        Assertions.checkArgument(i2 > 0, "height must be positive, but is: " + i2);
        this.colorInfo = colorInfo;
        this.width = i;
        this.height = i2;
        this.pixelWidthHeightRatio = f;
        this.offsetToAddUs = j;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public ColorInfo colorInfo;
        public int height;
        public long offsetToAddUs;
        public float pixelWidthHeightRatio;
        public int width;

        public Builder(ColorInfo colorInfo, int i, int i2) {
            this.colorInfo = colorInfo;
            this.width = i;
            this.height = i2;
            this.pixelWidthHeightRatio = 1.0f;
        }

        public FrameInfo build() {
            return new FrameInfo(this.colorInfo, this.width, this.height, this.pixelWidthHeightRatio, this.offsetToAddUs);
        }

        @CanIgnoreReturnValue
        public Builder setColorInfo(ColorInfo colorInfo) {
            this.colorInfo = colorInfo;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setHeight(int i) {
            this.height = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setOffsetToAddUs(long j) {
            this.offsetToAddUs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPixelWidthHeightRatio(float f) {
            this.pixelWidthHeightRatio = f;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setWidth(int i) {
            this.width = i;
            return this;
        }

        public Builder(FrameInfo frameInfo) {
            this.colorInfo = frameInfo.colorInfo;
            this.width = frameInfo.width;
            this.height = frameInfo.height;
            this.pixelWidthHeightRatio = frameInfo.pixelWidthHeightRatio;
            this.offsetToAddUs = frameInfo.offsetToAddUs;
        }
    }
}
