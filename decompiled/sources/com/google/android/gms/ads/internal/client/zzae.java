package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbny;
import com.google.android.gms.internal.ads.zzbyn;
import com.google.android.gms.internal.ads.zzbzw;
import com.google.android.gms.internal.ads.zzbzx;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzae extends zzax {
    public final /* synthetic */ Context zza;
    public final /* synthetic */ zzbny zzb;

    public zzae(zzaw zzawVar, Context context, zzbny zzbnyVar) {
        this.zza = context;
        this.zzb = zzbnyVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final /* bridge */ /* synthetic */ Object zza() {
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final /* bridge */ /* synthetic */ Object zzb(zzce zzceVar) throws RemoteException {
        return zzceVar.zzp(ObjectWrapper.wrap(this.zza), this.zzb, 231700000);
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final /* bridge */ /* synthetic */ Object zzc() throws RemoteException {
        try {
            return ((zzbyn) zzbzx.zzb(this.zza, "com.google.android.gms.ads.DynamiteSignalGeneratorCreatorImpl", zzad.zza)).zze(ObjectWrapper.wrap(this.zza), this.zzb, 231700000);
        } catch (RemoteException | zzbzw | NullPointerException unused) {
            return null;
        }
    }
}
