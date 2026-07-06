package androidx.media3.exoplayer.video;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.video.VideoFrameReleaseControl;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class VideoFrameRenderControl {
    public final FrameRenderer frameRenderer;
    public long outputStreamOffsetUs;

    @Nullable
    public VideoSize pendingOutputVideoSize;
    public final VideoFrameReleaseControl videoFrameReleaseControl;
    public final VideoFrameReleaseControl.FrameReleaseInfo videoFrameReleaseInfo = new VideoFrameReleaseControl.FrameReleaseInfo();
    public final TimedValueQueue<VideoSize> videoSizeChanges = new TimedValueQueue<>();
    public final TimedValueQueue<Long> streamOffsets = new TimedValueQueue<>();
    public final LongArrayQueue presentationTimestampsUs = new LongArrayQueue();
    public VideoSize reportedVideoSize = VideoSize.UNKNOWN;
    public long lastPresentationTimeUs = -9223372036854775807L;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface FrameRenderer {
        void dropFrame();

        void onVideoSizeChanged(VideoSize videoSize);

        void renderFrame(long j, long j2, long j3, boolean z);
    }

    public VideoFrameRenderControl(FrameRenderer frameRenderer, VideoFrameReleaseControl videoFrameReleaseControl) {
        this.frameRenderer = frameRenderer;
        this.videoFrameReleaseControl = videoFrameReleaseControl;
    }

    public static <T> T getLastAndClear(TimedValueQueue<T> timedValueQueue) {
        Assertions.checkArgument(timedValueQueue.size() > 0);
        while (timedValueQueue.size() > 1) {
            timedValueQueue.pollFirst();
        }
        T tPollFirst = timedValueQueue.pollFirst();
        tPollFirst.getClass();
        return tPollFirst;
    }

    public final void dropFrame() {
        this.presentationTimestampsUs.remove();
        this.frameRenderer.dropFrame();
    }

    public void flush() {
        this.presentationTimestampsUs.clear();
        this.lastPresentationTimeUs = -9223372036854775807L;
        if (this.streamOffsets.size() > 0) {
            this.streamOffsets.add(0L, (Long) getLastAndClear(this.streamOffsets));
        }
        if (this.pendingOutputVideoSize != null) {
            this.videoSizeChanges.clear();
        } else if (this.videoSizeChanges.size() > 0) {
            this.pendingOutputVideoSize = (VideoSize) getLastAndClear(this.videoSizeChanges);
        }
    }

    public boolean hasReleasedFrame(long j) {
        long j2 = this.lastPresentationTimeUs;
        return j2 != -9223372036854775807L && j2 >= j;
    }

    public boolean isReady() {
        return this.videoFrameReleaseControl.isReady(true);
    }

    public final boolean maybeUpdateOutputStreamOffset(long j) {
        Long lPollFloor = this.streamOffsets.pollFloor(j);
        if (lPollFloor == null || lPollFloor.longValue() == this.outputStreamOffsetUs) {
            return false;
        }
        this.outputStreamOffsetUs = lPollFloor.longValue();
        return true;
    }

    public final boolean maybeUpdateVideoSize(long j) {
        VideoSize videoSizePollFloor = this.videoSizeChanges.pollFloor(j);
        if (videoSizePollFloor == null || videoSizePollFloor.equals(VideoSize.UNKNOWN) || videoSizePollFloor.equals(this.reportedVideoSize)) {
            return false;
        }
        this.reportedVideoSize = videoSizePollFloor;
        return true;
    }

    public void onOutputFrameAvailableForRendering(long j) {
        VideoSize videoSize = this.pendingOutputVideoSize;
        if (videoSize != null) {
            this.videoSizeChanges.add(j, videoSize);
            this.pendingOutputVideoSize = null;
        }
        this.presentationTimestampsUs.add(j);
    }

    public void onOutputSizeChanged(int i, int i2) {
        VideoSize videoSize = new VideoSize(i, i2);
        if (Util.areEqual(this.pendingOutputVideoSize, videoSize)) {
            return;
        }
        this.pendingOutputVideoSize = videoSize;
    }

    public void onStreamOffsetChange(long j, long j2) {
        this.streamOffsets.add(j, Long.valueOf(j2));
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        while (!this.presentationTimestampsUs.isEmpty()) {
            long jElement = this.presentationTimestampsUs.element();
            if (maybeUpdateOutputStreamOffset(jElement)) {
                this.videoFrameReleaseControl.lowerFirstFrameState(2);
            }
            int frameReleaseAction = this.videoFrameReleaseControl.getFrameReleaseAction(jElement, j, j2, this.outputStreamOffsetUs, false, this.videoFrameReleaseInfo);
            if (frameReleaseAction == 0 || frameReleaseAction == 1) {
                this.lastPresentationTimeUs = jElement;
                renderFrame(frameReleaseAction == 0);
            } else if (frameReleaseAction != 2 && frameReleaseAction != 3 && frameReleaseAction != 4) {
                if (frameReleaseAction != 5) {
                    throw new IllegalStateException(String.valueOf(frameReleaseAction));
                }
                return;
            } else {
                this.lastPresentationTimeUs = jElement;
                dropFrame();
            }
        }
    }

    public final void renderFrame(boolean z) {
        long jRemove = this.presentationTimestampsUs.remove();
        if (maybeUpdateVideoSize(jRemove)) {
            this.frameRenderer.onVideoSizeChanged(this.reportedVideoSize);
        }
        this.frameRenderer.renderFrame(z ? -1L : this.videoFrameReleaseInfo.getReleaseTimeNs(), jRemove, this.outputStreamOffsetUs, this.videoFrameReleaseControl.onFrameReleasedIsFirstFrame());
    }

    public void setPlaybackSpeed(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        Assertions.checkArgument(f > 0.0f);
        this.videoFrameReleaseControl.setPlaybackSpeed(f);
    }
}
