package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbvx extends zzato implements IInterface {
    public zzbvx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.rewarded.client.IRewardedAdSkuListener");
    }

    public final void zze(zzbvm zzbvmVar, String str, String str2) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbvmVar);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzbh(2, parcelZza);
    }
}
