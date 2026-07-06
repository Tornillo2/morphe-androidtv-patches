package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbjj extends zzato implements zzbjl {
    public zzbjj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.h5.client.IH5AdsManagerCreator");
    }

    @Override // com.google.android.gms.internal.ads.zzbjl
    public final zzbji zze(IObjectWrapper iObjectWrapper, zzbny zzbnyVar, int i, zzbjf zzbjfVar) throws RemoteException {
        zzbji zzbjgVar;
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        zzatq.zzf(parcelZza, zzbjfVar);
        Parcel parcelZzbg = zzbg(1, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbjgVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.h5.client.IH5AdsManager");
            zzbjgVar = iInterfaceQueryLocalInterface instanceof zzbji ? (zzbji) iInterfaceQueryLocalInterface : new zzbjg(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbjgVar;
    }
}
