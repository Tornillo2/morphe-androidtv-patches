package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzbs$$ExternalSyntheticOutline0;
import com.google.android.gms.ads.internal.client.zzcs;
import com.google.android.gms.ads.internal.client.zzcw;
import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zzdm;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzdp;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbgl extends zzato implements zzbgn {
    public zzbgl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final void zzA() throws RemoteException {
        zzbh(28, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final void zzB(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, bundle);
        zzbh(17, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final void zzC() throws RemoteException {
        zzbh(27, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final void zzD(zzcs zzcsVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzcsVar);
        zzbh(26, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final void zzE(zzdg zzdgVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzdgVar);
        zzbh(32, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final void zzF(zzbgk zzbgkVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbgkVar);
        zzbh(21, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final boolean zzG() throws RemoteException {
        Parcel parcelZzbg = zzbg(30, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final boolean zzH() throws RemoteException {
        Parcel parcelZzbg = zzbg(24, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final boolean zzI(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, bundle);
        Parcel parcelZzbg = zzbg(16, parcelZza);
        boolean z = parcelZzbg.readInt() != 0;
        parcelZzbg.recycle();
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final double zze() throws RemoteException {
        Parcel parcelZzbg = zzbg(8, zza());
        double d = parcelZzbg.readDouble();
        parcelZzbg.recycle();
        return d;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final Bundle zzf() throws RemoteException {
        Parcel parcelZzbg = zzbg(20, zza());
        Bundle bundle = (Bundle) zzatq.zza(parcelZzbg, Bundle.CREATOR);
        parcelZzbg.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final zzdn zzg() throws RemoteException {
        Parcel parcelZzbg = zzbg(31, zza());
        zzdn zzdnVarZzb = zzdm.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdnVarZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final zzdq zzh() throws RemoteException {
        Parcel parcelZzbg = zzbg(11, zza());
        zzdq zzdqVarZzb = zzdp.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdqVarZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final zzbei zzi() throws RemoteException {
        zzbei zzbegVar;
        Parcel parcelZzbg = zzbg(14, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbegVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
            zzbegVar = iInterfaceQueryLocalInterface instanceof zzbei ? (zzbei) iInterfaceQueryLocalInterface : new zzbeg(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbegVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final zzben zzj() throws RemoteException {
        zzben zzbelVar;
        Parcel parcelZzbg = zzbg(29, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbelVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IMediaContent");
            zzbelVar = iInterfaceQueryLocalInterface instanceof zzben ? (zzben) iInterfaceQueryLocalInterface : new zzbel(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbelVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final zzbeq zzk() throws RemoteException {
        zzbeq zzbeoVar;
        Parcel parcelZzbg = zzbg(5, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbeoVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
            zzbeoVar = iInterfaceQueryLocalInterface instanceof zzbeq ? (zzbeq) iInterfaceQueryLocalInterface : new zzbeo(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbeoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final IObjectWrapper zzl() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(19, zza()));
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final IObjectWrapper zzm() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(18, zza()));
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final String zzn() throws RemoteException {
        Parcel parcelZzbg = zzbg(7, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final String zzo() throws RemoteException {
        Parcel parcelZzbg = zzbg(4, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final String zzp() throws RemoteException {
        Parcel parcelZzbg = zzbg(6, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final String zzq() throws RemoteException {
        Parcel parcelZzbg = zzbg(2, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final String zzr() throws RemoteException {
        Parcel parcelZzbg = zzbg(12, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final String zzs() throws RemoteException {
        Parcel parcelZzbg = zzbg(10, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final String zzt() throws RemoteException {
        Parcel parcelZzbg = zzbg(9, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final List zzu() throws RemoteException {
        Parcel parcelZzbg = zzbg(3, zza());
        ArrayList arrayListZzb = zzatq.zzb(parcelZzbg);
        parcelZzbg.recycle();
        return arrayListZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final List zzv() throws RemoteException {
        Parcel parcelZzbg = zzbg(23, zza());
        ArrayList arrayListZzb = zzatq.zzb(parcelZzbg);
        parcelZzbg.recycle();
        return arrayListZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final void zzw() throws RemoteException {
        zzbh(22, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final void zzx() throws RemoteException {
        zzbh(13, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final void zzy(zzcw zzcwVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzcwVar);
        zzbh(25, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbgn
    public final void zzz(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, bundle);
        zzbh(15, parcelZza);
    }
}
