package com.amazon.ignitionshared;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import androidx.lifecycle.Lifecycle;
import com.amazon.ignitionshared.IgniteRenderer;
import com.amazon.ignitionshared.deeplink.DeepLinkIntentParser;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.deviceproperties.LocalOverridesProvider;
import com.amazon.livingroom.di.ApplicationComponent;
import com.amazon.livingroom.di.MainActivityModule;
import com.amazon.livingroom.di.Names;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbContext;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager;
import com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeConnector;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.primevideo.di.DaggerPrimeVideoApplicationComponent;
import j$.util.Objects;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {

    @Inject
    public ExploreByTouchHelper accessibilityHelper;

    @Inject
    public String actionOverrideDeviceProperties;

    @Inject
    public Provider<Boolean> allowLocalPropertyOverrides;

    @Inject
    public IgnitionContextProvider contextProvider;

    @Inject
    public DeepLinkIntentParser deepLinkIntentParser;

    @Inject
    public DeviceProperties deviceProperties;

    @Inject
    public FtvMpbContext ftvMpbContext;

    @Inject
    public GMBMessageSender gmbMessageSender;

    @Inject
    public IgniteRenderer.EventHandler igniteEventHandler;
    public SurfaceView igniteSurfaceView;
    public boolean isFirstStart;

    @Inject
    public LocalOverridesProvider localOverridesProvider;

    @Inject
    public MinervaMetrics minervaMetrics;

    @Inject
    public MediaPipelineBackendEngineManager mpbEngineManager;
    public boolean onHomePressed;
    public FrameLayout playbackSurfacesContainer;

    @Inject
    public IgniteRenderer renderer;

    @Inject
    public RendererManager rendererManager;

    @Inject
    public SonyCalibratedModeConnector sonyCalibratedModeConnector;

    @NotNull
    public final String tag;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LaunchType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ LaunchType[] $VALUES;
        public static final LaunchType Warm = new LaunchType("Warm", 0);
        public static final LaunchType Cold = new LaunchType("Cold", 1);

        public static final /* synthetic */ LaunchType[] $values() {
            return new LaunchType[]{Warm, Cold};
        }

        static {
            LaunchType[] launchTypeArr$values = $values();
            $VALUES = launchTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(launchTypeArr$values);
        }

        public LaunchType(String str, int i) {
        }

        @NotNull
        public static EnumEntries<LaunchType> getEntries() {
            return $ENTRIES;
        }

        public static LaunchType valueOf(String str) {
            return (LaunchType) Enum.valueOf(LaunchType.class, str);
        }

        public static LaunchType[] values() {
            return (LaunchType[]) $VALUES.clone();
        }
    }

    public MainActivity() {
        String simpleName = ((ClassReference) Reflection.getOrCreateKotlinClass(MainActivity.class)).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        this.tag = simpleName;
        this.isFirstStart = true;
    }

    public final Intent buildRestartIntent(Intent intent) {
        Intent intent2 = new Intent(getApplicationContext(), getClass());
        intent2.setFlags(268468224);
        intent2.setData(intent.getData());
        DeepLinkIntentParser.Companion companion = DeepLinkIntentParser.Companion;
        companion.getClass();
        String str = DeepLinkIntentParser.Companion.internalDeepLinkKey;
        String stringExtra = intent.getStringExtra(str);
        if (stringExtra != null) {
            companion.getClass();
            intent2.putExtra(str, stringExtra);
        }
        if (Objects.equals(intent.getAction(), AmazonButtonIntentMatcher.AMAZON_BUTTON_ACTION)) {
            intent2.setAction(intent.getAction());
        }
        return intent2;
    }

    @NotNull
    public final ExploreByTouchHelper getAccessibilityHelper() {
        ExploreByTouchHelper exploreByTouchHelper = this.accessibilityHelper;
        if (exploreByTouchHelper != null) {
            return exploreByTouchHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("accessibilityHelper");
        throw null;
    }

    @NotNull
    public final String getActionOverrideDeviceProperties() {
        String str = this.actionOverrideDeviceProperties;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException(Names.ACTION_OVERRIDE_DEVICE_PROPERTIES);
        throw null;
    }

    @NotNull
    public final Provider<Boolean> getAllowLocalPropertyOverrides() {
        Provider<Boolean> provider = this.allowLocalPropertyOverrides;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException(Names.ALLOW_LOCAL_PROPERTY_OVERRIDES);
        throw null;
    }

    @NotNull
    public final IgnitionContextProvider getContextProvider() {
        IgnitionContextProvider ignitionContextProvider = this.contextProvider;
        if (ignitionContextProvider != null) {
            return ignitionContextProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contextProvider");
        throw null;
    }

    @NotNull
    public final DeepLinkIntentParser getDeepLinkIntentParser() {
        DeepLinkIntentParser deepLinkIntentParser = this.deepLinkIntentParser;
        if (deepLinkIntentParser != null) {
            return deepLinkIntentParser;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deepLinkIntentParser");
        throw null;
    }

    @NotNull
    public final DeviceProperties getDeviceProperties() {
        DeviceProperties deviceProperties = this.deviceProperties;
        if (deviceProperties != null) {
            return deviceProperties;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deviceProperties");
        throw null;
    }

    @NotNull
    public final FtvMpbContext getFtvMpbContext() {
        FtvMpbContext ftvMpbContext = this.ftvMpbContext;
        if (ftvMpbContext != null) {
            return ftvMpbContext;
        }
        Intrinsics.throwUninitializedPropertyAccessException("ftvMpbContext");
        throw null;
    }

    @NotNull
    public final GMBMessageSender getGmbMessageSender() {
        GMBMessageSender gMBMessageSender = this.gmbMessageSender;
        if (gMBMessageSender != null) {
            return gMBMessageSender;
        }
        Intrinsics.throwUninitializedPropertyAccessException("gmbMessageSender");
        throw null;
    }

    @NotNull
    public final IgniteRenderer.EventHandler getIgniteEventHandler() {
        IgniteRenderer.EventHandler eventHandler = this.igniteEventHandler;
        if (eventHandler != null) {
            return eventHandler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("igniteEventHandler");
        throw null;
    }

    @NotNull
    public final SurfaceView getIgniteSurfaceView() {
        SurfaceView surfaceView = this.igniteSurfaceView;
        if (surfaceView != null) {
            return surfaceView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("igniteSurfaceView");
        throw null;
    }

    @NotNull
    public final LocalOverridesProvider getLocalOverridesProvider() {
        LocalOverridesProvider localOverridesProvider = this.localOverridesProvider;
        if (localOverridesProvider != null) {
            return localOverridesProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("localOverridesProvider");
        throw null;
    }

    @NotNull
    public final MinervaMetrics getMinervaMetrics() {
        MinervaMetrics minervaMetrics = this.minervaMetrics;
        if (minervaMetrics != null) {
            return minervaMetrics;
        }
        Intrinsics.throwUninitializedPropertyAccessException("minervaMetrics");
        throw null;
    }

    @NotNull
    public final MediaPipelineBackendEngineManager getMpbEngineManager() {
        MediaPipelineBackendEngineManager mediaPipelineBackendEngineManager = this.mpbEngineManager;
        if (mediaPipelineBackendEngineManager != null) {
            return mediaPipelineBackendEngineManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mpbEngineManager");
        throw null;
    }

    @NotNull
    public final IgniteRenderer getRenderer() {
        IgniteRenderer igniteRenderer = this.renderer;
        if (igniteRenderer != null) {
            return igniteRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("renderer");
        throw null;
    }

    @NotNull
    public final RendererManager getRendererManager() {
        RendererManager rendererManager = this.rendererManager;
        if (rendererManager != null) {
            return rendererManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rendererManager");
        throw null;
    }

    @NotNull
    public final SonyCalibratedModeConnector getSonyCalibratedModeConnector() {
        SonyCalibratedModeConnector sonyCalibratedModeConnector = this.sonyCalibratedModeConnector;
        if (sonyCalibratedModeConnector != null) {
            return sonyCalibratedModeConnector;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sonyCalibratedModeConnector");
        throw null;
    }

    public final void initContentView() {
        setContentView(R.layout.ignite_activity);
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.ignite_surface);
        surfaceView.getHolder().setFormat(-3);
        surfaceView.setZOrderMediaOverlay(true);
        setIgniteSurfaceView(surfaceView);
        this.playbackSurfacesContainer = (FrameLayout) findViewById(R.id.playback_surfaces_container);
    }

    public final void initEventListeners() {
        getIgniteSurfaceView().setOnKeyListener(getIgniteEventHandler());
        getIgniteSurfaceView().setOnClickListener(getIgniteEventHandler());
        getIgniteSurfaceView().setOnGenericMotionListener(getIgniteEventHandler());
        ViewCompat.setAccessibilityDelegate(getIgniteSurfaceView(), getAccessibilityHelper());
    }

    public final void maybeLoadIntentOverridesAndRestart(Intent intent) {
        Bundle extras;
        if (getAllowLocalPropertyOverrides().get().booleanValue() && getActionOverrideDeviceProperties().equals(intent.getAction()) && (extras = intent.getExtras()) != null) {
            getLocalOverridesProvider().setOverrides(extras);
            restartProcess(intent);
            throw null;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        super.onCreate(bundle);
        this.onHomePressed = false;
        turnScreenOn();
        initContentView();
        DaggerPrimeVideoApplicationComponent.AndroidTvMainActivityComponentBuilder androidTvMainActivityComponentBuilder = (DaggerPrimeVideoApplicationComponent.AndroidTvMainActivityComponentBuilder) ApplicationComponent.Companion.getInstance().mainActivityComponent();
        androidTvMainActivityComponentBuilder.mainActivityModule = new MainActivityModule(this, getIgniteSurfaceView());
        ((DaggerPrimeVideoApplicationComponent.AndroidTvMainActivityComponentImpl) androidTvMainActivityComponentBuilder.build()).injectMainActivity(this);
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        maybeLoadIntentOverridesAndRestart(intent);
        IgnitionContextProvider contextProvider = getContextProvider();
        Window window = getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "getWindow(...)");
        FrameLayout frameLayout = this.playbackSurfacesContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackSurfacesContainer");
            throw null;
        }
        contextProvider.onContextCreated(new IgnitionContext(this, window, frameLayout, getIgniteSurfaceView(), getAccessibilityHelper()));
        if (!getRendererManager().initializeRendering(jCurrentTimeMillis)) {
            Intent intent2 = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent2, "getIntent(...)");
            restartProcess(intent2);
            throw null;
        }
        initEventListeners();
        if (((Boolean) getDeviceProperties().get(DeviceProperties.SONY_CALIBRATED_MODE_ENABLED)).booleanValue()) {
            SonyCalibratedModeConnector sonyCalibratedModeConnector = getSonyCalibratedModeConnector();
            Lifecycle lifecycle = getLifecycle();
            Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
            sonyCalibratedModeConnector.observeLifecycle(lifecycle);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (!isFinishing()) {
            startActivity(new Intent(this, getClass()));
            getRendererManager().exitRenderingAndWait();
        }
        getContextProvider().onContextDestroyed();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        maybeLoadIntentOverridesAndRestart(intent);
        String str = getDeepLinkIntentParser().parse(intent);
        if (str != null) {
            sendDeepLinkEvent(str, LaunchType.Warm);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (this.isFirstStart) {
            DeepLinkIntentParser deepLinkIntentParser = getDeepLinkIntentParser();
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
            String str = deepLinkIntentParser.parse(intent);
            if (str != null) {
                sendDeepLinkEvent(str, LaunchType.Cold);
            }
            this.isFirstStart = false;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        getMpbEngineManager().releasePlayers();
        getMinervaMetrics().flush();
    }

    public final void restartProcess(Intent intent) {
        startActivity(buildRestartIntent(intent));
        finish();
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    public void sendDeepLinkEvent(@NotNull String deepLinkParams, @NotNull LaunchType launchType) {
        Intrinsics.checkNotNullParameter(deepLinkParams, "deepLinkParams");
        Intrinsics.checkNotNullParameter(launchType, "launchType");
        int iSendGMBMessageToClient = getGmbMessageSender().sendGMBMessageToClient("gmb.deeplinking.request", deepLinkParams, launchType == LaunchType.Cold ? 10000L : 0L);
        MetricEvent metricEventCreateMetricEvent = getMinervaMetrics().createMetricEvent(MinervaConstants.DEEP_LINK_SCHEMA_ID);
        String lowerCase = launchType.name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        metricEventCreateMetricEvent.addString("launchType", lowerCase);
        metricEventCreateMetricEvent.addLong("DeepLink.Success", 1 - iSendGMBMessageToClient);
        metricEventCreateMetricEvent.addLong("DeepLink.Error", iSendGMBMessageToClient);
        getMinervaMetrics().record(metricEventCreateMetricEvent, iSendGMBMessageToClient == 1);
    }

    public final void setAccessibilityHelper(@NotNull ExploreByTouchHelper exploreByTouchHelper) {
        Intrinsics.checkNotNullParameter(exploreByTouchHelper, "<set-?>");
        this.accessibilityHelper = exploreByTouchHelper;
    }

    public final void setActionOverrideDeviceProperties(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.actionOverrideDeviceProperties = str;
    }

    public final void setAllowLocalPropertyOverrides(@NotNull Provider<Boolean> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.allowLocalPropertyOverrides = provider;
    }

    public final void setContextProvider(@NotNull IgnitionContextProvider ignitionContextProvider) {
        Intrinsics.checkNotNullParameter(ignitionContextProvider, "<set-?>");
        this.contextProvider = ignitionContextProvider;
    }

    public final void setDeepLinkIntentParser(@NotNull DeepLinkIntentParser deepLinkIntentParser) {
        Intrinsics.checkNotNullParameter(deepLinkIntentParser, "<set-?>");
        this.deepLinkIntentParser = deepLinkIntentParser;
    }

    public final void setDeviceProperties(@NotNull DeviceProperties deviceProperties) {
        Intrinsics.checkNotNullParameter(deviceProperties, "<set-?>");
        this.deviceProperties = deviceProperties;
    }

    public final void setFtvMpbContext(@NotNull FtvMpbContext ftvMpbContext) {
        Intrinsics.checkNotNullParameter(ftvMpbContext, "<set-?>");
        this.ftvMpbContext = ftvMpbContext;
    }

    public final void setGmbMessageSender(@NotNull GMBMessageSender gMBMessageSender) {
        Intrinsics.checkNotNullParameter(gMBMessageSender, "<set-?>");
        this.gmbMessageSender = gMBMessageSender;
    }

    public final void setIgniteEventHandler(@NotNull IgniteRenderer.EventHandler eventHandler) {
        Intrinsics.checkNotNullParameter(eventHandler, "<set-?>");
        this.igniteEventHandler = eventHandler;
    }

    public final void setIgniteSurfaceView(@NotNull SurfaceView surfaceView) {
        Intrinsics.checkNotNullParameter(surfaceView, "<set-?>");
        this.igniteSurfaceView = surfaceView;
    }

    public final void setLocalOverridesProvider(@NotNull LocalOverridesProvider localOverridesProvider) {
        Intrinsics.checkNotNullParameter(localOverridesProvider, "<set-?>");
        this.localOverridesProvider = localOverridesProvider;
    }

    public final void setMinervaMetrics(@NotNull MinervaMetrics minervaMetrics) {
        Intrinsics.checkNotNullParameter(minervaMetrics, "<set-?>");
        this.minervaMetrics = minervaMetrics;
    }

    public final void setMpbEngineManager(@NotNull MediaPipelineBackendEngineManager mediaPipelineBackendEngineManager) {
        Intrinsics.checkNotNullParameter(mediaPipelineBackendEngineManager, "<set-?>");
        this.mpbEngineManager = mediaPipelineBackendEngineManager;
    }

    public final void setRenderer(@NotNull IgniteRenderer igniteRenderer) {
        Intrinsics.checkNotNullParameter(igniteRenderer, "<set-?>");
        this.renderer = igniteRenderer;
    }

    public final void setRendererManager(@NotNull RendererManager rendererManager) {
        Intrinsics.checkNotNullParameter(rendererManager, "<set-?>");
        this.rendererManager = rendererManager;
    }

    public final void setSonyCalibratedModeConnector(@NotNull SonyCalibratedModeConnector sonyCalibratedModeConnector) {
        Intrinsics.checkNotNullParameter(sonyCalibratedModeConnector, "<set-?>");
        this.sonyCalibratedModeConnector = sonyCalibratedModeConnector;
    }

    public final void turnScreenOn() {
        if (Build.VERSION.SDK_INT < 27) {
            getWindow().addFlags(2097152);
        }
    }

    @Named(Names.ACTION_OVERRIDE_DEVICE_PROPERTIES)
    public static /* synthetic */ void getActionOverrideDeviceProperties$annotations() {
    }

    @Named(Names.ALLOW_LOCAL_PROPERTY_OVERRIDES)
    public static /* synthetic */ void getAllowLocalPropertyOverrides$annotations() {
    }
}
