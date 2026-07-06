package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzaxd {

    @VisibleForTesting
    public zzatt zza;

    @VisibleForTesting
    public boolean zzb;
    public final ExecutorService zzc;

    public zzaxd() {
        this.zzc = zzbzi.zzb;
    }

    public zzaxd(final Context context) {
        ExecutorService executorService = zzbzi.zzb;
        this.zzc = executorService;
        executorService.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzawy
            @Override // java.lang.Runnable
            public final void run() {
                zzaxd zzaxdVar = this.zza;
                Context context2 = context;
                if (((Boolean) zzba.zza.zzd.zzb(zzbbk.zzeE)).booleanValue()) {
                    try {
                        zzaxdVar.zza = (zzatt) zzbzx.zzb(context2, "com.google.android.gms.ads.clearcut.DynamiteClearcutLogger", zzawz.zza);
                        zzaxdVar.zza.zze(ObjectWrapper.wrap(context2), "GMA_SDK");
                        zzaxdVar.zzb = true;
                    } catch (RemoteException | zzbzw | NullPointerException unused) {
                        zzbzt.zze("Cannot dynamite load clearcut");
                    }
                }
            }
        });
    }
}
