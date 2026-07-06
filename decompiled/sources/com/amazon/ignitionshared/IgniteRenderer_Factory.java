package com.amazon.ignitionshared;

import android.content.Context;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import com.amazon.ignitionshared.IgniteRenderer;
import com.amazon.ignitionshared.filesystem.LocalStorage;
import com.amazon.ignitionshared.metrics.JemallocAllocatorMetricRecorder;
import com.amazon.ignitionshared.migration.MigrationManager;
import com.amazon.livingroom.accessibility.TextToSpeechEngine;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider;
import com.amazon.livingroom.mediapipelinebackend.DrmProvisioner;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbContext;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.io.File;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext", "javax.inject.Named"})
public final class IgniteRenderer_Factory implements Factory<IgniteRenderer> {
    public final Provider<CachedTarExtractor> assetsExtractorProvider;
    public final Provider<Context> contextProvider;
    public final Provider<DeviceAttestationService> deviceAttestationServiceProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<DrmKeyStatusNotifier> drmKeyStatusNotifierProvider;
    public final Provider<DrmProvisioner> drmProvisionerProvider;
    public final Provider<IgniteRenderer.EventHandler> eventHandlerProvider;
    public final Provider<ExitReasonHandler> exitReasonHandlerProvider;
    public final Provider<FtvMpbContext> ftvMpbContextProvider;
    public final Provider<FtvMpbDrmContext.Factory> ftvMpbDrmContextFactoryProvider;
    public final Provider<GMBMessageProcessor> gmbMessageProcessorProvider;
    public final Provider<GMBMessageSender> gmbMessageSenderProvider;
    public final Provider<IgniteDevicePropertiesProvider> igniteDevicePropertiesProvider;
    public final Provider<IgnitionContextProvider> ignitionContextProvider;
    public final Provider<String[]> ignitionXStartupArgsProvider;
    public final Provider<JemallocAllocatorMetricRecorder> jemallocAllocatorMetricRecorderProvider;
    public final Provider<LaunchReasonIntentParser> launchReasonIntentParserProvider;
    public final Provider<LocalStorage> localStorageProvider;
    public final Provider<MigrationManager> migrationManagerProvider;
    public final Provider<MediaPipelineBackendEngineManager> mpbEngineManagerProvider;
    public final Provider<NativeThreadInitializer> nativeThreadInitializerProvider;
    public final Provider<File> rootDirProvider;
    public final Provider<Set<ServiceInitializer>> serviceInitializerSetProvider;
    public final Provider<TextToSpeechEngine> textToSpeechEngineProvider;

    public IgniteRenderer_Factory(Provider<Context> provider, Provider<IgniteRenderer.EventHandler> provider2, Provider<File> provider3, Provider<CachedTarExtractor> provider4, Provider<IgniteDevicePropertiesProvider> provider5, Provider<DrmProvisioner> provider6, Provider<MediaPipelineBackendEngineManager> provider7, Provider<FtvMpbContext> provider8, Provider<String[]> provider9, Provider<DeviceProperties> provider10, Provider<NativeThreadInitializer> provider11, Provider<LocalStorage> provider12, Provider<TextToSpeechEngine> provider13, Provider<GMBMessageProcessor> provider14, Provider<GMBMessageSender> provider15, Provider<ExitReasonHandler> provider16, Provider<DeviceAttestationService> provider17, Provider<MigrationManager> provider18, Provider<IgnitionContextProvider> provider19, Provider<LaunchReasonIntentParser> provider20, Provider<DrmKeyStatusNotifier> provider21, Provider<FtvMpbDrmContext.Factory> provider22, Provider<JemallocAllocatorMetricRecorder> provider23, Provider<Set<ServiceInitializer>> provider24) {
        this.contextProvider = provider;
        this.eventHandlerProvider = provider2;
        this.rootDirProvider = provider3;
        this.assetsExtractorProvider = provider4;
        this.igniteDevicePropertiesProvider = provider5;
        this.drmProvisionerProvider = provider6;
        this.mpbEngineManagerProvider = provider7;
        this.ftvMpbContextProvider = provider8;
        this.ignitionXStartupArgsProvider = provider9;
        this.devicePropertiesProvider = provider10;
        this.nativeThreadInitializerProvider = provider11;
        this.localStorageProvider = provider12;
        this.textToSpeechEngineProvider = provider13;
        this.gmbMessageProcessorProvider = provider14;
        this.gmbMessageSenderProvider = provider15;
        this.exitReasonHandlerProvider = provider16;
        this.deviceAttestationServiceProvider = provider17;
        this.migrationManagerProvider = provider18;
        this.ignitionContextProvider = provider19;
        this.launchReasonIntentParserProvider = provider20;
        this.drmKeyStatusNotifierProvider = provider21;
        this.ftvMpbDrmContextFactoryProvider = provider22;
        this.jemallocAllocatorMetricRecorderProvider = provider23;
        this.serviceInitializerSetProvider = provider24;
    }

