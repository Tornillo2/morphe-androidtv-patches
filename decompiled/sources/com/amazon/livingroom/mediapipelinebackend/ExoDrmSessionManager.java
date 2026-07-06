package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.FrameworkCryptoConfig;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ExoDrmSessionManager {
    public DrmSystem drmSystem;

    @Nullable
    @OptIn(markerClass = {UnstableApi.class})
    public DrmSession acquireSession(Format format) {
        DrmInitData drmInitData = format.drmInitData;
        MpbLog.t("ExoDrmSessionManager.acquireSession - format=" + format + " drmSystem=" + this.drmSystem);
        if (drmInitData == null) {
            return null;
        }
        try {
            DrmSystem drmSystem = this.drmSystem;
            if (drmSystem == null) {
                throw new IllegalStateException("No DRM system set");
            }
            UUID schemeId = drmSystem.getSchemeId();
            DrmInitData.SchemeData schemeData = getSchemeData(drmInitData, schemeId);
            if (schemeData == null) {
                throw new IllegalStateException("Cannot acquire session for DRM scheme " + schemeId);
            }
            byte[] bArr = schemeData.data;
            if (bArr == null) {
                throw new IllegalStateException("sessionId is null");
            }
            if (!this.drmSystem.isSessionOpen(bArr)) {
                throw new IllegalArgumentException("Tried to acquire session " + DrmUtils.toString(bArr) + " before it was opened.");
            }
            ExoDrmSession exoDrmSession = new ExoDrmSession(schemeId, bArr);
            MpbLog.t("ExoDrmSessionManager.acquireSession - session acquired: sessionId=" + DrmUtils.toString(bArr) + " exoDrmSession=" + exoDrmSession);
            return exoDrmSession;
        } catch (Exception e) {
            return new ExoDrmSession(e);
        }
    }

    @Nullable
    public UUID getDrmSchemeId() {
        DrmSystem drmSystem = this.drmSystem;
        if (drmSystem == null) {
            return null;
        }
        return drmSystem.getSchemeId();
    }

    @Nullable
    public byte[] getInitialSessionId() {
        DrmSystem drmSystem = this.drmSystem;
        if (drmSystem == null) {
            return null;
        }
        return drmSystem.getMediaDrmSessionId(drmSystem.getLastOpenedIgniteSessionId());
    }

    @OptIn(markerClass = {UnstableApi.class})
    public final DrmInitData.SchemeData getSchemeData(@NonNull DrmInitData drmInitData, UUID uuid) {
        for (int i = 0; i < drmInitData.schemeDataCount; i++) {
            DrmInitData.SchemeData schemeData = drmInitData.schemeDatas[i];
            if (schemeData.matches(uuid)) {
                return schemeData;
            }
        }
        return null;
    }

    @CalledFromNative
    public boolean setDrmSystem(@Nullable DrmSystem drmSystem) {
        MpbLog.t("ExoDrmSessionManager.setDrmSystem - drmSystem=" + drmSystem);
        this.drmSystem = drmSystem;
        return true;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @OptIn(markerClass = {UnstableApi.class})
    public static class ExoDrmSession implements DrmSession {
        public final DrmSession.DrmSessionException exception;
        public final FrameworkCryptoConfig frameworkCryptoConfig;
        public final UUID schemeId;
        public int state;

        public ExoDrmSession(UUID uuid, byte[] bArr) {
            this.exception = null;
            this.schemeId = uuid;
            this.frameworkCryptoConfig = new FrameworkCryptoConfig(uuid, bArr, false);
            this.state = 4;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSession
        @Nullable
        public CryptoConfig getCryptoConfig() {
            return this.frameworkCryptoConfig;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSession
        public DrmSession.DrmSessionException getError() {
            return this.exception;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSession
        public byte[] getOfflineLicenseKeySetId() {
            return null;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSession
        public UUID getSchemeUuid() {
            return this.schemeId;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSession
        public int getState() {
            return this.state;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSession
        public /* synthetic */ boolean playClearSamplesWithoutKeys() {
            return false;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSession
        public Map<String, String> queryKeyStatus() {
            return null;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSession
        public boolean requiresSecureDecoder(String str) {
            return !MimeTypes.isAudio(str);
        }

        public ExoDrmSession(Exception exc) {
            this.exception = new DrmSession.DrmSessionException(exc, 1000);
            this.schemeId = null;
            this.frameworkCryptoConfig = null;
            this.state = 1;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSession
        public void acquire(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        }

        @Override // androidx.media3.exoplayer.drm.DrmSession
        public void release(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        }
    }
}
