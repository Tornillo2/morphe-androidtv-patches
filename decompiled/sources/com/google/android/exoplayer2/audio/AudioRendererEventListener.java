package com.google.android.exoplayer2.audio;

import android.os.Handler;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface AudioRendererEventListener {

    /* JADX INFO: renamed from: com.google.android.exoplayer2.audio.AudioRendererEventListener$-CC, reason: invalid class name */
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

        /* JADX INFO: renamed from: $r8$lambda$4Ri-0gqhdQVJ3lQ2Ocyt3Pmpp4A, reason: not valid java name */
        public static /* synthetic */ void m445$r8$lambda$4Ri0gqhdQVJ3lQ2Ocyt3Pmpp4A(EventDispatcher eventDispatcher, long j) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioPositionAdvancing(j);
        }

        public static /* synthetic */ void $r8$lambda$5GoYWzXfYpUC9vHghQjDuHIo4bM(EventDispatcher eventDispatcher, Exception exc) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioCodecError(exc);
        }

        /* JADX INFO: renamed from: $r8$lambda$C_IXs2xVSpNSapn-OxnXBNpAoQo, reason: not valid java name */
        public static /* synthetic */ void m446$r8$lambda$C_IXs2xVSpNSapnOxnXBNpAoQo(EventDispatcher eventDispatcher, String str) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioDecoderReleased(str);
        }

        public static void $r8$lambda$Ml12m7aKwFQecAHx4WobiAEc1_8(EventDispatcher eventDispatcher, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.getClass();
            eventDispatcher.listener.onAudioInputFormatChanged(format, decoderReuseEvaluation);
        }

        /* JADX INFO: renamed from: $r8$lambda$XjfwAAn8hrASmHXL5Icje6-cVUs, reason: not valid java name */
        public static /* synthetic */ void m447$r8$lambda$XjfwAAn8hrASmHXL5Icje6cVUs(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioEnabled(decoderCounters);
        }

        public static /* synthetic */ void $r8$lambda$ZVG2FEQYmtzODM2duSKl615TteM(EventDispatcher eventDispatcher, int i, long j, long j2) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioUnderrun(i, j, j2);
        }

        /* JADX INFO: renamed from: $r8$lambda$lYHj--795KGpio8X7BMCvps_CEs, reason: not valid java name */
        public static /* synthetic */ void m448$r8$lambda$lYHj795KGpio8X7BMCvps_CEs(EventDispatcher eventDispatcher, boolean z) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onSkipSilenceEnabledChanged(z);
        }

        public static /* synthetic */ void $r8$lambda$qWCl95Oe8SCktJS_VnAMC8cWwRg(EventDispatcher eventDispatcher, Exception exc) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioSinkError(exc);
        }

        public static void $r8$lambda$qtaRMcrM4clcai95ZOShApGAoWs(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
            eventDispatcher.getClass();
            synchronized (decoderCounters) {
            }
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioDisabled(decoderCounters);
        }

        public static /* synthetic */ void $r8$lambda$rQzlWyF_WZ9R1GpIVNX_iFZhlPE(EventDispatcher eventDispatcher, String str, long j, long j2) {
            AudioRendererEventListener audioRendererEventListener = eventDispatcher.listener;
            Util.castNonNull(audioRendererEventListener);
            audioRendererEventListener.onAudioDecoderInitialized(str, j, j2);
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
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$5GoYWzXfYpUC9vHghQjDuHIo4bM(this.f$0, exc);
                    }
                });
            }
        }

        public void audioSinkError(final Exception exc) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$qWCl95Oe8SCktJS_VnAMC8cWwRg(this.f$0, exc);
                    }
                });
            }
        }

        public void decoderInitialized(final String str, final long j, final long j2) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$rQzlWyF_WZ9R1GpIVNX_iFZhlPE(this.f$0, str, j, j2);
                    }
                });
            }
        }

        public void decoderReleased(final String str) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.m446$r8$lambda$C_IXs2xVSpNSapnOxnXBNpAoQo(this.f$0, str);
                    }
                });
            }
        }

        public void disabled(final DecoderCounters decoderCounters) {
            synchronized (decoderCounters) {
            }
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$qtaRMcrM4clcai95ZOShApGAoWs(this.f$0, decoderCounters);
                    }
                });
            }
        }

        public void enabled(final DecoderCounters decoderCounters) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.m447$r8$lambda$XjfwAAn8hrASmHXL5Icje6cVUs(this.f$0, decoderCounters);
                    }
                });
            }
        }

        public void inputFormatChanged(final Format format, @Nullable final DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$Ml12m7aKwFQecAHx4WobiAEc1_8(this.f$0, format, decoderReuseEvaluation);
                    }
                });
            }
        }

        public void positionAdvancing(final long j) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.m445$r8$lambda$4Ri0gqhdQVJ3lQ2Ocyt3Pmpp4A(this.f$0, j);
                    }
                });
            }
        }

        public void skipSilenceEnabledChanged(final boolean z) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.m448$r8$lambda$lYHj795KGpio8X7BMCvps_CEs(this.f$0, z);
                    }
                });
            }
        }

        public void underrun(final int i, final long j, final long j2) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.$r8$lambda$ZVG2FEQYmtzODM2duSKl615TteM(this.f$0, i, j, j2);
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

    void onAudioUnderrun(int i, long j, long j2);

    void onSkipSilenceEnabledChanged(boolean z);
}
