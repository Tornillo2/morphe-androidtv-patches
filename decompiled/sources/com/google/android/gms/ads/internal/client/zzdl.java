package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzato;
import com.google.android.gms.internal.ads.zzatq;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdl extends zzato implements zzdn {
    public zzdl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IResponseInfo");
    }

    @Override // com.google.android.gms.ads.internal.client.zzdn
    public final Bundle zze() throws RemoteException {
        Parcel parcelZzbg = zzbg(5, zza());
        Bundle bundle = (Bundle) zzatq.zza(parcelZzbg, Bundle.CREATOR);
        parcelZzbg.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdn
    public final zzu zzf() throws RemoteException {
        Parcel parcelZzbg = zzbg(4, zza());
        zzu zzuVar = (zzu) zzatq.zza(parcelZzbg, zzu.CREATOR);
        parcelZzbg.recycle();
        return zzuVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdn
    public final String zzg() throws RemoteException {
        Parcel parcelZzbg = zzbg(1, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdn
    public final String zzh() throws RemoteException {
        Parcel parcelZzbg = zzbg(6, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdn
    public final String zzi() throws RemoteException {
        Parcel parcelZzbg = zzbg(2, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdn
    public final List zzj() throws RemoteException {
        Parcel parcelZzbg = zzbg(3, zza());
        ArrayList arrayListCreateTypedArrayList = parcelZzbg.createTypedArrayList(zzu.CREATOR);
        parcelZzbg.recycle();
        return arrayListCreateTypedArrayList;
    }
}
