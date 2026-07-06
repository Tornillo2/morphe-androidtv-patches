package com.amazon.avod.mpb.media.playback.pipeline;

import android.content.Context;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.media.playback.MimeTypes;
import com.amazon.avod.mpb.media.playback.render.AudioRenderer;
import com.amazon.avod.mpb.media.playback.render.MediaRenderer;
import com.amazon.avod.mpb.media.playback.render.VideoRenderer;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaRendererFactory {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final MediaRendererFactory instance = new MediaRendererFactory();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final MediaRendererFactory getInstance() {
            return MediaRendererFactory.instance;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }
    }

    @NotNull
    public static final MediaRendererFactory getInstance() {
        Companion.getClass();
        return instance;
    }

    @NotNull
    public final MediaRenderer newMediaRenderer(@NotNull String mimeType, @NotNull Context appContext, @NotNull MediaPipelineContext mediaPipelineContext, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, boolean z) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
        if (MimeTypes.isAudio(mimeType)) {
            return new AudioRenderer(mediaPipelineContext, capabilityDetector, z);
        }
        if (MimeTypes.isVideo(mimeType)) {
            return new VideoRenderer(appContext, mediaPipelineContext);
        }
        throw new MediaPipelineBackendException("Unsupported mimeType ".concat(mimeType), MediaPipelineBackendResultCode.AV_MPB_INTERNAL_ERROR);
    }
}
