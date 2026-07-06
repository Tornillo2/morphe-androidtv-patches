package com.amazon.ignitionshared.watchnext;

import android.content.Context;
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
public final class WatchNextParser_Factory implements Factory<WatchNextParser> {
    public final Provider<Context> contextProvider;
    public final Provider<MinervaMetrics> minervaMetricsProvider;

    public WatchNextParser_Factory(Provider<Context> provider, Provider<MinervaMetrics> provider2) {
        this.contextProvider = provider;
        this.minervaMetricsProvider = provider2;
    }

    public static WatchNextParser_Factory create(Provider<Context> provider, Provider<MinervaMetrics> provider2) {
        return new WatchNextParser_Factory(provider, provider2);
    }

    public static WatchNextParser newInstance(Context context, MinervaMetrics minervaMetrics) {
        return new WatchNextParser(context, minervaMetrics);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public WatchNextParser get() {
        return new WatchNextParser(this.contextProvider.get(), this.minervaMetricsProvider.get());
    }
}
