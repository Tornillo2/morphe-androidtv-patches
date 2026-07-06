package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.tree.Bindings;
import javax.el.ELContext;
import javax.el.ELException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AstChoice extends AstRightValue {
    public final AstNode no;
    public final AstNode question;
    public final AstNode yes;

    public AstChoice(AstNode astNode, AstNode astNode2, AstNode astNode3) {
        this.question = astNode;
        this.yes = astNode2;
        this.no = astNode3;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        this.question.appendStructure(sb, bindings);
        sb.append(" ? ");
        this.yes.appendStructure(sb, bindings);
        sb.append(" : ");
        this.no.appendStructure(sb, bindings);
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) throws ELException {
        return (((Boolean) bindings.convert(this.question.eval(bindings, eLContext), Boolean.class)).booleanValue() ? this.yes : this.no).eval(bindings, eLContext);
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 3;
    }

    public String toString() {
        return "?";
    }

    @Override // de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        if (i == 0) {
            return this.question;
        }
        if (i == 1) {
            return this.yes;
        }
        if (i == 2) {
            return this.no;
        }
        return null;
    }
}
