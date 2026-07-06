package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbnw extends zzato implements zzbny {
    public zzbnw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }

    @Override // com.google.android.gms.internal.ads.zzbny
    public final zzbob zzb(String str) throws RemoteException {
        zzbob zzbnzVar;
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        Parcel parcelZzbg = zzbg(1, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbnzVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            zzbnzVar = iInterfaceQueryLocalInterface instanceof zzbob ? (zzbob) iInterfaceQueryLocalInterface : new zzbnz(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbnzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbny
    public final zzbpv zzc(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        Parcel parcelZzbg = zzbg(3, parcelZza);
        zzbpv zzbpvVarZzb = zzbpu.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbpvVarZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbny
    public final boolean zzd(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        Parcel parcelZzbg = zzbg(4, parcelZza);
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbny
    public final boolean zze(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        Parcel parcelZzbg = zzbg(2, parcelZza);
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }
}
