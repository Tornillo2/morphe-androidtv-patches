package com.amazon.livingroom.mediapipelinebackend;

import android.content.Context;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata({"com.amazon.livingroom.di.ApplicationContext"})
public final class FtvMpbContext_Factory implements Factory<FtvMpbContext> {
    public final Provider<FtvMpbApi.Factory> apiFactoryProvider;
    public final Provider<Context> applicationContextProvider;
    public final Provider<ApplicationVisibilityMonitor> applicationVisibilityMonitorProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;

    public FtvMpbContext_Factory(Provider<Context> provider, Provider<FtvMpbApi.Factory> provider2, Provider<DeviceProperties> provider3, Provider<ApplicationVisibilityMonitor> provider4) {
        this.applicationContextProvider = provider;
        this.apiFactoryProvider = provider2;
        this.devicePropertiesProvider = provider3;
        this.applicationVisibilityMonitorProvider = provider4;
    }

    public static FtvMpbContext_Factory create(Provider<Context> provider, Provider<FtvMpbApi.Factory> provider2, Provider<DeviceProperties> provider3, Provider<ApplicationVisibilityMonitor> provider4) {
        return new FtvMpbContext_Factory(provider, provider2, provider3, provider4);
    }

    public static FtvMpbContext newInstance(Context context, FtvMpbApi.Factory factory, DeviceProperties deviceProperties, ApplicationVisibilityMonitor applicationVisibilityMonitor) {
        return new FtvMpbContext(context, factory, deviceProperties, applicationVisibilityMonitor);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public FtvMpbContext get() {
        return new FtvMpbContext(this.applicationContextProvider.get(), this.apiFactoryProvider.get(), this.devicePropertiesProvider.get(), this.applicationVisibilityMonitorProvider.get());
    }
}
