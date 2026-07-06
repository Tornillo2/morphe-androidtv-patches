package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbrm extends zzato implements zzbro {
    public zzbrm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.offline.IOfflineUtils");
    }

    @Override // com.google.android.gms.internal.ads.zzbro
    public final void zze(Intent intent) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, intent);
        zzbh(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbro
    public final void zzf() throws RemoteException {
        zzbh(3, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbro
    public final void zzg(IObjectWrapper iObjectWrapper, String str, String str2) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzbh(2, parcelZza);
    }
}
