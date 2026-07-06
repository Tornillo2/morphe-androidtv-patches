package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzbzt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzev implements Runnable {
    public final /* synthetic */ zzew zza;

    public zzev(zzew zzewVar) {
        this.zza = zzewVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzew zzewVar = this.zza;
        if (zzewVar.zza != null) {
            try {
                zzewVar.zza.zze(1);
            } catch (RemoteException e) {
                zzbzt.zzk("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
