package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdg extends zzfg implements zzgk {
    private static final zzdg zzb;
    private int zzd;
    private zzdl zze;
    private zzdl zzf;
    private int zzg;

    static {
        zzdg zzdgVar = new zzdg();
        zzb = zzdgVar;
        zzfg.zzw(zzdg.class, zzdgVar);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003᠌\u0002", new Object[]{"zzd", "zze", "zzf", "zzg", zzdn.zza});
        }
        if (i2 == 3) {
            return new zzdg();
        }
        zzdj zzdjVar = null;
        if (i2 == 4) {
            return new zzdf(zzdjVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
