package com.android.billingclient.api;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.billingclient.BuildConfig;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzht;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhv;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhx;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhz;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzia;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzic;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzie;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzij;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzio;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zziq;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zziu;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzix;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjr;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzjt;
import j$.util.Objects;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class BillingClientImpl extends BillingClient {
    public boolean zzA;
    public boolean zzB;
    public boolean zzC;

    @Nullable
    public PendingPurchasesParams zzD;
    public boolean zzE;
    public boolean zzF;

    @Nullable
    public volatile BillingClientStateListener zzG;
    public ExecutorService zzH;
    public final Long zzI;
    public com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbj zzJ;
    public final Object zza;
    public volatile int zzb;
    public final String zzc;

    @Nullable
    public final String zzd;
    public final Handler zze;

    @Nullable
    public volatile zzy zzf;
    public Context zzg;
    public zzcn zzh;
    public volatile com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzi;
    public volatile zzbl zzj;
    public boolean zzk;
    public boolean zzl;
    public int zzm;
    public boolean zzn;
    public boolean zzo;
    public boolean zzp;
    public boolean zzq;
    public boolean zzr;
    public boolean zzs;
    public boolean zzt;
    public boolean zzu;
    public boolean zzv;
    public boolean zzw;
    public boolean zzx;
    public boolean zzy;
    public boolean zzz;

    @AnyThread
    public BillingClientImpl(Context context, PendingPurchasesParams pendingPurchasesParams, PurchasesUpdatedListener purchasesUpdatedListener, String str, String str2, @Nullable UserChoiceBillingListener userChoiceBillingListener, @Nullable zzcn zzcnVar, @Nullable ExecutorService executorService, BillingClient.Builder builder) {
        this.zza = new Object();
        this.zzb = 0;
        this.zze = new Handler(Looper.getMainLooper());
        this.zzm = 0;
        this.zzI = Long.valueOf(new Random().nextLong());
        this.zzJ = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzaz.zza();
        this.zzc = str;
        this.zzd = zzaw();
        initialize(context, purchasesUpdatedListener, pendingPurchasesParams, userChoiceBillingListener, str, (zzcn) null, builder);
    }

    public static /* synthetic */ Void zzA(BillingClientImpl billingClientImpl, ExternalOfferAvailabilityListener externalOfferAvailabilityListener) throws Exception {
        billingClientImpl.zzaA(externalOfferAvailabilityListener);
        return null;
    }

    public static /* synthetic */ Void zzB(BillingClientImpl billingClientImpl, ExternalOfferInformationDialogListener externalOfferInformationDialogListener, Activity activity, ResultReceiver resultReceiver) throws Exception {
        billingClientImpl.zzaC(externalOfferInformationDialogListener, activity, resultReceiver);
        return null;
    }

    public static /* synthetic */ Void zzC(BillingClientImpl billingClientImpl, ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener, String str) throws Exception {
        billingClientImpl.zzay(externalOfferReportingDetailsListener, null);
        return null;
    }

    public static /* synthetic */ Void zzD(BillingClientImpl billingClientImpl, AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener) throws Exception {
        billingClientImpl.zzax(alternativeBillingOnlyReportingDetailsListener);
        return null;
    }

    public static /* synthetic */ Void zzE(BillingClientImpl billingClientImpl, AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener) throws Exception {
        billingClientImpl.zzaz(alternativeBillingOnlyAvailabilityListener);
        return null;
    }

    @Nullable
    public static Future zzG(Callable callable, long j, @Nullable final Runnable runnable, Handler handler, ExecutorService executorService) {
        try {
            final Future futureSubmit = executorService.submit(callable);
            handler.postDelayed(new Runnable() { // from class: com.android.billingclient.api.zzap
                @Override // java.lang.Runnable
                public final void run() {
                    Future future = futureSubmit;
                    if (future.isDone() || future.isCancelled()) {
                        return;
                    }
                    Runnable runnable2 = runnable;
                    future.cancel(true);
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Async task is taking too long, cancel it!");
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            }, (long) (j * 0.95d));
            return futureSubmit;
        } catch (Exception e) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Async task throws exception!", e);
            return null;
        }
    }

    public static void zzH(BillingClientImpl billingClientImpl, ConsumeResponseListener consumeResponseListener, ConsumeParams consumeParams) {
        zzic zzicVar = zzic.EXECUTE_ASYNC_TIMEOUT;
        BillingResult billingResult = zzcp.zzk;
        billingClientImpl.zzbd(zzicVar, 4, billingResult);
        consumeResponseListener.onConsumeResponse(billingResult, consumeParams.zza);
    }

    public static /* synthetic */ void zzI(BillingClientImpl billingClientImpl, PurchasesResponseListener purchasesResponseListener) {
        zzic zzicVar = zzic.EXECUTE_ASYNC_TIMEOUT;
        BillingResult billingResult = zzcp.zzk;
        billingClientImpl.zzbd(zzicVar, 9, billingResult);
        purchasesResponseListener.onQueryPurchasesResponse(billingResult, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzk());
    }

    public static /* synthetic */ void zzJ(BillingClientImpl billingClientImpl, BillingConfigResponseListener billingConfigResponseListener) {
        zzic zzicVar = zzic.EXECUTE_ASYNC_TIMEOUT;
        BillingResult billingResult = zzcp.zzk;
        billingClientImpl.zzbd(zzicVar, 13, billingResult);
        billingConfigResponseListener.onBillingConfigResponse(billingResult, null);
    }

    public static /* synthetic */ void zzL(BillingClientImpl billingClientImpl, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        zzic zzicVar = zzic.EXECUTE_ASYNC_TIMEOUT;
        BillingResult billingResult = zzcp.zzk;
        billingClientImpl.zzbd(zzicVar, 3, billingResult);
        acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult);
    }

    public static void zzN(BillingClientImpl billingClientImpl, ProductDetailsResponseListener productDetailsResponseListener) {
        zzic zzicVar = zzic.EXECUTE_ASYNC_TIMEOUT;
        BillingResult billingResult = zzcp.zzk;
        billingClientImpl.zzbd(zzicVar, 7, billingResult);
        productDetailsResponseListener.onProductDetailsResponse(billingResult, new QueryProductDetailsResult(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzk(), com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzby.zza));
    }

    public static void zzP(BillingClientImpl billingClientImpl, BillingResult billingResult) {
        if (billingClientImpl.zzf.zzb != null) {
            billingClientImpl.zzf.zzb.onPurchasesUpdated(billingResult, null);
        } else {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "No valid listener is set in BroadcastManager");
        }
    }

    public static final String zzaZ(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "CLOSED" : "CONNECTED" : "CONNECTING" : "DISCONNECTED";
    }

    public static /* bridge */ /* synthetic */ void zzac(BillingClientImpl billingClientImpl, int i) {
        billingClientImpl.zzm = i;
        billingClientImpl.zzC = i >= 26;
        billingClientImpl.zzB = i >= 24;
        billingClientImpl.zzA = i >= 23;
        billingClientImpl.zzz = i >= 22;
        billingClientImpl.zzy = i >= 21;
        billingClientImpl.zzx = i >= 20;
        billingClientImpl.zzw = i >= 19;
        billingClientImpl.zzv = i >= 18;
        billingClientImpl.zzu = i >= 17;
        billingClientImpl.zzt = i >= 16;
        billingClientImpl.zzs = i >= 15;
        billingClientImpl.zzr = i >= 14;
        billingClientImpl.zzq = i >= 12;
        billingClientImpl.zzp = i >= 9;
        billingClientImpl.zzo = i >= 8;
        billingClientImpl.zzn = i >= 6;
    }

    public static /* bridge */ /* synthetic */ void zzae(BillingClientImpl billingClientImpl, int i) {
        if (i != 0) {
            billingClientImpl.zzaS(0);
            return;
        }
        synchronized (billingClientImpl.zza) {
            try {
                if (billingClientImpl.zzb == 3) {
                    return;
                }
                billingClientImpl.zzaS(2);
                zzy zzyVar = billingClientImpl.zzf != null ? billingClientImpl.zzf : null;
                if (zzyVar != null) {
                    zzyVar.zzg(billingClientImpl.zzy);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static /* bridge */ /* synthetic */ boolean zzai(BillingClientImpl billingClientImpl) {
        boolean z;
        synchronized (billingClientImpl.zza) {
            z = true;
            if (billingClientImpl.zzb != 1) {
                z = false;
            }
        }
        return z;
    }

    @Nullable
    @SuppressLint({"PrivateApi"})
    public static String zzaw() {
        try {
            return (String) Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    public static /* bridge */ /* synthetic */ BillingResult zzl(Exception exc) {
        return exc instanceof DeadObjectException ? zzcp.zzj : zzcp.zzh;
    }

    public static /* synthetic */ Object zzq(BillingClientImpl billingClientImpl, int i, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzp zzpVar) {
        billingClientImpl.zzaU(new zzbi(billingClientImpl, zzpVar), i);
        return "reconnectIfNeeded";
    }

    public static Object zzr(BillingClientImpl billingClientImpl, ConsumeResponseListener consumeResponseListener, ConsumeParams consumeParams) throws Throwable {
        if (billingClientImpl.zzaX(30000L)) {
            billingClientImpl.zzaD(consumeParams, consumeResponseListener);
            return null;
        }
        zzic zzicVar = zzic.SERVICE_CONNECTION_NOT_READY;
        BillingResult billingResult = zzcp.zzj;
        billingClientImpl.zzbd(zzicVar, 4, billingResult);
        consumeResponseListener.onConsumeResponse(billingResult, consumeParams.zza);
        return null;
    }

    public static Object zzs(BillingClientImpl billingClientImpl, ProductDetailsResponseListener productDetailsResponseListener, QueryProductDetailsParams queryProductDetailsParams) throws JSONException {
        if (!billingClientImpl.zzaX(30000L)) {
            zzic zzicVar = zzic.SERVICE_CONNECTION_NOT_READY;
            BillingResult billingResult = zzcp.zzj;
            billingClientImpl.zzbd(zzicVar, 7, billingResult);
            productDetailsResponseListener.onProductDetailsResponse(billingResult, new QueryProductDetailsResult(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzk(), com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzby.zza));
            return null;
        }
        if (billingClientImpl.zzu) {
            zzbu zzbuVarZzg = billingClientImpl.zzg(queryProductDetailsParams);
            productDetailsResponseListener.onProductDetailsResponse(zzcp.zza(zzbuVarZzg.zzc, zzbuVarZzg.zzd), new QueryProductDetailsResult(zzbuVarZzg.zza, zzbuVarZzg.zzb));
            return null;
        }
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Querying product details is not supported.");
        zzic zzicVar2 = zzic.PRODUCT_DETAILS_NOT_SUPPORTED;
        BillingResult billingResult2 = zzcp.zzr;
        billingClientImpl.zzbd(zzicVar2, 7, billingResult2);
        productDetailsResponseListener.onProductDetailsResponse(billingResult2, new QueryProductDetailsResult(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzk(), com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzby.zza));
        return null;
    }

    public static /* synthetic */ Object zzt(BillingClientImpl billingClientImpl, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener, AcknowledgePurchaseParams acknowledgePurchaseParams) throws Exception {
        billingClientImpl.zzas(acknowledgePurchaseResponseListener, acknowledgePurchaseParams);
        return null;
    }

    public static /* synthetic */ Object zzu(BillingClientImpl billingClientImpl, Bundle bundle, Activity activity, ResultReceiver resultReceiver) throws Exception {
        billingClientImpl.zzau(bundle, activity, resultReceiver);
        return null;
    }

    public static /* synthetic */ Object zzv(BillingClientImpl billingClientImpl, BillingConfigResponseListener billingConfigResponseListener) throws Exception {
        billingClientImpl.zzat(billingConfigResponseListener);
        return null;
    }

    public static /* synthetic */ Void zzz(BillingClientImpl billingClientImpl, AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener, Activity activity, ResultReceiver resultReceiver) throws Exception {
        billingClientImpl.zzaB(alternativeBillingOnlyInformationDialogListener, activity, resultReceiver);
        return null;
    }

    @Override // com.android.billingclient.api.BillingClient
    public void acknowledgePurchase(final AcknowledgePurchaseParams acknowledgePurchaseParams, final AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        if (zzG(new Callable() { // from class: com.android.billingclient.api.zzag
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzas(acknowledgePurchaseResponseListener, acknowledgePurchaseParams);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzah
            @Override // java.lang.Runnable
            public final void run() {
                BillingClientImpl.zzL(this.zza, acknowledgePurchaseResponseListener);
            }
        }, zzan(), zzF()) == null) {
            BillingResult billingResultZzaq = zzaq();
            zzbd(zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, 3, billingResultZzaq);
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResultZzaq);
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public void consumeAsync(final ConsumeParams consumeParams, final ConsumeResponseListener consumeResponseListener) {
        if (zzG(new Callable() { // from class: com.android.billingclient.api.zzaq
            @Override // java.util.concurrent.Callable
            public final Object call() throws Throwable {
                BillingClientImpl.zzr(this.zza, consumeResponseListener, consumeParams);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzas
            @Override // java.lang.Runnable
            public final void run() {
                BillingClientImpl.zzH(this.zza, consumeResponseListener, consumeParams);
            }
        }, zzan(), zzF()) == null) {
            BillingResult billingResultZzaq = zzaq();
            zzbd(zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, 4, billingResultZzaq);
            consumeResponseListener.onConsumeResponse(billingResultZzaq, consumeParams.zza);
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    @zzf
    public void createAlternativeBillingOnlyReportingDetailsAsync(final AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener) {
        if (zzG(new Callable() { // from class: com.android.billingclient.api.zzal
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzax(alternativeBillingOnlyReportingDetailsListener);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzam
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzaH(alternativeBillingOnlyReportingDetailsListener, zzcp.zzk, zzic.EXECUTE_ASYNC_TIMEOUT, null);
            }
        }, zzan(), zzF()) == null) {
            zzaH(alternativeBillingOnlyReportingDetailsListener, zzaq(), zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, null);
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    @zzj
    public void createExternalOfferReportingDetailsAsync(final ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener) {
        final String str = null;
        if (zzG(new Callable(externalOfferReportingDetailsListener, str) { // from class: com.android.billingclient.api.zzaw
            public final /* synthetic */ ExternalOfferReportingDetailsListener zzb;

            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzay(this.zzb, null);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzax
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzaI(externalOfferReportingDetailsListener, zzcp.zzk, zzic.EXECUTE_ASYNC_TIMEOUT, null);
            }
        }, zzan(), zzF()) == null) {
            zzaI(externalOfferReportingDetailsListener, zzaq(), zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, null);
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public void endConnection() {
        zzbi(12);
        synchronized (this.zza) {
            try {
            } finally {
            }
            if (this.zzf != null) {
                this.zzf.zzf();
                try {
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Unbinding from service.");
                    zzaV();
                } catch (Throwable th) {
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "There was an exception while unbinding from the service while ending connection!", th);
                }
                try {
                    zzaT();
                } finally {
                    try {
                    } finally {
                    }
                }
            } else {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Unbinding from service.");
                zzaV();
                zzaT();
            }
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    @zzk
    public void getBillingConfigAsync(GetBillingConfigParams getBillingConfigParams, final BillingConfigResponseListener billingConfigResponseListener) {
        if (zzG(new Callable() { // from class: com.android.billingclient.api.zzaj
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzat(billingConfigResponseListener);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzak
            @Override // java.lang.Runnable
            public final void run() {
                BillingClientImpl.zzJ(this.zza, billingConfigResponseListener);
            }
        }, zzan(), zzF()) == null) {
            BillingResult billingResultZzaq = zzaq();
            zzbd(zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, 13, billingResultZzaq);
            billingConfigResponseListener.onBillingConfigResponse(billingResultZzaq, null);
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public final int getConnectionState() {
        int i;
        synchronized (this.zza) {
            i = this.zzb;
        }
        return i;
    }

    public final void initialize(Context context, PurchasesUpdatedListener purchasesUpdatedListener, PendingPurchasesParams pendingPurchasesParams, @Nullable zzb zzbVar, String str, @Nullable zzcn zzcnVar, BillingClient.Builder builder) {
        this.zzg = context.getApplicationContext();
        zzio zzioVarZzc = zziq.zzc();
        zzioVarZzc.zzs(str);
        String str2 = this.zzd;
        if (str2 != null) {
            zzioVarZzc.zzt(str2);
        }
        zzioVarZzc.zzq(this.zzg.getPackageName());
        zzioVarZzc.zzn(this.zzI.longValue());
        zzioVarZzc.zzr(builder.zza);
        zzioVarZzc.zza(Build.VERSION.SDK_INT);
        zzioVarZzc.zzp(781211065L);
        try {
            zzioVarZzc.zzl(this.zzg.getPackageManager().getPackageInfo(this.zzg.getPackageName(), 0).versionCode);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Error getting app version code.", th);
        }
        if (zzcnVar != null) {
            this.zzh = zzcnVar;
        } else {
            this.zzh = new zzcr(this.zzg, (zziq) zzioVarZzc.zze());
        }
        if (purchasesUpdatedListener == null) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        this.zzf = new zzy(this.zzg, purchasesUpdatedListener, null, zzbVar, null, this.zzh);
        this.zzD = pendingPurchasesParams;
        this.zzF = zzbVar != null;
        this.zzg.getPackageName();
        this.zzE = builder.zza;
    }

    @Override // com.android.billingclient.api.BillingClient
    @zzf
    public void isAlternativeBillingOnlyAvailableAsync(final AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener) {
        if (zzG(new Callable() { // from class: com.android.billingclient.api.zzan
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzaz(alternativeBillingOnlyAvailabilityListener);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzao
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzaF(alternativeBillingOnlyAvailabilityListener, zzcp.zzk, zzic.EXECUTE_ASYNC_TIMEOUT, null);
            }
        }, zzan(), zzF()) == null) {
            zzaF(alternativeBillingOnlyAvailabilityListener, zzaq(), zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, null);
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    @zzj
    public void isExternalOfferAvailableAsync(final ExternalOfferAvailabilityListener externalOfferAvailabilityListener) {
        if (zzG(new Callable() { // from class: com.android.billingclient.api.zzba
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzaA(externalOfferAvailabilityListener);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzab
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzaJ(externalOfferAvailabilityListener, zzcp.zzk, zzic.EXECUTE_ASYNC_TIMEOUT, null);
            }
        }, zzan(), zzF()) == null) {
            zzaJ(externalOfferAvailabilityListener, zzaq(), zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, null);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.android.billingclient.api.BillingClient
    public final BillingResult isFeatureSupported(String str) {
        if (!zzaW(3000L)) {
            BillingResult billingResult = zzcp.zzj;
            zzic zzicVar = zzic.SERVICE_CONNECTION_NOT_READY;
            if (billingResult.zza != 0) {
                zzbd(zzicVar, 5, billingResult);
                return billingResult;
            }
            zzbi(5);
            return billingResult;
        }
        int i = zzcp.zzG;
        switch (str.hashCode()) {
            case -422092961:
                if (str.equals(BillingClient.FeatureType.SUBSCRIPTIONS_UPDATE)) {
                    BillingResult billingResult2 = this.zzl ? zzcp.zzi : zzcp.zzm;
                    zzbc(billingResult2, zzic.SUBSCRIPTIONS_UPDATE_NOT_SUPPORTED, 3);
                    return billingResult2;
                }
                break;
            case 96321:
                if (str.equals(BillingClient.FeatureType.CROSS_SELL)) {
                    BillingResult billingResult3 = this.zzs ? zzcp.zzi : zzcp.zzo;
                    zzbc(billingResult3, zzic.CROSS_APP_NOT_SUPPORTED, 6);
                    return billingResult3;
                }
                break;
            case 97314:
                if (str.equals(BillingClient.FeatureType.IN_APP_MESSAGING)) {
                    BillingResult billingResult4 = this.zzq ? zzcp.zzi : zzcp.zzs;
                    zzbc(billingResult4, zzic.IN_APP_MESSAGE_NOT_SUPPORTED, 5);
                    return billingResult4;
                }
                break;
            case 98307:
                if (str.equals("ccc")) {
                    BillingResult billingResult5 = this.zzt ? zzcp.zzi : zzcp.zzp;
                    zzbc(billingResult5, zzic.MULTI_ITEM_NOT_SUPPORTED, 8);
                    return billingResult5;
                }
                break;
            case 99300:
                if (str.equals(BillingClient.FeatureType.PER_TRANSACTION_OFFER)) {
                    BillingResult billingResult6 = this.zzr ? zzcp.zzi : zzcp.zzq;
                    zzbc(billingResult6, zzic.OFFER_ID_TOKEN_NOT_SUPPORTED, 7);
                    return billingResult6;
                }
                break;
            case 100293:
                if (str.equals("eee")) {
                    BillingResult billingResult7 = this.zzt ? zzcp.zzi : zzcp.zzp;
                    zzbc(billingResult7, zzic.PBL_FOR_PAYMENTS_GATEWAY_BUYFLOW_NOT_SUPPORTED, 9);
                    return billingResult7;
                }
                break;
            case 101286:
                if (str.equals(BillingClient.FeatureType.PRODUCT_DETAILS)) {
                    BillingResult billingResult8 = this.zzu ? zzcp.zzi : zzcp.zzr;
                    zzbc(billingResult8, zzic.PRODUCT_DETAILS_NOT_SUPPORTED, 10);
                    return billingResult8;
                }
                break;
            case 102279:
                if (str.equals(BillingClient.FeatureType.BILLING_CONFIG)) {
                    BillingResult billingResult9 = this.zzv ? zzcp.zzi : zzcp.zzy;
                    zzbc(billingResult9, zzic.GET_BILLING_CONFIG_NOT_SUPPORTED, 11);
                    return billingResult9;
                }
                break;
            case 103272:
                if (str.equals("hhh")) {
                    BillingResult billingResult10 = this.zzv ? zzcp.zzi : zzcp.zzz;
                    zzbc(billingResult10, zzic.QUERY_PRODUCT_DETAILS_WITH_SERIALIZED_DOCID_NOT_SUPPORTED, 12);
                    return billingResult10;
                }
                break;
            case 104265:
                if (str.equals("iii")) {
                    BillingResult billingResult11 = this.zzx ? zzcp.zzi : zzcp.zzC;
                    zzbc(billingResult11, zzic.QUERY_PRODUCT_DETAILS_WITH_DEVELOPER_SPECIFIED_ACCOUNT_NOT_SUPPORTED, 13);
                    return billingResult11;
                }
                break;
            case 105258:
                if (str.equals(BillingClient.FeatureType.ALTERNATIVE_BILLING_ONLY)) {
                    BillingResult billingResult12 = this.zzy ? zzcp.zzi : zzcp.zzD;
                    zzbc(billingResult12, zzic.ALTERNATIVE_BILLING_ONLY_NOT_SUPPORTED, 14);
                    return billingResult12;
                }
                break;
            case 106251:
                if (str.equals(BillingClient.FeatureType.EXTERNAL_OFFER)) {
                    BillingResult billingResult13 = this.zzB ? zzcp.zzi : zzcp.zzA;
                    zzbc(billingResult13, zzic.LAUNCH_EXTERNAL_OFFER_FLOW_NOT_SUPPORTED, 18);
                    return billingResult13;
                }
                break;
            case 107244:
                if (str.equals("lll")) {
                    BillingResult billingResult14 = this.zzA ? zzcp.zzi : zzcp.zzu;
                    zzbc(billingResult14, zzic.MULTI_ITEM_WITH_SEASON_PASS_NOT_SUPPORTED, 19);
                    return billingResult14;
                }
                break;
            case 108237:
                if (str.equals(BillingClient.FeatureType.AUTO_PAY)) {
                    BillingResult billingResult15 = this.zzB ? zzcp.zzi : zzcp.zzv;
                    zzbc(billingResult15, zzic.AUTO_PAY_NOT_SUPPORTED, 20);
                    return billingResult15;
                }
                break;
            case 109230:
                if (str.equals("nnn")) {
                    BillingResult billingResult16 = this.zzC ? zzcp.zzi : zzcp.zzw;
                    zzbc(billingResult16, zzic.INCLUDE_SUSPENDED_SUBSCRIPTIONS_NOT_SUPPORTED, 21);
                    return billingResult16;
                }
                break;
            case 207616302:
                if (str.equals(BillingClient.FeatureType.PRICE_CHANGE_CONFIRMATION)) {
                    BillingResult billingResult17 = this.zzo ? zzcp.zzi : zzcp.zzn;
                    zzbc(billingResult17, zzic.PRICE_CHANGE_CONFIRMATION_NOT_SUPPORTED, 4);
                    return billingResult17;
                }
                break;
            case 1987365622:
                if (str.equals(BillingClient.FeatureType.SUBSCRIPTIONS)) {
                    BillingResult billingResult18 = this.zzk ? zzcp.zzi : zzcp.zzl;
                    zzbc(billingResult18, zzic.SUBSCRIPTIONS_NOT_SUPPORTED, 2);
                    return billingResult18;
                }
                break;
        }
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Unsupported feature: ".concat(str));
        BillingResult billingResult19 = zzcp.zzx;
        zzbc(billingResult19, zzic.UNKNOWN_FEATURE, 1);
        return billingResult19;
    }

    @Override // com.android.billingclient.api.BillingClient
    public final boolean isReady() {
        if (this.zzE) {
            return true;
        }
        return zzaY();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0491  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x049a  */
    /* JADX WARN: Type inference failed for: r32v0, types: [com.android.billingclient.api.BillingClientImpl] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v32 */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r5v36 */
    /* JADX WARN: Type inference failed for: r5v37 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r6v0, types: [long] */
    /* JADX WARN: Type inference failed for: r6v1, types: [long] */
    @Override // com.android.billingclient.api.BillingClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.billingclient.api.BillingResult launchBillingFlow(android.app.Activity r33, com.android.billingclient.api.BillingFlowParams r34) {
        /*
            Method dump skipped, instruction units count: 1573
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.launchBillingFlow(android.app.Activity, com.android.billingclient.api.BillingFlowParams):com.android.billingclient.api.BillingResult");
    }

    public final int launchBillingFlowCpp(Activity activity, BillingFlowParams billingFlowParams) {
        return launchBillingFlow(activity, billingFlowParams).zza;
    }

    @Override // com.android.billingclient.api.BillingClient
    public void queryProductDetailsAsync(final QueryProductDetailsParams queryProductDetailsParams, final ProductDetailsResponseListener productDetailsResponseListener) {
        if (zzG(new Callable() { // from class: com.android.billingclient.api.zzar
            @Override // java.util.concurrent.Callable
            public final Object call() throws JSONException {
                BillingClientImpl.zzs(this.zza, productDetailsResponseListener, queryProductDetailsParams);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzav
            @Override // java.lang.Runnable
            public final void run() {
                BillingClientImpl.zzN(this.zza, productDetailsResponseListener);
            }
        }, zzan(), zzF()) == null) {
            BillingResult billingResultZzaq = zzaq();
            zzbd(zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, 7, billingResultZzaq);
            productDetailsResponseListener.onProductDetailsResponse(billingResultZzaq, new QueryProductDetailsResult(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzk(), com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzby.zza));
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void queryPurchasesAsync(QueryPurchasesParams queryPurchasesParams, final PurchasesResponseListener purchasesResponseListener) {
        if (zzG(new zzbc(this, purchasesResponseListener, queryPurchasesParams.zza, false), 30000L, new Runnable() { // from class: com.android.billingclient.api.zzaz
            @Override // java.lang.Runnable
            public final void run() {
                BillingClientImpl.zzI(this.zza, purchasesResponseListener);
            }
        }, zzan(), zzF()) == null) {
            BillingResult billingResultZzaq = zzaq();
            zzbd(zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, 9, billingResultZzaq);
            purchasesResponseListener.onQueryPurchasesResponse(billingResultZzaq, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr.zzk());
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    @zzf
    public BillingResult showAlternativeBillingOnlyInformationDialog(final Activity activity, final AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener) {
        if (activity == null) {
            throw new IllegalArgumentException("Please provide a valid activity.");
        }
        if (!zzaW(3000L)) {
            zzic zzicVar = zzic.SERVICE_CONNECTION_NOT_READY;
            BillingResult billingResult = zzcp.zzj;
            zzbd(zzicVar, 16, billingResult);
            return billingResult;
        }
        if (!this.zzy) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Current Play Store version doesn't support alternative billing only.");
            zzic zzicVar2 = zzic.ALTERNATIVE_BILLING_ONLY_NOT_SUPPORTED;
            BillingResult billingResult2 = zzcp.zzD;
            zzbd(zzicVar2, 16, billingResult2);
            return billingResult2;
        }
        Handler handler = this.zze;
        final zzbe zzbeVar = new zzbe(this, handler, alternativeBillingOnlyInformationDialogListener);
        if (zzG(new Callable() { // from class: com.android.billingclient.api.zzae
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzaB(alternativeBillingOnlyInformationDialogListener, activity, zzbeVar);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzaf
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzaM(alternativeBillingOnlyInformationDialogListener, zzcp.zzk, zzic.EXECUTE_ASYNC_TIMEOUT, null);
            }
        }, handler, zzF()) != null) {
            return zzcp.zzi;
        }
        BillingResult billingResultZzaq = zzaq();
        zzbd(zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, 16, billingResultZzaq);
        return billingResultZzaq;
    }

    @Override // com.android.billingclient.api.BillingClient
    @zzj
    public BillingResult showExternalOfferInformationDialog(final Activity activity, final ExternalOfferInformationDialogListener externalOfferInformationDialogListener) {
        if (activity == null) {
            throw new IllegalArgumentException("Please provide a valid activity.");
        }
        if (!zzaW(3000L)) {
            zzic zzicVar = zzic.SERVICE_CONNECTION_NOT_READY;
            BillingResult billingResult = zzcp.zzj;
            zzbd(zzicVar, 25, billingResult);
            return billingResult;
        }
        if (!this.zzz) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Current Play Store version doesn't support external offer.");
            zzic zzicVar2 = zzic.EXTERNAL_OFFER_NOT_SUPPORTED;
            BillingResult billingResult2 = zzcp.zzt;
            zzbd(zzicVar2, 25, billingResult2);
            return billingResult2;
        }
        Handler handler = this.zze;
        final zzbf zzbfVar = new zzbf(this, handler, externalOfferInformationDialogListener);
        if (zzG(new Callable() { // from class: com.android.billingclient.api.zzat
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzaC(externalOfferInformationDialogListener, activity, zzbfVar);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.zzau
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzaK(externalOfferInformationDialogListener, zzcp.zzk, zzic.EXECUTE_ASYNC_TIMEOUT, null);
            }
        }, handler, zzF()) != null) {
            return zzcp.zzi;
        }
        BillingResult billingResultZzaq = zzaq();
        zzbd(zzic.MISSING_RESULT_FROM_EXECUTE_ASYNC, 25, billingResultZzaq);
        return billingResultZzaq;
    }

    @Override // com.android.billingclient.api.BillingClient
    public final BillingResult showInAppMessages(final Activity activity, InAppMessageParams inAppMessageParams, InAppMessageResponseListener inAppMessageResponseListener) {
        if (!zzaW(3000L)) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Service disconnected.");
            return zzcp.zzj;
        }
        if (!this.zzq) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Current client doesn't support showing in-app messages.");
            return zzcp.zzs;
        }
        View viewFindViewById = activity.findViewById(R.id.content);
        IBinder windowToken = viewFindViewById.getWindowToken();
        Rect rect = new Rect();
        viewFindViewById.getGlobalVisibleRect(rect);
        final Bundle bundle = new Bundle();
        bundle.putBinder("KEY_WINDOW_TOKEN", windowToken);
        bundle.putInt("KEY_DIMEN_LEFT", rect.left);
        bundle.putInt("KEY_DIMEN_TOP", rect.top);
        bundle.putInt("KEY_DIMEN_RIGHT", rect.right);
        bundle.putInt("KEY_DIMEN_BOTTOM", rect.bottom);
        bundle.putString("playBillingLibraryVersion", this.zzc);
        String str = this.zzd;
        if (str != null) {
            bundle.putString("playBillingLibraryWrapperVersion", str);
        }
        bundle.putIntegerArrayList("KEY_CATEGORY_IDS", inAppMessageParams.zza);
        Handler handler = this.zze;
        final zzbd zzbdVar = new zzbd(this, handler, inAppMessageResponseListener);
        zzG(new Callable() { // from class: com.android.billingclient.api.zzay
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzau(bundle, activity, zzbdVar);
                return null;
            }
        }, 5000L, null, handler, zzF());
        return zzcp.zzi;
    }

    public final void startConnection(long j) {
        startConnection(new zzbw(j));
    }

    public final synchronized ExecutorService zzF() {
        try {
            if (this.zzH == null) {
                this.zzH = Executors.newFixedThreadPool(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zza, new zzbb(this));
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.zzH;
    }

    public final /* synthetic */ Void zzaA(ExternalOfferAvailabilityListener externalOfferAvailabilityListener) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        try {
            if (!zzaX(30000L)) {
                zzaJ(externalOfferAvailabilityListener, zzcp.zzj, zzic.SERVICE_CONNECTION_NOT_READY, null);
            } else if (this.zzB) {
                synchronized (this.zza) {
                    zzamVar = this.zzi;
                }
                if (zzamVar == null) {
                    zzaJ(externalOfferAvailabilityListener, zzcp.zzj, zzic.SERVICE_RESET_TO_NULL, null);
                } else {
                    zzamVar.zzq(24, this.zzg.getPackageName(), com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzg(this.zzc, this.zzd, this.zzI.longValue()), new zzbs(externalOfferAvailabilityListener, this.zzh, this.zzm, null));
                }
            } else {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Current client doesn't support launching external offer flow.");
                zzaJ(externalOfferAvailabilityListener, zzcp.zzA, zzic.LAUNCH_EXTERNAL_OFFER_FLOW_NOT_SUPPORTED, null);
            }
        } catch (DeadObjectException e) {
            zzaJ(externalOfferAvailabilityListener, zzcp.zzj, zzic.IS_EXTERNAL_PAYMENT_AVAILABLE_SERVICE_CALL_EXCEPTION, e);
        } catch (Exception e2) {
            zzaJ(externalOfferAvailabilityListener, zzcp.zzh, zzic.IS_EXTERNAL_PAYMENT_AVAILABLE_SERVICE_CALL_EXCEPTION, e2);
        }
        return null;
    }

    public final /* synthetic */ Void zzaB(AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener, Activity activity, ResultReceiver resultReceiver) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        try {
            synchronized (this.zza) {
                zzamVar = this.zzi;
            }
            if (zzamVar == null) {
                zzaM(alternativeBillingOnlyInformationDialogListener, zzcp.zzj, zzic.SERVICE_RESET_TO_NULL, null);
            } else {
                zzamVar.zzm(21, this.zzg.getPackageName(), com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzg(this.zzc, this.zzd, this.zzI.longValue()), new zzbo(new WeakReference(activity), resultReceiver, null));
            }
        } catch (DeadObjectException e) {
            zzaM(alternativeBillingOnlyInformationDialogListener, zzcp.zzj, zzic.SHOW_ALTERNATIVE_BILLING_ONLY_DIALOG_SERVICE_CALL_EXCEPTION, e);
        } catch (Exception e2) {
            zzaM(alternativeBillingOnlyInformationDialogListener, zzcp.zzh, zzic.SHOW_ALTERNATIVE_BILLING_ONLY_DIALOG_SERVICE_CALL_EXCEPTION, e2);
        }
        return null;
    }

    public final /* synthetic */ Void zzaC(ExternalOfferInformationDialogListener externalOfferInformationDialogListener, Activity activity, ResultReceiver resultReceiver) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        try {
            synchronized (this.zza) {
                zzamVar = this.zzi;
            }
            if (zzamVar == null) {
                zzaK(externalOfferInformationDialogListener, zzcp.zzj, zzic.SERVICE_RESET_TO_NULL, null);
            } else {
                zzamVar.zzo(22, this.zzg.getPackageName(), com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzg(this.zzc, this.zzd, this.zzI.longValue()), new zzbq(new WeakReference(activity), resultReceiver, null));
            }
        } catch (DeadObjectException e) {
            zzaK(externalOfferInformationDialogListener, zzcp.zzj, zzic.SHOW_EXTERNAL_PAYMENT_DIALOG_SERVICE_CALL_EXCEPTION, e);
        } catch (Exception e2) {
            zzaK(externalOfferInformationDialogListener, zzcp.zzh, zzic.SHOW_EXTERNAL_PAYMENT_DIALOG_SERVICE_CALL_EXCEPTION, e2);
        }
        return null;
    }

    /* JADX WARN: Finally extract failed */
    public final void zzaD(ConsumeParams consumeParams, ConsumeResponseListener consumeResponseListener) throws Throwable {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        int iZza;
        String strZzj;
        String str = consumeParams.zza;
        try {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Consuming purchase with token: " + str);
            synchronized (this.zza) {
                try {
                    try {
                        zzamVar = this.zzi;
                    } catch (Throwable th) {
                        th = th;
                        while (true) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    }
                } catch (DeadObjectException e) {
                    e = e;
                    zzaG(consumeResponseListener, str, zzcp.zzj, zzic.CONSUME_PURCHASE_SERVICE_CALL_EXCEPTION, "Error consuming purchase!", e);
                    return;
                } catch (Exception e2) {
                    e = e2;
                    zzaG(consumeResponseListener, str, zzcp.zzh, zzic.CONSUME_PURCHASE_SERVICE_CALL_EXCEPTION, "Error consuming purchase!", e);
                    return;
                }
            }
            if (zzamVar == null) {
                try {
                    zzaG(consumeResponseListener, str, zzcp.zzj, zzic.SERVICE_RESET_TO_NULL, "Service has been reset to null.", null);
                    return;
                } catch (DeadObjectException e3) {
                    e = e3;
                    zzaG(consumeResponseListener, str, zzcp.zzj, zzic.CONSUME_PURCHASE_SERVICE_CALL_EXCEPTION, "Error consuming purchase!", e);
                    return;
                } catch (Exception e4) {
                    e = e4;
                    zzaG(consumeResponseListener, str, zzcp.zzh, zzic.CONSUME_PURCHASE_SERVICE_CALL_EXCEPTION, "Error consuming purchase!", e);
                    return;
                }
            }
            if (this.zzp) {
                String packageName = this.zzg.getPackageName();
                boolean z = this.zzp;
                String str2 = this.zzc;
                String str3 = this.zzd;
                long jLongValue = this.zzI.longValue();
                Bundle bundle = new Bundle();
                if (z) {
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzc(bundle, str2, str3, jLongValue);
                }
                Bundle bundleZze = zzamVar.zze(9, packageName, str, bundle);
                iZza = bundleZze.getInt("RESPONSE_CODE");
                strZzj = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzj(bundleZze, "BillingClient");
            } else {
                iZza = zzamVar.zza(3, this.zzg.getPackageName(), str);
                strZzj = "";
            }
            BillingResult billingResultZza = zzcp.zza(iZza, strZzj);
            if (iZza == 0) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Successfully consumed purchase.");
                consumeResponseListener.onConsumeResponse(billingResultZza, str);
            } else {
                zzaG(consumeResponseListener, str, billingResultZza, zzic.BILLING_RESULT_RECEIVED_FROM_PHONESKY, "Error consuming purchase with token. Response code: " + iZza, null);
            }
        } catch (DeadObjectException e5) {
            e = e5;
        } catch (Exception e6) {
            e = e6;
        }
    }

    public final void zzaE(AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener, BillingResult billingResult, zzic zzicVar, @Nullable Exception exc) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Error in acknowledge purchase!", exc);
        zzbf(zzicVar, 3, billingResult, zzcm.zza(exc));
        acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult);
    }

    public final void zzaF(AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener, BillingResult billingResult, zzic zzicVar, @Nullable Exception exc) {
        zzbf(zzicVar, 14, billingResult, zzcm.zza(exc));
        alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(billingResult);
    }

    public final void zzaG(ConsumeResponseListener consumeResponseListener, String str, BillingResult billingResult, zzic zzicVar, String str2, @Nullable Exception exc) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", str2, exc);
        zzbf(zzicVar, 4, billingResult, zzcm.zza(exc));
        consumeResponseListener.onConsumeResponse(billingResult, str);
    }

    public final void zzaH(AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener, BillingResult billingResult, zzic zzicVar, @Nullable Exception exc) {
        zzbf(zzicVar, 15, billingResult, zzcm.zza(exc));
        alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(billingResult, null);
    }

    public final void zzaI(ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener, BillingResult billingResult, zzic zzicVar, @Nullable Exception exc) {
        zzbf(zzicVar, 24, billingResult, zzcm.zza(exc));
        externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(billingResult, null);
    }

    public final void zzaJ(ExternalOfferAvailabilityListener externalOfferAvailabilityListener, BillingResult billingResult, zzic zzicVar, @Nullable Exception exc) {
        zzbf(zzicVar, 23, billingResult, zzcm.zza(exc));
        externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(billingResult);
    }

    public final void zzaK(ExternalOfferInformationDialogListener externalOfferInformationDialogListener, BillingResult billingResult, zzic zzicVar, @Nullable Exception exc) {
        zzbf(zzicVar, 25, billingResult, zzcm.zza(exc));
        externalOfferInformationDialogListener.onExternalOfferInformationDialogResponse(billingResult);
    }

    public final void zzaL(BillingConfigResponseListener billingConfigResponseListener, BillingResult billingResult, zzic zzicVar, @Nullable Exception exc) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "getBillingConfig got an exception.", exc);
        zzbf(zzicVar, 13, billingResult, zzcm.zza(exc));
        billingConfigResponseListener.onBillingConfigResponse(billingResult, null);
    }

    public final void zzaM(AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener, BillingResult billingResult, zzic zzicVar, @Nullable Exception exc) {
        zzbf(zzicVar, 16, billingResult, zzcm.zza(exc));
        alternativeBillingOnlyInformationDialogListener.onAlternativeBillingOnlyInformationDialogResponse(billingResult);
    }

    public final void zzaN(int i, zzic zzicVar, @Nullable Exception exc) {
        zzhv zzhvVar;
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "showInAppMessages error.", exc);
        zzcn zzcnVar = this.zzh;
        String strZza = zzcm.zza(exc);
        try {
            zzia zziaVarZzc = zzie.zzc();
            zziaVarZzc.zzo(i);
            if (zzicVar != null) {
                zziaVarZzc.zzn(zzicVar);
            }
            if (strZza != null) {
                zziaVarZzc.zza(strZza);
            }
            zzht zzhtVarZzc = zzhv.zzc();
            zzhtVarZzc.zzl(zziaVarZzc);
            zzhtVarZzc.zzp(30);
            zzhvVar = (zzhv) zzhtVarZzc.zze();
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to create logging payload", th);
            zzhvVar = null;
        }
        zzcnVar.zza(zzhvVar);
    }

    public final void zzaO(zzhv zzhvVar) {
        try {
            this.zzh.zzb(zzhvVar, this.zzm);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
    }

    public final void zzaP(zzhv zzhvVar, long j, boolean z) {
        try {
            this.zzh.zze(zzhvVar, this.zzm, j, z);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
    }

    public final void zzaQ(zzhz zzhzVar) {
        try {
            this.zzh.zzg(zzhzVar, this.zzm);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
    }

    public final void zzaR(zzic zzicVar, BillingResult billingResult, int i) {
        try {
            int i2 = zzcm.zza;
            zzht zzhtVar = (zzht) zzcm.zzb(zzicVar, 6, billingResult, null, zzij.BROADCAST_ACTION_UNSPECIFIED).zzm();
            zzjr zzjrVarZzc = zzjt.zzc();
            zzjrVarZzc.zza(i > 0);
            zzjrVarZzc.zzl(i);
            zzhtVar.zzo(zzjrVarZzc);
            zzaO((zzhv) zzhtVar.zze());
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
    }

    public final void zzaS(int i) {
        synchronized (this.zza) {
            try {
                if (this.zzb == 3) {
                    return;
                }
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Setting clientState from " + zzaZ(this.zzb) + " to " + zzaZ(i));
                this.zzb = i;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final synchronized void zzaT() {
        ExecutorService executorService = this.zzH;
        if (executorService != null) {
            executorService.shutdownNow();
            this.zzH = null;
        }
    }

    public final void zzaU(BillingClientStateListener billingClientStateListener, int i) {
        zzic zzicVar;
        BillingResult billingResultZzap;
        BillingResult billingResult;
        synchronized (this.zza) {
            try {
                if (zzaY()) {
                    billingResultZzap = zzap(i);
                } else {
                    if (this.zzb == 1) {
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Client is already in the process of connecting to billing service.");
                        zzic zzicVar2 = zzic.BILLING_CLIENT_CONNECTING;
                        billingResult = zzcp.zzd;
                        zzaR(zzicVar2, billingResult, i);
                    } else if (this.zzb == 3) {
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
                        zzic zzicVar3 = zzic.BILLING_CLIENT_CLOSED;
                        billingResult = zzcp.zzj;
                        zzaR(zzicVar3, billingResult, i);
                    } else {
                        zzaS(1);
                        if (i == 0) {
                            this.zzG = billingClientStateListener;
                            i = 0;
                        }
                        zzaV();
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Starting in-app billing setup.");
                        this.zzj = new zzbl(this, billingClientStateListener, i, null);
                        this.zzj.zzc();
                        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
                        intent.setPackage("com.android.vending");
                        List<ResolveInfo> listQueryIntentServices = this.zzg.getPackageManager().queryIntentServices(intent, 0);
                        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
                            zzicVar = zzic.INTENT_SERVICE_NOT_FOUND;
                        } else {
                            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
                            if (serviceInfo != null) {
                                String str = serviceInfo.packageName;
                                String str2 = serviceInfo.name;
                                if (!Objects.equals(str, "com.android.vending") || str2 == null) {
                                    zzicVar = zzic.INVALID_PHONESKY_PACKAGE;
                                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "The device doesn't have valid Play Store.");
                                } else {
                                    ComponentName componentName = new ComponentName(str, str2);
                                    Intent intent2 = new Intent(intent);
                                    intent2.setComponent(componentName);
                                    intent2.putExtra("playBillingLibraryVersion", this.zzc);
                                    synchronized (this.zza) {
                                        try {
                                            if (this.zzb == 2) {
                                                billingResultZzap = zzap(i);
                                            } else if (this.zzb != 1) {
                                                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Client state no longer CONNECTING, returning service disconnected.");
                                                zzic zzicVar4 = zzic.BILLING_CLIENT_TRANSITIONED_OUT_OF_CONNECTING;
                                                billingResult = zzcp.zzj;
                                                zzaR(zzicVar4, billingResult, i);
                                            } else {
                                                zzbl zzblVar = this.zzj;
                                                if ((i <= 0 || Build.VERSION.SDK_INT < 29) ? this.zzg.bindService(intent2, zzblVar, 1) : this.zzg.bindService(intent2, 1, zzF(), zzblVar)) {
                                                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Service was bonded successfully.");
                                                    billingResultZzap = null;
                                                } else {
                                                    zzicVar = zzic.BILLING_SERVICE_BLOCKED;
                                                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Connection to Billing service is blocked.");
                                                }
                                            }
                                        } finally {
                                        }
                                    }
                                }
                            } else {
                                zzicVar = zzic.INVALID_PHONESKY_PACKAGE;
                                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "The device doesn't have valid Play Store.");
                            }
                        }
                        zzaS(0);
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Billing service unavailable on device.");
                        BillingResult billingResult2 = zzcp.zzb;
                        zzaR(zzicVar, billingResult2, i);
                        billingResultZzap = billingResult2;
                    }
                    billingResultZzap = billingResult;
                }
            } finally {
            }
        }
        if (billingResultZzap != null) {
            billingClientStateListener.onBillingSetupFinished(billingResultZzap);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzaV() {
        synchronized (this.zza) {
            if (this.zzj != null) {
                try {
                    this.zzg.unbindService(this.zzj);
                } catch (Throwable th) {
                    try {
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "There was an exception while unbinding service!", th);
                        this.zzi = null;
                        this.zzj = null;
                    } finally {
                        this.zzi = null;
                        this.zzj = null;
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean zzaW(long j) {
        try {
            int i = ((BillingResult) zzar(1).get(Build.VERSION.SDK_INT < 29 ? 0L : 3000L, TimeUnit.MILLISECONDS)).zza;
            if (i == 0) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Reconnection succeeded with result: " + i);
            } else {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Reconnection failed with result: " + i);
            }
        } catch (Exception e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Error during reconnection attempt: ", e);
        }
        return zzaY();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean zzaX(long j) {
        long jMax;
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbg zzbgVarZzb = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbg.zzb(this.zzJ);
        long jZza = 30000;
        for (int i = 1; i <= 3; i++) {
            try {
                jMax = Math.max(0L, jZza);
            } catch (Exception e) {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Error during reconnection attempt: ", e);
            }
            if (jMax <= 0) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "No time remaining for reconnection attempt.");
                return zzaY();
            }
            int i2 = ((BillingResult) zzar(i).get(jMax, TimeUnit.MILLISECONDS)).zza;
            if (i2 == 0) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Reconnection succeeded with result: " + i2);
                return zzaY();
            }
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Reconnection failed with result: " + i2);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            jZza = 30000 - zzbgVarZzb.zza(timeUnit);
            long jPow = ((long) Math.pow(2.0d, i - 1)) * 1000;
            if (jZza < jPow) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Reconnection failed due to timeout limit reached.");
                return zzaY();
            }
            if (i < 3 && jPow > 0) {
                try {
                    Thread.sleep(jPow);
                    jZza = 30000 - zzbgVarZzb.zza(timeUnit);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Error sleeping during reconnection attempt: ", e2);
                }
            }
        }
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Max retries reached.");
        return zzaY();
    }

    public final boolean zzaY() {
        boolean z;
        synchronized (this.zza) {
            try {
                z = false;
                if (this.zzb == 2 && this.zzi != null && this.zzj != null) {
                    z = true;
                }
            } finally {
            }
        }
        return z;
    }

    public final void zzag(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.zze.post(runnable);
        }
    }

    public final /* synthetic */ Bundle zzal(int i, String str, String str2, BillingFlowParams billingFlowParams, Bundle bundle) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        try {
            synchronized (this.zza) {
                zzamVar = this.zzi;
            }
            return zzamVar == null ? com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzd(zzcp.zzj, zzic.SERVICE_RESET_TO_NULL) : zzamVar.zzg(i, this.zzg.getPackageName(), str, str2, null, bundle);
        } catch (DeadObjectException e) {
            return com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zze(zzcp.zzj, zzic.LAUNCH_BILLING_FLOW_EXCEPTION, zzcm.zza(e));
        } catch (Exception e2) {
            return com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zze(zzcp.zzh, zzic.LAUNCH_BILLING_FLOW_EXCEPTION, zzcm.zza(e2));
        }
    }

    public final /* synthetic */ Bundle zzam(String str, String str2) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        try {
            synchronized (this.zza) {
                zzamVar = this.zzi;
            }
            return zzamVar == null ? com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzd(zzcp.zzj, zzic.SERVICE_RESET_TO_NULL) : zzamVar.zzf(3, this.zzg.getPackageName(), str, str2, null);
        } catch (DeadObjectException e) {
            return com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zze(zzcp.zzj, zzic.LAUNCH_BILLING_FLOW_EXCEPTION, zzcm.zza(e));
        } catch (Exception e2) {
            return com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zze(zzcp.zzh, zzic.LAUNCH_BILLING_FLOW_EXCEPTION, zzcm.zza(e2));
        }
    }

    public final Handler zzan() {
        return Looper.myLooper() == null ? this.zze : new Handler(Looper.myLooper());
    }

    public final zzbu zzao(BillingResult billingResult, zzic zzicVar, String str, @Nullable Exception exc) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", str, exc);
        zzbf(zzicVar, 7, billingResult, zzcm.zza(exc));
        return new zzbu(billingResult.zza, billingResult.zzc, new ArrayList(), new ArrayList());
    }

    public final BillingResult zzap(int i) {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Service connection is valid. No need to re-initialize.");
        zzhx zzhxVarZzc = zzhz.zzc();
        zzhxVarZzc.zzo(6);
        zzjr zzjrVarZzc = zzjt.zzc();
        zzjrVarZzc.zzn(true);
        zzjrVarZzc.zza(i > 0);
        zzjrVarZzc.zzl(i);
        zzhxVarZzc.zzn(zzjrVarZzc);
        zzaQ((zzhz) zzhxVarZzc.zze());
        return zzcp.zzi;
    }

    public final BillingResult zzaq() {
        int[] iArr = {0, 3};
        synchronized (this.zza) {
            for (int i = 0; i < 2; i++) {
                if (this.zzb == iArr[i]) {
                    return zzcp.zzj;
                }
            }
            return zzcp.zzh;
        }
    }

    public final com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcx zzar(final int i) {
        if (this.zzE && !zzaY()) {
            return com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzu.zza(new com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzr() { // from class: com.android.billingclient.api.zzaa
                @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzr
                public final Object zza(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzp zzpVar) {
                    BillingClientImpl.zzq(this.zza, i, zzpVar);
                    return "reconnectIfNeeded";
                }
            });
        }
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Already connected or not opted into auto reconnection.");
        return new com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcv(zzcp.zzi);
    }

    public final Object zzas(AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener, AcknowledgePurchaseParams acknowledgePurchaseParams) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        try {
            if (!zzaX(30000L)) {
                zzic zzicVar = zzic.SERVICE_CONNECTION_NOT_READY;
                BillingResult billingResult = zzcp.zzj;
                zzbd(zzicVar, 3, billingResult);
                acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult);
                return null;
            }
            if (TextUtils.isEmpty(acknowledgePurchaseParams.zza)) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Please provide a valid purchase token.");
                zzic zzicVar2 = zzic.EMPTY_PURCHASE_TOKEN;
                BillingResult billingResult2 = zzcp.zzg;
                zzbd(zzicVar2, 3, billingResult2);
                acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult2);
                return null;
            }
            if (!this.zzp) {
                zzic zzicVar3 = zzic.API_VERSION_NOT_V9;
                BillingResult billingResult3 = zzcp.zza;
                zzbd(zzicVar3, 3, billingResult3);
                acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult3);
                return null;
            }
            synchronized (this.zza) {
                zzamVar = this.zzi;
            }
            if (zzamVar == null) {
                zzaE(acknowledgePurchaseResponseListener, zzcp.zzj, zzic.SERVICE_RESET_TO_NULL, null);
                return null;
            }
            String packageName = this.zzg.getPackageName();
            String str = acknowledgePurchaseParams.zza;
            String str2 = this.zzc;
            String str3 = this.zzd;
            long jLongValue = this.zzI.longValue();
            int i = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zza;
            Bundle bundle = new Bundle();
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzc(bundle, str2, str3, jLongValue);
            Bundle bundleZzd = zzamVar.zzd(9, packageName, str, bundle);
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzcp.zza(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzb(bundleZzd, "BillingClient"), com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzj(bundleZzd, "BillingClient")));
            return null;
        } catch (DeadObjectException e) {
            zzaE(acknowledgePurchaseResponseListener, zzcp.zzj, zzic.ACKNOWLEDGE_PURCHASE_SERVICE_CALL_EXCEPTION, e);
            return null;
        } catch (Exception e2) {
            zzaE(acknowledgePurchaseResponseListener, zzcp.zzh, zzic.ACKNOWLEDGE_PURCHASE_SERVICE_CALL_EXCEPTION, e2);
            return null;
        }
    }

    public final /* synthetic */ Object zzat(BillingConfigResponseListener billingConfigResponseListener) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        zzbv zzbvVar = null;
        try {
            if (!zzaX(30000L)) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Service disconnected.");
                zzic zzicVar = zzic.SERVICE_CONNECTION_NOT_READY;
                BillingResult billingResult = zzcp.zzj;
                zzbd(zzicVar, 13, billingResult);
                billingConfigResponseListener.onBillingConfigResponse(billingResult, null);
                return null;
            }
            if (!this.zzv) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Current client doesn't support get billing config.");
                zzic zzicVar2 = zzic.GET_BILLING_CONFIG_NOT_SUPPORTED;
                BillingResult billingResult2 = zzcp.zzy;
                zzbd(zzicVar2, 13, billingResult2);
                billingConfigResponseListener.onBillingConfigResponse(billingResult2, null);
                return null;
            }
            synchronized (this.zza) {
                zzamVar = this.zzi;
            }
            if (zzamVar == null) {
                zzaL(billingConfigResponseListener, zzcp.zzj, zzic.SERVICE_RESET_TO_NULL, null);
                return null;
            }
            String packageName = this.zzg.getPackageName();
            String str = this.zzc;
            String str2 = this.zzd;
            long jLongValue = this.zzI.longValue();
            int i = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zza;
            Bundle bundle = new Bundle();
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzc(bundle, str, str2, jLongValue);
            zzamVar.zzn(18, packageName, bundle, new zzbp(billingConfigResponseListener, this.zzh, this.zzm, zzbvVar));
            return null;
        } catch (DeadObjectException e) {
            zzaL(billingConfigResponseListener, zzcp.zzj, zzic.GET_BILLING_CONFIG_SERVICE_CALL_EXCEPTION, e);
            return null;
        } catch (Exception e2) {
            zzaL(billingConfigResponseListener, zzcp.zzh, zzic.GET_BILLING_CONFIG_SERVICE_CALL_EXCEPTION, e2);
            return null;
        }
    }

    public final /* synthetic */ Object zzau(Bundle bundle, Activity activity, ResultReceiver resultReceiver) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        try {
            synchronized (this.zza) {
                zzamVar = this.zzi;
            }
            if (zzamVar == null) {
                zzaN(-1, zzic.SERVICE_RESET_TO_NULL, null);
            } else {
                zzamVar.zzr(12, this.zzg.getPackageName(), bundle, new zzbt(new WeakReference(activity), resultReceiver, null));
            }
        } catch (DeadObjectException e) {
            zzaN(-1, zzic.SERVICE_CALL_EXCEPTION, e);
        } catch (Exception e2) {
            zzaN(6, zzic.SERVICE_CALL_EXCEPTION, e2);
        }
        return null;
    }

    public final String zzav(QueryProductDetailsParams queryProductDetailsParams) {
        String str = queryProductDetailsParams.zza;
        return !TextUtils.isEmpty(str) ? str : this.zzg.getPackageName();
    }

    public final /* synthetic */ Void zzax(AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        zzbv zzbvVar = null;
        try {
            if (!zzaX(30000L)) {
                zzaH(alternativeBillingOnlyReportingDetailsListener, zzcp.zzj, zzic.SERVICE_CONNECTION_NOT_READY, null);
            } else if (this.zzy) {
                synchronized (this.zza) {
                    zzamVar = this.zzi;
                }
                if (zzamVar == null) {
                    zzaH(alternativeBillingOnlyReportingDetailsListener, zzcp.zzj, zzic.SERVICE_RESET_TO_NULL, null);
                } else {
                    zzamVar.zzk(21, this.zzg.getPackageName(), com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzg(this.zzc, this.zzd, this.zzI.longValue()), new zzbm(alternativeBillingOnlyReportingDetailsListener, this.zzh, this.zzm, zzbvVar));
                }
            } else {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Current client doesn't support alternative billing only.");
                zzaH(alternativeBillingOnlyReportingDetailsListener, zzcp.zzD, zzic.ALTERNATIVE_BILLING_ONLY_NOT_SUPPORTED, null);
            }
        } catch (DeadObjectException e) {
            zzaH(alternativeBillingOnlyReportingDetailsListener, zzcp.zzj, zzic.CREATE_ALTERNATIVE_BILLING_ONLY_TOKEN_SERVICE_CALL_EXCEPTION, e);
        } catch (Exception e2) {
            zzaH(alternativeBillingOnlyReportingDetailsListener, zzcp.zzh, zzic.CREATE_ALTERNATIVE_BILLING_ONLY_TOKEN_SERVICE_CALL_EXCEPTION, e2);
        }
        return null;
    }

    public final /* synthetic */ Void zzay(ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener, String str) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        zzbv zzbvVar = null;
        try {
            if (!zzaX(30000L)) {
                zzaI(externalOfferReportingDetailsListener, zzcp.zzj, zzic.SERVICE_CONNECTION_NOT_READY, null);
            } else if (this.zzz) {
                synchronized (this.zza) {
                    zzamVar = this.zzi;
                }
                if (zzamVar == null) {
                    zzaI(externalOfferReportingDetailsListener, zzcp.zzj, zzic.SERVICE_RESET_TO_NULL, null);
                } else {
                    String packageName = this.zzg.getPackageName();
                    long j = this.zzg.getPackageManager().getPackageInfo(this.zzg.getPackageName(), 0).firstInstallTime;
                    String str2 = this.zzc;
                    String str3 = this.zzd;
                    long jLongValue = this.zzI.longValue();
                    int i = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zza;
                    Bundle bundle = new Bundle();
                    com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzc(bundle, str2, str3, jLongValue);
                    bundle.putLong("appInstallTimeMillis", j);
                    zzamVar.zzl(22, packageName, bundle, new zzbn(externalOfferReportingDetailsListener, this.zzh, this.zzm, zzbvVar));
                }
            } else {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Current client doesn't support external offer.");
                zzaI(externalOfferReportingDetailsListener, zzcp.zzt, zzic.EXTERNAL_OFFER_NOT_SUPPORTED, null);
            }
        } catch (DeadObjectException e) {
            zzaI(externalOfferReportingDetailsListener, zzcp.zzj, zzic.CREATE_EXTERNAL_PAYMENT_REPORTING_DETAILS_SERVICE_CALL_EXCEPTION, e);
        } catch (Exception e2) {
            zzaI(externalOfferReportingDetailsListener, zzcp.zzh, zzic.CREATE_EXTERNAL_PAYMENT_REPORTING_DETAILS_SERVICE_CALL_EXCEPTION, e2);
        }
        return null;
    }

    public final /* synthetic */ Void zzaz(AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener) throws Exception {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        try {
            if (!zzaX(30000L)) {
                zzaF(alternativeBillingOnlyAvailabilityListener, zzcp.zzj, zzic.SERVICE_CONNECTION_NOT_READY, null);
            } else if (this.zzy) {
                synchronized (this.zza) {
                    zzamVar = this.zzi;
                }
                if (zzamVar == null) {
                    zzaF(alternativeBillingOnlyAvailabilityListener, zzcp.zzj, zzic.SERVICE_RESET_TO_NULL, null);
                } else {
                    zzamVar.zzp(21, this.zzg.getPackageName(), com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzg(this.zzc, this.zzd, this.zzI.longValue()), new zzbr(alternativeBillingOnlyAvailabilityListener, this.zzh, this.zzm, null));
                }
            } else {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Current client doesn't support alternative billing only.");
                zzaF(alternativeBillingOnlyAvailabilityListener, zzcp.zzD, zzic.ALTERNATIVE_BILLING_ONLY_NOT_SUPPORTED, null);
            }
        } catch (DeadObjectException e) {
            zzaF(alternativeBillingOnlyAvailabilityListener, zzcp.zzj, zzic.IS_ALTERNATIVE_BILLING_ONLY_AVAILABLE_SERVICE_CALL_EXCEPTION, e);
        } catch (Exception e2) {
            zzaF(alternativeBillingOnlyAvailabilityListener, zzcp.zzh, zzic.IS_ALTERNATIVE_BILLING_ONLY_AVAILABLE_SERVICE_CALL_EXCEPTION, e2);
        }
        return null;
    }

    public final zzdb zzba(int i, BillingResult billingResult, zzic zzicVar, String str, @Nullable Exception exc) {
        zzbf(zzicVar, 9, billingResult, zzcm.zza(exc));
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", str, exc);
        return new zzdb(billingResult, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0167 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.android.billingclient.api.zzdb zzbb(java.lang.String r17, boolean r18, int r19) {
        /*
            Method dump skipped, instruction units count: 551
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.zzbb(java.lang.String, boolean, int):com.android.billingclient.api.zzdb");
    }

    public final void zzbc(BillingResult billingResult, zzic zzicVar, int i) {
        zzhz zzhzVar = null;
        zzhv zzhvVar = null;
        if (billingResult.zza == 0) {
            int i2 = zzcm.zza;
            try {
                zzhx zzhxVarZzc = zzhz.zzc();
                zzhxVarZzc.zzo(5);
                zziu zziuVarZzc = zzix.zzc();
                zziuVarZzc.zza(i);
                zzhxVarZzc.zzl((zzix) zziuVarZzc.zze());
                zzhzVar = (zzhz) zzhxVarZzc.zze();
            } catch (Exception e) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to create logging payload", e);
            }
            zzaQ(zzhzVar);
            return;
        }
        int i3 = zzcm.zza;
        try {
            zzht zzhtVarZzc = zzhv.zzc();
            zzia zziaVarZzc = zzie.zzc();
            zziaVarZzc.zzo(billingResult.zza);
            zziaVarZzc.zzl(billingResult.zzc);
            zziaVarZzc.zzn(zzicVar);
            zzhtVarZzc.zzl(zziaVarZzc);
            zzhtVarZzc.zzp(5);
            zziu zziuVarZzc2 = zzix.zzc();
            zziuVarZzc2.zza(i);
            zzhtVarZzc.zzm((zzix) zziuVarZzc2.zze());
            zzhvVar = (zzhv) zzhtVarZzc.zze();
        } catch (Exception e2) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingLogger", "Unable to create logging payload", e2);
        }
        zzaO(zzhvVar);
    }

    public final void zzbd(zzic zzicVar, int i, BillingResult billingResult) {
        try {
            int i2 = zzcm.zza;
            zzaO(zzcm.zzb(zzicVar, i, billingResult, null, zzij.BROADCAST_ACTION_UNSPECIFIED));
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
    }

    public final void zzbe(zzic zzicVar, int i, BillingResult billingResult, long j) {
        try {
            int i2 = zzcm.zza;
            try {
                this.zzh.zzc(zzcm.zzb(zzicVar, 2, billingResult, null, zzij.BROADCAST_ACTION_UNSPECIFIED), this.zzm, j);
            } catch (Throwable th) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
            }
        } catch (Throwable th2) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th2);
        }
    }

    public final void zzbf(zzic zzicVar, int i, BillingResult billingResult, @Nullable String str) {
        try {
            int i2 = zzcm.zza;
            zzaO(zzcm.zzb(zzicVar, i, billingResult, str, zzij.BROADCAST_ACTION_UNSPECIFIED));
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
    }

    public final void zzbg(zzic zzicVar, int i, BillingResult billingResult, long j, boolean z) {
        try {
            int i2 = zzcm.zza;
            zzaP(zzcm.zzb(zzicVar, 2, billingResult, null, zzij.BROADCAST_ACTION_UNSPECIFIED), j, z);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
    }

    public final void zzbh(zzic zzicVar, int i, BillingResult billingResult, @Nullable String str, long j, boolean z) {
        try {
            int i2 = zzcm.zza;
            zzaP(zzcm.zzb(zzicVar, 2, billingResult, str, zzij.BROADCAST_ACTION_UNSPECIFIED), j, z);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
    }

    public final void zzbi(int i) {
        try {
            int i2 = zzcm.zza;
            zzaQ(zzcm.zzc(i, zzij.BROADCAST_ACTION_UNSPECIFIED));
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to log.", th);
        }
    }

    @VisibleForTesting
    public final zzbu zzg(QueryProductDetailsParams queryProductDetailsParams) throws JSONException {
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzam zzamVar;
        int i;
        int i2;
        QueryProductDetailsParams queryProductDetailsParams2 = queryProductDetailsParams;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String strZzb = queryProductDetailsParams2.zzb();
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbr zzbrVar = queryProductDetailsParams2.zzb;
        int size = zzbrVar.size();
        int i3 = 0;
        while (i3 < size) {
            int i4 = i3 + 20;
            ArrayList arrayList3 = new ArrayList(zzbrVar.subList(i3, i4 > size ? size : i4));
            ArrayList<String> arrayList4 = new ArrayList<>();
            int size2 = arrayList3.size();
            for (int i5 = 0; i5 < size2; i5++) {
                arrayList4.add(((QueryProductDetailsParams.Product) arrayList3.get(i5)).zza);
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList4);
            String str = this.zzc;
            bundle.putString("playBillingLibraryVersion", str);
            try {
                synchronized (this.zza) {
                    zzamVar = this.zzi;
                }
                if (zzamVar == null) {
                    return zzao(zzcp.zzj, zzic.SERVICE_RESET_TO_NULL, "Service has been reset to null.", null);
                }
                boolean z = this.zzw && this.zzD.enablePrepaidPlans;
                zzav(queryProductDetailsParams);
                zzav(queryProductDetailsParams);
                zzav(queryProductDetailsParams);
                zzav(queryProductDetailsParams);
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zza zzaVarZza = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zza.zza(z, true, true, true, true, true);
                Bundle bundleZzj = zzamVar.zzj(true != this.zzx ? 17 : 20, this.zzg.getPackageName(), strZzb, bundle, com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzf(str, this.zzd, arrayList3, queryProductDetailsParams2.zza, null, zzaVarZza, this.zzI.longValue()));
                if (bundleZzj == null) {
                    return zzao(zzcp.zzB, zzic.NULL_BUNDLE_FROM_GET_SKU_DETAILS_SERVICE_CALL, "queryProductDetailsAsync got empty product details response.", null);
                }
                if (!bundleZzj.containsKey("DETAILS_LIST")) {
                    int iZzb = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzb(bundleZzj, "BillingClient");
                    String strZzj = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzj(bundleZzj, "BillingClient");
                    return iZzb != 0 ? zzao(zzcp.zza(iZzb, strZzj), zzic.BILLING_RESULT_RECEIVED_FROM_PHONESKY, MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("getSkuDetails() failed for queryProductDetailsAsync. Response code: ", iZzb), null) : zzao(zzcp.zza(6, strZzj), zzic.MISSING_DETAILS_LIST_IN_GET_SKU_DETAILS_RESPONSE, "getSkuDetails() returned a bundle with neither an error nor a product detail list for queryProductDetailsAsync.", null);
                }
                ArrayList<String> stringArrayList = bundleZzj.getStringArrayList("DETAILS_LIST");
                if (stringArrayList == null) {
                    return zzao(zzcp.zzB, zzic.NULL_DETAILS_LIST_IN_GET_SKU_DETAILS_RESPONSE, "queryProductDetailsAsync got null response list", null);
                }
                ArrayList arrayList5 = new ArrayList();
                int size3 = stringArrayList.size();
                for (int i6 = 0; i6 < size3; i6++) {
                    try {
                        ProductDetails productDetails = new ProductDetails(stringArrayList.get(i6));
                        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Got product details: ".concat(productDetails.toString()));
                        arrayList5.add(productDetails);
                    } catch (JSONException e) {
                        return zzao(zzcp.zza(6, "Error trying to decode SkuDetails."), zzic.ERROR_DECODING_SKU_DETAILS, "Got a JSON exception trying to decode ProductDetails. \n Exception: ", e);
                    }
                }
                ArrayList<String> stringArrayList2 = bundleZzj.getStringArrayList("UNFETCHED_PRODUCT_LIST");
                new ArrayList();
                try {
                    ArrayList arrayList6 = new ArrayList();
                    if (stringArrayList2 != null) {
                        int size4 = stringArrayList2.size();
                        int i7 = 0;
                        while (i7 < size4) {
                            String str2 = stringArrayList2.get(i7);
                            i7++;
                            UnfetchedProduct unfetchedProduct = new UnfetchedProduct(str2);
                            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzm("BillingClient", "Got unfetchedProduct: ".concat(unfetchedProduct.toString()));
                            arrayList6.add(unfetchedProduct);
                        }
                    } else {
                        int size5 = arrayList3.size();
                        int i8 = 0;
                        while (i8 < size5) {
                            Object obj = arrayList3.get(i8);
                            int i9 = i8 + 1;
                            QueryProductDetailsParams.Product product = (QueryProductDetailsParams.Product) obj;
                            int size6 = arrayList5.size();
                            int i10 = 0;
                            while (true) {
                                if (i10 >= size6) {
                                    i = size5;
                                    i2 = i9;
                                    arrayList6.add(new UnfetchedProduct(new JSONObject().put("productId", product.zza).put("type", product.zzb).put("statusCode", 0).toString()));
                                    break;
                                }
                                Object obj2 = arrayList5.get(i10);
                                i10++;
                                ProductDetails productDetails2 = (ProductDetails) obj2;
                                i = size5;
                                i2 = i9;
                                if (!product.zza.equals(productDetails2.zzc) || !product.zzb.equals(productDetails2.zzd)) {
                                    size5 = i;
                                    i9 = i2;
                                }
                            }
                            size5 = i;
                            i8 = i2;
                        }
                    }
                    arrayList.addAll(arrayList5);
                    arrayList2.addAll(arrayList6);
                    queryProductDetailsParams2 = queryProductDetailsParams;
                    i3 = i4;
                } catch (JSONException e2) {
                    return zzao(zzcp.zza(6, "Error trying to decode SkuDetails."), zzic.ERROR_DECODING_SKU_DETAILS, "Got a JSON exception trying to decode UnfetchedProduct. \n Exception: ", e2);
                }
            } catch (DeadObjectException e3) {
                return zzao(zzcp.zzj, zzic.GET_SKU_DETAILS_SERVICE_CALL_EXCEPTION, "queryProductDetailsAsync got a remote exception (try to reconnect).", e3);
            } catch (Exception e4) {
                return zzao(zzcp.zzh, zzic.GET_SKU_DETAILS_SERVICE_CALL_EXCEPTION, "queryProductDetailsAsync got a remote exception (try to reconnect).", e4);
            }
        }
        return new zzbu(0, "", arrayList, arrayList2);
    }

    public final zzcn zzj() {
        return this.zzh;
    }

    public final BillingResult zzm(final BillingResult billingResult) {
        if (Thread.interrupted()) {
            return billingResult;
        }
        this.zze.post(new Runnable() { // from class: com.android.billingclient.api.zzai
            @Override // java.lang.Runnable
            public final void run() {
                BillingClientImpl.zzP(this.zza, billingResult);
            }
        });
        return billingResult;
    }

    @Override // com.android.billingclient.api.BillingClient
    public void startConnection(BillingClientStateListener billingClientStateListener) {
        zzaU(billingClientStateListener, 0);
    }

    public BillingClientImpl(String str) {
        this.zza = new Object();
        this.zzb = 0;
        this.zze = new Handler(Looper.getMainLooper());
        this.zzm = 0;
        this.zzI = Long.valueOf(new Random().nextLong());
        this.zzJ = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzaz.zza();
        this.zzc = str;
        this.zzd = zzaw();
    }

    @AnyThread
    public BillingClientImpl(@Nullable String str, Context context, @Nullable zzcn zzcnVar, @Nullable ExecutorService executorService, BillingClient.Builder builder) {
        this.zza = new Object();
        this.zzb = 0;
        this.zze = new Handler(Looper.getMainLooper());
        this.zzm = 0;
        Long lValueOf = Long.valueOf(new Random().nextLong());
        this.zzI = lValueOf;
        this.zzJ = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzaz.zza();
        this.zzc = BuildConfig.VERSION_NAME;
        String strZzaw = zzaw();
        this.zzd = strZzaw;
        this.zzg = context.getApplicationContext();
        zzio zzioVarZzc = zziq.zzc();
        zzioVarZzc.zzs(BuildConfig.VERSION_NAME);
        if (strZzaw != null) {
            zzioVarZzc.zzt(strZzaw);
        }
        zzioVarZzc.zzq(this.zzg.getPackageName());
        zzioVarZzc.zzn(lValueOf.longValue());
        zzioVarZzc.zzr(builder.zza);
        zzioVarZzc.zza(Build.VERSION.SDK_INT);
        zzioVarZzc.zzp(781211065L);
        try {
            zzioVarZzc.zzl(this.zzg.getPackageManager().getPackageInfo(this.zzg.getPackageName(), 0).versionCode);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Error getting app version code.", th);
        }
        this.zzh = new zzcr(this.zzg, (zziq) zzioVarZzc.zze());
        this.zzg.getPackageName();
        this.zzE = builder.zza;
    }

    public final void initialize(Context context, PurchasesUpdatedListener purchasesUpdatedListener, PendingPurchasesParams pendingPurchasesParams, @Nullable UserChoiceBillingListener userChoiceBillingListener, String str, @Nullable zzcn zzcnVar, BillingClient.Builder builder) {
        this.zzg = context.getApplicationContext();
        zzio zzioVarZzc = zziq.zzc();
        zzioVarZzc.zzs(str);
        String str2 = this.zzd;
        if (str2 != null) {
            zzioVarZzc.zzt(str2);
        }
        zzioVarZzc.zzq(this.zzg.getPackageName());
        zzioVarZzc.zzn(this.zzI.longValue());
        zzioVarZzc.zzr(builder.zza);
        zzioVarZzc.zza(Build.VERSION.SDK_INT);
        zzioVarZzc.zzp(781211065L);
        try {
            zzioVarZzc.zzl(this.zzg.getPackageManager().getPackageInfo(this.zzg.getPackageName(), 0).versionCode);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Error getting app version code.", th);
        }
        if (zzcnVar != null) {
            this.zzh = zzcnVar;
        } else {
            this.zzh = new zzcr(this.zzg, (zziq) zzioVarZzc.zze());
        }
        if (purchasesUpdatedListener == null) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        this.zzf = new zzy(this.zzg, purchasesUpdatedListener, null, null, userChoiceBillingListener, this.zzh);
        this.zzD = pendingPurchasesParams;
        this.zzF = userChoiceBillingListener != null;
        this.zzE = builder.zza;
    }

    @AnyThread
    public BillingClientImpl(@Nullable String str, PendingPurchasesParams pendingPurchasesParams, Context context, PurchasesUpdatedListener purchasesUpdatedListener, @Nullable zzb zzbVar, @Nullable zzcn zzcnVar, @Nullable ExecutorService executorService, BillingClient.Builder builder) {
        this.zza = new Object();
        this.zzb = 0;
        this.zze = new Handler(Looper.getMainLooper());
        this.zzm = 0;
        this.zzI = Long.valueOf(new Random().nextLong());
        this.zzJ = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzaz.zza();
        this.zzc = BuildConfig.VERSION_NAME;
        this.zzd = zzaw();
        initialize(context, purchasesUpdatedListener, pendingPurchasesParams, (zzb) null, BuildConfig.VERSION_NAME, (zzcn) null, builder);
    }

    public BillingClientImpl(Activity activity, PendingPurchasesParams pendingPurchasesParams, String str, BillingClient.Builder builder) {
        this(activity.getApplicationContext(), pendingPurchasesParams, new zzbw(), str, null, null, null, null, builder);
    }

    @AnyThread
    public BillingClientImpl(@Nullable String str, PendingPurchasesParams pendingPurchasesParams, Context context, zzcu zzcuVar, @Nullable zzcn zzcnVar, @Nullable ExecutorService executorService, BillingClient.Builder builder) {
        this.zza = new Object();
        this.zzb = 0;
        this.zze = new Handler(Looper.getMainLooper());
        this.zzm = 0;
        Long lValueOf = Long.valueOf(new Random().nextLong());
        this.zzI = lValueOf;
        this.zzJ = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzaz.zza();
        this.zzc = BuildConfig.VERSION_NAME;
        String strZzaw = zzaw();
        this.zzd = strZzaw;
        this.zzg = context.getApplicationContext();
        zzio zzioVarZzc = zziq.zzc();
        zzioVarZzc.zzs(BuildConfig.VERSION_NAME);
        if (strZzaw != null) {
            zzioVarZzc.zzt(strZzaw);
        }
        zzioVarZzc.zzq(this.zzg.getPackageName());
        zzioVarZzc.zzn(lValueOf.longValue());
        zzioVarZzc.zzr(builder.zza);
        zzioVarZzc.zza(Build.VERSION.SDK_INT);
        zzioVarZzc.zzp(781211065L);
        try {
            zzioVarZzc.zzl(this.zzg.getPackageManager().getPackageInfo(this.zzg.getPackageName(), 0).versionCode);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Error getting app version code.", th);
        }
        this.zzh = new zzcr(this.zzg, (zziq) zzioVarZzc.zze());
        com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzn("BillingClient", "Billing client should have a valid listener but the provided is null.");
        this.zzf = new zzy(this.zzg, null, null, null, null, this.zzh);
        this.zzD = pendingPurchasesParams;
        this.zzg.getPackageName();
        this.zzE = builder.zza;
    }

    @AnyThread
    public BillingClientImpl(@Nullable String str, PendingPurchasesParams pendingPurchasesParams, Context context, PurchasesUpdatedListener purchasesUpdatedListener, @Nullable UserChoiceBillingListener userChoiceBillingListener, @Nullable zzcn zzcnVar, @Nullable ExecutorService executorService, BillingClient.Builder builder) {
        this(context, pendingPurchasesParams, purchasesUpdatedListener, BuildConfig.VERSION_NAME, null, userChoiceBillingListener, null, null, builder);
    }
}
