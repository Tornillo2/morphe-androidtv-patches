package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzbpo extends zzatp implements zzbpp {
    public zzbpo() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final boolean zzbE(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzbon zzbonVarZzb = zzbom.zzb(parcel.readStrongBinder());
            zzatq.zzc(parcel);
            zzg(zzbonVarZzb);
        } else if (i == 2) {
            String string = parcel.readString();
            zzatq.zzc(parcel);
            zze(string);
        } else {
            if (i != 3) {
                return false;
            }
            zze zzeVar = (zze) zzatq.zza(parcel, zze.CREATOR);
            zzatq.zzc(parcel);
            zzf(zzeVar);
        }
        parcel2.writeNoException();
        return true;
    }
}
