package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzdx;
import com.google.android.gms.ads.internal.client.zzfd;
import com.google.android.gms.ads.internal.client.zzfe;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ParametersAreNonnullByDefault
public final class zzbvy extends RewardedAd {
    public final String zza;
    public final zzbvp zzb;
    public final Context zzc;
    public final zzbwh zzd = new zzbwh();

    @Nullable
    public OnAdMetadataChangedListener zze;

    @Nullable
    public OnPaidEventListener zzf;

    @Nullable
    public FullScreenContentCallback zzg;

    public zzbvy(Context context, String str) {
        this.zzc = context.getApplicationContext();
        this.zza = str;
        this.zzb = zzay.zza().zzq(context, str, new zzbnv());
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    public final Bundle getAdMetadata() {
        try {
            zzbvp zzbvpVar = this.zzb;
            if (zzbvpVar != null) {
                return zzbvpVar.zzb();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
        return new Bundle();
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    @NonNull
    public final String getAdUnitId() {
        return this.zza;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    @Nullable
    public final FullScreenContentCallback getFullScreenContentCallback() {
        return this.zzg;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    @Nullable
    public final OnAdMetadataChangedListener getOnAdMetadataChangedListener() {
        return this.zze;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    @Nullable
    public final OnPaidEventListener getOnPaidEventListener() {
        return this.zzf;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    @NonNull
    public final ResponseInfo getResponseInfo() {
        zzdn zzdnVarZzc = null;
        try {
            zzbvp zzbvpVar = this.zzb;
            if (zzbvpVar != null) {
                zzdnVarZzc = zzbvpVar.zzc();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
        return new ResponseInfo(zzdnVarZzc);
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    @NonNull
    public final RewardItem getRewardItem() {
        try {
            zzbvp zzbvpVar = this.zzb;
            zzbvm zzbvmVarZzd = zzbvpVar != null ? zzbvpVar.zzd() : null;
            return zzbvmVarZzd == null ? RewardItem.DEFAULT_REWARD : new zzbvz(zzbvmVarZzd);
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            return RewardItem.DEFAULT_REWARD;
        }
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    public final void setFullScreenContentCallback(@Nullable FullScreenContentCallback fullScreenContentCallback) {
        this.zzg = fullScreenContentCallback;
        this.zzd.zza = fullScreenContentCallback;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    public final void setImmersiveMode(boolean z) {
        try {
            zzbvp zzbvpVar = this.zzb;
            if (zzbvpVar != null) {
                zzbvpVar.zzh(z);
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    public final void setOnAdMetadataChangedListener(@Nullable OnAdMetadataChangedListener onAdMetadataChangedListener) {
        try {
            this.zze = onAdMetadataChangedListener;
            zzbvp zzbvpVar = this.zzb;
            if (zzbvpVar != null) {
                zzbvpVar.zzi(new zzfd(onAdMetadataChangedListener));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    public final void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener) {
        try {
            this.zzf = onPaidEventListener;
            zzbvp zzbvpVar = this.zzb;
            if (zzbvpVar != null) {
                zzbvpVar.zzj(new zzfe(onPaidEventListener));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    public final void setServerSideVerificationOptions(@Nullable ServerSideVerificationOptions serverSideVerificationOptions) {
        if (serverSideVerificationOptions != null) {
            try {
                zzbvp zzbvpVar = this.zzb;
                if (zzbvpVar != null) {
                    zzbvpVar.zzl(new zzbwd(serverSideVerificationOptions));
                }
            } catch (RemoteException e) {
                zzbzt.zzl("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.rewarded.RewardedAd
    public final void show(@NonNull Activity activity, @NonNull OnUserEarnedRewardListener onUserEarnedRewardListener) {
        this.zzd.zzb = onUserEarnedRewardListener;
        if (activity == null) {
            zzbzt.zzj("The activity for show is null, will proceed with show using the context provided when loading the ad.");
        }
        try {
            zzbvp zzbvpVar = this.zzb;
            if (zzbvpVar != null) {
                zzbvpVar.zzk(this.zzd);
                this.zzb.zzm(ObjectWrapper.wrap(activity));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final void zza(zzdx zzdxVar, RewardedAdLoadCallback rewardedAdLoadCallback) {
        try {
            zzbvp zzbvpVar = this.zzb;
            if (zzbvpVar != null) {
                zzbvpVar.zzf(zzp.zza.zza(this.zzc, zzdxVar), new zzbwc(rewardedAdLoadCallback, this));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }
}
