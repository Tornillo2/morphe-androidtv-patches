package com.amazon.livingroom.mediapipelinebackend;

import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.AnyThread;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.media3.exoplayer.audio.AudioSink$InitializationException$$ExternalSyntheticOutline0;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import com.amazon.ignitionshared.IgnitionContext;
import com.amazon.ignitionshared.IgnitionContextProvider;
import com.amazon.ignitionshared.LifecycleBoundHandler;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.google.common.util.concurrent.SettableFuture;
import j$.util.function.Consumer$CC;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nDisplayModeManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DisplayModeManager.kt\ncom/amazon/livingroom/mediapipelinebackend/DisplayModeManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,430:1\n1#2:431\n*E\n"})
public final class DisplayModeManager implements com.amazon.avod.mpb.api.callback.DisplayModeManager {

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final DisplayManager displayManager;

    @NotNull
    public final DisplayModeMatcher displayModeMatcher;
    public int expectedDefaultModeId;
    public int expectedModeId;

    @NotNull
    public final Handler handler;

    @Nullable
    public HdcpCheck hdcpCheck;

    @NotNull
    public final HdcpChecker hdcpChecker;

    @NotNull
    public final IgnitionContextProvider ignitionContextProvider;

    @NotNull
    public final LifecycleBoundHandler lifecycleBoundHandler;

    @Nullable
    public Consumer<Exception> onComplete;
    public boolean waitUntilHdcpRecovers;

    @NotNull
    public final WindowManager windowManager;

    /* JADX INFO: renamed from: com.amazon.livingroom.mediapipelinebackend.DisplayModeManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        public AnonymousClass1(Object obj) {
            super(1, obj, DisplayModeManager.class, "onDrmKeyStatusChange", "onDrmKeyStatusChange(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((DisplayModeManager) this.receiver).onDrmKeyStatusChange(z);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public abstract class HdcpCheck implements Runnable {
        public final long checkStartTimeMs;
        public final long pollInterval;

        public HdcpCheck() {
            Object obj = DisplayModeManager.this.deviceProperties.get(DeviceProperties.DISPLAY_MODE_CHANGE_HDCP_POLL_INTERVAL_MS);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            this.pollInterval = ((Number) obj).longValue();
            this.checkStartTimeMs = SystemClock.elapsedRealtime();
        }

        public abstract boolean doRun();

        public final long getCheckStartTimeMs() {
            return this.checkStartTimeMs;
        }

        @UiThread
        public abstract void onDrmKeyStatusChange(boolean z);

        @Override // java.lang.Runnable
        @UiThread
        public final void run() {
            if (Intrinsics.areEqual(DisplayModeManager.this.hdcpCheck, this) && DisplayModeManager.this.onComplete != null && doRun()) {
                DisplayModeManager.this.handler.postDelayed(this, this.pollInterval);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class HdcpLossCheck extends HdcpCheck {
        public final long timeoutMs;

        public HdcpLossCheck() {
            super();
            Object obj = DisplayModeManager.this.deviceProperties.get(DeviceProperties.DISPLAY_MODE_CHANGE_HDCP_DROP_TIMEOUT_MS);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            this.timeoutMs = ((Number) obj).longValue();
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.DisplayModeManager.HdcpCheck
        @UiThread
        public boolean doRun() {
            HdcpVersion currentHdcpVersion = DisplayModeManager.this.hdcpChecker.getCurrentHdcpVersion(true);
            Intrinsics.checkNotNullExpressionValue(currentHdcpVersion, "getCurrentHdcpVersion(...)");
            MpbLog.t("[FRM] HDCP version " + currentHdcpVersion.getFullVersion());
            if (currentHdcpVersion.getMajorVersion() <= 0) {
                MpbLog.w("[FRM] HDCP dropped after a display mode change. Waiting for it to recover...");
                startWaitForRecovery(false);
                return false;
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime() - this.checkStartTimeMs;
            if (jElapsedRealtime < this.timeoutMs) {
                return true;
            }
            MpbLog.w("[FRM] Spent " + jElapsedRealtime + " ms waiting for HDCP to drop - assuming it won't drop");
            DisplayModeManager.this.completeSuccessfully();
            return false;
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.DisplayModeManager.HdcpCheck
        @UiThread
        public void onDrmKeyStatusChange(boolean z) {
            if (z) {
                MpbLog.w("[FRM] DRM keys unusable after display mode change. Waiting for them to recover...");
                startWaitForRecovery(true);
            }
        }

        public final void startWaitForRecovery(boolean z) {
            DisplayModeManager displayModeManager = DisplayModeManager.this;
            displayModeManager.setHdcpCheck(displayModeManager.new HdcpRecoveryCheck(z));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class HdcpRecoveryCheck extends HdcpCheck {
        public boolean hasUnusableKeys;
        public final long timeoutMs;

        public HdcpRecoveryCheck(boolean z) {
            super();
            this.hasUnusableKeys = z;
            Object obj = DisplayModeManager.this.deviceProperties.get(DeviceProperties.DISPLAY_MODE_CHANGE_HDCP_RECOVERY_TIMEOUT_MS);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            this.timeoutMs = ((Number) obj).longValue();
        }

        @UiThread
        public final boolean checkRecovery() {
            HdcpVersion currentHdcpVersion = DisplayModeManager.this.hdcpChecker.getCurrentHdcpVersion(true);
            Intrinsics.checkNotNullExpressionValue(currentHdcpVersion, "getCurrentHdcpVersion(...)");
            MpbLog.t("[FRM] HDCP version " + currentHdcpVersion.getFullVersion());
            if (currentHdcpVersion.getMajorVersion() > 0 && !this.hasUnusableKeys) {
                DisplayModeManager.this.completeSuccessfully();
                return true;
            }
            MpbLog.t("[FRM] Still waiting for HDCP to recover: version=" + currentHdcpVersion.getFullVersion() + " hasUnusableKeys=" + this.hasUnusableKeys);
            return false;
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.DisplayModeManager.HdcpCheck
        @UiThread
        public boolean doRun() {
            if (checkRecovery()) {
                return false;
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime() - this.checkStartTimeMs;
            if (jElapsedRealtime < this.timeoutMs) {
                return true;
            }
            MpbLog.e("[FRM] Spent " + jElapsedRealtime + " ms waiting for HDCP to recover - assuming it won't");
            DisplayModeManager.this.complete(new TimeoutException(ChannelLogoUtils$$ExternalSyntheticOutline0.m("Timed out waiting for HDCP to recover after ", jElapsedRealtime, " after display mode change")));
            return false;
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.DisplayModeManager.HdcpCheck
        @UiThread
        public void onDrmKeyStatusChange(boolean z) {
            this.hasUnusableKeys = z;
            if (z) {
                MpbLog.w("[FRM] DRM keys are still unusable after display mode change");
            } else {
                MpbLog.w("[FRM] DRM keys are usable again after display mode change. Checking if the mode change is complete...");
                checkRecovery();
            }
        }
    }

    public static /* synthetic */ int $r8$lambda$1iaPdvErKalqhdGt9ooyMlrzfJQ(Display display) {
        clearTargetFrameRate$lambda$6(display);
        return 0;
    }

