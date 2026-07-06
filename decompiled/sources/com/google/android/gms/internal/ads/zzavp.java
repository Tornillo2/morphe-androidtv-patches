package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzbu;
import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zzdm;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzavp extends zzato implements zzavr {
    public zzavp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.appopen.client.IAppOpenAd");
    }

    @Override // com.google.android.gms.internal.ads.zzavr
    public final zzbu zze() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzavr
    public final zzdn zzf() throws RemoteException {
        Parcel parcelZzbg = zzbg(5, zza());
        zzdn zzdnVarZzb = zzdm.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdnVarZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzavr
    public final void zzg(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i = zzatq.zza;
        parcelZza.writeInt(z ? 1 : 0);
        zzbh(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzavr
    public final void zzh(zzdg zzdgVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzdgVar);
        zzbh(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzavr
    public final void zzi(IObjectWrapper iObjectWrapper, zzavy zzavyVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzavyVar);
        zzbh(4, parcelZza);
    }
}
