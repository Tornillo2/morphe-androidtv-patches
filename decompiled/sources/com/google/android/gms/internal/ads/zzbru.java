package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbru extends zzatp implements zzbrv {
    public zzbru() {
        super("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }

    public static zzbrv zzG(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        return iInterfaceQueryLocalInterface instanceof zzbrv ? (zzbrv) iInterfaceQueryLocalInterface : new zzbrt(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                Bundle bundle = (Bundle) zzatq.zza(parcel, Bundle.CREATOR);
                zzatq.zzc(parcel);
                zzk(bundle);
                parcel2.writeNoException();
                return true;
            case 2:
                parcel2.writeNoException();
                return true;
            case 3:
                zzs();
                parcel2.writeNoException();
                return true;
            case 4:
                zzq();
                parcel2.writeNoException();
                return true;
            case 5:
                zzn();
                parcel2.writeNoException();
                return true;
            case 6:
                Bundle bundle2 = (Bundle) zzatq.zza(parcel, Bundle.CREATOR);
                zzatq.zzc(parcel);
                zzr(bundle2);
                parcel2.writeNoException();
                zzatq.zze(parcel2, bundle2);
                return true;
            case 7:
                zzt();
                parcel2.writeNoException();
                return true;
            case 8:
                zzl();
                parcel2.writeNoException();
                return true;
            case 9:
                zzw();
                parcel2.writeNoException();
                return true;
            case 10:
                zzh();
                parcel2.writeNoException();
                return true;
            case 11:
                boolean zZzF = zzF();
                parcel2.writeNoException();
                int i3 = zzatq.zza;
                parcel2.writeInt(zZzF ? 1 : 0);
                return true;
            case 12:
                parcel.readInt();
                parcel.readInt();
                zzatq.zzc(parcel);
                parcel2.writeNoException();
                return true;
            case 13:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzj(iObjectWrapperAsInterface);
                parcel2.writeNoException();
                return true;
            case 14:
                zzu();
                parcel2.writeNoException();
                return true;
            case 15:
                int i4 = parcel.readInt();
                String[] strArrCreateStringArray = parcel.createStringArray();
                int[] iArrCreateIntArray = parcel.createIntArray();
                zzatq.zzc(parcel);
                zzo(i4, strArrCreateStringArray, iArrCreateIntArray);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
