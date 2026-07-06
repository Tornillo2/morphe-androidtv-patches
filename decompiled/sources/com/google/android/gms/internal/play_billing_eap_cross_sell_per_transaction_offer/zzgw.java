package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzgw extends zzhb {
    public zzgw() {
        super(null);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhb
    public final void zza() {
        if (!this.zzd) {
            if (this.zzb > 0) {
                ((zzew) ((zzgx) zzg(0)).zzb).zze();
                throw null;
            }
            Iterator it = zzd().iterator();
            if (it.hasNext()) {
                ((zzew) ((Map.Entry) it.next()).getKey()).zze();
                throw null;
            }
        }
        super.zza();
    }
}
