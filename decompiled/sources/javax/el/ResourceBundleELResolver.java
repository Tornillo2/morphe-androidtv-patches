package javax.el;

import java.beans.FeatureDescriptor;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ResourceBundleELResolver extends ELResolver {
    private final boolean isResolvable(Object obj) {
        return obj instanceof ResourceBundle;
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (obj instanceof ResourceBundle) {
            return String.class;
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        if (!(obj instanceof ResourceBundle)) {
            return null;
        }
        final Enumeration<String> keys = ((ResourceBundle) obj).getKeys();
        return new Iterator<FeatureDescriptor>() { // from class: javax.el.ResourceBundleELResolver.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public FeatureDescriptor next() {
                FeatureDescriptor featureDescriptor = new FeatureDescriptor();
                featureDescriptor.setDisplayName((String) keys.nextElement());
                featureDescriptor.setName(featureDescriptor.getDisplayName());
                featureDescriptor.setShortDescription("");
                featureDescriptor.setExpert(true);
                featureDescriptor.setHidden(false);
                featureDescriptor.setPreferred(true);
                featureDescriptor.setValue("type", String.class);
                featureDescriptor.setValue(ELResolver.RESOLVABLE_AT_DESIGN_TIME, Boolean.TRUE);
                return featureDescriptor;
            }
        };
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        if (!(obj instanceof ResourceBundle)) {
            return null;
        }
        eLContext.setPropertyResolved(true);
        return null;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        Object object = null;
        if (obj instanceof ResourceBundle) {
            if (obj2 != null) {
                try {
                    object = ((ResourceBundle) obj).getObject(obj2.toString());
                } catch (MissingResourceException unused) {
                    object = "???" + obj2 + "???";
                }
            }
            eLContext.setPropertyResolved(true);
        }
        return object;
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        if (obj instanceof ResourceBundle) {
            eLContext.setPropertyResolved(true);
        }
        return true;
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        if (obj instanceof ResourceBundle) {
            throw new PropertyNotWritableException("resolver is read-only");
        }
    }
}
