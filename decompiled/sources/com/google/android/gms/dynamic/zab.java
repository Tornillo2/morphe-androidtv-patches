package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zab implements zah {
    public final /* synthetic */ Activity zaa;
    public final /* synthetic */ Bundle zab;
    public final /* synthetic */ Bundle zac;
    public final /* synthetic */ DeferredLifecycleHelper zad;

    public zab(DeferredLifecycleHelper deferredLifecycleHelper, Activity activity, Bundle bundle, Bundle bundle2) {
        this.zad = deferredLifecycleHelper;
        this.zaa = activity;
        this.zab = bundle;
        this.zac = bundle2;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final int zaa() {
        return 0;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final void zab(LifecycleDelegate lifecycleDelegate) {
        this.zad.zaa.onInflate(this.zaa, this.zab, this.zac);
    }
}
