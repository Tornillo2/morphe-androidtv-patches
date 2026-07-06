package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.common.base.Supplier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda54 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ TrackSelectionParameters f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda54(SimpleBasePlayer.State state, TrackSelectionParameters trackSelectionParameters) {
        this.f$0 = state;
        this.f$1 = trackSelectionParameters;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return SimpleBasePlayer.$r8$lambda$RqeXMcqORXA1fG8j9_5QuODxiqw(this.f$0, this.f$1);
    }
}
