package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzato;
import com.google.android.gms.internal.ads.zzatq;
import com.google.android.gms.internal.ads.zzavu;
import com.google.android.gms.internal.ads.zzbci;
import com.google.android.gms.internal.ads.zzbse;
import com.google.android.gms.internal.ads.zzbsh;
import com.google.android.gms.internal.ads.zzbvc;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbs extends zzato implements zzbu {
    public zzbs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdManager");
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzA() throws RemoteException {
        zzbh(11, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzB() throws RemoteException {
        zzbh(6, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzC(zzbe zzbeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbeVar);
        zzbh(20, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzD(zzbh zzbhVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbhVar);
        zzbh(7, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzE(zzby zzbyVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzF(zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzqVar);
        zzbh(13, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzG(zzcb zzcbVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzcbVar);
        zzbh(8, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzH(zzavu zzavuVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzavuVar);
        zzbh(40, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzI(zzw zzwVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzwVar);
        zzbh(39, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzJ(zzci zzciVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzciVar);
        zzbh(45, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzK(zzdu zzduVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzL(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i = zzatq.zza;
        parcelZza.writeInt(z ? 1 : 0);
        zzbh(34, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzM(zzbse zzbseVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzN(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i = zzatq.zza;
        parcelZza.writeInt(z ? 1 : 0);
        zzbh(22, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzO(zzbci zzbciVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzP(zzdg zzdgVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzdgVar);
        zzbh(42, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzQ(zzbsh zzbshVar, String str) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzR(String str) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzS(zzbvc zzbvcVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzT(String str) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzU(zzfl zzflVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzflVar);
        zzbh(29, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzW(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(44, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzX() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final boolean zzY() throws RemoteException {
        Parcel parcelZzbg = zzbg(23, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final boolean zzZ() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final boolean zzaa(zzl zzlVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzlVar);
        Parcel parcelZzbg = zzbg(4, parcelZza);
        boolean z = parcelZzbg.readInt() != 0;
        parcelZzbg.recycle();
        return z;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzab(zzcf zzcfVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final Bundle zzd() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final zzq zzg() throws RemoteException {
        Parcel parcelZzbg = zzbg(12, zza());
        zzq zzqVar = (zzq) zzatq.zza(parcelZzbg, zzq.CREATOR);
        parcelZzbg.recycle();
        return zzqVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final zzbh zzi() throws RemoteException {
        zzbh zzbfVar;
        Parcel parcelZzbg = zzbg(33, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbfVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
            zzbfVar = iInterfaceQueryLocalInterface instanceof zzbh ? (zzbh) iInterfaceQueryLocalInterface : new zzbf(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbfVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final zzcb zzj() throws RemoteException {
        zzcb zzbzVar;
        Parcel parcelZzbg = zzbg(32, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbzVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
            zzbzVar = iInterfaceQueryLocalInterface instanceof zzcb ? (zzcb) iInterfaceQueryLocalInterface : new zzbz(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbzVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final zzdn zzk() throws RemoteException {
        zzdn zzdlVar;
        Parcel parcelZzbg = zzbg(41, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzdlVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IResponseInfo");
            zzdlVar = iInterfaceQueryLocalInterface instanceof zzdn ? (zzdn) iInterfaceQueryLocalInterface : new zzdl(strongBinder);
        }
        parcelZzbg.recycle();
        return zzdlVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final zzdq zzl() throws RemoteException {
        zzdq zzdoVar;
        Parcel parcelZzbg = zzbg(26, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzdoVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
            zzdoVar = iInterfaceQueryLocalInterface instanceof zzdq ? (zzdq) iInterfaceQueryLocalInterface : new zzdo(strongBinder);
        }
        parcelZzbg.recycle();
        return zzdoVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final IObjectWrapper zzn() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(1, zza()));
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final String zzr() throws RemoteException {
        Parcel parcelZzbg = zzbg(31, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final String zzs() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final String zzt() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzx() throws RemoteException {
        zzbh(2, zza());
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzy(zzl zzlVar, zzbk zzbkVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, zzbkVar);
        zzbh(43, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbu
    public final void zzz() throws RemoteException {
        zzbh(5, zza());
    }
}
