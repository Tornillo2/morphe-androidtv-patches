package org.apache.commons.lang3.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.reflect.FieldUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ReflectionDiffBuilder implements Builder<DiffResult> {
    public final DiffBuilder diffBuilder;
    public final Object left;
    public final Object right;

    public <T> ReflectionDiffBuilder(T t, T t2, ToStringStyle toStringStyle) {
        this.left = t;
        this.right = t2;
        this.diffBuilder = new DiffBuilder(t, t2, toStringStyle, true);
    }

    private boolean accept(Field field) {
        if (field.getName().indexOf(36) == -1 && !Modifier.isTransient(field.getModifiers())) {
            return !Modifier.isStatic(field.getModifiers());
        }
        return false;
    }

    public final void appendFields(Class<?> cls) {
        for (Field field : FieldUtils.getAllFields(cls)) {
            if (accept(field)) {
                try {
                    this.diffBuilder.append(field.getName(), FieldUtils.readField(field, this.left, true), FieldUtils.readField(field, this.right, true));
                } catch (IllegalAccessException e) {
                    throw new InternalError("Unexpected IllegalAccessException: " + e.getMessage());
                }
            }
        }
    }

    @Override // org.apache.commons.lang3.builder.Builder
    public DiffResult build() {
        if (this.left.equals(this.right)) {
            return this.diffBuilder.build();
        }
        appendFields(this.left.getClass());
        return this.diffBuilder.build();
    }
}
