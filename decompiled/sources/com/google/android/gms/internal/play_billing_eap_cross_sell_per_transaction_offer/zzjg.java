package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzjg extends zzfg implements zzgk {
    private static final zzjg zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private zziq zzg;
    private zzit zzh;

    static {
        zzjg zzjgVar = new zzjg();
        zzb = zzjgVar;
        zzfg.zzw(zzjg.class, zzjgVar);
    }

    public static /* synthetic */ void zzA(zzjg zzjgVar, zzhv zzhvVar) {
        zzjgVar.zzf = zzhvVar;
        zzjgVar.zze = 2;
    }

    public static /* synthetic */ void zzB(zzjg zzjgVar, zzhz zzhzVar) {
        zzjgVar.zzf = zzhzVar;
        zzjgVar.zze = 3;
    }

    public static /* synthetic */ void zzC(zzjg zzjgVar, zzih zzihVar) {
        zzihVar.getClass();
        zzjgVar.zzf = zzihVar;
        zzjgVar.zze = 7;
    }

    public static /* synthetic */ void zzD(zzjg zzjgVar, zziq zziqVar) {
        zziqVar.getClass();
        zzjgVar.zzg = zziqVar;
        zzjgVar.zzd |= 1;
    }

    public static /* synthetic */ void zzE(zzjg zzjgVar, zzjm zzjmVar) {
        zzjmVar.getClass();
        zzjgVar.zzf = zzjmVar;
        zzjgVar.zze = 8;
    }

    public static /* synthetic */ void zzF(zzjg zzjgVar, zzjq zzjqVar) {
        zzjgVar.zzf = zzjqVar;
        zzjgVar.zze = 4;
    }

    public static zzje zzc() {
        return (zzje) zzb.zzl();
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\b\u0001\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဉ\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006ဉ\u0001\u0007<\u0000\b<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzhv.class, zzhz.class, zzjq.class, zzin.class, "zzh", zzih.class, zzjm.class});
        }
        if (i2 == 3) {
            return new zzjg();
        }
        zzjf zzjfVar = null;
        if (i2 == 4) {
            return new zzje(zzjfVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
