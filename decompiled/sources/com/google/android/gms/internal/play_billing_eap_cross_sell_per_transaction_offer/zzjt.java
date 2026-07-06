package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzjt extends zzfg implements zzgk {
    private static final zzjt zzb;
    private int zzd;
    private int zze;
    private boolean zzf;
    private long zzg;
    private boolean zzh;
    private int zzi;

    static {
        zzjt zzjtVar = new zzjt();
        zzb = zzjtVar;
        zzfg.zzw(zzjt.class, zzjtVar);
    }

    public static /* synthetic */ void zzA(zzjt zzjtVar, boolean z) {
        zzjtVar.zzd |= 8;
        zzjtVar.zzh = z;
    }

    public static /* synthetic */ void zzB(zzjt zzjtVar, int i) {
        zzjtVar.zzd |= 16;
        zzjtVar.zzi = i;
    }

    public static /* synthetic */ void zzC(zzjt zzjtVar, long j) {
        zzjtVar.zzd |= 4;
        zzjtVar.zzg = j;
    }

    public static /* synthetic */ void zzD(zzjt zzjtVar, boolean z) {
        zzjtVar.zzd |= 2;
        zzjtVar.zzf = true;
    }

    public static zzjr zzc() {
        return (zzjr) zzb.zzl();
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001င\u0000\u0002ဇ\u0001\u0003ဂ\u0002\u0004ဇ\u0003\u0005င\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzjt();
        }
        zzjs zzjsVar = null;
        if (i2 == 4) {
            return new zzjr(zzjsVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
