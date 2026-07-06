package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdf;
import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzavq extends zzatp implements zzavr {
    public zzavq() {
        super("com.google.android.gms.ads.internal.appopen.client.IAppOpenAd");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzavy zzavwVar = null;
        switch (i) {
            case 2:
                zze();
                throw null;
            case 3:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.appopen.client.IAppOpenAdPresentationCallback");
                    if (iInterfaceQueryLocalInterface instanceof zzavv) {
                    }
                }
                zzatq.zzc(parcel);
                parcel2.writeNoException();
                return true;
            case 4:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.appopen.client.IAppOpenFullScreenContentCallback");
                    zzavwVar = iInterfaceQueryLocalInterface2 instanceof zzavy ? (zzavy) iInterfaceQueryLocalInterface2 : new zzavw(strongBinder2);
                }
                zzatq.zzc(parcel);
                zzi(iObjectWrapperAsInterface, zzavwVar);
                parcel2.writeNoException();
                return true;
            case 5:
                zzdn zzdnVarZzf = zzf();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdnVarZzf);
                return true;
            case 6:
                boolean zZzg = zzatq.zzg(parcel);
                zzatq.zzc(parcel);
                zzg(zZzg);
                parcel2.writeNoException();
                return true;
            case 7:
                zzdg zzdgVarZzb = zzdf.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzh(zzdgVarZzb);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
