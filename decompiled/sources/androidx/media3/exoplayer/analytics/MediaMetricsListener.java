package androidx.media3.exoplayer.analytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.DeniedByServerException;
import android.media.MediaCodec;
import android.media.MediaDrm;
import android.media.NotProvisionedException;
import android.media.metrics.LogSessionId;
import android.media.metrics.MediaMetricsManager;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackSession;
import android.media.metrics.TrackChangeEvent;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.NetworkTypeObserver;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.FileDataSource;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.UdpDataSource;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.PlaybackSessionManager;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.UnsupportedDrmException;
import androidx.media3.exoplayer.mediacodec.MediaCodecDecoderException;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(31)
@UnstableApi
public final class MediaMetricsListener implements AnalyticsListener, PlaybackSessionManager.Listener {

    @Nullable
    public String activeSessionId;
    public int audioUnderruns;
    public final Context context;

    @Nullable
    public Format currentAudioFormat;

    @Nullable
    public Format currentTextFormat;

    @Nullable
    public Format currentVideoFormat;
    public int discontinuityReason;
    public int droppedFrames;
    public boolean hasFatalError;
    public int ioErrorType;
    public boolean isSeeking;

    @Nullable
    public PlaybackMetrics.Builder metricsBuilder;

    @Nullable
    public PendingFormatUpdate pendingAudioFormat;

    @Nullable
    public PlaybackException pendingPlayerError;

    @Nullable
    public PendingFormatUpdate pendingTextFormat;

