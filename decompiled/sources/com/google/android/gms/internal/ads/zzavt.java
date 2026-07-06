package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzavt extends zzatp implements zzavu {
    public zzavt() {
        super("com.google.android.gms.ads.internal.appopen.client.IAppOpenAdLoadCallback");
    }

    public static zzavu zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.appopen.client.IAppOpenAdLoadCallback");
        return iInterfaceQueryLocalInterface instanceof zzavu ? (zzavu) iInterfaceQueryLocalInterface : new zzavs(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzavr zzavpVar;
        if (i == 1) {
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder == null) {
                zzavpVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.appopen.client.IAppOpenAd");
                zzavpVar = iInterfaceQueryLocalInterface instanceof zzavr ? (zzavr) iInterfaceQueryLocalInterface : new zzavp(strongBinder);
            }
            zzatq.zzc(parcel);
            zzd(zzavpVar);
        } else if (i == 2) {
            parcel.readInt();
            zzatq.zzc(parcel);
        } else {
            if (i != 3) {
                return false;
            }
            zze zzeVar = (zze) zzatq.zza(parcel, zze.CREATOR);
            zzatq.zzc(parcel);
            zzc(zzeVar);
        }
        parcel2.writeNoException();
        return true;
    }
}
