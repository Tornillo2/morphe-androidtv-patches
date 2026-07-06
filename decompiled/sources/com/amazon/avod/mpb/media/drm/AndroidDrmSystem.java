package com.amazon.avod.mpb.media.drm;

import android.annotation.SuppressLint;
import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.media.NotProvisionedException;
import android.media.ResourceBusyException;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.activity.ComponentActivity$$ExternalSyntheticNonNull0;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import androidx.media3.exoplayer.analytics.MediaMetricsListener$$ExternalSyntheticApiModelOutline5;
import com.amazon.avod.mpb.api.core.FailoverManager;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.api.core.NoOpFailoverManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
@SourceDebugExtension({"SMAP\nAndroidDrmSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidDrmSystem.kt\ncom/amazon/avod/mpb/media/drm/AndroidDrmSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,993:1\n1869#2,2:994\n1761#2,3:996\n*S KotlinDebug\n*F\n+ 1 AndroidDrmSystem.kt\ncom/amazon/avod/mpb/media/drm/AndroidDrmSystem\n*L\n125#1:994,2\n861#1:996,3\n*E\n"})
public final class AndroidDrmSystem implements AutoCloseable {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String NVIDIA = "nVIDIA";

    @NotNull
    public static final String TAG = "AndroidDrmSystem";
    public static int nextIgniteSessionId = 1;

    @NotNull
    public final Base64Util base64Util;
    public final int buildVersion;

    @NotNull
    public final MediaDrmProvisioner drmProvisioner;

    @NotNull
    public final FailoverManager failoverManager;

    @NotNull
    public final DrmKeyStatusNotifier keyStatusNotifier;

    @Nullable
    public String lastOpenedIgniteSessionId;

    @Nullable
    public byte[] mainMediaDrmSessionId;

    @Nullable
    public MediaDrm mediaDrm;

    @NotNull
    public final Object mutex;

    @NotNull
    public final UUID schemeId;

    @NotNull
    public final Map<String, DrmSession> sessionTracker;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DrmSession {

        @NotNull
        public final byte[] mediaDrmSessionId;

        @Nullable
        public String persistentSessionId;

        @NotNull
        public final SessionType sessionType;

        public DrmSession(@NotNull byte[] mediaDrmSessionId, @NotNull SessionType sessionType, @Nullable String str) {
            Intrinsics.checkNotNullParameter(mediaDrmSessionId, "mediaDrmSessionId");
            Intrinsics.checkNotNullParameter(sessionType, "sessionType");
            this.mediaDrmSessionId = mediaDrmSessionId;
            this.sessionType = sessionType;
            this.persistentSessionId = str;
        }

        public static /* synthetic */ DrmSession copy$default(DrmSession drmSession, byte[] bArr, SessionType sessionType, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                bArr = drmSession.mediaDrmSessionId;
            }
            if ((i & 2) != 0) {
                sessionType = drmSession.sessionType;
            }
            if ((i & 4) != 0) {
                str = drmSession.persistentSessionId;
            }
            return drmSession.copy(bArr, sessionType, str);
        }

        @NotNull
        public final byte[] component1() {
            return this.mediaDrmSessionId;
        }

        @NotNull
        public final SessionType component2() {
            return this.sessionType;
        }

        @Nullable
        public final String component3() {
            return this.persistentSessionId;
        }

        @NotNull
        public final DrmSession copy(@NotNull byte[] mediaDrmSessionId, @NotNull SessionType sessionType, @Nullable String str) {
            Intrinsics.checkNotNullParameter(mediaDrmSessionId, "mediaDrmSessionId");
            Intrinsics.checkNotNullParameter(sessionType, "sessionType");
            return new DrmSession(mediaDrmSessionId, sessionType, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof DrmSession) {
                return Arrays.equals(this.mediaDrmSessionId, ((DrmSession) obj).mediaDrmSessionId);
            }
            return false;
        }

        @NotNull
        public final byte[] getMediaDrmSessionId() {
            return this.mediaDrmSessionId;
        }

        @Nullable
        public final String getPersistentSessionId() {
            return this.persistentSessionId;
        }

        @NotNull
        public final SessionType getSessionType() {
            return this.sessionType;
        }

        public int hashCode() {
            return Arrays.hashCode(this.mediaDrmSessionId);
        }

        public final void setPersistentSessionId(@Nullable String str) {
            this.persistentSessionId = str;
        }

