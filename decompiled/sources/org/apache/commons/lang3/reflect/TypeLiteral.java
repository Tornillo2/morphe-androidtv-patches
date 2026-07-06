package org.apache.commons.lang3.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class TypeLiteral<T> implements Typed<T> {
    public static final TypeVariable<Class<TypeLiteral>> T = TypeLiteral.class.getTypeParameters()[0];
    public final String toString;
    public final Type value;

    public TypeLiteral() {
        Map<TypeVariable<?>, Type> typeArguments = TypeUtils.getTypeArguments(getClass(), TypeLiteral.class);
        TypeVariable<Class<TypeLiteral>> typeVariable = T;
        Type type = typeArguments.get(typeVariable);
        Validate.notNull(type, "%s does not assign type parameter %s", getClass(), TypeUtils.toLongString(typeVariable));
        Type type2 = type;
        this.value = type2;
        this.toString = String.format("%s<%s>", "TypeLiteral", TypeUtils.toString(type2));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TypeLiteral) {
            return TypeUtils.equals(this.value, ((TypeLiteral) obj).value);
        }
        return false;
    }

    @Override // org.apache.commons.lang3.reflect.Typed
    public Type getType() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() | 592;
    }

    public String toString() {
        return this.toString;
    }
}
