package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda1 implements SimpleBasePlayer.PositionSupplier {
    public final /* synthetic */ long f$0;

    public /* synthetic */ SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda1(long j) {
        this.f$0 = j;
    }

    @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
    public final long get() {
        long j = this.f$0;
        SimpleBasePlayer.PositionSupplier.CC.lambda$getConstant$0(j);
        return j;
    }
}
