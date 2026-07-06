package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzhv extends zzfg implements zzgk {
    private static final zzhv zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private int zzg;
    private zzie zzh;
    private int zzi;

    static {
        zzhv zzhvVar = new zzhv();
        zzb = zzhvVar;
        zzfg.zzw(zzhv.class, zzhvVar);
    }

    public static zzhv zzA(byte[] bArr, zzes zzesVar) throws zzfo {
        return (zzhv) zzfg.zzp(zzb, bArr, zzesVar);
    }

    public static void zzC(zzhv zzhvVar, zzij zzijVar) {
        zzhvVar.zzi = zzijVar.zzf;
        zzhvVar.zzd |= 4;
    }

    public static /* synthetic */ void zzD(zzhv zzhvVar, zzie zzieVar) {
        zzieVar.getClass();
        zzhvVar.zzh = zzieVar;
        zzhvVar.zzd |= 2;
    }

    public static /* synthetic */ void zzE(zzhv zzhvVar, zzix zzixVar) {
        zzixVar.getClass();
        zzhvVar.zzf = zzixVar;
        zzhvVar.zze = 4;
    }

    public static /* synthetic */ void zzF(zzhv zzhvVar, zzjd zzjdVar) {
        zzjdVar.getClass();
        zzhvVar.zzf = zzjdVar;
        zzhvVar.zze = 7;
    }

    public static /* synthetic */ void zzG(zzhv zzhvVar, zzjt zzjtVar) {
        zzjtVar.getClass();
        zzhvVar.zzf = zzjtVar;
        zzhvVar.zze = 6;
    }

    public static /* synthetic */ void zzH(zzhv zzhvVar, int i) {
        zzhvVar.zzg = i - 1;
        zzhvVar.zzd |= 1;
    }

    public static zzht zzc() {
        return (zzht) zzb.zzl();
    }

    public final zzjd zzB() {
        return this.zze == 7 ? (zzjd) this.zzf : zzjd.zzd();
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0006\u0001\u0001\u0001\u0007\u0006\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0004<\u0000\u0005᠌\u0002\u0006<\u0000\u0007<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzhw.zza, "zzh", zzix.class, "zzi", zzii.zza, zzjt.class, zzjd.class});
        }
        if (i2 == 3) {
            return new zzhv();
        }
        zzhu zzhuVar = null;
        if (i2 == 4) {
            return new zzht(zzhuVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
