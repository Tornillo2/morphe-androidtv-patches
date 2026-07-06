package androidx.media3.exoplayer;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface Renderer extends PlayerMessage.Target {
    public static final int MSG_CUSTOM_BASE = 10000;
    public static final int MSG_SET_AUDIO_ATTRIBUTES = 3;
    public static final int MSG_SET_AUDIO_SESSION_ID = 10;
    public static final int MSG_SET_AUX_EFFECT_INFO = 6;
    public static final int MSG_SET_CAMERA_MOTION_LISTENER = 8;
    public static final int MSG_SET_CHANGE_FRAME_RATE_STRATEGY = 5;
    public static final int MSG_SET_IMAGE_OUTPUT = 15;
    public static final int MSG_SET_PREFERRED_AUDIO_DEVICE = 12;
    public static final int MSG_SET_SCALING_MODE = 4;
    public static final int MSG_SET_SKIP_SILENCE_ENABLED = 9;
    public static final int MSG_SET_VIDEO_EFFECTS = 13;
    public static final int MSG_SET_VIDEO_FRAME_METADATA_LISTENER = 7;
    public static final int MSG_SET_VIDEO_OUTPUT = 1;
    public static final int MSG_SET_VIDEO_OUTPUT_RESOLUTION = 14;
    public static final int MSG_SET_VOLUME = 2;
    public static final int MSG_SET_WAKEUP_LISTENER = 11;
    public static final int STATE_DISABLED = 0;
    public static final int STATE_ENABLED = 1;
    public static final int STATE_STARTED = 2;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessageType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface WakeupListener {
        void onSleep();

        void onWakeup();
    }

    void disable();

    void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j, boolean z, boolean z2, long j2, long j3, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException;

    void enableMayRenderStartOfStream();

    RendererCapabilities getCapabilities();

    @Nullable
    MediaClock getMediaClock();

    String getName();

    long getReadingPositionUs();

    int getState();

    @Nullable
    SampleStream getStream();

    int getTrackType();

    boolean hasReadStreamToEnd();

    void init(int i, PlayerId playerId, Clock clock);

    boolean isCurrentStreamFinal();

    boolean isEnded();

    boolean isReady();

    void maybeThrowStreamError() throws IOException;

    void release();

    void render(long j, long j2) throws ExoPlaybackException;

    void replaceStream(Format[] formatArr, SampleStream sampleStream, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException;

    void reset();

    void resetPosition(long j) throws ExoPlaybackException;

    void setCurrentStreamFinal();

    void setPlaybackSpeed(float f, float f2) throws ExoPlaybackException;

    void setTimeline(Timeline timeline);

    void start() throws ExoPlaybackException;

    void stop();

    /* JADX INFO: renamed from: androidx.media3.exoplayer.Renderer$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static void $default$enableMayRenderStartOfStream(Renderer renderer) {
        }

        public static void $default$release(Renderer renderer) {
        }

        public static void $default$setPlaybackSpeed(Renderer renderer, float f, float f2) throws ExoPlaybackException {
        }
    }
}
