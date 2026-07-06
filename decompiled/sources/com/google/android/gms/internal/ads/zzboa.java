package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzboa extends zzatp implements zzbob {
    public zzboa() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzboe zzbocVar;
        zzboe zzbocVar2;
        zzboe zzbocVar3;
        zzboe zzbocVar4;
        zzboe zzbocVar5;
        zzboe zzbocVar6;
        zzboe zzbocVar7;
        zzboe zzbocVar8;
        zzboe zzbocVar9 = null;
        switch (i) {
            case 1:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzq zzqVar = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                zzl zzlVar = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string = parcel.readString();
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zzbocVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzbocVar = iInterfaceQueryLocalInterface instanceof zzboe ? (zzboe) iInterfaceQueryLocalInterface : new zzboc(strongBinder);
                }
                zzatq.zzc(parcel);
                zzu(iObjectWrapperAsInterface, zzqVar, zzlVar, string, zzbocVar);
                parcel2.writeNoException();
                return true;
            case 2:
                IObjectWrapper iObjectWrapperZzn = zzn();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, iObjectWrapperZzn);
                return true;
            case 3:
                IObjectWrapper iObjectWrapperAsInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzl zzlVar2 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string2 = parcel.readString();
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 == null) {
                    zzbocVar2 = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzbocVar2 = iInterfaceQueryLocalInterface2 instanceof zzboe ? (zzboe) iInterfaceQueryLocalInterface2 : new zzboc(strongBinder2);
                }
                zzatq.zzc(parcel);
                zzx(iObjectWrapperAsInterface2, zzlVar2, string2, zzbocVar2);
                parcel2.writeNoException();
                return true;
            case 4:
                zzI();
                parcel2.writeNoException();
                return true;
            case 5:
                zzo();
                parcel2.writeNoException();
                return true;
            case 6:
                IObjectWrapper iObjectWrapperAsInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzq zzqVar2 = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                zzl zzlVar3 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string3 = parcel.readString();
                String string4 = parcel.readString();
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 == null) {
                    zzbocVar3 = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzbocVar3 = iInterfaceQueryLocalInterface3 instanceof zzboe ? (zzboe) iInterfaceQueryLocalInterface3 : new zzboc(strongBinder3);
                }
                zzatq.zzc(parcel);
                zzv(iObjectWrapperAsInterface3, zzqVar2, zzlVar3, string3, string4, zzbocVar3);
                parcel2.writeNoException();
                return true;
            case 7:
                IObjectWrapper iObjectWrapperAsInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzl zzlVar4 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string5 = parcel.readString();
                String string6 = parcel.readString();
                IBinder strongBinder4 = parcel.readStrongBinder();
                if (strongBinder4 == null) {
                    zzbocVar4 = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzbocVar4 = iInterfaceQueryLocalInterface4 instanceof zzboe ? (zzboe) iInterfaceQueryLocalInterface4 : new zzboc(strongBinder4);
                }
                zzatq.zzc(parcel);
                zzy(iObjectWrapperAsInterface4, zzlVar4, string5, string6, zzbocVar4);
                parcel2.writeNoException();
                return true;
            case 8:
                zzE();
                parcel2.writeNoException();
                return true;
            case 9:
                zzF();
                parcel2.writeNoException();
                return true;
            case 10:
                IObjectWrapper iObjectWrapperAsInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzl zzlVar5 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string7 = parcel.readString();
                zzbvh zzbvhVarZzb = zzbvg.zzb(parcel.readStrongBinder());
                String string8 = parcel.readString();
                zzatq.zzc(parcel);
                zzp(iObjectWrapperAsInterface5, zzlVar5, string7, zzbvhVarZzb, string8);
                parcel2.writeNoException();
                return true;
            case 11:
                zzl zzlVar6 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string9 = parcel.readString();
                zzatq.zzc(parcel);
                zzs(zzlVar6, string9);
                parcel2.writeNoException();
                return true;
            case 12:
                zzL();
                parcel2.writeNoException();
                return true;
            case 13:
                boolean zZzN = zzN();
                parcel2.writeNoException();
                int i3 = zzatq.zza;
                parcel2.writeInt(zZzN ? 1 : 0);
                return true;
            case 14:
                IObjectWrapper iObjectWrapperAsInterface6 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzl zzlVar7 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string10 = parcel.readString();
                String string11 = parcel.readString();
                IBinder strongBinder5 = parcel.readStrongBinder();
                if (strongBinder5 == null) {
                    zzbocVar5 = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzbocVar5 = iInterfaceQueryLocalInterface5 instanceof zzboe ? (zzboe) iInterfaceQueryLocalInterface5 : new zzboc(strongBinder5);
                }
                zzbee zzbeeVar = (zzbee) zzatq.zza(parcel, zzbee.CREATOR);
                ArrayList<String> arrayListCreateStringArrayList = parcel.createStringArrayList();
                zzatq.zzc(parcel);
                zzz(iObjectWrapperAsInterface6, zzlVar7, string10, string11, zzbocVar5, zzbeeVar, arrayListCreateStringArrayList);
                parcel2.writeNoException();
                return true;
            case 15:
                parcel2.writeNoException();
                zzatq.zzf(parcel2, null);
                return true;
            case 16:
                parcel2.writeNoException();
                zzatq.zzf(parcel2, null);
                return true;
            case 17:
                Bundle bundleZze = zze();
                parcel2.writeNoException();
                zzatq.zze(parcel2, bundleZze);
                return true;
            case 18:
                Bundle bundleZzf = zzf();
                parcel2.writeNoException();
                zzatq.zze(parcel2, bundleZzf);
                return true;
            case 19:
                Bundle bundleZzg = zzg();
                parcel2.writeNoException();
                zzatq.zze(parcel2, bundleZzg);
                return true;
            case 20:
                zzl zzlVar8 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string12 = parcel.readString();
                String string13 = parcel.readString();
                zzatq.zzc(parcel);
                zzB(zzlVar8, string12, string13);
                parcel2.writeNoException();
                return true;
            case 21:
                IObjectWrapper iObjectWrapperAsInterface7 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzD(iObjectWrapperAsInterface7);
                parcel2.writeNoException();
                return true;
            case 22:
                parcel2.writeNoException();
                int i4 = zzatq.zza;
                parcel2.writeInt(0);
                return true;
            case 23:
                IObjectWrapper iObjectWrapperAsInterface8 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbvh zzbvhVarZzb2 = zzbvg.zzb(parcel.readStrongBinder());
                ArrayList<String> arrayListCreateStringArrayList2 = parcel.createStringArrayList();
                zzatq.zzc(parcel);
                zzr(iObjectWrapperAsInterface8, zzbvhVarZzb2, arrayListCreateStringArrayList2);
                parcel2.writeNoException();
                return true;
            case 24:
                zzbfk zzbfkVarZzi = zzi();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbfkVarZzi);
                return true;
            case 25:
                boolean zZzg = zzatq.zzg(parcel);
                zzatq.zzc(parcel);
                zzG(zZzg);
                parcel2.writeNoException();
                return true;
            case 26:
                zzdq zzdqVarZzh = zzh();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdqVarZzh);
                return true;
            case 27:
                zzbon zzbonVarZzk = zzk();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbonVarZzk);
                return true;
            case 28:
                IObjectWrapper iObjectWrapperAsInterface9 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzl zzlVar9 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string14 = parcel.readString();
                IBinder strongBinder6 = parcel.readStrongBinder();
                if (strongBinder6 == null) {
                    zzbocVar6 = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface6 = strongBinder6.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzbocVar6 = iInterfaceQueryLocalInterface6 instanceof zzboe ? (zzboe) iInterfaceQueryLocalInterface6 : new zzboc(strongBinder6);
                }
                zzatq.zzc(parcel);
                zzA(iObjectWrapperAsInterface9, zzlVar9, string14, zzbocVar6);
                parcel2.writeNoException();
                return true;
            case 29:
            default:
                return false;
            case 30:
                IObjectWrapper iObjectWrapperAsInterface10 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzK(iObjectWrapperAsInterface10);
                parcel2.writeNoException();
                return true;
            case 31:
                IObjectWrapper iObjectWrapperAsInterface11 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzbki zzbkiVarZzb = zzbkh.zzb(parcel.readStrongBinder());
                ArrayList arrayListCreateTypedArrayList = parcel.createTypedArrayList(zzbko.CREATOR);
                zzatq.zzc(parcel);
                zzq(iObjectWrapperAsInterface11, zzbkiVarZzb, arrayListCreateTypedArrayList);
                parcel2.writeNoException();
                return true;
            case 32:
                IObjectWrapper iObjectWrapperAsInterface12 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzl zzlVar10 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string15 = parcel.readString();
                IBinder strongBinder7 = parcel.readStrongBinder();
                if (strongBinder7 == null) {
                    zzbocVar7 = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface7 = strongBinder7.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzbocVar7 = iInterfaceQueryLocalInterface7 instanceof zzboe ? (zzboe) iInterfaceQueryLocalInterface7 : new zzboc(strongBinder7);
                }
                zzatq.zzc(parcel);
                zzC(iObjectWrapperAsInterface12, zzlVar10, string15, zzbocVar7);
                parcel2.writeNoException();
                return true;
            case 33:
                zzbqj zzbqjVarZzl = zzl();
                parcel2.writeNoException();
                zzatq.zze(parcel2, zzbqjVarZzl);
                return true;
            case 34:
                zzbqj zzbqjVarZzm = zzm();
                parcel2.writeNoException();
                zzatq.zze(parcel2, zzbqjVarZzm);
                return true;
            case 35:
                IObjectWrapper iObjectWrapperAsInterface13 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzq zzqVar3 = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                zzl zzlVar11 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string16 = parcel.readString();
                String string17 = parcel.readString();
                IBinder strongBinder8 = parcel.readStrongBinder();
                if (strongBinder8 == null) {
                    zzbocVar8 = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface8 = strongBinder8.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzbocVar8 = iInterfaceQueryLocalInterface8 instanceof zzboe ? (zzboe) iInterfaceQueryLocalInterface8 : new zzboc(strongBinder8);
                }
                zzatq.zzc(parcel);
                zzw(iObjectWrapperAsInterface13, zzqVar3, zzlVar11, string16, string17, zzbocVar8);
                parcel2.writeNoException();
                return true;
            case 36:
                zzboh zzbohVarZzj = zzj();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbohVarZzj);
                return true;
            case 37:
                IObjectWrapper iObjectWrapperAsInterface14 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzJ(iObjectWrapperAsInterface14);
                parcel2.writeNoException();
                return true;
            case 38:
                IObjectWrapper iObjectWrapperAsInterface15 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzl zzlVar12 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
                String string18 = parcel.readString();
                IBinder strongBinder9 = parcel.readStrongBinder();
                if (strongBinder9 != null) {
                    IInterface iInterfaceQueryLocalInterface9 = strongBinder9.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    zzbocVar9 = iInterfaceQueryLocalInterface9 instanceof zzboe ? (zzboe) iInterfaceQueryLocalInterface9 : new zzboc(strongBinder9);
                }
                zzatq.zzc(parcel);
                zzt(iObjectWrapperAsInterface15, zzlVar12, string18, zzbocVar9);
                parcel2.writeNoException();
                return true;
            case 39:
                IObjectWrapper iObjectWrapperAsInterface16 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzH(iObjectWrapperAsInterface16);
                parcel2.writeNoException();
                return true;
        }
    }
}
