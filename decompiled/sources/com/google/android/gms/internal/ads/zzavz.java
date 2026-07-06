package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzbu;
import com.google.android.gms.ads.internal.client.zzdx;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzavz {
    public zzbu zza;
    public final Context zzb;
    public final String zzc;
    public final zzdx zzd;

    @AppOpenAd.AppOpenAdOrientation
    public final int zze;
    public final AppOpenAd.AppOpenAdLoadCallback zzf;
    public final zzbnv zzg = new zzbnv();
    public final zzp zzh = zzp.zza;

    public zzavz(Context context, String str, zzdx zzdxVar, @AppOpenAd.AppOpenAdOrientation int i, AppOpenAd.AppOpenAdLoadCallback appOpenAdLoadCallback) {
        this.zzb = context;
        this.zzc = str;
        this.zzd = zzdxVar;
        this.zze = i;
        this.zzf = appOpenAdLoadCallback;
    }

    public final void zza() {
        try {
            zzbu zzbuVarZzd = zzay.zza().zzd(this.zzb, zzq.zzb(), this.zzc, this.zzg);
            this.zza = zzbuVarZzd;
            if (zzbuVarZzd != null) {
                int i = this.zze;
                if (i != 3) {
                    zzbuVarZzd.zzI(new zzw(i));
                }
                this.zza.zzH(new zzavm(this.zzf, this.zzc));
                this.zza.zzaa(this.zzh.zza(this.zzb, this.zzd));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }
}
