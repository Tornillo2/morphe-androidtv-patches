package com.android.billingclient.api;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;
import com.android.billingclient.api.BillingClient;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhv;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhz;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzij;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzck extends BillingClientImpl {
    public final Context zza;
    public volatile int zzb;

    @Nullable
    public volatile com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzau zzc;
    public volatile zzci zzd;

    @Nullable
    public volatile ScheduledExecutorService zze;

    @AnyThread
    public zzck(@Nullable String str, Context context, @Nullable zzcn zzcnVar, @Nullable ExecutorService executorService, BillingClient.Builder builder) {
        super(null, context, null, null, builder);
        this.zzb = 0;
        this.zza = context;
    }

    public static final boolean zzaC(int i) {
        return i > 0;
    }

    public static boolean zzas(zzck zzckVar, int i) {
        return i > 0;
    }

    public static /* synthetic */ Object zzav(zzck zzckVar, int i, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzp zzpVar) {
        try {
            if (zzckVar.zzc == null) {
                throw null;
            }
            zzckVar.zzc.zza(zzckVar.zza.getPackageName(), i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 6 ? "QUERY_PRODUCT_DETAILS_ASYNC" : "START_CONNECTION" : "IS_FEATURE_SUPPORTED" : "CONSUME_ASYNC" : "ACKNOWLEDGE_PURCHASE" : "LAUNCH_BILLING_FLOW", new zzch(zzpVar));
            return "billingOverrideService.getBillingOverride";
        } catch (Exception e) {
            zzckVar.zzaF(zzic.BILLING_OVERRIDE_SERVICE_CALL_EXCEPTION, 28, zzcp.zzF);
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClientTesting", "An error occurred while retrieving billing override.", e);
            zzpVar.zzb(0);
            return "billingOverrideService.getBillingOverride";
        }
    }

    @Override // com.android.billingclient.api.BillingClientImpl, com.android.billingclient.api.BillingClient
    public final void acknowledgePurchase(final AcknowledgePurchaseParams acknowledgePurchaseParams, final AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        Objects.requireNonNull(acknowledgePurchaseResponseListener);
        zzaH(3, new Consumer() { // from class: com.android.billingclient.api.zzcc
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse((BillingResult) obj);
            }
        }, new Runnable() { // from class: com.android.billingclient.api.zzcd
            @Override // java.lang.Runnable
            public final void run() {
                super/*com.android.billingclient.api.BillingClientImpl*/.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
            }
        });
    }

    @Override // com.android.billingclient.api.BillingClientImpl, com.android.billingclient.api.BillingClient
    public final void consumeAsync(final ConsumeParams consumeParams, final ConsumeResponseListener consumeResponseListener) {
        zzaH(4, new Consumer() { // from class: com.android.billingclient.api.zzbz
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                consumeResponseListener.onConsumeResponse((BillingResult) obj, consumeParams.zza);
            }
        }, new Runnable() { // from class: com.android.billingclient.api.zzca
            @Override // java.lang.Runnable
            public final void run() {
                super/*com.android.billingclient.api.BillingClientImpl*/.consumeAsync(consumeParams, consumeResponseListener);
            }
        });
    }

    @Override // com.android.billingclient.api.BillingClientImpl, com.android.billingclient.api.BillingClient
    public final void endConnection() {
        zzaA();
        super.endConnection();
    }

    @Override // com.android.billingclient.api.BillingClientImpl, com.android.billingclient.api.BillingClient
    public final BillingResult launchBillingFlow(final Activity activity, final BillingFlowParams billingFlowParams) {
        Consumer consumer = new Consumer() { // from class: com.android.billingclient.api.zzce
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                this.zza.zzm((BillingResult) obj);
            }
        };
        Callable callable = new Callable() { // from class: com.android.billingclient.api.zzcf
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return super/*com.android.billingclient.api.BillingClientImpl*/.launchBillingFlow(activity, billingFlowParams);
            }
        };
        int iZzay = zzay(zzaE(2));
        if (iZzay > 0) {
            BillingResult billingResultZzaD = zzaD(2, iZzay);
            consumer.accept(billingResultZzaD);
            return billingResultZzaD;
        }
        try {
            return (BillingResult) callable.call();
        } catch (Exception e) {
            zzic zzicVar = zzic.BILLING_OVERRIDE_SERVICE_FALLBACK_ERROR;
            BillingResult billingResult = zzcp.zzh;
            zzaF(zzicVar, 2, billingResult);
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClientTesting", "An internal error occurred.", e);
            return billingResult;
        }
    }

    @Override // com.android.billingclient.api.BillingClientImpl, com.android.billingclient.api.BillingClient
    public final void queryProductDetailsAsync(final QueryProductDetailsParams queryProductDetailsParams, final ProductDetailsResponseListener productDetailsResponseListener) {
        zzaH(7, new Consumer() { // from class: com.android.billingclient.api.zzbx
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                QueryProductDetailsResult queryProductDetailsResult = new QueryProductDetailsResult(new ArrayList(), new ArrayList());
                productDetailsResponseListener.onProductDetailsResponse((BillingResult) obj, queryProductDetailsResult);
            }
        }, new Runnable() { // from class: com.android.billingclient.api.zzby
            @Override // java.lang.Runnable
            public final void run() {
                super/*com.android.billingclient.api.BillingClientImpl*/.queryProductDetailsAsync(queryProductDetailsParams, productDetailsResponseListener);
            }
        });
    }

    @Override // com.android.billingclient.api.BillingClientImpl, com.android.billingclient.api.BillingClient
    public final void startConnection(BillingClientStateListener billingClientStateListener) {
        zzaB();
        zzaU(billingClientStateListener, 0);
    }

    public final synchronized void zzaA() {
        zzaG(27);
        try {
            try {
                if (this.zzd != null && this.zzc != null) {
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClientTesting", "Unbinding from Billing Override Service.");
                    this.zza.unbindService(this.zzd);
                    this.zzd = new zzci(this, null);
                }
                this.zzc = null;
                if (this.zze != null) {
                    this.zze.shutdownNow();
                    this.zze = null;
                }
            } catch (RuntimeException e) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClientTesting", "There was an exception while ending Billing Override Service connection!", e);
            }
            this.zzb = 3;
        } catch (Throwable th) {
            this.zzb = 3;
            throw th;
        }
    }

    public final synchronized void zzaB() {
        if (zzat()) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClientTesting", "Billing Override Service connection is valid. No need to re-initialize.");
            zzaG(26);
            return;
        }
        if (this.zzb == 1) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClientTesting", "Client is already in the process of connecting to Billing Override Service.");
            return;
        }
        if (this.zzb == 3) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClientTesting", "Billing Override Service Client was already closed and can't be reused. Please create another instance.");
            zzaF(zzic.BILLING_CLIENT_CLOSED, 26, zzcp.zza(-1, "Billing Override Service connection is disconnected."));
            return;
        }
        this.zzb = 1;
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClientTesting", "Starting Billing Override Service setup.");
        this.zzd = new zzci(this, null);
        Intent intent = new Intent("com.google.android.apps.play.billingtestcompanion.BillingOverrideService.BIND");
        intent.setPackage("com.google.android.apps.play.billingtestcompanion");
        Context context = this.zza;
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        zzic zzicVar = zzic.REASON_UNSPECIFIED;
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            zzicVar = zzic.INTENT_SERVICE_NOT_FOUND;
        } else {
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            if (serviceInfo != null) {
                String str = serviceInfo.packageName;
                String str2 = serviceInfo.name;
                if (!Objects.equals(str, "com.google.android.apps.play.billingtestcompanion") || str2 == null) {
                    zzicVar = zzic.BILLING_SERVICE_BLOCKED;
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClientTesting", "The device doesn't have valid Play Billing Lab.");
                } else {
                    ComponentName componentName = new ComponentName(str, str2);
                    Intent intent2 = new Intent(intent);
                    intent2.setComponent(componentName);
                    if (context.bindService(intent2, this.zzd, 1)) {
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClientTesting", "Billing Override Service was bonded successfully.");
                        return;
                    } else {
                        zzicVar = zzic.BILLING_SERVICE_BLOCKED;
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClientTesting", "Connection to Billing Override Service is blocked.");
                    }
                }
            }
        }
        this.zzb = 0;
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClientTesting", "Billing Override Service unavailable on device.");
        zzaF(zzicVar, 26, zzcp.zza(2, "Billing Override Service unavailable on device."));
    }

    public final BillingResult zzaD(int i, int i2) {
        BillingResult billingResultZza = zzcp.zza(i2, "Billing override value was set by a license tester.");
        zzaF(zzic.LICENSE_TESTER_BILLING_OVERRIDE, i, billingResultZza);
        return billingResultZza;
    }

    public final com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcx zzaE(final int i) {
        if (zzat()) {
            return com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzu.zza(new com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzr() { // from class: com.android.billingclient.api.zzcb
                @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzr
                public final Object zza(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzp zzpVar) {
                    zzck.zzav(this.zza, i, zzpVar);
                    return "billingOverrideService.getBillingOverride";
                }
            });
        }
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClientTesting", "Billing Override Service is not ready.");
        zzaF(zzic.BILLING_OVERRIDE_SERVICE_CONNECTION_NOT_READY, 28, zzcp.zza(-1, "Billing Override Service connection is disconnected."));
        return new com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcv(0);
    }

    public final void zzaF(zzic zzicVar, int i, BillingResult billingResult) {
        int i2 = zzcm.zza;
        zzhv zzhvVarZzb = zzcm.zzb(zzicVar, i, billingResult, null, zzij.BROADCAST_ACTION_UNSPECIFIED);
        Objects.requireNonNull(zzhvVarZzb, "ApiFailure should not be null");
        this.zzh.zza(zzhvVarZzb);
    }

    public final void zzaG(int i) {
        int i2 = zzcm.zza;
        zzhz zzhzVarZzc = zzcm.zzc(i, zzij.BROADCAST_ACTION_UNSPECIFIED);
        Objects.requireNonNull(zzhzVarZzc, "ApiSuccess should not be null");
        this.zzh.zzf(zzhzVarZzc);
    }

    public final void zzaH(int i, Consumer consumer, Runnable runnable) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcs.zzc(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcs.zzb(zzaE(i), 28500L, TimeUnit.MILLISECONDS, zzaz()), new zzcg(this, i, consumer, runnable), zzF());
    }

    public final synchronized boolean zzat() {
        if (this.zzb == 2 && this.zzc != null) {
            if (this.zzd != null) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int zzay(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcx zzcxVar) {
        try {
            return ((Integer) zzcxVar.get(28500L, TimeUnit.MILLISECONDS)).intValue();
        } catch (TimeoutException e) {
            zzaF(zzic.BILLING_OVERRIDE_SERVICE_CALL_TIMEOUT, 28, zzcp.zzF);
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClientTesting", "Asynchronous call to Billing Override Service timed out.", e);
            return 0;
        } catch (Exception e2) {
            if (e2 instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            zzaF(zzic.BILLING_OVERRIDE_SERVICE_CALL_EXCEPTION, 28, zzcp.zzF);
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClientTesting", "An error occurred while retrieving billing override.", e2);
            return 0;
        }
    }

    public final synchronized ScheduledExecutorService zzaz() {
        try {
            if (this.zze == null) {
                this.zze = Executors.newSingleThreadScheduledExecutor();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.zze;
    }

    @AnyThread
    public zzck(@Nullable String str, PendingPurchasesParams pendingPurchasesParams, Context context, zzcu zzcuVar, @Nullable zzcn zzcnVar, @Nullable ExecutorService executorService, BillingClient.Builder builder) {
        super(null, pendingPurchasesParams, context, null, null, null, builder);
        this.zzb = 0;
        this.zza = context;
    }

    @AnyThread
    public zzck(@Nullable String str, PendingPurchasesParams pendingPurchasesParams, Context context, PurchasesUpdatedListener purchasesUpdatedListener, @Nullable zzb zzbVar, @Nullable zzcn zzcnVar, @Nullable ExecutorService executorService, BillingClient.Builder builder) {
        super((String) null, pendingPurchasesParams, context, purchasesUpdatedListener, (zzb) null, (zzcn) null, (ExecutorService) null, builder);
        this.zzb = 0;
        this.zza = context;
    }

    @AnyThread
    public zzck(@Nullable String str, PendingPurchasesParams pendingPurchasesParams, Context context, PurchasesUpdatedListener purchasesUpdatedListener, @Nullable UserChoiceBillingListener userChoiceBillingListener, @Nullable zzcn zzcnVar, @Nullable ExecutorService executorService, BillingClient.Builder builder) {
        super((String) null, pendingPurchasesParams, context, purchasesUpdatedListener, userChoiceBillingListener, (zzcn) null, (ExecutorService) null, builder);
        this.zzb = 0;
        this.zza = context;
    }
}
