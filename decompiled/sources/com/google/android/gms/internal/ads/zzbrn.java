package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbrn extends zzatp implements zzbro {
    public zzbrn() {
        super("com.google.android.gms.ads.internal.offline.IOfflineUtils");
    }

    public static zzbro zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.offline.IOfflineUtils");
        return iInterfaceQueryLocalInterface instanceof zzbro ? (zzbro) iInterfaceQueryLocalInterface : new zzbrm(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            Intent intent = (Intent) zzatq.zza(parcel, Intent.CREATOR);
            zzatq.zzc(parcel);
            zze(intent);
        } else if (i == 2) {
            IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            String string = parcel.readString();
            String string2 = parcel.readString();
            zzatq.zzc(parcel);
            zzg(iObjectWrapperAsInterface, string, string2);
        } else {
            if (i != 3) {
                return false;
            }
            zzf();
        }
        parcel2.writeNoException();
        return true;
    }
}
