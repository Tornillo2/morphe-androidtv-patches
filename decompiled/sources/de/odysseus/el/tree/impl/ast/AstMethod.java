package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.tree.Bindings;
import de.odysseus.el.tree.Node;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.MethodInfo;
import javax.el.MethodNotFoundException;
import javax.el.PropertyNotFoundException;
import javax.el.ValueReference;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AstMethod extends AstNode {
    public final AstParameters params;
    public final AstProperty property;

    public AstMethod(AstProperty astProperty, AstParameters astParameters) {
        this.property = astProperty;
        this.params = astParameters;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        this.property.appendStructure(sb, bindings);
        this.params.appendStructure(sb, bindings);
    }

    public Object eval(Bindings bindings, ELContext eLContext, boolean z) {
        Object objEval = this.property.getPrefix().eval(bindings, eLContext);
        if (objEval == null) {
            if (z) {
                return null;
            }
            throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", this.property.getPrefix()));
        }
        Object property = this.property.getProperty(bindings, eLContext);
        if (property == null) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.method.notfound", AbstractJsonLexerKt.NULL, objEval));
        }
        String str = (String) bindings.convert(property, String.class);
        eLContext.setPropertyResolved(false);
        Object objInvoke = eLContext.getELResolver().invoke(eLContext, objEval, str, null, this.params.eval(bindings, eLContext));
        if (eLContext.isPropertyResolved()) {
            return objInvoke;
        }
        throw new MethodNotFoundException(LocalMessages.get("error.property.method.notfound", str, objEval.getClass()));
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 2;
    }

    @Override // de.odysseus.el.tree.Node
    public Node getChild(int i) {
        if (i == 0) {
            return this.property;
        }
        if (i == 1) {
            return this.params;
        }
        return null;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public MethodInfo getMethodInfo(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr) {
        return null;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public Class<?> getType(Bindings bindings, ELContext eLContext) {
        return null;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final ValueReference getValueReference(Bindings bindings, ELContext eLContext) {
        return null;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public Object invoke(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr, Object[] objArr) {
        return eval(bindings, eLContext, false);
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isLeftValue() {
        return false;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isLiteralText() {
        return false;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isMethodInvocation() {
        return true;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isReadOnly(Bindings bindings, ELContext eLContext) {
        return true;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public void setValue(Bindings bindings, ELContext eLContext, Object obj) {
        throw new ELException(LocalMessages.get("error.value.set.rvalue", getStructuralId(bindings)));
    }

    public String toString() {
        return "<method>";
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        return eval(bindings, eLContext, true);
    }
}
