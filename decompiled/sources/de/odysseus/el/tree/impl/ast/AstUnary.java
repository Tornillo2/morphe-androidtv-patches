package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.misc.BooleanOperations;
import de.odysseus.el.misc.NumberOperations;
import de.odysseus.el.misc.TypeConverter;
import de.odysseus.el.tree.Bindings;
import javax.el.ELContext;
import javax.el.ELException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AstUnary extends AstRightValue {
    public static final Operator EMPTY = new AnonymousClass1();
    public static final Operator NEG = new AnonymousClass2();
    public static final Operator NOT = new AnonymousClass3();
    public final AstNode child;
    public final Operator operator;

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstUnary$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass1 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstUnary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj) {
            return Boolean.valueOf(BooleanOperations.empty(typeConverter, obj));
        }

        public String toString() {
            return "empty";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstUnary$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass2 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstUnary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj) {
            return NumberOperations.neg(typeConverter, obj);
        }

        public String toString() {
            return "-";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstUnary$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass3 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstUnary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj) {
            return Boolean.valueOf(!((Boolean) typeConverter.convert(obj, Boolean.class)).booleanValue());
        }

        public String toString() {
            return "!";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Operator {
        Object eval(Bindings bindings, ELContext eLContext, AstNode astNode);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class SimpleOperator implements Operator {
        public abstract Object apply(TypeConverter typeConverter, Object obj);

        @Override // de.odysseus.el.tree.impl.ast.AstUnary.Operator
        public Object eval(Bindings bindings, ELContext eLContext, AstNode astNode) {
            return apply(bindings, astNode.eval(bindings, eLContext));
        }
    }

    public AstUnary(AstNode astNode, Operator operator) {
        this.child = astNode;
        this.operator = operator;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        sb.append(this.operator);
        sb.append(' ');
        this.child.appendStructure(sb, bindings);
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) throws ELException {
        return this.operator.eval(bindings, eLContext, this.child);
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 1;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public String toString() {
        return "'" + this.operator.toString() + "'";
    }

    @Override // de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        if (i == 0) {
            return this.child;
        }
        return null;
    }
}
