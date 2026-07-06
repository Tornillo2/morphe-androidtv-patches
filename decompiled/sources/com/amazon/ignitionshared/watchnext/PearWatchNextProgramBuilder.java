package com.amazon.ignitionshared.watchnext;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.annotation.RequiresApi;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.tvprovider.media.tv.WatchNextProgram;
import com.amazon.ignitionshared.pear.Catalog;
import com.amazon.ignitionshared.pear.GroupWidget;
import com.amazon.ignitionshared.pear.TitleItemWidget;
import com.amazon.ignitionshared.pear.TitleItemWidgetDecorations;
import com.amazon.ignitionshared.pear.VisualItem;
import com.amazon.ignitionshared.pear.WatchState;
import com.amazon.ignitionshared.pear.WatchStateEpisode;
import com.amazon.ignitionshared.pear.WatchStateEpisodeStarted;
import com.amazon.ignitionshared.pear.WatchStateMovieStarted;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.reporting.Log;
import j$.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(26)
@SourceDebugExtension({"SMAP\nPearWatchNextProgramBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PearWatchNextProgramBuilder.kt\ncom/amazon/ignitionshared/watchnext/PearWatchNextProgramBuilder\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,139:1\n1617#2,9:140\n1869#2:149\n1870#2:151\n1626#2:152\n1374#2:153\n1460#2,5:154\n774#2:159\n865#2,2:160\n295#2,2:162\n1#3:150\n29#4:164\n29#4:165\n*S KotlinDebug\n*F\n+ 1 PearWatchNextProgramBuilder.kt\ncom/amazon/ignitionshared/watchnext/PearWatchNextProgramBuilder\n*L\n26#1:140,9\n26#1:149\n26#1:151\n26#1:152\n41#1:153\n41#1:154,5\n43#1:159\n43#1:160,2\n74#1:162,2\n26#1:150\n100#1:164\n101#1:165\n*E\n"})
public final class PearWatchNextProgramBuilder {

    @NotNull
    public static final String CATALOG_TYPE_ERROR_METRIC = "WatchNextPlacement.CatalogTypeError";

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String MISSING_COVER_IMAGE_METRIC = "WatchNextPlacement.Error.MissingCoverImage";

    @NotNull
    public static final String MISSING_LAST_ENGAGEMENT_TIME_METRIC = "WatchNextPlacement.MissingLastEngagementTime";

    @NotNull
    public static final String MISSING_WATCH_STATE_METRIC = "WatchNextPlacement.MissingWatchState";
    public static final String TAG = "PearWatchNextProgramBuilder";

    @NotNull
    public static final String WATCH_NEXT_TYPE_ERROR_METRIC = "WatchNextPlacement.WatchNextTypeError";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public PearWatchNextProgramBuilder() {
    }

