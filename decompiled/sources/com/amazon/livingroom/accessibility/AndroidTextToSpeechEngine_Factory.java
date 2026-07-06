package com.amazon.livingroom.accessibility;

import android.content.Context;
import com.amazon.ignitionshared.TextToSpeechStatusProvider;
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
public final class AndroidTextToSpeechEngine_Factory implements Factory<AndroidTextToSpeechEngine> {
    public final Provider<Context> contextProvider;
    public final Provider<MinervaMetrics> minervaMetricsProvider;
    public final Provider<TextToSpeechStatusProvider> ttsStatusProvider;

    public AndroidTextToSpeechEngine_Factory(Provider<Context> provider, Provider<TextToSpeechStatusProvider> provider2, Provider<MinervaMetrics> provider3) {
        this.contextProvider = provider;
        this.ttsStatusProvider = provider2;
        this.minervaMetricsProvider = provider3;
    }

    public static AndroidTextToSpeechEngine_Factory create(Provider<Context> provider, Provider<TextToSpeechStatusProvider> provider2, Provider<MinervaMetrics> provider3) {
        return new AndroidTextToSpeechEngine_Factory(provider, provider2, provider3);
    }

    public static AndroidTextToSpeechEngine newInstance(Context context, TextToSpeechStatusProvider textToSpeechStatusProvider, MinervaMetrics minervaMetrics) {
        return new AndroidTextToSpeechEngine(context, textToSpeechStatusProvider, minervaMetrics);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AndroidTextToSpeechEngine get() {
        return new AndroidTextToSpeechEngine(this.contextProvider.get(), this.ttsStatusProvider.get(), this.minervaMetricsProvider.get());
    }
}
