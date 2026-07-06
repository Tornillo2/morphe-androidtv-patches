package com.amazon.ignitionshared.watchnext;

import android.os.Build;
import androidx.tvprovider.media.tv.WatchNextProgram;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.ignitionshared.pear.AppRecommendations;
import com.amazon.ignitionshared.pear.CollectionWidget;
import com.amazon.ignitionshared.pear.GroupWidget;
import com.amazon.ignitionshared.pear.PearPlacement;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.Names;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nWatchNextPlacement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WatchNextPlacement.kt\ncom/amazon/ignitionshared/watchnext/WatchNextPlacement\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,79:1\n774#2:80\n865#2,2:81\n1374#2:83\n1460#2,5:84\n*S KotlinDebug\n*F\n+ 1 WatchNextPlacement.kt\ncom/amazon/ignitionshared/watchnext/WatchNextPlacement\n*L\n68#1:80\n68#1:81,2\n70#1:83\n70#1:84,5\n*E\n"})
public final class WatchNextPlacement implements PearPlacement {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String WATCH_NEXT_PLACEMENT_CLEAR_METRIC = "WatchNextPlacement.Clear";

    @NotNull
    public static final String WATCH_NEXT_PLACEMENT_UPDATE_METRIC = "WatchNextPlacement.Update";

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final MinervaMetrics minervaMetrics;

    @NotNull
    public final PearWatchNextProgramBuilder pearWatchNextProgramBuilder;

    @NotNull
    public final Map<String, String> placementIdMap;

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
    public WatchNextPlacement(@NotNull WatchNextPublisher watchNextPublisher, @NotNull MinervaMetrics minervaMetrics, @Named(Names.PEAR_PLACEMENT_ID_MAP) @NotNull Map<String, String> placementIdMap, @NotNull PearWatchNextProgramBuilder pearWatchNextProgramBuilder, @NotNull DeviceProperties deviceProperties) {
        Intrinsics.checkNotNullParameter(watchNextPublisher, "watchNextPublisher");
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        Intrinsics.checkNotNullParameter(placementIdMap, "placementIdMap");
        Intrinsics.checkNotNullParameter(pearWatchNextProgramBuilder, "pearWatchNextProgramBuilder");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        this.watchNextPublisher = watchNextPublisher;
        this.minervaMetrics = minervaMetrics;
        this.placementIdMap = placementIdMap;
        this.pearWatchNextProgramBuilder = pearWatchNextProgramBuilder;
        this.deviceProperties = deviceProperties;
    }

    private final List<GroupWidget> extractGroupWidgetsForCurrentPlacement(AppRecommendations appRecommendations) {
        List<CollectionWidget> list = appRecommendations.feeds.recsV1.widgets;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (Intrinsics.areEqual(((CollectionWidget) obj).decorations.uiRowMapping.rowid, this.placementIdMap.get(Names.PEAR_WATCH_NEXT_PLACEMENT_KEY))) {
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
        if (Build.VERSION.SDK_INT >= 26 && ((Boolean) this.deviceProperties.get(DeviceProperties.PEAR_WATCH_NEXT)).booleanValue()) {
            if (!((Boolean) this.deviceProperties.get(DeviceProperties.IS_WATCH_NEXT_ENABLED)).booleanValue()) {
                this.watchNextPublisher.clearEntries();
                return;
            }
            MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.WATCH_NEXT_PLACEMENT_SCHEMA_ID);
            List<WatchNextProgram> listBuildWatchNextProgramList = this.pearWatchNextProgramBuilder.buildWatchNextProgramList(extractGroupWidgetsForCurrentPlacement(appRecommendations), metricEventCreateMetricEvent);
            if (((ArrayList) listBuildWatchNextProgramList).isEmpty()) {
                recordWatchNextMetric(metricEventCreateMetricEvent, WATCH_NEXT_PLACEMENT_CLEAR_METRIC, this.watchNextPublisher.clearEntries());
            } else {
                recordWatchNextMetric(metricEventCreateMetricEvent, WATCH_NEXT_PLACEMENT_UPDATE_METRIC, this.watchNextPublisher.upsertEntries(listBuildWatchNextProgramList));
            }
        }
    }

    public final void recordWatchNextMetric(MetricEvent metricEvent, String str, boolean z) {
        if (z) {
            metricEvent.addLong(str, 1L);
        } else {
            metricEvent.addLong(str, 0L);
        }
        this.minervaMetrics.record(metricEvent);
    }
}
