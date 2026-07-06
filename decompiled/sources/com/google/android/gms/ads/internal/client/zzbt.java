package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzatp;
import com.google.android.gms.internal.ads.zzatq;
import com.google.android.gms.internal.ads.zzavt;
import com.google.android.gms.internal.ads.zzavu;
import com.google.android.gms.internal.ads.zzbch;
import com.google.android.gms.internal.ads.zzbci;
import com.google.android.gms.internal.ads.zzbsd;
import com.google.android.gms.internal.ads.zzbse;
import com.google.android.gms.internal.ads.zzbsg;
import com.google.android.gms.internal.ads.zzbsh;
import com.google.android.gms.internal.ads.zzbvb;
import com.google.android.gms.internal.ads.zzbvc;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbt extends zzatp implements zzbu {
    public zzbt() {
        super("com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static zzbu zzac(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        return iInterfaceQueryLocalInterface instanceof zzbu ? (zzbu) iInterfaceQueryLocalInterface : new zzbs(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzbh zzbfVar = null;
        zzci zzcgVar = null;
        zzbk zzbiVar = null;
        zzdg zzdeVar = null;
        zzby zzbwVar = null;
        zzcf zzcfVar = null;
        zzbe zzbcVar = null;
        zzcb zzbzVar = null;
        switch (i) {
            case 1:
                IObjectWrapper iObjectWrapperZzn = zzn();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, iObjectWrapperZzn);
                return true;
            case 2:
                zzx();
                parcel2.writeNoException();
                return true;
            case 3:
                boolean zZzZ = zzZ();
                parcel2.writeNoException();
                int i3 = zzatq.zza;
                parcel2.writeInt(zZzZ ? 1 : 0);
                return true;
            case 4:
                zzl zzlVar = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                zzatq.zzc(parcel);
                boolean zZzaa = zzaa(zzlVar);
                parcel2.writeNoException();
                parcel2.writeInt(zZzaa ? 1 : 0);
                return true;
            case 5:
                zzz();
                parcel2.writeNoException();
                return true;
            case 6:
                zzB();
                parcel2.writeNoException();
                return true;
            case 7:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzbfVar = iInterfaceQueryLocalInterface instanceof zzbh ? (zzbh) iInterfaceQueryLocalInterface : new zzbf(strongBinder);
                }
                zzatq.zzc(parcel);
                zzD(zzbfVar);
                parcel2.writeNoException();
                return true;
            case 8:
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                    zzbzVar = iInterfaceQueryLocalInterface2 instanceof zzcb ? (zzcb) iInterfaceQueryLocalInterface2 : new zzbz(strongBinder2);
                }
                zzatq.zzc(parcel);
                zzG(zzbzVar);
                parcel2.writeNoException();
                return true;
            case 9:
                zzX();
                parcel2.writeNoException();
                return true;
            case 10:
                parcel2.writeNoException();
                return true;
            case 11:
                zzA();
                parcel2.writeNoException();
                return true;
            case 12:
                zzq zzqVarZzg = zzg();
                parcel2.writeNoException();
                zzatq.zze(parcel2, zzqVarZzg);
                return true;
            case 13:
                zzq zzqVar = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                zzatq.zzc(parcel);
                zzF(zzqVar);
                parcel2.writeNoException();
                return true;
            case 14:
                zzbse zzbseVarZzb = zzbsd.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzM(zzbseVarZzb);
                parcel2.writeNoException();
                return true;
            case 15:
                zzbsh zzbshVarZzb = zzbsg.zzb(parcel.readStrongBinder());
                String string = parcel.readString();
                zzatq.zzc(parcel);
                zzQ(zzbshVarZzb, string);
                parcel2.writeNoException();
                return true;
            case 16:
            case 17:
            case 27:
            case 28:
            default:
                return false;
            case 18:
                String strZzs = zzs();
                parcel2.writeNoException();
                parcel2.writeString(strZzs);
                return true;
            case 19:
                zzbci zzbciVarZzb = zzbch.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzO(zzbciVarZzb);
                parcel2.writeNoException();
                return true;
            case 20:
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdClickListener");
                    zzbcVar = iInterfaceQueryLocalInterface3 instanceof zzbe ? (zzbe) iInterfaceQueryLocalInterface3 : new zzbc(strongBinder3);
                }
                zzatq.zzc(parcel);
                zzC(zzbcVar);
                parcel2.writeNoException();
                return true;
            case 21:
                IBinder strongBinder4 = parcel.readStrongBinder();
                if (strongBinder4 != null) {
                    IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzcfVar = iInterfaceQueryLocalInterface4 instanceof zzcf ? (zzcf) iInterfaceQueryLocalInterface4 : new zzcf(strongBinder4);
                }
                zzatq.zzc(parcel);
                zzab(zzcfVar);
                parcel2.writeNoException();
                return true;
            case 22:
                boolean zZzg = zzatq.zzg(parcel);
                zzatq.zzc(parcel);
                zzN(zZzg);
                parcel2.writeNoException();
                return true;
            case 23:
                boolean zZzY = zzY();
                parcel2.writeNoException();
                int i4 = zzatq.zza;
                parcel2.writeInt(zZzY ? 1 : 0);
                return true;
            case 24:
                zzbvc zzbvcVarZzb = zzbvb.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzS(zzbvcVarZzb);
                parcel2.writeNoException();
                return true;
            case 25:
                String string2 = parcel.readString();
                zzatq.zzc(parcel);
                zzT(string2);
                parcel2.writeNoException();
                return true;
            case 26:
                zzdq zzdqVarZzl = zzl();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdqVarZzl);
                return true;
            case 29:
                zzfl zzflVar = (zzfl) zzatq.zza(parcel, zzfl.CREATOR);
                zzatq.zzc(parcel);
                zzU(zzflVar);
                parcel2.writeNoException();
                return true;
            case 30:
                zzdu zzduVar = (zzdu) zzatq.zza(parcel, zzdu.CREATOR);
                zzatq.zzc(parcel);
                zzK(zzduVar);
                parcel2.writeNoException();
                return true;
            case 31:
                String strZzr = zzr();
                parcel2.writeNoException();
                parcel2.writeString(strZzr);
                return true;
            case 32:
                zzcb zzcbVarZzj = zzj();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzcbVarZzj);
                return true;
            case 33:
                zzbh zzbhVarZzi = zzi();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbhVarZzi);
                return true;
            case 34:
                boolean zZzg2 = zzatq.zzg(parcel);
                zzatq.zzc(parcel);
                zzL(zZzg2);
                parcel2.writeNoException();
                return true;
            case 35:
                String strZzt = zzt();
                parcel2.writeNoException();
                parcel2.writeString(strZzt);
                return true;
            case 36:
                IBinder strongBinder5 = parcel.readStrongBinder();
                if (strongBinder5 != null) {
                    IInterface iInterfaceQueryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdMetadataListener");
                    zzbwVar = iInterfaceQueryLocalInterface5 instanceof zzby ? (zzby) iInterfaceQueryLocalInterface5 : new zzbw(strongBinder5);
                }
                zzatq.zzc(parcel);
                zzE(zzbwVar);
                parcel2.writeNoException();
                return true;
            case 37:
                Bundle bundleZzd = zzd();
                parcel2.writeNoException();
                zzatq.zze(parcel2, bundleZzd);
                return true;
            case 38:
                String string3 = parcel.readString();
                zzatq.zzc(parcel);
                zzR(string3);
                parcel2.writeNoException();
                return true;
            case 39:
                zzw zzwVar = (zzw) zzatq.zza(parcel, zzw.CREATOR);
                zzatq.zzc(parcel);
                zzI(zzwVar);
                parcel2.writeNoException();
                return true;
            case 40:
                zzavu zzavuVarZze = zzavt.zze(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzH(zzavuVarZze);
                parcel2.writeNoException();
                return true;
            case 41:
                zzdn zzdnVarZzk = zzk();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdnVarZzk);
                return true;
            case 42:
                IBinder strongBinder6 = parcel.readStrongBinder();
                if (strongBinder6 != null) {
                    IInterface iInterfaceQueryLocalInterface6 = strongBinder6.queryLocalInterface("com.google.android.gms.ads.internal.client.IOnPaidEventListener");
                    zzdeVar = iInterfaceQueryLocalInterface6 instanceof zzdg ? (zzdg) iInterfaceQueryLocalInterface6 : new zzde(strongBinder6);
                }
                zzatq.zzc(parcel);
                zzP(zzdeVar);
                parcel2.writeNoException();
                return true;
            case 43:
                zzl zzlVar2 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                IBinder strongBinder7 = parcel.readStrongBinder();
                if (strongBinder7 != null) {
                    IInterface iInterfaceQueryLocalInterface7 = strongBinder7.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoadCallback");
                    zzbiVar = iInterfaceQueryLocalInterface7 instanceof zzbk ? (zzbk) iInterfaceQueryLocalInterface7 : new zzbi(strongBinder7);
                }
                zzatq.zzc(parcel);
                zzy(zzlVar2, zzbiVar);
                parcel2.writeNoException();
                return true;
            case 44:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzW(iObjectWrapperAsInterface);
                parcel2.writeNoException();
                return true;
            case 45:
                IBinder strongBinder8 = parcel.readStrongBinder();
                if (strongBinder8 != null) {
                    IInterface iInterfaceQueryLocalInterface8 = strongBinder8.queryLocalInterface("com.google.android.gms.ads.internal.client.IFullScreenContentCallback");
                    zzcgVar = iInterfaceQueryLocalInterface8 instanceof zzci ? (zzci) iInterfaceQueryLocalInterface8 : new zzcg(strongBinder8);
                }
                zzatq.zzc(parcel);
                zzJ(zzcgVar);
                parcel2.writeNoException();
                return true;
        }
    }
}
