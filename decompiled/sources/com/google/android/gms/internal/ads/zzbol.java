package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzbs$$ExternalSyntheticOutline0;
import com.google.android.gms.ads.internal.client.zzdp;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbol extends zzato implements zzbon {
    public zzbol(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final boolean zzA() throws RemoteException {
        Parcel parcelZzbg = zzbg(18, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final boolean zzB() throws RemoteException {
        Parcel parcelZzbg = zzbg(17, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final double zze() throws RemoteException {
        Parcel parcelZzbg = zzbg(8, zza());
        double d = parcelZzbg.readDouble();
        parcelZzbg.recycle();
        return d;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final float zzf() throws RemoteException {
        Parcel parcelZzbg = zzbg(23, zza());
        float f = parcelZzbg.readFloat();
        parcelZzbg.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final float zzg() throws RemoteException {
        Parcel parcelZzbg = zzbg(25, zza());
        float f = parcelZzbg.readFloat();
        parcelZzbg.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final float zzh() throws RemoteException {
        Parcel parcelZzbg = zzbg(24, zza());
        float f = parcelZzbg.readFloat();
        parcelZzbg.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final Bundle zzi() throws RemoteException {
        Parcel parcelZzbg = zzbg(16, zza());
        Bundle bundle = (Bundle) zzatq.zza(parcelZzbg, Bundle.CREATOR);
        parcelZzbg.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final zzdq zzj() throws RemoteException {
        Parcel parcelZzbg = zzbg(11, zza());
        zzdq zzdqVarZzb = zzdp.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdqVarZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final zzbei zzk() throws RemoteException {
        Parcel parcelZzbg = zzbg(12, zza());
        zzbei zzbeiVarZzj = zzbeh.zzj(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbeiVarZzj;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final zzbeq zzl() throws RemoteException {
        Parcel parcelZzbg = zzbg(5, zza());
        zzbeq zzbeqVarZzg = zzbep.zzg(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbeqVarZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final IObjectWrapper zzm() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(13, zza()));
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final IObjectWrapper zzn() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(14, zza()));
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final IObjectWrapper zzo() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(15, zza()));
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzp() throws RemoteException {
        Parcel parcelZzbg = zzbg(7, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzq() throws RemoteException {
        Parcel parcelZzbg = zzbg(4, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzr() throws RemoteException {
        Parcel parcelZzbg = zzbg(6, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzs() throws RemoteException {
        Parcel parcelZzbg = zzbg(2, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzt() throws RemoteException {
        Parcel parcelZzbg = zzbg(10, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final String zzu() throws RemoteException {
        Parcel parcelZzbg = zzbg(9, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final List zzv() throws RemoteException {
        Parcel parcelZzbg = zzbg(3, zza());
        ArrayList arrayListZzb = zzatq.zzb(parcelZzbg);
        parcelZzbg.recycle();
        return arrayListZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final void zzw(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(20, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final void zzx() throws RemoteException {
        zzbh(19, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final void zzy(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, iObjectWrapper2);
        zzatq.zzf(parcelZza, iObjectWrapper3);
        zzbh(21, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbon
    public final void zzz(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(22, parcelZza);
    }
}
