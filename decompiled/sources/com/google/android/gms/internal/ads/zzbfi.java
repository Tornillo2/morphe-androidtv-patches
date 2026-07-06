package com.google.android.gms.internal.ads;

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
public final class zzbfi extends zzato implements zzbfk {
    public zzbfi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final zzdq zze() throws RemoteException {
        Parcel parcelZzbg = zzbg(7, zza());
        zzdq zzdqVarZzb = zzdp.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzdqVarZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final zzben zzf() throws RemoteException {
        zzben zzbelVar;
        Parcel parcelZzbg = zzbg(16, zza());
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

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final zzbeq zzg(String str) throws RemoteException {
        zzbeq zzbeoVar;
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        Parcel parcelZzbg = zzbg(2, parcelZza);
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

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final IObjectWrapper zzh() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(9, zza()));
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final String zzi() throws RemoteException {
        Parcel parcelZzbg = zzbg(4, zza());
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final String zzj(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        Parcel parcelZzbg = zzbg(1, parcelZza);
        String string = parcelZzbg.readString();
        parcelZzbg.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final List zzk() throws RemoteException {
        Parcel parcelZzbg = zzbg(3, zza());
        ArrayList<String> arrayListCreateStringArrayList = parcelZzbg.createStringArrayList();
        parcelZzbg.recycle();
        return arrayListCreateStringArrayList;
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final void zzl() throws RemoteException {
        zzbh(8, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final void zzm() throws RemoteException {
        zzbh(15, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final void zzn(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzbh(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final void zzo() throws RemoteException {
        zzbh(6, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final void zzp(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzbh(14, parcelZza);
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final boolean zzq() throws RemoteException {
        Parcel parcelZzbg = zzbg(12, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final boolean zzr(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        Parcel parcelZzbg = zzbg(17, parcelZza);
        boolean z = parcelZzbg.readInt() != 0;
        parcelZzbg.recycle();
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final boolean zzs(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        Parcel parcelZzbg = zzbg(10, parcelZza);
        boolean z = parcelZzbg.readInt() != 0;
        parcelZzbg.recycle();
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzbfk
    public final boolean zzt() throws RemoteException {
        Parcel parcelZzbg = zzbg(13, zza());
        boolean zZzg = zzatq.zzg(parcelZzbg);
        parcelZzbg.recycle();
        return zZzg;
    }
}
