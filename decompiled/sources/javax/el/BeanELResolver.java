package javax.el;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import j$.util.concurrent.ConcurrentHashMap;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class BeanELResolver extends ELResolver {
    public final ConcurrentHashMap<Class<?>, BeanProperties> cache;
    public ExpressionFactory defaultFactory;
    public final boolean readOnly;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BeanProperties {
        public final Map<String, BeanProperty> map = new HashMap();

        public BeanProperties(Class<?> cls) {
            try {
                for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(cls).getPropertyDescriptors()) {
                    this.map.put(propertyDescriptor.getName(), new BeanProperty(propertyDescriptor));
                }
            } catch (IntrospectionException e) {
                throw new ELException((Throwable) e);
            }
        }

        public BeanProperty getBeanProperty(String str) {
            return this.map.get(str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BeanProperty {
        public final PropertyDescriptor descriptor;
        public Method readMethod;
        public Method writedMethod;

        public BeanProperty(PropertyDescriptor propertyDescriptor) {
            this.descriptor = propertyDescriptor;
        }

        public Class<?> getPropertyType() {
            return this.descriptor.getPropertyType();
        }

        public Method getReadMethod() {
            if (this.readMethod == null) {
                this.readMethod = BeanELResolver.findAccessibleMethod(this.descriptor.getReadMethod());
            }
            return this.readMethod;
        }

        public Method getWriteMethod() {
            if (this.writedMethod == null) {
                this.writedMethod = BeanELResolver.findAccessibleMethod(this.descriptor.getWriteMethod());
            }
            return this.writedMethod;
        }

        public boolean isReadOnly() {
            return getWriteMethod() == null;
        }
    }

    public BeanELResolver(boolean z) {
        this.readOnly = z;
        this.cache = new ConcurrentHashMap<>();
    }

    public static Method findAccessibleMethod(Method method) {
        Method methodFindPublicAccessibleMethod = findPublicAccessibleMethod(method);
        if (methodFindPublicAccessibleMethod != null || method == null || !Modifier.isPublic(method.getModifiers())) {
            return methodFindPublicAccessibleMethod;
        }
        try {
            method.setAccessible(true);
            return method;
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static Method findPublicAccessibleMethod(Method method) {
        Method methodFindPublicAccessibleMethod;
        if (method == null || !Modifier.isPublic(method.getModifiers())) {
            return null;
        }
        if (method.isAccessible() || Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            return method;
        }
        for (Class<?> cls : method.getDeclaringClass().getInterfaces()) {
            try {
                methodFindPublicAccessibleMethod = findPublicAccessibleMethod(cls.getMethod(method.getName(), method.getParameterTypes()));
            } catch (NoSuchMethodException unused) {
            }
            if (methodFindPublicAccessibleMethod != null) {
                return methodFindPublicAccessibleMethod;
            }
        }
        Class<? super Object> superclass = method.getDeclaringClass().getSuperclass();
        if (superclass != null) {
            try {
                Method methodFindPublicAccessibleMethod2 = findPublicAccessibleMethod(superclass.getMethod(method.getName(), method.getParameterTypes()));
                if (methodFindPublicAccessibleMethod2 != null) {
                    return methodFindPublicAccessibleMethod2;
                }
            } catch (NoSuchMethodException unused2) {
            }
        }
        return null;
    }

    private final boolean isResolvable(Object obj) {
        return obj != null;
    }

    public final Object[] coerceParams(ExpressionFactory expressionFactory, Method method, Object[] objArr) {
        Object objNewInstance;
        Object obj;
        Class<?>[] parameterTypes = method.getParameterTypes();
        int length = parameterTypes.length;
        Object[] objArr2 = new Object[length];
        if (!method.isVarArgs()) {
            if (objArr.length != length) {
                throw new ELException("Bad argument count");
            }
            for (int i = 0; i < length; i++) {
                coerceValue(objArr2, i, expressionFactory, objArr[i], parameterTypes[i]);
            }
            return objArr2;
        }
        int length2 = parameterTypes.length - 1;
        if (objArr.length < length2) {
            throw new ELException("Bad argument count");
        }
        for (int i2 = 0; i2 < length2; i2++) {
            coerceValue(objArr2, i2, expressionFactory, objArr[i2], parameterTypes[i2]);
        }
        Class<?> componentType = parameterTypes[length2].getComponentType();
        int length3 = objArr.length - length2;
        if (length3 == 1) {
            obj = objArr[length2];
            if (obj != null && obj.getClass().isArray()) {
                if (!parameterTypes[length2].isInstance(obj)) {
                    int length4 = Array.getLength(obj);
                    objNewInstance = Array.newInstance(componentType, length4);
                    for (int i3 = 0; i3 < length4; i3++) {
                        coerceValue(objNewInstance, i3, expressionFactory, Array.get(obj, i3), componentType);
                    }
                }
                objArr2[length2] = obj;
                return objArr2;
            }
            objNewInstance = Array.newInstance(componentType, 1);
            coerceValue(objNewInstance, 0, expressionFactory, obj, componentType);
        } else {
            objNewInstance = Array.newInstance(componentType, length3);
            for (int i4 = 0; i4 < length3; i4++) {
                coerceValue(objNewInstance, i4, expressionFactory, objArr[length2 + i4], componentType);
            }
        }
        obj = objNewInstance;
        objArr2[length2] = obj;
        return objArr2;
    }

    public final void coerceValue(Object obj, int i, ExpressionFactory expressionFactory, Object obj2, Class<?> cls) {
        if (obj2 != null || cls.isPrimitive()) {
            Array.set(obj, i, expressionFactory.coerceToType(obj2, cls));
        }
    }

    public final Method findMethod(Object obj, String str, Class<?>[] clsArr, int i) {
        if (clsArr != null) {
            try {
                return findAccessibleMethod(obj.getClass().getMethod(str, clsArr));
            } catch (NoSuchMethodException unused) {
                return null;
            }
        }
        Method method = null;
        for (Method method2 : obj.getClass().getMethods()) {
            if (method2.getName().equals(str)) {
                int length = method2.getParameterTypes().length;
                if (method2.isVarArgs() && i >= length - 1) {
                    method = method2;
                } else if (i == length) {
                    return findAccessibleMethod(method2);
                }
            }
        }
        if (method == null) {
            return null;
        }
        return findAccessibleMethod(method);
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (obj != null) {
            return Object.class;
        }
        return null;
    }

    public final ExpressionFactory getExpressionFactory(ELContext eLContext) {
        Object context = eLContext.getContext(ExpressionFactory.class);
        if (context instanceof ExpressionFactory) {
            return (ExpressionFactory) context;
        }
        if (this.defaultFactory == null) {
            this.defaultFactory = ExpressionFactory.newInstance(null);
        }
        return this.defaultFactory;
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            final PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors();
            return new Iterator<FeatureDescriptor>() { // from class: javax.el.BeanELResolver.1
                public int next = 0;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    PropertyDescriptor[] propertyDescriptorArr = propertyDescriptors;
                    return propertyDescriptorArr != null && this.next < propertyDescriptorArr.length;
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException("cannot remove");
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public FeatureDescriptor next() {
                    PropertyDescriptor[] propertyDescriptorArr = propertyDescriptors;
                    int i = this.next;
                    this.next = i + 1;
                    PropertyDescriptor propertyDescriptor = propertyDescriptorArr[i];
                    FeatureDescriptor featureDescriptor = new FeatureDescriptor();
                    featureDescriptor.setDisplayName(propertyDescriptor.getDisplayName());
                    featureDescriptor.setName(propertyDescriptor.getName());
                    featureDescriptor.setShortDescription(propertyDescriptor.getShortDescription());
                    featureDescriptor.setExpert(propertyDescriptor.isExpert());
                    featureDescriptor.setHidden(propertyDescriptor.isHidden());
                    featureDescriptor.setPreferred(propertyDescriptor.isPreferred());
                    featureDescriptor.setValue("type", propertyDescriptor.getPropertyType());
                    featureDescriptor.setValue(ELResolver.RESOLVABLE_AT_DESIGN_TIME, Boolean.TRUE);
                    return featureDescriptor;
                }
            };
        } catch (IntrospectionException unused) {
            return Collections.EMPTY_LIST.iterator();
        }
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null) {
            return null;
        }
        Class<?> propertyType = toBeanProperty(obj, obj2).getPropertyType();
        eLContext.setPropertyResolved(true);
        return propertyType;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null) {
            return null;
        }
        Method readMethod = toBeanProperty(obj, obj2).getReadMethod();
        if (readMethod == null) {
            throw new PropertyNotFoundException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot read property ", obj2));
        }
        try {
            Object objInvoke = readMethod.invoke(obj, null);
            eLContext.setPropertyResolved(true);
            return objInvoke;
        } catch (InvocationTargetException e) {
            throw new ELException(e.getCause());
        } catch (Exception e2) {
            throw new ELException(e2);
        }
    }

    @Override // javax.el.ELResolver
    public Object invoke(ELContext eLContext, Object obj, Object obj2, Class<?>[] clsArr, Object[] objArr) {
        eLContext.getClass();
        if (obj == null) {
            return null;
        }
        if (objArr == null) {
            objArr = new Object[0];
        }
        String string = obj2.toString();
        Method methodFindMethod = findMethod(obj, string, clsArr, objArr.length);
        if (methodFindMethod == null) {
            StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Cannot find method ", string, " with ");
            sbM.append(objArr.length);
            sbM.append(" parameters in ");
            sbM.append(obj.getClass());
            throw new MethodNotFoundException(sbM.toString());
        }
        try {
            Object objInvoke = methodFindMethod.invoke(obj, coerceParams(getExpressionFactory(eLContext), methodFindMethod, objArr));
            eLContext.setPropertyResolved(true);
            return objInvoke;
        } catch (IllegalAccessException e) {
            throw new ELException(e);
        } catch (InvocationTargetException e2) {
            throw new ELException(e2.getCause());
        }
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        boolean z = this.readOnly;
        if (obj == null) {
            return z;
        }
        boolean zIsReadOnly = toBeanProperty(obj, obj2).isReadOnly() | z;
        eLContext.setPropertyResolved(true);
        return zIsReadOnly;
    }

    public final void purgeBeanClasses(ClassLoader classLoader) {
        Iterator<Class<?>> it = this.cache.keySet().iterator();
        while (it.hasNext()) {
            if (classLoader == it.next().getClassLoader()) {
                it.remove();
            }
        }
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        eLContext.getClass();
        if (obj != null) {
            if (this.readOnly) {
                throw new PropertyNotWritableException("resolver is read-only");
            }
            Method writeMethod = toBeanProperty(obj, obj2).getWriteMethod();
            if (writeMethod == null) {
                throw new PropertyNotWritableException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot write property: ", obj2));
            }
            try {
                writeMethod.invoke(obj, obj3);
                eLContext.setPropertyResolved(true);
            } catch (IllegalAccessException e) {
                throw new PropertyNotWritableException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot write property: ", obj2), e);
            } catch (IllegalArgumentException e2) {
                throw new ELException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot write property: ", obj2), e2);
            } catch (InvocationTargetException e3) {
                throw new ELException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot write property: ", obj2), e3.getCause());
            }
        }
    }

    public final BeanProperty toBeanProperty(Object obj, Object obj2) {
        BeanProperties beanPropertiesPutIfAbsent;
        BeanProperties beanProperties = this.cache.get(obj.getClass());
        if (beanProperties == null && (beanPropertiesPutIfAbsent = this.cache.putIfAbsent(obj.getClass(), (beanProperties = new BeanProperties(obj.getClass())))) != null) {
            beanProperties = beanPropertiesPutIfAbsent;
        }
        BeanProperty beanProperty = obj2 == null ? null : beanProperties.getBeanProperty(obj2.toString());
        if (beanProperty != null) {
            return beanProperty;
        }
        throw new PropertyNotFoundException("Could not find property " + obj2 + " in " + obj.getClass());
    }

    public BeanELResolver() {
        this(false);
    }
}
