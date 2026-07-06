package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzjb extends zzfg implements zzgk {
    private static final zzjb zzb;
    private int zzd;
    private int zze;
    private String zzf = "";

    static {
        zzjb zzjbVar = new zzjb();
        zzb = zzjbVar;
        zzfg.zzw(zzjb.class, zzjbVar);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", zzja.zza, "zzf"});
        }
        if (i2 == 3) {
            return new zzjb();
        }
        zzjc zzjcVar = null;
        if (i2 == 4) {
            return new zziz(zzjcVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
