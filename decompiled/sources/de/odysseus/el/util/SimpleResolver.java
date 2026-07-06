package de.odysseus.el.util;

import java.beans.FeatureDescriptor;
import java.util.Iterator;
import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.ResourceBundleELResolver;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SimpleResolver extends ELResolver {
    public static final ELResolver DEFAULT_RESOLVER_READ_ONLY = new CompositeELResolver() { // from class: de.odysseus.el.util.SimpleResolver.1
        {
            add(new ArrayELResolver(true));
            add(new ListELResolver(true));
            add(new MapELResolver(true));
            add(new ResourceBundleELResolver());
            add(new BeanELResolver(true));
        }
    };
    public static final ELResolver DEFAULT_RESOLVER_READ_WRITE = new CompositeELResolver() { // from class: de.odysseus.el.util.SimpleResolver.2
        {
            add(new ArrayELResolver(false));
            add(new ListELResolver(false));
            add(new MapELResolver(false));
            add(new ResourceBundleELResolver());
            add(new BeanELResolver(false));
        }
    };
    public final CompositeELResolver delegate;
    public final RootPropertyResolver root;

    public SimpleResolver(ELResolver eLResolver, boolean z) {
        CompositeELResolver compositeELResolver = new CompositeELResolver();
        this.delegate = compositeELResolver;
        RootPropertyResolver rootPropertyResolver = new RootPropertyResolver(z);
        this.root = rootPropertyResolver;
        compositeELResolver.add(rootPropertyResolver);
        compositeELResolver.add(eLResolver);
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        return this.delegate.getCommonPropertyType(eLContext, obj);
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        return this.delegate.getFeatureDescriptors(eLContext, obj);
    }

    public RootPropertyResolver getRootPropertyResolver() {
        return this.root;
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        return this.delegate.getType(eLContext, obj, obj2);
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        return this.delegate.getValue(eLContext, obj, obj2);
    }

    @Override // javax.el.ELResolver
    public Object invoke(ELContext eLContext, Object obj, Object obj2, Class<?>[] clsArr, Object[] objArr) {
        return this.delegate.invoke(eLContext, obj, obj2, clsArr, objArr);
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        return this.delegate.isReadOnly(eLContext, obj, obj2);
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        this.delegate.setValue(eLContext, obj, obj2, obj3);
    }

    public SimpleResolver(ELResolver eLResolver) {
        this(eLResolver, false);
    }

    public SimpleResolver(boolean z) {
        this(z ? DEFAULT_RESOLVER_READ_ONLY : DEFAULT_RESOLVER_READ_WRITE, z);
    }

    public SimpleResolver() {
        this(DEFAULT_RESOLVER_READ_WRITE, false);
    }
}
