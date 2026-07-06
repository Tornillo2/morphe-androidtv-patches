package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import sun.misc.Unsafe;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzhk extends zzhl {
    public zzhk(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhl
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhl
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhl
    public final void zzc(Object obj, long j, boolean z) {
        if (zzhm.zzb) {
            zzhm.zzD(obj, j, z ? (byte) 1 : (byte) 0);
        } else {
            zzhm.zzE(obj, j, z ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhl
    public final void zzd(Object obj, long j, byte b) {
        if (zzhm.zzb) {
            zzhm.zzD(obj, j, b);
        } else {
            zzhm.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhl
    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhl
    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhl
    public final boolean zzg(Object obj, long j) {
        return zzhm.zzb ? zzhm.zzt(obj, j) : zzhm.zzu(obj, j);
    }
}
