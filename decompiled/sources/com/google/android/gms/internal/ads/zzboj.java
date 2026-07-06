package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
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
public final class zzboj extends zzato implements IInterface {
    public zzboj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
    }

    public final double zze() throws RemoteException {
        Parcel parcelZzbg = zzbg(7, zza());
        double d = parcelZzbg.readDouble();
        parcelZzbg.recycle();
        return d;
    }

    public final Bundle zzf() throws RemoteException {
        Parcel parcelZzbg = zzbg(15, zza());
        Bundle bundle = (Bundle) zzatq.zza(parcelZzbg, Bundle.CREATOR);
        parcelZzbg.recycle();
        return bundle;
    }

    public final zzdq zzg() throws RemoteException {
        Parcel parcelZzbg = zzbg(17, zza());
        zzdq zzdqVarZzb = zzdp.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdqVarZzb;
    }

    public final zzbei zzh() throws RemoteException {
        Parcel parcelZzbg = zzbg(19, zza());
        zzbei zzbeiVarZzj = zzbeh.zzj(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbeiVarZzj;
    }

    public final zzbeq zzi() throws RemoteException {
        Parcel parcelZzbg = zzbg(5, zza());
        zzbeq zzbeqVarZzg = zzbep.zzg(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbeqVarZzg;
    }

    public final IObjectWrapper zzj() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(18, zza()));
    }

    public final IObjectWrapper zzk() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(20, zza()));
    }

    public final IObjectWrapper zzl() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(21, zza()));
    }

    public final String zzm() throws RemoteException {
        Parcel parcelZzbg = zzbg(4, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    public final String zzn() throws RemoteException {
        Parcel parcelZzbg = zzbg(6, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    public final String zzo() throws RemoteException {
        Parcel parcelZzbg = zzbg(2, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    public final String zzp() throws RemoteException {
        Parcel parcelZzbg = zzbg(9, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    public final String zzq() throws RemoteException {
        Parcel parcelZzbg = zzbg(8, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    public final List zzr() throws RemoteException {
        Parcel parcelZzbg = zzbg(3, zza());
        ArrayList arrayListZzb = zzatq.zzb(parcelZzbg);
        parcelZzbg.recycle();
        return arrayListZzb;
    }

    public final void zzs(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(11, parcelZza);
    }

    public final void zzt() throws RemoteException {
        zzbh(10, zza());
    }

    public final void zzu(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(12, parcelZza);
    }

    public final void zzv(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, iObjectWrapper2);
        zzatq.zzf(parcelZza, iObjectWrapper3);
        zzbh(22, parcelZza);
    }

    public final void zzw(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(16, parcelZza);
    }

    public final boolean zzx() throws RemoteException {
        Parcel parcelZzbg = zzbg(14, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    public final boolean zzy() throws RemoteException {
        Parcel parcelZzbg = zzbg(13, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }
}
