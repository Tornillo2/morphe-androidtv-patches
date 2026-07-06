package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzbs$$ExternalSyntheticOutline0;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbof extends zzato implements zzboh {
    public zzbof(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationInterscrollerAd");
    }

    @Override // com.google.android.gms.internal.ads.zzboh
    public final IObjectWrapper zze() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(1, zza()));
    }

    @Override // com.google.android.gms.internal.ads.zzboh
    public final boolean zzf() throws RemoteException {
        Parcel parcelZzbg = zzbg(2, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }
}
