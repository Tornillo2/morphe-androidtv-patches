package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.errorprone.annotations.concurrent.GuardedBy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaan extends zabg {
    public final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks zaa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaan(zaao zaaoVar, zabf zabfVar, BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zabfVar);
        this.zaa = connectionProgressReportCallbacks;
    }

    @Override // com.google.android.gms.common.api.internal.zabg
    @GuardedBy("lock")
    public final void zaa() {
        this.zaa.onReportServiceBinding(new ConnectionResult(16, null));
    }
}
