package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbny;
import com.google.android.gms.internal.ads.zzbwb;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzav extends zzax {
    public final /* synthetic */ Context zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzbny zzc;
    public final /* synthetic */ zzaw zzd;

    public zzav(zzaw zzawVar, Context context, String str, zzbny zzbnyVar) {
        this.zzd = zzawVar;
        this.zza = context;
        this.zzb = str;
        this.zzc = zzbnyVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final Object zza() {
        zzaw.zzt(this.zza, "rewarded");
        return new zzfc();
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final /* bridge */ /* synthetic */ Object zzb(zzce zzceVar) throws RemoteException {
        return zzceVar.zzo(ObjectWrapper.wrap(this.zza), this.zzb, this.zzc, 231700000);
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final /* bridge */ /* synthetic */ Object zzc() throws RemoteException {
        return zzbwb.zza(this.zza, this.zzb, this.zzc);
    }
}
