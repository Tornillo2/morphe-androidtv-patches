package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import java.lang.ref.WeakReference;
import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbt extends com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzan {
    public final WeakReference zza;
    public final ResultReceiver zzb;

    public /* synthetic */ zzbt(WeakReference weakReference, ResultReceiver resultReceiver, zzbv zzbvVar) {
        this.zza = weakReference;
        this.zzb = resultReceiver;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzao
    public final void zza(Bundle bundle) throws RemoteException {
        ResultReceiver resultReceiver = this.zzb;
        if (resultReceiver == null) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Unable to send result for in-app messaging");
            return;
        }
        if (bundle == null) {
            resultReceiver.send(0, null);
            return;
        }
        Activity activity = (Activity) this.zza.get();
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("KEY_LAUNCH_INTENT");
        if (activity == null || pendingIntent == null) {
            resultReceiver.send(0, null);
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Unable to launch intent for in-app messaging");
            return;
        }
        try {
            Intent intent = new Intent(activity, (Class<?>) ProxyBillingActivity.class);
            intent.putExtra(ProxyBillingActivity.KEY_IN_APP_MESSAGE_RESULT_RECEIVER, resultReceiver);
            intent.putExtra("IN_APP_MESSAGE_INTENT", pendingIntent);
            activity.startActivity(intent);
        } catch (CancellationException e) {
            this.zzb.send(0, null);
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Exception caught while launching intent for in-app messaging.", e);
        }
    }
}
