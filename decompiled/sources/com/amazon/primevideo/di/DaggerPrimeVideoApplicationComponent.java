package com.amazon.primevideo.di;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.os.Handler;
import android.view.SurfaceView;
import android.view.WindowManager;
import androidx.tvprovider.media.tv.PreviewChannelHelper;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import com.amazon.ignition.recommendation.contentprovider.RequestStructureContentProvider;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor_Factory;
import com.amazon.ignitionshared.CachedTarExtractor;
import com.amazon.ignitionshared.CachedTarExtractor_Factory;
import com.amazon.ignitionshared.DeviceAttestationService;
import com.amazon.ignitionshared.DeviceAttestationService_Factory;
import com.amazon.ignitionshared.ExitReasonHandler;
import com.amazon.ignitionshared.ExitReasonHandler_Factory;
import com.amazon.ignitionshared.GMBMessageProcessor;
import com.amazon.ignitionshared.GMBMessageProcessor_Factory;
import com.amazon.ignitionshared.GMBMessageSender;
import com.amazon.ignitionshared.GMBMessageSender_Factory;
import com.amazon.ignitionshared.IgniteAllocator;
import com.amazon.ignitionshared.IgniteAllocator_Factory;
import com.amazon.ignitionshared.IgniteExploreByTouchHelper;
import com.amazon.ignitionshared.IgniteExploreByTouchHelper_Factory;
import com.amazon.ignitionshared.IgniteRenderer;
import com.amazon.ignitionshared.IgniteRenderer_EventHandler_Factory;
import com.amazon.ignitionshared.IgniteRenderer_Factory;
import com.amazon.ignitionshared.IgnitionContextProvider;
import com.amazon.ignitionshared.IgnitionContextProvider_Factory;
import com.amazon.ignitionshared.LaunchReasonIntentParser;
import com.amazon.ignitionshared.LaunchReasonIntentParser_Factory;
import com.amazon.ignitionshared.LifecycleBoundHandler;
import com.amazon.ignitionshared.LifecycleBoundHandler_Factory;
import com.amazon.ignitionshared.MainActivity;
import com.amazon.ignitionshared.MapSqliteHelper;
import com.amazon.ignitionshared.MapSqliteHelper_Factory;
import com.amazon.ignitionshared.NativeAllocatorMessageHandler;
import com.amazon.ignitionshared.NativeAllocatorMessageHandler_Factory;
import com.amazon.ignitionshared.NativeThreadInitializer;
import com.amazon.ignitionshared.NativeThreadInitializer_Factory;
import com.amazon.ignitionshared.RendererManager;
import com.amazon.ignitionshared.RendererManager_Factory;
import com.amazon.ignitionshared.ServiceInitializer;
import com.amazon.ignitionshared.TextToSpeechStatusProvider;
import com.amazon.ignitionshared.TextToSpeechStatusProvider_Factory;
import com.amazon.ignitionshared.deeplink.AndroidTVDeepLinkIntentParser;
import com.amazon.ignitionshared.deeplink.AndroidTVDeepLinkIntentParser_Factory;
import com.amazon.ignitionshared.filesystem.LocalStorage;
import com.amazon.ignitionshared.filesystem.LocalStorage_Factory;
import com.amazon.ignitionshared.metrics.JemallocAllocatorMetricRecorder;
import com.amazon.ignitionshared.metrics.JemallocAllocatorMetricRecorder_Factory;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.ignitionshared.metrics.MinervaMetrics_Factory;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder_Factory;
import com.amazon.ignitionshared.migration.IgniteMigrationManager;
import com.amazon.ignitionshared.migration.IgniteMigrationManager_Factory;
import com.amazon.ignitionshared.network.TerminatorIdProvider_Factory;
import com.amazon.ignitionshared.network.VolleyModule_ProvideBackgroundDeliveryRequestQueueFactory;
import com.amazon.ignitionshared.network.VolleyModule_ProvideHurlStackFactory;
import com.amazon.ignitionshared.network.VolleyModule_ProvideRequestQueueFactory;
import com.amazon.ignitionshared.network.VolleyModule_ProvideSecureSslSocketFactoryFactory;
import com.amazon.ignitionshared.pear.PearAccessTokenProvider;
import com.amazon.ignitionshared.pear.PearAccessTokenProvider_Factory;
import com.amazon.ignitionshared.pear.PearPlacement;
import com.amazon.ignitionshared.pear.PearRecommendationFlowController;
import com.amazon.ignitionshared.pear.PearRecommendationFlowController_Factory;
import com.amazon.ignitionshared.pear.PearRecommendationServiceInitializer;
import com.amazon.ignitionshared.pear.PearRecommendationServiceInitializer_Factory;
import com.amazon.ignitionshared.pear.PearResponseParser;
import com.amazon.ignitionshared.pear.PearResponseParser_Factory;
import com.amazon.ignitionshared.pear.PearUpdateStructure;
import com.amazon.ignitionshared.pear.PearUpdateStructure_Factory;
import com.amazon.ignitionshared.pear.PearUriBuilder;
import com.amazon.ignitionshared.pear.PearUriBuilder_Factory;
import com.amazon.ignitionshared.pear.RecommendationUpdater;
import com.amazon.ignitionshared.pear.RecommendationUpdater_Factory;
import com.amazon.ignitionshared.receiver.BootUpReceiver;
import com.amazon.ignitionshared.receiver.ScheduleRecommendationsOnInstallReceiver;
import com.amazon.ignitionshared.recommendation.BootstrapRequestStructureBuilder;
import com.amazon.ignitionshared.recommendation.BootstrapRequestStructureBuilder_Factory;
import com.amazon.ignitionshared.recommendation.PersonalisedRecommendationPlacement;
import com.amazon.ignitionshared.recommendation.PersonalisedRecommendationPlacement_Factory;
import com.amazon.ignitionshared.recommendation.contentprovider.RequestStructureContentProviderManager;
import com.amazon.ignitionshared.recommendation.contentprovider.RequestStructureContentProviderManager_Factory;
import com.amazon.ignitionshared.recommendation.dispacher.RecommendationRequestDispatcher;
import com.amazon.ignitionshared.recommendation.dispacher.RecommendationRequestDispatcher_Factory;
import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler;
import com.amazon.ignitionshared.recommendation.handler.RecommendationHandler_Factory;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder;
import com.amazon.ignitionshared.recommendation.metric.RecommendationsMetricRecorder_Factory;
import com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher;
import com.amazon.ignitionshared.recommendation.scheduler.RecommendationUpdaterPeriodicWorkRequestBuilder;
import com.amazon.ignitionshared.recommendation.scheduler.RecommendationUpdaterPeriodicWorkRequestBuilder_Factory;
import com.amazon.ignitionshared.recommendation.scheduler.RecommendationsScheduler;
import com.amazon.ignitionshared.recommendation.scheduler.RecommendationsScheduler_Factory;
import com.amazon.ignitionshared.service.AppStartupConfigCacheRefresher;
import com.amazon.ignitionshared.service.ClearWatchNextWorker;
import com.amazon.ignitionshared.service.DtidRequestOnStartupWorker;
import com.amazon.ignitionshared.service.PeriodicUpdateRecommendationsWorker;
import com.amazon.ignitionshared.service.UpdateRecommendationsWorker;
import com.amazon.ignitionshared.service.UpdateWatchNextWorker;
import com.amazon.ignitionshared.tvinput.TifOverlaySurfaceHolderCallbackProvider;
import com.amazon.ignitionshared.tvinput.TifOverlaySurfaceHolderCallbackProvider_Factory;
import com.amazon.ignitionshared.watchnext.PearWatchNextProgramBuilder_Factory;
import com.amazon.ignitionshared.watchnext.WatchNextDatabaseAdapter;
import com.amazon.ignitionshared.watchnext.WatchNextDatabaseAdapter_Factory;
import com.amazon.ignitionshared.watchnext.WatchNextHandler;
import com.amazon.ignitionshared.watchnext.WatchNextHandler_Factory;
import com.amazon.ignitionshared.watchnext.WatchNextParser;
import com.amazon.ignitionshared.watchnext.WatchNextParser_Factory;
import com.amazon.ignitionshared.watchnext.WatchNextPlacement;
import com.amazon.ignitionshared.watchnext.WatchNextPlacement_Factory;
import com.amazon.ignitionshared.watchnext.WatchNextPublisher;
import com.amazon.ignitionshared.watchnext.WatchNextPublisher_Factory;
import com.amazon.livingroom.accessibility.AndroidTextToSpeechEngine;
import com.amazon.livingroom.accessibility.AndroidTextToSpeechEngine_Factory;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigCache;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigCache_Factory;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigProvider;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigProvider_Factory;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigRequester;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigRequester_Factory;
import com.amazon.livingroom.appstartupconfig.GetAppStartupConfigUriFactory_Factory;
import com.amazon.livingroom.auth.ApplicationAccessTokenProvider;
import com.amazon.livingroom.auth.ApplicationAccessTokenProvider_Factory;
import com.amazon.livingroom.auth.ApplicationAccessTokenRequester;
import com.amazon.livingroom.auth.ApplicationAccessTokenRequester_Factory;
import com.amazon.livingroom.auth.RefreshTokenParser;
import com.amazon.livingroom.auth.RefreshTokenParser_Factory;
import com.amazon.livingroom.auth.RefreshTokenProvider;
import com.amazon.livingroom.auth.RefreshTokenProvider_Factory;
import com.amazon.livingroom.deviceproperties.ApplicationPackagePropertiesProvider_Factory;
import com.amazon.livingroom.deviceproperties.ApplicationSourcePropertiesProvider;
import com.amazon.livingroom.deviceproperties.ApplicationSourcePropertiesProvider_Factory;
import com.amazon.livingroom.deviceproperties.AudioProperties;
import com.amazon.livingroom.deviceproperties.AudioProperties_Factory;
import com.amazon.livingroom.deviceproperties.DecoderCapabilitiesProvider;
import com.amazon.livingroom.deviceproperties.DecoderCapabilitiesProvider_Factory;
import com.amazon.livingroom.deviceproperties.DefaultDeviceProperties;
import com.amazon.livingroom.deviceproperties.DefaultDeviceProperties_Factory;
import com.amazon.livingroom.deviceproperties.DeviceBuildProperties_Factory;
import com.amazon.livingroom.deviceproperties.DeviceIdProvider_Factory;
import com.amazon.livingroom.deviceproperties.DisplayModeProperties;
import com.amazon.livingroom.deviceproperties.DisplayModeProperties_Factory;
import com.amazon.livingroom.deviceproperties.DisplayPropertiesProvider_Factory;
import com.amazon.livingroom.deviceproperties.LocalOverridesProvider;
import com.amazon.livingroom.deviceproperties.LocalOverridesProvider_Factory;
import com.amazon.livingroom.deviceproperties.MaxVideoResolutionProvider;
import com.amazon.livingroom.deviceproperties.MaxVideoResolutionProvider_Factory;
import com.amazon.livingroom.deviceproperties.NetworkProperties;
import com.amazon.livingroom.deviceproperties.NetworkProperties_Factory;
import com.amazon.livingroom.deviceproperties.OperatingSystemProperties;
import com.amazon.livingroom.deviceproperties.OperatingSystemProperties_Factory;
import com.amazon.livingroom.deviceproperties.OverridableDeviceProperties;
import com.amazon.livingroom.deviceproperties.PlatformDeviceProperties;
import com.amazon.livingroom.deviceproperties.PlatformProperty;
import com.amazon.livingroom.deviceproperties.RecommendationsPropertiesProvider;
import com.amazon.livingroom.deviceproperties.RecommendationsPropertiesProvider_Factory;
import com.amazon.livingroom.deviceproperties.RemoteOverridesProvider;
import com.amazon.livingroom.deviceproperties.RemoteOverridesProvider_Factory;
import com.amazon.livingroom.deviceproperties.SonyCalibratedModePropertiesProvider;
import com.amazon.livingroom.deviceproperties.SonyCalibratedModePropertiesProvider_Factory;
import com.amazon.livingroom.deviceproperties.SystemProperties_Factory;
import com.amazon.livingroom.deviceproperties.VideoCapabilitiesProvider;
import com.amazon.livingroom.deviceproperties.VideoCapabilitiesProvider_Factory;
import com.amazon.livingroom.deviceproperties.dtid.DtidCache;
import com.amazon.livingroom.deviceproperties.dtid.DtidCache_Factory;
import com.amazon.livingroom.deviceproperties.dtid.DtidProvider;
import com.amazon.livingroom.deviceproperties.dtid.DtidProvider_Factory;
import com.amazon.livingroom.deviceproperties.dtid.DtidRequestUriFactory;
import com.amazon.livingroom.deviceproperties.dtid.DtidRequestUriFactory_Factory;
import com.amazon.livingroom.deviceproperties.dtid.DtidRequester;
import com.amazon.livingroom.deviceproperties.dtid.DtidRequester_Factory;
import com.amazon.livingroom.deviceproperties.expression.ExpressionEvaluator;
import com.amazon.livingroom.di.CoreModule;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideActivityManagerFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideApplicationPackageNameFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideAudioManagerFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideContentResolverFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideDisplayManagerFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideDrmKeyStatusNotifierFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideDtidRequestRetryPolicyFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideDtidSharedPreferencesFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideGascRetryPolicyFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideIgnitionXStartupArgumentsFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideLocalExpressionEvaluatorFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideLocallyOverridableDevicePropertiesFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideMainHandlerFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideMinervaClientFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvidePackageManagerFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvidePictureQualityControllerFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvidePlatformDevicePropertiesFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideRecommendationsSharedPreferencesFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideRemoteExpressionEvaluatorFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideRemotelyOverridableDevicePropertiesFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideRendererManagerExitCallbackFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideRequestStructureContentProviderPreferenceFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideSupportedLocaleLanguageTagsFactory;
import com.amazon.livingroom.di.CoreModule_Companion_ProvideWindowManagerFactory;
import com.amazon.livingroom.di.MainActivityComponent;
import com.amazon.livingroom.di.MainActivityModule;
import com.amazon.livingroom.di.MainActivityModule_ProvideIgniteSurfaceViewFactory;
import com.amazon.livingroom.di.Names;
import com.amazon.livingroom.di.RecommendationModule_ProvideRecommendationPublisherFactory;
import com.amazon.livingroom.di.WatchNextModule_ProvidePreviewChannelHelperFactory;
import com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider;
import com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider_Factory;
import com.amazon.livingroom.localisation.DeviceLocaleProvider;
import com.amazon.livingroom.localisation.DeviceLocaleProvider_Factory;
import com.amazon.livingroom.mediapipelinebackend.AudioFocusManager;
import com.amazon.livingroom.mediapipelinebackend.AudioFocusManager_Factory;
import com.amazon.livingroom.mediapipelinebackend.C0052FtvMpbApi_Factory;
import com.amazon.livingroom.mediapipelinebackend.C0053FtvMpbDrmContext_Factory;
import com.amazon.livingroom.mediapipelinebackend.C0054FtvMpbInstance_Factory;
import com.amazon.livingroom.mediapipelinebackend.C0055InternalPlaybackSurface_Factory;
import com.amazon.livingroom.mediapipelinebackend.C0056MediaPipelineBackendEngine_Factory;
import com.amazon.livingroom.mediapipelinebackend.CodecQuerier;
import com.amazon.livingroom.mediapipelinebackend.CodecQuerier_Factory;
import com.amazon.livingroom.mediapipelinebackend.DisplayInformation;
import com.amazon.livingroom.mediapipelinebackend.DisplayInformation_Factory;
import com.amazon.livingroom.mediapipelinebackend.DisplayModeManager;
import com.amazon.livingroom.mediapipelinebackend.DisplayModeManager_Factory;
import com.amazon.livingroom.mediapipelinebackend.DisplayModeMatcher_Factory;
import com.amazon.livingroom.mediapipelinebackend.DrmProvisioner;
import com.amazon.livingroom.mediapipelinebackend.DrmProvisioner_Factory;
import com.amazon.livingroom.mediapipelinebackend.ExternalPlaybackSurfaceRegistry;
import com.amazon.livingroom.mediapipelinebackend.ExternalPlaybackSurfaceRegistry_Factory;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbApi;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbApi_Factory_Impl;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbContext;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbContext_Factory;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext_Factory_Impl;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbInstance_Factory_Impl;
import com.amazon.livingroom.mediapipelinebackend.HdcpChecker;
import com.amazon.livingroom.mediapipelinebackend.HdcpChecker_Factory;
import com.amazon.livingroom.mediapipelinebackend.HdcpVersionProvider;
import com.amazon.livingroom.mediapipelinebackend.HdcpVersionProvider_Factory;
import com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface;
import com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface_Factory_Impl;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager_Factory;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine_Factory_Impl;
import com.amazon.livingroom.mediapipelinebackend.MediaSessionAdapter;
import com.amazon.livingroom.mediapipelinebackend.MediaSessionAdapter_Factory;
import com.amazon.livingroom.mediapipelinebackend.MediaSessionCallback;
import com.amazon.livingroom.mediapipelinebackend.MediaSessionCallback_Factory;
import com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeConnector;
import com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeConnector_Factory;
import com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeController;
import com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeController_Factory;
import com.amazon.livingroom.mediapipelinebackend.WakeLocker;
import com.amazon.livingroom.mediapipelinebackend.WakeLocker_Factory;
import com.amazon.livingroom.mediapipelinebackend.WidevineCapabilitiesProvider;
import com.amazon.livingroom.mediapipelinebackend.WidevineCapabilitiesProvider_Factory;
import com.amazon.livingroom.voice.VoiceService;
import com.amazon.livingroom.voice.VoiceService_Factory;
import com.amazon.minerva.client.thirdparty.api.AmazonMinerva;
import com.amazon.primevideo.BuildConfig;
import com.amazon.primevideo.PrimeVideoApplication;
import com.amazon.primevideo.advertising.GoogleAdvertisingProperties;
import com.amazon.primevideo.advertising.GoogleAdvertisingProperties_Factory;
import com.amazon.primevideo.di.AndroidTvMainActivityComponent;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvideApplicationColorFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvideApplicationIDFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvideApplicationIconFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvideApplicationRecommendationNameFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvideDefaultDTIDFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvideDeviceAttestationAttestorFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvideDtidAcmEndpointFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvideIgniteAssetsArchiveNameFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvidePearRecommendationsEnabledFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvidePearWatchNextEnabledFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvidePersonalisedRecommendationsPlacementIdMapFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvideRecommendationDefaultChannelIdFactory;
import com.amazon.primevideo.di.IgnitionApplicationModule_Companion_ProvideWatchNextPlacementIdMapFactory;
import com.amazon.primevideo.nativebilling.BillingClientProvider;
import com.amazon.primevideo.nativebilling.BillingClientProvider_Factory;
import com.amazon.primevideo.nativebilling.BillingClientStatusProvider;
import com.amazon.primevideo.nativebilling.BillingClientStatusProvider_Factory;
import com.amazon.primevideo.nativebilling.BillingProvider;
import com.amazon.primevideo.nativebilling.BillingProvider_Factory;
import com.amazon.primevideo.nativebilling.BillingServiceInitializer;
import com.amazon.primevideo.nativebilling.BillingServiceInitializer_Factory;
import com.amazon.primevideo.nativebilling.GoogleBillingProperties;
import com.amazon.primevideo.nativebilling.GoogleBillingProperties_Factory;
import com.amazon.primevideo.nativebilling.ProductDetailParamsFactory_Factory;
import com.amazon.primevideo.receiver.LocaleChangeReceiver;
import com.amazon.primevideo.service.UpdateRecommendationsJobService;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.sony.dtv.picturequalitycontrol.PictureQualityController;
import dagger.internal.DaggerGenerated;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapFactory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetFactory;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@DaggerGenerated
public final class DaggerPrimeVideoApplicationComponent {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AndroidTvMainActivityComponentBuilder implements AndroidTvMainActivityComponent.Builder {
        public MainActivityModule mainActivityModule;
        public final PrimeVideoApplicationComponentImpl primeVideoApplicationComponentImpl;

