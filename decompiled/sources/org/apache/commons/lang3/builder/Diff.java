package org.apache.commons.lang3.builder;

import java.lang.reflect.Type;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.commons.lang3.tuple.Pair;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class Diff<T> extends Pair<T, T> {
    public static final long serialVersionUID = 1;
    public final String fieldName;
    public final Type type;

    public Diff(String str) {
        Class cls = TypeUtils.getTypeArguments(getClass(), Diff.class).get(Diff.class.getTypeParameters()[0]);
        this.type = cls == null ? Object.class : cls;
        this.fieldName = str;
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    public final Type getType() {
        return this.type;
    }

    @Override // java.util.Map.Entry
    public final T setValue(T t) {
        throw new UnsupportedOperationException("Cannot alter Diff object.");
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public final String toString() {
        return String.format("[%s: %s, %s]", this.fieldName, getLeft(), getRight());
    }
}
