package de.odysseus.el.tree;

import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.misc.TypeConverter;
import java.lang.reflect.Method;
import java.util.List;
import javax.el.ELException;
import javax.el.FunctionMapper;
import javax.el.ValueExpression;
import javax.el.VariableMapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class Tree {
    public final boolean deferred;
    public final List<FunctionNode> functions;
    public final List<IdentifierNode> identifiers;
    public final ExpressionNode root;

    public Tree(ExpressionNode expressionNode, List<FunctionNode> list, List<IdentifierNode> list2, boolean z) {
        this.root = expressionNode;
        this.functions = list;
        this.identifiers = list2;
        this.deferred = z;
    }

    public Bindings bind(FunctionMapper functionMapper, VariableMapper variableMapper) {
        return bind(functionMapper, variableMapper, null);
    }

    public Iterable<FunctionNode> getFunctionNodes() {
        return this.functions;
    }

    public Iterable<IdentifierNode> getIdentifierNodes() {
        return this.identifiers;
    }

    public ExpressionNode getRoot() {
        return this.root;
    }

    public boolean isDeferred() {
        return this.deferred;
    }

    public String toString() {
        return getRoot().getStructuralId(null);
    }

    public Bindings bind(FunctionMapper functionMapper, VariableMapper variableMapper, TypeConverter typeConverter) {
        Method[] methodArr;
        ValueExpression[] valueExpressionArr = null;
        if (this.functions.isEmpty()) {
            methodArr = null;
        } else {
            if (functionMapper == null) {
                throw new ELException(LocalMessages.get("error.function.nomapper", new Object[0]));
            }
            methodArr = new Method[this.functions.size()];
            for (int i = 0; i < this.functions.size(); i++) {
                FunctionNode functionNode = this.functions.get(i);
                String name = functionNode.getName();
                int iIndexOf = name.indexOf(58);
                Method methodResolveFunction = iIndexOf < 0 ? functionMapper.resolveFunction("", name) : functionMapper.resolveFunction(name.substring(0, iIndexOf), name.substring(iIndexOf + 1));
                if (methodResolveFunction == null) {
                    throw new ELException(LocalMessages.get("error.function.notfound", name));
                }
                if (functionNode.isVarArgs() && methodResolveFunction.isVarArgs()) {
                    if (methodResolveFunction.getParameterTypes().length > functionNode.getParamCount() + 1) {
                        throw new ELException(LocalMessages.get("error.function.params", name));
                    }
                } else if (methodResolveFunction.getParameterTypes().length != functionNode.getParamCount()) {
                    throw new ELException(LocalMessages.get("error.function.params", name));
                }
                methodArr[functionNode.getIndex()] = methodResolveFunction;
            }
        }
        if (this.identifiers.size() > 0) {
            ValueExpression[] valueExpressionArr2 = new ValueExpression[this.identifiers.size()];
            for (int i2 = 0; i2 < this.identifiers.size(); i2++) {
                IdentifierNode identifierNode = this.identifiers.get(i2);
                valueExpressionArr2[identifierNode.getIndex()] = variableMapper != null ? variableMapper.resolveVariable(identifierNode.getName()) : null;
            }
            valueExpressionArr = valueExpressionArr2;
        }
        return new Bindings(methodArr, valueExpressionArr, typeConverter);
    }
}
