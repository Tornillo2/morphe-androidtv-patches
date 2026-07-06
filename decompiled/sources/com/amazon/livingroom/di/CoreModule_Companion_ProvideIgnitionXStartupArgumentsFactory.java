package com.amazon.livingroom.di;

import com.amazon.livingroom.deviceproperties.DeviceProperties;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class CoreModule_Companion_ProvideIgnitionXStartupArgumentsFactory implements Factory<String[]> {
    public final Provider<String> applicationIdProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;

    public CoreModule_Companion_ProvideIgnitionXStartupArgumentsFactory(Provider<DeviceProperties> provider, Provider<String> provider2) {
        this.devicePropertiesProvider = provider;
        this.applicationIdProvider = provider2;
    }

    public static CoreModule_Companion_ProvideIgnitionXStartupArgumentsFactory create(Provider<DeviceProperties> provider, Provider<String> provider2) {
        return new CoreModule_Companion_ProvideIgnitionXStartupArgumentsFactory(provider, provider2);
    }

    public static String[] provideIgnitionXStartupArguments(DeviceProperties deviceProperties, String str) {
        String[] strArrProvideIgnitionXStartupArguments = CoreModule.Companion.provideIgnitionXStartupArguments(deviceProperties, str);
        Preconditions.checkNotNullFromProvides(strArrProvideIgnitionXStartupArguments);
        return strArrProvideIgnitionXStartupArguments;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public String[] get() {
        return provideIgnitionXStartupArguments(this.devicePropertiesProvider.get(), this.applicationIdProvider.get());
    }
}
