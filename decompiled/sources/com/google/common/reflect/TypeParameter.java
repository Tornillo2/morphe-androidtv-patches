package com.google.common.reflect;

import com.google.common.base.Preconditions;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class TypeParameter<T> extends TypeCapture<T> {
    public final TypeVariable<?> typeVariable;

    public TypeParameter() {
        Type typeCapture = capture();
        Preconditions.checkArgument(typeCapture instanceof TypeVariable, "%s should be a type variable.", typeCapture);
        this.typeVariable = (TypeVariable) typeCapture;
    }

    public final boolean equals(Object o) {
        if (o instanceof TypeParameter) {
            return this.typeVariable.equals(((TypeParameter) o).typeVariable);
        }
        return false;
    }

    public final int hashCode() {
        return this.typeVariable.hashCode();
    }

    public String toString() {
        return this.typeVariable.toString();
    }
}
