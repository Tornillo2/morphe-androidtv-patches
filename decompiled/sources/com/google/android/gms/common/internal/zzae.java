package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzae extends com.google.android.gms.internal.common.zza implements zzag {
    public zzae(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    @Override // com.google.android.gms.common.internal.zzag
    public final com.google.android.gms.common.zzq zze(com.google.android.gms.common.zzo zzoVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzc(parcelZza, zzoVar);
        Parcel parcelZzB = zzB(6, parcelZza);
        com.google.android.gms.common.zzq zzqVar = (com.google.android.gms.common.zzq) com.google.android.gms.internal.common.zzc.zza(parcelZzB, com.google.android.gms.common.zzq.CREATOR);
        parcelZzB.recycle();
        return zzqVar;
    }

    @Override // com.google.android.gms.common.internal.zzag
    public final com.google.android.gms.common.zzq zzf(com.google.android.gms.common.zzo zzoVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzc(parcelZza, zzoVar);
        Parcel parcelZzB = zzB(8, parcelZza);
        com.google.android.gms.common.zzq zzqVar = (com.google.android.gms.common.zzq) com.google.android.gms.internal.common.zzc.zza(parcelZzB, com.google.android.gms.common.zzq.CREATOR);
        parcelZzB.recycle();
        return zzqVar;
    }

    @Override // com.google.android.gms.common.internal.zzag
    public final boolean zzg() throws RemoteException {
        Parcel parcelZzB = zzB(9, zza());
        boolean zZzf = com.google.android.gms.internal.common.zzc.zzf(parcelZzB);
        parcelZzB.recycle();
        return zZzf;
    }

    @Override // com.google.android.gms.common.internal.zzag
    public final boolean zzh(com.google.android.gms.common.zzs zzsVar, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzc(parcelZza, zzsVar);
        com.google.android.gms.internal.common.zzc.zze(parcelZza, iObjectWrapper);
        Parcel parcelZzB = zzB(5, parcelZza);
        boolean z = parcelZzB.readInt() != 0;
        parcelZzB.recycle();
        return z;
    }

    @Override // com.google.android.gms.common.internal.zzag
    public final boolean zzi() throws RemoteException {
        Parcel parcelZzB = zzB(7, zza());
        boolean zZzf = com.google.android.gms.internal.common.zzc.zzf(parcelZzB);
        parcelZzB.recycle();
        return zZzf;
    }
}
