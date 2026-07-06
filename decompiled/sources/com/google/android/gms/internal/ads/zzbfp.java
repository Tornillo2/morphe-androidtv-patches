package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbfp extends zzato implements zzbfr {
    public zzbfp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbfr
    public final void zze(zzbfh zzbfhVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbfhVar);
        zzbh(1, parcelZza);
    }
}
