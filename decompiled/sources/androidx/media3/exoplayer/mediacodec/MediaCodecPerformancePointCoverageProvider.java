package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodecInfo;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import com.amazon.avod.mpb.api.query.MediaCodecQuerier;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class MediaCodecPerformancePointCoverageProvider {
    public static final int COVERAGE_RESULT_NO = 1;
    public static final int COVERAGE_RESULT_NO_PERFORMANCE_POINTS_UNSUPPORTED = 0;
    public static final int COVERAGE_RESULT_YES = 2;
    public static Boolean shouldIgnorePerformancePoints;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static final class Api29 {
        @DoNotInline
        public static int areResolutionAndFrameRateCovered(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2, double d) {
            List<MediaCodecInfo.VideoCapabilities.PerformancePoint> supportedPerformancePoints = videoCapabilities.getSupportedPerformancePoints();
            if (supportedPerformancePoints == null || supportedPerformancePoints.isEmpty()) {
                return 0;
            }
            MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline2.m();
            int iEvaluatePerformancePointCoverage = evaluatePerformancePointCoverage(supportedPerformancePoints, MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline1.m(i, i2, (int) d));
            if (iEvaluatePerformancePointCoverage == 1 && MediaCodecPerformancePointCoverageProvider.shouldIgnorePerformancePoints == null) {
                Boolean boolValueOf = Boolean.valueOf(shouldIgnorePerformancePoints());
                MediaCodecPerformancePointCoverageProvider.shouldIgnorePerformancePoints = boolValueOf;
                if (boolValueOf.booleanValue()) {
                    return 0;
                }
            }
            return iEvaluatePerformancePointCoverage;
        }

        public static int evaluatePerformancePointCoverage(List<MediaCodecInfo.VideoCapabilities.PerformancePoint> list, MediaCodecInfo.VideoCapabilities.PerformancePoint performancePoint) {
            for (int i = 0; i < list.size(); i++) {
                if (MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline0.m(list.get(i)).covers(performancePoint)) {
                    return 2;
                }
            }
            return 1;
        }

        public static boolean shouldIgnorePerformancePoints() {
            List<MediaCodecInfo.VideoCapabilities.PerformancePoint> supportedPerformancePoints;
            if (Util.SDK_INT >= 35) {
                return false;
            }
            try {
                Format.Builder builder = new Format.Builder();
                builder.sampleMimeType = MimeTypes.normalizeMimeType("video/avc");
                Format format = new Format(builder);
                if (format.sampleMimeType != null) {
                    List<MediaCodecInfo> decoderInfosSoftMatch = MediaCodecUtil.getDecoderInfosSoftMatch(MediaCodecSelector.DEFAULT, format, false, false);
                    for (int i = 0; i < decoderInfosSoftMatch.size(); i++) {
                        if (decoderInfosSoftMatch.get(i).capabilities != null && decoderInfosSoftMatch.get(i).capabilities.getVideoCapabilities() != null && (supportedPerformancePoints = decoderInfosSoftMatch.get(i).capabilities.getVideoCapabilities().getSupportedPerformancePoints()) != null && !supportedPerformancePoints.isEmpty()) {
                            MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline2.m();
                            return evaluatePerformancePointCoverage(supportedPerformancePoints, MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline1.m(MediaCodecQuerier.HD_MIN_WIDTH, MediaCodecQuerier.HD_MIN_HEIGHT, 60)) == 1;
                        }
                    }
                }
            } catch (MediaCodecUtil.DecoderQueryException unused) {
            }
            return true;
        }
    }

    public static int areResolutionAndFrameRateCovered(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2, double d) {
        if (Util.SDK_INT < 29) {
            return 0;
        }
        Boolean bool = shouldIgnorePerformancePoints;
        if (bool == null || !bool.booleanValue()) {
            return Api29.areResolutionAndFrameRateCovered(videoCapabilities, i, i2, d);
        }
        return 0;
    }
}
