package com.amazon.ignitionshared.watchnext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import androidx.annotation.RequiresApi;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.tvprovider.media.tv.WatchNextProgram;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.reporting.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 26)
@SourceDebugExtension({"SMAP\nWatchNextParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WatchNextParser.kt\ncom/amazon/ignitionshared/watchnext/WatchNextParser\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,101:1\n29#2:102\n29#2:103\n*S KotlinDebug\n*F\n+ 1 WatchNextParser.kt\ncom/amazon/ignitionshared/watchnext/WatchNextParser\n*L\n73#1:102\n74#1:103\n*E\n"})
public final class WatchNextParser {

    @NotNull
    public static final String FAILURE_RESPONSE_NOT_ARRAY_METRIC = "WatchNextParser.ParseWatchNext.Failure.ResponseNotArray";

    @NotNull
    public static final String NO_VALID_ENTRY_IN_RESPONSE_METRIC = "WatchNextParser.ParseWatchNext.Failure.NoValidEntryInResponse";

    @NotNull
    public static final String SUCCESS_METRIC = "WatchNextParser.ParseWatchNext.Success";
    public static final String TAG = "WatchNextParser";

    @NotNull
    public static final String WARNING_INVALID_ENTRY_PARSING_FAILED_METRIC = "WatchNextParser.ParseWatchNext.Success";

    @NotNull
    public final Context context;

    @NotNull
    public final MinervaMetrics minervaMetrics;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Map<String, Integer> contentTypeMap = WatchNextUtilsKt.generateContentTypeMap();

    @NotNull
    public static final Map<String, Integer> watchNextProgramTypeMap = WatchNextUtilsKt.generateWatchNextProgramTypeMap();
    public static final Map<String, String> appUrls = Collections.singletonMap("com.imdbtv.livingroom.IMDbTvApplication", "imdbtv://3plr/watch?gti=");

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public WatchNextParser(@ApplicationContext @NotNull Context context, @NotNull MinervaMetrics minervaMetrics) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        this.context = context;
        this.minervaMetrics = minervaMetrics;
    }

    @SuppressLint({"RestrictedApi"})
    public final WatchNextProgram parseIndividualProgram(JSONObject jSONObject) throws JSONException {
        WatchNextProgram.Builder builder = new WatchNextProgram.Builder();
        Integer num = contentTypeMap.get(jSONObject.getString("mediaType"));
        Intrinsics.checkNotNull(num);
        builder.setType(num.intValue());
        Map<String, Integer> map = watchNextProgramTypeMap;
        Integer num2 = map.get(jSONObject.getString("watchNextType"));
        Intrinsics.checkNotNull(num2);
        builder.setWatchNextType(num2.intValue());
        builder.setLastEngagementTimeUtcMillis(jSONObject.getLong("lastEngagementTimeInUtcMs"));
        builder.setTitle(jSONObject.getString("title"));
        builder.setDescription(jSONObject.getString(TvContractCompat.Channels.COLUMN_DESCRIPTION));
        String string = jSONObject.getString("imageUrl");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        builder.setPosterArtUri(Uri.parse(string));
        String str = appUrls.get(this.context.getApplicationInfo().name);
        builder.setIntentUri(Uri.parse(((Object) str) + jSONObject.getString("contentId")));
        builder.setDurationMillis(jSONObject.getInt("totalTimeInMs"));
        builder.setContentId(jSONObject.getString("contentId"));
        Integer num3 = map.get(jSONObject.getString("watchNextType"));
        if (num3 != null && num3.intValue() == 0) {
            builder.setLastPlaybackPositionMillis(jSONObject.getInt("elapsedTimeInMs"));
        }
        WatchNextProgram watchNextProgram = new WatchNextProgram();
        watchNextProgram.mValues = builder.mValues;
        return watchNextProgram;
    }

    @NotNull
    public final List<WatchNextProgram> parseWatchNextMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.WATCH_NEXT_PARSER_SCHEMA_ID);
        try {
            JSONArray jSONArray = new JSONArray(message);
            ArrayList arrayList = new ArrayList(jSONArray.length());
            int length = jSONArray.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                    arrayList.add(parseIndividualProgram(jSONObject));
                } catch (JSONException unused) {
                    i++;
                }
            }
            if (i != 0) {
                Log.e(TAG, "Failure parsing events in WatchNext message: ".concat(message));
                metricEventCreateMetricEvent.addLong("WatchNextParser.ParseWatchNext.Success", i);
            }
            if (arrayList.isEmpty()) {
                metricEventCreateMetricEvent.addLong(NO_VALID_ENTRY_IN_RESPONSE_METRIC, 1L);
            } else {
                metricEventCreateMetricEvent.addLong("WatchNextParser.ParseWatchNext.Success", 1L);
            }
            this.minervaMetrics.record(metricEventCreateMetricEvent);
            return arrayList;
        } catch (JSONException unused2) {
            metricEventCreateMetricEvent.addLong(FAILURE_RESPONSE_NOT_ARRAY_METRIC, 1L);
            this.minervaMetrics.record(metricEventCreateMetricEvent);
            return new ArrayList();
        }
    }
}
