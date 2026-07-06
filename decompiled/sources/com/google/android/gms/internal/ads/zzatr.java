package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzatr extends zzato implements zzatt {
    public zzatr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.clearcut.IClearcut");
    }

    @Override // com.google.android.gms.internal.ads.zzatt
    public final void zze(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString("GMA_SDK");
        zzbh(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzatt
    public final void zzf() throws RemoteException {
        zzbh(3, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzatt
    public final void zzg(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzbh(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzatt
    public final void zzh(int[] iArr) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeIntArray(null);
        zzbh(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzatt
    public final void zzi(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(0);
        zzbh(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzatt
    public final void zzj(byte[] bArr) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeByteArray(bArr);
        zzbh(5, parcelZza);
    }
}
