package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbdx {
    public final Context zza;

    public zzbdx(Context context) {
        this.zza = context;
    }

    public final void zza(zzbtf zzbtfVar) {
        try {
            ((zzbdy) zzbzx.zzb(this.zza, "com.google.android.gms.ads.flags.FlagRetrieverSupplierProxy", zzbdw.zza)).zze(zzbtfVar);
        } catch (RemoteException e) {
            zzbzt.zzj("Error calling setFlagsAccessedBeforeInitializedListener: ".concat(String.valueOf(e.getMessage())));
        } catch (zzbzw e2) {
            zzbzt.zzj("Could not load com.google.android.gms.ads.flags.FlagRetrieverSupplierProxy:".concat(String.valueOf(e2.getMessage())));
        }
    }
}
