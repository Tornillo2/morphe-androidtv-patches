package com.amazon.livingroom.mediapipelinebackend;

import android.media.AudioManager;
import com.amazon.livingroom.mediapipelinebackend.AudioFocusManager;
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
public final class AudioFocusManager_Factory implements Factory<AudioFocusManager> {
    public final Provider<AudioManager> audioManagerProvider;
    public final Provider<AudioFocusManager.Callback> callbackProvider;

    public AudioFocusManager_Factory(Provider<AudioManager> provider, Provider<AudioFocusManager.Callback> provider2) {
        this.audioManagerProvider = provider;
        this.callbackProvider = provider2;
    }

    public static AudioFocusManager_Factory create(Provider<AudioManager> provider, Provider<AudioFocusManager.Callback> provider2) {
        return new AudioFocusManager_Factory(provider, provider2);
    }

    public static AudioFocusManager newInstance(AudioManager audioManager, AudioFocusManager.Callback callback) {
        return new AudioFocusManager(audioManager, callback);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AudioFocusManager get() {
        return new AudioFocusManager(this.audioManagerProvider.get(), this.callbackProvider.get());
    }
}
