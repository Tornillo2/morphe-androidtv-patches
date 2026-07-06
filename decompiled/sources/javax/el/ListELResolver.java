package javax.el;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ListELResolver extends ELResolver {
    public final boolean readOnly;

    public ListELResolver() {
        this(false);
    }

    private static final boolean isResolvable(Object obj) {
        return obj instanceof List;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v9, types: [int] */
    public static final int toIndex(List<?> list, Object obj) {
        ?? IntValue;
        if (obj instanceof Number) {
            IntValue = ((Number) obj).intValue();
        } else if (obj instanceof String) {
            try {
                IntValue = Integer.valueOf((String) obj).intValue();
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot parse list index: ", obj));
            }
        } else if (obj instanceof Character) {
            IntValue = ((Character) obj).charValue();
        } else {
            if (!(obj instanceof Boolean)) {
                throw new IllegalArgumentException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot coerce property to list index: ", obj));
            }
            IntValue = ((Boolean) obj).booleanValue();
        }
        if (list == null || (IntValue >= 0 && IntValue < list.size())) {
            return IntValue;
        }
        throw new PropertyNotFoundException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("List index out of bounds: ", IntValue));
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (obj instanceof List) {
            return Integer.class;
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        return null;
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        if (!(obj instanceof List)) {
            return null;
        }
        toIndex((List) obj, obj2);
        eLContext.setPropertyResolved(true);
        return Object.class;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        Object obj3 = null;
        if (obj instanceof List) {
            int index = toIndex(null, obj2);
            List list = (List) obj;
            if (index >= 0 && index < list.size()) {
                obj3 = list.get(index);
            }
            eLContext.setPropertyResolved(true);
        }
        return obj3;
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        if (obj instanceof List) {
            toIndex((List) obj, obj2);
            eLContext.setPropertyResolved(true);
        }
        return this.readOnly;
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        if (obj instanceof List) {
            if (this.readOnly) {
                throw new PropertyNotWritableException("resolver is read-only");
            }
            List list = (List) obj;
            try {
                list.set(toIndex(list, obj2), obj3);
                eLContext.setPropertyResolved(true);
            } catch (ArrayStoreException e) {
                throw new IllegalArgumentException(e);
            } catch (UnsupportedOperationException e2) {
                throw new PropertyNotWritableException(e2);
            }
        }
    }

    public ListELResolver(boolean z) {
        this.readOnly = z;
    }
}
