package com.amazon.ignitionshared.pear;

import com.amazon.livingroom.di.Names;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nPearResponseParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PearResponseParser.kt\ncom/amazon/ignitionshared/pear/PearResponseParser\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,32:1\n295#2,2:33\n*S KotlinDebug\n*F\n+ 1 PearResponseParser.kt\ncom/amazon/ignitionshared/pear/PearResponseParser\n*L\n21#1:33,2\n*E\n"})
public final class PearResponseParser {

    @NotNull
    public final String recommendationApplicationName;

    @Inject
    public PearResponseParser(@Named(Names.RECOMMENDATION_APPLICATION_KEY) @NotNull String recommendationApplicationName) {
        Intrinsics.checkNotNullParameter(recommendationApplicationName, "recommendationApplicationName");
        this.recommendationApplicationName = recommendationApplicationName;
    }

    @NotNull
    public final AppRecommendations parseResponse(@NotNull JsonObject response, @NotNull String minervaEventName, @NotNull MetricEvent pearMinervaMetricEvent) {
        Object next;
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(minervaEventName, "minervaEventName");
        Intrinsics.checkNotNullParameter(pearMinervaMetricEvent, "pearMinervaMetricEvent");
        PearResponseMessage pearResponseMessageOf = PearResponseMessage.Companion.of(response);
        if (pearResponseMessageOf.byapp.isEmpty()) {
            throw new IllegalArgumentException("No application keys found in PeAR response");
        }
        Iterator<T> it = pearResponseMessageOf.byapp.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((AppRecommendations) next).app, this.recommendationApplicationName)) {
                break;
            }
        }
        AppRecommendations appRecommendations = (AppRecommendations) next;
        if (appRecommendations == null) {
            throw new IllegalArgumentException("Application key not found in response");
        }
        if (!appRecommendations.feeds.recsV1.widgets.isEmpty()) {
            return appRecommendations;
        }
        pearMinervaMetricEvent.addLong(minervaEventName.concat(".ResponseError.EmptyAppRecommendations"), 1L);
        throw new IllegalArgumentException("No placement collection widgets returned in response");
    }
}
