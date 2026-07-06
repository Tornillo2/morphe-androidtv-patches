package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaab implements PendingResult.StatusListener {
    public final /* synthetic */ BasePendingResult zaa;
    public final /* synthetic */ zaad zab;

    public zaab(zaad zaadVar, BasePendingResult basePendingResult) {
        this.zab = zaadVar;
        this.zaa = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        this.zab.zaa.remove(this.zaa);
    }
}
