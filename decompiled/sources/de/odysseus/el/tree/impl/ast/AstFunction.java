package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.tree.Bindings;
import de.odysseus.el.tree.FunctionNode;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.el.ELContext;
import javax.el.ELException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AstFunction extends AstRightValue implements FunctionNode {
    public final int index;
    public final String name;
    public final AstParameters params;
    public final boolean varargs;

    public AstFunction(String str, int i, AstParameters astParameters, boolean z) {
        this.name = str;
        this.index = i;
        this.params = astParameters;
        this.varargs = z;
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public void appendStructure(StringBuilder sb, Bindings bindings) {
        sb.append((bindings == null || !bindings.isFunctionBound(this.index)) ? this.name : "<fn>");
        this.params.appendStructure(sb, bindings);
    }

    @Override // de.odysseus.el.tree.impl.ast.AstNode
    public Object eval(Bindings bindings, ELContext eLContext) {
        try {
            return invoke(bindings, eLContext, null, bindings.getFunction(this.index));
        } catch (IllegalAccessException e) {
            throw new ELException(LocalMessages.get("error.function.access", this.name), e);
        } catch (InvocationTargetException e2) {
            throw new ELException(LocalMessages.get("error.function.invocation", this.name), e2.getCause());
        }
    }

    @Override // de.odysseus.el.tree.Node
    public int getCardinality() {
        return 1;
    }

    @Override // de.odysseus.el.tree.FunctionNode
    public int getIndex() {
        return this.index;
    }

    @Override // de.odysseus.el.tree.FunctionNode
    public String getName() {
        return this.name;
    }

    public AstNode getParam(int i) {
        return this.params.getChild(i);
    }

    @Override // de.odysseus.el.tree.FunctionNode
    public int getParamCount() {
        return this.params.getCardinality();
    }

    public Object invoke(Bindings bindings, ELContext eLContext, Object obj, Method method) throws IllegalAccessException, InvocationTargetException {
        Object[] objArr;
        Object objNewInstance;
        Object objEval;
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length > 0) {
            int length = parameterTypes.length;
            objArr = new Object[length];
            int i = 0;
            if (this.varargs && method.isVarArgs()) {
                for (int i2 = 0; i2 < length - 1; i2++) {
                    Object objEval2 = getParam(i2).eval(bindings, eLContext);
                    if (objEval2 != null || parameterTypes[i2].isPrimitive()) {
                        objArr[i2] = bindings.convert(objEval2, parameterTypes[i2]);
                    }
                }
                int length2 = parameterTypes.length - 1;
                Class<?> componentType = parameterTypes[length2].getComponentType();
                int paramCount = getParamCount() - length2;
                if (paramCount == 1) {
                    objEval = getParam(length2).eval(bindings, eLContext);
                    if (objEval == null || !objEval.getClass().isArray()) {
                        objNewInstance = Array.newInstance(componentType, 1);
                        if (objEval != null || componentType.isPrimitive()) {
                            Array.set(objNewInstance, 0, bindings.convert(objEval, componentType));
                        }
                    } else {
                        if (!parameterTypes[length2].isInstance(objEval)) {
                            int length3 = Array.getLength(objEval);
                            Object objNewInstance2 = Array.newInstance(componentType, length3);
                            while (i < length3) {
                                Object obj2 = Array.get(objEval, i);
                                if (obj2 != null || componentType.isPrimitive()) {
                                    Array.set(objNewInstance2, i, bindings.convert(obj2, componentType));
                                }
                                i++;
                            }
                            objEval = objNewInstance2;
                        }
                        objArr[length2] = objEval;
                    }
                } else {
                    objNewInstance = Array.newInstance(componentType, paramCount);
                    while (i < paramCount) {
                        Object objEval3 = getParam(length2 + i).eval(bindings, eLContext);
                        if (objEval3 != null || componentType.isPrimitive()) {
                            Array.set(objNewInstance, i, bindings.convert(objEval3, componentType));
                        }
                        i++;
                    }
                }
                objEval = objNewInstance;
                objArr[length2] = objEval;
            } else {
                while (i < length) {
                    Object objEval4 = getParam(i).eval(bindings, eLContext);
                    if (objEval4 != null || parameterTypes[i].isPrimitive()) {
                        objArr[i] = bindings.convert(objEval4, parameterTypes[i]);
                    }
                    i++;
                }
            }
        } else {
            objArr = null;
        }
        return method.invoke(obj, objArr);
    }

    @Override // de.odysseus.el.tree.FunctionNode
    public boolean isVarArgs() {
        return this.varargs;
    }

    public String toString() {
        return this.name;
    }

    @Override // de.odysseus.el.tree.Node
    public AstNode getChild(int i) {
        if (i == 0) {
            return this.params;
        }
        return null;
    }

    public AstFunction(String str, int i, AstParameters astParameters) {
        this(str, i, astParameters, false);
    }
}
