package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.AdFormat;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class MediationConfiguration {

    @NonNull
    public static final String CUSTOM_EVENT_SERVER_PARAMETER_FIELD = "parameter";
    public final AdFormat zza;
    public final Bundle zzb;

    public MediationConfiguration(@NonNull AdFormat adFormat, @NonNull Bundle bundle) {
        this.zza = adFormat;
        this.zzb = bundle;
    }

    @NonNull
    public AdFormat getFormat() {
        return this.zza;
    }

    @NonNull
    public Bundle getServerParameters() {
        return this.zzb;
    }
}
