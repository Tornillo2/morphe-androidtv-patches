package com.amazon.ignitionshared.recommendation.dispacher;

import com.amazon.ignitionshared.networking.JsonRequestWithHeaders;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RequestFuture;
import java.util.Map;
import javax.inject.Inject;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RecommendationRequestDispatcher {
    public static final float BACKOFF_MULTIPLIER = 2.0f;

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int MAX_NUMBER_OF_RETRIES = 3;
    public static final int TIME_OUT_IN_MILLISECONDS = 4000;

    @NotNull
    public final RequestQueue requestQueue;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public RecommendationRequestDispatcher(@NotNull RequestQueue requestQueue) {
        Intrinsics.checkNotNullParameter(requestQueue, "requestQueue");
        this.requestQueue = requestQueue;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RequestFuture dispatch$default(RecommendationRequestDispatcher recommendationRequestDispatcher, String str, Map map, JsonObject jsonObject, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            map = MapsKt__MapsKt.emptyMap();
        }
        if ((i2 & 4) != 0) {
            jsonObject = null;
        }
        if ((i2 & 8) != 0) {
            i = 0;
        }
        return recommendationRequestDispatcher.dispatch(str, map, jsonObject, i);
    }

    @NotNull
    public final RequestFuture<JsonObject> dispatch(@NotNull String url, @NotNull Map<String, String> headers, @Nullable JsonObject jsonObject, int i) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        RequestFuture<JsonObject> requestFuture = new RequestFuture<>();
        JsonRequestWithHeaders jsonRequestWithHeaders = new JsonRequestWithHeaders(i, url, jsonObject, requestFuture, requestFuture, headers);
        jsonRequestWithHeaders.mRetryPolicy = new DefaultRetryPolicy(4000, 3, 2.0f);
        this.requestQueue.add(jsonRequestWithHeaders);
        return requestFuture;
    }
}
