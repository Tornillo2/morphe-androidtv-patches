package com.amazon.livingroom.mediapipelinebackend;

import android.media.MediaDrm;
import android.media.UnsupportedSchemeException;
import androidx.media3.common.C;
import com.amazon.livingroom.di.ApplicationScope;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class WidevineCapabilitiesProvider {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int DEFAULT_MAX_NUMBER_OF_SESSIONS = 1;

    @Nullable
    public MediaDrm _mediaDrm = tryCreateMediaDrm();

    @NotNull
    public final OneTimeMpbLog emptyHdcpReportedLog = new OneTimeMpbLog();
    public boolean mediaDrmCreationFailed;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final MediaDrm createMediaDrm$ignitionshared_release(@NotNull UUID cryptoSchemeId) {
            Intrinsics.checkNotNullParameter(cryptoSchemeId, "cryptoSchemeId");
            return new MediaDrm(cryptoSchemeId);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public WidevineCapabilitiesProvider() {
    }

    @NotNull
    public final String getHdcpLevel() {
        try {
            String property = getProperty("hdcpLevel");
            if (property != null) {
                return property;
            }
            OneTimeMpbLog.w$default(this.emptyHdcpReportedLog, "Widevine is not supported by device so returning empty HDCP level", null, 2, null);
            return "";
        } catch (IllegalStateException e) {
            MpbLog.e("Could not get HDCP version", e);
            return "";
        }
    }

    public final int getMaxNumberOfSessions() {
        try {
            String property = getProperty("maxNumberOfSessions");
            if (property == null) {
                MpbLog.w("Widevine is not supported by device so returning default max number of sessions");
                return 1;
            }
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException e) {
                MpbLog.e("maxNumberOfSessions='" + property + "' could not be converted to an int", e);
                return 1;
            }
        } catch (IllegalStateException e2) {
            MpbLog.e("Failed to get maxNumberOfSessions from Widevine, returning default value", e2);
            return 1;
        }
    }

    public final MediaDrm getMediaDrm() {
        if (this._mediaDrm == null) {
            this._mediaDrm = tryCreateMediaDrm();
        }
        return this._mediaDrm;
    }

    public final synchronized String getProperty(String str) {
        MediaDrm mediaDrm = getMediaDrm();
        if (mediaDrm == null) {
            return null;
        }
        try {
            return mediaDrm.getPropertyString(str);
        } catch (IllegalStateException e) {
            mediaDrm.release();
            this._mediaDrm = null;
            throw e;
        }
    }

    public final MediaDrm tryCreateMediaDrm() {
        if (this.mediaDrmCreationFailed) {
            return null;
        }
        try {
            Companion companion = Companion;
            UUID WIDEVINE_UUID = C.WIDEVINE_UUID;
            Intrinsics.checkNotNullExpressionValue(WIDEVINE_UUID, "WIDEVINE_UUID");
            return companion.createMediaDrm$ignitionshared_release(WIDEVINE_UUID);
        } catch (UnsupportedSchemeException e) {
            MpbLog.e("Widevine is not supported, so cannot check Widevine capabilities", e);
            this.mediaDrmCreationFailed = true;
            return null;
        }
    }
}
