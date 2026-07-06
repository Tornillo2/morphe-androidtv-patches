package com.amazon.livingroom.mediapipelinebackend;

import android.content.Intent;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import com.amazon.ignitionshared.TextToSpeechStatusProvider;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.mediapipelinebackend.AudioFocusManager;
import com.amazon.livingroom.voice.VoiceService;
import com.amazon.livingroom.voice.models.VoiceModelFactory;
import com.amazon.reporting.Log;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class MediaSessionCallback extends MediaSessionCompat.Callback implements AudioFocusManager.Callback {
    public static final String LOG_TAG = "PV-MediaSessionCallback";
    public final MediaEventHandler mediaEventHandler;
    public final TextToSpeechStatusProvider textToSpeechStatusProvider;
    public final VoiceService voiceService;

    @Inject
    public MediaSessionCallback(MediaEventHandler mediaEventHandler, VoiceService voiceService, TextToSpeechStatusProvider textToSpeechStatusProvider) {
        this.mediaEventHandler = mediaEventHandler;
        this.voiceService = voiceService;
        this.textToSpeechStatusProvider = textToSpeechStatusProvider;
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.AudioFocusManager.Callback
    public void onAudioFocusGain() {
        Log.i(LOG_TAG, "MediaSessionCallback.onAudioFocusGain()");
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.AudioFocusManager.Callback
    public synchronized void onAudioFocusTransientLoss() {
        try {
            Log.i(LOG_TAG, "MediaSessionCallback.onAudioFocusTransientLoss()");
            if (this.voiceService.isVoiceEnabled()) {
                this.voiceService.sendVoiceCommand(VoiceModelFactory.createPauseCommand());
            } else {
                this.mediaEventHandler.pause();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.AudioFocusManager.Callback
    public synchronized void onAudioFocusTransientLossCanDuck() {
        try {
            Log.i(LOG_TAG, "MediaSessionCallback.onAudioFocusTransientLossCanDuck()");
            if (this.textToSpeechStatusProvider.getTtsEnabledStatus() == TextToSpeechStatusProvider.TtsEnabledStatus.ENABLED) {
                Log.i(LOG_TAG, "MediaSessionCallback.onAudioFocusTransientLossCanDuck() not pausing playback since accessibility speech is active; allow to speak over playback");
            } else if (this.voiceService.isVoiceEnabled()) {
                this.voiceService.sendVoiceCommand(VoiceModelFactory.createPauseCommand());
            } else {
                this.mediaEventHandler.pause();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onFastForward() {
        Log.i(LOG_TAG, "MediaSessionCallback.onFastForward()");
        this.voiceService.sendVoiceCommand(VoiceModelFactory.createFastForwardCommand());
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public boolean onMediaButtonEvent(Intent intent) {
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent == null || !(keyEvent.getKeyCode() == 90 || keyEvent.getKeyCode() == 89)) {
            return super.onMediaButtonEvent(intent);
        }
        return true;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public synchronized void onPause() {
        try {
            Log.i(LOG_TAG, "MediaSessionCallback.onPause()");
            if (this.voiceService.isVoiceEnabled()) {
                this.voiceService.sendVoiceCommand(VoiceModelFactory.createPauseCommand());
            } else {
                this.mediaEventHandler.pause();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public synchronized void onPlay() {
        try {
            Log.i(LOG_TAG, "MediaSessionCallback.onPlay()");
            if (this.voiceService.isVoiceEnabled()) {
                this.voiceService.sendVoiceCommand(VoiceModelFactory.createPlayCommand());
            } else {
                this.mediaEventHandler.play();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onRewind() {
        Log.i(LOG_TAG, "MediaSessionCallback.onRewind()");
        this.voiceService.sendVoiceCommand(VoiceModelFactory.createRewindCommand());
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onSeekTo(long j) {
        Log.i(LOG_TAG, String.format("MediaSessionCallback.onSeekTo(%s)", Long.valueOf(j)));
        this.voiceService.sendVoiceCommand(VoiceModelFactory.createSeekCommand(j));
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onSetCaptioningEnabled(boolean z) {
        Log.i(LOG_TAG, String.format("MediaSessionCallback.onSetCaptioningEnabled(%s)", Boolean.valueOf(z)));
        if (z) {
            this.voiceService.sendVoiceCommand(VoiceModelFactory.createEnableCaptionsCommand());
        } else {
            this.voiceService.sendVoiceCommand(VoiceModelFactory.createDisableCaptionsCommand());
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onSkipToNext() {
        Log.i(LOG_TAG, "MediaSessionCallback.onSkipToNext()");
        this.voiceService.sendVoiceCommand(VoiceModelFactory.createSkipToNextCommand());
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback, com.amazon.livingroom.mediapipelinebackend.AudioFocusManager.Callback
    public synchronized void onStop() {
        try {
            Log.i(LOG_TAG, "MediaSessionCallback.onStop()");
            if (this.voiceService.isVoiceEnabled()) {
                this.voiceService.sendVoiceCommand(VoiceModelFactory.createStopCommand());
            } else {
                this.mediaEventHandler.stop();
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
