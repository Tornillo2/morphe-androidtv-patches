package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbsy;
import com.google.android.gms.internal.ads.zzbzw;
import com.google.android.gms.internal.ads.zzbzx;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzaq extends zzax {
    public final /* synthetic */ Context zza;
    public final /* synthetic */ zzaw zzb;

    public zzaq(zzaw zzawVar, Context context) {
        this.zzb = zzawVar;
        this.zza = context;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final Object zza() {
        zzaw.zzt(this.zza, "mobile_ads_settings");
        return new zzey();
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final /* bridge */ /* synthetic */ Object zzb(zzce zzceVar) throws RemoteException {
        return zzceVar.zzg(ObjectWrapper.wrap(this.zza), 231700000);
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final Object zzc() throws RemoteException {
        zzbbk.zza(this.zza);
        if (!((Boolean) zzba.zza.zzd.zzb(zzbbk.zzjl)).booleanValue()) {
            return this.zzb.zzc.zza(this.zza);
        }
        try {
            IBinder iBinderZze = ((zzcp) zzbzx.zzb(this.zza, "com.google.android.gms.ads.ChimeraMobileAdsSettingManagerCreatorImpl", zzap.zza)).zze(ObjectWrapper.wrap(this.zza), 231700000);
            if (iBinderZze == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZze.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            return iInterfaceQueryLocalInterface instanceof zzco ? (zzco) iInterfaceQueryLocalInterface : new zzcm(iBinderZze);
        } catch (RemoteException e) {
            e = e;
            this.zzb.zzh = zzbsy.zza(this.zza);
            this.zzb.zzh.zzf(e, "ClientApiBroker.getMobileAdsSettingsManager");
            return null;
        } catch (zzbzw e2) {
            e = e2;
            this.zzb.zzh = zzbsy.zza(this.zza);
            this.zzb.zzh.zzf(e, "ClientApiBroker.getMobileAdsSettingsManager");
            return null;
        } catch (NullPointerException e3) {
            e = e3;
            this.zzb.zzh = zzbsy.zza(this.zza);
            this.zzb.zzh.zzf(e, "ClientApiBroker.getMobileAdsSettingsManager");
            return null;
        }
    }
}