    @Inject
    @UiThread
    public DisplayModeManager(@NotNull WindowManager windowManager, @NotNull IgnitionContextProvider ignitionContextProvider, @NotNull Handler handler, @NotNull HdcpChecker hdcpChecker, @NotNull DisplayManager displayManager, @NotNull DisplayModeMatcher displayModeMatcher, @NotNull LifecycleBoundHandler lifecycleBoundHandler, @NotNull DeviceProperties deviceProperties, @NotNull DrmKeyStatusNotifier drmKeyStatusNotifier) {
        Intrinsics.checkNotNullParameter(windowManager, "windowManager");
        Intrinsics.checkNotNullParameter(ignitionContextProvider, "ignitionContextProvider");
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(hdcpChecker, "hdcpChecker");
        Intrinsics.checkNotNullParameter(displayManager, "displayManager");
        Intrinsics.checkNotNullParameter(displayModeMatcher, "displayModeMatcher");
        Intrinsics.checkNotNullParameter(lifecycleBoundHandler, "lifecycleBoundHandler");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(drmKeyStatusNotifier, "drmKeyStatusNotifier");
        this.windowManager = windowManager;
        this.ignitionContextProvider = ignitionContextProvider;
        this.handler = handler;
        this.hdcpChecker = hdcpChecker;
        this.displayManager = displayManager;
        this.displayModeMatcher = displayModeMatcher;
        this.lifecycleBoundHandler = lifecycleBoundHandler;
        this.deviceProperties = deviceProperties;
        if (Build.VERSION.SDK_INT >= 23) {
            displayManager.registerDisplayListener(new DisplayListener(), handler);
        }
        drmKeyStatusNotifier.setKeyStatusChangeCallback(new AnonymousClass1(this));
    }

