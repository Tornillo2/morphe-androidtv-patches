package com.amazon.livingroom.di;

import android.content.Context;
import androidx.tvprovider.media.tv.PreviewChannelHelper;
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
public final class WatchNextModule_ProvidePreviewChannelHelperFactory implements Factory<PreviewChannelHelper> {
    public final Provider<Context> contextProvider;

    public WatchNextModule_ProvidePreviewChannelHelperFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static WatchNextModule_ProvidePreviewChannelHelperFactory create(Provider<Context> provider) {
        return new WatchNextModule_ProvidePreviewChannelHelperFactory(provider);
    }

    public static PreviewChannelHelper providePreviewChannelHelper(Context context) {
        return new PreviewChannelHelper(context);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PreviewChannelHelper get() {
        return new PreviewChannelHelper(this.contextProvider.get());
    }
}
