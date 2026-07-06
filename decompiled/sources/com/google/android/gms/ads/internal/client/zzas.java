package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbet;
import com.google.android.gms.internal.ads.zzbex;
import com.google.android.gms.internal.ads.zzbsy;
import com.google.android.gms.internal.ads.zzbzw;
import com.google.android.gms.internal.ads.zzbzx;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzas extends zzax {
    public final /* synthetic */ FrameLayout zza;
    public final /* synthetic */ FrameLayout zzb;
    public final /* synthetic */ Context zzc;
    public final /* synthetic */ zzaw zzd;

    public zzas(zzaw zzawVar, FrameLayout frameLayout, FrameLayout frameLayout2, Context context) {
        this.zzd = zzawVar;
        this.zza = frameLayout;
        this.zzb = frameLayout2;
        this.zzc = context;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final Object zza() {
        zzaw.zzt(this.zzc, "native_ad_view_delegate");
        return new zzez();
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    public final Object zzb(zzce zzceVar) throws RemoteException {
        return zzceVar.zzi(ObjectWrapper.wrap(this.zza), new ObjectWrapper(this.zzb));
    }

    @Override // com.google.android.gms.ads.internal.client.zzax
    @Nullable
    public final Object zzc() throws RemoteException {
        zzbbk.zza(this.zzc);
        if (!((Boolean) zzba.zza.zzd.zzb(zzbbk.zzjl)).booleanValue()) {
            return this.zzd.zzd.zza(this.zzc, this.zza, this.zzb);
        }
        try {
            return zzbet.zzbx(((zzbex) zzbzx.zzb(this.zzc, "com.google.android.gms.ads.ChimeraNativeAdViewDelegateCreatorImpl", zzar.zza)).zze(ObjectWrapper.wrap(this.zzc), new ObjectWrapper(this.zza), new ObjectWrapper(this.zzb), 231700000));
        } catch (RemoteException | zzbzw | NullPointerException e) {
            this.zzd.zzh = zzbsy.zza(this.zzc);
            this.zzd.zzh.zzf(e, "ClientApiBroker.createNativeAdViewDelegate");
            return null;
        }
    }
}
