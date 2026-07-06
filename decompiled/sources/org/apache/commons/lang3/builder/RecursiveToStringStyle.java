package org.apache.commons.lang3.builder;

import java.util.Collection;
import org.apache.commons.lang3.ClassUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class RecursiveToStringStyle extends ToStringStyle {
    public static final long serialVersionUID = 1;

    public boolean accept(Class<?> cls) {
        return true;
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, Object obj) {
        if (ClassUtils.isPrimitiveWrapper(obj.getClass()) || String.class.equals(obj.getClass())) {
            stringBuffer.append(obj);
        } else {
            stringBuffer.append(ReflectionToStringBuilder.toString(obj, this));
        }
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, Collection<?> collection) {
        appendClassName(stringBuffer, collection);
        appendIdentityHashCode(stringBuffer, collection);
        appendDetail(stringBuffer, str, collection.toArray());
    }
}
