package com.amazon.avod.mpb.media.playback.util;

import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TrackAdapterImpl {

    @NotNull
    public static final TrackAdapterImpl INSTANCE = new TrackAdapterImpl();

    public final void writeAdaptedSample(@NotNull ByteBuffer sampleBuffer, @NotNull ByteBuffer decoderBuffer, long j, @NotNull TrackAdaptionStatus rv) {
        Intrinsics.checkNotNullParameter(sampleBuffer, "sampleBuffer");
        Intrinsics.checkNotNullParameter(decoderBuffer, "decoderBuffer");
        Intrinsics.checkNotNullParameter(rv, "rv");
        if (sampleBuffer.remaining() > decoderBuffer.capacity()) {
            rv.set(MediaPipelineBackendResultCode.AV_MPB_SAMPLE_ADAPTION_FAILURE_DECODER_BUFFER_SIZE_TOO_SMALL, 0L, j, sampleBuffer.remaining(), decoderBuffer.remaining());
            return;
        }
        if (sampleBuffer.remaining() < j) {
            rv.set(MediaPipelineBackendResultCode.AV_MPB_SAMPLE_ADAPTION_FAILURE_SAMPLE_BUFFER_SIZE_TOO_SMALL, 0L, j, sampleBuffer.remaining(), decoderBuffer.remaining());
            return;
        }
        decoderBuffer.put(sampleBuffer);
        long jLimit = sampleBuffer.limit();
        sampleBuffer.limit((int) j);
        if (jLimit != j) {
            rv.set(MediaPipelineBackendResultCode.AV_MPB_SAMPLE_ADAPTION_FAILURE_BYTES_COPIED_NOT_EQUAL_TO_SAMPLE_BUFFER_SIZE, jLimit, j, sampleBuffer.remaining(), decoderBuffer.remaining());
        } else if (jLimit == 0) {
            rv.set(MediaPipelineBackendResultCode.AV_MPB_SAMPLE_ADAPTION_FAILURE_SAMPLE_BUFFER_SIZE_ZERO, jLimit, j, sampleBuffer.remaining(), decoderBuffer.remaining());
        } else {
            rv.set(MediaPipelineBackendResultCode.AV_MPB_SUCCESS, jLimit, j, sampleBuffer.remaining(), decoderBuffer.remaining());
        }
    }
}
