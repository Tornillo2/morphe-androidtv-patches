package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbem extends zzatp implements zzben {
    public zzbem() {
        super("com.google.android.gms.ads.internal.formats.client.IMediaContent");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzbfy zzbfyVar;
        switch (i) {
            case 2:
                float fZze = zze();
                parcel2.writeNoException();
                parcel2.writeFloat(fZze);
                return true;
            case 3:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzj(iObjectWrapperAsInterface);
                parcel2.writeNoException();
                return true;
            case 4:
                IObjectWrapper iObjectWrapperZzi = zzi();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, iObjectWrapperZzi);
                return true;
            case 5:
                float fZzg = zzg();
                parcel2.writeNoException();
                parcel2.writeFloat(fZzg);
                return true;
            case 6:
                float fZzf = zzf();
                parcel2.writeNoException();
                parcel2.writeFloat(fZzf);
                return true;
            case 7:
                zzdq zzdqVarZzh = zzh();
                parcel2.writeNoException();
                zzatq.zzf(parcel2, zzdqVarZzh);
                return true;
            case 8:
                boolean zZzl = zzl();
                parcel2.writeNoException();
                int i3 = zzatq.zza;
                parcel2.writeInt(zZzl ? 1 : 0);
                return true;
            case 9:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnMediaContentChangedListener");
                    zzbfyVar = iInterfaceQueryLocalInterface instanceof zzbfy ? (zzbfy) iInterfaceQueryLocalInterface : new zzbfy(strongBinder);
                } else {
                    zzbfyVar = null;
                }
                zzatq.zzc(parcel);
                zzm(zzbfyVar);
                throw null;
            case 10:
                boolean zZzk = zzk();
                parcel2.writeNoException();
                int i4 = zzatq.zza;
                parcel2.writeInt(zZzk ? 1 : 0);
                return true;
            default:
                return false;
        }
    }
}