    public static final void clearTargetFrameRate$lambda$5(SettableFuture settableFuture, Exception exc) {
        if (exc != null) {
            settableFuture.setException(exc);
        } else {
            settableFuture.set(Unit.INSTANCE);
        }
    }

    public static final int clearTargetFrameRate$lambda$6(Display it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return 0;
    }

    public static final Unit clearTargetFrameRate$lambda$7(SettableFuture settableFuture) throws ExecutionException, InterruptedException, TimeoutException {
        settableFuture.blockingGet(30L, TimeUnit.SECONDS);
        return Unit.INSTANCE;
    }

    public static final void onDrmKeyStatusChange$lambda$9(DisplayModeManager displayModeManager, boolean z) {
        HdcpCheck hdcpCheck = displayModeManager.hdcpCheck;
        if (hdcpCheck == null) {
            MpbLog.t("[FRM] onDrmKeyStatusChange called but we are not waiting for a display mode change to complete");
            return;
        }
        MpbLog.t("[FRM] onDrmKeyStatusChange(" + z + ")");
        hdcpCheck.onDrmKeyStatusChange(z);
    }

    public static final int setTargetFrameRate$lambda$4(DisplayModeManager displayModeManager, float f, Display display) {
        Display.Mode mode;
        Display.Mode mode2;
        Intrinsics.checkNotNullParameter(display, "display");
        Display.Mode[] supportedModes = display.getSupportedModes();
        Display.Mode mode3 = null;
        int i = 0;
        if (displayModeManager.onComplete == null) {
            mode2 = display.getMode();
            Intrinsics.checkNotNullExpressionValue(mode2, "getMode(...)");
        } else {
            int i2 = displayModeManager.expectedModeId;
            if (i2 == 0) {
                i2 = displayModeManager.expectedDefaultModeId;
            }
            Intrinsics.checkNotNull(supportedModes);
            int length = supportedModes.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    mode = null;
                    break;
                }
                mode = supportedModes[i3];
                if (mode.getModeId() == i2) {
                    break;
                }
                i3++;
            }
            if (mode == null) {
                mode2 = display.getMode();
                Intrinsics.checkNotNullExpressionValue(mode2, "getMode(...)");
            } else {
                mode2 = mode;
            }
        }
        if (displayModeManager.expectedDefaultModeId != 0) {
            Intrinsics.checkNotNull(supportedModes);
            int length2 = supportedModes.length;
            while (true) {
                if (i >= length2) {
                    break;
                }
                Display.Mode mode4 = supportedModes[i];
                if (mode4.getModeId() == displayModeManager.expectedDefaultModeId) {
                    mode3 = mode4;
                    break;
                }
                i++;
            }
            if (mode3 == null) {
                mode3 = display.getMode();
            }
        } else {
            mode3 = display.getMode();
        }
        DisplayModeMatcher displayModeMatcher = displayModeManager.displayModeMatcher;
        Display.Mode[] supportedModes2 = display.getSupportedModes();
        Intrinsics.checkNotNullExpressionValue(supportedModes2, "getSupportedModes(...)");
        Intrinsics.checkNotNull(mode3);
        return displayModeMatcher.findBestMode(supportedModes2, f, mode2, mode3).getModeId();
    }

    @Override // com.amazon.avod.mpb.api.callback.DisplayModeManager
    @WorkerThread
    public void clearTargetFrameRate() {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        MpbLog.w("[FRM] Restoring default display mode");
        final SettableFuture settableFutureCreate = SettableFuture.create();
        setMode(new Consumer() { // from class: com.amazon.livingroom.mediapipelinebackend.DisplayModeManager$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DisplayModeManager.clearTargetFrameRate$lambda$5(settableFutureCreate, (Exception) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        }, new DisplayModeManager$$ExternalSyntheticLambda5());
        this.lifecycleBoundHandler.run(new Function0() { // from class: com.amazon.livingroom.mediapipelinebackend.DisplayModeManager$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DisplayModeManager.clearTargetFrameRate$lambda$7(settableFutureCreate);
            }
        });
    }

    @UiThread
    public final void complete(Exception exc) {
        this.hdcpChecker.setFreeze(false);
        Consumer<Exception> consumer = this.onComplete;
        if (consumer != null) {
            consumer.accept(exc);
        }
        this.onComplete = null;
    }

    @UiThread
    public final void completeExceptionally(Exception exc) {
        complete(exc);
    }

    @UiThread
    public final void completeSuccessfully() {
        MpbLog.w("[FRM] Finished setting refresh mode");
        complete(null);
    }

    @UiThread
    public final Display getDisplay() {
        IgnitionContext ignitionContext;
        Window window;
        if (Build.VERSION.SDK_INT < 30 || (ignitionContext = this.ignitionContextProvider.context) == null || (window = ignitionContext.window) == null) {
            Display defaultDisplay = this.windowManager.getDefaultDisplay();
            Intrinsics.checkNotNullExpressionValue(defaultDisplay, "getDefaultDisplay(...)");
            return defaultDisplay;
        }
        Display display = window.getContext().getDisplay();
        Intrinsics.checkNotNullExpressionValue(display, "getDisplay(...)");
        return display;
    }

    @RequiresApi(23)
    @UiThread
    public final int getResolvedExpectedModeId(Display display) {
        if (this.onComplete == null) {
            return display.getMode().getModeId();
        }
        int i = this.expectedModeId;
        return i == 0 ? this.expectedDefaultModeId : i;
    }

    @AnyThread
    public final void onDrmKeyStatusChange(final boolean z) {
        this.handler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.DisplayModeManager$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                DisplayModeManager.onDrmKeyStatusChange$lambda$9(this.f$0, z);
            }
        });
    }

    public final void setHdcpCheck(HdcpCheck hdcpCheck) {
        this.hdcpCheck = hdcpCheck;
        Handler handler = this.handler;
        Object obj = this.deviceProperties.get(DeviceProperties.DISPLAY_MODE_CHANGE_HDCP_POLL_INTERVAL_MS);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        handler.postDelayed(hdcpCheck, ((Number) obj).longValue());
    }

    @AnyThread
    @RequiresApi(23)
    public final void setMode(final Consumer<Exception> consumer, final Function1<? super Display, Integer> function1) {
        this.handler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.DisplayModeManager$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.setModeInUiThread(consumer, function1);
            }
        });
    }

    @RequiresApi(23)
    @UiThread
    public final void setModeInUiThread(Consumer<Exception> consumer, Function1<? super Display, Integer> function1) {
        IgnitionContext ignitionContext = this.ignitionContextProvider.context;
        Window window = ignitionContext != null ? ignitionContext.window : null;
        if (window == null) {
            MpbLog.w("[FRM] Skipping display mode change because we don't have a window");
            consumer.accept(null);
            return;
        }
        Display display = getDisplay();
        int iIntValue = function1.invoke(display).intValue();
        Consumer<Exception> consumer2 = this.onComplete;
        if (consumer2 != null) {
            consumer2.accept(new CancellationException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Interrupted by another display mode change to mode ", iIntValue)));
        } else if (this.expectedModeId == 0) {
            int modeId = display.getMode().getModeId();
            this.expectedDefaultModeId = modeId;
            MpbLog.i("[FRM] The default display mode is expected to be " + modeId);
        }
        int resolvedExpectedModeId = getResolvedExpectedModeId(display);
        if (resolvedExpectedModeId == iIntValue || (iIntValue == 0 && this.expectedDefaultModeId == resolvedExpectedModeId)) {
            this.expectedModeId = iIntValue;
            if (this.onComplete == null) {
                MpbLog.i("[FRM] Skipping redundant display mode switch to mode " + iIntValue);
                consumer.accept(null);
                return;
            }
            MpbLog.i("[FRM] Display mode switch to mode " + iIntValue + " already in progress");
            this.onComplete = consumer;
            return;
        }
        MpbLog.w("[FRM] Switching to display mode " + iIntValue);
        this.onComplete = consumer;
        this.expectedModeId = iIntValue;
        this.hdcpChecker.setFreeze(true);
        HdcpVersion currentHdcpVersion = this.hdcpChecker.getCurrentHdcpVersion(true);
        Intrinsics.checkNotNullExpressionValue(currentHdcpVersion, "getCurrentHdcpVersion(...)");
        this.waitUntilHdcpRecovers = currentHdcpVersion.getMajorVersion() > 0;
        WindowManager.LayoutParams attributes = window.getAttributes();
        Intrinsics.checkNotNullExpressionValue(attributes, "getAttributes(...)");
        attributes.preferredDisplayModeId = iIntValue;
        window.setAttributes(attributes);
    }

    @Override // com.amazon.avod.mpb.api.callback.DisplayModeManager
    @AnyThread
    public void setTargetFrameRate(final float f, @NotNull final Consumer<Exception> onCompleted) {
        Intrinsics.checkNotNullParameter(onCompleted, "onCompleted");
        int i = Build.VERSION.SDK_INT;
        if (i < 23) {
            MpbLog.i("[FRM] Frame rate matching is not available on Android SDK " + i);
            this.handler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.DisplayModeManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    onCompleted.accept(null);
                }
            });
            return;
        }
        if (!((Boolean) this.deviceProperties.get(DeviceProperties.FRAME_RATE_MATCHING_ENABLED)).booleanValue()) {
            MpbLog.i("[FRM] Frame rate matching is disabled by device properties");
            this.handler.post(new Runnable() { // from class: com.amazon.livingroom.mediapipelinebackend.DisplayModeManager$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    onCompleted.accept(null);
                }
            });
        } else {
            MpbLog.w("[FRM] Attempting to set refresh rate to " + f);
            setMode(onCompleted, new Function1() { // from class: com.amazon.livingroom.mediapipelinebackend.DisplayModeManager$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(DisplayModeManager.setTargetFrameRate$lambda$4(this.f$0, f, (Display) obj));
                }
            });
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    @UiThread
    @SourceDebugExtension({"SMAP\nDisplayModeManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DisplayModeManager.kt\ncom/amazon/livingroom/mediapipelinebackend/DisplayModeManager$DisplayListener\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,430:1\n13472#2,2:431\n*S KotlinDebug\n*F\n+ 1 DisplayModeManager.kt\ncom/amazon/livingroom/mediapipelinebackend/DisplayModeManager$DisplayListener\n*L\n236#1:431,2\n*E\n"})
    public final class DisplayListener implements DisplayManager.DisplayListener {
        public DisplayListener() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
            Display display = DisplayModeManager.this.getDisplay();
            if (i != display.getDisplayId()) {
                MpbLog.i("[FRM] Display " + i + " changed but the application is shown on display " + display.getDisplayId());
                return;
            }
            Display display2 = DisplayModeManager.this.displayManager.getDisplay(i);
            Display.Mode mode = display2.getMode();
            int modeId = display2.getMode().getModeId();
            int flags = display2.getFlags();
            int physicalWidth = mode.getPhysicalWidth();
            int physicalHeight = mode.getPhysicalHeight();
            float refreshRate = mode.getRefreshRate();
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("[FRM] Display ", i, " has changed. Now set to mode ", modeId, " (");
            AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sbM, physicalWidth, "x", physicalHeight, "@");
            sbM.append(refreshRate);
            sbM.append(") flags=");
            sbM.append(flags);
            sbM.append(" (SUPPORTS_PROTECTED_BUFFERS=");
            AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sbM, flags & 1, ", SECURE=", flags & 2, ", PRESENTATION=");
            sbM.append(flags & 8);
            sbM.append(", PRIVATE=");
            sbM.append(flags & 4);
            sbM.append(")");
            MpbLog.i(sbM.toString());
            MpbLog.t("[FRM] Supported display modes:");
            Display.Mode[] supportedModes = display2.getSupportedModes();
            Intrinsics.checkNotNullExpressionValue(supportedModes, "getSupportedModes(...)");
            for (Display.Mode mode2 : supportedModes) {
                int modeId2 = mode2.getModeId();
                int physicalWidth2 = mode2.getPhysicalWidth();
                int physicalHeight2 = mode2.getPhysicalHeight();
                float refreshRate2 = mode2.getRefreshRate();
                StringBuilder sbM2 = MutableFloatList$$ExternalSyntheticOutline0.m("[FRM]     Supported display mode id=", modeId2, StringUtils.SPACE, physicalWidth2, "x");
                sbM2.append(physicalHeight2);
                sbM2.append("@");
                sbM2.append(refreshRate2);
                MpbLog.t(sbM2.toString());
            }
            MpbLog.t("[FRM] End of supported display modes");
            DisplayModeManager displayModeManager = DisplayModeManager.this;
            if (displayModeManager.onComplete == null || modeId != displayModeManager.getResolvedExpectedModeId(display2)) {
                return;
            }
            DisplayModeManager displayModeManager2 = DisplayModeManager.this;
            if (!displayModeManager2.waitUntilHdcpRecovers) {
                MpbLog.i("[FRM] Display " + i + " has changed to expected mode " + displayModeManager2.expectedModeId + ", not waiting for HDCP to recover");
                DisplayModeManager.this.completeSuccessfully();
                return;
            }
            MpbLog.i("[FRM] Display " + i + " has changed to expected mode " + displayModeManager2.expectedModeId + ", now waiting for DRM to recover");
            DisplayModeManager displayModeManager3 = DisplayModeManager.this;
            displayModeManager3.setHdcpCheck(displayModeManager3.new HdcpLossCheck());
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
        }
    }
}
