package com.google.android.gms.internal.measurement;

import j$.util.DesugarCollections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzlx extends zzmh {
    public zzlx(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzmh
    public final void zza() {
        if (!this.zzd) {
            for (int i = 0; i < this.zzb.size(); i++) {
                Map.Entry entryZzg = zzg(i);
                if (((zzjs) entryZzg.getKey()).zzc()) {
                    entryZzg.setValue(DesugarCollections.unmodifiableList((List) entryZzg.getValue()));
                }
            }
            for (Map.Entry entry : zzc()) {
                if (((zzjs) entry.getKey()).zzc()) {
                    entry.setValue(DesugarCollections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
