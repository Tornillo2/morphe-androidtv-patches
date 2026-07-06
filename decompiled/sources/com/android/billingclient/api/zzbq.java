package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbq extends com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzae {
    public final WeakReference zza;
    public final ResultReceiver zzb;

    public /* synthetic */ zzbq(WeakReference weakReference, ResultReceiver resultReceiver, zzbv zzbvVar) {
        this.zza = weakReference;
        this.zzb = resultReceiver;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzaf
    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            this.zzb.send(6, null);
            return;
        }
        if (!bundle.containsKey("RESPONSE_CODE")) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Response bundle doesn't contain a response code");
            this.zzb.send(6, bundle);
            return;
        }
        int iZzb = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzb(bundle, "BillingClient");
        if (iZzb != 0) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Unable to launch intent for external offer dialog" + iZzb);
            this.zzb.send(iZzb, bundle);
            return;
        }
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("EXTERNAL_PAYMENT_DIALOG_INTENT");
        if (pendingIntent == null) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "The PendingIntent for the external offer dialog is null");
            Bundle bundle2 = new Bundle();
            bundle2.putInt("RESPONSE_CODE", 6);
            bundle2.putString("DEBUG_MESSAGE", "An internal error occurred.");
            this.zzb.send(6, bundle2);
            return;
        }
        try {
            Activity activity = (Activity) this.zza.get();
            Intent intent = new Intent(activity, (Class<?>) ProxyBillingActivityV2.class);
            intent.putExtra("external_payment_dialog_result_receiver", this.zzb);
            intent.putExtra("external_payment_dialog_pending_intent", pendingIntent);
            activity.startActivity(intent);
        } catch (RuntimeException e) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Runtime error while launching intent for the external offer dialog.", e);
            Bundle bundle3 = new Bundle();
            bundle3.putInt("RESPONSE_CODE", 6);
            bundle3.putString("DEBUG_MESSAGE", "An internal error occurred.");
            bundle3.putInt("INTERNAL_LOG_ERROR_REASON", zzic.RUNTIME_EXCEPTION_ON_LAUNCHING_EXTERNAL_PAYMENT_DIALOG_INTENT.zzbJ);
            String name = e.getClass().getName();
            String message = e.getMessage();
            if (message == null) {
                message = "";
            }
            bundle3.putString("INTERNAL_LOG_ERROR_ADDITIONAL_DETAILS", String.format("%s: %s", name, message));
            this.zzb.send(6, bundle3);
        }
    }
}
