package com.amazon.livingroom.di;

import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.deviceproperties.OverridableDeviceProperties;
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
public final class CoreModule_Companion_ProvideLocalExpressionEvaluatorFactory implements Factory<ExpressionEvaluator> {
    public final Provider<OverridableDeviceProperties> defaultPropertiesProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;

    public CoreModule_Companion_ProvideLocalExpressionEvaluatorFactory(Provider<DeviceProperties> provider, Provider<OverridableDeviceProperties> provider2) {
        this.devicePropertiesProvider = provider;
        this.defaultPropertiesProvider = provider2;
    }

    public static CoreModule_Companion_ProvideLocalExpressionEvaluatorFactory create(Provider<DeviceProperties> provider, Provider<OverridableDeviceProperties> provider2) {
        return new CoreModule_Companion_ProvideLocalExpressionEvaluatorFactory(provider, provider2);
    }

    public static ExpressionEvaluator provideLocalExpressionEvaluator(DeviceProperties deviceProperties, OverridableDeviceProperties overridableDeviceProperties) {
        return CoreModule.Companion.provideLocalExpressionEvaluator(deviceProperties, overridableDeviceProperties);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ExpressionEvaluator get() {
        return CoreModule.Companion.provideLocalExpressionEvaluator(this.devicePropertiesProvider.get(), this.defaultPropertiesProvider.get());
    }
}
