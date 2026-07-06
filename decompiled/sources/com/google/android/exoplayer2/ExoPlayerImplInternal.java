package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda3;
import com.google.android.exoplayer2.DefaultMediaClock;
import com.google.android.exoplayer2.MediaSourceList;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.SystemHandlerWrapper;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSourceList.MediaSourceListInfoRefreshListener, DefaultMediaClock.PlaybackParametersListener, PlayerMessage.Sender {
    public static final int ACTIVE_INTERVAL_MS = 10;
    public static final int IDLE_INTERVAL_MS = 1000;
    public static final int MSG_ADD_MEDIA_SOURCES = 18;
    public static final int MSG_ATTEMPT_RENDERER_ERROR_RECOVERY = 25;
    public static final int MSG_DO_SOME_WORK = 2;
    public static final int MSG_MOVE_MEDIA_SOURCES = 19;
    public static final int MSG_PERIOD_PREPARED = 8;
    public static final int MSG_PLAYBACK_PARAMETERS_CHANGED_INTERNAL = 16;
    public static final int MSG_PLAYLIST_UPDATE_REQUESTED = 22;
    public static final int MSG_PREPARE = 0;
    public static final int MSG_RELEASE = 7;
    public static final int MSG_REMOVE_MEDIA_SOURCES = 20;
    public static final int MSG_SEEK_TO = 3;
    public static final int MSG_SEND_MESSAGE = 14;
    public static final int MSG_SEND_MESSAGE_TO_TARGET_THREAD = 15;
    public static final int MSG_SET_FOREGROUND_MODE = 13;
    public static final int MSG_SET_MEDIA_SOURCES = 17;
    public static final int MSG_SET_OFFLOAD_SCHEDULING_ENABLED = 24;
    public static final int MSG_SET_PAUSE_AT_END_OF_WINDOW = 23;
    public static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    public static final int MSG_SET_PLAY_WHEN_READY = 1;
    public static final int MSG_SET_REPEAT_MODE = 11;
    public static final int MSG_SET_SEEK_PARAMETERS = 5;
    public static final int MSG_SET_SHUFFLE_ENABLED = 12;
    public static final int MSG_SET_SHUFFLE_ORDER = 21;
    public static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 9;
    public static final int MSG_STOP = 6;
    public static final int MSG_TRACK_SELECTION_INVALIDATED = 10;
    public static final long PLAYBACK_BUFFER_EMPTY_THRESHOLD_US = 500000;
    public static final long PLAYBACK_STUCK_AFTER_MS = 4000;
    public static final String TAG = "ExoPlayerImplInternal";
    public final long backBufferDurationUs;
    public final BandwidthMeter bandwidthMeter;
    public final Clock clock;
    public boolean deliverPendingMessageAtStartPositionRequired;
    public final TrackSelectorResult emptyTrackSelectorResult;
    public int enabledRendererCount;
    public boolean foregroundMode;
    public final HandlerWrapper handler;

    @Nullable
    public final HandlerThread internalPlaybackThread;
    public boolean isRebuffering;
    public final LivePlaybackSpeedControl livePlaybackSpeedControl;
    public final LoadControl loadControl;
    public final DefaultMediaClock mediaClock;
    public final MediaSourceList mediaSourceList;
    public int nextPendingMessageIndexHint;
    public boolean offloadSchedulingEnabled;
    public boolean pauseAtEndOfWindow;

    @Nullable
    public SeekPosition pendingInitialSeekPosition;
    public final ArrayList<PendingMessageInfo> pendingMessages;
    public boolean pendingPauseAtEndOfPeriod;

    @Nullable
    public ExoPlaybackException pendingRecoverableRendererError;
    public final Timeline.Period period;
    public PlaybackInfo playbackInfo;
    public PlaybackInfoUpdate playbackInfoUpdate;
    public final PlaybackInfoUpdateListener playbackInfoUpdateListener;
    public final Looper playbackLooper;
    public final MediaPeriodQueue queue;
    public final long releaseTimeoutMs;
    public boolean released;
    public final RendererCapabilities[] rendererCapabilities;
    public long rendererPositionUs;
    public final Renderer[] renderers;
    public final Set<Renderer> renderersToReset;
    public int repeatMode;
    public boolean requestForRendererSleep;
    public final boolean retainBackBufferFromKeyframe;
    public SeekParameters seekParameters;
    public long setForegroundModeTimeoutMs;
    public boolean shouldContinueLoading;
    public boolean shuffleModeEnabled;
    public final TrackSelector trackSelector;
    public final Timeline.Window window;
    public final Logger log = new Logger(Logger.Module.Player, "ExoPlayerImplInternal");
    public long playbackMaybeBecameStuckAtMs = -9223372036854775807L;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaSourceListUpdateMessage {
        public final List<MediaSourceList.MediaSourceHolder> mediaSourceHolders;
        public final long positionUs;
        public final ShuffleOrder shuffleOrder;
        public final int windowIndex;

        public MediaSourceListUpdateMessage(List<MediaSourceList.MediaSourceHolder> list, ShuffleOrder shuffleOrder, int i, long j) {
            this.mediaSourceHolders = list;
            this.shuffleOrder = shuffleOrder;
            this.windowIndex = i;
            this.positionUs = j;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MoveMediaItemsMessage {
        public final int fromIndex;
        public final int newFromIndex;
        public final ShuffleOrder shuffleOrder;
        public final int toIndex;

        public MoveMediaItemsMessage(int i, int i2, int i3, ShuffleOrder shuffleOrder) {
            this.fromIndex = i;
            this.toIndex = i2;
            this.newFromIndex = i3;
            this.shuffleOrder = shuffleOrder;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;

        @Nullable
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.message = playerMessage;
        }

        public void setResolvedPosition(int i, long j, Object obj) {
            this.resolvedPeriodIndex = i;
            this.resolvedPeriodTimeUs = j;
            this.resolvedPeriodUid = obj;
        }

        @Override // java.lang.Comparable
        public int compareTo(PendingMessageInfo pendingMessageInfo) {
            Object obj = this.resolvedPeriodUid;
            if ((obj == null) != (pendingMessageInfo.resolvedPeriodUid == null)) {
                return obj != null ? -1 : 1;
            }
            if (obj == null) {
                return 0;
            }
            int i = this.resolvedPeriodIndex - pendingMessageInfo.resolvedPeriodIndex;
            return i != 0 ? i : Util.compareLong(this.resolvedPeriodTimeUs, pendingMessageInfo.resolvedPeriodTimeUs);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PlaybackInfoUpdate {
        public int discontinuityReason;
        public boolean hasPendingChange;
        public boolean hasPlayWhenReadyChangeReason;
        public int operationAcks;
        public int playWhenReadyChangeReason;
        public PlaybackInfo playbackInfo;
        public boolean positionDiscontinuity;

        public PlaybackInfoUpdate(PlaybackInfo playbackInfo) {
            this.playbackInfo = playbackInfo;
        }

        public void incrementPendingOperationAcks(int i) {
            this.hasPendingChange |= i > 0;
            this.operationAcks += i;
        }

        public void setPlayWhenReadyChangeReason(int i) {
            this.hasPendingChange = true;
            this.hasPlayWhenReadyChangeReason = true;
            this.playWhenReadyChangeReason = i;
        }

        public void setPlaybackInfo(PlaybackInfo playbackInfo) {
            this.hasPendingChange |= this.playbackInfo != playbackInfo;
            this.playbackInfo = playbackInfo;
        }

        public void setPositionDiscontinuity(int i) {
            if (this.positionDiscontinuity && this.discontinuityReason != 5) {
                Assertions.checkArgument(i == 5);
                return;
            }
            this.hasPendingChange = true;
            this.positionDiscontinuity = true;
            this.discontinuityReason = i;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface PlaybackInfoUpdateListener {
        void onPlaybackInfoUpdate(PlaybackInfoUpdate playbackInfoUpdate);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PositionUpdateForPlaylistChange {
        public final boolean endPlayback;
        public final boolean forceBufferingState;
        public final MediaSource.MediaPeriodId periodId;
        public final long periodPositionUs;
        public final long requestedContentPositionUs;
        public final boolean setTargetLiveOffset;

        public PositionUpdateForPlaylistChange(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2, boolean z, boolean z2, boolean z3) {
            this.periodId = mediaPeriodId;
            this.periodPositionUs = j;
            this.requestedContentPositionUs = j2;
            this.forceBufferingState = z;
            this.endPlayback = z2;
            this.setTargetLiveOffset = z3;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline, int i, long j) {
            this.timeline = timeline;
            this.windowIndex = i;
            this.windowPositionUs = j;
        }
    }

    public static /* synthetic */ void $r8$lambda$J9YTXTK1k0zCfZsi6DQfZMT2sYk(ExoPlayerImplInternal exoPlayerImplInternal, PlayerMessage playerMessage) {
        exoPlayerImplInternal.getClass();
        try {
            exoPlayerImplInternal.deliverMessage(playerMessage);
        } catch (ExoPlaybackException e) {
            Log.e("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector, TrackSelectorResult trackSelectorResult, LoadControl loadControl, BandwidthMeter bandwidthMeter, int i, boolean z, AnalyticsCollector analyticsCollector, SeekParameters seekParameters, LivePlaybackSpeedControl livePlaybackSpeedControl, long j, boolean z2, Looper looper, Clock clock, PlaybackInfoUpdateListener playbackInfoUpdateListener, PlayerId playerId, Looper looper2) {
        this.playbackInfoUpdateListener = playbackInfoUpdateListener;
        this.renderers = rendererArr;
        this.trackSelector = trackSelector;
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.loadControl = loadControl;
        this.bandwidthMeter = bandwidthMeter;
        this.repeatMode = i;
        this.shuffleModeEnabled = z;
        this.seekParameters = seekParameters;
        this.livePlaybackSpeedControl = livePlaybackSpeedControl;
        this.releaseTimeoutMs = j;
        this.setForegroundModeTimeoutMs = j;
        this.pauseAtEndOfWindow = z2;
        this.clock = clock;
        this.backBufferDurationUs = loadControl.getBackBufferDurationUs();
        this.retainBackBufferFromKeyframe = loadControl.retainBackBufferFromKeyframe();
        PlaybackInfo playbackInfoCreateDummy = PlaybackInfo.createDummy(trackSelectorResult);
        this.playbackInfo = playbackInfoCreateDummy;
        this.playbackInfoUpdate = new PlaybackInfoUpdate(playbackInfoCreateDummy);
        this.rendererCapabilities = new RendererCapabilities[rendererArr.length];
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            rendererArr[i2].init(i2, playerId);
            this.rendererCapabilities[i2] = rendererArr[i2].getCapabilities();
        }
        this.mediaClock = new DefaultMediaClock(this, clock);
        this.pendingMessages = new ArrayList<>();
        this.renderersToReset = Sets.newIdentityHashSet();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        trackSelector.init(this, bandwidthMeter);
        this.deliverPendingMessageAtStartPositionRequired = true;
        HandlerWrapper handlerWrapperCreateHandler = clock.createHandler(looper, null);
        this.queue = new MediaPeriodQueue(analyticsCollector, handlerWrapperCreateHandler);
        this.mediaSourceList = new MediaSourceList(this, analyticsCollector, handlerWrapperCreateHandler, playerId);
        if (looper2 != null) {
            this.internalPlaybackThread = null;
            this.playbackLooper = looper2;
        } else {
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:Playback", -16);
            this.internalPlaybackThread = handlerThread;
            handlerThread.start();
            this.playbackLooper = handlerThread.getLooper();
        }
        this.handler = clock.createHandler(this.playbackLooper, this);
    }

    public static Format[] getFormats(ExoTrackSelection exoTrackSelection) {
        int length = exoTrackSelection != null ? exoTrackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        for (int i = 0; i < length; i++) {
            formatArr[i] = exoTrackSelection.getFormat(i);
        }
        return formatArr;
    }

    public static String getStateString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNKNOWN" : "ENDED" : "READY" : "BUFFERING" : "IDLE";
    }

    public static boolean isIgnorableServerSideAdInsertionPeriodChange(boolean z, MediaSource.MediaPeriodId mediaPeriodId, long j, MediaSource.MediaPeriodId mediaPeriodId2, Timeline.Period period, long j2) {
        if (!z && j == j2 && mediaPeriodId.periodUid.equals(mediaPeriodId2.periodUid)) {
            return (mediaPeriodId.isAd() && period.isServerSideInsertedAdGroup(mediaPeriodId.adGroupIndex)) ? (period.getAdState(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup) == 4 || period.getAdState(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup) == 2) ? false : true : mediaPeriodId2.isAd() && period.isServerSideInsertedAdGroup(mediaPeriodId2.adGroupIndex);
        }
        return false;
    }

    public static boolean isRendererEnabled(Renderer renderer) {
        return renderer.getState() != 0;
    }

    public static boolean isUsingPlaceholderPeriod(PlaybackInfo playbackInfo, Timeline.Period period) {
        MediaSource.MediaPeriodId mediaPeriodId = playbackInfo.periodId;
        Timeline timeline = playbackInfo.timeline;
        return timeline.isEmpty() || timeline.getPeriodByUid(mediaPeriodId.periodUid, period).isPlaceholder;
    }

    public static void resolvePendingMessageEndOfStreamPosition(Timeline timeline, PendingMessageInfo pendingMessageInfo, Timeline.Window window, Timeline.Period period) {
        int i = timeline.getWindow(timeline.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period).windowIndex, window, 0L).lastPeriodIndex;
        Object obj = timeline.getPeriod(i, period, true).uid;
        long j = period.durationUs;
        pendingMessageInfo.setResolvedPosition(i, j != -9223372036854775807L ? j - 1 : Long.MAX_VALUE, obj);
    }

    public static boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo, Timeline timeline, Timeline timeline2, int i, boolean z, Timeline.Window window, Timeline.Period period) {
        Object obj = pendingMessageInfo.resolvedPeriodUid;
        if (obj == null) {
            long j = pendingMessageInfo.message.positionMs;
            long jMsToUs = j == Long.MIN_VALUE ? -9223372036854775807L : Util.msToUs(j);
            PlayerMessage playerMessage = pendingMessageInfo.message;
            Pair<Object, Long> pairResolveSeekPositionUs = resolveSeekPositionUs(timeline, new SeekPosition(playerMessage.timeline, playerMessage.mediaItemIndex, jMsToUs), false, i, z, window, period);
            if (pairResolveSeekPositionUs == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(timeline.getIndexOfPeriod(pairResolveSeekPositionUs.first), ((Long) pairResolveSeekPositionUs.second).longValue(), pairResolveSeekPositionUs.first);
            if (pendingMessageInfo.message.positionMs == Long.MIN_VALUE) {
                resolvePendingMessageEndOfStreamPosition(timeline, pendingMessageInfo, window, period);
            }
            return true;
        }
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        if (indexOfPeriod == -1) {
            return false;
        }
        if (pendingMessageInfo.message.positionMs == Long.MIN_VALUE) {
            resolvePendingMessageEndOfStreamPosition(timeline, pendingMessageInfo, window, period);
            return true;
        }
        pendingMessageInfo.resolvedPeriodIndex = indexOfPeriod;
        timeline2.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period);
        if (period.isPlaceholder && timeline2.getWindow(period.windowIndex, window, 0L).firstPeriodIndex == timeline2.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid)) {
            Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(window, period, timeline.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period).windowIndex, pendingMessageInfo.resolvedPeriodTimeUs + period.positionInWindowUs);
            pendingMessageInfo.setResolvedPosition(timeline.getIndexOfPeriod(periodPositionUs.first), ((Long) periodPositionUs.second).longValue(), periodPositionUs.first);
        }
        return true;
    }

    public static PositionUpdateForPlaylistChange resolvePositionForPlaylistChange(Timeline timeline, PlaybackInfo playbackInfo, @Nullable SeekPosition seekPosition, MediaPeriodQueue mediaPeriodQueue, int i, boolean z, Timeline.Window window, Timeline.Period period) {
        int i2;
        long j;
        long j2;
        long jLongValue;
        int firstWindowIndex;
        long jLongValue2;
        boolean z2;
        boolean z3;
        boolean z4;
        int firstWindowIndex2;
        boolean z5;
        Timeline timeline2;
        Timeline.Period period2;
        long j3;
        long j4;
        int i3;
        long jLongValue3;
        int firstWindowIndex3;
        boolean z6;
        boolean z7;
        boolean z8;
        if (timeline.isEmpty()) {
            return new PositionUpdateForPlaylistChange(PlaybackInfo.PLACEHOLDER_MEDIA_PERIOD_ID, 0L, -9223372036854775807L, false, true, false);
        }
        MediaSource.MediaPeriodId mediaPeriodId = playbackInfo.periodId;
        Object obj = mediaPeriodId.periodUid;
        boolean zIsUsingPlaceholderPeriod = isUsingPlaceholderPeriod(playbackInfo, period);
        long j5 = (playbackInfo.periodId.isAd() || zIsUsingPlaceholderPeriod) ? playbackInfo.requestedContentPositionUs : playbackInfo.positionUs;
        boolean z9 = false;
        if (seekPosition != null) {
            i2 = -1;
            j = -9223372036854775807L;
            Pair<Object, Long> pairResolveSeekPositionUs = resolveSeekPositionUs(timeline, seekPosition, true, i, z, window, period);
            if (pairResolveSeekPositionUs == null) {
                firstWindowIndex3 = timeline.getFirstWindowIndex(z);
                jLongValue3 = j5;
                z6 = false;
                z7 = false;
                z8 = true;
            } else {
                if (seekPosition.windowPositionUs == -9223372036854775807L) {
                    firstWindowIndex3 = timeline.getPeriodByUid(pairResolveSeekPositionUs.first, period).windowIndex;
                    jLongValue3 = j5;
                    z6 = false;
                } else {
                    obj = pairResolveSeekPositionUs.first;
                    jLongValue3 = ((Long) pairResolveSeekPositionUs.second).longValue();
                    firstWindowIndex3 = -1;
                    z6 = true;
                }
                z7 = playbackInfo.playbackState == 4;
                z8 = false;
            }
            firstWindowIndex = firstWindowIndex3;
            jLongValue = jLongValue3;
            z4 = z6;
            z2 = z7;
            z3 = z8;
            j2 = 0;
        } else {
            i2 = -1;
            j = -9223372036854775807L;
            if (playbackInfo.timeline.isEmpty()) {
                firstWindowIndex = timeline.getFirstWindowIndex(z);
            } else if (timeline.getIndexOfPeriod(obj) == -1) {
                Object objResolveSubsequentPeriod = resolveSubsequentPeriod(window, period, i, z, obj, playbackInfo.timeline, timeline);
                if (objResolveSubsequentPeriod == null) {
                    firstWindowIndex2 = timeline.getFirstWindowIndex(z);
                    z5 = true;
                } else {
                    firstWindowIndex2 = timeline.getPeriodByUid(objResolveSubsequentPeriod, period).windowIndex;
                    z5 = false;
                }
                firstWindowIndex = firstWindowIndex2;
                obj = obj;
                jLongValue = j5;
                z3 = z5;
                j2 = 0;
                z2 = false;
                z4 = false;
            } else if (j5 == -9223372036854775807L) {
                firstWindowIndex = timeline.getPeriodByUid(obj, period).windowIndex;
                obj = obj;
            } else if (zIsUsingPlaceholderPeriod) {
                playbackInfo.timeline.getPeriodByUid(mediaPeriodId.periodUid, period);
                j2 = 0;
                if (playbackInfo.timeline.getWindow(period.windowIndex, window, 0L).firstPeriodIndex == playbackInfo.timeline.getIndexOfPeriod(mediaPeriodId.periodUid)) {
                    Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(window, period, timeline.getPeriodByUid(obj, period).windowIndex, period.positionInWindowUs + j5);
                    obj = periodPositionUs.first;
                    jLongValue2 = ((Long) periodPositionUs.second).longValue();
                } else {
                    obj = obj;
                    jLongValue2 = j5;
                }
                jLongValue = jLongValue2;
                firstWindowIndex = -1;
                z2 = false;
                z3 = false;
                z4 = true;
            } else {
                j2 = 0;
                obj = obj;
                jLongValue = j5;
                firstWindowIndex = -1;
                z2 = false;
                z3 = false;
                z4 = false;
            }
            jLongValue = j5;
            j2 = 0;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        if (firstWindowIndex != i2) {
            period2 = period;
            Pair<Object, Long> periodPositionUs2 = timeline.getPeriodPositionUs(window, period2, firstWindowIndex, -9223372036854775807L);
            timeline2 = timeline;
            obj = periodPositionUs2.first;
            jLongValue = ((Long) periodPositionUs2.second).longValue();
            j3 = j;
        } else {
            timeline2 = timeline;
            period2 = period;
            j3 = jLongValue;
        }
        MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAdsAfterPeriodPositionChange = mediaPeriodQueue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(timeline2, obj, jLongValue);
        int i4 = mediaPeriodIdResolveMediaPeriodIdForAdsAfterPeriodPositionChange.nextAdGroupIndex;
        boolean z10 = i4 == i2 || ((i3 = mediaPeriodId.nextAdGroupIndex) != i2 && i4 >= i3);
        if (mediaPeriodId.periodUid.equals(obj) && !mediaPeriodId.isAd() && !mediaPeriodIdResolveMediaPeriodIdForAdsAfterPeriodPositionChange.isAd() && z10) {
            z9 = true;
        }
        long j6 = j2;
        MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodIdResolveMediaPeriodIdForAdsAfterPeriodPositionChange;
        boolean zIsIgnorableServerSideAdInsertionPeriodChange = isIgnorableServerSideAdInsertionPeriodChange(zIsUsingPlaceholderPeriod, mediaPeriodId, j5, mediaPeriodId2, timeline2.getPeriodByUid(obj, period2), j3);
        if (z9 || zIsIgnorableServerSideAdInsertionPeriodChange) {
            mediaPeriodId2 = mediaPeriodId;
        }
        if (!mediaPeriodId2.isAd()) {
            j4 = jLongValue;
        } else if (mediaPeriodId2.equals(mediaPeriodId)) {
            jLongValue = playbackInfo.positionUs;
            j4 = jLongValue;
        } else {
            timeline2.getPeriodByUid(mediaPeriodId2.periodUid, period2);
            j4 = mediaPeriodId2.adIndexInAdGroup == period2.getFirstAdIndexToPlay(mediaPeriodId2.adGroupIndex) ? period2.adPlaybackState.adResumePositionUs : j6;
        }
        return new PositionUpdateForPlaylistChange(mediaPeriodId2, j4, j3, z2, z3, z4);
    }

    @Nullable
    public static Pair<Object, Long> resolveSeekPositionUs(Timeline timeline, SeekPosition seekPosition, boolean z, int i, boolean z2, Timeline.Window window, Timeline.Period period) {
        Pair<Object, Long> periodPositionUs;
        Timeline timeline2;
        Object objResolveSubsequentPeriod;
        Timeline timeline3 = seekPosition.timeline;
        if (timeline.isEmpty()) {
            return null;
        }
        boolean zIsEmpty = timeline3.isEmpty();
        Timeline timeline4 = timeline3;
        if (zIsEmpty) {
            timeline4 = timeline;
        }
        try {
            periodPositionUs = timeline4.getPeriodPositionUs(window, period, seekPosition.windowIndex, seekPosition.windowPositionUs);
            timeline2 = timeline4;
        } catch (IndexOutOfBoundsException unused) {
        }
        if (!timeline.equals(timeline2)) {
            if (timeline.getIndexOfPeriod(periodPositionUs.first) == -1) {
                if (z && (objResolveSubsequentPeriod = resolveSubsequentPeriod(window, period, i, z2, periodPositionUs.first, timeline2, timeline)) != null) {
                    return timeline.getPeriodPositionUs(window, period, timeline.getPeriodByUid(objResolveSubsequentPeriod, period).windowIndex, -9223372036854775807L);
                }
                return null;
            }
            if (timeline2.getPeriodByUid(periodPositionUs.first, period).isPlaceholder && timeline2.getWindow(period.windowIndex, window, 0L).firstPeriodIndex == timeline2.getIndexOfPeriod(periodPositionUs.first)) {
                return timeline.getPeriodPositionUs(window, period, timeline.getPeriodByUid(periodPositionUs.first, period).windowIndex, seekPosition.windowPositionUs);
            }
        }
        return periodPositionUs;
    }

    @Nullable
    public static Object resolveSubsequentPeriod(Timeline.Window window, Timeline.Period period, int i, boolean z, Object obj, Timeline timeline, Timeline timeline2) {
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        int periodCount = timeline.getPeriodCount();
        int i2 = 0;
        int nextPeriodIndex = indexOfPeriod;
        int indexOfPeriod2 = -1;
        while (i2 < periodCount && indexOfPeriod2 == -1) {
            Timeline.Window window2 = window;
            Timeline.Period period2 = period;
            int i3 = i;
            boolean z2 = z;
            Timeline timeline3 = timeline;
            nextPeriodIndex = timeline3.getNextPeriodIndex(nextPeriodIndex, period2, window2, i3, z2);
            if (nextPeriodIndex == -1) {
                break;
            }
            indexOfPeriod2 = timeline2.getIndexOfPeriod(timeline3.getUidOfPeriod(nextPeriodIndex));
            i2++;
            timeline = timeline3;
            period = period2;
            window = window2;
            i = i3;
            z = z2;
        }
        if (indexOfPeriod2 == -1) {
            return null;
        }
        return timeline2.getUidOfPeriod(indexOfPeriod2);
    }

    public final void addMediaItemsInternal(MediaSourceListUpdateMessage mediaSourceListUpdateMessage, int i) throws Throwable {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        MediaSourceList mediaSourceList = this.mediaSourceList;
        if (i == -1) {
            i = mediaSourceList.mediaSourceHolders.size();
        }
        handleMediaSourceListInfoRefreshed(mediaSourceList.addMediaSources(i, mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), false);
    }

    public void addMediaSources(int i, List<MediaSourceList.MediaSourceHolder> list, ShuffleOrder shuffleOrder) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(18, i, 0, new MediaSourceListUpdateMessage(list, shuffleOrder, -1, -9223372036854775807L))).sendToTarget();
    }

    public final void attemptRendererErrorRecovery() throws ExoPlaybackException {
        seekToCurrentPosition(true);
    }

    public final void deliverMessage(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.isCanceled()) {
            return;
        }
        try {
            playerMessage.target.handleMessage(playerMessage.type, playerMessage.payload);
        } finally {
            playerMessage.markAsProcessed(true);
        }
    }

    public final void disableRenderer(Renderer renderer) throws ExoPlaybackException {
        if (isRendererEnabled(renderer)) {
            this.mediaClock.onRendererDisabled(renderer);
            ensureStopped(renderer);
            renderer.disable();
            this.enabledRendererCount--;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x01a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void doSomeWork() throws com.google.android.exoplayer2.ExoPlaybackException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 497
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.doSomeWork():void");
    }

    public final void enableRenderer(int i, boolean z) throws ExoPlaybackException {
        Renderer renderer = this.renderers[i];
        if (isRendererEnabled(renderer)) {
            return;
        }
        MediaPeriodQueue mediaPeriodQueue = this.queue;
        MediaPeriodHolder mediaPeriodHolder = mediaPeriodQueue.reading;
        boolean z2 = mediaPeriodHolder == mediaPeriodQueue.playing;
        TrackSelectorResult trackSelectorResult = mediaPeriodHolder.trackSelectorResult;
        RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i];
        Format[] formats = getFormats(trackSelectorResult.selections[i]);
        boolean z3 = shouldPlayWhenReady() && this.playbackInfo.playbackState == 3;
        boolean z4 = !z && z3;
        this.enabledRendererCount++;
        this.renderersToReset.add(renderer);
        renderer.enable(rendererConfiguration, formats, mediaPeriodHolder.sampleStreams[i], this.rendererPositionUs, z4, z2, mediaPeriodHolder.getStartPositionRendererTime(), mediaPeriodHolder.rendererPositionOffsetUs);
        renderer.handleMessage(11, new Renderer.WakeupListener() { // from class: com.google.android.exoplayer2.ExoPlayerImplInternal.1
            @Override // com.google.android.exoplayer2.Renderer.WakeupListener
            public void onSleep() {
                ExoPlayerImplInternal.this.requestForRendererSleep = true;
            }

            @Override // com.google.android.exoplayer2.Renderer.WakeupListener
            public void onWakeup() {
                ExoPlayerImplInternal.this.handler.sendEmptyMessage(2);
            }
        });
        this.mediaClock.onRendererEnabled(renderer);
        if (z3) {
            renderer.start();
        }
    }

    public final void enableRenderers() throws ExoPlaybackException {
        enableRenderers(new boolean[this.renderers.length]);
    }

    public final void ensureStopped(Renderer renderer) {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    public void experimentalSetForegroundModeTimeoutMs(long j) {
        this.setForegroundModeTimeoutMs = j;
    }

    public void experimentalSetOffloadSchedulingEnabled(boolean z) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(24, z ? 1 : 0, 0)).sendToTarget();
    }

    public final ImmutableList<Metadata> extractMetadataFromTrackSelectionArray(ExoTrackSelection[] exoTrackSelectionArr) {
        ImmutableList.Builder builder = new ImmutableList.Builder(4);
        boolean z = false;
        for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
            if (exoTrackSelection != null) {
                Metadata metadata = exoTrackSelection.getFormat(0).metadata;
                if (metadata == null) {
                    builder.add(new Metadata(new Metadata.Entry[0]));
                } else {
                    builder.add(metadata);
                    z = true;
                }
            }
        }
        return z ? builder.build() : ImmutableList.of();
    }

    public final long getCurrentLiveOffsetUs() {
        PlaybackInfo playbackInfo = this.playbackInfo;
        return getLiveOffsetUs(playbackInfo.timeline, playbackInfo.periodId.periodUid, playbackInfo.positionUs);
    }

    public final long getLiveOffsetUs(Timeline timeline, Object obj, long j) {
        timeline.getWindow(timeline.getPeriodByUid(obj, this.period).windowIndex, this.window);
        Timeline.Window window = this.window;
        if (window.windowStartTimeMs != -9223372036854775807L && window.isLive()) {
            Timeline.Window window2 = this.window;
            if (window2.isDynamic) {
                return Util.msToUs(Util.getNowUnixTimeMs(window2.elapsedRealtimeEpochOffsetMs) - this.window.windowStartTimeMs) - (j + this.period.positionInWindowUs);
            }
        }
        return -9223372036854775807L;
    }

    public final long getMaxRendererReadPositionUs() {
        MediaPeriodHolder mediaPeriodHolder = this.queue.reading;
        if (mediaPeriodHolder == null) {
            return 0L;
        }
        long jMax = mediaPeriodHolder.rendererPositionOffsetUs;
        if (!mediaPeriodHolder.prepared) {
            return jMax;
        }
        int i = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i >= rendererArr.length) {
                return jMax;
            }
            if (isRendererEnabled(rendererArr[i]) && this.renderers[i].getStream() == mediaPeriodHolder.sampleStreams[i]) {
                long readingPositionUs = this.renderers[i].getReadingPositionUs();
                if (readingPositionUs == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                jMax = Math.max(readingPositionUs, jMax);
            }
            i++;
        }
    }

    public final Pair<MediaSource.MediaPeriodId, Long> getPlaceholderFirstMediaPeriodPositionUs(Timeline timeline) {
        if (timeline.isEmpty()) {
            return Pair.create(PlaybackInfo.PLACEHOLDER_MEDIA_PERIOD_ID, 0L);
        }
        Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(this.window, this.period, timeline.getFirstWindowIndex(this.shuffleModeEnabled), -9223372036854775807L);
        MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAdsAfterPeriodPositionChange = this.queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(timeline, periodPositionUs.first, 0L);
        long jLongValue = ((Long) periodPositionUs.second).longValue();
        if (mediaPeriodIdResolveMediaPeriodIdForAdsAfterPeriodPositionChange.isAd()) {
            timeline.getPeriodByUid(mediaPeriodIdResolveMediaPeriodIdForAdsAfterPeriodPositionChange.periodUid, this.period);
            jLongValue = mediaPeriodIdResolveMediaPeriodIdForAdsAfterPeriodPositionChange.adIndexInAdGroup == this.period.getFirstAdIndexToPlay(mediaPeriodIdResolveMediaPeriodIdForAdsAfterPeriodPositionChange.adGroupIndex) ? this.period.adPlaybackState.adResumePositionUs : 0L;
        }
        return Pair.create(mediaPeriodIdResolveMediaPeriodIdForAdsAfterPeriodPositionChange, Long.valueOf(jLongValue));
    }

    public Looper getPlaybackLooper() {
        return this.playbackLooper;
    }

    public final long getTotalBufferedDurationUs() {
        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
    }

    public final void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            this.queue.reevaluateBuffer(this.rendererPositionUs);
            maybeContinueLoading();
        }
    }

    public final void handleIoException(IOException iOException, int i) {
        ExoPlaybackException exoPlaybackExceptionCreateForSource = ExoPlaybackException.createForSource(iOException, i);
        MediaPeriodHolder mediaPeriodHolder = this.queue.playing;
        if (mediaPeriodHolder != null) {
            exoPlaybackExceptionCreateForSource = exoPlaybackExceptionCreateForSource.copyWithMediaPeriodId(mediaPeriodHolder.info.id);
        }
        Log.e("ExoPlayerImplInternal", "Playback error", exoPlaybackExceptionCreateForSource);
        stopInternal(false, false);
        this.playbackInfo = this.playbackInfo.copyWithPlaybackError(exoPlaybackExceptionCreateForSource);
    }

    public final void handleLoadingMediaPeriodChanged(boolean z) {
        MediaPeriodHolder mediaPeriodHolder = this.queue.loading;
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodHolder == null ? this.playbackInfo.periodId : mediaPeriodHolder.info.id;
        boolean zEquals = this.playbackInfo.loadingMediaPeriodId.equals(mediaPeriodId);
        if (!zEquals) {
            this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(mediaPeriodId);
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        playbackInfo.bufferedPositionUs = mediaPeriodHolder == null ? playbackInfo.positionUs : mediaPeriodHolder.getBufferedPositionUs();
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if ((!zEquals || z) && mediaPeriodHolder != null && mediaPeriodHolder.prepared) {
            updateLoadControlTrackSelection(mediaPeriodHolder.trackGroups, mediaPeriodHolder.trackSelectorResult);
        }
    }

    public final void handleMediaSourceListInfoRefreshed(Timeline timeline, boolean z) throws Throwable {
        MediaSource.MediaPeriodId mediaPeriodId;
        long j;
        Timeline timeline2;
        long j2;
        boolean z2;
        Timeline timeline3;
        long j3;
        int i;
        Timeline timeline4;
        PositionUpdateForPlaylistChange positionUpdateForPlaylistChangeResolvePositionForPlaylistChange = resolvePositionForPlaylistChange(timeline, this.playbackInfo, this.pendingInitialSeekPosition, this.queue, this.repeatMode, this.shuffleModeEnabled, this.window, this.period);
        MediaSource.MediaPeriodId mediaPeriodId2 = positionUpdateForPlaylistChangeResolvePositionForPlaylistChange.periodId;
        long j4 = positionUpdateForPlaylistChangeResolvePositionForPlaylistChange.requestedContentPositionUs;
        boolean z3 = positionUpdateForPlaylistChangeResolvePositionForPlaylistChange.forceBufferingState;
        long jSeekToPeriodPosition = positionUpdateForPlaylistChangeResolvePositionForPlaylistChange.periodPositionUs;
        int i2 = 1;
        boolean z4 = (this.playbackInfo.periodId.equals(mediaPeriodId2) && jSeekToPeriodPosition == this.playbackInfo.positionUs) ? false : true;
        Timeline timeline5 = null;
        try {
            if (positionUpdateForPlaylistChangeResolvePositionForPlaylistChange.endPlayback) {
                if (this.playbackInfo.playbackState != 1) {
                    setState(4);
                }
                resetInternal(false, false, false, true);
            }
            try {
                if (z4) {
                    timeline3 = timeline;
                    i2 = -1;
                    if (!timeline3.isEmpty()) {
                        for (MediaPeriodHolder mediaPeriodHolder = this.queue.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.next) {
                            if (mediaPeriodHolder.info.id.equals(mediaPeriodId2)) {
                                mediaPeriodHolder.info = this.queue.getUpdatedMediaPeriodInfo(timeline3, mediaPeriodHolder.info);
                                mediaPeriodHolder.updateClipping();
                            }
                        }
                        jSeekToPeriodPosition = seekToPeriodPosition(mediaPeriodId2, jSeekToPeriodPosition, z3);
                    }
                } else {
                    try {
                        try {
                            i2 = -1;
                            try {
                                timeline3 = timeline;
                                if (!this.queue.updateQueuedPeriods(timeline, this.rendererPositionUs, getMaxRendererReadPositionUs())) {
                                    seekToCurrentPosition(false);
                                }
                            } catch (Throwable th) {
                                th = th;
                                timeline5 = timeline;
                                mediaPeriodId = mediaPeriodId2;
                                j = j4;
                                timeline2 = timeline5;
                                PlaybackInfo playbackInfo = this.playbackInfo;
                                updatePlaybackSpeedSettingsForNewPeriod(timeline2, mediaPeriodId, playbackInfo.timeline, playbackInfo.periodId, positionUpdateForPlaylistChangeResolvePositionForPlaylistChange.setTargetLiveOffset ? jSeekToPeriodPosition : -9223372036854775807L, false);
                                if (z4 || j != this.playbackInfo.requestedContentPositionUs) {
                                    PlaybackInfo playbackInfo2 = this.playbackInfo;
                                    Object obj = playbackInfo2.periodId.periodUid;
                                    Timeline timeline6 = playbackInfo2.timeline;
                                    if (!z4 || !z || timeline6.isEmpty() || timeline6.getPeriodByUid(obj, this.period).isPlaceholder) {
                                        j2 = j;
                                        z2 = false;
                                    } else {
                                        j2 = j;
                                        z2 = true;
                                    }
                                    this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId, jSeekToPeriodPosition, j2, this.playbackInfo.discontinuityStartPositionUs, z2, timeline2.getIndexOfPeriod(obj) == i2 ? 4 : 3);
                                }
                                resetPendingPauseAtEndOfPeriod();
                                resolvePendingMessagePositions(timeline2, this.playbackInfo.timeline);
                                this.playbackInfo = this.playbackInfo.copyWithTimeline(timeline2);
                                if (!timeline2.isEmpty()) {
                                    this.pendingInitialSeekPosition = null;
                                }
                                handleLoadingMediaPeriodChanged(false);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            timeline5 = timeline;
                            i2 = -1;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        timeline5 = timeline;
                        i2 = -1;
                    }
                }
                PlaybackInfo playbackInfo3 = this.playbackInfo;
                updatePlaybackSpeedSettingsForNewPeriod(timeline3, mediaPeriodId2, playbackInfo3.timeline, playbackInfo3.periodId, positionUpdateForPlaylistChangeResolvePositionForPlaylistChange.setTargetLiveOffset ? jSeekToPeriodPosition : -9223372036854775807L, false);
                if (z4 || j4 != this.playbackInfo.requestedContentPositionUs) {
                    PlaybackInfo playbackInfo4 = this.playbackInfo;
                    Object obj2 = playbackInfo4.periodId.periodUid;
                    Timeline timeline7 = playbackInfo4.timeline;
                    boolean z5 = z4 && z && !timeline7.isEmpty() && !timeline7.getPeriodByUid(obj2, this.period).isPlaceholder;
                    long j5 = this.playbackInfo.discontinuityStartPositionUs;
                    if (timeline3.getIndexOfPeriod(obj2) == i2) {
                        j3 = j4;
                        i = 4;
                    } else {
                        j3 = j4;
                        i = 3;
                    }
                    timeline4 = timeline3;
                    this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId2, jSeekToPeriodPosition, j3, j5, z5, i);
                } else {
                    timeline4 = timeline3;
                }
                resetPendingPauseAtEndOfPeriod();
                resolvePendingMessagePositions(timeline4, this.playbackInfo.timeline);
                this.playbackInfo = this.playbackInfo.copyWithTimeline(timeline4);
                if (!timeline4.isEmpty()) {
                    this.pendingInitialSeekPosition = null;
                }
                handleLoadingMediaPeriodChanged(false);
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
            mediaPeriodId = mediaPeriodId2;
            j = j4;
            i2 = -1;
            timeline2 = timeline;
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) throws Throwable {
        MediaPeriodHolder mediaPeriodHolder;
        try {
            switch (message.what) {
                case 0:
                    prepareInternal();
                    break;
                case 1:
                    setPlayWhenReadyInternal(message.arg1 != 0, message.arg2, true, 1);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) message.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message.obj);
                    break;
                case 5:
                    this.seekParameters = (SeekParameters) message.obj;
                    break;
                case 6:
                    stopInternal(false, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handlePeriodPrepared((MediaPeriod) message.obj);
                    break;
                case 9:
                    handleContinueLoadingRequested((MediaPeriod) message.obj);
                    break;
                case 10:
                    reselectTracksInternal();
                    break;
                case 11:
                    setRepeatModeInternal(message.arg1);
                    break;
                case 12:
                    setShuffleModeEnabledInternal(message.arg1 != 0);
                    break;
                case 13:
                    setForegroundModeInternal(message.arg1 != 0, (AtomicBoolean) message.obj);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) message.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) message.obj);
                    break;
                case 16:
                    handlePlaybackParameters((PlaybackParameters) message.obj, false);
                    break;
                case 17:
                    setMediaItemsInternal((MediaSourceListUpdateMessage) message.obj);
                    break;
                case 18:
                    addMediaItemsInternal((MediaSourceListUpdateMessage) message.obj, message.arg1);
                    break;
                case 19:
                    moveMediaItemsInternal((MoveMediaItemsMessage) message.obj);
                    break;
                case 20:
                    removeMediaItemsInternal(message.arg1, message.arg2, (ShuffleOrder) message.obj);
                    break;
                case 21:
                    setShuffleOrderInternal((ShuffleOrder) message.obj);
                    break;
                case 22:
                    mediaSourceListUpdateRequestedInternal();
                    break;
                case 23:
                    setPauseAtEndOfWindowInternal(message.arg1 != 0);
                    break;
                case 24:
                    setOffloadSchedulingEnabledInternal(message.arg1 == 1);
                    break;
                case 25:
                    seekToCurrentPosition(true);
                    break;
                default:
                    return false;
            }
        } catch (ExoPlaybackException e) {
            e = e;
            if (e.type == 1 && (mediaPeriodHolder = this.queue.reading) != null) {
                e = e.copyWithMediaPeriodId(mediaPeriodHolder.info.id);
            }
            if (e.isRecoverable && this.pendingRecoverableRendererError == null) {
                Log.w("ExoPlayerImplInternal", "Recoverable renderer error", e);
                this.pendingRecoverableRendererError = e;
                HandlerWrapper handlerWrapper = this.handler;
                handlerWrapper.sendMessageAtFrontOfQueue(handlerWrapper.obtainMessage(25, e));
            } else {
                ExoPlaybackException exoPlaybackException = this.pendingRecoverableRendererError;
                if (exoPlaybackException != null) {
                    exoPlaybackException.addSuppressed(e);
                    e = this.pendingRecoverableRendererError;
                }
                Log.e("ExoPlayerImplInternal", "Playback error", e);
                stopInternal(true, false);
                this.playbackInfo = this.playbackInfo.copyWithPlaybackError(e);
            }
        } catch (ParserException e2) {
            int i = e2.dataType;
            if (i == 1) {
                i = e2.contentIsMalformed ? 3001 : 3003;
            } else if (i == 4) {
                i = e2.contentIsMalformed ? 3002 : 3004;
            }
            handleIoException(e2, i);
        } catch (DrmSession.DrmSessionException e3) {
            handleIoException(e3, e3.errorCode);
        } catch (BehindLiveWindowException e4) {
            handleIoException(e4, 1002);
        } catch (DataSourceException e5) {
            handleIoException(e5, e5.reason);
        } catch (IOException e6) {
            handleIoException(e6, 2000);
        } catch (RuntimeException e7) {
            ExoPlaybackException exoPlaybackExceptionCreateForUnexpected = ExoPlaybackException.createForUnexpected(e7, ((e7 instanceof IllegalStateException) || (e7 instanceof IllegalArgumentException)) ? 1004 : 1000);
            Log.e("ExoPlayerImplInternal", "Playback error", exoPlaybackExceptionCreateForUnexpected);
            stopInternal(true, false);
            this.playbackInfo = this.playbackInfo.copyWithPlaybackError(exoPlaybackExceptionCreateForUnexpected);
        }
        maybeNotifyPlaybackInfoChanged();
        return true;
    }

    public final void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (this.queue.isLoading(mediaPeriod)) {
            MediaPeriodHolder mediaPeriodHolder = this.queue.loading;
            mediaPeriodHolder.handlePrepared(this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.timeline);
            updateLoadControlTrackSelection(mediaPeriodHolder.trackGroups, mediaPeriodHolder.trackSelectorResult);
            if (mediaPeriodHolder == this.queue.playing) {
                resetRendererPosition(mediaPeriodHolder.info.startPositionUs);
                enableRenderers();
                PlaybackInfo playbackInfo = this.playbackInfo;
                MediaSource.MediaPeriodId mediaPeriodId = playbackInfo.periodId;
                long j = mediaPeriodHolder.info.startPositionUs;
                this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId, j, playbackInfo.requestedContentPositionUs, j, false, 5);
            }
            maybeContinueLoading();
        }
    }

    public final void handlePlaybackParameters(PlaybackParameters playbackParameters, boolean z) throws ExoPlaybackException {
        handlePlaybackParameters(playbackParameters, playbackParameters.speed, true, z);
    }

    @CheckResult
    public final PlaybackInfo handlePositionDiscontinuity(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2, long j3, boolean z, int i) {
        this.deliverPendingMessageAtStartPositionRequired = (!this.deliverPendingMessageAtStartPositionRequired && j == this.playbackInfo.positionUs && mediaPeriodId.equals(this.playbackInfo.periodId)) ? false : true;
        resetPendingPauseAtEndOfPeriod();
        PlaybackInfo playbackInfo = this.playbackInfo;
        TrackGroupArray trackGroupArray = playbackInfo.trackGroups;
        TrackSelectorResult trackSelectorResult = playbackInfo.trackSelectorResult;
        List<Metadata> listOf = playbackInfo.staticMetadata;
        if (this.mediaSourceList.isPrepared) {
            MediaPeriodHolder mediaPeriodHolder = this.queue.playing;
            trackGroupArray = mediaPeriodHolder == null ? TrackGroupArray.EMPTY : mediaPeriodHolder.trackGroups;
            trackSelectorResult = mediaPeriodHolder == null ? this.emptyTrackSelectorResult : mediaPeriodHolder.trackSelectorResult;
            listOf = extractMetadataFromTrackSelectionArray(trackSelectorResult.selections);
            if (mediaPeriodHolder != null) {
                MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
                if (mediaPeriodInfo.requestedContentPositionUs != j2) {
                    mediaPeriodHolder.info = mediaPeriodInfo.copyWithRequestedContentPositionUs(j2);
                }
            }
        } else if (!mediaPeriodId.equals(playbackInfo.periodId)) {
            trackGroupArray = TrackGroupArray.EMPTY;
            trackSelectorResult = this.emptyTrackSelectorResult;
            listOf = ImmutableList.of();
        }
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        TrackSelectorResult trackSelectorResult2 = trackSelectorResult;
        List<Metadata> list = listOf;
        if (z) {
            this.playbackInfoUpdate.setPositionDiscontinuity(i);
        }
        return this.playbackInfo.copyWithNewPosition(mediaPeriodId, j, j2, j3, getTotalBufferedDurationUs(), trackGroupArray2, trackSelectorResult2, list);
    }

    public final boolean hasReachedServerSideInsertedAdsTransition(Renderer renderer, MediaPeriodHolder mediaPeriodHolder) {
        MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder.next;
        if (mediaPeriodHolder.info.isFollowedByTransitionToSameStream && mediaPeriodHolder2.prepared) {
            return (renderer instanceof TextRenderer) || (renderer instanceof MetadataRenderer) || renderer.getReadingPositionUs() >= mediaPeriodHolder2.getStartPositionRendererTime();
        }
        return false;
    }

    public final boolean hasReadingPeriodFinishedReading() {
        MediaPeriodHolder mediaPeriodHolder = this.queue.reading;
        if (mediaPeriodHolder.prepared) {
            int i = 0;
            while (true) {
                Renderer[] rendererArr = this.renderers;
                if (i >= rendererArr.length) {
                    return true;
                }
                Renderer renderer = rendererArr[i];
                SampleStream sampleStream = mediaPeriodHolder.sampleStreams[i];
                if (renderer.getStream() != sampleStream || (sampleStream != null && !renderer.hasReadStreamToEnd() && !hasReachedServerSideInsertedAdsTransition(renderer, mediaPeriodHolder))) {
                    break;
                }
                i++;
            }
        }
        return false;
    }

    public final boolean isLoadingPossible() {
        MediaPeriodHolder mediaPeriodHolder = this.queue.loading;
        return (mediaPeriodHolder == null || mediaPeriodHolder.getNextLoadPositionUs() == Long.MIN_VALUE) ? false : true;
    }

    public final boolean isTimelineReady() {
        MediaPeriodHolder mediaPeriodHolder = this.queue.playing;
        long j = mediaPeriodHolder.info.durationUs;
        if (mediaPeriodHolder.prepared) {
            return j == -9223372036854775807L || this.playbackInfo.positionUs < j || !shouldPlayWhenReady();
        }
        return false;
    }

    public final void maybeContinueLoading() {
        boolean zShouldContinueLoading = shouldContinueLoading();
        this.shouldContinueLoading = zShouldContinueLoading;
        if (zShouldContinueLoading) {
            this.queue.loading.continueLoading(this.rendererPositionUs);
        }
        updateIsLoading();
    }

    public final void maybeNotifyPlaybackInfoChanged() {
        this.playbackInfoUpdate.setPlaybackInfo(this.playbackInfo);
        PlaybackInfoUpdate playbackInfoUpdate = this.playbackInfoUpdate;
        if (playbackInfoUpdate.hasPendingChange) {
            this.playbackInfoUpdateListener.onPlaybackInfoUpdate(playbackInfoUpdate);
            this.playbackInfoUpdate = new PlaybackInfoUpdate(this.playbackInfo);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x0079, code lost:
    
        r3 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void maybeTriggerPendingMessages(long r9, long r11) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.maybeTriggerPendingMessages(long, long):void");
    }

    public final void maybeUpdateLoadingPeriod() throws ExoPlaybackException {
        MediaPeriodInfo nextMediaPeriodInfo;
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        if (this.queue.shouldLoadNextMediaPeriod() && (nextMediaPeriodInfo = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo)) != null) {
            MediaPeriodHolder mediaPeriodHolderEnqueueNextMediaPeriodHolder = this.queue.enqueueNextMediaPeriodHolder(this.rendererCapabilities, this.trackSelector, this.loadControl.getAllocator(), this.mediaSourceList, nextMediaPeriodInfo, this.emptyTrackSelectorResult);
            mediaPeriodHolderEnqueueNextMediaPeriodHolder.mediaPeriod.prepare(this, nextMediaPeriodInfo.startPositionUs);
            if (this.queue.playing == mediaPeriodHolderEnqueueNextMediaPeriodHolder) {
                resetRendererPosition(nextMediaPeriodInfo.startPositionUs);
            }
            handleLoadingMediaPeriodChanged(false);
        }
        if (!this.shouldContinueLoading) {
            maybeContinueLoading();
        } else {
            this.shouldContinueLoading = isLoadingPossible();
            updateIsLoading();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void maybeUpdatePlayingPeriod() throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r14 = this;
            r0 = 0
            r1 = 0
        L2:
            boolean r2 = r14.shouldAdvancePlayingPeriod()
            if (r2 == 0) goto L5e
            if (r1 == 0) goto Ld
            r14.maybeNotifyPlaybackInfoChanged()
        Ld:
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r14.queue
            com.google.android.exoplayer2.MediaPeriodHolder r1 = r1.advancePlayingPeriod()
            r1.getClass()
            com.google.android.exoplayer2.PlaybackInfo r2 = r14.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r2.periodId
            java.lang.Object r2 = r2.periodUid
            com.google.android.exoplayer2.MediaPeriodInfo r3 = r1.info
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r3 = r3.id
            java.lang.Object r3 = r3.periodUid
            boolean r2 = r2.equals(r3)
            r3 = 1
            if (r2 == 0) goto L42
            com.google.android.exoplayer2.PlaybackInfo r2 = r14.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r2.periodId
            int r4 = r2.adGroupIndex
            r5 = -1
            if (r4 != r5) goto L42
            com.google.android.exoplayer2.MediaPeriodInfo r4 = r1.info
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r4 = r4.id
            int r6 = r4.adGroupIndex
            if (r6 != r5) goto L42
            int r2 = r2.nextAdGroupIndex
            int r4 = r4.nextAdGroupIndex
            if (r2 == r4) goto L42
            r2 = 1
            goto L43
        L42:
            r2 = 0
        L43:
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r1.info
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r5 = r1.id
            long r6 = r1.startPositionUs
            long r8 = r1.requestedContentPositionUs
            r12 = r2 ^ 1
            r13 = 0
            r10 = r6
            r4 = r14
            com.google.android.exoplayer2.PlaybackInfo r1 = r4.handlePositionDiscontinuity(r5, r6, r8, r10, r12, r13)
            r4.playbackInfo = r1
            r14.resetPendingPauseAtEndOfPeriod()
            r14.updatePlaybackPositions()
            r1 = 1
            goto L2
        L5e:
            r4 = r14
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.maybeUpdatePlayingPeriod():void");
    }

    public final void maybeUpdateReadingPeriod() throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder = this.queue.reading;
        if (mediaPeriodHolder == null) {
            return;
        }
        int i = 0;
        if (mediaPeriodHolder.next != null && !this.pendingPauseAtEndOfPeriod) {
            if (hasReadingPeriodFinishedReading()) {
                MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder.next;
                if (mediaPeriodHolder2.prepared || this.rendererPositionUs >= mediaPeriodHolder2.getStartPositionRendererTime()) {
                    TrackSelectorResult trackSelectorResult = mediaPeriodHolder.trackSelectorResult;
                    MediaPeriodHolder mediaPeriodHolderAdvanceReadingPeriod = this.queue.advanceReadingPeriod();
                    TrackSelectorResult trackSelectorResult2 = mediaPeriodHolderAdvanceReadingPeriod.trackSelectorResult;
                    Timeline timeline = this.playbackInfo.timeline;
                    updatePlaybackSpeedSettingsForNewPeriod(timeline, mediaPeriodHolderAdvanceReadingPeriod.info.id, timeline, mediaPeriodHolder.info.id, -9223372036854775807L, false);
                    if (mediaPeriodHolderAdvanceReadingPeriod.prepared && mediaPeriodHolderAdvanceReadingPeriod.mediaPeriod.readDiscontinuity() != -9223372036854775807L) {
                        setAllRendererStreamsFinal(mediaPeriodHolderAdvanceReadingPeriod.getStartPositionRendererTime());
                        return;
                    }
                    for (int i2 = 0; i2 < this.renderers.length; i2++) {
                        boolean zIsRendererEnabled = trackSelectorResult.isRendererEnabled(i2);
                        boolean zIsRendererEnabled2 = trackSelectorResult2.isRendererEnabled(i2);
                        if (zIsRendererEnabled && !this.renderers[i2].isCurrentStreamFinal()) {
                            boolean z = this.rendererCapabilities[i2].getTrackType() == -2;
                            RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i2];
                            RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i2];
                            if (!zIsRendererEnabled2 || !rendererConfiguration2.equals(rendererConfiguration) || z) {
                                setCurrentStreamFinal(this.renderers[i2], mediaPeriodHolderAdvanceReadingPeriod.getStartPositionRendererTime());
                            }
                        }
                    }
                    return;
                }
                return;
            }
            return;
        }
        if (!mediaPeriodHolder.info.isFinal && !this.pendingPauseAtEndOfPeriod) {
            return;
        }
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i >= rendererArr.length) {
                return;
            }
            Renderer renderer = rendererArr[i];
            SampleStream sampleStream = mediaPeriodHolder.sampleStreams[i];
            if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                long j = mediaPeriodHolder.info.durationUs;
                setCurrentStreamFinal(renderer, (j == -9223372036854775807L || j == Long.MIN_VALUE) ? -9223372036854775807L : mediaPeriodHolder.rendererPositionOffsetUs + j);
            }
            i++;
        }
    }

    public final void maybeUpdateReadingRenderers() throws ExoPlaybackException {
        MediaPeriodQueue mediaPeriodQueue = this.queue;
        MediaPeriodHolder mediaPeriodHolder = mediaPeriodQueue.reading;
        if (mediaPeriodHolder == null || mediaPeriodQueue.playing == mediaPeriodHolder || mediaPeriodHolder.allRenderersInCorrectState || !replaceStreamsOrDisableRendererForTransition()) {
            return;
        }
        enableRenderers();
    }

    public final void mediaSourceListUpdateRequestedInternal() throws Throwable {
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.createTimeline(), true);
    }

    public final void moveMediaItemsInternal(MoveMediaItemsMessage moveMediaItemsMessage) throws Throwable {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.moveMediaSourceRange(moveMediaItemsMessage.fromIndex, moveMediaItemsMessage.toIndex, moveMediaItemsMessage.newFromIndex, moveMediaItemsMessage.shuffleOrder), false);
    }

    public void moveMediaSources(int i, int i2, int i3, ShuffleOrder shuffleOrder) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(19, new MoveMediaItemsMessage(i, i2, i3, shuffleOrder))).sendToTarget();
    }

    public final void notifyTrackSelectionDiscontinuity() {
        for (MediaPeriodHolder mediaPeriodHolder = this.queue.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.next) {
            for (ExoTrackSelection exoTrackSelection : mediaPeriodHolder.trackSelectorResult.selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onDiscontinuity();
                }
            }
        }
    }

    public final void notifyTrackSelectionPlayWhenReadyChanged(boolean z) {
        for (MediaPeriodHolder mediaPeriodHolder = this.queue.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.next) {
            for (ExoTrackSelection exoTrackSelection : mediaPeriodHolder.trackSelectorResult.selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onPlayWhenReadyChanged(z);
                }
            }
        }
    }

    public final void notifyTrackSelectionRebuffer() {
        for (MediaPeriodHolder mediaPeriodHolder = this.queue.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.next) {
            for (ExoTrackSelection exoTrackSelection : mediaPeriodHolder.trackSelectorResult.selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onRebuffer();
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.DefaultMediaClock.PlaybackParametersListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(16, playbackParameters)).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.MediaSourceList.MediaSourceListInfoRefreshListener
    public void onPlaylistUpdateRequested() {
        this.handler.sendEmptyMessage(22);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(8, mediaPeriod)).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector.InvalidationListener
    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(10);
    }

    public void prepare() {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(0)).sendToTarget();
    }

    public final void prepareInternal() {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        resetInternal(false, false, false, true);
        this.loadControl.onPrepared();
        setState(this.playbackInfo.timeline.isEmpty() ? 4 : 2);
        this.mediaSourceList.prepare(this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    public synchronized boolean release() {
        if (!this.released && this.playbackLooper.getThread().isAlive()) {
            this.handler.sendEmptyMessage(7);
            waitUninterruptibly(new Supplier() { // from class: com.google.android.exoplayer2.ExoPlayerImplInternal$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return Boolean.valueOf(this.f$0.released);
                }
            }, this.releaseTimeoutMs);
            return this.released;
        }
        return true;
    }

    public final void releaseInternal() {
        resetInternal(true, false, true, false);
        this.loadControl.onReleased();
        setState(1);
        HandlerThread handlerThread = this.internalPlaybackThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    public final void removeMediaItemsInternal(int i, int i2, ShuffleOrder shuffleOrder) throws Throwable {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.removeMediaSourceRange(i, i2, shuffleOrder), false);
    }

    public void removeMediaSources(int i, int i2, ShuffleOrder shuffleOrder) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(20, i, i2, shuffleOrder)).sendToTarget();
    }

    public final boolean replaceStreamsOrDisableRendererForTransition() throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder = this.queue.reading;
        TrackSelectorResult trackSelectorResult = mediaPeriodHolder.trackSelectorResult;
        int i = 0;
        boolean z = false;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i >= rendererArr.length) {
                return !z;
            }
            Renderer renderer = rendererArr[i];
            if (isRendererEnabled(renderer)) {
                boolean z2 = renderer.getStream() != mediaPeriodHolder.sampleStreams[i];
                if (!trackSelectorResult.isRendererEnabled(i) || z2) {
                    if (!renderer.isCurrentStreamFinal()) {
                        renderer.replaceStream(getFormats(trackSelectorResult.selections[i]), mediaPeriodHolder.sampleStreams[i], mediaPeriodHolder.getStartPositionRendererTime(), mediaPeriodHolder.rendererPositionOffsetUs);
                    } else if (renderer.isEnded()) {
                        disableRenderer(renderer);
                    } else {
                        z = true;
                    }
                }
            }
            i++;
        }
    }

    public final void reselectTracksInternal() throws ExoPlaybackException {
        int i;
        float f = this.mediaClock.getPlaybackParameters().speed;
        MediaPeriodQueue mediaPeriodQueue = this.queue;
        MediaPeriodHolder mediaPeriodHolder = mediaPeriodQueue.reading;
        boolean z = true;
        for (MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodQueue.playing; mediaPeriodHolder2 != null && mediaPeriodHolder2.prepared; mediaPeriodHolder2 = mediaPeriodHolder2.next) {
            TrackSelectorResult trackSelectorResultSelectTracks = mediaPeriodHolder2.selectTracks(f, this.playbackInfo.timeline);
            if (!trackSelectorResultSelectTracks.isEquivalent(mediaPeriodHolder2.trackSelectorResult)) {
                if (z) {
                    MediaPeriodQueue mediaPeriodQueue2 = this.queue;
                    MediaPeriodHolder mediaPeriodHolder3 = mediaPeriodQueue2.playing;
                    boolean zRemoveAfter = mediaPeriodQueue2.removeAfter(mediaPeriodHolder3);
                    boolean[] zArr = new boolean[this.renderers.length];
                    long jApplyTrackSelection = mediaPeriodHolder3.applyTrackSelection(trackSelectorResultSelectTracks, this.playbackInfo.positionUs, zRemoveAfter, zArr);
                    PlaybackInfo playbackInfo = this.playbackInfo;
                    boolean z2 = (playbackInfo.playbackState == 4 || jApplyTrackSelection == playbackInfo.positionUs) ? false : true;
                    PlaybackInfo playbackInfo2 = this.playbackInfo;
                    i = 4;
                    this.playbackInfo = handlePositionDiscontinuity(playbackInfo2.periodId, jApplyTrackSelection, playbackInfo2.requestedContentPositionUs, playbackInfo2.discontinuityStartPositionUs, z2, 5);
                    if (z2) {
                        resetRendererPosition(jApplyTrackSelection);
                    }
                    boolean[] zArr2 = new boolean[this.renderers.length];
                    int i2 = 0;
                    while (true) {
                        Renderer[] rendererArr = this.renderers;
                        if (i2 >= rendererArr.length) {
                            break;
                        }
                        Renderer renderer = rendererArr[i2];
                        boolean zIsRendererEnabled = isRendererEnabled(renderer);
                        zArr2[i2] = zIsRendererEnabled;
                        SampleStream sampleStream = mediaPeriodHolder3.sampleStreams[i2];
                        if (zIsRendererEnabled) {
                            if (sampleStream != renderer.getStream()) {
                                disableRenderer(renderer);
                            } else if (zArr[i2]) {
                                renderer.resetPosition(this.rendererPositionUs);
                            }
                        }
                        i2++;
                    }
                    enableRenderers(zArr2);
                } else {
                    i = 4;
                    this.queue.removeAfter(mediaPeriodHolder2);
                    if (mediaPeriodHolder2.prepared) {
                        mediaPeriodHolder2.applyTrackSelection(trackSelectorResultSelectTracks, Math.max(mediaPeriodHolder2.info.startPositionUs, this.rendererPositionUs - mediaPeriodHolder2.rendererPositionOffsetUs), false);
                    }
                }
                handleLoadingMediaPeriodChanged(true);
                if (this.playbackInfo.playbackState != i) {
                    maybeContinueLoading();
                    updatePlaybackPositions();
                    this.handler.sendEmptyMessage(2);
                    return;
                }
                return;
            }
            if (mediaPeriodHolder2 == mediaPeriodHolder) {
                z = false;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a3 A[PHI: r4 r5 r7
      0x00a3: PHI (r4v3 com.google.android.exoplayer2.source.MediaSource$MediaPeriodId) = 
      (r4v2 com.google.android.exoplayer2.source.MediaSource$MediaPeriodId)
      (r4v8 com.google.android.exoplayer2.source.MediaSource$MediaPeriodId)
     binds: [B:30:0x0078, B:32:0x009d] A[DONT_GENERATE, DONT_INLINE]
      0x00a3: PHI (r5v2 long) = (r5v1 long), (r5v4 long) binds: [B:30:0x0078, B:32:0x009d] A[DONT_GENERATE, DONT_INLINE]
      0x00a3: PHI (r7v3 long) = (r7v2 long), (r7v5 long) binds: [B:30:0x0078, B:32:0x009d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void resetInternal(boolean r29, boolean r30, boolean r31, boolean r32) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.resetInternal(boolean, boolean, boolean, boolean):void");
    }

    public final void resetPendingPauseAtEndOfPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.queue.playing;
        this.pendingPauseAtEndOfPeriod = mediaPeriodHolder != null && mediaPeriodHolder.info.isLastInTimelineWindow && this.pauseAtEndOfWindow;
    }

    public final void resetRendererPosition(long j) throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder = this.queue.playing;
        long j2 = j + (mediaPeriodHolder == null ? 1000000000000L : mediaPeriodHolder.rendererPositionOffsetUs);
        this.rendererPositionUs = j2;
        this.mediaClock.resetPosition(j2);
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                renderer.resetPosition(this.rendererPositionUs);
            }
        }
        notifyTrackSelectionDiscontinuity();
    }

    public final void resolvePendingMessagePositions(Timeline timeline, Timeline timeline2) {
        if (timeline.isEmpty() && timeline2.isEmpty()) {
            return;
        }
        int size = this.pendingMessages.size() - 1;
        while (size >= 0) {
            Timeline timeline3 = timeline;
            Timeline timeline4 = timeline2;
            if (!resolvePendingMessagePosition(this.pendingMessages.get(size), timeline3, timeline4, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
                this.pendingMessages.get(size).message.markAsProcessed(false);
                this.pendingMessages.remove(size);
            }
            size--;
            timeline = timeline3;
            timeline2 = timeline4;
        }
        Collections.sort(this.pendingMessages);
    }

    public final void scheduleNextWork(long j, long j2) {
        this.handler.sendEmptyMessageAtTime(2, j + j2);
    }

    public void seekTo(Timeline timeline, int i, long j) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(3, new SeekPosition(timeline, i, j))).sendToTarget();
    }

    public final void seekToCurrentPosition(boolean z) throws ExoPlaybackException {
        MediaSource.MediaPeriodId mediaPeriodId = this.queue.playing.info.id;
        long jSeekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs, true, false);
        if (jSeekToPeriodPosition != this.playbackInfo.positionUs) {
            PlaybackInfo playbackInfo = this.playbackInfo;
            this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId, jSeekToPeriodPosition, playbackInfo.requestedContentPositionUs, playbackInfo.discontinuityStartPositionUs, z, 5);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00a1 A[Catch: all -> 0x00a4, TryCatch #2 {all -> 0x00a4, blocks: (B:22:0x0097, B:24:0x00a1, B:30:0x00ad, B:32:0x00b3, B:33:0x00b6, B:35:0x00be, B:39:0x00ce, B:43:0x00d6), top: B:93:0x0097 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void seekToInternal(com.google.android.exoplayer2.ExoPlayerImplInternal.SeekPosition r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.seekToInternal(com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition):void");
    }

    public final long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j, boolean z) throws ExoPlaybackException {
        MediaPeriodQueue mediaPeriodQueue = this.queue;
        return seekToPeriodPosition(mediaPeriodId, j, mediaPeriodQueue.playing != mediaPeriodQueue.reading, z);
    }

    @Override // com.google.android.exoplayer2.PlayerMessage.Sender
    public synchronized void sendMessage(PlayerMessage playerMessage) {
        if (!this.released && this.playbackLooper.getThread().isAlive()) {
            ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(14, playerMessage)).sendToTarget();
            return;
        }
        Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
        playerMessage.markAsProcessed(false);
    }

    public final void sendMessageInternal(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.positionMs == -9223372036854775807L) {
            sendMessageToTarget(playerMessage);
            return;
        }
        if (this.playbackInfo.timeline.isEmpty()) {
            this.pendingMessages.add(new PendingMessageInfo(playerMessage));
            return;
        }
        PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(playerMessage);
        Timeline timeline = this.playbackInfo.timeline;
        if (!resolvePendingMessagePosition(pendingMessageInfo, timeline, timeline, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
            playerMessage.markAsProcessed(false);
        } else {
            this.pendingMessages.add(pendingMessageInfo);
            Collections.sort(this.pendingMessages);
        }
    }

    public final void sendMessageToTarget(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.looper != this.playbackLooper) {
            ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(15, playerMessage)).sendToTarget();
            return;
        }
        deliverMessage(playerMessage);
        int i = this.playbackInfo.playbackState;
        if (i == 3 || i == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    public final void sendMessageToTargetThread(final PlayerMessage playerMessage) {
        Looper looper = playerMessage.looper;
        if (!looper.getThread().isAlive()) {
            Log.w("TAG", "Trying to send message on a dead thread.");
            playerMessage.markAsProcessed(false);
        } else {
            ((SystemHandlerWrapper) this.clock.createHandler(looper, null)).post(new Runnable() { // from class: com.google.android.exoplayer2.ExoPlayerImplInternal$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ExoPlayerImplInternal.$r8$lambda$J9YTXTK1k0zCfZsi6DQfZMT2sYk(this.f$0, playerMessage);
                }
            });
        }
    }

    public final void setAllRendererStreamsFinal(long j) {
        for (Renderer renderer : this.renderers) {
            if (renderer.getStream() != null) {
                setCurrentStreamFinal(renderer, j);
            }
        }
    }

    public final void setCurrentStreamFinal(Renderer renderer, long j) {
        renderer.setCurrentStreamFinal();
        if (renderer instanceof TextRenderer) {
            ((TextRenderer) renderer).setFinalStreamEndPositionUs(j);
        }
    }

    public synchronized boolean setForegroundMode(boolean z) {
        if (!this.released && this.playbackLooper.getThread().isAlive()) {
            if (z) {
                ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(13, 1, 0)).sendToTarget();
                return true;
            }
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(13, 0, 0, atomicBoolean)).sendToTarget();
            waitUninterruptibly(new ExoPlayerImplInternal$$ExternalSyntheticLambda3(atomicBoolean), this.setForegroundModeTimeoutMs);
            return atomicBoolean.get();
        }
        return true;
    }

    public final void setForegroundModeInternal(boolean z, @Nullable AtomicBoolean atomicBoolean) {
        if (this.foregroundMode != z) {
            this.foregroundMode = z;
            if (!z) {
                for (Renderer renderer : this.renderers) {
                    if (!isRendererEnabled(renderer) && this.renderersToReset.remove(renderer)) {
                        renderer.reset();
                    }
                }
            }
        }
        if (atomicBoolean != null) {
            synchronized (this) {
                atomicBoolean.set(true);
                notifyAll();
            }
        }
    }

    public final void setMediaClockPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.removeMessages(16);
        this.mediaClock.setPlaybackParameters(playbackParameters);
    }

    public final void setMediaItemsInternal(MediaSourceListUpdateMessage mediaSourceListUpdateMessage) throws Throwable {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        if (mediaSourceListUpdateMessage.windowIndex != -1) {
            this.pendingInitialSeekPosition = new SeekPosition(new PlaylistTimeline(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), mediaSourceListUpdateMessage.windowIndex, mediaSourceListUpdateMessage.positionUs);
        }
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.setMediaSources(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), false);
    }

    public void setMediaSources(List<MediaSourceList.MediaSourceHolder> list, int i, long j, ShuffleOrder shuffleOrder) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(17, new MediaSourceListUpdateMessage(list, shuffleOrder, i, j))).sendToTarget();
    }

    public final void setOffloadSchedulingEnabledInternal(boolean z) {
        if (z == this.offloadSchedulingEnabled) {
            return;
        }
        this.offloadSchedulingEnabled = z;
        if (z || !this.playbackInfo.sleepingForOffload) {
            return;
        }
        this.handler.sendEmptyMessage(2);
    }

    public void setPauseAtEndOfWindow(boolean z) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(23, z ? 1 : 0, 0)).sendToTarget();
    }

    public final void setPauseAtEndOfWindowInternal(boolean z) throws ExoPlaybackException {
        this.pauseAtEndOfWindow = z;
        resetPendingPauseAtEndOfPeriod();
        if (this.pendingPauseAtEndOfPeriod) {
            MediaPeriodQueue mediaPeriodQueue = this.queue;
            if (mediaPeriodQueue.reading != mediaPeriodQueue.playing) {
                seekToCurrentPosition(true);
                handleLoadingMediaPeriodChanged(false);
            }
        }
    }

    public void setPlayWhenReady(boolean z, int i) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(1, z ? 1 : 0, i)).sendToTarget();
    }

    public final void setPlayWhenReadyInternal(boolean z, int i, boolean z2, int i2) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(z2 ? 1 : 0);
        this.playbackInfoUpdate.setPlayWhenReadyChangeReason(i2);
        this.playbackInfo = this.playbackInfo.copyWithPlayWhenReady(z, i);
        this.isRebuffering = false;
        notifyTrackSelectionPlayWhenReadyChanged(z);
        if (!shouldPlayWhenReady()) {
            stopRenderers();
            updatePlaybackPositions();
            return;
        }
        int i3 = this.playbackInfo.playbackState;
        if (i3 == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (i3 == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(4, playbackParameters)).sendToTarget();
    }

    public final void setPlaybackParametersInternal(PlaybackParameters playbackParameters) throws ExoPlaybackException {
        setMediaClockPlaybackParameters(playbackParameters);
        handlePlaybackParameters(this.mediaClock.getPlaybackParameters(), true);
    }

    public void setRepeatMode(int i) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(11, i, 0)).sendToTarget();
    }

    public final void setRepeatModeInternal(int i) throws ExoPlaybackException {
        this.repeatMode = i;
        MediaPeriodQueue mediaPeriodQueue = this.queue;
        Timeline timeline = this.playbackInfo.timeline;
        mediaPeriodQueue.repeatMode = i;
        if (!mediaPeriodQueue.updateForPlaybackModeChange(timeline)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    public void setSeekParameters(SeekParameters seekParameters) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(5, seekParameters)).sendToTarget();
    }

    public final void setSeekParametersInternal(SeekParameters seekParameters) {
        this.seekParameters = seekParameters;
    }

    public void setShuffleModeEnabled(boolean z) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(12, z ? 1 : 0, 0)).sendToTarget();
    }

    public final void setShuffleModeEnabledInternal(boolean z) throws ExoPlaybackException {
        this.shuffleModeEnabled = z;
        MediaPeriodQueue mediaPeriodQueue = this.queue;
        Timeline timeline = this.playbackInfo.timeline;
        mediaPeriodQueue.shuffleModeEnabled = z;
        if (!mediaPeriodQueue.updateForPlaybackModeChange(timeline)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    public void setShuffleOrder(ShuffleOrder shuffleOrder) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(21, shuffleOrder)).sendToTarget();
    }

    public final void setShuffleOrderInternal(ShuffleOrder shuffleOrder) throws Throwable {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.setShuffleOrder(shuffleOrder), false);
    }

    public final void setState(int i) {
        if (this.playbackInfo.playbackState != i) {
            if (i != 2) {
                this.playbackMaybeBecameStuckAtMs = -9223372036854775807L;
            }
            this.log.d("state: Current= " + getStateString(i) + ", Previous= " + getStateString(this.playbackInfo.playbackState));
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(i);
        }
    }

    public final boolean shouldAdvancePlayingPeriod() {
        MediaPeriodHolder mediaPeriodHolder;
        MediaPeriodHolder mediaPeriodHolder2;
        return shouldPlayWhenReady() && !this.pendingPauseAtEndOfPeriod && (mediaPeriodHolder = this.queue.playing) != null && (mediaPeriodHolder2 = mediaPeriodHolder.next) != null && this.rendererPositionUs >= mediaPeriodHolder2.getStartPositionRendererTime() && mediaPeriodHolder2.allRenderersInCorrectState;
    }

    public final boolean shouldContinueLoading() {
        long j;
        long j2;
        if (!isLoadingPossible()) {
            return false;
        }
        MediaPeriodHolder mediaPeriodHolder = this.queue.loading;
        long totalBufferedDurationUs = getTotalBufferedDurationUs(mediaPeriodHolder.getNextLoadPositionUs());
        if (mediaPeriodHolder == this.queue.playing) {
            j = this.rendererPositionUs;
            j2 = mediaPeriodHolder.rendererPositionOffsetUs;
        } else {
            j = this.rendererPositionUs - mediaPeriodHolder.rendererPositionOffsetUs;
            j2 = mediaPeriodHolder.info.startPositionUs;
        }
        long j3 = j - j2;
        boolean zShouldContinueLoading = this.loadControl.shouldContinueLoading(j3, totalBufferedDurationUs, this.mediaClock.getPlaybackParameters().speed);
        if (zShouldContinueLoading || totalBufferedDurationUs >= 500000) {
            return zShouldContinueLoading;
        }
        if (this.backBufferDurationUs <= 0 && !this.retainBackBufferFromKeyframe) {
            return zShouldContinueLoading;
        }
        this.queue.playing.mediaPeriod.discardBuffer(this.playbackInfo.positionUs, false);
        return this.loadControl.shouldContinueLoading(j3, totalBufferedDurationUs, this.mediaClock.getPlaybackParameters().speed);
    }

    public final boolean shouldPlayWhenReady() {
        PlaybackInfo playbackInfo = this.playbackInfo;
        return playbackInfo.playWhenReady && playbackInfo.playbackSuppressionReason == 0;
    }

    public final boolean shouldTransitionToReadyState(boolean z) {
        if (this.enabledRendererCount == 0) {
            return isTimelineReady();
        }
        if (z) {
            PlaybackInfo playbackInfo = this.playbackInfo;
            if (playbackInfo.isLoading) {
                long targetLiveOffsetUs = shouldUseLivePlaybackSpeedControl(playbackInfo.timeline, this.queue.playing.info.id) ? this.livePlaybackSpeedControl.getTargetLiveOffsetUs() : -9223372036854775807L;
                MediaPeriodHolder mediaPeriodHolder = this.queue.loading;
                boolean z2 = mediaPeriodHolder.isFullyBuffered() && mediaPeriodHolder.info.isFinal;
                boolean z3 = mediaPeriodHolder.info.id.isAd() && !mediaPeriodHolder.prepared;
                if (z2 || z3 || this.loadControl.shouldStartPlayback(getTotalBufferedDurationUs(), this.mediaClock.getPlaybackParameters().speed, this.isRebuffering, targetLiveOffsetUs)) {
                }
            }
            return true;
        }
        return false;
    }

    public final boolean shouldUseLivePlaybackSpeedControl(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (mediaPeriodId.isAd() || timeline.isEmpty()) {
            return false;
        }
        timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window);
        if (!this.window.isLive()) {
            return false;
        }
        Timeline.Window window = this.window;
        return window.isDynamic && window.windowStartTimeMs != -9223372036854775807L;
    }

    public final void startRenderers() throws ExoPlaybackException {
        this.isRebuffering = false;
        this.mediaClock.start();
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                renderer.start();
            }
        }
    }

    public void stop() {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(6)).sendToTarget();
    }

    public final void stopInternal(boolean z, boolean z2) {
        resetInternal(z || !this.foregroundMode, false, true, false);
        this.playbackInfoUpdate.incrementPendingOperationAcks(z2 ? 1 : 0);
        this.loadControl.onStopped();
        setState(1);
    }

    public final void stopRenderers() throws ExoPlaybackException {
        this.mediaClock.stop();
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                ensureStopped(renderer);
            }
        }
    }

    public final void updateIsLoading() {
        MediaPeriodHolder mediaPeriodHolder = this.queue.loading;
        boolean z = this.shouldContinueLoading || (mediaPeriodHolder != null && mediaPeriodHolder.mediaPeriod.isLoading());
        PlaybackInfo playbackInfo = this.playbackInfo;
        if (z != playbackInfo.isLoading) {
            this.playbackInfo = playbackInfo.copyWithIsLoading(z);
        }
    }

    public final void updateLoadControlTrackSelection(TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult) {
        this.loadControl.onTracksSelected(this.renderers, trackGroupArray, trackSelectorResult.selections);
    }

    public final void updatePeriods() throws ExoPlaybackException {
        if (this.playbackInfo.timeline.isEmpty() || !this.mediaSourceList.isPrepared) {
            return;
        }
        maybeUpdateLoadingPeriod();
        maybeUpdateReadingPeriod();
        maybeUpdateReadingRenderers();
        maybeUpdatePlayingPeriod();
    }

    public final void updatePlaybackPositions() throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder = this.queue.playing;
        if (mediaPeriodHolder == null) {
            return;
        }
        long discontinuity = mediaPeriodHolder.prepared ? mediaPeriodHolder.mediaPeriod.readDiscontinuity() : -9223372036854775807L;
        if (discontinuity != -9223372036854775807L) {
            resetRendererPosition(discontinuity);
            if (discontinuity != this.playbackInfo.positionUs) {
                PlaybackInfo playbackInfo = this.playbackInfo;
                long j = discontinuity;
                this.playbackInfo = handlePositionDiscontinuity(playbackInfo.periodId, j, playbackInfo.requestedContentPositionUs, j, true, 5);
            }
        } else {
            long jSyncAndGetPositionUs = this.mediaClock.syncAndGetPositionUs(mediaPeriodHolder != this.queue.reading);
            this.rendererPositionUs = jSyncAndGetPositionUs;
            long j2 = jSyncAndGetPositionUs - mediaPeriodHolder.rendererPositionOffsetUs;
            maybeTriggerPendingMessages(this.playbackInfo.positionUs, j2);
            this.playbackInfo.positionUs = j2;
        }
        this.playbackInfo.bufferedPositionUs = this.queue.loading.getBufferedPositionUs();
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (playbackInfo2.playWhenReady && playbackInfo2.playbackState == 3 && shouldUseLivePlaybackSpeedControl(playbackInfo2.timeline, playbackInfo2.periodId) && this.playbackInfo.playbackParameters.speed == 1.0f) {
            float adjustedPlaybackSpeed = this.livePlaybackSpeedControl.getAdjustedPlaybackSpeed(getCurrentLiveOffsetUs(), getTotalBufferedDurationUs());
            if (this.mediaClock.getPlaybackParameters().speed != adjustedPlaybackSpeed) {
                setMediaClockPlaybackParameters(this.playbackInfo.playbackParameters.withSpeed(adjustedPlaybackSpeed));
                handlePlaybackParameters(this.playbackInfo.playbackParameters, this.mediaClock.getPlaybackParameters().speed, false, false);
            }
        }
    }

    public final void updatePlaybackSpeedSettingsForNewPeriod(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline2, MediaSource.MediaPeriodId mediaPeriodId2, long j, boolean z) throws ExoPlaybackException {
        if (!shouldUseLivePlaybackSpeedControl(timeline, mediaPeriodId)) {
            PlaybackParameters playbackParameters = mediaPeriodId.isAd() ? PlaybackParameters.DEFAULT : this.playbackInfo.playbackParameters;
            if (this.mediaClock.getPlaybackParameters().equals(playbackParameters)) {
                return;
            }
            setMediaClockPlaybackParameters(playbackParameters);
            handlePlaybackParameters(this.playbackInfo.playbackParameters, playbackParameters.speed, false, false);
            return;
        }
        timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window);
        this.livePlaybackSpeedControl.setLiveConfiguration(this.window.liveConfiguration);
        if (j != -9223372036854775807L) {
            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(getLiveOffsetUs(timeline, mediaPeriodId.periodUid, j));
            return;
        }
        if (!Util.areEqual(!timeline2.isEmpty() ? timeline2.getWindow(timeline2.getPeriodByUid(mediaPeriodId2.periodUid, this.period).windowIndex, this.window, 0L).uid : null, this.window.uid) || z) {
            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(-9223372036854775807L);
        }
    }

    public final void updateTrackSelectionPlaybackSpeed(float f) {
        for (MediaPeriodHolder mediaPeriodHolder = this.queue.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.next) {
            for (ExoTrackSelection exoTrackSelection : mediaPeriodHolder.trackSelectorResult.selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onPlaybackSpeed(f);
                }
            }
        }
    }

    public final synchronized void waitUninterruptibly(Supplier<Boolean> supplier, long j) {
        long jElapsedRealtime = this.clock.elapsedRealtime() + j;
        boolean z = false;
        while (!supplier.get().booleanValue() && j > 0) {
            try {
                this.clock.getClass();
                wait(j);
            } catch (InterruptedException unused) {
                z = true;
            }
            j = jElapsedRealtime - this.clock.elapsedRealtime();
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public final void enableRenderers(boolean[] zArr) throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder = this.queue.reading;
        TrackSelectorResult trackSelectorResult = mediaPeriodHolder.trackSelectorResult;
        for (int i = 0; i < this.renderers.length; i++) {
            if (!trackSelectorResult.isRendererEnabled(i) && this.renderersToReset.remove(this.renderers[i])) {
                this.renderers[i].reset();
            }
        }
        for (int i2 = 0; i2 < this.renderers.length; i2++) {
            if (trackSelectorResult.isRendererEnabled(i2)) {
                enableRenderer(i2, zArr[i2]);
            }
        }
        mediaPeriodHolder.allRenderersInCorrectState = true;
    }

    public final long getTotalBufferedDurationUs(long j) {
        MediaPeriodHolder mediaPeriodHolder = this.queue.loading;
        if (mediaPeriodHolder == null) {
            return 0L;
        }
        return Math.max(0L, j - (this.rendererPositionUs - mediaPeriodHolder.rendererPositionOffsetUs));
    }

    public final void handlePlaybackParameters(PlaybackParameters playbackParameters, float f, boolean z, boolean z2) throws ExoPlaybackException {
        if (z) {
            if (z2) {
                this.playbackInfoUpdate.incrementPendingOperationAcks(1);
            }
            this.playbackInfo = this.playbackInfo.copyWithPlaybackParameters(playbackParameters);
        }
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
        for (Renderer renderer : this.renderers) {
            if (renderer != null) {
                renderer.setPlaybackSpeed(f, playbackParameters.speed);
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        ((SystemHandlerWrapper.SystemMessage) this.handler.obtainMessage(9, mediaPeriod)).sendToTarget();
    }

    public final long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j, boolean z, boolean z2) throws ExoPlaybackException {
        MediaPeriodQueue mediaPeriodQueue;
        stopRenderers();
        this.isRebuffering = false;
        if (z2 || this.playbackInfo.playbackState == 3) {
            setState(2);
        }
        MediaPeriodHolder mediaPeriodHolder = this.queue.playing;
        MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder;
        while (mediaPeriodHolder2 != null && !mediaPeriodId.equals(mediaPeriodHolder2.info.id)) {
            mediaPeriodHolder2 = mediaPeriodHolder2.next;
        }
        if (z || mediaPeriodHolder != mediaPeriodHolder2 || (mediaPeriodHolder2 != null && mediaPeriodHolder2.rendererPositionOffsetUs + j < 0)) {
            for (Renderer renderer : this.renderers) {
                disableRenderer(renderer);
            }
            if (mediaPeriodHolder2 != null) {
                while (true) {
                    mediaPeriodQueue = this.queue;
                    if (mediaPeriodQueue.playing == mediaPeriodHolder2) {
                        break;
                    }
                    mediaPeriodQueue.advancePlayingPeriod();
                }
                mediaPeriodQueue.removeAfter(mediaPeriodHolder2);
                mediaPeriodHolder2.rendererPositionOffsetUs = 1000000000000L;
                enableRenderers();
            }
        }
        if (mediaPeriodHolder2 != null) {
            this.queue.removeAfter(mediaPeriodHolder2);
            if (!mediaPeriodHolder2.prepared) {
                mediaPeriodHolder2.info = mediaPeriodHolder2.info.copyWithStartPositionUs(j);
            } else if (mediaPeriodHolder2.hasEnabledTracks) {
                j = mediaPeriodHolder2.mediaPeriod.seekToUs(j);
                mediaPeriodHolder2.mediaPeriod.discardBuffer(j - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            }
            resetRendererPosition(j);
            maybeContinueLoading();
        } else {
            this.queue.clear();
            resetRendererPosition(j);
        }
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return j;
    }
}
