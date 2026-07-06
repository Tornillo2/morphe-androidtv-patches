package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbks extends zzatp implements zzbkt {
    public zzbks() {
        super("com.google.android.gms.ads.internal.instream.client.IInstreamAd");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzbkw zzbkuVar;
        if (i == 3) {
            zzdq zzdqVarZzb = zzb();
            parcel2.writeNoException();
            zzatq.zzf(parcel2, zzdqVarZzb);
            return true;
        }
        if (i == 4) {
            zzd();
            parcel2.writeNoException();
            return true;
        }
        if (i == 5) {
            IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder == null) {
                zzbkuVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.instream.client.IInstreamAdCallback");
                zzbkuVar = iInterfaceQueryLocalInterface instanceof zzbkw ? (zzbkw) iInterfaceQueryLocalInterface : new zzbku(strongBinder);
            }
            zzatq.zzc(parcel);
            zzf(iObjectWrapperAsInterface, zzbkuVar);
            parcel2.writeNoException();
            return true;
        }
        if (i == 6) {
            IObjectWrapper iObjectWrapperAsInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            zzatq.zzc(parcel);
            zze(iObjectWrapperAsInterface2);
            parcel2.writeNoException();
            return true;
        }
        if (i != 7) {
            return false;
        }
        zzben zzbenVarZzc = zzc();
        parcel2.writeNoException();
        zzatq.zzf(parcel2, zzbenVarZzc);
        return true;
    }
}
