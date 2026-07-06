package androidx.media3.exoplayer.drm;

import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.media.NotProvisionedException;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.MediaMetricsListener$$ExternalSyntheticApiModelOutline5;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DrmUtil {
    public static final int ERROR_SOURCE_EXO_MEDIA_DRM = 1;
    public static final int ERROR_SOURCE_LICENSE_ACQUISITION = 2;
    public static final int ERROR_SOURCE_PROVISIONING = 3;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(18)
    public static final class Api18 {
        @DoNotInline
        public static boolean isDeniedByServerException(@Nullable Throwable th) {
            return th instanceof DeniedByServerException;
        }

        @DoNotInline
        public static boolean isMissingSchemeDataException(@Nullable Throwable th) {
            return th instanceof DefaultDrmSessionManager.MissingSchemeDataException;
        }

        @DoNotInline
        public static boolean isNotProvisionedException(@Nullable Throwable th) {
            return th instanceof NotProvisionedException;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(21)
    public static final class Api21 {
        @DoNotInline
        public static boolean isMediaDrmStateException(@Nullable Throwable th) {
            return th instanceof MediaDrm.MediaDrmStateException;
        }

        @DoNotInline
        public static int mediaDrmStateExceptionToErrorCode(Throwable th) {
            return Util.getErrorCodeForMediaDrmErrorCode(Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaDrm.MediaDrmStateException) th).getDiagnosticInfo()));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static final class Api23 {
        @DoNotInline
        public static boolean isMediaDrmResetException(@Nullable Throwable th) {
            return MediaMetricsListener$$ExternalSyntheticApiModelOutline5.m(th);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorSource {
    }

    public static int getErrorCodeForMediaDrmException(Throwable th, int i) {
        int i2 = Util.SDK_INT;
        if (i2 >= 21 && Api21.isMediaDrmStateException(th)) {
            return Api21.mediaDrmStateExceptionToErrorCode(th);
        }
        if (i2 >= 23 && Api23.isMediaDrmResetException(th)) {
            return 6006;
        }
        if ((i2 >= 18 && Api18.isNotProvisionedException(th)) || isFailureToConstructNotProvisionedException(th)) {
            return 6002;
        }
        if (i2 >= 18 && Api18.isDeniedByServerException(th)) {
            return 6007;
        }
        if (th instanceof UnsupportedDrmException) {
            return 6001;
        }
        if (i2 >= 18 && Api18.isMissingSchemeDataException(th)) {
            return 6003;
        }
        if (th instanceof KeysExpiredException) {
            return 6008;
        }
        if (i == 1) {
            return 6006;
        }
        if (i == 2) {
            return 6004;
        }
        if (i == 3) {
            return 6002;
        }
        throw new IllegalArgumentException();
    }

    public static boolean isFailureToConstructNotProvisionedException(@Nullable Throwable th) {
        return Util.SDK_INT == 34 && (th instanceof NoSuchMethodError) && th.getMessage() != null && th.getMessage().contains("Landroid/media/NotProvisionedException;.<init>(");
    }

    public static boolean isFailureToConstructResourceBusyException(@Nullable Throwable th) {
        return Util.SDK_INT == 34 && (th instanceof NoSuchMethodError) && th.getMessage() != null && th.getMessage().contains("Landroid/media/ResourceBusyException;.<init>(");
    }
}
