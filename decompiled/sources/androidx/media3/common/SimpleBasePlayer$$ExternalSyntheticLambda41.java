package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda41 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer f$0;
    public final /* synthetic */ SimpleBasePlayer.State f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda41(SimpleBasePlayer simpleBasePlayer, SimpleBasePlayer.State state, List list, int i) {
        this.f$0 = simpleBasePlayer;
        this.f$1 = state;
        this.f$2 = list;
        this.f$3 = i;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return SimpleBasePlayer.m69$r8$lambda$YxXW4ZvUu2T4o5ykO2tFFGKszA(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
