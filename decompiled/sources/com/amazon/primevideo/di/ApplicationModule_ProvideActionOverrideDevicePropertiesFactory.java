package com.amazon.primevideo.di;

import com.amazon.primevideo.BuildConfig;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class ApplicationModule_ProvideActionOverrideDevicePropertiesFactory implements Factory<String> {
    public final ApplicationModule module;

    public ApplicationModule_ProvideActionOverrideDevicePropertiesFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideActionOverrideDevicePropertiesFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideActionOverrideDevicePropertiesFactory(applicationModule);
    }

    public static String provideActionOverrideDeviceProperties(ApplicationModule applicationModule) {
        applicationModule.getClass();
        return BuildConfig.ACTION_OVERRIDE_DEVICE_PROPERTIES;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        get();
        return BuildConfig.ACTION_OVERRIDE_DEVICE_PROPERTIES;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public String get() {
        provideActionOverrideDeviceProperties(this.module);
        return BuildConfig.ACTION_OVERRIDE_DEVICE_PROPERTIES;
    }
}
