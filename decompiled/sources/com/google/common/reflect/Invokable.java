package com.google.common.reflect;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Invokable<T, R> implements AnnotatedElement, Member {
    public static final boolean ANNOTATED_TYPE_EXISTS = initAnnotatedTypeExists();
    public final AccessibleObject accessibleObject;
    public final Member member;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ConstructorInvokable<T> extends Invokable<T, T> {
        public final Constructor<?> constructor;

        public ConstructorInvokable(Constructor<?> constructor) {
            super(constructor);
            this.constructor = constructor;
        }

        @Override // com.google.common.reflect.Invokable
        public Type[] getGenericExceptionTypes() {
            return this.constructor.getGenericExceptionTypes();
        }

        @Override // com.google.common.reflect.Invokable
        public Type[] getGenericParameterTypes() {
            Type[] genericParameterTypes = this.constructor.getGenericParameterTypes();
            if (genericParameterTypes.length <= 0 || !mayNeedHiddenThis()) {
                return genericParameterTypes;
            }
            Class<?>[] parameterTypes = this.constructor.getParameterTypes();
            return (genericParameterTypes.length == parameterTypes.length && parameterTypes[0] == this.member.getDeclaringClass().getEnclosingClass()) ? (Type[]) Arrays.copyOfRange(genericParameterTypes, 1, genericParameterTypes.length) : genericParameterTypes;
        }

        @Override // com.google.common.reflect.Invokable
        public Type getGenericReturnType() {
            Class<?> declaringClass = this.member.getDeclaringClass();
            TypeVariable<Class<?>>[] typeParameters = declaringClass.getTypeParameters();
            return typeParameters.length > 0 ? Types.newParameterizedType(declaringClass, typeParameters) : declaringClass;
        }

        @Override // com.google.common.reflect.Invokable
        public final Annotation[][] getParameterAnnotations() {
            return this.constructor.getParameterAnnotations();
        }

        @Override // com.google.common.reflect.Invokable
        public final TypeVariable<?>[] getTypeParameters() {
            TypeVariable<Class<?>>[] typeParameters = this.member.getDeclaringClass().getTypeParameters();
            TypeVariable<Constructor<?>>[] typeParameters2 = this.constructor.getTypeParameters();
            TypeVariable<?>[] typeVariableArr = new TypeVariable[typeParameters.length + typeParameters2.length];
            System.arraycopy(typeParameters, 0, typeVariableArr, 0, typeParameters.length);
            System.arraycopy(typeParameters2, 0, typeVariableArr, typeParameters.length, typeParameters2.length);
            return typeVariableArr;
        }

        @Override // com.google.common.reflect.Invokable
        public final Object invokeInternal(Object receiver, Object[] args) throws IllegalAccessException, InvocationTargetException {
            try {
                return this.constructor.newInstance(args);
            } catch (InstantiationException e) {
                throw new RuntimeException(this.constructor + " failed.", e);
            }
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isOverridable() {
            return false;
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isVarArgs() {
            return this.constructor.isVarArgs();
        }

        public final boolean mayNeedHiddenThis() {
            Class<?> declaringClass = this.constructor.getDeclaringClass();
            if (declaringClass.getEnclosingConstructor() != null) {
                return true;
            }
            return declaringClass.getEnclosingMethod() != null ? !Modifier.isStatic(r1.getModifiers()) : (declaringClass.getEnclosingClass() == null || Modifier.isStatic(declaringClass.getModifiers())) ? false : true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MethodInvokable<T> extends Invokable<T, Object> {
        public final Method method;

        public MethodInvokable(Method method) {
            super(method);
            this.method = method;
        }

        @Override // com.google.common.reflect.Invokable
        public Type[] getGenericExceptionTypes() {
            return this.method.getGenericExceptionTypes();
        }

        @Override // com.google.common.reflect.Invokable
        public Type[] getGenericParameterTypes() {
            return this.method.getGenericParameterTypes();
        }

        @Override // com.google.common.reflect.Invokable
        public Type getGenericReturnType() {
            return this.method.getGenericReturnType();
        }

        @Override // com.google.common.reflect.Invokable
        public final Annotation[][] getParameterAnnotations() {
            return this.method.getParameterAnnotations();
        }

        @Override // com.google.common.reflect.Invokable
        public final TypeVariable<?>[] getTypeParameters() {
            return this.method.getTypeParameters();
        }

        @Override // com.google.common.reflect.Invokable
        public final Object invokeInternal(Object receiver, Object[] args) throws IllegalAccessException, InvocationTargetException {
            return this.method.invoke(receiver, args);
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isOverridable() {
            return (isFinal() || isPrivate() || isStatic() || Modifier.isFinal(this.member.getDeclaringClass().getModifiers())) ? false : true;
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isVarArgs() {
            return this.method.isVarArgs();
        }
    }

    public <M extends AccessibleObject & Member> Invokable(M member) {
        member.getClass();
        this.accessibleObject = member;
        this.member = member;
    }

    public static Invokable<?, Object> from(Method method) {
        return new MethodInvokable(method);
    }

    public static boolean initAnnotatedTypeExists() {
        try {
            Class.forName("java.lang.reflect.AnnotatedType");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Invokable) {
            Invokable invokable = (Invokable) obj;
            if (getOwnerType().equals(invokable.getOwnerType()) && this.member.equals(invokable.member)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.reflect.AnnotatedElement
    public final <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this.accessibleObject.getAnnotation(cls);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public final Annotation[] getAnnotations() {
        return this.accessibleObject.getAnnotations();
    }

    @Override // java.lang.reflect.AnnotatedElement
    public final Annotation[] getDeclaredAnnotations() {
        return this.accessibleObject.getDeclaredAnnotations();
    }

    @Override // java.lang.reflect.Member
    public final Class<? super T> getDeclaringClass() {
        return (Class<? super T>) this.member.getDeclaringClass();
    }

    public final ImmutableList<TypeToken<? extends Throwable>> getExceptionTypes() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type : getGenericExceptionTypes()) {
            builder.add(new TypeToken.SimpleTypeToken(type));
        }
        return builder.build();
    }

    public abstract Type[] getGenericExceptionTypes();

    public abstract Type[] getGenericParameterTypes();

    public abstract Type getGenericReturnType();

    @Override // java.lang.reflect.Member
    public final int getModifiers() {
        return this.member.getModifiers();
    }

    @Override // java.lang.reflect.Member
    public final String getName() {
        return this.member.getName();
    }

    public TypeToken<T> getOwnerType() {
        return new TypeToken.SimpleTypeToken(this.member.getDeclaringClass());
    }

    public abstract Annotation[][] getParameterAnnotations();

    @IgnoreJRERequirement
    public final ImmutableList<Parameter> getParameters() {
        Type[] genericParameterTypes = getGenericParameterTypes();
        Annotation[][] parameterAnnotations = getParameterAnnotations();
        Object[] objArr = new Object[genericParameterTypes.length];
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < genericParameterTypes.length; i++) {
            builder.add(new Parameter(this, i, new TypeToken.SimpleTypeToken(genericParameterTypes[i]), parameterAnnotations[i], objArr[i]));
        }
        return builder.build();
    }

    public final TypeToken<? extends R> getReturnType() {
        return new TypeToken.SimpleTypeToken(getGenericReturnType());
    }

    public abstract TypeVariable<?>[] getTypeParameters();

    public int hashCode() {
        return this.member.hashCode();
    }

    @CanIgnoreReturnValue
    public final R invoke(T t, Object... objArr) throws IllegalAccessException, InvocationTargetException {
        objArr.getClass();
        return (R) invokeInternal(t, objArr);
    }

    public abstract Object invokeInternal(Object receiver, Object[] args) throws IllegalAccessException, InvocationTargetException;

    public final boolean isAbstract() {
        return Modifier.isAbstract(this.member.getModifiers());
    }

    public final boolean isAccessible() {
        return this.accessibleObject.isAccessible();
    }

    @Override // java.lang.reflect.AnnotatedElement
    public final boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return this.accessibleObject.isAnnotationPresent(annotationClass);
    }

    public final boolean isFinal() {
        return Modifier.isFinal(this.member.getModifiers());
    }

    public final boolean isNative() {
        return Modifier.isNative(this.member.getModifiers());
    }

    public abstract boolean isOverridable();

    public final boolean isPackagePrivate() {
        return (isPrivate() || isPublic() || isProtected()) ? false : true;
    }

    public final boolean isPrivate() {
        return Modifier.isPrivate(this.member.getModifiers());
    }

    public final boolean isProtected() {
        return Modifier.isProtected(this.member.getModifiers());
    }

    public final boolean isPublic() {
        return Modifier.isPublic(this.member.getModifiers());
    }

    public final boolean isStatic() {
        return Modifier.isStatic(this.member.getModifiers());
    }

    public final boolean isSynchronized() {
        return Modifier.isSynchronized(this.member.getModifiers());
    }

    @Override // java.lang.reflect.Member
    public final boolean isSynthetic() {
        return this.member.isSynthetic();
    }

    public final boolean isTransient() {
        return Modifier.isTransient(this.member.getModifiers());
    }

    public abstract boolean isVarArgs();

    public final boolean isVolatile() {
        return Modifier.isVolatile(this.member.getModifiers());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R1 extends R> Invokable<T, R1> returning(Class<R1> returnType) {
        returning(new TypeToken.SimpleTypeToken(returnType));
        return this;
    }

    public final void setAccessible(boolean flag) {
        this.accessibleObject.setAccessible(flag);
    }

    public String toString() {
        return this.member.toString();
    }

    public final boolean trySetAccessible() {
        try {
            this.accessibleObject.setAccessible(true);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static <T> Invokable<T, T> from(Constructor<T> constructor) {
        return new ConstructorInvokable(constructor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R1 extends R> Invokable<T, R1> returning(TypeToken<R1> returnType) {
        if (getReturnType().isSubtypeOf(returnType.runtimeType)) {
            return this;
        }
        throw new IllegalArgumentException("Invokable is known to return " + getReturnType() + ", not " + returnType);
    }
}
