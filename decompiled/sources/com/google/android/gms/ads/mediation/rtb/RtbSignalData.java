package com.google.android.gms.ads.mediation.rtb;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class RtbSignalData {
    public final Context zza;
    public final List zzb;
    public final Bundle zzc;

    @Nullable
    public final AdSize zzd;

    public RtbSignalData(@NonNull Context context, @NonNull List<MediationConfiguration> list, @NonNull Bundle bundle, @Nullable AdSize adSize) {
        this.zza = context;
        this.zzb = list;
        this.zzc = bundle;
        this.zzd = adSize;
    }

    @Nullable
    public AdSize getAdSize() {
        return this.zzd;
    }

    @Nullable
    @Deprecated
    public MediationConfiguration getConfiguration() {
        List list = this.zzb;
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (MediationConfiguration) this.zzb.get(0);
    }

    @NonNull
    public List<MediationConfiguration> getConfigurations() {
        return this.zzb;
    }

    @NonNull
    public Context getContext() {
        return this.zza;
    }

    @NonNull
    public Bundle getNetworkExtras() {
        return this.zzc;
    }
}
