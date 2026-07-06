package com.android.billingclient.api;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhv;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhz;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzih;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzij;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjm;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjq;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface zzcn {
    public static final /* synthetic */ int zza = 0;

    static {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbu.zzc("com.android.vending.billing.PURCHASES_UPDATED", zzij.PURCHASES_UPDATED_ACTION, "com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", zzij.LOCAL_PURCHASES_UPDATED_ACTION, "com.android.vending.billing.ALTERNATIVE_BILLING", zzij.ALTERNATIVE_BILLING_ACTION);
    }

    void zza(zzhv zzhvVar);

    void zzb(@Nullable zzhv zzhvVar, int i);

    void zzc(zzhv zzhvVar, int i, long j);

    void zzd(zzhv zzhvVar, long j, boolean z);

    void zze(zzhv zzhvVar, int i, long j, boolean z);

    void zzf(@Nullable zzhz zzhzVar);

    void zzg(@Nullable zzhz zzhzVar, int i);

    void zzh(zzhz zzhzVar, long j, boolean z);

    void zzi(zzih zzihVar);

    void zzj(zzjm zzjmVar);

    void zzk(@Nullable zzjq zzjqVar);
}
