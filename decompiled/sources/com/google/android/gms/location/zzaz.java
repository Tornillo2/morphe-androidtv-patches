package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzaz extends com.google.android.gms.internal.location.zzb implements zzba {
    public zzaz() {
        super("com.google.android.gms.location.ILocationCallback");
    }

    public static zzba zzb(IBinder iBinder) {
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        return iInterfaceQueryLocalInterface instanceof zzba ? (zzba) iInterfaceQueryLocalInterface : new zzay(iBinder);
    }

    @Override // com.google.android.gms.internal.location.zzb
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzd((LocationResult) com.google.android.gms.internal.location.zzc.zzb(parcel, LocationResult.CREATOR));
        } else {
            if (i != 2) {
                return false;
            }
            zze((LocationAvailability) com.google.android.gms.internal.location.zzc.zzb(parcel, LocationAvailability.CREATOR));
        }
        return true;
    }
}
