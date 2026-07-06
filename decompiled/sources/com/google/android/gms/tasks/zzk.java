package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzk implements Runnable {
    public final /* synthetic */ Task zza;
    public final /* synthetic */ zzl zzb;

    public zzk(zzl zzlVar, Task task) {
        this.zzb = zzlVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzb) {
            try {
                OnFailureListener onFailureListener = this.zzb.zzc;
                if (onFailureListener != null) {
                    Exception exception = this.zza.getException();
                    Preconditions.checkNotNull(exception);
                    onFailureListener.onFailure(exception);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
