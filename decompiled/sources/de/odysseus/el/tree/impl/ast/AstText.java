package de.odysseus.el.tree.impl.ast;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.tree.Bindings;
import de.odysseus.el.tree.Node;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.MethodInfo;
import javax.el.ValueReference;
import org.apache.commons.lang3.builder.ToStringStyle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AstText extends AstNode {
    public final String value;

    public AstText(String str) {
        this.value = str;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        int length = this.value.length() - 1;
        for (int i = 0; i < length; i++) {
            char cCharAt = this.value.charAt(i);
            if ((cCharAt == '#' || cCharAt == '$') && this.value.charAt(i + 1) == '{') {
                sb.append('\\');
            }
            sb.append(cCharAt);
        }
        if (length >= 0) {
            sb.append(this.value.charAt(length));
        }
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        return this.value;
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 0;
    }

    @Override // de.odysseus.el.tree.Node
    public /* bridge */ /* synthetic */ Node getChild(int i) {
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
    public ValueReference getValueReference(Bindings bindings, ELContext eLContext) {
        return null;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public Object invoke(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr, Object[] objArr) {
        return cls == null ? this.value : bindings.convert(this.value, cls);
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isLeftValue() {
        return false;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isLiteralText() {
        return true;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isMethodInvocation() {
        return false;
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
        return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE), this.value, ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
    }

    @Override // de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        return null;
    }
}
