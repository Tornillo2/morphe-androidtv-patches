package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbrt extends zzato implements zzbrv {
    public zzbrt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final boolean zzF() throws RemoteException {
        Parcel parcelZzbg = zzbg(11, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzg(int i, int i2, Intent intent) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        parcelZza.writeInt(i2);
        zzatq.zzd(parcelZza, intent);
        zzbh(12, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzh() throws RemoteException {
        zzbh(10, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(13, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzk(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, bundle);
        zzbh(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzl() throws RemoteException {
        zzbh(8, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzn() throws RemoteException {
        zzbh(5, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzo(int i, String[] strArr, int[] iArr) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        parcelZza.writeStringArray(strArr);
        parcelZza.writeIntArray(iArr);
        zzbh(15, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzp() throws RemoteException {
        zzbh(2, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzq() throws RemoteException {
        zzbh(4, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzr(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, bundle);
        Parcel parcelZzbg = zzbg(6, parcelZza);
        if (parcelZzbg.readInt() != 0) {
            bundle.readFromParcel(parcelZzbg);
        }
        parcelZzbg.recycle();
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzs() throws RemoteException {
        zzbh(3, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzt() throws RemoteException {
        zzbh(7, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzu() throws RemoteException {
        zzbh(14, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbrv
    public final void zzw() throws RemoteException {
        zzbh(9, zza());
    }
}
