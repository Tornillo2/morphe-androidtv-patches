package androidx.media3.exoplayer.video;

import android.view.Surface;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.video.VideoSink;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface VideoSinkProvider {
    void clearOutputSurfaceInfo();

    VideoSink getSink();

    VideoFrameReleaseControl getVideoFrameReleaseControl();

    void initialize(Format format) throws VideoSink.VideoSinkException;

    boolean isInitialized();

    void release();

    void setClock(Clock clock);

    void setOutputSurfaceInfo(Surface surface, Size size);

    void setPendingVideoEffects(List<Effect> list);

    void setStreamOffsetUs(long j);

    void setVideoEffects(List<Effect> list);

    void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    void setVideoFrameReleaseControl(VideoFrameReleaseControl videoFrameReleaseControl);
}
