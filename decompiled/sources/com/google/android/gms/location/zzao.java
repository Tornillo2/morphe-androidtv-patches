package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzao extends com.google.android.gms.internal.location.zzah {
    public final TaskCompletionSource<Void> zza;

    public zzao(TaskCompletionSource<Void> taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.location.zzai
    public final void zzb(com.google.android.gms.internal.location.zzaa zzaaVar) {
        TaskUtil.setResultOrApiException(zzaaVar.zzb, null, this.zza);
    }

    public void zzc() {
    }
}
