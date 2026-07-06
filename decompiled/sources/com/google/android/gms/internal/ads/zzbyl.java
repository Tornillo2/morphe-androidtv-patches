package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbyl extends zzato implements zzbyn {
    public zzbyl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.signals.ISignalGeneratorCreator");
    }

    @Override // com.google.android.gms.internal.ads.zzbyn
    public final zzbyk zze(IObjectWrapper iObjectWrapper, zzbny zzbnyVar, int i) throws RemoteException {
        zzbyk zzbyiVar;
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(2, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbyiVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.signals.ISignalGenerator");
            zzbyiVar = iInterfaceQueryLocalInterface instanceof zzbyk ? (zzbyk) iInterfaceQueryLocalInterface : new zzbyi(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbyiVar;
    }
}
