package de.odysseus.el;

import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.misc.TypeConverter;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ValueExpression;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ObjectValueExpression extends ValueExpression {
    public static final long serialVersionUID = 1;
    public final TypeConverter converter;
    public final Object object;
    public final Class<?> type;

    public ObjectValueExpression(TypeConverter typeConverter, Object obj, Class<?> cls) {
        this.converter = typeConverter;
        this.object = obj;
        this.type = cls;
        if (cls == null) {
            throw new NullPointerException(LocalMessages.get("error.value.notype", new Object[0]));
        }
    }

    @Override // javax.el.Expression
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != ObjectValueExpression.class) {
            return false;
        }
        ObjectValueExpression objectValueExpression = (ObjectValueExpression) obj;
        if (this.type != objectValueExpression.type) {
            return false;
        }
        Object obj2 = this.object;
        Object obj3 = objectValueExpression.object;
        if (obj2 != obj3) {
            return obj2 != null && obj2.equals(obj3);
        }
        return true;
    }

    @Override // javax.el.ValueExpression
    public Class<?> getExpectedType() {
        return this.type;
    }

    @Override // javax.el.Expression
    public String getExpressionString() {
        return null;
    }

    @Override // javax.el.ValueExpression
    public Class<?> getType(ELContext eLContext) {
        return null;
    }

    @Override // javax.el.ValueExpression
    public Object getValue(ELContext eLContext) {
        return this.converter.convert(this.object, this.type);
    }

    @Override // javax.el.Expression
    public int hashCode() {
        Object obj = this.object;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @Override // javax.el.Expression
    public boolean isLiteralText() {
        return false;
    }

    @Override // javax.el.ValueExpression
    public boolean isReadOnly(ELContext eLContext) {
        return true;
    }

    @Override // javax.el.ValueExpression
    public void setValue(ELContext eLContext, Object obj) {
        throw new ELException(LocalMessages.get("error.value.set.rvalue", "<object value expression>"));
    }

    public String toString() {
        return "ValueExpression(" + this.object + ")";
    }
}
