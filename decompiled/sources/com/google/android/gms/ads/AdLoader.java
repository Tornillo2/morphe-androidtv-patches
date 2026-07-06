package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.OnAdManagerAdViewLoadedListener;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zzbn;
import com.google.android.gms.ads.internal.client.zzbq;
import com.google.android.gms.ads.internal.client.zzdx;
import com.google.android.gms.ads.internal.client.zzeu;
import com.google.android.gms.ads.internal.client.zzfl;
import com.google.android.gms.ads.internal.client.zzg;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeCustomFormatAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbdb;
import com.google.android.gms.internal.ads.zzbee;
import com.google.android.gms.internal.ads.zzbgv;
import com.google.android.gms.internal.ads.zzbgx;
import com.google.android.gms.internal.ads.zzbgy;
import com.google.android.gms.internal.ads.zzbnv;
import com.google.android.gms.internal.ads.zzbri;
import com.google.android.gms.internal.ads.zzbrk;
import com.google.android.gms.internal.ads.zzbzi;
import com.google.android.gms.internal.ads.zzbzt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AdLoader {
    public final zzp zza;
    public final Context zzb;
    public final zzbn zzc;

    public AdLoader(Context context, zzbn zzbnVar, zzp zzpVar) {
        this.zzb = context;
        this.zzc = zzbnVar;
        this.zza = zzpVar;
    }

    public boolean isLoading() {
        try {
            return this.zzc.zzi();
        } catch (RemoteException e) {
            zzbzt.zzk("Failed to check if ad is loading.", e);
            return false;
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(@NonNull AdRequest adRequest) {
        zzb(adRequest.zza);
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAds(@NonNull AdRequest adRequest, int i) {
        try {
            this.zzc.zzh(this.zza.zza(this.zzb, adRequest.zza), i);
        } catch (RemoteException e) {
            zzbzt.zzh("Failed to load ads.", e);
        }
    }

    public final /* synthetic */ void zza(zzdx zzdxVar) {
        try {
            this.zzc.zzg(this.zza.zza(this.zzb, zzdxVar));
        } catch (RemoteException e) {
            zzbzt.zzh("Failed to load ad.", e);
        }
    }

    public final void zzb(final zzdx zzdxVar) {
        zzbbk.zza(this.zzb);
        if (((Boolean) zzbdb.zzc.zze()).booleanValue()) {
            if (((Boolean) zzba.zza.zzd.zzb(zzbbk.zzjG)).booleanValue()) {
                zzbzi.zzb.execute(new Runnable() { // from class: com.google.android.gms.ads.zza
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zza.zza(zzdxVar);
                    }
                });
                return;
            }
        }
        try {
            this.zzc.zzg(this.zza.zza(this.zzb, zzdxVar));
        } catch (RemoteException e) {
            zzbzt.zzh("Failed to load ad.", e);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public final Context zza;
        public final zzbq zzb;

        public Builder(@NonNull Context context, @NonNull String str) {
            Preconditions.checkNotNull(context, "context cannot be null");
            zzbq zzbqVarZzc = zzay.zza().zzc(context, str, new zzbnv());
            this.zza = context;
            this.zzb = zzbqVarZzc;
        }

        @NonNull
        public AdLoader build() {
            try {
                return new AdLoader(this.zza, this.zzb.zze(), zzp.zza);
            } catch (RemoteException e) {
                zzbzt.zzh("Failed to build AdLoader.", e);
                return new AdLoader(this.zza, new zzeu().zzc(), zzp.zza);
            }
        }

        @NonNull
        public Builder forAdManagerAdView(@NonNull OnAdManagerAdViewLoadedListener onAdManagerAdViewLoadedListener, @NonNull AdSize... adSizeArr) {
            if (adSizeArr == null || adSizeArr.length <= 0) {
                throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
            }
            try {
                this.zzb.zzj(new zzbgx(onAdManagerAdViewLoadedListener), new zzq(this.zza, adSizeArr));
                return this;
            } catch (RemoteException e) {
                zzbzt.zzk("Failed to add Google Ad Manager banner ad listener", e);
                return this;
            }
        }

        @NonNull
        public Builder forCustomFormatAd(@NonNull String str, @NonNull NativeCustomFormatAd.OnCustomFormatAdLoadedListener onCustomFormatAdLoadedListener, @Nullable NativeCustomFormatAd.OnCustomClickListener onCustomClickListener) {
            zzbri zzbriVar = new zzbri(onCustomFormatAdLoadedListener, onCustomClickListener);
            try {
                this.zzb.zzh(str, zzbriVar.zzb(), zzbriVar.zza());
                return this;
            } catch (RemoteException e) {
                zzbzt.zzk("Failed to add custom format ad listener", e);
                return this;
            }
        }

        @NonNull
        @Deprecated
        public Builder forCustomTemplateAd(@NonNull String str, @NonNull NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, @Nullable NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
            zzbgv zzbgvVar = new zzbgv(onCustomTemplateAdLoadedListener, onCustomClickListener);
            try {
                this.zzb.zzh(str, zzbgvVar.zze(), zzbgvVar.zzd());
                return this;
            } catch (RemoteException e) {
                zzbzt.zzk("Failed to add custom template ad listener", e);
                return this;
            }
        }

        @NonNull
        public Builder forNativeAd(@NonNull NativeAd.OnNativeAdLoadedListener onNativeAdLoadedListener) {
            try {
                this.zzb.zzk(new zzbrk(onNativeAdLoadedListener));
                return this;
            } catch (RemoteException e) {
                zzbzt.zzk("Failed to add google native ad listener", e);
                return this;
            }
        }

        @NonNull
        @Deprecated
        public Builder forUnifiedNativeAd(@NonNull UnifiedNativeAd.OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
            try {
                this.zzb.zzk(new zzbgy(onUnifiedNativeAdLoadedListener));
                return this;
            } catch (RemoteException e) {
                zzbzt.zzk("Failed to add google native ad listener", e);
                return this;
            }
        }

        @NonNull
        public Builder withAdListener(@NonNull AdListener adListener) {
            try {
                this.zzb.zzl(new zzg(adListener));
                return this;
            } catch (RemoteException e) {
                zzbzt.zzk("Failed to set AdListener.", e);
                return this;
            }
        }

        @NonNull
        public Builder withAdManagerAdViewOptions(@NonNull AdManagerAdViewOptions adManagerAdViewOptions) {
            try {
                this.zzb.zzm(adManagerAdViewOptions);
                return this;
            } catch (RemoteException e) {
                zzbzt.zzk("Failed to specify Ad Manager banner ad options", e);
                return this;
            }
        }

        @NonNull
        @Deprecated
        public Builder withNativeAdOptions(@NonNull NativeAdOptions nativeAdOptions) {
            try {
                this.zzb.zzo(new zzbee(nativeAdOptions));
                return this;
            } catch (RemoteException e) {
                zzbzt.zzk("Failed to specify native ad options", e);
                return this;
            }
        }

        @NonNull
        public Builder withNativeAdOptions(@NonNull com.google.android.gms.ads.nativead.NativeAdOptions nativeAdOptions) {
            try {
                zzbq zzbqVar = this.zzb;
                boolean z = nativeAdOptions.zza;
                boolean z2 = nativeAdOptions.zzc;
                int i = nativeAdOptions.zzd;
                VideoOptions videoOptions = nativeAdOptions.zze;
                zzbqVar.zzo(new zzbee(4, z, -1, z2, i, videoOptions != null ? new zzfl(videoOptions) : null, nativeAdOptions.zzf, nativeAdOptions.zzb, nativeAdOptions.zzh, nativeAdOptions.zzg));
                return this;
            } catch (RemoteException e) {
                zzbzt.zzk("Failed to specify native ad options", e);
                return this;
            }
        }
    }

    public void loadAd(@NonNull AdManagerAdRequest adManagerAdRequest) {
        zzb(adManagerAdRequest.zza);
    }
}
