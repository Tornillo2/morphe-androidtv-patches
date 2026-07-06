package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzato;
import com.google.android.gms.internal.ads.zzatq;
import com.google.android.gms.internal.ads.zzbny;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbv extends zzato implements IInterface {
    public zzbv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdManagerCreator");
    }

    public final IBinder zze(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, zzbny zzbnyVar, int i, int i2) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzqVar);
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        parcelZza.writeInt(i2);
        Parcel parcelZzbg = zzbg(2, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        parcelZzbg.recycle();
        return strongBinder;
    }
}
