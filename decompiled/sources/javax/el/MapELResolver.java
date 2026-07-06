package javax.el;

import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class MapELResolver extends ELResolver {
    public final boolean readOnly;

    public MapELResolver() {
        this(false);
    }

    private final boolean isResolvable(Object obj) {
        return obj instanceof Map;
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (obj instanceof Map) {
            return Object.class;
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        if (!(obj instanceof Map)) {
            return null;
        }
        final Iterator it = ((Map) obj).keySet().iterator();
        return new Iterator<FeatureDescriptor>() { // from class: javax.el.MapELResolver.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("cannot remove");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public FeatureDescriptor next() {
                Object next = it.next();
                FeatureDescriptor featureDescriptor = new FeatureDescriptor();
                featureDescriptor.setDisplayName(next == null ? AbstractJsonLexerKt.NULL : next.toString());
                featureDescriptor.setName(featureDescriptor.getDisplayName());
                featureDescriptor.setShortDescription("");
                featureDescriptor.setExpert(true);
                featureDescriptor.setHidden(false);
                featureDescriptor.setPreferred(true);
                featureDescriptor.setValue("type", next == null ? null : next.getClass());
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
        if (!(obj instanceof Map)) {
            return null;
        }
        eLContext.setPropertyResolved(true);
        return Object.class;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        if (!(obj instanceof Map)) {
            return null;
        }
        Object obj3 = ((Map) obj).get(obj2);
        eLContext.setPropertyResolved(true);
        return obj3;
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        if (obj instanceof Map) {
            eLContext.setPropertyResolved(true);
        }
        return this.readOnly;
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        if (obj instanceof Map) {
            if (this.readOnly) {
                throw new PropertyNotWritableException("resolver is read-only");
            }
            ((Map) obj).put(obj2, obj3);
            eLContext.setPropertyResolved(true);
        }
    }

    public MapELResolver(boolean z) {
        this.readOnly = z;
    }
}
