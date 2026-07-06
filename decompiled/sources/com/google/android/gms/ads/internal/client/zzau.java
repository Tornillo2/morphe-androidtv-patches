package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbez;
import com.google.android.gms.internal.ads.zzbfd;
import com.google.android.gms.internal.ads.zzbsy;
import com.google.android.gms.internal.ads.zzbzw;
import com.google.android.gms.internal.ads.zzbzx;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzau extends zzax {
    public final /* synthetic */ View zza;
    public final /* synthetic */ HashMap zzb;
    public final /* synthetic */ HashMap zzc;
    public final /* synthetic */ zzaw zzd;

    public zzau(zzaw zzawVar, View view, HashMap map, HashMap map2) {
        this.zzd = zzawVar;
        this.zza = view;
        this.zzb = map;
        this.zzc = map2;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final Object zza() {
        zzaw.zzt(this.zza.getContext(), "native_ad_view_holder_delegate");
        return new zzfa();
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final Object zzb(zzce zzceVar) throws RemoteException {
        return zzceVar.zzj(ObjectWrapper.wrap(this.zza), new ObjectWrapper(this.zzb), new ObjectWrapper(this.zzc));
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final Object zzc() throws RemoteException {
        zzbbk.zza(this.zza.getContext());
        if (!((Boolean) zzba.zza.zzd.zzb(zzbbk.zzjl)).booleanValue()) {
            return this.zzd.zzg.zza(this.zza, this.zzb, this.zzc);
        }
        try {
            return zzbez.zze(((zzbfd) zzbzx.zzb(this.zza.getContext(), "com.google.android.gms.ads.ChimeraNativeAdViewHolderDelegateCreatorImpl", zzat.zza)).zze(ObjectWrapper.wrap(this.zza), new ObjectWrapper(this.zzb), new ObjectWrapper(this.zzc)));
        } catch (RemoteException | zzbzw | NullPointerException e) {
            this.zzd.zzh = zzbsy.zza(this.zza.getContext());
            this.zzd.zzh.zzf(e, "ClientApiBroker.createNativeAdViewHolderDelegate");
            return null;
        }
    }
}
