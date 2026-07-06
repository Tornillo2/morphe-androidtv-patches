package com.amazon.ignition.recommendation.contentprovider;

import android.content.SharedPreferences;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.di.Names;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.inject.Named;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class RequestStructureContentProvider_MembersInjector implements MembersInjector<RequestStructureContentProvider> {
    public final Provider<MetricsRecorder> metricsRecorderProvider;
    public final Provider<SharedPreferences> preferencesProvider;

    public RequestStructureContentProvider_MembersInjector(Provider<SharedPreferences> provider, Provider<MetricsRecorder> provider2) {
        this.preferencesProvider = provider;
        this.metricsRecorderProvider = provider2;
    }

    public static MembersInjector<RequestStructureContentProvider> create(Provider<SharedPreferences> provider, Provider<MetricsRecorder> provider2) {
        return new RequestStructureContentProvider_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.amazon.ignition.recommendation.contentprovider.RequestStructureContentProvider.metricsRecorder")
    public static void injectMetricsRecorder(RequestStructureContentProvider requestStructureContentProvider, MetricsRecorder metricsRecorder) {
        requestStructureContentProvider.metricsRecorder = metricsRecorder;
    }

    @InjectedFieldSignature("com.amazon.ignition.recommendation.contentprovider.RequestStructureContentProvider.preferences")
    @Named(Names.RECOMMENDATION_REQUEST_STRUCTURE_CONTENT_PROVIDER)
    public static void injectPreferences(RequestStructureContentProvider requestStructureContentProvider, SharedPreferences sharedPreferences) {
        requestStructureContentProvider.preferences = sharedPreferences;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RequestStructureContentProvider requestStructureContentProvider) {
        requestStructureContentProvider.preferences = this.preferencesProvider.get();
        requestStructureContentProvider.metricsRecorder = this.metricsRecorderProvider.get();
    }
}
