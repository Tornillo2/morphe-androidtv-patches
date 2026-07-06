package de.odysseus.el.tree.impl.ast;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import de.odysseus.el.tree.Bindings;
import javax.el.ELContext;
import org.apache.commons.lang3.builder.ToStringStyle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AstString extends AstLiteral {
    public final String value;

    public AstString(String str) {
        this.value = str;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        sb.append("'");
        int length = this.value.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = this.value.charAt(i);
            if (cCharAt == '\\' || cCharAt == '\'') {
                sb.append('\\');
            }
            sb.append(cCharAt);
        }
        sb.append("'");
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        return this.value;
    }

    public String toString() {
        return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE), this.value, ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
    }
}
