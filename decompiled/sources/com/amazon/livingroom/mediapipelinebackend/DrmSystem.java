package com.amazon.livingroom.mediapipelinebackend;

import android.annotation.SuppressLint;
import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.media.NotProvisionedException;
import android.media.ResourceBusyException;
import android.media.UnsupportedSchemeException;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.C;
import androidx.media3.exoplayer.analytics.MediaMetricsListener$$ExternalSyntheticApiModelOutline5;
import com.amazon.avod.mpb.media.drm.AndroidDrmSystem$OnKeyStatusChangeListener$$ExternalSyntheticApiModelOutline0;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Pair;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DrmSystem implements AutoCloseable {
    public static final AtomicInteger nextIgniteSessionId = new AtomicInteger(1);
    public final DrmProvisioner drmProvisioner;
    public volatile String lastOpenedIgniteSessionId;
    public MediaDrm mediaDrm;
    public final UUID schemeId;
    public final Map<String, byte[]> sessionIds = new ConcurrentHashMap();
    public byte[] mainMediaDrmSessionId = openMainSession();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(api = 23)
    public class OnKeyStatusChangeListener implements MediaDrm.OnKeyStatusChangeListener {
        @Override // android.media.MediaDrm.OnKeyStatusChangeListener
        public void onKeyStatusChange(@NonNull MediaDrm mediaDrm, @NonNull byte[] bArr, @NonNull List<MediaDrm.KeyStatus> list, boolean z) {
            MpbLog.i("onKeyStatusChange - mediaDrm=" + mediaDrm + " sessionId=" + DrmUtils.toString(bArr) + " hasNewUsableKey=" + z);
            Iterator<MediaDrm.KeyStatus> it = list.iterator();
            while (it.hasNext()) {
                MediaDrm.KeyStatus keyStatusM = AndroidDrmSystem$OnKeyStatusChangeListener$$ExternalSyntheticApiModelOutline0.m(it.next());
                String string = DrmUtils.toString(keyStatusM.getKeyId());
                int statusCode = keyStatusM.getStatusCode();
                MpbLog.i("keyId=" + string + " status=" + (statusCode != 0 ? statusCode != 1 ? statusCode != 2 ? statusCode != 3 ? statusCode != 4 ? statusCode != 5 ? Integer.toString(keyStatusM.getStatusCode()) : "STATUS_USABLE_IN_FUTURE" : "STATUS_INTERNAL_ERROR" : "STATUS_PENDING" : "STATUS_OUTPUT_NOT_ALLOWED" : "STATUS_EXPIRED" : "STATUS_USABLE"));
            }
        }

        public OnKeyStatusChangeListener() {
        }
    }

    public DrmSystem(@NonNull UUID uuid, @NonNull DrmProvisioner drmProvisioner) throws UnsupportedSchemeException, ResourceBusyException {
        this.schemeId = uuid;
        this.drmProvisioner = drmProvisioner;
        this.mediaDrm = new MediaDrm(uuid);
        MpbLog.t("DrmSystemManager - mainMediaDrmSessionId=" + DrmUtils.toString(this.mainMediaDrmSessionId));
        if (Build.VERSION.SDK_INT >= 23) {
            this.mediaDrm.setOnKeyStatusChangeListener(new OnKeyStatusChangeListener(), new Handler(Looper.getMainLooper()));
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        MediaDrm mediaDrm = this.mediaDrm;
        if (mediaDrm != null) {
            mediaDrm.release();
            this.mediaDrm = null;
            this.mainMediaDrmSessionId = null;
            this.lastOpenedIgniteSessionId = null;
            this.sessionIds.clear();
        }
    }

    public final String createIgniteSessionId() {
        return Integer.toHexString(nextIgniteSessionId.getAndIncrement());
    }

    @NonNull
    @CalledFromNative
    public ResultHolder<String> createSession() {
        MpbLog.t("DrmSystemManager.createSession - mediaDrm=" + this.mediaDrm);
        if (isClosed()) {
            return ResultHolder.fromErrorCode(20);
        }
        Pair<Integer, byte[]> pairTryCreateSession = tryCreateSession();
        if (pairTryCreateSession.first.intValue() != 0) {
            return ResultHolder.fromErrorCode(pairTryCreateSession.first.intValue());
        }
        String strCreateIgniteSessionId = createIgniteSessionId();
        this.sessionIds.put(strCreateIgniteSessionId, pairTryCreateSession.second);
        this.lastOpenedIgniteSessionId = strCreateIgniteSessionId;
        MpbLog.t("DrmSystemManager - Opened mediaDrmSessionId=" + DrmUtils.toString(pairTryCreateSession.second) + " igniteSessionId=" + strCreateIgniteSessionId);
        return ResultHolder.fromResult(strCreateIgniteSessionId);
    }

    @CalledFromNative
    public int destroySession(@NonNull String str) {
        byte[] mappedMediaDrmSessionId = getMappedMediaDrmSessionId(str);
        MpbLog.t("DrmSystemManager.destroySession - mediaDrm=" + this.mediaDrm + " igniteSessionId=" + str + " mediaDrmSessionId=" + DrmUtils.toString(mappedMediaDrmSessionId));
        if (isClosed()) {
            return 20;
        }
        if (mappedMediaDrmSessionId == null) {
            MpbLog.e("Unknown igniteSessionId=" + str);
            return 19;
        }
        try {
            this.mediaDrm.closeSession(mappedMediaDrmSessionId);
            this.sessionIds.remove(str);
            synchronized (this) {
                try {
                    if (str.equals(this.lastOpenedIgniteSessionId)) {
                        this.lastOpenedIgniteSessionId = null;
                    }
                } finally {
                }
            }
            return 0;
        } catch (Exception e) {
            MpbLog.e("Failed to close DRM session", e);
            return 10;
        }
    }

    @SuppressLint({"WrongConstant"})
    public final void enableSessionSharing() {
        this.mediaDrm.setPropertyString("sessionSharing", "enable");
    }

    @NonNull
    @CalledFromNative
    public ResultHolder<DrmKeyRequest> generateRequest(@NonNull String str, @NonNull byte[] bArr) {
        byte[] bArr2;
        byte[] mappedMediaDrmSessionId = getMappedMediaDrmSessionId(str);
        MpbLog.t("DrmSystemManager.generateRequest - mediaDrm=" + this.mediaDrm + " igniteSessionId=" + str + " mediaDrmSessionId=" + DrmUtils.toString(mappedMediaDrmSessionId));
        if (isClosed()) {
            return ResultHolder.fromErrorCode(20);
        }
        if (mappedMediaDrmSessionId == null) {
            MpbLog.e("Unknown igniteSessionId=" + str);
            return ResultHolder.fromErrorCode(19);
        }
        MediaDrm.KeyRequest keyRequest = null;
        int errorCode = 0;
        try {
            try {
                bArr2 = bArr;
                try {
                    keyRequest = this.mediaDrm.getKeyRequest(mappedMediaDrmSessionId, bArr2, null, 1, null);
                } catch (NotProvisionedException e) {
                    e = e;
                    MpbLog.w("Device not provisioned for DRM while generating a key request - attempting to provision it...", e);
                    try {
                        this.drmProvisioner.provision(this.mediaDrm);
                        keyRequest = this.mediaDrm.getKeyRequest(mappedMediaDrmSessionId, bArr2, null, 1, null);
                    } catch (NotProvisionedException e2) {
                        MpbLog.e("Provisioning reported no errors, but device still not provisioned while generating a key request", e2);
                        errorCode = 12;
                    } catch (DrmProvisioningException e3) {
                        MpbLog.e("Failed to provision for DRM while generating a key request", e3);
                        errorCode = e3.getErrorCode();
                    }
                }
            } catch (Exception e4) {
                if (Build.VERSION.SDK_INT < 23 || !MediaMetricsListener$$ExternalSyntheticApiModelOutline5.m(e4)) {
                    MpbLog.e("Failed to generate DRM key request", e4);
                    errorCode = 13;
                } else {
                    MpbLog.e("Failed to generate DRM key request - MediaServer crashed and/or restarted", e4);
                    errorCode = 29;
                }
            }
        } catch (NotProvisionedException e5) {
            e = e5;
            bArr2 = bArr;
        }
        return errorCode != 0 ? ResultHolder.fromErrorCode(errorCode) : keyRequest == null ? ResultHolder.fromErrorCode(28) : ResultHolder.fromResult(new DrmKeyRequest(keyRequest));
    }

    @Nullable
    public String getLastOpenedIgniteSessionId() {
        return this.lastOpenedIgniteSessionId;
    }

    public final byte[] getMappedMediaDrmSessionId(String str) {
        if (str == null) {
            return null;
        }
        return this.sessionIds.get(str);
    }

    @Nullable
    public byte[] getMediaDrmSessionId(@Nullable String str) {
        byte[] bArr = this.mainMediaDrmSessionId;
        return bArr != null ? bArr : getMappedMediaDrmSessionId(str);
    }

    @NonNull
    public UUID getSchemeId() {
        return this.schemeId;
    }

    public final boolean isClosed() {
        return this.mediaDrm == null;
    }

    public boolean isSessionOpen(@NonNull byte[] bArr) {
        if (Arrays.equals(this.mainMediaDrmSessionId, bArr)) {
            return true;
        }
        Iterator<byte[]> it = this.sessionIds.values().iterator();
        while (it.hasNext()) {
            if (Arrays.equals(it.next(), bArr)) {
                return true;
            }
        }
        return false;
    }

    public final byte[] openMainSession() throws ResourceBusyException {
        if (!this.schemeId.equals(C.WIDEVINE_UUID)) {
            return null;
        }
        enableSessionSharing();
        try {
            return this.mediaDrm.openSession();
        } catch (NotProvisionedException e) {
            MpbLog.w("Device not provisioned for DRM while opening a master session - attempting to provision it...", e);
            this.drmProvisioner.provision(this.mediaDrm);
            try {
                return this.mediaDrm.openSession();
            } catch (NotProvisionedException e2) {
                throw new DrmProvisioningException(26, "Provisioning reported no errors, but device still not provisioned while opening a master session", e2);
            }
        }
    }

    @CalledFromNative
    public int processResponse(@NonNull String str, @NonNull byte[] bArr) {
        byte[] mappedMediaDrmSessionId = getMappedMediaDrmSessionId(str);
        MpbLog.t("DrmSystemManager.processResponse - mediaDrm=" + this.mediaDrm + " igniteSessionId=" + str + " mediaDrmSessionId=" + DrmUtils.toString(mappedMediaDrmSessionId));
        if (isClosed()) {
            return 20;
        }
        if (mappedMediaDrmSessionId == null) {
            MpbLog.e("Unknown igniteSessionId=" + str);
            return 19;
        }
        if (bArr.length == 0) {
            MpbLog.e("Empty DRM key response");
            return 31;
        }
        try {
            try {
                this.mediaDrm.provideKeyResponse(mappedMediaDrmSessionId, bArr);
            } catch (NotProvisionedException e) {
                MpbLog.w("Device not provisioned for DRM while providing a key response - attempting to provision it...", e);
                try {
                    this.drmProvisioner.provision(this.mediaDrm);
                    this.mediaDrm.provideKeyResponse(mappedMediaDrmSessionId, bArr);
                } catch (NotProvisionedException e2) {
                    MpbLog.e("Provisioning reported no errors, but device still not provisioned while providing a key response", e2);
                    return 15;
                } catch (DrmProvisioningException e3) {
                    MpbLog.e("Failed to provision for DRM while providing a key response", e3);
                    return e3.getErrorCode();
                }
            }
            if (!"nVIDIA".equalsIgnoreCase(Build.MANUFACTURER) || !"FAIL".equalsIgnoreCase(this.mediaDrm.queryKeyStatus(mappedMediaDrmSessionId).get("STATUS"))) {
                return 0;
            }
            MpbLog.e("Failed to process key response without an exception");
            return 17;
        } catch (DeniedByServerException e4) {
            MpbLog.e("DRM key request denied by server", e4);
            return 14;
        } catch (Exception e5) {
            if (Build.VERSION.SDK_INT < 23 || !MediaMetricsListener$$ExternalSyntheticApiModelOutline5.m(e5)) {
                MpbLog.e("Failed to process DRM response", e5);
                return 16;
            }
            MpbLog.e("Failed to provide DRM key response - MediaServer crashed and/or restarted", e5);
            return 29;
        }
    }

    @CalledFromNative
    public int releaseKeys(@NonNull String str) {
        byte[] mappedMediaDrmSessionId = getMappedMediaDrmSessionId(str);
        MpbLog.t("DrmSystemManager.releaseKeys - mediaDrm=" + this.mediaDrm + " igniteSessionId=" + str + " mediaDrmSessionId=" + DrmUtils.toString(mappedMediaDrmSessionId));
        if (isClosed()) {
            return 20;
        }
        if (mappedMediaDrmSessionId == null) {
            MpbLog.e("Unknown igniteSessionId=" + str);
            return 19;
        }
        try {
            this.mediaDrm.removeKeys(mappedMediaDrmSessionId);
            try {
                this.mediaDrm.closeSession(mappedMediaDrmSessionId);
                Pair<Integer, byte[]> pairTryCreateSession = tryCreateSession();
                if (pairTryCreateSession.first.intValue() != 0) {
                    MpbLog.e("Failed to create new session after releasing keys");
                    return pairTryCreateSession.first.intValue();
                }
                this.sessionIds.put(str, pairTryCreateSession.second);
                return 0;
            } catch (Exception e) {
                MpbLog.e("Failed to close DRM session during keys release", e);
                return 30;
            }
        } catch (Exception e2) {
            MpbLog.e("Failed to remove DRM keys from session", e2);
            return 11;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlin.Pair<java.lang.Integer, byte[]> tryCreateSession() {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            android.media.MediaDrm r2 = r4.mediaDrm     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb android.media.NotProvisionedException -> Ld
            byte[] r2 = r2.openSession()     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb android.media.NotProvisionedException -> Ld
            goto L5c
        L9:
            r0 = move-exception
            goto L37
        Lb:
            r0 = move-exception
            goto L54
        Ld:
            r2 = move-exception
            java.lang.String r3 = "Device not provisioned for DRM while opening a session - attempting to provision it..."
            com.amazon.livingroom.mediapipelinebackend.MpbLog.w(r3, r2)     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb
            com.amazon.livingroom.mediapipelinebackend.DrmProvisioner r2 = r4.drmProvisioner     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb android.media.NotProvisionedException -> L21 com.amazon.livingroom.mediapipelinebackend.DrmProvisioningException -> L23
            android.media.MediaDrm r3 = r4.mediaDrm     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb android.media.NotProvisionedException -> L21 com.amazon.livingroom.mediapipelinebackend.DrmProvisioningException -> L23
            r2.provision(r3)     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb android.media.NotProvisionedException -> L21 com.amazon.livingroom.mediapipelinebackend.DrmProvisioningException -> L23
            android.media.MediaDrm r2 = r4.mediaDrm     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb android.media.NotProvisionedException -> L21 com.amazon.livingroom.mediapipelinebackend.DrmProvisioningException -> L23
            byte[] r2 = r2.openSession()     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb android.media.NotProvisionedException -> L21 com.amazon.livingroom.mediapipelinebackend.DrmProvisioningException -> L23
            goto L5c
        L21:
            r0 = move-exception
            goto L25
        L23:
            r0 = move-exception
            goto L2d
        L25:
            java.lang.String r2 = "Provisioning reported no errors, but device still not provisioned while opening a session"
            com.amazon.livingroom.mediapipelinebackend.MpbLog.e(r2, r0)     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb
            r0 = 18
            goto L4a
        L2d:
            java.lang.String r2 = "Failed to provision for DRM while opening a session"
            com.amazon.livingroom.mediapipelinebackend.MpbLog.e(r2, r0)     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb
            int r0 = r0.getErrorCode()     // Catch: java.lang.Exception -> L9 android.media.ResourceBusyException -> Lb
            goto L4a
        L37:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 23
            if (r2 < r3) goto L4c
            boolean r2 = androidx.media3.exoplayer.analytics.MediaMetricsListener$$ExternalSyntheticApiModelOutline5.m(r0)
            if (r2 == 0) goto L4c
            java.lang.String r2 = "Failed to open DRM session - MediaServer crashed and/or restarted"
            com.amazon.livingroom.mediapipelinebackend.MpbLog.e(r2, r0)
            r0 = 29
        L4a:
            r2 = r1
            goto L5c
        L4c:
            java.lang.String r2 = "Failed to open DRM session"
            com.amazon.livingroom.mediapipelinebackend.MpbLog.e(r2, r0)
            r0 = 9
            goto L4a
        L54:
            java.lang.String r2 = "Failed to open DRM session due to ResourceBusyException"
            com.amazon.livingroom.mediapipelinebackend.MpbLog.e(r2, r0)
            r0 = 8
            goto L4a
        L5c:
            if (r0 == 0) goto L68
            kotlin.Pair r2 = new kotlin.Pair
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2.<init>(r0, r1)
            return r2
        L68:
            if (r2 != 0) goto L76
            kotlin.Pair r0 = new kotlin.Pair
            r2 = 27
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.<init>(r2, r1)
            return r0
        L76:
            kotlin.Pair r1 = new kotlin.Pair
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.<init>(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.livingroom.mediapipelinebackend.DrmSystem.tryCreateSession():kotlin.Pair");
    }
}
