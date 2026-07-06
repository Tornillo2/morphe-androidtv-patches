package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.ads.zzato;
import com.google.android.gms.internal.ads.zzatq;
import com.google.android.gms.internal.ads.zzbee;
import com.google.android.gms.internal.ads.zzbfo;
import com.google.android.gms.internal.ads.zzbfr;
import com.google.android.gms.internal.ads.zzbfu;
import com.google.android.gms.internal.ads.zzbfx;
import com.google.android.gms.internal.ads.zzbgb;
import com.google.android.gms.internal.ads.zzbge;
import com.google.android.gms.internal.ads.zzbkq;
import com.google.android.gms.internal.ads.zzbkz;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbo extends zzato implements zzbq {
    public zzbo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final zzbn zze() throws RemoteException {
        zzbn zzblVar;
        Parcel parcelZzbg = zzbg(1, zza());
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzblVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoader");
            zzblVar = iInterfaceQueryLocalInterface instanceof zzbn ? (zzbn) iInterfaceQueryLocalInterface : new zzbl(strongBinder);
        }
        parcelZzbg.recycle();
        return zzblVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzf(zzbfo zzbfoVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzg(zzbfr zzbfrVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzh(String str, zzbfx zzbfxVar, zzbfu zzbfuVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, zzbfxVar);
        zzatq.zzf(parcelZza, zzbfuVar);
        zzbh(5, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzi(zzbkz zzbkzVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzj(zzbgb zzbgbVar, zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbgbVar);
        zzatq.zzd(parcelZza, zzqVar);
        zzbh(8, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzk(zzbge zzbgeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbgeVar);
        zzbh(10, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzl(zzbh zzbhVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, zzbhVar);
        zzbh(2, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzm(AdManagerAdViewOptions adManagerAdViewOptions) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, adManagerAdViewOptions);
        zzbh(15, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzn(zzbkq zzbkqVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzo(zzbee zzbeeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzd(parcelZza, zzbeeVar);
        zzbh(6, parcelZza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzp(PublisherAdViewOptions publisherAdViewOptions) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbq
    public final void zzq(zzcf zzcfVar) throws RemoteException {
        throw null;
    }
}
