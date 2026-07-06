package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.OptIn;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public final class RendererCapabilityChecker {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String FORMAT_EXCEED_AUDIO_RENDERER_CAPABILITIES = "RendererFormatSupport.AudioFormatExceedsRendererCapabilities";

    @NotNull
    public static final String FORMAT_EXCEED_VIDEO_RENDERER_CAPABILITIES = "RendererFormatSupport.VideoFormatExceedsRendererCapabilities";

    @NotNull
    public final MinervaMetrics minervaMetrics;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Result {

        @NotNull
        public final String formatSupportName;

        @NotNull
        public final SupportStatus status;

        public Result(@NotNull SupportStatus status, @NotNull String formatSupportName) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(formatSupportName, "formatSupportName");
            this.status = status;
            this.formatSupportName = formatSupportName;
        }

        public static /* synthetic */ Result copy$default(Result result, SupportStatus supportStatus, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                supportStatus = result.status;
            }
            if ((i & 2) != 0) {
                str = result.formatSupportName;
            }
            return result.copy(supportStatus, str);
        }

        @NotNull
        public final SupportStatus component1() {
            return this.status;
        }

        @NotNull
        public final String component2() {
            return this.formatSupportName;
        }

        @NotNull
        public final Result copy(@NotNull SupportStatus status, @NotNull String formatSupportName) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(formatSupportName, "formatSupportName");
            return new Result(status, formatSupportName);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Result)) {
                return false;
            }
            Result result = (Result) obj;
            return this.status == result.status && Intrinsics.areEqual(this.formatSupportName, result.formatSupportName);
        }

        @NotNull
        public final String getFormatSupportName() {
            return this.formatSupportName;
        }

        @NotNull
        public final SupportStatus getStatus() {
            return this.status;
        }

        public int hashCode() {
            return this.formatSupportName.hashCode() + (this.status.hashCode() * 31);
        }

        public final boolean isFormatSupported() {
            return this.status == SupportStatus.SUPPORTED;
        }

        @NotNull
        public String toString() {
            return "Result(status=" + this.status + ", formatSupportName=" + this.formatSupportName + ")";
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SupportStatus {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ SupportStatus[] $VALUES;
        public static final SupportStatus SUPPORTED = new SupportStatus("SUPPORTED", 0);
        public static final SupportStatus NOT_SUPPORTED = new SupportStatus("NOT_SUPPORTED", 1);

        public static final /* synthetic */ SupportStatus[] $values() {
            return new SupportStatus[]{SUPPORTED, NOT_SUPPORTED};
        }

        static {
            SupportStatus[] supportStatusArr$values = $values();
            $VALUES = supportStatusArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(supportStatusArr$values);
        }

        public SupportStatus(String str, int i) {
        }

        @NotNull
        public static EnumEntries<SupportStatus> getEntries() {
            return $ENTRIES;
        }

        public static SupportStatus valueOf(String str) {
            return (SupportStatus) Enum.valueOf(SupportStatus.class, str);
        }

        public static SupportStatus[] values() {
            return (SupportStatus[]) $VALUES.clone();
        }
    }

    public RendererCapabilityChecker(@NotNull MinervaMetrics minervaMetrics) {
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        this.minervaMetrics = minervaMetrics;
    }

    @NotNull
    public final Result checkFormatSupportLevel(@NotNull Renderer renderer, @NotNull Format format) {
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        Intrinsics.checkNotNullParameter(format, "format");
        int iSupportsFormat = renderer.getCapabilities().supportsFormat(format) & 7;
        String supportLevelName = getSupportLevelName(iSupportsFormat);
        MpbLog.t("RendererCapabilityChecker: " + format + "'s renderer support level: " + supportLevelName);
        if (!isFormatSupportedByRenderer(iSupportsFormat)) {
            return new Result(SupportStatus.NOT_SUPPORTED, supportLevelName);
        }
        if (isFormatExceedRendererCapabilities(iSupportsFormat)) {
            recordFormatExceedCapabilitiesMetric(format);
            MpbLog.w("RendererCapabilityChecker: " + format + " exceeds renderer capabilities, playback might fail.");
        }
        return new Result(SupportStatus.SUPPORTED, supportLevelName);
    }

    public final int getFormatSupportLevel(RendererCapabilities rendererCapabilities, Format format) {
        return rendererCapabilities.supportsFormat(format) & 7;
    }

    public final String getSupportLevelName(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "Unknown Format Support Level" : "FORMAT_HANDLED" : "FORMAT_EXCEEDS_CAPABILITIES" : "FORMAT_UNSUPPORTED_DRM" : "FORMAT_UNSUPPORTED_SUBTYPE" : "FORMAT_UNSUPPORTED_TYPE";
    }

    public final boolean isFormatExceedRendererCapabilities(int i) {
        return i == 3;
    }

    public final boolean isFormatSupportedByRenderer(int i) {
        return i > 2;
    }

    public final void recordFormatExceedCapabilitiesMetric(Format format) {
        if (MimeTypes.isAudio(format.sampleMimeType)) {
            recordMetric(FORMAT_EXCEED_AUDIO_RENDERER_CAPABILITIES);
        } else {
            if (MimeTypes.isVideo(format.sampleMimeType)) {
                recordMetric(FORMAT_EXCEED_VIDEO_RENDERER_CAPABILITIES);
                return;
            }
            MpbLog.w("RendererCapabilityChecker - Unable to record metric, unknown stream format: " + format);
        }
    }

    public final void recordMetric(String str) {
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.RENDERER_FORMAT_SUPPORT_SCHEMA_ID);
        metricEventCreateMetricEvent.addLong(str, 1L);
        this.minervaMetrics.record(metricEventCreateMetricEvent);
    }
}
