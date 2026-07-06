package com.amazon.livingroom.deviceproperties;

import androidx.annotation.NonNull;
import com.amazon.livingroom.deviceproperties.DeviceProperties;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractDeviceProperties implements DeviceProperties {
    @Override // com.amazon.livingroom.deviceproperties.DeviceProperties
    @NonNull
    public <T> T get(@NonNull DeviceProperties.Property<T> property) {
        return (T) get(property, this);
    }

    public abstract <T> T get(@NonNull DeviceProperties.Property<T> property, @NonNull DeviceProperties deviceProperties);
}
