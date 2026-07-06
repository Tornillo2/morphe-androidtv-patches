package de.odysseus.el.tree.impl.ast;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import de.odysseus.el.tree.Bindings;
import javax.el.ELContext;
import javax.el.ELException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AstDot extends AstProperty {
    public final String property;

    public AstDot(AstNode astNode, String str, boolean z) {
        this(astNode, str, z, false);
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        getChild(0).appendStructure(sb, bindings);
        sb.append(ExternalFourCCMapper.CODEC_NAME_SPLITTER);
        sb.append(this.property);
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 1;
    }

    public String toString() {
        return ". " + this.property;
    }

    public AstDot(AstNode astNode, String str, boolean z, boolean z2) {
        super(astNode, z, true, z2);
        this.property = str;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstProperty
    public String getProperty(Bindings bindings, ELContext eLContext) throws ELException {
        return this.property;
    }
}
