package com.amazon.primevideo.nativebilling;

import android.os.Handler;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.startup.MetricGroup;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.primevideo.nativebilling.BillingClientProvider;
import com.amazon.primevideo.nativebilling.BillingClientStatusProvider;
import com.amazon.primevideo.nativebilling.BillingConstants;
import com.amazon.primevideo.nativebilling.BillingUtils;
import com.amazon.reporting.Log;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class BillingClientStatusProvider {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String METRIC_SOURCE = "BillingClientStatusProvider";
    public static final String TAG = "BillingClientStatusProvider";

    @Nullable
    public BillingClientProvider.BillingClientHolder billingClientHolder;

    @NotNull
    public final BillingClientProvider billingClientProvider;

    @NotNull
    public final Handler handler;

    @NotNull
    public final MetricsRecorder metricsRecorder;

    @NotNull
    public final BillingConstants.Companion.MinervaMetricConstantsBuilder minervaMetricConstantsBuilder;
    public boolean perTransactionSupported;
    public boolean productDetailsSupported;
    public boolean subscriptionsSupported;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public BillingClientStatusProvider(@NotNull BillingClientProvider billingClientProvider, @NotNull Handler handler, @NotNull MetricsRecorder metricsRecorder) {
        Intrinsics.checkNotNullParameter(billingClientProvider, "billingClientProvider");
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(metricsRecorder, "metricsRecorder");
        this.billingClientProvider = billingClientProvider;
        this.handler = handler;
        this.metricsRecorder = metricsRecorder;
        BillingConstants.Companion.MinervaMetricConstantsBuilder minervaMetricConstantsBuilder = new BillingConstants.Companion.MinervaMetricConstantsBuilder(METRIC_SOURCE);
        this.minervaMetricConstantsBuilder = minervaMetricConstantsBuilder;
        this.productDetailsSupported = true;
        this.subscriptionsSupported = true;
        this.perTransactionSupported = true;
        checkStatusAndCacheResult(1, new BillingProviderMetricRecorder(metricsRecorder, MinervaConstants.BILLING_CLIENT_STATUS_PROVIDER_INITIALIZATION_SCHEMA_ID, minervaMetricConstantsBuilder.initializationEventName));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void checkStatusAndCacheResult(int i, BillingProviderMetricRecorder billingProviderMetricRecorder) {
        BillingClientProvider.BillingClientHolder billingClientHolderProvideBillingClientHolder = this.billingClientProvider.provideBillingClientHolder(new BillingClientStatusProvider$$ExternalSyntheticLambda0());
        this.billingClientHolder = billingClientHolderProvideBillingClientHolder;
        BillingClient billingClient = billingClientHolderProvideBillingClientHolder.billingClient;
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = billingProviderMetricRecorder;
        billingClient.startConnection(new AnonymousClass2(booleanRef, objectRef, this, i, billingClient));
    }

    public final synchronized void handleUnsupported(String str) {
        this.productDetailsSupported = false;
        this.subscriptionsSupported = false;
        this.perTransactionSupported = false;
        Log.d(TAG, str);
        BillingClientProvider.BillingClientHolder billingClientHolder = this.billingClientHolder;
        Intrinsics.checkNotNull(billingClientHolder);
        billingClientHolder.close();
    }

    public final synchronized boolean isChannelsSupported() {
        boolean z;
        try {
            z = this.productDetailsSupported && this.subscriptionsSupported;
            recordSupportLevel(this.minervaMetricConstantsBuilder.channelsSupport, z);
        } catch (Throwable th) {
            throw th;
        }
        return z;
    }

    public final synchronized boolean isPrimeAddOnSupported() {
        boolean z;
        try {
            z = this.productDetailsSupported && this.subscriptionsSupported;
            recordSupportLevel(this.minervaMetricConstantsBuilder.primeAddOnSupport, z);
        } catch (Throwable th) {
            throw th;
        }
        return z;
    }

    public final synchronized boolean isPrimeSupported() {
        boolean z;
        try {
            z = this.productDetailsSupported && this.subscriptionsSupported;
            recordSupportLevel(this.minervaMetricConstantsBuilder.primeSupport, z);
        } catch (Throwable th) {
            throw th;
        }
        return z;
    }

    public final synchronized boolean isTvodSupported() {
        boolean z;
        try {
            z = this.productDetailsSupported && this.perTransactionSupported;
            recordSupportLevel(this.minervaMetricConstantsBuilder.tvodSupport, z);
        } catch (Throwable th) {
            throw th;
        }
        return z;
    }

    public final synchronized void querySupportLevel(BillingClient billingClient) {
        try {
            if (billingClient.isFeatureSupported(BillingClient.FeatureType.PRODUCT_DETAILS).zza != 0) {
                this.productDetailsSupported = false;
            }
            if (billingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS).zza != 0) {
                this.subscriptionsSupported = false;
            }
            if (billingClient.isFeatureSupported(BillingClient.FeatureType.PER_TRANSACTION_OFFER).zza != 0) {
                this.perTransactionSupported = false;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void recordSupportLevel(String str, boolean z) {
        MetricGroup metricGroupCreateMetricGroup = this.metricsRecorder.createMetricGroup(MinervaConstants.BILLING_CLIENT_STATUS_PROVIDER_STATUS_SCHEMA_ID);
        MetricGroup.addCounterMetric$default(metricGroupCreateMetricGroup, str, z ? 1 : 0, null, 4, null);
        this.metricsRecorder.recordMinerva(metricGroupCreateMetricGroup);
    }

    /* JADX INFO: renamed from: com.amazon.primevideo.nativebilling.BillingClientStatusProvider$checkStatusAndCacheResult$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass2 implements BillingClientStateListener {
        public final /* synthetic */ BillingClient $billingClient;
        public final /* synthetic */ Ref.ObjectRef<BillingProviderMetricRecorder> $initGPBMetricRecorder;
        public final /* synthetic */ Ref.BooleanRef $receivedCallback;
        public final /* synthetic */ int $tries;
        public final /* synthetic */ BillingClientStatusProvider this$0;

        public AnonymousClass2(Ref.BooleanRef booleanRef, Ref.ObjectRef<BillingProviderMetricRecorder> objectRef, BillingClientStatusProvider billingClientStatusProvider, int i, BillingClient billingClient) {
            this.$receivedCallback = booleanRef;
            this.$initGPBMetricRecorder = objectRef;
            this.this$0 = billingClientStatusProvider;
            this.$tries = i;
            this.$billingClient = billingClient;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static final Unit onBillingSetupFinished$lambda$0(BillingClientStatusProvider billingClientStatusProvider, Ref.ObjectRef objectRef, BillingResult billingResult) {
            billingClientStatusProvider.handleUnsupported("Billing unsupported due to too many failed initialization attempts");
            ((BillingProviderMetricRecorder) objectRef.element).record(false, BillingConstants.Companion.getMetricNameFromResponse(billingResult.zza));
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static final Unit onBillingSetupFinished$lambda$1(Ref.ObjectRef objectRef, BillingClientStatusProvider billingClientStatusProvider, int i) {
            ((BillingProviderMetricRecorder) objectRef.element).addCounterToMetricGroup(billingClientStatusProvider.minervaMetricConstantsBuilder.retry);
            BillingClientProvider.BillingClientHolder billingClientHolder = billingClientStatusProvider.billingClientHolder;
            Intrinsics.checkNotNull(billingClientHolder);
            billingClientHolder.close();
            billingClientStatusProvider.checkStatusAndCacheResult(i + 1, (BillingProviderMetricRecorder) objectRef.element);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [T, com.amazon.primevideo.nativebilling.BillingProviderMetricRecorder] */
        @Override // com.android.billingclient.api.BillingClientStateListener
        public void onBillingSetupFinished(final BillingResult billingResult) {
            Intrinsics.checkNotNullParameter(billingResult, "billingResult");
            Ref.BooleanRef booleanRef = this.$receivedCallback;
            boolean z = true;
            if (booleanRef.element) {
                Ref.ObjectRef<BillingProviderMetricRecorder> objectRef = this.$initGPBMetricRecorder;
                BillingClientStatusProvider billingClientStatusProvider = this.this$0;
                objectRef.element = new BillingProviderMetricRecorder(billingClientStatusProvider.metricsRecorder, MinervaConstants.BILLING_CLIENT_STATUS_PROVIDER_REINITIALIZATION_SCHEMA_ID, billingClientStatusProvider.minervaMetricConstantsBuilder.reinitilizationEventName);
            } else {
                booleanRef.element = true;
            }
            try {
                BillingConstants.Companion companion = BillingConstants.Companion;
                companion.getClass();
                if (BillingConstants.TRANSIENT_ERRORS.contains(Integer.valueOf(billingResult.zza))) {
                    BillingUtils.Companion companion2 = BillingUtils.Companion;
                    final int i = this.$tries;
                    final BillingClientStatusProvider billingClientStatusProvider2 = this.this$0;
                    Handler handler = billingClientStatusProvider2.handler;
                    final Ref.ObjectRef<BillingProviderMetricRecorder> objectRef2 = this.$initGPBMetricRecorder;
                    companion2.retryWithExponentialBackoff(i, handler, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingClientStatusProvider$checkStatusAndCacheResult$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BillingClientStatusProvider.AnonymousClass2.onBillingSetupFinished$lambda$0(billingClientStatusProvider2, objectRef2, billingResult);
                        }
                    }, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingClientStatusProvider$checkStatusAndCacheResult$2$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BillingClientStatusProvider.AnonymousClass2.onBillingSetupFinished$lambda$1(objectRef2, billingClientStatusProvider2, i);
                        }
                    });
                    return;
                }
                int i2 = billingResult.zza;
                if (i2 != 0) {
                    z = false;
                }
                this.$initGPBMetricRecorder.element.record(z, companion.getMetricNameFromResponse(i2));
                if (!z) {
                    this.this$0.handleUnsupported("Billing unsupported due to inability to initialise billing client");
                    return;
                }
                this.this$0.querySupportLevel(this.$billingClient);
                BillingClientProvider.BillingClientHolder billingClientHolder = this.this$0.billingClientHolder;
                Intrinsics.checkNotNull(billingClientHolder);
                billingClientHolder.close();
            } catch (Throwable th) {
                this.this$0.handleUnsupported("Billing unsupported due to exception thrown in handler");
                throw th;
            }
        }

        @Override // com.android.billingclient.api.BillingClientStateListener
        public void onBillingServiceDisconnected() {
        }
    }
}
