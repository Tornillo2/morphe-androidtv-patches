package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdp;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbpt extends zzato implements zzbpv {
    public zzbpt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final zzdq zze() throws RemoteException {
        Parcel parcelZzbg = zzbg(5, zza());
        zzdq zzdqVarZzb = zzdp.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdqVarZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final zzbqj zzf() throws RemoteException {
        Parcel parcelZzbg = zzbg(2, zza());
        zzbqj zzbqjVar = (zzbqj) zzatq.zza(parcelZzbg, zzbqj.CREATOR);
        parcelZzbg.recycle();
        return zzbqjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final zzbqj zzg() throws RemoteException {
        Parcel parcelZzbg = zzbg(3, zza());
        zzbqj zzbqjVar = (zzbqj) zzatq.zza(parcelZzbg, zzbqj.CREATOR);
        parcelZzbg.recycle();
        return zzbqjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzh(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, zzq zzqVar, zzbpy zzbpyVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        zzatq.zzd(parcelZza, bundle);
        zzatq.zzd(parcelZza, bundle2);
        zzatq.zzd(parcelZza, zzqVar);
        zzatq.zzf(parcelZza, zzbpyVar);
        zzbh(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzi(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpg zzbpgVar, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbpgVar);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(23, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzj(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpj zzbpjVar, zzboe zzboeVar, zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbpjVar);
        zzatq.zzf(parcelZza, zzboeVar);
        zzatq.zzd(parcelZza, zzqVar);
        zzbh(13, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzk(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpj zzbpjVar, zzboe zzboeVar, zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbpjVar);
        zzatq.zzf(parcelZza, zzboeVar);
        zzatq.zzd(parcelZza, zzqVar);
        zzbh(21, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzl(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpm zzbpmVar, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbpmVar);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(14, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzm(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpp zzbppVar, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbppVar);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(18, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzn(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpp zzbppVar, zzboe zzboeVar, zzbee zzbeeVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbppVar);
        zzatq.zzf(parcelZza, zzboeVar);
        zzatq.zzd(parcelZza, zzbeeVar);
        zzbh(22, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzo(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbps zzbpsVar, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbpsVar);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(20, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzp(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbps zzbpsVar, zzboe zzboeVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzatq.zzd(parcelZza, zzlVar);
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbpsVar);
        zzatq.zzf(parcelZza, zzboeVar);
        zzbh(16, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzq(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzbh(19, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final boolean zzr(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        Parcel parcelZzbg = zzbg(24, parcelZza);
        boolean z = parcelZzbg.readInt() != 0;
        parcelZzbg.recycle();
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final boolean zzs(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        Parcel parcelZzbg = zzbg(15, parcelZza);
        boolean z = parcelZzbg.readInt() != 0;
        parcelZzbg.recycle();
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final boolean zzt(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        Parcel parcelZzbg = zzbg(17, parcelZza);
        boolean z = parcelZzbg.readInt() != 0;
        parcelZzbg.recycle();
        return z;
    }
}
