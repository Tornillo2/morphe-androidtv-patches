package com.amazon.livingroom.voice;

import com.amazon.ignitionshared.GMBMessageProcessor;
import com.amazon.ignitionshared.GMBMessageSender;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
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
public final class VoiceService_Factory implements Factory<VoiceService> {
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<GMBMessageProcessor> gmbMessageProcessorProvider;
    public final Provider<GMBMessageSender> gmbMessageSenderProvider;
    public final Provider<MinervaMetrics> minervaMetricsProvider;

    public VoiceService_Factory(Provider<GMBMessageProcessor> provider, Provider<GMBMessageSender> provider2, Provider<DeviceProperties> provider3, Provider<MinervaMetrics> provider4) {
        this.gmbMessageProcessorProvider = provider;
        this.gmbMessageSenderProvider = provider2;
        this.devicePropertiesProvider = provider3;
        this.minervaMetricsProvider = provider4;
    }

    public static VoiceService_Factory create(Provider<GMBMessageProcessor> provider, Provider<GMBMessageSender> provider2, Provider<DeviceProperties> provider3, Provider<MinervaMetrics> provider4) {
        return new VoiceService_Factory(provider, provider2, provider3, provider4);
    }

    public static VoiceService newInstance(GMBMessageProcessor gMBMessageProcessor, GMBMessageSender gMBMessageSender, DeviceProperties deviceProperties, MinervaMetrics minervaMetrics) {
        return new VoiceService(gMBMessageProcessor, gMBMessageSender, deviceProperties, minervaMetrics);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public VoiceService get() {
        return new VoiceService(this.gmbMessageProcessorProvider.get(), this.gmbMessageSenderProvider.get(), this.devicePropertiesProvider.get(), this.minervaMetricsProvider.get());
    }
}
