package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzbs$$ExternalSyntheticOutline0;
import com.google.android.gms.ads.internal.client.zzdp;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbnz extends zzato implements zzbob {
    public zzbnz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzA(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzlVar);
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(28, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzB(zzl zzlVar, String str, String str2) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzC(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzlVar);
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(32, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzD(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(21, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzE() throws RemoteException {
        zzbh(8, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzF() throws RemoteException {
        zzbh(9, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzG(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i = zzatq.zza;
        parcelZza.writeInt(z ? 1 : 0);
        zzbh(25, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzH(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(39, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzI() throws RemoteException {
        zzbh(4, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzJ(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(37, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzK(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(30, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzL() throws RemoteException {
        zzbh(12, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final boolean zzM() throws RemoteException {
        Parcel parcelZzbg = zzbg(22, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final boolean zzN() throws RemoteException {
        Parcel parcelZzbg = zzbg(13, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final zzboj zzO() throws RemoteException {
        zzboj zzbojVar;
        Parcel parcelZzbg = zzbg(15, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbojVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
            zzbojVar = iInterfaceQueryLocalInterface instanceof zzboj ? (zzboj) iInterfaceQueryLocalInterface : new zzboj(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbojVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final zzbok zzP() throws RemoteException {
        zzbok zzbokVar;
        Parcel parcelZzbg = zzbg(16, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbokVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
            zzbokVar = iInterfaceQueryLocalInterface instanceof zzbok ? (zzbok) iInterfaceQueryLocalInterface : new zzbok(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbokVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final Bundle zze() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final Bundle zzf() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final Bundle zzg() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final zzdq zzh() throws RemoteException {
        Parcel parcelZzbg = zzbg(26, zza());
        zzdq zzdqVarZzb = zzdp.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdqVarZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final zzbfk zzi() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final zzboh zzj() throws RemoteException {
        zzboh zzbofVar;
        Parcel parcelZzbg = zzbg(36, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbofVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationInterscrollerAd");
            zzbofVar = iInterfaceQueryLocalInterface instanceof zzboh ? (zzboh) iInterfaceQueryLocalInterface : new zzbof(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbofVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final zzbon zzk() throws RemoteException {
        zzbon zzbolVar;
        Parcel parcelZzbg = zzbg(27, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbolVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
            zzbolVar = iInterfaceQueryLocalInterface instanceof zzbon ? (zzbon) iInterfaceQueryLocalInterface : new zzbol(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbolVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final zzbqj zzl() throws RemoteException {
        Parcel parcelZzbg = zzbg(33, zza());
        zzbqj zzbqjVar = (zzbqj) zzatq.zza(parcelZzbg, zzbqj.CREATOR);
        parcelZzbg.recycle();
        return zzbqjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final zzbqj zzm() throws RemoteException {
        Parcel parcelZzbg = zzbg(34, zza());
        zzbqj zzbqjVar = (zzbqj) zzatq.zza(parcelZzbg, zzbqj.CREATOR);
        parcelZzbg.recycle();
        return zzbqjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final IObjectWrapper zzn() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(2, zza()));
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzo() throws RemoteException {
        zzbh(5, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzp(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, zzbvh zzbvhVar, String str2) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzlVar);
        parcelZza.writeString(null);
        zzatq.zzf(parcelZza, zzbvhVar);
        parcelZza.writeString(str2);
        zzbh(10, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzq(IObjectWrapper iObjectWrapper, zzbki zzbkiVar, List list) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbkiVar);
        parcelZza.writeTypedList(list);
        zzbh(31, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzr(IObjectWrapper iObjectWrapper, zzbvh zzbvhVar, List list) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbvhVar);
        parcelZza.writeStringList(list);
        zzbh(23, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzs(zzl zzlVar, String str) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzlVar);
        parcelZza.writeString(str);
        zzbh(11, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzt(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzlVar);
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(38, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzu(IObjectWrapper iObjectWrapper, zzq zzqVar, zzl zzlVar, String str, zzboe zzboeVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzv(IObjectWrapper iObjectWrapper, zzq zzqVar, zzl zzlVar, String str, String str2, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzqVar);
        zzatq.zzd(parcelZza, zzlVar);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzw(IObjectWrapper iObjectWrapper, zzq zzqVar, zzl zzlVar, String str, String str2, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzqVar);
        zzatq.zzd(parcelZza, zzlVar);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(35, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzx(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, zzboe zzboeVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzy(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, String str2, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzlVar);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzz(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, String str2, zzboe zzboeVar, zzbee zzbeeVar, List list) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzlVar);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzf(parcelZza, zzboeVar);
        zzatq.zzd(parcelZza, zzbeeVar);
        parcelZza.writeStringList(list);
        zzbh(14, parcelZza);
    }
}
