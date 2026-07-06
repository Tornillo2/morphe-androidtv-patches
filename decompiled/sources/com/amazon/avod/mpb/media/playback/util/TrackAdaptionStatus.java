package com.amazon.avod.mpb.media.playback.util;

import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TrackAdaptionStatus {
    public long bytesWritten;
    public long decoderBufferRemaining;

    @NotNull
    public MediaPipelineBackendResultCode errorCode = MediaPipelineBackendResultCode.AV_MPB_SUCCESS;
    public long sampleBufferRemaining;
    public long sampleBufferSize;

    public final long getBytesWritten() {
        return this.bytesWritten;
    }

    public final long getDecoderBufferRemaining() {
        return this.decoderBufferRemaining;
    }

    @NotNull
    public final MediaPipelineBackendResultCode getErrorCode() {
        return this.errorCode;
    }

    public final long getSampleBufferRemaining() {
        return this.sampleBufferRemaining;
    }

    public final long getSampleBufferSize() {
        return this.sampleBufferSize;
    }

    @NotNull
    public final TrackAdaptionStatus set(@NotNull MediaPipelineBackendResultCode errorCode, long j, long j2, long j3, long j4) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        this.errorCode = errorCode;
        this.bytesWritten = j;
        this.sampleBufferSize = j2;
        this.sampleBufferRemaining = j3;
        this.decoderBufferRemaining = j4;
        return this;
    }
}
