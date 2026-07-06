package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.nativead.NativeAd;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbra extends NativeAd.AdChoicesInfo {
    public final List zza = new ArrayList();
    public String zzb;

    public zzbra(zzbei zzbeiVar) {
        try {
            this.zzb = zzbeiVar.zzg();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            this.zzb = "";
        }
        try {
            for (Object obj : zzbeiVar.zzh()) {
                zzbeq zzbeqVarZzg = obj instanceof IBinder ? zzbep.zzg((IBinder) obj) : null;
                if (zzbeqVarZzg != null) {
                    this.zza.add(new zzbrc(zzbeqVarZzg));
                }
            }
        } catch (RemoteException e2) {
            zzbzt.zzh("", e2);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd.AdChoicesInfo
    public final List<NativeAd.Image> getImages() {
        return this.zza;
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd.AdChoicesInfo
    public final CharSequence getText() {
        return this.zzb;
    }
}
