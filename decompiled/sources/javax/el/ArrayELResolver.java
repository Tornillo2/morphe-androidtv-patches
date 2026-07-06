package javax.el;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import java.beans.FeatureDescriptor;
import java.lang.reflect.Array;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ArrayELResolver extends ELResolver {
    public final boolean readOnly;

    public ArrayELResolver() {
        this(false);
    }

    private final boolean isResolvable(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (isResolvable(obj)) {
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
        if (!isResolvable(obj)) {
            return null;
        }
        toIndex(obj, obj2);
        Class<?> componentType = obj.getClass().getComponentType();
        eLContext.setPropertyResolved(true);
        return componentType;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        Object obj3 = null;
        if (isResolvable(obj)) {
            int index = toIndex(null, obj2);
            if (index >= 0 && index < Array.getLength(obj)) {
                obj3 = Array.get(obj, index);
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
        if (isResolvable(obj)) {
            toIndex(obj, obj2);
            eLContext.setPropertyResolved(true);
        }
        return this.readOnly;
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        if (eLContext == null) {
            throw new NullPointerException("context is null");
        }
        if (isResolvable(obj)) {
            if (this.readOnly) {
                throw new PropertyNotWritableException("resolver is read-only");
            }
            Array.set(obj, toIndex(obj, obj2), obj3);
            eLContext.setPropertyResolved(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v9, types: [int] */
    public final int toIndex(Object obj, Object obj2) {
        ?? IntValue;
        if (obj2 instanceof Number) {
            IntValue = ((Number) obj2).intValue();
        } else if (obj2 instanceof String) {
            try {
                IntValue = Integer.valueOf((String) obj2).intValue();
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot parse array index: ", obj2));
            }
        } else if (obj2 instanceof Character) {
            IntValue = ((Character) obj2).charValue();
        } else {
            if (!(obj2 instanceof Boolean)) {
                throw new IllegalArgumentException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Cannot coerce property to array index: ", obj2));
            }
            IntValue = ((Boolean) obj2).booleanValue();
        }
        if (obj == null || (IntValue >= 0 && IntValue < Array.getLength(obj))) {
            return IntValue;
        }
        throw new PropertyNotFoundException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Array index out of bounds: ", IntValue));
    }

    public ArrayELResolver(boolean z) {
        this.readOnly = z;
    }
}
