package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbph extends zzato implements zzbpj {
    public zzbph(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzbpj
    public final void zze(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString("Adapter returned null.");
        zzbh(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpj
    public final void zzf(zze zzeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzeVar);
        zzbh(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpj
    public final void zzg(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpj
    public final void zzh(zzboh zzbohVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbohVar);
        zzbh(4, parcelZza);
    }
}
