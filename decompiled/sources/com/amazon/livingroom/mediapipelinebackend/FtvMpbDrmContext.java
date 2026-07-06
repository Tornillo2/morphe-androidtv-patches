package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.api.core.FailoverManager;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.media.drm.AndroidDrmKeyRequest;
import com.amazon.avod.mpb.media.drm.AndroidDrmSystem;
import com.amazon.avod.mpb.media.drm.AndroidDrmSystemManager;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import com.amazon.avod.mpb.media.drm.MediaDrmProvisioner;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nFtvMpbDrmContext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FtvMpbDrmContext.kt\ncom/amazon/livingroom/mediapipelinebackend/FtvMpbDrmContext\n*L\n1#1,193:1\n150#1,11:194\n150#1,11:205\n150#1,11:216\n150#1,11:227\n150#1,11:238\n*S KotlinDebug\n*F\n+ 1 FtvMpbDrmContext.kt\ncom/amazon/livingroom/mediapipelinebackend/FtvMpbDrmContext\n*L\n50#1:194,11\n69#1:205,11\n75#1:216,11\n81#1:227,11\n109#1:238,11\n*E\n"})
public final class FtvMpbDrmContext {

    @NotNull
    public final ApplicationVisibilityMonitor applicationVisibilityMonitor;

    @NotNull
    public final DrmKeyStatusNotifier drmKeyStatusNotifier;

    @NotNull
    public final MediaDrmProvisioner drmProvisioner;

    @NotNull
    public final FailoverManager failoverManager;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AssistedFactory
    public interface Factory {
        @NotNull
        FtvMpbDrmContext create(@NotNull MediaDrmProvisioner mediaDrmProvisioner, @NotNull DrmKeyStatusNotifier drmKeyStatusNotifier, @NotNull FailoverManager failoverManager);
    }

    @AssistedInject
    public FtvMpbDrmContext(@Assisted @NotNull MediaDrmProvisioner drmProvisioner, @Assisted @NotNull DrmKeyStatusNotifier drmKeyStatusNotifier, @Assisted @NotNull FailoverManager failoverManager, @NotNull ApplicationVisibilityMonitor applicationVisibilityMonitor) {
        Intrinsics.checkNotNullParameter(drmProvisioner, "drmProvisioner");
        Intrinsics.checkNotNullParameter(drmKeyStatusNotifier, "drmKeyStatusNotifier");
        Intrinsics.checkNotNullParameter(failoverManager, "failoverManager");
        Intrinsics.checkNotNullParameter(applicationVisibilityMonitor, "applicationVisibilityMonitor");
        this.drmProvisioner = drmProvisioner;
        this.drmKeyStatusNotifier = drmKeyStatusNotifier;
        this.failoverManager = failoverManager;
        this.applicationVisibilityMonitor = applicationVisibilityMonitor;
    }

    public static final Unit destroySession$lambda$6(AndroidDrmSystem androidDrmSystem, String str) throws MediaPipelineBackendException {
        MpbLog.i("FtvMpbDrmContext.destroySession drmSystem=" + androidDrmSystem + ", igniteSessionId=" + str);
        androidDrmSystem.destroySession(str);
        return Unit.INSTANCE;
    }

    public static final Unit destroySystem$lambda$1(AndroidDrmSystemManager androidDrmSystemManager, AndroidDrmSystem androidDrmSystem) {
        MpbLog.i("FtvMpbDrmContext.destroySystem drmSystemManager=" + androidDrmSystemManager + ", drmSystem=" + androidDrmSystem);
        androidDrmSystemManager.destroySystem(androidDrmSystem);
        return Unit.INSTANCE;
    }

    public static final Unit processResponse$lambda$10(AndroidDrmSystem androidDrmSystem, String str, byte[] bArr) throws MediaPipelineBackendException {
        MpbLog.i("FtvMpbDrmContext.processResponse drmSystem=" + androidDrmSystem + ", igniteSessionId=" + str + ", responseSize=" + bArr.length);
        androidDrmSystem.processResponse(str, bArr);
        return Unit.INSTANCE;
    }

    public static final Unit releaseKeys$lambda$7(AndroidDrmSystem androidDrmSystem, String str) throws MediaPipelineBackendException {
        MpbLog.i("FtvMpbDrmContext.releaseKeys drmSystem=" + androidDrmSystem + ", igniteSessionId=" + str);
        androidDrmSystem.releaseKeys(str);
        return Unit.INSTANCE;
    }

