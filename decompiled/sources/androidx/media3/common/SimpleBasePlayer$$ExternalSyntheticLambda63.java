package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda63 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ PlaybackParameters f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda63(SimpleBasePlayer.State state, PlaybackParameters playbackParameters) {
        this.f$0 = state;
        this.f$1 = playbackParameters;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return SimpleBasePlayer.$r8$lambda$N143NepUE2kUII04spvrQFUUoE0(this.f$0, this.f$1);
    }
}
