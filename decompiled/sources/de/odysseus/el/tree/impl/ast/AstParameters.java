package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.tree.Bindings;
import java.util.List;
import javax.el.ELContext;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AstParameters extends AstRightValue {
    public final List<AstNode> nodes;

    public AstParameters(List<AstNode> list) {
        this.nodes = list;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        sb.append("(");
        for (int i = 0; i < this.nodes.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            this.nodes.get(i).appendStructure(sb, bindings);
        }
        sb.append(")");
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return this.nodes.size();
    }

    public String toString() {
        return "(...)";
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object[] eval(Bindings bindings, ELContext eLContext) {
        Object[] objArr = new Object[this.nodes.size()];
        for (int i = 0; i < this.nodes.size(); i++) {
            objArr[i] = this.nodes.get(i).eval(bindings, eLContext);
        }
        return objArr;
    }

    @Override // de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        return this.nodes.get(i);
    }
}
