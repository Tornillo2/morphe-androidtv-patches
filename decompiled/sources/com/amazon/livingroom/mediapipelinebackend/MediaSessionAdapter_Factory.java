package com.amazon.livingroom.mediapipelinebackend;

import android.support.v4.media.session.MediaSessionCompat;
import com.amazon.ignitionshared.IgnitionContextProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class MediaSessionAdapter_Factory implements Factory<MediaSessionAdapter> {
    public final Provider<MediaSessionCompat.Callback> callbackProvider;
    public final Provider<IgnitionContextProvider> ignitionContextProvider;
    public final Provider<String> programNameProvider;

    public MediaSessionAdapter_Factory(Provider<IgnitionContextProvider> provider, Provider<MediaSessionCompat.Callback> provider2, Provider<String> provider3) {
        this.ignitionContextProvider = provider;
        this.callbackProvider = provider2;
        this.programNameProvider = provider3;
    }

    public static MediaSessionAdapter_Factory create(Provider<IgnitionContextProvider> provider, Provider<MediaSessionCompat.Callback> provider2, Provider<String> provider3) {
        return new MediaSessionAdapter_Factory(provider, provider2, provider3);
    }

    public static MediaSessionAdapter newInstance(IgnitionContextProvider ignitionContextProvider, MediaSessionCompat.Callback callback, String str) {
        return new MediaSessionAdapter(ignitionContextProvider, callback, str);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public MediaSessionAdapter get() {
        return new MediaSessionAdapter(this.ignitionContextProvider.get(), this.callbackProvider.get(), this.programNameProvider.get());
    }
}
