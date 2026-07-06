package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.tree.Bindings;
import javax.el.ELContext;
import javax.el.MethodInfo;
import javax.el.ValueReference;
import org.apache.commons.text.StringSubstitutor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AstEval extends AstNode {
    public final AstNode child;
    public final boolean deferred;

    public AstEval(AstNode astNode, boolean z) {
        this.child = astNode;
        this.deferred = z;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        sb.append(this.deferred ? "#{" : StringSubstitutor.DEFAULT_VAR_START);
        this.child.appendStructure(sb, bindings);
        sb.append("}");
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        return this.child.eval(bindings, eLContext);
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 1;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public MethodInfo getMethodInfo(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr) {
        return this.child.getMethodInfo(bindings, eLContext, cls, clsArr);
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public Class<?> getType(Bindings bindings, ELContext eLContext) {
        return this.child.getType(bindings, eLContext);
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public ValueReference getValueReference(Bindings bindings, ELContext eLContext) {
        return this.child.getValueReference(bindings, eLContext);
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public Object invoke(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr, Object[] objArr) {
        return this.child.invoke(bindings, eLContext, cls, clsArr, objArr);
    }

    public boolean isDeferred() {
        return this.deferred;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isLeftValue() {
        return getChild(0).isLeftValue();
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isLiteralText() {
        return this.child.isLiteralText();
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isMethodInvocation() {
        return getChild(0).isMethodInvocation();
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isReadOnly(Bindings bindings, ELContext eLContext) {
        return this.child.isReadOnly(bindings, eLContext);
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public void setValue(Bindings bindings, ELContext eLContext, Object obj) {
        this.child.setValue(bindings, eLContext, obj);
    }

    public String toString() {
        return (this.deferred ? "#" : "$").concat("{...}");
    }

    @Override // de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        if (i == 0) {
            return this.child;
        }
        return null;
    }
}
