package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzah;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import com.google.errorprone.annotations.concurrent.GuardedBy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
@Deprecated
public final class GoogleServices {
    public static final Object zza = new Object();

    @Nullable
    @GuardedBy("lock")
    public static GoogleServices zzb;

    @Nullable
    public final String zzc;
    public final Status zzd;
    public final boolean zze;
    public final boolean zzf;

    @KeepForSdk
    @VisibleForTesting
    public GoogleServices(String str, boolean z) {
        this.zzc = str;
        this.zzd = Status.RESULT_SUCCESS;
        this.zze = z;
        this.zzf = !z;
    }

    @KeepForSdk
    public static GoogleServices checkInitialized(String str) {
        GoogleServices googleServices;
        synchronized (zza) {
            try {
                googleServices = zzb;
                if (googleServices == null) {
                    throw new IllegalStateException("Initialize must be called before " + str + ExternalFourCCMapper.CODEC_NAME_SPLITTER);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return googleServices;
    }

    @KeepForSdk
    @VisibleForTesting
    public static void clearInstanceForTest() {
        synchronized (zza) {
            zzb = null;
        }
    }

    @Nullable
    @KeepForSdk
    public static String getGoogleAppId() {
        return checkInitialized("getGoogleAppId").zzc;
    }

    @NonNull
    @KeepForSdk
    public static Status initialize(@NonNull Context context) {
        Status status;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (zza) {
            try {
                if (zzb == null) {
                    zzb = new GoogleServices(context);
                }
                status = zzb.zzd;
            } catch (Throwable th) {
                throw th;
            }
        }
        return status;
    }

    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        GoogleServices googleServicesCheckInitialized = checkInitialized("isMeasurementEnabled");
        return googleServicesCheckInitialized.zzd.isSuccess() && googleServicesCheckInitialized.zze;
    }

    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return checkInitialized("isMeasurementExplicitlyDisabled").zzf;
    }

    @KeepForSdk
    @VisibleForTesting
    public Status checkGoogleAppId(String str) {
        String str2 = this.zzc;
        return (str2 == null || str2.equals(str)) ? Status.RESULT_SUCCESS : new Status(10, MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '", this.zzc, "'."), null, null);
    }

    @KeepForSdk
    @VisibleForTesting
    public GoogleServices(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            int integer = resources.getInteger(identifier);
            boolean z = integer == 0;
            z = integer != 0;
            this.zzf = z;
        } else {
            this.zzf = false;
        }
        this.zze = z;
        zzah.zzc(context);
        String string = zzah.zzc;
        string = string == null ? new StringResourceValueReader(context).getString("google_app_id") : string;
        if (TextUtils.isEmpty(string)) {
            this.zzd = new Status(10, "Missing google app id value from from string resources with name google_app_id.", null, null);
            this.zzc = null;
        } else {
            this.zzc = string;
            this.zzd = Status.RESULT_SUCCESS;
        }
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public static Status initialize(@NonNull Context context, @NonNull String str, boolean z) {
        Preconditions.checkNotNull(context, "Context must not be null.");
        Preconditions.checkNotEmpty(str, "App ID must be nonempty.");
        synchronized (zza) {
            try {
                GoogleServices googleServices = zzb;
                if (googleServices != null) {
                    return googleServices.checkGoogleAppId(str);
                }
                GoogleServices googleServices2 = new GoogleServices(str, z);
                zzb = googleServices2;
                return googleServices2.zzd;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
