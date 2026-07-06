package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class BillingClient {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface BillingResponseCode {
        public static final int BILLING_UNAVAILABLE = 3;
        public static final int DEVELOPER_ERROR = 5;
        public static final int ERROR = 6;

        @zzs
        public static final int EXPIRED_OFFER_TOKEN = 11;
        public static final int FEATURE_NOT_SUPPORTED = -2;
        public static final int ITEM_ALREADY_OWNED = 7;
        public static final int ITEM_NOT_OWNED = 8;
        public static final int ITEM_UNAVAILABLE = 4;
        public static final int NETWORK_ERROR = 12;
        public static final int OK = 0;
        public static final int SERVICE_DISCONNECTED = -1;

        @Deprecated
        public static final int SERVICE_TIMEOUT = -3;
        public static final int SERVICE_UNAVAILABLE = 2;
        public static final int USER_CANCELED = 1;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AnyThread
    public static final class Builder {
        public volatile boolean zza;
        public volatile com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzbj zzb;
        public volatile String zzc;
        public volatile PendingPurchasesParams zzd;
        public final Context zze;
        public volatile PurchasesUpdatedListener zzf;
        public volatile zzcu zzg;
        public volatile zzcn zzh;
        public volatile zzb zzi;
        public volatile UserChoiceBillingListener zzj;

        @Nullable
        public volatile ExecutorService zzk;
        public volatile boolean zzl;
        public volatile boolean zzm;
        public volatile boolean zzn;

        public /* synthetic */ Builder(Context context, zzz zzzVar) {
            this.zze = context;
        }

        @NonNull
        public BillingClient build() {
            Context context = this.zze;
            if (context == null) {
                throw new IllegalArgumentException("Please provide a valid Context.");
            }
            if (this.zzf == null) {
                if (this.zzj != null) {
                    throw new IllegalArgumentException("Please provide a valid listener for Google Play Billing purchases updates when enabling User Choice Billing.");
                }
                if (this.zzl || this.zzm) {
                    return zza() ? new zzck(null, context, null, null, this) : new BillingClientImpl(null, context, null, null, this);
                }
                throw new IllegalArgumentException("Please provide a valid listener for purchases updates.");
            }
            if (this.zzd == null || !this.zzd.enableOneTimeProducts) {
                throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
            }
            if (this.zzf == null) {
                PendingPurchasesParams pendingPurchasesParams = this.zzd;
                return zza() ? new zzck(null, pendingPurchasesParams, context, null, null, null, this) : new BillingClientImpl(null, pendingPurchasesParams, context, null, null, null, this);
            }
            if (this.zzj == null) {
                PendingPurchasesParams pendingPurchasesParams2 = this.zzd;
                PurchasesUpdatedListener purchasesUpdatedListener = this.zzf;
                return zza() ? new zzck((String) null, pendingPurchasesParams2, context, purchasesUpdatedListener, (zzb) null, (zzcn) null, (ExecutorService) null, this) : new BillingClientImpl((String) null, pendingPurchasesParams2, context, purchasesUpdatedListener, (zzb) null, (zzcn) null, (ExecutorService) null, this);
            }
            PendingPurchasesParams pendingPurchasesParams3 = this.zzd;
            PurchasesUpdatedListener purchasesUpdatedListener2 = this.zzf;
            UserChoiceBillingListener userChoiceBillingListener = this.zzj;
            return zza() ? new zzck((String) null, pendingPurchasesParams3, context, purchasesUpdatedListener2, userChoiceBillingListener, (zzcn) null, (ExecutorService) null, this) : new BillingClientImpl((String) null, pendingPurchasesParams3, context, purchasesUpdatedListener2, userChoiceBillingListener, (zzcn) null, (ExecutorService) null, this);
        }

        @NonNull
        @zzf
        public Builder enableAlternativeBillingOnly() {
            this.zzl = true;
            return this;
        }

        @NonNull
        @zzh
        public Builder enableAutoServiceReconnection() {
            this.zza = true;
            return this;
        }

        @NonNull
        @zzj
        public Builder enableExternalOffer() {
            this.zzm = true;
            return this;
        }

        @NonNull
        @zzr
        public Builder enablePendingPurchases(@NonNull PendingPurchasesParams pendingPurchasesParams) {
            this.zzd = pendingPurchasesParams;
            return this;
        }

        @NonNull
        @zzu
        public Builder enableUserChoiceBilling(@NonNull UserChoiceBillingListener userChoiceBillingListener) {
            this.zzj = userChoiceBillingListener;
            return this;
        }

        @NonNull
        public Builder setListener(@NonNull PurchasesUpdatedListener purchasesUpdatedListener) {
            this.zzf = purchasesUpdatedListener;
            return this;
        }

        public final boolean zza() {
            try {
                Context context = this.zze;
                return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean("com.google.android.play.billingclient.enableBillingOverridesTesting", false);
            } catch (Exception e) {
                com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzc.zzo("BillingClient", "Unable to retrieve metadata value for enableBillingOverridesTesting.", e);
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ConnectionState {
        public static final int CLOSED = 3;
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface FeatureType {

        @NonNull
        @zzf
        public static final String ALTERNATIVE_BILLING_ONLY = "jjj";

        @NonNull
        @zzg
        public static final String AUTO_PAY = "mmm";

        @NonNull
        @zzk
        public static final String BILLING_CONFIG = "ggg";

        @NonNull
        @zzi
        public static final String CROSS_SELL = "aaa";

        @NonNull
        @zzj
        public static final String EXTERNAL_OFFER = "kkk";

        @NonNull
        public static final String IN_APP_MESSAGING = "bbb";

        @NonNull
        @zzs
        public static final String PER_TRANSACTION_OFFER = "ddd";

        @NonNull
        public static final String PRICE_CHANGE_CONFIRMATION = "priceChangeConfirmation";

        @NonNull
        public static final String PRODUCT_DETAILS = "fff";

        @NonNull
        public static final String SUBSCRIPTIONS = "subscriptions";

        @NonNull
        public static final String SUBSCRIPTIONS_UPDATE = "subscriptionsUpdate";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @zzn
    @Retention(RetentionPolicy.SOURCE)
    public @interface OnPurchasesUpdatedSubResponseCode {
        public static final int NO_APPLICABLE_SUB_RESPONSE_CODE = 0;
        public static final int PAYMENT_DECLINED_DUE_TO_INSUFFICIENT_FUNDS = 1;
        public static final int USER_INELIGIBLE = 2;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProductType {

        @NonNull
        public static final String INAPP = "inapp";

        @NonNull
        public static final String SUBS = "subs";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @Deprecated
    public @interface SkuType {

        @NonNull
        public static final String INAPP = "inapp";

        @NonNull
        public static final String SUBS = "subs";
    }

    @NonNull
    @AnyThread
    public static Builder newBuilder(@NonNull Context context) {
        return new Builder(context, null);
    }

    @AnyThread
    public abstract void acknowledgePurchase(@NonNull AcknowledgePurchaseParams acknowledgePurchaseParams, @NonNull AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener);

    @AnyThread
    public abstract void consumeAsync(@NonNull ConsumeParams consumeParams, @NonNull ConsumeResponseListener consumeResponseListener);

    @AnyThread
    @zzf
    @KeepForSdk
    public abstract void createAlternativeBillingOnlyReportingDetailsAsync(@NonNull AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener);

    @AnyThread
    @zzj
    public abstract void createExternalOfferReportingDetailsAsync(@NonNull ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener);

    @AnyThread
    public abstract void endConnection();

    @zzk
    @AnyThread
    public abstract void getBillingConfigAsync(@NonNull GetBillingConfigParams getBillingConfigParams, @NonNull BillingConfigResponseListener billingConfigResponseListener);

    @AnyThread
    public abstract int getConnectionState();

    @AnyThread
    @zzf
    @KeepForSdk
    public abstract void isAlternativeBillingOnlyAvailableAsync(@NonNull AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener);

    @AnyThread
    @zzj
    public abstract void isExternalOfferAvailableAsync(@NonNull ExternalOfferAvailabilityListener externalOfferAvailabilityListener);

    @NonNull
    @AnyThread
    public abstract BillingResult isFeatureSupported(@NonNull String str);

    @AnyThread
    public abstract boolean isReady();

    @NonNull
    @UiThread
    public abstract BillingResult launchBillingFlow(@NonNull Activity activity, @NonNull BillingFlowParams billingFlowParams);

    @AnyThread
    public abstract void queryProductDetailsAsync(@NonNull QueryProductDetailsParams queryProductDetailsParams, @NonNull ProductDetailsResponseListener productDetailsResponseListener);

    @AnyThread
    public abstract void queryPurchasesAsync(@NonNull QueryPurchasesParams queryPurchasesParams, @NonNull PurchasesResponseListener purchasesResponseListener);

    @NonNull
    @zzf
    @UiThread
    public abstract BillingResult showAlternativeBillingOnlyInformationDialog(@NonNull Activity activity, @NonNull AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener);

    @NonNull
    @zzj
    @UiThread
    public abstract BillingResult showExternalOfferInformationDialog(@NonNull Activity activity, @NonNull ExternalOfferInformationDialogListener externalOfferInformationDialogListener);

    @NonNull
    @UiThread
    public abstract BillingResult showInAppMessages(@NonNull Activity activity, @NonNull InAppMessageParams inAppMessageParams, @NonNull InAppMessageResponseListener inAppMessageResponseListener);

    @AnyThread
    public abstract void startConnection(@NonNull BillingClientStateListener billingClientStateListener);
}
