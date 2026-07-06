package com.android.billingclient.api;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UsedByReflection("PlatformActivityProxy")
public class ProxyBillingActivityV2 extends ComponentActivity {
    public ActivityResultLauncher zza;
    public ActivityResultLauncher zzb;
    public ActivityResultLauncher zzc;

    @Nullable
    public ResultReceiver zzd;

    @Nullable
    public ResultReceiver zze;

    @Nullable
    public ResultReceiver zzf;

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.zza = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), this.mActivityResultRegistry, new ActivityResultCallback() { // from class: com.android.billingclient.api.zzcy
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.zza.zza((ActivityResult) obj);
            }
        });
        this.zzb = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), this.mActivityResultRegistry, new ActivityResultCallback() { // from class: com.android.billingclient.api.zzcz
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.zza.zzb((ActivityResult) obj);
            }
        });
        this.zzc = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), this.mActivityResultRegistry, new ActivityResultCallback() { // from class: com.android.billingclient.api.zzda
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.zza.zzc((ActivityResult) obj);
            }
        });
        if (bundle != null) {
            if (bundle.containsKey("alternative_billing_only_dialog_result_receiver")) {
                this.zzd = (ResultReceiver) bundle.getParcelable("alternative_billing_only_dialog_result_receiver");
            }
            if (bundle.containsKey("external_payment_dialog_result_receiver")) {
                this.zze = (ResultReceiver) bundle.getParcelable("external_payment_dialog_result_receiver");
            }
            if (bundle.containsKey("external_offer_flow_result_receiver")) {
                this.zzf = (ResultReceiver) bundle.getParcelable("external_offer_flow_result_receiver");
                return;
            }
            return;
        }
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("ProxyBillingActivityV2", "Launching Play Store billing dialog");
        if (getIntent().hasExtra("ALTERNATIVE_BILLING_ONLY_DIALOG_INTENT")) {
            PendingIntent pendingIntent = (PendingIntent) getIntent().getParcelableExtra("ALTERNATIVE_BILLING_ONLY_DIALOG_INTENT");
            this.zzd = (ResultReceiver) getIntent().getParcelableExtra("alternative_billing_only_dialog_result_receiver");
            this.zza.launch(new IntentSenderRequest.Builder(pendingIntent).build());
        } else if (getIntent().hasExtra("external_payment_dialog_pending_intent")) {
            PendingIntent pendingIntent2 = (PendingIntent) getIntent().getParcelableExtra("external_payment_dialog_pending_intent");
            this.zze = (ResultReceiver) getIntent().getParcelableExtra("external_payment_dialog_result_receiver");
            this.zzb.launch(new IntentSenderRequest.Builder(pendingIntent2).build());
        } else if (getIntent().hasExtra("external_offer_flow_pending_intent")) {
            PendingIntent pendingIntent3 = (PendingIntent) getIntent().getParcelableExtra("external_offer_flow_pending_intent");
            this.zzf = (ResultReceiver) getIntent().getParcelableExtra("external_offer_flow_result_receiver");
            this.zzc.launch(new IntentSenderRequest.Builder(pendingIntent3).build());
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ResultReceiver resultReceiver = this.zzd;
        if (resultReceiver != null) {
            bundle.putParcelable("alternative_billing_only_dialog_result_receiver", resultReceiver);
        }
        ResultReceiver resultReceiver2 = this.zze;
        if (resultReceiver2 != null) {
            bundle.putParcelable("external_payment_dialog_result_receiver", resultReceiver2);
        }
        ResultReceiver resultReceiver3 = this.zzf;
        if (resultReceiver3 != null) {
            bundle.putParcelable("external_offer_flow_result_receiver", resultReceiver3);
        }
    }

    @VisibleForTesting
    public final void zza(ActivityResult activityResult) {
        Intent intent = activityResult.mData;
        int i = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzh(intent, "ProxyBillingActivityV2").zza;
        ResultReceiver resultReceiver = this.zzd;
        if (resultReceiver != null) {
            resultReceiver.send(i, intent == null ? null : intent.getExtras());
        }
        int i2 = activityResult.mResultCode;
        if (i2 != -1 || i != 0) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("ProxyBillingActivityV2", "Alternative billing only dialog finished with resultCode " + i2 + " and billing's responseCode: " + i);
        }
        finish();
    }

    @VisibleForTesting
    public final void zzb(ActivityResult activityResult) {
        Intent intent = activityResult.mData;
        int i = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzh(intent, "ProxyBillingActivityV2").zza;
        ResultReceiver resultReceiver = this.zze;
        if (resultReceiver != null) {
            resultReceiver.send(i, intent == null ? null : intent.getExtras());
        }
        int i2 = activityResult.mResultCode;
        if (i2 != -1 || i != 0) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("ProxyBillingActivityV2", String.format("External offer dialog finished with resultCode: %s and billing's responseCode: %s", Integer.valueOf(i2), Integer.valueOf(i)));
        }
        finish();
    }

    @VisibleForTesting
    public final void zzc(ActivityResult activityResult) {
        Intent intent = activityResult.mData;
        Bundle extras = intent == null ? null : intent.getExtras();
        if (activityResult.mResultCode != -1) {
            if (extras == null) {
                extras = new Bundle();
            }
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("ProxyBillingActivityV2", String.format("External offer flow finished with resultCode: %s", Integer.valueOf(activityResult.mResultCode)));
            extras.putInt("INTERNAL_LOG_ERROR_REASON", zzic.ERROR_IN_ACTIVITY_RESULT.zzbJ);
            extras.putString("INTERNAL_LOG_ERROR_ADDITIONAL_DETAILS", String.format("External offer flow finished with error resultCode: %s", Integer.valueOf(activityResult.mResultCode)));
        }
        int i = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzh(intent, "ProxyBillingActivityV2").zza;
        ResultReceiver resultReceiver = this.zzf;
        if (resultReceiver != null) {
            resultReceiver.send(i, extras);
        } else {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("ProxyBillingActivityV2", "External offer flow result receiver is null");
        }
        if (i != 0) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("ProxyBillingActivityV2", String.format("External offer flow finished with billing responseCode: %s", Integer.valueOf(i)));
        }
        finish();
    }
}
