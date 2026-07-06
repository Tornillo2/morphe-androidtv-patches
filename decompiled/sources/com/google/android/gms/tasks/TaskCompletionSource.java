package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class TaskCompletionSource<TResult> {
    public final zzw zza = new zzw();

    public TaskCompletionSource() {
    }

    @NonNull
    public Task<TResult> getTask() {
        return this.zza;
    }

    public void setException(@NonNull Exception exc) {
        this.zza.zza(exc);
    }

    public void setResult(@Nullable TResult tresult) {
        this.zza.zzb(tresult);
    }

    public boolean trySetException(@NonNull Exception exc) {
        return this.zza.zzd(exc);
    }

    public boolean trySetResult(@Nullable TResult tresult) {
        return this.zza.zze(tresult);
    }

    public TaskCompletionSource(@NonNull CancellationToken cancellationToken) {
        cancellationToken.onCanceledRequested(new zzs(this));
    }
}
