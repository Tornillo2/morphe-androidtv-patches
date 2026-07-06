package com.amazon.livingroom.mediapipelinebackend;

import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.view.WindowManager;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import com.amazon.ignitionshared.IgnitionContextProvider;
import com.amazon.ignitionshared.LifecycleBoundHandler;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class DisplayModeManager_Factory implements Factory<DisplayModeManager> {
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<DisplayManager> displayManagerProvider;
    public final Provider<DisplayModeMatcher> displayModeMatcherProvider;
    public final Provider<DrmKeyStatusNotifier> drmKeyStatusNotifierProvider;
    public final Provider<Handler> handlerProvider;
    public final Provider<HdcpChecker> hdcpCheckerProvider;
    public final Provider<IgnitionContextProvider> ignitionContextProvider;
    public final Provider<LifecycleBoundHandler> lifecycleBoundHandlerProvider;
    public final Provider<WindowManager> windowManagerProvider;

    public DisplayModeManager_Factory(Provider<WindowManager> provider, Provider<IgnitionContextProvider> provider2, Provider<Handler> provider3, Provider<HdcpChecker> provider4, Provider<DisplayManager> provider5, Provider<DisplayModeMatcher> provider6, Provider<LifecycleBoundHandler> provider7, Provider<DeviceProperties> provider8, Provider<DrmKeyStatusNotifier> provider9) {
        this.windowManagerProvider = provider;
        this.ignitionContextProvider = provider2;
        this.handlerProvider = provider3;
        this.hdcpCheckerProvider = provider4;
        this.displayManagerProvider = provider5;
        this.displayModeMatcherProvider = provider6;
        this.lifecycleBoundHandlerProvider = provider7;
        this.devicePropertiesProvider = provider8;
        this.drmKeyStatusNotifierProvider = provider9;
    }

    public static DisplayModeManager_Factory create(Provider<WindowManager> provider, Provider<IgnitionContextProvider> provider2, Provider<Handler> provider3, Provider<HdcpChecker> provider4, Provider<DisplayManager> provider5, Provider<DisplayModeMatcher> provider6, Provider<LifecycleBoundHandler> provider7, Provider<DeviceProperties> provider8, Provider<DrmKeyStatusNotifier> provider9) {
        return new DisplayModeManager_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static DisplayModeManager newInstance(WindowManager windowManager, IgnitionContextProvider ignitionContextProvider, Handler handler, HdcpChecker hdcpChecker, DisplayManager displayManager, DisplayModeMatcher displayModeMatcher, LifecycleBoundHandler lifecycleBoundHandler, DeviceProperties deviceProperties, DrmKeyStatusNotifier drmKeyStatusNotifier) {
        return new DisplayModeManager(windowManager, ignitionContextProvider, handler, hdcpChecker, displayManager, displayModeMatcher, lifecycleBoundHandler, deviceProperties, drmKeyStatusNotifier);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DisplayModeManager get() {
        return new DisplayModeManager(this.windowManagerProvider.get(), this.ignitionContextProvider.get(), this.handlerProvider.get(), this.hdcpCheckerProvider.get(), this.displayManagerProvider.get(), this.displayModeMatcherProvider.get(), this.lifecycleBoundHandlerProvider.get(), this.devicePropertiesProvider.get(), this.drmKeyStatusNotifierProvider.get());
    }
}