        @NotNull
        public String toString() {
            SessionType sessionType = this.sessionType;
            String hexString = DrmUtils.INSTANCE.toHexString(this.mediaDrmSessionId);
            String str = this.persistentSessionId;
            StringBuilder sb = new StringBuilder("(sessionType=");
            sb.append(sessionType);
            sb.append(" mediaDrmSessionId=");
            sb.append(hexString);
            sb.append(" persistentSessionId=");
            return ActivityCompat$$ExternalSyntheticOutline0.m(sb, str, ")");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(api = 23)
    public final class OnKeyStatusChangeListener implements MediaDrm.OnKeyStatusChangeListener {
        public OnKeyStatusChangeListener() {
        }

        @Override // android.media.MediaDrm.OnKeyStatusChangeListener
        public void onKeyStatusChange(@NotNull MediaDrm mediaDrm, @NotNull byte[] sessionId, @NotNull List<MediaDrm.KeyStatus> keyInformation, boolean z) {
            Intrinsics.checkNotNullParameter(mediaDrm, "mediaDrm");
            Intrinsics.checkNotNullParameter(sessionId, "sessionId");
            Intrinsics.checkNotNullParameter(keyInformation, "keyInformation");
            MpbLog.logf("AndroidDrmSystem.onKeyStatusChange mediaDrm=" + mediaDrm + " sessionId=" + DrmUtils.INSTANCE.toHexString(sessionId) + " hasNewUsableKey=" + z, new Object[0]);
            Iterator<MediaDrm.KeyStatus> it = keyInformation.iterator();
            while (it.hasNext()) {
                MediaDrm.KeyStatus keyStatusM = AndroidDrmSystem$OnKeyStatusChangeListener$$ExternalSyntheticApiModelOutline0.m(it.next());
                String hexString = DrmUtils.INSTANCE.toHexString(keyStatusM.getKeyId());
                int statusCode = keyStatusM.getStatusCode();
                MpbLog.logf("AndroidDrmSystem.onKeyStatusChange keyId=" + hexString + " status=" + (statusCode != 0 ? statusCode != 1 ? statusCode != 2 ? statusCode != 3 ? statusCode != 4 ? statusCode != 5 ? String.valueOf(keyStatusM.getStatusCode()) : "STATUS_USABLE_IN_FUTURE" : "STATUS_INTERNAL_ERROR" : "STATUS_PENDING" : "STATUS_OUTPUT_NOT_ALLOWED" : "STATUS_EXPIRED" : "STATUS_USABLE"), new Object[0]);
            }
            AndroidDrmSystem.this.keyStatusNotifier.onKeyStatusChange(sessionId, keyInformation);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SessionType.values().length];
            try {
                iArr[SessionType.PERSISTENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SessionType.NON_PERSISTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AndroidDrmSystem(@NotNull UUID schemeId, @NotNull MediaDrmProvisioner drmProvisioner, int i, @NotNull Base64Util base64Util, @NotNull DrmKeyStatusNotifier keyStatusNotifier, @NotNull FailoverManager failoverManager) throws MediaPipelineBackendException, ResourceBusyException {
        Intrinsics.checkNotNullParameter(schemeId, "schemeId");
        Intrinsics.checkNotNullParameter(drmProvisioner, "drmProvisioner");
        Intrinsics.checkNotNullParameter(base64Util, "base64Util");
        Intrinsics.checkNotNullParameter(keyStatusNotifier, "keyStatusNotifier");
        Intrinsics.checkNotNullParameter(failoverManager, "failoverManager");
        this.schemeId = schemeId;
        this.drmProvisioner = drmProvisioner;
        this.buildVersion = i;
        this.base64Util = base64Util;
        this.keyStatusNotifier = keyStatusNotifier;
        this.failoverManager = failoverManager;
        this.sessionTracker = new LinkedHashMap();
        this.mutex = new Object();
        MediaDrm mediaDrm = new MediaDrm(schemeId);
        if (i >= 23) {
            mediaDrm.setOnKeyStatusChangeListener(new OnKeyStatusChangeListener(), new Handler(Looper.getMainLooper()));
        }
        this.mediaDrm = mediaDrm;
        byte[] bArrOpenMainSession = openMainSession();
        this.mainMediaDrmSessionId = bArrOpenMainSession;
        MpbLog.logf("AndroidDrmSystem.init mainMediaDrmSessionId=".concat(DrmUtils.INSTANCE.toHexString(bArrOpenMainSession)), new Object[0]);
    }

    public final void assertSessionNotAlreadyMapped(String str) throws MediaPipelineBackendException {
        if (this.sessionTracker.get(str) == null) {
            return;
        }
        String strM = MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("AndroidDrmSystem Ignite session ID '", str, "' is already mapped!");
        MpbLog.errorf(strM, new Object[0]);
        MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException(strM, MediaPipelineBackendResultCode.DRM_SESSION_ALREADY_MAPPED);
        this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
        throw mediaPipelineBackendException;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.mutex) {
            try {
                MediaDrm mediaDrm = this.mediaDrm;
                if (mediaDrm != null) {
                    mediaDrm.release();
                    this.mediaDrm = null;
                    this.mainMediaDrmSessionId = null;
                    this.lastOpenedIgniteSessionId = null;
                    Iterator<T> it = this.sessionTracker.values().iterator();
                    while (it.hasNext()) {
                        this.keyStatusNotifier.removeSession(((DrmSession) it.next()).mediaDrmSessionId);
                    }
                    this.sessionTracker.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String createIgniteSessionId() {
        int i = nextIgniteSessionId;
        nextIgniteSessionId = i + 1;
        return Integer.toHexString(i);
    }

    @NotNull
    public final String createPersistentSession() throws MediaPipelineBackendException {
        String strCreateIgniteSessionId;
        synchronized (this.mutex) {
            byte[] bArrTryCreateSession = tryCreateSession();
            strCreateIgniteSessionId = createIgniteSessionId();
            Intrinsics.checkNotNull(strCreateIgniteSessionId);
            assertSessionNotAlreadyMapped(strCreateIgniteSessionId);
            this.sessionTracker.put(strCreateIgniteSessionId, new DrmSession(bArrTryCreateSession, SessionType.PERSISTENT, null));
            this.lastOpenedIgniteSessionId = strCreateIgniteSessionId;
            MpbLog.logf("AndroidDrmSystem.createPersistentSession opened igniteSessionId=%s -> drmSession=%s", strCreateIgniteSessionId, this.sessionTracker.get(strCreateIgniteSessionId));
        }
        return strCreateIgniteSessionId;
    }

    @NotNull
    public final String createSession() throws MediaPipelineBackendException {
        String strCreateIgniteSessionId;
        synchronized (this.mutex) {
            byte[] bArrTryCreateSession = tryCreateSession();
            strCreateIgniteSessionId = createIgniteSessionId();
            Intrinsics.checkNotNull(strCreateIgniteSessionId);
            assertSessionNotAlreadyMapped(strCreateIgniteSessionId);
            this.sessionTracker.put(strCreateIgniteSessionId, new DrmSession(bArrTryCreateSession, SessionType.NON_PERSISTENT, null));
            this.lastOpenedIgniteSessionId = strCreateIgniteSessionId;
            MpbLog.logf("AndroidDrmSystem.createSession opened igniteSessionId=%s -> drmSession=%s", strCreateIgniteSessionId, this.sessionTracker.get(strCreateIgniteSessionId));
        }
        return strCreateIgniteSessionId;
    }

    public final byte[] decodePersistentSessionId(String str) throws MediaPipelineBackendException {
        try {
            return this.base64Util.decode(str);
        } catch (IllegalArgumentException e) {
            MpbLog.exceptionf(e, "AndroidDrmSystem.decodePersistentSessionId failed to decode persistentSessionId from Base64", new Object[0]);
            MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException("AndroidDrmSystem.decodePersistentSessionId failed to decode persistentSessionId from Base64", MediaPipelineBackendResultCode.DRM_PERSISTENT_SESSION_ID_DECODING_FAILED, e);
            this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
            throw mediaPipelineBackendException;
        }
    }

    public final void destroySession(@NotNull String igniteSessionId) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        synchronized (this.mutex) {
            try {
                DrmSession mappedDrmSession = getMappedDrmSession(igniteSessionId);
                byte[] bArr = mappedDrmSession != null ? mappedDrmSession.mediaDrmSessionId : null;
                MediaDrm mediaDrm = this.mediaDrm;
                orThrow(mediaDrm);
                if (bArr == null) {
                    String strConcat = "AndroidDrmSystem.destroySession mediaDrmSessionId not found for igniteSessionId=".concat(igniteSessionId);
                    MpbLog.errorf(strConcat, new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException(strConcat, MediaPipelineBackendResultCode.DRM_SESSION_NOT_MAPPED);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
                    throw mediaPipelineBackendException;
                }
                try {
                    try {
                        MpbLog.logf("AndroidDrmSystem.destroySession igniteSessionId=%s -> drmSession=%s ", igniteSessionId, mappedDrmSession);
                        mediaDrm.closeSession(bArr);
                        this.sessionTracker.remove(igniteSessionId);
                        if (igniteSessionId.equals(this.lastOpenedIgniteSessionId)) {
                            this.lastOpenedIgniteSessionId = null;
                        }
                    } catch (Exception e) {
                        MpbLog.exceptionf(e, "AndroidDrmSystem.destroySession failed to close DRM session", new Object[0]);
                        MediaPipelineBackendException mediaPipelineBackendException2 = new MediaPipelineBackendException("AndroidDrmSystem.destroySession failed to close DRM session", MediaPipelineBackendResultCode.DRM_CLOSE_SESSION_FAILED, e);
                        this.failoverManager.evaluateFatal(mediaPipelineBackendException2.resultCode);
                        throw mediaPipelineBackendException2;
                    }
                } finally {
                    this.keyStatusNotifier.removeSession(bArr);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @SuppressLint({"WrongConstant"})
    public final void enableSessionSharing() throws MediaPipelineBackendException {
        MediaDrm mediaDrm = this.mediaDrm;
        orThrow(mediaDrm);
        mediaDrm.setPropertyString("sessionSharing", "enable");
    }

    public final String encodeKeySetId(byte[] bArr) throws MediaPipelineBackendException {
        try {
            return this.base64Util.encode(bArr);
        } catch (Exception e) {
            MpbLog.exceptionf(e, "AndroidDrmSystem.encodeKeySetId failed to encode keySetId to Base64", new Object[0]);
            MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException("AndroidDrmSystem.encodeKeySetId failed to encode keySetId to Base64", MediaPipelineBackendResultCode.DRM_KEY_SET_ID_ENCODING_FAILED, e);
            this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
            throw mediaPipelineBackendException;
        }
    }

    @SuppressLint({"NewApi"})
    @NotNull
    public final AndroidDrmKeyRequest generateRequest(@NotNull String igniteSessionId, @NotNull byte[] initData) throws MediaPipelineBackendException {
        int i;
        MediaDrm.KeyRequest keyRequest;
        AndroidDrmKeyRequest androidDrmKeyRequest;
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        Intrinsics.checkNotNullParameter(initData, "initData");
        synchronized (this.mutex) {
            try {
                DrmSession mappedDrmSession = getMappedDrmSession(igniteSessionId);
                byte[] bArr = mappedDrmSession != null ? mappedDrmSession.mediaDrmSessionId : null;
                MediaDrm mediaDrm = this.mediaDrm;
                orThrow(mediaDrm);
                if (bArr == null) {
                    String strConcat = "AndroidDrmSystem.generateRequest mediaDrmSessionId not found for igniteSessionId=".concat(igniteSessionId);
                    MpbLog.errorf(strConcat, new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException(strConcat, MediaPipelineBackendResultCode.DRM_SESSION_NOT_MAPPED);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
                    throw mediaPipelineBackendException;
                }
                int i2 = WhenMappings.$EnumSwitchMapping$0[mappedDrmSession.sessionType.ordinal()];
                if (i2 == 1) {
                    i = 2;
                } else {
                    if (i2 != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    i = 1;
                }
                MpbLog.logf("AndroidDrmSystem.generateRequest igniteSessionId=%s -> drmSession=%s", igniteSessionId, mappedDrmSession);
                try {
                    keyRequest = mediaDrm.getKeyRequest(bArr, initData, null, i, null);
                } catch (NotProvisionedException e) {
                    MpbLog.warnf("AndroidDrmSystem.generateRequest device not provisioned for DRM: " + e + " - attempting to provision it...", new Object[0]);
                    try {
                        this.drmProvisioner.provision(mediaDrm);
                        keyRequest = mediaDrm.getKeyRequest(bArr, initData, null, i, null);
                    } catch (NotProvisionedException e2) {
                        MpbLog.exceptionf(e2, "AndroidDrmSystem.generateRequest provisioning reported no errors, but device still not provisioned", new Object[0]);
                        MediaPipelineBackendException mediaPipelineBackendException2 = new MediaPipelineBackendException("AndroidDrmSystem.generateRequest provisioning reported no errors, but device still not provisioned", MediaPipelineBackendResultCode.DRM_GENERATE_KEY_REQUEST_NOT_PROVISIONED, e2);
                        this.failoverManager.evaluateFatal(mediaPipelineBackendException2.resultCode);
                        throw mediaPipelineBackendException2;
                    } catch (MediaPipelineBackendException e3) {
                        MpbLog.exceptionf(e3, "AndroidDrmSystem.generateRequest failed to provision for DRM", new Object[0]);
                        throw e3;
                    }
                } catch (Exception e4) {
                    if (this.buildVersion < 23 || !MediaMetricsListener$$ExternalSyntheticApiModelOutline5.m(e4)) {
                        MpbLog.exceptionf(e4, "AndroidDrmSystem.generateRequest failed to generate DRM key request", new Object[0]);
                        MediaPipelineBackendException mediaPipelineBackendException3 = new MediaPipelineBackendException("AndroidDrmSystem.generateRequest failed to generate DRM key request", MediaPipelineBackendResultCode.DRM_GENERATE_KEY_REQUEST_UNKNOWN_FAILURE, e4);
                        this.failoverManager.evaluateFatal(mediaPipelineBackendException3.resultCode);
                        throw mediaPipelineBackendException3;
                    }
                    MpbLog.exceptionf(e4, "AndroidDrmSystem.generateRequest failed to generate DRM key request - MediaServer crashed and/or restarted", new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException4 = new MediaPipelineBackendException("AndroidDrmSystem.generateRequest failed to generate DRM key request - MediaServer crashed and/or restarted", MediaPipelineBackendResultCode.DRM_SYSTEM_RESET_REQUIRED, e4);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException4.resultCode);
                    throw mediaPipelineBackendException4;
                }
                Intrinsics.checkNotNull(keyRequest);
                androidDrmKeyRequest = new AndroidDrmKeyRequest(keyRequest);
            } catch (Throwable th) {
                throw th;
            }
        }
        return androidDrmKeyRequest;
    }

    @Nullable
    public final String getLastOpenedIgniteSessionId() {
        return this.lastOpenedIgniteSessionId;
    }

    @SuppressLint({"NewApi"})
    @NotNull
    public final String getLicenseStatus(@NotNull String igniteSessionId) throws MediaPipelineBackendException {
        HashMap<String, String> mapQueryKeyStatus;
        String licenseStatus$default;
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        synchronized (this.mutex) {
            try {
                DrmSession mappedDrmSession = getMappedDrmSession(igniteSessionId);
                byte[] bArr = mappedDrmSession != null ? mappedDrmSession.mediaDrmSessionId : null;
                MediaDrm mediaDrm = this.mediaDrm;
                orThrow(mediaDrm);
                if (bArr == null) {
                    String strConcat = "AndroidDrmSystem.getLicenseStatus mediaDrmSession not found for igniteSessionId=".concat(igniteSessionId);
                    MpbLog.errorf(strConcat, new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException(strConcat, MediaPipelineBackendResultCode.DRM_SESSION_NOT_MAPPED);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
                    throw mediaPipelineBackendException;
                }
                try {
                    mapQueryKeyStatus = mediaDrm.queryKeyStatus(bArr);
                } catch (NotProvisionedException e) {
                    MpbLog.warnf("AndroidDrmSystem.getLicenseStatus device not provisioned for DRM: " + e + " - attempting to provision it...", new Object[0]);
                    try {
                        this.drmProvisioner.provision(mediaDrm);
                        mapQueryKeyStatus = mediaDrm.queryKeyStatus(bArr);
                    } catch (NotProvisionedException e2) {
                        MpbLog.exceptionf(e2, "AndroidDrmSystem.getLicenseStatus provisioning reported no errors, but device still not provisioned", new Object[0]);
                        MediaPipelineBackendException mediaPipelineBackendException2 = new MediaPipelineBackendException("AndroidDrmSystem.getLicenseStatus provisioning reported no errors, but device still not provisioned", MediaPipelineBackendResultCode.DRM_LICENSE_STATUS_NOT_PROVISIONED, e2);
                        this.failoverManager.evaluateFatal(mediaPipelineBackendException2.resultCode);
                        throw mediaPipelineBackendException2;
                    } catch (MediaPipelineBackendException e3) {
                        MpbLog.exceptionf(e3, "AndroidDrmSystem.getLicenseStatus failed to provision for DRM", new Object[0]);
                        throw e3;
                    }
                } catch (Exception e4) {
                    if (this.buildVersion < 23 || !MediaMetricsListener$$ExternalSyntheticApiModelOutline5.m(e4)) {
                        MpbLog.exceptionf(e4, "AndroidDrmSystem.getLicenseStatus failed to query key status", new Object[0]);
                        MediaPipelineBackendException mediaPipelineBackendException3 = new MediaPipelineBackendException("AndroidDrmSystem.getLicenseStatus failed to query key status", MediaPipelineBackendResultCode.DRM_LICENSE_STATUS_UNKNOWN_FAILURE, e4);
                        this.failoverManager.evaluateFatal(mediaPipelineBackendException3.resultCode);
                        throw mediaPipelineBackendException3;
                    }
                    MpbLog.exceptionf(e4, "AndroidDrmSystem.getLicenseStatus failed to query key status - MediaServer crashed and/or restarted", new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException4 = new MediaPipelineBackendException("AndroidDrmSystem.getLicenseStatus failed to query key status - MediaServer crashed and/or restarted", MediaPipelineBackendResultCode.DRM_SYSTEM_RESET_REQUIRED, e4);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException4.resultCode);
                    throw mediaPipelineBackendException4;
                }
                Intrinsics.checkNotNull(mapQueryKeyStatus);
                licenseStatus$default = WidevineRightsParser.parseLicenseStatus$default(WidevineRightsParser.INSTANCE, mapQueryKeyStatus, null, 2, null);
                MpbLog.logf("AndroidDrmSystem.getLicenseStatus igniteSessionId=%s -> drmSession=%s returning keyStatusJson=%s", igniteSessionId, mappedDrmSession, licenseStatus$default);
            } catch (Throwable th) {
                throw th;
            }
        }
        return licenseStatus$default;
    }

    public final DrmSession getMappedDrmSession(String str) {
        return this.sessionTracker.get(str);
    }

    @Nullable
    public final byte[] getMediaDrmSessionId(@Nullable String str) {
        byte[] bArr;
        synchronized (this.mutex) {
            bArr = this.mainMediaDrmSessionId;
            if (bArr == null) {
                DrmSession mappedDrmSession = getMappedDrmSession(str);
                bArr = mappedDrmSession != null ? mappedDrmSession.mediaDrmSessionId : null;
            }
        }
        return bArr;
    }

    @SuppressLint({"NewApi"})
    @NotNull
    public final String getPersistentSessionId(@NotNull String igniteSessionId) throws MediaPipelineBackendException {
        String str;
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        synchronized (this.mutex) {
            try {
                DrmSession mappedDrmSession = getMappedDrmSession(igniteSessionId);
                str = mappedDrmSession != null ? mappedDrmSession.persistentSessionId : null;
                if (str == null) {
                    String strConcat = "AndroidDrmSystem.getPersistentId persistentSessionId not found for igniteSessionId=".concat(igniteSessionId);
                    MpbLog.errorf(strConcat, new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException(strConcat, MediaPipelineBackendResultCode.DRM_PERSISTENT_SESSION_ID_NOT_FOUND);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
                    throw mediaPipelineBackendException;
                }
                MpbLog.logf("AndroidDrmSystem.getPersistentId igniteSessionId=%s -> drmSession=%s", igniteSessionId, mappedDrmSession);
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    @NotNull
    public final UUID getSchemeId() {
        return this.schemeId;
    }

    public final boolean isSessionOpen(@NotNull byte[] targetSessionId) {
        boolean z;
        Intrinsics.checkNotNullParameter(targetSessionId, "targetSessionId");
        synchronized (this.mutex) {
            try {
                if (Arrays.equals(this.mainMediaDrmSessionId, targetSessionId)) {
                    z = true;
                    break;
                }
                Collection<DrmSession> collectionValues = this.sessionTracker.values();
                if (!ComponentActivity$$ExternalSyntheticNonNull0.m(collectionValues) || !collectionValues.isEmpty()) {
                    Iterator<T> it = collectionValues.iterator();
                    while (it.hasNext()) {
                        if (Arrays.equals(((DrmSession) it.next()).mediaDrmSessionId, targetSessionId)) {
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    @NotNull
    public final String loadSession(@NotNull String persistentSessionId) throws MediaPipelineBackendException {
        String strCreateIgniteSessionId;
        Intrinsics.checkNotNullParameter(persistentSessionId, "persistentSessionId");
        synchronized (this.mutex) {
            byte[] bArrTryCreateSession = tryCreateSession();
            MediaDrm mediaDrm = this.mediaDrm;
            orThrow(mediaDrm);
            try {
                mediaDrm.restoreKeys(bArrTryCreateSession, decodePersistentSessionId(persistentSessionId));
                strCreateIgniteSessionId = createIgniteSessionId();
                Intrinsics.checkNotNull(strCreateIgniteSessionId);
                assertSessionNotAlreadyMapped(strCreateIgniteSessionId);
                this.sessionTracker.put(strCreateIgniteSessionId, new DrmSession(bArrTryCreateSession, SessionType.PERSISTENT, persistentSessionId));
                this.lastOpenedIgniteSessionId = strCreateIgniteSessionId;
                MpbLog.logf("AndroidDrmSystem.loadSession loaded igniteSessionId=%s -> drmSession=%s", strCreateIgniteSessionId, this.sessionTracker.get(strCreateIgniteSessionId));
            } catch (Exception e) {
                MpbLog.exceptionf(e, "AndroidDrmSystem.loadSession failed to restore keySetId", new Object[0]);
                MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException("AndroidDrmSystem.loadSession failed to restore keySetId", MediaPipelineBackendResultCode.DRM_LOAD_SESSION_RESTORE_KEYS_FAILED, e);
                this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
                throw mediaPipelineBackendException;
            }
        }
        return strCreateIgniteSessionId;
    }

    public final Void notifyAndThrow(MediaPipelineBackendException mediaPipelineBackendException) throws MediaPipelineBackendException {
        this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
        throw mediaPipelineBackendException;
    }

    public final byte[] openMainSession() throws MediaPipelineBackendException, ResourceBusyException {
        UUID uuid = this.schemeId;
        DrmUtils.INSTANCE.getClass();
        if (!Intrinsics.areEqual(uuid, DrmUtils.WIDEVINE_UUID)) {
            return null;
        }
        enableSessionSharing();
        MediaDrm mediaDrm = this.mediaDrm;
        orThrow(mediaDrm);
        try {
            return mediaDrm.openSession();
        } catch (NotProvisionedException e) {
            MpbLog.warnf("AndroidDrmSystem.openMainSession device not provisioned for DRM: " + e + " - attempting to provision it...", new Object[0]);
            this.drmProvisioner.provision(mediaDrm);
            try {
                return mediaDrm.openSession();
            } catch (NotProvisionedException e2) {
                MpbLog.exceptionf(e2, "AndroidDrmSystem.openMainSession provisioning reported no errors, but device still not provisioned", new Object[0]);
                MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException("AndroidDrmSystem.openMainSession provisioning reported no errors, but device still not provisioned", MediaPipelineBackendResultCode.DRM_CREATE_MASTER_SESSION_NOT_PROVISIONED, e2);
                this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
                throw mediaPipelineBackendException;
            }
        }
    }

    public final MediaDrm orThrow(MediaDrm mediaDrm) throws MediaPipelineBackendException {
        if (mediaDrm != null) {
            return mediaDrm;
        }
        MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException("AndroidDrmSystem MediaDrm not instantiated", MediaPipelineBackendResultCode.DRM_SYSTEM_CLOSED);
        this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
        throw mediaPipelineBackendException;
    }

    @SuppressLint({"NewApi"})
    public final void processResponse(@NotNull String igniteSessionId, @NotNull byte[] response) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        Intrinsics.checkNotNullParameter(response, "response");
        synchronized (this.mutex) {
            try {
                DrmSession mappedDrmSession = getMappedDrmSession(igniteSessionId);
                byte[] bArr = mappedDrmSession != null ? mappedDrmSession.mediaDrmSessionId : null;
                MpbLog.logf("AndroidDrmSystem.processResponse igniteSessionId=%s -> drmSession=%s", igniteSessionId, mappedDrmSession);
                MediaDrm mediaDrm = this.mediaDrm;
                orThrow(mediaDrm);
                try {
                    if (bArr == null) {
                        String strConcat = "AndroidDrmSystem.processResponse mediaDrmSessionId not found for igniteSessionId=".concat(igniteSessionId);
                        MpbLog.errorf(strConcat, new Object[0]);
                        MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException(strConcat, MediaPipelineBackendResultCode.DRM_SESSION_NOT_MAPPED);
                        this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
                        throw mediaPipelineBackendException;
                    }
                    try {
                        byte[] bArrProvideKeyResponse = mediaDrm.provideKeyResponse(bArr, response);
                        if (mappedDrmSession.sessionType == SessionType.PERSISTENT) {
                            if (bArrProvideKeyResponse == null || bArrProvideKeyResponse.length == 0) {
                                String strConcat2 = "AndroidDrmSystem.processResponse MediaDrm returned null or empty keySetId for persistent license response igniteSessionId=".concat(igniteSessionId);
                                MpbLog.errorf(strConcat2, new Object[0]);
                                notifyAndThrow(new MediaPipelineBackendException(strConcat2, MediaPipelineBackendResultCode.DRM_PROCESS_KEY_RESPONSE_UNKNOWN_FAILURE));
                                throw null;
                            }
                            mappedDrmSession.persistentSessionId = encodeKeySetId(bArrProvideKeyResponse);
                            MpbLog.logf("AndroidDrmSystem.processResponse for igniteSessionId=%s -> drmSession=%s generated keySetId=%s", igniteSessionId, mappedDrmSession, DrmUtils.INSTANCE.toHexString(bArrProvideKeyResponse));
                        }
                    } catch (NotProvisionedException e) {
                        MpbLog.warnf("AndroidDrmSystem.processResponse device not provisioned for DRM: " + e + " - attempting to provision it...", new Object[0]);
                        try {
                            this.drmProvisioner.provision(mediaDrm);
                            mediaDrm.provideKeyResponse(bArr, response);
                        } catch (NotProvisionedException e2) {
                            MpbLog.exceptionf(e2, "AndroidDrmSystem.processResponse provisioning reported no errors, but device still not provisioned", new Object[0]);
                            MediaPipelineBackendException mediaPipelineBackendException2 = new MediaPipelineBackendException("AndroidDrmSystem.processResponse provisioning reported no errors, but device still not provisioned", MediaPipelineBackendResultCode.DRM_PROCESS_KEY_RESPONSE_NOT_PROVISIONED, e2);
                            this.failoverManager.evaluateFatal(mediaPipelineBackendException2.resultCode);
                            throw mediaPipelineBackendException2;
                        } catch (MediaPipelineBackendException e3) {
                            MpbLog.exceptionf(e3, "AndroidDrmSystem.processResponse failed to provision for DRM", new Object[0]);
                            throw e3;
                        }
                    }
                    if ("nVIDIA".equalsIgnoreCase(Build.MANUFACTURER)) {
                        HashMap<String, String> mapQueryKeyStatus = mediaDrm.queryKeyStatus(bArr);
                        Intrinsics.checkNotNullExpressionValue(mapQueryKeyStatus, "queryKeyStatus(...)");
                        if ("FAIL".equalsIgnoreCase(mapQueryKeyStatus.get("STATUS"))) {
                            MpbLog.errorf("AndroidDrmSystem.processResponse failed to process key response without an exception", new Object[0]);
                            MediaPipelineBackendException mediaPipelineBackendException3 = new MediaPipelineBackendException("AndroidDrmSystem.processResponse failed to process key response without an exception", MediaPipelineBackendResultCode.DRM_INVALID_KEY_RESPONSE);
                            this.failoverManager.evaluateFatal(mediaPipelineBackendException3.resultCode);
                            throw mediaPipelineBackendException3;
                        }
                    }
                } catch (DeniedByServerException e4) {
                    MpbLog.exceptionf(e4, "AndroidDrmSystem.processResponse DRM key request denied by server", new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException4 = new MediaPipelineBackendException("AndroidDrmSystem.processResponse DRM key request denied by server", MediaPipelineBackendResultCode.DRM_PROCESS_RESPONSE_KEY_REQUEST_DENIED, e4);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException4.resultCode);
                    throw mediaPipelineBackendException4;
                } catch (Exception e5) {
                    if (this.buildVersion < 23 || !MediaMetricsListener$$ExternalSyntheticApiModelOutline5.m(e5)) {
                        MpbLog.exceptionf(e5, "AndroidDrmSystem.processResponse failed to process DRM response", new Object[0]);
                        MediaPipelineBackendException mediaPipelineBackendException5 = new MediaPipelineBackendException("AndroidDrmSystem.processResponse failed to process DRM response", MediaPipelineBackendResultCode.DRM_PROCESS_KEY_RESPONSE_UNKNOWN_FAILURE, e5);
                        this.failoverManager.evaluateFatal(mediaPipelineBackendException5.resultCode);
                        throw mediaPipelineBackendException5;
                    }
                    MpbLog.exceptionf(e5, "AndroidDrmSystem.processResponse failed to process DRM key response - MediaServer crashed and/or restarted", new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException6 = new MediaPipelineBackendException("AndroidDrmSystem.processResponse failed to process DRM key response - MediaServer crashed and/or restarted", MediaPipelineBackendResultCode.DRM_SYSTEM_RESET_REQUIRED, e5);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException6.resultCode);
                    throw mediaPipelineBackendException6;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void releaseKeys(@NotNull String igniteSessionId) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        synchronized (this.mutex) {
            try {
                DrmSession mappedDrmSession = getMappedDrmSession(igniteSessionId);
                byte[] bArr = mappedDrmSession != null ? mappedDrmSession.mediaDrmSessionId : null;
                MediaDrm mediaDrm = this.mediaDrm;
                orThrow(mediaDrm);
                if (bArr == null) {
                    String strConcat = "AndroidDrmSystem.releaseKeys mediaDrmSessionId not found for igniteSessionId=".concat(igniteSessionId);
                    MpbLog.errorf(strConcat, new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException(strConcat, MediaPipelineBackendResultCode.DRM_SESSION_NOT_MAPPED);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
                    throw mediaPipelineBackendException;
                }
                try {
                    MpbLog.logf("AndroidDrmSystem.releaseKeys igniteSessionId=%s -> drmSession=%s", igniteSessionId, mappedDrmSession);
                    mediaDrm.removeKeys(bArr);
                    try {
                        mediaDrm.closeSession(bArr);
                        this.sessionTracker.put(igniteSessionId, DrmSession.copy$default(mappedDrmSession, tryCreateSession(), null, null, 2, null));
                    } catch (Exception e) {
                        MpbLog.exceptionf(e, "AndroidDrmSystem.releaseKeys failed to close DRM session during keys release", new Object[0]);
                        MediaPipelineBackendException mediaPipelineBackendException2 = new MediaPipelineBackendException("AndroidDrmSystem.releaseKeys failed to close DRM session during keys release", MediaPipelineBackendResultCode.DRM_KEY_RELEASE_SESSION_CLOSE_FAILED, e);
                        this.failoverManager.evaluateFatal(mediaPipelineBackendException2.resultCode);
                        throw mediaPipelineBackendException2;
                    }
                } catch (Exception e2) {
                    MpbLog.exceptionf(e2, "AndroidDrmSystem.releaseKeys failed to remove DRM keys from session", new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException3 = new MediaPipelineBackendException("AndroidDrmSystem.releaseKeys failed to remove DRM keys from session", MediaPipelineBackendResultCode.DRM_RELEASE_KEYS_FAILED, e2);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException3.resultCode);
                    throw mediaPipelineBackendException3;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void removeKeys(@NotNull String igniteSessionId) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(igniteSessionId, "igniteSessionId");
        synchronized (this.mutex) {
            try {
                DrmSession mappedDrmSession = getMappedDrmSession(igniteSessionId);
                byte[] bArr = mappedDrmSession != null ? mappedDrmSession.mediaDrmSessionId : null;
                MediaDrm mediaDrm = this.mediaDrm;
                orThrow(mediaDrm);
                if (bArr == null) {
                    String strConcat = "AndroidDrmSystem.removeKeys mediaDrmSessionId not found for igniteSessionId=".concat(igniteSessionId);
                    MpbLog.errorf(strConcat, new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException(strConcat, MediaPipelineBackendResultCode.DRM_SESSION_NOT_MAPPED);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
                    throw mediaPipelineBackendException;
                }
                String str = mappedDrmSession.persistentSessionId;
                if (str == null) {
                    String strConcat2 = "AndroidDrmSystem.removeKeys persistentSessionId not found for igniteSessionId=".concat(igniteSessionId);
                    MpbLog.errorf(strConcat2, new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException2 = new MediaPipelineBackendException(strConcat2, MediaPipelineBackendResultCode.DRM_SESSION_NOT_MAPPED);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException2.resultCode);
                    throw mediaPipelineBackendException2;
                }
                try {
                    MpbLog.logf("AndroidDrmSystem.removeKeys igniteSessionId=%s -> drmSession=%s", igniteSessionId, mappedDrmSession);
                    mediaDrm.getKeyRequest(decodePersistentSessionId(str), null, null, 3, null);
                    try {
                        mediaDrm.closeSession(bArr);
                        this.sessionTracker.put(igniteSessionId, DrmSession.copy$default(mappedDrmSession, tryCreateSession(), null, null, 2, null));
                    } catch (Exception e) {
                        MpbLog.exceptionf(e, "AndroidDrmSystem.removeKeys failed to close DRM session during keys release", new Object[0]);
                        MediaPipelineBackendException mediaPipelineBackendException3 = new MediaPipelineBackendException("AndroidDrmSystem.removeKeys failed to close DRM session during keys release", MediaPipelineBackendResultCode.DRM_KEY_REMOVE_SESSION_CLOSE_FAILED, e);
                        this.failoverManager.evaluateFatal(mediaPipelineBackendException3.resultCode);
                        throw mediaPipelineBackendException3;
                    }
                } catch (Exception e2) {
                    MpbLog.exceptionf(e2, "AndroidDrmSystem.removeKeys failed to remove DRM keys from session", new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException4 = new MediaPipelineBackendException("AndroidDrmSystem.removeKeys failed to remove DRM keys from session", MediaPipelineBackendResultCode.DRM_REMOVE_KEYS_FAILED, e2);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException4.resultCode);
                    throw mediaPipelineBackendException4;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @SuppressLint({"NewApi"})
    public final byte[] tryCreateSession() throws MediaPipelineBackendException {
        byte[] bArrOpenSession;
        MediaDrm mediaDrm = this.mediaDrm;
        orThrow(mediaDrm);
        try {
            try {
                bArrOpenSession = mediaDrm.openSession();
            } catch (NotProvisionedException e) {
                MpbLog.warnf("AndroidDrmSystem.tryCreateSession device not provisioned: " + e + " - attempting to provision it...", new Object[0]);
                try {
                    this.drmProvisioner.provision(mediaDrm);
                    bArrOpenSession = mediaDrm.openSession();
                } catch (NotProvisionedException e2) {
                    MpbLog.exceptionf(e2, "AndroidDrmSystem.tryCreateSession provisioning reported no errors, but device still not provisioned", new Object[0]);
                    MediaPipelineBackendException mediaPipelineBackendException = new MediaPipelineBackendException("AndroidDrmSystem.tryCreateSession provisioning reported no errors, but device still not provisioned", MediaPipelineBackendResultCode.DRM_CREATE_SESSION_NOT_PROVISIONED, e2);
                    this.failoverManager.evaluateFatal(mediaPipelineBackendException.resultCode);
                    throw mediaPipelineBackendException;
                } catch (MediaPipelineBackendException e3) {
                    MpbLog.exceptionf(e3, "AndroidDrmSystem.tryCreateSession failed to provision for DRM", new Object[0]);
                    throw e3;
                }
            }
            Intrinsics.checkNotNull(bArrOpenSession);
            return bArrOpenSession;
        } catch (ResourceBusyException e4) {
            MpbLog.exceptionf(e4, "AndroidDrmSystem.tryCreateSession failed to open DRM session due to ResourceBusyException", new Object[0]);
            MediaPipelineBackendException mediaPipelineBackendException2 = new MediaPipelineBackendException("AndroidDrmSystem.tryCreateSession failed to open DRM session due to ResourceBusyException", MediaPipelineBackendResultCode.DRM_RESOURCE_BUSY, e4);
            this.failoverManager.evaluateFatal(mediaPipelineBackendException2.resultCode);
            throw mediaPipelineBackendException2;
        } catch (Exception e5) {
            if (this.buildVersion < 23 || !MediaMetricsListener$$ExternalSyntheticApiModelOutline5.m(e5)) {
                MpbLog.exceptionf(e5, "AndroidDrmSystem.tryCreateSession failed to open DRM session", new Object[0]);
                MediaPipelineBackendException mediaPipelineBackendException3 = new MediaPipelineBackendException("AndroidDrmSystem.tryCreateSession failed to open DRM session", MediaPipelineBackendResultCode.DRM_OPEN_SESSION_UNKNOWN_FAILURE, e5);
                this.failoverManager.evaluateFatal(mediaPipelineBackendException3.resultCode);
                throw mediaPipelineBackendException3;
            }
            MpbLog.exceptionf(e5, "AndroidDrmSystem.tryCreateSession failed to open DRM session - MediaServer crashed and/or restarted", new Object[0]);
            MediaPipelineBackendException mediaPipelineBackendException4 = new MediaPipelineBackendException("AndroidDrmSystem.tryCreateSession failed to open DRM session - MediaServer crashed and/or restarted", MediaPipelineBackendResultCode.DRM_SYSTEM_RESET_REQUIRED, e5);
            this.failoverManager.evaluateFatal(mediaPipelineBackendException4.resultCode);
            throw mediaPipelineBackendException4;
        }
    }

    public /* synthetic */ AndroidDrmSystem(UUID uuid, MediaDrmProvisioner mediaDrmProvisioner, int i, Base64Util base64Util, DrmKeyStatusNotifier drmKeyStatusNotifier, FailoverManager failoverManager, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(uuid, mediaDrmProvisioner, i, base64Util, drmKeyStatusNotifier, (i2 & 32) != 0 ? NoOpFailoverManager.INSTANCE : failoverManager);
    }
}
