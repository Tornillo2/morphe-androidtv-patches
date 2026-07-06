package com.amazon.livingroom.deviceproperties.expression;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.FunctionMapper;
import javax.el.ValueExpression;
import javax.el.VariableMapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ELContextImpl extends ELContext {
    public final ELResolver elResolver;
    public final FunctionMapper functionMapper;
    public VariableMapper variableMapper;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class VariableMapperImpl extends VariableMapper {
        public Map<String, ValueExpression> map;

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

        public VariableMapperImpl() {
            this.map = Collections.EMPTY_MAP;
        }
    }

    public ELContextImpl(ELResolver eLResolver, FunctionMapper functionMapper) {
        this.elResolver = eLResolver;
        this.functionMapper = functionMapper;
    }

    @Override // javax.el.ELContext
    public ELResolver getELResolver() {
        return this.elResolver;
    }

    @Override // javax.el.ELContext
    public FunctionMapper getFunctionMapper() {
        return this.functionMapper;
    }

    @Override // javax.el.ELContext
    public VariableMapper getVariableMapper() {
        if (this.variableMapper == null) {
            this.variableMapper = new VariableMapperImpl();
        }
        return this.variableMapper;
    }
}
