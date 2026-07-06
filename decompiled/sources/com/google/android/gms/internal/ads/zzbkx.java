package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbkx extends zzato implements zzbkz {
    public zzbkx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.instream.client.IInstreamAdLoadCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzbkz
    public final void zze(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzbh(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbkz
    public final void zzf(zze zzeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzeVar);
        zzbh(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbkz
    public final void zzg(zzbkt zzbktVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbktVar);
        zzbh(1, parcelZza);
    }
}
