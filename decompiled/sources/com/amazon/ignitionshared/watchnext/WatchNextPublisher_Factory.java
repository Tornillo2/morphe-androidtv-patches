package com.amazon.ignitionshared.watchnext;

import android.content.Context;
import androidx.tvprovider.media.tv.PreviewChannelHelper;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
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
public final class WatchNextPublisher_Factory implements Factory<WatchNextPublisher> {
    public final Provider<Context> contextProvider;
    public final Provider<WatchNextDatabaseAdapter> dbProvider;
    public final Provider<MinervaMetrics> minervaMetricsProvider;
    public final Provider<PreviewChannelHelper> previewChannelHelperProvider;

    public WatchNextPublisher_Factory(Provider<Context> provider, Provider<MinervaMetrics> provider2, Provider<WatchNextDatabaseAdapter> provider3, Provider<PreviewChannelHelper> provider4) {
        this.contextProvider = provider;
        this.minervaMetricsProvider = provider2;
        this.dbProvider = provider3;
        this.previewChannelHelperProvider = provider4;
    }

    public static WatchNextPublisher_Factory create(Provider<Context> provider, Provider<MinervaMetrics> provider2, Provider<WatchNextDatabaseAdapter> provider3, Provider<PreviewChannelHelper> provider4) {
        return new WatchNextPublisher_Factory(provider, provider2, provider3, provider4);
    }

    public static WatchNextPublisher newInstance(Context context, MinervaMetrics minervaMetrics, WatchNextDatabaseAdapter watchNextDatabaseAdapter, PreviewChannelHelper previewChannelHelper) {
        return new WatchNextPublisher(context, minervaMetrics, watchNextDatabaseAdapter, previewChannelHelper);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public WatchNextPublisher get() {
        return new WatchNextPublisher(this.contextProvider.get(), this.minervaMetricsProvider.get(), this.dbProvider.get(), this.previewChannelHelperProvider.get());
    }
}
