package javax.el;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ValueReference implements Serializable {
    public static final long serialVersionUID = 1;
    public Object base;
    public Object property;

    public ValueReference(Object obj, Object obj2) {
        this.base = obj;
        this.property = obj2;
    }

    public Object getBase() {
        return this.base;
    }

    public Object getProperty() {
        return this.property;
    }
}
