package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzato;
import com.google.android.gms.internal.ads.zzatq;
import com.google.android.gms.internal.ads.zzbet;
import com.google.android.gms.internal.ads.zzbeu;
import com.google.android.gms.internal.ads.zzbez;
import com.google.android.gms.internal.ads.zzbfa;
import com.google.android.gms.internal.ads.zzbjf;
import com.google.android.gms.internal.ads.zzbjh;
import com.google.android.gms.internal.ads.zzbji;
import com.google.android.gms.internal.ads.zzbny;
import com.google.android.gms.internal.ads.zzbrn;
import com.google.android.gms.internal.ads.zzbro;
import com.google.android.gms.internal.ads.zzbru;
import com.google.android.gms.internal.ads.zzbrv;
import com.google.android.gms.internal.ads.zzbuz;
import com.google.android.gms.internal.ads.zzbvo;
import com.google.android.gms.internal.ads.zzbvp;
import com.google.android.gms.internal.ads.zzbyj;
import com.google.android.gms.internal.ads.zzbyk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcc extends zzato implements zzce {
    public zzcc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IClientApi");
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbq zzb(IObjectWrapper iObjectWrapper, String str, zzbny zzbnyVar, int i) throws RemoteException {
        zzbq zzboVar;
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(3, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzboVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            zzboVar = iInterfaceQueryLocalInterface instanceof zzbq ? (zzbq) iInterfaceQueryLocalInterface : new zzbo(strongBinder);
        }
        parcelZzbg.recycle();
        return zzboVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbu zzc(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, zzbny zzbnyVar, int i) throws RemoteException {
        zzbu zzbsVar;
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzqVar);
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(13, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbsVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzbsVar = iInterfaceQueryLocalInterface instanceof zzbu ? (zzbu) iInterfaceQueryLocalInterface : new zzbs(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbsVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbu zzd(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, zzbny zzbnyVar, int i) throws RemoteException {
        zzbu zzbsVar;
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzqVar);
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(1, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbsVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzbsVar = iInterfaceQueryLocalInterface instanceof zzbu ? (zzbu) iInterfaceQueryLocalInterface : new zzbs(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbsVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbu zze(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, zzbny zzbnyVar, int i) throws RemoteException {
        zzbu zzbsVar;
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzqVar);
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(2, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbsVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzbsVar = iInterfaceQueryLocalInterface instanceof zzbu ? (zzbu) iInterfaceQueryLocalInterface : new zzbs(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbsVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbu zzf(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, int i) throws RemoteException {
        zzbu zzbsVar;
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzd(parcelZza, zzqVar);
        parcelZza.writeString(str);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(10, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzbsVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            zzbsVar = iInterfaceQueryLocalInterface instanceof zzbu ? (zzbu) iInterfaceQueryLocalInterface : new zzbs(strongBinder);
        }
        parcelZzbg.recycle();
        return zzbsVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzco zzg(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        zzco zzcmVar;
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(9, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzcmVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            zzcmVar = iInterfaceQueryLocalInterface instanceof zzco ? (zzco) iInterfaceQueryLocalInterface : new zzcm(strongBinder);
        }
        parcelZzbg.recycle();
        return zzcmVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzdj zzh(IObjectWrapper iObjectWrapper, zzbny zzbnyVar, int i) throws RemoteException {
        zzdj zzdhVar;
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(17, parcelZza);
        IBinder strongBinder = parcelZzbg.readStrongBinder();
        if (strongBinder == null) {
            zzdhVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IOutOfContextTester");
            zzdhVar = iInterfaceQueryLocalInterface instanceof zzdj ? (zzdj) iInterfaceQueryLocalInterface : new zzdh(strongBinder);
        }
        parcelZzbg.recycle();
        return zzdhVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbeu zzi(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, iObjectWrapper2);
        Parcel parcelZzbg = zzbg(5, parcelZza);
        zzbeu zzbeuVarZzbx = zzbet.zzbx(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbeuVarZzbx;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbfa zzj(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, iObjectWrapper2);
        zzatq.zzf(parcelZza, iObjectWrapper3);
        Parcel parcelZzbg = zzbg(11, parcelZza);
        zzbfa zzbfaVarZze = zzbez.zze(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbfaVarZze;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbji zzk(IObjectWrapper iObjectWrapper, zzbny zzbnyVar, int i, zzbjf zzbjfVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        zzatq.zzf(parcelZza, zzbjfVar);
        Parcel parcelZzbg = zzbg(16, parcelZza);
        zzbji zzbjiVarZzb = zzbjh.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbjiVarZzb;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbro zzl(IObjectWrapper iObjectWrapper, zzbny zzbnyVar, int i) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(15, parcelZza);
        zzbro zzbroVarZzb = zzbrn.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbroVarZzb;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbrv zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        Parcel parcelZzbg = zzbg(8, parcelZza);
        zzbrv zzbrvVarZzG = zzbru.zzG(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbrvVarZzG;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbuz zzn(IObjectWrapper iObjectWrapper, zzbny zzbnyVar, int i) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbvp zzo(IObjectWrapper iObjectWrapper, String str, zzbny zzbnyVar, int i) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(12, parcelZza);
        zzbvp zzbvpVarZzq = zzbvo.zzq(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbvpVarZzq;
    }

    @Override // com.google.android.gms.ads.internal.client.zzce
    public final zzbyk zzp(IObjectWrapper iObjectWrapper, zzbny zzbnyVar, int i) throws RemoteException {
        Parcel parcelZza = zza();
        zzatq.zzf(parcelZza, iObjectWrapper);
        zzatq.zzf(parcelZza, zzbnyVar);
        parcelZza.writeInt(231700000);
        Parcel parcelZzbg = zzbg(14, parcelZza);
        zzbyk zzbykVarZzb = zzbyj.zzb(parcelZzbg.readStrongBinder());
        parcelZzbg.recycle();
        return zzbykVarZzb;
    }
}
