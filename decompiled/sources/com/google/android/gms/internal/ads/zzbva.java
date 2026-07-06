package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbva extends zzato implements zzbvc {
    public zzbva(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbvc
    public final void zze(zzbuw zzbuwVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbuwVar);
        zzbh(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvc
    public final void zzf() throws RemoteException {
        zzbh(4, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvc
    public final void zzg(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzbh(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvc
    public final void zzh() throws RemoteException {
        zzbh(6, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvc
    public final void zzi() throws RemoteException {
        zzbh(1, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvc
    public final void zzj() throws RemoteException {
        zzbh(2, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvc
    public final void zzk() throws RemoteException {
        zzbh(8, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvc
    public final void zzl() throws RemoteException {
        zzbh(3, zza());
    }
}
