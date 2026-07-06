package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.android.gms.ads.internal.client.zzbu;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbgw implements Runnable {
    public final /* synthetic */ AdManagerAdView zza;
    public final /* synthetic */ zzbu zzb;
    public final /* synthetic */ zzbgx zzc;

    public zzbgw(zzbgx zzbgxVar, AdManagerAdView adManagerAdView, zzbu zzbuVar) {
        this.zzc = zzbgxVar;
        this.zza = adManagerAdView;
        this.zzb = zzbuVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AdManagerAdView adManagerAdView = this.zza;
        if (adManagerAdView.zza.zzz(this.zzb)) {
            this.zzc.zza.onAdManagerAdViewLoaded(this.zza);
        } else {
            zzbzt.zzj("Could not bind.");
        }
    }
}
