package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzcr;
import com.google.android.gms.ads.internal.client.zzcs;
import com.google.android.gms.ads.internal.client.zzcv;
import com.google.android.gms.ads.internal.client.zzcw;
import com.google.android.gms.ads.internal.client.zzdf;
import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbgm extends zzatp implements zzbgn {
    public zzbgm() {
        super("com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzbgk zzbgiVar;
        switch (i) {
            case 2:
                String strZzq = zzq();
                parcel2.writeNoException();
                parcel2.writeString(strZzq);
                return true;
            case 3:
                List listZzu = zzu();
                parcel2.writeNoException();
                parcel2.writeList(listZzu);
                return true;
            case 4:
                String strZzo = zzo();
                parcel2.writeNoException();
                parcel2.writeString(strZzo);
                return true;
            case 5:
                zzbeq zzbeqVarZzk = zzk();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbeqVarZzk);
                return true;
            case 6:
                String strZzp = zzp();
                parcel2.writeNoException();
                parcel2.writeString(strZzp);
                return true;
            case 7:
                String strZzn = zzn();
                parcel2.writeNoException();
                parcel2.writeString(strZzn);
                return true;
            case 8:
                double dZze = zze();
                parcel2.writeNoException();
                parcel2.writeDouble(dZze);
                return true;
            case 9:
                String strZzt = zzt();
                parcel2.writeNoException();
                parcel2.writeString(strZzt);
                return true;
            case 10:
                String strZzs = zzs();
                parcel2.writeNoException();
                parcel2.writeString(strZzs);
                return true;
            case 11:
                zzdq zzdqVarZzh = zzh();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdqVarZzh);
                return true;
            case 12:
                String strZzr = zzr();
                parcel2.writeNoException();
                parcel2.writeString(strZzr);
                return true;
            case 13:
                zzx();
                parcel2.writeNoException();
                return true;
            case 14:
                zzbei zzbeiVarZzi = zzi();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbeiVarZzi);
                return true;
            case 15:
                Bundle bundle = (Bundle) zzatq.zza(parcel, Bundle.CREATOR);
                zzatq.zzc(parcel);
                zzz(bundle);
                parcel2.writeNoException();
                return true;
            case 16:
                Bundle bundle2 = (Bundle) zzatq.zza(parcel, Bundle.CREATOR);
                zzatq.zzc(parcel);
                boolean zZzI = zzI(bundle2);
                parcel2.writeNoException();
                parcel2.writeInt(zZzI ? 1 : 0);
                return true;
            case 17:
                Bundle bundle3 = (Bundle) zzatq.zza(parcel, Bundle.CREATOR);
                zzatq.zzc(parcel);
                zzB(bundle3);
                parcel2.writeNoException();
                return true;
            case 18:
                IObjectWrapper iObjectWrapperZzm = zzm();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, iObjectWrapperZzm);
                return true;
            case 19:
                IObjectWrapper iObjectWrapperZzl = zzl();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, iObjectWrapperZzl);
                return true;
            case 20:
                Bundle bundleZzf = zzf();
                parcel2.writeNoException();
                zzatq.zze(parcel2, bundleZzf);
                return true;
            case 21:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zzbgiVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener");
                    zzbgiVar = iInterfaceQueryLocalInterface instanceof zzbgk ? (zzbgk) iInterfaceQueryLocalInterface : new zzbgi(strongBinder);
                }
                zzatq.zzc(parcel);
                zzF(zzbgiVar);
                parcel2.writeNoException();
                return true;
            case 22:
                zzw();
                parcel2.writeNoException();
                return true;
            case 23:
                List listZzv = zzv();
                parcel2.writeNoException();
                parcel2.writeList(listZzv);
                return true;
            case 24:
                boolean zZzH = zzH();
                parcel2.writeNoException();
                int i3 = zzatq.zza;
                parcel2.writeInt(zZzH ? 1 : 0);
                return true;
            case 25:
                zzcw zzcwVarZzb = zzcv.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzy(zzcwVarZzb);
                parcel2.writeNoException();
                return true;
            case 26:
                zzcs zzcsVarZzb = zzcr.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzD(zzcsVarZzb);
                parcel2.writeNoException();
                return true;
            case 27:
                zzC();
                parcel2.writeNoException();
                return true;
            case 28:
                zzA();
                parcel2.writeNoException();
                return true;
            case 29:
                zzben zzbenVarZzj = zzj();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbenVarZzj);
                return true;
            case 30:
                boolean zZzG = zzG();
                parcel2.writeNoException();
                int i4 = zzatq.zza;
                parcel2.writeInt(zZzG ? 1 : 0);
                return true;
            case 31:
                zzdn zzdnVarZzg = zzg();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdnVarZzg);
                return true;
            case 32:
                zzdg zzdgVarZzb = zzdf.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzE(zzdgVarZzb);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
