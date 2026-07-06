package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.tree.Bindings;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.MethodInfo;
import javax.el.ValueReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AstRightValue extends AstNode {
    @Override // de.odysseus.el.tree.ExpressionNode
    public final MethodInfo getMethodInfo(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr) {
        return null;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final Class<?> getType(Bindings bindings, ELContext eLContext) {
        return null;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final ValueReference getValueReference(Bindings bindings, ELContext eLContext) {
        return null;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final Object invoke(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr, Object[] objArr) {
        throw new ELException(LocalMessages.get("error.method.invalid", getStructuralId(bindings)));
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final boolean isLeftValue() {
        return false;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final boolean isLiteralText() {
        return false;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isMethodInvocation() {
        return false;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final boolean isReadOnly(Bindings bindings, ELContext eLContext) {
        return true;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final void setValue(Bindings bindings, ELContext eLContext, Object obj) {
        throw new ELException(LocalMessages.get("error.value.set.rvalue", getStructuralId(bindings)));
    }
}
