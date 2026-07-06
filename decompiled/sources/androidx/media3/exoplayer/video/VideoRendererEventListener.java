package androidx.media3.exoplayer.video;

import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface VideoRendererEventListener {

    /* JADX INFO: renamed from: androidx.media3.exoplayer.video.VideoRendererEventListener$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @Deprecated
        public static void $default$onVideoInputFormatChanged(VideoRendererEventListener videoRendererEventListener, Format format) {
        }

        public static void $default$onVideoInputFormatChanged(VideoRendererEventListener videoRendererEventListener, Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
        }

        public static void $default$onVideoCodecError(VideoRendererEventListener videoRendererEventListener, Exception exc) {
        }

        public static void $default$onVideoDecoderReleased(VideoRendererEventListener videoRendererEventListener, String str) {
        }

        public static void $default$onVideoDisabled(VideoRendererEventListener videoRendererEventListener, DecoderCounters decoderCounters) {
        }

        public static void $default$onVideoEnabled(VideoRendererEventListener videoRendererEventListener, DecoderCounters decoderCounters) {
        }

        public static void $default$onVideoSizeChanged(VideoRendererEventListener videoRendererEventListener, VideoSize videoSize) {
        }

        public static void $default$onDroppedFrames(VideoRendererEventListener videoRendererEventListener, int i, long j) {
        }

        public static void $default$onRenderedFirstFrame(VideoRendererEventListener videoRendererEventListener, Object obj, long j) {
        }

        public static void $default$onVideoFrameProcessingOffset(VideoRendererEventListener videoRendererEventListener, long j, int i) {
        }

        public static void $default$onVideoDecoderInitialized(VideoRendererEventListener videoRendererEventListener, String str, long j, long j2) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EventDispatcher {

        @Nullable
        public final Handler handler;

        @Nullable
        public final VideoRendererEventListener listener;

        public static /* synthetic */ void $r8$lambda$3d5qzAJzOG_kuEfp2g3VIBP3hOw(EventDispatcher eventDispatcher, String str, long j, long j2) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoDecoderInitialized(str, j, j2);
        }

        public static /* synthetic */ void $r8$lambda$BS3v4lfsbIsC1pS1t4MOpgJmItM(EventDispatcher eventDispatcher, Exception exc) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoCodecError(exc);
        }

        /* JADX INFO: renamed from: $r8$lambda$FATKILHOILtblmax_24wXXpjB-o, reason: not valid java name */
        public static /* synthetic */ void m172$r8$lambda$FATKILHOILtblmax_24wXXpjBo(EventDispatcher eventDispatcher, int i, long j) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onDroppedFrames(i, j);
        }

        public static void $r8$lambda$R8pPANH_yLr6RjVhX7e7MTZhKvw(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
            eventDispatcher.getClass();
            synchronized (decoderCounters) {
            }
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoDisabled(decoderCounters);
        }

        public static /* synthetic */ void $r8$lambda$RCDE6TO4DjhzG9D1RvBEW0QdJ1Q(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoEnabled(decoderCounters);
        }

        public static /* synthetic */ void $r8$lambda$hYpsisbOw8XuChb4SVjo3uhAwzQ(EventDispatcher eventDispatcher, VideoSize videoSize) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoSizeChanged(videoSize);
        }

        /* JADX INFO: renamed from: $r8$lambda$kuS-aucIvnEpIGflCbxQMWvwx9A, reason: not valid java name */
        public static /* synthetic */ void m173$r8$lambda$kuSaucIvnEpIGflCbxQMWvwx9A(EventDispatcher eventDispatcher, long j, int i) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoFrameProcessingOffset(j, i);
        }

        public static void $r8$lambda$mox6ykqhlpdIdUZdrU6RYbR_khI(EventDispatcher eventDispatcher, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.getClass();
            eventDispatcher.listener.onVideoInputFormatChanged(format, decoderReuseEvaluation);
        }

        /* JADX INFO: renamed from: $r8$lambda$pJR4DhexRjdqGlem2_e-y6dQsHw, reason: not valid java name */
        public static /* synthetic */ void m174$r8$lambda$pJR4DhexRjdqGlem2_ey6dQsHw(EventDispatcher eventDispatcher, String str) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoDecoderReleased(str);
        }

        public static /* synthetic */ void $r8$lambda$uPa6eK25oSO4e0vXNWIOHayyzMU(EventDispatcher eventDispatcher, Object obj, long j) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onRenderedFirstFrame(obj, j);
        }

        public EventDispatcher(@Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener) {
            if (videoRendererEventListener != null) {
                handler.getClass();
            } else {
                handler = null;
            }
            this.handler = handler;
            this.listener = videoRendererEventListener;
        }

        public void decoderInitialized(final String str, final long j, final long j2) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$3d5qzAJzOG_kuEfp2g3VIBP3hOw(this.f$0, str, j, j2);
                    }
                });
            }
        }

        public void decoderReleased(final String str) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.m174$r8$lambda$pJR4DhexRjdqGlem2_ey6dQsHw(this.f$0, str);
                    }
                });
            }
        }

        public void disabled(final DecoderCounters decoderCounters) {
            synchronized (decoderCounters) {
            }
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$R8pPANH_yLr6RjVhX7e7MTZhKvw(this.f$0, decoderCounters);
                    }
                });
            }
        }

        public void droppedFrames(final int i, final long j) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.m172$r8$lambda$FATKILHOILtblmax_24wXXpjBo(this.f$0, i, j);
                    }
                });
            }
        }

        public void enabled(final DecoderCounters decoderCounters) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$RCDE6TO4DjhzG9D1RvBEW0QdJ1Q(this.f$0, decoderCounters);
                    }
                });
            }
        }

        public void inputFormatChanged(final Format format, @Nullable final DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$mox6ykqhlpdIdUZdrU6RYbR_khI(this.f$0, format, decoderReuseEvaluation);
                    }
                });
            }
        }

        public void renderedFirstFrame(final Object obj) {
            if (this.handler != null) {
                final long jElapsedRealtime = SystemClock.elapsedRealtime();
                this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$uPa6eK25oSO4e0vXNWIOHayyzMU(this.f$0, obj, jElapsedRealtime);
                    }
                });
            }
        }

        public void reportVideoFrameProcessingOffset(final long j, final int i) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.m173$r8$lambda$kuSaucIvnEpIGflCbxQMWvwx9A(this.f$0, j, i);
                    }
                });
            }
        }

        public void videoCodecError(final Exception exc) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$BS3v4lfsbIsC1pS1t4MOpgJmItM(this.f$0, exc);
                    }
                });
            }
        }

        public void videoSizeChanged(final VideoSize videoSize) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$hYpsisbOw8XuChb4SVjo3uhAwzQ(this.f$0, videoSize);
                    }
                });
            }
        }
    }

    void onDroppedFrames(int i, long j);

    void onRenderedFirstFrame(Object obj, long j);

    void onVideoCodecError(Exception exc);

    void onVideoDecoderInitialized(String str, long j, long j2);

    void onVideoDecoderReleased(String str);

    void onVideoDisabled(DecoderCounters decoderCounters);

    void onVideoEnabled(DecoderCounters decoderCounters);

    void onVideoFrameProcessingOffset(long j, int i);

    @Deprecated
    void onVideoInputFormatChanged(Format format);

    void onVideoInputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation);

    void onVideoSizeChanged(VideoSize videoSize);
}
