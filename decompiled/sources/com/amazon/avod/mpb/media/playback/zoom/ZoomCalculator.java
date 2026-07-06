package com.amazon.avod.mpb.media.playback.zoom;

import com.amazon.avod.mpb.api.core.DevicePropertyProvider;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.media.playback.zoom.ZoomLevel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt__MathJVMKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nZoomCalculator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZoomCalculator.kt\ncom/amazon/avod/mpb/media/playback/zoom/ZoomCalculator\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,171:1\n1#2:172\n*E\n"})
public final class ZoomCalculator {
    public static final double DELTA = 0.01d;

    @NotNull
    public static final String TAG = "ZoomCalculator";

    @NotNull
    public ZoomLevel currentLevel;
    public boolean hasDar;

    @NotNull
    public final Object mutex;
    public int screenHeight;
    public int screenWidth;
    public int surfaceLeft;
    public int surfaceTop;
    public int videoHeight;
    public int videoWidth;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final VideoRegion OFF_SCREEN_REGION_NON_ZERO = new VideoRegion(0, 0, 1, 1);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public ZoomCalculator(@NotNull DevicePropertyProvider propertyProvider) {
        Intrinsics.checkNotNullParameter(propertyProvider, "propertyProvider");
        this.mutex = new Object();
        this.screenWidth = propertyProvider.getMaxVideoWidth();
        this.screenHeight = propertyProvider.getMaxVideoHeight();
        this.currentLevel = ZoomLevel.Native.INSTANCE;
    }

    @NotNull
    public final VideoRegion getVideoRegion() {
        char c;
        char c2;
        double dMax;
        synchronized (this.mutex) {
            if (!this.hasDar) {
                throw new IllegalStateException("setAspectRatio with a valid DAR must be called first");
            }
            ZoomLevel zoomLevelForFullScreen = this.currentLevel;
            if (zoomLevelForFullScreen instanceof ZoomLevel.Invisible) {
                MpbLog.logf("%s.getVideoRegion level=Invisible -> off-screen", TAG);
                return OFF_SCREEN_REGION_NON_ZERO;
            }
            if (zoomLevelForFullScreen instanceof ZoomLevel.FullScreen) {
                zoomLevelForFullScreen = getZoomLevelForFullScreen();
            }
            double d = this.screenWidth;
            int i = this.videoWidth;
            double d2 = d / ((double) i);
            double d3 = this.screenHeight;
            int i2 = this.videoHeight;
            double d4 = d3 / ((double) i2);
            double d5 = ((double) i) / ((double) i2);
            if (zoomLevelForFullScreen instanceof ZoomLevel.Native) {
                dMax = Math.min(d2, d4);
            } else if (zoomLevelForFullScreen instanceof ZoomLevel.FullScreen) {
                dMax = Math.max(d2, d4);
            } else {
                if (!(zoomLevelForFullScreen instanceof ZoomLevel.WindowBoxing)) {
                    if (!(zoomLevelForFullScreen instanceof ZoomLevel.Custom)) {
                        if (zoomLevelForFullScreen instanceof ZoomLevel.Invisible) {
                            throw new IllegalStateException("Invisible is handled before this when");
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                    c = 1;
                    c2 = 0;
                    dMax = (Math.max(zoomLevelForFullScreen.zoomRatio, d5) / Math.min(zoomLevelForFullScreen.zoomRatio, d5)) * Math.max(d2, d4);
                    int iRoundToInt = MathKt__MathJVMKt.roundToInt(((double) this.videoWidth) * dMax);
                    int iRoundToInt2 = MathKt__MathJVMKt.roundToInt(((double) this.videoHeight) * dMax);
                    VideoRegion videoRegion = new VideoRegion(this.surfaceTop + ((this.screenHeight - iRoundToInt2) / 2), this.surfaceLeft + ((this.screenWidth - iRoundToInt) / 2), iRoundToInt, iRoundToInt2);
                    ZoomLevel zoomLevel = this.currentLevel;
                    Object[] objArr = new Object[4];
                    objArr[c2] = TAG;
                    objArr[c] = zoomLevel;
                    objArr[2] = zoomLevelForFullScreen;
                    objArr[3] = videoRegion;
                    MpbLog.logf("%s.getVideoRegion level=%s effective=%s -> %s", objArr);
                    return videoRegion;
                }
                dMax = Math.min(d2, d4) * ((double) zoomLevelForFullScreen.zoomRatio);
            }
            c = 1;
            c2 = 0;
            int iRoundToInt3 = MathKt__MathJVMKt.roundToInt(((double) this.videoWidth) * dMax);
            int iRoundToInt22 = MathKt__MathJVMKt.roundToInt(((double) this.videoHeight) * dMax);
            VideoRegion videoRegion2 = new VideoRegion(this.surfaceTop + ((this.screenHeight - iRoundToInt22) / 2), this.surfaceLeft + ((this.screenWidth - iRoundToInt3) / 2), iRoundToInt3, iRoundToInt22);
            ZoomLevel zoomLevel2 = this.currentLevel;
            Object[] objArr2 = new Object[4];
            objArr2[c2] = TAG;
            objArr2[c] = zoomLevel2;
            objArr2[2] = zoomLevelForFullScreen;
            objArr2[3] = videoRegion2;
            MpbLog.logf("%s.getVideoRegion level=%s effective=%s -> %s", objArr2);
            return videoRegion2;
        }
    }

    public final ZoomLevel getZoomLevelForFullScreen() {
        return Math.abs((((double) this.screenWidth) / ((double) this.screenHeight)) - (((double) this.videoWidth) / ((double) this.videoHeight))) < 0.01d ? new ZoomLevel.Custom(2.39f) : ZoomLevel.FullScreen.INSTANCE;
    }

    public final boolean isInitialized() {
        boolean z;
        synchronized (this.mutex) {
            z = this.hasDar;
        }
        return z;
    }

    public final void setAspectRatio(int i, float f) {
        synchronized (this.mutex) {
            if (Float.isNaN(f) || f <= 0.0f || i <= 0) {
                throw new IllegalStateException("setAspectRatio must be called with valid encodedVideoWidth and DAR");
            }
            this.videoWidth = i;
            this.videoHeight = MathKt__MathJVMKt.roundToInt(i / f);
            this.hasDar = true;
            MpbLog.logf("%s.setAspectRatio video=%dx%d DAR=%s", TAG, Integer.valueOf(this.videoWidth), Integer.valueOf(this.videoHeight), Float.valueOf(f));
        }
    }

    public final void setViewport(int i, int i2, int i3, int i4) {
        synchronized (this.mutex) {
            this.surfaceLeft = i;
            this.surfaceTop = i2;
            this.screenWidth = i3;
            this.screenHeight = i4;
            MpbLog.logf("%s.setViewport left=%d top=%d width=%d height=%d", TAG, Integer.valueOf(i), Integer.valueOf(this.surfaceTop), Integer.valueOf(this.screenWidth), Integer.valueOf(this.screenHeight));
        }
    }

    public final void setZoomLevel(@NotNull ZoomLevel level) {
        Intrinsics.checkNotNullParameter(level, "level");
        synchronized (this.mutex) {
            this.currentLevel = level;
        }
    }
}
