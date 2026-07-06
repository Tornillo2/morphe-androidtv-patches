package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda57 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda57(SimpleBasePlayer.State state, int i, long j) {
        this.f$0 = state;
        this.f$1 = i;
        this.f$2 = j;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        SimpleBasePlayer.State state = this.f$0;
        return SimpleBasePlayer.getStateWithNewPlaylistAndPosition(state, state.playlist, this.f$1, this.f$2);
    }
}
