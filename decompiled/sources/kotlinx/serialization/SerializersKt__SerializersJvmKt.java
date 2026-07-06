package kotlinx.serialization;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.PlatformKt;
import kotlinx.serialization.internal.PrimitivesKt;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nSerializersJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersJvm.kt\nkotlinx/serialization/SerializersKt__SerializersJvmKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,201:1\n11158#2:202\n11493#2,3:203\n1557#3:206\n1628#3,3:207\n37#4:210\n36#4,3:211\n1#5:214\n*S KotlinDebug\n*F\n+ 1 SerializersJvm.kt\nkotlinx/serialization/SerializersKt__SerializersJvmKt\n*L\n113#1:202\n113#1:203,3\n140#1:206\n140#1:207,3\n169#1:210\n169#1:211,3\n*E\n"})
public final /* synthetic */ class SerializersKt__SerializersJvmKt {
    public static final KSerializer<Object> genericArraySerializer$SerializersKt__SerializersJvmKt(SerializersModule serializersModule, GenericArrayType genericArrayType, boolean z) {
        KSerializer<Object> kSerializerSerializerOrNull;
        KClass orCreateKotlinClass;
        Type genericComponentType = genericArrayType.getGenericComponentType();
        if (genericComponentType instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) genericComponentType).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
            genericComponentType = (Type) ArraysKt___ArraysKt.first(upperBounds);
        }
        Intrinsics.checkNotNull(genericComponentType);
        if (z) {
            kSerializerSerializerOrNull = serializer(serializersModule, genericComponentType);
        } else {
            kSerializerSerializerOrNull = serializerOrNull(serializersModule, genericComponentType);
            if (kSerializerSerializerOrNull == null) {
                return null;
            }
        }
        if (genericComponentType instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) genericComponentType).getRawType();
            Intrinsics.checkNotNull(rawType, "null cannot be cast to non-null type java.lang.Class<*>");
            orCreateKotlinClass = Reflection.getOrCreateKotlinClass((Class) rawType);
        } else {
            if (!(genericComponentType instanceof KClass)) {
                throw new IllegalStateException("unsupported type in GenericArray: " + Reflection.getOrCreateKotlinClass(genericComponentType.getClass()));
            }
            orCreateKotlinClass = (KClass) genericComponentType;
        }
        Intrinsics.checkNotNull(orCreateKotlinClass, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
        return BuiltinSerializersKt.ArraySerializer(orCreateKotlinClass, kSerializerSerializerOrNull);
    }

    public static final Class<?> prettyClass$SerializersKt__SerializersJvmKt(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            Intrinsics.checkNotNullExpressionValue(rawType, "getRawType(...)");
            return prettyClass$SerializersKt__SerializersJvmKt(rawType);
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
            Object objFirst = ArraysKt___ArraysKt.first(upperBounds);
            Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
            return prettyClass$SerializersKt__SerializersJvmKt((Type) objFirst);
        }
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            Intrinsics.checkNotNullExpressionValue(genericComponentType, "getGenericComponentType(...)");
            return prettyClass$SerializersKt__SerializersJvmKt(genericComponentType);
        }
        throw new IllegalArgumentException("type should be an instance of Class<?>, GenericArrayType, ParametrizedType or WildcardType, but actual argument " + type + " has type " + Reflection.getOrCreateKotlinClass(type.getClass()));
    }

    public static final <T> KSerializer<T> reflectiveOrContextual$SerializersKt__SerializersJvmKt(SerializersModule serializersModule, Class<T> cls, List<? extends KSerializer<Object>> list) throws IllegalAccessException, InvocationTargetException {
        KSerializer[] kSerializerArr = (KSerializer[]) list.toArray(new KSerializer[0]);
        KSerializer<T> kSerializerConstructSerializerForGivenTypeArgs = PlatformKt.constructSerializerForGivenTypeArgs(cls, (KSerializer<Object>[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
        if (kSerializerConstructSerializerForGivenTypeArgs != null) {
            return kSerializerConstructSerializerForGivenTypeArgs;
        }
        KClass<T> orCreateKotlinClass = Reflection.getOrCreateKotlinClass(cls);
        KSerializer<T> kSerializerBuiltinSerializerOrNull = PrimitivesKt.builtinSerializerOrNull(orCreateKotlinClass);
        if (kSerializerBuiltinSerializerOrNull != null) {
            return kSerializerBuiltinSerializerOrNull;
        }
        KSerializer<T> contextual = serializersModule.getContextual(orCreateKotlinClass, list);
        if (contextual != null) {
            return contextual;
        }
        if (cls.isInterface()) {
            return new PolymorphicSerializer(Reflection.factory.getOrCreateKotlinClass(cls));
        }
        return null;
    }

    @NotNull
    public static final KSerializer<Object> serializer(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return serializer(SerializersModuleKt.getEmptySerializersModule(), type);
    }

    public static final KSerializer<Object> serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(SerializersModule serializersModule, Type type, boolean z) {
        ArrayList arrayList;
        if (type instanceof GenericArrayType) {
            return genericArraySerializer$SerializersKt__SerializersJvmKt(serializersModule, (GenericArrayType) type, z);
        }
        if (type instanceof Class) {
            return typeSerializer$SerializersKt__SerializersJvmKt(serializersModule, (Class) type, z);
        }
        int i = 0;
        if (!(type instanceof ParameterizedType)) {
            if (type instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
                Object objFirst = ArraysKt___ArraysKt.first(upperBounds);
                Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
                return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt$default(serializersModule, (Type) objFirst, false, 2, null);
            }
            throw new IllegalArgumentException("type should be an instance of Class<?>, GenericArrayType, ParametrizedType or WildcardType, but actual argument " + type + " has type " + Reflection.getOrCreateKotlinClass(type.getClass()));
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type rawType = parameterizedType.getRawType();
        Intrinsics.checkNotNull(rawType, "null cannot be cast to non-null type java.lang.Class<*>");
        Class cls = (Class) rawType;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Intrinsics.checkNotNull(actualTypeArguments);
        if (z) {
            arrayList = new ArrayList(actualTypeArguments.length);
            for (Type type2 : actualTypeArguments) {
                Intrinsics.checkNotNull(type2);
                arrayList.add(serializer(serializersModule, type2));
            }
        } else {
            arrayList = new ArrayList(actualTypeArguments.length);
            for (Type type3 : actualTypeArguments) {
                Intrinsics.checkNotNull(type3);
                KSerializer<Object> kSerializerSerializerOrNull = serializerOrNull(serializersModule, type3);
                if (kSerializerSerializerOrNull == null) {
                    return null;
                }
                arrayList.add(kSerializerSerializerOrNull);
            }
        }
        if (Set.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.SetSerializer((KSerializer) arrayList.get(0));
        }
        if (List.class.isAssignableFrom(cls) || Collection.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.ListSerializer((KSerializer) arrayList.get(0));
        }
        if (Map.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.MapSerializer((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1));
        }
        if (Map.Entry.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.MapEntrySerializer((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1));
        }
        if (Pair.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.PairSerializer((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1));
        }
        if (Triple.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.TripleSerializer((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1), (KSerializer) arrayList.get(2));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            KSerializer kSerializer = (KSerializer) obj;
            Intrinsics.checkNotNull(kSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any?>");
            arrayList2.add(kSerializer);
        }
        return reflectiveOrContextual$SerializersKt__SerializersJvmKt(serializersModule, cls, arrayList2);
    }

    public static /* synthetic */ KSerializer serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt$default(SerializersModule serializersModule, Type type, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(serializersModule, type, z);
    }

    @Nullable
    public static final KSerializer<Object> serializerOrNull(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return serializerOrNull(SerializersModuleKt.getEmptySerializersModule(), type);
    }

    public static final KSerializer<Object> typeSerializer$SerializersKt__SerializersJvmKt(SerializersModule serializersModule, Class<?> cls, boolean z) {
        KSerializer<Object> kSerializerSerializerOrNull;
        if (!cls.isArray() || cls.getComponentType().isPrimitive()) {
            return reflectiveOrContextual$SerializersKt__SerializersJvmKt(serializersModule, cls, EmptyList.INSTANCE);
        }
        Class<?> componentType = cls.getComponentType();
        Intrinsics.checkNotNullExpressionValue(componentType, "getComponentType(...)");
        if (z) {
            kSerializerSerializerOrNull = serializer(serializersModule, componentType);
        } else {
            kSerializerSerializerOrNull = serializerOrNull(serializersModule, componentType);
            if (kSerializerSerializerOrNull == null) {
                return null;
            }
        }
        return BuiltinSerializersKt.ArraySerializer(Reflection.getOrCreateKotlinClass(componentType), kSerializerSerializerOrNull);
    }

    @NotNull
    public static final KSerializer<Object> serializer(@NotNull SerializersModule serializersModule, @NotNull Type type) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        KSerializer<Object> kSerializerSerializerByJavaTypeImpl$SerializersKt__SerializersJvmKt = serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(serializersModule, type, true);
        if (kSerializerSerializerByJavaTypeImpl$SerializersKt__SerializersJvmKt != null) {
            return kSerializerSerializerByJavaTypeImpl$SerializersKt__SerializersJvmKt;
        }
        PlatformKt.serializerNotRegistered(prettyClass$SerializersKt__SerializersJvmKt(type));
        throw null;
    }

    @Nullable
    public static final KSerializer<Object> serializerOrNull(@NotNull SerializersModule serializersModule, @NotNull Type type) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(serializersModule, type, false);
    }
}
