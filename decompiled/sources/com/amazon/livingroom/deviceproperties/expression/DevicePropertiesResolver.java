package com.amazon.livingroom.deviceproperties.expression;

import androidx.annotation.Nullable;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.reporting.Log;
import java.util.Iterator;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.PropertyNotWritableException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DevicePropertiesResolver extends ELResolver {
    public static final String LOG_TAG = "DevicePropertiesResolver";
    public final DeviceProperties deviceProperties;

    public DevicePropertiesResolver(DeviceProperties deviceProperties) {
        this.deviceProperties = deviceProperties;
    }

    @Nullable
    public final DeviceProperties.Property<?> findDeviceProperty(Object obj) {
        String string = obj.toString();
        DeviceProperties.Property<?> propertyFindProperty = DeviceProperties.CC.findProperty(string);
        if (propertyFindProperty == null) {
            Log.w(LOG_TAG, "Unknown device property: " + string);
        }
        return propertyFindProperty;
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (obj == this.deviceProperties) {
            return String.class;
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public Iterator getFeatureDescriptors(ELContext eLContext, Object obj) {
        return null;
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        if (obj != this.deviceProperties) {
            return Object.class;
        }
        eLContext.setPropertyResolved(true);
        return Object.class;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        if (obj != this.deviceProperties) {
            return null;
        }
        eLContext.setPropertyResolved(true);
        DeviceProperties.Property<?> propertyFindDeviceProperty = findDeviceProperty(obj2);
        if (propertyFindDeviceProperty == null) {
            return null;
        }
        return this.deviceProperties.get(propertyFindDeviceProperty);
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        if (obj == this.deviceProperties) {
            eLContext.setPropertyResolved(true);
        }
        return true;
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        if (obj == this.deviceProperties) {
            throw new PropertyNotWritableException("Device properties are read-only");
        }
    }
}