    public static final Unit removeKeys$lambda$8(AndroidDrmSystem androidDrmSystem, String str) throws MediaPipelineBackendException {
        MpbLog.i("FtvMpbDrmContext.removeKeys drmSystem=" + androidDrmSystem + ", igniteSessionId=" + str);
        androidDrmSystem.removeKeys(str);
        return Unit.INSTANCE;
    }

    public static final Unit shutdown$lambda$2(AndroidDrmSystemManager androidDrmSystemManager) {
        MpbLog.i("FtvMpbDrmContext.shutdown drmSystemManager=" + androidDrmSystemManager);
        androidDrmSystemManager.shutdown();
        return Unit.INSTANCE;
    }

    @CalledFromNative
    @NotNull
    public final AndroidDrmSystemManager createInstance() {
        MpbLog.i("FtvMpbDrmContext.createInstance");
        return new AndroidDrmSystemManager(this.drmProvisioner, this.failoverManager, null, null, 12, null);
    }

    @CalledFromNative
    @NotNull
    public final String createPersistentSession(@NotNull AndroidDrmSystem drmSystem) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        try {
            MpbLog.i("FtvMpbDrmContext.createPersistentSession drmSystem=" + drmSystem);
            return drmSystem.createPersistentSession();
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            MpbLog.e("Throwing MPB exception errorCode=" + e.resultCode.name() + ": " + e.getMessage(), e);
            throw e;
        }
    }

    @CalledFromNative
    @NotNull
    public final String createSession(@NotNull AndroidDrmSystem drmSystem) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        try {
            MpbLog.i("FtvMpbDrmContext.createSession drmSystem=" + drmSystem);
            return drmSystem.createSession();
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            MpbLog.e("Throwing MPB exception errorCode=" + e.resultCode.name() + ": " + e.getMessage(), e);
            throw e;
        }
    }

    @CalledFromNative
    @NotNull
    public final AndroidDrmSystem createSystem(@NotNull AndroidDrmSystemManager drmSystemManager, @NotNull String name) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(drmSystemManager, "drmSystemManager");
        Intrinsics.checkNotNullParameter(name, "name");
        try {
            MpbLog.i("FtvMpbDrmContext.createSystem drmSystemManager=" + drmSystemManager + ", name=" + name);
            return drmSystemManager.createSystem(name, this.drmKeyStatusNotifier);
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            MpbLog.e("Throwing MPB exception errorCode=" + e.resultCode.name() + ": " + e.getMessage(), e);
            throw e;
        }
    }

    @CalledFromNative
    public final int destroySession(@NotNull final AndroidDrmSystem drmSystem, @NotNull final String igniteSessionId) {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        return handleBackgroundErrorsReturningInt(new Function0() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FtvMpbDrmContext.destroySession$lambda$6(drmSystem, igniteSessionId);
            }
        });
    }

    @CalledFromNative
    public final int destroySystem(@NotNull final AndroidDrmSystemManager drmSystemManager, @NotNull final AndroidDrmSystem drmSystem) {
        Intrinsics.checkNotNullParameter(drmSystemManager, "drmSystemManager");
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        return handleBackgroundErrorsReturningInt(new Function0() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FtvMpbDrmContext.destroySystem$lambda$1(drmSystemManager, drmSystem);
            }
        });
    }

    @CalledFromNative
    @NotNull
    public final AndroidDrmKeyRequest generateRequest(@NotNull AndroidDrmSystem drmSystem, @NotNull String igniteSessionId, @NotNull byte[] initData) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        Intrinsics.checkNotNullParameter(initData, "initData");
        try {
            MpbLog.i("FtvMpbDrmContext.generateRequest drmSystem=" + drmSystem + ", igniteSessionId=" + igniteSessionId + ", initDataSize=" + initData.length);
            return drmSystem.generateRequest(igniteSessionId, initData);
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            MpbLog.e("Throwing MPB exception errorCode=" + e.resultCode.name() + ": " + e.getMessage(), e);
            throw e;
        }
    }

    @CalledFromNative
    @NotNull
    public final String getCapabilities(@NotNull AndroidDrmSystemManager drmSystemManager) {
        Intrinsics.checkNotNullParameter(drmSystemManager, "drmSystemManager");
        MpbLog.i("FtvMpbDrmContext.getCapabilities drmSystemManager=" + drmSystemManager);
        return drmSystemManager.capabilities;
    }

    @CalledFromNative
    @NotNull
    public final byte[] getData(@NotNull AndroidDrmKeyRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        MpbLog.i("FtvMpbDrmContext.getData request=" + request + ", dataSize=" + request.getData().length);
        return request.getData();
    }

    @CalledFromNative
    @NotNull
    public final String getLicenseStatus(@NotNull AndroidDrmSystem drmSystem, @NotNull String igniteSessionId) {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        MpbLog.i("FtvMpbDrmContext.getLicenseStatus drmSystem=" + drmSystem + ", igniteSessionId=" + igniteSessionId);
        return drmSystem.getLicenseStatus(igniteSessionId);
    }

    @CalledFromNative
    @NotNull
    public final String getPersistentSessionId(@NotNull AndroidDrmSystem drmSystem, @NotNull String igniteSessionId) {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        MpbLog.i("FtvMpbDrmContext.getPersistentSessionId drmSystem=" + drmSystem + ", igniteSessionId=" + igniteSessionId);
        return drmSystem.getPersistentSessionId(igniteSessionId);
    }

    @CalledFromNative
    public final int getRequestType(@NotNull AndroidDrmKeyRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        MpbLog.i("FtvMpbDrmContext.getRequestType request=" + request + ", requestType=" + request.getRequestType());
        return request.getRequestType();
    }

    public final Pair<MediaPipelineBackendResultCode, String> getSuppressionErrorCodeMessage() {
        if (this.applicationVisibilityMonitor.foregroundSessionId == 0) {
            return new Pair<>(MediaPipelineBackendResultCode.AV_MPB_INVALID_BACKGROUND_OPERATION, "Background DRM error suppressed");
        }
        return null;
    }

    public final <T> T handleBackgroundErrors(Function0<? extends T> function0) throws MediaPipelineBackendException {
        try {
            return function0.invoke();
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            MpbLog.e("Throwing MPB exception errorCode=" + e.resultCode.name() + ": " + e.getMessage(), e);
            throw e;
        }
    }

    public final int handleBackgroundErrorsReturningInt(Function0<Unit> function0) throws MediaPipelineBackendException {
        try {
            function0.invoke();
            return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            MpbLog.e("Throwing MPB exception errorCode=" + e.resultCode.name() + ": " + e.getMessage(), e);
            throw e;
        }
    }

    @CalledFromNative
    @NotNull
    public final String loadSession(@NotNull AndroidDrmSystem drmSystem, @NotNull String persistentSessionId) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        Intrinsics.checkNotNullParameter(persistentSessionId, "persistentSessionId");
        try {
            MpbLog.i("FtvMpbDrmContext.loadSession drmSystem=" + drmSystem + ", persistentSessionId=" + persistentSessionId);
            return drmSystem.loadSession(persistentSessionId);
        } catch (MediaPipelineBackendException e) {
            e = e;
            Pair<MediaPipelineBackendResultCode, String> suppressionErrorCodeMessage = getSuppressionErrorCodeMessage();
            if (suppressionErrorCodeMessage != null) {
                e = new MediaPipelineBackendException(suppressionErrorCodeMessage.second, suppressionErrorCodeMessage.first, e);
            }
            MpbLog.e("Throwing MPB exception errorCode=" + e.resultCode.name() + ": " + e.getMessage(), e);
            throw e;
        }
    }

    @CalledFromNative
    public final int processResponse(@NotNull final AndroidDrmSystem drmSystem, @NotNull final String igniteSessionId, @NotNull final byte[] response) {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        Intrinsics.checkNotNullParameter(response, "response");
        return handleBackgroundErrorsReturningInt(new Function0() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FtvMpbDrmContext.processResponse$lambda$10(drmSystem, igniteSessionId, response);
            }
        });
    }

    @CalledFromNative
    public final int releaseKeys(@NotNull final AndroidDrmSystem drmSystem, @NotNull final String igniteSessionId) {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        return handleBackgroundErrorsReturningInt(new Function0() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FtvMpbDrmContext.releaseKeys$lambda$7(drmSystem, igniteSessionId);
            }
        });
    }

    @CalledFromNative
    public final int removeKeys(@NotNull final AndroidDrmSystem drmSystem, @NotNull final String igniteSessionId) {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        return handleBackgroundErrorsReturningInt(new Function0() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FtvMpbDrmContext.removeKeys$lambda$8(drmSystem, igniteSessionId);
            }
        });
    }

    @CalledFromNative
    public final int shutdown(@NotNull final AndroidDrmSystemManager drmSystemManager) {
        Intrinsics.checkNotNullParameter(drmSystemManager, "drmSystemManager");
        return handleBackgroundErrorsReturningInt(new Function0() { // from class: com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FtvMpbDrmContext.shutdown$lambda$2(drmSystemManager);
            }
        });
    }
}
