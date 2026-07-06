package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzie extends zzfg implements zzgk {
    private static final zzie zzb;
    private int zzd;
    private int zze;
    private int zzg;
    private int zzi;
    private String zzf = "";
    private String zzh = "";

    static {
        zzie zzieVar = new zzie();
        zzb = zzieVar;
        zzfg.zzw(zzie.class, zzieVar);
    }

    public static /* synthetic */ void zzA(zzie zzieVar, String str) {
        zzieVar.zzd |= 8;
        zzieVar.zzh = str;
    }

    public static /* synthetic */ void zzB(zzie zzieVar, String str) {
        str.getClass();
        zzieVar.zzd |= 2;
        zzieVar.zzf = str;
    }

    public static /* synthetic */ void zzC(zzie zzieVar, int i) {
        zzieVar.zzd |= 16;
        zzieVar.zzi = i;
    }

    public static void zzD(zzie zzieVar, zzic zzicVar) {
        zzieVar.zzg = zzicVar.zzbJ;
        zzieVar.zzd |= 4;
    }

    public static /* synthetic */ void zzE(zzie zzieVar, int i) {
        zzieVar.zzd |= 1;
        zzieVar.zze = i;
    }

    public static zzia zzc() {
        return (zzia) zzb.zzl();
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0005\u0000\u0001\u0001\u0007\u0005\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0004᠌\u0002\u0005ဈ\u0003\u0007င\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", zzib.zza, "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzie();
        }
        zzid zzidVar = null;
        if (i2 == 4) {
            return new zzia(zzidVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