    @Nullable
    public PendingFormatUpdate pendingVideoFormat;
    public final PlaybackSession playbackSession;
    public int playedFrames;
    public boolean reportedEventsForCurrentSession;
    public final PlaybackSessionManager sessionManager;
    public final Timeline.Window window = new Timeline.Window();
    public final Timeline.Period period = new Timeline.Period();
    public final HashMap<String, Long> bandwidthBytes = new HashMap<>();
    public final HashMap<String, Long> bandwidthTimeMs = new HashMap<>();
    public final long startTimeMs = SystemClock.elapsedRealtime();
    public int currentPlaybackState = 0;
    public int currentNetworkType = 0;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ErrorInfo {
        public final int errorCode;
        public final int subErrorCode;

        public ErrorInfo(int i, int i2) {
            this.errorCode = i;
            this.subErrorCode = i2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PendingFormatUpdate {
        public final Format format;
        public final int selectionReason;
        public final String sessionId;

        public PendingFormatUpdate(Format format, int i, String str) {
            this.format = format;
            this.selectionReason = i;
            this.sessionId = str;
        }
    }

    public MediaMetricsListener(Context context, PlaybackSession playbackSession) {
        this.context = context.getApplicationContext();
        this.playbackSession = playbackSession;
        DefaultPlaybackSessionManager defaultPlaybackSessionManager = new DefaultPlaybackSessionManager();
        this.sessionManager = defaultPlaybackSessionManager;
        defaultPlaybackSessionManager.listener = this;
    }

    @Nullable
    public static MediaMetricsListener create(Context context) {
        MediaMetricsManager mediaMetricsManagerM = MediaMetricsListener$$ExternalSyntheticApiModelOutline6.m(context.getSystemService("media_metrics"));
        if (mediaMetricsManagerM == null) {
            return null;
        }
        return new MediaMetricsListener(context, mediaMetricsManagerM.createPlaybackSession());
    }

    @SuppressLint({"SwitchIntDef"})
    public static int getDrmErrorCode(int i) {
        switch (Util.getErrorCodeForMediaDrmErrorCode(i)) {
            case 6002:
                return 24;
            case 6003:
                return 28;
            case 6004:
                return 25;
            case 6005:
                return 26;
            default:
                return 27;
        }
    }

    @Nullable
    public static DrmInitData getDrmInitData(ImmutableList<Tracks.Group> immutableList) {
        DrmInitData drmInitData;
        UnmodifiableIterator<Tracks.Group> it = immutableList.iterator();
        while (it.hasNext()) {
            Tracks.Group next = it.next();
            for (int i = 0; i < next.length; i++) {
                if (next.trackSelected[i] && (drmInitData = next.getTrackFormat(i).drmInitData) != null) {
                    return drmInitData;
                }
            }
        }
        return null;
    }

    public static int getDrmType(DrmInitData drmInitData) {
        for (int i = 0; i < drmInitData.schemeDataCount; i++) {
            UUID uuid = drmInitData.schemeDatas[i].uuid;
            if (uuid.equals(C.WIDEVINE_UUID)) {
                return 3;
            }
            if (uuid.equals(C.PLAYREADY_UUID)) {
                return 2;
            }
            if (uuid.equals(C.CLEARKEY_UUID)) {
                return 6;
            }
        }
        return 1;
    }

    public static ErrorInfo getErrorInfo(PlaybackException playbackException, Context context, boolean z) {
        int i;
        boolean z2;
        if (playbackException.errorCode == 1001) {
            return new ErrorInfo(20, 0);
        }
        if (playbackException instanceof ExoPlaybackException) {
            ExoPlaybackException exoPlaybackException = (ExoPlaybackException) playbackException;
            z2 = exoPlaybackException.type == 1;
            i = exoPlaybackException.rendererFormatSupport;
        } else {
            i = 0;
            z2 = false;
        }
        Throwable cause = playbackException.getCause();
        cause.getClass();
        if (!(cause instanceof IOException)) {
            if (z2 && (i == 0 || i == 1)) {
                return new ErrorInfo(35, 0);
            }
            if (z2 && i == 3) {
                return new ErrorInfo(15, 0);
            }
            if (z2 && i == 2) {
                return new ErrorInfo(23, 0);
            }
            if (cause instanceof MediaCodecRenderer.DecoderInitializationException) {
                return new ErrorInfo(13, Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaCodecRenderer.DecoderInitializationException) cause).diagnosticInfo));
            }
            if (cause instanceof MediaCodecDecoderException) {
                return new ErrorInfo(14, Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaCodecDecoderException) cause).diagnosticInfo));
            }
            if (cause instanceof OutOfMemoryError) {
                return new ErrorInfo(14, 0);
            }
            if (cause instanceof AudioSink.InitializationException) {
                return new ErrorInfo(17, ((AudioSink.InitializationException) cause).audioTrackState);
            }
            if (cause instanceof AudioSink.WriteException) {
                return new ErrorInfo(18, ((AudioSink.WriteException) cause).errorCode);
            }
            if (Util.SDK_INT < 16 || !(cause instanceof MediaCodec.CryptoException)) {
                return new ErrorInfo(22, 0);
            }
            int errorCode = ((MediaCodec.CryptoException) cause).getErrorCode();
            return new ErrorInfo(getDrmErrorCode(errorCode), errorCode);
        }
        if (cause instanceof HttpDataSource.InvalidResponseCodeException) {
            return new ErrorInfo(5, ((HttpDataSource.InvalidResponseCodeException) cause).responseCode);
        }
        if ((cause instanceof HttpDataSource.InvalidContentTypeException) || (cause instanceof ParserException)) {
            return new ErrorInfo(z ? 10 : 11, 0);
        }
        boolean z3 = cause instanceof HttpDataSource.HttpDataSourceException;
        if (z3 || (cause instanceof UdpDataSource.UdpDataSourceException)) {
            if (NetworkTypeObserver.getInstance(context).getNetworkType() == 1) {
                return new ErrorInfo(3, 0);
            }
            Throwable cause2 = cause.getCause();
            return cause2 instanceof UnknownHostException ? new ErrorInfo(6, 0) : cause2 instanceof SocketTimeoutException ? new ErrorInfo(7, 0) : (z3 && ((HttpDataSource.HttpDataSourceException) cause).type == 1) ? new ErrorInfo(4, 0) : new ErrorInfo(8, 0);
        }
        if (playbackException.errorCode == 1002) {
            return new ErrorInfo(21, 0);
        }
        if (!(cause instanceof DrmSession.DrmSessionException)) {
            if (!(cause instanceof FileDataSource.FileDataSourceException) || !(cause.getCause() instanceof FileNotFoundException)) {
                return new ErrorInfo(9, 0);
            }
            Throwable cause3 = cause.getCause();
            cause3.getClass();
            Throwable cause4 = cause3.getCause();
            return (Util.SDK_INT >= 21 && (cause4 instanceof ErrnoException) && ((ErrnoException) cause4).errno == OsConstants.EACCES) ? new ErrorInfo(32, 0) : new ErrorInfo(31, 0);
        }
        Throwable cause5 = cause.getCause();
        cause5.getClass();
        int i2 = Util.SDK_INT;
        if (i2 < 21 || !(cause5 instanceof MediaDrm.MediaDrmStateException)) {
            return (i2 < 23 || !MediaMetricsListener$$ExternalSyntheticApiModelOutline5.m(cause5)) ? (i2 < 18 || !(cause5 instanceof NotProvisionedException)) ? (i2 < 18 || !(cause5 instanceof DeniedByServerException)) ? cause5 instanceof UnsupportedDrmException ? new ErrorInfo(23, 0) : cause5 instanceof DefaultDrmSessionManager.MissingSchemeDataException ? new ErrorInfo(28, 0) : new ErrorInfo(30, 0) : new ErrorInfo(29, 0) : new ErrorInfo(24, 0) : new ErrorInfo(27, 0);
        }
        int errorCodeFromPlatformDiagnosticsInfo = Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaDrm.MediaDrmStateException) cause5).getDiagnosticInfo());
        return new ErrorInfo(getDrmErrorCode(errorCodeFromPlatformDiagnosticsInfo), errorCodeFromPlatformDiagnosticsInfo);
    }

    public static Pair<String, String> getLanguageAndRegion(String str) {
        String[] strArrSplit = Util.split(str, "-");
        return Pair.create(strArrSplit[0], strArrSplit.length >= 2 ? strArrSplit[1] : null);
    }

    public static int getNetworkType(Context context) {
        switch (NetworkTypeObserver.getInstance(context).getNetworkType()) {
            case 0:
                return 0;
            case 1:
                return 9;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
            case 8:
            default:
                return 1;
            case 7:
                return 3;
            case 9:
                return 8;
            case 10:
                return 7;
        }
    }

    public static int getStreamType(MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        if (localConfiguration == null) {
            return 0;
        }
        int iInferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType);
        if (iInferContentTypeForUriAndMimeType == 0) {
            return 3;
        }
        if (iInferContentTypeForUriAndMimeType != 1) {
            return iInferContentTypeForUriAndMimeType != 2 ? 1 : 4;
        }
        return 5;
    }

    public static int getTrackChangeReason(int i) {
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 1 : 4;
        }
        return 3;
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    public final boolean canReportPendingFormatUpdate(@Nullable PendingFormatUpdate pendingFormatUpdate) {
        return pendingFormatUpdate != null && pendingFormatUpdate.sessionId.equals(this.sessionManager.getActiveSessionId());
    }

    public final void finishCurrentSession() {
        PlaybackMetrics.Builder builder = this.metricsBuilder;
        if (builder != null && this.reportedEventsForCurrentSession) {
            builder.setAudioUnderrunCount(this.audioUnderruns);
            this.metricsBuilder.setVideoFramesDropped(this.droppedFrames);
            this.metricsBuilder.setVideoFramesPlayed(this.playedFrames);
            Long l = this.bandwidthTimeMs.get(this.activeSessionId);
            this.metricsBuilder.setNetworkTransferDurationMillis(l == null ? 0L : l.longValue());
            Long l2 = this.bandwidthBytes.get(this.activeSessionId);
            this.metricsBuilder.setNetworkBytesRead(l2 == null ? 0L : l2.longValue());
            this.metricsBuilder.setStreamSource((l2 == null || l2.longValue() <= 0) ? 0 : 1);
            this.playbackSession.reportPlaybackMetrics(this.metricsBuilder.build());
        }
        this.metricsBuilder = null;
        this.activeSessionId = null;
        this.audioUnderruns = 0;
        this.droppedFrames = 0;
        this.playedFrames = 0;
        this.currentVideoFormat = null;
        this.currentAudioFormat = null;
        this.currentTextFormat = null;
        this.reportedEventsForCurrentSession = false;
    }

    public LogSessionId getLogSessionId() {
        return this.playbackSession.getSessionId();
    }

    public final void maybeAddSessions(AnalyticsListener.Events events) {
        for (int i = 0; i < events.size(); i++) {
            int i2 = events.flags.get(i);
            AnalyticsListener.EventTime eventTime = events.getEventTime(i2);
            if (i2 == 0) {
                this.sessionManager.updateSessionsWithTimelineChange(eventTime);
            } else if (i2 == 11) {
                this.sessionManager.updateSessionsWithDiscontinuity(eventTime, this.discontinuityReason);
            } else {
                this.sessionManager.updateSessions(eventTime);
            }
        }
    }

    public final void maybeReportNetworkChange(long j) {
        int networkType = getNetworkType(this.context);
        if (networkType != this.currentNetworkType) {
            this.currentNetworkType = networkType;
            this.playbackSession.reportNetworkEvent(MediaMetricsListener$$ExternalSyntheticApiModelOutline1.m().setNetworkType(networkType).setTimeSinceCreatedMillis(j - this.startTimeMs).build());
        }
    }

    public final void maybeReportPlaybackError(long j) {
        PlaybackException playbackException = this.pendingPlayerError;
        if (playbackException == null) {
            return;
        }
        ErrorInfo errorInfo = getErrorInfo(playbackException, this.context, this.ioErrorType == 4);
        this.playbackSession.reportPlaybackErrorEvent(MediaMetricsListener$$ExternalSyntheticApiModelOutline2.m().setTimeSinceCreatedMillis(j - this.startTimeMs).setErrorCode(errorInfo.errorCode).setSubErrorCode(errorInfo.subErrorCode).setException(playbackException).build());
        this.reportedEventsForCurrentSession = true;
        this.pendingPlayerError = null;
    }

    public final void maybeReportPlaybackStateChange(Player player, AnalyticsListener.Events events, long j) {
        if (player.getPlaybackState() != 2) {
            this.isSeeking = false;
        }
        if (player.getPlayerError() == null) {
            this.hasFatalError = false;
        } else if (events.contains(10)) {
            this.hasFatalError = true;
        }
        int iResolveNewPlaybackState = resolveNewPlaybackState(player);
        if (this.currentPlaybackState != iResolveNewPlaybackState) {
            this.currentPlaybackState = iResolveNewPlaybackState;
            this.reportedEventsForCurrentSession = true;
            this.playbackSession.reportPlaybackStateEvent(MediaMetricsListener$$ExternalSyntheticApiModelOutline3.m().setState(this.currentPlaybackState).setTimeSinceCreatedMillis(j - this.startTimeMs).build());
        }
    }

    public final void maybeReportTrackChanges(Player player, AnalyticsListener.Events events, long j) {
        if (events.contains(2)) {
            Tracks currentTracks = player.getCurrentTracks();
            boolean zIsTypeSelected = currentTracks.isTypeSelected(2);
            boolean zIsTypeSelected2 = currentTracks.isTypeSelected(1);
            boolean zIsTypeSelected3 = currentTracks.isTypeSelected(3);
            if (zIsTypeSelected || zIsTypeSelected2 || zIsTypeSelected3) {
                if (!zIsTypeSelected) {
                    maybeUpdateVideoFormat(j, null, 0);
                }
                if (!zIsTypeSelected2) {
                    maybeUpdateAudioFormat(j, null, 0);
                }
                if (!zIsTypeSelected3) {
                    maybeUpdateTextFormat(j, null, 0);
                }
            }
        }
        if (canReportPendingFormatUpdate(this.pendingVideoFormat)) {
            PendingFormatUpdate pendingFormatUpdate = this.pendingVideoFormat;
            Format format = pendingFormatUpdate.format;
            if (format.height != -1) {
                maybeUpdateVideoFormat(j, format, pendingFormatUpdate.selectionReason);
                this.pendingVideoFormat = null;
            }
        }
        if (canReportPendingFormatUpdate(this.pendingAudioFormat)) {
            PendingFormatUpdate pendingFormatUpdate2 = this.pendingAudioFormat;
            maybeUpdateAudioFormat(j, pendingFormatUpdate2.format, pendingFormatUpdate2.selectionReason);
            this.pendingAudioFormat = null;
        }
        if (canReportPendingFormatUpdate(this.pendingTextFormat)) {
            PendingFormatUpdate pendingFormatUpdate3 = this.pendingTextFormat;
            maybeUpdateTextFormat(j, pendingFormatUpdate3.format, pendingFormatUpdate3.selectionReason);
            this.pendingTextFormat = null;
        }
    }

    public final void maybeUpdateAudioFormat(long j, @Nullable Format format, int i) {
        if (Util.areEqual(this.currentAudioFormat, format)) {
            return;
        }
        int i2 = (this.currentAudioFormat == null && i == 0) ? 1 : i;
        this.currentAudioFormat = format;
        reportTrackChangeEvent(0, j, format, i2);
    }

    public final void maybeUpdateMetricsBuilderValues(Player player, AnalyticsListener.Events events) {
        DrmInitData drmInitData;
        if (events.contains(0)) {
            AnalyticsListener.EventTime eventTime = events.getEventTime(0);
            if (this.metricsBuilder != null) {
                maybeUpdateTimelineMetadata(eventTime.timeline, eventTime.mediaPeriodId);
            }
        }
        if (events.contains(2) && this.metricsBuilder != null && (drmInitData = getDrmInitData(player.getCurrentTracks().groups)) != null) {
            PlaybackMetrics.Builder builder = this.metricsBuilder;
            Util.castNonNull(builder);
            MediaMetricsListener$$ExternalSyntheticApiModelOutline4.m(builder);
            builder.setDrmType(getDrmType(drmInitData));
        }
        if (events.contains(1011)) {
            this.audioUnderruns++;
        }
    }

    public final void maybeUpdateTextFormat(long j, @Nullable Format format, int i) {
        if (Util.areEqual(this.currentTextFormat, format)) {
            return;
        }
        int i2 = (this.currentTextFormat == null && i == 0) ? 1 : i;
        this.currentTextFormat = format;
        reportTrackChangeEvent(2, j, format, i2);
    }

    @RequiresNonNull({"metricsBuilder"})
    public final void maybeUpdateTimelineMetadata(Timeline timeline, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        int indexOfPeriod;
        PlaybackMetrics.Builder builder = this.metricsBuilder;
        if (mediaPeriodId == null || (indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodId.periodUid)) == -1) {
            return;
        }
        timeline.getPeriod(indexOfPeriod, this.period, false);
        timeline.getWindow(this.period.windowIndex, this.window);
        builder.setStreamType(getStreamType(this.window.mediaItem));
        Timeline.Window window = this.window;
        if (window.durationUs != -9223372036854775807L && !window.isPlaceholder && !window.isDynamic && !window.isLive()) {
            builder.setMediaDurationMillis(Util.usToMs(this.window.durationUs));
        }
        builder.setPlaybackType(this.window.isLive() ? 2 : 1);
        this.reportedEventsForCurrentSession = true;
    }

    public final void maybeUpdateVideoFormat(long j, @Nullable Format format, int i) {
        if (Util.areEqual(this.currentVideoFormat, format)) {
            return;
        }
        int i2 = (this.currentVideoFormat == null && i == 0) ? 1 : i;
        this.currentVideoFormat = format;
        reportTrackChangeEvent(1, j, format, i2);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if (mediaPeriodId != null) {
            String sessionForMediaPeriodId = this.sessionManager.getSessionForMediaPeriodId(eventTime.timeline, mediaPeriodId);
            Long l = this.bandwidthBytes.get(sessionForMediaPeriodId);
            Long l2 = this.bandwidthTimeMs.get(sessionForMediaPeriodId);
            this.bandwidthBytes.put(sessionForMediaPeriodId, Long.valueOf((l == null ? 0L : l.longValue()) + j));
            this.bandwidthTimeMs.put(sessionForMediaPeriodId, Long.valueOf((l2 != null ? l2.longValue() : 0L) + ((long) i)));
        }
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, CueGroup cueGroup) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        if (eventTime.mediaPeriodId == null) {
            return;
        }
        Format format = mediaLoadData.trackFormat;
        format.getClass();
        int i = mediaLoadData.trackSelectionReason;
        PlaybackSessionManager playbackSessionManager = this.sessionManager;
        Timeline timeline = eventTime.timeline;
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        mediaPeriodId.getClass();
        PendingFormatUpdate pendingFormatUpdate = new PendingFormatUpdate(format, i, playbackSessionManager.getSessionForMediaPeriodId(timeline, mediaPeriodId));
        int i2 = mediaLoadData.trackType;
        if (i2 != 0) {
            if (i2 == 1) {
                this.pendingAudioFormat = pendingFormatUpdate;
                return;
            } else if (i2 != 2) {
                if (i2 != 3) {
                    return;
                }
                this.pendingTextFormat = pendingFormatUpdate;
                return;
            }
        }
        this.pendingVideoFormat = pendingFormatUpdate;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onEvents(Player player, AnalyticsListener.Events events) {
        if (events.size() == 0) {
            return;
        }
        maybeAddSessions(events);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        maybeUpdateMetricsBuilderValues(player, events);
        maybeReportPlaybackError(jElapsedRealtime);
        maybeReportTrackChanges(player, events, jElapsedRealtime);
        maybeReportNetworkChange(jElapsedRealtime);
        maybeReportPlaybackStateChange(player, events, jElapsedRealtime);
        if (events.contains(1028)) {
            this.sessionManager.finishAllSessions(events.getEventTime(1028));
        }
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.ioErrorType = mediaLoadData.dataType;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPlayerError(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
        this.pendingPlayerError = playbackException;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onSessionActive(AnalyticsListener.EventTime eventTime, String str) {
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if (mediaPeriodId == null || !mediaPeriodId.isAd()) {
            finishCurrentSession();
            this.activeSessionId = str;
            this.metricsBuilder = MediaMetricsListener$$ExternalSyntheticApiModelOutline7.m().setPlayerName(MediaLibraryInfo.TAG).setPlayerVersion(MediaLibraryInfo.VERSION);
            maybeUpdateTimelineMetadata(eventTime.timeline, eventTime.mediaPeriodId);
        }
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onSessionFinished(AnalyticsListener.EventTime eventTime, String str, boolean z) {
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if ((mediaPeriodId == null || !mediaPeriodId.isAd()) && str.equals(this.activeSessionId)) {
            finishCurrentSession();
        }
        this.bandwidthTimeMs.remove(str);
        this.bandwidthBytes.remove(str);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        this.droppedFrames += decoderCounters.droppedBufferCount;
        this.playedFrames += decoderCounters.renderedOutputBufferCount;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2, int i3, float f) {
    }

    public final void reportTrackChangeEvent(int i, long j, @Nullable Format format, int i2) {
        TrackChangeEvent.Builder timeSinceCreatedMillis = MediaMetricsListener$$ExternalSyntheticApiModelOutline0.m(i).setTimeSinceCreatedMillis(j - this.startTimeMs);
        if (format != null) {
            timeSinceCreatedMillis.setTrackState(1);
            timeSinceCreatedMillis.setTrackChangeReason(getTrackChangeReason(i2));
            String str = format.containerMimeType;
            if (str != null) {
                timeSinceCreatedMillis.setContainerMimeType(str);
            }
            String str2 = format.sampleMimeType;
            if (str2 != null) {
                timeSinceCreatedMillis.setSampleMimeType(str2);
            }
            String str3 = format.codecs;
            if (str3 != null) {
                timeSinceCreatedMillis.setCodecName(str3);
            }
            int i3 = format.bitrate;
            if (i3 != -1) {
                timeSinceCreatedMillis.setBitrate(i3);
            }
            int i4 = format.width;
            if (i4 != -1) {
                timeSinceCreatedMillis.setWidth(i4);
            }
            int i5 = format.height;
            if (i5 != -1) {
                timeSinceCreatedMillis.setHeight(i5);
            }
            int i6 = format.channelCount;
            if (i6 != -1) {
                timeSinceCreatedMillis.setChannelCount(i6);
            }
            int i7 = format.sampleRate;
            if (i7 != -1) {
                timeSinceCreatedMillis.setAudioSampleRate(i7);
            }
            String str4 = format.language;
            if (str4 != null) {
                Pair<String, String> languageAndRegion = getLanguageAndRegion(str4);
                timeSinceCreatedMillis.setLanguage((String) languageAndRegion.first);
                Object obj = languageAndRegion.second;
                if (obj != null) {
                    timeSinceCreatedMillis.setLanguageRegion((String) obj);
                }
            }
            float f = format.frameRate;
            if (f != -1.0f) {
                timeSinceCreatedMillis.setVideoFrameRate(f);
            }
        } else {
            timeSinceCreatedMillis.setTrackState(0);
        }
        this.reportedEventsForCurrentSession = true;
        this.playbackSession.reportTrackChangeEvent(timeSinceCreatedMillis.build());
    }

    public final int resolveNewPlaybackState(Player player) {
        int playbackState = player.getPlaybackState();
        if (this.isSeeking) {
            return 5;
        }
        if (this.hasFatalError) {
            return 13;
        }
        if (playbackState == 4) {
            return 11;
        }
        if (playbackState == 2) {
            int i = this.currentPlaybackState;
            if (i == 0 || i == 2) {
                return 2;
            }
            if (player.getPlayWhenReady()) {
                return player.getPlaybackSuppressionReason() != 0 ? 10 : 6;
            }
            return 7;
        }
        if (playbackState == 3) {
            if (player.getPlayWhenReady()) {
                return player.getPlaybackSuppressionReason() != 0 ? 9 : 3;
            }
            return 4;
        }
        if (playbackState != 1 || this.currentPlaybackState == 0) {
            return this.currentPlaybackState;
        }
        return 12;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, List list) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
        if (i == 1) {
            this.isSeeking = true;
        }
        this.discontinuityReason = i;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
        PendingFormatUpdate pendingFormatUpdate = this.pendingVideoFormat;
        if (pendingFormatUpdate != null) {
            Format format = pendingFormatUpdate.format;
            if (format.height == -1) {
                format.getClass();
                Format.Builder builder = new Format.Builder(format);
                builder.width = videoSize.width;
                builder.height = videoSize.height;
                this.pendingVideoFormat = new PendingFormatUpdate(new Format(builder), pendingFormatUpdate.selectionReason, pendingFormatUpdate.sessionId);
            }
        }
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onDrmKeysLoaded(AnalyticsListener.EventTime eventTime) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onDrmKeysRemoved(AnalyticsListener.EventTime eventTime) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onDrmKeysRestored(AnalyticsListener.EventTime eventTime) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onDrmSessionReleased(AnalyticsListener.EventTime eventTime) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onPlayerReleased(AnalyticsListener.EventTime eventTime) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime eventTime) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioAttributesChanged(AnalyticsListener.EventTime eventTime, AudioAttributes audioAttributes) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioPositionAdvancing(AnalyticsListener.EventTime eventTime, long j) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioSessionIdChanged(AnalyticsListener.EventTime eventTime, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioSinkError(AnalyticsListener.EventTime eventTime, Exception exc) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioTrackInitialized(AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioTrackReleased(AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAvailableCommandsChanged(AnalyticsListener.EventTime eventTime, Player.Commands commands) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onDeviceInfoChanged(AnalyticsListener.EventTime eventTime, DeviceInfo deviceInfo) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception exc) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onIsLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onIsPlayingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onMaxSeekToPreviousPositionChanged(AnalyticsListener.EventTime eventTime, long j) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onMediaMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onMetadata(AnalyticsListener.EventTime eventTime, Metadata metadata) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onPlaybackParametersChanged(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onPlaybackStateChanged(AnalyticsListener.EventTime eventTime, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onPlaybackSuppressionReasonChanged(AnalyticsListener.EventTime eventTime, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onPlayerErrorChanged(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onPlaylistMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onRepeatModeChanged(AnalyticsListener.EventTime eventTime, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onSeekBackIncrementChanged(AnalyticsListener.EventTime eventTime, long j) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onSeekForwardIncrementChanged(AnalyticsListener.EventTime eventTime, long j) {
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onSessionCreated(AnalyticsListener.EventTime eventTime, String str) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onShuffleModeChanged(AnalyticsListener.EventTime eventTime, boolean z) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onSkipSilenceEnabledChanged(AnalyticsListener.EventTime eventTime, boolean z) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onTimelineChanged(AnalyticsListener.EventTime eventTime, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onTrackSelectionParametersChanged(AnalyticsListener.EventTime eventTime, TrackSelectionParameters trackSelectionParameters) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onTracksChanged(AnalyticsListener.EventTime eventTime, Tracks tracks) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onUpstreamDiscarded(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onVideoCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onVideoDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onVideoEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onVolumeChanged(AnalyticsListener.EventTime eventTime, float f) {
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onAdPlaybackStarted(AnalyticsListener.EventTime eventTime, String str, String str2) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onDeviceVolumeChanged(AnalyticsListener.EventTime eventTime, int i, boolean z) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int i, long j) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onLoadCanceled(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onLoadCompleted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onLoadStarted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onMediaItemTransition(AnalyticsListener.EventTime eventTime, MediaItem mediaItem, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onPlayWhenReadyChanged(AnalyticsListener.EventTime eventTime, boolean z, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onPlayerStateChanged(AnalyticsListener.EventTime eventTime, boolean z, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onRenderedFirstFrame(AnalyticsListener.EventTime eventTime, Object obj, long j) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onSurfaceSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onVideoFrameProcessingOffset(AnalyticsListener.EventTime eventTime, long j, int i) {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public /* synthetic */ void onAudioUnderrun(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
    }
}
