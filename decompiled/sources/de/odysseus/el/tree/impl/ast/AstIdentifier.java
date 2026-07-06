package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.tree.Bindings;
import de.odysseus.el.tree.IdentifierNode;
import de.odysseus.el.tree.Node;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.MethodExpression;
import javax.el.MethodInfo;
import javax.el.MethodNotFoundException;
import javax.el.PropertyNotFoundException;
import javax.el.ValueExpression;
import javax.el.ValueReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AstIdentifier extends AstNode implements IdentifierNode {
    public final boolean ignoreReturnType;
    public final int index;
    public final String name;

    public AstIdentifier(String str, int i, boolean z) {
        this.name = str;
        this.index = i;
        this.ignoreReturnType = z;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        sb.append((bindings == null || !bindings.isVariableBound(this.index)) ? this.name : "<var>");
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        ValueExpression variable = bindings.getVariable(this.index);
        if (variable != null) {
            return variable.getValue(eLContext);
        }
        eLContext.setPropertyResolved(false);
        Object value = eLContext.getELResolver().getValue(eLContext, null, this.name);
        if (eLContext.isPropertyResolved()) {
            return value;
        }
        throw new PropertyNotFoundException(LocalMessages.get("error.identifier.property.notfound", this.name));
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 0;
    }

    @Override // de.odysseus.el.tree.Node
    public /* bridge */ /* synthetic */ Node getChild(int i) {
        return null;
    }

    @Override // de.odysseus.el.tree.IdentifierNode
    public int getIndex() {
        return this.index;
    }

    public MethodExpression getMethodExpression(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr) {
        Object objEval = eval(bindings, eLContext);
        if (objEval == null) {
            throw new MethodNotFoundException(LocalMessages.get("error.identifier.method.notfound", this.name));
        }
        if (!(objEval instanceof Method)) {
            if (objEval instanceof MethodExpression) {
                return (MethodExpression) objEval;
            }
            throw new MethodNotFoundException(LocalMessages.get("error.identifier.method.notamethod", this.name, objEval.getClass()));
        }
        final Method methodFindAccessibleMethod = findAccessibleMethod((Method) objEval);
        if (methodFindAccessibleMethod == null) {
            throw new MethodNotFoundException(LocalMessages.get("error.identifier.method.notfound", this.name));
        }
        if (!this.ignoreReturnType && cls != null && !cls.isAssignableFrom(methodFindAccessibleMethod.getReturnType())) {
            throw new MethodNotFoundException(LocalMessages.get("error.identifier.method.returntype", methodFindAccessibleMethod.getReturnType(), this.name, cls));
        }
        if (Arrays.equals(methodFindAccessibleMethod.getParameterTypes(), clsArr)) {
            return new MethodExpression() { // from class: de.odysseus.el.tree.impl.ast.AstIdentifier.1
                public static final long serialVersionUID = 1;

                @Override // javax.el.Expression
                public boolean equals(Object obj) {
                    return obj == this;
                }

                @Override // javax.el.Expression
                public String getExpressionString() {
                    return null;
                }

                @Override // javax.el.MethodExpression
                public MethodInfo getMethodInfo(ELContext eLContext2) {
                    return new MethodInfo(methodFindAccessibleMethod.getName(), methodFindAccessibleMethod.getReturnType(), methodFindAccessibleMethod.getParameterTypes());
                }

                @Override // javax.el.Expression
                public int hashCode() {
                    return 0;
                }

                @Override // javax.el.MethodExpression
                public Object invoke(ELContext eLContext2, Object[] objArr) {
                    try {
                        return methodFindAccessibleMethod.invoke(null, objArr);
                    } catch (IllegalAccessException unused) {
                        throw new ELException(LocalMessages.get("error.identifier.method.access", AstIdentifier.this.name));
                    } catch (IllegalArgumentException e) {
                        throw new ELException(LocalMessages.get("error.identifier.method.invocation", AstIdentifier.this.name, e));
                    } catch (InvocationTargetException e2) {
                        throw new ELException(LocalMessages.get("error.identifier.method.invocation", AstIdentifier.this.name, e2.getCause()));
                    }
                }

                @Override // javax.el.Expression
                public boolean isLiteralText() {
                    return false;
                }
            };
        }
        throw new MethodNotFoundException(LocalMessages.get("error.identifier.method.notfound", this.name));
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public MethodInfo getMethodInfo(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr) {
        return getMethodExpression(bindings, eLContext, cls, clsArr).getMethodInfo(eLContext);
    }

    @Override // de.odysseus.el.tree.IdentifierNode
    public String getName() {
        return this.name;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public Class<?> getType(Bindings bindings, ELContext eLContext) {
        ValueExpression variable = bindings.getVariable(this.index);
        if (variable != null) {
            return variable.getType(eLContext);
        }
        eLContext.setPropertyResolved(false);
        Class<?> type = eLContext.getELResolver().getType(eLContext, null, this.name);
        if (eLContext.isPropertyResolved()) {
            return type;
        }
        throw new PropertyNotFoundException(LocalMessages.get("error.identifier.property.notfound", this.name));
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public ValueReference getValueReference(Bindings bindings, ELContext eLContext) {
        ValueExpression variable = bindings.getVariable(this.index);
        return variable != null ? variable.getValueReference(eLContext) : new ValueReference(null, this.name);
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public Object invoke(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr, Object[] objArr) {
        return getMethodExpression(bindings, eLContext, cls, clsArr).invoke(eLContext, objArr);
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isLeftValue() {
        return true;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isLiteralText() {
        return false;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isMethodInvocation() {
        return false;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isReadOnly(Bindings bindings, ELContext eLContext) {
        ValueExpression variable = bindings.getVariable(this.index);
        if (variable != null) {
            return variable.isReadOnly(eLContext);
        }
        eLContext.setPropertyResolved(false);
        boolean zIsReadOnly = eLContext.getELResolver().isReadOnly(eLContext, null, this.name);
        if (eLContext.isPropertyResolved()) {
            return zIsReadOnly;
        }
        throw new PropertyNotFoundException(LocalMessages.get("error.identifier.property.notfound", this.name));
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public void setValue(Bindings bindings, ELContext eLContext, Object obj) {
        ValueExpression variable = bindings.getVariable(this.index);
        if (variable != null) {
            variable.setValue(eLContext, obj);
            return;
        }
        eLContext.setPropertyResolved(false);
        Class<?> type = eLContext.getELResolver().getType(eLContext, null, this.name);
        if (eLContext.isPropertyResolved()) {
            if (type != null && (obj != null || type.isPrimitive())) {
                obj = bindings.convert(obj, type);
            }
            eLContext.setPropertyResolved(false);
        }
        eLContext.getELResolver().setValue(eLContext, null, this.name, obj);
        if (!eLContext.isPropertyResolved()) {
            throw new PropertyNotFoundException(LocalMessages.get("error.identifier.property.notfound", this.name));
        }
    }

    public String toString() {
        return this.name;
    }

    @Override // de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        return null;
    }

    public AstIdentifier(String str, int i) {
        this(str, i, false);
    }
}
