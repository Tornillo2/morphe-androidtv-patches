package com.google.android.exoplayer2.audio;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AudioCapabilitiesReceiver {

    @Nullable
    public AudioCapabilities audioCapabilities;
    public final Context context;

    @Nullable
    public final ExternalSurroundSoundSettingObserver externalSurroundSoundSettingObserver;
    public final Handler handler;
    public final Listener listener;

    @Nullable
    public final BroadcastReceiver receiver;
    public boolean registered;
    public final ContentResolver resolver;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ExternalSurroundSoundSettingObserver extends ContentObserver {
        public final ContentResolver resolver;
        public final Uri settingUri;

        public ExternalSurroundSoundSettingObserver(Handler handler, ContentResolver contentResolver, Uri uri) {
            super(handler);
            this.resolver = contentResolver;
            this.settingUri = uri;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            AudioCapabilitiesReceiver audioCapabilitiesReceiver = AudioCapabilitiesReceiver.this;
            audioCapabilitiesReceiver.onNewAudioCapabilities(AudioCapabilities.getCapabilities(audioCapabilitiesReceiver.context));
        }

        public void register() {
            this.resolver.registerContentObserver(this.settingUri, false, this);
        }

        public void unregister() {
            this.resolver.unregisterContentObserver(this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class HdmiAudioPlugBroadcastReceiver extends BroadcastReceiver {
        public HdmiAudioPlugBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (isInitialStickyBroadcast()) {
                return;
            }
            AudioCapabilitiesReceiver.this.onNewAudioCapabilities(AudioCapabilities.getCapabilities(context, intent));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities);
    }

    public AudioCapabilitiesReceiver(Context context, Listener listener) {
        boolean zUseSurroundSoundFlagV17;
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        listener.getClass();
        this.listener = listener;
        Handler handlerCreateHandlerForCurrentOrMainLooper = Util.createHandlerForCurrentOrMainLooper();
        this.handler = handlerCreateHandlerForCurrentOrMainLooper;
        Uri externalSurroundSoundGlobalSettingUri = AudioCapabilities.getExternalSurroundSoundGlobalSettingUri();
        HdmiAudioPlugBroadcastReceiver hdmiAudioPlugBroadcastReceiver = null;
        this.externalSurroundSoundSettingObserver = externalSurroundSoundGlobalSettingUri != null ? new ExternalSurroundSoundSettingObserver(handlerCreateHandlerForCurrentOrMainLooper, applicationContext.getContentResolver(), externalSurroundSoundGlobalSettingUri) : null;
        int i = Util.SDK_INT;
        if (i >= 17) {
            ContentResolver contentResolver = applicationContext.getContentResolver();
            this.resolver = contentResolver;
            zUseSurroundSoundFlagV17 = AudioCapabilities.useSurroundSoundFlagV17(contentResolver);
        } else {
            this.resolver = null;
            zUseSurroundSoundFlagV17 = false;
        }
        if (i >= 21 && !zUseSurroundSoundFlagV17) {
            hdmiAudioPlugBroadcastReceiver = new HdmiAudioPlugBroadcastReceiver();
        }
        this.receiver = hdmiAudioPlugBroadcastReceiver;
    }

    public final void onNewAudioCapabilities(AudioCapabilities audioCapabilities) {
        if (!this.registered || audioCapabilities.equals(this.audioCapabilities)) {
            return;
        }
        this.audioCapabilities = audioCapabilities;
        this.listener.onAudioCapabilitiesChanged(audioCapabilities);
    }

    public AudioCapabilities register() {
        if (this.registered) {
            AudioCapabilities audioCapabilities = this.audioCapabilities;
            audioCapabilities.getClass();
            return audioCapabilities;
        }
        this.registered = true;
        ExternalSurroundSoundSettingObserver externalSurroundSoundSettingObserver = this.externalSurroundSoundSettingObserver;
        if (externalSurroundSoundSettingObserver != null) {
            externalSurroundSoundSettingObserver.register();
        }
        Intent intentRegisterReceiver = null;
        if (this.receiver != null) {
            intentRegisterReceiver = this.context.registerReceiver(this.receiver, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"), null, this.handler);
        }
        AudioCapabilities capabilities = AudioCapabilities.getCapabilities(this.context, intentRegisterReceiver);
        this.audioCapabilities = capabilities;
        return capabilities;
    }

    public void unregister() {
        if (this.registered) {
            this.audioCapabilities = null;
            BroadcastReceiver broadcastReceiver = this.receiver;
            if (broadcastReceiver != null) {
                this.context.unregisterReceiver(broadcastReceiver);
            }
            ExternalSurroundSoundSettingObserver externalSurroundSoundSettingObserver = this.externalSurroundSoundSettingObserver;
            if (externalSurroundSoundSettingObserver != null) {
                externalSurroundSoundSettingObserver.unregister();
            }
            this.registered = false;
        }
    }
}
