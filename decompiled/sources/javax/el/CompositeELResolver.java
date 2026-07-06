package javax.el;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class CompositeELResolver extends ELResolver {
    public final List<ELResolver> resolvers = new ArrayList();

    public void add(ELResolver eLResolver) {
        if (eLResolver == null) {
            throw new NullPointerException("resolver must not be null");
        }
        this.resolvers.add(eLResolver);
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        int size = this.resolvers.size();
        Class<?> cls = null;
        for (int i = 0; i < size; i++) {
            Class<?> commonPropertyType = this.resolvers.get(i).getCommonPropertyType(eLContext, obj);
            if (commonPropertyType != null) {
                if (cls == null || commonPropertyType.isAssignableFrom(cls)) {
                    cls = commonPropertyType;
                } else if (!cls.isAssignableFrom(commonPropertyType)) {
                    cls = Object.class;
                }
            }
        }
        return cls;
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(final ELContext eLContext, final Object obj) {
        return new Iterator<FeatureDescriptor>() { // from class: javax.el.CompositeELResolver.1
            public Iterator<FeatureDescriptor> empty = Collections.EMPTY_LIST.iterator();
            public Iterator<FeatureDescriptor> features = this.empty;
            public Iterator<ELResolver> resolvers;

            {
                this.resolvers = CompositeELResolver.this.resolvers.iterator();
            }

            public Iterator<FeatureDescriptor> features() {
                while (!this.features.hasNext() && this.resolvers.hasNext()) {
                    Iterator<FeatureDescriptor> featureDescriptors = this.resolvers.next().getFeatureDescriptors(eLContext, obj);
                    this.features = featureDescriptors;
                    if (featureDescriptors == null) {
                        this.features = this.empty;
                    }
                }
                return this.features;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return features().hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                features().remove();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public FeatureDescriptor next() {
                return features().next();
            }
        };
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        eLContext.setPropertyResolved(false);
        int size = this.resolvers.size();
        for (int i = 0; i < size; i++) {
            Class<?> type = this.resolvers.get(i).getType(eLContext, obj, obj2);
            if (eLContext.isPropertyResolved()) {
                return type;
            }
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        eLContext.setPropertyResolved(false);
        int size = this.resolvers.size();
        for (int i = 0; i < size; i++) {
            Object value = this.resolvers.get(i).getValue(eLContext, obj, obj2);
            if (eLContext.isPropertyResolved()) {
                return value;
            }
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public Object invoke(ELContext eLContext, Object obj, Object obj2, Class<?>[] clsArr, Object[] objArr) {
        int i = 0;
        eLContext.setPropertyResolved(false);
        int size = this.resolvers.size();
        while (i < size) {
            ELContext eLContext2 = eLContext;
            Object obj3 = obj;
            Object obj4 = obj2;
            Class<?>[] clsArr2 = clsArr;
            Object[] objArr2 = objArr;
            Object objInvoke = this.resolvers.get(i).invoke(eLContext2, obj3, obj4, clsArr2, objArr2);
            if (eLContext2.isPropertyResolved()) {
                return objInvoke;
            }
            i++;
            eLContext = eLContext2;
            obj = obj3;
            obj2 = obj4;
            clsArr = clsArr2;
            objArr = objArr2;
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        eLContext.setPropertyResolved(false);
        int size = this.resolvers.size();
        for (int i = 0; i < size; i++) {
            boolean zIsReadOnly = this.resolvers.get(i).isReadOnly(eLContext, obj, obj2);
            if (eLContext.isPropertyResolved()) {
                return zIsReadOnly;
            }
        }
        return false;
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        eLContext.setPropertyResolved(false);
        int size = this.resolvers.size();
        for (int i = 0; i < size; i++) {
            this.resolvers.get(i).setValue(eLContext, obj, obj2, obj3);
            if (eLContext.isPropertyResolved()) {
                return;
            }
        }
    }
}
