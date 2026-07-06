package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbyj extends zzatp implements zzbyk {
    public zzbyj() {
        super("com.google.android.gms.ads.internal.signals.ISignalGenerator");
    }

    public static zzbyk zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.signals.ISignalGenerator");
        return iInterfaceQueryLocalInterface instanceof zzbyk ? (zzbyk) iInterfaceQueryLocalInterface : new zzbyi(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzbyh zzbyfVar = null;
        switch (i) {
            case 1:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbyo zzbyoVar = (zzbyo) zzatq.zza(parcel, zzbyo.CREATOR);
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.signals.ISignalCallback");
                    zzbyfVar = iInterfaceQueryLocalInterface instanceof zzbyh ? (zzbyh) iInterfaceQueryLocalInterface : new zzbyf(strongBinder);
                }
                zzatq.zzc(parcel);
                zze(iObjectWrapperAsInterface, zzbyoVar, zzbyfVar);
                parcel2.writeNoException();
                return true;
            case 2:
                IObjectWrapper iObjectWrapperAsInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzj(iObjectWrapperAsInterface2);
                parcel2.writeNoException();
                return true;
            case 3:
                IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(null);
                return true;
            case 4:
                IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(null);
                return true;
            case 5:
                ArrayList arrayListCreateTypedArrayList = parcel.createTypedArrayList(Uri.CREATOR);
                IObjectWrapper iObjectWrapperAsInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbsk zzbskVarZzb = zzbsj.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzl(arrayListCreateTypedArrayList, iObjectWrapperAsInterface3, zzbskVarZzb);
                parcel2.writeNoException();
                return true;
            case 6:
                ArrayList arrayListCreateTypedArrayList2 = parcel.createTypedArrayList(Uri.CREATOR);
                IObjectWrapper iObjectWrapperAsInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbsk zzbskVarZzb2 = zzbsj.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzk(arrayListCreateTypedArrayList2, iObjectWrapperAsInterface4, zzbskVarZzb2);
                parcel2.writeNoException();
                return true;
            case 7:
                zzbst zzbstVar = (zzbst) zzatq.zza(parcel, zzbst.CREATOR);
                zzatq.zzc(parcel);
                zzf(zzbstVar);
                parcel2.writeNoException();
                return true;
            case 8:
                IObjectWrapper iObjectWrapperAsInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzi(iObjectWrapperAsInterface5);
                parcel2.writeNoException();
                return true;
            case 9:
                ArrayList arrayListCreateTypedArrayList3 = parcel.createTypedArrayList(Uri.CREATOR);
                IObjectWrapper iObjectWrapperAsInterface6 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbsk zzbskVarZzb3 = zzbsj.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzh(arrayListCreateTypedArrayList3, iObjectWrapperAsInterface6, zzbskVarZzb3);
                parcel2.writeNoException();
                return true;
            case 10:
                ArrayList arrayListCreateTypedArrayList4 = parcel.createTypedArrayList(Uri.CREATOR);
                IObjectWrapper iObjectWrapperAsInterface7 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbsk zzbskVarZzb4 = zzbsj.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzg(arrayListCreateTypedArrayList4, iObjectWrapperAsInterface7, zzbskVarZzb4);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
