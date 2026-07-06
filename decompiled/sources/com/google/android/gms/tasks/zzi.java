package com.google.android.gms.tasks;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzi implements Runnable {
    public final /* synthetic */ Task zza;
    public final /* synthetic */ zzj zzb;

    public zzi(zzj zzjVar, Task task) {
        this.zzb = zzjVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzb) {
            try {
                OnCompleteListener onCompleteListener = this.zzb.zzc;
                if (onCompleteListener != null) {
                    onCompleteListener.onComplete(this.zza);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
