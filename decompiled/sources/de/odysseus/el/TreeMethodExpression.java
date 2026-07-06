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
import java.util.Arrays;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.FunctionMapper;
import javax.el.MethodExpression;
import javax.el.MethodInfo;
import javax.el.VariableMapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TreeMethodExpression extends MethodExpression {
    public static final long serialVersionUID = 1;
    public final Bindings bindings;
    public final TreeBuilder builder;
    public final boolean deferred;
    public final String expr;
    public transient ExpressionNode node;
    public String structure;
    public final Class<?> type;
    public final Class<?>[] types;

    public TreeMethodExpression(TreeStore treeStore, FunctionMapper functionMapper, VariableMapper variableMapper, TypeConverter typeConverter, String str, Class<?> cls, Class<?>[] clsArr) {
        Tree tree = treeStore.get(str);
        this.builder = treeStore.getBuilder();
        this.bindings = tree.bind(functionMapper, variableMapper, typeConverter);
        this.expr = str;
        this.type = cls;
        this.types = clsArr;
        this.node = tree.getRoot();
        this.deferred = tree.isDeferred();
        if (this.node.isLiteralText()) {
            if (cls == Void.TYPE || cls == Void.class) {
                throw new ELException(LocalMessages.get("error.method.literal.void", str));
            }
        } else {
            if (this.node.isMethodInvocation()) {
                return;
            }
            if (!this.node.isLeftValue()) {
                throw new ELException(LocalMessages.get("error.method.invalid", str));
            }
            if (clsArr == null) {
                throw new NullPointerException(LocalMessages.get("error.method.notypes", new Object[0]));
            }
        }
    }

    public void dump(PrintWriter printWriter) {
        NodePrinter.dump(printWriter, this.node);
    }

    @Override // javax.el.Expression
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == TreeMethodExpression.class) {
            TreeMethodExpression treeMethodExpression = (TreeMethodExpression) obj;
            if (this.builder.equals(treeMethodExpression.builder) && this.type == treeMethodExpression.type && Arrays.equals(this.types, treeMethodExpression.types) && getStructuralId().equals(treeMethodExpression.getStructuralId()) && this.bindings.equals(treeMethodExpression.bindings)) {
                return true;
            }
        }
        return false;
    }

    @Override // javax.el.Expression
    public String getExpressionString() {
        return this.expr;
    }

    @Override // javax.el.MethodExpression
    public MethodInfo getMethodInfo(ELContext eLContext) throws ELException {
        return this.node.getMethodInfo(this.bindings, eLContext, this.type, this.types);
    }

    public final String getStructuralId() {
        if (this.structure == null) {
            this.structure = this.node.getStructuralId(this.bindings);
        }
        return this.structure;
    }

    @Override // javax.el.Expression
    public int hashCode() {
        return getStructuralId().hashCode();
    }

    @Override // javax.el.MethodExpression
    public Object invoke(ELContext eLContext, Object[] objArr) throws ELException {
        return this.node.invoke(this.bindings, eLContext, this.type, this.types, objArr);
    }

    public boolean isDeferred() {
        return this.deferred;
    }

    @Override // javax.el.Expression
    public boolean isLiteralText() {
        return this.node.isLiteralText();
    }

    @Override // javax.el.MethodExpression
    public boolean isParmetersProvided() {
        return this.node.isMethodInvocation();
    }

    public final void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        try {
            this.node = this.builder.build(this.expr).getRoot();
        } catch (ELException e) {
            throw new IOException(e.getMessage());
        }
    }

    public String toString() {
        return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder("TreeMethodExpression("), this.expr, ")");
    }
}
