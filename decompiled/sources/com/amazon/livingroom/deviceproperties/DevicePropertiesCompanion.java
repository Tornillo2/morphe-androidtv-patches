package com.amazon.livingroom.deviceproperties;

import com.amazon.livingroom.deviceproperties.DeviceProperties;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DevicePropertiesCompanion {
    public static final Map<String, DeviceProperties.Property<?>> PROPERTIES_BY_NAME = new HashMap();

    public static <T> DeviceProperties.Property<T> registerProperty(Class<T> cls, String str, PropertyGetter<T> propertyGetter) {
        DeviceProperties.Property<T> property = new DeviceProperties.Property<>(cls, str, propertyGetter);
        PROPERTIES_BY_NAME.put(str, property);
        return property;
    }
}
