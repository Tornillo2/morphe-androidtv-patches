package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.api.callback.LogCallback;
import com.amazon.avod.mpb.api.core.FtvMpbJson;
import com.amazon.avod.mpb.api.core.MediaCodecMediaPipelineBackendApiImpl;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendApiCallbacks;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.query.CodecQueryResult;
import com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance;
import com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class FtvMpbApi {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String LOG_TAG = "FtvMpbApi";

    @NotNull
    public final MediaCodecMediaPipelineBackendApiImpl api;

    @NotNull
    public final AudioFocusManager audioFocusManager;

    @NotNull
    public final MediaPipelineBackendApiCallbacks callbacks;

    @NotNull
    public final FtvMpbInstance.Factory instanceFactory;

    @NotNull
    public final InternalPlaybackSurface.Factory internalSurfaceFactory;

    @NotNull
    public final MediaSessionAdapter mediaSessionAdapter;
    public int nextMpbInstanceId;

    @NotNull
    public final WakeLocker wakeLocker;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AssistedFactory
    public interface Factory {
        @NotNull
        FtvMpbApi create(@NotNull MediaCodecMediaPipelineBackendApiImpl mediaCodecMediaPipelineBackendApiImpl, @NotNull MediaPipelineBackendApiCallbacks mediaPipelineBackendApiCallbacks);
    }

    @AssistedInject
    public FtvMpbApi(@Assisted @NotNull MediaCodecMediaPipelineBackendApiImpl api, @Assisted @NotNull MediaPipelineBackendApiCallbacks callbacks, @NotNull FtvMpbInstance.Factory instanceFactory, @NotNull InternalPlaybackSurface.Factory internalSurfaceFactory, @NotNull WakeLocker wakeLocker, @NotNull AudioFocusManager audioFocusManager, @NotNull MediaSessionAdapter mediaSessionAdapter) {
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        Intrinsics.checkNotNullParameter(instanceFactory, "instanceFactory");
        Intrinsics.checkNotNullParameter(internalSurfaceFactory, "internalSurfaceFactory");
        Intrinsics.checkNotNullParameter(wakeLocker, "wakeLocker");
        Intrinsics.checkNotNullParameter(audioFocusManager, "audioFocusManager");
        Intrinsics.checkNotNullParameter(mediaSessionAdapter, "mediaSessionAdapter");
        this.api = api;
        this.callbacks = callbacks;
        this.instanceFactory = instanceFactory;
        this.internalSurfaceFactory = internalSurfaceFactory;
        this.wakeLocker = wakeLocker;
        this.audioFocusManager = audioFocusManager;
        this.mediaSessionAdapter = mediaSessionAdapter;
    }

    @CalledFromNative
    @NotNull
    public final CodecQueryResult canDecode(@NotNull String attribJson) {
        Intrinsics.checkNotNullParameter(attribJson, "attribJson");
        try {
            return this.api.canDecodeMedia(FtvMpbJson.INSTANCE.buildCodecQuery(attribJson));
        } catch (MediaPipelineBackendException e) {
            MpbLog.i("FtvMpbApi.canDecode error for ".concat(attribJson), e);
            return new CodecQueryResult(false, false, false);
        }
    }

    @CalledFromNative
    @NotNull
    public final FtvMpbInstance createInstance() throws MediaPipelineBackendException {
        MpbLog.i("FtvMpbApi.createInstance " + this.api);
        int i = this.nextMpbInstanceId + 1;
        this.nextMpbInstanceId = i;
        MediaCodecRenderer mediaCodecRendererCreateInstance = this.api.createInstance();
        MediaPipelineCompositeListener mediaPipelineCompositeListener = new MediaPipelineCompositeListener();
        mediaPipelineCompositeListener.addListener(new WakeLockerAdapter(this.wakeLocker));
        mediaPipelineCompositeListener.addListener(this.audioFocusManager);
        mediaPipelineCompositeListener.addListener(this.mediaSessionAdapter);
        return this.instanceFactory.create(this, mediaCodecRendererCreateInstance, mediaPipelineCompositeListener, this.internalSurfaceFactory.create("AvodMpb-" + i));
    }

    @CalledFromNative
    public final int destroy() {
        return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
    }

    public final void destroyInstance(@NotNull MediaCodecRenderer mediaCodecRenderer) {
        Intrinsics.checkNotNullParameter(mediaCodecRenderer, "mediaCodecRenderer");
        this.api.destroyInstance(mediaCodecRenderer);
    }

    @CalledFromNative
    @NotNull
    public final String getApiProperty(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        MpbLog.i("FtvMpbApi.getApiProperty " + this.api + StringUtils.SPACE + key);
        return this.api.getProperty(key);
    }

    @CalledFromNative
    @NotNull
    public final String getCapabilities() {
        return this.api.getCapabilities();
    }

    public final void log(@NotNull LogCallback.LogLevel logLevel, @Nullable Throwable th, @NotNull String format, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(logLevel, "logLevel");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        this.callbacks.logCallback.onLog(logLevel, th, format, Arrays.copyOf(args, args.length));
    }

    @CalledFromNative
    public final int setApiProperty(@NotNull String key, @NotNull String value) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        MpbLog.i("FtvMpbApi.setApiProperty " + this.api + StringUtils.SPACE + key + StringUtils.SPACE + value);
        this.api.setProperty(key, value);
        return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
    }
}
