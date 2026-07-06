package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzatp;
import com.google.android.gms.internal.ads.zzatq;
import com.google.android.gms.internal.ads.zzbkk;
import com.google.android.gms.internal.ads.zzbkl;
import com.google.android.gms.internal.ads.zzbnx;
import com.google.android.gms.internal.ads.zzbny;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzcn extends zzatp implements zzco {
    public zzcn() {
        super("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzda zzcyVar;
        switch (i) {
            case 1:
                zzk();
                parcel2.writeNoException();
                return true;
            case 2:
                float f = parcel.readFloat();
                zzatq.zzc(parcel);
                zzq(f);
                parcel2.writeNoException();
                return true;
            case 3:
                String string = parcel.readString();
                zzatq.zzc(parcel);
                zzr(string);
                parcel2.writeNoException();
                return true;
            case 4:
                boolean zZzg = zzatq.zzg(parcel);
                zzatq.zzc(parcel);
                zzp(zZzg);
                parcel2.writeNoException();
                return true;
            case 5:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                String string2 = parcel.readString();
                zzatq.zzc(parcel);
                zzn(iObjectWrapperAsInterface, string2);
                parcel2.writeNoException();
                return true;
            case 6:
                String string3 = parcel.readString();
                IObjectWrapper iObjectWrapperAsInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzl(string3, iObjectWrapperAsInterface2);
                parcel2.writeNoException();
                return true;
            case 7:
                float fZze = zze();
                parcel2.writeNoException();
                parcel2.writeFloat(fZze);
                return true;
            case 8:
                boolean zZzv = zzv();
                parcel2.writeNoException();
                int i3 = zzatq.zza;
                parcel2.writeInt(zZzv ? 1 : 0);
                return true;
            case 9:
                String strZzf = zzf();
                parcel2.writeNoException();
                parcel2.writeString(strZzf);
                return true;
            case 10:
                String string4 = parcel.readString();
                zzatq.zzc(parcel);
                zzh(string4);
                parcel2.writeNoException();
                return true;
            case 11:
                zzbny zzbnyVarZzf = zzbnx.zzf(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzo(zzbnyVarZzf);
                parcel2.writeNoException();
                return true;
            case 12:
                zzbkl zzbklVarZzc = zzbkk.zzc(parcel.readStrongBinder());
                zzatq.zzc(parcel);
                zzs(zzbklVarZzc);
                parcel2.writeNoException();
                return true;
            case 13:
                List listZzg = zzg();
                parcel2.writeNoException();
                parcel2.writeTypedList(listZzg);
                return true;
            case 14:
                zzff zzffVar = (zzff) zzatq.zza(parcel, zzff.CREATOR);
                zzatq.zzc(parcel);
                zzu(zzffVar);
                parcel2.writeNoException();
                return true;
            case 15:
                zzi();
                parcel2.writeNoException();
                return true;
            case 16:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zzcyVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IOnAdInspectorClosedListener");
                    zzcyVar = iInterfaceQueryLocalInterface instanceof zzda ? (zzda) iInterfaceQueryLocalInterface : new zzcy(strongBinder);
                }
                zzatq.zzc(parcel);
                zzm(zzcyVar);
                parcel2.writeNoException();
                return true;
            case 17:
                boolean zZzg2 = zzatq.zzg(parcel);
                zzatq.zzc(parcel);
                zzj(zZzg2);
                parcel2.writeNoException();
                return true;
            case 18:
                String string5 = parcel.readString();
                zzatq.zzc(parcel);
                zzt(string5);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
