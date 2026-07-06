package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbvq extends zzato implements zzbvs {
    public zzbvq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zze() throws RemoteException {
        zzbh(7, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzf() throws RemoteException {
        zzbh(6, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzg() throws RemoteException {
        zzbh(2, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzh(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzbh(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzi(zze zzeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzeVar);
        zzbh(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzj() throws RemoteException {
        zzbh(1, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbvs
    public final void zzk(zzbvm zzbvmVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbvmVar);
        zzbh(3, parcelZza);
    }
}
