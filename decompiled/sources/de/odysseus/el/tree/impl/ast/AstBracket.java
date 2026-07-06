package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.tree.Bindings;
import javax.el.ELContext;
import javax.el.ELException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AstBracket extends AstProperty {
    public final AstNode property;

    public AstBracket(AstNode astNode, AstNode astNode2, boolean z, boolean z2) {
        this(astNode, astNode2, z, z2, false);
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        getChild(0).appendStructure(sb, bindings);
        sb.append("[");
        getChild(1).appendStructure(sb, bindings);
        sb.append("]");
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 2;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstProperty
    public Object getProperty(Bindings bindings, ELContext eLContext) throws ELException {
        return this.property.eval(bindings, eLContext);
    }

    public String toString() {
        return "[...]";
    }

    public AstBracket(AstNode astNode, AstNode astNode2, boolean z, boolean z2, boolean z3) {
        super(astNode, z, z2, z3);
        this.property = astNode2;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstProperty, de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        return i == 1 ? this.property : super.getChild(i);
    }
}
