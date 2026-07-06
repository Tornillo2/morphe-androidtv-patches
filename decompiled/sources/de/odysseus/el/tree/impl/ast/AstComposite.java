package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.tree.Bindings;
import java.util.List;
import javax.el.ELContext;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AstComposite extends AstRightValue {
    public final List<AstNode> nodes;

    public AstComposite(List<AstNode> list) {
        this.nodes = list;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        for (int i = 0; i < getCardinality(); i++) {
            this.nodes.get(i).appendStructure(sb, bindings);
        }
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < getCardinality(); i++) {
            sb.append((String) bindings.convert(this.nodes.get(i).eval(bindings, eLContext), String.class));
        }
        return sb.toString();
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return this.nodes.size();
    }

    public String toString() {
        return "composite";
    }

    @Override // de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        return this.nodes.get(i);
    }
}
