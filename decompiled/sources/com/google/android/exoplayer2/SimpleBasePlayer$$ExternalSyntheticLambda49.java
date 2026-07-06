package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda49 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ MediaMetadata f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda49(SimpleBasePlayer.State state, MediaMetadata mediaMetadata) {
        this.f$0 = state;
        this.f$1 = mediaMetadata;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return SimpleBasePlayer.$r8$lambda$_c7k193WP3EJwxUpNBynMzLqLRM(this.f$0, this.f$1);
    }
}
