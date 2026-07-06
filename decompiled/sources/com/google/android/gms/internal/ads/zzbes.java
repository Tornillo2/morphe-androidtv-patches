package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzbs$$ExternalSyntheticOutline0;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbes extends zzato implements zzbeu {
    public zzbes(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
    }

    @Override // com.google.android.gms.internal.ads.zzbeu
    public final IObjectWrapper zzb(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(2, parcelZza));
    }

    @Override // com.google.android.gms.internal.ads.zzbeu
    public final void zzbs(String str, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbeu
    public final void zzbt(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbeu
    public final void zzbu(zzben zzbenVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbenVar);
        zzbh(8, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbeu
    public final void zzbv(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbeu
    public final void zzbw(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbeu
    public final void zzc() throws RemoteException {
        zzbh(4, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbeu
    public final void zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbeu
    public final void zze(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeInt(i);
        zzbh(5, parcelZza);
    }
}
