package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzs extends zzo {
    public final /* synthetic */ zzt zzg;

    public zzs(zzt zztVar) {
        Objects.requireNonNull(zztVar);
        this.zzg = zztVar;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzo
    public final String zza() {
        zzp zzpVar = (zzp) this.zzg.zza.get();
        return zzpVar == null ? "Completer object has been garbage collected, future will fail soon" : MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("tag=[", String.valueOf(zzpVar.zza), "]");
    }
}
