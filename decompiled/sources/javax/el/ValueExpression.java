package javax.el;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ValueExpression extends Expression {
    public static final long serialVersionUID = 1;

    public abstract Class<?> getExpectedType();

    public abstract Class<?> getType(ELContext eLContext);

    public abstract Object getValue(ELContext eLContext);

    public ValueReference getValueReference(ELContext eLContext) {
        return null;
    }

    public abstract boolean isReadOnly(ELContext eLContext);

    public abstract void setValue(ELContext eLContext, Object obj);
}
