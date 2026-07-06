package com.amazon.ignitionshared;

import androidx.customview.widget.ExploreByTouchHelper;
import com.amazon.ignitionshared.IgniteRenderer;
import com.amazon.ignitionshared.deeplink.DeepLinkIntentParser;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.deviceproperties.LocalOverridesProvider;
import com.amazon.livingroom.di.Names;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbContext;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager;
import com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeConnector;
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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
    public final Provider<ExploreByTouchHelper> accessibilityHelperProvider;
    public final Provider<String> actionOverrideDevicePropertiesProvider;
    public final Provider<Boolean> allowLocalPropertyOverridesProvider;
    public final Provider<IgnitionContextProvider> contextProvider;
    public final Provider<DeepLinkIntentParser> deepLinkIntentParserProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<FtvMpbContext> ftvMpbContextProvider;
    public final Provider<GMBMessageSender> gmbMessageSenderProvider;
    public final Provider<IgniteRenderer.EventHandler> igniteEventHandlerProvider;
    public final Provider<LocalOverridesProvider> localOverridesProvider;
    public final Provider<MinervaMetrics> minervaMetricsProvider;
    public final Provider<MediaPipelineBackendEngineManager> mpbEngineManagerProvider;
    public final Provider<RendererManager> rendererManagerProvider;
    public final Provider<IgniteRenderer> rendererProvider;
    public final Provider<SonyCalibratedModeConnector> sonyCalibratedModeConnectorProvider;

    public MainActivity_MembersInjector(Provider<IgnitionContextProvider> provider, Provider<DeviceProperties> provider2, Provider<LocalOverridesProvider> provider3, Provider<FtvMpbContext> provider4, Provider<MediaPipelineBackendEngineManager> provider5, Provider<IgniteRenderer.EventHandler> provider6, Provider<IgniteRenderer> provider7, Provider<RendererManager> provider8, Provider<ExploreByTouchHelper> provider9, Provider<MinervaMetrics> provider10, Provider<SonyCalibratedModeConnector> provider11, Provider<GMBMessageSender> provider12, Provider<Boolean> provider13, Provider<String> provider14, Provider<DeepLinkIntentParser> provider15) {
        this.contextProvider = provider;
        this.devicePropertiesProvider = provider2;
        this.localOverridesProvider = provider3;
        this.ftvMpbContextProvider = provider4;
        this.mpbEngineManagerProvider = provider5;
        this.igniteEventHandlerProvider = provider6;
        this.rendererProvider = provider7;
        this.rendererManagerProvider = provider8;
        this.accessibilityHelperProvider = provider9;
        this.minervaMetricsProvider = provider10;
        this.sonyCalibratedModeConnectorProvider = provider11;
        this.gmbMessageSenderProvider = provider12;
        this.allowLocalPropertyOverridesProvider = provider13;
        this.actionOverrideDevicePropertiesProvider = provider14;
        this.deepLinkIntentParserProvider = provider15;
    }

    public static MembersInjector<MainActivity> create(Provider<IgnitionContextProvider> provider, Provider<DeviceProperties> provider2, Provider<LocalOverridesProvider> provider3, Provider<FtvMpbContext> provider4, Provider<MediaPipelineBackendEngineManager> provider5, Provider<IgniteRenderer.EventHandler> provider6, Provider<IgniteRenderer> provider7, Provider<RendererManager> provider8, Provider<ExploreByTouchHelper> provider9, Provider<MinervaMetrics> provider10, Provider<SonyCalibratedModeConnector> provider11, Provider<GMBMessageSender> provider12, Provider<Boolean> provider13, Provider<String> provider14, Provider<DeepLinkIntentParser> provider15) {
        return new MainActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15);
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.accessibilityHelper")
    public static void injectAccessibilityHelper(MainActivity mainActivity, ExploreByTouchHelper exploreByTouchHelper) {
        mainActivity.accessibilityHelper = exploreByTouchHelper;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.actionOverrideDeviceProperties")
    @Named(Names.ACTION_OVERRIDE_DEVICE_PROPERTIES)
    public static void injectActionOverrideDeviceProperties(MainActivity mainActivity, String str) {
        mainActivity.actionOverrideDeviceProperties = str;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.allowLocalPropertyOverrides")
    @Named(Names.ALLOW_LOCAL_PROPERTY_OVERRIDES)
    public static void injectAllowLocalPropertyOverrides(MainActivity mainActivity, javax.inject.Provider<Boolean> provider) {
        mainActivity.allowLocalPropertyOverrides = provider;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.contextProvider")
    public static void injectContextProvider(MainActivity mainActivity, IgnitionContextProvider ignitionContextProvider) {
        mainActivity.contextProvider = ignitionContextProvider;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.deepLinkIntentParser")
    public static void injectDeepLinkIntentParser(MainActivity mainActivity, DeepLinkIntentParser deepLinkIntentParser) {
        mainActivity.deepLinkIntentParser = deepLinkIntentParser;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.deviceProperties")
    public static void injectDeviceProperties(MainActivity mainActivity, DeviceProperties deviceProperties) {
        mainActivity.deviceProperties = deviceProperties;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.ftvMpbContext")
    public static void injectFtvMpbContext(MainActivity mainActivity, FtvMpbContext ftvMpbContext) {
        mainActivity.ftvMpbContext = ftvMpbContext;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.gmbMessageSender")
    public static void injectGmbMessageSender(MainActivity mainActivity, GMBMessageSender gMBMessageSender) {
        mainActivity.gmbMessageSender = gMBMessageSender;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.igniteEventHandler")
    public static void injectIgniteEventHandler(MainActivity mainActivity, IgniteRenderer.EventHandler eventHandler) {
        mainActivity.igniteEventHandler = eventHandler;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.localOverridesProvider")
    public static void injectLocalOverridesProvider(MainActivity mainActivity, LocalOverridesProvider localOverridesProvider) {
        mainActivity.localOverridesProvider = localOverridesProvider;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.minervaMetrics")
    public static void injectMinervaMetrics(MainActivity mainActivity, MinervaMetrics minervaMetrics) {
        mainActivity.minervaMetrics = minervaMetrics;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.mpbEngineManager")
    public static void injectMpbEngineManager(MainActivity mainActivity, MediaPipelineBackendEngineManager mediaPipelineBackendEngineManager) {
        mainActivity.mpbEngineManager = mediaPipelineBackendEngineManager;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.renderer")
    public static void injectRenderer(MainActivity mainActivity, IgniteRenderer igniteRenderer) {
        mainActivity.renderer = igniteRenderer;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.rendererManager")
    public static void injectRendererManager(MainActivity mainActivity, RendererManager rendererManager) {
        mainActivity.rendererManager = rendererManager;
    }

    @InjectedFieldSignature("com.amazon.ignitionshared.MainActivity.sonyCalibratedModeConnector")
    public static void injectSonyCalibratedModeConnector(MainActivity mainActivity, SonyCalibratedModeConnector sonyCalibratedModeConnector) {
        mainActivity.sonyCalibratedModeConnector = sonyCalibratedModeConnector;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MainActivity mainActivity) {
        mainActivity.contextProvider = this.contextProvider.get();
        mainActivity.deviceProperties = this.devicePropertiesProvider.get();
        mainActivity.localOverridesProvider = this.localOverridesProvider.get();
        mainActivity.ftvMpbContext = this.ftvMpbContextProvider.get();
        mainActivity.mpbEngineManager = this.mpbEngineManagerProvider.get();
        mainActivity.igniteEventHandler = this.igniteEventHandlerProvider.get();
        mainActivity.renderer = this.rendererProvider.get();
        mainActivity.rendererManager = this.rendererManagerProvider.get();
        mainActivity.accessibilityHelper = this.accessibilityHelperProvider.get();
        mainActivity.minervaMetrics = this.minervaMetricsProvider.get();
        mainActivity.sonyCalibratedModeConnector = this.sonyCalibratedModeConnectorProvider.get();
        mainActivity.gmbMessageSender = this.gmbMessageSenderProvider.get();
        mainActivity.allowLocalPropertyOverrides = this.allowLocalPropertyOverridesProvider;
        mainActivity.actionOverrideDeviceProperties = this.actionOverrideDevicePropertiesProvider.get();
        mainActivity.deepLinkIntentParser = this.deepLinkIntentParserProvider.get();
    }
}
