package com.google.android.gms.tasks;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzm implements Runnable {
    public final /* synthetic */ Task zza;
    public final /* synthetic */ zzn zzb;

    public zzm(zzn zznVar, Task task) {
        this.zzb = zznVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzb) {
            try {
                OnSuccessListener onSuccessListener = this.zzb.zzc;
                if (onSuccessListener != null) {
                    onSuccessListener.onSuccess(this.zza.getResult());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
