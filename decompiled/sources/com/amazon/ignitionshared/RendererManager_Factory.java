package com.amazon.ignitionshared;

import com.amazon.ignitionshared.RendererManager;
import com.amazon.ignitionshared.tvinput.TifOverlaySurfaceHolderCallbackProvider;
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
public final class RendererManager_Factory implements Factory<RendererManager> {
    public final Provider<IgnitionContextProvider> contextProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;
    public final Provider<RendererManager.ExitCallback> exitCallbackProvider;
    public final Provider<ExitReasonHandler> exitReasonHandlerProvider;
    public final Provider<LifecycleBoundHandler> lifecycleBoundHandlerProvider;
    public final Provider<Renderer> rendererProvider;
    public final Provider<TifOverlaySurfaceHolderCallbackProvider> tifOverlaySurfaceHolderCallbackProvider;
    public final Provider<ApplicationVisibilityMonitor> visibilityMonitorProvider;

    public RendererManager_Factory(Provider<IgnitionContextProvider> provider, Provider<DeviceProperties> provider2, Provider<TifOverlaySurfaceHolderCallbackProvider> provider3, Provider<Renderer> provider4, Provider<LifecycleBoundHandler> provider5, Provider<ApplicationVisibilityMonitor> provider6, Provider<ExitReasonHandler> provider7, Provider<RendererManager.ExitCallback> provider8) {
        this.contextProvider = provider;
        this.devicePropertiesProvider = provider2;
        this.tifOverlaySurfaceHolderCallbackProvider = provider3;
        this.rendererProvider = provider4;
        this.lifecycleBoundHandlerProvider = provider5;
        this.visibilityMonitorProvider = provider6;
        this.exitReasonHandlerProvider = provider7;
        this.exitCallbackProvider = provider8;
    }

    public static RendererManager_Factory create(Provider<IgnitionContextProvider> provider, Provider<DeviceProperties> provider2, Provider<TifOverlaySurfaceHolderCallbackProvider> provider3, Provider<Renderer> provider4, Provider<LifecycleBoundHandler> provider5, Provider<ApplicationVisibilityMonitor> provider6, Provider<ExitReasonHandler> provider7, Provider<RendererManager.ExitCallback> provider8) {
        return new RendererManager_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static RendererManager newInstance(IgnitionContextProvider ignitionContextProvider, DeviceProperties deviceProperties, TifOverlaySurfaceHolderCallbackProvider tifOverlaySurfaceHolderCallbackProvider, Renderer renderer, LifecycleBoundHandler lifecycleBoundHandler, ApplicationVisibilityMonitor applicationVisibilityMonitor, ExitReasonHandler exitReasonHandler, RendererManager.ExitCallback exitCallback) {
        return new RendererManager(ignitionContextProvider, deviceProperties, tifOverlaySurfaceHolderCallbackProvider, renderer, lifecycleBoundHandler, applicationVisibilityMonitor, exitReasonHandler, exitCallback);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public RendererManager get() {
        return new RendererManager(this.contextProvider.get(), this.devicePropertiesProvider.get(), this.tifOverlaySurfaceHolderCallbackProvider.get(), this.rendererProvider.get(), this.lifecycleBoundHandlerProvider.get(), this.visibilityMonitorProvider.get(), this.exitReasonHandlerProvider.get(), this.exitCallbackProvider.get());
    }
}
