package androidx.media3.exoplayer.source.ads;

import android.os.Handler;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.StreamKey;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.BaseMediaSource;
import androidx.media3.exoplayer.source.EmptySampleStream;
import androidx.media3.exoplayer.source.ForwardingTimeline;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ServerSideAdInsertionMediaSource extends BaseMediaSource implements MediaSource.MediaSourceCaller, MediaSourceEventListener, DrmSessionEventListener {

    @Nullable
    public final AdPlaybackStateUpdater adPlaybackStateUpdater;

    @Nullable
    public SharedMediaPeriod lastUsedMediaPeriod;
    public final MediaSource mediaSource;

    @Nullable
    @GuardedBy("this")
    public Handler playbackHandler;
    public final ListMultimap<Pair<Long, Object>, SharedMediaPeriod> mediaPeriods = new ArrayListMultimap();
    public ImmutableMap<Object, AdPlaybackState> adPlaybackStates = RegularImmutableMap.EMPTY;
    public final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcherWithoutId = createEventDispatcher(null);
    public final DrmSessionEventListener.EventDispatcher drmEventDispatcherWithoutId = createDrmEventDispatcher(null);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AdPlaybackStateUpdater {
        boolean onAdPlaybackStateUpdateRequested(Timeline timeline);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaPeriodImpl implements MediaPeriod {
        public MediaPeriod.Callback callback;
        public final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
        public boolean[] hasNotifiedDownstreamFormatChange = new boolean[0];
        public boolean isPrepared;
        public long lastStartPositionUs;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
        public final SharedMediaPeriod sharedPeriod;

        public MediaPeriodImpl(SharedMediaPeriod sharedMediaPeriod, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener.EventDispatcher eventDispatcher2) {
            this.sharedPeriod = sharedMediaPeriod;
            this.mediaPeriodId = mediaPeriodId;
            this.mediaSourceEventDispatcher = eventDispatcher;
            this.drmEventDispatcher = eventDispatcher2;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public boolean continueLoading(LoadingInfo loadingInfo) {
            return this.sharedPeriod.continueLoading(this, loadingInfo);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void discardBuffer(long j, boolean z) {
            this.sharedPeriod.discardBuffer(this, j, z);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
            return this.sharedPeriod.getAdjustedSeekPositionUs(this, j, seekParameters);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public long getBufferedPositionUs() {
            return this.sharedPeriod.getBufferedPositionUs(this);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public long getNextLoadPositionUs() {
            return this.sharedPeriod.getNextLoadPositionUs(this);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
            return this.sharedPeriod.actualMediaPeriod.getStreamKeys(list);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public TrackGroupArray getTrackGroups() {
            return this.sharedPeriod.actualMediaPeriod.getTrackGroups();
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public boolean isLoading() {
            return this.sharedPeriod.isLoading(this);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void maybeThrowPrepareError() throws IOException {
            this.sharedPeriod.maybeThrowPrepareError();
        }

        public void onPrepared() {
            MediaPeriod.Callback callback = this.callback;
            if (callback != null) {
                callback.onPrepared(this);
            }
            this.isPrepared = true;
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public void prepare(MediaPeriod.Callback callback, long j) {
            this.callback = callback;
            this.sharedPeriod.prepare(this, j);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long readDiscontinuity() {
            return this.sharedPeriod.readDiscontinuity(this);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
        public void reevaluateBuffer(long j) {
            SharedMediaPeriod sharedMediaPeriod = this.sharedPeriod;
            sharedMediaPeriod.actualMediaPeriod.reevaluateBuffer(sharedMediaPeriod.getStreamPositionUsWithNotYetStartedHandling(this, j));
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long seekToUs(long j) {
            return this.sharedPeriod.seekToUs(this, j);
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod
        public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
            if (this.hasNotifiedDownstreamFormatChange.length == 0) {
                this.hasNotifiedDownstreamFormatChange = new boolean[sampleStreamArr.length];
            }
            return this.sharedPeriod.selectTracks(this, exoTrackSelectionArr, zArr, sampleStreamArr, zArr2, j);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SampleStreamImpl implements SampleStream {
        public final MediaPeriodImpl mediaPeriod;
        public final int streamIndex;

        public SampleStreamImpl(MediaPeriodImpl mediaPeriodImpl, int i) {
            this.mediaPeriod = mediaPeriodImpl;
            this.streamIndex = i;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return this.mediaPeriod.sharedPeriod.isReady(this.streamIndex);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() throws IOException {
            this.mediaPeriod.sharedPeriod.maybeThrowError(this.streamIndex);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            MediaPeriodImpl mediaPeriodImpl = this.mediaPeriod;
            return mediaPeriodImpl.sharedPeriod.readData(mediaPeriodImpl, this.streamIndex, formatHolder, decoderInputBuffer, i);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long j) {
            MediaPeriodImpl mediaPeriodImpl = this.mediaPeriod;
            return mediaPeriodImpl.sharedPeriod.skipData(mediaPeriodImpl, this.streamIndex, j);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ServerSideAdInsertionTimeline extends ForwardingTimeline {
        public final ImmutableMap<Object, AdPlaybackState> adPlaybackStates;

        public ServerSideAdInsertionTimeline(Timeline timeline, ImmutableMap<Object, AdPlaybackState> immutableMap) {
            super(timeline);
            Assertions.checkState(timeline.getWindowCount() == 1);
            Timeline.Period period = new Timeline.Period();
            for (int i = 0; i < timeline.getPeriodCount(); i++) {
                timeline.getPeriod(i, period, true);
                Object obj = period.uid;
                obj.getClass();
                Assertions.checkState(immutableMap.containsKey(obj));
            }
            this.adPlaybackStates = immutableMap;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            super.getPeriod(i, period, true);
            AdPlaybackState adPlaybackState = this.adPlaybackStates.get(period.uid);
            adPlaybackState.getClass();
            long j = period.durationUs;
            long mediaPeriodPositionUsForContent = j == -9223372036854775807L ? adPlaybackState.contentDurationUs : ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(j, -1, adPlaybackState);
            Timeline.Period period2 = new Timeline.Period();
            long mediaPeriodPositionUsForContent2 = 0;
            for (int i2 = 0; i2 < i + 1; i2++) {
                this.timeline.getPeriod(i2, period2, true);
                AdPlaybackState adPlaybackState2 = this.adPlaybackStates.get(period2.uid);
                adPlaybackState2.getClass();
                if (i2 == 0) {
                    mediaPeriodPositionUsForContent2 = -ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(-period2.positionInWindowUs, -1, adPlaybackState2);
                }
                if (i2 != i) {
                    mediaPeriodPositionUsForContent2 = ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(period2.durationUs, -1, adPlaybackState2) + mediaPeriodPositionUsForContent2;
                }
            }
            period.set(period.id, period.uid, period.windowIndex, mediaPeriodPositionUsForContent, mediaPeriodPositionUsForContent2, adPlaybackState, period.isPlaceholder);
            return period;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            super.getWindow(i, window, j);
            Timeline.Period period = new Timeline.Period();
            getPeriod(window.firstPeriodIndex, period, true);
            Object obj = period.uid;
            obj.getClass();
            AdPlaybackState adPlaybackState = this.adPlaybackStates.get(obj);
            adPlaybackState.getClass();
            long mediaPeriodPositionUsForContent = ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(window.positionInFirstPeriodUs, -1, adPlaybackState);
            if (window.durationUs == -9223372036854775807L) {
                long j2 = adPlaybackState.contentDurationUs;
                if (j2 != -9223372036854775807L) {
                    window.durationUs = j2 - mediaPeriodPositionUsForContent;
                }
            } else {
                Timeline.Period period2 = this.timeline.getPeriod(window.lastPeriodIndex, period, true);
                long j3 = period2.positionInWindowUs;
                AdPlaybackState adPlaybackState2 = this.adPlaybackStates.get(period2.uid);
                adPlaybackState2.getClass();
                getPeriod(window.lastPeriodIndex, period, false);
                window.durationUs = period.positionInWindowUs + ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(window.durationUs - j3, -1, adPlaybackState2);
            }
            window.positionInFirstPeriodUs = mediaPeriodPositionUsForContent;
            return window;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SharedMediaPeriod implements MediaPeriod.Callback {
        public final MediaPeriod actualMediaPeriod;
        public AdPlaybackState adPlaybackState;
        public boolean hasStartedPreparing;
        public boolean isPrepared;

        @Nullable
        public MediaPeriodImpl loadingPeriod;
        public final Object periodUid;
        public final List<MediaPeriodImpl> mediaPeriods = new ArrayList();
        public final Map<Long, Pair<LoadEventInfo, MediaLoadData>> activeLoads = new HashMap();
        public ExoTrackSelection[] trackSelections = new ExoTrackSelection[0];
        public SampleStream[] sampleStreams = new SampleStream[0];
        public MediaLoadData[] lastDownstreamFormatChangeData = new MediaLoadData[0];

        public SharedMediaPeriod(MediaPeriod mediaPeriod, Object obj, AdPlaybackState adPlaybackState) {
            this.actualMediaPeriod = mediaPeriod;
            this.periodUid = obj;
            this.adPlaybackState = adPlaybackState;
        }

        public void add(MediaPeriodImpl mediaPeriodImpl) {
            this.mediaPeriods.add(mediaPeriodImpl);
        }

        public boolean canReuseMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, long j) {
            MediaPeriodImpl mediaPeriodImpl = (MediaPeriodImpl) Iterables.getLast(this.mediaPeriods);
            return ServerSideAdInsertionUtil.getStreamPositionUs(j, mediaPeriodId, this.adPlaybackState) == ServerSideAdInsertionUtil.getStreamPositionUs(ServerSideAdInsertionMediaSource.getMediaPeriodEndPositionUs(mediaPeriodImpl, this.adPlaybackState), mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public boolean continueLoading(MediaPeriodImpl mediaPeriodImpl, LoadingInfo loadingInfo) {
            MediaPeriodImpl mediaPeriodImpl2 = this.loadingPeriod;
            if (mediaPeriodImpl2 != null && !mediaPeriodImpl.equals(mediaPeriodImpl2)) {
                for (Pair<LoadEventInfo, MediaLoadData> pair : this.activeLoads.values()) {
                    mediaPeriodImpl2.mediaSourceEventDispatcher.loadCompleted((LoadEventInfo) pair.first, ServerSideAdInsertionMediaSource.correctMediaLoadData(mediaPeriodImpl2, (MediaLoadData) pair.second, this.adPlaybackState));
                    mediaPeriodImpl.mediaSourceEventDispatcher.loadStarted((LoadEventInfo) pair.first, ServerSideAdInsertionMediaSource.correctMediaLoadData(mediaPeriodImpl, (MediaLoadData) pair.second, this.adPlaybackState));
                }
            }
            this.loadingPeriod = mediaPeriodImpl;
            long streamPositionUsWithNotYetStartedHandling = getStreamPositionUsWithNotYetStartedHandling(mediaPeriodImpl, loadingInfo.playbackPositionUs);
            MediaPeriod mediaPeriod = this.actualMediaPeriod;
            LoadingInfo.Builder builder = new LoadingInfo.Builder(loadingInfo);
            builder.playbackPositionUs = streamPositionUsWithNotYetStartedHandling;
            return mediaPeriod.continueLoading(new LoadingInfo(builder));
        }

        public void discardBuffer(MediaPeriodImpl mediaPeriodImpl, long j, boolean z) {
            this.actualMediaPeriod.discardBuffer(ServerSideAdInsertionUtil.getStreamPositionUs(j, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState), z);
        }

        public final int findMatchingStreamIndex(MediaLoadData mediaLoadData) {
            String str;
            if (mediaLoadData.trackFormat == null) {
                return -1;
            }
            int i = 0;
            loop0: while (true) {
                ExoTrackSelection[] exoTrackSelectionArr = this.trackSelections;
                if (i >= exoTrackSelectionArr.length) {
                    return -1;
                }
                ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i];
                if (exoTrackSelection != null) {
                    TrackGroup trackGroup = exoTrackSelection.getTrackGroup();
                    boolean z = mediaLoadData.trackType == 0 && trackGroup.equals(this.actualMediaPeriod.getTrackGroups().get(0));
                    for (int i2 = 0; i2 < trackGroup.length; i2++) {
                        Format format = trackGroup.formats[i2];
                        if (format.equals(mediaLoadData.trackFormat) || (z && (str = format.id) != null && str.equals(mediaLoadData.trackFormat.id))) {
                            break loop0;
                        }
                    }
                }
                i++;
            }
            return i;
        }

        public long getAdjustedSeekPositionUs(MediaPeriodImpl mediaPeriodImpl, long j, SeekParameters seekParameters) {
            return ServerSideAdInsertionUtil.getMediaPeriodPositionUs(this.actualMediaPeriod.getAdjustedSeekPositionUs(ServerSideAdInsertionUtil.getStreamPositionUs(j, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState), seekParameters), mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public long getBufferedPositionUs(MediaPeriodImpl mediaPeriodImpl) {
            return getMediaPeriodPositionUsWithEndOfSourceHandling(mediaPeriodImpl, this.actualMediaPeriod.getBufferedPositionUs());
        }

        @Nullable
        public MediaPeriodImpl getMediaPeriodForEvent(@Nullable MediaLoadData mediaLoadData) {
            if (mediaLoadData == null || mediaLoadData.mediaStartTimeMs == -9223372036854775807L) {
                return null;
            }
            for (int i = 0; i < this.mediaPeriods.size(); i++) {
                MediaPeriodImpl mediaPeriodImpl = this.mediaPeriods.get(i);
                if (mediaPeriodImpl.isPrepared) {
                    long mediaPeriodPositionUs = ServerSideAdInsertionUtil.getMediaPeriodPositionUs(Util.msToUs(mediaLoadData.mediaStartTimeMs), mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
                    long mediaPeriodEndPositionUs = ServerSideAdInsertionMediaSource.getMediaPeriodEndPositionUs(mediaPeriodImpl, this.adPlaybackState);
                    if (mediaPeriodPositionUs >= 0 && mediaPeriodPositionUs < mediaPeriodEndPositionUs) {
                        return mediaPeriodImpl;
                    }
                }
            }
            return null;
        }

        public final long getMediaPeriodPositionUsWithEndOfSourceHandling(MediaPeriodImpl mediaPeriodImpl, long j) {
            if (j == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            long mediaPeriodPositionUs = ServerSideAdInsertionUtil.getMediaPeriodPositionUs(j, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
            if (mediaPeriodPositionUs >= ServerSideAdInsertionMediaSource.getMediaPeriodEndPositionUs(mediaPeriodImpl, this.adPlaybackState)) {
                return Long.MIN_VALUE;
            }
            return mediaPeriodPositionUs;
        }

        public long getNextLoadPositionUs(MediaPeriodImpl mediaPeriodImpl) {
            return getMediaPeriodPositionUsWithEndOfSourceHandling(mediaPeriodImpl, this.actualMediaPeriod.getNextLoadPositionUs());
        }

        public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
            return this.actualMediaPeriod.getStreamKeys(list);
        }

        public final long getStreamPositionUsWithNotYetStartedHandling(MediaPeriodImpl mediaPeriodImpl, long j) {
            long j2 = mediaPeriodImpl.lastStartPositionUs;
            return j < j2 ? ServerSideAdInsertionUtil.getStreamPositionUs(j2, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState) - (mediaPeriodImpl.lastStartPositionUs - j) : ServerSideAdInsertionUtil.getStreamPositionUs(j, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public TrackGroupArray getTrackGroups() {
            return this.actualMediaPeriod.getTrackGroups();
        }

        public boolean isLoading(MediaPeriodImpl mediaPeriodImpl) {
            return mediaPeriodImpl.equals(this.loadingPeriod) && this.actualMediaPeriod.isLoading();
        }

        public boolean isReady(int i) {
            SampleStream sampleStream = this.sampleStreams[i];
            Util.castNonNull(sampleStream);
            return sampleStream.isReady();
        }

        public boolean isUnused() {
            return this.mediaPeriods.isEmpty();
        }

        public final void maybeNotifyDownstreamFormatChanged(MediaPeriodImpl mediaPeriodImpl, int i) {
            MediaLoadData mediaLoadData;
            boolean[] zArr = mediaPeriodImpl.hasNotifiedDownstreamFormatChange;
            if (zArr[i] || (mediaLoadData = this.lastDownstreamFormatChangeData[i]) == null) {
                return;
            }
            zArr[i] = true;
            mediaPeriodImpl.mediaSourceEventDispatcher.downstreamFormatChanged(ServerSideAdInsertionMediaSource.correctMediaLoadData(mediaPeriodImpl, mediaLoadData, this.adPlaybackState));
        }

        public void maybeThrowError(int i) throws IOException {
            SampleStream sampleStream = this.sampleStreams[i];
            Util.castNonNull(sampleStream);
            sampleStream.maybeThrowError();
        }

        public void maybeThrowPrepareError() throws IOException {
            this.actualMediaPeriod.maybeThrowPrepareError();
        }

        public void onDownstreamFormatChanged(MediaPeriodImpl mediaPeriodImpl, MediaLoadData mediaLoadData) {
            int iFindMatchingStreamIndex = findMatchingStreamIndex(mediaLoadData);
            if (iFindMatchingStreamIndex != -1) {
                this.lastDownstreamFormatChangeData[iFindMatchingStreamIndex] = mediaLoadData;
                mediaPeriodImpl.hasNotifiedDownstreamFormatChange[iFindMatchingStreamIndex] = true;
            }
        }

        public void onLoadFinished(LoadEventInfo loadEventInfo) {
            this.activeLoads.remove(Long.valueOf(loadEventInfo.loadTaskId));
        }

        public void onLoadStarted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            this.activeLoads.put(Long.valueOf(loadEventInfo.loadTaskId), Pair.create(loadEventInfo, mediaLoadData));
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
        public void onPrepared(MediaPeriod mediaPeriod) {
            this.isPrepared = true;
            for (int i = 0; i < this.mediaPeriods.size(); i++) {
                this.mediaPeriods.get(i).onPrepared();
            }
        }

        public void prepare(MediaPeriodImpl mediaPeriodImpl, long j) {
            mediaPeriodImpl.lastStartPositionUs = j;
            if (this.hasStartedPreparing) {
                if (this.isPrepared) {
                    mediaPeriodImpl.onPrepared();
                }
            } else {
                this.hasStartedPreparing = true;
                this.actualMediaPeriod.prepare(this, ServerSideAdInsertionUtil.getStreamPositionUs(j, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState));
            }
        }

        public int readData(MediaPeriodImpl mediaPeriodImpl, int i, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2) {
            long bufferedPositionUs = getBufferedPositionUs(mediaPeriodImpl);
            SampleStream sampleStream = this.sampleStreams[i];
            Util.castNonNull(sampleStream);
            int data = sampleStream.readData(formatHolder, decoderInputBuffer, i2 | 5);
            long mediaPeriodPositionUsWithEndOfSourceHandling = getMediaPeriodPositionUsWithEndOfSourceHandling(mediaPeriodImpl, decoderInputBuffer.timeUs);
            if ((data == -4 && mediaPeriodPositionUsWithEndOfSourceHandling == Long.MIN_VALUE) || (data == -3 && bufferedPositionUs == Long.MIN_VALUE && !decoderInputBuffer.waitingForKeys)) {
                maybeNotifyDownstreamFormatChanged(mediaPeriodImpl, i);
                decoderInputBuffer.clear();
                decoderInputBuffer.addFlag(4);
                return -4;
            }
            if (data == -4) {
                maybeNotifyDownstreamFormatChanged(mediaPeriodImpl, i);
                this.sampleStreams[i].readData(formatHolder, decoderInputBuffer, i2);
                decoderInputBuffer.timeUs = mediaPeriodPositionUsWithEndOfSourceHandling;
            }
            return data;
        }

        public long readDiscontinuity(MediaPeriodImpl mediaPeriodImpl) {
            if (!mediaPeriodImpl.equals(this.mediaPeriods.get(0))) {
                return -9223372036854775807L;
            }
            long discontinuity = this.actualMediaPeriod.readDiscontinuity();
            if (discontinuity == -9223372036854775807L) {
                return -9223372036854775807L;
            }
            return ServerSideAdInsertionUtil.getMediaPeriodPositionUs(discontinuity, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public void reevaluateBuffer(MediaPeriodImpl mediaPeriodImpl, long j) {
            this.actualMediaPeriod.reevaluateBuffer(getStreamPositionUsWithNotYetStartedHandling(mediaPeriodImpl, j));
        }

        public void release(MediaSource mediaSource) {
            mediaSource.releasePeriod(this.actualMediaPeriod);
        }

        public void remove(MediaPeriodImpl mediaPeriodImpl) {
            if (mediaPeriodImpl.equals(this.loadingPeriod)) {
                this.loadingPeriod = null;
                this.activeLoads.clear();
            }
            this.mediaPeriods.remove(mediaPeriodImpl);
        }

        public long seekToUs(MediaPeriodImpl mediaPeriodImpl, long j) {
            return ServerSideAdInsertionUtil.getMediaPeriodPositionUs(this.actualMediaPeriod.seekToUs(ServerSideAdInsertionUtil.getStreamPositionUs(j, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState)), mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public long selectTracks(MediaPeriodImpl mediaPeriodImpl, ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
            mediaPeriodImpl.lastStartPositionUs = j;
            if (!mediaPeriodImpl.equals(this.mediaPeriods.get(0))) {
                for (int i = 0; i < exoTrackSelectionArr.length; i++) {
                    ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i];
                    boolean z = true;
                    if (exoTrackSelection != null) {
                        if (zArr[i] && sampleStreamArr[i] != null) {
                            z = false;
                        }
                        zArr2[i] = z;
                        if (z) {
                            sampleStreamArr[i] = Util.areEqual(this.trackSelections[i], exoTrackSelection) ? new SampleStreamImpl(mediaPeriodImpl, i) : new EmptySampleStream();
                        }
                    } else {
                        sampleStreamArr[i] = null;
                        zArr2[i] = true;
                    }
                }
                return j;
            }
            this.trackSelections = (ExoTrackSelection[]) Arrays.copyOf(exoTrackSelectionArr, exoTrackSelectionArr.length);
            long streamPositionUs = ServerSideAdInsertionUtil.getStreamPositionUs(j, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
            SampleStream[] sampleStreamArr2 = this.sampleStreams;
            SampleStream[] sampleStreamArr3 = sampleStreamArr2.length == 0 ? new SampleStream[exoTrackSelectionArr.length] : (SampleStream[]) Arrays.copyOf(sampleStreamArr2, sampleStreamArr2.length);
            long jSelectTracks = this.actualMediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr3, zArr2, streamPositionUs);
            this.sampleStreams = (SampleStream[]) Arrays.copyOf(sampleStreamArr3, sampleStreamArr3.length);
            this.lastDownstreamFormatChangeData = (MediaLoadData[]) Arrays.copyOf(this.lastDownstreamFormatChangeData, sampleStreamArr3.length);
            for (int i2 = 0; i2 < sampleStreamArr3.length; i2++) {
                if (sampleStreamArr3[i2] == null) {
                    sampleStreamArr[i2] = null;
                    this.lastDownstreamFormatChangeData[i2] = null;
                } else if (sampleStreamArr[i2] == null || zArr2[i2]) {
                    sampleStreamArr[i2] = new SampleStreamImpl(mediaPeriodImpl, i2);
                    this.lastDownstreamFormatChangeData[i2] = null;
                }
            }
            return ServerSideAdInsertionUtil.getMediaPeriodPositionUs(jSelectTracks, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
        }

        public int skipData(MediaPeriodImpl mediaPeriodImpl, int i, long j) {
            long streamPositionUs = ServerSideAdInsertionUtil.getStreamPositionUs(j, mediaPeriodImpl.mediaPeriodId, this.adPlaybackState);
            SampleStream sampleStream = this.sampleStreams[i];
            Util.castNonNull(sampleStream);
            return sampleStream.skipData(streamPositionUs);
        }

        public void updateAdPlaybackState(AdPlaybackState adPlaybackState) {
            this.adPlaybackState = adPlaybackState;
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
            MediaPeriodImpl mediaPeriodImpl = this.loadingPeriod;
            if (mediaPeriodImpl == null) {
                return;
            }
            MediaPeriod.Callback callback = mediaPeriodImpl.callback;
            callback.getClass();
            callback.onContinueLoadingRequested(this.loadingPeriod);
        }
    }

    public static void $r8$lambda$YOLDTFL5rwwshBYu0RLsAZQMw8E(ServerSideAdInsertionMediaSource serverSideAdInsertionMediaSource, ImmutableMap immutableMap, Timeline timeline) {
        AdPlaybackState adPlaybackState;
        for (SharedMediaPeriod sharedMediaPeriod : serverSideAdInsertionMediaSource.mediaPeriods.values()) {
            AdPlaybackState adPlaybackState2 = (AdPlaybackState) immutableMap.get(sharedMediaPeriod.periodUid);
            if (adPlaybackState2 != null) {
                sharedMediaPeriod.adPlaybackState = adPlaybackState2;
            }
        }
        SharedMediaPeriod sharedMediaPeriod2 = serverSideAdInsertionMediaSource.lastUsedMediaPeriod;
        if (sharedMediaPeriod2 != null && (adPlaybackState = (AdPlaybackState) immutableMap.get(sharedMediaPeriod2.periodUid)) != null) {
            serverSideAdInsertionMediaSource.lastUsedMediaPeriod.adPlaybackState = adPlaybackState;
        }
        serverSideAdInsertionMediaSource.adPlaybackStates = immutableMap;
        serverSideAdInsertionMediaSource.refreshSourceInfo(new ServerSideAdInsertionTimeline(timeline, immutableMap));
    }

    public ServerSideAdInsertionMediaSource(MediaSource mediaSource, @Nullable AdPlaybackStateUpdater adPlaybackStateUpdater) {
        this.mediaSource = mediaSource;
        this.adPlaybackStateUpdater = adPlaybackStateUpdater;
    }

    public static MediaLoadData correctMediaLoadData(MediaPeriodImpl mediaPeriodImpl, MediaLoadData mediaLoadData, AdPlaybackState adPlaybackState) {
        return new MediaLoadData(mediaLoadData.dataType, mediaLoadData.trackType, mediaLoadData.trackFormat, mediaLoadData.trackSelectionReason, mediaLoadData.trackSelectionData, correctMediaLoadDataPositionMs(mediaLoadData.mediaStartTimeMs, mediaPeriodImpl, adPlaybackState), correctMediaLoadDataPositionMs(mediaLoadData.mediaEndTimeMs, mediaPeriodImpl, adPlaybackState));
    }

    public static long correctMediaLoadDataPositionMs(long j, MediaPeriodImpl mediaPeriodImpl, AdPlaybackState adPlaybackState) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        long jMsToUs = Util.msToUs(j);
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodImpl.mediaPeriodId;
        return Util.usToMs(mediaPeriodId.isAd() ? ServerSideAdInsertionUtil.getMediaPeriodPositionUsForAd(jMsToUs, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, adPlaybackState) : ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(jMsToUs, -1, adPlaybackState));
    }

    public static long getMediaPeriodEndPositionUs(MediaPeriodImpl mediaPeriodImpl, AdPlaybackState adPlaybackState) {
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodImpl.mediaPeriodId;
        if (mediaPeriodId.isAd()) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(mediaPeriodId.adGroupIndex);
            if (adGroup.count == -1) {
                return 0L;
            }
            return adGroup.durationsUs[mediaPeriodId.adIndexInAdGroup];
        }
        int i = mediaPeriodId.nextAdGroupIndex;
        if (i == -1) {
            return Long.MAX_VALUE;
        }
        long j = adPlaybackState.getAdGroup(i).timeUs;
        if (j == Long.MIN_VALUE) {
            return Long.MAX_VALUE;
        }
        return j;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return this.mediaSource.canUpdateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        SharedMediaPeriod sharedMediaPeriod;
        Pair<Long, Object> pair = new Pair<>(Long.valueOf(mediaPeriodId.windowSequenceNumber), mediaPeriodId.periodUid);
        SharedMediaPeriod sharedMediaPeriod2 = this.lastUsedMediaPeriod;
        boolean z = false;
        if (sharedMediaPeriod2 != null) {
            if (sharedMediaPeriod2.periodUid.equals(mediaPeriodId.periodUid)) {
                sharedMediaPeriod = this.lastUsedMediaPeriod;
                this.mediaPeriods.put(pair, sharedMediaPeriod);
                z = true;
            } else {
                this.lastUsedMediaPeriod.release(this.mediaSource);
                sharedMediaPeriod = null;
            }
            this.lastUsedMediaPeriod = null;
        } else {
            sharedMediaPeriod = null;
        }
        if (sharedMediaPeriod == null && ((sharedMediaPeriod = (SharedMediaPeriod) Iterables.getLast(this.mediaPeriods.get(pair), null)) == null || !sharedMediaPeriod.canReuseMediaPeriod(mediaPeriodId, j))) {
            AdPlaybackState adPlaybackState = this.adPlaybackStates.get(mediaPeriodId.periodUid);
            adPlaybackState.getClass();
            SharedMediaPeriod sharedMediaPeriod3 = new SharedMediaPeriod(this.mediaSource.createPeriod(new MediaSource.MediaPeriodId(mediaPeriodId.periodUid, mediaPeriodId.windowSequenceNumber), allocator, ServerSideAdInsertionUtil.getStreamPositionUs(j, mediaPeriodId, adPlaybackState)), mediaPeriodId.periodUid, adPlaybackState);
            this.mediaPeriods.put(pair, sharedMediaPeriod3);
            sharedMediaPeriod = sharedMediaPeriod3;
        }
        MediaPeriodImpl mediaPeriodImpl = new MediaPeriodImpl(sharedMediaPeriod, mediaPeriodId, createEventDispatcher(mediaPeriodId), createDrmEventDispatcher(mediaPeriodId));
        sharedMediaPeriod.add(mediaPeriodImpl);
        if (z && sharedMediaPeriod.trackSelections.length > 0) {
            mediaPeriodImpl.seekToUs(j);
        }
        return mediaPeriodImpl;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void disableInternal() {
        releaseLastUsedMediaPeriod();
        this.mediaSource.disable(this);
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void enableInternal() {
        this.mediaSource.enable(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaSource.getMediaItem();
    }

    @Nullable
    public final MediaPeriodImpl getMediaPeriodForEvent(@Nullable MediaSource.MediaPeriodId mediaPeriodId, @Nullable MediaLoadData mediaLoadData, boolean z) {
        if (mediaPeriodId == null) {
            return null;
        }
        List<SharedMediaPeriod> list = this.mediaPeriods.get(new Pair<>(Long.valueOf(mediaPeriodId.windowSequenceNumber), mediaPeriodId.periodUid));
        if (list.isEmpty()) {
            return null;
        }
        if (z) {
            SharedMediaPeriod sharedMediaPeriod = (SharedMediaPeriod) Iterables.getLast(list);
            MediaPeriodImpl mediaPeriodImpl = sharedMediaPeriod.loadingPeriod;
            return mediaPeriodImpl != null ? mediaPeriodImpl : (MediaPeriodImpl) Iterables.getLast(sharedMediaPeriod.mediaPeriods);
        }
        for (int i = 0; i < list.size(); i++) {
            MediaPeriodImpl mediaPeriodForEvent = list.get(i).getMediaPeriodForEvent(mediaLoadData);
            if (mediaPeriodForEvent != null) {
                return mediaPeriodForEvent;
            }
        }
        return list.get(0).mediaPeriods.get(0);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.mediaSource.maybeThrowSourceInfoRefreshError();
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onDownstreamFormatChanged(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, false);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.downstreamFormatChanged(mediaLoadData);
            return;
        }
        mediaPeriodForEvent.sharedPeriod.onDownstreamFormatChanged(mediaPeriodForEvent, mediaLoadData);
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.downstreamFormatChanged(correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState));
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmKeysLoaded(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, false);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmKeysLoaded();
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmKeysLoaded();
        }
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmKeysRemoved(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, false);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmKeysRemoved();
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmKeysRemoved();
        }
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmKeysRestored(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, false);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmKeysRestored();
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmKeysRestored();
        }
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public /* synthetic */ void onDrmSessionAcquired(int i, MediaSource.MediaPeriodId mediaPeriodId) {
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmSessionManagerError(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, false);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmSessionManagerError(exc);
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmSessionManagerError(exc);
        }
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmSessionReleased(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, false);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmSessionReleased();
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmSessionReleased();
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onLoadCanceled(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, true);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.loadCanceled(loadEventInfo, mediaLoadData);
            return;
        }
        mediaPeriodForEvent.sharedPeriod.onLoadFinished(loadEventInfo);
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.loadCanceled(loadEventInfo, correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState));
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onLoadCompleted(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, true);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.loadCompleted(loadEventInfo, mediaLoadData);
            return;
        }
        mediaPeriodForEvent.sharedPeriod.onLoadFinished(loadEventInfo);
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.loadCompleted(loadEventInfo, correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState));
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onLoadError(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, true);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.loadError(loadEventInfo, mediaLoadData, iOException, z);
            return;
        }
        if (z) {
            mediaPeriodForEvent.sharedPeriod.onLoadFinished(loadEventInfo);
        }
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.loadError(loadEventInfo, correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState), iOException, z);
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onLoadStarted(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, true);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.loadStarted(loadEventInfo, mediaLoadData);
            return;
        }
        mediaPeriodForEvent.sharedPeriod.onLoadStarted(loadEventInfo, mediaLoadData);
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.loadStarted(loadEventInfo, correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState));
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.MediaSourceCaller
    public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
        AdPlaybackStateUpdater adPlaybackStateUpdater = this.adPlaybackStateUpdater;
        if ((adPlaybackStateUpdater == null || !adPlaybackStateUpdater.onAdPlaybackStateUpdateRequested(timeline)) && !this.adPlaybackStates.isEmpty()) {
            refreshSourceInfo(new ServerSideAdInsertionTimeline(timeline, this.adPlaybackStates));
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public void onUpstreamDiscarded(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, mediaLoadData, false);
        if (mediaPeriodForEvent == null) {
            this.mediaSourceEventDispatcherWithoutId.upstreamDiscarded(mediaLoadData);
            return;
        }
        MediaSourceEventListener.EventDispatcher eventDispatcher = mediaPeriodForEvent.mediaSourceEventDispatcher;
        AdPlaybackState adPlaybackState = this.adPlaybackStates.get(mediaPeriodForEvent.mediaPeriodId.periodUid);
        adPlaybackState.getClass();
        eventDispatcher.upstreamDiscarded(correctMediaLoadData(mediaPeriodForEvent, mediaLoadData, adPlaybackState));
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        Handler handlerCreateHandlerForCurrentLooper = Util.createHandlerForCurrentLooper();
        synchronized (this) {
            this.playbackHandler = handlerCreateHandlerForCurrentLooper;
        }
        this.mediaSource.addEventListener(handlerCreateHandlerForCurrentLooper, this);
        this.mediaSource.addDrmEventListener(handlerCreateHandlerForCurrentLooper, this);
        MediaSource mediaSource = this.mediaSource;
        PlayerId playerId = this.playerId;
        Assertions.checkStateNotNull(playerId);
        mediaSource.prepareSource(this, transferListener, playerId);
    }

    public final void releaseLastUsedMediaPeriod() {
        SharedMediaPeriod sharedMediaPeriod = this.lastUsedMediaPeriod;
        if (sharedMediaPeriod != null) {
            sharedMediaPeriod.release(this.mediaSource);
            this.lastUsedMediaPeriod = null;
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaPeriodImpl mediaPeriodImpl = (MediaPeriodImpl) mediaPeriod;
        mediaPeriodImpl.sharedPeriod.remove(mediaPeriodImpl);
        if (mediaPeriodImpl.sharedPeriod.mediaPeriods.isEmpty()) {
            this.mediaPeriods.remove(new Pair(Long.valueOf(mediaPeriodImpl.mediaPeriodId.windowSequenceNumber), mediaPeriodImpl.mediaPeriodId.periodUid), mediaPeriodImpl.sharedPeriod);
            if (this.mediaPeriods.isEmpty()) {
                this.lastUsedMediaPeriod = mediaPeriodImpl.sharedPeriod;
            } else {
                mediaPeriodImpl.sharedPeriod.release(this.mediaSource);
            }
        }
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        releaseLastUsedMediaPeriod();
        synchronized (this) {
            this.playbackHandler = null;
        }
        this.mediaSource.releaseSource(this);
        this.mediaSource.removeEventListener(this);
        this.mediaSource.removeDrmEventListener(this);
    }

    public void setAdPlaybackStates(final ImmutableMap<Object, AdPlaybackState> immutableMap, final Timeline timeline) {
        Assertions.checkArgument(!immutableMap.isEmpty());
        Object obj = immutableMap.values().asList().get(0).adsId;
        obj.getClass();
        UnmodifiableIterator<Map.Entry<Object, AdPlaybackState>> it = immutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Object, AdPlaybackState> next = it.next();
            Object key = next.getKey();
            AdPlaybackState value = next.getValue();
            Assertions.checkArgument(obj.equals(value.adsId));
            AdPlaybackState adPlaybackState = this.adPlaybackStates.get(key);
            if (adPlaybackState != null) {
                for (int i = value.removedAdGroupCount; i < value.adGroupCount; i++) {
                    AdPlaybackState.AdGroup adGroup = value.getAdGroup(i);
                    Assertions.checkArgument(adGroup.isServerSideInserted);
                    if (i < adPlaybackState.adGroupCount && ServerSideAdInsertionUtil.getAdCountInGroup(value, i) < ServerSideAdInsertionUtil.getAdCountInGroup(adPlaybackState, i)) {
                        AdPlaybackState.AdGroup adGroup2 = value.getAdGroup(i + 1);
                        Assertions.checkArgument(adGroup.contentResumeOffsetUs + adGroup2.contentResumeOffsetUs == adPlaybackState.getAdGroup(i).contentResumeOffsetUs);
                        Assertions.checkArgument(adGroup.timeUs + adGroup.contentResumeOffsetUs == adGroup2.timeUs);
                    }
                    if (adGroup.timeUs == Long.MIN_VALUE) {
                        Assertions.checkArgument(ServerSideAdInsertionUtil.getAdCountInGroup(value, i) == 0);
                    }
                }
            }
        }
        synchronized (this) {
            try {
                Handler handler = this.playbackHandler;
                if (handler == null) {
                    this.adPlaybackStates = immutableMap;
                } else {
                    handler.post(new Runnable() { // from class: androidx.media3.exoplayer.source.ads.ServerSideAdInsertionMediaSource$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            ServerSideAdInsertionMediaSource.$r8$lambda$YOLDTFL5rwwshBYu0RLsAZQMw8E(this.f$0, immutableMap, timeline);
                        }
                    });
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void updateMediaItem(MediaItem mediaItem) {
        this.mediaSource.updateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public void onDrmSessionAcquired(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, int i2) {
        MediaPeriodImpl mediaPeriodForEvent = getMediaPeriodForEvent(mediaPeriodId, null, true);
        if (mediaPeriodForEvent == null) {
            this.drmEventDispatcherWithoutId.drmSessionAcquired(i2);
        } else {
            mediaPeriodForEvent.drmEventDispatcher.drmSessionAcquired(i2);
        }
    }
}
