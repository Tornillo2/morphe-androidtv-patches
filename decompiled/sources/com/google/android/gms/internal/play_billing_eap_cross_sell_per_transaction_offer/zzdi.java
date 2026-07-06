package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdi extends zzfg implements zzgk {
    private static final zzdi zzb;
    private zzfl zzd = zzgr.zze();

    static {
        zzdi zzdiVar = new zzdi();
        zzb = zzdiVar;
        zzfg.zzw(zzdi.class, zzdiVar);
    }

    public static zzdh zza() {
        return (zzdh) zzb.zzl();
    }

    public static /* synthetic */ void zzd(zzdi zzdiVar, Iterable iterable) {
        zzfl zzflVar = zzdiVar.zzd;
        if (!zzflVar.zzc()) {
            int size = zzflVar.size();
            zzdiVar.zzd = zzflVar.zzd(size + size);
        }
        zzdq.zzg(iterable, zzdiVar.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzgs(zzb, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzdg.class});
        }
        if (i2 == 3) {
            return new zzdi();
        }
        zzdj zzdjVar = null;
        if (i2 == 4) {
            return new zzdh(zzdjVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
