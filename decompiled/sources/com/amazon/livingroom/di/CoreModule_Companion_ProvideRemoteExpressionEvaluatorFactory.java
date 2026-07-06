package com.amazon.livingroom.di;

import com.amazon.livingroom.deviceproperties.DefaultDeviceProperties;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
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
public final class CoreModule_Companion_ProvideRemoteExpressionEvaluatorFactory implements Factory<ExpressionEvaluator> {
    public final Provider<DefaultDeviceProperties> defaultPropertiesProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;

    public CoreModule_Companion_ProvideRemoteExpressionEvaluatorFactory(Provider<DeviceProperties> provider, Provider<DefaultDeviceProperties> provider2) {
        this.devicePropertiesProvider = provider;
        this.defaultPropertiesProvider = provider2;
    }

    public static CoreModule_Companion_ProvideRemoteExpressionEvaluatorFactory create(Provider<DeviceProperties> provider, Provider<DefaultDeviceProperties> provider2) {
        return new CoreModule_Companion_ProvideRemoteExpressionEvaluatorFactory(provider, provider2);
    }

    public static ExpressionEvaluator provideRemoteExpressionEvaluator(DeviceProperties deviceProperties, DefaultDeviceProperties defaultDeviceProperties) {
        return CoreModule.Companion.provideRemoteExpressionEvaluator(deviceProperties, defaultDeviceProperties);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ExpressionEvaluator get() {
        return CoreModule.Companion.provideRemoteExpressionEvaluator(this.devicePropertiesProvider.get(), this.defaultPropertiesProvider.get());
    }
}
