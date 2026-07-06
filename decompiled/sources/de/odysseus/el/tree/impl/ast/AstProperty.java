package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.tree.Bindings;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.MethodInfo;
import javax.el.MethodNotFoundException;
import javax.el.PropertyNotFoundException;
import javax.el.ValueReference;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AstProperty extends AstNode {
    public final boolean ignoreReturnType;
    public final boolean lvalue;
    public final AstNode prefix;
    public final boolean strict;

    public AstProperty(AstNode astNode, boolean z, boolean z2, boolean z3) {
        this.prefix = astNode;
        this.lvalue = z;
        this.strict = z2;
        this.ignoreReturnType = z3;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        Object objEval = this.prefix.eval(bindings, eLContext);
        if (objEval == null) {
            return null;
        }
        Object property = getProperty(bindings, eLContext);
        if (property == null && this.strict) {
            return null;
        }
        eLContext.setPropertyResolved(false);
        Object value = eLContext.getELResolver().getValue(eLContext, objEval, property);
        if (eLContext.isPropertyResolved()) {
            return value;
        }
        throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", property, objEval));
    }

    public Method findMethod(String str, Class<?> cls, Class<?> cls2, Class<?>[] clsArr) {
        try {
            Method methodFindAccessibleMethod = findAccessibleMethod(cls.getMethod(str, clsArr));
            if (methodFindAccessibleMethod == null) {
                throw new MethodNotFoundException(LocalMessages.get("error.property.method.notfound", str, cls));
            }
            if (this.ignoreReturnType || cls2 == null || cls2.isAssignableFrom(methodFindAccessibleMethod.getReturnType())) {
                return methodFindAccessibleMethod;
            }
            throw new MethodNotFoundException(LocalMessages.get("error.property.method.returntype", methodFindAccessibleMethod.getReturnType(), str, cls, cls2));
        } catch (NoSuchMethodException unused) {
            throw new MethodNotFoundException(LocalMessages.get("error.property.method.notfound", str, cls));
        }
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public MethodInfo getMethodInfo(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr) {
        Object objEval = this.prefix.eval(bindings, eLContext);
        if (objEval == null) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", this.prefix));
        }
        Object property = getProperty(bindings, eLContext);
        if (property == null && this.strict) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.method.notfound", AbstractJsonLexerKt.NULL, objEval));
        }
        Method methodFindMethod = findMethod((String) bindings.convert(property, String.class), objEval.getClass(), cls, clsArr);
        return new MethodInfo(methodFindMethod.getName(), methodFindMethod.getReturnType(), clsArr);
    }

    public AstNode getPrefix() {
        return this.prefix;
    }

    public abstract Object getProperty(Bindings bindings, ELContext eLContext) throws ELException;

    @Override // de.odysseus.el.tree.ExpressionNode
    public Class<?> getType(Bindings bindings, ELContext eLContext) {
        if (!this.lvalue) {
            return null;
        }
        Object objEval = this.prefix.eval(bindings, eLContext);
        if (objEval == null) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", this.prefix));
        }
        Object property = getProperty(bindings, eLContext);
        if (property == null && this.strict) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", AbstractJsonLexerKt.NULL, objEval));
        }
        eLContext.setPropertyResolved(false);
        Class<?> type = eLContext.getELResolver().getType(eLContext, objEval, property);
        if (eLContext.isPropertyResolved()) {
            return type;
        }
        throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", property, objEval));
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public ValueReference getValueReference(Bindings bindings, ELContext eLContext) {
        Object objEval = this.prefix.eval(bindings, eLContext);
        if (objEval == null) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", this.prefix));
        }
        Object property = getProperty(bindings, eLContext);
        if (property == null && this.strict) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", AbstractJsonLexerKt.NULL, objEval));
        }
        return new ValueReference(objEval, property);
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public Object invoke(Bindings bindings, ELContext eLContext, Class<?> cls, Class<?>[] clsArr, Object[] objArr) {
        Object objEval = this.prefix.eval(bindings, eLContext);
        if (objEval == null) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", this.prefix));
        }
        Object property = getProperty(bindings, eLContext);
        if (property == null && this.strict) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.method.notfound", AbstractJsonLexerKt.NULL, objEval));
        }
        String str = (String) bindings.convert(property, String.class);
        try {
            return findMethod(str, objEval.getClass(), cls, clsArr).invoke(objEval, objArr);
        } catch (IllegalAccessException unused) {
            throw new ELException(LocalMessages.get("error.property.method.access", str, objEval.getClass()));
        } catch (IllegalArgumentException e) {
            throw new ELException(LocalMessages.get("error.property.method.invocation", str, objEval.getClass()), e);
        } catch (InvocationTargetException e2) {
            throw new ELException(LocalMessages.get("error.property.method.invocation", str, objEval.getClass()), e2.getCause());
        }
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final boolean isLeftValue() {
        return this.lvalue;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final boolean isLiteralText() {
        return false;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isMethodInvocation() {
        return false;
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public boolean isReadOnly(Bindings bindings, ELContext eLContext) throws ELException {
        if (!this.lvalue) {
            return true;
        }
        Object objEval = this.prefix.eval(bindings, eLContext);
        if (objEval == null) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", this.prefix));
        }
        Object property = getProperty(bindings, eLContext);
        if (property == null && this.strict) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", AbstractJsonLexerKt.NULL, objEval));
        }
        eLContext.setPropertyResolved(false);
        boolean zIsReadOnly = eLContext.getELResolver().isReadOnly(eLContext, objEval, property);
        if (eLContext.isPropertyResolved()) {
            return zIsReadOnly;
        }
        throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", property, objEval));
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public void setValue(Bindings bindings, ELContext eLContext, Object obj) throws ELException {
        if (!this.lvalue) {
            throw new ELException(LocalMessages.get("error.value.set.rvalue", getStructuralId(bindings)));
        }
        Object objEval = this.prefix.eval(bindings, eLContext);
        if (objEval == null) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.base.null", this.prefix));
        }
        Object property = getProperty(bindings, eLContext);
        if (property == null && this.strict) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", AbstractJsonLexerKt.NULL, objEval));
        }
        eLContext.setPropertyResolved(false);
        Class<?> type = eLContext.getELResolver().getType(eLContext, objEval, property);
        if (eLContext.isPropertyResolved()) {
            if (type != null && (obj != null || type.isPrimitive())) {
                obj = bindings.convert(obj, type);
            }
            eLContext.setPropertyResolved(false);
        }
        eLContext.getELResolver().setValue(eLContext, objEval, property, obj);
        if (!eLContext.isPropertyResolved()) {
            throw new PropertyNotFoundException(LocalMessages.get("error.property.property.notfound", property, objEval));
        }
    }

    @Override // de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        if (i == 0) {
            return this.prefix;
        }
        return null;
    }

    public AstProperty(AstNode astNode, boolean z, boolean z2) {
        this(astNode, z, z2, false);
    }
}
