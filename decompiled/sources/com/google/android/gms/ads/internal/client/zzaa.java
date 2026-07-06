package com.google.android.gms.ads.internal.client;

import android.app.Activity;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbru;
import com.google.android.gms.internal.ads.zzbry;
import com.google.android.gms.internal.ads.zzbsy;
import com.google.android.gms.internal.ads.zzbzw;
import com.google.android.gms.internal.ads.zzbzx;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzaa extends zzax {
    public final /* synthetic */ Activity zza;
    public final /* synthetic */ zzaw zzb;

    public zzaa(zzaw zzawVar, Activity activity) {
        this.zzb = zzawVar;
        this.zza = activity;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final /* bridge */ /* synthetic */ Object zza() {
        zzaw.zzt(this.zza, "ad_overlay");
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final /* bridge */ /* synthetic */ Object zzb(zzce zzceVar) throws RemoteException {
        return zzceVar.zzm(ObjectWrapper.wrap(this.zza));
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final Object zzc() throws RemoteException {
        zzbbk.zza(this.zza);
        if (!((Boolean) zzba.zza.zzd.zzb(zzbbk.zzjl)).booleanValue()) {
            return this.zzb.zzf.zza(this.zza);
        }
        try {
            return zzbru.zzG(((zzbry) zzbzx.zzb(this.zza, "com.google.android.gms.ads.ChimeraAdOverlayCreatorImpl", zzz.zza)).zze(ObjectWrapper.wrap(this.zza)));
        } catch (RemoteException | zzbzw | NullPointerException e) {
            this.zzb.zzh = zzbsy.zza(this.zza.getApplicationContext());
            this.zzb.zzh.zzf(e, "ClientApiBroker.createAdOverlay");
            return null;
        }
    }
}
