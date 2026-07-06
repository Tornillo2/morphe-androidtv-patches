package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzea extends zzee {
    public final int zzc;

    public zzea(byte[] bArr, int i, int i2) {
        super(bArr);
        zzeg.zzh(0, i2, bArr.length);
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzee, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeg
    public final byte zza(int i) {
        int i2 = this.zzc;
        if (((i2 - (i + 1)) | i) >= 0) {
            return ((zzee) this).zza[i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Index < 0: ", i));
        }
        throw new ArrayIndexOutOfBoundsException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Index > length: ", i, ", ", i2));
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzee, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeg
    public final byte zzb(int i) {
        return ((zzee) this).zza[i];
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzee
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzee, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzeg
    public final int zzd() {
        return this.zzc;
    }
}
