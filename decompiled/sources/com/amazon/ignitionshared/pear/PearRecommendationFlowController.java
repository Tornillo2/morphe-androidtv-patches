package com.amazon.ignitionshared.pear;

import com.amazon.ignitionshared.recommendation.dispacher.RecommendationRequestDispatcher;
import com.android.volley.toolbox.RequestFuture;
import com.google.common.net.HttpHeaders;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Pair;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PearRecommendationFlowController {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String RECOMMENDATION_REQUEST_ACCESS_TOKEN_PLACEHOLDER = "$.credentials.oauth-2.0-v1.access_token.latest";

    @NotNull
    public final PearAccessTokenProvider pearAccessTokenProvider;

    @NotNull
    public final PearUpdateStructure pearUpdateStructure;

    @NotNull
    public final PearUriBuilder pearUriBuilder;

    @NotNull
    public final RecommendationRequestDispatcher recommendationRequestDispatcher;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public PearRecommendationFlowController(@NotNull RecommendationRequestDispatcher recommendationRequestDispatcher, @NotNull PearUpdateStructure pearUpdateStructure, @NotNull PearAccessTokenProvider pearAccessTokenProvider, @NotNull PearUriBuilder pearUriBuilder) {
        Intrinsics.checkNotNullParameter(recommendationRequestDispatcher, "recommendationRequestDispatcher");
        Intrinsics.checkNotNullParameter(pearUpdateStructure, "pearUpdateStructure");
        Intrinsics.checkNotNullParameter(pearAccessTokenProvider, "pearAccessTokenProvider");
        Intrinsics.checkNotNullParameter(pearUriBuilder, "pearUriBuilder");
        this.recommendationRequestDispatcher = recommendationRequestDispatcher;
        this.pearUpdateStructure = pearUpdateStructure;
        this.pearAccessTokenProvider = pearAccessTokenProvider;
        this.pearUriBuilder = pearUriBuilder;
    }

    @NotNull
    public final RequestFuture<JsonObject> beginErrorPlacementsFlow(@NotNull PearParameterUpdateMessage parameterUpdateMessage) {
        Intrinsics.checkNotNullParameter(parameterUpdateMessage, "parameterUpdateMessage");
        return dispatchPlacementsRequest(parameterUpdateMessage.update.recsV1.error.requestV1);
    }

    @NotNull
    public final RequestFuture<JsonObject> beginPlacementsFlow(@NotNull PearParameterUpdateMessage parameterUpdateMessage) {
        Intrinsics.checkNotNullParameter(parameterUpdateMessage, "parameterUpdateMessage");
        return dispatchPlacementsRequest(parameterUpdateMessage.update.recsV1.request.requestV1);
    }

    public final RequestFuture<JsonObject> dispatchPlacementsRequest(HttpRequestV1 httpRequestV1) {
        String accessToken;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, JsonElement> entry : httpRequestV1.headers.content.entrySet()) {
            if (!Intrinsics.areEqual(entry.getKey(), HttpHeaders.AUTHORIZATION)) {
                String key = entry.getKey();
                JsonElement value = entry.getValue();
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlinx.serialization.json.JsonPrimitive");
                linkedHashMap.put(key, ((JsonPrimitive) value).getContent());
            } else if (this.pearUpdateStructure.getCredentials() != null && (accessToken = this.pearAccessTokenProvider.getAccessToken()) != null) {
                String key2 = entry.getKey();
                JsonElement value2 = entry.getValue();
                Intrinsics.checkNotNull(value2, "null cannot be cast to non-null type kotlinx.serialization.json.JsonPrimitive");
                linkedHashMap.put(key2, tryReplaceAccessToken(((JsonPrimitive) value2).getContent(), accessToken));
            }
        }
        return this.recommendationRequestDispatcher.dispatch(this.pearUriBuilder.buildPearDynamicUri(httpRequestV1), linkedHashMap, httpRequestV1.body, httpRequestV1.getVerb());
    }

    public final String tryReplaceAccessToken(String str, String str2) {
        if (StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) RECOMMENDATION_REQUEST_ACCESS_TOKEN_PLACEHOLDER, false, 2, (Object) null)) {
            return StringsKt__StringsJVMKt.replace$default(str, RECOMMENDATION_REQUEST_ACCESS_TOKEN_PLACEHOLDER, str2, false, 4, (Object) null);
        }
        throw new IllegalArgumentException("Stored update structure does not contain expected placeholder for access token header");
    }

    @NotNull
    public final RequestFuture<JsonObject> updateRecommendationsUsingBootstrapPlacements() {
        return RecommendationRequestDispatcher.dispatch$default(this.recommendationRequestDispatcher, this.pearUriBuilder.buildPearBootstrapUri(), MapsKt__MapsJVMKt.mapOf(new Pair("x-gasc-enabled", "true")), null, 0, 12, null);
    }
}
