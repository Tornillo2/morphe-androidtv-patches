package com.amazon.ignitionshared;

import android.os.SystemClock;
import android.view.Surface;
import android.view.SurfaceHolder;
import androidx.annotation.MainThread;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.ignitionshared.Renderer;
import com.amazon.ignitionshared.tvinput.TifOverlaySurfaceHolderCallbackProvider;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nRendererManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RendererManager.kt\ncom/amazon/ignitionshared/RendererManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,426:1\n1#2:427\n*E\n"})
public final class RendererManager implements DefaultLifecycleObserver, IgnitionContextListener {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String LOG_TAG;

    @NotNull
    public final ExitCallback exitCallback;

    @NotNull
    public final ExitReasonHandler exitReasonHandler;
    public final int igniteThreadStackSize;
    public boolean isActivityStarted;
    public boolean isSurfaceChanging;
    public final long killAppOnExcessiveBackgroundTransitionWaitThresholdMS;

    @NotNull
    public final LifecycleBoundHandler lifecycleBoundHandler;

    @Nullable
    public Surface overlaySurface;

    @NotNull
    public final Renderer renderer;

    @NotNull
    public RenderingState renderingState;

    @NotNull
    public final Object renderingStateMutex;

    @Nullable
    public Thread renderingThread;
    public final boolean shouldKillAppOnExcessiveWaitDuringBackgroundTransition;
    public final boolean supportsForegroundUnfocused;

    @Nullable
    public Surface surface;

    @NotNull
    public final Object surfaceChangeMutex;

