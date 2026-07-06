package androidx.media3.exoplayer.upstream;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class CmcdData {
    public static final Joiner COMMA_JOINER = new Joiner(",");
    public final CmcdObject cmcdObject;
    public final CmcdRequest cmcdRequest;
    public final CmcdSession cmcdSession;
    public final CmcdStatus cmcdStatus;
    public final int dataTransmissionMode;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CmcdObject {
        public final int bitrateKbps;
        public final ImmutableList<String> customDataList;
        public final long objectDurationMs;

        @Nullable
        public final String objectType;
        public final int topBitrateKbps;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {

            @Nullable
            public String objectType;
            public int bitrateKbps = -2147483647;
            public int topBitrateKbps = -2147483647;
            public long objectDurationMs = -9223372036854775807L;
            public ImmutableList<String> customDataList = ImmutableList.of();

            public CmcdObject build() {
                return new CmcdObject(this);
            }

            @CanIgnoreReturnValue
            public Builder setBitrateKbps(int i) {
                Assertions.checkArgument(i >= 0 || i == -2147483647);
                this.bitrateKbps = i;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setCustomDataList(List<String> list) {
                this.customDataList = ImmutableList.copyOf((Collection) list);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setObjectDurationMs(long j) {
                Assertions.checkArgument(j >= 0 || j == -9223372036854775807L);
                this.objectDurationMs = j;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setObjectType(@Nullable String str) {
                this.objectType = str;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setTopBitrateKbps(int i) {
                Assertions.checkArgument(i >= 0 || i == -2147483647);
                this.topBitrateKbps = i;
                return this;
            }
        }

        public void populateCmcdDataMap(ArrayListMultimap<String, String> arrayListMultimap) {
            ArrayList arrayList = new ArrayList();
            if (this.bitrateKbps != -2147483647) {
                arrayList.add("br=" + this.bitrateKbps);
            }
            if (this.topBitrateKbps != -2147483647) {
                arrayList.add("tb=" + this.topBitrateKbps);
            }
            if (this.objectDurationMs != -9223372036854775807L) {
                arrayList.add("d=" + this.objectDurationMs);
            }
            if (!TextUtils.isEmpty(this.objectType)) {
                arrayList.add("ot=" + this.objectType);
            }
            arrayList.addAll(this.customDataList);
            if (arrayList.isEmpty()) {
                return;
            }
            arrayListMultimap.putAll(CmcdConfiguration.KEY_CMCD_OBJECT, arrayList);
        }

        public CmcdObject(Builder builder) {
            this.bitrateKbps = builder.bitrateKbps;
            this.topBitrateKbps = builder.topBitrateKbps;
            this.objectDurationMs = builder.objectDurationMs;
            this.objectType = builder.objectType;
            this.customDataList = builder.customDataList;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CmcdRequest {
        public final long bufferLengthMs;
        public final ImmutableList<String> customDataList;
        public final long deadlineMs;
        public final long measuredThroughputInKbps;

        @Nullable
        public final String nextObjectRequest;

        @Nullable
        public final String nextRangeRequest;
        public final boolean startup;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {

            @Nullable
            public String nextObjectRequest;

            @Nullable
            public String nextRangeRequest;
            public boolean startup;
            public long bufferLengthMs = -9223372036854775807L;
            public long measuredThroughputInKbps = -2147483647L;
            public long deadlineMs = -9223372036854775807L;
            public ImmutableList<String> customDataList = ImmutableList.of();

            public CmcdRequest build() {
                return new CmcdRequest(this);
            }

            @CanIgnoreReturnValue
            public Builder setBufferLengthMs(long j) {
                Assertions.checkArgument(j >= 0 || j == -9223372036854775807L);
                this.bufferLengthMs = ((j + 50) / 100) * 100;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setCustomDataList(List<String> list) {
                this.customDataList = ImmutableList.copyOf((Collection) list);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setDeadlineMs(long j) {
                Assertions.checkArgument(j >= 0 || j == -9223372036854775807L);
                this.deadlineMs = ((j + 50) / 100) * 100;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMeasuredThroughputInKbps(long j) {
                Assertions.checkArgument(j >= 0 || j == -2147483647L);
                this.measuredThroughputInKbps = ((j + 50) / 100) * 100;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setNextObjectRequest(@Nullable String str) {
                this.nextObjectRequest = str == null ? null : Uri.encode(str);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setNextRangeRequest(@Nullable String str) {
                this.nextRangeRequest = str;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setStartup(boolean z) {
                this.startup = z;
                return this;
            }
        }

        public void populateCmcdDataMap(ArrayListMultimap<String, String> arrayListMultimap) {
            ArrayList arrayList = new ArrayList();
            if (this.bufferLengthMs != -9223372036854775807L) {
                arrayList.add("bl=" + this.bufferLengthMs);
            }
            if (this.measuredThroughputInKbps != -2147483647L) {
                arrayList.add("mtp=" + this.measuredThroughputInKbps);
            }
            if (this.deadlineMs != -9223372036854775807L) {
                arrayList.add("dl=" + this.deadlineMs);
            }
            if (this.startup) {
                arrayList.add(CmcdConfiguration.KEY_STARTUP);
            }
            if (!TextUtils.isEmpty(this.nextObjectRequest)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_NEXT_OBJECT_REQUEST, this.nextObjectRequest));
            }
            if (!TextUtils.isEmpty(this.nextRangeRequest)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_NEXT_RANGE_REQUEST, this.nextRangeRequest));
            }
            arrayList.addAll(this.customDataList);
            if (arrayList.isEmpty()) {
                return;
            }
            arrayListMultimap.putAll(CmcdConfiguration.KEY_CMCD_REQUEST, arrayList);
        }

        public CmcdRequest(Builder builder) {
            this.bufferLengthMs = builder.bufferLengthMs;
            this.measuredThroughputInKbps = builder.measuredThroughputInKbps;
            this.deadlineMs = builder.deadlineMs;
            this.startup = builder.startup;
            this.nextObjectRequest = builder.nextObjectRequest;
            this.nextRangeRequest = builder.nextRangeRequest;
            this.customDataList = builder.customDataList;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CmcdSession {
        public static final int VERSION = 1;

        @Nullable
        public final String contentId;
        public final ImmutableList<String> customDataList;
        public final float playbackRate;

        @Nullable
        public final String sessionId;

        @Nullable
        public final String streamType;

        @Nullable
        public final String streamingFormat;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {

            @Nullable
            public String contentId;
            public ImmutableList<String> customDataList = ImmutableList.of();
            public float playbackRate;

            @Nullable
            public String sessionId;

            @Nullable
            public String streamType;

            @Nullable
            public String streamingFormat;

            public CmcdSession build() {
                return new CmcdSession(this);
            }

            @CanIgnoreReturnValue
            public Builder setContentId(@Nullable String str) {
                Assertions.checkArgument(str == null || str.length() <= 64);
                this.contentId = str;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setCustomDataList(List<String> list) {
                this.customDataList = ImmutableList.copyOf((Collection) list);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setPlaybackRate(float f) {
                Assertions.checkArgument(f > 0.0f || f == -3.4028235E38f);
                this.playbackRate = f;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setSessionId(@Nullable String str) {
                Assertions.checkArgument(str == null || str.length() <= 64);
                this.sessionId = str;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setStreamType(@Nullable String str) {
                this.streamType = str;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setStreamingFormat(@Nullable String str) {
                this.streamingFormat = str;
                return this;
            }
        }

        public void populateCmcdDataMap(ArrayListMultimap<String, String> arrayListMultimap) {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.contentId)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_CONTENT_ID, this.contentId));
            }
            if (!TextUtils.isEmpty(this.sessionId)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_SESSION_ID, this.sessionId));
            }
            if (!TextUtils.isEmpty(this.streamingFormat)) {
                arrayList.add("sf=" + this.streamingFormat);
            }
            if (!TextUtils.isEmpty(this.streamType)) {
                arrayList.add("st=" + this.streamType);
            }
            float f = this.playbackRate;
            if (f != -3.4028235E38f && f != 1.0f) {
                arrayList.add(Util.formatInvariant("%s=%.2f", CmcdConfiguration.KEY_PLAYBACK_RATE, Float.valueOf(f)));
            }
            arrayList.addAll(this.customDataList);
            if (arrayList.isEmpty()) {
                return;
            }
            arrayListMultimap.putAll(CmcdConfiguration.KEY_CMCD_SESSION, arrayList);
        }

        public CmcdSession(Builder builder) {
            this.contentId = builder.contentId;
            this.sessionId = builder.sessionId;
            this.streamingFormat = builder.streamingFormat;
            this.streamType = builder.streamType;
            this.playbackRate = builder.playbackRate;
            this.customDataList = builder.customDataList;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CmcdStatus {
        public final boolean bufferStarvation;
        public final ImmutableList<String> customDataList;
        public final int maximumRequestedThroughputKbps;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder {
            public boolean bufferStarvation;
            public int maximumRequestedThroughputKbps = -2147483647;
            public ImmutableList<String> customDataList = ImmutableList.of();

            public CmcdStatus build() {
                return new CmcdStatus(this);
            }

            @CanIgnoreReturnValue
            public Builder setBufferStarvation(boolean z) {
                this.bufferStarvation = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setCustomDataList(List<String> list) {
                this.customDataList = ImmutableList.copyOf((Collection) list);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setMaximumRequestedThroughputKbps(int i) {
                Assertions.checkArgument(i >= 0 || i == -2147483647);
                if (i != -2147483647) {
                    i = ((i + 50) / 100) * 100;
                }
                this.maximumRequestedThroughputKbps = i;
                return this;
            }
        }

        public void populateCmcdDataMap(ArrayListMultimap<String, String> arrayListMultimap) {
            ArrayList arrayList = new ArrayList();
            if (this.maximumRequestedThroughputKbps != -2147483647) {
                arrayList.add("rtp=" + this.maximumRequestedThroughputKbps);
            }
            if (this.bufferStarvation) {
                arrayList.add(CmcdConfiguration.KEY_BUFFER_STARVATION);
            }
            arrayList.addAll(this.customDataList);
            if (arrayList.isEmpty()) {
                return;
            }
            arrayListMultimap.putAll(CmcdConfiguration.KEY_CMCD_STATUS, arrayList);
        }

        public CmcdStatus(Builder builder) {
            this.maximumRequestedThroughputKbps = builder.maximumRequestedThroughputKbps;
            this.bufferStarvation = builder.bufferStarvation;
            this.customDataList = builder.customDataList;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory {
        public static final Pattern CUSTOM_KEY_NAME_PATTERN = Pattern.compile(".*-.*");
        public static final String OBJECT_TYPE_AUDIO_ONLY = "a";
        public static final String OBJECT_TYPE_INIT_SEGMENT = "i";
        public static final String OBJECT_TYPE_MUXED_AUDIO_AND_VIDEO = "av";
        public static final String OBJECT_TYPE_VIDEO_ONLY = "v";
        public static final String STREAMING_FORMAT_DASH = "d";
        public static final String STREAMING_FORMAT_HLS = "h";
        public static final String STREAMING_FORMAT_SS = "s";
        public static final String STREAM_TYPE_LIVE = "l";
        public static final String STREAM_TYPE_VOD = "v";
        public final long bufferedDurationUs;
        public long chunkDurationUs;
        public final CmcdConfiguration cmcdConfiguration;
        public final boolean didRebuffer;
        public final boolean isBufferEmpty;
        public final boolean isLive;

        @Nullable
        public String nextObjectRequest;

        @Nullable
        public String nextRangeRequest;

        @Nullable
        public String objectType;
        public final float playbackRate;
        public final String streamingFormat;
        public final ExoTrackSelection trackSelection;

        public Factory(CmcdConfiguration cmcdConfiguration, ExoTrackSelection exoTrackSelection, long j, float f, String str, boolean z, boolean z2, boolean z3) {
            Assertions.checkArgument(j >= 0);
            Assertions.checkArgument(f > 0.0f);
            this.cmcdConfiguration = cmcdConfiguration;
            this.trackSelection = exoTrackSelection;
            this.bufferedDurationUs = j;
            this.playbackRate = f;
            this.streamingFormat = str;
            this.isLive = z;
            this.didRebuffer = z2;
            this.isBufferEmpty = z3;
            this.chunkDurationUs = -9223372036854775807L;
        }

        @Nullable
        public static String getObjectType(ExoTrackSelection exoTrackSelection) {
            Assertions.checkArgument(exoTrackSelection != null);
            int trackType = MimeTypes.getTrackType(exoTrackSelection.getSelectedFormat().sampleMimeType);
            if (trackType == -1) {
                trackType = MimeTypes.getTrackType(exoTrackSelection.getSelectedFormat().containerMimeType);
            }
            if (trackType == 1) {
                return "a";
            }
            if (trackType == 2) {
                return "v";
            }
            return null;
        }

        public CmcdData createCmcdData() {
            ImmutableListMultimap<String, String> customData = this.cmcdConfiguration.requestConfig.getCustomData();
            UnmodifiableIterator<String> it = customData.keySet().iterator();
            while (it.hasNext()) {
                validateCustomDataListFormat(customData.get(it.next()));
            }
            int iCeilDivide = Util.ceilDivide(this.trackSelection.getSelectedFormat().bitrate, 1000);
            CmcdObject.Builder builder = new CmcdObject.Builder();
            if (!getIsInitSegment()) {
                this.cmcdConfiguration.isBitrateLoggingAllowed();
                builder.setBitrateKbps(iCeilDivide);
                this.cmcdConfiguration.isTopBitrateLoggingAllowed();
                TrackGroup trackGroup = this.trackSelection.getTrackGroup();
                int iMax = this.trackSelection.getSelectedFormat().bitrate;
                for (int i = 0; i < trackGroup.length; i++) {
                    iMax = Math.max(iMax, trackGroup.formats[i].bitrate);
                }
                builder.setTopBitrateKbps(Util.ceilDivide(iMax, 1000));
                this.cmcdConfiguration.isObjectDurationLoggingAllowed();
                builder.setObjectDurationMs(Util.usToMs(this.chunkDurationUs));
            }
            this.cmcdConfiguration.isObjectTypeLoggingAllowed();
            builder.objectType = this.objectType;
            if (customData.containsKey(CmcdConfiguration.KEY_CMCD_OBJECT)) {
                builder.setCustomDataList(customData.get(CmcdConfiguration.KEY_CMCD_OBJECT));
            }
            CmcdRequest.Builder builder2 = new CmcdRequest.Builder();
            if (!getIsInitSegment()) {
                this.cmcdConfiguration.isBufferLengthLoggingAllowed();
                builder2.setBufferLengthMs(Util.usToMs(this.bufferedDurationUs));
            }
            this.cmcdConfiguration.isMeasuredThroughputLoggingAllowed();
            if (this.trackSelection.getLatestBitrateEstimate() != -2147483647L) {
                builder2.setMeasuredThroughputInKbps(Util.ceilDivide(this.trackSelection.getLatestBitrateEstimate(), 1000L));
            }
            this.cmcdConfiguration.isDeadlineLoggingAllowed();
            builder2.setDeadlineMs(Util.usToMs((long) (this.bufferedDurationUs / this.playbackRate)));
            this.cmcdConfiguration.isStartupLoggingAllowed();
            builder2.startup = this.didRebuffer || this.isBufferEmpty;
            this.cmcdConfiguration.isNextObjectRequestLoggingAllowed();
            builder2.setNextObjectRequest(this.nextObjectRequest);
            this.cmcdConfiguration.isNextRangeRequestLoggingAllowed();
            builder2.nextRangeRequest = this.nextRangeRequest;
            if (customData.containsKey(CmcdConfiguration.KEY_CMCD_REQUEST)) {
                builder2.setCustomDataList(customData.get(CmcdConfiguration.KEY_CMCD_REQUEST));
            }
            CmcdSession.Builder builder3 = new CmcdSession.Builder();
            this.cmcdConfiguration.isContentIdLoggingAllowed();
            builder3.setContentId(this.cmcdConfiguration.contentId);
            this.cmcdConfiguration.isSessionIdLoggingAllowed();
            builder3.setSessionId(this.cmcdConfiguration.sessionId);
            this.cmcdConfiguration.isStreamingFormatLoggingAllowed();
            builder3.streamingFormat = this.streamingFormat;
            this.cmcdConfiguration.isStreamTypeLoggingAllowed();
            builder3.streamType = this.isLive ? STREAM_TYPE_LIVE : "v";
            this.cmcdConfiguration.isPlaybackRateLoggingAllowed();
            builder3.setPlaybackRate(this.playbackRate);
            if (customData.containsKey(CmcdConfiguration.KEY_CMCD_SESSION)) {
                builder3.setCustomDataList(customData.get(CmcdConfiguration.KEY_CMCD_SESSION));
            }
            CmcdStatus.Builder builder4 = new CmcdStatus.Builder();
            this.cmcdConfiguration.isMaximumRequestThroughputLoggingAllowed();
            this.cmcdConfiguration.requestConfig.getClass();
            builder4.setMaximumRequestedThroughputKbps(-2147483647);
            this.cmcdConfiguration.isBufferStarvationLoggingAllowed();
            builder4.bufferStarvation = this.didRebuffer;
            if (customData.containsKey(CmcdConfiguration.KEY_CMCD_STATUS)) {
                builder4.setCustomDataList(customData.get(CmcdConfiguration.KEY_CMCD_STATUS));
            }
            return new CmcdData(new CmcdObject(builder), new CmcdRequest(builder2), new CmcdSession(builder3), new CmcdStatus(builder4), this.cmcdConfiguration.dataTransmissionMode);
        }

        public final boolean getIsInitSegment() {
            String str = this.objectType;
            return str != null && str.equals("i");
        }

        @CanIgnoreReturnValue
        public Factory setChunkDurationUs(long j) {
            Assertions.checkArgument(j >= 0);
            this.chunkDurationUs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setNextObjectRequest(@Nullable String str) {
            this.nextObjectRequest = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setNextRangeRequest(@Nullable String str) {
            this.nextRangeRequest = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setObjectType(@Nullable String str) {
            this.objectType = str;
            return this;
        }

        public final void validateCustomDataListFormat(List<String> list) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                Assertions.checkState(CUSTOM_KEY_NAME_PATTERN.matcher(Util.split(it.next(), "=")[0]).matches());
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ObjectType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamingFormat {
    }

    public DataSpec addToDataSpec(DataSpec dataSpec) {
        ArrayListMultimap<String, String> arrayListMultimap = new ArrayListMultimap<>();
        this.cmcdObject.populateCmcdDataMap(arrayListMultimap);
        this.cmcdRequest.populateCmcdDataMap(arrayListMultimap);
        this.cmcdSession.populateCmcdDataMap(arrayListMultimap);
        this.cmcdStatus.populateCmcdDataMap(arrayListMultimap);
        if (this.dataTransmissionMode == 0) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (String str : arrayListMultimap.keySet()) {
                List list = arrayListMultimap.get((Object) str);
                Collections.sort(list);
                builder.put(str, COMMA_JOINER.join(list));
            }
            return dataSpec.withAdditionalHeaders(builder.build(true));
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = arrayListMultimap.asMap().values().iterator();
        while (it.hasNext()) {
            arrayList.addAll((Collection) it.next());
        }
        Collections.sort(arrayList);
        Uri.Builder builderAppendQueryParameter = dataSpec.uri.buildUpon().appendQueryParameter(CmcdConfiguration.CMCD_QUERY_PARAMETER_KEY, COMMA_JOINER.join(arrayList));
        DataSpec.Builder builder2 = new DataSpec.Builder(dataSpec);
        builder2.uri = builderAppendQueryParameter.build();
        return builder2.build();
    }

    public CmcdData(CmcdObject cmcdObject, CmcdRequest cmcdRequest, CmcdSession cmcdSession, CmcdStatus cmcdStatus, int i) {
        this.cmcdObject = cmcdObject;
        this.cmcdRequest = cmcdRequest;
        this.cmcdSession = cmcdSession;
        this.cmcdStatus = cmcdStatus;
        this.dataTransmissionMode = i;
    }
}
