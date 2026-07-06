package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public enum zzb {
    RESPONSE_CODE_UNSPECIFIED(-999),
    SERVICE_TIMEOUT(-3),
    FEATURE_NOT_SUPPORTED(-2),
    SERVICE_DISCONNECTED(-1),
    OK(0),
    USER_CANCELED(1),
    SERVICE_UNAVAILABLE(2),
    BILLING_UNAVAILABLE(3),
    ITEM_UNAVAILABLE(4),
    DEVELOPER_ERROR(5),
    ERROR(6),
    ITEM_ALREADY_OWNED(7),
    ITEM_NOT_OWNED(8),
    EXPIRED_OFFER_TOKEN(11),
    NETWORK_ERROR(12);

    public static final zzbu zzp;
    public final int zzr;

    static {
        zzbt zzbtVar = new zzbt();
        for (zzb zzbVar : values()) {
            zzbtVar.zza(Integer.valueOf(zzbVar.zzr), zzbVar);
        }
        zzp = zzbtVar.zzb();
    }

    zzb(int i) {
        this.zzr = i;
    }

    public static zzb zza(int i) {
        zzbu zzbuVar = zzp;
        Integer numValueOf = Integer.valueOf(i);
        return !zzbuVar.containsKey(numValueOf) ? RESPONSE_CODE_UNSPECIFIED : (zzb) zzbuVar.get(numValueOf);
    }
}
