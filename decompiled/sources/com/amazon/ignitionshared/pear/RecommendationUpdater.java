package com.amazon.ignitionshared.pear;

import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher;
import com.amazon.ignitionshared.recommendation.scheduler.RecommendationsScheduler;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.reporting.Log;
import java.util.Set;
import javax.inject.Inject;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RecommendationUpdater {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String MINERVA_GENERAL_FLOW_EVENT_NAME = "PearRecommendationUpdater.GeneralFlowRequest";

    @NotNull
    public static final String PLACEMENTS_BOOTSTRAP_REQUEST_EVENT_NAME = "PearRecommendationUpdater.BootstrapPlacementsRequest";

    @NotNull
    public static final String PLACEMENTS_ERROR_REQUEST_EVENT_NAME = "PearRecommendationUpdater.ErrorPlacementsRequest";

    @NotNull
    public static final String PLACEMENTS_REQUEST_EVENT_NAME = "PearRecommendationUpdater.PlacementsRequest";
    public static final String TAG = "RecommendationUpdater";

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final MinervaMetrics minervaMetrics;

    @NotNull
    public final PearRecommendationFlowController pearRecommendationFlowController;

    @NotNull
    public final PearResponseParser pearResponseParser;

    @NotNull
    public final RecommendationsScheduler pearScheduler;

    @NotNull
    public final PearUpdateStructure pearUpdateStructure;

    @NotNull
    public final Set<PearPlacement> placementsSet;

    @NotNull
    public final RecommendationPublisher recommendationPublisher;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public RecommendationUpdater(@NotNull PearResponseParser pearResponseParser, @NotNull PearUpdateStructure pearUpdateStructure, @NotNull MinervaMetrics minervaMetrics, @NotNull PearRecommendationFlowController pearRecommendationFlowController, @NotNull DeviceProperties deviceProperties, @NotNull RecommendationPublisher recommendationPublisher, @NotNull RecommendationsScheduler pearScheduler, @NotNull Set<PearPlacement> placementsSet) {
        Intrinsics.checkNotNullParameter(pearResponseParser, "pearResponseParser");
        Intrinsics.checkNotNullParameter(pearUpdateStructure, "pearUpdateStructure");
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        Intrinsics.checkNotNullParameter(pearRecommendationFlowController, "pearRecommendationFlowController");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(recommendationPublisher, "recommendationPublisher");
        Intrinsics.checkNotNullParameter(pearScheduler, "pearScheduler");
        Intrinsics.checkNotNullParameter(placementsSet, "placementsSet");
        this.pearResponseParser = pearResponseParser;
        this.pearUpdateStructure = pearUpdateStructure;
        this.minervaMetrics = minervaMetrics;
        this.pearRecommendationFlowController = pearRecommendationFlowController;
        this.deviceProperties = deviceProperties;
        this.recommendationPublisher = recommendationPublisher;
        this.pearScheduler = pearScheduler;
        this.placementsSet = placementsSet;
    }

    public final boolean isRecommendationEnabled() {
        Object obj = this.deviceProperties.get(DeviceProperties.RECOMMENDATIONS_ENABLED);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Boolean) obj).booleanValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0191, code lost:
    
        if (r12 == null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0208, code lost:
    
        if (r12 == null) goto L46;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean sendRequest(kotlin.jvm.functions.Function0<? extends com.android.volley.toolbox.RequestFuture<kotlinx.serialization.json.JsonObject>> r19, java.lang.String r20, com.amazon.minerva.client.thirdparty.api.MetricEvent r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 557
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ignitionshared.pear.RecommendationUpdater.sendRequest(kotlin.jvm.functions.Function0, java.lang.String, com.amazon.minerva.client.thirdparty.api.MetricEvent):boolean");
    }

    public final void updateRecommendations() {
        String str = TAG;
        Log.d(str, "Recommendations enabled: " + isRecommendationEnabled());
        if (!isRecommendationEnabled()) {
            Log.i(str, "Recommendations are not enabled");
            this.recommendationPublisher.clearRecommendations();
            return;
        }
        final PearParameterUpdateMessage storedUpdateMessage = this.pearUpdateStructure.getStoredUpdateMessage();
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.PEAR_RECOMMENDATION_UPDATER_GENERAL_FLOW_EVENT_SCHEMA_ID);
        metricEventCreateMetricEvent.addLong("PearRecommendationUpdater.GeneralFlowRequest.Started", 1L);
        boolean zSendRequest = false;
        if (storedUpdateMessage != null) {
            MetricEvent metricEventCreateMetricEvent2 = this.minervaMetrics.createMetricEvent(MinervaConstants.PEAR_RECOMMENDATION_UPDATER_PLACEMENTS_EVENT_SCHEMA_ID);
            try {
                zSendRequest = sendRequest(new Function0() { // from class: com.amazon.ignitionshared.pear.RecommendationUpdater$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        RecommendationUpdater recommendationUpdater = this.f$0;
                        return recommendationUpdater.pearRecommendationFlowController.beginPlacementsFlow(storedUpdateMessage);
                    }
                }, PLACEMENTS_REQUEST_EVENT_NAME, metricEventCreateMetricEvent2);
            } catch (IllegalArgumentException e) {
                metricEventCreateMetricEvent2.addLong("PearRecommendationUpdater.PlacementsRequest.Failure.MissingAccessTokenPlaceholder", 1L);
                metricEventCreateMetricEvent2.addLong("PearRecommendationUpdater.PlacementsRequest.Failure", 1L);
                this.minervaMetrics.record(metricEventCreateMetricEvent2, true);
                Log.e(TAG, "Unable to perform normal placements flow.", e);
            } catch (Exception e2) {
                Log.e(TAG, "Exception while calling placements api", e2);
            }
            if (!zSendRequest) {
                try {
                    zSendRequest = sendRequest(new Function0() { // from class: com.amazon.ignitionshared.pear.RecommendationUpdater$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            RecommendationUpdater recommendationUpdater = this.f$0;
                            return recommendationUpdater.pearRecommendationFlowController.beginErrorPlacementsFlow(storedUpdateMessage);
                        }
                    }, PLACEMENTS_ERROR_REQUEST_EVENT_NAME, this.minervaMetrics.createMetricEvent(MinervaConstants.PEAR_RECOMMENDATION_UPDATER_ERROR_PLACEMENTS_EVENT_SCHEMA_ID));
                } catch (Exception e3) {
                    Log.e(TAG, "Exception while calling error placements api", e3);
                }
            }
        }
        if (!zSendRequest) {
            try {
                zSendRequest = sendRequest(new Function0() { // from class: com.amazon.ignitionshared.pear.RecommendationUpdater$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return this.f$0.pearRecommendationFlowController.updateRecommendationsUsingBootstrapPlacements();
                    }
                }, PLACEMENTS_BOOTSTRAP_REQUEST_EVENT_NAME, this.minervaMetrics.createMetricEvent(MinervaConstants.PEAR_RECOMMENDATION_UPDATER_BOOTSTRAP_PLACEMENTS_EVENT_SCHEMA_ID));
            } catch (Exception e4) {
                Log.e(TAG, "Exception while calling bootstrap placements api", e4);
            }
        }
        if (zSendRequest) {
            metricEventCreateMetricEvent.addLong("PearRecommendationUpdater.GeneralFlowRequest.Success", 1L);
        } else {
            metricEventCreateMetricEvent.addLong("PearRecommendationUpdater.GeneralFlowRequest.Failure", 1L);
        }
        this.minervaMetrics.record(metricEventCreateMetricEvent, true);
    }
}
