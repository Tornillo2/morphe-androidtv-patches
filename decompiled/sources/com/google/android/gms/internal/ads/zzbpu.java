package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbpu extends zzatp implements zzbpv {
    public zzbpu() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzbpv zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        return iInterfaceQueryLocalInterface instanceof zzbpv ? (zzbpv) iInterfaceQueryLocalInterface : new zzbpt(iBinder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.google.android.gms.internal.ads.zzbpu, com.google.android.gms.internal.ads.zzbpv] */
    /* JADX WARN: Type inference failed for: r5v11, types: [com.google.android.gms.internal.ads.zzbps] */
    /* JADX WARN: Type inference failed for: r5v15, types: [com.google.android.gms.internal.ads.zzbpp] */
    /* JADX WARN: Type inference failed for: r5v19, types: [com.google.android.gms.internal.ads.zzbps] */
    /* JADX WARN: Type inference failed for: r5v21, types: [com.google.android.gms.internal.ads.zzbpj] */
    /* JADX WARN: Type inference failed for: r5v24, types: [com.google.android.gms.internal.ads.zzbpp] */
    /* JADX WARN: Type inference failed for: r5v28, types: [com.google.android.gms.internal.ads.zzbpg] */
    /* JADX WARN: Type inference failed for: r5v5, types: [com.google.android.gms.internal.ads.zzbpj] */
    /* JADX WARN: Type inference failed for: r5v9, types: [com.google.android.gms.internal.ads.zzbpm] */
    /* JADX WARN: Type inference failed for: r6v3, types: [com.google.android.gms.internal.ads.zzbpy] */
    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IInterface zzbpeVar = null;
        if (i == 1) {
            IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            String string = parcel.readString();
            Parcelable.Creator creator = Bundle.CREATOR;
            Bundle bundle = (Bundle) zzatq.zza(parcel, creator);
            Bundle bundle2 = (Bundle) zzatq.zza(parcel, creator);
            zzq zzqVar = (zzq) zzatq.zza(parcel, zzq.CREATOR);
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder != null) {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
                zzbpeVar = iInterfaceQueryLocalInterface instanceof zzbpy ? (zzbpy) iInterfaceQueryLocalInterface : new zzbpw(strongBinder);
            }
            zzatq.zzc(parcel);
            zzh(iObjectWrapperAsInterface, string, bundle, bundle2, zzqVar, zzbpeVar);
            parcel2.writeNoException();
        } else if (i == 2) {
            zzbqj zzbqjVarZzf = zzf();
            parcel2.writeNoException();
            zzatq.zze(parcel2, zzbqjVarZzf);
        } else if (i == 3) {
            zzbqj zzbqjVarZzg = zzg();
            parcel2.writeNoException();
            zzatq.zze(parcel2, zzbqjVarZzg);
        } else if (i == 5) {
            zzdq zzdqVarZze = zze();
            parcel2.writeNoException();
            zzatq.zzf(parcel2, zzdqVarZze);
        } else if (i == 10) {
            IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            zzatq.zzc(parcel);
            parcel2.writeNoException();
        } else if (i != 11) {
            switch (i) {
                case 13:
                    String string2 = parcel.readString();
                    String string3 = parcel.readString();
                    zzl zzlVar = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder2 = parcel.readStrongBinder();
                    if (strongBinder2 != null) {
                        IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                        zzbpeVar = iInterfaceQueryLocalInterface2 instanceof zzbpj ? (zzbpj) iInterfaceQueryLocalInterface2 : new zzbph(strongBinder2);
                    }
                    ?? r5 = zzbpeVar;
                    zzboe zzboeVarZzb = zzbod.zzb(parcel.readStrongBinder());
                    zzq zzqVar2 = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                    zzatq.zzc(parcel);
                    zzj(string2, string3, zzlVar, iObjectWrapperAsInterface2, r5, zzboeVarZzb, zzqVar2);
                    parcel2.writeNoException();
                    break;
                case 14:
                    String string4 = parcel.readString();
                    String string5 = parcel.readString();
                    zzl zzlVar2 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder3 = parcel.readStrongBinder();
                    if (strongBinder3 != null) {
                        IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback");
                        zzbpeVar = iInterfaceQueryLocalInterface3 instanceof zzbpm ? (zzbpm) iInterfaceQueryLocalInterface3 : new zzbpk(strongBinder3);
                    }
                    zzboe zzboeVarZzb2 = zzbod.zzb(parcel.readStrongBinder());
                    zzatq.zzc(parcel);
                    zzl(string4, string5, zzlVar2, iObjectWrapperAsInterface3, zzbpeVar, zzboeVarZzb2);
                    parcel2.writeNoException();
                    break;
                case 15:
                    IObjectWrapper iObjectWrapperAsInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    zzatq.zzc(parcel);
                    boolean zZzs = zzs(iObjectWrapperAsInterface4);
                    parcel2.writeNoException();
                    parcel2.writeInt(zZzs ? 1 : 0);
                    break;
                case 16:
                    String string6 = parcel.readString();
                    String string7 = parcel.readString();
                    zzl zzlVar3 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder4 = parcel.readStrongBinder();
                    if (strongBinder4 != null) {
                        IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback");
                        zzbpeVar = iInterfaceQueryLocalInterface4 instanceof zzbps ? (zzbps) iInterfaceQueryLocalInterface4 : new zzbpq(strongBinder4);
                    }
                    zzboe zzboeVarZzb3 = zzbod.zzb(parcel.readStrongBinder());
                    zzatq.zzc(parcel);
                    zzp(string6, string7, zzlVar3, iObjectWrapperAsInterface5, zzbpeVar, zzboeVarZzb3);
                    parcel2.writeNoException();
                    break;
                case 17:
                    IObjectWrapper iObjectWrapperAsInterface6 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    zzatq.zzc(parcel);
                    boolean zZzt = zzt(iObjectWrapperAsInterface6);
                    parcel2.writeNoException();
                    parcel2.writeInt(zZzt ? 1 : 0);
                    break;
                case 18:
                    String string8 = parcel.readString();
                    String string9 = parcel.readString();
                    zzl zzlVar4 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface7 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder5 = parcel.readStrongBinder();
                    if (strongBinder5 != null) {
                        IInterface iInterfaceQueryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
                        zzbpeVar = iInterfaceQueryLocalInterface5 instanceof zzbpp ? (zzbpp) iInterfaceQueryLocalInterface5 : new zzbpn(strongBinder5);
                    }
                    zzboe zzboeVarZzb4 = zzbod.zzb(parcel.readStrongBinder());
                    zzatq.zzc(parcel);
                    zzm(string8, string9, zzlVar4, iObjectWrapperAsInterface7, zzbpeVar, zzboeVarZzb4);
                    parcel2.writeNoException();
                    break;
                case 19:
                    String string10 = parcel.readString();
                    zzatq.zzc(parcel);
                    zzq(string10);
                    parcel2.writeNoException();
                    break;
                case 20:
                    String string11 = parcel.readString();
                    String string12 = parcel.readString();
                    zzl zzlVar5 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface8 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder6 = parcel.readStrongBinder();
                    if (strongBinder6 != null) {
                        IInterface iInterfaceQueryLocalInterface6 = strongBinder6.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback");
                        zzbpeVar = iInterfaceQueryLocalInterface6 instanceof zzbps ? (zzbps) iInterfaceQueryLocalInterface6 : new zzbpq(strongBinder6);
                    }
                    zzboe zzboeVarZzb5 = zzbod.zzb(parcel.readStrongBinder());
                    zzatq.zzc(parcel);
                    zzo(string11, string12, zzlVar5, iObjectWrapperAsInterface8, zzbpeVar, zzboeVarZzb5);
                    parcel2.writeNoException();
                    break;
                case 21:
                    String string13 = parcel.readString();
                    String string14 = parcel.readString();
                    zzl zzlVar6 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface9 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder7 = parcel.readStrongBinder();
                    if (strongBinder7 != null) {
                        IInterface iInterfaceQueryLocalInterface7 = strongBinder7.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                        zzbpeVar = iInterfaceQueryLocalInterface7 instanceof zzbpj ? (zzbpj) iInterfaceQueryLocalInterface7 : new zzbph(strongBinder7);
                    }
                    ?? r52 = zzbpeVar;
                    zzboe zzboeVarZzb6 = zzbod.zzb(parcel.readStrongBinder());
                    zzq zzqVar3 = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                    zzatq.zzc(parcel);
                    zzk(string13, string14, zzlVar6, iObjectWrapperAsInterface9, r52, zzboeVarZzb6, zzqVar3);
                    parcel2.writeNoException();
                    break;
                case 22:
                    String string15 = parcel.readString();
                    String string16 = parcel.readString();
                    zzl zzlVar7 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface10 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder8 = parcel.readStrongBinder();
                    if (strongBinder8 != null) {
                        IInterface iInterfaceQueryLocalInterface8 = strongBinder8.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
                        zzbpeVar = iInterfaceQueryLocalInterface8 instanceof zzbpp ? (zzbpp) iInterfaceQueryLocalInterface8 : new zzbpn(strongBinder8);
                    }
                    zzboe zzboeVarZzb7 = zzbod.zzb(parcel.readStrongBinder());
                    zzbee zzbeeVar = (zzbee) zzatq.zza(parcel, zzbee.CREATOR);
                    zzatq.zzc(parcel);
                    zzn(string15, string16, zzlVar7, iObjectWrapperAsInterface10, zzbpeVar, zzboeVarZzb7, zzbeeVar);
                    parcel2.writeNoException();
                    break;
                case 23:
                    String string17 = parcel.readString();
                    String string18 = parcel.readString();
                    zzl zzlVar8 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface11 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder9 = parcel.readStrongBinder();
                    if (strongBinder9 != null) {
                        IInterface iInterfaceQueryLocalInterface9 = strongBinder9.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IAppOpenCallback");
                        zzbpeVar = iInterfaceQueryLocalInterface9 instanceof zzbpg ? (zzbpg) iInterfaceQueryLocalInterface9 : new zzbpe(strongBinder9);
                    }
                    zzboe zzboeVarZzb8 = zzbod.zzb(parcel.readStrongBinder());
                    zzatq.zzc(parcel);
                    zzi(string17, string18, zzlVar8, iObjectWrapperAsInterface11, zzbpeVar, zzboeVarZzb8);
                    parcel2.writeNoException();
                    break;
                case 24:
                    IObjectWrapper iObjectWrapperAsInterface12 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    zzatq.zzc(parcel);
                    boolean zZzr = zzr(iObjectWrapperAsInterface12);
                    parcel2.writeNoException();
                    parcel2.writeInt(zZzr ? 1 : 0);
                    break;
                default:
                    return false;
            }
        } else {
            parcel.createStringArray();
            zzatq.zzc(parcel);
            parcel2.writeNoException();
        }
        return true;
    }
}
