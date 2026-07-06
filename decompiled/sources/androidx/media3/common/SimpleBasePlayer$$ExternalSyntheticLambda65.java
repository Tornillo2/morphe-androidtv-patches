package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda65 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ TrackSelectionParameters f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda65(SimpleBasePlayer.State state, TrackSelectionParameters trackSelectionParameters) {
        this.f$0 = state;
        this.f$1 = trackSelectionParameters;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return SimpleBasePlayer.m72$r8$lambda$e0U_mSusx48zst6CylazaO1v6c(this.f$0, this.f$1);
    }
}
