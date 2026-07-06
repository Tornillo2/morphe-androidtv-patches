package com.amazon.ignitionshared.recommendation;

import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.ignitionshared.pear.AppRecommendations;
import com.amazon.ignitionshared.pear.CollectionWidget;
import com.amazon.ignitionshared.pear.GroupWidget;
import com.amazon.ignitionshared.pear.PearPlacement;
import com.amazon.ignitionshared.pear.TitleItemWidget;
import com.amazon.ignitionshared.pear.TitleItemWidgetDecorations;
import com.amazon.ignitionshared.pear.VisualItem;
import com.amazon.ignitionshared.recommendation.model.Recommendation;
import com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher;
import com.amazon.livingroom.di.Names;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nPersonalisedRecommendationPlacement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PersonalisedRecommendationPlacement.kt\ncom/amazon/ignitionshared/recommendation/PersonalisedRecommendationPlacement\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,78:1\n1869#2:79\n1878#2,2:80\n295#2,2:82\n1880#2:84\n1870#2:85\n774#2:86\n865#2,2:87\n1374#2:89\n1460#2,5:90\n*S KotlinDebug\n*F\n+ 1 PersonalisedRecommendationPlacement.kt\ncom/amazon/ignitionshared/recommendation/PersonalisedRecommendationPlacement\n*L\n27#1:79\n28#1:80,2\n29#1:82,2\n28#1:84\n27#1:85\n65#1:86\n65#1:87,2\n68#1:89\n68#1:90,5\n*E\n"})
public final class PersonalisedRecommendationPlacement implements PearPlacement {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String MISSING_COVER_IMAGE_METRIC = "PersonalisedRecommendationPlacement.Error.MissingCoverImage";

    @NotNull
    public static final String PARSED_NOT_PUBLISHED_METRIC = "PersonalisedRecommendationPlacement.ParsedNotPublished";

    @NotNull
    public static final String PUBLISH_METRIC = "PersonalisedRecommendationPlacement.Publish";

    @NotNull
    public final MinervaMetrics minervaMetrics;

    @NotNull
    public final Map<String, String> placementIdMap;

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
    public PersonalisedRecommendationPlacement(@NotNull RecommendationPublisher recommendationPublisher, @NotNull MinervaMetrics minervaMetrics, @Named(Names.PEAR_PLACEMENT_ID_MAP) @NotNull Map<String, String> placementIdMap) {
        Intrinsics.checkNotNullParameter(recommendationPublisher, "recommendationPublisher");
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        Intrinsics.checkNotNullParameter(placementIdMap, "placementIdMap");
        this.recommendationPublisher = recommendationPublisher;
        this.minervaMetrics = minervaMetrics;
        this.placementIdMap = placementIdMap;
    }

    public final List<GroupWidget> extractGroupWidgetsForCurrentPlacement(AppRecommendations appRecommendations) {
        List<CollectionWidget> list = appRecommendations.feeds.recsV1.widgets;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (Intrinsics.areEqual(((CollectionWidget) obj).decorations.uiRowMapping.rowid, this.placementIdMap.get(Names.PEAR_PERSONALISED_RECOMMENDATIONS_PLACEMENT_KEY))) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, ((CollectionWidget) obj2).widgetlist);
        }
        return arrayList2;
    }

    @Override // com.amazon.ignitionshared.pear.PearPlacement
    public void publishPlacement(@NotNull AppRecommendations appRecommendations) {
        Intrinsics.checkNotNullParameter(appRecommendations, "appRecommendations");
        ArrayList arrayList = new ArrayList();
        List<GroupWidget> listExtractGroupWidgetsForCurrentPlacement = extractGroupWidgetsForCurrentPlacement(appRecommendations);
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.PERSONALISED_RECOMMENDATION_PLACEMENT_SCHEMA_ID);
        ArrayList arrayList2 = (ArrayList) listExtractGroupWidgetsForCurrentPlacement;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            GroupWidget groupWidget = (GroupWidget) obj;
            int i2 = 0;
            for (Object obj2 : groupWidget.itemslist) {
                int i3 = i2 + 1;
                Object obj3 = null;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                TitleItemWidget titleItemWidget = (TitleItemWidget) obj2;
                Iterator<T> it = titleItemWidget.decorations.visuals.items.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (Intrinsics.areEqual(((VisualItem) next).type, "cover")) {
                        obj3 = next;
                        break;
                    }
                }
                VisualItem visualItem = (VisualItem) obj3;
                if (visualItem != null) {
                    String str = titleItemWidget.title;
                    String str2 = visualItem.url;
                    TitleItemWidgetDecorations titleItemWidgetDecorations = titleItemWidget.decorations;
                    String str3 = titleItemWidgetDecorations.deeplink.nativeV2;
                    arrayList.add(new Recommendation(str, titleItemWidgetDecorations.description, groupWidget.title, str2, str2, str3, i2, 0));
                } else {
                    metricEventCreateMetricEvent.addLong(MISSING_COVER_IMAGE_METRIC, 1L);
                }
                i2 = i3;
            }
        }
        this.recommendationPublisher.clearRecommendations();
        recordRecommendationMetric(metricEventCreateMetricEvent, this.recommendationPublisher.publishRecommendations(arrayList));
    }

    public final void recordRecommendationMetric(MetricEvent metricEvent, boolean z) {
        if (z) {
            metricEvent.addLong(PUBLISH_METRIC, 1L);
        } else {
            metricEvent.addLong(PUBLISH_METRIC, 0L);
            metricEvent.addLong(PARSED_NOT_PUBLISHED_METRIC, 1L);
        }
        this.minervaMetrics.record(metricEvent, true);
    }
}
