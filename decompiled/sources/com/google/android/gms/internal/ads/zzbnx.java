package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbnx extends zzatp implements zzbny {
    public zzbnx() {
        super("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }

    public static zzbny zzf(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        return iInterfaceQueryLocalInterface instanceof zzbny ? (zzbny) iInterfaceQueryLocalInterface : new zzbnw(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            String string = parcel.readString();
            zzatq.zzc(parcel);
            zzbob zzbobVarZzb = zzb(string);
            parcel2.writeNoException();
            zzatq.zzf(parcel2, zzbobVarZzb);
        } else if (i == 2) {
            String string2 = parcel.readString();
            zzatq.zzc(parcel);
            boolean zZze = zze(string2);
            parcel2.writeNoException();
            parcel2.writeInt(zZze ? 1 : 0);
        } else if (i == 3) {
            String string3 = parcel.readString();
            zzatq.zzc(parcel);
            zzbpv zzbpvVarZzc = zzc(string3);
            parcel2.writeNoException();
            zzatq.zzf(parcel2, zzbpvVarZzc);
        } else {
            if (i != 4) {
                return false;
            }
            String string4 = parcel.readString();
            zzatq.zzc(parcel);
            boolean zZzd = zzd(string4);
            parcel2.writeNoException();
            parcel2.writeInt(zZzd ? 1 : 0);
        }
        return true;
    }
}
