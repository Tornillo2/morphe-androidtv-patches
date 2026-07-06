package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbod extends zzatp implements zzboe {
    public zzbod() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
    }

    public static zzboe zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
        return iInterfaceQueryLocalInterface instanceof zzboe ? (zzboe) iInterfaceQueryLocalInterface : new zzboc(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zze();
                break;
            case 2:
                zzf();
                break;
            case 3:
                int i3 = parcel.readInt();
                zzatq.zzc(parcel);
                zzg(i3);
                break;
            case 4:
                zzn();
                break;
            case 5:
                zzp();
                break;
            case 6:
                zzo();
                break;
            case 7:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
                }
                zzatq.zzc(parcel);
                break;
            case 8:
                zzm();
                break;
            case 9:
                String string = parcel.readString();
                String string2 = parcel.readString();
                zzatq.zzc(parcel);
                zzq(string, string2);
                break;
            case 10:
                zzbfj.zzb(parcel.readStrongBinder());
                parcel.readString();
                zzatq.zzc(parcel);
                break;
            case 11:
                zzv();
                break;
            case 12:
                parcel.readString();
                zzatq.zzc(parcel);
                break;
            case 13:
                zzy();
                break;
            case 14:
                zzbvi zzbviVar = (zzbvi) zzatq.zza(parcel, zzbvi.CREATOR);
                zzatq.zzc(parcel);
                zzs(zzbviVar);
                break;
            case 15:
                zzw();
                break;
            case 16:
                zzbvm zzbvmVarZzb = zzbvl.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzt(zzbvmVarZzb);
                break;
            case 17:
                int i4 = parcel.readInt();
                zzatq.zzc(parcel);
                zzj(i4);
                break;
            case 18:
                zzu();
                break;
            case 19:
                zzatq.zzc(parcel);
                break;
            case 20:
                zzx();
                break;
            case 21:
                String string3 = parcel.readString();
                zzatq.zzc(parcel);
                zzl(string3);
                break;
            case 22:
                int i5 = parcel.readInt();
                String string4 = parcel.readString();
                zzatq.zzc(parcel);
                zzi(i5, string4);
                break;
            case 23:
                zze zzeVar = (zze) zzatq.zza(parcel, zze.CREATOR);
                zzatq.zzc(parcel);
                zzh(zzeVar);
                break;
            case 24:
                zze zzeVar2 = (zze) zzatq.zza(parcel, zze.CREATOR);
                zzatq.zzc(parcel);
                zzk(zzeVar2);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
