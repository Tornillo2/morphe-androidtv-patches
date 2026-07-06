package com.amazon.ignitionshared.pear;

import android.os.Build;
import com.amazon.ignitionshared.GMBMessageProcessor;
import com.amazon.ignitionshared.GMBMessageSender;
import com.amazon.ignitionshared.ServiceInitializer;
import com.amazon.ignitionshared.recommendation.contentprovider.RequestStructureContentProviderManager;
import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
import com.amazon.ignitionshared.watchnext.WatchNextPublisher;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class PearRecommendationServiceInitializer implements ServiceInitializer {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String PEAR_GMB_RESPONSE_MESSAGE_PAYLOAD = "{\"status\" : \"success\", \"message\" : \"\"}";

    @NotNull
    public static final String RECOMMENDATION_PARAMETERS_UPDATE_MESSAGE_TYPE = "gmb.ode.personalized_parameter_refresh.request";

    @NotNull
    public static final String RECOMMENDATION_PARAMETERS_UPDATE_RESPONSE_MESSAGE_TYPE = "gmb.ode.personalized_parameter_refresh.response";

    @NotNull
    public static final String RECOMMENDATION_REFRESH_REQUEST_MESSAGE_TYPE = "gmb.ode.personalization_refresh_hint.request";

    @NotNull
    public static final String RECOMMENDATION_REFRESH_REQUEST_RESPONSE_MESSAGE_TYPE = "gmb.ode.personalization_refresh_hint.response";

    @NotNull
    public final GMBMessageProcessor gmbMessageProcessor;

    @NotNull
    public final GMBMessageSender gmbMessageSender;

    @NotNull
    public final PearAccessTokenProvider pearAccessTokenProvider;

    @NotNull
    public final PearUpdateStructure pearUpdateStructure;

    @NotNull
    public final RecommendationHandler recommendationHandler;

    @NotNull
    public final RequestStructureContentProviderManager requestStructureProviderManager;

    @NotNull
    public final WatchNextPublisher watchNextPublisher;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public PearRecommendationServiceInitializer(@NotNull GMBMessageProcessor gmbMessageProcessor, @NotNull GMBMessageSender gmbMessageSender, @NotNull PearUpdateStructure pearUpdateStructure, @NotNull RecommendationHandler recommendationHandler, @NotNull PearAccessTokenProvider pearAccessTokenProvider, @NotNull WatchNextPublisher watchNextPublisher, @NotNull RequestStructureContentProviderManager requestStructureProviderManager) {
        Intrinsics.checkNotNullParameter(gmbMessageProcessor, "gmbMessageProcessor");
        Intrinsics.checkNotNullParameter(gmbMessageSender, "gmbMessageSender");
        Intrinsics.checkNotNullParameter(pearUpdateStructure, "pearUpdateStructure");
        Intrinsics.checkNotNullParameter(recommendationHandler, "recommendationHandler");
        Intrinsics.checkNotNullParameter(pearAccessTokenProvider, "pearAccessTokenProvider");
        Intrinsics.checkNotNullParameter(watchNextPublisher, "watchNextPublisher");
        Intrinsics.checkNotNullParameter(requestStructureProviderManager, "requestStructureProviderManager");
        this.gmbMessageProcessor = gmbMessageProcessor;
        this.gmbMessageSender = gmbMessageSender;
        this.pearUpdateStructure = pearUpdateStructure;
        this.recommendationHandler = recommendationHandler;
        this.pearAccessTokenProvider = pearAccessTokenProvider;
        this.watchNextPublisher = watchNextPublisher;
        this.requestStructureProviderManager = requestStructureProviderManager;
    }

    public static final void initialize$lambda$2$lambda$0(PearRecommendationServiceInitializer pearRecommendationServiceInitializer, String str) {
        GMBMessageSender.sendGMBMessageToClient$default(pearRecommendationServiceInitializer.gmbMessageSender, RECOMMENDATION_REFRESH_REQUEST_RESPONSE_MESSAGE_TYPE, PEAR_GMB_RESPONSE_MESSAGE_PAYLOAD, 0L, 4, null);
        pearRecommendationServiceInitializer.recommendationHandler.updateRecommendations();
        pearRecommendationServiceInitializer.requestStructureProviderManager.updateRequestStructure();
    }

    public static final void initialize$lambda$2$lambda$1(PearRecommendationServiceInitializer pearRecommendationServiceInitializer, String str) {
        GMBMessageSender.sendGMBMessageToClient$default(pearRecommendationServiceInitializer.gmbMessageSender, RECOMMENDATION_PARAMETERS_UPDATE_RESPONSE_MESSAGE_TYPE, PEAR_GMB_RESPONSE_MESSAGE_PAYLOAD, 0L, 4, null);
        PearUpdateStructure pearUpdateStructure = pearRecommendationServiceInitializer.pearUpdateStructure;
        Intrinsics.checkNotNull(str);
        pearUpdateStructure.updateStoredPearMessage(str);
        if (Build.VERSION.SDK_INT >= 26) {
            pearRecommendationServiceInitializer.watchNextPublisher.clearEntries();
        }
        pearRecommendationServiceInitializer.pearAccessTokenProvider.clearAccessToken();
        pearRecommendationServiceInitializer.recommendationHandler.updateRecommendations();
        pearRecommendationServiceInitializer.requestStructureProviderManager.updateRequestStructure();
    }

    @Override // com.amazon.ignitionshared.ServiceInitializer
    public void initialize() {
        GMBMessageProcessor gMBMessageProcessor = this.gmbMessageProcessor;
        gMBMessageProcessor.subscribeMessageHandler(RECOMMENDATION_REFRESH_REQUEST_MESSAGE_TYPE, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.ignitionshared.pear.PearRecommendationServiceInitializer$$ExternalSyntheticLambda0
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                PearRecommendationServiceInitializer.initialize$lambda$2$lambda$0(this.f$0, str);
            }
        });
        gMBMessageProcessor.subscribeMessageHandler(RECOMMENDATION_PARAMETERS_UPDATE_MESSAGE_TYPE, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.ignitionshared.pear.PearRecommendationServiceInitializer$$ExternalSyntheticLambda1
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                PearRecommendationServiceInitializer.initialize$lambda$2$lambda$1(this.f$0, str);
            }
        });
    }
}
