package com.amazon.livingroom.di;

import android.content.Context;
import android.media.AudioManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class CoreModule_Companion_ProvideAudioManagerFactory implements Factory<AudioManager> {
    public final Provider<Context> contextProvider;

    public CoreModule_Companion_ProvideAudioManagerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static CoreModule_Companion_ProvideAudioManagerFactory create(Provider<Context> provider) {
        return new CoreModule_Companion_ProvideAudioManagerFactory(provider);
    }

    public static AudioManager provideAudioManager(Context context) {
        return CoreModule.Companion.provideAudioManager(context);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AudioManager get() {
        return CoreModule.Companion.provideAudioManager(this.contextProvider.get());
    }
}
