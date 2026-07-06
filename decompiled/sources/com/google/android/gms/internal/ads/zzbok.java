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
public final class zzbok extends zzato implements IInterface {
    public zzbok(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
    }

    public final Bundle zze() throws RemoteException {
        Parcel parcelZzbg = zzbg(13, zza());
        Bundle bundle = (Bundle) zzatq.zza(parcelZzbg, Bundle.CREATOR);
        parcelZzbg.recycle();
        return bundle;
    }

    public final zzdq zzf() throws RemoteException {
        Parcel parcelZzbg = zzbg(16, zza());
        zzdq zzdqVarZzb = zzdp.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdqVarZzb;
    }

    public final zzbei zzg() throws RemoteException {
        Parcel parcelZzbg = zzbg(19, zza());
        zzbei zzbeiVarZzj = zzbeh.zzj(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbeiVarZzj;
    }

    public final zzbeq zzh() throws RemoteException {
        Parcel parcelZzbg = zzbg(5, zza());
        zzbeq zzbeqVarZzg = zzbep.zzg(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbeqVarZzg;
    }

    public final IObjectWrapper zzi() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(15, zza()));
    }

    public final IObjectWrapper zzj() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(20, zza()));
    }

    public final IObjectWrapper zzk() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(21, zza()));
    }

    public final String zzl() throws RemoteException {
        Parcel parcelZzbg = zzbg(7, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
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

    public final List zzp() throws RemoteException {
        Parcel parcelZzbg = zzbg(3, zza());
        ArrayList arrayListZzb = zzatq.zzb(parcelZzbg);
        parcelZzbg.recycle();
        return arrayListZzb;
    }

    public final void zzq(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(9, parcelZza);
    }

    public final void zzr() throws RemoteException {
        zzbh(8, zza());
    }

    public final void zzs(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(10, parcelZza);
    }

    public final void zzt(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, iObjectWrapper2);
        zzatq.zzf(parcelZza, iObjectWrapper3);
        zzbh(22, parcelZza);
    }

    public final void zzu(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(14, parcelZza);
    }

    public final boolean zzv() throws RemoteException {
        Parcel parcelZzbg = zzbg(12, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    public final boolean zzw() throws RemoteException {
        Parcel parcelZzbg = zzbg(11, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }
}
