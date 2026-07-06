package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzato;
import com.google.android.gms.internal.ads.zzatq;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdr extends zzato implements zzdt {
    public zzdr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
    }

    @Override // com.google.android.gms.ads.internal.client.zzdt
    public final void zze() throws RemoteException {
        zzbh(4, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzdt
    public final void zzf(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i = zzatq.zza;
        parcelZza.writeInt(z ? 1 : 0);
        zzbh(5, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzdt
    public final void zzg() throws RemoteException {
        zzbh(3, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzdt
    public final void zzh() throws RemoteException {
        zzbh(2, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzdt
    public final void zzi() throws RemoteException {
        zzbh(1, zza());
    }
}
