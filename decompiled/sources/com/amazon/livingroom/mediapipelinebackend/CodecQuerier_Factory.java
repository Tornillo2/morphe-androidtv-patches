package com.amazon.livingroom.mediapipelinebackend;

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
public final class CodecQuerier_Factory implements Factory<CodecQuerier> {
    public final Provider<DeviceProperties> devicePropertiesProvider;

    public CodecQuerier_Factory(Provider<DeviceProperties> provider) {
        this.devicePropertiesProvider = provider;
    }

    public static CodecQuerier_Factory create(Provider<DeviceProperties> provider) {
        return new CodecQuerier_Factory(provider);
    }

    public static CodecQuerier newInstance(DeviceProperties deviceProperties) {
        return new CodecQuerier(deviceProperties);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public CodecQuerier get() {
        return new CodecQuerier(this.devicePropertiesProvider.get());
    }
}
