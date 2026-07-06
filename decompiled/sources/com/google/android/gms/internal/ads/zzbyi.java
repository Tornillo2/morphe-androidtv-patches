package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbyi extends zzato implements zzbyk {
    public zzbyi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.signals.ISignalGenerator");
    }

    @Override // com.google.android.gms.internal.ads.zzbyk
    public final void zze(IObjectWrapper iObjectWrapper, zzbyo zzbyoVar, zzbyh zzbyhVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzbyoVar);
        zzatq.zzf(parcelZza, zzbyhVar);
        zzbh(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbyk
    public final void zzf(zzbst zzbstVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzbstVar);
        zzbh(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbyk
    public final void zzg(List list, IObjectWrapper iObjectWrapper, zzbsk zzbskVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbskVar);
        zzbh(10, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbyk
    public final void zzh(List list, IObjectWrapper iObjectWrapper, zzbsk zzbskVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbskVar);
        zzbh(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbyk
    public final void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(8, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbyk
    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbyk
    public final void zzk(List list, IObjectWrapper iObjectWrapper, zzbsk zzbskVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbskVar);
        zzbh(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbyk
    public final void zzl(List list, IObjectWrapper iObjectWrapper, zzbsk zzbskVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbskVar);
        zzbh(5, parcelZza);
    }
}
