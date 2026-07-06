package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.tree.Bindings;
import javax.el.ELContext;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AstNested extends AstRightValue {
    public final AstNode child;

    public AstNested(AstNode astNode) {
        this.child = astNode;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        sb.append("(");
        this.child.appendStructure(sb, bindings);
        sb.append(")");
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        return this.child.eval(bindings, eLContext);
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 1;
    }

    public String toString() {
        return "(...)";
    }

    @Override // de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        if (i == 0) {
            return this.child;
        }
        return null;
    }
}
