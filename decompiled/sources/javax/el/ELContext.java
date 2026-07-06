package javax.el;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ELContext {
    public Map<Class<?>, Object> context;
    public Locale locale;
    public boolean resolved;

    public Object getContext(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("key is null");
        }
        Map<Class<?>, Object> map = this.context;
        if (map == null) {
            return null;
        }
        return map.get(cls);
    }

    public abstract ELResolver getELResolver();

    public abstract FunctionMapper getFunctionMapper();

    public Locale getLocale() {
        return this.locale;
    }

    public abstract VariableMapper getVariableMapper();

    public boolean isPropertyResolved() {
        return this.resolved;
    }

    public void putContext(Class<?> cls, Object obj) {
        if (cls == null) {
            throw new NullPointerException("key is null");
        }
        if (this.context == null) {
            this.context = new HashMap();
        }
        this.context.put(cls, obj);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setPropertyResolved(boolean z) {
        this.resolved = z;
    }
}
