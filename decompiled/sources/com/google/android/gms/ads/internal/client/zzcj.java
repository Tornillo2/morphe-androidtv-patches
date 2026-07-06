package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzato;
import com.google.android.gms.internal.ads.zzatq;
import com.google.android.gms.internal.ads.zzbnx;
import com.google.android.gms.internal.ads.zzbny;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcj extends zzato implements zzcl {
    public zzcj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.ILiteSdkInfo");
    }

    @Override // com.google.android.gms.ads.internal.client.zzcl
    public final zzbny getAdapterCreator() throws RemoteException {
        Parcel parcelZzbg = zzbg(2, zza());
        zzbny zzbnyVarZzf = zzbnx.zzf(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbnyVarZzf;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcl
    public final zzen getLiteSdkVersion() throws RemoteException {
        Parcel parcelZzbg = zzbg(1, zza());
        zzen zzenVar = (zzen) zzatq.zza(parcelZzbg, zzen.CREATOR);
        parcelZzbg.recycle();
        return zzenVar;
    }
}
