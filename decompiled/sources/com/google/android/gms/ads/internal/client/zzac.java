package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbny;
import com.google.android.gms.internal.ads.zzbsy;
import com.google.android.gms.internal.ads.zzbzw;
import com.google.android.gms.internal.ads.zzbzx;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzac extends zzax {
    public final /* synthetic */ Context zza;
    public final /* synthetic */ zzbny zzb;

    public zzac(zzaw zzawVar, Context context, zzbny zzbnyVar) {
        this.zza = context;
        this.zzb = zzbnyVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final /* bridge */ /* synthetic */ Object zza() {
        zzaw.zzt(this.zza, "out_of_context_tester");
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final Object zzb(zzce zzceVar) throws RemoteException {
        IObjectWrapper iObjectWrapperWrap = ObjectWrapper.wrap(this.zza);
        zzbbk.zza(this.zza);
        if (((Boolean) zzba.zza.zzd.zzb(zzbbk.zziO)).booleanValue()) {
            return zzceVar.zzh(iObjectWrapperWrap, this.zzb, 231700000);
        }
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final Object zzc() throws RemoteException {
        IObjectWrapper iObjectWrapperWrap = ObjectWrapper.wrap(this.zza);
        zzbbk.zza(this.zza);
        if (((Boolean) zzba.zza.zzd.zzb(zzbbk.zziO)).booleanValue()) {
            try {
                return ((zzdk) zzbzx.zzb(this.zza, "com.google.android.gms.ads.DynamiteOutOfContextTesterCreatorImpl", zzab.zza)).zze(iObjectWrapperWrap, this.zzb, 231700000);
            } catch (RemoteException | zzbzw | NullPointerException e) {
                zzbsy.zza(this.zza).zzf(e, "ClientApiBroker.getOutOfContextTester");
            }
        }
        return null;
    }
}
