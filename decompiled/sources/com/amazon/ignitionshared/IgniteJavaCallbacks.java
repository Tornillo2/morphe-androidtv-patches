package com.amazon.ignitionshared;

import com.amazon.ignitionshared.Renderer;
import com.amazon.livingroom.mediapipelinebackend.CalledFromNative;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nIgniteJavaCallbacks.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IgniteJavaCallbacks.kt\ncom/amazon/ignitionshared/IgniteJavaCallbacks\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,52:1\n1#2:53\n*E\n"})
public final class IgniteJavaCallbacks {

    @Nullable
    public Function0<? extends LaunchReason> launchReasonCallback;

    @Nullable
    public Function0<Unit> requestExitToBackgroundCallback;
    public Renderer.StateListener stateListener;

    @NotNull
    public final Object requestExitToBackgroundMutex = new Object();

    @NotNull
    public final Object launchReasonCallbackMutex = new Object();

    @CalledFromNative
    public final int getLaunchReason() {
        int i;
        LaunchReason launchReasonInvoke;
        synchronized (this.launchReasonCallbackMutex) {
            try {
                Function0<? extends LaunchReason> function0 = this.launchReasonCallback;
                i = (function0 == null || (launchReasonInvoke = function0.invoke()) == null) ? LaunchReason.PRELOAD.code : launchReasonInvoke.code;
            } catch (Throwable th) {
                throw th;
            }
        }
        return i;
    }

    @Nullable
    public final Function0<LaunchReason> getLaunchReasonCallback() {
        return this.launchReasonCallback;
    }

    @Nullable
    public final Function0<Unit> getRequestExitToBackgroundCallback() {
        return this.requestExitToBackgroundCallback;
    }

    @CalledFromNative
    public final void onRenderingPaused() {
        Renderer.StateListener stateListener = this.stateListener;
        if (stateListener != null) {
            stateListener.onRenderingPaused();
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("stateListener");
            throw null;
        }
    }

    @CalledFromNative
    public final void onRenderingPausing() {
        Renderer.StateListener stateListener = this.stateListener;
        if (stateListener != null) {
            stateListener.onRenderingPausing();
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("stateListener");
            throw null;
        }
    }

    @CalledFromNative
    public final void onRenderingStarted() {
        Renderer.StateListener stateListener = this.stateListener;
        if (stateListener != null) {
            stateListener.onRenderingStarted();
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("stateListener");
            throw null;
        }
    }

    @CalledFromNative
    public final void onRenderingStartedUnfocused() {
        Renderer.StateListener stateListener = this.stateListener;
        if (stateListener != null) {
            stateListener.onRenderingStartedUnfocused();
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("stateListener");
            throw null;
        }
    }

    @CalledFromNative
    public final void onSurfaceChangeFinish() {
        Renderer.StateListener stateListener = this.stateListener;
        if (stateListener != null) {
            stateListener.onSurfaceChangeFinish();
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("stateListener");
            throw null;
        }
    }

    @CalledFromNative
    public final void requestExitToBackground() {
        synchronized (this.requestExitToBackgroundMutex) {
            Function0<Unit> function0 = this.requestExitToBackgroundCallback;
            if (function0 != null) {
                function0.invoke();
            }
        }
    }

    public final void setLaunchReasonCallback(@Nullable Function0<? extends LaunchReason> function0) {
        synchronized (this.launchReasonCallbackMutex) {
            this.launchReasonCallback = function0;
        }
    }

    public final void setRequestExitToBackgroundCallback(@Nullable Function0<Unit> function0) {
        synchronized (this.requestExitToBackgroundMutex) {
            this.requestExitToBackgroundCallback = function0;
        }
    }

    public final void setStateListener(@NotNull Renderer.StateListener stateListener) {
        Intrinsics.checkNotNullParameter(stateListener, "stateListener");
        this.stateListener = stateListener;
    }
}
