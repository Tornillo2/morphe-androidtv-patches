package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda36 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ SimpleBasePlayer.State f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda36(SimpleBasePlayer simpleBasePlayer, List list, SimpleBasePlayer.State state, int i, long j) {
        this.f$0 = simpleBasePlayer;
        this.f$1 = list;
        this.f$2 = state;
        this.f$3 = i;
        this.f$4 = j;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return SimpleBasePlayer.$r8$lambda$01PmFwFxQnwjkGSZuekCQx0XfYg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
