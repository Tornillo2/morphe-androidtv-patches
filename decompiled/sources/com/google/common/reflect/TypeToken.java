package com.google.common.reflect;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.Ordering;
import com.google.common.collect.ReverseNaturalOrdering;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeResolver;
import com.google.common.reflect.Types;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.Objects;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class TypeToken<T> extends TypeCapture<T> implements Serializable {
    public static final long serialVersionUID = 3637540370352322684L;

    @LazyInit
    public transient TypeResolver covariantTypeResolver;

    @LazyInit
    public transient TypeResolver invariantTypeResolver;
    public final Type runtimeType;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Bounds {
        public final Type[] bounds;
        public final boolean target;

        public Bounds(Type[] bounds, boolean target) {
            this.bounds = bounds;
            this.target = target;
        }

        public boolean isSubtypeOf(Type supertype) {
            for (Type type : this.bounds) {
                boolean zIsSubtypeOf = new SimpleTypeToken(type).isSubtypeOf(supertype);
                boolean z = this.target;
                if (zIsSubtypeOf == z) {
                    return z;
                }
            }
            return !this.target;
        }

        public boolean isSupertypeOf(Type subtype) {
            SimpleTypeToken simpleTypeToken = new SimpleTypeToken(subtype);
            for (Type type : this.bounds) {
                boolean zIsSubtypeOf = simpleTypeToken.isSubtypeOf(type);
                boolean z = this.target;
                if (zIsSubtypeOf == z) {
                    return z;
                }
            }
            return !this.target;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SimpleTypeToken<T> extends TypeToken<T> {
        public static final long serialVersionUID = 0;

        public SimpleTypeToken(Type type) {
            super(type);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class TypeCollector<K> {
        public static final TypeCollector<TypeToken<?>> FOR_GENERIC_TYPE = new AnonymousClass1();
        public static final TypeCollector<Class<?>> FOR_RAW_TYPE = new AnonymousClass2();

        /* JADX INFO: renamed from: com.google.common.reflect.TypeToken$TypeCollector$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 extends TypeCollector<TypeToken<?>> {
            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Iterable<? extends TypeToken<?>> getInterfaces(TypeToken<?> type) {
                return type.getGenericInterfaces();
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Class<?> getRawType(TypeToken<?> type) {
                return type.getRawType();
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public TypeToken<?> getSuperclass(TypeToken<?> type) {
                return type.getGenericSuperclass();
            }
        }

        /* JADX INFO: renamed from: com.google.common.reflect.TypeToken$TypeCollector$2, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass2 extends TypeCollector<Class<?>> {
            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Class<?> getRawType(Class<?> type) {
                return type;
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Iterable<? extends Class<?>> getInterfaces(Class<?> type) {
                return Arrays.asList(type.getInterfaces());
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Class<?> getSuperclass(Class<?> type) {
                return type.getSuperclass();
            }
        }

        /* JADX INFO: renamed from: com.google.common.reflect.TypeToken$TypeCollector$3, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass3 extends ForwardingTypeCollector<K> {
            public AnonymousClass3(TypeCollector delegate) {
                super(delegate);
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public ImmutableList<K> collectTypes(Iterable<? extends K> types) {
                ImmutableList.Builder builder = ImmutableList.builder();
                for (K k : types) {
                    if (!getRawType(k).isInterface()) {
                        builder.add(k);
                    }
                }
                return super.collectTypes((Iterable) builder.build());
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector.ForwardingTypeCollector, com.google.common.reflect.TypeToken.TypeCollector
            public Iterable<? extends K> getInterfaces(K type) {
                return ImmutableSet.of();
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class ForwardingTypeCollector<K> extends TypeCollector<K> {
            public final TypeCollector<K> delegate;

            public ForwardingTypeCollector(TypeCollector<K> delegate) {
                this.delegate = delegate;
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Iterable<? extends K> getInterfaces(K type) {
                return this.delegate.getInterfaces(type);
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Class<?> getRawType(K type) {
                return this.delegate.getRawType(type);
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public K getSuperclass(K type) {
                return this.delegate.getSuperclass(type);
            }
        }

        public TypeCollector() {
        }

        public static <K, V> ImmutableList<K> sortKeysByValue(final Map<K, V> map, final Comparator<? super V> valueComparator) {
            return ImmutableList.sortedCopyOf(new Ordering<K>() { // from class: com.google.common.reflect.TypeToken.TypeCollector.4
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.collect.Ordering, java.util.Comparator
                public int compare(K left, K right) {
                    Comparator comparator = valueComparator;
                    Object obj = map.get(left);
                    Objects.requireNonNull(obj);
                    Object obj2 = map.get(right);
                    Objects.requireNonNull(obj2);
                    return comparator.compare(obj, obj2);
                }
            }, map.keySet());
        }

        public final TypeCollector<K> classesOnly() {
            return new AnonymousClass3(this);
        }

        public ImmutableList<K> collectTypes(Iterable<? extends K> types) {
            HashMap map = new HashMap();
            Iterator<? extends K> it = types.iterator();
            while (it.hasNext()) {
                collectTypes(it.next(), map);
            }
            NaturalOrdering.INSTANCE.getClass();
            return sortKeysByValue(map, ReverseNaturalOrdering.INSTANCE);
        }

        public abstract Iterable<? extends K> getInterfaces(K type);

        public abstract Class<?> getRawType(K type);

        public abstract K getSuperclass(K type);

        public TypeCollector(AnonymousClass1 anonymousClass1) {
        }

        public final ImmutableList<K> collectTypes(K type) {
            return collectTypes((Iterable) ImmutableList.of(type));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        public final int collectTypes(K k, Map<? super K, Integer> map) {
            Integer num = map.get(k);
            if (num != null) {
                return num.intValue();
            }
            boolean zIsInterface = getRawType(k).isInterface();
            Iterator<? extends K> it = getInterfaces(k).iterator();
            int iMax = zIsInterface;
            while (it.hasNext()) {
                iMax = Math.max(iMax, collectTypes(it.next(), map));
            }
            K superclass = getSuperclass(k);
            int iMax2 = iMax;
            if (superclass != null) {
                iMax2 = Math.max(iMax, collectTypes(superclass, map));
            }
            int i = iMax2 + 1;
            map.put(k, Integer.valueOf(i));
            return i;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum TypeFilter implements Predicate<TypeToken<?>> {
        IGNORE_TYPE_VARIABLE_OR_WILDCARD { // from class: com.google.common.reflect.TypeToken.TypeFilter.1
            @Override // com.google.common.base.Predicate
            public boolean apply(TypeToken<?> type) {
                Type type2 = type.runtimeType;
                return ((type2 instanceof TypeVariable) || (type2 instanceof WildcardType)) ? false : true;
            }
        },
        INTERFACE_ONLY { // from class: com.google.common.reflect.TypeToken.TypeFilter.2
            @Override // com.google.common.base.Predicate
            public boolean apply(TypeToken<?> type) {
                return type.getRawType().isInterface();
            }
        };

        TypeFilter(AnonymousClass1 anonymousClass1) {
        }
    }

    public TypeToken() {
        Type typeCapture = capture();
        this.runtimeType = typeCapture;
        Preconditions.checkState(!(typeCapture instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", typeCapture);
    }

    public static Bounds any(Type[] bounds) {
        return new Bounds(bounds, true);
    }

    public static Type canonicalizeTypeArg(TypeVariable<?> declaration, Type typeArg) {
        return typeArg instanceof WildcardType ? canonicalizeWildcardType(declaration, (WildcardType) typeArg) : canonicalizeWildcardsInType(typeArg);
    }

    public static WildcardType canonicalizeWildcardType(TypeVariable<?> declaration, WildcardType type) {
        Type[] bounds = declaration.getBounds();
        ArrayList arrayList = new ArrayList();
        for (Type type2 : type.getUpperBounds()) {
            if (!any(bounds).isSubtypeOf(type2)) {
                arrayList.add(canonicalizeWildcardsInType(type2));
            }
        }
        return new Types.WildcardTypeImpl(type.getLowerBounds(), (Type[]) arrayList.toArray(new Type[0]));
    }

    public static ParameterizedType canonicalizeWildcardsInParameterizedType(ParameterizedType type) {
        Class cls = (Class) type.getRawType();
        TypeVariable<Class<T>>[] typeParameters = cls.getTypeParameters();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            actualTypeArguments[i] = canonicalizeTypeArg(typeParameters[i], actualTypeArguments[i]);
        }
        return Types.newParameterizedTypeWithOwner(type.getOwnerType(), cls, actualTypeArguments);
    }

    public static Type canonicalizeWildcardsInType(Type type) {
        return type instanceof ParameterizedType ? canonicalizeWildcardsInParameterizedType((ParameterizedType) type) : type instanceof GenericArrayType ? Types.newArrayType(canonicalizeWildcardsInType(((GenericArrayType) type).getGenericComponentType())) : type;
    }

    public static Bounds every(Type[] bounds) {
        return new Bounds(bounds, false);
    }

    public static Type newArrayClassOrGenericArrayType(Type componentType) {
        return Types.JavaVersion.JAVA7.newArrayType(componentType);
    }

    public static <T> TypeToken<T> of(Class<T> type) {
        return new SimpleTypeToken(type);
    }

    @VisibleForTesting
    public static <T> TypeToken<? extends T> toGenericType(Class<T> cls) {
        if (cls.isArray()) {
            return new SimpleTypeToken(Types.newArrayType(toGenericType(cls.getComponentType()).runtimeType));
        }
        TypeVariable<Class<T>>[] typeParameters = cls.getTypeParameters();
        Type type = (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) ? null : toGenericType(cls.getEnclosingClass()).runtimeType;
        return (typeParameters.length > 0 || !(type == null || type == cls.getEnclosingClass())) ? new SimpleTypeToken(Types.newParameterizedTypeWithOwner(type, cls, typeParameters)) : new SimpleTypeToken(cls);
    }

    public final TypeToken<? super T> boundAsSuperclass(Type bound) {
        SimpleTypeToken simpleTypeToken = new SimpleTypeToken(bound);
        if (simpleTypeToken.getRawType().isInterface()) {
            return null;
        }
        return simpleTypeToken;
    }

    public final ImmutableList<TypeToken<? super T>> boundsAsInterfaces(Type[] bounds) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type : bounds) {
            SimpleTypeToken simpleTypeToken = new SimpleTypeToken(type);
            if (simpleTypeToken.getRawType().isInterface()) {
                builder.add(simpleTypeToken);
            }
        }
        return builder.build();
    }

    public final Invokable<T, T> constructor(Constructor<?> constructor) {
        Preconditions.checkArgument(constructor.getDeclaringClass() == getRawType(), "%s not declared by %s", constructor, getRawType());
        return new Invokable.ConstructorInvokable<T>(constructor) { // from class: com.google.common.reflect.TypeToken.2
            @Override // com.google.common.reflect.Invokable.ConstructorInvokable, com.google.common.reflect.Invokable
            public Type[] getGenericExceptionTypes() {
                TypeResolver covariantTypeResolver = TypeToken.this.getCovariantTypeResolver();
                Type[] genericExceptionTypes = this.constructor.getGenericExceptionTypes();
                covariantTypeResolver.resolveTypesInPlace(genericExceptionTypes);
                return genericExceptionTypes;
            }

            @Override // com.google.common.reflect.Invokable.ConstructorInvokable, com.google.common.reflect.Invokable
            public Type[] getGenericParameterTypes() {
                TypeResolver invariantTypeResolver = TypeToken.this.getInvariantTypeResolver();
                Type[] genericParameterTypes = super.getGenericParameterTypes();
                invariantTypeResolver.resolveTypesInPlace(genericParameterTypes);
                return genericParameterTypes;
            }

            @Override // com.google.common.reflect.Invokable.ConstructorInvokable, com.google.common.reflect.Invokable
            public Type getGenericReturnType() {
                return TypeToken.this.getCovariantTypeResolver().resolveType(super.getGenericReturnType());
            }

            @Override // com.google.common.reflect.Invokable
            public TypeToken<T> getOwnerType() {
                return TypeToken.this;
            }

            @Override // com.google.common.reflect.Invokable
            public String toString() {
                return getOwnerType() + "(" + new Joiner(", ").join(getGenericParameterTypes()) + ")";
            }
        };
    }

    public boolean equals(Object o) {
        if (o instanceof TypeToken) {
            return this.runtimeType.equals(((TypeToken) o).runtimeType);
        }
        return false;
    }

    public final TypeToken<? extends T> getArraySubtype(Class<?> subclass) {
        Class<?> componentType = subclass.getComponentType();
        if (componentType != null) {
            TypeToken<?> componentType2 = getComponentType();
            Objects.requireNonNull(componentType2);
            return new SimpleTypeToken(Types.JavaVersion.JAVA7.newArrayType(componentType2.getSubtype(componentType).runtimeType));
        }
        throw new IllegalArgumentException(subclass + " does not appear to be a subtype of " + this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final TypeToken<? super T> getArraySupertype(Class<? super T> supertype) {
        TypeToken<?> componentType = getComponentType();
        if (componentType != 0) {
            Class<?> componentType2 = supertype.getComponentType();
            Objects.requireNonNull(componentType2);
            return new SimpleTypeToken(Types.JavaVersion.JAVA7.newArrayType(componentType.getSupertype(componentType2).runtimeType));
        }
        throw new IllegalArgumentException(supertype + " isn't a super type of " + this);
    }

    public final TypeToken<?> getComponentType() {
        Type componentType = Types.getComponentType(this.runtimeType);
        if (componentType == null) {
            return null;
        }
        return new SimpleTypeToken(componentType);
    }

    public final TypeResolver getCovariantTypeResolver() {
        TypeResolver typeResolver = this.covariantTypeResolver;
        if (typeResolver != null) {
            return typeResolver;
        }
        TypeResolver typeResolverCovariantly = TypeResolver.covariantly(this.runtimeType);
        this.covariantTypeResolver = typeResolverCovariantly;
        return typeResolverCovariantly;
    }

    public final ImmutableList<TypeToken<? super T>> getGenericInterfaces() {
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return boundsAsInterfaces(((TypeVariable) type).getBounds());
        }
        if (type instanceof WildcardType) {
            return boundsAsInterfaces(((WildcardType) type).getUpperBounds());
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type2 : getRawType().getGenericInterfaces()) {
            builder.add(resolveSupertype(type2));
        }
        return builder.build();
    }

    public final TypeToken<? super T> getGenericSuperclass() {
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return boundAsSuperclass(((TypeVariable) type).getBounds()[0]);
        }
        if (type instanceof WildcardType) {
            return boundAsSuperclass(((WildcardType) type).getUpperBounds()[0]);
        }
        Type genericSuperclass = getRawType().getGenericSuperclass();
        if (genericSuperclass == null) {
            return null;
        }
        return (TypeToken<? super T>) resolveSupertype(genericSuperclass);
    }

    public final TypeResolver getInvariantTypeResolver() {
        TypeResolver typeResolver = this.invariantTypeResolver;
        if (typeResolver != null) {
            return typeResolver;
        }
        TypeResolver typeResolverInvariantly = TypeResolver.invariantly(this.runtimeType);
        this.invariantTypeResolver = typeResolverInvariantly;
        return typeResolverInvariantly;
    }

    public final Type getOwnerTypeIfPresent() {
        Type type = this.runtimeType;
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getOwnerType();
        }
        if (type instanceof Class) {
            return ((Class) type).getEnclosingClass();
        }
        return null;
    }

    public final Class<? super T> getRawType() {
        return getRawTypes().iterator().next();
    }

    public final ImmutableSet<Class<? super T>> getRawTypes() {
        final ImmutableSet.Builder builder = ImmutableSet.builder();
        new TypeVisitor(this) { // from class: com.google.common.reflect.TypeToken.4
            public final /* synthetic */ TypeToken this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitClass(Class<?> t) {
                builder.add(t);
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitGenericArrayType(GenericArrayType t) {
                builder.add(Types.getArrayClass(new SimpleTypeToken(t.getGenericComponentType()).getRawType()));
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitParameterizedType(ParameterizedType t) {
                builder.add((Class) t.getRawType());
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitTypeVariable(TypeVariable<?> t) {
                visit(t.getBounds());
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitWildcardType(WildcardType t) {
                visit(t.getUpperBounds());
            }
        }.visit(this.runtimeType);
        return builder.build();
    }

    public final TypeToken<? extends T> getSubtype(Class<?> subclass) {
        Preconditions.checkArgument(!(this.runtimeType instanceof TypeVariable), "Cannot get subtype of type variable <%s>", this);
        Type type = this.runtimeType;
        if (type instanceof WildcardType) {
            return getSubtypeFromLowerBounds(subclass, ((WildcardType) type).getLowerBounds());
        }
        if (isArray()) {
            return getArraySubtype(subclass);
        }
        Preconditions.checkArgument(getRawType().isAssignableFrom(subclass), "%s isn't a subclass of %s", subclass, this);
        SimpleTypeToken simpleTypeToken = new SimpleTypeToken(resolveTypeArgsForSubclass(subclass));
        Preconditions.checkArgument(simpleTypeToken.isSubtypeOf(this.runtimeType), "%s does not appear to be a subtype of %s", simpleTypeToken, this);
        return simpleTypeToken;
    }

    public final TypeToken<? extends T> getSubtypeFromLowerBounds(Class<?> subclass, Type[] lowerBounds) {
        if (lowerBounds.length > 0) {
            return new SimpleTypeToken(lowerBounds[0]).getSubtype(subclass);
        }
        throw new IllegalArgumentException(subclass + " isn't a subclass of " + this);
    }

    public final TypeToken<? super T> getSupertype(Class<? super T> cls) {
        Preconditions.checkArgument(someRawTypeIsSubclassOf(cls), "%s is not a super class of %s", cls, this);
        Type type = this.runtimeType;
        return type instanceof TypeVariable ? getSupertypeFromUpperBounds(cls, ((TypeVariable) type).getBounds()) : type instanceof WildcardType ? getSupertypeFromUpperBounds(cls, ((WildcardType) type).getUpperBounds()) : cls.isArray() ? getArraySupertype(cls) : (TypeToken<? super T>) resolveSupertype(toGenericType(cls).runtimeType);
    }

    public final TypeToken<? super T> getSupertypeFromUpperBounds(Class<? super T> supertype, Type[] upperBounds) {
        for (Type type : upperBounds) {
            SimpleTypeToken simpleTypeToken = new SimpleTypeToken(type);
            if (simpleTypeToken.isSubtypeOf(supertype)) {
                return simpleTypeToken.getSupertype(supertype);
            }
        }
        throw new IllegalArgumentException(supertype + " isn't a super type of " + this);
    }

    public final Type getType() {
        return this.runtimeType;
    }

    public final TypeToken<T>.TypeSet getTypes() {
        return new TypeSet();
    }

    public int hashCode() {
        return this.runtimeType.hashCode();
    }

    public final boolean is(Type formalType, TypeVariable<?> declaration) {
        if (this.runtimeType.equals(formalType)) {
            return true;
        }
        if (!(formalType instanceof WildcardType)) {
            return canonicalizeWildcardsInType(this.runtimeType).equals(canonicalizeWildcardsInType(formalType));
        }
        Types.WildcardTypeImpl wildcardTypeImpl = (Types.WildcardTypeImpl) canonicalizeWildcardType(declaration, (WildcardType) formalType);
        return every(Types.toArray(wildcardTypeImpl.upperBounds)).isSupertypeOf(this.runtimeType) && every(Types.toArray(wildcardTypeImpl.lowerBounds)).isSubtypeOf(this.runtimeType);
    }

    public final boolean isArray() {
        return getComponentType() != null;
    }

    public final boolean isOwnedBySubtypeOf(Type supertype) {
        Iterator<TypeToken<? super T>> it = new TypeSet().iterator();
        while (it.hasNext()) {
            Type ownerTypeIfPresent = it.next().getOwnerTypeIfPresent();
            if (ownerTypeIfPresent != null && new SimpleTypeToken(ownerTypeIfPresent).isSubtypeOf(supertype)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isPrimitive() {
        Type type = this.runtimeType;
        return (type instanceof Class) && ((Class) type).isPrimitive();
    }

    public final boolean isSubtypeOf(TypeToken<?> type) {
        return isSubtypeOf(type.runtimeType);
    }

    public final boolean isSubtypeOfArrayType(GenericArrayType supertype) {
        Type type = this.runtimeType;
        if (!(type instanceof Class)) {
            if (type instanceof GenericArrayType) {
                return new SimpleTypeToken(((GenericArrayType) type).getGenericComponentType()).isSubtypeOf(supertype.getGenericComponentType());
            }
            return false;
        }
        Class cls = (Class) type;
        if (cls.isArray()) {
            return new SimpleTypeToken(cls.getComponentType()).isSubtypeOf(supertype.getGenericComponentType());
        }
        return false;
    }

    public final boolean isSubtypeOfParameterizedType(ParameterizedType supertype) {
        Class<? super T> rawType = new SimpleTypeToken(supertype).getRawType();
        if (!someRawTypeIsSubclassOf(rawType)) {
            return false;
        }
        TypeVariable<Class<? super T>>[] typeParameters = rawType.getTypeParameters();
        Type[] actualTypeArguments = supertype.getActualTypeArguments();
        for (int i = 0; i < typeParameters.length; i++) {
            if (!new SimpleTypeToken(getCovariantTypeResolver().resolveType(typeParameters[i])).is(actualTypeArguments[i], typeParameters[i])) {
                return false;
            }
        }
        return Modifier.isStatic(((Class) supertype.getRawType()).getModifiers()) || supertype.getOwnerType() == null || isOwnedBySubtypeOf(supertype.getOwnerType());
    }

    public final boolean isSupertypeOf(Type type) {
        return new SimpleTypeToken(type).isSubtypeOf(this.runtimeType);
    }

    public final boolean isSupertypeOfArray(GenericArrayType subtype) {
        Type type = this.runtimeType;
        if (type instanceof Class) {
            Class cls = (Class) type;
            return !cls.isArray() ? cls.isAssignableFrom(Object[].class) : new SimpleTypeToken(subtype.getGenericComponentType()).isSubtypeOf(cls.getComponentType());
        }
        if (type instanceof GenericArrayType) {
            return new SimpleTypeToken(subtype.getGenericComponentType()).isSubtypeOf(((GenericArrayType) this.runtimeType).getGenericComponentType());
        }
        return false;
    }

    public final boolean isWrapper() {
        return Primitives.allWrapperTypes().contains(this.runtimeType);
    }

    public final Invokable<T, Object> method(Method method) {
        Preconditions.checkArgument(someRawTypeIsSubclassOf(method.getDeclaringClass()), "%s not declared by %s", method, this);
        return new Invokable.MethodInvokable<T>(method) { // from class: com.google.common.reflect.TypeToken.1
            @Override // com.google.common.reflect.Invokable.MethodInvokable, com.google.common.reflect.Invokable
            public Type[] getGenericExceptionTypes() {
                TypeResolver covariantTypeResolver = TypeToken.this.getCovariantTypeResolver();
                Type[] genericExceptionTypes = this.method.getGenericExceptionTypes();
                covariantTypeResolver.resolveTypesInPlace(genericExceptionTypes);
                return genericExceptionTypes;
            }

            @Override // com.google.common.reflect.Invokable.MethodInvokable, com.google.common.reflect.Invokable
            public Type[] getGenericParameterTypes() {
                TypeResolver invariantTypeResolver = TypeToken.this.getInvariantTypeResolver();
                Type[] genericParameterTypes = this.method.getGenericParameterTypes();
                invariantTypeResolver.resolveTypesInPlace(genericParameterTypes);
                return genericParameterTypes;
            }

            @Override // com.google.common.reflect.Invokable.MethodInvokable, com.google.common.reflect.Invokable
            public Type getGenericReturnType() {
                return TypeToken.this.getCovariantTypeResolver().resolveType(this.method.getGenericReturnType());
            }

            @Override // com.google.common.reflect.Invokable
            public TypeToken<T> getOwnerType() {
                return TypeToken.this;
            }

            @Override // com.google.common.reflect.Invokable
            public String toString() {
                return getOwnerType() + ExternalFourCCMapper.CODEC_NAME_SPLITTER + this.member.toString();
            }
        };
    }

    @CanIgnoreReturnValue
    public final TypeToken<T> rejectTypeVariables() {
        new TypeVisitor() { // from class: com.google.common.reflect.TypeToken.3
            @Override // com.google.common.reflect.TypeVisitor
            public void visitGenericArrayType(GenericArrayType type) {
                visit(type.getGenericComponentType());
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitParameterizedType(ParameterizedType type) {
                visit(type.getActualTypeArguments());
                visit(type.getOwnerType());
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitTypeVariable(TypeVariable<?> type) {
                throw new IllegalArgumentException(TypeToken.this.runtimeType + "contains a type variable and is not safe for the operation");
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitWildcardType(WildcardType type) {
                visit(type.getLowerBounds());
                visit(type.getUpperBounds());
            }
        }.visit(this.runtimeType);
        return this;
    }

    public final TypeToken<?> resolveSupertype(Type type) {
        SimpleTypeToken simpleTypeToken = new SimpleTypeToken(getCovariantTypeResolver().resolveType(type));
        simpleTypeToken.covariantTypeResolver = this.covariantTypeResolver;
        simpleTypeToken.invariantTypeResolver = this.invariantTypeResolver;
        return simpleTypeToken;
    }

    public final TypeToken<?> resolveType(Type type) {
        type.getClass();
        return new SimpleTypeToken(getInvariantTypeResolver().resolveType(type));
    }

    public final Type resolveTypeArgsForSubclass(Class<?> subclass) {
        if ((this.runtimeType instanceof Class) && (subclass.getTypeParameters().length == 0 || getRawType().getTypeParameters().length != 0)) {
            return subclass;
        }
        TypeToken genericType = toGenericType(subclass);
        return new TypeResolver().where(genericType.getSupertype(getRawType()).runtimeType, this.runtimeType).resolveType(genericType.runtimeType);
    }

    public final boolean someRawTypeIsSubclassOf(Class<?> superclass) {
        UnmodifiableIterator<Class<? super T>> it = getRawTypes().iterator();
        while (it.hasNext()) {
            if (superclass.isAssignableFrom(it.next())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return Types.toString(this.runtimeType);
    }

    public final TypeToken<T> unwrap() {
        return isWrapper() ? new SimpleTypeToken(Primitives.unwrap((Class) this.runtimeType)) : this;
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParam, Class<X> typeArg) {
        return where(typeParam, new SimpleTypeToken(typeArg));
    }

    public final TypeToken<T> wrap() {
        return isPrimitive() ? new SimpleTypeToken(Primitives.wrap((Class) this.runtimeType)) : this;
    }

    public Object writeReplace() {
        return new SimpleTypeToken(new TypeResolver().resolveType(this.runtimeType));
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class ClassSet extends TypeToken<T>.TypeSet {
        public static final long serialVersionUID = 0;
        public transient ImmutableSet<TypeToken<? super T>> classes;

        public ClassSet() {
            super();
        }

        private Object readResolve() {
            TypeToken typeToken = TypeToken.this;
            typeToken.getClass();
            return new TypeSet().classes();
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet interfaces() {
            throw new UnsupportedOperationException("classes().interfaces() not supported.");
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public Set<Class<? super T>> rawTypes() {
            TypeCollector<Class<?>> typeCollector = TypeCollector.FOR_RAW_TYPE;
            typeCollector.getClass();
            return ImmutableSet.copyOf((Collection) new TypeCollector.AnonymousClass3(typeCollector).collectTypes((Iterable) TypeToken.this.getRawTypes()));
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.classes;
            if (immutableSet != null) {
                return immutableSet;
            }
            TypeCollector<TypeToken<?>> typeCollector = TypeCollector.FOR_GENERIC_TYPE;
            typeCollector.getClass();
            ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(new TypeCollector.AnonymousClass3(typeCollector).collectTypes(TypeToken.this)).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
            this.classes = set;
            return set;
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet classes() {
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class InterfaceSet extends TypeToken<T>.TypeSet {
        public static final long serialVersionUID = 0;
        public final transient TypeToken<T>.TypeSet allTypes;
        public transient ImmutableSet<TypeToken<? super T>> interfaces;

        public InterfaceSet(TypeToken<T>.TypeSet allTypes) {
            super();
            this.allTypes = allTypes;
        }

        private Object readResolve() {
            TypeToken typeToken = TypeToken.this;
            typeToken.getClass();
            return new TypeSet().interfaces();
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet classes() {
            throw new UnsupportedOperationException("interfaces().classes() not supported.");
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public Set<Class<? super T>> rawTypes() {
            return FluentIterable.from(TypeCollector.FOR_RAW_TYPE.collectTypes(TypeToken.this.getRawTypes())).filter(new TypeToken$InterfaceSet$$ExternalSyntheticLambda0()).toSet();
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.interfaces;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(this.allTypes).filter(TypeFilter.INTERFACE_ONLY).toSet();
            this.interfaces = set;
            return set;
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet interfaces() {
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class TypeSet extends ForwardingSet<TypeToken<? super T>> implements Serializable {
        public static final long serialVersionUID = 0;
        public transient ImmutableSet<TypeToken<? super T>> types;

        public TypeSet() {
        }

        public TypeToken<T>.TypeSet classes() {
            return new ClassSet();
        }

        public TypeToken<T>.TypeSet interfaces() {
            return new InterfaceSet(this);
        }

        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf((Collection) TypeCollector.FOR_RAW_TYPE.collectTypes(TypeToken.this.getRawTypes()));
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.types;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(TypeCollector.FOR_GENERIC_TYPE.collectTypes(TypeToken.this)).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
            this.types = set;
            return set;
        }
    }

    public static TypeToken<?> of(Type type) {
        return new SimpleTypeToken(type);
    }

    public final boolean isSubtypeOf(Type supertype) {
        supertype.getClass();
        if (supertype instanceof WildcardType) {
            return any(((WildcardType) supertype).getLowerBounds()).isSupertypeOf(this.runtimeType);
        }
        Type type = this.runtimeType;
        if (type instanceof WildcardType) {
            return any(((WildcardType) type).getUpperBounds()).isSubtypeOf(supertype);
        }
        if (type instanceof TypeVariable) {
            return type.equals(supertype) || any(((TypeVariable) this.runtimeType).getBounds()).isSubtypeOf(supertype);
        }
        if (type instanceof GenericArrayType) {
            return new SimpleTypeToken(supertype).isSupertypeOfArray((GenericArrayType) this.runtimeType);
        }
        if (supertype instanceof Class) {
            return someRawTypeIsSubclassOf((Class) supertype);
        }
        if (supertype instanceof ParameterizedType) {
            return isSubtypeOfParameterizedType((ParameterizedType) supertype);
        }
        if (supertype instanceof GenericArrayType) {
            return isSubtypeOfArrayType((GenericArrayType) supertype);
        }
        return false;
    }

    public TypeToken(Class<?> declaringClass) {
        Type typeCapture = capture();
        if (typeCapture instanceof Class) {
            this.runtimeType = typeCapture;
        } else {
            this.runtimeType = TypeResolver.covariantly(declaringClass).resolveType(typeCapture);
        }
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParam, TypeToken<X> typeArg) {
        return new SimpleTypeToken(new TypeResolver().where(ImmutableMap.of(new TypeResolver.TypeVariableKey(typeParam.typeVariable), typeArg.runtimeType)).resolveType(this.runtimeType));
    }

    public final boolean isSupertypeOf(TypeToken<?> type) {
        return type.isSubtypeOf(this.runtimeType);
    }

    public TypeToken(Type type) {
        type.getClass();
        this.runtimeType = type;
    }
}
