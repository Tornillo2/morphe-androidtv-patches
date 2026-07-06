package com.amazon.primevideo.nativebilling;

import com.amazon.minerva.client.thirdparty.transport.UploadResult;
import java.util.Set;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class BillingConstants {
    public static final int BILLING_FAILED_TO_INIT = 99904;
    public static final int FAILED_TO_PARSE_PARAMETERS = 99906;
    public static final int OFFER_NOT_FOUND = 99902;
    public static final int OPERATION_TIMEOUT = 99903;
    public static final int PRODUCT_NOT_FOUND = 99901;
    public static final int PURCHASE_ALREADY_IN_PROGRESS = 99905;

    @NotNull
    public static final String REPORT_ALTERNATIVE_BILLING_STATUS_MESSAGE_TYPE = "billing.reportAlternativeBillingStatusResponse";

    @NotNull
    public static final String REPORT_GET_ALTERNATIVE_TOKEN_MESSAGE_TYPE = "billing.reportAlternativeBillingTokenResponse";

    @NotNull
    public static final String REPORT_PLAY_STORE_COUNTRY_MESSAGE_TYPE = "billing.reportStoreCountry";

    @NotNull
    public static final String REPORT_PURCHASE_EVENT_MESSAGE_TYPE = "billing.reportPurchaseEvent";

    @NotNull
    public static final String REPORT_PURCHASE_LAUNCH_STATE_MESSAGE_TYPE = "billing.reportPurchaseLaunchState";

    @NotNull
    public static final String REPORT_SHOW_ALTERNATIVE_BILLING_DIALOG_MESSAGE_TYPE = "billing.reportAlternativeBillingDialogResponse";

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Set<Integer> TRANSIENT_ERRORS = ArraysKt___ArraysKt.toSet(new Integer[]{12, 2, -1, 6, 7, 8});

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class MinervaMetricConstantsBuilder {

            @NotNull
            public final String billingClientIllegalState;

            @NotNull
            public final String billingFailedInitMetric;

            @NotNull
            public final String channelsSupport;

            @NotNull
            public final String deserializationFaultMetricName;

            @NotNull
            public final String getAlternativeBillingStatusEventName;

            @NotNull
            public final String getAlternativeBillingTokenEventName;

            @NotNull
            public final String initializationEventName;

            @NotNull
            public final String initializationTimeoutMetric;

            @NotNull
            public final String launchPurchaseEventName;

            @NotNull
            public final String noMatchingOfferMetric;

            @NotNull
            public final String noOffersMetric;

            @NotNull
            public final String noProductFoundMetric;

            @NotNull
            public final String primeAddOnSupport;

            @NotNull
            public final String primeSupport;

            @NotNull
            public final String purchaseAlreadyInProgressMetric;

            @NotNull
            public final String purchaseUpdatedEventName;

            @NotNull
            public final String queryPlayStoreCountryEventName;

            @NotNull
            public final String querySubscriptionEventName;

            @NotNull
            public final String queryTvodEventName;

            @NotNull
            public final String reinitilizationEventName;

            @NotNull
            public final String retry;

            @NotNull
            public final String showAlternativeBillingDialogEventName;

            @NotNull
            public final String tvodSupport;

            public MinervaMetricConstantsBuilder(@NotNull String source) {
                Intrinsics.checkNotNullParameter(source, "source");
                this.reinitilizationEventName = source.concat(".Reinitialization");
                this.initializationEventName = source.concat(".Initialization");
                this.queryTvodEventName = source.concat(".QueryTvod");
                this.querySubscriptionEventName = source.concat(".QuerySubscription");
                this.launchPurchaseEventName = source.concat(".LaunchPurchase");
                this.purchaseUpdatedEventName = source.concat(".PurchaseUpdated");
                this.queryPlayStoreCountryEventName = source.concat(".QueryCountry");
                this.showAlternativeBillingDialogEventName = source.concat(".AlternativeBillingDialog");
                this.getAlternativeBillingTokenEventName = source.concat(".AlternativeBillingToken");
                this.getAlternativeBillingStatusEventName = source.concat(".AlternativeBillingStatus");
                this.deserializationFaultMetricName = "DeserializationFault";
                this.purchaseAlreadyInProgressMetric = "PurchaseAlreadyInProgress";
                this.noProductFoundMetric = "NoProductFound";
                this.noOffersMetric = "NoOffers";
                this.noMatchingOfferMetric = "NoMatchingOffer";
                this.initializationTimeoutMetric = "InitializationTimeout";
                this.billingFailedInitMetric = "BillingFailedInit";
                this.billingClientIllegalState = "BillingClientIllegalState";
                this.retry = "Retry";
                this.primeSupport = source.concat(".PrimeFlow.Supported");
                this.tvodSupport = source.concat(".TvodFlow.Supported");
                this.channelsSupport = source.concat(".ChannelsFlow.Supported");
                this.primeAddOnSupport = source.concat(".PrimeAddOnFlow.Supported");
            }

            @NotNull
            public final String getBillingClientIllegalState() {
                return this.billingClientIllegalState;
            }

            @NotNull
            public final String getBillingFailedInitMetric() {
                return this.billingFailedInitMetric;
            }

            @NotNull
            public final String getChannelsSupport() {
                return this.channelsSupport;
            }

            @NotNull
            public final String getDeserializationFaultMetricName() {
                return this.deserializationFaultMetricName;
            }

            @NotNull
            public final String getGetAlternativeBillingStatusEventName() {
                return this.getAlternativeBillingStatusEventName;
            }

            @NotNull
            public final String getGetAlternativeBillingTokenEventName() {
                return this.getAlternativeBillingTokenEventName;
            }

            @NotNull
            public final String getInitializationEventName() {
                return this.initializationEventName;
            }

            @NotNull
            public final String getInitializationTimeoutMetric() {
                return this.initializationTimeoutMetric;
            }

            @NotNull
            public final String getLaunchPurchaseEventName() {
                return this.launchPurchaseEventName;
            }

            @NotNull
            public final String getNoMatchingOfferMetric() {
                return this.noMatchingOfferMetric;
            }

            @NotNull
            public final String getNoOffersMetric() {
                return this.noOffersMetric;
            }

            @NotNull
            public final String getNoProductFoundMetric() {
                return this.noProductFoundMetric;
            }

            @NotNull
            public final String getPrimeAddOnSupport() {
                return this.primeAddOnSupport;
            }

            @NotNull
            public final String getPrimeSupport() {
                return this.primeSupport;
            }

            @NotNull
            public final String getPurchaseAlreadyInProgressMetric() {
                return this.purchaseAlreadyInProgressMetric;
            }

            @NotNull
            public final String getPurchaseUpdatedEventName() {
                return this.purchaseUpdatedEventName;
            }

            @NotNull
            public final String getQueryPlayStoreCountryEventName() {
                return this.queryPlayStoreCountryEventName;
            }

            @NotNull
            public final String getQuerySubscriptionEventName() {
                return this.querySubscriptionEventName;
            }

            @NotNull
            public final String getQueryTvodEventName() {
                return this.queryTvodEventName;
            }

            @NotNull
            public final String getReinitilizationEventName() {
                return this.reinitilizationEventName;
            }

            @NotNull
            public final String getRetry() {
                return this.retry;
            }

            @NotNull
            public final String getShowAlternativeBillingDialogEventName() {
                return this.showAlternativeBillingDialogEventName;
            }

            @NotNull
            public final String getTvodSupport() {
                return this.tvodSupport;
            }
        }

        public Companion() {
        }

        @NotNull
        public final String getGmbErrorStringFromResponse(int i) {
            if (i == -2) {
                return "FEATURE_NOT_SUPPORTED";
            }
            if (i == -1) {
                return "SERVICE_DISCONNECTED";
            }
            if (i == 11) {
                return "EXPIRED_OFFER_TOKEN";
            }
            if (i == 12) {
                return "NETWORK_ERROR";
            }
            switch (i) {
                case 1:
                    return "USER_CANCELED";
                case 2:
                    return "SERVICE_UNAVAILABLE";
                case 3:
                    return "BILLING_UNAVAILABLE";
                case 4:
                    return "ITEM_UNAVAILABLE";
                case 5:
                    return "DEVELOPER_ERROR";
                case 6:
                    return "ERROR";
                case 7:
                    return "ITEM_ALREADY_OWNED";
                case 8:
                    return "ITEM_NOT_OWNED";
                default:
                    switch (i) {
                        case BillingConstants.PRODUCT_NOT_FOUND /* 99901 */:
                            return "PRODUCT_NOT_FOUND";
                        case BillingConstants.OFFER_NOT_FOUND /* 99902 */:
                            return "OFFER_NOT_FOUND";
                        case BillingConstants.OPERATION_TIMEOUT /* 99903 */:
                            return "OPERATION_TIMEOUT";
                        case BillingConstants.BILLING_FAILED_TO_INIT /* 99904 */:
                            return "BILLING_FAILED_TO_INIT";
                        case BillingConstants.PURCHASE_ALREADY_IN_PROGRESS /* 99905 */:
                            return "PURCHASE_ALREADY_IN_PROGRESS";
                        case BillingConstants.FAILED_TO_PARSE_PARAMETERS /* 99906 */:
                            return "FAILED_TO_PARSE_PARAMETERS";
                        default:
                            return UploadResult.UNEXPECTED_ERROR;
                    }
            }
        }

        @Nullable
        public final String getMetricNameFromResponse(int i) {
            String str;
            switch (i) {
                case -2:
                    str = "FeatureNotSupported";
                    break;
                case -1:
                    str = "ServiceDisconnected";
                    break;
                case 0:
                    str = null;
                    break;
                case 1:
                    str = "UserCancelled";
                    break;
                case 2:
                    str = "ServiceUnavailable";
                    break;
                case 3:
                    str = "BillingUnavailable";
                    break;
                case 4:
                    str = "ItemUnavailable";
                    break;
                case 5:
                    str = "DeveloperError";
                    break;
                case 6:
                    str = "Error";
                    break;
                case 7:
                    str = "ItemAlreadyOwned";
                    break;
                case 8:
                    str = "ItemNotOwned";
                    break;
                case 9:
                case 10:
                default:
                    str = "UnexpectedError";
                    break;
                case 11:
                    str = "ExpiredOfferToken";
                    break;
                case 12:
                    str = "NetworkError";
                    break;
            }
            if (str != null) {
                return "GoogleError.".concat(str);
            }
            return null;
        }

        @NotNull
        public final Set<Integer> getTRANSIENT_ERRORS() {
            return BillingConstants.TRANSIENT_ERRORS;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }
}
