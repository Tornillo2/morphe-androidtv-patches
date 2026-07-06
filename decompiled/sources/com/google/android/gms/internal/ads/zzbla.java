package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdLoadCallback;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AppEventListener;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzbb;
import com.google.android.gms.ads.internal.client.zzbu;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzdx;
import com.google.android.gms.ads.internal.client.zzfe;
import com.google.android.gms.ads.internal.client.zzh;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbla extends AdManagerInterstitialAd {
    public final Context zza;
    public final zzp zzb;
    public final zzbu zzc;
    public final String zzd;
    public final zzbnv zze;

    @Nullable
    public AppEventListener zzf;

    @Nullable
    public FullScreenContentCallback zzg;

    @Nullable
    public OnPaidEventListener zzh;

    public zzbla(Context context, String str) {
        zzbnv zzbnvVar = new zzbnv();
        this.zze = zzbnvVar;
        this.zza = context;
        this.zzd = str;
        this.zzb = zzp.zza;
        this.zzc = zzay.zza().zze(context, new zzq(), str, zzbnvVar);
    }

    @Override // com.google.android.gms.ads.interstitial.InterstitialAd
    public final String getAdUnitId() {
        return this.zzd;
    }

    @Override // com.google.android.gms.ads.admanager.AdManagerInterstitialAd
    @Nullable
    public final AppEventListener getAppEventListener() {
        return this.zzf;
    }

    @Override // com.google.android.gms.ads.interstitial.InterstitialAd
    @Nullable
    public final FullScreenContentCallback getFullScreenContentCallback() {
        return this.zzg;
    }

    @Override // com.google.android.gms.ads.interstitial.InterstitialAd
    @Nullable
    public final OnPaidEventListener getOnPaidEventListener() {
        return this.zzh;
    }

    @Override // com.google.android.gms.ads.interstitial.InterstitialAd
    @NonNull
    public final ResponseInfo getResponseInfo() {
        zzdn zzdnVarZzk = null;
        try {
            zzbu zzbuVar = this.zzc;
            if (zzbuVar != null) {
                zzdnVarZzk = zzbuVar.zzk();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
        return new ResponseInfo(zzdnVarZzk);
    }

    @Override // com.google.android.gms.ads.admanager.AdManagerInterstitialAd
    public final void setAppEventListener(@Nullable AppEventListener appEventListener) {
        try {
            this.zzf = appEventListener;
            zzbu zzbuVar = this.zzc;
            if (zzbuVar != null) {
                zzbuVar.zzG(appEventListener != null ? new zzaum(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.interstitial.InterstitialAd
    public final void setFullScreenContentCallback(@Nullable FullScreenContentCallback fullScreenContentCallback) {
        try {
            this.zzg = fullScreenContentCallback;
            zzbu zzbuVar = this.zzc;
            if (zzbuVar != null) {
                zzbuVar.zzJ(new zzbb(fullScreenContentCallback));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.interstitial.InterstitialAd
    public final void setImmersiveMode(boolean z) {
        try {
            zzbu zzbuVar = this.zzc;
            if (zzbuVar != null) {
                zzbuVar.zzL(z);
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.interstitial.InterstitialAd
    public final void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener) {
        try {
            this.zzh = onPaidEventListener;
            zzbu zzbuVar = this.zzc;
            if (zzbuVar != null) {
                zzbuVar.zzP(new zzfe(onPaidEventListener));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.interstitial.InterstitialAd
    public final void show(@NonNull Activity activity) {
        if (activity == null) {
            zzbzt.zzj("The activity for show is null, will proceed with show using the context provided when loading the ad.");
        }
        try {
            zzbu zzbuVar = this.zzc;
            if (zzbuVar != null) {
                zzbuVar.zzW(ObjectWrapper.wrap(activity));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final void zza(zzdx zzdxVar, AdLoadCallback adLoadCallback) {
        try {
            zzbu zzbuVar = this.zzc;
            if (zzbuVar != null) {
                zzbuVar.zzy(this.zzb.zza(this.zza, zzdxVar), new zzh(adLoadCallback, this));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            adLoadCallback.onAdFailedToLoad(new LoadAdError(0, "Internal Error.", MobileAds.ERROR_DOMAIN, null, null));
        }
    }
}
