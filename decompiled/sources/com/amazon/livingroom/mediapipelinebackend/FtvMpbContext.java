package com.amazon.livingroom.mediapipelinebackend;

import android.content.Context;
import android.os.Build;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import com.amazon.avod.mpb.api.callback.LogCallback;
import com.amazon.avod.mpb.api.callback.PropertyChangedCallback;
import com.amazon.avod.mpb.api.core.CapabilitiesProvider;
import com.amazon.avod.mpb.api.core.DevicePropertyProvider;
import com.amazon.avod.mpb.api.core.FailoverManager;
import com.amazon.avod.mpb.api.core.MediaCodecCapabilitiesProvider;
import com.amazon.avod.mpb.api.core.MediaCodecMediaPipelineBackendApiImpl;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendApiCallbacks;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbApi;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.time.DateUtils;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class FtvMpbContext {

    @NotNull
    public static final String AMAZON_MANUFACTURER = "Amazon";

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String LOG_TAG = "FtvMpbContext";

    @NotNull
    public final FtvMpbApi.Factory apiFactory;

    @NotNull
    public final Context applicationContext;

    @NotNull
    public final ApplicationVisibilityMonitor applicationVisibilityMonitor;

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final Lazy failoverManager$delegate;

    @NotNull
    public final DevicePropertiesProviderImpl propertyProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class DevicePropertiesProviderImpl implements DevicePropertyProvider {
        public DevicePropertiesProviderImpl() {
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean decoderBecomingInactiveWorkAround() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.DECODER_INACTIVE_WORKAROUND);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public int getCanaryThreshold() {
            return 1;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public long getCanaryWindowLengthMs() {
            return DateUtils.MILLIS_PER_HOUR;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        @NotNull
        public Set<String> getFailoverHandledErrorSet() {
            return DevicePropertyProvider.DefaultImpls.getFailoverHandledErrorSet(this);
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public int getFailoverThreshold() {
            return 3;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public long getFailoverWindowLengthMs() {
            return 1800000L;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public float getFrameDropDetectionPercentage() {
            return 10.0f;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public float getHfrBurstFrameDropPercentage() {
            return 30.0f;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public long getHfrBurstWindowLengthMs() {
            return 30000L;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public int getHfrBurstWindowThreshold() {
            return 3;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public float getHfrContinualFrameDropPercentage() {
            return 5.0f;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public long getHfrContinualWindowLengthMs() {
            return 30000L;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public int getHfrContinualWindowThreshold() {
            return 10;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public long getHfrFallbackBackoffTimeMs() {
            return 86400000L;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public int getHfrMaxFallbacksBeforeBlock() {
            return 3;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public float getHfrMinFrameRate() {
            return 48.0f;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public int getMaxVideoHeight() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.MAX_VIDEO_HEIGHT);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Number) obj).intValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public int getMaxVideoWidth() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.MAX_VIDEO_WIDTH);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Number) obj).intValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public long getPerfEvalWindowLengthMs() {
            return 30000L;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public int getPerfEvalWindowThreshold() {
            return 40;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isApplicationInBackground() {
            return FtvMpbContext.this.applicationVisibilityMonitor.foregroundSessionId == 0;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isAsyncModeEnabled() {
            if (((Boolean) FtvMpbContext.this.deviceProperties.get(DeviceProperties.FORCE_DISABLE_MEDIA_CODEC_ASYNC_QUEUEING)).booleanValue()) {
                return false;
            }
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.FORCE_ENABLE_MEDIA_CODEC_ASYNC_QUEUEING);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isAv1Enabled() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.SUPPORTS_AV1);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isCanaryEnabled() {
            return true;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isCanaryErrorWildcardEnabled() {
            return true;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isDeferredSurfacePlaybackEnabled() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.ENABLE_DEFERRED_SURFACE_PLAYBACK);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isDolbyHdmiPassthroughAvailable() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.IS_DOLBY_HDMI_PASSTHROUGH_AVAILABLE);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isDolbyVisionHDREnabled() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.SUPPORTS_DOLBY_VISION);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isFailoverErrorWildcardEnabled() {
            return false;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isFrameDropEvaluatorEnabled() {
            return true;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isHandleMidstreamSurfaceDestroyEnabled() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.IS_HANDLE_MIDSTREAM_SURFACE_DESTROY_ENABLED);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isHdr10Enabled() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.SUPPORTS_HDR10);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isHevcEnabled() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.SUPPORTS_HEVC);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isHfrEvaluatorEnabled() {
            return true;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isIntraChunkSeekingSupported() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.SUPPORTS_INTRA_CHUNK_SEEKING);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isNewGetBufferApiEnabled() {
            return !"Amazon".equalsIgnoreCase(Build.MANUFACTURER) || Build.VERSION.SDK_INT >= 28;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isOpticalOutputEnabled() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.IS_OPTICAL_OUTPUT_ENABLED);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isPlayerStackFailoverEnabled() {
            return true;
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isTunnelModeEnabled() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.TUNNELED_VIDEO_PLAYBACK_ENABLED);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isTunnelModeOverBtEnabled() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.TUNNELED_VIDEO_PLAYBACK_OVER_BT_ENABLED);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean isVerboseAvSyncLoggingEnabled() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.VERBOSE_AV_SYNC_LOGGING_ENABLED);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }

        @Override // com.amazon.avod.mpb.api.core.DevicePropertyProvider
        public boolean supportsSurroundSound() {
            Object obj = FtvMpbContext.this.deviceProperties.get(DeviceProperties.SUPPORTS_SURROUND_SOUND);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            return ((Boolean) obj).booleanValue();
        }
    }

    @Inject
    public FtvMpbContext(@ApplicationContext @NotNull Context applicationContext, @NotNull FtvMpbApi.Factory apiFactory, @NotNull DeviceProperties deviceProperties, @NotNull ApplicationVisibilityMonitor applicationVisibilityMonitor) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        Intrinsics.checkNotNullParameter(apiFactory, "apiFactory");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(applicationVisibilityMonitor, "applicationVisibilityMonitor");
        this.applicationContext = applicationContext;
        this.apiFactory = apiFactory;
        this.deviceProperties = deviceProperties;
        this.applicationVisibilityMonitor = applicationVisibilityMonitor;
        this.propertyProvider = new DevicePropertiesProviderImpl();
        this.failoverManager$delegate = LazyKt__LazyJVMKt.lazy(new FtvMpbContext$$ExternalSyntheticLambda0());
    }

    public static final void createApi$lambda$1(long j, long j2, String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        FtvMpbNativeBridge.Companion.onApiPropertyChanged(j, j2, key, value);
    }

    @CalledFromNative
    @NotNull
    public final ResultHolder<FtvMpbApi> createApi(long j, final long j2, final long j3) {
        StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("FtvMpbContext.createApi (", j, ", ");
        sbM.append(j2);
        sbM.append(", ");
        sbM.append(j3);
        sbM.append(")");
        MpbLog.i(sbM.toString());
        FtvMpbApiLogCallbackImpl ftvMpbApiLogCallbackImpl = new FtvMpbApiLogCallbackImpl(j, j3);
        MediaPipelineBackendApiCallbacks mediaPipelineBackendApiCallbacks = new MediaPipelineBackendApiCallbacks(ftvMpbApiLogCallbackImpl, new PropertyChangedCallback() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbContext$$ExternalSyntheticLambda1
            @Override // com.amazon.avod.mpb.api.callback.PropertyChangedCallback
            public final void onPropertyChanged(String str, String str2) {
                FtvMpbContext.createApi$lambda$1(j2, j3, str, str2);
            }
        });
        ftvMpbApiLogCallbackImpl.onLog(LogCallback.LogLevel.LEVEL_INFO, null, "Creating FtvMpb API", new Object[0]);
        MediaCodecMediaPipelineBackendApiImpl.MediaPipelineBackendContextImpl mediaPipelineBackendContextImpl = new MediaCodecMediaPipelineBackendApiImpl.MediaPipelineBackendContextImpl(this.applicationContext);
        Object obj = this.deviceProperties.get(DeviceProperties.SUPPORTS_INTRA_CHUNK_SEEKING);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        Object obj2 = this.deviceProperties.get(DeviceProperties.SUPPORTS_FILMMAKER_MODE);
        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        Object obj3 = this.deviceProperties.get(DeviceProperties.SUPPORTS_VARIABLE_ASPECT_RATIO);
        Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
        boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
        Object obj4 = this.deviceProperties.get(DeviceProperties.MAX_MPB_INSTANCES);
        Intrinsics.checkNotNullExpressionValue(obj4, "get(...)");
        return ResultHolder.fromResult(this.apiFactory.create(MediaCodecMediaPipelineBackendApiImpl.Factory.create(mediaPipelineBackendApiCallbacks, mediaPipelineBackendContextImpl, (CapabilitiesProvider) new MediaCodecCapabilitiesProvider(zBooleanValue, zBooleanValue2, zBooleanValue3, ((Number) obj4).intValue()), (DevicePropertyProvider) this.propertyProvider, getFailoverManager()), mediaPipelineBackendApiCallbacks));
    }

    @NotNull
    public final FailoverManager getFailoverManager() {
        return (FailoverManager) this.failoverManager$delegate.getValue();
    }
}
