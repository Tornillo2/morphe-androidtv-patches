package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzatp;
import com.google.android.gms.internal.ads.zzatq;
import com.google.android.gms.internal.ads.zzbeu;
import com.google.android.gms.internal.ads.zzbfa;
import com.google.android.gms.internal.ads.zzbje;
import com.google.android.gms.internal.ads.zzbjf;
import com.google.android.gms.internal.ads.zzbji;
import com.google.android.gms.internal.ads.zzbnx;
import com.google.android.gms.internal.ads.zzbny;
import com.google.android.gms.internal.ads.zzbro;
import com.google.android.gms.internal.ads.zzbrv;
import com.google.android.gms.internal.ads.zzbvp;
import com.google.android.gms.internal.ads.zzbyk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzcd extends zzatp implements zzce {
    public zzcd() {
        super("com.google.android.gms.ads.internal.client.IClientApi");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzq zzqVar = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                String string = parcel.readString();
                zzbny zzbnyVarZzf = zzbnx.zzf(parcel.readStrongBinder());
                int i3 = parcel.readInt();
                zzatq.zzc(parcel);
                zzbu zzbuVarZzd = zzd(iObjectWrapperAsInterface, zzqVar, string, zzbnyVarZzf, i3);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbuVarZzd);
                return true;
            case 2:
                IObjectWrapper iObjectWrapperAsInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzq zzqVar2 = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                String string2 = parcel.readString();
                zzbny zzbnyVarZzf2 = zzbnx.zzf(parcel.readStrongBinder());
                int i4 = parcel.readInt();
                zzatq.zzc(parcel);
                zzbu zzbuVarZze = zze(iObjectWrapperAsInterface2, zzqVar2, string2, zzbnyVarZzf2, i4);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbuVarZze);
                return true;
            case 3:
                IObjectWrapper iObjectWrapperAsInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                String string3 = parcel.readString();
                zzbny zzbnyVarZzf3 = zzbnx.zzf(parcel.readStrongBinder());
                int i5 = parcel.readInt();
                zzatq.zzc(parcel);
                zzbq zzbqVarZzb = zzb(iObjectWrapperAsInterface3, string3, zzbnyVarZzf3, i5);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbqVarZzb);
                return true;
            case 4:
                IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(null);
                return true;
            case 5:
                IObjectWrapper iObjectWrapperAsInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper iObjectWrapperAsInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzbeu zzbeuVarZzi = zzi(iObjectWrapperAsInterface4, iObjectWrapperAsInterface5);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbeuVarZzi);
                return true;
            case 6:
                IObjectWrapper iObjectWrapperAsInterface6 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbny zzbnyVarZzf4 = zzbnx.zzf(parcel.readStrongBinder());
                int i6 = parcel.readInt();
                zzatq.zzc(parcel);
                zzn(iObjectWrapperAsInterface6, zzbnyVarZzf4, i6);
                throw null;
            case 7:
                IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                parcel2.writeNoException();
                parcel2.writeStrongBinder(null);
                return true;
            case 8:
                IObjectWrapper iObjectWrapperAsInterface7 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzbrv zzbrvVarZzm = zzm(iObjectWrapperAsInterface7);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbrvVarZzm);
                return true;
            case 9:
                IObjectWrapper iObjectWrapperAsInterface8 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                int i7 = parcel.readInt();
                zzatq.zzc(parcel);
                zzco zzcoVarZzg = zzg(iObjectWrapperAsInterface8, i7);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzcoVarZzg);
                return true;
            case 10:
                IObjectWrapper iObjectWrapperAsInterface9 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzq zzqVar3 = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                String string4 = parcel.readString();
                int i8 = parcel.readInt();
                zzatq.zzc(parcel);
                zzbu zzbuVarZzf = zzf(iObjectWrapperAsInterface9, zzqVar3, string4, i8);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbuVarZzf);
                return true;
            case 11:
                IObjectWrapper iObjectWrapperAsInterface10 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper iObjectWrapperAsInterface11 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper iObjectWrapperAsInterface12 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzbfa zzbfaVarZzj = zzj(iObjectWrapperAsInterface10, iObjectWrapperAsInterface11, iObjectWrapperAsInterface12);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbfaVarZzj);
                return true;
            case 12:
                IObjectWrapper iObjectWrapperAsInterface13 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                String string5 = parcel.readString();
                zzbny zzbnyVarZzf5 = zzbnx.zzf(parcel.readStrongBinder());
                int i9 = parcel.readInt();
                zzatq.zzc(parcel);
                zzbvp zzbvpVarZzo = zzo(iObjectWrapperAsInterface13, string5, zzbnyVarZzf5, i9);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbvpVarZzo);
                return true;
            case 13:
                IObjectWrapper iObjectWrapperAsInterface14 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzq zzqVar4 = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                String string6 = parcel.readString();
                zzbny zzbnyVarZzf6 = zzbnx.zzf(parcel.readStrongBinder());
                int i10 = parcel.readInt();
                zzatq.zzc(parcel);
                zzbu zzbuVarZzc = zzc(iObjectWrapperAsInterface14, zzqVar4, string6, zzbnyVarZzf6, i10);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbuVarZzc);
                return true;
            case 14:
                IObjectWrapper iObjectWrapperAsInterface15 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbny zzbnyVarZzf7 = zzbnx.zzf(parcel.readStrongBinder());
                int i11 = parcel.readInt();
                zzatq.zzc(parcel);
                zzbyk zzbykVarZzp = zzp(iObjectWrapperAsInterface15, zzbnyVarZzf7, i11);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbykVarZzp);
                return true;
            case 15:
                IObjectWrapper iObjectWrapperAsInterface16 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbny zzbnyVarZzf8 = zzbnx.zzf(parcel.readStrongBinder());
                int i12 = parcel.readInt();
                zzatq.zzc(parcel);
                zzbro zzbroVarZzl = zzl(iObjectWrapperAsInterface16, zzbnyVarZzf8, i12);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbroVarZzl);
                return true;
            case 16:
                IObjectWrapper iObjectWrapperAsInterface17 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbny zzbnyVarZzf9 = zzbnx.zzf(parcel.readStrongBinder());
                int i13 = parcel.readInt();
                zzbjf zzbjfVarZzc = zzbje.zzc(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzbji zzbjiVarZzk = zzk(iObjectWrapperAsInterface17, zzbnyVarZzf9, i13, zzbjfVarZzc);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbjiVarZzk);
                return true;
            case 17:
                IObjectWrapper iObjectWrapperAsInterface18 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbny zzbnyVarZzf10 = zzbnx.zzf(parcel.readStrongBinder());
                int i14 = parcel.readInt();
                zzatq.zzc(parcel);
                zzdj zzdjVarZzh = zzh(iObjectWrapperAsInterface18, zzbnyVarZzf10, i14);
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdjVarZzh);
                return true;
            default:
                return false;
        }
    }
}
