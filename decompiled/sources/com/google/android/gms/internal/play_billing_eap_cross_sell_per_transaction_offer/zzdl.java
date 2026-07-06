package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdl extends zzfg implements zzgk {
    private static final zzdl zzb;
    private int zzd;
    private String zze = "";

    static {
        zzdl zzdlVar = new zzdl();
        zzb = zzdlVar;
        zzfg.zzw(zzdl.class, zzdlVar);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzdl();
        }
        zzdm zzdmVar = null;
        if (i2 == 4) {
            return new zzdk(zzdmVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
