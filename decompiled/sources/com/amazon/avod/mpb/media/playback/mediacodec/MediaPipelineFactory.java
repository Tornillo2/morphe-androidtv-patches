package com.amazon.avod.mpb.media.playback.mediacodec;

import android.content.Context;
import com.amazon.avod.mpb.media.playback.avsync.MediaClock;
import com.amazon.avod.mpb.media.playback.pipeline.AsyncMediaPipeline;
import com.amazon.avod.mpb.media.playback.pipeline.MediaPipeline;
import com.amazon.avod.mpb.media.playback.pipeline.MediaPipelineContext;
import com.amazon.avod.mpb.media.playback.pipeline.SyncMediaPipeline;
import com.amazon.avod.mpb.media.playback.source.MediaSource;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaPipelineFactory {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final MediaPipelineFactory instance = new MediaPipelineFactory();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final MediaPipelineFactory getInstance() {
            return MediaPipelineFactory.instance;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }
    }

    @NotNull
    public static final MediaPipelineFactory getInstance() {
        Companion.getClass();
        return instance;
    }

    @NotNull
    public final MediaPipeline newMediaPipeline(@NotNull MediaSource mediaSource, @NotNull Context appContext, @NotNull MediaClock mediaClock, @NotNull MediaPipelineContext mediaPipelineContext, boolean z, @Nullable Integer num, boolean z2, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, @Nullable Integer num2, int i) {
        Intrinsics.checkNotNullParameter(mediaSource, "mediaSource");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(mediaClock, "mediaClock");
        Intrinsics.checkNotNullParameter(mediaPipelineContext, "mediaPipelineContext");
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
        return z2 ? new AsyncMediaPipeline(mediaSource, appContext, mediaClock, mediaPipelineContext, z, num, capabilityDetector, num2, i) : new SyncMediaPipeline(mediaSource, appContext, mediaClock, mediaPipelineContext, z, num, capabilityDetector, num2, i);
    }
}
