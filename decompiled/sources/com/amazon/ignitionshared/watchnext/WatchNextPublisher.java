package com.amazon.ignitionshared.watchnext;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import androidx.annotation.RequiresApi;
import androidx.tvprovider.media.tv.PreviewChannelHelper;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.tvprovider.media.tv.WatchNextProgram;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.reporting.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 26)
@SuppressLint({"RestrictedApi"})
public final class WatchNextPublisher {

    @NotNull
    public static final String CLEAR_FAILURE_DELETION_FAILED_METRIC = "WatchNextPublisher.Clear.Failure.DeletionFailed";

    @NotNull
    public static final String CLEAR_FAILURE_FAILED_TO_RETRIEVE_ENTRIES_METRIC = "WatchNextPublisher.Clear.Failure.FailedToRetrieveEntries";

    @NotNull
    public static final String CLEAR_FAILURE_ILLEGAL_ARGUMENT_EXCEPTION_METRIC = "WatchNextPublisher.Clear.Failure.IllegalArgumentException";

    @NotNull
    public static final String CLEAR_SUCCESS_METRIC = "WatchNextPublisher.Clear.Success";

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "WatchNextPublisher";

    @NotNull
    public static final String UPSERT_FAILURE_DELETION_FAILED_METRIC = "WatchNextPublisher.Upsert.Failure.DeletionFailed";

    @NotNull
    public static final String UPSERT_FAILURE_ENTRY_REJECTED_METRIC = "WatchNextPublisher.Upsert.Failure.EntryRejected";

    @NotNull
    public static final String UPSERT_FAILURE_FAILED_TO_GET_ENTRIES_METRIC = "WatchNextPublisher.Upsert.Failure.FailedToGetEntries";

    @NotNull
    public static final String UPSERT_FAILURE_ILLEGAL_ARGUMENT_EXCEPTION_METRIC = "WatchNextPublisher.Upsert.Failure.IllegalArgumentException";

    @NotNull
    public static final String UPSERT_SUCCESS_METRIC = "WatchNextPublisher.Upsert.Success";

    @NotNull
    public final Context context;

    @NotNull
    public final WatchNextDatabaseAdapter db;

    @NotNull
    public final MinervaMetrics minervaMetrics;

    @NotNull
    public final PreviewChannelHelper previewChannelHelper;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public WatchNextPublisher(@ApplicationContext @NotNull Context context, @NotNull MinervaMetrics minervaMetrics, @NotNull WatchNextDatabaseAdapter db, @NotNull PreviewChannelHelper previewChannelHelper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(previewChannelHelper, "previewChannelHelper");
        this.context = context;
        this.minervaMetrics = minervaMetrics;
        this.db = db;
        this.previewChannelHelper = previewChannelHelper;
    }

    public final boolean areMatchingPrograms(WatchNextProgram watchNextProgram, WatchNextProgram watchNextProgram2) {
        return Intrinsics.areEqual(watchNextProgram.getTitle(), watchNextProgram2.getTitle()) && Intrinsics.areEqual(watchNextProgram.getIntentUri(), watchNextProgram2.getIntentUri()) && Intrinsics.areEqual(watchNextProgram.getDescription(), watchNextProgram2.getDescription()) && Intrinsics.areEqual(watchNextProgram.getPosterArtUri(), watchNextProgram2.getPosterArtUri()) && watchNextProgram.getWatchNextType() == watchNextProgram2.getWatchNextType() && watchNextProgram.getType() == watchNextProgram2.getType() && watchNextProgram.getLastEngagementTimeUtcMillis() == watchNextProgram2.getLastEngagementTimeUtcMillis() && watchNextProgram.getLastPlaybackPositionMillis() == watchNextProgram2.getLastPlaybackPositionMillis() && watchNextProgram.getDurationMillis() == watchNextProgram2.getDurationMillis();
    }

