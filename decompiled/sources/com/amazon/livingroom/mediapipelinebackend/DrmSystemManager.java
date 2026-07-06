package com.amazon.livingroom.mediapipelinebackend;

import android.media.MediaDrm;
import android.media.UnsupportedSchemeException;
import androidx.annotation.NonNull;
import androidx.media3.common.C;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DrmSystemManager {
    public final Set<DrmSystem> activeDrmSystems = new HashSet();
    public final DrmProvisioner drmProvisioner;

    public DrmSystemManager(@NonNull DrmProvisioner drmProvisioner) {
        this.drmProvisioner = drmProvisioner;
    }

    @NonNull
    @CalledFromNative
    public ResultHolder<DrmSystem> createSystem(String str) {
        MpbLog.t("DrmSystemManager.createSystem");
        UUID drmSchemeId = getDrmSchemeId(str);
        if (drmSchemeId == null) {
            return ResultHolder.fromErrorCode(2);
        }
        try {
            DrmSystem drmSystem = new DrmSystem(drmSchemeId, this.drmProvisioner);
            this.activeDrmSystems.add(drmSystem);
            MpbLog.t("DrmSystemManager - created drmSystem=" + drmSystem);
            return ResultHolder.fromResult(drmSystem);
        } catch (UnsupportedSchemeException unused) {
            MpbLog.e("Unsupported DRM scheme '" + drmSchemeId + "'");
            return ResultHolder.fromErrorCode(4);
        } catch (DrmProvisioningException e) {
            MpbLog.e("Failed to provision during DrmSystem creation", e);
            return ResultHolder.fromErrorCode(e.getErrorCode());
        } catch (Exception e2) {
            MpbLog.e("Failed to initialize DRM scheme", e2);
            return ResultHolder.fromErrorCode(5);
        }
    }

    @CalledFromNative
    public int destroySystem(@NonNull DrmSystem drmSystem) {
        MpbLog.t("DrmSystemManager.destroySystem - drmSystem=" + drmSystem);
        this.activeDrmSystems.remove(drmSystem);
        try {
            drmSystem.close();
            return 0;
        } catch (Exception e) {
            MpbLog.e("Failed to close DrmSystem", e);
            return 6;
        }
    }

    public final UUID getDrmSchemeId(String str) {
        str.getClass();
        if (str.equals("com.microsoft.playready")) {
            return C.PLAYREADY_UUID;
        }
        if (str.equals("com.widevine.alpha")) {
            return C.WIDEVINE_UUID;
        }
        return null;
    }

    @CalledFromNative
    public int isSupported(@NonNull String str) {
        MpbLog.t("DrmSystemManager.init - schemeName=" + str);
        UUID drmSchemeId = getDrmSchemeId(str);
        if (drmSchemeId == null) {
            MpbLog.w("Unrecognized DRM scheme '" + str + "'");
            return 2;
        }
        if (MediaDrm.isCryptoSchemeSupported(drmSchemeId)) {
            return 0;
        }
        MpbLog.e("Unsupported DRM scheme '" + str + "'");
        return 3;
    }

    @CalledFromNative
    public int shutdown() {
        MpbLog.t("DrmSystemManager.shutdown");
        int i = 0;
        if (!this.activeDrmSystems.isEmpty()) {
            MpbLog.w("Shutting down DRM with " + this.activeDrmSystems.size() + " active systems");
            Iterator<DrmSystem> it = this.activeDrmSystems.iterator();
            while (it.hasNext()) {
                try {
                    it.next().close();
                } catch (Exception e) {
                    MpbLog.e("Failed to close DRM system during shutdown", e);
                    i = 7;
                }
            }
            this.activeDrmSystems.clear();
        }
        return i;
    }
}
