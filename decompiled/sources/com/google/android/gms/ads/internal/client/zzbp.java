package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.ads.zzatp;
import com.google.android.gms.internal.ads.zzatq;
import com.google.android.gms.internal.ads.zzbee;
import com.google.android.gms.internal.ads.zzbfn;
import com.google.android.gms.internal.ads.zzbfo;
import com.google.android.gms.internal.ads.zzbfq;
import com.google.android.gms.internal.ads.zzbfr;
import com.google.android.gms.internal.ads.zzbft;
import com.google.android.gms.internal.ads.zzbfu;
import com.google.android.gms.internal.ads.zzbfw;
import com.google.android.gms.internal.ads.zzbfx;
import com.google.android.gms.internal.ads.zzbga;
import com.google.android.gms.internal.ads.zzbgb;
import com.google.android.gms.internal.ads.zzbgd;
import com.google.android.gms.internal.ads.zzbge;
import com.google.android.gms.internal.ads.zzbkq;
import com.google.android.gms.internal.ads.zzbky;
import com.google.android.gms.internal.ads.zzbkz;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbp extends zzatp implements zzbq {
    public zzbp() {
        super("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzbh zzbfVar = null;
        zzcf zzcfVar = null;
        switch (i) {
            case 1:
                zzbn zzbnVarZze = zze();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbnVarZze);
                return true;
            case 2:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzbfVar = iInterfaceQueryLocalInterface instanceof zzbh ? (zzbh) iInterfaceQueryLocalInterface : new zzbf(strongBinder);
                }
                zzatq.zzc(parcel);
                zzl(zzbfVar);
                parcel2.writeNoException();
                return true;
            case 3:
                zzbfo zzbfoVarZzb = zzbfn.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzf(zzbfoVarZzb);
                parcel2.writeNoException();
                return true;
            case 4:
                zzbfr zzbfrVarZzb = zzbfq.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzg(zzbfrVarZzb);
                parcel2.writeNoException();
                return true;
            case 5:
                String string = parcel.readString();
                zzbfx zzbfxVarZzb = zzbfw.zzb(parcel.readStrongBinder());
                zzbfu zzbfuVarZzb = zzbft.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzh(string, zzbfxVarZzb, zzbfuVarZzb);
                parcel2.writeNoException();
                return true;
            case 6:
                zzbee zzbeeVar = (zzbee) zzatq.zza(parcel, zzbee.CREATOR);
                zzatq.zzc(parcel);
                zzo(zzbeeVar);
                parcel2.writeNoException();
                return true;
            case 7:
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzcfVar = iInterfaceQueryLocalInterface2 instanceof zzcf ? (zzcf) iInterfaceQueryLocalInterface2 : new zzcf(strongBinder2);
                }
                zzatq.zzc(parcel);
                zzq(zzcfVar);
                parcel2.writeNoException();
                return true;
            case 8:
                zzbgb zzbgbVarZzb = zzbga.zzb(parcel.readStrongBinder());
                zzq zzqVar = (zzq) zzatq.zza(parcel, zzq.CREATOR);
                zzatq.zzc(parcel);
                zzj(zzbgbVarZzb, zzqVar);
                parcel2.writeNoException();
                return true;
            case 9:
                PublisherAdViewOptions publisherAdViewOptions = (PublisherAdViewOptions) zzatq.zza(parcel, PublisherAdViewOptions.CREATOR);
                zzatq.zzc(parcel);
                zzp(publisherAdViewOptions);
                parcel2.writeNoException();
                return true;
            case 10:
                zzbge zzbgeVarZzb = zzbgd.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzk(zzbgeVarZzb);
                parcel2.writeNoException();
                return true;
            case 11:
            case 12:
            default:
                return false;
            case 13:
                zzbkq zzbkqVar = (zzbkq) zzatq.zza(parcel, zzbkq.CREATOR);
                zzatq.zzc(parcel);
                zzn(zzbkqVar);
                parcel2.writeNoException();
                return true;
            case 14:
                zzbkz zzbkzVarZzb = zzbky.zzb(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzi(zzbkzVarZzb);
                parcel2.writeNoException();
                return true;
            case 15:
                AdManagerAdViewOptions adManagerAdViewOptions = (AdManagerAdViewOptions) zzatq.zza(parcel, AdManagerAdViewOptions.CREATOR);
                zzatq.zzc(parcel);
                zzm(adManagerAdViewOptions);
                parcel2.writeNoException();
                return true;
        }
    }
}
