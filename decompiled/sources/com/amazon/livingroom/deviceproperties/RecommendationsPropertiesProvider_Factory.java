package com.amazon.livingroom.deviceproperties;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class RecommendationsPropertiesProvider_Factory implements Factory<RecommendationsPropertiesProvider> {
    public final Provider<Boolean> isPearRecommendationsEnabledProvider;
    public final Provider<Boolean> isPearWatchNextEnabledProvider;

    public RecommendationsPropertiesProvider_Factory(Provider<Boolean> provider, Provider<Boolean> provider2) {
        this.isPearRecommendationsEnabledProvider = provider;
        this.isPearWatchNextEnabledProvider = provider2;
    }

    public static RecommendationsPropertiesProvider_Factory create(Provider<Boolean> provider, Provider<Boolean> provider2) {
        return new RecommendationsPropertiesProvider_Factory(provider, provider2);
    }

    public static RecommendationsPropertiesProvider newInstance(boolean z, boolean z2) {
        return new RecommendationsPropertiesProvider(z, z2);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RecommendationsPropertiesProvider get() {
        return new RecommendationsPropertiesProvider(this.isPearRecommendationsEnabledProvider.get().booleanValue(), this.isPearWatchNextEnabledProvider.get().booleanValue());
    }
}