    @NotNull
    public final ApplicationVisibilityMonitor visibilityMonitor;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ExitCallback {
        /* JADX INFO: renamed from: onRenderingExit */
        void mo257onRenderingExit(int i);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class RendererStateListener implements Renderer.StateListener {
        public RendererStateListener() {
        }

        @Override // com.amazon.ignitionshared.Renderer.StateListener
        public void onRenderingPaused() {
            RendererManager.this.setState(RenderingState.PAUSED);
        }

        @Override // com.amazon.ignitionshared.Renderer.StateListener
        public void onRenderingPausing() {
            RendererManager.this.setState(RenderingState.PAUSING);
        }

        @Override // com.amazon.ignitionshared.Renderer.StateListener
        public void onRenderingStarted() {
            RendererManager.this.setState(RenderingState.RUNNING);
        }

        @Override // com.amazon.ignitionshared.Renderer.StateListener
        public void onRenderingStartedUnfocused() {
            RendererManager.this.setState(RenderingState.RUNNING_UNFOCUSED);
        }

        @Override // com.amazon.ignitionshared.Renderer.StateListener
        public void onSurfaceChangeFinish() {
            Object obj = RendererManager.this.surfaceChangeMutex;
            RendererManager rendererManager = RendererManager.this;
            synchronized (obj) {
                Log.i(RendererManager.LOG_TAG, "Native surface change finished, setting isSurfaceChanging = false");
                rendererManager.isSurfaceChanging = false;
                rendererManager.surfaceChangeMutex.notifyAll();
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class RenderingState {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ RenderingState[] $VALUES;
        public static final RenderingState PENDING_TRANSITION = new RenderingState("PENDING_TRANSITION", 0);
        public static final RenderingState PAUSING = new RenderingState("PAUSING", 1);
        public static final RenderingState PAUSED = new RenderingState("PAUSED", 2);
        public static final RenderingState RUNNING_UNFOCUSED = new RenderingState("RUNNING_UNFOCUSED", 3);
        public static final RenderingState RUNNING = new RenderingState(DebugCoroutineInfoImplKt.RUNNING, 4);
        public static final RenderingState FINISHED = new RenderingState("FINISHED", 5);

        public static final /* synthetic */ RenderingState[] $values() {
            return new RenderingState[]{PENDING_TRANSITION, PAUSING, PAUSED, RUNNING_UNFOCUSED, RUNNING, FINISHED};
        }

        static {
            RenderingState[] renderingStateArr$values = $values();
            $VALUES = renderingStateArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(renderingStateArr$values);
        }

        public RenderingState(String str, int i) {
        }

        @NotNull
        public static EnumEntries<RenderingState> getEntries() {
            return $ENTRIES;
        }

        public static RenderingState valueOf(String str) {
            return (RenderingState) Enum.valueOf(RenderingState.class, str);
        }

        public static RenderingState[] values() {
            return (RenderingState[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class SurfaceHolderCallback implements SurfaceHolder.Callback {
        public SurfaceHolderCallback() {
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0049 A[Catch: all -> 0x003f, TryCatch #0 {all -> 0x003f, blocks: (B:4:0x002e, B:6:0x003a, B:15:0x0049, B:16:0x004c, B:11:0x0041, B:13:0x0045), top: B:22:0x002e }] */
        @Override // android.view.SurfaceHolder.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void surfaceChanged(@org.jetbrains.annotations.NotNull android.view.SurfaceHolder r6, int r7, int r8, int r9) {
            /*
                r5 = this;
                java.lang.String r0 = "holder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = com.amazon.ignitionshared.RendererManager.access$getLOG_TAG$cp()
                android.view.Surface r1 = r6.getSurface()
                java.lang.String r2 = "Surface changed: format="
                java.lang.String r3 = " width="
                java.lang.String r4 = " height="
                java.lang.StringBuilder r7 = androidx.collection.MutableFloatList$$ExternalSyntheticOutline0.m(r2, r7, r3, r8, r4)
                r7.append(r9)
                java.lang.String r8 = " surface="
                r7.append(r8)
                r7.append(r1)
                java.lang.String r7 = r7.toString()
                com.amazon.reporting.Log.i(r0, r7)
                com.amazon.ignitionshared.RendererManager r7 = com.amazon.ignitionshared.RendererManager.this
                java.lang.Object r8 = r7.renderingStateMutex
                monitor-enter(r8)
                android.view.Surface r6 = r6.getSurface()     // Catch: java.lang.Throwable -> L3f
                r7.surface = r6     // Catch: java.lang.Throwable -> L3f
                com.amazon.ignitionshared.RendererManager$RenderingState r6 = r7.renderingState     // Catch: java.lang.Throwable -> L3f
                com.amazon.ignitionshared.RendererManager$RenderingState r9 = com.amazon.ignitionshared.RendererManager.RenderingState.RUNNING_UNFOCUSED     // Catch: java.lang.Throwable -> L3f
                if (r6 != r9) goto L41
                boolean r9 = r7.isActivityStarted     // Catch: java.lang.Throwable -> L3f
                if (r9 == 0) goto L49
                goto L41
            L3f:
                r6 = move-exception
                goto L58
            L41:
                com.amazon.ignitionshared.RendererManager$RenderingState r9 = com.amazon.ignitionshared.RendererManager.RenderingState.RUNNING     // Catch: java.lang.Throwable -> L3f
                if (r6 != r9) goto L4c
                boolean r6 = r7.isActivityStarted     // Catch: java.lang.Throwable -> L3f
                if (r6 == 0) goto L4c
            L49:
                r7.pauseRenderingAndWait()     // Catch: java.lang.Throwable -> L3f
            L4c:
                r7.maybeResumeRendering()     // Catch: java.lang.Throwable -> L3f
                monitor-exit(r8)
                java.lang.String r6 = com.amazon.ignitionshared.RendererManager.LOG_TAG
                java.lang.String r7 = "Returning from surfaceChanged"
                com.amazon.reporting.Log.i(r6, r7)
                return
            L58:
                monitor-exit(r8)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ignitionshared.RendererManager.SurfaceHolderCallback.surfaceChanged(android.view.SurfaceHolder, int, int, int):void");
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(@NotNull SurfaceHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Log.i(RendererManager.LOG_TAG, "Surface created: " + holder.getSurface());
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(@NotNull SurfaceHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Log.i(RendererManager.LOG_TAG, "Surface destroyed: " + holder.getSurface());
            RendererManager rendererManager = RendererManager.this;
            synchronized (rendererManager.renderingStateMutex) {
                rendererManager.surface = null;
                rendererManager.pauseRenderingAndWait();
            }
            Log.i(RendererManager.LOG_TAG, "Returning from surfaceDestroyed");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class TifOverlaySurfaceHolderCallback implements SurfaceHolder.Callback {
        public TifOverlaySurfaceHolderCallback() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(@NotNull SurfaceHolder holder, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            String str = RendererManager.LOG_TAG;
            Surface surface = holder.getSurface();
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("TIF overlay surface changed: format=", i, " width=", i2, " height=");
            sbM.append(i3);
            sbM.append(" surface=");
            sbM.append(surface);
            Log.i(str, sbM.toString());
            RendererManager rendererManager = RendererManager.this;
            synchronized (rendererManager.renderingStateMutex) {
                if (!Intrinsics.areEqual(rendererManager.overlaySurface, holder.getSurface())) {
                    rendererManager.surfaceChangeWithWait(holder.getSurface());
                    rendererManager.overlaySurface = holder.getSurface();
                }
            }
            Log.d(RendererManager.LOG_TAG, "Returning from TIF overlay surfaceChanged");
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(@NotNull SurfaceHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Log.i(RendererManager.LOG_TAG, "TIF overlay surface created: " + holder.getSurface());
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(@NotNull SurfaceHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Log.i(RendererManager.LOG_TAG, "TIF overlay surface destroyed: " + holder.getSurface());
            RendererManager rendererManager = RendererManager.this;
            synchronized (rendererManager.renderingStateMutex) {
                try {
                    rendererManager.overlaySurface = null;
                    if (rendererManager.isActivityStarted) {
                        rendererManager.maybeResumeRendering();
                    } else {
                        rendererManager.surfaceChangeWithWait(null);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            Log.d(RendererManager.LOG_TAG, "Returning from TIF overlay surfaceDestroyed");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RenderingState.values().length];
            try {
                iArr[RenderingState.PAUSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RenderingState.RUNNING_UNFOCUSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[RenderingState.RUNNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static Unit $r8$lambda$j1zFmRFImxdX1azwX6AiaejWWEc(IgniteRenderer igniteRenderer) {
        igniteRenderer.onDisplayOff();
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void $r8$lambda$zUytpSqyGqzVZMbDyVJW_t4cmTo(RendererManager rendererManager, long j) {
        initializeRendering$lambda$7(rendererManager, j);
        throw null;
    }

    static {
        String simpleName = ((ClassReference) Reflection.factory.getOrCreateKotlinClass(RendererManager.class)).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        LOG_TAG = simpleName;
    }

    @Inject
    public RendererManager(@NotNull IgnitionContextProvider contextProvider, @NotNull DeviceProperties deviceProperties, @NotNull TifOverlaySurfaceHolderCallbackProvider tifOverlaySurfaceHolderCallbackProvider, @NotNull Renderer renderer, @NotNull LifecycleBoundHandler lifecycleBoundHandler, @NotNull ApplicationVisibilityMonitor visibilityMonitor, @NotNull ExitReasonHandler exitReasonHandler, @NotNull ExitCallback exitCallback) {
        Intrinsics.checkNotNullParameter(contextProvider, "contextProvider");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(tifOverlaySurfaceHolderCallbackProvider, "tifOverlaySurfaceHolderCallbackProvider");
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        Intrinsics.checkNotNullParameter(lifecycleBoundHandler, "lifecycleBoundHandler");
        Intrinsics.checkNotNullParameter(visibilityMonitor, "visibilityMonitor");
        Intrinsics.checkNotNullParameter(exitReasonHandler, "exitReasonHandler");
        Intrinsics.checkNotNullParameter(exitCallback, "exitCallback");
        this.renderer = renderer;
        this.lifecycleBoundHandler = lifecycleBoundHandler;
        this.visibilityMonitor = visibilityMonitor;
        this.exitReasonHandler = exitReasonHandler;
        this.exitCallback = exitCallback;
        Object obj = deviceProperties.get(DeviceProperties.KILL_APP_ON_EXCESSIVE_BACKGROUND_TRANSITION_WAIT);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        this.shouldKillAppOnExcessiveWaitDuringBackgroundTransition = ((Boolean) obj).booleanValue();
        Object obj2 = deviceProperties.get(DeviceProperties.KILL_APP_ON_EXCESSIVE_BACKGROUND_TRANSITION_WAIT_THRESHOLD_MS);
        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
        this.killAppOnExcessiveBackgroundTransitionWaitThresholdMS = ((Number) obj2).longValue();
        Object obj3 = deviceProperties.get(DeviceProperties.SUPPORTS_FOREGROUND_UNFOCUSED);
        Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
        this.supportsForegroundUnfocused = ((Boolean) obj3).booleanValue();
        Object obj4 = deviceProperties.get(DeviceProperties.IGNITIONX_STACK_SIZE);
        Intrinsics.checkNotNullExpressionValue(obj4, "get(...)");
        this.igniteThreadStackSize = ((Number) obj4).intValue();
        this.renderingState = RenderingState.PENDING_TRANSITION;
        this.renderingStateMutex = new Object();
        this.surfaceChangeMutex = new Object();
        IgnitionContext ignitionContext = contextProvider.context;
        if (ignitionContext != null) {
            onContextCreated(ignitionContext);
        }
        contextProvider.addListener(this);
        tifOverlaySurfaceHolderCallbackProvider.setCallback(new TifOverlaySurfaceHolderCallback());
    }

    public static final void initializeRendering$lambda$7(RendererManager rendererManager, long j) {
        rendererManager.visibilityMonitor.registerHdmiReceiver();
        ApplicationVisibilityMonitor applicationVisibilityMonitor = rendererManager.visibilityMonitor;
        try {
            Renderer renderer = rendererManager.renderer;
            final IgniteRenderer igniteRenderer = renderer instanceof IgniteRenderer ? (IgniteRenderer) renderer : null;
            if (igniteRenderer != null) {
                applicationVisibilityMonitor.onHdmiDisconnected = new Function0() { // from class: com.amazon.ignitionshared.RendererManager$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RendererManager.$r8$lambda$j1zFmRFImxdX1azwX6AiaejWWEc(igniteRenderer);
                    }
                };
            }
            String str = LOG_TAG;
            Log.d(str, "Initializing renderer");
            int iInitializeRendering = rendererManager.renderer.initializeRendering(rendererManager.new RendererStateListener(), j);
            if (iInitializeRendering == 0) {
                Log.d(str, "Ignite exited with no errors");
            } else {
                Log.e(str, "Ignite exited with error code: " + iInitializeRendering);
            }
            rendererManager.setState(RenderingState.FINISHED);
            rendererManager.exitCallback.mo257onRenderingExit(iInitializeRendering);
            throw null;
        } finally {
        }
    }

    public static final Unit initializeRendering$lambda$7$lambda$6$lambda$5$lambda$4(IgniteRenderer igniteRenderer) {
        igniteRenderer.onDisplayOff();
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void waitUntilStateIsAtLeast$default(RendererManager rendererManager, RenderingState renderingState, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        rendererManager.waitUntilStateIsAtLeast(renderingState, j);
    }

    public final void exitRenderingAndWait() {
        synchronized (this.renderingStateMutex) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                waitUntilStateIsAtLeast$default(this, RenderingState.PAUSED, 0L, 2, null);
                if (this.renderingState == RenderingState.FINISHED) {
                    return;
                }
                this.renderer.exitRendering();
                try {
                    Log.d(LOG_TAG, "Waiting for rendering to finish...");
                    while (this.renderingState != RenderingState.FINISHED) {
                        this.renderingStateMutex.wait();
                    }
                    String str = LOG_TAG;
                    Log.d(str, "Rendering finished. Waiting for rendering thread to exit...");
                    Thread thread = this.renderingThread;
                    Intrinsics.checkNotNull(thread);
                    thread.join();
                    Log.d(str, "Rendering thread exited");
                } catch (InterruptedException unused) {
                    Log.w(LOG_TAG, "Interrupted while waiting for the rendering thread to finish");
                    Thread.currentThread().interrupt();
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    @MainThread
    public final boolean initializeRendering(final long j) {
        boolean z;
        if (this.renderingThread != null) {
            synchronized (this.renderingStateMutex) {
                z = this.renderingState != RenderingState.FINISHED;
            }
            return z;
        }
        Thread thread = new Thread(null, new Runnable() { // from class: com.amazon.ignitionshared.RendererManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RendererManager.$r8$lambda$zUytpSqyGqzVZMbDyVJW_t4cmTo(this.f$0, j);
                throw null;
            }
        }, "IgniteThread", this.igniteThreadStackSize);
        thread.start();
        this.renderingThread = thread;
        return true;
    }

    public final void maybeResumeRendering() {
        boolean z;
        Surface surface;
        if (this.renderingState == RenderingState.FINISHED || (((z = this.isActivityStarted) && this.surface == null) || !(z || this.supportsForegroundUnfocused))) {
            return;
        }
        waitUntilStateIsAtLeast$default(this, RenderingState.PAUSED, 0L, 2, null);
        int i = WhenMappings.$EnumSwitchMapping$0[this.renderingState.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3 || this.isActivityStarted) {
                    return;
                }
            } else if (!this.isActivityStarted && this.overlaySurface == null) {
                return;
            }
        }
        String str = this.isActivityStarted ? "" : "unfocused";
        String str2 = LOG_TAG;
        Log.d(str2, "Requesting rendering resume ".concat(str));
        if ((this.isActivityStarted || (surface = this.overlaySurface) == null) && (surface = this.surface) == null) {
            Log.w(str2, "No valid surface available for resume");
            surface = null;
        }
        this.visibilityMonitor.onForeground();
        this.renderingState = RenderingState.PENDING_TRANSITION;
        this.renderer.resumeRendering(true ^ this.isActivityStarted, surface);
    }

    @Override // com.amazon.ignitionshared.IgnitionContextListener
    public void onContextCreated(@NotNull IgnitionContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.activity.getLifecycle().addObserver(this);
        context.igniteSurfaceView.getHolder().addCallback(new SurfaceHolderCallback());
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(@NotNull LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        String str = LOG_TAG;
        Log.i(str, "onStart");
        synchronized (this.renderingStateMutex) {
            this.isActivityStarted = true;
            maybeResumeRendering();
        }
        Log.i(str, "Returning from onStart");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(@NotNull LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        String str = LOG_TAG;
        Log.i(str, "onStop");
        synchronized (this.renderingStateMutex) {
            this.isActivityStarted = false;
            pauseRenderingAndWait();
        }
        Log.i(str, "Returning from onStop");
    }

    public final void pauseRenderingAndWait() {
        RenderingState renderingState = RenderingState.PAUSING;
        waitUntilStateIsAtLeast(renderingState, this.killAppOnExcessiveBackgroundTransitionWaitThresholdMS);
        if (this.renderingState.compareTo(RenderingState.PAUSED) <= 0 || this.renderingState.compareTo(RenderingState.FINISHED) >= 0) {
            return;
        }
        Log.d(LOG_TAG, "Requesting rendering pause");
        this.visibilityMonitor.onBackground();
        this.renderingState = RenderingState.PENDING_TRANSITION;
        this.renderer.pauseRendering();
        waitUntilStateIsAtLeast(renderingState, this.killAppOnExcessiveBackgroundTransitionWaitThresholdMS);
    }

    public final void setState(RenderingState renderingState) {
        synchronized (this.renderingStateMutex) {
            Log.d(LOG_TAG, "Rendering state: " + renderingState);
            this.renderingState = renderingState;
            this.renderingStateMutex.notifyAll();
        }
    }

    public final void surfaceChangeWithWait(Surface surface) {
        synchronized (this.surfaceChangeMutex) {
            try {
                this.isSurfaceChanging = true;
                this.renderer.updateIgniteSurfaceRef(surface);
                Log.d(LOG_TAG, "Waiting for surface change to complete, isSurfaceChanging: " + this.isSurfaceChanging);
                while (this.isSurfaceChanging) {
                    this.surfaceChangeMutex.wait();
                }
                Log.d(LOG_TAG, "Surface change complete");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final long timeSinceInvocation(long j) {
        return SystemClock.elapsedRealtime() - j;
    }

    public final void toggleRenderingState(@NotNull Function1<? super RenderingState, ? extends RenderingState> getDesiredState) {
        Intrinsics.checkNotNullParameter(getDesiredState, "getDesiredState");
        synchronized (this.renderingStateMutex) {
            try {
                RenderingState renderingStateInvoke = getDesiredState.invoke(this.renderingState);
                String str = LOG_TAG;
                Log.i(str, "toggleRenderingState(" + renderingStateInvoke + "): currentState=" + this.renderingState);
                if (this.isActivityStarted) {
                    return;
                }
                int i = renderingStateInvoke == null ? -1 : WhenMappings.$EnumSwitchMapping$0[renderingStateInvoke.ordinal()];
                if (i != -1) {
                    if (i == 1) {
                        pauseRenderingAndWait();
                    } else {
                        if (i != 2) {
                            throw new IllegalArgumentException("Invalid desired state provided: " + this.renderingState);
                        }
                        maybeResumeRendering();
                    }
                    Log.i(str, "Returning from toggleRenderingState");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void waitUntilStateIsAtLeast(RenderingState renderingState, long j) {
        Log.d(LOG_TAG, "Waiting for state to be at least " + renderingState + " from " + this.renderingState + " with timeout=" + j);
        boolean z = true;
        this.lifecycleBoundHandler.setInterrupted(true);
        try {
            try {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                if (j != 0) {
                    z = false;
                }
                while (this.renderingState.compareTo(renderingState) < 0 && (SystemClock.elapsedRealtime() - jElapsedRealtime < j || z)) {
                    if (z || !this.shouldKillAppOnExcessiveWaitDuringBackgroundTransition) {
                        this.renderingStateMutex.wait();
                    } else {
                        long jElapsedRealtime2 = j - (SystemClock.elapsedRealtime() - jElapsedRealtime);
                        if (jElapsedRealtime2 > 0) {
                            this.renderingStateMutex.wait(jElapsedRealtime2);
                        }
                    }
                }
                if (this.renderingState.compareTo(renderingState) < 0 && SystemClock.elapsedRealtime() - jElapsedRealtime >= j && this.shouldKillAppOnExcessiveWaitDuringBackgroundTransition) {
                    Log.e(LOG_TAG, "Application did not complete transition to " + renderingState + " in " + j + " milliseconds, terminating");
                    this.exitReasonHandler.saveExcessiveBackgroundWaitTermination();
                    System.exit(0);
                    throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
                }
            } catch (InterruptedException unused) {
                Log.w(LOG_TAG, "Interrupted while waiting for state at least " + renderingState);
                Thread.currentThread().interrupt();
            }
        } finally {
            this.lifecycleBoundHandler.setInterrupted(false);
        }
    }

    @Override // com.amazon.ignitionshared.IgnitionContextListener
    public void onContextDestroyed() {
    }
}
