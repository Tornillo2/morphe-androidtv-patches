package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbsj extends zzatp implements zzbsk {
    public zzbsj() {
        super("com.google.android.gms.ads.internal.query.IUpdateUrlsCallback");
    }

    public static zzbsk zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.query.IUpdateUrlsCallback");
        return iInterfaceQueryLocalInterface instanceof zzbsk ? (zzbsk) iInterfaceQueryLocalInterface : new zzbsi(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            ArrayList arrayListCreateTypedArrayList = parcel.createTypedArrayList(Uri.CREATOR);
            zzatq.zzc(parcel);
            zzf(arrayListCreateTypedArrayList);
        } else {
            if (i != 2) {
                return false;
            }
            String string = parcel.readString();
            zzatq.zzc(parcel);
            zze(string);
        }
        parcel2.writeNoException();
        return true;
    }
}
