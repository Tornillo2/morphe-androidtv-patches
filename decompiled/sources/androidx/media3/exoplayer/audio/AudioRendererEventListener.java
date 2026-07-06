package androidx.media3.exoplayer.audio;

import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface AudioRendererEventListener {

    /* JADX INFO: renamed from: androidx.media3.exoplayer.audio.AudioRendererEventListener$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @Deprecated
        public static void $default$onAudioInputFormatChanged(AudioRendererEventListener audioRendererEventListener, Format format) {
        }

        public static void $default$onAudioInputFormatChanged(AudioRendererEventListener audioRendererEventListener, Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
        }

        public static void $default$onAudioCodecError(AudioRendererEventListener audioRendererEventListener, Exception exc) {
        }

        public static void $default$onAudioDecoderReleased(AudioRendererEventListener audioRendererEventListener, String str) {
        }

        public static void $default$onAudioDisabled(AudioRendererEventListener audioRendererEventListener, DecoderCounters decoderCounters) {
        }

        public static void $default$onAudioEnabled(AudioRendererEventListener audioRendererEventListener, DecoderCounters decoderCounters) {
        }

        public static void $default$onAudioPositionAdvancing(AudioRendererEventListener audioRendererEventListener, long j) {
        }

        public static void $default$onAudioSinkError(AudioRendererEventListener audioRendererEventListener, Exception exc) {
        }

        public static void $default$onAudioTrackInitialized(AudioRendererEventListener audioRendererEventListener, AudioSink.AudioTrackConfig audioTrackConfig) {
        }

        public static void $default$onAudioTrackReleased(AudioRendererEventListener audioRendererEventListener, AudioSink.AudioTrackConfig audioTrackConfig) {
        }

        public static void $default$onSkipSilenceEnabledChanged(AudioRendererEventListener audioRendererEventListener, boolean z) {
        }

        public static void $default$onAudioDecoderInitialized(AudioRendererEventListener audioRendererEventListener, String str, long j, long j2) {
        }

        public static void $default$onAudioUnderrun(AudioRendererEventListener audioRendererEventListener, int i, long j, long j2) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EventDispatcher {

        @Nullable
        public final Handler handler;

        @Nullable
        public final AudioRendererEventListener listener;

        public static /* synthetic */ void $r8$lambda$1y6wHjNFTGl6rJjbHS56PamKz4s(EventDispatcher eventDispatcher, boolean z) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onSkipSilenceEnabledChanged(z);
        }

        public static /* synthetic */ void $r8$lambda$6qDnRdoO6AL4FkibZr5XHuoR5lE(EventDispatcher eventDispatcher, int i, long j, long j2) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioUnderrun(i, j, j2);
        }

        public static /* synthetic */ void $r8$lambda$7vTqHG6vg8Qa60IT2j4aNjeGBds(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioEnabled(decoderCounters);
        }

        public static void $r8$lambda$FlmGccfkha0ag05dECiqBJAuk88(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
            eventDispatcher.getClass();
            synchronized (decoderCounters) {
            }
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioDisabled(decoderCounters);
        }

        /* JADX INFO: renamed from: $r8$lambda$MT2KYrt9IIbSc275Q-EkQouA98k, reason: not valid java name */
        public static /* synthetic */ void m133$r8$lambda$MT2KYrt9IIbSc275QEkQouA98k(EventDispatcher eventDispatcher, long j) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioPositionAdvancing(j);
        }

        /* JADX INFO: renamed from: $r8$lambda$PWzZVgP_qcYYwHT9-ex389Si8ck, reason: not valid java name */
        public static /* synthetic */ void m134$r8$lambda$PWzZVgP_qcYYwHT9ex389Si8ck(EventDispatcher eventDispatcher, AudioSink.AudioTrackConfig audioTrackConfig) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioTrackReleased(audioTrackConfig);
        }

        /* JADX INFO: renamed from: $r8$lambda$SMcLPJsvmTPj121naC_ZP9mxC-8, reason: not valid java name */
        public static /* synthetic */ void m135$r8$lambda$SMcLPJsvmTPj121naC_ZP9mxC8(EventDispatcher eventDispatcher, Exception exc) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioCodecError(exc);
        }

        public static void $r8$lambda$Z5f042bJXV5vEZXCrKisqN6sD6A(EventDispatcher eventDispatcher, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.getClass();
            eventDispatcher.listener.onAudioInputFormatChanged(format, decoderReuseEvaluation);
        }

        public static /* synthetic */ void $r8$lambda$a9Pub2SxFdfykbxF5Tzjc2Dq5Qo(EventDispatcher eventDispatcher, AudioSink.AudioTrackConfig audioTrackConfig) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioTrackInitialized(audioTrackConfig);
        }

        public static /* synthetic */ void $r8$lambda$fEcWRdluRpf6Sc1acacbn3LSqxQ(EventDispatcher eventDispatcher, Exception exc) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioSinkError(exc);
        }

        /* JADX INFO: renamed from: $r8$lambda$iU84reV-pQ--7pc5bCRMOvKwD0o, reason: not valid java name */
        public static /* synthetic */ void m136$r8$lambda$iU84reVpQ7pc5bCRMOvKwD0o(EventDispatcher eventDispatcher, String str, long j, long j2) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioDecoderInitialized(str, j, j2);
        }

        public static /* synthetic */ void $r8$lambda$xEOGYixhjKwpE_zNETDBA3ZKZvY(EventDispatcher eventDispatcher, String str) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioDecoderReleased(str);
        }

        public EventDispatcher(@Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener) {
            if (audioRendererEventListener != null) {
                handler.getClass();
            } else {
                handler = null;
            }
            this.handler = handler;
            this.listener = audioRendererEventListener;
        }

        public void audioCodecError(final Exception exc) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.m135$r8$lambda$SMcLPJsvmTPj121naC_ZP9mxC8(this.f$0, exc);
                    }
                });
            }
        }

        public void audioSinkError(final Exception exc) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$fEcWRdluRpf6Sc1acacbn3LSqxQ(this.f$0, exc);
                    }
                });
            }
        }

        public void audioTrackInitialized(final AudioSink.AudioTrackConfig audioTrackConfig) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$a9Pub2SxFdfykbxF5Tzjc2Dq5Qo(this.f$0, audioTrackConfig);
                    }
                });
            }
        }

        public void audioTrackReleased(final AudioSink.AudioTrackConfig audioTrackConfig) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.m134$r8$lambda$PWzZVgP_qcYYwHT9ex389Si8ck(this.f$0, audioTrackConfig);
                    }
                });
            }
        }

        public void decoderInitialized(final String str, final long j, final long j2) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.m136$r8$lambda$iU84reVpQ7pc5bCRMOvKwD0o(this.f$0, str, j, j2);
                    }
                });
            }
        }

        public void decoderReleased(final String str) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$xEOGYixhjKwpE_zNETDBA3ZKZvY(this.f$0, str);
                    }
                });
            }
        }

        public void disabled(final DecoderCounters decoderCounters) {
            synchronized (decoderCounters) {
            }
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda10
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$FlmGccfkha0ag05dECiqBJAuk88(this.f$0, decoderCounters);
                    }
                });
            }
        }

        public void enabled(final DecoderCounters decoderCounters) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$7vTqHG6vg8Qa60IT2j4aNjeGBds(this.f$0, decoderCounters);
                    }
                });
            }
        }

        public void inputFormatChanged(final Format format, @Nullable final DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$Z5f042bJXV5vEZXCrKisqN6sD6A(this.f$0, format, decoderReuseEvaluation);
                    }
                });
            }
        }

        public void positionAdvancing(final long j) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda11
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.m133$r8$lambda$MT2KYrt9IIbSc275QEkQouA98k(this.f$0, j);
                    }
                });
            }
        }

        public void skipSilenceEnabledChanged(final boolean z) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$1y6wHjNFTGl6rJjbHS56PamKz4s(this.f$0, z);
                    }
                });
            }
        }

        public void underrun(final int i, final long j, final long j2) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$6qDnRdoO6AL4FkibZr5XHuoR5lE(this.f$0, i, j, j2);
                    }
                });
            }
        }
    }

    void onAudioCodecError(Exception exc);

    void onAudioDecoderInitialized(String str, long j, long j2);

    void onAudioDecoderReleased(String str);

    void onAudioDisabled(DecoderCounters decoderCounters);

    void onAudioEnabled(DecoderCounters decoderCounters);

    @Deprecated
    void onAudioInputFormatChanged(Format format);

    void onAudioInputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation);

    void onAudioPositionAdvancing(long j);

    void onAudioSinkError(Exception exc);

    void onAudioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig);

    void onAudioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig);

    void onAudioUnderrun(int i, long j, long j2);

    void onSkipSilenceEnabledChanged(boolean z);
}
