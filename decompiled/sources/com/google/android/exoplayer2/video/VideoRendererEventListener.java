package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface VideoRendererEventListener {

    /* JADX INFO: renamed from: com.google.android.exoplayer2.video.VideoRendererEventListener$-CC, reason: invalid class name */
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

        public static /* synthetic */ void $r8$lambda$2FfdQMqbprC4S8IKXVP_bf9YjOc(EventDispatcher eventDispatcher, int i, long j) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onDroppedFrames(i, j);
        }

        public static /* synthetic */ void $r8$lambda$96568J0Fdcvbx0u8UpfuRBlXxqw(EventDispatcher eventDispatcher, Exception exc) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoCodecError(exc);
        }

        /* JADX INFO: renamed from: $r8$lambda$GUWnd-ebIy4HaOIE-X11lP8lrL8, reason: not valid java name */
        public static void m493$r8$lambda$GUWndebIy4HaOIEX11lP8lrL8(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
            eventDispatcher.getClass();
            synchronized (decoderCounters) {
            }
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoDisabled(decoderCounters);
        }

        public static /* synthetic */ void $r8$lambda$eNBP1zdXEFl1l_okXilSz2egaec(EventDispatcher eventDispatcher, Object obj, long j) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onRenderedFirstFrame(obj, j);
        }

        public static /* synthetic */ void $r8$lambda$ey80JGySjREGWqWWiHFsj0AUeUc(EventDispatcher eventDispatcher, long j, int i) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoFrameProcessingOffset(j, i);
        }

        public static /* synthetic */ void $r8$lambda$g8ihfYJz7jb1_c7xUDI6fuNzzqU(EventDispatcher eventDispatcher, VideoSize videoSize) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoSizeChanged(videoSize);
        }

        /* JADX INFO: renamed from: $r8$lambda$mWch6chQ0XWq-k2Ys6i218_B3dw, reason: not valid java name */
        public static /* synthetic */ void m494$r8$lambda$mWch6chQ0XWqk2Ys6i218_B3dw(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoEnabled(decoderCounters);
        }

        public static /* synthetic */ void $r8$lambda$maSFbUiM_DjEL8xhk_lq8M35yTw(EventDispatcher eventDispatcher, String str, long j, long j2) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoDecoderInitialized(str, j, j2);
        }

        /* JADX INFO: renamed from: $r8$lambda$mjmrnjQOitBpvLIn3h-arazn5io, reason: not valid java name */
        public static void m495$r8$lambda$mjmrnjQOitBpvLIn3harazn5io(EventDispatcher eventDispatcher, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.getClass();
            eventDispatcher.listener.onVideoInputFormatChanged(format, decoderReuseEvaluation);
        }

        /* JADX INFO: renamed from: $r8$lambda$uAcre0T14-a6ckV-agz8TtOcE1I, reason: not valid java name */
        public static /* synthetic */ void m496$r8$lambda$uAcre0T14a6ckVagz8TtOcE1I(EventDispatcher eventDispatcher, String str) {
            VideoRendererEventListener videoRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(videoRendererEventListener);
            videoRendererEventListener.onVideoDecoderReleased(str);
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
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$maSFbUiM_DjEL8xhk_lq8M35yTw(this.f$0, str, j, j2);
                    }
                });
            }
        }

        public void decoderReleased(final String str) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.m496$r8$lambda$uAcre0T14a6ckVagz8TtOcE1I(this.f$0, str);
                    }
                });
            }
        }

        public void disabled(final DecoderCounters decoderCounters) {
            synchronized (decoderCounters) {
            }
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.m493$r8$lambda$GUWndebIy4HaOIEX11lP8lrL8(this.f$0, decoderCounters);
                    }
                });
            }
        }

        public void droppedFrames(final int i, final long j) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$2FfdQMqbprC4S8IKXVP_bf9YjOc(this.f$0, i, j);
                    }
                });
            }
        }

        public void enabled(final DecoderCounters decoderCounters) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.m494$r8$lambda$mWch6chQ0XWqk2Ys6i218_B3dw(this.f$0, decoderCounters);
                    }
                });
            }
        }

        public void inputFormatChanged(final Format format, @Nullable final DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.m495$r8$lambda$mjmrnjQOitBpvLIn3harazn5io(this.f$0, format, decoderReuseEvaluation);
                    }
                });
            }
        }

        public void renderedFirstFrame(final Object obj) {
            if (this.handler != null) {
                final long jElapsedRealtime = SystemClock.elapsedRealtime();
                this.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$eNBP1zdXEFl1l_okXilSz2egaec(this.f$0, obj, jElapsedRealtime);
                    }
                });
            }
        }

        public void reportVideoFrameProcessingOffset(final long j, final int i) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$ey80JGySjREGWqWWiHFsj0AUeUc(this.f$0, j, i);
                    }
                });
            }
        }

        public void videoCodecError(final Exception exc) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$96568J0Fdcvbx0u8UpfuRBlXxqw(this.f$0, exc);
                    }
                });
            }
        }

        public void videoSizeChanged(final VideoSize videoSize) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.video.VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoRendererEventListener.EventDispatcher.$r8$lambda$g8ihfYJz7jb1_c7xUDI6fuNzzqU(this.f$0, videoSize);
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
