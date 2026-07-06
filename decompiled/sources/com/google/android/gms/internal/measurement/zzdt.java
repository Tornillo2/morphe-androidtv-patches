package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzdt implements Runnable {
    public final long zzh;
    public final long zzi;
    public final boolean zzj;
    public final /* synthetic */ zzee zzk;

    public zzdt(zzee zzeeVar, boolean z) {
        this.zzk = zzeeVar;
        this.zzh = zzeeVar.zza.currentTimeMillis();
        this.zzi = zzeeVar.zza.elapsedRealtime();
        this.zzj = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzk.zzh) {
            zzb();
            return;
        }
        try {
            zza();
        } catch (Exception e) {
            this.zzk.zzS(e, false, this.zzj);
            zzb();
        }
    }

    public abstract void zza() throws RemoteException;

    public void zzb() {
    }
}
