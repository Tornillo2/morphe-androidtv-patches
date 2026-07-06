package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzboc extends zzato implements zzboe {
    public zzboc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zze() throws RemoteException {
        zzbh(1, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzf() throws RemoteException {
        zzbh(2, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzg(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzbh(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzh(zze zzeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzeVar);
        zzbh(23, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzi(int i, String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        parcelZza.writeString(str);
        zzbh(22, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzj(int i) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzk(zze zzeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzeVar);
        zzbh(24, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzl(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzbh(21, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzm() throws RemoteException {
        zzbh(8, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzn() throws RemoteException {
        zzbh(4, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzo() throws RemoteException {
        zzbh(6, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzp() throws RemoteException {
        zzbh(5, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzq(String str, String str2) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzbh(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzr(zzbfk zzbfkVar, String str) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbfkVar);
        parcelZza.writeString(str);
        zzbh(10, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzs(zzbvi zzbviVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzt(zzbvm zzbvmVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbvmVar);
        zzbh(16, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzu() throws RemoteException {
        zzbh(18, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzv() throws RemoteException {
        zzbh(11, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzw() throws RemoteException {
        zzbh(15, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzx() throws RemoteException {
        zzbh(20, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzy() throws RemoteException {
        zzbh(13, zza());
    }
}
