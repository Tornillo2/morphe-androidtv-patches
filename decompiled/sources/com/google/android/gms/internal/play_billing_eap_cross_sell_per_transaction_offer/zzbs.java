package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbs {
    public final Object zza;
    public final Object zzb;
    public final Object zzc;

    public zzbs(Object obj, Object obj2, Object obj3) {
        this.zza = obj;
        this.zzb = obj2;
        this.zzc = obj3;
    }

    public final IllegalArgumentException zza() {
        Object obj = this.zzc;
        Object obj2 = this.zzb;
        Object obj3 = this.zza;
        String strValueOf = String.valueOf(obj3);
        String strValueOf2 = String.valueOf(obj2);
        String strValueOf3 = String.valueOf(obj3);
        String strValueOf4 = String.valueOf(obj);
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("Multiple entries with same key: ", strValueOf, "=", strValueOf2, " and ");
        sbM.append(strValueOf3);
        sbM.append("=");
        sbM.append(strValueOf4);
        return new IllegalArgumentException(sbM.toString());
    }
}
