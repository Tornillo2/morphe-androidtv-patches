package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.base.Supplier;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer f$0;
    public final /* synthetic */ SimpleBasePlayer.State f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda0(SimpleBasePlayer simpleBasePlayer, SimpleBasePlayer.State state, List list, int i) {
        this.f$0 = simpleBasePlayer;
        this.f$1 = state;
        this.f$2 = list;
        this.f$3 = i;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return SimpleBasePlayer.m413$r8$lambda$nm4OGf5ysRisvmSpC1xJrQdFwU(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
