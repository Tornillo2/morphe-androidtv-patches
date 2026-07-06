package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzix extends zzfg implements zzgk {
    private static final zzix zzb;
    private int zzd;
    private int zze;

    static {
        zzix zzixVar = new zzix();
        zzb = zzixVar;
        zzfg.zzw(zzix.class, zzixVar);
    }

    public static /* synthetic */ void zzA(zzix zzixVar, int i) {
        zzixVar.zze = i - 1;
        zzixVar.zzd |= 1;
    }

    public static zziu zzc() {
        return (zziu) zzb.zzl();
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zziv.zza});
        }
        if (i2 == 3) {
            return new zzix();
        }
        zziw zziwVar = null;
        if (i2 == 4) {
            return new zziu(zziwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
