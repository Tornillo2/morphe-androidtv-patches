package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdd;
import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zzdm;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbvn extends zzato implements zzbvp {
    public zzbvn(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final Bundle zzb() throws RemoteException {
        Parcel parcelZzbg = zzbg(9, zza());
        Bundle bundle = (Bundle) zzatq.zza(parcelZzbg, Bundle.CREATOR);
        parcelZzbg.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final zzdn zzc() throws RemoteException {
        Parcel parcelZzbg = zzbg(12, zza());
        zzdn zzdnVarZzb = zzdm.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdnVarZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final zzbvm zzd() throws RemoteException {
        zzbvm zzbvkVar;
        Parcel parcelZzbg = zzbg(11, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbvkVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardItem");
            zzbvkVar = iInterfaceQueryLocalInterface instanceof zzbvm ? (zzbvm) iInterfaceQueryLocalInterface : new zzbvk(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbvkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final String zze() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzf(zzl zzlVar, zzbvw zzbvwVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, zzbvwVar);
        zzbh(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzg(zzl zzlVar, zzbvw zzbvwVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, zzbvwVar);
        zzbh(14, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzh(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i = zzatq.zza;
        parcelZza.writeInt(z ? 1 : 0);
        zzbh(15, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzi(zzdd zzddVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzddVar);
        zzbh(8, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzj(zzdg zzdgVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzdgVar);
        zzbh(13, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzk(zzbvs zzbvsVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbvsVar);
        zzbh(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzl(zzbwd zzbwdVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzbwdVar);
        zzbh(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzn(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final boolean zzo() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvp
    public final void zzp(zzbvx zzbvxVar) throws RemoteException {
        throw null;
    }
}
