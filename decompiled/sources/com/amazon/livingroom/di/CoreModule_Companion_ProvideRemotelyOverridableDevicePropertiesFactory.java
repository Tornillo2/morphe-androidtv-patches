package com.amazon.livingroom.di;

import com.amazon.livingroom.deviceproperties.OverridableDeviceProperties;
import com.amazon.livingroom.deviceproperties.PlatformDeviceProperties;
import com.amazon.livingroom.deviceproperties.RemoteOverridesProvider;
import com.amazon.livingroom.deviceproperties.expression.ExpressionEvaluator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named"})
public final class CoreModule_Companion_ProvideRemotelyOverridableDevicePropertiesFactory implements Factory<OverridableDeviceProperties> {
    public final Provider<PlatformDeviceProperties> defaultPropertiesProvider;
    public final Provider<ExpressionEvaluator> expressionEvaluatorProvider;
    public final Provider<RemoteOverridesProvider> remoteOverridesProvider;

    public CoreModule_Companion_ProvideRemotelyOverridableDevicePropertiesFactory(Provider<PlatformDeviceProperties> provider, Provider<RemoteOverridesProvider> provider2, Provider<ExpressionEvaluator> provider3) {
        this.defaultPropertiesProvider = provider;
        this.remoteOverridesProvider = provider2;
        this.expressionEvaluatorProvider = provider3;
    }

    public static CoreModule_Companion_ProvideRemotelyOverridableDevicePropertiesFactory create(Provider<PlatformDeviceProperties> provider, Provider<RemoteOverridesProvider> provider2, Provider<ExpressionEvaluator> provider3) {
        return new CoreModule_Companion_ProvideRemotelyOverridableDevicePropertiesFactory(provider, provider2, provider3);
    }

    public static OverridableDeviceProperties provideRemotelyOverridableDeviceProperties(PlatformDeviceProperties platformDeviceProperties, RemoteOverridesProvider remoteOverridesProvider, javax.inject.Provider<ExpressionEvaluator> provider) {
        return CoreModule.Companion.provideRemotelyOverridableDeviceProperties(platformDeviceProperties, remoteOverridesProvider, provider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public OverridableDeviceProperties get() {
        return CoreModule.Companion.provideRemotelyOverridableDeviceProperties(this.defaultPropertiesProvider.get(), this.remoteOverridesProvider.get(), this.expressionEvaluatorProvider);
    }
}
