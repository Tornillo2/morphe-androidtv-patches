package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzee extends zzed {
    public final byte[] zza;

    public zzee(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeg
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzeg) && zzd() == ((zzeg) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (!(obj instanceof zzee)) {
                return obj.equals(this);
            }
            zzee zzeeVar = (zzee) obj;
            int i = super.zza;
            int i2 = ((zzeg) zzeeVar).zza;
            if (i == 0 || i2 == 0 || i == i2) {
                int iZzd = zzd();
                if (iZzd > zzeeVar.zzd()) {
                    throw new IllegalArgumentException("Length too large: " + iZzd + zzd());
                }
                if (iZzd > zzeeVar.zzd()) {
                    throw new IllegalArgumentException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Ran off end of other: 0, ", iZzd, ", ", zzeeVar.zzd()));
                }
                byte[] bArr = this.zza;
                byte[] bArr2 = zzeeVar.zza;
                int i3 = 0;
                int i4 = 0;
                while (i3 < iZzd) {
                    if (bArr[i3] == bArr2[i4]) {
                        i3++;
                        i4++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeg
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeg
    public byte zzb(int i) {
        return this.zza[i];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeg
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeg
    public final int zze(int i, int i2, int i3) {
        return zzfm.zzb(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeg
    public final zzeg zzf(int i, int i2) {
        int iZzh = zzeg.zzh(0, i2, zzd());
        return iZzh == 0 ? zzeg.zzb : new zzea(this.zza, 0, iZzh);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeg
    public final void zzg(zzdx zzdxVar) throws IOException {
        ((zzek) zzdxVar).zzc(this.zza, 0, zzd());
    }
}
