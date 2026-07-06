package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdc;
import com.google.android.gms.ads.internal.client.zzdd;
import com.google.android.gms.ads.internal.client.zzdf;
import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbvo extends zzatp implements zzbvp {
    public zzbvo() {
        super("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
    }

    public static zzbvp zzq(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
        return iInterfaceQueryLocalInterface instanceof zzbvp ? (zzbvp) iInterfaceQueryLocalInterface : new zzbvn(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzbvw zzbvuVar = null;
        zzbvw zzbvuVar2 = null;
        zzbvx zzbvxVar = null;
        zzbvs zzbvqVar = null;
        switch (i) {
            case 1:
                zzl zzlVar = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdLoadCallback");
                    zzbvuVar = iInterfaceQueryLocalInterface instanceof zzbvw ? (zzbvw) iInterfaceQueryLocalInterface : new zzbvu(strongBinder);
                }
                zzatq.zzc(parcel);
                zzf(zzlVar, zzbvuVar);
                parcel2.writeNoException();
                return true;
            case 2:
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCallback");
                    zzbvqVar = iInterfaceQueryLocalInterface2 instanceof zzbvs ? (zzbvs) iInterfaceQueryLocalInterface2 : new zzbvq(strongBinder2);
                }
                zzatq.zzc(parcel);
                zzk(zzbvqVar);
                parcel2.writeNoException();
                return true;
            case 3:
                boolean zZzo = zzo();
                parcel2.writeNoException();
                int i3 = zzatq.zza;
                parcel2.writeInt(zZzo ? 1 : 0);
                return true;
            case 4:
                String strZze = zze();
                parcel2.writeNoException();
                parcel2.writeString(strZze);
                return true;
            case 5:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzm(iObjectWrapperAsInterface);
                parcel2.writeNoException();
                return true;
            case 6:
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdSkuListener");
                    zzbvxVar = iInterfaceQueryLocalInterface3 instanceof zzbvx ? (zzbvx) iInterfaceQueryLocalInterface3 : new zzbvx(strongBinder3);
                }
                zzatq.zzc(parcel);
                zzp(zzbvxVar);
                parcel2.writeNoException();
                return true;
            case 7:
                zzbwd zzbwdVar = (zzbwd) zzatq.zza(parcel, zzbwd.CREATOR);
                zzatq.zzc(parcel);
                zzl(zzbwdVar);
                parcel2.writeNoException();
                return true;
            case 8:
                zzdd zzddVarZzb = zzdc.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzi(zzddVarZzb);
                parcel2.writeNoException();
                return true;
            case 9:
                Bundle bundleZzb = zzb();
                parcel2.writeNoException();
                zzatq.zze(parcel2, bundleZzb);
                return true;
            case 10:
                IObjectWrapper iObjectWrapperAsInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                boolean zZzg = zzatq.zzg(parcel);
                zzatq.zzc(parcel);
                zzn(iObjectWrapperAsInterface2, zZzg);
                parcel2.writeNoException();
                return true;
            case 11:
                zzbvm zzbvmVarZzd = zzd();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbvmVarZzd);
                return true;
            case 12:
                zzdn zzdnVarZzc = zzc();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdnVarZzc);
                return true;
            case 13:
                zzdg zzdgVarZzb = zzdf.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzj(zzdgVarZzb);
                parcel2.writeNoException();
                return true;
            case 14:
                zzl zzlVar2 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                IBinder strongBinder4 = parcel.readStrongBinder();
                if (strongBinder4 != null) {
                    IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdLoadCallback");
                    zzbvuVar2 = iInterfaceQueryLocalInterface4 instanceof zzbvw ? (zzbvw) iInterfaceQueryLocalInterface4 : new zzbvu(strongBinder4);
                }
                zzatq.zzc(parcel);
                zzg(zzlVar2, zzbvuVar2);
                parcel2.writeNoException();
                return true;
            case 15:
                boolean zZzg2 = zzatq.zzg(parcel);
                zzatq.zzc(parcel);
                zzh(zZzg2);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
