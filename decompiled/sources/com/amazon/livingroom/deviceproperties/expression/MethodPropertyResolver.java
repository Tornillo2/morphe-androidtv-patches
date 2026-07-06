package com.amazon.livingroom.deviceproperties.expression;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.el.BeanELResolver;
import javax.el.ELContext;
import javax.el.ELResolver;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MethodPropertyResolver extends ELResolver {
    public static final Set<String> FORBIDDEN_METHODS = new HashSet();
    public BeanELResolver delegate;

    public MethodPropertyResolver() {
        Set<String> set = FORBIDDEN_METHODS;
        set.add("clone");
        set.add("finalize");
        set.add("getClass");
        set.add("notify");
        set.add("notifyAll");
        set.add("wait");
        this.delegate = new BeanELResolver(true);
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        return null;
    }

    @Override // javax.el.ELResolver
    public Iterator getFeatureDescriptors(ELContext eLContext, Object obj) {
        return null;
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        return null;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        return null;
    }

    @Override // javax.el.ELResolver
    public Object invoke(ELContext eLContext, Object obj, Object obj2, Class<?>[] clsArr, Object[] objArr) {
        if (obj2 == null || !FORBIDDEN_METHODS.contains(obj2.toString())) {
            return this.delegate.invoke(eLContext, obj, obj2, clsArr, objArr);
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        return false;
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
    }
}
