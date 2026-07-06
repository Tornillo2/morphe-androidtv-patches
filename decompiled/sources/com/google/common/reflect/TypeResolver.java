package com.google.common.reflect;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.reflect.Types;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TypeResolver {
    public final TypeTable typeTable;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TypeMappingIntrospector extends TypeVisitor {
        public final Map<TypeVariableKey, Type> mappings = new HashMap();

        public static ImmutableMap<TypeVariableKey, Type> getTypeMappings(Type contextType) {
            contextType.getClass();
            TypeMappingIntrospector typeMappingIntrospector = new TypeMappingIntrospector();
            typeMappingIntrospector.visit(contextType);
            return ImmutableMap.copyOf((Map) typeMappingIntrospector.mappings);
        }

        public final void map(TypeVariableKey var, Type arg) {
            if (this.mappings.containsKey(var)) {
                return;
            }
            Type type = arg;
            while (type != null) {
                if (var.equalsType(type)) {
                    while (arg != null) {
                        arg = this.mappings.remove(TypeVariableKey.forLookup(arg));
                    }
                    return;
                }
                type = this.mappings.get(TypeVariableKey.forLookup(type));
            }
            this.mappings.put(var, arg);
        }

        @Override // com.google.common.reflect.TypeVisitor
        public void visitClass(Class<?> clazz) {
            visit(clazz.getGenericSuperclass());
            visit(clazz.getGenericInterfaces());
        }

        @Override // com.google.common.reflect.TypeVisitor
        public void visitParameterizedType(ParameterizedType parameterizedType) {
            Class cls = (Class) parameterizedType.getRawType();
            TypeVariable[] typeParameters = cls.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Preconditions.checkState(typeParameters.length == actualTypeArguments.length);
            for (int i = 0; i < typeParameters.length; i++) {
                map(new TypeVariableKey(typeParameters[i]), actualTypeArguments[i]);
            }
            visit(cls);
            visit(parameterizedType.getOwnerType());
        }

        @Override // com.google.common.reflect.TypeVisitor
        public void visitTypeVariable(TypeVariable<?> t) {
            visit(t.getBounds());
        }

        @Override // com.google.common.reflect.TypeVisitor
        public void visitWildcardType(WildcardType t) {
            visit(t.getUpperBounds());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TypeVariableKey {
        public final TypeVariable<?> var;

        public TypeVariableKey(TypeVariable<?> var) {
            var.getClass();
            this.var = var;
        }

        public static TypeVariableKey forLookup(Type t) {
            if (t instanceof TypeVariable) {
                return new TypeVariableKey((TypeVariable) t);
            }
            return null;
        }

        public boolean equals(Object obj) {
            if (obj instanceof TypeVariableKey) {
                return equalsTypeVariable(((TypeVariableKey) obj).var);
            }
            return false;
        }

        public boolean equalsType(Type type) {
            if (type instanceof TypeVariable) {
                return equalsTypeVariable((TypeVariable) type);
            }
            return false;
        }

        public final boolean equalsTypeVariable(TypeVariable<?> that) {
            return this.var.getGenericDeclaration().equals(that.getGenericDeclaration()) && this.var.getName().equals(that.getName());
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.var.getGenericDeclaration(), this.var.getName()});
        }

        public String toString() {
            return this.var.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class WildcardCapturer {
        public static final WildcardCapturer INSTANCE = new WildcardCapturer();
        public final AtomicInteger id;

        public final Type capture(Type type) {
            type.getClass();
            if ((type instanceof Class) || (type instanceof TypeVariable)) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                return Types.newArrayType(notForTypeVariable().capture(((GenericArrayType) type).getGenericComponentType()));
            }
            if (!(type instanceof ParameterizedType)) {
                if (!(type instanceof WildcardType)) {
                    throw new AssertionError("must have been one of the known types");
                }
                WildcardType wildcardType = (WildcardType) type;
                return wildcardType.getLowerBounds().length == 0 ? captureAsTypeVariable(wildcardType.getUpperBounds()) : type;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class cls = (Class) parameterizedType.getRawType();
            TypeVariable<?>[] typeParameters = cls.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (int i = 0; i < actualTypeArguments.length; i++) {
                actualTypeArguments[i] = forTypeVariable(typeParameters[i]).capture(actualTypeArguments[i]);
            }
            return Types.newParameterizedTypeWithOwner(notForTypeVariable().captureNullable(parameterizedType.getOwnerType()), cls, actualTypeArguments);
        }

        public TypeVariable<?> captureAsTypeVariable(Type[] upperBounds) {
            return Types.newArtificialTypeVariable(WildcardCapturer.class, "capture#" + this.id.incrementAndGet() + "-of ? extends " + Joiner.on('&').join(upperBounds), upperBounds);
        }

        public final Type captureNullable(Type type) {
            if (type == null) {
                return null;
            }
            return capture(type);
        }

        public final WildcardCapturer forTypeVariable(final TypeVariable<?> typeParam) {
            return new WildcardCapturer(this, this.id) { // from class: com.google.common.reflect.TypeResolver.WildcardCapturer.1
                public final /* synthetic */ WildcardCapturer this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.common.reflect.TypeResolver.WildcardCapturer
                public TypeVariable<?> captureAsTypeVariable(Type[] upperBounds) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(upperBounds));
                    linkedHashSet.addAll(Arrays.asList(typeParam.getBounds()));
                    if (linkedHashSet.size() > 1) {
                        linkedHashSet.remove(Object.class);
                    }
                    return super.captureAsTypeVariable((Type[]) linkedHashSet.toArray(new Type[0]));
                }
            };
        }

        public final WildcardCapturer notForTypeVariable() {
            return new WildcardCapturer(this.id);
        }

        public WildcardCapturer() {
            this(new AtomicInteger());
        }

        public WildcardCapturer(AtomicInteger id) {
            this.id = id;
        }
    }

    public static TypeResolver covariantly(Type contextType) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(contextType));
    }

    public static <T> T expectArgument(Class<T> type, Object arg) {
        try {
            return type.cast(arg);
        } catch (ClassCastException unused) {
            throw new IllegalArgumentException(arg + " is not a " + type.getSimpleName());
        }
    }

    public static TypeResolver invariantly(Type contextType) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(WildcardCapturer.INSTANCE.capture(contextType)));
    }

    public static void populateTypeMappings(final Map<TypeVariableKey, Type> mappings, Type from, final Type to) {
        if (from.equals(to)) {
            return;
        }
        new TypeVisitor() { // from class: com.google.common.reflect.TypeResolver.1
            @Override // com.google.common.reflect.TypeVisitor
            public void visitClass(Class<?> fromClass) {
                if (to instanceof WildcardType) {
                    return;
                }
                throw new IllegalArgumentException("No type mapping from " + fromClass + " to " + to);
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitGenericArrayType(GenericArrayType fromArrayType) {
                Type type = to;
                if (type instanceof WildcardType) {
                    return;
                }
                Type componentType = Types.getComponentType(type);
                Preconditions.checkArgument(componentType != null, "%s is not an array type.", to);
                TypeResolver.populateTypeMappings(mappings, fromArrayType.getGenericComponentType(), componentType);
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitParameterizedType(ParameterizedType fromParameterizedType) {
                Type type = to;
                if (type instanceof WildcardType) {
                    return;
                }
                ParameterizedType parameterizedType = (ParameterizedType) TypeResolver.expectArgument(ParameterizedType.class, type);
                if (fromParameterizedType.getOwnerType() != null && parameterizedType.getOwnerType() != null) {
                    TypeResolver.populateTypeMappings(mappings, fromParameterizedType.getOwnerType(), parameterizedType.getOwnerType());
                }
                Preconditions.checkArgument(fromParameterizedType.getRawType().equals(parameterizedType.getRawType()), "Inconsistent raw type: %s vs. %s", fromParameterizedType, to);
                Type[] actualTypeArguments = fromParameterizedType.getActualTypeArguments();
                Type[] actualTypeArguments2 = parameterizedType.getActualTypeArguments();
                Preconditions.checkArgument(actualTypeArguments.length == actualTypeArguments2.length, "%s not compatible with %s", fromParameterizedType, parameterizedType);
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    TypeResolver.populateTypeMappings(mappings, actualTypeArguments[i], actualTypeArguments2[i]);
                }
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitTypeVariable(TypeVariable<?> typeVariable) {
                mappings.put(new TypeVariableKey(typeVariable), to);
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitWildcardType(WildcardType fromWildcardType) {
                Type type = to;
                if (type instanceof WildcardType) {
                    WildcardType wildcardType = (WildcardType) type;
                    Type[] upperBounds = fromWildcardType.getUpperBounds();
                    Type[] upperBounds2 = wildcardType.getUpperBounds();
                    Type[] lowerBounds = fromWildcardType.getLowerBounds();
                    Type[] lowerBounds2 = wildcardType.getLowerBounds();
                    Preconditions.checkArgument(upperBounds.length == upperBounds2.length && lowerBounds.length == lowerBounds2.length, "Incompatible type: %s vs. %s", fromWildcardType, to);
                    for (int i = 0; i < upperBounds.length; i++) {
                        TypeResolver.populateTypeMappings(mappings, upperBounds[i], upperBounds2[i]);
                    }
                    for (int i2 = 0; i2 < lowerBounds.length; i2++) {
                        TypeResolver.populateTypeMappings(mappings, lowerBounds[i2], lowerBounds2[i2]);
                    }
                }
            }
        }.visit(from);
    }

    public final Type resolveGenericArrayType(GenericArrayType type) {
        return Types.newArrayType(resolveType(type.getGenericComponentType()));
    }

    public final ParameterizedType resolveParameterizedType(ParameterizedType type) {
        Type ownerType = type.getOwnerType();
        return Types.newParameterizedTypeWithOwner(ownerType == null ? null : resolveType(ownerType), (Class) resolveType(type.getRawType()), resolveTypes(type.getActualTypeArguments()));
    }

    public Type resolveType(Type type) {
        type.getClass();
        return type instanceof TypeVariable ? this.typeTable.resolve((TypeVariable) type) : type instanceof ParameterizedType ? resolveParameterizedType((ParameterizedType) type) : type instanceof GenericArrayType ? Types.newArrayType(resolveType(((GenericArrayType) type).getGenericComponentType())) : type instanceof WildcardType ? resolveWildcardType((WildcardType) type) : type;
    }

    public final Type[] resolveTypes(Type[] types) {
        Type[] typeArr = new Type[types.length];
        for (int i = 0; i < types.length; i++) {
            typeArr[i] = resolveType(types[i]);
        }
        return typeArr;
    }

    @CanIgnoreReturnValue
    public Type[] resolveTypesInPlace(Type[] types) {
        for (int i = 0; i < types.length; i++) {
            types[i] = resolveType(types[i]);
        }
        return types;
    }

    public final WildcardType resolveWildcardType(WildcardType type) {
        return new Types.WildcardTypeImpl(resolveTypes(type.getLowerBounds()), resolveTypes(type.getUpperBounds()));
    }

    public TypeResolver where(Map<TypeVariableKey, ? extends Type> mappings) {
        return new TypeResolver(this.typeTable.where(mappings));
    }

    public TypeResolver() {
        this.typeTable = new TypeTable();
    }

    public TypeResolver where(Type formal, Type actual) {
        HashMap map = new HashMap();
        formal.getClass();
        actual.getClass();
        populateTypeMappings(map, formal, actual);
        return where(map);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TypeTable {
        public final ImmutableMap<TypeVariableKey, Type> map;

        public TypeTable() {
            this.map = RegularImmutableMap.EMPTY;
        }

        public final Type resolve(final TypeVariable<?> var) {
            return resolveInternal(var, new TypeTable(this) { // from class: com.google.common.reflect.TypeResolver.TypeTable.1
                public final /* synthetic */ TypeTable this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.common.reflect.TypeResolver.TypeTable
                public Type resolveInternal(TypeVariable<?> intermediateVar, TypeTable forDependent) {
                    return intermediateVar.getGenericDeclaration().equals(var.getGenericDeclaration()) ? intermediateVar : this.resolveInternal(intermediateVar, forDependent);
                }
            });
        }

        public Type resolveInternal(TypeVariable<?> var, TypeTable forDependants) {
            Type type = this.map.get(new TypeVariableKey(var));
            if (type != null) {
                return new TypeResolver(forDependants).resolveType(type);
            }
            Type[] bounds = var.getBounds();
            if (bounds.length != 0) {
                Type[] typeArrResolveTypes = new TypeResolver(forDependants).resolveTypes(bounds);
                if (!Types.NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY || !Arrays.equals(bounds, typeArrResolveTypes)) {
                    return Types.newArtificialTypeVariable(var.getGenericDeclaration(), var.getName(), typeArrResolveTypes);
                }
            }
            return var;
        }

        public final TypeTable where(Map<TypeVariableKey, ? extends Type> mappings) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            builder.putAll(this.map);
            for (Map.Entry<TypeVariableKey, ? extends Type> entry : mappings.entrySet()) {
                TypeVariableKey key = entry.getKey();
                Type value = entry.getValue();
                Preconditions.checkArgument(true ^ key.equalsType(value), "Type variable %s bound to itself", key);
                builder.put(key, value);
            }
            return new TypeTable(builder.build(true));
        }

        public TypeTable(ImmutableMap<TypeVariableKey, Type> map) {
            this.map = map;
        }
    }

    public TypeResolver(TypeTable typeTable) {
        this.typeTable = typeTable;
    }
}
