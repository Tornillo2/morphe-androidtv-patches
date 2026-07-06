package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzbs$$ExternalSyntheticOutline0;
import com.google.android.gms.ads.internal.client.zzdp;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbel extends zzato implements zzben {
    public zzbel(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IMediaContent");
    }

    @Override // com.google.android.gms.internal.ads.zzben
    public final float zze() throws RemoteException {
        Parcel parcelZzbg = zzbg(2, zza());
        float f = parcelZzbg.readFloat();
        parcelZzbg.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.ads.zzben
    public final float zzf() throws RemoteException {
        Parcel parcelZzbg = zzbg(6, zza());
        float f = parcelZzbg.readFloat();
        parcelZzbg.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.ads.zzben
    public final float zzg() throws RemoteException {
        Parcel parcelZzbg = zzbg(5, zza());
        float f = parcelZzbg.readFloat();
        parcelZzbg.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.ads.zzben
    public final zzdq zzh() throws RemoteException {
        Parcel parcelZzbg = zzbg(7, zza());
        zzdq zzdqVarZzb = zzdp.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdqVarZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzben
    public final IObjectWrapper zzi() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(4, zza()));
    }

    @Override // com.google.android.gms.internal.ads.zzben
    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzben
    public final boolean zzk() throws RemoteException {
        Parcel parcelZzbg = zzbg(10, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzben
    public final boolean zzl() throws RemoteException {
        Parcel parcelZzbg = zzbg(8, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzben
    public final void zzm(zzbfy zzbfyVar) throws RemoteException {
        throw null;
    }
}
