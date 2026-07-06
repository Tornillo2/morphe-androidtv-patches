package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzbs$$ExternalSyntheticOutline0;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbeo extends zzato implements zzbeq {
    public zzbeo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }

    @Override // com.google.android.gms.internal.ads.zzbeq
    public final double zzb() throws RemoteException {
        Parcel parcelZzbg = zzbg(3, zza());
        double d = parcelZzbg.readDouble();
        parcelZzbg.recycle();
        return d;
    }

    @Override // com.google.android.gms.internal.ads.zzbeq
    public final int zzc() throws RemoteException {
        Parcel parcelZzbg = zzbg(5, zza());
        int i = parcelZzbg.readInt();
        parcelZzbg.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.ads.zzbeq
    public final int zzd() throws RemoteException {
        Parcel parcelZzbg = zzbg(4, zza());
        int i = parcelZzbg.readInt();
        parcelZzbg.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.ads.zzbeq
    public final Uri zze() throws RemoteException {
        Parcel parcelZzbg = zzbg(2, zza());
        Uri uri = (Uri) zzatq.zza(parcelZzbg, Uri.CREATOR);
        parcelZzbg.recycle();
        return uri;
    }

    @Override // com.google.android.gms.internal.ads.zzbeq
    public final IObjectWrapper zzf() throws RemoteException {
        return zzbs$$ExternalSyntheticOutline0.m(zzbg(1, zza()));
    }
}
