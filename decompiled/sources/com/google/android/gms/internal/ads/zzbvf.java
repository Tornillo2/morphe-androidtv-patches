package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbvf extends zzato implements zzbvh {
    public zzbvf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(8, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zzg(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeInt(i);
        zzbh(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zzh(IObjectWrapper iObjectWrapper) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zzk(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zzl(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zzm(IObjectWrapper iObjectWrapper, zzbvi zzbviVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzbviVar);
        zzbh(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zzn(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(11, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvh
    public final void zzo(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(5, parcelZza);
    }
}
