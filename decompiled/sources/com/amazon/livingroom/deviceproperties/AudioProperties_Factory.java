package com.amazon.livingroom.deviceproperties;

import android.content.Context;
import android.media.AudioManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class AudioProperties_Factory implements Factory<AudioProperties> {
    public final Provider<AudioManager> audioManagerProvider;
    public final Provider<Context> contextProvider;

    public AudioProperties_Factory(Provider<Context> provider, Provider<AudioManager> provider2) {
        this.contextProvider = provider;
        this.audioManagerProvider = provider2;
    }

    public static AudioProperties_Factory create(Provider<Context> provider, Provider<AudioManager> provider2) {
        return new AudioProperties_Factory(provider, provider2);
    }

    public static AudioProperties newInstance(Context context, AudioManager audioManager) {
        return new AudioProperties(context, audioManager);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AudioProperties get() {
        return new AudioProperties(this.contextProvider.get(), this.audioManagerProvider.get());
    }
}
