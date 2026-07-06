package com.amazon.livingroom.mediapipelinebackend;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import android.view.Surface;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import com.amazon.avod.mpb.api.callback.BufferUnderrunCallback;
import com.amazon.avod.mpb.api.callback.EndOfStreamCallback;
import com.amazon.avod.mpb.api.callback.ErrorCallback;
import com.amazon.avod.mpb.api.callback.LogCallback;
import com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks;
import com.amazon.avod.mpb.api.callback.PlaybackStartedCallback;
import com.amazon.avod.mpb.api.callback.PropertyChangedCallback;
import com.amazon.avod.mpb.api.callback.ReadyToPlayCallback;
import com.amazon.avod.mpb.api.callback.SampleAddReferenceCallback;
import com.amazon.avod.mpb.api.callback.SampleRemoveReferenceCallback;
import com.amazon.avod.mpb.api.callback.SurfaceResizerCallback;
import com.amazon.avod.mpb.api.core.MediaPipelineBackend;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.TrackConfiguration;
import com.amazon.avod.mpb.api.sample.AudioCodecType;
import com.amazon.avod.mpb.api.sample.AudioMetadata;
import com.amazon.avod.mpb.api.sample.AudioSample;
import com.amazon.avod.mpb.api.sample.DiagnosticInfo;
import com.amazon.avod.mpb.api.sample.EncryptionInfo;
import com.amazon.avod.mpb.api.sample.VideoCodecType;
import com.amazon.avod.mpb.api.sample.VideoMetadata;
import com.amazon.avod.mpb.api.sample.VideoSample;
import com.amazon.avod.mpb.media.drm.AndroidDrmSystem;
import com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import com.amazon.livingroom.mediapipelinebackend.ExternalPlaybackSurfaceRegistry;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance;
import com.amazon.livingroom.mediapipelinebackend.PlaybackSurface;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nFtvMpbInstance.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FtvMpbInstance.kt\ncom/amazon/livingroom/mediapipelinebackend/FtvMpbInstance\n*L\n1#1,472:1\n338#1,17:473\n338#1,17:490\n338#1,17:507\n338#1,17:524\n338#1,17:541\n338#1,17:558\n338#1,17:575\n338#1,17:592\n338#1,17:609\n338#1,17:626\n338#1,17:643\n338#1,17:660\n338#1,17:677\n338#1,17:694\n338#1,17:711\n338#1,17:728\n*S KotlinDebug\n*F\n+ 1 FtvMpbInstance.kt\ncom/amazon/livingroom/mediapipelinebackend/FtvMpbInstance\n*L\n66#1:473,17\n74#1:490,17\n92#1:507,17\n103#1:524,17\n120#1:541,17\n132#1:558,17\n138#1:575,17\n145#1:592,17\n162#1:609,17\n169#1:626,17\n176#1:643,17\n183#1:660,17\n201#1:677,17\n236#1:694,17\n275#1:711,17\n281#1:728,17\n*E\n"})
public final class FtvMpbInstance {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String LOG_TAG = "FtvMpbInstance";

    @NotNull
    public final FtvMpbApi api;

    @NotNull
    public final ApplicationVisibilityMonitor applicationVisibilityMonitor;

    @NotNull
    public final PlaybackSurface defaultSurfaceRef;

    @NotNull
    public final DisplayModeManager displayModeManager;

    @NotNull
    public final ExternalPlaybackSurfaceRegistry externalPlaybackSurfaceRegistry;
    public int foregroundSessionAtInitTime;

    @NotNull
    public final MediaPipelineListener listener;

    @NotNull
    public final MediaCodecRenderer mediaCodecRenderer;

    @NotNull
    public final PlaybackSurface.Listener onSurfaceChanged;
    public boolean previouslyShutdown;

    @NotNull
    public PlaybackSurface surfaceRef;
    public boolean surfaceRefBound;

