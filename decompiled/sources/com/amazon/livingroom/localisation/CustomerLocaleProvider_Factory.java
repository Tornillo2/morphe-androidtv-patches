package com.amazon.livingroom.localisation;

import com.amazon.livingroom.appstartupconfig.AppStartupConfigProvider;
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
public final class CustomerLocaleProvider_Factory implements Factory<CustomerLocaleProvider> {
    public final Provider<AppStartupConfigProvider> appStartupConfigProvider;
    public final Provider<DeviceLocaleProvider> deviceLocaleProvider;

    public CustomerLocaleProvider_Factory(Provider<DeviceLocaleProvider> provider, Provider<AppStartupConfigProvider> provider2) {
        this.deviceLocaleProvider = provider;
        this.appStartupConfigProvider = provider2;
    }

    public static CustomerLocaleProvider_Factory create(Provider<DeviceLocaleProvider> provider, Provider<AppStartupConfigProvider> provider2) {
        return new CustomerLocaleProvider_Factory(provider, provider2);
    }

    public static CustomerLocaleProvider newInstance(DeviceLocaleProvider deviceLocaleProvider, AppStartupConfigProvider appStartupConfigProvider) {
        return new CustomerLocaleProvider(deviceLocaleProvider, appStartupConfigProvider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public CustomerLocaleProvider get() {
        return new CustomerLocaleProvider(this.deviceLocaleProvider.get(), this.appStartupConfigProvider.get());
    }
}
