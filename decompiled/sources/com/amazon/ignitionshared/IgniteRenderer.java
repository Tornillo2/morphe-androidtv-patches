package com.amazon.ignitionshared;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import com.amazon.ignitionshared.Renderer;
import com.amazon.ignitionshared.filesystem.LocalStorage;
import com.amazon.ignitionshared.metrics.JemallocAllocatorMetricRecorder;
import com.amazon.ignitionshared.migration.MigrationManager;
import com.amazon.livingroom.accessibility.TextToSpeechEngine;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider;
import com.amazon.livingroom.mediapipelinebackend.DrmProvisioner;
import com.amazon.livingroom.mediapipelinebackend.DrmSystemManager;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbContext;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext;
import com.amazon.livingroom.mediapipelinebackend.MediaEventHandler;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager;
import com.amazon.livingroom.mediapipelinebackend.MpbLog;
import com.amazon.reporting.Log;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class IgniteRenderer implements Renderer, IgnitionContextListener {

    @NotNull
    public final CachedTarExtractor assetsExtractor;

    @NotNull
    public final Context context;

    @NotNull
    public final DeviceAttestationService deviceAttestationService;

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final DrmKeyStatusNotifier drmKeyStatusNotifier;

    @NotNull
    public final DrmProvisioner drmProvisioner;

    @NotNull
    public final EventHandler eventHandler;

    @NotNull
    public final ExitReasonHandler exitReasonHandler;

    @NotNull
    public final FtvMpbContext ftvMpbContext;

    @NotNull
    public final FtvMpbDrmContext.Factory ftvMpbDrmContextFactory;

    @NotNull
    public final GMBMessageProcessor gmbMessageProcessor;

    @NotNull
    public final GMBMessageSender gmbMessageSender;

    @NotNull
    public final IgniteDevicePropertiesProvider igniteDevicePropertiesProvider;
    public boolean igniteInitialized;

    @NotNull
    public final IgnitionContextProvider ignitionContextProvider;

    @NotNull
    public final String[] ignitionXStartupArgs;

    @NotNull
    public final IgniteJavaCallbacks javaCallbacks;

    @NotNull
    public final JemallocAllocatorMetricRecorder jemallocAllocatorMetricRecorder;

    @NotNull
    public final LaunchReasonIntentParser launchReasonIntentParser;

    @NotNull
    public final LocalStorage localStorage;

    @NotNull
    public final MigrationManager migrationManager;

    @NotNull
    public final MediaPipelineBackendEngineManager mpbEngineManager;

    @NotNull
    public final NativeThreadInitializer nativeThreadInitializer;

    @NotNull
    public final File rootDir;

    @NotNull
    public final Provider<TextToSpeechEngine> textToSpeechEngineProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @ApplicationScope
    public static class EventHandler implements View.OnKeyListener, View.OnClickListener, View.OnGenericMotionListener, MediaEventHandler {

        @Nullable
        public volatile IgniteRenderer igniteRenderer;

        @Inject
        public EventHandler() {
        }

        @Nullable
        public final IgniteRenderer getIgniteRenderer$ignitionshared_release() {
            return this.igniteRenderer;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            simulateKeyEvent(23);
        }

        @Override // android.view.View.OnGenericMotionListener
        public boolean onGenericMotion(@NotNull View view, @NotNull MotionEvent event) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(event, "event");
            IgniteRenderer igniteRenderer = this.igniteRenderer;
            if (igniteRenderer == null || event.getSource() == 16777232) {
                return false;
            }
            return igniteRenderer.dispatchPointerEvent(event.getButtonState(), event.getAction(), event.getX() / view.getWidth(), event.getY() / view.getHeight(), event.getAxisValue(10), event.getAxisValue(9));
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(@Nullable View view, int i, @NotNull KeyEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            IgniteRenderer igniteRenderer = this.igniteRenderer;
            if (igniteRenderer == null) {
                return false;
            }
            return igniteRenderer.dispatchKeyEvent(event.getAction(), i, event.getMetaState(), event.getRepeatCount());
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.MediaEventHandler
        public void pause() {
            simulateKeyEvent(127);
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.MediaEventHandler
        public void play() {
            simulateKeyEvent(126);
        }

        public final void setIgniteRenderer$ignitionshared_release(@Nullable IgniteRenderer igniteRenderer) {
            this.igniteRenderer = igniteRenderer;
        }

        public void simulateKeyEvent(int i) {
            IgniteRenderer igniteRenderer = this.igniteRenderer;
            if (igniteRenderer == null) {
                return;
            }
            igniteRenderer.dispatchKeyEvent(0, i, 0, 0);
            igniteRenderer.dispatchKeyEvent(1, i, 0, 0);
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.MediaEventHandler
        public void stop() {
            simulateKeyEvent(86);
        }
    }

    @Inject
    public IgniteRenderer(@ApplicationContext @NotNull Context context, @NotNull EventHandler eventHandler, @Named(Names.IGNITE_ROOT_DIR) @NotNull File rootDir, @NotNull CachedTarExtractor assetsExtractor, @NotNull IgniteDevicePropertiesProvider igniteDevicePropertiesProvider, @NotNull DrmProvisioner drmProvisioner, @NotNull MediaPipelineBackendEngineManager mpbEngineManager, @NotNull FtvMpbContext ftvMpbContext, @Named(Names.IGNITIONX_STARTUP_ARGUMENTS) @NotNull String[] ignitionXStartupArgs, @NotNull DeviceProperties deviceProperties, @NotNull NativeThreadInitializer nativeThreadInitializer, @NotNull LocalStorage localStorage, @NotNull Provider<TextToSpeechEngine> textToSpeechEngineProvider, @NotNull GMBMessageProcessor gmbMessageProcessor, @NotNull GMBMessageSender gmbMessageSender, @NotNull ExitReasonHandler exitReasonHandler, @NotNull DeviceAttestationService deviceAttestationService, @NotNull MigrationManager migrationManager, @NotNull IgnitionContextProvider ignitionContextProvider, @NotNull LaunchReasonIntentParser launchReasonIntentParser, @NotNull DrmKeyStatusNotifier drmKeyStatusNotifier, @NotNull FtvMpbDrmContext.Factory ftvMpbDrmContextFactory, @NotNull JemallocAllocatorMetricRecorder jemallocAllocatorMetricRecorder, @NotNull Set<ServiceInitializer> serviceInitializerSet) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(eventHandler, "eventHandler");
        Intrinsics.checkNotNullParameter(rootDir, "rootDir");
        Intrinsics.checkNotNullParameter(assetsExtractor, "assetsExtractor");
        Intrinsics.checkNotNullParameter(igniteDevicePropertiesProvider, "igniteDevicePropertiesProvider");
        Intrinsics.checkNotNullParameter(drmProvisioner, "drmProvisioner");
        Intrinsics.checkNotNullParameter(mpbEngineManager, "mpbEngineManager");
        Intrinsics.checkNotNullParameter(ftvMpbContext, "ftvMpbContext");
        Intrinsics.checkNotNullParameter(ignitionXStartupArgs, "ignitionXStartupArgs");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(nativeThreadInitializer, "nativeThreadInitializer");
        Intrinsics.checkNotNullParameter(localStorage, "localStorage");
        Intrinsics.checkNotNullParameter(textToSpeechEngineProvider, "textToSpeechEngineProvider");
        Intrinsics.checkNotNullParameter(gmbMessageProcessor, "gmbMessageProcessor");
        Intrinsics.checkNotNullParameter(gmbMessageSender, "gmbMessageSender");
        Intrinsics.checkNotNullParameter(exitReasonHandler, "exitReasonHandler");
        Intrinsics.checkNotNullParameter(deviceAttestationService, "deviceAttestationService");
        Intrinsics.checkNotNullParameter(migrationManager, "migrationManager");
        Intrinsics.checkNotNullParameter(ignitionContextProvider, "ignitionContextProvider");
        Intrinsics.checkNotNullParameter(launchReasonIntentParser, "launchReasonIntentParser");
        Intrinsics.checkNotNullParameter(drmKeyStatusNotifier, "drmKeyStatusNotifier");
        Intrinsics.checkNotNullParameter(ftvMpbDrmContextFactory, "ftvMpbDrmContextFactory");
        Intrinsics.checkNotNullParameter(jemallocAllocatorMetricRecorder, "jemallocAllocatorMetricRecorder");
        Intrinsics.checkNotNullParameter(serviceInitializerSet, "serviceInitializerSet");
        this.context = context;
        this.eventHandler = eventHandler;
        this.rootDir = rootDir;
        this.assetsExtractor = assetsExtractor;
        this.igniteDevicePropertiesProvider = igniteDevicePropertiesProvider;
        this.drmProvisioner = drmProvisioner;
        this.mpbEngineManager = mpbEngineManager;
        this.ftvMpbContext = ftvMpbContext;
        this.ignitionXStartupArgs = ignitionXStartupArgs;
        this.deviceProperties = deviceProperties;
        this.nativeThreadInitializer = nativeThreadInitializer;
        this.localStorage = localStorage;
        this.textToSpeechEngineProvider = textToSpeechEngineProvider;
        this.gmbMessageProcessor = gmbMessageProcessor;
        this.gmbMessageSender = gmbMessageSender;
        this.exitReasonHandler = exitReasonHandler;
        this.deviceAttestationService = deviceAttestationService;
        this.migrationManager = migrationManager;
        this.ignitionContextProvider = ignitionContextProvider;
        this.launchReasonIntentParser = launchReasonIntentParser;
        this.drmKeyStatusNotifier = drmKeyStatusNotifier;
        this.ftvMpbDrmContextFactory = ftvMpbDrmContextFactory;
        this.jemallocAllocatorMetricRecorder = jemallocAllocatorMetricRecorder;
        this.javaCallbacks = new IgniteJavaCallbacks();
        Iterator<ServiceInitializer> it = serviceInitializerSet.iterator();
        while (it.hasNext()) {
            it.next().initialize();
        }
        this.ignitionContextProvider.addListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean dispatchKeyEvent(int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean dispatchPointerEvent(int i, int i2, float f, float f2, float f3, float f4);

    private final native void exitIgnite();

    private final native int initializeIgnite(long j, String[] strArr);

    public static final Unit onContextCreated$lambda$2(IgnitionContext ignitionContext) {
        final AppCompatActivity appCompatActivity = ignitionContext.activity;
        appCompatActivity.runOnUiThread(new Runnable() { // from class: com.amazon.ignitionshared.IgniteRenderer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                appCompatActivity.moveTaskToBack(true);
            }
        });
        return Unit.INSTANCE;
    }

    private final native void pauseIgnite();

    private final native void resumeIgnite(boolean z, Surface surface);

    public static final LaunchReason resumeRendering$lambda$5$lambda$4(IgniteRenderer igniteRenderer, IgnitionContext ignitionContext) {
        LaunchReasonIntentParser launchReasonIntentParser = igniteRenderer.launchReasonIntentParser;
        Intent intent = ignitionContext.activity.getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        return launchReasonIntentParser.parse(intent);
    }

    @Override // com.amazon.ignitionshared.Renderer
    public void exitRendering() {
        exitIgnite();
    }

    @Override // com.amazon.ignitionshared.Renderer
    public int initializeRendering(@NotNull Renderer.StateListener stateListener, long j) {
        Intrinsics.checkNotNullParameter(stateListener, "stateListener");
        IgniteJavaCallbacks igniteJavaCallbacks = this.javaCallbacks;
        igniteJavaCallbacks.getClass();
        igniteJavaCallbacks.stateListener = stateListener;
        this.migrationManager.migrate();
        this.assetsExtractor.extractIfNew();
        Object obj = this.deviceProperties.get(DeviceProperties.IGNITIONX_USE_JEMALLOC_ALLOCATOR);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        if (((Boolean) obj).booleanValue()) {
            try {
                System.loadLibrary("ignite_allocator");
                this.jemallocAllocatorMetricRecorder.recordSuccess();
            } catch (UnsatisfiedLinkError e) {
                Log.w("IgniteAllocator", "Ignite Allocator library not found", e);
                this.jemallocAllocatorMetricRecorder.recordError();
            }
        }
        System.loadLibrary("prime-video-device-layer");
        System.loadLibrary("ignite-android-support");
        System.loadLibrary("ignite");
        MpbLog.logToNative = true;
        Object obj2 = this.deviceProperties.get(DeviceProperties.MPB_LOG_EVERYTHING_AS_WARNING);
        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
        MpbLog.allLevelsAsWarning = ((Boolean) obj2).booleanValue();
        String absolutePath = this.rootDir.getAbsolutePath();
        DrmSystemManager drmSystemManager = new DrmSystemManager(this.drmProvisioner);
        FtvMpbDrmContext ftvMpbDrmContextCreate = this.ftvMpbDrmContextFactory.create(this.drmProvisioner, this.drmKeyStatusNotifier, this.ftvMpbContext.getFailoverManager());
        Object obj3 = this.deviceProperties.get(DeviceProperties.USE_MEDIA_CODEC_BACKED_MPB);
        Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
        boolean zBooleanValue = ((Boolean) obj3).booleanValue();
        TextToSpeechEngine textToSpeechEngine = this.textToSpeechEngineProvider.get();
        try {
            IgniteInitializer.initializeJni(this.nativeThreadInitializer);
            IgniteInitializer.initializeCAres(this.context);
            IgniteInitializer.initializeAndroidContext(absolutePath);
            IgniteInitializer.initializeLifecycleBridge(this.javaCallbacks);
            IgniteInitializer.initializeDevicePropertiesProvider(this.igniteDevicePropertiesProvider);
            IgniteInitializer.initializeTextToSpeech(textToSpeechEngine);
            if (!zBooleanValue) {
                ftvMpbDrmContextCreate = null;
            }
            IgniteInitializer.initializeDrmBridge(drmSystemManager, ftvMpbDrmContextCreate);
            IgniteInitializer.initializeMpb(this.mpbEngineManager, zBooleanValue ? this.ftvMpbContext : null);
            IgniteInitializer.initializeLocalStorage(this.localStorage);
            IgniteInitializer.initializeGMBMessageProcessor(this.gmbMessageProcessor);
            IgniteInitializer.initializeExitReasonHandler(this.exitReasonHandler);
            IgniteInitializer.initializeDeviceAttestation(this.deviceAttestationService);
            this.gmbMessageSender.setIgniteInitialised(true);
            this.igniteInitialized = true;
            this.eventHandler.igniteRenderer = this;
            int iInitializeIgnite = initializeIgnite(j, this.ignitionXStartupArgs);
            this.gmbMessageSender.setIgniteInitialised(false);
            this.igniteInitialized = false;
            AutoCloseableKt.closeFinally(textToSpeechEngine, null);
            return iInitializeIgnite;
        } finally {
        }
    }

    public final native void notifyDisplayOff();

    public final native void notifyLowMemory();

    @Override // com.amazon.ignitionshared.IgnitionContextListener
    public void onContextCreated(@NotNull final IgnitionContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.javaCallbacks.setRequestExitToBackgroundCallback(new Function0() { // from class: com.amazon.ignitionshared.IgniteRenderer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return IgniteRenderer.onContextCreated$lambda$2(context);
            }
        });
    }

    @Override // com.amazon.ignitionshared.IgnitionContextListener
    public void onContextDestroyed() {
        this.javaCallbacks.setRequestExitToBackgroundCallback(null);
        this.javaCallbacks.setLaunchReasonCallback(null);
    }

    public final void onDisplayOff() {
        if (this.igniteInitialized) {
            notifyDisplayOff();
        }
    }

    public final void onLowMemory() {
        if (this.igniteInitialized) {
            notifyLowMemory();
        }
    }

    @Override // com.amazon.ignitionshared.Renderer
    public void pauseRendering() {
        pauseIgnite();
    }

    @Override // com.amazon.ignitionshared.Renderer
    public void resumeRendering(boolean z, @Nullable Surface surface) {
        final IgnitionContext ignitionContext = this.ignitionContextProvider.context;
        if (ignitionContext != null) {
            IgniteJavaCallbacks igniteJavaCallbacks = this.javaCallbacks;
            Function0<? extends LaunchReason> function0 = igniteJavaCallbacks.launchReasonCallback;
            if (function0 == null) {
                function0 = new Function0() { // from class: com.amazon.ignitionshared.IgniteRenderer$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return IgniteRenderer.resumeRendering$lambda$5$lambda$4(this.f$0, ignitionContext);
                    }
                };
            }
            igniteJavaCallbacks.setLaunchReasonCallback(function0);
        }
        resumeIgnite(z, surface);
    }

    public final native void updateIgniteSurface(@Nullable Surface surface);

    @Override // com.amazon.ignitionshared.Renderer
    public void updateIgniteSurfaceRef(@Nullable Surface surface) {
        updateIgniteSurface(surface);
    }
}
