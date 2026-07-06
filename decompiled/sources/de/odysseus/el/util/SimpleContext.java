package de.odysseus.el.util;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.FunctionMapper;
import javax.el.ValueExpression;
import javax.el.VariableMapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SimpleContext extends ELContext {
    public Functions functions;
    public ELResolver resolver;
    public Variables variables;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Functions extends FunctionMapper {
        public Map<String, Method> map = Collections.EMPTY_MAP;

        @Override // javax.el.FunctionMapper
        public Method resolveFunction(String str, String str2) {
            return this.map.get(str + ":" + str2);
        }

        public void setFunction(String str, String str2, Method method) {
            if (this.map.isEmpty()) {
                this.map = new HashMap();
            }
            this.map.put(str + ":" + str2, method);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Variables extends VariableMapper {
        public Map<String, ValueExpression> map = Collections.EMPTY_MAP;

        @Override // javax.el.VariableMapper
        public ValueExpression resolveVariable(String str) {
            return this.map.get(str);
        }

        @Override // javax.el.VariableMapper
        public ValueExpression setVariable(String str, ValueExpression valueExpression) {
            if (this.map.isEmpty()) {
                this.map = new HashMap();
            }
            return this.map.put(str, valueExpression);
        }
    }

    public SimpleContext(ELResolver eLResolver) {
        this.resolver = eLResolver;
    }

    @Override // javax.el.ELContext
    public ELResolver getELResolver() {
        if (this.resolver == null) {
            this.resolver = new SimpleResolver();
        }
        return this.resolver;
    }

    @Override // javax.el.ELContext
    public FunctionMapper getFunctionMapper() {
        if (this.functions == null) {
            this.functions = new Functions();
        }
        return this.functions;
    }

    @Override // javax.el.ELContext
    public VariableMapper getVariableMapper() {
        if (this.variables == null) {
            this.variables = new Variables();
        }
        return this.variables;
    }

    public void setELResolver(ELResolver eLResolver) {
        this.resolver = eLResolver;
    }

    public void setFunction(String str, String str2, Method method) {
        if (this.functions == null) {
            this.functions = new Functions();
        }
        this.functions.setFunction(str, str2, method);
    }

    public ValueExpression setVariable(String str, ValueExpression valueExpression) {
        if (this.variables == null) {
            this.variables = new Variables();
        }
        return this.variables.setVariable(str, valueExpression);
    }

    public SimpleContext() {
        this(null);
    }
}