    public static IgniteRenderer_Factory create(Provider<Context> provider, Provider<IgniteRenderer.EventHandler> provider2, Provider<File> provider3, Provider<CachedTarExtractor> provider4, Provider<IgniteDevicePropertiesProvider> provider5, Provider<DrmProvisioner> provider6, Provider<MediaPipelineBackendEngineManager> provider7, Provider<FtvMpbContext> provider8, Provider<String[]> provider9, Provider<DeviceProperties> provider10, Provider<NativeThreadInitializer> provider11, Provider<LocalStorage> provider12, Provider<TextToSpeechEngine> provider13, Provider<GMBMessageProcessor> provider14, Provider<GMBMessageSender> provider15, Provider<ExitReasonHandler> provider16, Provider<DeviceAttestationService> provider17, Provider<MigrationManager> provider18, Provider<IgnitionContextProvider> provider19, Provider<LaunchReasonIntentParser> provider20, Provider<DrmKeyStatusNotifier> provider21, Provider<FtvMpbDrmContext.Factory> provider22, Provider<JemallocAllocatorMetricRecorder> provider23, Provider<Set<ServiceInitializer>> provider24) {
        return new IgniteRenderer_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24);
    }

    public static IgniteRenderer newInstance(Context context, IgniteRenderer.EventHandler eventHandler, File file, CachedTarExtractor cachedTarExtractor, IgniteDevicePropertiesProvider igniteDevicePropertiesProvider, DrmProvisioner drmProvisioner, MediaPipelineBackendEngineManager mediaPipelineBackendEngineManager, FtvMpbContext ftvMpbContext, String[] strArr, DeviceProperties deviceProperties, NativeThreadInitializer nativeThreadInitializer, LocalStorage localStorage, javax.inject.Provider<TextToSpeechEngine> provider, GMBMessageProcessor gMBMessageProcessor, GMBMessageSender gMBMessageSender, ExitReasonHandler exitReasonHandler, DeviceAttestationService deviceAttestationService, MigrationManager migrationManager, IgnitionContextProvider ignitionContextProvider, LaunchReasonIntentParser launchReasonIntentParser, DrmKeyStatusNotifier drmKeyStatusNotifier, FtvMpbDrmContext.Factory factory, JemallocAllocatorMetricRecorder jemallocAllocatorMetricRecorder, Set<ServiceInitializer> set) {
        return new IgniteRenderer(context, eventHandler, file, cachedTarExtractor, igniteDevicePropertiesProvider, drmProvisioner, mediaPipelineBackendEngineManager, ftvMpbContext, strArr, deviceProperties, nativeThreadInitializer, localStorage, provider, gMBMessageProcessor, gMBMessageSender, exitReasonHandler, deviceAttestationService, migrationManager, ignitionContextProvider, launchReasonIntentParser, drmKeyStatusNotifier, factory, jemallocAllocatorMetricRecorder, set);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public IgniteRenderer get() {
        return new IgniteRenderer(this.contextProvider.get(), this.eventHandlerProvider.get(), this.rootDirProvider.get(), this.assetsExtractorProvider.get(), this.igniteDevicePropertiesProvider.get(), this.drmProvisionerProvider.get(), this.mpbEngineManagerProvider.get(), this.ftvMpbContextProvider.get(), this.ignitionXStartupArgsProvider.get(), this.devicePropertiesProvider.get(), this.nativeThreadInitializerProvider.get(), this.localStorageProvider.get(), this.textToSpeechEngineProvider, this.gmbMessageProcessorProvider.get(), this.gmbMessageSenderProvider.get(), this.exitReasonHandlerProvider.get(), this.deviceAttestationServiceProvider.get(), this.migrationManagerProvider.get(), this.ignitionContextProvider.get(), this.launchReasonIntentParserProvider.get(), this.drmKeyStatusNotifierProvider.get(), this.ftvMpbDrmContextFactoryProvider.get(), this.jemallocAllocatorMetricRecorderProvider.get(), this.serviceInitializerSetProvider.get());
    }
}
