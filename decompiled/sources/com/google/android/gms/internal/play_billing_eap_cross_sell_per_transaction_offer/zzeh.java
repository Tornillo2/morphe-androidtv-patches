package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzeh extends zzej {
    public int zzc;
    public int zzd = Integer.MAX_VALUE;
    public int zzb = 0;

    public /* synthetic */ zzeh(byte[] bArr, int i, int i2, boolean z, zzei zzeiVar) {
    }

    public final int zza(int i) throws zzfo {
        int i2 = this.zzd;
        this.zzd = 0;
        int i3 = this.zzb + this.zzc;
        this.zzb = i3;
        if (i3 <= 0) {
            this.zzc = 0;
            return i2;
        }
        this.zzc = i3;
        this.zzb = 0;
        return i2;
    }
}
