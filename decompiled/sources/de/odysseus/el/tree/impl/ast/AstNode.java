package de.odysseus.el.tree.impl.ast;

import de.odysseus.el.tree.Bindings;
import de.odysseus.el.tree.ExpressionNode;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import javax.el.ELContext;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AstNode implements ExpressionNode {
    public static Method findPublicAccessibleMethod(Method method) {
        Method methodFindPublicAccessibleMethod;
        if (method == null || !Modifier.isPublic(method.getModifiers())) {
            return null;
        }
        if (method.isAccessible() || Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            return method;
        }
        for (Class<?> cls : method.getDeclaringClass().getInterfaces()) {
            try {
                methodFindPublicAccessibleMethod = findPublicAccessibleMethod(cls.getMethod(method.getName(), method.getParameterTypes()));
            } catch (NoSuchMethodException unused) {
            }
            if (methodFindPublicAccessibleMethod != null) {
                return methodFindPublicAccessibleMethod;
            }
        }
        Class<? super Object> superclass = method.getDeclaringClass().getSuperclass();
        if (superclass != null) {
            try {
                Method methodFindPublicAccessibleMethod2 = findPublicAccessibleMethod(superclass.getMethod(method.getName(), method.getParameterTypes()));
                if (methodFindPublicAccessibleMethod2 != null) {
                    return methodFindPublicAccessibleMethod2;
                }
            } catch (NoSuchMethodException unused2) {
            }
        }
        return null;
    }

    public abstract void appendStructure(StringBuilder sb, Bindings bindings);

    public abstract Object eval(Bindings bindings, ELContext eLContext);

    public Method findAccessibleMethod(Method method) {
        Method methodFindPublicAccessibleMethod = findPublicAccessibleMethod(method);
        if (methodFindPublicAccessibleMethod != null || method == null || !Modifier.isPublic(method.getModifiers())) {
            return methodFindPublicAccessibleMethod;
        }
        try {
            method.setAccessible(true);
            return method;
        } catch (SecurityException unused) {
            return null;
        }
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final String getStructuralId(Bindings bindings) {
        StringBuilder sb = new StringBuilder();
        appendStructure(sb, bindings);
        return sb.toString();
    }

    @Override // de.odysseus.el.tree.ExpressionNode
    public final Object getValue(Bindings bindings, ELContext eLContext, Class<?> cls) {
        Object objEval = eval(bindings, eLContext);
        return cls != null ? bindings.convert(objEval, cls) : objEval;
    }
}
