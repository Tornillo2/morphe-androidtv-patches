package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbfs extends zzato implements zzbfu {
    public zzbfs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbfu
    public final void zze(zzbfk zzbfkVar, String str) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbfkVar);
        parcelZza.writeString(str);
        zzbh(1, parcelZza);
    }
}
