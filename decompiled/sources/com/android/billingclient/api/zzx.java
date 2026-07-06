package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzes;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhv;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzij;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public final class zzx extends BroadcastReceiver {
    public final /* synthetic */ zzy zza;
    public boolean zzb;
    public final boolean zzc;

    public zzx(zzy zzyVar, boolean z) {
        Objects.requireNonNull(zzyVar);
        this.zza = zzyVar;
        this.zzc = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003c  */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onReceive(android.content.Context r17, android.content.Intent r18) {
        /*
            Method dump skipped, instruction units count: 535
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.zzx.onReceive(android.content.Context, android.content.Intent):void");
    }

    public final synchronized void zza(Context context, IntentFilter intentFilter) {
        try {
            if (this.zzb) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 33) {
                context.registerReceiver(this, intentFilter, true != this.zzc ? 4 : 2);
            } else {
                context.registerReceiver(this, intentFilter);
            }
            this.zzb = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void zzb(Context context, IntentFilter intentFilter, String str) {
        zzx zzxVar;
        try {
            try {
                if (this.zzb) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= 33) {
                    zzxVar = this;
                    context.registerReceiver(zzxVar, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST", null, true != this.zzc ? 4 : 2);
                } else {
                    zzxVar = this;
                    context.registerReceiver(this, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST", null);
                }
                zzxVar.zzb = true;
                return;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        throw th;
    }

    public final synchronized void zzc(Context context) {
        if (!this.zzb) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingBroadcastManager", "Receiver is not registered.");
        } else {
            context.unregisterReceiver(this);
            this.zzb = false;
        }
    }

    public final void zzd(Bundle bundle, BillingResult billingResult, int i, zzij zzijVar, long j, boolean z) {
        try {
            if (bundle.getByteArray("FAILURE_LOGGING_PAYLOAD") != null) {
                this.zza.zze.zzd(zzhv.zzA(bundle.getByteArray("FAILURE_LOGGING_PAYLOAD"), zzes.zza()), j, z);
            } else {
                this.zza.zze.zzd(zzcm.zzb(zzic.BILLING_RESULT_RECEIVED_FROM_PHONESKY, i, billingResult, null, zzijVar), j, z);
            }
        } catch (Throwable unused) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingBroadcastManager", "Failed parsing Api failure.");
        }
    }
}
