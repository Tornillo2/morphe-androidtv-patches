package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationAppOpenAd;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbqe implements MediationAdLoadCallback {
    public final /* synthetic */ zzbpg zza;
    public final /* synthetic */ zzboe zzb;
    public final /* synthetic */ zzbqh zzc;

    public zzbqe(zzbqh zzbqhVar, zzbpg zzbpgVar, zzboe zzboeVar) {
        this.zzc = zzbqhVar;
        this.zza = zzbpgVar;
        this.zzb = zzboeVar;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(AdError adError) {
        try {
            this.zza.zzf(adError.zza());
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    @Nullable
    public final /* bridge */ /* synthetic */ Object onSuccess(Object obj) {
        MediationAppOpenAd mediationAppOpenAd = (MediationAppOpenAd) obj;
        if (mediationAppOpenAd != null) {
            try {
                this.zzc.zzd = mediationAppOpenAd;
                this.zza.zzg();
            } catch (RemoteException e) {
                zzbzt.zzh("", e);
            }
            return new zzbqi(this.zzb);
        }
        zzbzt.zzj("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
        try {
            this.zza.zze("Adapter returned null.");
            return null;
        } catch (RemoteException e2) {
            zzbzt.zzh("", e2);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String str) {
        onFailure(new AdError(0, str, AdError.UNDEFINED_DOMAIN, null));
    }
}
