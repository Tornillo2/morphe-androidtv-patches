package com.amazon.livingroom.deviceproperties;

import com.amazon.livingroom.di.Names;
import javax.inject.Inject;
import javax.inject.Named;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RecommendationsPropertiesProvider {
    public final boolean isPearRecommendationsEnabled;
    public final boolean isPearWatchNextEnabled;
    public final long pearRefreshInterval = 1440;

    @Inject
    public RecommendationsPropertiesProvider(@Named(Names.PEAR_RECOMMENDATIONS_ENABLED) boolean z, @Named(Names.PEAR_WATCH_NEXT_ENABLED) boolean z2) {
        this.isPearRecommendationsEnabled = z;
        this.isPearWatchNextEnabled = z2;
    }

    public final boolean getPearRecommendationsEnabled() {
        return this.isPearRecommendationsEnabled;
    }

    public final long getPearRefreshInterval() {
        return this.pearRefreshInterval;
    }

    public final boolean getPearWatchNextEnabled() {
        return this.isPearWatchNextEnabled;
    }
}
