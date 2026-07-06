package com.amazon.avod.mpb.media.drm;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WidevineRightsParser {

    @NotNull
    public static final WidevineRightsParser INSTANCE = new WidevineRightsParser();

    @NotNull
    public static final String LICENSE_DURATION_REMAINING = "LicenseDurationRemaining";

    @NotNull
    public static final String LICENSE_EXPIRY_KEY = "licenseExpiryEpochSeconds";

    @NotNull
    public static final String LICENSE_TYPE = "LicenseType";

    @NotNull
    public static final String LICENSE_TYPE_OFFLINE = "Offline";

    @NotNull
    public static final String LICENSE_TYPE_STREAMING = "Streaming";

    @NotNull
    public static final String PERSIST_ALLOWED = "PersistAllowed";

    @NotNull
    public static final String PLAYBACK_DURATION_REMAINING = "PlaybackDurationRemaining";

    @NotNull
    public static final String PLAYBACK_EXPIRY_KEY = "playbackExpiryEpochSeconds";

    @NotNull
    public static final String PLAY_ALLOWED = "PlayAllowed";

    @NotNull
    public static final String RENEWAL_SERVER_URL = "RenewalServerUrl";

    @NotNull
    public static final String RENEW_ALLOWED = "RenewAllowed";

    @NotNull
    public static final String TAG = "WidevineRightsParser";

    /* JADX INFO: renamed from: com.amazon.avod.mpb.media.drm.WidevineRightsParser$parseLicenseStatus$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Long> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0, System.class, "currentTimeMillis", "currentTimeMillis()J", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Long invoke() {
            return Long.valueOf(System.currentTimeMillis());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String parseLicenseStatus$default(WidevineRightsParser widevineRightsParser, Map map, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = AnonymousClass1.INSTANCE;
        }
        return widevineRightsParser.parseLicenseStatus(map, function0);
    }

    @NotNull
    public final String parseLicenseStatus(@NotNull Map<String, ? extends Object> rights, @NotNull Function0<Long> clock) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(rights, "rights");
        Intrinsics.checkNotNullParameter(clock, "clock");
        validateRights(rights);
        try {
            long j = Long.parseLong(String.valueOf(rights.get("PlaybackDurationRemaining")));
            long j2 = Long.parseLong(String.valueOf(rights.get("LicenseDurationRemaining")));
            if (j <= 0 && j2 <= 0) {
                String strM = MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(AbstractResolvableFuture$$ExternalSyntheticOutline2.m("WidevineRightsParser expired license: both playback (", j, ") and license ("), j2, ") durations are <= 0");
                MpbLog.warnf(strM, new Object[0]);
                throw new MediaPipelineBackendException(strM, MediaPipelineBackendResultCode.DRM_LICENSE_STATUS_LICENSE_EXPIRED);
            }
            if (j <= 0) {
                String strM2 = ChannelLogoUtils$$ExternalSyntheticOutline0.m("WidevineRightsParser expired license: playback duration is <= 0 (", j, ")");
                MpbLog.warnf(strM2, new Object[0]);
                throw new MediaPipelineBackendException(strM2, MediaPipelineBackendResultCode.DRM_LICENSE_STATUS_PLAYBACK_CLOCK_EXPIRED);
            }
            if (j2 > 0) {
                long seconds = TimeUnit.MILLISECONDS.toSeconds(clock.invoke().longValue());
                return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(AbstractResolvableFuture$$ExternalSyntheticOutline2.m("{\"playbackExpiryEpochSeconds\":", j == Long.MAX_VALUE ? Long.MAX_VALUE : j + seconds, ",\"licenseExpiryEpochSeconds\":"), j2 != Long.MAX_VALUE ? seconds + j2 : Long.MAX_VALUE, "}");
            }
            String strM3 = ChannelLogoUtils$$ExternalSyntheticOutline0.m("WidevineRightsParser expired license: license duration is <= 0 (", j2, ")");
            MpbLog.warnf(strM3, new Object[0]);
            throw new MediaPipelineBackendException(strM3, MediaPipelineBackendResultCode.DRM_LICENSE_STATUS_LICENSE_CLOCK_EXPIRED);
        } catch (NumberFormatException e) {
            String strM4 = RemoteInput$$ExternalSyntheticOutline0.m("WidevineRightsParser failed to parse durations: ", e.getMessage());
            MpbLog.errorf(strM4, new Object[0]);
            throw new MediaPipelineBackendException(strM4, MediaPipelineBackendResultCode.DRM_LICENSE_STATUS_INVALID_DURATIONS);
        }
    }

    public final void validateRights(Map<String, ? extends Object> map) throws MediaPipelineBackendException {
        if (map.isEmpty()) {
            MpbLog.warnf("WidevineRightsParser widevine rights map was empty", new Object[0]);
            throw new MediaPipelineBackendException("WidevineRightsParser widevine rights map was empty", MediaPipelineBackendResultCode.DRM_LICENSE_STATUS_MALFORMED_RIGHTS_MAP);
        }
        for (String str : CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{PLAY_ALLOWED, PERSIST_ALLOWED, RENEW_ALLOWED, LICENSE_TYPE, "PlaybackDurationRemaining", "LicenseDurationRemaining"})) {
            if (map.get(str) == null) {
                String strM = MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("WidevineRightsParser widevine rights map did not contain the ", str, " key");
                MpbLog.warnf(strM, new Object[0]);
                throw new MediaPipelineBackendException(strM, MediaPipelineBackendResultCode.DRM_LICENSE_STATUS_MALFORMED_RIGHTS_MAP);
            }
        }
        String strValueOf = String.valueOf(map.get(LICENSE_TYPE));
        if (!strValueOf.equalsIgnoreCase(LICENSE_TYPE_OFFLINE) && !strValueOf.equalsIgnoreCase(LICENSE_TYPE_STREAMING)) {
            String strConcat = "WidevineRightsParser invalid license type: ".concat(strValueOf);
            MpbLog.warnf(strConcat, new Object[0]);
            throw new MediaPipelineBackendException(strConcat, MediaPipelineBackendResultCode.DRM_LICENSE_STATUS_INVALID_LICENSE_TYPE);
        }
        if (map.get(RENEWAL_SERVER_URL) == null) {
            MpbLog.warnf("WidevineRightsParser widevine rights map did not contain the RenewalServerUrl key (not critical)", new Object[0]);
        }
    }
}
