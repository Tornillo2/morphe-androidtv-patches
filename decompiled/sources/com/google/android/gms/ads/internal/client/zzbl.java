package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzato;
import com.google.android.gms.internal.ads.zzatq;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbl extends zzato implements zzbn {
    public zzbl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoader");
    }

    @Override // com.google.android.gms.ads.internal.client.zzbn
    public final String zze() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbn
    public final String zzf() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbn
    public final void zzg(zzl zzlVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzlVar);
        zzbh(1, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbn
    public final void zzh(zzl zzlVar, int i) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzlVar);
        parcelZza.writeInt(i);
        zzbh(5, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbn
    public final boolean zzi() throws RemoteException {
        Parcel parcelZzbg = zzbg(3, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }
}
