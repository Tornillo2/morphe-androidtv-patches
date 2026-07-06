package com.amazon.livingroom.deviceproperties.expression;

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
public final class ExpressionEvaluator_Factory implements Factory<ExpressionEvaluator> {
    public final Provider<DeviceProperties> defaultPropertiesProvider;
    public final Provider<DeviceProperties> devicePropertiesProvider;

    public ExpressionEvaluator_Factory(Provider<DeviceProperties> provider, Provider<DeviceProperties> provider2) {
        this.devicePropertiesProvider = provider;
        this.defaultPropertiesProvider = provider2;
    }

    public static ExpressionEvaluator_Factory create(Provider<DeviceProperties> provider, Provider<DeviceProperties> provider2) {
        return new ExpressionEvaluator_Factory(provider, provider2);
    }

    public static ExpressionEvaluator newInstance(DeviceProperties deviceProperties, DeviceProperties deviceProperties2) {
        return new ExpressionEvaluator(deviceProperties, deviceProperties2);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ExpressionEvaluator get() {
        return new ExpressionEvaluator(this.devicePropertiesProvider.get(), this.defaultPropertiesProvider.get());
    }
}