    public final boolean clearEntries() {
        boolean z;
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.WATCH_NEXT_PUBLISHER_SCHEMA_ID);
        long[] insertedProgramIds = this.db.getInsertedProgramIds();
        if (insertedProgramIds == null) {
            metricEventCreateMetricEvent.addLong(CLEAR_FAILURE_FAILED_TO_RETRIEVE_ENTRIES_METRIC, 1L);
            return false;
        }
        try {
            z = false;
            for (long j : insertedProgramIds) {
                if (this.context.getContentResolver().delete(ContentUris.withAppendedId(TvContractCompat.WatchNextPrograms.CONTENT_URI, j), null, null) != 1) {
                    Log.d(TAG, "Failed to delete entry during clear");
                    metricEventCreateMetricEvent.addLong(CLEAR_FAILURE_DELETION_FAILED_METRIC, 1L);
                    z = true;
                } else {
                    Log.d(TAG, "Entry cleared");
                    metricEventCreateMetricEvent.addLong(CLEAR_SUCCESS_METRIC, 1L);
                }
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Failed to clear", e);
            metricEventCreateMetricEvent.addLong(CLEAR_FAILURE_ILLEGAL_ARGUMENT_EXCEPTION_METRIC, 1L);
            z = true;
        }
        this.minervaMetrics.record(metricEventCreateMetricEvent, true);
        return !z;
    }

    public final boolean upsertEntries(@NotNull List<WatchNextProgram> watchNextEntries) {
        Intrinsics.checkNotNullParameter(watchNextEntries, "watchNextEntries");
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.WATCH_NEXT_PUBLISHER_SCHEMA_ID);
        Map<String, WatchNextProgram> contentIdToProgramsMapForInserted = this.db.getContentIdToProgramsMapForInserted();
        boolean z = false;
        if (contentIdToProgramsMapForInserted == null) {
            metricEventCreateMetricEvent.addLong(UPSERT_FAILURE_FAILED_TO_GET_ENTRIES_METRIC, 1L);
            this.minervaMetrics.record(metricEventCreateMetricEvent);
            return false;
        }
        try {
            for (WatchNextProgram watchNextProgram : watchNextEntries) {
                HashMap map = (HashMap) contentIdToProgramsMapForInserted;
                WatchNextProgram watchNextProgram2 = (WatchNextProgram) map.get(watchNextProgram.getContentId());
                if (watchNextProgram2 != null) {
                    if (!areMatchingPrograms(watchNextProgram2, watchNextProgram)) {
                        this.previewChannelHelper.updateWatchNextProgram(watchNextProgram, watchNextProgram2.getId());
                        Log.d(TAG, "Entry updated");
                        metricEventCreateMetricEvent.addLong(UPSERT_SUCCESS_METRIC, 1L);
                    }
                    map.remove(watchNextProgram.getContentId());
                } else if (this.previewChannelHelper.publishWatchNextProgram(watchNextProgram) != -1) {
                    Log.d(TAG, "Published entry");
                    metricEventCreateMetricEvent.addLong(UPSERT_SUCCESS_METRIC, 1L);
                } else {
                    Log.d(TAG, "Entry rejected");
                    metricEventCreateMetricEvent.addLong(UPSERT_FAILURE_ENTRY_REJECTED_METRIC, 1L);
                    z = true;
                }
            }
            Iterator it = ((HashMap) contentIdToProgramsMapForInserted).entrySet().iterator();
            while (it.hasNext()) {
                if (this.context.getContentResolver().delete(ContentUris.withAppendedId(TvContractCompat.WatchNextPrograms.CONTENT_URI, ((WatchNextProgram) ((Map.Entry) it.next()).getValue()).getId()), null, null) != 1) {
                    Log.d(TAG, "Failed to delete entry during update");
                    metricEventCreateMetricEvent.addLong(UPSERT_FAILURE_DELETION_FAILED_METRIC, 1L);
                    z = true;
                } else {
                    Log.d(TAG, "Entry deleted");
                    metricEventCreateMetricEvent.addLong(UPSERT_SUCCESS_METRIC, 1L);
                }
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Failed to upsert", e);
            metricEventCreateMetricEvent.addLong(UPSERT_FAILURE_ILLEGAL_ARGUMENT_EXCEPTION_METRIC, 1L);
            z = true;
        }
        this.minervaMetrics.record(metricEventCreateMetricEvent);
        return !z;
    }
}
