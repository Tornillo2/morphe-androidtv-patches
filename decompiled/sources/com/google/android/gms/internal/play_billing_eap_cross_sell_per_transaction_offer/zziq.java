package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zziq extends zzfg implements zzgk {
    private static final zziq zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private int zzh;
    private long zzi;
    private long zzj;
    private boolean zzk;
    private int zzl;
    private int zzm;
    private long zzn;

    static {
        zziq zziqVar = new zziq();
        zzb = zziqVar;
        zzfg.zzw(zziq.class, zziqVar);
    }

    public static /* synthetic */ void zzA(zziq zziqVar, int i) {
        zziqVar.zzd |= 128;
        zziqVar.zzl = i;
    }

    public static /* synthetic */ void zzB(zziq zziqVar, int i) {
        zziqVar.zzd |= 256;
        zziqVar.zzm = i;
    }

    public static /* synthetic */ void zzC(zziq zziqVar, int i) {
        zziqVar.zzd |= 8;
        zziqVar.zzh = i;
    }

    public static /* synthetic */ void zzD(zziq zziqVar, long j) {
        zziqVar.zzd |= 16;
        zziqVar.zzi = j;
    }

    public static /* synthetic */ void zzE(zziq zziqVar, long j) {
        zziqVar.zzd |= 32;
        zziqVar.zzj = j;
    }

    public static /* synthetic */ void zzF(zziq zziqVar, long j) {
        zziqVar.zzd |= 512;
        zziqVar.zzn = 781211065L;
    }

    public static /* synthetic */ void zzG(zziq zziqVar, String str) {
        str.getClass();
        zziqVar.zzd |= 4;
        zziqVar.zzg = str;
    }

    public static /* synthetic */ void zzH(zziq zziqVar, boolean z) {
        zziqVar.zzd |= 64;
        zziqVar.zzk = z;
    }

    public static /* synthetic */ void zzI(zziq zziqVar, String str) {
        str.getClass();
        zziqVar.zzd |= 1;
        zziqVar.zze = str;
    }

    public static /* synthetic */ void zzJ(zziq zziqVar, String str) {
        zziqVar.zzd |= 2;
        zziqVar.zzf = str;
    }

    public static zzio zzc() {
        return (zzio) zzb.zzl();
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0002\u0003င\u0003\u0004ဂ\u0004\u0005ဈ\u0001\u0006ဂ\u0005\u0007ဇ\u0006\bင\u0007\tင\b\nဂ\t", new Object[]{"zzd", "zze", "zzg", "zzh", "zzi", "zzf", "zzj", "zzk", "zzl", "zzm", "zzn"});
        }
        if (i2 == 3) {
            return new zziq();
        }
        zzip zzipVar = null;
        if (i2 == 4) {
            return new zzio(zzipVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
