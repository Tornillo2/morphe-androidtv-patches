package com.amazon.ignitionshared.watchnext;

import com.amazon.ignitionshared.metrics.MinervaMetrics;
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
public final class WatchNextHandler_Factory implements Factory<WatchNextHandler> {
    public final Provider<MinervaMetrics> minervaMetricsProvider;
    public final Provider<WatchNextParser> parserProvider;
    public final Provider<WatchNextPublisher> publisherProvider;

    public WatchNextHandler_Factory(Provider<WatchNextPublisher> provider, Provider<WatchNextParser> provider2, Provider<MinervaMetrics> provider3) {
        this.publisherProvider = provider;
        this.parserProvider = provider2;
        this.minervaMetricsProvider = provider3;
    }

    public static WatchNextHandler_Factory create(Provider<WatchNextPublisher> provider, Provider<WatchNextParser> provider2, Provider<MinervaMetrics> provider3) {
        return new WatchNextHandler_Factory(provider, provider2, provider3);
    }

    public static WatchNextHandler newInstance(WatchNextPublisher watchNextPublisher, WatchNextParser watchNextParser, MinervaMetrics minervaMetrics) {
        return new WatchNextHandler(watchNextPublisher, watchNextParser, minervaMetrics);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public WatchNextHandler get() {
        return new WatchNextHandler(this.publisherProvider.get(), this.parserProvider.get(), this.minervaMetricsProvider.get());
    }
}
