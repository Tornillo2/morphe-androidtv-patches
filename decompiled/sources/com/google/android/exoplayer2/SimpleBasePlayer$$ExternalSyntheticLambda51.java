package com.google.android.exoplayer2;

import android.view.SurfaceHolder;
import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda51 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ SurfaceHolder f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda51(SimpleBasePlayer.State state, SurfaceHolder surfaceHolder) {
        this.f$0 = state;
        this.f$1 = surfaceHolder;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return SimpleBasePlayer.$r8$lambda$HGSUszyuIP1qAGkZ96MgTgndKWg(this.f$0, this.f$1);
    }
}
