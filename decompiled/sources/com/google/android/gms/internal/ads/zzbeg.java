package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbeg extends zzato implements zzbei {
    public zzbeg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }

    @Override // com.google.android.gms.internal.ads.zzbei
    public final String zzg() throws RemoteException {
        Parcel parcelZzbg = zzbg(2, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbei
    public final List zzh() throws RemoteException {
        Parcel parcelZzbg = zzbg(3, zza());
        ArrayList arrayListZzb = zzatq.zzb(parcelZzbg);
        parcelZzbg.recycle();
        return arrayListZzb;
    }
}
