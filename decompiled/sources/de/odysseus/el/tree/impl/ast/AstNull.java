package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.tree.Bindings;
import javax.el.ELContext;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AstNull extends AstLiteral {
    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        sb.append(AbstractJsonLexerKt.NULL);
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        return null;
    }

    public String toString() {
        return AbstractJsonLexerKt.NULL;
    }
}
