package com.google.android.gms.common.api.internal;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;
import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zadc {
    public static final Status zaa = new Status(8, "The connection to Google Play services was lost", null, null);

    @VisibleForTesting
    public final Set zab = DesugarCollections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    public final zadb zac = new zadb(this);

    public final void zaa(BasePendingResult basePendingResult) {
        this.zab.add(basePendingResult);
        basePendingResult.zan(this.zac);
    }

    public final void zab() {
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.zab.toArray(new BasePendingResult[0])) {
            basePendingResult.zan(null);
            if (basePendingResult.zam()) {
                this.zab.remove(basePendingResult);
            }
        }
    }
}
