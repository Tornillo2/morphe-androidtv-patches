package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzbzt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzer implements Runnable {
    public final /* synthetic */ zzet zza;

    public zzer(zzet zzetVar) {
        this.zza = zzetVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeu zzeuVar = this.zza.zza;
        if (zzeuVar.zza != null) {
            try {
                zzeuVar.zza.zze(1);
            } catch (RemoteException e) {
                zzbzt.zzk("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
