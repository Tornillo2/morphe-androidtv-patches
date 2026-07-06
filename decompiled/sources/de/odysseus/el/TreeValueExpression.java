package de.odysseus.el;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.misc.TypeConverter;
import de.odysseus.el.tree.Bindings;
import de.odysseus.el.tree.ExpressionNode;
import de.odysseus.el.tree.NodePrinter;
import de.odysseus.el.tree.Tree;
import de.odysseus.el.tree.TreeBuilder;
import de.odysseus.el.tree.TreeStore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.FunctionMapper;
import javax.el.ValueExpression;
import javax.el.ValueReference;
import javax.el.VariableMapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TreeValueExpression extends ValueExpression {
    public static final long serialVersionUID = 1;
    public final Bindings bindings;
    public final TreeBuilder builder;
    public final boolean deferred;
    public final String expr;
    public transient ExpressionNode node;
    public String structure;
    public final Class<?> type;

    public TreeValueExpression(TreeStore treeStore, FunctionMapper functionMapper, VariableMapper variableMapper, TypeConverter typeConverter, String str, Class<?> cls) {
        Tree tree = treeStore.get(str);
        this.builder = treeStore.getBuilder();
        this.bindings = tree.bind(functionMapper, variableMapper, typeConverter);
        this.expr = str;
        this.type = cls;
        this.node = tree.getRoot();
        this.deferred = tree.isDeferred();
        if (cls == null) {
            throw new NullPointerException(LocalMessages.get("error.value.notype", new Object[0]));
        }
    }

    private String getStructuralId() {
        if (this.structure == null) {
            this.structure = this.node.getStructuralId(this.bindings);
        }
        return this.structure;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        try {
            this.node = this.builder.build(this.expr).getRoot();
        } catch (ELException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void dump(PrintWriter printWriter) {
        NodePrinter.dump(printWriter, this.node);
    }

    @Override // javax.el.Expression
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == TreeValueExpression.class) {
            TreeValueExpression treeValueExpression = (TreeValueExpression) obj;
            if (this.builder.equals(treeValueExpression.builder) && this.type == treeValueExpression.type && getStructuralId().equals(treeValueExpression.getStructuralId()) && this.bindings.equals(treeValueExpression.bindings)) {
                return true;
            }
        }
        return false;
    }

    @Override // javax.el.ValueExpression
    public Class<?> getExpectedType() {
        return this.type;
    }

    @Override // javax.el.Expression
    public String getExpressionString() {
        return this.expr;
    }

    @Override // javax.el.ValueExpression
    public Class<?> getType(ELContext eLContext) throws ELException {
        return this.node.getType(this.bindings, eLContext);
    }

    @Override // javax.el.ValueExpression
    public Object getValue(ELContext eLContext) throws ELException {
        return this.node.getValue(this.bindings, eLContext, this.type);
    }

    @Override // javax.el.ValueExpression
    public ValueReference getValueReference(ELContext eLContext) {
        return this.node.getValueReference(this.bindings, eLContext);
    }

    @Override // javax.el.Expression
    public int hashCode() {
        return getStructuralId().hashCode();
    }

    public boolean isDeferred() {
        return this.deferred;
    }

    public boolean isLeftValue() {
        return this.node.isLeftValue();
    }

    @Override // javax.el.Expression
    public boolean isLiteralText() {
        return this.node.isLiteralText();
    }

    @Override // javax.el.ValueExpression
    public boolean isReadOnly(ELContext eLContext) throws ELException {
        return this.node.isReadOnly(this.bindings, eLContext);
    }

    @Override // javax.el.ValueExpression
    public void setValue(ELContext eLContext, Object obj) throws ELException {
        this.node.setValue(this.bindings, eLContext, obj);
    }

    public String toString() {
        return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder("TreeValueExpression("), this.expr, ")");
    }
}
