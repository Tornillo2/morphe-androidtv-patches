package de.odysseus.el.util;

import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import j$.util.DesugarCollections;
import java.beans.FeatureDescriptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.PropertyNotFoundException;
import javax.el.PropertyNotWritableException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class RootPropertyResolver extends ELResolver {
    public final Map<String, Object> map;
    public final boolean readOnly;

    public RootPropertyResolver() {
        this(false);
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (eLContext == null) {
            return String.class;
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        return null;
    }

    public Object getProperty(String str) {
        return this.map.get(str);
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        if (resolve(eLContext, obj, obj2)) {
            return Object.class;
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        if (!resolve(eLContext, obj, obj2)) {
            return null;
        }
        String str = (String) obj2;
        if (isProperty(str)) {
            return getProperty(str);
        }
        throw new PropertyNotFoundException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot find property ", obj2));
    }

    @Override // javax.el.ELResolver
    public Object invoke(ELContext eLContext, Object obj, Object obj2, Class<?>[] clsArr, Object[] objArr) {
        if (!resolve(eLContext, obj, obj2)) {
            return null;
        }
        throw new NullPointerException("Cannot invoke method " + obj2 + " on null");
    }

    public boolean isProperty(String str) {
        return this.map.containsKey(str);
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        if (resolve(eLContext, obj, obj2)) {
            return this.readOnly;
        }
        return false;
    }

    public final boolean isResolvable(Object obj) {
        return obj == null;
    }

    public Iterable<String> properties() {
        return this.map.keySet();
    }

    public final boolean resolve(ELContext eLContext, Object obj, Object obj2) {
        eLContext.setPropertyResolved(obj == null && (obj2 instanceof String));
        return eLContext.isPropertyResolved();
    }

    public void setProperty(String str, Object obj) {
        this.map.put(str, obj);
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) throws PropertyNotWritableException {
        if (resolve(eLContext, obj, obj2)) {
            if (this.readOnly) {
                throw new PropertyNotWritableException("Resolver is read only!");
            }
            setProperty((String) obj2, obj3);
        }
    }

    public RootPropertyResolver(boolean z) {
        this.map = DesugarCollections.synchronizedMap(new HashMap());
        this.readOnly = z;
    }
}
