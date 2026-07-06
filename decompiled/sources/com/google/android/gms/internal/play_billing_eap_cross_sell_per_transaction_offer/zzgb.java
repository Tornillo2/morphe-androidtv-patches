package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzgb implements zzgu {
    public static final zzgh zza = new zzfz();
    public final zzgh zzb;

    public zzgb() {
        int i = zzgq.zza;
        zzga zzgaVar = new zzga(zzfb.zza, zza);
        byte[] bArr = zzfm.zzb;
        this.zzb = zzgaVar;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgu
    public final zzgt zza(Class cls) {
        int i = zzgv.zza;
        if (!zzfg.class.isAssignableFrom(cls)) {
            int i2 = zzgq.zza;
        }
        zzgg zzggVarZzb = this.zzb.zzb(cls);
        if (zzggVarZzb.zzb()) {
            int i3 = zzgq.zza;
            return new zzgn(zzgv.zzb, zzev.zza(), zzggVarZzb.zza());
        }
        int i4 = zzgq.zza;
        return zzgm.zzl(cls, zzggVarZzb, zzgp.zza(), zzfx.zza(), zzgv.zzb, zzggVarZzb.zzc() + (-1) != 1 ? zzev.zza() : null, zzgf.zza());
    }
}
