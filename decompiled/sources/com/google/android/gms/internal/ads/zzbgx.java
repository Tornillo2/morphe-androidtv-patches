package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.android.gms.ads.formats.OnAdManagerAdViewLoadedListener;
import com.google.android.gms.ads.internal.client.zzbu;
import com.google.android.gms.ads.internal.client.zzg;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbgx extends zzbga {
    public final OnAdManagerAdViewLoadedListener zza;

    public zzbgx(OnAdManagerAdViewLoadedListener onAdManagerAdViewLoadedListener) {
        this.zza = onAdManagerAdViewLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzbgb
    public final void zze(zzbu zzbuVar, IObjectWrapper iObjectWrapper) {
        if (zzbuVar == null || iObjectWrapper == null) {
            return;
        }
        AdManagerAdView adManagerAdView = new AdManagerAdView((Context) ObjectWrapper.unwrap(iObjectWrapper));
        try {
            if (zzbuVar.zzi() instanceof zzg) {
                zzg zzgVar = (zzg) zzbuVar.zzi();
                adManagerAdView.setAdListener(zzgVar != null ? zzgVar.zza : null);
            }
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
        try {
            if (zzbuVar.zzj() instanceof zzaum) {
                zzaum zzaumVar = (zzaum) zzbuVar.zzj();
                adManagerAdView.setAppEventListener(zzaumVar != null ? zzaumVar.zza : null);
            }
        } catch (RemoteException e2) {
            zzbzt.zzh("", e2);
        }
        zzbzm.zza.post(new zzbgw(this, adManagerAdView, zzbuVar));
    }
}
