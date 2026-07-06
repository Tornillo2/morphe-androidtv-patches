package de.odysseus.el.tree;

import javax.el.ELContext;
import javax.el.MethodInfo;
import javax.el.ValueReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface ExpressionNode extends Node {
    MethodInfo getMethodInfo(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr);

    String getStructuralId(Bindings bindings);

    Class<?> getType(Bindings bindings, ELContext eLContext);

    Object getValue(Bindings bindings, ELContext eLContext, Class<?> cls);

    ValueReference getValueReference(Bindings bindings, ELContext eLContext);

    Object invoke(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr, Object[] objArr);

    boolean isLeftValue();

    boolean isLiteralText();

    boolean isMethodInvocation();

    boolean isReadOnly(Bindings bindings, ELContext eLContext);

    void setValue(Bindings bindings, ELContext eLContext, Object obj);
}
