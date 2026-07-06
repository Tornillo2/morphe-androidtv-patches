package javax.el;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class MethodInfo {
    public final String name;
    public final Class<?>[] paramTypes;
    public final Class<?> returnType;

    public MethodInfo(String str, Class<?> cls, Class<?>[] clsArr) {
        this.name = str;
        this.returnType = cls;
        this.paramTypes = clsArr;
    }

    public String getName() {
        return this.name;
    }

    public Class<?>[] getParamTypes() {
        return this.paramTypes;
    }

    public Class<?> getReturnType() {
        return this.returnType;
    }
}
