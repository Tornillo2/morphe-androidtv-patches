package de.odysseus.el.tree;

import de.odysseus.el.misc.TypeConverter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.el.ValueExpression;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class Bindings implements TypeConverter {
    public static final Method[] NO_FUNCTIONS = new Method[0];
    public static final ValueExpression[] NO_VARIABLES = new ValueExpression[0];
    public static final long serialVersionUID = 1;
    public final TypeConverter converter;
    public transient Method[] functions;
    public final ValueExpression[] variables;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MethodWrapper implements Serializable {
        public static final long serialVersionUID = 1;
        public transient Method method;

        public final void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            try {
                this.method = ((Class) objectInputStream.readObject()).getDeclaredMethod((String) objectInputStream.readObject(), (Class[]) objectInputStream.readObject());
            } catch (NoSuchMethodException e) {
                throw new IOException(e.getMessage());
            }
        }

        public final void writeObject(ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.method.getDeclaringClass());
            objectOutputStream.writeObject(this.method.getName());
            objectOutputStream.writeObject(this.method.getParameterTypes());
        }

        public MethodWrapper(Method method) {
            this.method = method;
        }
    }

    public Bindings(Method[] methodArr, ValueExpression[] valueExpressionArr) {
        this(methodArr, valueExpressionArr, TypeConverter.DEFAULT);
    }

    @Override // de.odysseus.el.misc.TypeConverter
    public <T> T convert(Object obj, Class<T> cls) {
        return (T) this.converter.convert(obj, cls);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Bindings) {
            Bindings bindings = (Bindings) obj;
            if (Arrays.equals(this.functions, bindings.functions) && Arrays.equals(this.variables, bindings.variables) && this.converter.equals(bindings.converter)) {
                return true;
            }
        }
        return false;
    }

    public Method getFunction(int i) {
        return this.functions[i];
    }

    public ValueExpression getVariable(int i) {
        return this.variables[i];
    }

    public int hashCode() {
        return (Arrays.hashCode(this.functions) ^ Arrays.hashCode(this.variables)) ^ this.converter.hashCode();
    }

    public boolean isFunctionBound(int i) {
        return i >= 0 && i < this.functions.length;
    }

    public boolean isVariableBound(int i) {
        if (i < 0) {
            return false;
        }
        ValueExpression[] valueExpressionArr = this.variables;
        return i < valueExpressionArr.length && valueExpressionArr[i] != null;
    }

    public final void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        MethodWrapper[] methodWrapperArr = (MethodWrapper[]) objectInputStream.readObject();
        if (methodWrapperArr.length == 0) {
            this.functions = NO_FUNCTIONS;
            return;
        }
        this.functions = new Method[methodWrapperArr.length];
        int i = 0;
        while (true) {
            Method[] methodArr = this.functions;
            if (i >= methodArr.length) {
                return;
            }
            methodArr[i] = methodWrapperArr[i].method;
            i++;
        }
    }

    public final void writeObject(ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
        int length = this.functions.length;
        MethodWrapper[] methodWrapperArr = new MethodWrapper[length];
        for (int i = 0; i < length; i++) {
            methodWrapperArr[i] = new MethodWrapper(this.functions[i]);
        }
        objectOutputStream.writeObject(methodWrapperArr);
    }

    public Bindings(Method[] methodArr, ValueExpression[] valueExpressionArr, TypeConverter typeConverter) {
        this.functions = (methodArr == null || methodArr.length == 0) ? NO_FUNCTIONS : methodArr;
        this.variables = (valueExpressionArr == null || valueExpressionArr.length == 0) ? NO_VARIABLES : valueExpressionArr;
        this.converter = typeConverter == null ? TypeConverter.DEFAULT : typeConverter;
    }
}
