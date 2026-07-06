package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzhz extends zzfg implements zzgk {
    private static final zzhz zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private int zzg;
    private int zzh;

    static {
        zzhz zzhzVar = new zzhz();
        zzb = zzhzVar;
        zzfg.zzw(zzhz.class, zzhzVar);
    }

    public static void zzB(zzhz zzhzVar, zzij zzijVar) {
        zzhzVar.zzh = zzijVar.zzf;
        zzhzVar.zzd |= 2;
    }

    public static /* synthetic */ void zzC(zzhz zzhzVar, zzix zzixVar) {
        zzixVar.getClass();
        zzhzVar.zzf = zzixVar;
        zzhzVar.zze = 2;
    }

    public static /* synthetic */ void zzD(zzhz zzhzVar, zzjd zzjdVar) {
        zzjdVar.getClass();
        zzhzVar.zzf = zzjdVar;
        zzhzVar.zze = 4;
    }

    public static /* synthetic */ void zzE(zzhz zzhzVar, zzjt zzjtVar) {
        zzjtVar.getClass();
        zzhzVar.zzf = zzjtVar;
        zzhzVar.zze = 3;
    }

    public static /* synthetic */ void zzF(zzhz zzhzVar, int i) {
        zzhzVar.zzg = i - 1;
        zzhzVar.zzd |= 1;
    }

    public static zzhx zzc() {
        return (zzhx) zzb.zzl();
    }

    public final zzjd zzA() {
        return this.zze == 4 ? (zzjd) this.zzf : zzjd.zzd();
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0005\u0001\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005᠌\u0001", new Object[]{"zzf", "zze", "zzd", "zzg", zzhw.zza, zzix.class, zzjt.class, zzjd.class, "zzh", zzii.zza});
        }
        if (i2 == 3) {
            return new zzhz();
        }
        zzhy zzhyVar = null;
        if (i2 == 4) {
            return new zzhx(zzhyVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
