package com.google.common.reflect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.UnmodifiableIterator;
import j$.util.Objects;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Types {
    public static final Joiner COMMA_JOINER;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ClassOwnership {
        OWNED_BY_ENCLOSING_CLASS { // from class: com.google.common.reflect.Types.ClassOwnership.1
            @Override // com.google.common.reflect.Types.ClassOwnership
            public Class<?> getOwnerType(Class<?> rawType) {
                return rawType.getEnclosingClass();
            }
        },
        LOCAL_CLASS_HAS_NO_OWNER { // from class: com.google.common.reflect.Types.ClassOwnership.2
            @Override // com.google.common.reflect.Types.ClassOwnership
            public Class<?> getOwnerType(Class<?> rawType) {
                if (rawType.isLocalClass()) {
                    return null;
                }
                return rawType.getEnclosingClass();
            }
        };

        public static final ClassOwnership JVM_BEHAVIOR = detectJvmBehavior();

        /* JADX INFO: renamed from: com.google.common.reflect.Types$ClassOwnership$1LocalClass, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class C1LocalClass<T> {
        }

        /* JADX INFO: renamed from: com.google.common.reflect.Types$ClassOwnership$3, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass3 extends C1LocalClass<String> {
        }

        public static ClassOwnership detectJvmBehavior() {
            ParameterizedType parameterizedType = (ParameterizedType) AnonymousClass3.class.getGenericSuperclass();
            Objects.requireNonNull(parameterizedType);
            for (ClassOwnership classOwnership : values()) {
                if (classOwnership.getOwnerType(C1LocalClass.class) == parameterizedType.getOwnerType()) {
                    return classOwnership;
                }
            }
            throw new AssertionError();
        }

        public abstract Class<?> getOwnerType(Class<?> rawType);

        ClassOwnership(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
        public static final long serialVersionUID = 0;
        public final Type componentType;

        public GenericArrayTypeImpl(Type componentType) {
            this.componentType = JavaVersion.CURRENT.usedInGenericType(componentType);
        }

        public boolean equals(Object obj) {
            if (obj instanceof GenericArrayType) {
                return com.google.common.base.Objects.equal(this.componentType, ((GenericArrayType) obj).getGenericComponentType());
            }
            return false;
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return Types.toString(this.componentType) + "[]";
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 com.google.common.reflect.Types$JavaVersion, still in use, count: 1, list:
      (r0v0 com.google.common.reflect.Types$JavaVersion) from 0x0063: SPUT (r0v0 com.google.common.reflect.Types$JavaVersion) (LINE:100) com.google.common.reflect.Types.JavaVersion.CURRENT com.google.common.reflect.Types$JavaVersion
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:252)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:180)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class JavaVersion {
        JAVA6 { // from class: com.google.common.reflect.Types.JavaVersion.1
            @Override // com.google.common.reflect.Types.JavaVersion
            public GenericArrayType newArrayType(Type componentType) {
                return new GenericArrayTypeImpl(componentType);
            }

            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                type.getClass();
                if (!(type instanceof Class)) {
                    return type;
                }
                Class cls = (Class) type;
                return cls.isArray() ? new GenericArrayTypeImpl(cls.getComponentType()) : type;
            }

            @Override // com.google.common.reflect.Types.JavaVersion
            public Type newArrayType(Type componentType) {
                return new GenericArrayTypeImpl(componentType);
            }
        },
        JAVA7 { // from class: com.google.common.reflect.Types.JavaVersion.2
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type newArrayType(Type componentType) {
                return componentType instanceof Class ? Types.getArrayClass((Class) componentType) : new GenericArrayTypeImpl(componentType);
            }

            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                type.getClass();
                return type;
            }
        },
        JAVA8 { // from class: com.google.common.reflect.Types.JavaVersion.3
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type newArrayType(Type componentType) {
                return JavaVersion.JAVA7.newArrayType(componentType);
            }

            @Override // com.google.common.reflect.Types.JavaVersion
            public String typeName(Type type) {
                try {
                    return (String) Type.class.getMethod("getTypeName", null).invoke(type, null);
                } catch (IllegalAccessException e) {
                    e = e;
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException unused) {
                    throw new AssertionError("Type.getTypeName should be available in Java 8");
                } catch (InvocationTargetException e2) {
                    e = e2;
                    throw new RuntimeException(e);
                }
            }

            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                return JavaVersion.JAVA7.usedInGenericType(type);
            }
        },
        JAVA9 { // from class: com.google.common.reflect.Types.JavaVersion.4
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type newArrayType(Type componentType) {
                return JavaVersion.JAVA8.newArrayType(componentType);
            }

            @Override // com.google.common.reflect.Types.JavaVersion
            public String typeName(Type type) {
                return JavaVersion.JAVA8.typeName(type);
            }

            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                return JavaVersion.JAVA8.usedInGenericType(type);
            }
        };

        public static final JavaVersion CURRENT;

        /* JADX INFO: renamed from: com.google.common.reflect.Types$JavaVersion$5, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass5 extends TypeCapture<Map.Entry<String, int[][]>> {
        }

        /* JADX INFO: renamed from: com.google.common.reflect.Types$JavaVersion$6, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass6 extends TypeCapture<int[]> {
        }

        static {
            if (AnnotatedElement.class.isAssignableFrom(TypeVariable.class)) {
                if (new AnonymousClass5().capture().toString().contains("java.util.Map.java.util.Map")) {
                    CURRENT = javaVersion;
                    return;
                } else {
                    CURRENT = javaVersion;
                    return;
                }
            }
            if (new AnonymousClass6().capture() instanceof Class) {
                CURRENT = javaVersion;
            } else {
                CURRENT = javaVersion;
            }
        }

        public JavaVersion() {
        }

        public static JavaVersion valueOf(String name) {
            return (JavaVersion) Enum.valueOf(JavaVersion.class, name);
        }

        public static JavaVersion[] values() {
            return (JavaVersion[]) $VALUES.clone();
        }

        public boolean jdkTypeDuplicatesOwnerName() {
            return !(this instanceof AnonymousClass4);
        }

        public abstract Type newArrayType(Type componentType);

        public String typeName(Type type) {
            return Types.toString(type);
        }

        public final ImmutableList<Type> usedInGenericType(Type[] types) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (Type type : types) {
                builder.add(usedInGenericType(type));
            }
            return builder.build();
        }

        public abstract Type usedInGenericType(Type type);

        public JavaVersion(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NativeTypeVariableEquals<X> {
        public static final boolean NATIVE_TYPE_VARIABLE_ONLY = !NativeTypeVariableEquals.class.getTypeParameters()[0].equals(Types.newArtificialTypeVariable(NativeTypeVariableEquals.class, "X", new Type[0]));
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
        public static final long serialVersionUID = 0;
        public final ImmutableList<Type> argumentsList;
        public final Type ownerType;
        public final Class<?> rawType;

        public ParameterizedTypeImpl(Type ownerType, Class<?> rawType, Type[] typeArguments) {
            rawType.getClass();
            Preconditions.checkArgument(typeArguments.length == rawType.getTypeParameters().length);
            Types.disallowPrimitiveType(typeArguments, "type parameter");
            this.ownerType = ownerType;
            this.rawType = rawType;
            this.argumentsList = JavaVersion.CURRENT.usedInGenericType(typeArguments);
        }

        public boolean equals(Object other) {
            if (!(other instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) other;
            return this.rawType.equals(parameterizedType.getRawType()) && com.google.common.base.Objects.equal(this.ownerType, parameterizedType.getOwnerType()) && Arrays.equals(Types.toArray(this.argumentsList), parameterizedType.getActualTypeArguments());
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return Types.toArray(this.argumentsList);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            Type type = this.ownerType;
            return ((type == null ? 0 : type.hashCode()) ^ this.argumentsList.hashCode()) ^ this.rawType.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.ownerType != null) {
                JavaVersion javaVersion = JavaVersion.CURRENT;
                if (javaVersion.jdkTypeDuplicatesOwnerName()) {
                    sb.append(javaVersion.typeName(this.ownerType));
                    sb.append('.');
                }
            }
            sb.append(this.rawType.getName());
            sb.append('<');
            Joiner joiner = Types.COMMA_JOINER;
            ImmutableList<Type> immutableList = this.argumentsList;
            final JavaVersion javaVersion2 = JavaVersion.CURRENT;
            Objects.requireNonNull(javaVersion2);
            sb.append(joiner.join(Iterables.transform(immutableList, new Function() { // from class: com.google.common.reflect.Types$ParameterizedTypeImpl$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return javaVersion2.typeName((Type) obj);
                }
            })));
            sb.append('>');
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TypeVariableImpl<D extends GenericDeclaration> {
        public final ImmutableList<Type> bounds;
        public final D genericDeclaration;
        public final String name;

        public TypeVariableImpl(D genericDeclaration, String name, Type[] bounds) {
            Types.disallowPrimitiveType(bounds, "bound for type variable");
            genericDeclaration.getClass();
            this.genericDeclaration = genericDeclaration;
            name.getClass();
            this.name = name;
            this.bounds = ImmutableList.copyOf(bounds);
        }

        public boolean equals(Object obj) {
            if (!NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY) {
                if (obj instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) obj;
                    if (this.name.equals(typeVariable.getName()) && this.genericDeclaration.equals(typeVariable.getGenericDeclaration())) {
                        return true;
                    }
                }
                return false;
            }
            if (obj != null && Proxy.isProxyClass(obj.getClass()) && (Proxy.getInvocationHandler(obj) instanceof TypeVariableInvocationHandler)) {
                TypeVariableImpl typeVariableImpl = ((TypeVariableInvocationHandler) Proxy.getInvocationHandler(obj)).typeVariableImpl;
                if (this.name.equals(typeVariableImpl.name) && this.genericDeclaration.equals(typeVariableImpl.genericDeclaration) && this.bounds.equals(typeVariableImpl.bounds)) {
                    return true;
                }
            }
            return false;
        }

        public Type[] getBounds() {
            return Types.toArray(this.bounds);
        }

        public D getGenericDeclaration() {
            return this.genericDeclaration;
        }

        public String getName() {
            return this.name;
        }

        public String getTypeName() {
            return this.name;
        }

        public int hashCode() {
            return this.genericDeclaration.hashCode() ^ this.name.hashCode();
        }

        public String toString() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TypeVariableInvocationHandler implements InvocationHandler {
        public static final ImmutableMap<String, Method> typeVariableMethods;
        public final TypeVariableImpl<?> typeVariableImpl;

        static {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (Method method : TypeVariableImpl.class.getMethods()) {
                if (method.getDeclaringClass().equals(TypeVariableImpl.class)) {
                    try {
                        method.setAccessible(true);
                    } catch (AccessControlException unused) {
                    }
                    builder.put(method.getName(), method);
                }
            }
            typeVariableMethods = builder.build(false);
        }

        public TypeVariableInvocationHandler(TypeVariableImpl<?> typeVariableImpl) {
            this.typeVariableImpl = typeVariableImpl;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String name = method.getName();
            Method method2 = typeVariableMethods.get(name);
            if (method2 == null) {
                throw new UnsupportedOperationException(name);
            }
            try {
                return method2.invoke(this.typeVariableImpl, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class WildcardTypeImpl implements WildcardType, Serializable {
        public static final long serialVersionUID = 0;
        public final ImmutableList<Type> lowerBounds;
        public final ImmutableList<Type> upperBounds;

        public WildcardTypeImpl(Type[] lowerBounds, Type[] upperBounds) {
            Types.disallowPrimitiveType(lowerBounds, "lower bound for wildcard");
            Types.disallowPrimitiveType(upperBounds, "upper bound for wildcard");
            JavaVersion javaVersion = JavaVersion.CURRENT;
            this.lowerBounds = javaVersion.usedInGenericType(lowerBounds);
            this.upperBounds = javaVersion.usedInGenericType(upperBounds);
        }

        public boolean equals(Object obj) {
            if (obj instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) obj;
                if (this.lowerBounds.equals(Arrays.asList(wildcardType.getLowerBounds())) && this.upperBounds.equals(Arrays.asList(wildcardType.getUpperBounds()))) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            return Types.toArray(this.lowerBounds);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return Types.toArray(this.upperBounds);
        }

        public int hashCode() {
            return this.lowerBounds.hashCode() ^ this.upperBounds.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("?");
            UnmodifiableIterator<Type> it = this.lowerBounds.iterator();
            while (it.hasNext()) {
                Type next = it.next();
                sb.append(" super ");
                sb.append(JavaVersion.CURRENT.typeName(next));
            }
            Iterator it2 = ((Iterables.AnonymousClass4) Types.filterUpperBounds(this.upperBounds)).iterator();
            while (true) {
                AbstractIterator abstractIterator = (AbstractIterator) it2;
                if (!abstractIterator.hasNext()) {
                    return sb.toString();
                }
                Type type = (Type) abstractIterator.next();
                sb.append(" extends ");
                sb.append(JavaVersion.CURRENT.typeName(type));
            }
        }
    }

    static {
        Joiner joiner = new Joiner(", ");
        COMMA_JOINER = new Joiner.AnonymousClass1(joiner, joiner, AbstractJsonLexerKt.NULL);
    }

    public static void disallowPrimitiveType(Type[] types, String usedAs) {
        for (Type type : types) {
            if (type instanceof Class) {
                Preconditions.checkArgument(!r2.isPrimitive(), "Primitive type '%s' used as %s", (Class) type, usedAs);
            }
        }
    }

    public static Iterable<Type> filterUpperBounds(Iterable<Type> bounds) {
        return Iterables.filter(bounds, new Predicates.NotPredicate(Predicates.equalTo(Object.class)));
    }

    public static Class<?> getArrayClass(Class<?> componentType) {
        return Array.newInstance(componentType, 0).getClass();
    }

    public static Type getComponentType(Type type) {
        type.getClass();
        final AtomicReference atomicReference = new AtomicReference();
        new TypeVisitor() { // from class: com.google.common.reflect.Types.1
            @Override // com.google.common.reflect.TypeVisitor
            public void visitClass(Class<?> t) {
                atomicReference.set(t.getComponentType());
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitGenericArrayType(GenericArrayType t) {
                atomicReference.set(t.getGenericComponentType());
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitTypeVariable(TypeVariable<?> t) {
                atomicReference.set(Types.subtypeOfComponentType(t.getBounds()));
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitWildcardType(WildcardType t) {
                atomicReference.set(Types.subtypeOfComponentType(t.getUpperBounds()));
            }
        }.visit(type);
        return (Type) atomicReference.get();
    }

    public static Type newArrayType(Type componentType) {
        if (!(componentType instanceof WildcardType)) {
            return JavaVersion.CURRENT.newArrayType(componentType);
        }
        WildcardType wildcardType = (WildcardType) componentType;
        Type[] lowerBounds = wildcardType.getLowerBounds();
        Preconditions.checkArgument(lowerBounds.length <= 1, "Wildcard cannot have more than one lower bounds.");
        if (lowerBounds.length == 1) {
            return supertypeOf(newArrayType(lowerBounds[0]));
        }
        Type[] upperBounds = wildcardType.getUpperBounds();
        Preconditions.checkArgument(upperBounds.length == 1, "Wildcard should have only one upper bound.");
        return subtypeOf(newArrayType(upperBounds[0]));
    }

    public static <D extends GenericDeclaration> TypeVariable<D> newArtificialTypeVariable(D declaration, String name, Type... bounds) {
        if (bounds.length == 0) {
            bounds = new Type[]{Object.class};
        }
        return newTypeVariableImpl(declaration, name, bounds);
    }

    public static ParameterizedType newParameterizedType(Class<?> rawType, Type... arguments) {
        return new ParameterizedTypeImpl(ClassOwnership.JVM_BEHAVIOR.getOwnerType(rawType), rawType, arguments);
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type ownerType, Class<?> rawType, Type... arguments) {
        if (ownerType == null) {
            return newParameterizedType(rawType, arguments);
        }
        arguments.getClass();
        Preconditions.checkArgument(rawType.getEnclosingClass() != null, "Owner type for unenclosed %s", rawType);
        return new ParameterizedTypeImpl(ownerType, rawType, arguments);
    }

    public static <D extends GenericDeclaration> TypeVariable<D> newTypeVariableImpl(D genericDeclaration, String name, Type[] bounds) {
        return (TypeVariable) Reflection.newProxy(TypeVariable.class, new TypeVariableInvocationHandler(new TypeVariableImpl(genericDeclaration, name, bounds)));
    }

    @VisibleForTesting
    public static WildcardType subtypeOf(Type upperBound) {
        return new WildcardTypeImpl(new Type[0], new Type[]{upperBound});
    }

    public static Type subtypeOfComponentType(Type[] bounds) {
        for (Type type : bounds) {
            Type componentType = getComponentType(type);
            if (componentType != null) {
                if (componentType instanceof Class) {
                    Class cls = (Class) componentType;
                    if (cls.isPrimitive()) {
                        return cls;
                    }
                }
                return subtypeOf(componentType);
            }
        }
        return null;
    }

    @VisibleForTesting
    public static WildcardType supertypeOf(Type lowerBound) {
        return new WildcardTypeImpl(new Type[]{lowerBound}, new Type[]{Object.class});
    }

    public static Type[] toArray(Collection<Type> types) {
        return (Type[]) types.toArray(new Type[0]);
    }

    public static String toString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }
}
