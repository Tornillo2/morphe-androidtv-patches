package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.ignitionshared.TextToSpeechStatusProvider;
import com.amazon.livingroom.voice.VoiceService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class MediaSessionCallback_Factory implements Factory<MediaSessionCallback> {
    public final Provider<MediaEventHandler> mediaEventHandlerProvider;
    public final Provider<TextToSpeechStatusProvider> textToSpeechStatusProvider;
    public final Provider<VoiceService> voiceServiceProvider;

    public MediaSessionCallback_Factory(Provider<MediaEventHandler> provider, Provider<VoiceService> provider2, Provider<TextToSpeechStatusProvider> provider3) {
        this.mediaEventHandlerProvider = provider;
        this.voiceServiceProvider = provider2;
        this.textToSpeechStatusProvider = provider3;
    }

    public static MediaSessionCallback_Factory create(Provider<MediaEventHandler> provider, Provider<VoiceService> provider2, Provider<TextToSpeechStatusProvider> provider3) {
        return new MediaSessionCallback_Factory(provider, provider2, provider3);
    }

    public static MediaSessionCallback newInstance(MediaEventHandler mediaEventHandler, VoiceService voiceService, TextToSpeechStatusProvider textToSpeechStatusProvider) {
        return new MediaSessionCallback(mediaEventHandler, voiceService, textToSpeechStatusProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public MediaSessionCallback get() {
        return new MediaSessionCallback(this.mediaEventHandlerProvider.get(), this.voiceServiceProvider.get(), this.textToSpeechStatusProvider.get());
    }
}
