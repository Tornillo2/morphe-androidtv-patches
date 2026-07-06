package com.amazon.livingroom.mediapipelinebackend;

import android.content.Context;
import android.media.MediaCodec;
import android.os.Build;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.audio.AudioCapabilities;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.exoplayer.audio.MediaCodecAudioRenderer;
import androidx.media3.exoplayer.mediacodec.DefaultMediaCodecAdapterFactory;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.util.EventLogger;
import androidx.media3.exoplayer.video.MediaCodecVideoRenderer;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import com.amazon.ignitionshared.TextToSpeechStatusProvider;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.mediapipelinebackend.AvMediaPeriod;
import com.amazon.livingroom.mediapipelinebackend.AvSampleStream;
import com.amazon.livingroom.mediapipelinebackend.Constants;
import com.amazon.livingroom.mediapipelinebackend.HdmiAudioPlugBroadcastReceiver;
import com.amazon.livingroom.mediapipelinebackend.PlaybackSurface;
import com.amazon.livingroom.mediapipelinebackend.ReadyToPlayTracker;
import com.amazon.livingroom.mediapipelinebackend.RendererCapabilityChecker;
import com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeController;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.reporting.Log;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import j$.util.Objects;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.builder.ToStringStyle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public class MediaPipelineBackendEngine {
    public static final int AUDIO_RENDERER_INDEX = 0;
    public static final int MAX_DROPPED_FRAMES_TO_NOTIFY = 1;
    public static final int NUMBER_OF_TRACKS = 2;
    public static final int VIDEO_RENDERER_INDEX = 1;
    public AudioCapabilities audioCapabilities;
    public float audioVolume;
    public final Context context;
    public final DestroyInterceptor destroyInterceptor;
    public final DeviceProperties deviceProperties;
    public final ExoDrmSessionManager drmSessionManager;
    public final ErrorManager errorManager;
    public final Handler exoPlayerHandler;
    public int foregroundSessionIdAtInitTime;
    public final Format[] formats;
    public final HdcpChecker hdcpChecker;
    public final HdmiAudioPlugBroadcastReceiver hdmiAudioPlugReceiver;
    public boolean isTtsEnabled;
    public final MediaPipelineListener listener;
    public final LoadControl loadControl;
    public final String logPrefix;
    public AvMediaSource mediaSource;
    public final MinervaMetrics minervaMetrics;
    public MpbLifecycleState mpbLifecycleState;
    public final NativeMediaPipelineBackend nativeMpb;
    public final PlaybackSurface playbackSurface;
    public final PlaybackSurface.Listener playbackSurfaceListener;
    public ExoPlayer player;
    public int playerState;
    public Surface playerSurface;
    public final ReadyToPlayTracker readyToPlayTracker;
    public final RendererCapabilityChecker rendererCapabilityChecker;
    public Renderer[] renderers;
    public final SonyCalibratedModeController.CalibratedModeChangeListener sonyCalibratedModeChangeListener;
    public final SonyCalibratedModeController sonyCalibratedModeController;
    public final DefaultTrackSelector trackSelector;
    public final TextToSpeechStatusProvider ttsStatusProvider;
    public final ApplicationVisibilityMonitor visibilityMonitor;
    public final ConditionVariable playerIdle = new ConditionVariable(true);
    public final ConditionVariable playerNotPreparing = new ConditionVariable(true);
    public final ConditionVariable seekCompleted = new ConditionVariable(true);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AudioRendererEventListener implements androidx.media3.exoplayer.audio.AudioRendererEventListener {
        public final String logPrefix;

        public AudioRendererEventListener(String str) {
            this.logPrefix = str;
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioCodecError(@NonNull Exception exc) {
            MpbLog.e(this.logPrefix + "Audio decoder error", exc);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioDecoderInitialized(@NonNull String str, long j, long j2) {
            MpbLog.i(this.logPrefix + "Audio decoder initialized: decoderName=" + str + " initializedTimestampMs=" + j + " initializationDurationMs=" + j2);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioDecoderReleased(@NonNull String str) {
            MpbLog.i(this.logPrefix + "Audio decoder released: decoderName=" + str);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioDisabled(@NonNull DecoderCounters decoderCounters) {
            MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "Audio disabled");
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioEnabled(@NonNull DecoderCounters decoderCounters) {
            MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "Audio enabled");
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public /* synthetic */ void onAudioInputFormatChanged(Format format) {
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioPositionAdvancing(long j) {
            MpbLog.t(this.logPrefix + "Audio position advancing: playoutStartSystemTimeMs=" + j);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioSinkError(@NonNull Exception exc) {
            MpbLog.e(this.logPrefix + "Audio sink error", exc);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioTrackInitialized(@NonNull AudioSink.AudioTrackConfig audioTrackConfig) {
            MpbLog.i(this.logPrefix + "AudioTrack initialized: encoding=" + audioTrackConfig.encoding + " sampleRate=" + audioTrackConfig.sampleRate + " channelConfig=" + audioTrackConfig.channelConfig + " tunneling=" + audioTrackConfig.tunneling + " offload=" + audioTrackConfig.offload + " bufferSize=" + audioTrackConfig.bufferSize);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioTrackReleased(@NonNull AudioSink.AudioTrackConfig audioTrackConfig) {
            MpbLog.i(this.logPrefix + "AudioTrack released: encoding=" + audioTrackConfig.encoding + " sampleRate=" + audioTrackConfig.sampleRate + " channelConfig=" + audioTrackConfig.channelConfig + " tunneling=" + audioTrackConfig.tunneling + " offload=" + audioTrackConfig.offload + " bufferSize=" + audioTrackConfig.bufferSize);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioUnderrun(int i, long j, long j2) {
            MpbLog.w(this.logPrefix + "Audio underrun: bufferSize=" + i + " bufferSizeMs=" + j + " elapsedSinceLastFeedMs=" + j2);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onSkipSilenceEnabledChanged(boolean z) {
            MpbLog.i(this.logPrefix + "Audio skip silence changed: enabled=" + z);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioInputFormatChanged(@NonNull Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
            if (decoderReuseEvaluation == null) {
                MpbLog.i(this.logPrefix + "Audio input format changed: format=" + format + " (no previous format)");
                return;
            }
            MpbLog.i(this.logPrefix + "Audio input format changed: format=" + format + " oldFormat=" + decoderReuseEvaluation.oldFormat + " decoderName=" + decoderReuseEvaluation.decoderName + " decoderReuseResult=" + decoderReuseEvaluation.result + " discardReasons=" + decoderReuseEvaluation.discardReasons);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DestroyInterceptor {
        boolean allowDestroy(MediaPipelineBackendEngine mediaPipelineBackendEngine);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AssistedFactory
    public interface Factory {
        MediaPipelineBackendEngine create(Handler handler, MediaPipelineListener mediaPipelineListener, DestroyInterceptor destroyInterceptor, PlaybackSurface playbackSurface, int i);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class HdcpInformation {
        public final HdcpVersion currentHdcpVersion;
        public final String currentHdcpVersionStr;
        public final HdcpVersion maxHdcpVersion;
        public final String maxHdcpVersionStr;

        public HdcpInformation() {
            HdcpVersion currentHdcpVersion = MediaPipelineBackendEngine.this.hdcpChecker.getCurrentHdcpVersion(true);
            this.currentHdcpVersion = currentHdcpVersion;
            HdcpVersion maxHdcpVersion = MediaPipelineBackendEngine.this.hdcpChecker.getMaxHdcpVersion();
            this.maxHdcpVersion = maxHdcpVersion;
            this.maxHdcpVersionStr = maxHdcpVersion == null ? AbstractJsonLexerKt.NULL : maxHdcpVersion.getFullVersion();
            this.currentHdcpVersionStr = currentHdcpVersion.getFullVersion();
        }

        public boolean checkForLossOfHDCP() {
            HdcpVersion hdcpVersion = this.maxHdcpVersion;
            return hdcpVersion != null && hdcpVersion.compareTo(this.currentHdcpVersion) > 0;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class InstanceDestroyedException extends IllegalStateException {
        public InstanceDestroyedException(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MPBPropertyConstants {
        public static final String VOLUME_KEY = "audioVolume";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class MediaPeriodListener implements AvMediaPeriod.Listener {
        @Override // com.amazon.livingroom.mediapipelinebackend.AvMediaPeriod.Listener
        public void onSeekCompleted(long j) {
            MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "Seek to " + j + " us completed");
            MediaPipelineBackendEngine.this.seekCompleted.open();
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AvMediaPeriod.Listener
        public void onSeekError(Throwable th) {
            MpbLog.e(MediaPipelineBackendEngine.this.logPrefix + "Seek Failed", th);
            MediaPipelineBackendEngine.this.seekCompleted.open();
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AvMediaPeriod.Listener
        public void onTrackSelectionError(Throwable th) {
            MediaPipelineBackendEngine.this.playerNotPreparing.open();
            if (MediaPipelineBackendEngine.this.suppressBackgroundError(th)) {
                return;
            }
            MediaPipelineBackendEngine.this.errorManager.onError(ErrorCode.ASYNC_TRACK_SELECTION_FAILED, "Track selection failed", th);
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AvMediaPeriod.Listener
        public void onTracksSelected() {
            MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), MediaPipelineBackendEngine.this.logPrefix, "Tracks selected");
            MediaPipelineBackendEngine.this.playerNotPreparing.open();
        }

        public MediaPeriodListener() {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MinervaMetricConstants {
        public static final String MPB_PLAYER_REINIT_UNNECESSARY = "MPBEngine.PlayerReinitializationUnnecessary";
        public static final String MPB_PLAYER_REINIT_WITH_DIFF_CONFIG = "MPBEngine.PlayerReinitializedWithDifferentConfig";
        public static final String MPB_PLAYER_RELEASED = "MPBEngine.PlayerReleased";
        public static final String MPB_SHUTDOWN_TIME = "MPBEngine.ShutdownTime";
        public static final String MPB_STOP_TIME = "MPBEngine.StopTime";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum MpbLifecycleState {
        DESTROYED,
        SHUTDOWN,
        INITIALIZED
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class PlayerListener implements Player.Listener {
        public boolean playWhenReady;
        public int playbackState;

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onCues(CueGroup cueGroup) {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onEvents(@NonNull Player player, Player.Events events) {
            MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.onEvents, number of events = " + events.size());
            for (int i = 0; i < events.size(); i++) {
                int i2 = events.flags.get(i);
                switch (i2) {
                    case 0:
                        MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), MediaPipelineBackendEngine.this.logPrefix, "MPBEngine.Player.Event: Timeline changed.");
                        break;
                    case 1:
                        MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.Player.Event: Media item transition to " + player.getCurrentMediaItem());
                        break;
                    case 2:
                        MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), MediaPipelineBackendEngine.this.logPrefix, "MPBEngine.Player.Event: Tracks changed.");
                        break;
                    case 3:
                    case 6:
                    default:
                        MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.Player.Event: Event occurred with ID " + i2);
                        break;
                    case 4:
                        MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.Player.Event: Playback state changed to " + MediaPipelineBackendEngine.getPlaybackStateName(player.getPlaybackState()));
                        break;
                    case 5:
                        MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.Player.Event: Play when ready changed to " + player.getPlayWhenReady());
                        break;
                    case 7:
                        MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.Player.Event: Is playing changed to " + player.isPlaying());
                        break;
                    case 8:
                        MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.Player.Event: Repeat mode changed to " + player.getRepeatMode());
                        break;
                    case 9:
                        MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.Player.Event: Shuffle mode enabled changed to " + player.getShuffleModeEnabled());
                        break;
                    case 10:
                        MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.Player.Event: Player error: " + player.getPlayerError());
                        break;
                    case 11:
                        MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), MediaPipelineBackendEngine.this.logPrefix, "MPBEngine.Player.Event: Position discontinuity.");
                        break;
                    case 12:
                        MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.Player.Event: Playback parameters changed to " + player.getPlaybackParameters());
                        break;
                }
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayWhenReadyChanged(boolean z, int i) {
            MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine - playWhenReady changed to: " + z + " for reason: " + MediaPipelineBackendEngine.getPlayWhenReadyChangedReasonName(i));
            this.playWhenReady = z;
            onPlayerStateChanged();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int i) {
            MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine - playbackState changed to: " + MediaPipelineBackendEngine.getPlaybackStateName(i));
            this.playbackState = i;
            onPlayerStateChanged();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerError(@NonNull PlaybackException playbackException) throws Throwable {
            MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine - onPlayerError: error code name:" + PlaybackException.getErrorCodeName(playbackException.errorCode) + ", error message=" + playbackException.getMessage());
            MediaPipelineBackendEngine.this.seekCompleted.open();
            MediaPipelineBackendEngine.this.playerNotPreparing.open();
            if (MediaPipelineBackendEngine.this.suppressBackgroundError(playbackException)) {
                return;
            }
            Throwable cause = playbackException.getCause();
            if (cause instanceof MediaCodec.CryptoException) {
                int errorCode = ((MediaCodec.CryptoException) cause).getErrorCode();
                switch (errorCode) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        MediaPipelineBackendEngine.this.errorManager.onError(ErrorCode.ASYNC_CRYPTO_EXCEPTION, ObjectListKt$$ExternalSyntheticOutline1.m("MediaCodec.CryptoException(", errorCode, ")"), playbackException);
                        break;
                    case 4:
                        HdcpInformation hdcpInformation = MediaPipelineBackendEngine.this.new HdcpInformation();
                        ErrorManager errorManager = MediaPipelineBackendEngine.this.errorManager;
                        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("MediaCodec.CryptoException(", errorCode, ") with loss of HDCP (current=");
                        sbM.append(hdcpInformation.currentHdcpVersionStr);
                        sbM.append(", max=");
                        errorManager.onError(ErrorCode.INSUFFICIENT_OUTPUT_PROTECTION, ActivityCompat$$ExternalSyntheticOutline0.m(sbM, hdcpInformation.maxHdcpVersionStr, ")"), playbackException);
                        break;
                    default:
                        HdcpInformation hdcpInformation2 = MediaPipelineBackendEngine.this.new HdcpInformation();
                        if (!hdcpInformation2.checkForLossOfHDCP()) {
                            ErrorManager errorManager2 = MediaPipelineBackendEngine.this.errorManager;
                            StringBuilder sbM2 = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("MediaCodec.CryptoException(", errorCode, ") without loss of HDCP (current=");
                            sbM2.append(hdcpInformation2.currentHdcpVersionStr);
                            sbM2.append(", max=");
                            errorManager2.onError(ErrorCode.ASYNC_CRYPTO_EXCEPTION_UNKNOWN, ActivityCompat$$ExternalSyntheticOutline0.m(sbM2, hdcpInformation2.maxHdcpVersionStr, ")"), playbackException);
                        } else {
                            ErrorManager errorManager3 = MediaPipelineBackendEngine.this.errorManager;
                            StringBuilder sbM3 = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("MediaCodec.CryptoException(", errorCode, ") with loss of HDCP (current=");
                            sbM3.append(hdcpInformation2.currentHdcpVersionStr);
                            sbM3.append(", max=");
                            errorManager3.onError(ErrorCode.INSUFFICIENT_OUTPUT_PROTECTION, ActivityCompat$$ExternalSyntheticOutline0.m(sbM3, hdcpInformation2.maxHdcpVersionStr, ")"), playbackException);
                        }
                        break;
                }
                return;
            }
            if ((cause instanceof AudioSink.WriteException) && MediaPipelineBackendEngine.this.isAudioTrackDeadObject(((AudioSink.WriteException) cause).errorCode)) {
                if (!AudioInfoReceiver.isHDMIAudioDevicePluggedIn(MediaPipelineBackendEngine.this.context)) {
                    MediaPipelineBackendEngine.this.errorManager.onError(ErrorCode.DISPLAY_DISCONNECTED, "AudioTrack.write failed because audio HDMI connection status changed", false, playbackException);
                    return;
                }
                MediaPipelineBackendEngine mediaPipelineBackendEngine = MediaPipelineBackendEngine.this;
                if (!mediaPipelineBackendEngine.getAudioCapabilities(mediaPipelineBackendEngine.isTtsEnabled).equals(MediaPipelineBackendEngine.this.audioCapabilities)) {
                    MediaPipelineBackendEngine.this.errorManager.onError(ErrorCode.ASYNC_AUDIO_CAPS_CHANGED, "AudioTrack.write failed because AudioCapability changed", false, playbackException);
                    return;
                }
            }
            if (!(cause instanceof ExoPlaybackException)) {
                MediaPipelineBackendEngine.this.errorManager.onError(ErrorCode.ASYNC_UNKNOWN_ERROR, "Unknown async error", playbackException);
                return;
            }
            ExoPlaybackException exoPlaybackException = (ExoPlaybackException) cause;
            int i = exoPlaybackException.type;
            if (i == 0) {
                ErrorManager errorManager4 = MediaPipelineBackendEngine.this.errorManager;
                String message = exoPlaybackException.getSourceException().getMessage();
                Objects.requireNonNull(message);
                errorManager4.onError(ErrorCode.ASYNC_SOURCE_ERROR, message, exoPlaybackException);
                return;
            }
            if (i == 1) {
                ErrorManager errorManager5 = MediaPipelineBackendEngine.this.errorManager;
                String message2 = exoPlaybackException.getRendererException().getMessage();
                Objects.requireNonNull(message2);
                errorManager5.onError(ErrorCode.ASYNC_RENDERER_ERROR, message2, exoPlaybackException);
                return;
            }
            if (i == 2) {
                ErrorManager errorManager6 = MediaPipelineBackendEngine.this.errorManager;
                String message3 = exoPlaybackException.getUnexpectedException().getMessage();
                Objects.requireNonNull(message3);
                errorManager6.onError(ErrorCode.ASYNC_UNEXPECTED_ERROR, message3, exoPlaybackException);
                return;
            }
            if (i != 3) {
                ErrorManager errorManager7 = MediaPipelineBackendEngine.this.errorManager;
                String message4 = exoPlaybackException.getMessage();
                Objects.requireNonNull(message4);
                errorManager7.onError(ErrorCode.ASYNC_UNKNOWN_PLAYBACK_ERROR, message4, exoPlaybackException);
                return;
            }
            ErrorManager errorManager8 = MediaPipelineBackendEngine.this.errorManager;
            String message5 = exoPlaybackException.getMessage();
            Objects.requireNonNull(message5);
            errorManager8.onError(ErrorCode.ASYNC_REMOTE_ERROR, message5, exoPlaybackException);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlayerStateChanged(boolean z, int i) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPositionDiscontinuity(int i) {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTimelineChanged(@NonNull Timeline timeline, int i) {
            MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.onTimelineChanged, reason " + i);
        }

        public PlayerListener() {
            this.playbackState = 1;
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onCues(List list) {
        }

        public void onPlayerStateChanged() {
            MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine - actioning player state change with playbackState: " + this.playbackState + " and playWhenReady: " + this.playWhenReady);
            int i = this.playbackState;
            if (i == 1) {
                MediaPipelineBackendEngine.this.playerIdle.open();
            } else if (i == 2) {
                MediaPipelineBackendEngine mediaPipelineBackendEngine = MediaPipelineBackendEngine.this;
                if (mediaPipelineBackendEngine.playerState == 3 && this.playWhenReady) {
                    mediaPipelineBackendEngine.reportBufferUnderrun();
                }
            } else if (i == 3) {
                if (MediaPipelineBackendEngine.this.playerState == 2) {
                    MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), MediaPipelineBackendEngine.this.logPrefix, "MPBEngine - ready to play");
                    MediaPipelineBackendEngine.this.nativeMpb.onReadyToPlay();
                    MediaPipelineBackendEngine.this.readyToPlayTracker.stopWatcher();
                }
                if (this.playWhenReady) {
                    if (MediaPipelineBackendEngine.this.playerState != 3) {
                        MpbLog.e(MediaPipelineBackendEngine.this.logPrefix + "Player entered (STATE_READY, playWhenReady) from state " + MediaPipelineBackendEngine.this.playerState);
                    }
                    MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), MediaPipelineBackendEngine.this.logPrefix, "MPBEngine - playback started");
                    MediaPipelineBackendEngine.this.nativeMpb.onPlaybackStarted();
                }
            } else if (i == 4) {
                if (this.playWhenReady) {
                    MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), MediaPipelineBackendEngine.this.logPrefix, "MPBEngine - stream finished");
                    MediaPipelineBackendEngine.this.nativeMpb.onStreamFinished();
                }
                MediaPipelineBackendEngine.this.readyToPlayTracker.stopWatcher();
            }
            MediaPipelineBackendEngine.this.playerState = this.playbackState;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            MpbLog.t(MediaPipelineBackendEngine.this.logPrefix + "MPBEngine.onPositionDiscontinuity, oldPositionMs = " + positionInfo.positionMs + ",newPosition=" + positionInfo2.positionMs + ", reason=" + i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onRenderedFirstFrame() {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onAudioSessionIdChanged(int i) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onIsLoadingChanged(boolean z) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onIsPlayingChanged(boolean z) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onLoadingChanged(boolean z) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMetadata(Metadata metadata) {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackParametersChanged(@NonNull PlaybackParameters playbackParameters) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRepeatModeChanged(int i) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSeekBackIncrementChanged(long j) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onTracksChanged(Tracks tracks) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onVolumeChanged(float f) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i) {
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class ReadyToPlayTrackerListener implements ReadyToPlayTracker.Listener {
        @Override // com.amazon.livingroom.mediapipelinebackend.ReadyToPlayTracker.Listener
        public void onWaitTrackerEvent() {
            MpbLog.w(MediaPipelineBackendEngine.this.logPrefix + "Exoplayer is taking too long to get ready after it was prepared with new MediaSource\n---Start Debug Info---\n" + MediaPipelineBackendEngine.this.getDebugInfo() + "\n---End Debug Info---");
        }

        public ReadyToPlayTrackerListener() {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class SonyCalibratedModeChangeListener implements SonyCalibratedModeController.CalibratedModeChangeListener {
        @Override // com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeController.CalibratedModeChangeListener
        public void onCalibratedModeChanged(int i) {
            MediaPipelineBackendEngine.this.nativeMpb.onPictureModeChanged(i);
        }

        public SonyCalibratedModeChangeListener() {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class VideoRendererEventListener implements androidx.media3.exoplayer.video.VideoRendererEventListener {
        public final String logPrefix;

        public VideoRendererEventListener(String str) {
            this.logPrefix = str;
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onDroppedFrames(int i, long j) {
            MpbLog.t(this.logPrefix + "Frames dropped: count=" + i + ", elapsedMs=" + j);
            MediaPipelineBackendEngine.this.nativeMpb.onFrameDropped();
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onRenderedFirstFrame(@NonNull Object obj, long j) {
            MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "First video frame rendered");
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoDecoderInitialized(@NonNull String str, long j, long j2) {
            MpbLog.t(this.logPrefix + "Video decoder initialized: decoderName=" + str + ", initializedTimestampMs=" + j + ", initializationDurationMs=" + j2);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoDisabled(DecoderCounters decoderCounters) {
            synchronized (decoderCounters) {
            }
            MpbLog.t(this.logPrefix + "Video disabled: decoderInitCount=" + decoderCounters.decoderInitCount + ", decoderReleaseCount=" + decoderCounters.decoderReleaseCount + ", queuedInputBufferCount=" + decoderCounters.queuedInputBufferCount + ", droppedInputBufferCount=" + decoderCounters.droppedInputBufferCount + ", skippedInputBufferCount=" + decoderCounters.skippedInputBufferCount + ", renderedOutputBufferCount=" + decoderCounters.renderedOutputBufferCount + ", skippedOutputBufferCount=" + decoderCounters.skippedOutputBufferCount + ", droppedOutputBufferCount=" + decoderCounters.droppedBufferCount + ", maxConsecutiveDroppedOutputBufferCount=" + decoderCounters.maxConsecutiveDroppedBufferCount + ", droppedToKeyframeCount=" + decoderCounters.droppedToKeyframeCount + ", totalVideoFrameProcessingOffsetUs=" + decoderCounters.totalVideoFrameProcessingOffsetUs + ", videoFrameProcessingOffsetCount=" + decoderCounters.videoFrameProcessingOffsetCount);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoEnabled(DecoderCounters decoderCounters) {
            synchronized (decoderCounters) {
            }
            MpbLog.t(this.logPrefix + "Video enabled: decoderInitCount=" + decoderCounters.decoderInitCount + ", decoderReleaseCount=" + decoderCounters.decoderReleaseCount + ", queuedInputBufferCount=" + decoderCounters.queuedInputBufferCount + ", droppedInputBufferCount=" + decoderCounters.droppedInputBufferCount + ", skippedInputBufferCount=" + decoderCounters.skippedInputBufferCount + ", renderedOutputBufferCount=" + decoderCounters.renderedOutputBufferCount + ", skippedOutputBufferCount=" + decoderCounters.skippedOutputBufferCount + ", droppedOutputBufferCount=" + decoderCounters.droppedBufferCount + ", maxConsecutiveDroppedOutputBufferCount=" + decoderCounters.maxConsecutiveDroppedBufferCount + ", droppedToKeyframeCount=" + decoderCounters.droppedToKeyframeCount + ", totalVideoFrameProcessingOffsetUs=" + decoderCounters.totalVideoFrameProcessingOffsetUs + ", videoFrameProcessingOffsetCount=" + decoderCounters.videoFrameProcessingOffsetCount);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoInputFormatChanged(Format format) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoSizeChanged(VideoSize videoSize) {
            MpbLog.i(this.logPrefix + "Video size changed: width=" + videoSize.width + ", height=" + videoSize.height + ", unappliedRotationDegrees=" + videoSize.unappliedRotationDegrees + ", pixelWidthHeightRatio=" + videoSize.pixelWidthHeightRatio + ")");
            MediaPipelineBackendEngine.this.playbackSurface.commitPendingAspectRatio();
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoInputFormatChanged(@NonNull Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.logPrefix);
            sb.append("Video input format changed: ");
            sb.append(format);
            sb.append(" decoderReuseEvaluation: ");
            sb.append(decoderReuseEvaluation != null ? decoderReuseEvaluation.result : -1);
            MpbLog.t(sb.toString());
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoCodecError(Exception exc) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoDecoderReleased(String str) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public /* synthetic */ void onVideoFrameProcessingOffset(long j, int i) {
        }
    }

    @AssistedInject
    public MediaPipelineBackendEngine(@ApplicationContext Context context, @Assisted Handler handler, @Assisted MediaPipelineListener mediaPipelineListener, @Assisted DestroyInterceptor destroyInterceptor, @Assisted PlaybackSurface playbackSurface, @Assisted int i, ApplicationVisibilityMonitor applicationVisibilityMonitor, HdcpChecker hdcpChecker, DeviceProperties deviceProperties, MinervaMetrics minervaMetrics, TextToSpeechStatusProvider textToSpeechStatusProvider, SonyCalibratedModeController sonyCalibratedModeController) {
        NativeMediaPipelineBackend nativeMediaPipelineBackend = new NativeMediaPipelineBackend();
        this.nativeMpb = nativeMediaPipelineBackend;
        this.drmSessionManager = new ExoDrmSessionManager();
        this.sonyCalibratedModeChangeListener = new SonyCalibratedModeChangeListener();
        this.loadControl = new AvLoadControl();
        this.formats = new Format[2];
        PlaybackSurface.Listener listener = new PlaybackSurface.Listener() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda10
            @Override // com.amazon.livingroom.mediapipelinebackend.PlaybackSurface.Listener
            public final void onSurfaceChanged(Surface surface) {
                this.f$0.setSurface(surface);
            }
        };
        this.playbackSurfaceListener = listener;
        this.mpbLifecycleState = MpbLifecycleState.SHUTDOWN;
        this.audioVolume = 1.0f;
        this.playerState = 1;
        if (handler.getLooper() != Looper.myLooper()) {
            throw new IllegalStateException("MediaPipelineBackendEngine must be constructed on the thread used to communicate with ExoPlayer");
        }
        this.context = context;
        this.exoPlayerHandler = handler;
        this.listener = mediaPipelineListener;
        this.destroyInterceptor = destroyInterceptor;
        this.playbackSurface = playbackSurface;
        String strM = ObjectListKt$$ExternalSyntheticOutline1.m("[", i, "] ");
        this.logPrefix = strM;
        this.visibilityMonitor = applicationVisibilityMonitor;
        this.hdcpChecker = hdcpChecker;
        this.deviceProperties = deviceProperties;
        this.minervaMetrics = minervaMetrics;
        this.ttsStatusProvider = textToSpeechStatusProvider;
        this.sonyCalibratedModeController = sonyCalibratedModeController;
        this.errorManager = new ErrorManager(nativeMediaPipelineBackend, mediaPipelineListener);
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(context);
        this.trackSelector = defaultTrackSelector;
        this.rendererCapabilityChecker = new RendererCapabilityChecker(minervaMetrics);
        this.readyToPlayTracker = new ReadyToPlayTracker(handler, new ReadyToPlayTrackerListener());
        if (((Boolean) deviceProperties.get(DeviceProperties.TUNNELED_VIDEO_PLAYBACK_ENABLED)).booleanValue()) {
            MpbLog.i(strM + "Tunneling mode is enabled");
            DefaultTrackSelector.Parameters.Builder builderBuildUponParameters = defaultTrackSelector.buildUponParameters();
            builderBuildUponParameters.tunnelingEnabled = true;
            defaultTrackSelector.setParameters(builderBuildUponParameters);
        } else {
            MpbLog.i(strM + "Tunneling mode is disabled");
        }
        this.playerSurface = playbackSurface.setListener(listener);
        this.hdmiAudioPlugReceiver = HdmiAudioPlugBroadcastReceiver.register(context, new HdmiAudioPlugBroadcastReceiver.Listener() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda11
            @Override // com.amazon.livingroom.mediapipelinebackend.HdmiAudioPlugBroadcastReceiver.Listener
            public final void onHdmiAudioPlugEvent(boolean z) {
                this.f$0.onHdmiAudioPlugEvent(z);
            }
        });
    }

    public static String getPlayWhenReadyChangedReasonName(int i) {
        switch (i) {
            case 1:
                return "USER_REQUEST";
            case 2:
                return "AUDIO_FOCUS_LOSS";
            case 3:
                return "AUDIO_BECOMING_NOISY";
            case 4:
                return "REMOTE";
            case 5:
                return "END_OF_MEDIA_ITEM";
            case 6:
                return "SUPPRESSED_TOO_LONG";
            default:
                return ObjectListKt$$ExternalSyntheticOutline1.m("Unknown(", i, ")");
        }
    }

    public static String getPlaybackStateName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? ObjectListKt$$ExternalSyntheticOutline1.m("Unknown(", i, ")") : "STATE_ENDED" : "STATE_READY" : "STATE_BUFFERING" : "STATE_IDLE";
    }

    public final int abortInBackground(String str) {
        int i = this.visibilityMonitor.foregroundSessionId;
        if (i <= 0) {
            return this.errorManager.onError(ErrorCode.INVALID_BACKGROUND_OPERATION, "The app is in the background so it can't " + str);
        }
        if (i == this.foregroundSessionIdAtInitTime) {
            return 0;
        }
        return this.errorManager.onError(ErrorCode.APP_WAS_IN_BACKGROUND, "The app was in the background and MPB must be reinitialized before calling " + str);
    }

    public final <T> T callOnApplicationThread(Callable<T> callable) throws ExecutionException, InterruptedException {
        FutureTask<T> futureTask = new FutureTask<>(callable);
        runOnApplicationThreadAsync((FutureTask) futureTask);
        return futureTask.get();
    }

    public final int checkForUnsupportedFormatLevel(final Renderer renderer, final Format format, final int i) throws ExecutionException, InterruptedException {
        return ((Integer) callOnApplicationThread(new Callable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$checkForUnsupportedFormatLevel$11(renderer, format, i);
            }
        })).intValue();
    }

    @CalledFromNative
    public int clearDrmSystem() {
        this.drmSessionManager.setDrmSystem(null);
        return 0;
    }

    @CalledFromNative
    public int destroy() {
        boolean zBooleanValue;
        try {
            try {
                zBooleanValue = ((Boolean) callOnApplicationThread(new Callable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda0
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return this.f$0.lambda$destroy$3();
                    }
                })).booleanValue();
            } catch (InstanceDestroyedException unused) {
                MpbLog.t(this.logPrefix + "Instance already destroyed, nothing left to destroy");
                zBooleanValue = true;
            }
            if (!zBooleanValue) {
                return 0;
            }
            MpbLog.i(this.logPrefix + "Joining ExoPlayer thread");
            this.exoPlayerHandler.getLooper().getThread().join();
            MpbLog.i(this.logPrefix + "Joined ExoPlayer thread");
            return 0;
        } catch (InterruptedException unused2) {
            Thread.currentThread().interrupt();
            return ErrorCode.MPB_DESTROY_INTERRUPTED;
        } catch (Exception e) {
            MpbLog.e(this.logPrefix + "destroy exception", e);
            return ErrorCode.MPB_DESTROY_EXCEPTION;
        }
    }

    @CalledFromNative
    public int flush() {
        MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "MPBEngine.flush");
        return 0;
    }

    @NonNull
    public final AudioCapabilities getAudioCapabilities(boolean z) {
        return (z || !((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_EAC3_PASSTHROUGH)).booleanValue()) ? AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES : Constants.AUDIO.SURROUND_AUDIO_CAPABILITIES;
    }

    public final String getDebugInfo() {
        if (this.player == null) {
            return "Unable to get debug info -- Player has already been released";
        }
        return "MediaSourceState: " + this.mediaSource.describeState() + "\nExoplayer BufferedPosition=" + this.player.getBufferedPosition() + "\nExoplayer TotalBufferedDuration=" + this.player.getTotalBufferedDuration() + "\nExoPlayer Playback State=" + this.player.getPlaybackState() + "\nExoplayer Playback Position=" + this.player.getCurrentPosition() + "\nDevice Using External Video Output=" + this.deviceProperties.get(DeviceProperties.HAS_EXTERNAL_OUTPUT) + "\nTunneling Mode Enabled=" + this.deviceProperties.get(DeviceProperties.TUNNELED_VIDEO_PLAYBACK_ENABLED) + "\nNetwork Connection Strength=" + this.deviceProperties.get(DeviceProperties.NETWORK_CONNECTION_STRENGTH);
    }

    @CalledFromNative
    public long getPlaybackTime() {
        try {
            return Util.msToUs(((Long) callOnApplicationThread(new Callable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda6
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$getPlaybackTime$12();
                }
            })).longValue());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.errorManager.onError(ErrorCode.MPB_GET_TIME_INTERRUPTED, "Interrupted while getting playback time", e);
            return Long.MIN_VALUE;
        } catch (Exception e2) {
            this.errorManager.onError(ErrorCode.MPB_GET_TIME_EXCEPTION, "Failed to get playback time", e2);
            return Long.MIN_VALUE;
        }
    }

    @CalledFromNative
    public ResultHolder<String> getProperty(String str) {
        return MPBPropertyConstants.VOLUME_KEY.equals(str) ? ResultHolder.fromResult(String.valueOf(this.audioVolume)) : ResultHolder.fromErrorCode(ErrorCode.MPB_KEY_NOT_FOUND);
    }

    @CalledFromNative
    public int init(final long j) {
        MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "MPBEngine.init");
        try {
            return ((Integer) callOnApplicationThread(new Callable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$init$5(j);
                }
            })).intValue();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return ErrorCode.MPB_INIT_INTERRUPTED;
        } catch (Exception e) {
            return this.errorManager.onError(ErrorCode.MPB_INIT_EXCEPTION, "init exception", e);
        }
    }

    public final void initRenderers(Context context, AudioCapabilities audioCapabilities) {
        AvMediaCodecSelectorHandler avMediaCodecSelectorHandler = new AvMediaCodecSelectorHandler(MediaCodecSelector.DEFAULT);
        DefaultAudioSink.Builder builder = new DefaultAudioSink.Builder();
        audioCapabilities.getClass();
        builder.audioCapabilities = audioCapabilities;
        builder.audioTrackBufferSizeProvider = new AvAudioTrackBufferSizeProvider();
        DefaultAudioSink defaultAudioSinkBuild = builder.build();
        DefaultMediaCodecAdapterFactory defaultMediaCodecAdapterFactory = new DefaultMediaCodecAdapterFactory(context);
        if (((Boolean) this.deviceProperties.get(DeviceProperties.FORCE_ENABLE_MEDIA_CODEC_ASYNC_QUEUEING)).booleanValue()) {
            MpbLog.i(this.logPrefix + "Force enabling asynchronous queueing for media codec");
            defaultMediaCodecAdapterFactory.asynchronousMode = 1;
        } else if (((Boolean) this.deviceProperties.get(DeviceProperties.FORCE_DISABLE_MEDIA_CODEC_ASYNC_QUEUEING)).booleanValue()) {
            MpbLog.i(this.logPrefix + "Force disabling asynchronous queueing for media codec");
            defaultMediaCodecAdapterFactory.asynchronousMode = 2;
        }
        MediaCodecAudioRenderer mediaCodecAudioRenderer = new MediaCodecAudioRenderer(context, defaultMediaCodecAdapterFactory, avMediaCodecSelectorHandler, false, this.exoPlayerHandler, new AudioRendererEventListener(this.logPrefix), defaultAudioSinkBuild);
        MediaCodecVideoRenderer mediaCodecVideoRenderer = new MediaCodecVideoRenderer(context, defaultMediaCodecAdapterFactory, avMediaCodecSelectorHandler, 5000L, false, this.exoPlayerHandler, new VideoRendererEventListener(this.logPrefix), 1, 30.0f, null);
        long jLongValue = ((Long) this.deviceProperties.get(DeviceProperties.AUDIO_RENDERER_TIME_LIMIT_MS)).longValue();
        long jLongValue2 = ((Long) this.deviceProperties.get(DeviceProperties.VIDEO_RENDERER_TIME_LIMIT_MS)).longValue();
        MpbLog.i(this.logPrefix + "Renderer time limits: audio=" + jLongValue + "ms video=" + jLongValue2 + "ms");
        mediaCodecAudioRenderer.renderTimeLimitMs = jLongValue;
        mediaCodecVideoRenderer.renderTimeLimitMs = jLongValue2;
        this.renderers = new Renderer[]{mediaCodecAudioRenderer, mediaCodecVideoRenderer};
    }

    public final boolean isAudioTrackDeadObject(int i) {
        return (Build.VERSION.SDK_INT >= 24 && i == -6) || i == -32;
    }

    public final boolean isInitialized() {
        return this.mpbLifecycleState.compareTo(MpbLifecycleState.INITIALIZED) >= 0;
    }

    public final Integer lambda$checkForUnsupportedFormatLevel$11(Renderer renderer, Format format, int i) throws Exception {
        RendererCapabilityChecker.Result resultCheckFormatSupportLevel = this.rendererCapabilityChecker.checkFormatSupportLevel(renderer, format);
        if (resultCheckFormatSupportLevel.isFormatSupported()) {
            return 0;
        }
        this.errorManager.onError(i, "No suitable decoder found for stream with " + format + " support level: " + resultCheckFormatSupportLevel.formatSupportName);
        return Integer.valueOf(i);
    }

    public final /* synthetic */ Boolean lambda$destroy$3() throws Exception {
        MpbLifecycleState mpbLifecycleState = this.mpbLifecycleState;
        MpbLifecycleState mpbLifecycleState2 = MpbLifecycleState.DESTROYED;
        if (mpbLifecycleState == mpbLifecycleState2) {
            return Boolean.TRUE;
        }
        if (!this.destroyInterceptor.allowDestroy(this)) {
            return Boolean.FALSE;
        }
        if (isInitialized()) {
            shutdown();
        }
        if (this.player != null) {
            releasePlayerInternal();
        }
        this.context.unregisterReceiver(this.hdmiAudioPlugReceiver);
        this.playbackSurface.clearListener(this.playbackSurfaceListener);
        this.playbackSurface.close();
        this.mpbLifecycleState = mpbLifecycleState2;
        this.exoPlayerHandler.getLooper().quitSafely();
        return Boolean.TRUE;
    }

    public final /* synthetic */ Long lambda$getPlaybackTime$12() throws Exception {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer == null) {
            return 0L;
        }
        long currentPosition = exoPlayer.getCurrentPosition();
        this.listener.onPlaybackPositionUpdate(currentPosition);
        return Long.valueOf(currentPosition);
    }

    public final /* synthetic */ Renderer[] lambda$init$4(Handler handler, androidx.media3.exoplayer.video.VideoRendererEventListener videoRendererEventListener, androidx.media3.exoplayer.audio.AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
        return this.renderers;
    }

    public final Integer lambda$init$5(long j) throws Exception {
        if (isInitialized()) {
            return Integer.valueOf(ErrorCode.MPB_INIT_ALREADY_INITIALIZED);
        }
        int i = this.visibilityMonitor.foregroundSessionId;
        this.foregroundSessionIdAtInitTime = i;
        if (i <= 0) {
            return Integer.valueOf(ErrorCode.INVALID_BACKGROUND_OPERATION);
        }
        this.nativeMpb.init(j);
        boolean z = this.ttsStatusProvider.getTtsEnabledStatus() == TextToSpeechStatusProvider.TtsEnabledStatus.ENABLED;
        AudioCapabilities audioCapabilities = getAudioCapabilities(z);
        if (this.player != null) {
            if (audioCapabilities.equals(this.audioCapabilities) && z == this.isTtsEnabled) {
                recordMetric(MinervaMetricConstants.MPB_PLAYER_REINIT_UNNECESSARY);
            } else {
                releasePlayerInternal();
                recordMetric(MinervaMetricConstants.MPB_PLAYER_REINIT_WITH_DIFF_CONFIG);
            }
        }
        if (this.player == null) {
            MpbLog.i(this.logPrefix + "Initialising ExoPlayer");
            this.audioCapabilities = audioCapabilities;
            this.isTtsEnabled = z;
            initRenderers(this.context, audioCapabilities);
            Looper looper = this.exoPlayerHandler.getLooper();
            RenderersFactory renderersFactory = new RenderersFactory() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda14
                @Override // androidx.media3.exoplayer.RenderersFactory
                public final Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
                    return this.f$0.renderers;
                }
            };
            ExoPlayer.Builder builder = new ExoPlayer.Builder(this.context);
            builder.setRenderersFactory(renderersFactory);
            builder.setTrackSelector(this.trackSelector);
            builder.setLoadControl(this.loadControl);
            builder.setLooper(looper);
            this.player = builder.build();
            setAudioVolumeOnPlayer(this.audioVolume);
            this.player.addListener(new PlayerListener());
            if (Log.LOGGING_ENABLED) {
                this.player.addAnalyticsListener(new EventLogger());
            }
            setVideoRendererSurface();
            this.sonyCalibratedModeController.addCalibratedModeChangeListener(this.sonyCalibratedModeChangeListener);
        }
        this.playbackSurface.resetViewport();
        this.mpbLifecycleState = MpbLifecycleState.INITIALIZED;
        this.listener.init();
        return 0;
    }

    public final /* synthetic */ void lambda$onHdmiAudioPlugEvent$2() {
        if (isInitialized()) {
            this.errorManager.onError(ErrorCode.DISPLAY_DISCONNECTED, "HDMI audio device disconnected during playback", false);
        }
    }

    public final /* synthetic */ void lambda$pause$8() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
        }
        this.listener.pause();
    }

    public final /* synthetic */ Integer lambda$play$7() throws Exception {
        int iAbortInBackground = abortInBackground("play");
        if (iAbortInBackground != 0) {
            return Integer.valueOf(iAbortInBackground);
        }
        if (1 == this.player.getPlaybackState()) {
            return Integer.valueOf(ErrorCode.MPB_PLAY_NOT_PREPARED);
        }
        if (!this.playerNotPreparing.block(-1L)) {
            return Integer.valueOf(ErrorCode.MPB_PLAY_TRACKS_NOT_CONFIGURED);
        }
        for (Renderer renderer : this.renderers) {
            renderer.setCurrentStreamFinal();
        }
        this.player.setPlayWhenReady(true);
        this.listener.play();
        return 0;
    }

    public final /* synthetic */ void lambda$releasePlayer$1() {
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.MPB_SCHEMA_ID);
        try {
            if (this.player != null) {
                if (isInitialized()) {
                    this.errorManager.onError(ErrorCode.INVALID_BACKGROUND_OPERATION, "ExoPlayer not released before going to background");
                    shutdown();
                }
                releasePlayerInternal();
            }
            MpbLog.i(this.logPrefix + "Player release result: true");
            metricEventCreateMetricEvent.addLong(MinervaMetricConstants.MPB_PLAYER_RELEASED, (long) 1);
            this.minervaMetrics.record(metricEventCreateMetricEvent);
        } catch (Throwable th) {
            MpbLog.i(this.logPrefix + "Player release result: false");
            metricEventCreateMetricEvent.addLong(MinervaMetricConstants.MPB_PLAYER_RELEASED, (long) 0);
            this.minervaMetrics.record(metricEventCreateMetricEvent);
            throw th;
        }
    }

    public final /* synthetic */ Boolean lambda$seek$10(long j) throws Exception {
        if (!this.playerNotPreparing.block(-1L)) {
            MpbLog.w(this.logPrefix + "Player not ready to seek");
            return Boolean.FALSE;
        }
        if (1 == this.player.getPlaybackState()) {
            MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "Not waiting for seek completion (player idle)");
            this.seekCompleted.open();
        } else {
            MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "Waiting for seek completion");
            this.seekCompleted.close();
        }
        this.player.seekTo(j);
        this.listener.seek(j);
        return Boolean.TRUE;
    }

    public final /* synthetic */ void lambda$setAudioVolumeOnPlayerOnApplicationThread$14() {
        setAudioVolumeOnPlayer(this.audioVolume);
    }

    public final /* synthetic */ Boolean lambda$setFormat$13(int i, Format format) throws Exception {
        if (this.formats[i] != null) {
            MpbLog.i(this.logPrefix + "Received new format for track " + i + ": " + format);
            return Boolean.FALSE;
        }
        MpbLog.i(this.logPrefix + "Configuring track " + i + ": " + format);
        this.formats[i] = format;
        return Boolean.valueOf(maybePreparePlayer());
    }

    public final /* synthetic */ void lambda$setSurface$0(Surface surface) {
        this.playerSurface = surface;
        if (this.player != null) {
            setVideoRendererSurface();
        }
    }

    public final /* synthetic */ void lambda$shutdown$6() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer == null) {
            this.playerIdle.open();
        } else {
            exoPlayer.stop();
            this.player.setPlayWhenReady(false);
            this.player.seekTo(0L);
            if (1 == this.player.getPlaybackState()) {
                this.playerIdle.open();
            } else {
                this.playerIdle.close();
            }
        }
        Arrays.fill(this.formats, (Object) null);
        this.playerNotPreparing.open();
        this.drmSessionManager.setDrmSystem(null);
        this.audioVolume = 1.0f;
        this.mpbLifecycleState = MpbLifecycleState.SHUTDOWN;
        this.readyToPlayTracker.stopWatcher();
        this.listener.shutdown();
    }

    public final /* synthetic */ void lambda$stop$9() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
        }
        this.listener.stop();
    }

    public final boolean maybePreparePlayer() {
        for (Format format : this.formats) {
            if (format == null) {
                return false;
            }
        }
        MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "MPBEngine - All tracks configured. Preparing player...");
        this.playerNotPreparing.close();
        NativeMediaPipelineBackend nativeMediaPipelineBackend = this.nativeMpb;
        Format[] formatArr = this.formats;
        MediaPeriodListener mediaPeriodListener = new MediaPeriodListener();
        MinervaMetrics minervaMetrics = this.minervaMetrics;
        DeviceProperties deviceProperties = this.deviceProperties;
        ExoDrmSessionManager exoDrmSessionManager = this.drmSessionManager;
        final PlaybackSurface playbackSurface = this.playbackSurface;
        Objects.requireNonNull(playbackSurface);
        AvMediaSource avMediaSource = new AvMediaSource(nativeMediaPipelineBackend, formatArr, mediaPeriodListener, minervaMetrics, deviceProperties, exoDrmSessionManager, new AvSampleStream.AspectRatioSetter() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda15
            @Override // com.amazon.livingroom.mediapipelinebackend.AvSampleStream.AspectRatioSetter
            public final void setPendingAspectRatio(float f) {
                playbackSurface.setPendingAspectRatio(f);
            }
        });
        this.mediaSource = avMediaSource;
        this.player.setMediaSource((MediaSource) avMediaSource, false);
        this.player.prepare();
        this.readyToPlayTracker.startWatcher();
        return true;
    }

    @CalledFromNative
    public int onAudioMetadata(int i, int i2, int i3) {
        int iAbortInBackground = abortInBackground("onAudioMetadata");
        if (iAbortInBackground != 0) {
            return iAbortInBackground;
        }
        try {
            Format formatCreateAudioFormat = FormatFactory.createAudioFormat(i, i2, i3, this.drmSessionManager.getDrmSchemeId(), this.drmSessionManager.getInitialSessionId());
            int iCheckForUnsupportedFormatLevel = checkForUnsupportedFormatLevel(this.renderers[0], formatCreateAudioFormat, ErrorCode.MPB_AUDIO_METADATA_UNSUPPORTED_FORMAT);
            if (iCheckForUnsupportedFormatLevel != 0) {
                return iCheckForUnsupportedFormatLevel;
            }
            setFormat(0, formatCreateAudioFormat);
            return 0;
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return ErrorCode.MPB_AUDIO_METADATA_INTERRUPTED;
        } catch (Exception e) {
            return this.errorManager.onError(ErrorCode.MPB_AUDIO_METADATA_EXCEPTION, "onAudioMetadata exception", e);
        }
    }

    public final void onHdmiAudioPlugEvent(boolean z) {
        if (z) {
            return;
        }
        try {
            runOnApplicationThreadAsync(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda13
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onHdmiAudioPlugEvent$2();
                }
            });
        } catch (InstanceDestroyedException unused) {
            MpbLog.i(this.logPrefix + "HDMI disconnected after the MPB has been destroyed");
        }
    }

    @CalledFromNative
    public int onVideoMetadata(int i, int i2, int i3, double d) {
        int iAbortInBackground = abortInBackground("onVideoMetadata");
        if (iAbortInBackground != 0) {
            return iAbortInBackground;
        }
        try {
            Format formatCreateVideoFormat = FormatFactory.createVideoFormat(i, i2, i3, (float) d, this.drmSessionManager.getDrmSchemeId(), this.drmSessionManager.getInitialSessionId());
            int iCheckForUnsupportedFormatLevel = checkForUnsupportedFormatLevel(this.renderers[1], formatCreateVideoFormat, ErrorCode.MPB_VIDEO_METADATA_UNSUPPORTED_FORMAT);
            if (iCheckForUnsupportedFormatLevel != 0) {
                return iCheckForUnsupportedFormatLevel;
            }
            this.playbackSurface.setPendingAspectRatio(formatCreateVideoFormat.width / formatCreateVideoFormat.height);
            this.playbackSurface.commitPendingAspectRatio();
            setFormat(1, formatCreateVideoFormat);
            return 0;
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return ErrorCode.MPB_VIDEO_METADATA_INTERRUPTED;
        } catch (Exception e) {
            return this.errorManager.onError(ErrorCode.MPB_VIDEO_METADATA_EXCEPTION, "onVideoMetadata exception", e);
        }
    }

    @CalledFromNative
    public int pause() {
        MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "MPBEngine.pause");
        try {
            runOnApplicationThread(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$pause$8();
                }
            });
            return 0;
        } catch (InstanceDestroyedException unused) {
            MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "Instance already destroyed, nothing left to pause");
            return 0;
        } catch (InterruptedException unused2) {
            Thread.currentThread().interrupt();
            return ErrorCode.MPB_PAUSE_INTERRUPTED;
        } catch (Exception e) {
            return this.errorManager.onError(ErrorCode.MPB_PAUSE_EXCEPTION, "pause exception", e);
        }
    }

    @CalledFromNative
    public int play() {
        MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "MPBEngine.play");
        try {
            return ((Integer) callOnApplicationThread(new Callable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda8
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$play$7();
                }
            })).intValue();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return ErrorCode.MPB_PLAY_INTERRUPTED;
        } catch (Exception e) {
            return this.errorManager.onError(ErrorCode.MPB_PLAY_EXCEPTION, "play exception", e);
        }
    }

    public final void recordMetric(String str) {
        try {
            MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.MPB_SCHEMA_ID);
            metricEventCreateMetricEvent.addLong(str, 1L);
            this.minervaMetrics.record(metricEventCreateMetricEvent);
        } catch (Exception e) {
            MpbLog.e(this.logPrefix + "Failed in recording MPBInit metric", e);
        }
    }

    public void releasePlayer() {
        try {
            runOnApplicationThread(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$releasePlayer$1();
                }
            });
        } catch (InstanceDestroyedException unused) {
            MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "MPB Engine already destroyed, nothing left to release");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while releasing player", e);
        } catch (ExecutionException e2) {
            throw new RuntimeException("Failed to release player", e2);
        }
    }

    public final void releasePlayerInternal() {
        MpbLog.i(this.logPrefix + "Releasing ExoPlayer");
        this.player.release();
        this.player = null;
        this.renderers = null;
    }

    public final void reportBufferUnderrun() {
        MpbLog.t(this.logPrefix + "MPBEngine - buffer underrun");
        if (!isInitialized()) {
            MpbLog.w(this.logPrefix + "Ignoring buffer underrun while MPB is not initialized");
            return;
        }
        int i = this.visibilityMonitor.foregroundSessionId;
        if (i == this.foregroundSessionIdAtInitTime) {
            pause();
            this.listener.onBufferUnderrun();
            this.nativeMpb.onBufferUnderrun();
        } else {
            ErrorManager errorManager = this.errorManager;
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Buffer underrun suppressed fsid=", i, " fsidInit=");
            sbM.append(this.foregroundSessionIdAtInitTime);
            errorManager.onError(ErrorCode.INVALID_BACKGROUND_OPERATION, sbM.toString());
        }
    }

    public final void runOnApplicationThread(Runnable runnable) throws ExecutionException, InterruptedException {
        ((FutureTask) runOnApplicationThreadAsync(runnable)).get();
    }

    public final Future<Void> runOnApplicationThreadAsync(Runnable runnable) {
        FutureTask futureTask = new FutureTask(runnable, null);
        runOnApplicationThreadAsync(futureTask);
        return futureTask;
    }

    @CalledFromNative
    public int seek(final long j) {
        MpbLog.t(this.logPrefix + "MPBEngine.seek(" + j + ")");
        int iAbortInBackground = abortInBackground("seek");
        if (iAbortInBackground != 0) {
            return iAbortInBackground;
        }
        if (j < 0) {
            MpbLog.e(this.logPrefix + "Attempted to seek to negative position: " + j + " ms)");
            return ErrorCode.MPB_SEEK_NEGATIVE_POSITION;
        }
        try {
            Callable callable = new Callable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$seek$10(j);
                }
            };
            if (!((Boolean) callOnApplicationThread(callable)).booleanValue()) {
                this.playerNotPreparing.block();
                if (!((Boolean) callOnApplicationThread(callable)).booleanValue()) {
                    return ErrorCode.MPB_SEEK_STILL_PREPARING;
                }
            }
            this.seekCompleted.block();
            MpbLog.t(this.logPrefix + "MPBEngine.seek - Seek completed");
            return 0;
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return ErrorCode.MPB_SEEK_INTERRUPTED;
        } catch (Exception e) {
            return this.errorManager.onError(ErrorCode.MPB_SEEK_EXCEPTION, "seek exception", e);
        }
    }

    public final void setAudioVolumeOnPlayer(float f) {
        if (this.player != null) {
            MpbLog.t(this.logPrefix + "setAudioVolumeOnPlayer() - volume set to " + f);
            this.player.setVolume(f);
        }
    }

    public final int setAudioVolumeOnPlayerOnApplicationThread() {
        try {
            runOnApplicationThreadAsync(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda16
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setAudioVolumeOnPlayerOnApplicationThread$14();
                }
            });
            return 0;
        } catch (InstanceDestroyedException unused) {
            MpbLog.i(this.logPrefix + "setAudioVolume after the MPB has been destroyed");
            return 0;
        }
    }

    @CalledFromNative
    public int setDrmSystem(DrmSystem drmSystem) {
        this.drmSessionManager.setDrmSystem(drmSystem);
        return 0;
    }

    public final void setFormat(final int i, final Format format) throws ExecutionException, InterruptedException {
        if (((Boolean) callOnApplicationThread(new Callable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda12
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$setFormat$13(i, format);
            }
        })).booleanValue()) {
            this.playerNotPreparing.block();
            MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "MPBEngine - Player finished preparing");
        }
    }

    @CalledFromNative
    public int setProperty(String str, String str2) {
        MpbLog.i(this.logPrefix + "setProperty() name=\"" + str + "\", value=\"" + str2 + ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
        if (!MPBPropertyConstants.VOLUME_KEY.equals(str)) {
            return ErrorCode.MPB_KEY_NOT_FOUND;
        }
        try {
            this.audioVolume = Float.parseFloat(str2);
            setAudioVolumeOnPlayerOnApplicationThread();
            return 0;
        } catch (NumberFormatException unused) {
            return ErrorCode.MPB_VALUE_NOT_SUPPORTED;
        }
    }

    public final void setSurface(@Nullable final Surface surface) {
        MpbLog.t(this.logPrefix + "Setting ExoPlayer surface to " + surface);
        try {
            runOnApplicationThread(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setSurface$0(surface);
                }
            });
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for player surface to be set", e);
        } catch (ExecutionException e2) {
            throw new RuntimeException("Failed to set player surface", e2);
        }
    }

    @CalledFromNative
    public int setVideoOutputPosition(int i, int i2, int i3, int i4) {
        try {
            this.playbackSurface.setViewport(i, i2, i3, i4);
            return 0;
        } catch (Exception e) {
            MpbLog.e(this.logPrefix + "Failed in setting video output position", e);
            return ErrorCode.SET_VIDEO_OUTPUT_POSITION_FAILED;
        }
    }

    public final void setVideoRendererSurface() {
        try {
            PlayerMessage playerMessageCreateMessage = this.player.createMessage(this.renderers[1]);
            playerMessageCreateMessage.setType(1);
            playerMessageCreateMessage.setPayload(this.playerSurface);
            playerMessageCreateMessage.send();
            playerMessageCreateMessage.blockUntilDelivered();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for player surface to be set", e);
        }
    }

    @CalledFromNative
    public int shutdown() {
        MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "MPBEngine.shutdown");
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.MPB_SCHEMA_ID);
        long jCurrentTimeMillis = System.currentTimeMillis();
        int iOnError = 0;
        try {
            try {
                try {
                    runOnApplicationThread(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda17
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$shutdown$6();
                        }
                    });
                    this.playerIdle.block();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    this.nativeMpb.close();
                    metricEventCreateMetricEvent.addLong(MinervaMetricConstants.MPB_SHUTDOWN_TIME, System.currentTimeMillis() - jCurrentTimeMillis);
                    this.minervaMetrics.record(metricEventCreateMetricEvent);
                    return ErrorCode.MPB_SHUTDOWN_INTERRUPTED;
                }
            } catch (InstanceDestroyedException unused2) {
                MpbLog.t(this.logPrefix + "Instance already destroyed, nothing left to shut down");
            } catch (Exception e) {
                iOnError = this.errorManager.onError(ErrorCode.MPB_SHUTDOWN_EXCEPTION, "shutdown exception", e);
            }
            return iOnError;
        } finally {
            this.nativeMpb.close();
            metricEventCreateMetricEvent.addLong(MinervaMetricConstants.MPB_SHUTDOWN_TIME, System.currentTimeMillis() - jCurrentTimeMillis);
            this.minervaMetrics.record(metricEventCreateMetricEvent);
        }
    }

    @CalledFromNative
    public int stop() {
        MediaPipelineBackendEngine$AudioRendererEventListener$$ExternalSyntheticOutline0.m(new StringBuilder(), this.logPrefix, "MPBEngine.stop");
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.MPB_SCHEMA_ID);
        long jCurrentTimeMillis = System.currentTimeMillis();
        int iOnError = 0;
        try {
            try {
                try {
                    try {
                        runOnApplicationThread(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine$$ExternalSyntheticLambda4
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.lambda$stop$9();
                            }
                        });
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        metricEventCreateMetricEvent.addLong(MinervaMetricConstants.MPB_STOP_TIME, System.currentTimeMillis() - jCurrentTimeMillis);
                        this.minervaMetrics.record(metricEventCreateMetricEvent);
                        return ErrorCode.MPB_STOP_INTERRUPTED;
                    }
                } catch (InstanceDestroyedException unused2) {
                    MpbLog.t(this.logPrefix + "Instance already destroyed, nothing left to stop");
                }
            } catch (Exception e) {
                iOnError = this.errorManager.onError(ErrorCode.MPB_STOP_EXCEPTION, "stop exception", e);
            }
            return iOnError;
        } finally {
            metricEventCreateMetricEvent.addLong(MinervaMetricConstants.MPB_STOP_TIME, System.currentTimeMillis() - jCurrentTimeMillis);
            this.minervaMetrics.record(metricEventCreateMetricEvent);
        }
    }

    public final boolean suppressBackgroundError(Throwable th) {
        if (!isInitialized()) {
            MpbLog.w(this.logPrefix + "Ignoring error while MPB is not initialized", th);
            return true;
        }
        int i = this.visibilityMonitor.foregroundSessionId;
        if (i == this.foregroundSessionIdAtInitTime) {
            return false;
        }
        ErrorManager errorManager = this.errorManager;
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Background error suppressed fsid=", i, " fsidInit=");
        sbM.append(this.foregroundSessionIdAtInitTime);
        errorManager.onError(ErrorCode.INVALID_BACKGROUND_OPERATION, sbM.toString(), th);
        return true;
    }

    public final <T> Future<T> runOnApplicationThreadAsync(FutureTask<T> futureTask) {
        if (this.exoPlayerHandler.getLooper() == Looper.myLooper()) {
            futureTask.run();
            return futureTask;
        }
        if (this.exoPlayerHandler.post(futureTask)) {
            return futureTask;
        }
        throw new InstanceDestroyedException("MPB instance has already been destroyed");
    }
}
