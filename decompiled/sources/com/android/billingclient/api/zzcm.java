package com.android.billingclient.api;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzht;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhv;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhx;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhz;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzia;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzie;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzij;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zzcm {
    public static final /* synthetic */ int zza = 0;

    static {
        int i = zzcn.zza;
    }

    @Nullable
    public static String zza(Exception exc) {
        if (exc == null) {
            return null;
        }
        try {
            String simpleName = exc.getClass().getSimpleName();
            String message = exc.getMessage();
            if (message == null) {
                message = "";
            }
            String str = simpleName + ":" + message;
            int i = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zza;
            return str.length() > 40 ? str.substring(0, 40) : str;
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to get truncated exception info", th);
            return null;
        }
    }

    @Nullable
    public static zzhv zzb(@Nullable zzic zzicVar, int i, BillingResult billingResult, @Nullable String str, zzij zzijVar) {
        try {
            zzia zziaVarZzc = zzie.zzc();
            zziaVarZzc.zzo(billingResult.zza);
            zziaVarZzc.zzl(billingResult.zzc);
            int i2 = billingResult.zzb;
            if (i2 != 0) {
                zziaVarZzc.zzm(i2);
            }
            if (zzicVar != null) {
                zziaVarZzc.zzn(zzicVar);
            }
            if (str != null) {
                zziaVarZzc.zza(str);
            }
            zzht zzhtVarZzc = zzhv.zzc();
            zzhtVarZzc.zzl(zziaVarZzc);
            zzhtVarZzc.zzp(i);
            if (!zzijVar.equals(zzij.BROADCAST_ACTION_UNSPECIFIED)) {
                zzhtVarZzc.zza(zzijVar);
            }
            return (zzhv) zzhtVarZzc.zze();
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to create logging payload", th);
            return null;
        }
    }

    @Nullable
    public static zzhz zzc(int i, zzij zzijVar) {
        try {
            zzhx zzhxVarZzc = zzhz.zzc();
            zzhxVarZzc.zzo(i);
            if (!zzijVar.equals(zzij.BROADCAST_ACTION_UNSPECIFIED)) {
                zzhxVarZzc.zza(zzijVar);
            }
            return (zzhz) zzhxVarZzc.zze();
        } catch (Exception e) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to create logging payload", e);
            return null;
        }
    }
}
