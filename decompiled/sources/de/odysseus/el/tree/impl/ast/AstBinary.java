package de.odysseus.el.tree.impl.ast;

import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.common.net.MediaType;
import de.odysseus.el.misc.BooleanOperations;
import de.odysseus.el.misc.NumberOperations;
import de.odysseus.el.misc.TypeConverter;
import de.odysseus.el.tree.Bindings;
import javax.el.ELContext;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AstBinary extends AstRightValue {
    public static final Operator ADD = new AnonymousClass1();
    public static final Operator AND = new AnonymousClass2();
    public static final Operator DIV = new AnonymousClass3();
    public static final Operator EQ = new AnonymousClass4();
    public static final Operator GE = new AnonymousClass5();
    public static final Operator GT = new AnonymousClass6();
    public static final Operator LE = new AnonymousClass7();
    public static final Operator LT = new AnonymousClass8();
    public static final Operator MOD = new AnonymousClass9();
    public static final Operator MUL = new AnonymousClass10();
    public static final Operator NE = new AnonymousClass11();
    public static final Operator OR = new AnonymousClass12();
    public static final Operator SUB = new AnonymousClass13();
    public final AstNode left;
    public final Operator operator;
    public final AstNode right;

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass1 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return NumberOperations.add(typeConverter, obj, obj2);
        }

        public String toString() {
            return "+";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$10, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass10 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return NumberOperations.mul(typeConverter, obj, obj2);
        }

        public String toString() {
            return MediaType.WILDCARD;
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$11, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass11 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return Boolean.valueOf(BooleanOperations.ne(typeConverter, obj, obj2));
        }

        public String toString() {
            return "!=";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$12, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass12 implements Operator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.Operator
        public Object eval(Bindings bindings, ELContext eLContext, AstNode astNode, AstNode astNode2) {
            Boolean bool = (Boolean) bindings.convert(astNode.eval(bindings, eLContext), Boolean.class);
            Boolean bool2 = Boolean.TRUE;
            return bool2.equals(bool) ? bool2 : (Boolean) bindings.convert(astNode2.eval(bindings, eLContext), Boolean.class);
        }

        public String toString() {
            return "||";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$13, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass13 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return NumberOperations.sub(typeConverter, obj, obj2);
        }

        public String toString() {
            return "-";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass2 implements Operator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.Operator
        public Object eval(Bindings bindings, ELContext eLContext, AstNode astNode, AstNode astNode2) {
            return Boolean.TRUE.equals((Boolean) bindings.convert(astNode.eval(bindings, eLContext), Boolean.class)) ? (Boolean) bindings.convert(astNode2.eval(bindings, eLContext), Boolean.class) : Boolean.FALSE;
        }

        public String toString() {
            return "&&";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass3 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return NumberOperations.div(typeConverter, obj, obj2);
        }

        public String toString() {
            return SchemaId.METRIC_SCHEMA_ID_DELIMITER;
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass4 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return Boolean.valueOf(BooleanOperations.eq(typeConverter, obj, obj2));
        }

        public String toString() {
            return "==";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass5 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return Boolean.valueOf(BooleanOperations.ge(typeConverter, obj, obj2));
        }

        public String toString() {
            return ">=";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$6, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass6 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return Boolean.valueOf(BooleanOperations.gt(typeConverter, obj, obj2));
        }

        public String toString() {
            return ">";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$7, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass7 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return Boolean.valueOf(BooleanOperations.le(typeConverter, obj, obj2));
        }

        public String toString() {
            return "<=";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$8, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass8 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return Boolean.valueOf(BooleanOperations.lt(typeConverter, obj, obj2));
        }

        public String toString() {
            return "<";
        }
    }

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.ast.AstBinary$9, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass9 extends SimpleOperator {
        @Override // de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator
        public Object apply(TypeConverter typeConverter, Object obj, Object obj2) {
            return NumberOperations.mod(typeConverter, obj, obj2);
        }

        public String toString() {
            return "%";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Operator {
        Object eval(Bindings bindings, ELContext eLContext, AstNode astNode, AstNode astNode2);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class SimpleOperator implements Operator {
        public abstract Object apply(TypeConverter typeConverter, Object obj, Object obj2);

        @Override // de.odysseus.el.tree.impl.ast.AstBinary.Operator
        public Object eval(Bindings bindings, ELContext eLContext, AstNode astNode, AstNode astNode2) {
            return apply(bindings, astNode.eval(bindings, eLContext), astNode2.eval(bindings, eLContext));
        }
    }

    public AstBinary(AstNode astNode, AstNode astNode2, Operator operator) {
        this.left = astNode;
        this.right = astNode2;
        this.operator = operator;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        this.left.appendStructure(sb, bindings);
        sb.append(' ');
        sb.append(this.operator);
        sb.append(' ');
        this.right.appendStructure(sb, bindings);
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        return this.operator.eval(bindings, eLContext, this.left, this.right);
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 2;
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
            return this.left;
        }
        if (i == 1) {
            return this.right;
        }
        return null;
    }
}
