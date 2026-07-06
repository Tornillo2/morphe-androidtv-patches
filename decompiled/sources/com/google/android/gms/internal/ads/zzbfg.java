package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbfg extends zzatp implements zzbfh {
    public zzbfg() {
        super("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                IObjectWrapper iObjectWrapperZzg = zzg();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, iObjectWrapperZzg);
                return true;
            case 3:
                String strZzk = zzk();
                parcel2.writeNoException();
                parcel2.writeString(strZzk);
                return true;
            case 4:
                List listZzm = zzm();
                parcel2.writeNoException();
                parcel2.writeList(listZzm);
                return true;
            case 5:
                String strZzi = zzi();
                parcel2.writeNoException();
                parcel2.writeString(strZzi);
                return true;
            case 6:
                zzbeq zzbeqVarZze = zze();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbeqVarZze);
                return true;
            case 7:
                String strZzj = zzj();
                parcel2.writeNoException();
                parcel2.writeString(strZzj);
                return true;
            case 8:
                String strZzh = zzh();
                parcel2.writeNoException();
                parcel2.writeString(strZzh);
                return true;
            case 9:
                Bundle bundleZzb = zzb();
                parcel2.writeNoException();
                zzatq.zze(parcel2, bundleZzb);
                return true;
            case 10:
                zzn();
                parcel2.writeNoException();
                return true;
            case 11:
                zzdq zzdqVarZzc = zzc();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdqVarZzc);
                return true;
            case 12:
                Bundle bundle = (Bundle) zzatq.zza(parcel, Bundle.CREATOR);
                zzatq.zzc(parcel);
                zzo(bundle);
                parcel2.writeNoException();
                return true;
            case 13:
                Bundle bundle2 = (Bundle) zzatq.zza(parcel, Bundle.CREATOR);
                zzatq.zzc(parcel);
                boolean zZzq = zzq(bundle2);
                parcel2.writeNoException();
                parcel2.writeInt(zZzq ? 1 : 0);
                return true;
            case 14:
                Bundle bundle3 = (Bundle) zzatq.zza(parcel, Bundle.CREATOR);
                zzatq.zzc(parcel);
                zzp(bundle3);
                parcel2.writeNoException();
                return true;
            case 15:
                zzbei zzbeiVarZzd = zzd();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzbeiVarZzd);
                return true;
            case 16:
                IObjectWrapper iObjectWrapperZzf = zzf();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, iObjectWrapperZzf);
                return true;
            case 17:
                String strZzl = zzl();
                parcel2.writeNoException();
                parcel2.writeString(strZzl);
                return true;
            default:
                return false;
        }
    }
}
