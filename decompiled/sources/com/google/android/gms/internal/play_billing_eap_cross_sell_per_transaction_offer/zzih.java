package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzih extends zzfg implements zzgk {
    private static final zzih zzb;

    static {
        zzih zzihVar = new zzih();
        zzb = zzihVar;
        zzfg.zzw(zzih.class, zzihVar);
    }

    public static zzih zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        zzig zzigVar = null;
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0000", null);
        }
        if (i2 == 3) {
            return new zzih();
        }
        if (i2 == 4) {
            return new zzif(zzigVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
