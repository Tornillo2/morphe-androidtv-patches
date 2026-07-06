package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzjd extends zzfg implements zzgk {
    private static final zzjd zzb;
    private int zzd;
    private zzfl zze = zzgr.zze();
    private String zzf = "";
    private boolean zzg;

    static {
        zzjd zzjdVar = new zzjd();
        zzb = zzjdVar;
        zzfg.zzw(zzjd.class, zzjdVar);
    }

    public static /* synthetic */ void zzA(zzjd zzjdVar, boolean z) {
        zzjdVar.zzd |= 2;
        zzjdVar.zzg = z;
    }

    public static zzjd zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဇ\u0001", new Object[]{"zzd", "zze", zzjb.class, "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzjd();
        }
        zzjc zzjcVar = null;
        if (i2 == 4) {
            return new zziy(zzjcVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
