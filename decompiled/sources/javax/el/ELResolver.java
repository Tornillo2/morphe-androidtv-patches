package javax.el;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ELResolver {
    public static final String RESOLVABLE_AT_DESIGN_TIME = "resolvableAtDesignTime";
    public static final String TYPE = "type";

    public abstract Class<?> getCommonPropertyType(ELContext eLContext, Object obj);

    public abstract Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj);

    public abstract Class<?> getType(ELContext eLContext, Object obj, Object obj2);

    public abstract Object getValue(ELContext eLContext, Object obj, Object obj2);

    public Object invoke(ELContext eLContext, Object obj, Object obj2, Class<?>[] clsArr, Object[] objArr) {
        return null;
    }

    public abstract boolean isReadOnly(ELContext eLContext, Object obj, Object obj2);

    public abstract void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3);
}
