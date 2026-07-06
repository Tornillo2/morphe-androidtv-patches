package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbny;
import com.google.android.gms.internal.ads.zzbsy;
import com.google.android.gms.internal.ads.zzbzw;
import com.google.android.gms.internal.ads.zzbzx;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzao extends zzax {
    public final /* synthetic */ Context zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzbny zzc;
    public final /* synthetic */ zzaw zzd;

    public zzao(zzaw zzawVar, Context context, String str, zzbny zzbnyVar) {
        this.zzd = zzawVar;
        this.zza = context;
        this.zzb = str;
        this.zzc = zzbnyVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final Object zza() {
        zzaw.zzt(this.zza, "native_ad");
        return new zzeu();
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final /* bridge */ /* synthetic */ Object zzb(zzce zzceVar) throws RemoteException {
        return zzceVar.zzb(ObjectWrapper.wrap(this.zza), this.zzb, this.zzc, 231700000);
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final Object zzc() throws RemoteException {
        zzbbk.zza(this.zza);
        if (!((Boolean) zzba.zza.zzd.zzb(zzbbk.zzjl)).booleanValue()) {
            return this.zzd.zzb.zza(this.zza, this.zzb, this.zzc);
        }
        try {
            IBinder iBinderZze = ((zzbr) zzbzx.zzb(this.zza, "com.google.android.gms.ads.ChimeraAdLoaderBuilderCreatorImpl", zzan.zza)).zze(ObjectWrapper.wrap(this.zza), this.zzb, this.zzc, 231700000);
            if (iBinderZze == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZze.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            return iInterfaceQueryLocalInterface instanceof zzbq ? (zzbq) iInterfaceQueryLocalInterface : new zzbo(iBinderZze);
        } catch (RemoteException e) {
            e = e;
            this.zzd.zzh = zzbsy.zza(this.zza);
            this.zzd.zzh.zzf(e, "ClientApiBroker.createAdLoaderBuilder");
            return null;
        } catch (zzbzw e2) {
            e = e2;
            this.zzd.zzh = zzbsy.zza(this.zza);
            this.zzd.zzh.zzf(e, "ClientApiBroker.createAdLoaderBuilder");
            return null;
        } catch (NullPointerException e3) {
            e = e3;
            this.zzd.zzh = zzbsy.zza(this.zza);
            this.zzd.zzh.zzf(e, "ClientApiBroker.createAdLoaderBuilder");
            return null;
        }
    }
}