    @NotNull
    public final SurfaceResizerCallbackImpl surfaceResizerCallback;

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
        FtvMpbInstance create(@NotNull FtvMpbApi ftvMpbApi, @NotNull MediaCodecRenderer mediaCodecRenderer, @NotNull MediaPipelineListener mediaPipelineListener, @NotNull PlaybackSurface playbackSurface);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class FtvMpbCallbacksImpl implements MediaPipelineBackendCallbacks {

        @NotNull
        public final BufferUnderrunCallback bufferUnderrunCallback;

        @NotNull
        public final EndOfStreamCallback endOfStreamCallback;

        @NotNull
        public final ErrorCallback errorCallback;

        @NotNull
        public final FtvMpbNativeBridge nativeBridge;

        @NotNull
        public final PlaybackStartedCallback playbackStartedCallback;

        @NotNull
        public final PropertyChangedCallback propertyChangedCallback;

        @NotNull
        public final ReadyToPlayCallback readyToPlayCallback;

        @NotNull
        public final SampleAddReferenceCallback sampleAddReferenceCallback;

        @NotNull
        public final SampleRemoveReferenceCallback sampleRemoveReferenceCallback;
        public final /* synthetic */ FtvMpbInstance this$0;

        public FtvMpbCallbacksImpl(@NotNull final FtvMpbInstance ftvMpbInstance, FtvMpbNativeBridge nativeBridge) {
            Intrinsics.checkNotNullParameter(nativeBridge, "nativeBridge");
            this.this$0 = ftvMpbInstance;
            this.nativeBridge = nativeBridge;
            this.bufferUnderrunCallback = new BufferUnderrunCallback() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance$FtvMpbCallbacksImpl$$ExternalSyntheticLambda0
                @Override // com.amazon.avod.mpb.api.callback.BufferUnderrunCallback
                public final void onBufferUnderrun() {
                    FtvMpbInstance.FtvMpbCallbacksImpl.bufferUnderrunCallback$lambda$0(this.f$0, ftvMpbInstance);
                }
            };
            this.endOfStreamCallback = new EndOfStreamCallback() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance$FtvMpbCallbacksImpl$$ExternalSyntheticLambda1
                @Override // com.amazon.avod.mpb.api.callback.EndOfStreamCallback
                public final void onEndOfStream() {
                    FtvMpbInstance.FtvMpbCallbacksImpl.endOfStreamCallback$lambda$1(this.f$0);
                }
            };
            this.errorCallback = new ErrorCallback() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance$FtvMpbCallbacksImpl$$ExternalSyntheticLambda2
                @Override // com.amazon.avod.mpb.api.callback.ErrorCallback
                public final void onError(String str, MediaPipelineBackendResultCode mediaPipelineBackendResultCode, String str2, ErrorCallback.ErrorSeverity errorSeverity) {
                    FtvMpbInstance.FtvMpbCallbacksImpl.errorCallback$lambda$3(ftvMpbInstance, this, str, mediaPipelineBackendResultCode, str2, errorSeverity);
                }
            };
            this.playbackStartedCallback = new PlaybackStartedCallback() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance$FtvMpbCallbacksImpl$$ExternalSyntheticLambda3
                @Override // com.amazon.avod.mpb.api.callback.PlaybackStartedCallback
                public final void onPlaybackStarted() {
                    FtvMpbInstance.FtvMpbCallbacksImpl.playbackStartedCallback$lambda$4(this.f$0);
                }
            };
            this.propertyChangedCallback = new PropertyChangedCallback() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance$FtvMpbCallbacksImpl$$ExternalSyntheticLambda4
                @Override // com.amazon.avod.mpb.api.callback.PropertyChangedCallback
                public final void onPropertyChanged(String str, String str2) {
                    FtvMpbInstance.FtvMpbCallbacksImpl.propertyChangedCallback$lambda$5(this.f$0, str, str2);
                }
            };
            this.readyToPlayCallback = new ReadyToPlayCallback() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance$FtvMpbCallbacksImpl$$ExternalSyntheticLambda5
                @Override // com.amazon.avod.mpb.api.callback.ReadyToPlayCallback
                public final void onReadyToPlay() {
                    FtvMpbInstance.FtvMpbCallbacksImpl.readyToPlayCallback$lambda$6(this.f$0);
                }
            };
            this.sampleAddReferenceCallback = new SampleAddReferenceCallback() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance$FtvMpbCallbacksImpl$$ExternalSyntheticLambda6
                @Override // com.amazon.avod.mpb.api.callback.SampleAddReferenceCallback
                public final void onSampleAddReference(ByteBuffer byteBuffer) {
                    FtvMpbInstance.FtvMpbCallbacksImpl.sampleAddReferenceCallback$lambda$7(this.f$0, byteBuffer);
                }
            };
            this.sampleRemoveReferenceCallback = new SampleRemoveReferenceCallback() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance$FtvMpbCallbacksImpl$$ExternalSyntheticLambda7
                @Override // com.amazon.avod.mpb.api.callback.SampleRemoveReferenceCallback
                public final void onSampleRemoveReference(ByteBuffer byteBuffer) {
                    FtvMpbInstance.FtvMpbCallbacksImpl.sampleRemoveReferenceCallback$lambda$8(this.f$0, byteBuffer);
                }
            };
        }

        public static final void bufferUnderrunCallback$lambda$0(FtvMpbCallbacksImpl ftvMpbCallbacksImpl, FtvMpbInstance ftvMpbInstance) {
            ftvMpbCallbacksImpl.nativeBridge.bufferUnderrun();
            ftvMpbInstance.listener.onBufferUnderrun();
        }

        public static final void endOfStreamCallback$lambda$1(FtvMpbCallbacksImpl ftvMpbCallbacksImpl) {
            ftvMpbCallbacksImpl.nativeBridge.endOfStream();
        }

        public static final void errorCallback$lambda$3(FtvMpbInstance ftvMpbInstance, FtvMpbCallbacksImpl ftvMpbCallbacksImpl, String description, MediaPipelineBackendResultCode errorCode, String componentName, ErrorCallback.ErrorSeverity severity) {
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage;
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            Intrinsics.checkNotNullParameter(componentName, "componentName");
            Intrinsics.checkNotNullParameter(severity, "severity");
            boolean z = severity == ErrorCallback.ErrorSeverity.SEV_FATAL;
            if (z && (suppressionErrorCodeMessage = ftvMpbInstance.getSuppressionErrorCodeMessage()) != null) {
                errorCode = suppressionErrorCodeMessage.first;
                description = AbstractResolvableFuture$$ExternalSyntheticOutline1.m(suppressionErrorCodeMessage.second, ": ", description);
            }
            ftvMpbInstance.api.log(LogCallback.LogLevel.LEVEL_ERROR, null, "Reporting asynchronous error code=%s isFatal=%b: %s", errorCode.name(), Boolean.valueOf(z), description);
            ftvMpbCallbacksImpl.nativeBridge.onError(description, errorCode.resultCode, componentName, z);
            ftvMpbInstance.listener.onError(errorCode.resultCode, description, z, null);
        }

        public static final void playbackStartedCallback$lambda$4(FtvMpbCallbacksImpl ftvMpbCallbacksImpl) {
            ftvMpbCallbacksImpl.nativeBridge.onPlaybackStarted();
        }

        public static final void propertyChangedCallback$lambda$5(FtvMpbCallbacksImpl ftvMpbCallbacksImpl, String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            ftvMpbCallbacksImpl.nativeBridge.onPropertyChanged(key, value);
        }

        public static final void readyToPlayCallback$lambda$6(FtvMpbCallbacksImpl ftvMpbCallbacksImpl) {
            ftvMpbCallbacksImpl.nativeBridge.onReadyToPlay();
        }

        public static final void sampleAddReferenceCallback$lambda$7(FtvMpbCallbacksImpl ftvMpbCallbacksImpl, ByteBuffer sampleData) {
            Intrinsics.checkNotNullParameter(sampleData, "sampleData");
            ftvMpbCallbacksImpl.nativeBridge.onSampleAddReference(sampleData);
        }

        public static final void sampleRemoveReferenceCallback$lambda$8(FtvMpbCallbacksImpl ftvMpbCallbacksImpl, ByteBuffer sampleData) {
            Intrinsics.checkNotNullParameter(sampleData, "sampleData");
            ftvMpbCallbacksImpl.nativeBridge.onSampleRemoveReference(sampleData);
        }

        @Override // com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks
        @NotNull
        public BufferUnderrunCallback getBufferUnderrunCallback() {
            return this.bufferUnderrunCallback;
        }

        @Override // com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks
        @NotNull
        public EndOfStreamCallback getEndOfStreamCallback() {
            return this.endOfStreamCallback;
        }

        @Override // com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks
        @NotNull
        public ErrorCallback getErrorCallback() {
            return this.errorCallback;
        }

        @Override // com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks
        @NotNull
        public PlaybackStartedCallback getPlaybackStartedCallback() {
            return this.playbackStartedCallback;
        }

        @Override // com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks
        @NotNull
        public PropertyChangedCallback getPropertyChangedCallback() {
            return this.propertyChangedCallback;
        }

        @Override // com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks
        @NotNull
        public ReadyToPlayCallback getReadyToPlayCallback() {
            return this.readyToPlayCallback;
        }

        @Override // com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks
        @NotNull
        public SampleAddReferenceCallback getSampleAddReferenceCallback() {
            return this.sampleAddReferenceCallback;
        }

        @Override // com.amazon.avod.mpb.api.callback.MediaPipelineBackendCallbacks
        @NotNull
        public SampleRemoveReferenceCallback getSampleRemoveReferenceCallback() {
            return this.sampleRemoveReferenceCallback;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstancePropertyKeys {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ InstancePropertyKeys[] $VALUES;
        public static final InstancePropertyKeys EXTERNAL_SURFACE_KEY;

        @NotNull
        public final String value;

        public static final /* synthetic */ InstancePropertyKeys[] $values() {
            return new InstancePropertyKeys[]{EXTERNAL_SURFACE_KEY};
        }

        static {
            InstancePropertyKeys instancePropertyKeys = new InstancePropertyKeys("EXTERNAL_SURFACE_KEY", 0, "externalSurfaceKey");
            EXTERNAL_SURFACE_KEY = instancePropertyKeys;
            InstancePropertyKeys[] instancePropertyKeysArr = {instancePropertyKeys};
            $VALUES = instancePropertyKeysArr;
            $ENTRIES = EnumEntriesKt.enumEntries(instancePropertyKeysArr);
        }

        public InstancePropertyKeys(String str, int i, String str2) {
            this.value = str2;
        }

        @NotNull
        public static EnumEntries<InstancePropertyKeys> getEntries() {
            return $ENTRIES;
        }

        public static InstancePropertyKeys valueOf(String str) {
            return (InstancePropertyKeys) Enum.valueOf(InstancePropertyKeys.class, str);
        }

        public static InstancePropertyKeys[] values() {
            return (InstancePropertyKeys[]) $VALUES.clone();
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SurfaceResizerCallbackImpl implements SurfaceResizerCallback {
        public SurfaceResizerCallbackImpl() {
        }

        @Override // com.amazon.avod.mpb.api.callback.SurfaceResizerCallback
        public void commitPendingAspectRatio() {
            FtvMpbInstance ftvMpbInstance = FtvMpbInstance.this;
            synchronized (ftvMpbInstance) {
                MpbLog.i("FtvMpbInstance SurfaceResizerCallbackImpl.commitPendingAspectRatio()");
                ftvMpbInstance.surfaceRef.commitPendingAspectRatio();
            }
        }

        @Override // com.amazon.avod.mpb.api.callback.SurfaceResizerCallback
        public void resetViewport() {
            FtvMpbInstance ftvMpbInstance = FtvMpbInstance.this;
            synchronized (ftvMpbInstance) {
                MpbLog.i("FtvMpbInstance SurfaceResizerCallbackImpl.resetViewport");
                ftvMpbInstance.surfaceRef.resetViewport();
            }
        }

        @Override // com.amazon.avod.mpb.api.callback.SurfaceResizerCallback
        public void setPendingAspectRatio(float f) {
            FtvMpbInstance ftvMpbInstance = FtvMpbInstance.this;
            synchronized (ftvMpbInstance) {
                MpbLog.i("FtvMpbInstance SurfaceResizerCallbackImpl.setAspectRatio(" + f + ")");
                ftvMpbInstance.surfaceRef.setPendingAspectRatio(f);
            }
        }

        @Override // com.amazon.avod.mpb.api.callback.SurfaceResizerCallback
        public void setViewport(int i, int i2, int i3, int i4) {
            FtvMpbInstance ftvMpbInstance = FtvMpbInstance.this;
            synchronized (ftvMpbInstance) {
                MpbLog.i("FtvMpbInstance SurfaceResizerCallbackImpl.setViewport(left=" + i + " top=" + i2 + " width=" + i3 + " height=" + i4 + ")");
                ftvMpbInstance.surfaceRef.setViewport(i, i2, i3, i4);
            }
        }
    }

    @AssistedInject
    public FtvMpbInstance(@Assisted @NotNull FtvMpbApi api, @Assisted @NotNull MediaCodecRenderer mediaCodecRenderer, @Assisted @NotNull MediaPipelineListener listener, @Assisted @NotNull PlaybackSurface defaultSurfaceRef, @NotNull DisplayModeManager displayModeManager, @NotNull ExternalPlaybackSurfaceRegistry externalPlaybackSurfaceRegistry, @NotNull ApplicationVisibilityMonitor applicationVisibilityMonitor) {
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(mediaCodecRenderer, "mediaCodecRenderer");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(defaultSurfaceRef, "defaultSurfaceRef");
        Intrinsics.checkNotNullParameter(displayModeManager, "displayModeManager");
        Intrinsics.checkNotNullParameter(externalPlaybackSurfaceRegistry, "externalPlaybackSurfaceRegistry");
        Intrinsics.checkNotNullParameter(applicationVisibilityMonitor, "applicationVisibilityMonitor");
        this.api = api;
        this.mediaCodecRenderer = mediaCodecRenderer;
        this.listener = listener;
        this.defaultSurfaceRef = defaultSurfaceRef;
        this.displayModeManager = displayModeManager;
        this.externalPlaybackSurfaceRegistry = externalPlaybackSurfaceRegistry;
        this.applicationVisibilityMonitor = applicationVisibilityMonitor;
        this.surfaceResizerCallback = new SurfaceResizerCallbackImpl();
        this.surfaceRef = defaultSurfaceRef;
        this.onSurfaceChanged = new PlaybackSurface.Listener() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance$$ExternalSyntheticLambda0
            @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface.Listener
            public final void onSurfaceChanged(Surface surface) {
                FtvMpbInstance.onSurfaceChanged$lambda$19(this.f$0, surface);
            }
        };
    }

    public static final void onSurfaceChanged$lambda$19(FtvMpbInstance ftvMpbInstance, Surface surface) {
        synchronized (ftvMpbInstance) {
            if (ftvMpbInstance.surfaceRefBound) {
                ftvMpbInstance.mediaCodecRenderer.setSurface(surface);
            }
        }
    }

    public final EncryptionInfo createEncryptionInfo(boolean z, byte[] bArr, byte[] bArr2, int[] iArr, int[] iArr2, String str) {
        if (!z) {
            return null;
        }
        Intrinsics.checkNotNull(bArr);
        Intrinsics.checkNotNull(bArr2);
        Intrinsics.checkNotNull(iArr);
        Intrinsics.checkNotNull(iArr2);
        Intrinsics.checkNotNull(str);
        return new EncryptionInfo(bArr, bArr2, iArr, iArr2, str);
    }

    @CalledFromNative
    public final int destroy() throws MediaPipelineBackendException {
        try {
            MpbLog.i("FtvMpbInstance.destroy " + this.mediaCodecRenderer);
            this.surfaceRef.clearListener(this.onSurfaceChanged);
            this.defaultSurfaceRef.close();
            this.api.destroyInstance(this.mediaCodecRenderer);
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    @NotNull
    public final DiagnosticInfo getDiagnosticInfo() {
        DiagnosticInfo diagnosticInfo = this.mediaCodecRenderer.getDiagnosticInfo();
        Intrinsics.checkNotNullExpressionValue(diagnosticInfo, "<get-diagnosticInfo>(...)");
        return diagnosticInfo;
    }

    @CalledFromNative
    public final long getPlaybackTime() {
        long playbackTimeUs = this.mediaCodecRenderer.getPlaybackTimeUs();
        this.listener.onPlaybackPositionUpdate(TimeUnit.MICROSECONDS.toMillis(playbackTimeUs));
        return playbackTimeUs;
    }

    @CalledFromNative
    @NotNull
    public final String getProperty(@NotNull String key) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(key, "key");
        MpbLog.i("FtvMpbInstance.setProperty " + this.mediaCodecRenderer + StringUtils.SPACE + key);
        String property = this.mediaCodecRenderer.getProperty(key);
        return property == null ? "" : property;
    }

    public final Pair<MediaPipelineBackendResultCode, String> getSuppressionErrorCodeMessage() {
        int i = this.applicationVisibilityMonitor.foregroundSessionId;
        boolean z = (i == 0 && Intrinsics.areEqual(this.surfaceRef, this.defaultSurfaceRef)) ? false : true;
        boolean z2 = i != this.foregroundSessionAtInitTime && Intrinsics.areEqual(this.surfaceRef, this.defaultSurfaceRef);
        if (z) {
            if (!z2) {
                return null;
            }
            MediaPipelineBackendResultCode mediaPipelineBackendResultCode = MediaPipelineBackendResultCode.AV_MPB_ERROR_AFTER_LIFECYCLE_TRANSITION;
            int i2 = this.foregroundSessionAtInitTime;
            PlaybackSurface playbackSurface = this.surfaceRef;
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("Error after the app was in the background fsid=", i, " fsidInit=", i2, " surfaceRef=");
            sbM.append(playbackSurface);
            return new Pair<>(mediaPipelineBackendResultCode, sbM.toString());
        }
        return new Pair<>(MediaPipelineBackendResultCode.AV_MPB_INVALID_BACKGROUND_OPERATION, "Background error suppressed fsidInit=" + this.foregroundSessionAtInitTime + " surfaceRef=" + this.surfaceRef);
    }

    public final int handleBackgroundErrors(Function0<Unit> function0) throws MediaPipelineBackendException {
        try {
            function0.invoke();
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    public final int init(long j) throws MediaPipelineBackendException {
        try {
            MpbLog.i("FtvMpbInstance.init " + this.mediaCodecRenderer);
            this.foregroundSessionAtInitTime = this.applicationVisibilityMonitor.foregroundSessionId;
            MediaCodecRenderer mediaCodecRenderer = this.mediaCodecRenderer;
            FtvMpbNativeBridge.Companion.getClass();
            mediaCodecRenderer.registerCallbacks(new FtvMpbCallbacksImpl(this, new FtvMpbNativeBridge(j)));
            mediaCodecRenderer.init(new MediaPipelineBackend.InitConfig(SetsKt__SetsJVMKt.setOf(TrackConfiguration.AV)), this.surfaceResizerCallback, this.displayModeManager);
            this.listener.init();
            if (this.previouslyShutdown) {
                this.surfaceRef.recreateSurfaceView();
            }
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    public final int onAudioAccessUnit(@NotNull ByteBuffer data, long j, long j2, long j3, boolean z, @Nullable byte[] bArr, @Nullable byte[] bArr2, @Nullable int[] iArr, @Nullable int[] iArr2, @Nullable String str) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            this.mediaCodecRenderer.audioOnSample(new AudioSample(data, j, j2, j3, z, createEncryptionInfo(z, bArr, bArr2, iArr, iArr2, str)));
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            MediaPipelineBackendException mediaPipelineBackendException = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                mediaPipelineBackendException = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, mediaPipelineBackendException);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, mediaPipelineBackendException, "Throwing MPB exception errorCode=%s: %s", mediaPipelineBackendException.resultCode.name(), mediaPipelineBackendException.getMessage());
            throw mediaPipelineBackendException;
        }
    }

    @CalledFromNative
    public final int onAudioMetadata(@NotNull String audioCodecType, int i, int i2) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(audioCodecType, "audioCodecType");
        try {
            MpbLog.i("FtvMpbInstance.onAudioMetadata " + this.mediaCodecRenderer + StringUtils.SPACE + audioCodecType + StringUtils.SPACE + i + StringUtils.SPACE + i2);
            this.mediaCodecRenderer.audioOnMetadata(new AudioMetadata(AudioCodecType.valueOf(audioCodecType), i, i2));
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    public final int onAudioStreamFinish() throws MediaPipelineBackendException {
        try {
            MpbLog.i("FtvMpbInstance.onAudioStreamFinish " + this.mediaCodecRenderer);
            this.mediaCodecRenderer.audioOnStreamFinish();
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    public final int onVideoAccessUnit(@NotNull ByteBuffer data, long j, long j2, long j3, boolean z, int i, int i2, boolean z2, @Nullable byte[] bArr, @Nullable byte[] bArr2, @Nullable int[] iArr, @Nullable int[] iArr2, @Nullable String str) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            this.mediaCodecRenderer.videoOnSample(new VideoSample(data, j, j2, j3, z, createEncryptionInfo(z, bArr, bArr2, iArr, iArr2, str), i, i2, z2));
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            MediaPipelineBackendException mediaPipelineBackendException = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                mediaPipelineBackendException = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, mediaPipelineBackendException);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, mediaPipelineBackendException, "Throwing MPB exception errorCode=%s: %s", mediaPipelineBackendException.resultCode.name(), mediaPipelineBackendException.getMessage());
            throw mediaPipelineBackendException;
        }
    }

    @CalledFromNative
    public final int onVideoMetadata(int i, int i2, @NotNull String videoCodecType, double d) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(videoCodecType, "videoCodecType");
        try {
            MpbLog.i("FtvMpbInstance.onVideoMetadata " + this.mediaCodecRenderer + StringUtils.SPACE + i + StringUtils.SPACE + i2 + StringUtils.SPACE + videoCodecType + StringUtils.SPACE + d);
            synchronized (this) {
                try {
                    if (!this.surfaceRefBound) {
                        Surface listener = this.surfaceRef.setListener(this.onSurfaceChanged);
                        if (listener != null) {
                            this.mediaCodecRenderer.setSurface(listener);
                        }
                        this.surfaceRefBound = true;
                    }
                } finally {
                }
            }
            this.mediaCodecRenderer.videoOnMetadata(new VideoMetadata(i, i2, VideoCodecType.valueOf(videoCodecType), d));
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            MediaPipelineBackendException mediaPipelineBackendException = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                mediaPipelineBackendException = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, mediaPipelineBackendException);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, mediaPipelineBackendException, "Throwing MPB exception errorCode=%s: %s", mediaPipelineBackendException.resultCode.name(), mediaPipelineBackendException.getMessage());
            throw mediaPipelineBackendException;
        }
    }

    @CalledFromNative
    public final int onVideoStreamFinish() throws MediaPipelineBackendException {
        try {
            MpbLog.i("FtvMpbInstance.onVideoStreamFinish " + this.mediaCodecRenderer);
            this.mediaCodecRenderer.videoOnStreamFinish();
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    public final int pause() throws MediaPipelineBackendException {
        try {
            MpbLog.i("FtvMpbInstance.pause " + this.mediaCodecRenderer);
            this.mediaCodecRenderer.pause();
            this.listener.pause();
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    public final int play() throws MediaPipelineBackendException {
        try {
            MpbLog.i("FtvMpbInstance.play " + this.mediaCodecRenderer);
            this.mediaCodecRenderer.play();
            this.listener.play();
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    public final int seek(long j) throws MediaPipelineBackendException {
        try {
            MpbLog.i("FtvMpbInstance.seek " + this.mediaCodecRenderer + StringUtils.SPACE + j + " ms");
            this.mediaCodecRenderer.seek(j);
            this.listener.seek(j);
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    public final int setDrmSystem(@NotNull AndroidDrmSystem drmSystem) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        try {
            MpbLog.i("FtvMpbInstance.setDrmSystem " + this.mediaCodecRenderer + StringUtils.SPACE + drmSystem);
            this.mediaCodecRenderer.setDrmSystem(drmSystem);
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    public final int setProperty(@NotNull String key, @NotNull String value) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            MpbLog.i("FtvMpbInstance.setProperty " + this.mediaCodecRenderer + StringUtils.SPACE + key + StringUtils.SPACE + value);
            if (key.equals(InstancePropertyKeys.EXTERNAL_SURFACE_KEY.value)) {
                setSurfaceRef(value);
            } else {
                this.mediaCodecRenderer.setProperty(key, value);
            }
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    public final void setSurfaceRef(String str) throws MediaPipelineBackendException {
        if (str.length() == 0) {
            MpbLog.t("Resetting to default surfaceRef");
            setSurfaceRef(this.defaultSurfaceRef);
            return;
        }
        MpbLog.t("Attempting to set player surface to " + str);
        ExternalPlaybackSurfaceRegistry.ExternalPlaybackSurface externalPlaybackSurface = this.externalPlaybackSurfaceRegistry.get(str);
        if (externalPlaybackSurface == null) {
            throw new MediaPipelineBackendException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Attempted to set player surface to ", str, " but that surface is not known"), MediaPipelineBackendResultCode.AV_MPB_EXTERNAL_SURFACE_KEY_INVALID);
        }
        MpbLog.t("Found player surface " + externalPlaybackSurface);
        setSurfaceRef(externalPlaybackSurface);
    }

    @CalledFromNative
    public final int setVideoOutputPosition(int i, int i2, int i3, int i4, int i5) throws MediaPipelineBackendException {
        try {
            MpbLog.i("FtvMpbInstance.setVideoOutputPosition " + this.mediaCodecRenderer + StringUtils.SPACE + i + StringUtils.SPACE + i2 + StringUtils.SPACE + i3 + StringUtils.SPACE + i4 + StringUtils.SPACE + i5);
            this.mediaCodecRenderer.setVideoOutputPosition(i, i2, i3, i4, i5);
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            MediaPipelineBackendException mediaPipelineBackendException = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                mediaPipelineBackendException = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, mediaPipelineBackendException);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, mediaPipelineBackendException, "Throwing MPB exception errorCode=%s: %s", mediaPipelineBackendException.resultCode.name(), mediaPipelineBackendException.getMessage());
            throw mediaPipelineBackendException;
        }
    }

    @CalledFromNative
    public final int shutdown() throws MediaPipelineBackendException {
        try {
            MpbLog.i("FtvMpbInstance.shutdown " + this.mediaCodecRenderer);
            this.mediaCodecRenderer.shutdown();
            this.listener.shutdown();
            this.surfaceRef.clearListener(this.onSurfaceChanged);
            this.surfaceRefBound = false;
            this.foregroundSessionAtInitTime = 0;
            this.previouslyShutdown = true;
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    @CalledFromNative
    public final int stop() throws MediaPipelineBackendException {
        try {
            MpbLog.i("FtvMpbInstance.stop " + this.mediaCodecRenderer);
            this.mediaCodecRenderer.stop();
            this.listener.stop();
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            this.api.log(LogCallback.LogLevel.LEVEL_ERROR, e, "Throwing MPB exception errorCode=%s: %s", e.resultCode.name(), e.getMessage());
            throw e;
        }
    }

    public final synchronized void setSurfaceRef(PlaybackSurface playbackSurface) {
        Surface listener;
        try {
            if (Intrinsics.areEqual(this.surfaceRef, playbackSurface)) {
                return;
            }
            boolean z = this.surfaceRef.getSurface() != null;
            this.surfaceRef.clearListener(this.onSurfaceChanged);
            this.surfaceRef = playbackSurface;
            if (this.surfaceRefBound && ((listener = playbackSurface.setListener(this.onSurfaceChanged)) != null || z)) {
                this.mediaCodecRenderer.setSurface(listener);
            }
            this.listener.onSurfaceSet(!Intrinsics.areEqual(playbackSurface, this.defaultSurfaceRef));
        } catch (Throwable th) {
            throw th;
        }
    }
}
