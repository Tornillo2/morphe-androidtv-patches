package com.amazon.livingroom.mediapipelinebackend;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.media3.exoplayer.AudioFocusManager$$ExternalSyntheticApiModelOutline0;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class AudioFocusManager extends MediaPipelineListener implements AudioManager.OnAudioFocusChangeListener {

    @NotNull
    public final AudioManager audioManager;

    @NotNull
    public final Callback callback;
    public boolean focusRequested;
    public boolean hasExternalSurface;

    @NotNull
    public final Impl impl;
    public boolean playing;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Callback {
        void onAudioFocusGain();

        void onAudioFocusTransientLoss();

        void onAudioFocusTransientLossCanDuck();

        void onStop();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Impl {
        void abandonAudioFocus();

        int requestAudioFocus();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class LegacyImpl implements Impl {
        public LegacyImpl() {
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AudioFocusManager.Impl
        public void abandonAudioFocus() {
            AudioFocusManager audioFocusManager = AudioFocusManager.this;
            audioFocusManager.audioManager.abandonAudioFocus(audioFocusManager);
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AudioFocusManager.Impl
        public int requestAudioFocus() {
            AudioFocusManager audioFocusManager = AudioFocusManager.this;
            return audioFocusManager.audioManager.requestAudioFocus(audioFocusManager, 3, 1);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(api = 26)
    public final class V26Impl implements Impl {

        @Nullable
        public AudioFocusRequest audioFocusRequest;

        public V26Impl() {
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AudioFocusManager.Impl
        public void abandonAudioFocus() {
            AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
            if (audioFocusRequest == null) {
                return;
            }
            AudioManager audioManager = AudioFocusManager.this.audioManager;
            Intrinsics.checkNotNull(audioFocusRequest);
            audioManager.abandonAudioFocusRequest(audioFocusRequest);
            this.audioFocusRequest = null;
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AudioFocusManager.Impl
        public int requestAudioFocus() {
            AudioFocusRequest audioFocusRequestBuild = AudioFocusManager$$ExternalSyntheticApiModelOutline0.m(1).setOnAudioFocusChangeListener(AudioFocusManager.this).setAudioAttributes(new AudioAttributes.Builder().setContentType(3).setUsage(1).build()).setWillPauseWhenDucked(true).build();
            this.audioFocusRequest = audioFocusRequestBuild;
            AudioManager audioManager = AudioFocusManager.this.audioManager;
            Intrinsics.checkNotNull(audioFocusRequestBuild);
            return audioManager.requestAudioFocus(audioFocusRequestBuild);
        }
    }

    @Inject
    public AudioFocusManager(@NotNull AudioManager audioManager, @NotNull Callback callback) {
        Intrinsics.checkNotNullParameter(audioManager, "audioManager");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.audioManager = audioManager;
        this.callback = callback;
        this.impl = Build.VERSION.SDK_INT >= 26 ? new V26Impl() : new LegacyImpl();
    }

    public final void abandonAudioFocus() {
        MpbLog.i("Abandoning audio focus");
        this.impl.abandonAudioFocus();
        this.focusRequested = false;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public synchronized void onAudioFocusChange(int i) {
        try {
            if (i == -3) {
                MpbLog.i("onAudioFocusChange(AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)");
                onAudioFocusTransientLossCanDuck();
            } else if (i == -2) {
                MpbLog.i("onAudioFocusChange(AUDIOFOCUS_LOSS_TRANSIENT)");
                onAudioFocusTransientLoss();
            } else if (i == -1) {
                MpbLog.i("onAudioFocusChange(AUDIOFOCUS_LOSS)");
                onAudioFocusLoss();
            } else if (i == 1) {
                MpbLog.i("onAudioFocusChange(AUDIOFOCUS_GAIN)");
                onAudioFocusGained();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void onAudioFocusGained() {
        this.callback.onAudioFocusGain();
    }

    public final void onAudioFocusLoss() {
        this.callback.onStop();
    }

    public final void onAudioFocusTransientLoss() {
        this.callback.onAudioFocusTransientLoss();
    }

    public final void onAudioFocusTransientLossCanDuck() {
        this.callback.onAudioFocusTransientLossCanDuck();
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public synchronized void onSurfaceSet(boolean z) {
        this.hasExternalSurface = z;
        updateAudioFocus();
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public synchronized void play() {
        this.playing = true;
        updateAudioFocus();
    }

    public final void requestAudioFocus() {
        MpbLog.i("Requesting audio focus");
        int iRequestAudioFocus = this.impl.requestAudioFocus();
        if (iRequestAudioFocus != 1) {
            MpbLog.w("Failed to get audio focus, but playing anyway: " + iRequestAudioFocus);
        }
        this.focusRequested = true;
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public synchronized void shutdown() {
        this.playing = false;
        updateAudioFocus();
    }

    public final void updateAudioFocus() {
        boolean z = this.playing && !this.hasExternalSurface;
        if (z == this.focusRequested) {
            return;
        }
        if (z) {
            requestAudioFocus();
        } else {
            abandonAudioFocus();
        }
    }
}
