package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ParametersAreNonnullByDefault
public class MediationAdConfiguration {
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE = 0;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE = 1;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED = -1;
    public final String zza;
    public final Bundle zzb;
    public final Bundle zzc;
    public final Context zzd;
    public final boolean zze;
    public final int zzf;
    public final int zzg;

    @Nullable
    public final String zzh;
    public final String zzi;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface TagForChildDirectedTreatment {
    }

    public MediationAdConfiguration(@NonNull Context context, @NonNull String str, @NonNull Bundle bundle, @NonNull Bundle bundle2, boolean z, @Nullable Location location, int i, int i2, @Nullable String str2, @NonNull String str3) {
        this.zza = str;
        this.zzb = bundle;
        this.zzc = bundle2;
        this.zzd = context;
        this.zze = z;
        this.zzf = i;
        this.zzg = i2;
        this.zzh = str2;
        this.zzi = str3;
    }

    @NonNull
    public String getBidResponse() {
        return this.zza;
    }

    @NonNull
    public Context getContext() {
        return this.zzd;
    }

    @Nullable
    public String getMaxAdContentRating() {
        return this.zzh;
    }

    @NonNull
    public Bundle getMediationExtras() {
        return this.zzc;
    }

    @NonNull
    public Bundle getServerParameters() {
        return this.zzb;
    }

    @NonNull
    public String getWatermark() {
        return this.zzi;
    }

    public boolean isTestRequest() {
        return this.zze;
    }

    public int taggedForChildDirectedTreatment() {
        return this.zzf;
    }

    public int taggedForUnderAgeTreatment() {
        return this.zzg;
    }
}
