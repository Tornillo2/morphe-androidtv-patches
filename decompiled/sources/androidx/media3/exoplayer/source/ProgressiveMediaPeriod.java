package androidx.media3.exoplayer.source;

import android.net.Uri;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.source.IcyDataSource;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ForwardingSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ProgressiveMediaPeriod implements MediaPeriod, ExtractorOutput, Loader.Callback<ExtractingLoadable>, Loader.ReleaseCallback, SampleQueue.UpstreamFormatChangedListener {
    public static final long DEFAULT_LAST_SAMPLE_DURATION_US = 10000;
    public static final Format ICY_FORMAT;
    public static final Map<String, String> ICY_METADATA_HEADERS = createIcyMetadataHeaders();
    public final Allocator allocator;

    @Nullable
    public MediaPeriod.Callback callback;
    public final long continueLoadingCheckIntervalBytes;

    @Nullable
    public final String customCacheKey;
    public final DataSource dataSource;
    public int dataType;
    public final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    public final DrmSessionManager drmSessionManager;
    public long durationUs;
    public int enabledTrackCount;
    public int extractedSamplesCountAtStartOfLoad;
    public final Handler handler;
    public boolean haveAudioVideoTracks;

    @Nullable
    public IcyHeaders icyHeaders;
    public boolean isLengthKnown;
    public boolean isLive;
    public final boolean isSingleSample;
    public long lastSeekPositionUs;
    public final Listener listener;
    public final ConditionVariable loadCondition;
    public final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    public final Loader loader = new Loader("ProgressiveMediaPeriod");
    public boolean loadingFinished;
    public final Runnable maybeFinishPrepareRunnable;
    public final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    public boolean notifyDiscontinuity;
    public final Runnable onContinueLoadingRequestedRunnable;
    public boolean pendingDeferredRetry;
    public long pendingResetPositionUs;
    public boolean prepared;
    public final ProgressiveMediaExtractor progressiveMediaExtractor;
    public boolean released;
    public TrackId[] sampleQueueTrackIds;
    public SampleQueue[] sampleQueues;
    public boolean sampleQueuesBuilt;
    public SeekMap seekMap;
    public boolean seenFirstTrackSelection;
    public TrackState trackState;
    public final Uri uri;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ExtractingLoadable implements Loader.Loadable, IcyDataSource.Listener {
        public final StatsDataSource dataSource;
        public final ExtractorOutput extractorOutput;

        @Nullable
        public TrackOutput icyTrackOutput;
        public volatile boolean loadCanceled;
        public final ConditionVariable loadCondition;
        public final ProgressiveMediaExtractor progressiveMediaExtractor;
        public long seekTimeUs;
        public boolean seenIcyMetadata;
        public final Uri uri;
        public final PositionHolder positionHolder = new PositionHolder();
        public boolean pendingExtractorSeek = true;
        public final long loadTaskId = LoadEventInfo.getNewId();
        public DataSpec dataSpec = buildDataSpec(0);

        public ExtractingLoadable(Uri uri, DataSource dataSource, ProgressiveMediaExtractor progressiveMediaExtractor, ExtractorOutput extractorOutput, ConditionVariable conditionVariable) {
            this.uri = uri;
            this.dataSource = new StatsDataSource(dataSource);
            this.progressiveMediaExtractor = progressiveMediaExtractor;
            this.extractorOutput = extractorOutput;
            this.loadCondition = conditionVariable;
        }

        public final DataSpec buildDataSpec(long j) {
            DataSpec.Builder builder = new DataSpec.Builder();
            builder.uri = this.uri;
            builder.position = j;
            builder.key = ProgressiveMediaPeriod.this.customCacheKey;
            builder.flags = 6;
            builder.httpRequestHeaders = ProgressiveMediaPeriod.ICY_METADATA_HEADERS;
            return builder.build();
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void cancelLoad() {
            this.loadCanceled = true;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void load() throws IOException {
            DataReader icyDataSource;
            int i;
            int i2 = 0;
            while (i2 == 0 && !this.loadCanceled) {
                try {
                    long j = this.positionHolder.position;
                    DataSpec dataSpecBuildDataSpec = buildDataSpec(j);
                    this.dataSpec = dataSpecBuildDataSpec;
                    long jOpen = this.dataSource.open(dataSpecBuildDataSpec);
                    if (this.loadCanceled) {
                        if (i2 != 1 && this.progressiveMediaExtractor.getCurrentInputPosition() != -1) {
                            this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                        }
                        DataSourceUtil.closeQuietly(this.dataSource);
                        return;
                    }
                    if (jOpen != -1) {
                        jOpen += j;
                        ProgressiveMediaPeriod.this.onLengthKnown();
                    }
                    long j2 = jOpen;
                    ProgressiveMediaPeriod.this.icyHeaders = IcyHeaders.parse(this.dataSource.dataSource.getResponseHeaders());
                    StatsDataSource statsDataSource = this.dataSource;
                    IcyHeaders icyHeaders = ProgressiveMediaPeriod.this.icyHeaders;
                    if (icyHeaders == null || (i = icyHeaders.metadataInterval) == -1) {
                        icyDataSource = statsDataSource;
                    } else {
                        icyDataSource = new IcyDataSource(statsDataSource, i, this);
                        TrackOutput trackOutputIcyTrack = ProgressiveMediaPeriod.this.icyTrack();
                        this.icyTrackOutput = trackOutputIcyTrack;
                        trackOutputIcyTrack.format(ProgressiveMediaPeriod.ICY_FORMAT);
                    }
                    this.progressiveMediaExtractor.init(icyDataSource, this.uri, this.dataSource.dataSource.getResponseHeaders(), j, j2, this.extractorOutput);
                    if (ProgressiveMediaPeriod.this.icyHeaders != null) {
                        this.progressiveMediaExtractor.disableSeekingOnMp3Streams();
                    }
                    if (this.pendingExtractorSeek) {
                        this.progressiveMediaExtractor.seek(j, this.seekTimeUs);
                        this.pendingExtractorSeek = false;
                    }
                    while (i2 == 0 && !this.loadCanceled) {
                        try {
                            this.loadCondition.block();
                            i2 = this.progressiveMediaExtractor.read(this.positionHolder);
                            long currentInputPosition = this.progressiveMediaExtractor.getCurrentInputPosition();
                            if (currentInputPosition > ProgressiveMediaPeriod.this.continueLoadingCheckIntervalBytes + j) {
                                this.loadCondition.close();
                                ProgressiveMediaPeriod progressiveMediaPeriod = ProgressiveMediaPeriod.this;
                                progressiveMediaPeriod.handler.post(progressiveMediaPeriod.onContinueLoadingRequestedRunnable);
                                j = currentInputPosition;
                            }
                        } catch (InterruptedException unused) {
                            throw new InterruptedIOException();
                        }
                    }
                    if (i2 == 1) {
                        i2 = 0;
                    } else if (this.progressiveMediaExtractor.getCurrentInputPosition() != -1) {
                        this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                    }
                    DataSourceUtil.closeQuietly(this.dataSource);
                } catch (Throwable th) {
                    if (i2 != 1 && this.progressiveMediaExtractor.getCurrentInputPosition() != -1) {
                        this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                    }
                    DataSourceUtil.closeQuietly(this.dataSource);
                    throw th;
                }
            }
        }

        @Override // androidx.media3.exoplayer.source.IcyDataSource.Listener
        public void onIcyMetadata(ParsableByteArray parsableByteArray) {
            long jMax = !this.seenIcyMetadata ? this.seekTimeUs : Math.max(ProgressiveMediaPeriod.this.getLargestQueuedTimestampUs(true), this.seekTimeUs);
            int iBytesLeft = parsableByteArray.bytesLeft();
            TrackOutput trackOutput = this.icyTrackOutput;
            trackOutput.getClass();
            trackOutput.sampleData(parsableByteArray, iBytesLeft);
            trackOutput.sampleMetadata(jMax, 1, iBytesLeft, 0, null);
            this.seenIcyMetadata = true;
        }

        public final void setLoadPosition(long j, long j2) {
            this.positionHolder.position = j;
            this.seekTimeUs = j2;
            this.pendingExtractorSeek = true;
            this.seenIcyMetadata = false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onSourceInfoRefreshed(long j, boolean z, boolean z2);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SampleStreamImpl implements SampleStream {
        public final int track;

        public SampleStreamImpl(int i) {
            this.track = i;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return ProgressiveMediaPeriod.this.isReady(this.track);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() throws IOException {
            ProgressiveMediaPeriod.this.maybeThrowError(this.track);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            return ProgressiveMediaPeriod.this.readData(this.track, formatHolder, decoderInputBuffer, i);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long j) {
            return ProgressiveMediaPeriod.this.skipData(this.track, j);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TrackId {
        public final int id;
        public final boolean isIcyTrack;

        public TrackId(int i, boolean z) {
            this.id = i;
            this.isIcyTrack = z;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && TrackId.class == obj.getClass()) {
                TrackId trackId = (TrackId) obj;
                if (this.id == trackId.id && this.isIcyTrack == trackId.isIcyTrack) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return (this.id * 31) + (this.isIcyTrack ? 1 : 0);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TrackState {
        public final boolean[] trackEnabledStates;
        public final boolean[] trackIsAudioVideoFlags;
        public final boolean[] trackNotifiedDownstreamFormats;
        public final TrackGroupArray tracks;

        public TrackState(TrackGroupArray trackGroupArray, boolean[] zArr) {
            this.tracks = trackGroupArray;
            this.trackIsAudioVideoFlags = zArr;
            int i = trackGroupArray.length;
            this.trackEnabledStates = new boolean[i];
            this.trackNotifiedDownstreamFormats = new boolean[i];
        }
    }

    public static void $r8$lambda$dqSMcOVucEUbHoOoHEY__SOT3rM(ProgressiveMediaPeriod progressiveMediaPeriod) {
        if (progressiveMediaPeriod.released) {
            return;
        }
        MediaPeriod.Callback callback = progressiveMediaPeriod.callback;
        callback.getClass();
        callback.onContinueLoadingRequested(progressiveMediaPeriod);
    }

    static {
        Format.Builder builder = new Format.Builder();
        builder.id = "icy";
        builder.sampleMimeType = MimeTypes.normalizeMimeType("application/x-icy");
        ICY_FORMAT = new Format(builder);
    }

    public ProgressiveMediaPeriod(Uri uri, DataSource dataSource, ProgressiveMediaExtractor progressiveMediaExtractor, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy, MediaSourceEventListener.EventDispatcher eventDispatcher2, Listener listener, Allocator allocator, @Nullable String str, int i, long j) {
        this.uri = uri;
        this.dataSource = dataSource;
        this.drmSessionManager = drmSessionManager;
        this.drmEventDispatcher = eventDispatcher;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.mediaSourceEventDispatcher = eventDispatcher2;
        this.listener = listener;
        this.allocator = allocator;
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = i;
        this.progressiveMediaExtractor = progressiveMediaExtractor;
        this.durationUs = j;
        this.isSingleSample = j != -9223372036854775807L;
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new Runnable() { // from class: androidx.media3.exoplayer.source.ProgressiveMediaPeriod$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.maybeFinishPrepare();
            }
        };
        this.onContinueLoadingRequestedRunnable = new Runnable() { // from class: androidx.media3.exoplayer.source.ProgressiveMediaPeriod$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ProgressiveMediaPeriod.$r8$lambda$dqSMcOVucEUbHoOoHEY__SOT3rM(this.f$0);
            }
        };
        this.handler = Util.createHandlerForCurrentLooper(null);
        this.sampleQueueTrackIds = new TrackId[0];
        this.sampleQueues = new SampleQueue[0];
        this.pendingResetPositionUs = -9223372036854775807L;
        this.dataType = 1;
    }

    public static Map<String, String> createIcyMetadataHeaders() {
        HashMap map = new HashMap();
        map.put("Icy-MetaData", "1");
        return DesugarCollections.unmodifiableMap(map);
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != -9223372036854775807L;
    }

    @EnsuresNonNull({"trackState", "seekMap"})
    public final void assertPrepared() {
        Assertions.checkState(this.prepared);
        this.trackState.getClass();
        this.seekMap.getClass();
    }

    public final boolean configureRetry(ExtractingLoadable extractingLoadable, int i) {
        SeekMap seekMap;
        if (this.isLengthKnown || !((seekMap = this.seekMap) == null || seekMap.getDurationUs() == -9223372036854775807L)) {
            this.extractedSamplesCountAtStartOfLoad = i;
            return true;
        }
        if (this.prepared && !suppressRead()) {
            this.pendingDeferredRetry = true;
            return false;
        }
        this.notifyDiscontinuity = this.prepared;
        this.lastSeekPositionUs = 0L;
        this.extractedSamplesCountAtStartOfLoad = 0;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset(false);
        }
        extractingLoadable.setLoadPosition(0L, 0L);
        return true;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (this.loadingFinished || this.loader.hasFatalError() || this.pendingDeferredRetry) {
            return false;
        }
        if (this.prepared && this.enabledTrackCount == 0) {
            return false;
        }
        boolean zOpen = this.loadCondition.open();
        if (this.loader.isLoading()) {
            return zOpen;
        }
        startLoading();
        return true;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        if (this.isSingleSample) {
            return;
        }
        assertPrepared();
        if (isPendingReset()) {
            return;
        }
        boolean[] zArr = this.trackState.trackEnabledStates;
        int length = this.sampleQueues.length;
        for (int i = 0; i < length; i++) {
            this.sampleQueues[i].discardTo(j, z, zArr[i]);
        }
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        assertPrepared();
        if (!this.seekMap.isSeekable()) {
            return 0L;
        }
        SeekMap.SeekPoints seekPoints = this.seekMap.getSeekPoints(j);
        return seekParameters.resolveSeekPositionUs(j, seekPoints.first.timeUs, seekPoints.second.timeUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long largestQueuedTimestampUs;
        assertPrepared();
        if (this.loadingFinished || this.enabledTrackCount == 0) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.haveAudioVideoTracks) {
            int length = this.sampleQueues.length;
            largestQueuedTimestampUs = Long.MAX_VALUE;
            for (int i = 0; i < length; i++) {
                TrackState trackState = this.trackState;
                if (trackState.trackIsAudioVideoFlags[i] && trackState.trackEnabledStates[i] && !this.sampleQueues[i].isLastSampleQueued()) {
                    largestQueuedTimestampUs = Math.min(largestQueuedTimestampUs, this.sampleQueues[i].getLargestQueuedTimestampUs());
                }
            }
        } else {
            largestQueuedTimestampUs = Long.MAX_VALUE;
        }
        if (largestQueuedTimestampUs == Long.MAX_VALUE) {
            largestQueuedTimestampUs = getLargestQueuedTimestampUs(false);
        }
        return largestQueuedTimestampUs == Long.MIN_VALUE ? this.lastSeekPositionUs : largestQueuedTimestampUs;
    }

    public final int getExtractedSamplesCount() {
        int writeIndex = 0;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            writeIndex += sampleQueue.getWriteIndex();
        }
        return writeIndex;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long getLargestQueuedTimestampUs(boolean r6) {
        /*
            r5 = this;
            r0 = -9223372036854775808
            r2 = 0
        L3:
            androidx.media3.exoplayer.source.SampleQueue[] r3 = r5.sampleQueues
            int r3 = r3.length
            if (r2 >= r3) goto L24
            if (r6 != 0) goto L15
            androidx.media3.exoplayer.source.ProgressiveMediaPeriod$TrackState r3 = r5.trackState
            r3.getClass()
            boolean[] r3 = r3.trackEnabledStates
            boolean r3 = r3[r2]
            if (r3 == 0) goto L21
        L15:
            androidx.media3.exoplayer.source.SampleQueue[] r3 = r5.sampleQueues
            r3 = r3[r2]
            long r3 = r3.getLargestQueuedTimestampUs()
            long r0 = java.lang.Math.max(r0, r3)
        L21:
            int r2 = r2 + 1
            goto L3
        L24:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.ProgressiveMediaPeriod.getLargestQueuedTimestampUs(boolean):long");
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return getBufferedPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public List getStreamKeys(List list) {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        assertPrepared();
        return this.trackState.tracks;
    }

    public TrackOutput icyTrack() {
        return prepareTrackOutput(new TrackId(0, true));
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.loader.isLoading() && this.loadCondition.isOpen();
    }

    public boolean isReady(int i) {
        return !suppressRead() && this.sampleQueues[i].isReady(this.loadingFinished);
    }

    public final void maybeFinishPrepare() {
        if (this.released || this.prepared || !this.sampleQueuesBuilt || this.seekMap == null) {
            return;
        }
        for (SampleQueue sampleQueue : this.sampleQueues) {
            if (sampleQueue.getUpstreamFormat() == null) {
                return;
            }
        }
        this.loadCondition.close();
        int length = this.sampleQueues.length;
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            Format upstreamFormat = this.sampleQueues[i].getUpstreamFormat();
            upstreamFormat.getClass();
            String str = upstreamFormat.sampleMimeType;
            boolean zIsAudio = MimeTypes.isAudio(str);
            boolean z = zIsAudio || MimeTypes.isVideo(str);
            zArr[i] = z;
            this.haveAudioVideoTracks = z | this.haveAudioVideoTracks;
            IcyHeaders icyHeaders = this.icyHeaders;
            if (icyHeaders != null) {
                if (zIsAudio || this.sampleQueueTrackIds[i].isIcyTrack) {
                    Metadata metadata = upstreamFormat.metadata;
                    Metadata metadata2 = metadata == null ? new Metadata(icyHeaders) : metadata.copyWithAppendedEntries(icyHeaders);
                    Format.Builder builder = new Format.Builder(upstreamFormat);
                    builder.metadata = metadata2;
                    upstreamFormat = new Format(builder);
                }
                if (zIsAudio && upstreamFormat.averageBitrate == -1 && upstreamFormat.peakBitrate == -1 && icyHeaders.bitrate != -1) {
                    Format.Builder builder2 = new Format.Builder(upstreamFormat);
                    builder2.averageBitrate = icyHeaders.bitrate;
                    upstreamFormat = new Format(builder2);
                }
            }
            trackGroupArr[i] = new TrackGroup(Integer.toString(i), upstreamFormat.copyWithCryptoType(this.drmSessionManager.getCryptoType(upstreamFormat)));
        }
        this.trackState = new TrackState(new TrackGroupArray(trackGroupArr), zArr);
        this.prepared = true;
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onPrepared(this);
    }

    public final void maybeNotifyDownstreamFormat(int i) {
        assertPrepared();
        TrackState trackState = this.trackState;
        boolean[] zArr = trackState.trackNotifiedDownstreamFormats;
        if (zArr[i]) {
            return;
        }
        Format format = trackState.tracks.get(i).formats[0];
        this.mediaSourceEventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(format.sampleMimeType), format, 0, null, this.lastSeekPositionUs);
        zArr[i] = true;
    }

    public final void maybeStartDeferredRetry(int i) {
        assertPrepared();
        boolean[] zArr = this.trackState.trackIsAudioVideoFlags;
        if (this.pendingDeferredRetry && zArr[i] && !this.sampleQueues[i].isReady(false)) {
            this.pendingResetPositionUs = 0L;
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = true;
            this.lastSeekPositionUs = 0L;
            this.extractedSamplesCountAtStartOfLoad = 0;
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.reset(false);
            }
            MediaPeriod.Callback callback = this.callback;
            callback.getClass();
            callback.onContinueLoadingRequested(this);
        }
    }

    public void maybeThrowError(int i) throws IOException {
        this.sampleQueues[i].maybeThrowError();
        maybeThrowError();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
        if (this.loadingFinished && !this.prepared) {
            throw ParserException.createForMalformedContainer("Loading finished before preparation is complete.", null);
        }
    }

    public final void onLengthKnown() {
        this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.source.ProgressiveMediaPeriod$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.isLengthKnown = true;
            }
        });
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.release();
        }
        this.progressiveMediaExtractor.release();
    }

    @Override // androidx.media3.exoplayer.source.SampleQueue.UpstreamFormatChangedListener
    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        this.loadCondition.open();
        startLoading();
    }

    public final TrackOutput prepareTrackOutput(TrackId trackId) {
        int length = this.sampleQueues.length;
        for (int i = 0; i < length; i++) {
            if (trackId.equals(this.sampleQueueTrackIds[i])) {
                return this.sampleQueues[i];
            }
        }
        SampleQueue sampleQueueCreateWithDrm = SampleQueue.createWithDrm(this.allocator, this.drmSessionManager, this.drmEventDispatcher);
        sampleQueueCreateWithDrm.upstreamFormatChangeListener = this;
        int i2 = length + 1;
        TrackId[] trackIdArr = (TrackId[]) Arrays.copyOf(this.sampleQueueTrackIds, i2);
        trackIdArr[length] = trackId;
        Util.castNonNullTypeArray(trackIdArr);
        this.sampleQueueTrackIds = trackIdArr;
        SampleQueue[] sampleQueueArr = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i2);
        sampleQueueArr[length] = sampleQueueCreateWithDrm;
        this.sampleQueues = sampleQueueArr;
        return sampleQueueCreateWithDrm;
    }

    public int readData(int i, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2) {
        if (suppressRead()) {
            return -3;
        }
        maybeNotifyDownstreamFormat(i);
        int i3 = this.sampleQueues[i].read(formatHolder, decoderInputBuffer, i2, this.loadingFinished);
        if (i3 == -3) {
            maybeStartDeferredRetry(i);
        }
        return i3;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        if (!this.notifyDiscontinuity) {
            return -9223372036854775807L;
        }
        if (!this.loadingFinished && getExtractedSamplesCount() <= this.extractedSamplesCountAtStartOfLoad) {
            return -9223372036854775807L;
        }
        this.notifyDiscontinuity = false;
        return this.lastSeekPositionUs;
    }

    public void release() {
        if (this.prepared) {
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.preRelease();
            }
        }
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages(null);
        this.callback = null;
        this.released = true;
    }

    public final boolean seekInsideBufferUs(boolean[] zArr, long j) {
        int length = this.sampleQueues.length;
        for (int i = 0; i < length; i++) {
            SampleQueue sampleQueue = this.sampleQueues[i];
            if (!(this.isSingleSample ? sampleQueue.seekTo(sampleQueue.absoluteFirstIndex) : sampleQueue.seekTo(j, false)) && (zArr[i] || !this.haveAudioVideoTracks)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(final SeekMap seekMap) {
        this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.source.ProgressiveMediaPeriod$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.setSeekMap(seekMap);
            }
        });
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long j) {
        assertPrepared();
        boolean[] zArr = this.trackState.trackIsAudioVideoFlags;
        if (!this.seekMap.isSeekable()) {
            j = 0;
        }
        this.notifyDiscontinuity = false;
        this.lastSeekPositionUs = j;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j;
            return j;
        }
        if (this.dataType == 7 || !seekInsideBufferUs(zArr, j)) {
            this.pendingDeferredRetry = false;
            this.pendingResetPositionUs = j;
            this.loadingFinished = false;
            if (this.loader.isLoading()) {
                for (SampleQueue sampleQueue : this.sampleQueues) {
                    sampleQueue.discardToEnd();
                }
                this.loader.cancelLoading();
                return j;
            }
            this.loader.fatalError = null;
            for (SampleQueue sampleQueue2 : this.sampleQueues) {
                sampleQueue2.reset(false);
            }
        }
        return j;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        ExoTrackSelection exoTrackSelection;
        assertPrepared();
        TrackState trackState = this.trackState;
        TrackGroupArray trackGroupArray = trackState.tracks;
        boolean[] zArr3 = trackState.trackEnabledStates;
        int i = this.enabledTrackCount;
        int i2 = 0;
        for (int i3 = 0; i3 < exoTrackSelectionArr.length; i3++) {
            SampleStream sampleStream = sampleStreamArr[i3];
            if (sampleStream != null && (exoTrackSelectionArr[i3] == null || !zArr[i3])) {
                int i4 = ((SampleStreamImpl) sampleStream).track;
                Assertions.checkState(zArr3[i4]);
                this.enabledTrackCount--;
                zArr3[i4] = false;
                sampleStreamArr[i3] = null;
            }
        }
        boolean z = !this.isSingleSample && (!this.seenFirstTrackSelection ? j == 0 : i != 0);
        for (int i5 = 0; i5 < exoTrackSelectionArr.length; i5++) {
            if (sampleStreamArr[i5] == null && (exoTrackSelection = exoTrackSelectionArr[i5]) != null) {
                Assertions.checkState(exoTrackSelection.length() == 1);
                Assertions.checkState(exoTrackSelection.getIndexInTrackGroup(0) == 0);
                int iIndexOf = trackGroupArray.indexOf(exoTrackSelection.getTrackGroup());
                Assertions.checkState(!zArr3[iIndexOf]);
                this.enabledTrackCount++;
                zArr3[iIndexOf] = true;
                sampleStreamArr[i5] = new SampleStreamImpl(iIndexOf);
                zArr2[i5] = true;
                if (!z) {
                    SampleQueue sampleQueue = this.sampleQueues[iIndexOf];
                    z = (sampleQueue.getReadIndex() == 0 || sampleQueue.seekTo(j, true)) ? false : true;
                }
            }
        }
        if (this.enabledTrackCount == 0) {
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length = sampleQueueArr.length;
                while (i2 < length) {
                    sampleQueueArr[i2].discardToEnd();
                    i2++;
                }
                this.loader.cancelLoading();
            } else {
                for (SampleQueue sampleQueue2 : this.sampleQueues) {
                    sampleQueue2.reset(false);
                }
            }
        } else if (z) {
            j = seekToUs(j);
            while (i2 < sampleStreamArr.length) {
                if (sampleStreamArr[i2] != null) {
                    zArr2[i2] = true;
                }
                i2++;
            }
        }
        this.seenFirstTrackSelection = true;
        return j;
    }

    public final void setSeekMap(SeekMap seekMap) {
        this.seekMap = this.icyHeaders == null ? seekMap : new SeekMap.Unseekable(-9223372036854775807L);
        if (seekMap.getDurationUs() == -9223372036854775807L && this.durationUs != -9223372036854775807L) {
            this.seekMap = new ForwardingSeekMap(this.seekMap) { // from class: androidx.media3.exoplayer.source.ProgressiveMediaPeriod.1
                @Override // androidx.media3.extractor.ForwardingSeekMap, androidx.media3.extractor.SeekMap
                public long getDurationUs() {
                    return ProgressiveMediaPeriod.this.durationUs;
                }
            };
        }
        this.durationUs = this.seekMap.getDurationUs();
        boolean z = !this.isLengthKnown && seekMap.getDurationUs() == -9223372036854775807L;
        this.isLive = z;
        this.dataType = z ? 7 : 1;
        this.listener.onSourceInfoRefreshed(this.durationUs, seekMap.isSeekable(), this.isLive);
        if (this.prepared) {
            return;
        }
        maybeFinishPrepare();
    }

    public int skipData(int i, long j) throws Throwable {
        if (suppressRead()) {
            return 0;
        }
        maybeNotifyDownstreamFormat(i);
        SampleQueue sampleQueue = this.sampleQueues[i];
        int skipCount = sampleQueue.getSkipCount(j, this.loadingFinished);
        sampleQueue.skip(skipCount);
        if (skipCount == 0) {
            maybeStartDeferredRetry(i);
        }
        return skipCount;
    }

    public final void startLoading() {
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.uri, this.dataSource, this.progressiveMediaExtractor, this, this.loadCondition);
        if (this.prepared) {
            Assertions.checkState(isPendingReset());
            long j = this.durationUs;
            if (j != -9223372036854775807L && this.pendingResetPositionUs > j) {
                this.loadingFinished = true;
                this.pendingResetPositionUs = -9223372036854775807L;
                return;
            }
            SeekMap seekMap = this.seekMap;
            seekMap.getClass();
            extractingLoadable.setLoadPosition(seekMap.getSeekPoints(this.pendingResetPositionUs).first.position, this.pendingResetPositionUs);
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.startTimeUs = this.pendingResetPositionUs;
            }
            this.pendingResetPositionUs = -9223372036854775807L;
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        this.mediaSourceEventDispatcher.loadStarted(new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, this.loader.startLoading(extractingLoadable, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType))), 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs);
    }

    public final boolean suppressRead() {
        return this.notifyDiscontinuity || isPendingReset();
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int i, int i2) {
        return prepareTrackOutput(new TrackId(i, false));
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadCanceled(ExtractingLoadable extractingLoadable, long j, long j2, boolean z) {
        StatsDataSource statsDataSource = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, statsDataSource.lastOpenedUri, statsDataSource.lastResponseHeaders, j, j2, statsDataSource.bytesRead);
        this.loadErrorHandlingPolicy.getClass();
        this.mediaSourceEventDispatcher.loadCanceled(loadEventInfo, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs);
        if (z) {
            return;
        }
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset(false);
        }
        if (this.enabledTrackCount > 0) {
            MediaPeriod.Callback callback = this.callback;
            callback.getClass();
            callback.onContinueLoadingRequested(this);
        }
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public void onLoadCompleted(ExtractingLoadable extractingLoadable, long j, long j2) {
        SeekMap seekMap;
        if (this.durationUs == -9223372036854775807L && (seekMap = this.seekMap) != null) {
            boolean zIsSeekable = seekMap.isSeekable();
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs(true);
            long j3 = largestQueuedTimestampUs == Long.MIN_VALUE ? 0L : largestQueuedTimestampUs + 10000;
            this.durationUs = j3;
            this.listener.onSourceInfoRefreshed(j3, zIsSeekable, this.isLive);
        }
        StatsDataSource statsDataSource = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, statsDataSource.lastOpenedUri, statsDataSource.lastResponseHeaders, j, j2, statsDataSource.bytesRead);
        this.loadErrorHandlingPolicy.getClass();
        this.mediaSourceEventDispatcher.loadCompleted(loadEventInfo, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs);
        this.loadingFinished = true;
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public Loader.LoadErrorAction onLoadError(ExtractingLoadable extractingLoadable, long j, long j2, IOException iOException, int i) {
        Loader.LoadErrorAction loadErrorAction;
        StatsDataSource statsDataSource = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, statsDataSource.lastOpenedUri, statsDataSource.lastResponseHeaders, j, j2, statsDataSource.bytesRead);
        long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, new MediaLoadData(1, -1, null, 0, null, Util.usToMs(extractingLoadable.seekTimeUs), Util.usToMs(this.durationUs)), iOException, i));
        if (retryDelayMsFor == -9223372036854775807L) {
            loadErrorAction = Loader.DONT_RETRY_FATAL;
        } else {
            int extractedSamplesCount = getExtractedSamplesCount();
            loadErrorAction = configureRetry(extractingLoadable, extractedSamplesCount) ? new Loader.LoadErrorAction(extractedSamplesCount > this.extractedSamplesCountAtStartOfLoad ? 1 : 0, retryDelayMsFor) : Loader.DONT_RETRY;
        }
        boolean zIsRetry = loadErrorAction.isRetry();
        this.mediaSourceEventDispatcher.loadError(loadEventInfo, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs, iOException, !zIsRetry);
        if (!zIsRetry) {
            this.loadErrorHandlingPolicy.getClass();
        }
        return loadErrorAction;
    }

    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError(this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType));
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
    }
}
