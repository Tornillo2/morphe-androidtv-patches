package com.google.android.gms.tasks;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzg implements Runnable {
    public final /* synthetic */ zzh zza;

    public zzg(zzh zzhVar) {
        this.zza = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza.zzb) {
            try {
                OnCanceledListener onCanceledListener = this.zza.zzc;
                if (onCanceledListener != null) {
                    onCanceledListener.onCanceled();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
