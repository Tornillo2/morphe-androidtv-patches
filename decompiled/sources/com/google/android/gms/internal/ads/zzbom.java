package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbom extends zzatp implements zzbon {
    public zzbom() {
        super("com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
    }

    public static zzbon zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
        return iInterfaceQueryLocalInterface instanceof zzbon ? (zzbon) iInterfaceQueryLocalInterface : new zzbol(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                String strZzs = zzs();
                parcel2.writeNoException();
                parcel2.writeString(strZzs);
                return true;
            case 3:
                List listZzv = zzv();
                parcel2.writeNoException();
                parcel2.writeList(listZzv);
                return true;
            case 4:
                String strZzq = zzq();
                parcel2.writeNoException();
                parcel2.writeString(strZzq);
                return true;
            case 5:
                zzbeq zzbeqVarZzl = zzl();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbeqVarZzl);
                return true;
            case 6:
                String strZzr = zzr();
                parcel2.writeNoException();
                parcel2.writeString(strZzr);
                return true;
            case 7:
                String strZzp = zzp();
                parcel2.writeNoException();
                parcel2.writeString(strZzp);
                return true;
            case 8:
                double dZze = zze();
                parcel2.writeNoException();
                parcel2.writeDouble(dZze);
                return true;
            case 9:
                String strZzu = zzu();
                parcel2.writeNoException();
                parcel2.writeString(strZzu);
                return true;
            case 10:
                String strZzt = zzt();
                parcel2.writeNoException();
                parcel2.writeString(strZzt);
                return true;
            case 11:
                zzdq zzdqVarZzj = zzj();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdqVarZzj);
                return true;
            case 12:
                parcel2.writeNoException();
                zzatq.zzf(parcel2, null);
                return true;
            case 13:
                IObjectWrapper iObjectWrapperZzm = zzm();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, iObjectWrapperZzm);
                return true;
            case 14:
                IObjectWrapper iObjectWrapperZzn = zzn();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, iObjectWrapperZzn);
                return true;
            case 15:
                IObjectWrapper iObjectWrapperZzo = zzo();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, iObjectWrapperZzo);
                return true;
            case 16:
                Bundle bundleZzi = zzi();
                parcel2.writeNoException();
                zzatq.zze(parcel2, bundleZzi);
                return true;
            case 17:
                boolean zZzB = zzB();
                parcel2.writeNoException();
                int i3 = zzatq.zza;
                parcel2.writeInt(zZzB ? 1 : 0);
                return true;
            case 18:
                boolean zZzA = zzA();
                parcel2.writeNoException();
                int i4 = zzatq.zza;
                parcel2.writeInt(zZzA ? 1 : 0);
                return true;
            case 19:
                zzx();
                parcel2.writeNoException();
                return true;
            case 20:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzw(iObjectWrapperAsInterface);
                parcel2.writeNoException();
                return true;
            case 21:
                IObjectWrapper iObjectWrapperAsInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper iObjectWrapperAsInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper iObjectWrapperAsInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzy(iObjectWrapperAsInterface2, iObjectWrapperAsInterface3, iObjectWrapperAsInterface4);
                parcel2.writeNoException();
                return true;
            case 22:
                IObjectWrapper iObjectWrapperAsInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzz(iObjectWrapperAsInterface5);
                parcel2.writeNoException();
                return true;
            case 23:
                float fZzf = zzf();
                parcel2.writeNoException();
                parcel2.writeFloat(fZzf);
                return true;
            case 24:
                float fZzh = zzh();
                parcel2.writeNoException();
                parcel2.writeFloat(fZzh);
                return true;
            case 25:
                float fZzg = zzg();
                parcel2.writeNoException();
                parcel2.writeFloat(fZzg);
                return true;
            default:
                return false;
        }
    }
}
