package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzit extends zzfg implements zzgk {
    private static final zzit zzb;
    private int zzd;
    private boolean zze;
    private boolean zzf;

    static {
        zzit zzitVar = new zzit();
        zzb = zzitVar;
        zzfg.zzw(zzit.class, zzitVar);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzit();
        }
        zzis zzisVar = null;
        if (i2 == 4) {
            return new zzir(zzisVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
