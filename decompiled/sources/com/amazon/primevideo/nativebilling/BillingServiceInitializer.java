package com.amazon.primevideo.nativebilling;

import com.amazon.ignitionshared.GMBMessageProcessor;
import com.amazon.ignitionshared.ServiceInitializer;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class BillingServiceInitializer implements ServiceInitializer {

    @NotNull
    public static final String BILLING_CANCEL_TRANSACTION = "billing.cancelTransaction";

    @NotNull
    public static final String BILLING_REQUEST_GET_ALTERNATIVE_BILLING_STATUS = "billing.getAlternativeBillingStatus";

    @NotNull
    public static final String BILLING_REQUEST_GET_ALTERNATIVE_BILLING_TOKEN = "billing.getAlternativeBillingToken";

    @NotNull
    public static final String BILLING_REQUEST_PLAY_STORE_COUNTRY = "billing.getStoreCountry";

    @NotNull
    public static final String BILLING_REQUEST_SHOW_ALTERNATIVE_BILLING_DIALOG = "billing.showAlternativeBillingDialog";

    @NotNull
    public static final String BILLING_REQUEST_START_SVOD_PURCHASE = "billing.startSvodPurchase";

    @NotNull
    public static final String BILLING_REQUEST_START_TVOD_PURCHASE = "billing.startTvodPurchase";

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "BillingServiceInitializer";

    @NotNull
    public final BillingProvider billingProvider;

    @NotNull
    public final GMBMessageProcessor gmbMessageProcessor;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public BillingServiceInitializer(@NotNull GMBMessageProcessor gmbMessageProcessor, @NotNull BillingProvider billingProvider) {
        Intrinsics.checkNotNullParameter(gmbMessageProcessor, "gmbMessageProcessor");
        Intrinsics.checkNotNullParameter(billingProvider, "billingProvider");
        this.gmbMessageProcessor = gmbMessageProcessor;
        this.billingProvider = billingProvider;
    }

    public static final void initialize$lambda$1$lambda$0(BillingServiceInitializer billingServiceInitializer, String str) {
        Log.d(TAG, "Received message to start purchase for SVOD");
        BillingProvider billingProvider = billingServiceInitializer.billingProvider;
        Intrinsics.checkNotNull(str);
        billingProvider.startSvodPurchase(str);
    }

    public static final void initialize$lambda$11$lambda$10(BillingServiceInitializer billingServiceInitializer, String str) {
        Log.d(TAG, "Received message to retrieve alternative billing token");
        BillingProvider billingProvider = billingServiceInitializer.billingProvider;
        Intrinsics.checkNotNull(str);
        billingProvider.getAlternativeBillingToken(str);
    }

    public static final void initialize$lambda$13$lambda$12(BillingServiceInitializer billingServiceInitializer, String str) {
        Log.d(TAG, "Received message to cancel current transaction");
        billingServiceInitializer.billingProvider.cancelCurrentTransaction();
    }

    public static final void initialize$lambda$3$lambda$2(BillingServiceInitializer billingServiceInitializer, String str) {
        Log.d(TAG, "Received message to start purchase for TVOD");
        BillingProvider billingProvider = billingServiceInitializer.billingProvider;
        Intrinsics.checkNotNull(str);
        billingProvider.startTvodPurchase(str);
    }

    public static final void initialize$lambda$5$lambda$4(BillingServiceInitializer billingServiceInitializer, String str) {
        Log.d(TAG, "Received message to query play store country with parameters " + str);
        billingServiceInitializer.billingProvider.queryPlayStoreCountry();
    }

    public static final void initialize$lambda$7$lambda$6(BillingServiceInitializer billingServiceInitializer, String str) {
        Log.d(TAG, "Received message to retrieve alternative billing status");
        BillingProvider billingProvider = billingServiceInitializer.billingProvider;
        Intrinsics.checkNotNull(str);
        billingProvider.getAlternativeBillingStatus(str);
    }

    public static final void initialize$lambda$9$lambda$8(BillingServiceInitializer billingServiceInitializer, String str) {
        Log.d(TAG, "Received message to display alternative billing dialog");
        BillingProvider billingProvider = billingServiceInitializer.billingProvider;
        Intrinsics.checkNotNull(str);
        billingProvider.showAlternativeBillingDialog(str);
    }

    @Override // com.amazon.ignitionshared.ServiceInitializer
    public void initialize() {
        this.gmbMessageProcessor.subscribeMessageHandler(BILLING_REQUEST_START_SVOD_PURCHASE, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.primevideo.nativebilling.BillingServiceInitializer$$ExternalSyntheticLambda0
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                BillingServiceInitializer.initialize$lambda$1$lambda$0(this.f$0, str);
            }
        });
        this.gmbMessageProcessor.subscribeMessageHandler(BILLING_REQUEST_START_TVOD_PURCHASE, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.primevideo.nativebilling.BillingServiceInitializer$$ExternalSyntheticLambda1
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                BillingServiceInitializer.initialize$lambda$3$lambda$2(this.f$0, str);
            }
        });
        this.gmbMessageProcessor.subscribeMessageHandler(BILLING_REQUEST_PLAY_STORE_COUNTRY, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.primevideo.nativebilling.BillingServiceInitializer$$ExternalSyntheticLambda2
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                BillingServiceInitializer.initialize$lambda$5$lambda$4(this.f$0, str);
            }
        });
        this.gmbMessageProcessor.subscribeMessageHandler(BILLING_REQUEST_GET_ALTERNATIVE_BILLING_STATUS, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.primevideo.nativebilling.BillingServiceInitializer$$ExternalSyntheticLambda3
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                BillingServiceInitializer.initialize$lambda$7$lambda$6(this.f$0, str);
            }
        });
        this.gmbMessageProcessor.subscribeMessageHandler(BILLING_REQUEST_SHOW_ALTERNATIVE_BILLING_DIALOG, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.primevideo.nativebilling.BillingServiceInitializer$$ExternalSyntheticLambda4
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                BillingServiceInitializer.initialize$lambda$9$lambda$8(this.f$0, str);
            }
        });
        this.gmbMessageProcessor.subscribeMessageHandler(BILLING_REQUEST_GET_ALTERNATIVE_BILLING_TOKEN, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.primevideo.nativebilling.BillingServiceInitializer$$ExternalSyntheticLambda5
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                BillingServiceInitializer.initialize$lambda$11$lambda$10(this.f$0, str);
            }
        });
        this.gmbMessageProcessor.subscribeMessageHandler(BILLING_CANCEL_TRANSACTION, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.primevideo.nativebilling.BillingServiceInitializer$$ExternalSyntheticLambda6
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                BillingServiceInitializer.initialize$lambda$13$lambda$12(this.f$0, str);
            }
        });
    }
}
