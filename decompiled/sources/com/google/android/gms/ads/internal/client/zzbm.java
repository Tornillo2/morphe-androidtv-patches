package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzatp;
import com.google.android.gms.internal.ads.zzatq;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbm extends zzatp implements zzbn {
    public zzbm() {
        super("com.google.android.gms.ads.internal.client.IAdLoader");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzl zzlVar = (zzl) zzatq.zza(parcel, zzl.CREATOR);
            zzatq.zzc(parcel);
            zzg(zzlVar);
            parcel2.writeNoException();
            return true;
        }
        if (i == 2) {
            String strZze = zze();
            parcel2.writeNoException();
            parcel2.writeString(strZze);
            return true;
        }
        if (i == 3) {
            boolean zZzi = zzi();
            parcel2.writeNoException();
            int i3 = zzatq.zza;
            parcel2.writeInt(zZzi ? 1 : 0);
            return true;
        }
        if (i == 4) {
            String strZzf = zzf();
            parcel2.writeNoException();
            parcel2.writeString(strZzf);
            return true;
        }
        if (i != 5) {
            return false;
        }
        zzl zzlVar2 = (zzl) zzatq.zza(parcel, zzl.CREATOR);
        int i4 = parcel.readInt();
        zzatq.zzc(parcel);
        zzh(zzlVar2, i4);
        parcel2.writeNoException();
        return true;
    }
}