    @SuppressLint({"RestrictedApi"})
    public final WatchNextProgram buildWatchNextProgram(TitleItemWidget titleItemWidget, MetricEvent metricEvent, Map<String, Integer> map, Map<String, Integer> map2) {
        Object next;
        TitleItemWidgetDecorations titleItemWidgetDecorations = titleItemWidget.decorations;
        if (titleItemWidgetDecorations.watchState == null) {
            metricEvent.addLong(MISSING_WATCH_STATE_METRIC, 1L);
            throw new IllegalArgumentException("Watch State field missing");
        }
        Integer num = map.get(titleItemWidgetDecorations.catalogType);
        Integer num2 = map2.get(titleItemWidget.decorations.watchState.getWatchNextType());
        if (num == null) {
            metricEvent.addLong(CATALOG_TYPE_ERROR_METRIC, 1L);
            throw new IllegalArgumentException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Catalog Type ", titleItemWidget.decorations.catalogType, " not supported"));
        }
        if (num2 == null) {
            metricEvent.addLong(WATCH_NEXT_TYPE_ERROR_METRIC, 1L);
            throw new IllegalArgumentException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Watch Next Type ", titleItemWidget.decorations.watchState.getWatchNextType(), " not supported"));
        }
        if (titleItemWidget.decorations.watchState.getLastEngagementTime() == null) {
            metricEvent.addLong(MISSING_LAST_ENGAGEMENT_TIME_METRIC, 1L);
            throw new IllegalArgumentException("LastEngagementTime field missing");
        }
        Iterator<T> it = titleItemWidget.decorations.visuals.items.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((VisualItem) next).type, "cover")) {
                break;
            }
        }
        VisualItem visualItem = (VisualItem) next;
        if (visualItem == null) {
            metricEvent.addLong(MISSING_COVER_IMAGE_METRIC, 1L);
            throw new IllegalArgumentException("Missing cover image for watchNext item");
        }
        WatchNextProgram.Builder builder = new WatchNextProgram.Builder();
        if (Intrinsics.areEqual(titleItemWidget.decorations.catalogType, "SEASON")) {
            builder.setType(3);
        } else {
            builder.mValues.put("type", num);
        }
        builder.mValues.put(TvContractCompat.WatchNextPrograms.COLUMN_WATCH_NEXT_TYPE, num2);
        builder.setLastEngagementTimeUtcMillis(Instant.parse(titleItemWidget.decorations.watchState.getLastEngagementTime()).toEpochMilli());
        Catalog catalog = titleItemWidget.decorations.catalog;
        if ((catalog != null ? catalog.title : null) == null || catalog.seasonNumber == null) {
            builder.setTitle(titleItemWidget.title);
        } else {
            builder.setTitle(catalog.title);
            builder.setSeasonNumber(catalog.seasonNumber.intValue());
        }
        builder.setDescription(titleItemWidget.decorations.description);
        builder.setPosterArtUri(Uri.parse(visualItem.url));
        builder.setIntentUri(Uri.parse(titleItemWidget.decorations.deeplink.f5android));
        Integer totalTimeSeconds = titleItemWidget.decorations.watchState.getTotalTimeSeconds();
        if (totalTimeSeconds != null) {
            builder.setDurationMillis(totalTimeSeconds.intValue() * 1000);
        }
        builder.setContentId(titleItemWidget.decorations.watchState.getFocusId().value);
        WatchState watchState = titleItemWidget.decorations.watchState;
        if (watchState instanceof WatchStateEpisode) {
            builder.setEpisodeNumber(((WatchStateEpisode) watchState).episodeNumber);
        } else if (watchState instanceof WatchStateEpisodeStarted) {
            builder.setEpisodeNumber(((WatchStateEpisodeStarted) watchState).episodeNumber);
        }
        Integer num3 = map2.get(titleItemWidget.decorations.watchState.getWatchNextType());
        if (num3 != null && num3.intValue() == 0) {
            WatchState watchState2 = titleItemWidget.decorations.watchState;
            if (watchState2 instanceof WatchStateEpisodeStarted) {
                builder.setLastPlaybackPositionMillis(((WatchStateEpisodeStarted) watchState2).bookmarkTimeSeconds * 1000);
            } else if (watchState2 instanceof WatchStateMovieStarted) {
                builder.setLastPlaybackPositionMillis(((WatchStateMovieStarted) watchState2).bookmarkTimeSeconds * 1000);
            }
        }
        WatchNextProgram watchNextProgram = new WatchNextProgram();
        watchNextProgram.mValues = builder.mValues;
        return watchNextProgram;
    }

    @NotNull
    public final List<WatchNextProgram> buildWatchNextProgramList(@NotNull List<GroupWidget> watchNextGroupWidgets, @NotNull MetricEvent watchNextMinervaMetricEvent) {
        WatchNextProgram watchNextProgramBuildWatchNextProgram;
        Intrinsics.checkNotNullParameter(watchNextGroupWidgets, "watchNextGroupWidgets");
        Intrinsics.checkNotNullParameter(watchNextMinervaMetricEvent, "watchNextMinervaMetricEvent");
        List<TitleItemWidget> listExtractEntitledTitleItemWidgets = extractEntitledTitleItemWidgets(watchNextGroupWidgets);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listExtractEntitledTitleItemWidgets.iterator();
        while (it.hasNext()) {
            try {
                watchNextProgramBuildWatchNextProgram = buildWatchNextProgram((TitleItemWidget) it.next(), watchNextMinervaMetricEvent, WatchNextUtilsKt.generateContentTypeMap(), WatchNextUtilsKt.generateWatchNextProgramTypeMap());
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "IllegalArgument Exception while building Watch Next Program", e);
                watchNextProgramBuildWatchNextProgram = null;
            }
            if (watchNextProgramBuildWatchNextProgram != null) {
                arrayList.add(watchNextProgramBuildWatchNextProgram);
            }
        }
        return arrayList;
    }

    public final List<TitleItemWidget> extractEntitledTitleItemWidgets(List<GroupWidget> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, ((GroupWidget) it.next()).itemslist);
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            Boolean bool = ((TitleItemWidget) obj).decorations.entitlement;
            if (bool != null && bool.booleanValue()) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }
}