        @Override // com.amazon.livingroom.di.MainActivityComponent.Builder
        public MainActivityComponent.Builder mainActivityModule(MainActivityModule mainActivityModule) {
            mainActivityModule.getClass();
            this.mainActivityModule = mainActivityModule;
            return this;
        }

        public AndroidTvMainActivityComponentBuilder(PrimeVideoApplicationComponentImpl primeVideoApplicationComponentImpl) {
            this.primeVideoApplicationComponentImpl = primeVideoApplicationComponentImpl;
        }

        @Override // com.amazon.livingroom.di.MainActivityComponent.Builder
        public AndroidTvMainActivityComponent build() {
            Preconditions.checkBuilderRequirement(this.mainActivityModule, MainActivityModule.class);
            return new AndroidTvMainActivityComponentImpl(this.primeVideoApplicationComponentImpl, this.mainActivityModule);
        }

        @Override // com.amazon.primevideo.di.AndroidTvMainActivityComponent.Builder, com.amazon.livingroom.di.MainActivityComponent.Builder
        public AndroidTvMainActivityComponent.Builder mainActivityModule(MainActivityModule mainActivityModule) {
            mainActivityModule.getClass();
            this.mainActivityModule = mainActivityModule;
            return this;
        }

        @Override // com.amazon.primevideo.di.AndroidTvMainActivityComponent.Builder, com.amazon.livingroom.di.MainActivityComponent.Builder
        public AndroidTvMainActivityComponentBuilder mainActivityModule(MainActivityModule mainActivityModule) {
            mainActivityModule.getClass();
            this.mainActivityModule = mainActivityModule;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AndroidTvMainActivityComponentImpl implements AndroidTvMainActivityComponent {
        public final AndroidTvMainActivityComponentImpl androidTvMainActivityComponentImpl = this;
        public Provider<IgniteExploreByTouchHelper> igniteExploreByTouchHelperProvider;
        public final PrimeVideoApplicationComponentImpl primeVideoApplicationComponentImpl;
        public Provider<SurfaceView> provideIgniteSurfaceViewProvider;

        public AndroidTvMainActivityComponentImpl(PrimeVideoApplicationComponentImpl primeVideoApplicationComponentImpl, MainActivityModule mainActivityModule) {
            this.primeVideoApplicationComponentImpl = primeVideoApplicationComponentImpl;
            initialize(mainActivityModule);
        }

        public final void initialize(MainActivityModule mainActivityModule) {
            MainActivityModule_ProvideIgniteSurfaceViewFactory mainActivityModule_ProvideIgniteSurfaceViewFactory = new MainActivityModule_ProvideIgniteSurfaceViewFactory(mainActivityModule);
            this.provideIgniteSurfaceViewProvider = mainActivityModule_ProvideIgniteSurfaceViewFactory;
            this.igniteExploreByTouchHelperProvider = DoubleCheck.provider((Provider) new IgniteExploreByTouchHelper_Factory(mainActivityModule_ProvideIgniteSurfaceViewFactory, this.primeVideoApplicationComponentImpl.eventHandlerProvider));
        }

        @Override // com.amazon.livingroom.di.MainActivityComponent
        public void inject(MainActivity mainActivity) {
            injectMainActivity(mainActivity);
        }

        @CanIgnoreReturnValue
        public final MainActivity injectMainActivity(MainActivity mainActivity) {
            mainActivity.contextProvider = this.primeVideoApplicationComponentImpl.ignitionContextProvider.get();
            mainActivity.deviceProperties = this.primeVideoApplicationComponentImpl.namedOverridableDeviceProperties();
            mainActivity.localOverridesProvider = this.primeVideoApplicationComponentImpl.localOverridesProvider.get();
            mainActivity.ftvMpbContext = this.primeVideoApplicationComponentImpl.ftvMpbContextProvider.get();
            mainActivity.mpbEngineManager = this.primeVideoApplicationComponentImpl.mediaPipelineBackendEngineManagerProvider.get();
            mainActivity.igniteEventHandler = this.primeVideoApplicationComponentImpl.eventHandlerProvider.get();
            mainActivity.renderer = this.primeVideoApplicationComponentImpl.igniteRendererProvider.get();
            mainActivity.rendererManager = this.primeVideoApplicationComponentImpl.rendererManagerProvider.get();
            mainActivity.accessibilityHelper = this.igniteExploreByTouchHelperProvider.get();
            mainActivity.minervaMetrics = this.primeVideoApplicationComponentImpl.minervaMetricsProvider.get();
            mainActivity.sonyCalibratedModeConnector = this.primeVideoApplicationComponentImpl.sonyCalibratedModeConnectorProvider.get();
            mainActivity.gmbMessageSender = this.primeVideoApplicationComponentImpl.gMBMessageSenderProvider.get();
            PrimeVideoApplicationComponentImpl primeVideoApplicationComponentImpl = this.primeVideoApplicationComponentImpl;
            mainActivity.allowLocalPropertyOverrides = primeVideoApplicationComponentImpl.provideAllowLocalPropertyOverridesProvider;
            ApplicationModule_ProvideActionOverrideDevicePropertiesFactory.provideActionOverrideDeviceProperties(primeVideoApplicationComponentImpl.applicationModule);
            mainActivity.actionOverrideDeviceProperties = BuildConfig.ACTION_OVERRIDE_DEVICE_PROPERTIES;
            mainActivity.deepLinkIntentParser = this.primeVideoApplicationComponentImpl.androidTVDeepLinkIntentParserProvider.get();
            return mainActivity;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public ApplicationModule applicationModule;

        public Builder() {
        }

        public Builder applicationModule(ApplicationModule applicationModule) {
            applicationModule.getClass();
            this.applicationModule = applicationModule;
            return this;
        }

        public PrimeVideoApplicationComponent build() {
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            return new PrimeVideoApplicationComponentImpl(this.applicationModule);
        }

        public Builder(DaggerPrimeVideoApplicationComponentIA daggerPrimeVideoApplicationComponentIA) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PrimeVideoApplicationComponentImpl implements PrimeVideoApplicationComponent {
        public Provider<AndroidTVDeepLinkIntentParser> androidTVDeepLinkIntentParserProvider;
        public Provider<AndroidTextToSpeechEngine> androidTextToSpeechEngineProvider;
        public Provider<AppStartupConfigCache> appStartupConfigCacheProvider;
        public Provider<AppStartupConfigProvider> appStartupConfigProvider;
        public Provider<AppStartupConfigRequester> appStartupConfigRequesterProvider;
        public Provider<ApplicationAccessTokenProvider> applicationAccessTokenProvider;
        public Provider<ApplicationAccessTokenRequester> applicationAccessTokenRequesterProvider;
        public final ApplicationModule applicationModule;
        public Provider applicationPackagePropertiesProvider;
        public Provider<ApplicationSourcePropertiesProvider> applicationSourcePropertiesProvider;
        public Provider<ApplicationVisibilityMonitor> applicationVisibilityMonitorProvider;
        public Provider<AudioFocusManager> audioFocusManagerProvider;
        public Provider<AudioProperties> audioPropertiesProvider;
        public Provider<BillingClientProvider> billingClientProvider;
        public Provider<BillingClientStatusProvider> billingClientStatusProvider;
        public Provider<BillingProvider> billingProvider;
        public Provider<BillingServiceInitializer> billingServiceInitializerProvider;
        public Provider<BootstrapRequestStructureBuilder> bootstrapRequestStructureBuilderProvider;
        public Provider<CachedTarExtractor> cachedTarExtractorProvider;
        public Provider<CodecQuerier> codecQuerierProvider;
        public Provider<DecoderCapabilitiesProvider> decoderCapabilitiesProvider;
        public Provider<DefaultDeviceProperties> defaultDevicePropertiesProvider;
        public Provider<DeviceAttestationService> deviceAttestationServiceProvider;
        public Provider deviceBuildPropertiesProvider;
        public Provider deviceIdProvider;
        public Provider<DeviceLocaleProvider> deviceLocaleProvider;
        public Provider<DisplayInformation> displayInformationProvider;
        public Provider<DisplayModeManager> displayModeManagerProvider;
        public Provider<DisplayModeProperties> displayModePropertiesProvider;
        public Provider displayPropertiesProvider;
        public Provider<DrmProvisioner> drmProvisionerProvider;
        public Provider<DtidCache> dtidCacheProvider;
        public Provider<DtidProvider> dtidProvider;
        public Provider<DtidRequestUriFactory> dtidRequestUriFactoryProvider;
        public Provider<DtidRequester> dtidRequesterProvider;
        public Provider<IgniteRenderer.EventHandler> eventHandlerProvider;
        public Provider<ExitReasonHandler> exitReasonHandlerProvider;
        public Provider<ExternalPlaybackSurfaceRegistry> externalPlaybackSurfaceRegistryProvider;
        public Provider factoryProvider;
        public Provider<InternalPlaybackSurface.Factory> factoryProvider2;
        public Provider<FtvMpbInstance.Factory> factoryProvider3;
        public Provider<FtvMpbApi.Factory> factoryProvider4;
        public Provider<FtvMpbDrmContext.Factory> factoryProvider5;
        public C0052FtvMpbApi_Factory ftvMpbApiProvider;
        public Provider<FtvMpbContext> ftvMpbContextProvider;
        public C0053FtvMpbDrmContext_Factory ftvMpbDrmContextProvider;
        public C0054FtvMpbInstance_Factory ftvMpbInstanceProvider;
        public Provider<GMBMessageProcessor> gMBMessageProcessorProvider;
        public Provider<GMBMessageSender> gMBMessageSenderProvider;
        public Provider getAppStartupConfigUriFactoryProvider;
        public Provider<GoogleAdvertisingProperties> googleAdvertisingPropertiesProvider;
        public Provider<GoogleBillingProperties> googleBillingPropertiesProvider;
        public Provider<HdcpChecker> hdcpCheckerProvider;
        public Provider<HdcpVersionProvider> hdcpVersionProvider;
        public Provider<IgniteAllocator> igniteAllocatorProvider;
        public Provider<IgniteDevicePropertiesProvider> igniteDevicePropertiesProvider;
        public Provider<IgniteMigrationManager> igniteMigrationManagerProvider;
        public Provider<IgniteRenderer> igniteRendererProvider;
        public Provider<IgnitionContextProvider> ignitionContextProvider;
        public C0055InternalPlaybackSurface_Factory internalPlaybackSurfaceProvider;
        public Provider<JemallocAllocatorMetricRecorder> jemallocAllocatorMetricRecorderProvider;
        public Provider<LaunchReasonIntentParser> launchReasonIntentParserProvider;
        public Provider<LifecycleBoundHandler> lifecycleBoundHandlerProvider;
        public Provider<LocalOverridesProvider> localOverridesProvider;
        public Provider<LocalStorage> localStorageProvider;
        public Provider<MapSqliteHelper> mapSqliteHelperProvider;
        public Provider<MaxVideoResolutionProvider> maxVideoResolutionProvider;
        public Provider<MediaPipelineBackendEngineManager> mediaPipelineBackendEngineManagerProvider;
        public C0056MediaPipelineBackendEngine_Factory mediaPipelineBackendEngineProvider;
        public Provider<MediaSessionAdapter> mediaSessionAdapterProvider;
        public Provider<MediaSessionCallback> mediaSessionCallbackProvider;
        public Provider<MetricsRecorder> metricsRecorderProvider;
        public Provider<MinervaMetrics> minervaMetricsProvider;
        public Provider<Map<String, String>> namedMapOfStringAndStringProvider;
        public Provider<NativeAllocatorMessageHandler> nativeAllocatorMessageHandlerProvider;
        public Provider<NativeThreadInitializer> nativeThreadInitializerProvider;
        public Provider<NetworkProperties> networkPropertiesProvider;
        public Provider<OperatingSystemProperties> operatingSystemPropertiesProvider;
        public Provider<PearAccessTokenProvider> pearAccessTokenProvider;
        public Provider<PearRecommendationFlowController> pearRecommendationFlowControllerProvider;
        public Provider<PearRecommendationServiceInitializer> pearRecommendationServiceInitializerProvider;
        public Provider<PearResponseParser> pearResponseParserProvider;
        public Provider<PearUpdateStructure> pearUpdateStructureProvider;
        public Provider<PearUriBuilder> pearUriBuilderProvider;
        public Provider<PersonalisedRecommendationPlacement> personalisedRecommendationPlacementProvider;
        public final PrimeVideoApplicationComponentImpl primeVideoApplicationComponentImpl = this;
        public Provider<ActivityManager> provideActivityManagerProvider;
        public Provider<Boolean> provideAllowLocalPropertyOverridesProvider;
        public Provider<Context> provideApplicationContextProvider;
        public Provider<String> provideApplicationNameProvider;
        public Provider<String> provideApplicationPackageNameProvider;
        public Provider<AudioManager> provideAudioManagerProvider;
        public Provider<RequestQueue> provideBackgroundDeliveryRequestQueueProvider;
        public Provider<ContentResolver> provideContentResolverProvider;
        public Provider<DeviceAttestationService.Attestor> provideDeviceAttestationAttestorProvider;
        public Provider<DisplayManager> provideDisplayManagerProvider;
        public Provider<DrmKeyStatusNotifier> provideDrmKeyStatusNotifierProvider;
        public Provider<SharedPreferences> provideDtidSharedPreferencesProvider;
        public Provider<HurlStack> provideHurlStackProvider;
        public Provider<String> provideIgniteAssetsArchiveHashProvider;
        public Provider<File> provideIgniteDataDirProvider;
        public Provider<String> provideIgniteExtractedAssetsHashKeyProvider;
        public Provider<String[]> provideIgnitionXStartupArgumentsProvider;
        public Provider<ExpressionEvaluator> provideLocalExpressionEvaluatorProvider;
        public Provider<OverridableDeviceProperties> provideLocallyOverridableDevicePropertiesProvider;
        public Provider<ComponentName> provideMainActivityNameProvider;
        public Provider<Handler> provideMainHandlerProvider;
        public Provider<AmazonMinerva> provideMinervaClientProvider;
        public Provider<PackageManager> providePackageManagerProvider;
        public Provider<PictureQualityController> providePictureQualityControllerProvider;
        public Provider<PlatformDeviceProperties> providePlatformDevicePropertiesProvider;
        public Provider<List<PlatformProperty<?>>> providePlatformPropertiesProvider;
        public Provider<PreviewChannelHelper> providePreviewChannelHelperProvider;
        public Provider<PearPlacement> provideRecommendationPearPlacementProvider;
        public Provider<RecommendationPublisher> provideRecommendationPublisherProvider;
        public Provider<SharedPreferences> provideRecommendationsSharedPreferencesProvider;
        public Provider<ExpressionEvaluator> provideRemoteExpressionEvaluatorProvider;
        public Provider<OverridableDeviceProperties> provideRemotelyOverridableDevicePropertiesProvider;
        public Provider<RequestQueue> provideRequestQueueProvider;
        public Provider<SharedPreferences> provideRequestStructureContentProviderPreferenceProvider;
        public Provider provideSecureSslSocketFactoryProvider;
        public Provider<PearPlacement> provideWatchNextPearPlacementProvider;
        public Provider<WindowManager> provideWindowManagerProvider;
        public Provider<RecommendationHandler> recommendationHandlerProvider;
        public Provider<RecommendationRequestDispatcher> recommendationRequestDispatcherProvider;
        public Provider<RecommendationUpdater> recommendationUpdaterProvider;
        public Provider<RecommendationsMetricRecorder> recommendationsMetricRecorderProvider;
        public Provider<RecommendationsPropertiesProvider> recommendationsPropertiesProvider;
        public Provider<RecommendationsScheduler> recommendationsSchedulerProvider;
        public Provider<RefreshTokenParser> refreshTokenParserProvider;
        public Provider<RefreshTokenProvider> refreshTokenProvider;
        public Provider<RemoteOverridesProvider> remoteOverridesProvider;
        public Provider<RendererManager> rendererManagerProvider;
        public Provider<RequestStructureContentProviderManager> requestStructureContentProviderManagerProvider;
        public Provider<Set<PearPlacement>> setOfPearPlacementProvider;
        public Provider<Set<ServiceInitializer>> setOfServiceInitializerProvider;
        public Provider<SonyCalibratedModeConnector> sonyCalibratedModeConnectorProvider;
        public Provider<SonyCalibratedModeController> sonyCalibratedModeControllerProvider;
        public Provider<SonyCalibratedModePropertiesProvider> sonyCalibratedModePropertiesProvider;
        public Provider systemPropertiesProvider;
        public Provider<TextToSpeechStatusProvider> textToSpeechStatusProvider;
        public Provider<TifOverlaySurfaceHolderCallbackProvider> tifOverlaySurfaceHolderCallbackProvider;
        public Provider<VideoCapabilitiesProvider> videoCapabilitiesProvider;
        public Provider<VoiceService> voiceServiceProvider;
        public Provider<WakeLocker> wakeLockerProvider;
        public Provider<WatchNextDatabaseAdapter> watchNextDatabaseAdapterProvider;
        public Provider<WatchNextHandler> watchNextHandlerProvider;
        public Provider<WatchNextParser> watchNextParserProvider;
        public Provider<WatchNextPlacement> watchNextPlacementProvider;
        public Provider<WatchNextPublisher> watchNextPublisherProvider;
        public Provider<WidevineCapabilitiesProvider> widevineCapabilitiesProvider;

        public PrimeVideoApplicationComponentImpl(ApplicationModule applicationModule) {
            this.applicationModule = applicationModule;
            initialize(applicationModule);
            initialize2(applicationModule);
            initialize3(applicationModule);
            initialize4(applicationModule);
            initialize5(applicationModule);
            initialize6(applicationModule);
        }

        public BootstrapRequestStructureBuilder bootstrapRequestStructureBuilder() {
            return new BootstrapRequestStructureBuilder(namedOverridableDeviceProperties());
        }

        public final void initialize(ApplicationModule applicationModule) {
            this.provideApplicationContextProvider = new ApplicationModule_ProvideApplicationContextFactory(applicationModule);
            Provider provider = DoubleCheck.provider((Provider) VolleyModule_ProvideSecureSslSocketFactoryFactory.InstanceHolder.INSTANCE);
            this.provideSecureSslSocketFactoryProvider = provider;
            VolleyModule_ProvideHurlStackFactory volleyModule_ProvideHurlStackFactory = new VolleyModule_ProvideHurlStackFactory(provider);
            this.provideHurlStackProvider = volleyModule_ProvideHurlStackFactory;
            this.provideBackgroundDeliveryRequestQueueProvider = DoubleCheck.provider((Provider) new VolleyModule_ProvideBackgroundDeliveryRequestQueueFactory(this.provideApplicationContextProvider, volleyModule_ProvideHurlStackFactory));
            this.googleAdvertisingPropertiesProvider = DoubleCheck.provider((Provider) new GoogleAdvertisingProperties_Factory(this.provideApplicationContextProvider));
            Provider provider2 = DoubleCheck.provider((Provider) SystemProperties_Factory.InstanceHolder.INSTANCE);
            this.systemPropertiesProvider = provider2;
            this.deviceBuildPropertiesProvider = DoubleCheck.provider((Provider) new DeviceBuildProperties_Factory(provider2));
            this.mapSqliteHelperProvider = DoubleCheck.provider((Provider) new MapSqliteHelper_Factory(this.provideApplicationContextProvider));
            this.metricsRecorderProvider = DoubleCheck.provider((Provider) MetricsRecorder_Factory.InstanceHolder.INSTANCE);
            ApplicationModule_ProvideIgniteDataDirFactory applicationModule_ProvideIgniteDataDirFactory = new ApplicationModule_ProvideIgniteDataDirFactory(applicationModule);
            this.provideIgniteDataDirProvider = applicationModule_ProvideIgniteDataDirFactory;
            Provider<LocalStorage> provider3 = DoubleCheck.provider((Provider) new LocalStorage_Factory(applicationModule_ProvideIgniteDataDirFactory));
            this.localStorageProvider = provider3;
            Provider<Context> provider4 = this.provideApplicationContextProvider;
            CoreModule_Companion_ProvideDtidSharedPreferencesFactory coreModule_Companion_ProvideDtidSharedPreferencesFactory = new CoreModule_Companion_ProvideDtidSharedPreferencesFactory(provider4);
            this.provideDtidSharedPreferencesProvider = coreModule_Companion_ProvideDtidSharedPreferencesFactory;
            this.deviceIdProvider = DoubleCheck.provider((Provider) new DeviceIdProvider_Factory(provider4, this.mapSqliteHelperProvider, this.metricsRecorderProvider, provider3, coreModule_Companion_ProvideDtidSharedPreferencesFactory));
            IgnitionApplicationModule_Companion_ProvideDefaultDTIDFactory ignitionApplicationModule_Companion_ProvideDefaultDTIDFactory = IgnitionApplicationModule_Companion_ProvideDefaultDTIDFactory.InstanceHolder.INSTANCE;
            Provider<DtidRequestUriFactory> provider5 = DoubleCheck.provider((Provider) new DtidRequestUriFactory_Factory(ignitionApplicationModule_Companion_ProvideDefaultDTIDFactory, IgnitionApplicationModule_Companion_ProvideDtidAcmEndpointFactory.InstanceHolder.INSTANCE, TerminatorIdProvider_Factory.InstanceHolder.INSTANCE));
            this.dtidRequestUriFactoryProvider = provider5;
            this.dtidRequesterProvider = DoubleCheck.provider((Provider) new DtidRequester_Factory(provider5, this.metricsRecorderProvider, this.provideBackgroundDeliveryRequestQueueProvider, CoreModule_Companion_ProvideDtidRequestRetryPolicyFactory.InstanceHolder.INSTANCE));
            Provider<DtidCache> provider6 = DoubleCheck.provider((Provider) new DtidCache_Factory(this.provideDtidSharedPreferencesProvider));
            this.dtidCacheProvider = provider6;
            this.dtidProvider = DoubleCheck.provider((Provider) new DtidProvider_Factory(this.dtidRequesterProvider, provider6, this.metricsRecorderProvider, ignitionApplicationModule_Companion_ProvideDefaultDTIDFactory));
            Provider<Context> provider7 = this.provideApplicationContextProvider;
            CoreModule_Companion_ProvidePackageManagerFactory coreModule_Companion_ProvidePackageManagerFactory = new CoreModule_Companion_ProvidePackageManagerFactory(provider7);
            this.providePackageManagerProvider = coreModule_Companion_ProvidePackageManagerFactory;
            this.applicationPackagePropertiesProvider = DoubleCheck.provider((Provider) new ApplicationPackagePropertiesProvider_Factory(provider7, coreModule_Companion_ProvidePackageManagerFactory));
            Provider<Context> provider8 = this.provideApplicationContextProvider;
            this.provideWindowManagerProvider = new CoreModule_Companion_ProvideWindowManagerFactory(provider8);
            this.provideDisplayManagerProvider = new CoreModule_Companion_ProvideDisplayManagerFactory(provider8);
            this.maxVideoResolutionProvider = DoubleCheck.provider((Provider) new MaxVideoResolutionProvider_Factory(this.systemPropertiesProvider));
            this.gMBMessageSenderProvider = DoubleCheck.provider((Provider) GMBMessageSender_Factory.InstanceHolder.INSTANCE);
            Provider<Handler> provider9 = DoubleCheck.provider((Provider) new CoreModule_Companion_ProvideMainHandlerFactory(this.provideApplicationContextProvider));
            this.provideMainHandlerProvider = provider9;
            this.displayPropertiesProvider = DoubleCheck.provider((Provider) new DisplayPropertiesProvider_Factory(this.provideWindowManagerProvider, this.provideDisplayManagerProvider, this.providePackageManagerProvider, this.systemPropertiesProvider, this.maxVideoResolutionProvider, this.provideApplicationContextProvider, this.gMBMessageSenderProvider, provider9));
        }

        public final void initialize2(ApplicationModule applicationModule) {
            Provider<DecoderCapabilitiesProvider> provider = DoubleCheck.provider((Provider) DecoderCapabilitiesProvider_Factory.InstanceHolder.INSTANCE);
            this.decoderCapabilitiesProvider = provider;
            this.videoCapabilitiesProvider = DoubleCheck.provider((Provider) new VideoCapabilitiesProvider_Factory(this.displayPropertiesProvider, provider));
            Provider<ContentResolver> provider2 = DoubleCheck.provider((Provider) new CoreModule_Companion_ProvideContentResolverFactory(this.provideApplicationContextProvider));
            this.provideContentResolverProvider = provider2;
            this.displayModePropertiesProvider = DoubleCheck.provider((Provider) new DisplayModeProperties_Factory(provider2));
            this.networkPropertiesProvider = DoubleCheck.provider((Provider) new NetworkProperties_Factory(this.provideApplicationContextProvider));
            Provider<Context> provider3 = this.provideApplicationContextProvider;
            CoreModule_Companion_ProvideAudioManagerFactory coreModule_Companion_ProvideAudioManagerFactory = new CoreModule_Companion_ProvideAudioManagerFactory(provider3);
            this.provideAudioManagerProvider = coreModule_Companion_ProvideAudioManagerFactory;
            this.audioPropertiesProvider = DoubleCheck.provider((Provider) new AudioProperties_Factory(provider3, coreModule_Companion_ProvideAudioManagerFactory));
            this.operatingSystemPropertiesProvider = DoubleCheck.provider((Provider) OperatingSystemProperties_Factory.InstanceHolder.INSTANCE);
            Provider<Context> provider4 = this.provideApplicationContextProvider;
            this.applicationSourcePropertiesProvider = new ApplicationSourcePropertiesProvider_Factory(provider4, this.providePackageManagerProvider);
            Provider<BillingClientProvider> provider5 = DoubleCheck.provider((Provider) new BillingClientProvider_Factory(provider4));
            this.billingClientProvider = provider5;
            Provider<BillingClientStatusProvider> provider6 = DoubleCheck.provider((Provider) new BillingClientStatusProvider_Factory(provider5, this.provideMainHandlerProvider, this.metricsRecorderProvider));
            this.billingClientStatusProvider = provider6;
            this.googleBillingPropertiesProvider = DoubleCheck.provider((Provider) new GoogleBillingProperties_Factory(provider6));
            Provider<PictureQualityController> provider7 = DoubleCheck.provider((Provider) CoreModule_Companion_ProvidePictureQualityControllerFactory.InstanceHolder.INSTANCE);
            this.providePictureQualityControllerProvider = provider7;
            Provider<SonyCalibratedModeConnector> provider8 = DoubleCheck.provider((Provider) new SonyCalibratedModeConnector_Factory(this.provideApplicationContextProvider, provider7, this.metricsRecorderProvider));
            this.sonyCalibratedModeConnectorProvider = provider8;
            SonyCalibratedModePropertiesProvider_Factory sonyCalibratedModePropertiesProvider_Factory = new SonyCalibratedModePropertiesProvider_Factory(provider8);
            this.sonyCalibratedModePropertiesProvider = sonyCalibratedModePropertiesProvider_Factory;
            RecommendationsPropertiesProvider_Factory recommendationsPropertiesProvider_Factory = new RecommendationsPropertiesProvider_Factory(IgnitionApplicationModule_Companion_ProvidePearRecommendationsEnabledFactory.InstanceHolder.INSTANCE, IgnitionApplicationModule_Companion_ProvidePearWatchNextEnabledFactory.InstanceHolder.INSTANCE);
            this.recommendationsPropertiesProvider = recommendationsPropertiesProvider_Factory;
            Provider<GoogleAdvertisingProperties> provider9 = this.googleAdvertisingPropertiesProvider;
            Provider provider10 = this.deviceBuildPropertiesProvider;
            Provider provider11 = this.deviceIdProvider;
            Provider<DtidProvider> provider12 = this.dtidProvider;
            Provider provider13 = this.applicationPackagePropertiesProvider;
            Provider<VideoCapabilitiesProvider> provider14 = this.videoCapabilitiesProvider;
            Provider<DisplayModeProperties> provider15 = this.displayModePropertiesProvider;
            Provider<NetworkProperties> provider16 = this.networkPropertiesProvider;
            Provider<AudioProperties> provider17 = this.audioPropertiesProvider;
            Provider<OperatingSystemProperties> provider18 = this.operatingSystemPropertiesProvider;
            Provider<ApplicationSourcePropertiesProvider> provider19 = this.applicationSourcePropertiesProvider;
            Provider<GoogleBillingProperties> provider20 = this.googleBillingPropertiesProvider;
            TerminatorIdProvider_Factory terminatorIdProvider_Factory = TerminatorIdProvider_Factory.InstanceHolder.INSTANCE;
            Provider<DefaultDeviceProperties> provider21 = DoubleCheck.provider((Provider) new DefaultDeviceProperties_Factory(provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, terminatorIdProvider_Factory, sonyCalibratedModePropertiesProvider_Factory, recommendationsPropertiesProvider_Factory));
            this.defaultDevicePropertiesProvider = provider21;
            ApplicationModule_ProvidePlatformPropertiesFactory applicationModule_ProvidePlatformPropertiesFactory = new ApplicationModule_ProvidePlatformPropertiesFactory(applicationModule);
            this.providePlatformPropertiesProvider = applicationModule_ProvidePlatformPropertiesFactory;
            this.providePlatformDevicePropertiesProvider = new CoreModule_Companion_ProvidePlatformDevicePropertiesFactory(provider21, applicationModule_ProvidePlatformPropertiesFactory);
            Provider<DeviceLocaleProvider> provider22 = DoubleCheck.provider((Provider) DeviceLocaleProvider_Factory.InstanceHolder.INSTANCE);
            this.deviceLocaleProvider = provider22;
            this.getAppStartupConfigUriFactoryProvider = DoubleCheck.provider((Provider) new GetAppStartupConfigUriFactory_Factory(this.providePlatformDevicePropertiesProvider, terminatorIdProvider_Factory, provider22, CoreModule_Companion_ProvideSupportedLocaleLanguageTagsFactory.InstanceHolder.INSTANCE));
            this.appStartupConfigCacheProvider = DoubleCheck.provider((Provider) new AppStartupConfigCache_Factory(this.provideApplicationContextProvider));
            CoreModule_Companion_ProvideApplicationPackageNameFactory coreModule_Companion_ProvideApplicationPackageNameFactory = new CoreModule_Companion_ProvideApplicationPackageNameFactory(this.provideApplicationContextProvider);
            this.provideApplicationPackageNameProvider = coreModule_Companion_ProvideApplicationPackageNameFactory;
            this.applicationAccessTokenRequesterProvider = DoubleCheck.provider((Provider) new ApplicationAccessTokenRequester_Factory(this.provideBackgroundDeliveryRequestQueueProvider, coreModule_Companion_ProvideApplicationPackageNameFactory));
            this.refreshTokenParserProvider = DoubleCheck.provider((Provider) RefreshTokenParser_Factory.InstanceHolder.INSTANCE);
        }

        public final void initialize3(ApplicationModule applicationModule) {
            Provider<RefreshTokenProvider> provider = DoubleCheck.provider((Provider) new RefreshTokenProvider_Factory(this.localStorageProvider, this.refreshTokenParserProvider));
            this.refreshTokenProvider = provider;
            Provider<ApplicationAccessTokenProvider> provider2 = DoubleCheck.provider((Provider) new ApplicationAccessTokenProvider_Factory(this.provideApplicationContextProvider, this.applicationAccessTokenRequesterProvider, provider));
            this.applicationAccessTokenProvider = provider2;
            this.appStartupConfigRequesterProvider = DoubleCheck.provider((Provider) new AppStartupConfigRequester_Factory(this.provideBackgroundDeliveryRequestQueueProvider, this.getAppStartupConfigUriFactoryProvider, CoreModule_Companion_ProvideGascRetryPolicyFactory.InstanceHolder.INSTANCE, this.appStartupConfigCacheProvider, this.metricsRecorderProvider, provider2, this.refreshTokenProvider));
            this.provideMinervaClientProvider = DoubleCheck.provider((Provider) new CoreModule_Companion_ProvideMinervaClientFactory(this.provideApplicationContextProvider, this.providePlatformDevicePropertiesProvider));
            Provider<AppStartupConfigProvider> provider3 = DoubleCheck.provider((Provider) new AppStartupConfigProvider_Factory(this.appStartupConfigRequesterProvider, this.appStartupConfigCacheProvider, this.refreshTokenProvider));
            this.appStartupConfigProvider = provider3;
            Provider<RemoteOverridesProvider> provider4 = DoubleCheck.provider((Provider) new RemoteOverridesProvider_Factory(this.provideApplicationContextProvider, provider3, this.metricsRecorderProvider));
            this.remoteOverridesProvider = provider4;
            DelegateFactory delegateFactory = new DelegateFactory();
            this.provideLocallyOverridableDevicePropertiesProvider = delegateFactory;
            CoreModule_Companion_ProvideRemoteExpressionEvaluatorFactory coreModule_Companion_ProvideRemoteExpressionEvaluatorFactory = new CoreModule_Companion_ProvideRemoteExpressionEvaluatorFactory(delegateFactory, this.defaultDevicePropertiesProvider);
            this.provideRemoteExpressionEvaluatorProvider = coreModule_Companion_ProvideRemoteExpressionEvaluatorFactory;
            this.provideRemotelyOverridableDevicePropertiesProvider = new CoreModule_Companion_ProvideRemotelyOverridableDevicePropertiesFactory(this.providePlatformDevicePropertiesProvider, provider4, coreModule_Companion_ProvideRemoteExpressionEvaluatorFactory);
            Provider<LocalOverridesProvider> provider5 = DoubleCheck.provider((Provider) new LocalOverridesProvider_Factory(this.provideApplicationContextProvider));
            this.localOverridesProvider = provider5;
            Provider<OverridableDeviceProperties> provider6 = this.provideLocallyOverridableDevicePropertiesProvider;
            Provider<OverridableDeviceProperties> provider7 = this.provideRemotelyOverridableDevicePropertiesProvider;
            CoreModule_Companion_ProvideLocalExpressionEvaluatorFactory coreModule_Companion_ProvideLocalExpressionEvaluatorFactory = new CoreModule_Companion_ProvideLocalExpressionEvaluatorFactory(provider6, provider7);
            this.provideLocalExpressionEvaluatorProvider = coreModule_Companion_ProvideLocalExpressionEvaluatorFactory;
            DelegateFactory.setDelegate((Provider) provider6, (Provider) new CoreModule_Companion_ProvideLocallyOverridableDevicePropertiesFactory(provider7, provider5, coreModule_Companion_ProvideLocalExpressionEvaluatorFactory));
            Provider<MinervaMetrics> provider8 = DoubleCheck.provider((Provider) new MinervaMetrics_Factory(this.provideMinervaClientProvider, this.provideLocallyOverridableDevicePropertiesProvider, this.metricsRecorderProvider));
            this.minervaMetricsProvider = provider8;
            this.recommendationsMetricRecorderProvider = DoubleCheck.provider((Provider) new RecommendationsMetricRecorder_Factory(provider8));
            IgnitionApplicationModule_Companion_ProvideApplicationRecommendationNameFactory ignitionApplicationModule_Companion_ProvideApplicationRecommendationNameFactory = IgnitionApplicationModule_Companion_ProvideApplicationRecommendationNameFactory.InstanceHolder.INSTANCE;
            this.pearResponseParserProvider = new PearResponseParser_Factory(ignitionApplicationModule_Companion_ProvideApplicationRecommendationNameFactory);
            CoreModule_Companion_ProvideRecommendationsSharedPreferencesFactory coreModule_Companion_ProvideRecommendationsSharedPreferencesFactory = new CoreModule_Companion_ProvideRecommendationsSharedPreferencesFactory(this.provideApplicationContextProvider);
            this.provideRecommendationsSharedPreferencesProvider = coreModule_Companion_ProvideRecommendationsSharedPreferencesFactory;
            this.pearUpdateStructureProvider = DoubleCheck.provider((Provider) new PearUpdateStructure_Factory(coreModule_Companion_ProvideRecommendationsSharedPreferencesFactory));
            Provider<RequestQueue> provider9 = DoubleCheck.provider((Provider) new VolleyModule_ProvideRequestQueueFactory(this.provideApplicationContextProvider, this.provideHurlStackProvider));
            this.provideRequestQueueProvider = provider9;
            this.recommendationRequestDispatcherProvider = new RecommendationRequestDispatcher_Factory(provider9);
            MapFactory.Builder builder = MapFactory.builder(2);
            builder.put(Names.PEAR_PERSONALISED_RECOMMENDATIONS_PLACEMENT_KEY, (Provider) IgnitionApplicationModule_Companion_ProvidePersonalisedRecommendationsPlacementIdMapFactory.InstanceHolder.INSTANCE);
            builder.put(Names.PEAR_WATCH_NEXT_PLACEMENT_KEY, (Provider) IgnitionApplicationModule_Companion_ProvideWatchNextPlacementIdMapFactory.InstanceHolder.INSTANCE);
            MapFactory mapFactoryBuild = builder.build();
            this.namedMapOfStringAndStringProvider = mapFactoryBuild;
            Provider<PearUriBuilder> provider10 = DoubleCheck.provider((Provider) new PearUriBuilder_Factory(this.provideLocallyOverridableDevicePropertiesProvider, TerminatorIdProvider_Factory.InstanceHolder.INSTANCE, mapFactoryBuild, ignitionApplicationModule_Companion_ProvideApplicationRecommendationNameFactory));
            this.pearUriBuilderProvider = provider10;
            Provider<PearAccessTokenProvider> provider11 = DoubleCheck.provider((Provider) new PearAccessTokenProvider_Factory(this.pearUpdateStructureProvider, this.recommendationRequestDispatcherProvider, provider10));
            this.pearAccessTokenProvider = provider11;
            this.pearRecommendationFlowControllerProvider = new PearRecommendationFlowController_Factory(this.recommendationRequestDispatcherProvider, this.pearUpdateStructureProvider, provider11, this.pearUriBuilderProvider);
            this.provideMainActivityNameProvider = DoubleCheck.provider((Provider) new IgnitionApplicationModule_Companion_ProvideMainActivityNameFactory(this.provideApplicationPackageNameProvider));
            this.provideApplicationNameProvider = new IgnitionApplicationModule_Companion_ProvideApplicationNameFactory(this.provideApplicationContextProvider);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final void initialize4(ApplicationModule applicationModule) {
            this.provideRecommendationPublisherProvider = DoubleCheck.provider((Provider) new RecommendationModule_ProvideRecommendationPublisherFactory(this.provideApplicationContextProvider, this.provideMainActivityNameProvider, this.provideApplicationNameProvider, IgnitionApplicationModule_Companion_ProvideApplicationIconFactory.InstanceHolder.INSTANCE, IgnitionApplicationModule_Companion_ProvideApplicationColorFactory.InstanceHolder.INSTANCE, IgnitionApplicationModule_Companion_ProvideRecommendationDefaultChannelIdFactory.InstanceHolder.INSTANCE));
            Provider<RecommendationHandler> provider = DoubleCheck.provider((Provider) new RecommendationHandler_Factory(this.provideApplicationContextProvider));
            this.recommendationHandlerProvider = provider;
            Provider<OverridableDeviceProperties> provider2 = this.provideLocallyOverridableDevicePropertiesProvider;
            Provider<RecommendationsMetricRecorder> provider3 = this.recommendationsMetricRecorderProvider;
            RecommendationUpdaterPeriodicWorkRequestBuilder_Factory recommendationUpdaterPeriodicWorkRequestBuilder_Factory = RecommendationUpdaterPeriodicWorkRequestBuilder_Factory.InstanceHolder.INSTANCE;
            Provider<Context> provider4 = this.provideApplicationContextProvider;
            this.recommendationsSchedulerProvider = new RecommendationsScheduler_Factory(provider2, provider3, provider, recommendationUpdaterPeriodicWorkRequestBuilder_Factory, provider4);
            this.watchNextDatabaseAdapterProvider = new WatchNextDatabaseAdapter_Factory(provider4);
            Provider<PreviewChannelHelper> provider5 = DoubleCheck.provider((Provider) new WatchNextModule_ProvidePreviewChannelHelperFactory(provider4));
            this.providePreviewChannelHelperProvider = provider5;
            Provider<Context> provider6 = this.provideApplicationContextProvider;
            Provider<MinervaMetrics> provider7 = this.minervaMetricsProvider;
            WatchNextPublisher_Factory watchNextPublisher_Factory = new WatchNextPublisher_Factory(provider6, provider7, this.watchNextDatabaseAdapterProvider, provider5);
            this.watchNextPublisherProvider = watchNextPublisher_Factory;
            WatchNextPlacement_Factory watchNextPlacement_Factory = new WatchNextPlacement_Factory(watchNextPublisher_Factory, provider7, this.namedMapOfStringAndStringProvider, PearWatchNextProgramBuilder_Factory.InstanceHolder.INSTANCE, this.provideLocallyOverridableDevicePropertiesProvider);
            this.watchNextPlacementProvider = watchNextPlacement_Factory;
            this.provideWatchNextPearPlacementProvider = DoubleCheck.provider((Provider) watchNextPlacement_Factory);
            PersonalisedRecommendationPlacement_Factory personalisedRecommendationPlacement_Factory = new PersonalisedRecommendationPlacement_Factory(this.provideRecommendationPublisherProvider, this.minervaMetricsProvider, this.namedMapOfStringAndStringProvider);
            this.personalisedRecommendationPlacementProvider = personalisedRecommendationPlacement_Factory;
            this.provideRecommendationPearPlacementProvider = DoubleCheck.provider((Provider) personalisedRecommendationPlacement_Factory);
            SetFactory.Builder builder = SetFactory.builder(2, 0);
            builder.individualProviders.add((Provider<T>) this.provideWatchNextPearPlacementProvider);
            builder.individualProviders.add((Provider<T>) this.provideRecommendationPearPlacementProvider);
            SetFactory setFactoryBuild = builder.build();
            this.setOfPearPlacementProvider = setFactoryBuild;
            Provider<PearResponseParser> provider8 = this.pearResponseParserProvider;
            Provider<PearUpdateStructure> provider9 = this.pearUpdateStructureProvider;
            Provider<MinervaMetrics> provider10 = this.minervaMetricsProvider;
            this.recommendationUpdaterProvider = new RecommendationUpdater_Factory(provider8, provider9, provider10, this.pearRecommendationFlowControllerProvider, this.provideLocallyOverridableDevicePropertiesProvider, this.provideRecommendationPublisherProvider, this.recommendationsSchedulerProvider, setFactoryBuild);
            WatchNextParser_Factory watchNextParser_Factory = new WatchNextParser_Factory(this.provideApplicationContextProvider, provider10);
            this.watchNextParserProvider = watchNextParser_Factory;
            this.watchNextHandlerProvider = DoubleCheck.provider((Provider) new WatchNextHandler_Factory(this.watchNextPublisherProvider, watchNextParser_Factory, provider10));
            this.ignitionContextProvider = DoubleCheck.provider((Provider) IgnitionContextProvider_Factory.InstanceHolder.INSTANCE);
            this.tifOverlaySurfaceHolderCallbackProvider = DoubleCheck.provider((Provider) TifOverlaySurfaceHolderCallbackProvider_Factory.InstanceHolder.INSTANCE);
            this.eventHandlerProvider = DoubleCheck.provider((Provider) IgniteRenderer_EventHandler_Factory.InstanceHolder.INSTANCE);
            Provider<Context> provider11 = this.provideApplicationContextProvider;
            IgnitionApplicationModule_Companion_ProvideIgniteAssetsArchiveHashFactory ignitionApplicationModule_Companion_ProvideIgniteAssetsArchiveHashFactory = new IgnitionApplicationModule_Companion_ProvideIgniteAssetsArchiveHashFactory(provider11);
            this.provideIgniteAssetsArchiveHashProvider = ignitionApplicationModule_Companion_ProvideIgniteAssetsArchiveHashFactory;
            ApplicationModule_ProvideIgniteExtractedAssetsHashKeyFactory applicationModule_ProvideIgniteExtractedAssetsHashKeyFactory = new ApplicationModule_ProvideIgniteExtractedAssetsHashKeyFactory(applicationModule);
            this.provideIgniteExtractedAssetsHashKeyProvider = applicationModule_ProvideIgniteExtractedAssetsHashKeyFactory;
            this.cachedTarExtractorProvider = DoubleCheck.provider((Provider) new CachedTarExtractor_Factory(provider11, IgnitionApplicationModule_Companion_ProvideIgniteAssetsArchiveNameFactory.InstanceHolder.INSTANCE, ignitionApplicationModule_Companion_ProvideIgniteAssetsArchiveHashFactory, applicationModule_ProvideIgniteExtractedAssetsHashKeyFactory, this.provideIgniteDataDirProvider));
            this.displayInformationProvider = DoubleCheck.provider((Provider) new DisplayInformation_Factory(this.provideWindowManagerProvider));
            Provider<WidevineCapabilitiesProvider> provider12 = DoubleCheck.provider((Provider) WidevineCapabilitiesProvider_Factory.InstanceHolder.INSTANCE);
            this.widevineCapabilitiesProvider = provider12;
            Provider<HdcpVersionProvider> provider13 = DoubleCheck.provider((Provider) new HdcpVersionProvider_Factory(this.provideApplicationContextProvider, provider12));
            this.hdcpVersionProvider = provider13;
            Provider<HdcpChecker> provider14 = DoubleCheck.provider((Provider) new HdcpChecker_Factory(provider13));
            this.hdcpCheckerProvider = provider14;
            this.igniteDevicePropertiesProvider = DoubleCheck.provider((Provider) new IgniteDevicePropertiesProvider_Factory(this.provideLocallyOverridableDevicePropertiesProvider, this.displayInformationProvider, provider14, this.widevineCapabilitiesProvider));
        }

        public final void initialize5(ApplicationModule applicationModule) {
            this.drmProvisionerProvider = DoubleCheck.provider((Provider) new DrmProvisioner_Factory(this.provideRequestQueueProvider));
            this.applicationVisibilityMonitorProvider = DoubleCheck.provider((Provider) new ApplicationVisibilityMonitor_Factory(this.provideApplicationContextProvider));
            this.textToSpeechStatusProvider = DoubleCheck.provider((Provider) new TextToSpeechStatusProvider_Factory(this.provideApplicationContextProvider));
            Provider<SonyCalibratedModeController> provider = DoubleCheck.provider((Provider) new SonyCalibratedModeController_Factory(this.sonyCalibratedModeConnectorProvider, this.provideLocallyOverridableDevicePropertiesProvider, this.applicationVisibilityMonitorProvider));
            this.sonyCalibratedModeControllerProvider = provider;
            C0056MediaPipelineBackendEngine_Factory c0056MediaPipelineBackendEngine_Factory = new C0056MediaPipelineBackendEngine_Factory(this.provideApplicationContextProvider, this.applicationVisibilityMonitorProvider, this.hdcpCheckerProvider, this.provideLocallyOverridableDevicePropertiesProvider, this.minervaMetricsProvider, this.textToSpeechStatusProvider, provider);
            this.mediaPipelineBackendEngineProvider = c0056MediaPipelineBackendEngine_Factory;
            this.factoryProvider = MediaPipelineBackendEngine_Factory_Impl.createFactoryProvider(c0056MediaPipelineBackendEngine_Factory);
            Provider<GMBMessageProcessor> provider2 = DoubleCheck.provider((Provider) GMBMessageProcessor_Factory.InstanceHolder.INSTANCE);
            this.gMBMessageProcessorProvider = provider2;
            Provider<VoiceService> provider3 = DoubleCheck.provider((Provider) new VoiceService_Factory(provider2, this.gMBMessageSenderProvider, this.provideLocallyOverridableDevicePropertiesProvider, this.minervaMetricsProvider));
            this.voiceServiceProvider = provider3;
            Provider<MediaSessionCallback> provider4 = DoubleCheck.provider((Provider) new MediaSessionCallback_Factory(this.eventHandlerProvider, provider3, this.textToSpeechStatusProvider));
            this.mediaSessionCallbackProvider = provider4;
            this.mediaSessionAdapterProvider = DoubleCheck.provider((Provider) new MediaSessionAdapter_Factory(this.ignitionContextProvider, provider4, this.provideApplicationPackageNameProvider));
            this.audioFocusManagerProvider = DoubleCheck.provider((Provider) new AudioFocusManager_Factory(this.provideAudioManagerProvider, this.mediaSessionCallbackProvider));
            this.wakeLockerProvider = DoubleCheck.provider((Provider) new WakeLocker_Factory(this.ignitionContextProvider, this.provideMainHandlerProvider));
            Provider<OverridableDeviceProperties> provider5 = this.provideLocallyOverridableDevicePropertiesProvider;
            this.codecQuerierProvider = new CodecQuerier_Factory(provider5);
            C0055InternalPlaybackSurface_Factory c0055InternalPlaybackSurface_Factory = new C0055InternalPlaybackSurface_Factory(this.ignitionContextProvider, this.provideMainHandlerProvider, provider5);
            this.internalPlaybackSurfaceProvider = c0055InternalPlaybackSurface_Factory;
            Provider<InternalPlaybackSurface.Factory> providerCreateFactoryProvider = InternalPlaybackSurface_Factory_Impl.createFactoryProvider(c0055InternalPlaybackSurface_Factory);
            this.factoryProvider2 = providerCreateFactoryProvider;
            this.mediaPipelineBackendEngineManagerProvider = DoubleCheck.provider((Provider) new MediaPipelineBackendEngineManager_Factory(this.factoryProvider, this.provideLocallyOverridableDevicePropertiesProvider, this.sonyCalibratedModeControllerProvider, this.mediaSessionAdapterProvider, this.audioFocusManagerProvider, this.wakeLockerProvider, this.codecQuerierProvider, providerCreateFactoryProvider));
            this.lifecycleBoundHandlerProvider = DoubleCheck.provider((Provider) LifecycleBoundHandler_Factory.InstanceHolder.INSTANCE);
            Provider<DrmKeyStatusNotifier> provider6 = DoubleCheck.provider((Provider) CoreModule_Companion_ProvideDrmKeyStatusNotifierFactory.InstanceHolder.INSTANCE);
            this.provideDrmKeyStatusNotifierProvider = provider6;
            this.displayModeManagerProvider = new DisplayModeManager_Factory(this.provideWindowManagerProvider, this.ignitionContextProvider, this.provideMainHandlerProvider, this.hdcpCheckerProvider, this.provideDisplayManagerProvider, DisplayModeMatcher_Factory.InstanceHolder.INSTANCE, this.lifecycleBoundHandlerProvider, this.provideLocallyOverridableDevicePropertiesProvider, provider6);
            Provider<ExternalPlaybackSurfaceRegistry> provider7 = DoubleCheck.provider((Provider) ExternalPlaybackSurfaceRegistry_Factory.InstanceHolder.INSTANCE);
            this.externalPlaybackSurfaceRegistryProvider = provider7;
            C0054FtvMpbInstance_Factory c0054FtvMpbInstance_Factory = new C0054FtvMpbInstance_Factory(this.displayModeManagerProvider, provider7, this.applicationVisibilityMonitorProvider);
            this.ftvMpbInstanceProvider = c0054FtvMpbInstance_Factory;
            Provider<FtvMpbInstance.Factory> providerCreateFactoryProvider2 = FtvMpbInstance_Factory_Impl.createFactoryProvider(c0054FtvMpbInstance_Factory);
            this.factoryProvider3 = providerCreateFactoryProvider2;
            C0052FtvMpbApi_Factory c0052FtvMpbApi_Factory = new C0052FtvMpbApi_Factory(providerCreateFactoryProvider2, this.factoryProvider2, this.wakeLockerProvider, this.audioFocusManagerProvider, this.mediaSessionAdapterProvider);
            this.ftvMpbApiProvider = c0052FtvMpbApi_Factory;
            Provider<FtvMpbApi.Factory> providerCreateFactoryProvider3 = FtvMpbApi_Factory_Impl.createFactoryProvider(c0052FtvMpbApi_Factory);
            this.factoryProvider4 = providerCreateFactoryProvider3;
            this.ftvMpbContextProvider = DoubleCheck.provider((Provider) new FtvMpbContext_Factory(this.provideApplicationContextProvider, providerCreateFactoryProvider3, this.provideLocallyOverridableDevicePropertiesProvider, this.applicationVisibilityMonitorProvider));
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final void initialize6(ApplicationModule applicationModule) {
            this.provideIgnitionXStartupArgumentsProvider = new CoreModule_Companion_ProvideIgnitionXStartupArgumentsFactory(this.provideLocallyOverridableDevicePropertiesProvider, IgnitionApplicationModule_Companion_ProvideApplicationIDFactory.InstanceHolder.INSTANCE);
            Provider<Context> provider = this.provideApplicationContextProvider;
            this.nativeThreadInitializerProvider = new NativeThreadInitializer_Factory(provider);
            this.androidTextToSpeechEngineProvider = new AndroidTextToSpeechEngine_Factory(provider, this.textToSpeechStatusProvider, this.minervaMetricsProvider);
            CoreModule_Companion_ProvideActivityManagerFactory coreModule_Companion_ProvideActivityManagerFactory = new CoreModule_Companion_ProvideActivityManagerFactory(provider);
            this.provideActivityManagerProvider = coreModule_Companion_ProvideActivityManagerFactory;
            this.exitReasonHandlerProvider = new ExitReasonHandler_Factory(provider, coreModule_Companion_ProvideActivityManagerFactory);
            Provider<DeviceAttestationService.Attestor> provider2 = DoubleCheck.provider((Provider) IgnitionApplicationModule_Companion_ProvideDeviceAttestationAttestorFactory.InstanceHolder.INSTANCE);
            this.provideDeviceAttestationAttestorProvider = provider2;
            this.deviceAttestationServiceProvider = DoubleCheck.provider((Provider) new DeviceAttestationService_Factory(provider2));
            this.igniteMigrationManagerProvider = DoubleCheck.provider((Provider) new IgniteMigrationManager_Factory(this.provideApplicationContextProvider));
            Provider<AndroidTVDeepLinkIntentParser> provider3 = DoubleCheck.provider((Provider) AndroidTVDeepLinkIntentParser_Factory.InstanceHolder.INSTANCE);
            this.androidTVDeepLinkIntentParserProvider = provider3;
            this.launchReasonIntentParserProvider = DoubleCheck.provider((Provider) new LaunchReasonIntentParser_Factory(provider3));
            C0053FtvMpbDrmContext_Factory c0053FtvMpbDrmContext_Factory = new C0053FtvMpbDrmContext_Factory(this.applicationVisibilityMonitorProvider);
            this.ftvMpbDrmContextProvider = c0053FtvMpbDrmContext_Factory;
            this.factoryProvider5 = FtvMpbDrmContext_Factory_Impl.createFactoryProvider(c0053FtvMpbDrmContext_Factory);
            this.jemallocAllocatorMetricRecorderProvider = DoubleCheck.provider((Provider) new JemallocAllocatorMetricRecorder_Factory(this.minervaMetricsProvider));
            Provider<IgniteAllocator> provider4 = DoubleCheck.provider((Provider) IgniteAllocator_Factory.InstanceHolder.INSTANCE);
            this.igniteAllocatorProvider = provider4;
            this.nativeAllocatorMessageHandlerProvider = DoubleCheck.provider((Provider) new NativeAllocatorMessageHandler_Factory(this.gMBMessageProcessorProvider, provider4));
            Provider<BillingProvider> provider5 = DoubleCheck.provider((Provider) new BillingProvider_Factory(this.gMBMessageSenderProvider, this.metricsRecorderProvider, this.billingClientProvider, ProductDetailParamsFactory_Factory.InstanceHolder.INSTANCE, this.provideMainHandlerProvider, this.ignitionContextProvider));
            this.billingProvider = provider5;
            this.billingServiceInitializerProvider = DoubleCheck.provider((Provider) new BillingServiceInitializer_Factory(this.gMBMessageProcessorProvider, provider5));
            Provider<Context> provider6 = this.provideApplicationContextProvider;
            CoreModule_Companion_ProvideRequestStructureContentProviderPreferenceFactory coreModule_Companion_ProvideRequestStructureContentProviderPreferenceFactory = new CoreModule_Companion_ProvideRequestStructureContentProviderPreferenceFactory(provider6);
            this.provideRequestStructureContentProviderPreferenceProvider = coreModule_Companion_ProvideRequestStructureContentProviderPreferenceFactory;
            Provider<OverridableDeviceProperties> provider7 = this.provideLocallyOverridableDevicePropertiesProvider;
            BootstrapRequestStructureBuilder_Factory bootstrapRequestStructureBuilder_Factory = new BootstrapRequestStructureBuilder_Factory(provider7);
            this.bootstrapRequestStructureBuilderProvider = bootstrapRequestStructureBuilder_Factory;
            Provider<PearUpdateStructure> provider8 = this.pearUpdateStructureProvider;
            RequestStructureContentProviderManager_Factory requestStructureContentProviderManager_Factory = new RequestStructureContentProviderManager_Factory(provider6, coreModule_Companion_ProvideRequestStructureContentProviderPreferenceFactory, provider7, provider8, bootstrapRequestStructureBuilder_Factory, this.minervaMetricsProvider);
            this.requestStructureContentProviderManagerProvider = requestStructureContentProviderManager_Factory;
            this.pearRecommendationServiceInitializerProvider = DoubleCheck.provider((Provider) new PearRecommendationServiceInitializer_Factory(this.gMBMessageProcessorProvider, this.gMBMessageSenderProvider, provider8, this.recommendationHandlerProvider, this.pearAccessTokenProvider, this.watchNextPublisherProvider, requestStructureContentProviderManager_Factory));
            SetFactory.Builder builder = SetFactory.builder(4, 0);
            builder.individualProviders.add((Provider<T>) this.nativeAllocatorMessageHandlerProvider);
            builder.individualProviders.add((Provider<T>) this.billingServiceInitializerProvider);
            builder.individualProviders.add((Provider<T>) this.pearRecommendationServiceInitializerProvider);
            builder.individualProviders.add((Provider<T>) this.voiceServiceProvider);
            SetFactory setFactoryBuild = builder.build();
            this.setOfServiceInitializerProvider = setFactoryBuild;
            Provider<IgniteRenderer> provider9 = DoubleCheck.provider((Provider) new IgniteRenderer_Factory(this.provideApplicationContextProvider, this.eventHandlerProvider, this.provideIgniteDataDirProvider, this.cachedTarExtractorProvider, this.igniteDevicePropertiesProvider, this.drmProvisionerProvider, this.mediaPipelineBackendEngineManagerProvider, this.ftvMpbContextProvider, this.provideIgnitionXStartupArgumentsProvider, this.provideLocallyOverridableDevicePropertiesProvider, this.nativeThreadInitializerProvider, this.localStorageProvider, this.androidTextToSpeechEngineProvider, this.gMBMessageProcessorProvider, this.gMBMessageSenderProvider, this.exitReasonHandlerProvider, this.deviceAttestationServiceProvider, this.igniteMigrationManagerProvider, this.ignitionContextProvider, this.launchReasonIntentParserProvider, this.provideDrmKeyStatusNotifierProvider, this.factoryProvider5, this.jemallocAllocatorMetricRecorderProvider, setFactoryBuild));
            this.igniteRendererProvider = provider9;
            this.rendererManagerProvider = DoubleCheck.provider((Provider) new RendererManager_Factory(this.ignitionContextProvider, this.provideLocallyOverridableDevicePropertiesProvider, this.tifOverlaySurfaceHolderCallbackProvider, provider9, this.lifecycleBoundHandlerProvider, this.applicationVisibilityMonitorProvider, this.exitReasonHandlerProvider, CoreModule_Companion_ProvideRendererManagerExitCallbackFactory.InstanceHolder.INSTANCE));
            this.provideAllowLocalPropertyOverridesProvider = new ApplicationModule_ProvideAllowLocalPropertyOverridesFactory(applicationModule);
        }

        @Override // com.amazon.livingroom.di.ApplicationComponent
        public void inject(AppStartupConfigCacheRefresher.InternalWorker internalWorker) {
            injectInternalWorker(internalWorker);
        }

        @CanIgnoreReturnValue
        public final BootUpReceiver injectBootUpReceiver(BootUpReceiver bootUpReceiver) {
            bootUpReceiver.recommendationsMetricRecorder = this.recommendationsMetricRecorderProvider.get();
            bootUpReceiver.recommendationsScheduler = recommendationsScheduler();
            bootUpReceiver.requestStructureProviderManager = requestStructureContentProviderManager();
            return bootUpReceiver;
        }

        @CanIgnoreReturnValue
        public final ClearWatchNextWorker injectClearWatchNextWorker(ClearWatchNextWorker clearWatchNextWorker) {
            clearWatchNextWorker.watchNextHandler = this.watchNextHandlerProvider.get();
            return clearWatchNextWorker;
        }

        @CanIgnoreReturnValue
        public final DtidRequestOnStartupWorker injectDtidRequestOnStartupWorker(DtidRequestOnStartupWorker dtidRequestOnStartupWorker) {
            dtidRequestOnStartupWorker.dtidRequester = this.dtidRequesterProvider.get();
            dtidRequestOnStartupWorker.dtidCache = this.dtidCacheProvider.get();
            IgnitionApplicationModule_Companion_ProvideDefaultDTIDFactory.provideDefaultDTID();
            dtidRequestOnStartupWorker.defaultDtid = "A2SNKIF736WF4T";
            dtidRequestOnStartupWorker.deviceProperties = this.defaultDevicePropertiesProvider.get();
            return dtidRequestOnStartupWorker;
        }

        @CanIgnoreReturnValue
        public final AppStartupConfigCacheRefresher.InternalWorker injectInternalWorker(AppStartupConfigCacheRefresher.InternalWorker internalWorker) {
            internalWorker.requester = this.appStartupConfigRequesterProvider.get();
            return internalWorker;
        }

        @CanIgnoreReturnValue
        public final LocaleChangeReceiver injectLocaleChangeReceiver(LocaleChangeReceiver localeChangeReceiver) {
            localeChangeReceiver.recommendationsMetricRecorder = this.recommendationsMetricRecorderProvider.get();
            localeChangeReceiver.recommendationHandler = this.recommendationHandlerProvider.get();
            return localeChangeReceiver;
        }

        @CanIgnoreReturnValue
        public final PeriodicUpdateRecommendationsWorker injectPeriodicUpdateRecommendationsWorker(PeriodicUpdateRecommendationsWorker periodicUpdateRecommendationsWorker) {
            periodicUpdateRecommendationsWorker.recommendationHandler = this.recommendationHandlerProvider.get();
            return periodicUpdateRecommendationsWorker;
        }

        @CanIgnoreReturnValue
        public final PrimeVideoApplication injectPrimeVideoApplication(PrimeVideoApplication primeVideoApplication) {
            primeVideoApplication.rendererManager = this.rendererManagerProvider.get();
            primeVideoApplication.renderer = this.igniteRendererProvider.get();
            primeVideoApplication.applicationAccessTokenProvider = this.applicationAccessTokenProvider.get();
            primeVideoApplication.recommendationsMetricRecorder = DoubleCheck.lazy((Provider) this.recommendationsMetricRecorderProvider);
            primeVideoApplication.recommendationHandler = DoubleCheck.lazy((Provider) this.recommendationHandlerProvider);
            return primeVideoApplication;
        }

        @CanIgnoreReturnValue
        public final RequestStructureContentProvider injectRequestStructureContentProvider(RequestStructureContentProvider requestStructureContentProvider) {
            requestStructureContentProvider.preferences = namedSharedPreferences();
            requestStructureContentProvider.metricsRecorder = this.metricsRecorderProvider.get();
            return requestStructureContentProvider;
        }

        @CanIgnoreReturnValue
        public final ScheduleRecommendationsOnInstallReceiver injectScheduleRecommendationsOnInstallReceiver(ScheduleRecommendationsOnInstallReceiver scheduleRecommendationsOnInstallReceiver) {
            scheduleRecommendationsOnInstallReceiver.recommendationsMetricRecorder = this.recommendationsMetricRecorderProvider.get();
            scheduleRecommendationsOnInstallReceiver.recommendationsScheduler = recommendationsScheduler();
            scheduleRecommendationsOnInstallReceiver.requestStructureProviderManager = requestStructureContentProviderManager();
            return scheduleRecommendationsOnInstallReceiver;
        }

        @CanIgnoreReturnValue
        public final UpdateRecommendationsJobService injectUpdateRecommendationsJobService(UpdateRecommendationsJobService updateRecommendationsJobService) {
            updateRecommendationsJobService.recommendationsMetricRecorder = this.recommendationsMetricRecorderProvider.get();
            return updateRecommendationsJobService;
        }

        @CanIgnoreReturnValue
        public final UpdateRecommendationsWorker injectUpdateRecommendationsWorker(UpdateRecommendationsWorker updateRecommendationsWorker) {
            updateRecommendationsWorker.recommendationsMetricRecorder = this.recommendationsMetricRecorderProvider.get();
            updateRecommendationsWorker.recommendationUpdaterProvider = this.recommendationUpdaterProvider;
            return updateRecommendationsWorker;
        }

        @CanIgnoreReturnValue
        public final UpdateWatchNextWorker injectUpdateWatchNextWorker(UpdateWatchNextWorker updateWatchNextWorker) {
            updateWatchNextWorker.watchNextHandler = this.watchNextHandlerProvider.get();
            return updateWatchNextWorker;
        }

        public OverridableDeviceProperties namedOverridableDeviceProperties() {
            return CoreModule.Companion.provideLocallyOverridableDeviceProperties(namedOverridableDeviceProperties2(), this.localOverridesProvider.get(), this.provideLocalExpressionEvaluatorProvider);
        }

        public OverridableDeviceProperties namedOverridableDeviceProperties2() {
            return CoreModule.Companion.provideRemotelyOverridableDeviceProperties(platformDeviceProperties(), this.remoteOverridesProvider.get(), this.provideRemoteExpressionEvaluatorProvider);
        }

        public SharedPreferences namedSharedPreferences() {
            return CoreModule.Companion.provideRequestStructureContentProviderPreference(this.applicationModule.provideApplicationContext());
        }

        public PlatformDeviceProperties platformDeviceProperties() {
            return CoreModule.Companion.providePlatformDeviceProperties(this.defaultDevicePropertiesProvider.get(), ApplicationModule_ProvidePlatformPropertiesFactory.providePlatformProperties(this.applicationModule));
        }

        public RecommendationsScheduler recommendationsScheduler() {
            return new RecommendationsScheduler(namedOverridableDeviceProperties(), this.recommendationsMetricRecorderProvider.get(), this.recommendationHandlerProvider.get(), new RecommendationUpdaterPeriodicWorkRequestBuilder(), this.applicationModule.provideApplicationContext());
        }

        public RequestStructureContentProviderManager requestStructureContentProviderManager() {
            return new RequestStructureContentProviderManager(this.applicationModule.provideApplicationContext(), namedSharedPreferences(), namedOverridableDeviceProperties(), this.pearUpdateStructureProvider.get(), bootstrapRequestStructureBuilder(), this.minervaMetricsProvider.get());
        }

        @Override // com.amazon.livingroom.di.ApplicationComponent
        public void inject(UpdateRecommendationsWorker updateRecommendationsWorker) {
            injectUpdateRecommendationsWorker(updateRecommendationsWorker);
        }

        @Override // com.amazon.livingroom.di.ApplicationComponent
        public AndroidTvMainActivityComponent.Builder mainActivityComponent() {
            return new AndroidTvMainActivityComponentBuilder(this.primeVideoApplicationComponentImpl);
        }

        @Override // com.amazon.livingroom.di.ApplicationComponent
        public void inject(UpdateWatchNextWorker updateWatchNextWorker) {
            injectUpdateWatchNextWorker(updateWatchNextWorker);
        }

        @Override // com.amazon.livingroom.di.ApplicationComponent
        public void inject(ClearWatchNextWorker clearWatchNextWorker) {
            injectClearWatchNextWorker(clearWatchNextWorker);
        }

        @Override // com.amazon.livingroom.di.ApplicationComponent
        public void inject(PeriodicUpdateRecommendationsWorker periodicUpdateRecommendationsWorker) {
            injectPeriodicUpdateRecommendationsWorker(periodicUpdateRecommendationsWorker);
        }

        @Override // com.amazon.livingroom.di.ApplicationComponent
        public void inject(DtidRequestOnStartupWorker dtidRequestOnStartupWorker) {
            injectDtidRequestOnStartupWorker(dtidRequestOnStartupWorker);
        }

        @Override // com.amazon.livingroom.di.ApplicationComponent
        public void inject(ScheduleRecommendationsOnInstallReceiver scheduleRecommendationsOnInstallReceiver) {
            injectScheduleRecommendationsOnInstallReceiver(scheduleRecommendationsOnInstallReceiver);
        }

        @Override // com.amazon.livingroom.di.ApplicationComponent
        public void inject(BootUpReceiver bootUpReceiver) {
            injectBootUpReceiver(bootUpReceiver);
        }

        @Override // com.amazon.primevideo.di.PrimeVideoApplicationComponent
        public void inject(PrimeVideoApplication primeVideoApplication) {
            injectPrimeVideoApplication(primeVideoApplication);
        }

        @Override // com.amazon.primevideo.di.PrimeVideoApplicationComponent
        public void inject(LocaleChangeReceiver localeChangeReceiver) {
            injectLocaleChangeReceiver(localeChangeReceiver);
        }

        @Override // com.amazon.primevideo.di.PrimeVideoApplicationComponent
        public void inject(UpdateRecommendationsJobService updateRecommendationsJobService) {
            injectUpdateRecommendationsJobService(updateRecommendationsJobService);
        }

        @Override // com.amazon.primevideo.di.PrimeVideoApplicationComponent
        public void inject(RequestStructureContentProvider requestStructureContentProvider) {
            injectRequestStructureContentProvider(requestStructureContentProvider);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
