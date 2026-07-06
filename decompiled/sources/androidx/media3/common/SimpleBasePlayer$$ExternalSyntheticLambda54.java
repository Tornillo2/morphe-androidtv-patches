package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda54 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer f$0;
    public final /* synthetic */ SimpleBasePlayer.State f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda54(SimpleBasePlayer simpleBasePlayer, SimpleBasePlayer.State state, int i, int i2, int i3) {
        this.f$0 = simpleBasePlayer;
        this.f$1 = state;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = i3;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return SimpleBasePlayer.$r8$lambda$dNWc6xoZLktGAvAIanZxhQ4QND4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
